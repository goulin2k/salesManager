package com.sales.service.impl;
    
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sales.common.Constants;
import com.sales.common.NormalFun;
import com.sales.dao.K3ReciveBilInfoDAO;
import com.sales.dao.SCustomerOwnerUserDAO;
import com.sales.dao.SInformationDAO;
import com.sales.dao.SLogDAO;
import com.sales.dao.SOrfqDAO;
import com.sales.dao.SaleBillDAO;
import com.sales.dao.TOrderDAO;
import com.sales.dao.TSaleBillStatusChangeDAO;
import com.sales.model.OrfqSale;
import com.sales.model.SCustomerOwnerUser;
import com.sales.model.SInformation;
import com.sales.model.SLog;
import com.sales.model.SaleBillStatus;
import com.sales.model.TOrder;
import com.sales.model.TReceiveBillInfo;
import com.sales.model.TSaleBillStatusChange;
import com.sales.service.SaleBillStatusChangeService;

public class SaleBillStatusChangeServiceImpl implements SaleBillStatusChangeService {
	
	private TSaleBillStatusChangeDAO saleStatusChangeDao;
	private SaleBillDAO saleBillDao;
	private TOrderDAO orderDao;
	private SCustomerOwnerUserDAO customerOwnerUserDao;
	private SInformationDAO informationDao;
	private SOrfqDAO orfqDao;
	private K3ReciveBilInfoDAO reciveBilInfoDao;
	private SLogDAO logDao;
	private static Logger logger = Logger.getLogger(SaleBillStatusChangeServiceImpl.class);
	
	public void dealK3Transaction(){
		//处理销售单据状态
		dealSaleBillStatusChange();
		//处理销售报价单
		dealOrfqStatus();
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void dealOrfqStatus(){	
		//上次审核时间
		Date checkDate = saleBillDao.getOrfqDealDate(Constants.ORFQ_CHECK_DATE);
		//审核修改时间
		Date checkLastDate = null;
		//审核单据号
		String checkBillNoes = "(";
		//反审核销售报价单
		List checkList = saleBillDao.getSaleCheck(NormalFun.formatDateTimeString(checkDate));
		
		if(checkList!=null && checkList.size()>0){
			for(int i=0; i<checkList.size(); i++){
				OrfqSale orfq = (OrfqSale) checkList.get(i);
				if(i == checkList.size()-1){
					checkLastDate = orfq.getDealDate();
				}
				checkBillNoes += "'" + orfq.getBillNo() + "',"; 
			}
			//修改销售管理系统报价单反审核状态
			if(checkBillNoes.length() > 1){
				checkBillNoes = checkBillNoes.substring(0, checkBillNoes.length() - 1) + ")";
				Map orfqCheckMap = new HashMap();
				orfqCheckMap.put("billNoes", checkBillNoes);
				orfqCheckMap.put("fStatus", Constants.ORFQ_VERIFY_NO);
				orfqDao.updateOrfqCheck(orfqCheckMap);
			}
			//修改审核时间
			if(checkLastDate != null){
				Map checkMap = new HashMap();
				checkMap.put("dealDate", NormalFun.formatDateTimeString(checkLastDate));
				checkMap.put("billType", Constants.ORFQ_CHECK_DATE);
				saleBillDao.updateOrfqDealDate(checkMap);
			}
			//添加日志
			SLog checkLog = new SLog();
			checkLog.setLogContent("销售报价单反审核，单据号：" + checkBillNoes);
			checkLog.setLogTime(new Date()); 
			checkLog.setTitle("销售报价单");
			checkLog.setTypeId(Constants.LOG_TYPE_SYNCHRONOUS);
			logDao.insert(checkLog);
		}
		//上次删除时间
		Date deleteDate = saleBillDao.getOrfqDealDate(Constants.ORFQ_DELETE_DATE);
		//删除修改时间
		Date deleteLastDate = null;
		//审核单据号
		String deleteBillNoes = "(";
		//k3删除销售报价单列表
		List deleteList = saleBillDao.getSaleDelete(NormalFun.formatDateTimeString(deleteDate));
		if(deleteList!=null && deleteList.size()>0){
			for(int i=0; i<deleteList.size(); i++){
				OrfqSale orfq = (OrfqSale) deleteList.get(i);
				if(i == deleteList.size()-1){
					deleteLastDate = orfq.getDealDate();
				}
				deleteBillNoes += "'" + orfq.getBillNo() + "',"; 		
			}
			//删除销售管理系统报价单
			String interIds = "(";
			if(deleteBillNoes.length() > 1){
				deleteBillNoes = deleteBillNoes.substring(0, deleteBillNoes.length() - 1) + ")";
				List interIdList = orfqDao.getInterIdBybillNo(deleteBillNoes);
				for(int j=0; j<interIdList.size(); j++){
					Integer interId = (Integer) interIdList.get(j);
					interIds += String.valueOf(interId) + ","; 
				}
				interIds = interIds.substring(0, interIds.length() - 1) + ")";
				if(interIds.length() > 3){
					orfqDao.deleteOrfqs(deleteBillNoes);
					orfqDao.deleteOrfqEntrys(interIds);
				}
			}
			//修改删除时间
			if(deleteLastDate != null){
				Map checkMap = new HashMap();
				checkMap.put("dealDate", NormalFun.formatDateTimeString(deleteLastDate));
				checkMap.put("billType", Constants.ORFQ_DELETE_DATE);
				saleBillDao.updateOrfqDealDate(checkMap);
			}
			//添加日志
			SLog deleteLog = new SLog();
			deleteLog.setLogContent("销售报价单删除，单据号：" + deleteBillNoes);
			deleteLog.setLogTime(new Date()); 
			deleteLog.setTitle("销售报价单");
			deleteLog.setTypeId(Constants.LOG_TYPE_SYNCHRONOUS);
			logDao.insert(deleteLog);
		}
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void dealSaleBillStatusChange(){
		//单据新增消息
		List statusList = saleBillDao.getSaleBillListNotRead();
		String statusIds = "(";
		for (int i = 0; i < statusList.size(); i++) {
			SaleBillStatus status = (SaleBillStatus) statusList.get(i);
			statusIds = statusIds + String.valueOf(status.getBillStatusId()) + ",";
			//销售订单消息
			if(status.getRelationType().intValue()==0 || status.getBillId()==null || status.getBillId().intValue()==0){
				//消息处理
				dealInfor(status.getOrderId(), status.getOrderId(), status.getRelationType(), 0);
			}
			else{
				//消息处理
				dealInfor(status.getOrderId(), status.getBillId(), status.getRelationType(), 0);
			}
		}
		//修改所有消息的已读状态
		if(statusIds.length() > 2){
			statusIds = statusIds.substring(0, statusIds.length()-1) + ")";
			saleBillDao.updateSaleBillRead(statusIds);
		} 
		
		//单据状态修改消息
		List statusChangeList = saleStatusChangeDao.getSaleBillStatusChange();
		String billStatusIds = "(";
		for (int i = 0; i < statusChangeList.size(); i++) {
			TSaleBillStatusChange change = (TSaleBillStatusChange) statusChangeList.get(i);
			billStatusIds = billStatusIds + String.valueOf(change.getBillStatusId()) + ","; 
			//销售订单消息
			if(change.getBillType().intValue() == 81){
				//消息处理
				dealInfor(change.getBillId(), change.getBillId(), 0, 1);
			}
			else{
				//状态转换
				Integer relationType = typeChange(change.getBillType());
				//通过状态修改的类型和id找到对应的状态关联对象列表
				List billList = getBillList(relationType, change.getBillId());
				for (int j = 0; j < billList.size(); j++) {
					SaleBillStatus billStatus = (SaleBillStatus) billList.get(j);
					//消息处理
					dealInfor(billStatus.getOrderId(), change.getBillId(), relationType, 1);
				}
			}
		} 
		
		//获取新增销售收款单信息，并生成通知消息
		List<TReceiveBillInfo> recvList = reciveBilInfoDao.getInsertedBills(); 
		logger.error("获取新增销售收款单信息: [" + recvList.size() + "]");
		for (Iterator iterator = recvList.iterator(); iterator.hasNext();) {
			TReceiveBillInfo tReceiveBillInfo = (TReceiveBillInfo) iterator
					.next();
			dealInsertedReviceBill(tReceiveBillInfo);
			
		}
		
		//修改所有消息的已读状态
		if(billStatusIds.length() > 2){
			billStatusIds = billStatusIds.substring(0, billStatusIds.length()-1) + ")";
			saleStatusChangeDao.updateSaleBillStatusChangeRead(billStatusIds);
		} 
	} 
	
	private Integer typeChange(Integer billType){
		if(billType == null){
			return 0;
		}
		//入库单
		else if(billType.intValue() == 1){
			return 3;
		}
		//出库单
		else if(billType.intValue() == 21){
			return 5;
		}
		//采购申请单
		else if(billType.intValue() == 70){
			return 2;
		}
		//发票
		else if(billType.intValue() == 80 || billType.intValue() == 86){
			return 6;
		}
		//发货通知单
		else if(billType.intValue() == 83){
			return 4;
		}
		//收款单
		else if(billType.intValue() == 1000){
			return 7;
		}
		//报价单
		else if(billType.intValue() == 84){
			return 1;
		}
		else{
			return 0;
		}
	}
	
	private List getBillList(Integer relationType, Integer billId){
		Map queryMap = new HashMap();
		queryMap.put("relationType", relationType); 
		queryMap.put("billId", billId);
		List billList = saleBillDao.getSaleBillListByBillId(queryMap);
		return billList;
	}
	
	private Integer getCustomerIdByOrderId(Integer orderId){
		Map queryOrderMap = new HashMap();
		queryOrderMap.put("orderId", orderId); 
		queryOrderMap.put("customerIds", null); 
		TOrder order = orderDao.getOrderById(queryOrderMap);
		if(order == null)
			return null;
		return order.getFCustID();
	}
	
	private boolean isSend(List inforList, Integer sendUserId){
		boolean send = false;
		for (int i = 0; i < inforList.size(); i++) {
			Integer userId = (Integer) inforList.get(i);
			if(userId.intValue() == sendUserId.intValue()){
				send = true;
				break;
			}
		}
		return send;
	}
	
	/**
	 *  新增插入新增销售收款单通知消息
	 * @param content
	 * @param billId
	 */
	private void insertInfor(String content, int billId) {
		String url = "receiveBill!show?receiveBill.fBillID=" + billId;
		
		SInformation infor = new SInformation();
		infor.setAddTime(new Timestamp(System.currentTimeMillis()));
		infor.setContent(content);
		infor.setSendUserId(1);
		infor.setStatus(Constants.INFO_STATUS_NEW);
		infor.setTitle("收款单新增");
		infor.setType(SInformation.BuzType.SALE_RCB.ordinal());
		infor.setImageUrl(url);
		infor.setBuzId(billId);
		informationDao.insert(infor);
		
	}
	//typeId:新增（0）、修改状态（1）
	private void insertInfor(Integer billId, Integer sendUserId, Integer relationType, 
			Integer orderId, Integer typeId){
		String title = null;
		String content = null;
		String url = null;
		int infoTye = 0;
		if(relationType == null){
			title = "";
			content = "";
			url = "";
		}
		else if(relationType.intValue() == 1){
			infoTye = 6;
			title = "销售订单"; 
			if(typeId.intValue() == 0){
				content = "单据关联状态通知：你的订单" + orderId + "在K3系统已生成，请及时跟踪查看";
			}
			else{
				content = "单据审核状态通知消息：你的订单" + orderId + "已审核，请及时跟踪查看";
			}
			url = "order!show?order.fInterID=" + billId;
		}
		else if(relationType.intValue() == 2){
			infoTye = 7;
			title = "采购申请单"; 
			url = "request!show?request.fInterId=" + billId;
		}
		else if(relationType.intValue() == 3){
			infoTye = 9;
			title = "采购入库单"; 
			url = "stockBill!show?stockBill.fInterID=" + billId;
		}
		else if(relationType.intValue() == 4){
			infoTye = 10;
			title = "销售发货通知单"; 
			url = "outStock!show?outStock.fInterID=" + billId;
		}
		else if(relationType.intValue() == 5){
			infoTye = 11;
			title = "销售出库单"; 
			url = "stockBillOut!show?stockBillOut.fInterID=" + billId;
		}
		else if(relationType.intValue() == 6){
			infoTye = 12;
			title = "销售发票"; 
			url = "sale!show?sale.fInterID=" + billId;
		}
		else if(relationType.intValue() == 7){
			infoTye = 13;
			title = "销售收款单"; 
			url = "receiveBill!show?receiveBill.fBillID=" + billId;
		}
		else if(relationType.intValue() == 11){
			infoTye = 8;
			title = "采购订单"; 
			url = "";
		}
		else{
			title = "";
			content = "";
			url = "";
		}
		if(relationType != null && relationType.intValue() != 1){ 
			if(typeId.intValue() == 1){
				content = "单据关联状态通知：你的订单" + orderId + "关联" +  title + billId + "在K3系统已生成，请及时跟踪查看";
			}
			else{
				content = "单据审核状态通知消息：你的订单" + orderId + "关联" +  title + billId + "已审核，请及时跟踪查看";
			}
		}
		SInformation infor = new SInformation();
		infor.setAddTime(new Timestamp(System.currentTimeMillis()));
		infor.setContent(content);
		infor.setSendUserId(sendUserId);
		infor.setStatus(Constants.INFO_STATUS_NEW);
		infor.setTitle(title);
		infor.setType(relationType.intValue()+5);
		infor.setImageUrl(url);
		infor.setBuzId(billId);
		informationDao.insert(infor);
	}
	
	private void dealInfor(Integer orderId, Integer billId, Integer relationType, Integer typeId){
		//通过订单id找到对应的客户
		Integer customerId = getCustomerIdByOrderId(orderId);
		if(customerId == null)
			return;
		List userList = customerOwnerUserDao.getUserListByCustomerId(customerId);
		for (int k = 0; k < userList.size(); k++) {
			SCustomerOwnerUser ownerUser = (SCustomerOwnerUser) userList.get(k); 
			//添加该消息
			insertInfor(billId, ownerUser.getUserId(), relationType, orderId, typeId); 
		}
	}
	
	/**
	 * 处理新增销售单通知消息
	 * @param bill
	 */
	private void dealInsertedReviceBill(TReceiveBillInfo bill){
		String content = "";
		//通过订单id找到对应的客户
		Integer customerId = bill.getCustomerId();
		if(customerId == null)
			return;
		List userList = customerOwnerUserDao.getUserListByCustomerId(customerId);
		for (int k = 0; k < userList.size(); k++) {
			SCustomerOwnerUser ownerUser = (SCustomerOwnerUser) userList.get(k); 
			content = "客户【" + bill.getCustomerName() + "】销售收款单 （" 
					+ bill.getNumber() + "）已生成，收款金额：" + NormalFun.formatCurrency(bill.getReceiveAmount()); 
			//添加该消息
			insertInfor(content, bill.getBillId()); 
		}
	}

	public TSaleBillStatusChangeDAO getSaleStatusChangeDao() {
		return saleStatusChangeDao;
	}

	public void setSaleStatusChangeDao(TSaleBillStatusChangeDAO saleStatusChangeDao) {
		this.saleStatusChangeDao = saleStatusChangeDao;
	}

	public SaleBillDAO getSaleBillDao() {
		return saleBillDao;
	}

	public void setSaleBillDao(SaleBillDAO saleBillDao) {
		this.saleBillDao = saleBillDao;
	}

	public TOrderDAO getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(TOrderDAO orderDao) {
		this.orderDao = orderDao;
	}

	public SCustomerOwnerUserDAO getCustomerOwnerUserDao() {
		return customerOwnerUserDao;
	}

	public void setCustomerOwnerUserDao(SCustomerOwnerUserDAO customerOwnerUserDao) {
		this.customerOwnerUserDao = customerOwnerUserDao;
	}

	public SInformationDAO getInformationDao() {
		return informationDao;
	}

	public void setInformationDao(SInformationDAO informationDao) {
		this.informationDao = informationDao;
	}

	public SOrfqDAO getOrfqDao() {
		return orfqDao;
	}

	public void setOrfqDao(SOrfqDAO orfqDao) {
		this.orfqDao = orfqDao;
	}

	public SLogDAO getLogDao() {
		return logDao;
	}

	public void setLogDao(SLogDAO logDao) {
		this.logDao = logDao;
	}

	/**
	 * @param reciveBilInfoDao the reciveBilInfoDao to set
	 */
	public void setReciveBilInfoDao(K3ReciveBilInfoDAO reciveBilInfoDao) {
		this.reciveBilInfoDao = reciveBilInfoDao;
	}

}
