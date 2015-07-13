package com.sales.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import my.com.ibatis.core.dao.support.Page;

import com.sales.common.Constants;
import com.sales.common.NormalFun;
import com.sales.dao.SCustomerLevelDAO;
import com.sales.dao.SCustomerOwnerUserDAO;
import com.sales.dao.SCustomerUserDAO;
import com.sales.dao.SInformationDAO;
import com.sales.dao.SLogDAO;
import com.sales.dao.TCustomerDAO;
import com.sales.dao.TOrderDAO;
import com.sales.model.K3OrderCustomerStatistics;
import com.sales.model.SCustomerLevel;
import com.sales.model.SCustomerOwnerUser;
import com.sales.model.SCustomerUser;
import com.sales.model.SInformation;
import com.sales.model.SLog;
import com.sales.model.SUser;
import com.sales.model.TCustomer;
import com.sales.service.CustomerService;

/** 
 * @author  
 * @version 创建时间：2013-6-14 下午03:44:34 
 *  
 */
public class CustomerServiceImpl implements CustomerService {
	
	private TCustomerDAO customerDao;
	private SCustomerOwnerUserDAO customerOwnerUserDao;
	private SCustomerUserDAO customerUserDao;
	private SCustomerLevelDAO customerLevelDao;
	

	private SLogDAO logDao;
	private SInformationDAO informationDao;

	public SInformationDAO getInformationDao() {
		return informationDao;
	}

	public void setInformationDao(SInformationDAO informationDao) {
		this.informationDao = informationDao;
	}

	public SLogDAO getLogDao() {
		return logDao;
	}

	public void setLogDao(SLogDAO logDao) {
		this.logDao = logDao;
	}

	public SCustomerLevelDAO getCustomerLevelDao() {
		return customerLevelDao;
	}
	
	public void setCustomerLevelDao(SCustomerLevelDAO customerLevelDao) {
		this.customerLevelDao = customerLevelDao;
	}

	public SCustomerOwnerUserDAO getCustomerOwnerUserDao() {
		return customerOwnerUserDao;
	}

	public void setCustomerOwnerUserDao(SCustomerOwnerUserDAO customerOwnerUserDao) {
		this.customerOwnerUserDao = customerOwnerUserDao;
	}

	public SCustomerUserDAO getCustomerUserDao() {
		return customerUserDao;
	}

	public void setCustomerUserDao(SCustomerUserDAO customerUserDao) {
		this.customerUserDao = customerUserDao;
	}

	public TCustomerDAO getCustomerDao() {
		return customerDao;
	}

	public void setCustomerDao(TCustomerDAO customerDao) {
		this.customerDao = customerDao;
	}

	@Override
	public List<TCustomer> getCustomerList(Long pageNumber, int pageSize) {
		
		List customerList = this.customerDao.getCustomerList(pageNumber, pageSize);
		return customerList;
	}
	
	@Override
	public Integer getCustomerCount(){
		return this.customerDao.getCustomerCount();
	}

	@Override
	public TCustomer getCustomerInfoById(Integer customerId) {
		
		TCustomer tcustomer = this.customerDao.getCustomerInfoById(customerId);
		return tcustomer;
	}

	@Override
	public Integer getCOUCount(SCustomerOwnerUser couser, String customerIds, int oweredStatus) {
		if(customerIds == Constants.USER_FILTER_MARK)
			customerIds = null;
		return this.customerOwnerUserDao.getCOUCount(couser, customerIds, oweredStatus);
	}

	@Override
	public List<SCustomerOwnerUser> getCOUList(Long pageNumber, int pageSize, 
			SCustomerOwnerUser couser, String customerIds, int oweredStatus) {
		if(customerIds == Constants.USER_FILTER_MARK)
			customerIds = null;
		return this.customerOwnerUserDao.getCOUList(pageNumber, pageSize, couser,  customerIds, oweredStatus);
	}

	@Override
	public void deleteCOUser(Integer id,Integer userId) {
		
		//this.customerOwnerUserDao.deleteByPrimaryKey(id);
		SCustomerOwnerUser record = new SCustomerOwnerUser();
		record.setCustomerOwnerUserId(id);
		record.setOverTime(new Date());
		record.setUserId(userId);
		this.customerOwnerUserDao.updateByPrimaryKeySelective(record);
		
	}

	@Override
	public void addCOUser(SCustomerOwnerUser couser) {
		
		couser.setAddTime(new Date());
		if(couser.getCustomerOwnerUserId()!=null){
			//this.customerOwnerUserDao.updateCustomerOwerUser(couser);
			couser.setOverTime(new Date());
			this.customerOwnerUserDao.updateByPrimaryKeySelective(couser);
			couser.setCustomerOwnerUserId(null);
			this.customerOwnerUserDao.insert(couser);
		}else{
			this.customerOwnerUserDao.insert(couser);
		}
		//添加日志
		SLog log = new SLog();
		log.setLogContent("用户" + couser.getCreateUserName() + "新增客户"+couser.getCustomerId()+"分配人,ID为" + couser.getUserId());
		log.setLogTime(new Date());
		log.setOperateUserId(couser.getCreateUserId());
		log.setTitle("新增客户分配人");
		log.setTypeId(Constants.LOG_TYPE_DEAL);
		logDao.insert(log);
		
	}

	@Override
	public void addCustomerUser(SCustomerUser customerUser) {
		
		this.customerUserDao.insert(customerUser);
		//添加日志
		SLog log = new SLog();
		log.setLogContent("用户" + customerUser.getCreateUserName() + "新增客户"+customerUser.getCustomerId()+"关注人，ID为" + customerUser.getUserId());
		log.setLogTime(new Date());
		log.setOperateUserId(customerUser.getCreateUserId());
		log.setTitle("新增客户关注人");
		log.setTypeId(Constants.LOG_TYPE_DEAL);
		logDao.insert(log);
	}

	@Override
	public List<SCustomerUser> getCustomerUserListByCustomerId(Integer customerId) {
		
		return this.customerUserDao.getCustomerUserListByCustomerId(customerId);
	}
	
	@Override
	public List<SCustomerUser> getCustomerUserListByUserId(Integer userId) {
		
		return this.customerUserDao.getCustomerUserListByCustomerId(userId);
	}

	@Override
	public void deleteCustomerUser(Integer id) {
		
		this.customerUserDao.deleteByPrimaryKey(id);
	}

	@Override
	public Integer getCustomerUserCount(SCustomerUser customerUser) {
		
		return this.customerUserDao.getCustomerUserCount(customerUser);
	}

	@Override
	public List<SCustomerUser> selectCustomerUserList(Long pageNumber,
			int pageSize, SCustomerUser customerUser) {
		
		return this.customerUserDao.selectCustomerUserList(pageNumber, pageSize, customerUser);
	}

	@Override
	public void updateCustomerLevel(SCustomerLevel customerLevel) {
		
		this.customerLevelDao.deleteByCustomerId(customerLevel.getCustomerId());
		this.customerLevelDao.insert(customerLevel);
		//添加日志
		SLog log = new SLog();
		log.setLogContent("用户" + customerLevel.getCreateUserName() + "修改客户"+customerLevel.getCustomerId()+"等级");
		log.setLogTime(new Date());
		log.setOperateUserId(customerLevel.getCreateUserId());
		log.setTitle("修改客户等级");
		log.setTypeId(Constants.LOG_TYPE_DEAL);
		logDao.insert(log);
		//通知客户分配人
		List customerOwnerUserList = this.customerOwnerUserDao.getUserListByCustomerId(customerLevel.getCustomerId());
		if(customerOwnerUserList != null & customerOwnerUserList.size()>0){
			for (int i = 0; i < customerOwnerUserList.size(); i++) {
				SCustomerOwnerUser customerOwnerUser = (SCustomerOwnerUser) customerOwnerUserList.get(i);
				insertInfor("用户" + customerLevel.getCreateUserName() + "修改客户"+customerLevel.getCustomerId()+"等级", 
						customerOwnerUser.getUserId(), "修改客户等级", null,customerLevel.getCustomerId());
			}
		}
		//通知客户关注人
		List customerUserList = this.customerUserDao.getCustomerUserListByCustomerId(customerLevel.getCustomerId());
		if(customerUserList != null & customerUserList.size()>0){
			for (int i = 0; i < customerUserList.size(); i++) {
				SCustomerUser customerUser = (SCustomerUser) customerUserList.get(i);
				insertInfor("用户" + customerLevel.getCreateUserName() + "修改客户"+customerLevel.getCustomerId()+"等级", 
						customerUser.getUserId(), "修改客户等级", null,customerLevel.getCustomerId());
			}
		}
	}

	@Override
	public SCustomerLevel selectCustomerLevelByCustomerId(Integer customerId) {
		
		return this.customerLevelDao.selectByCustomerId(customerId);
	}

	@Override
	public List<TCustomer> selectCustomerList(Long pageNumber, int pageSize,
			TCustomer tcustomer) {
		
		return this.customerDao.selectCustomerList(pageNumber, pageSize, tcustomer);
	}

	@Override
	public Integer getCustomerCountByObj(TCustomer tcustomer) {
		
		return this.customerDao.getCustomerCountByObj(tcustomer);
	}
	
	public List<TCustomer> getOpenCustomerList(String fname, String fnumber, String customerIds, Long pageNumber, int pageSize) { 
		long startRow = Page.getStartOfPage(pageNumber, pageSize);
		long endRow = pageSize;
		if(customerIds==null || customerIds.trim().equals("")){
			customerIds = "(-1)";
		}
		else if(Constants.USER_FILTER_MARK.equals(customerIds)){
			customerIds = null;
		}
		Map<String,Comparable> queryMap = new HashMap<String,Comparable>();
		queryMap.put("startRow", startRow);
		queryMap.put("endRow", endRow);
		queryMap.put("fname", fname);
		queryMap.put("fnumber", fnumber); 
		queryMap.put("customerIds", customerIds); 
		List customerList = this.customerDao.getOpenCustomerList(queryMap); 
		return customerList;
	}
	
	public Integer getOpenCustomerCount(String fname, String fnumber, String customerIds) {  
		if(customerIds==null || customerIds.trim().equals("")){
			customerIds = "(-1)";
		}
		else if(Constants.USER_FILTER_MARK.equals(customerIds)){
			customerIds = null;
		}
		Map<String,Comparable> queryMap = new HashMap<String,Comparable>(); 
		queryMap.put("fname", fname);
		queryMap.put("fnumber", fnumber); 
		queryMap.put("customerIds", customerIds); 
		Integer count = this.customerDao.getOpenCustomerCount(queryMap); 
		return count;
	}
	
	public List<TCustomer> getOpenDistributionCustomerList(String fname, String fnumber, String customerIds, Long pageNumber, int pageSize) { 
		long startRow = Page.getStartOfPage(pageNumber, pageSize);
		long endRow = pageSize;
		if(customerIds==null || customerIds.trim().equals("")){
			customerIds = "(-1)";
		}
		else if(Constants.USER_FILTER_MARK.equals(customerIds)){
			customerIds = null;
		}
		Map<String,Comparable> queryMap = new HashMap<String,Comparable>();
		queryMap.put("startRow", startRow);
		queryMap.put("endRow", endRow);
		queryMap.put("fname", fname);
		queryMap.put("fnumber", fnumber); 
		queryMap.put("customerIds", customerIds); 
		List customerList = this.customerDao.getOpenDistributionCustomerList(queryMap); 
		return customerList;
	}
	
	public Integer getOpenDistributionCustomerCount(String fname, String fnumber, String customerIds) {  
		if(customerIds==null || customerIds.trim().equals("")){
			customerIds = "(-1)";
		}
		else if(Constants.USER_FILTER_MARK.equals(customerIds)){
			customerIds = null;
		}
		Map<String,Comparable> queryMap = new HashMap<String,Comparable>(); 
		queryMap.put("fname", fname);
		queryMap.put("fnumber", fnumber); 
		queryMap.put("customerIds", customerIds); 
		Integer count = this.customerDao.getOpenDistributionCustomerCount(queryMap); 
		return count;
	}

	@Override
	public Integer getCustomerCountByFilter(TCustomer tcustomer,
			String customerIds) {
		
		if(customerIds==null || customerIds.trim().equals("")){
			customerIds = "(-1)";
		}
		Map<String,Comparable> queryMap = new HashMap<String,Comparable>();
		if(tcustomer.getEvaFinGenId()!=null && tcustomer.getEvaFinGenId() == Constants.NOT_ASSASE_MARK ){
			tcustomer.setEvaFinGenMark(tcustomer.getEvaFinGenId());
			tcustomer.setEvaFinGenId(null);
		}
		if(tcustomer.getEvaFinManagerId()!=null && tcustomer.getEvaFinManagerId() == Constants.NOT_CUSTOMER_CREDIT_MARK ){
			tcustomer.setEvaFinManagerMark(tcustomer.getEvaFinManagerId());
			tcustomer.setEvaFinManagerId(null);
		}
		if(tcustomer.getEvaFinSalemagagerId()!=null && tcustomer.getEvaFinSalemagagerId() == Constants.NOT_ASSASE_MARK ){
			tcustomer.setEvaFinSalemagagerMark(tcustomer.getEvaFinSalemagagerId());
			tcustomer.setEvaFinSalemagagerId(null);
		}
		if(tcustomer.getCustomerLevel()!=null && tcustomer.getCustomerLevel() == Constants.NOT_CUSTOMER_LEVEL_MARK ){
			tcustomer.setCustomerLevelMark(tcustomer.getCustomerLevel());
			tcustomer.setCustomerLevel(null);
		}
		queryMap.put("fname", tcustomer.getFname());
		queryMap.put("fnumber", tcustomer.getFnumber()); 
		queryMap.put("cuserName", NormalFun.trimNull(tcustomer.getCuserName()));
		queryMap.put("couserName", NormalFun.trimNull(tcustomer.getCouserName()));
		queryMap.put("customerLevel", tcustomer.getCustomerLevel());
		queryMap.put("customerIds", customerIds); 
		queryMap.put("evaFinGenId", tcustomer.getEvaFinGenId());
		queryMap.put("evaFinManagerId", tcustomer.getEvaFinManagerId());
		queryMap.put("evaFinSalemagagerId", tcustomer.getEvaFinSalemagagerId());
		queryMap.put("evaFinSalemagagerMark", tcustomer.getEvaFinSalemagagerMark());
		queryMap.put("evaFinManagerMark", tcustomer.getEvaFinManagerMark());
		queryMap.put("evaFinGenMark", tcustomer.getEvaFinGenMark());
		queryMap.put("customerLevelMark", tcustomer.getCustomerLevelMark());
		queryMap.put("linkmanWriteStatus", tcustomer.getLinkmanWriteStatus());
		Integer count = this.customerDao.getCustomerCountByFilter(queryMap);
		return count;
	}

	@Override
	public List<TCustomer> getCustomerListByFilter(TCustomer tcustomer,
			String customerIds, Long pageNumber, int pageSize) {
		
		long startRow = Page.getStartOfPage(pageNumber, pageSize);
		long endRow = pageSize;
//		if(customerIds==null || customerIds.trim().equals("")){
//			customerIds = "(-1)";
//		}
		Map<String,Comparable> queryMap = new HashMap<String,Comparable>();
			if(tcustomer.getEvaFinGenId()!=null && tcustomer.getEvaFinGenId() == Constants.NOT_ASSASE_MARK ){
				tcustomer.setEvaFinGenMark(tcustomer.getEvaFinGenId());
				tcustomer.setEvaFinGenId(null);
			}
			if(tcustomer.getEvaFinManagerId()!=null && tcustomer.getEvaFinManagerId() == Constants.NOT_CUSTOMER_CREDIT_MARK ){
				tcustomer.setEvaFinManagerMark(tcustomer.getEvaFinManagerId());
				tcustomer.setEvaFinManagerId(null);
			}
			if(tcustomer.getEvaFinSalemagagerId()!=null && tcustomer.getEvaFinSalemagagerId() == Constants.NOT_ASSASE_MARK ){
				tcustomer.setEvaFinSalemagagerMark(tcustomer.getEvaFinSalemagagerId());
				tcustomer.setEvaFinSalemagagerId(null);
			}
			if(tcustomer.getCustomerLevel()!=null && tcustomer.getCustomerLevel() == Constants.NOT_CUSTOMER_LEVEL_MARK ){
				tcustomer.setCustomerLevelMark(tcustomer.getCustomerLevel());
				tcustomer.setCustomerLevel(null);
			}
		queryMap.put("startRow", startRow);
		queryMap.put("endRow", endRow);
		queryMap.put("fname", tcustomer.getFname());
		queryMap.put("fnumber", tcustomer.getFnumber());
		
		queryMap.put("cuserName", NormalFun.trimNull(tcustomer.getCuserName()));
		queryMap.put("couserName", NormalFun.trimNull(tcustomer.getCouserName()));
		
		queryMap.put("customerLevel", tcustomer.getCustomerLevel());
		queryMap.put("customerIds", customerIds); 
		queryMap.put("evaFinGenId", tcustomer.getEvaFinGenId());
		queryMap.put("evaFinManagerId", tcustomer.getEvaFinManagerId());
		queryMap.put("evaFinSalemagagerId", tcustomer.getEvaFinSalemagagerId());
		queryMap.put("evaFinSalemagagerMark", tcustomer.getEvaFinSalemagagerMark());
		queryMap.put("evaFinManagerMark", tcustomer.getEvaFinManagerMark());
		queryMap.put("evaFinGenMark", tcustomer.getEvaFinGenMark());
		queryMap.put("customerLevelMark", tcustomer.getCustomerLevelMark());
		queryMap.put("linkmanWriteStatus", tcustomer.getLinkmanWriteStatus());
		List customerList = this.customerDao.getCustomerListByFilter(queryMap);
		return customerList;
	}

	@Override
	public List<SCustomerOwnerUser> getCOUserListByCustomerId(Integer customerId) {
		;
		return this.customerOwnerUserDao.getUserListByCustomerId(customerId);
	}

	@Override
	public void deleteCOUserByCustomerId(Integer customerId) {
		
		this.customerOwnerUserDao.deleteByCustomerId(customerId);
	}

	@Override
	public void deleteCustomerUserByCustomerId(Integer customerId,
			Integer userId) {
		
		this.customerUserDao.deleteByCustomerId(customerId, userId);
	}
	
	private void insertInfor(String content, Integer sendUserId, String title, String url, int customerId){
		SInformation infor = new SInformation();
		infor.setAddTime(new Timestamp(System.currentTimeMillis()));
		infor.setContent(content);
		infor.setSendUserId(sendUserId);
		infor.setStatus(Constants.INFO_STATUS_NEW);
		infor.setTitle(title);
		infor.setType(SInformation.BuzType.PLAN.ordinal());
		infor.setImageUrl(url);
		infor.setBuzId(customerId);
		informationDao.insert(infor);
	}

	@Override
	public Integer getHistoryCOUCount(SCustomerOwnerUser couser,
			String customerIds, int oweredStatus) {
		
		if(customerIds == Constants.USER_FILTER_MARK)
			customerIds = null;
		return this.customerOwnerUserDao.getHistoryCOUCount(couser, customerIds, oweredStatus);
	}

	@Override
	public List<SCustomerOwnerUser> getHistoryCOUList(Long pageNumber,
			int pageSize, SCustomerOwnerUser couser, String customerIds,
			int oweredStatus) {
		
		if(customerIds == Constants.USER_FILTER_MARK)
			customerIds = null;
		return this.customerOwnerUserDao.getHistoryCOUList(pageNumber, pageSize, couser,  customerIds, oweredStatus);
	}

}
