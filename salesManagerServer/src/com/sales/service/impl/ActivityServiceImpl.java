package com.sales.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sales.common.Constants;
import com.sales.common.NormalFun;
import com.sales.dao.SInformationDAO;
import com.sales.dao.SLogDAO;
import com.sales.dao.SSalesActivityDAO;
import com.sales.dao.SSalesProjectDAO;
import com.sales.model.SInformation;
import com.sales.model.SLog;
import com.sales.model.SSalesActivity;
import com.sales.model.SSalesProject;
import com.sales.model.SUser;
import com.sales.service.ActivityService;
import com.sales.service.SUserService;

public class ActivityServiceImpl implements ActivityService {
	
	private SSalesActivityDAO activityDao;	
	private SSalesProjectDAO projectDao;
	private SLogDAO logDao;
	private SInformationDAO informationDao;
	
	private SUserService userService;
	
	/**
	 * @param userService the userService to set
	 */
	public void setUserService(SUserService userService) {
		this.userService = userService;
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void addActivity(SSalesActivity activity, String childUserIds) {
		Integer activityId = activityDao.insertActivity(activity); 
		if(activity.getProjectId()!=null && activity.getProjectId()>0){
			//修改计划进度
			Map queryMap = new HashMap();
			queryMap.put("projectId", activity.getProjectId()); 
			if(childUserIds==null || childUserIds.trim().equals("")){
				childUserIds = "(-1)";
			}
			else if(Constants.USER_FILTER_MARK.equals(childUserIds)){
				childUserIds = null;
			}
			queryMap.put("childUserIds", childUserIds);
			SSalesProject project = projectDao.getProjectById(queryMap);
			if(project != null){ 
				project.setCompletionRate(activity.getCompletionRate());
				if(activity.getCompletionRate()!=null && !activity.getCompletionRate().equalsIgnoreCase("") && activity.getCompletionRate().equals("100%")){
					project.setCompletionTime(activity.getActivityDate());
				}
				projectDao.updateSalesProjectRate(project);			
				//活动负责人与计划创建人不是同一个人，通知计划创建人
				if(project.getCreateUserId().intValue() != activity.getResponseUserId().intValue()){
					insertInfor("用户:" + activity.getResponseUserName() + "为销售计划：" + project.getTopical() + "新增活动：" + activity.getTopical(), 
							project.getCreateUserId(), "销售活动", "project!get?project.projectId=" + String.valueOf(activity.getProjectId()),
							activityId);
				}
				//活动负责人与计划负责人不是同一个人，通知计划负责人
				if(project.getResponseUserId()!=null && project.getResponseUserId().intValue()!=activity.getResponseUserId().intValue()){
					insertInfor("用户:" + activity.getResponseUserName() + "为销售计划：" + project.getTopical() + "新增活动：" + activity.getTopical(), 
							project.getResponseUserId(), "销售活动", "project!get?project.projectId=" + String.valueOf(activity.getProjectId()),
							activityId);
				}
				//通知计划关注人
				List attentionList = projectDao.getAttentionUserByProjectId(activity.getProjectId());
				for (int i = 0; i < attentionList.size(); i++) {
					SUser attentionUser = (SUser) attentionList.get(i);
					insertInfor("用户:" + activity.getResponseUserName() + "为销售计划：" + project.getTopical() + "新增活动：" + activity.getTopical(), 
							attentionUser.getUserId(), "销售活动", "project!get?project.projectId=" + String.valueOf(activity.getProjectId()),
							activityId);
				}
				
			}
		} 
		
		//通知活动创建人所有上级用户该计划信息
		List<SUser> users = userService.getParentUsersById(activity.getResponseUserId().intValue());
		for (Iterator iterator = users.iterator(); iterator.hasNext();) {
			SUser sUser = (SUser) iterator.next();
			insertInfor("用户:" + activity.getResponseUserName() + "新增销售活动：【" + activity.getTopical() + "】" , 
					sUser.getUserId(), "销售活动", "activity!show?activity.activityId=" + String.valueOf(activity.getActivityId()),
					activityId);
		}
		//添加日志
		SLog log = new SLog();
		log.setLogContent("用户" + activity.getResponseUserName() + "新增销售活动：" + activity.getTopical());
		log.setLogTime(new Date());
		log.setOperateUserId(activity.getResponseUserId());
		log.setTitle("销售活动");
		log.setTypeId(Constants.LOG_TYPE_DEAL);
		logDao.insert(log);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void updateActivity(SSalesActivity activity) {
		activityDao.updateActivity(activity); 
		if(activity.getProjectId()!=null && activity.getProjectId()>0){
			SSalesProject project = new SSalesProject();
			project.setProjectId(activity.getProjectId());
			project.setCompletionRate(activity.getCompletionRate());
			if(activity.getCompletionRate()!=null && !activity.getCompletionRate().equalsIgnoreCase("") && activity.getCompletionRate().equals("100%")){
				project.setCompletionTime(new Date());
			}
			projectDao.updateSalesProjectRate(project);
		} 
		SLog log = new SLog();
		log.setLogContent("用户" + activity.getResponseUserId() + "修改销售活动：" + activity.getTopical());
		log.setLogTime(new Date());
		log.setOperateUserId(activity.getResponseUserId());
		log.setTitle("销售活动");
		log.setTypeId(Constants.LOG_TYPE_DEAL);
		logDao.insert(log);
	}
	
	public SSalesActivity getActivityById(Integer activityId, String childUserIds) { 
		Map queryMap = new HashMap();
		queryMap.put("activityId", activityId); 
		if(childUserIds==null || childUserIds.trim().equals("")){
			childUserIds = "(-1)";
		}
		else if(Constants.USER_FILTER_MARK.equals(childUserIds)){
			childUserIds = null;
		}
		queryMap.put("childUserIds", childUserIds);
		SSalesActivity activity = activityDao.getActivityById(queryMap);
		return activity;			
	}
	
	public void deleteActivity(Integer activityId) { 
		activityDao.deleteActivity(activityId); 
    }
	
	public List getActivityList(Integer projectId, Integer customerId, Integer responseUserId, String responseUserName, String topical, 
			String startTime, String endTime, Integer pageNumber, Integer pageSize, Integer enumerationId, String childUserIds){
		Map queryMap = new HashMap();
		queryMap.put("projectId", projectId); 
		queryMap.put("customerId", customerId);
		queryMap.put("responseUserId", responseUserId); 
		if(responseUserName == null || responseUserName.trim().length() == 0)
			queryMap.put("responseUserName", null);
		else
			queryMap.put("responseUserName", responseUserName);
		queryMap.put("topical", topical);
		queryMap.put("enumerationId", enumerationId); 
		if(childUserIds==null || childUserIds.trim().equals("")){
			childUserIds = "(-1)";
		}
		else if(Constants.USER_FILTER_MARK.equals(childUserIds)){
			childUserIds = null;
		}
		queryMap.put("childUserIds", childUserIds); 
		if(startTime!=null && !startTime.trim().equals("")){
			queryMap.put("startTime", NormalFun.formatStringDateTime(startTime + " 00:00:00"));
		}
		if(endTime!=null && !endTime.trim().equals("")){
			queryMap.put("endTime", NormalFun.formatStringDateTime(endTime + " 23:59:59")); 
		}
		Integer startNumber = (pageNumber - 1) * pageSize; 
		queryMap.put("startNumber", startNumber);
		queryMap.put("pageSize", pageSize);
		List activityList = activityDao.getActivityList(queryMap);
		return activityList;
	}
	
	public Integer getActivityCount(Integer projectId, Integer customerId, Integer responseUserId, String responseUserName, 
			String topical, String startTime, String endTime, Integer enumerationId, String childUserIds){
		Map queryMap = new HashMap();
		queryMap.put("projectId", projectId); 
		queryMap.put("customerId", customerId);
		queryMap.put("responseUserId", responseUserId); 
		if(responseUserName == null || responseUserName.trim().length() == 0)
			queryMap.put("responseUserName", null);
		else
			queryMap.put("responseUserName", responseUserName);
		queryMap.put("topical", topical);
		queryMap.put("enumerationId", enumerationId);  
		if(childUserIds==null || childUserIds.trim().equals("")){
			childUserIds = "(-1)";
		}
		else if(Constants.USER_FILTER_MARK.equals(childUserIds)){
			childUserIds = null;
		}
		queryMap.put("childUserIds", childUserIds); 
		if(startTime!=null && !startTime.trim().equals("")){
			queryMap.put("startTime", NormalFun.formatStringDateTime(startTime + " 00:00:00"));
		}
		if(endTime!=null && !endTime.trim().equals("")){
			queryMap.put("endTime", NormalFun.formatStringDateTime(endTime + " 23:59:59")); 
		}
		Integer activityCount = activityDao.getActivityCount(queryMap);
		return activityCount;
	}
	
	private void insertInfor(String content, Integer sendUserId, String title, String url, int activtyId){
		SInformation infor = new SInformation();
		infor.setAddTime( new Timestamp(System.currentTimeMillis()));
		infor.setContent(content);
		infor.setSendUserId(sendUserId);
		infor.setStatus(Constants.INFO_STATUS_NEW);
		infor.setTitle(title);
		infor.setType(SInformation.BuzType.ACTIVITY.ordinal());
		infor.setImageUrl(url);
		infor.setBuzId(activtyId);
		informationDao.insert(infor);
	}
    
    public List getActivityByCustomerId(Integer customerId){
    	List projectList = activityDao.getActivityByCustomerId(customerId);
    	return projectList;
    }

	public SSalesActivityDAO getActivityDao() {
		return activityDao;
	}

	public void setActivityDao(SSalesActivityDAO activityDao) {
		this.activityDao = activityDao;
	}
	
	public SSalesProjectDAO getProjectDao() {
		return projectDao;
	}

	public void setProjectDao(SSalesProjectDAO projectDao) {
		this.projectDao = projectDao;
	}

	public SLogDAO getLogDao() {
		return logDao;
	}

	public void setLogDao(SLogDAO logDao) {
		this.logDao = logDao;
	}

	public SInformationDAO getInformationDao() {
		return informationDao;
	}

	public void setInformationDao(SInformationDAO informationDao) {
		this.informationDao = informationDao;
	}

	@Override
	public List getActivityByCustomerProId(Integer customerProId) {
		// TODO Auto-generated method stub
		return this.activityDao.getActivityByCustomerProId(customerProId);
	}

}
