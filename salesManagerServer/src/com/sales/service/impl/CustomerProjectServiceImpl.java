package com.sales.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.sales.action.SaleAction;
import com.sales.common.Constants;
import com.sales.dao.SCustomerProjectDAO;
import com.sales.dao.SLogDAO;
import com.sales.model.SCustomerProject;
import com.sales.model.SLog;
import com.sales.model.SSalesActivity;
import com.sales.service.ActivityService;
import com.sales.service.CustomerProjectService;

/** 
 * @author  
 * @version 创建时间：2013-6-27 下午05:06:11 
 *  
 */
public class CustomerProjectServiceImpl implements CustomerProjectService{
	
	private SCustomerProjectDAO customerProjectDao;
	private ActivityService activityService;
	private SLogDAO logDao;

	public SLogDAO getLogDao() {
		return logDao;
	}

	public void setLogDao(SLogDAO logDao) {
		this.logDao = logDao;
	}

	public SCustomerProjectDAO getCustomerProjectDao() {
		return customerProjectDao;
	}

	public void setCustomerProjectDao(SCustomerProjectDAO customerProjectDao) {
		this.customerProjectDao = customerProjectDao;
	}

	/**
	 * @param activityService the activityService to set
	 */
	public void setActivityService(ActivityService activityService) {
		this.activityService = activityService;
	}

	@Override
	public List<SCustomerProject> getCPListByCustomerId(Integer customerId) {
		
		List cpList = this.customerProjectDao.getCustomerProjectListByCustomerId(customerId);
		return cpList;
	}

	@Override
	public void addCustomerProject(SCustomerProject customerProject) {
		
		this.customerProjectDao.insert(customerProject);
		
		/* --自动添加一条销售活动
		SSalesActivity activity = new SSalesActivity();
		activity.setActivityDate(new Timestamp((new Date()).getTime()));
		activity.setComment("增加商机：" + customerProject.getName());
		activity.setCustomerId(customerProject.getCustomerId());
		activity.setCustomerProjectId(customerProject.getProjectId());
		activity.setEnumerationId(11);   //销售项目类型
		activity.setProjectId(null);
		activity.setResponseUserId(customerProject.getCreateUserId());
		activity.setTopical("增加商机：" + customerProject.getName());
		activity.setVisitPerson(customerProject.getLinkmanName());
		activityService.addActivity(activity, null);
		**/
		
		
		//添加日志
		SLog log = new SLog();
		log.setLogContent("用户" + customerProject.getCreateUserName() + "为客户"+customerProject.getCustomerName()+"新增项目" + customerProject.getName());
		log.setLogTime(new Date());
		log.setOperateUserId(customerProject.getCreateUserId());
		log.setTitle("新增客户项目");
		log.setTypeId(Constants.LOG_TYPE_DEAL);
		logDao.insert(log);
	}

	@Override
	public void deleteCustomerProject(Integer id) {
		
		this.customerProjectDao.deleteByPrimaryKey(id);
	}

	@Override
	public SCustomerProject getCustomerProjectById(Integer id) {
		
		return this.customerProjectDao.selectByPrimaryKey(id);
	}

	@Override
	public void updateCustomerProject(SCustomerProject customerProject) {
		
		this.customerProjectDao.updateByPrimaryKeySelective(customerProject);
		//添加日志
		SLog log = new SLog();
		log.setLogContent("用户" + customerProject.getCreateUserName() + "为客户"+customerProject.getCustomerName()+"修改项目" + customerProject.getName());
		log.setLogTime(new Date());
		log.setOperateUserId(customerProject.getCreateUserId());
		log.setTitle("修改客户项目");
		log.setTypeId(Constants.LOG_TYPE_DEAL);
		logDao.insert(log);
	}

	@Override
	public Integer getCustomerProjectCount(SCustomerProject project) {
		
		return this.customerProjectDao.getCustomerProjectCount(project);
	}

	@Override
	public List<SCustomerProject> selectCPlist(Long pageNumber, int pageSize, 
			SCustomerProject project) {
		
		return this.customerProjectDao.selectCPlist(pageNumber, pageSize, project);
	}

	@Override
	public Integer getCPCountByCustomerIds(SCustomerProject project, String customerIds) {
		
		return this.customerProjectDao.getCPCountByCustomerIds(project, customerIds);
	}

	@Override
	public List<SCustomerProject> selectCPlistByCustomerIds(Long pageNumber,
			int pageSize, SCustomerProject project, String customerIds) {
		
		return this.customerProjectDao.selectCPlistByCustomerIds(pageNumber, pageSize, 
				project, customerIds);
	}

}
