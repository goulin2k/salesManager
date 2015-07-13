package com.sales.service.impl;
/** 
 * @author  
 * @version 创建时间：2013-6-27 下午09:36:03 
 * 
 *  
 */
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.sales.common.Constants;
import com.sales.dao.SCustomerFinEvaluationDAO;
import com.sales.dao.SCustomerOwnerUserDAO;
import com.sales.dao.SCustomerUserDAO;
import com.sales.dao.SInformationDAO;
import com.sales.dao.SLogDAO;
import com.sales.service.CustomerFinEvaluationService;
import com.sales.model.SCustomerFinEvaluation;
import com.sales.model.SCustomerOwnerUser;
import com.sales.model.SCustomerUser;
import com.sales.model.SInformation;
import com.sales.model.SLog;

public class CustomerFinEvaluationServiceImpl implements CustomerFinEvaluationService{
	
	private SCustomerFinEvaluationDAO customerFinEvaluationDao;
	private SLogDAO logDao;
	private SInformationDAO informationDao;
	private SCustomerOwnerUserDAO customerOwnerUserDao;
	private SCustomerUserDAO customerUserDao;

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

	public SCustomerFinEvaluationDAO getCustomerFinEvaluationDao() {
		return customerFinEvaluationDao;
	}

	public void setCustomerFinEvaluationDao(
			SCustomerFinEvaluationDAO customerFinEvaluationDao) {
		this.customerFinEvaluationDao = customerFinEvaluationDao;
	}

	@Override
	public void deleteCustomerFinEvaluationByCustomerId(Integer customerId) {
		// TODO Auto-generated method stub
		this.customerFinEvaluationDao.deleteByCustomerId(customerId);
	}

	@Override
	public SCustomerFinEvaluation getCustomerFinEvaluationByCustomerId(
			Integer customerId) {
		// TODO Auto-generated method stub
		return this.customerFinEvaluationDao.selectByCustomerId(customerId);
	}

	@Override
	public void updateCustomerFinEvaluation(
			SCustomerFinEvaluation customerFinEvaluation) {
		// TODO Auto-generated method stub
		this.customerFinEvaluationDao.deleteByCustomerId(customerFinEvaluation.getCustomerId());
		this.customerFinEvaluationDao.insert(customerFinEvaluation);
		//添加日志
		SLog log = new SLog();
		log.setLogContent("用户" + customerFinEvaluation.getCreateUserName() + "修改客户" + customerFinEvaluation.getCustomerId()+"财务评价");
		log.setLogTime(new Date());
		log.setOperateUserId(customerFinEvaluation.getCreateUserId());
		log.setTitle("修改客户财务评价");
		log.setTypeId(Constants.LOG_TYPE_DEAL);
		logDao.insert(log);
		//通知客户分配人
		List customerOwnerUserList = this.customerOwnerUserDao.getUserListByCustomerId(customerFinEvaluation.getCustomerId());
		if(customerOwnerUserList != null & customerOwnerUserList.size()>0){
			for (int i = 0; i < customerOwnerUserList.size(); i++) {
				SCustomerOwnerUser customerOwnerUser = (SCustomerOwnerUser) customerOwnerUserList.get(i);
				insertInfor("用户" + customerFinEvaluation.getCreateUserName() + "修改客户" + customerFinEvaluation.getCustomerId()+"财务评价", 
						customerOwnerUser.getUserId(), "修改客户财务评介", null,customerFinEvaluation.getCustomerId());
			}
		}
		//通知客户关注人
		List customerUserList = this.customerUserDao.getCustomerUserListByCustomerId(customerFinEvaluation.getCustomerId());
		if(customerUserList != null & customerUserList.size()>0){
			for (int i = 0; i < customerUserList.size(); i++) {
				SCustomerUser customerUser = (SCustomerUser) customerUserList.get(i);
				insertInfor("用户" + customerFinEvaluation.getCreateUserName() + "修改客户" + customerFinEvaluation.getCustomerId()+"财务评价", 
						customerUser.getUserId(), "修改客户财务评价", null,customerFinEvaluation.getCustomerId());
			}
		}
		
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

}
