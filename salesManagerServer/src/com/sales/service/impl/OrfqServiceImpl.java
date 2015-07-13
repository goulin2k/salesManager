package com.sales.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sales.BaseTestCase;
import com.sales.common.Constants;
import com.sales.dao.SCustomerOwnerUserDAO;
import com.sales.dao.SInformationDAO;
import com.sales.dao.SLogDAO;
import com.sales.dao.SOrfqDAO;
import com.sales.dao.SUserDAO;
import com.sales.dao.TOrfqDAO;
import com.sales.model.SCustomerOwnerUser;
import com.sales.model.SInformation;
import com.sales.model.SLog;
import com.sales.model.SUser;
import com.sales.model.TOrfq;
import com.sales.model.TOrfqEntry;
import com.sales.service.OrfqService;

public class OrfqServiceImpl implements OrfqService {
	
	private TOrfqDAO orfqDao;
	private SLogDAO logDao;
	private SOrfqDAO sorfqDao;
	private SUserDAO sUserDao;
	private SInformationDAO informationDao;
	private SCustomerOwnerUserDAO customerOwnerUserDao;
	
	protected static final Logger logger = (Logger) Logger
			.getLogger(OrfqServiceImpl.class); 

	public TOrfq getOrfqById(Integer orfqId, String customerIds){
		if(customerIds==null || customerIds.trim().equals("")){
			customerIds = "(-1)";
		}
		else if(Constants.USER_FILTER_MARK.equals(customerIds)){
			customerIds = null;
		}
		Map queryMap = new HashMap();
		queryMap.put("orfqId", orfqId); 
		queryMap.put("customerIds", customerIds); 
		TOrfq orfq = (TOrfq) orfqDao.getOrfqById(queryMap);
		return orfq;
	}
	
	public List getOrfqList(Integer pageNumber, Integer pageSize, TOrfq orfq, String customerIds){ 
		Integer startNumber = (pageNumber - 1) * pageSize;
		orfq.setStartNumber(startNumber);
		orfq.setPageSize(pageSize);
		if(customerIds==null || customerIds.trim().equals("")){
			customerIds = "(-1)";
		}
		else if(Constants.USER_FILTER_MARK.equals(customerIds)){
			customerIds = null;
		}
		orfq.setCustomerIds(customerIds);
		List orfqList = orfqDao.getOrfqList(orfq);
		return orfqList;
	}
	
	public Integer getOrfqCount(TOrfq orfq, String customerIds){ 
		if(customerIds==null || customerIds.trim().equals("")){
			customerIds = "(-1)";
		}
		else if(Constants.USER_FILTER_MARK.equals(customerIds)){
			customerIds = null;
		}
		orfq.setCustomerIds(customerIds);
		Integer orfqCount = orfqDao.getOrfqCount(orfq);
		return orfqCount;
	}
	
	//新生成单据编号
	@Transactional( propagation=Propagation.REQUIRED)
	public String getDesc(){
		//新增的单据号
		String fDesc = orfqDao.getOrfqDesc(); 
		//修改现有的最新单据号 + 1 
		String billNo = String.valueOf(Integer.parseInt(fDesc.substring(fDesc.indexOf("AQ+")+3, fDesc.length())) + 1);
		int billLength = 6-billNo.length();
		if(billNo.length() < 6){
			for(int j=0; j<billLength; j++){
				billNo = "0" + billNo;
			}
		}
		billNo = "AQ+" + billNo;
		orfqDao.updateOrfqBillNo(billNo);
		return fDesc;
	}
	
	@Transactional(propagation=Propagation.MANDATORY)
	public String insertOrfq(TOrfq orfq, List orfqEntryList){		
		if(orfq.getFBillerID() == null)
			throw(new RuntimeException("登录用户与K3未关联，不能进行单据操作！"));		
		//执行存储过程，获取最新当前单据 FInterID
		Integer fInterID = orfqDao.getNewInterId();
		//选择当前的单据号FCurNo
		String curNo = String.valueOf(orfqDao.getCurNo());
		//不足6位补足
		int noLength = 6 - curNo.length();
		if(curNo.length() < 6){
			for(int j=0; j<noLength; j++){
				curNo = "0" + curNo;
			}
		}
//		Integer fInterID = orfqDao.getInterId(Constants.ORFQ_BILL_NO_F + curNo) + 1;
		String billNO = Constants.ORFQ_BILL_NO_F;
		List interList = orfqDao.getBillNo();
		String t = null;
		for(int i=0; i<interList.size(); i++){
			String s = (String) interList.get(i);
			if(!Constants.ORFQ_BILL_NO_F.equals(s)){
				//下一位
				t = String.valueOf(Integer.parseInt(s) + 1);
				//不足6位补足
				int sLength = 6 - s.length();
				if(s.length() < 6){
					for(int j=0; j<sLength; j++){
						s = "0" + s;
					}
				}
				billNO += s;
			}
		} 
		orfq.setFInterID(fInterID);
		orfq.setFBillNo(billNO);
		//修改no
		orfqDao.updateOrfqId(); 
		//插入单据头
		orfqDao.insertOrfq(orfq); 
		//依据单据头id插入1条或多条分录
		
		int j = 0;
		for(int i=0; i<orfqEntryList.size(); i++){
			TOrfqEntry orfqEntry = new TOrfqEntry();
			orfqEntry = (TOrfqEntry) orfqEntryList.get(i); 
			orfqEntry.setFPrice(orfqEntry.getFAuxPrice());
			orfqEntry.setFAuxQtyFrom(orfqEntry.getFAuxQty());
			orfqEntry.setFAuxQtyTo(orfqEntry.getFAuxQty());
			orfqEntry.setFAuxTaxPriceDiscount(orfqEntry.getFAuxTaxPriceDiscount());
			orfqEntry.setFInterID(fInterID);
			orfqEntry.setFEntryID(++j);
			orfqDao.insertOrfqEntry(orfqEntry);
		}
		//插入单据审核状态表初始化该单据审核信息
		orfqDao.insertOrfqCheckStatus(fInterID);
		
		//插入单据审核记录表中初始化该单据的审核信息
		Map insertMap = new HashMap(); 
		insertMap.put("fInterID", fInterID);
		insertMap.put("fBillNo", orfq.getFBillNo());
		insertMap.put("fBillerID", orfq.getFBillerID());
		orfqDao.insertOrfqCheckRecords(insertMap);
		//修改现有的最新单据号 + 1  
		int billLength = 6 - t.length();
		if(t.length() < 6){
			for(int k=0; k<billLength; k++){
				t = "0" + t;
			}
		}
		String billNo = Constants.ORFQ_BILL_NO_F + t;
		orfqDao.updateOrfqBillNo(billNo);
		SLog log = new SLog();
		log.setLogContent("用户" + orfq.getFBillerID() + "新增销售报价单：" + orfq.getFBillNo());
		log.setLogTime(new Date());
		log.setOperateUserId(orfq.getFBillerID());
		log.setTitle("销售报价单");
		log.setTypeId(Constants.LOG_TYPE_DEAL);
		logDao.insert(log);
		
		logger.warn("新增K3报价单：\t" + orfq);
		for(int i=0; i<orfqEntryList.size(); i++){
			logger.warn("K3报价单明细信息：\t" + (TOrfqEntry)orfqEntryList.get(i));
		}
		return billNO;
	}
	
	public List getOrfqAllList(String billIds){
		List orfqList = orfqDao.getOrfqAllList("(" + billIds + ")");
		return orfqList;
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteOrfqById(Integer fInterID, String customerIds, Integer userId){
		if(customerIds==null || customerIds.trim().equals("")){
			customerIds = "(-1)";
		}
		else if(Constants.USER_FILTER_MARK.equals(customerIds)){
			customerIds = null;
		}
		Map deleteMap = new HashMap();
		deleteMap.put("fInterID", fInterID); 
		deleteMap.put("customerIds", customerIds); 
		Integer count = orfqDao.deleteOrfqById(deleteMap);
		SLog log = new SLog();
		if(count.intValue() == 1){
			orfqDao.deleteOrfqEntryById(fInterID);
			orfqDao.deleteICCCSById(fInterID);
			orfqDao.deleteICCCRById(fInterID);
			log.setLogContent("用户" + userId + "删除销售报价单：" + fInterID);
			logger.warn("用户" + userId + "删除销售报价单：" + fInterID);
		}
		else{
			log.setLogContent("用户" + userId + "试图删除销售报价单：" + fInterID + "失败");
		}
		
		log.setLogTime(new Date());
		log.setOperateUserId(userId);
		log.setTitle("销售报价单");
		log.setTypeId(Constants.LOG_TYPE_DEAL);
		logDao.insert(log);
	}
	
	public List getSOrfqList(Integer pageNumber, Integer pageSize, TOrfq orfq, String customerIds){ 
		Integer startNumber = (pageNumber - 1) * pageSize;
		orfq.setStartNumber(startNumber);
		orfq.setPageSize(pageSize);
		if(customerIds==null || customerIds.trim().equals("")){
			customerIds = "(-1)";
		}
		else if(Constants.USER_FILTER_MARK.equals(customerIds)){
			customerIds = null;
		}
		orfq.setCustomerIds(customerIds);
		List orfqList = sorfqDao.getOrfqList(orfq);
		return orfqList;
	}
	
	public Integer getSOrfqCount(TOrfq orfq, String customerIds){ 
		if(customerIds==null || customerIds.trim().equals("")){
			customerIds = "(-1)";
		}
		else if(Constants.USER_FILTER_MARK.equals(customerIds)){
			customerIds = null;
		}
		orfq.setCustomerIds(customerIds);
		Integer orfqCount = sorfqDao.getOrfqCount(orfq);
		return orfqCount;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void insertSOrfq(TOrfq orfq, List orfqEntryList, String userName){ 
		if(orfq.getFBillerID() == null)
			throw(new RuntimeException("登录用户与K3未关联，不能进行单据操作！"));
		//客户未分配，不允许添加报价单
		List<SCustomerOwnerUser> owerUserList = customerOwnerUserDao.getUserListByCustomerId(orfq.getFCustID()); 
		if(owerUserList!=null && owerUserList.size()>0){
			//插入单据头
			Integer fInterID = sorfqDao.insertOrfq(orfq);
			//依据单据头id插入1条或多条分录		
			int j = 0;
			for(int i=0; i<orfqEntryList.size(); i++){
				TOrfqEntry orfqEntry = (TOrfqEntry) orfqEntryList.get(i); 
				if(orfqEntry != null){
					
					orfqEntry.setFAmount(orfqEntry.getFAuxPrice() * orfqEntry.getFAuxQty());
					orfqEntry.setFAmountIncludeTax(orfqEntry.getFAuxPriceIncludeTax() * orfqEntry.getFAuxQty());
					orfqEntry.setFTaxAmount(orfqEntry.getFAmountIncludeTax() - orfqEntry.getFAmount());
					orfqEntry.setFDiscountAmt(orfqEntry.getFDescount()/100 * orfqEntry.getFAuxPrice() * orfqEntry.getFAuxQty());
					
					orfqEntry.setFPrice(orfqEntry.getFAuxPrice());
					orfqEntry.setFAuxQtyFrom(orfqEntry.getFAuxQty());
					orfqEntry.setFAuxQtyTo(orfqEntry.getFAuxQty());
					orfqEntry.setFInterID(fInterID);
					orfqEntry.setFEntryID(++j);
					sorfqDao.insertOrfqEntry(orfqEntry);
				}
			}
			for(int i=0; i<owerUserList.size(); i++){
				SCustomerOwnerUser owerUser = (SCustomerOwnerUser) owerUserList.get(i);
				//将审核人员定为相应的副总或者总经理
				if(owerUser.getSalegenId() != null){
					String title = "销售报价单";
					String content = "用户:" + userName + "新增销售报价单，请审核！";
					String url = "orfq!verify?orfq.fInterID=" + fInterID;
					insertInfor(title, content, url, owerUser.getSalegenId(), fInterID);
				}
			}
			SLog log = new SLog();
			log.setLogContent("用户" + userName + "新增销售报价单：" + orfq.getFBillNo());
			log.setLogTime(new Date());
			log.setOperateUserId(orfq.getFBillerID());
			log.setTitle("销售报价单");
			log.setTypeId(Constants.LOG_TYPE_DEAL);
			logDao.insert(log);
			
			logger.warn("新增待审核报价单：\t" + orfq);
			for(int i=0; i<orfqEntryList.size(); i++){
				logger.warn("待审核报价单明细信息：\t" + (TOrfqEntry)orfqEntryList.get(i));
			}
		}
	}

	public TOrfq getSOrfqById(Integer orfqId, String customerIds){
		if(customerIds==null || customerIds.trim().equals("")){
			customerIds = "(-1)";
		}
		else if(Constants.USER_FILTER_MARK.equals(customerIds)){
			customerIds = null;
		}
		Map queryMap = new HashMap();
		queryMap.put("orfqId", orfqId); 
		queryMap.put("customerIds", customerIds); 
		TOrfq orfq = (TOrfq) sorfqDao.getOrfqById(queryMap);
		return orfq;
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void verifySOrfq(Integer fInterID, Integer fStatus, SUser sUser, String customerIds){ 
		if(customerIds==null || customerIds.trim().equals("")){
			customerIds = "(-1)";
		}
		else if(Constants.USER_FILTER_MARK.equals(customerIds)){
			customerIds = null;
		}
		Map verifyMap = new HashMap(); 
		verifyMap.put("fInterID", fInterID);
		verifyMap.put("fStatus", fStatus);
		verifyMap.put("checkUserId", sUser.getUserId());
		verifyMap.put("checkerName", sUser.getUserName());
		verifyMap.put("customerIds", customerIds);
		Integer count = sorfqDao.verifySOrfq(verifyMap);
		SLog log = new SLog();
		if(count.intValue() == 1){
			log.setLogContent("用户" + sUser.getUserName() + "审核销售报价单：" + fInterID);
			String title = "销售报价单";
			String content = "";
			String url = "";
			Map queryMap = new HashMap();
			queryMap.put("orfqId", fInterID); 
			queryMap.put("customerIds", null); 
			TOrfq orfq = sorfqDao.getOrfqById(queryMap);	
			List financial = null;
			List logistics = null;
			//审核通过将数据写入k3
			if(fStatus.intValue() == Constants.ORFQ_VERIFY_YES.intValue()){		
				String billNo = insertOrfq(orfq, orfq.getOrfqEntryList());
				//修改销售管理系统里面报价单的编号
				TOrfq orfqBill = new TOrfq();
				orfqBill.setFInterID(fInterID);
				orfqBill.setFBillNo(billNo);
				sorfqDao.updateBillNoByInterId(orfqBill);
				content = "用户:" + sUser.getUserName() + "审核销售报价单[" + orfq.getFInterID() + "]通过！";
				url = "orfq!showSorfq?orfq.fInterID=" + fInterID;
				financial = sUserDao.getUserListByPositionId(Constants.ROLE_FINANCIAL);
				logistics = sUserDao.getUserListByPositionId(Constants.ROLE_LOGISTICS);
			}
			else{
				content = "用户:" + sUser.getUserName() + "审核销售报价单[" + orfq.getFInterID() + "]不通过！";
				url = "orfq!updateSOrfq?orfq.fInterID=" + fInterID;
			}  
			logger.warn(content);
			
			insertInfor(title, content, url, orfq.getUserId(),fInterID);
			//审核通过通知给财务主管和后勤部经理
			if(financial != null){
				for(int i=0; i<financial.size(); i++){
					SUser financialUser = (SUser) financial.get(i);
					insertInfor(title, content, url, financialUser.getUserId(),fInterID);
				}
			}
			if(logistics != null){
				for(int i=0; i<logistics.size(); i++){
					SUser logisticsUser = (SUser) logistics.get(i);
					insertInfor(title, content, url, logisticsUser.getUserId(),fInterID);
				}
			}
		}
		else{
			log.setLogContent("用户" + sUser.getUserName() + "试图审核销售报价单：" + fInterID + "失败");
		}
		log.setLogTime(new Date());
		log.setOperateUserId(sUser.getUserId());
		log.setTitle("销售报价单");
		log.setTypeId(Constants.LOG_TYPE_DEAL);
		logDao.insert(log);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteSOrfqById(Integer fInterID, String customerIds, Integer userId){
		if(customerIds==null || customerIds.trim().equals("")){
			customerIds = "(-1)";
		}
		else if(Constants.USER_FILTER_MARK.equals(customerIds)){
			customerIds = null;
		}
		Map deleteMap = new HashMap();
		deleteMap.put("fInterID", fInterID); 
		deleteMap.put("customerIds", customerIds); 
		Integer count = sorfqDao.deleteSOrfqById(deleteMap);
		SLog log = new SLog();
		if(count.intValue() == 1){
			sorfqDao.deleteSOrfqEntryById(fInterID); 
			log.setLogContent("用户" + userId + "删除销售报价单：" + fInterID);
		}
		else{
			log.setLogContent("用户" + userId + "试图删除销售报价单：" + fInterID + "失败");
		}
		log.setLogTime(new Date());
		log.setOperateUserId(userId);
		log.setTitle("销售报价单");
		log.setTypeId(Constants.LOG_TYPE_DEAL);
		logDao.insert(log);
		
		logger.warn("用户" + userId + "删除销售报价单：" + fInterID);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void dealSOrfq(TOrfq orfq, List orfqEntryList){ 
		orfq.setFStatus(Constants.ORFQ_VERIFY_NONE);
		sorfqDao.updateSOrfq(orfq);
		sorfqDao.deleteSOrfqEntryById(orfq.getFInterID());
		//依据单据头id插入1条或多条分录
		int j = 0;
		for(int i=0; i<orfqEntryList.size(); i++){
			TOrfqEntry orfqEntry = (TOrfqEntry) orfqEntryList.get(i); 
			if(orfqEntry != null){
				orfqEntry.setFPrice(orfqEntry.getFAuxPrice());
				orfqEntry.setFAuxQtyFrom(orfqEntry.getFAuxQty());
				orfqEntry.setFAuxQtyTo(orfqEntry.getFAuxQty());
				orfqEntry.setFInterID(orfq.getFInterID());
				orfqEntry.setFEntryID(j++);
				sorfqDao.insertOrfqEntry(orfqEntry);
			}
		}
		SLog log = new SLog();
		log.setLogContent("用户" + orfq.getFBillerID() + "修改销售报价单：" + orfq.getFBillNo());
		log.setLogTime(new Date());
		log.setOperateUserId(orfq.getFBillerID());
		log.setTitle("销售报价单");
		log.setTypeId(Constants.LOG_TYPE_DEAL);
		logDao.insert(log);
		
		logger.warn("用户" + orfq.getFBillerID() + "修改销售报价单：" + orfq.getFInterID());
	}

	private void insertInfor(String title, String content, String url,
			Integer sendUserId, Integer orfqId) {
		SInformation infor = new SInformation();
		infor.setAddTime(new Timestamp(System.currentTimeMillis()));
		infor.setContent(content);
		infor.setSendUserId(sendUserId);
		infor.setStatus(Constants.INFO_STATUS_NEW);
		infor.setTitle(title);
		infor.setType(SInformation.BuzType.SALE_ORF.ordinal());
		infor.setImageUrl(url);
		infor.setBuzId(orfqId);
		informationDao.insert(infor);
	}
	
	//修改k3的报价单信息
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateOrfq(TOrfq orfq, List orfqEntryList){ 
		orfq.setFStatus(Constants.ORFQ_VERIFY_NONE);
		sorfqDao.updateSOrfq(orfq);
		sorfqDao.deleteSOrfqEntryById(orfq.getFInterID());
		//依据单据头id插入1条或多条分录
		int j = 0;
		for(int i=0; i<orfqEntryList.size(); i++){
			TOrfqEntry orfqEntry = (TOrfqEntry) orfqEntryList.get(i); 
			if(orfqEntry != null){
				orfqEntry.setFPrice(orfqEntry.getFAuxPrice());
				orfqEntry.setFAuxQtyFrom(orfqEntry.getFAuxQty());
				orfqEntry.setFAuxQtyTo(orfqEntry.getFAuxQty());
				orfqEntry.setFInterID(orfq.getFInterID());
				orfqEntry.setFEntryID(j++);
				sorfqDao.insertOrfqEntry(orfqEntry);
			}
		}
		SLog log = new SLog();
		log.setLogContent("用户" + orfq.getFBillerID() + "修改销售报价单：" + orfq.getFInterID());
		log.setLogTime(new Date());
		log.setOperateUserId(orfq.getFBillerID());
		log.setTitle("销售报价单");
		log.setTypeId(Constants.LOG_TYPE_DEAL);
		logDao.insert(log);
		logger.warn("用户" + orfq.getFBillerID() + "修改K3销售报价单：" + orfq.getFInterID());
	}
	
	public TOrfqDAO getOrfqDao() {
		return orfqDao;
	}

	public void setOrfqDao(TOrfqDAO orfqDao) {
		this.orfqDao = orfqDao;
	}

	public SLogDAO getLogDao() {
		return logDao;
	}

	public void setLogDao(SLogDAO logDao) {
		this.logDao = logDao;
	}

	public SOrfqDAO getSorfqDao() {
		return sorfqDao;
	}

	public void setSorfqDao(SOrfqDAO sorfqDao) {
		this.sorfqDao = sorfqDao;
	}

	public SUserDAO getsUserDao() {
		return sUserDao;
	}

	public void setsUserDao(SUserDAO sUserDao) {
		this.sUserDao = sUserDao;
	}

	public SInformationDAO getInformationDao() {
		return informationDao;
	}

	public void setInformationDao(SInformationDAO informationDao) {
		this.informationDao = informationDao;
	}

	public SCustomerOwnerUserDAO getCustomerOwnerUserDao() {
		return customerOwnerUserDao;
	}

	public void setCustomerOwnerUserDao(SCustomerOwnerUserDAO customerOwnerUserDao) {
		this.customerOwnerUserDao = customerOwnerUserDao;
	} 

}
