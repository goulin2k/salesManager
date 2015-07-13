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
import com.sales.dao.SSalesProjectDAO;
import com.sales.model.SInformation;
import com.sales.model.SLog;
import com.sales.model.SProjectAttentionUser;
import com.sales.model.SSalesProject;
import com.sales.model.SUser;
import com.sales.model.SalePlanStatistics;
import com.sales.model.SalePlanWithActivityVO;
import com.sales.service.ProjectService;
import com.sales.service.SUserService;

public class ProjectServiceImpl implements ProjectService {
	
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
	public void addProject(SSalesProject project, String attentionUserId){
		Integer projectId = projectDao.insertSalesProject(project);
		if(attentionUserId!=null && !attentionUserId.trim().equals("")){
			String[] attentionUserIds = attentionUserId.split(",");
			for(int i=0; i<attentionUserIds.length; i++){
				SProjectAttentionUser attentionUser = new SProjectAttentionUser();
				attentionUser.setProjectId(projectId);
				attentionUser.setUserId(Integer.parseInt(attentionUserIds[i]));
				projectDao.insertProjectAttentionUser(attentionUser);
				insertInfor("用户:" + project.getCreateUserName() + "指定您为销售计划：" + project.getTopical() + "的关注人", 
						Integer.parseInt(attentionUserIds[i]), "销售计划", "project!get?project.projectId=" + String.valueOf(projectId),
						projectId);
			}
		}
		//发送消息给计划执行人
		if(project.getCreateUserId().intValue() != project.getResponseUserId().intValue()){
			insertInfor("用户:" + project.getCreateUserName() + "指定您为销售计划：" + project.getTopical() + "的负责人", 
					project.getResponseUserId(), "销售计划", "project!get?project.projectId=" + String.valueOf(projectId),projectId
					);
		}
		
		//通知计划创建人所有上级用户该计划信息
		List<SUser> users = userService.getParentUsersById(project.getCreateUserId().intValue());
		for (Iterator iterator = users.iterator(); iterator.hasNext();) {
			SUser sUser = (SUser) iterator.next();
			insertInfor("用户:" + project.getCreateUserName() + " 新建销售计划：【" + project.getTopical() + "】", 
					sUser.getUserId(), "销售计划", "project!get?project.projectId=" + String.valueOf(projectId),
					projectId);
		}
		
		//添加日志
		SLog log = new SLog();
		log.setLogContent("用户" + project.getCreateUserId() + "新增销售计划：" + project.getTopical());
		log.setLogTime(new Date());
		log.setOperateUserId(project.getCreateUserId());
		log.setTitle("销售计划");
		log.setTypeId(Constants.LOG_TYPE_DEAL);
		logDao.insert(log);
	}
	
	private void insertInfor(String content, Integer sendUserId, String title, String url, Integer projectId){
		SInformation infor = new SInformation();
		infor.setAddTime(new Timestamp(System.currentTimeMillis()));
		infor.setContent(content);
		infor.setSendUserId(sendUserId);
		infor.setStatus(Constants.INFO_STATUS_NEW);
		infor.setTitle(title);
		infor.setType(SInformation.BuzType.PLAN.ordinal());
		infor.setImageUrl(url);
		infor.setBuzId(projectId);
		informationDao.insert(infor);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateProject(SSalesProject project, String attentionUserId, String childUserIds){
		//得到老的计划
		Map queryMap = new HashMap();
		queryMap.put("projectId", project.getProjectId()); 
		if(childUserIds==null || Constants.USER_FILTER_MARK.equals(childUserIds)){
			childUserIds = null;
		}else if(childUserIds.trim().equals("")){
			childUserIds = "(-1)";
		}
		
		queryMap.put("childUserIds", childUserIds);
		SSalesProject oldProject = projectDao.getProjectById(queryMap);
		List attentionUserOldList = oldProject.getAttentionUserList();
		//执行修改
		projectDao.updateProject(project);
		projectDao.deleteAttentionUserByProject(project.getProjectId());
		if(attentionUserId!=null && !attentionUserId.trim().equals("")){
			String[] attentionUserIds = attentionUserId.split(",");
			for(int i=0; i<attentionUserIds.length; i++){
				SProjectAttentionUser attentionUser = new SProjectAttentionUser();
				attentionUser.setProjectId(project.getProjectId());
				attentionUser.setUserId(Integer.parseInt(attentionUserIds[i]));
				projectDao.insertProjectAttentionUser(attentionUser);
				//插入消息
				if(attentionUserOldList != null){
					boolean isSend = true;
					for (int j = 0; j < attentionUserOldList.size(); j++) {
						SUser attentionUserOld = (SUser) attentionUserOldList.get(j);
						if(Integer.parseInt(attentionUserIds[i]) == attentionUserOld.getUserId().intValue()){
							isSend = false;
							break;
						}
					}
					if(isSend){
						insertInfor("用户:" + project.getCreateUserName() + "指定您为销售计划：" + project.getTopical() + "的关注人", 
								Integer.parseInt(attentionUserIds[i]), "销售计划", "project!get?project.projectId=" + String.valueOf(project.getProjectId()),
								project.getProjectId());
					}
				}
			}
		} 
		//插入消息
		if(project.getResponseUserId()!=null && oldProject.getResponseUserId().intValue()!=project.getResponseUserId().intValue()){
			insertInfor("用户:" + project.getCreateUserName() + "指定您为销售计划：" + project.getTopical() + "的负责人", 
					project.getResponseUserId(), "销售计划", "project!get?project.projectId=" + String.valueOf(project.getProjectId()),
					project.getProjectId());
		}		
		//插入日志
		SLog log = new SLog();
		log.setLogContent("用户" + project.getCreateUserId() + "修改销售计划：" + project.getTopical());
		log.setLogTime(new Date());
		log.setOperateUserId(project.getCreateUserId());
		log.setTitle("销售计划");
		log.setTypeId(Constants.LOG_TYPE_DEAL);
		logDao.insert(log);
	}
	
	
	
	/* (non-Javadoc)
	 * @see com.sales.service.ProjectService#commitProject(com.sales.model.SSalesProject)
	 */
	@Override
	public void commitProject(SSalesProject project) {
		project.setStatus(1);
		projectDao.commitSalesProject(project);
		
		SLog log = new SLog();
		log.setLogContent("用户" + project.getCreateUserId() + "提交销售计划：" + project.getTopical());
		log.setLogTime(new Date());
		log.setOperateUserId(project.getCreateUserId());
		log.setTitle("销售计划");
		log.setTypeId(Constants.LOG_TYPE_DEAL);
		logDao.insert(log);
	}
	
	

	/* (non-Javadoc)
	 * @see com.sales.service.ProjectService#commitProject(java.lang.Integer)
	 */
	@Override
	public void commitProject(Integer projectId) {
		SSalesProject project = getProjectById(projectId, null);
		if(project == null)
			return;
		commitProject(project);
	}
	
	

	/* (non-Javadoc)
	 * @see com.sales.service.ProjectService#removeProject(java.lang.Integer)
	 */
	@Override
	public void removeProject(Integer projectId) {
		SSalesProject project = getProjectById(projectId, null);
		if(project == null)
			return;
		
		if(project.getStatus() == 1)		//保证已提交的计划不能删除
			throw(new RuntimeException("已提交的计划不能删除."));
		projectDao.deleteByPrimaryKey(projectId);
		
	}

	/* (non-Javadoc)
	 * @see com.sales.service.ProjectService#getProjectList(java.lang.String, java.lang.String, java.lang.Integer, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	public List getProjectList(String childUserIds, String topical, Integer enumerationId, 
			String responseUserName, String createUserName,
			String startTime, String endTime, String assessUser, Integer createUserId, int status,
			Integer pageNumber, Integer pageSize, Integer customerId, Integer sessionUserId){
		Map queryMap = new HashMap();
		if(childUserIds==null || Constants.USER_FILTER_MARK.equals(childUserIds)){
			childUserIds = null;
		}else if(childUserIds.trim().equals("")){
			childUserIds = "(-1)";
		}
		
		queryMap.put("childUserIds", childUserIds); 
		queryMap.put("topical", topical);
		queryMap.put("enumerationId", enumerationId); 
		if(responseUserName == null || responseUserName.trim().length() == 0)
			queryMap.put("responseUserName", null);
		else
			queryMap.put("responseUserName", responseUserName);
		
		if(createUserName == null || createUserName.trim().length() == 0)
			queryMap.put("createUserName", null);
		else
			queryMap.put("createUserName", createUserName);
		
		queryMap.put("customerId", customerId);
		if(startTime!=null && !startTime.trim().equals("")){
			queryMap.put("startTime", NormalFun.formatStringDateTime(startTime + " 00:00:00"));
		}
		if(endTime!=null && !endTime.trim().equals("")){
			queryMap.put("endTime", NormalFun.formatStringDateTime(endTime + " 23:59:59")); 
		}
		if(assessUser!=null && !assessUser.trim().equals("")){
			queryMap.put("assessUser", assessUser); 
		}
		queryMap.put("createUserId", createUserId);
		queryMap.put("status", status);
		
		Integer startNumber = (pageNumber - 1) * pageSize; 
		queryMap.put("startNumber", startNumber);
		queryMap.put("pageSize", pageSize);
		List projectList = projectDao.getProjectList(queryMap);
		for (int i = 0; i < projectList.size(); i++) {
			SSalesProject project = (SSalesProject) projectList.get(i);
			//查询用户是创建人
			if(project.getCreateUserId()!=null && sessionUserId.intValue()==project.getCreateUserId().intValue()){
				project.setProjectRole(Constants.PROJECT_CREATE);
			}
			//查询用户只是负责人
			else if(project.getCreateUserId()!=null && project.getResponseUserId()!=null 
					&& sessionUserId.intValue()!=project.getCreateUserId().intValue() && sessionUserId.intValue()==project.getResponseUserId().intValue()){
				project.setProjectRole(Constants.PROJECT_RESPONSE);
			}
			//查询用户只是关注人
			else if(project.getCreateUserId()!=null && project.getResponseUserId()!=null 
					&& sessionUserId.intValue()!=project.getCreateUserId().intValue() && sessionUserId.intValue()!=project.getResponseUserId().intValue()){
				project.setProjectRole(Constants.PROJECT_ATTENTION);
			}
		}
		return projectList;
	}
	
	/* (non-Javadoc)
	 * @see com.sales.service.ProjectService#getProjectCount(java.lang.String, java.lang.String, java.lang.Integer, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Integer)
	 */
	public Integer getProjectCount(String childUserIds, String topical, Integer enumerationId, 
			String responseUserName, String createUserName, 
			String startTime, String endTime, String assessUser, Integer createUserId, int status, Integer customerId){
		Map queryMap = new HashMap();
		if(childUserIds==null || Constants.USER_FILTER_MARK.equals(childUserIds)){
			childUserIds = null;
		}else if(childUserIds.trim().equals("")){
			childUserIds = "(-1)";
		}
		
		queryMap.put("childUserIds", childUserIds); 
		queryMap.put("topical", topical); 
		queryMap.put("enumerationId", enumerationId); 
		if(responseUserName == null || responseUserName.trim().length() == 0)
			queryMap.put("responseUserName", null);
		else
			queryMap.put("responseUserName", responseUserName);
		if(createUserName == null || createUserName.trim().length() == 0)
			queryMap.put("createUserName", null);
		else
			queryMap.put("createUserName", createUserName);
		queryMap.put("customerId", customerId);
		if(startTime!=null && !startTime.trim().equals("")){
			queryMap.put("startTime", NormalFun.formatStringDateTime(startTime + " 00:00:00"));
		}
		if(endTime!=null && !endTime.trim().equals("")){
			queryMap.put("endTime", NormalFun.formatStringDateTime(endTime + " 23:59:59")); 
		}
		if(assessUser!=null && !assessUser.trim().equals("")){
			queryMap.put("assessUser", assessUser); 
		}
		queryMap.put("createUserId", createUserId);
		queryMap.put("status", status);
		Integer projectCount = projectDao.getProjectCount(queryMap);
		return projectCount;
	}
	
	

	/* (non-Javadoc)
	 * @see com.sales.service.ProjectService#getProjectList(java.lang.String, java.lang.Integer, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String)
	 */
	@Override
	public List getProjectList(String childUserIds, Integer responseUserId,
			String planStartTime, String planEndTime, String completStartTime,
			String completEndTime, Integer pageNumber, Integer pageSize,
			String orderBy, String orderType, Integer sessionUserId) {
		Map queryMap = new HashMap();
		if(childUserIds==null || Constants.USER_FILTER_MARK.equals(childUserIds)){
			childUserIds = null;
		}else if(childUserIds.trim().equals("")){
			childUserIds = "(-1)";
		}
		
		queryMap.put("childUserIds", childUserIds); 
		
		if(responseUserId != null && responseUserId > 0)
			queryMap.put("responseUserId", responseUserId);
		
		if(planStartTime != null && planStartTime.trim().length() > 0)
			queryMap.put("planStartTime", planStartTime);
		if(planEndTime != null && planEndTime.trim().length() > 0)
			queryMap.put("planEndTime", planEndTime);
		
		if(completStartTime != null && completStartTime.trim().length() > 0)
			queryMap.put("completStartTime", completStartTime);
		if(completEndTime != null && completEndTime.trim().length() > 0)
			queryMap.put("completEndTime", completEndTime);
		
		Integer startNumber = (pageNumber - 1) * pageSize; 
		queryMap.put("startNumber", startNumber);
		queryMap.put("pageSize", pageSize);
		
		queryMap.put("orderBy", orderBy);
		queryMap.put("orderType", orderType);
		List projectList = projectDao.getProjectListMobile(queryMap);
		for (int i = 0; i < projectList.size(); i++) {
			SSalesProject project = (SSalesProject) projectList.get(i);
			//查询用户是创建人
			if(project.getCreateUserId()!=null && sessionUserId.intValue()==project.getCreateUserId().intValue()){
				project.setProjectRole(Constants.PROJECT_CREATE);
			}
			//查询用户只是负责人
			else if(project.getCreateUserId()!=null && project.getResponseUserId()!=null 
					&& sessionUserId.intValue()!=project.getCreateUserId().intValue() && sessionUserId.intValue()==project.getResponseUserId().intValue()){
				project.setProjectRole(Constants.PROJECT_RESPONSE);
			}
			//查询用户只是关注人
			else if(project.getCreateUserId()!=null && project.getResponseUserId()!=null 
					&& sessionUserId.intValue()!=project.getCreateUserId().intValue() && sessionUserId.intValue()!=project.getResponseUserId().intValue()){
				project.setProjectRole(Constants.PROJECT_ATTENTION);
			}
		}
		return projectList;
	}

	/* (non-Javadoc)
	 * @see com.sales.service.ProjectService#getProjectCount(java.lang.String, java.lang.Integer, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String)
	 */
	@Override
	public Integer getProjectCount(String childUserIds, Integer responseUserId,
			String planStartTime, String planEndTime, String completStartTime,
			String completEndTime) {
		Map queryMap = new HashMap();
		if(childUserIds==null || Constants.USER_FILTER_MARK.equals(childUserIds)){
			childUserIds = null;
		}else if(childUserIds.trim().equals("")){
			childUserIds = "(-1)";
		}
		
		queryMap.put("childUserIds", childUserIds); 
		
		if(responseUserId != null && responseUserId > 0)
			queryMap.put("responseUserId", responseUserId);
		
		if(planStartTime != null && planStartTime.trim().length() > 0)
			queryMap.put("planStartTime", planStartTime);
		if(planEndTime != null && planEndTime.trim().length() > 0)
			queryMap.put("planEndTime", planEndTime);
		
		if(completStartTime != null && completStartTime.trim().length() > 0)
			queryMap.put("completStartTime", completStartTime);
		if(completEndTime != null && completEndTime.trim().length() > 0)
			queryMap.put("completEndTime", completEndTime);
		
		
		
		Integer projectCount = projectDao.getProjectCountMobile(queryMap);
		return projectCount;
	}

	@Override
	public List getProjectList(Integer sessionUserId, String startTime,
			String endTime, boolean hasChildren) {
		Map queryMap = new HashMap();
		queryMap.put("sessionUserId", sessionUserId); 		
		if(startTime!=null && !startTime.trim().equals("")){
			queryMap.put("startTime", NormalFun.formatStringDateTime(startTime + " 00:00:00"));
		}
		if(endTime!=null && !endTime.trim().equals("")){
			queryMap.put("endTime", NormalFun.formatStringDateTime(endTime + " 23:59:59")); 
		}
		
		List projectList = projectDao.getProjectList(queryMap);
		return projectList;
	}

	public SSalesProject getProjectById(Integer projectId, String childUserIds){
		Map queryMap = new HashMap();
		queryMap.put("projectId", projectId); 
		
		if(childUserIds==null ){
			childUserIds = null;
		}else if(childUserIds.trim().equals(""))
			childUserIds = "(-1)";
		else if(Constants.USER_FILTER_MARK.equals(childUserIds)){
			childUserIds = null;
		}
		queryMap.put("childUserIds", childUserIds);
		SSalesProject project = projectDao.getProjectById(queryMap);
		return project;
	}
    
    public List getAttentionUserByProjectId(Integer projectId){
    	List attentionUser = projectDao.getAttentionUserByProjectId(projectId);
    	return attentionUser;
    }
	
	public List getProjectOpenList(Integer responseUserId, String topical, Integer enumerationId, 
			String startTime, String endTime, Integer pageNumber, Integer pageSize, Integer customerId){
		Map queryMap = new HashMap(); 
		queryMap.put("responseUserId", responseUserId); 
		queryMap.put("topical", topical);
		queryMap.put("enumerationId", enumerationId); 
		queryMap.put("customerId", customerId);
		if(startTime!=null && !startTime.trim().equals("")){
			queryMap.put("startTime", NormalFun.formatStringDateTime(startTime + " 00:00:00"));
		}
		if(endTime!=null && !endTime.trim().equals("")){
			queryMap.put("endTime", NormalFun.formatStringDateTime(endTime + " 23:59:59")); 
		}
		Integer startNumber = (pageNumber - 1) * pageSize; 
		queryMap.put("startNumber", startNumber);
		queryMap.put("pageSize", pageSize);
		List projectList = projectDao.getProjectOpenList(queryMap);
		return projectList;
	}
	
	public Integer getProjectOpenCount(Integer responseUserId, String topical, Integer enumerationId, String startTime, String endTime, Integer customerId){
		Map queryMap = new HashMap();
		queryMap.put("responseUserId", responseUserId); 
		queryMap.put("topical", topical); 
		queryMap.put("enumerationId", enumerationId); 
		queryMap.put("customerId", customerId);
		if(startTime!=null && !startTime.trim().equals("")){
			queryMap.put("startTime", NormalFun.formatStringDateTime(startTime + " 00:00:00"));
		}
		if(endTime!=null && !endTime.trim().equals("")){
			queryMap.put("endTime", NormalFun.formatStringDateTime(endTime + " 23:59:59")); 
		}
		Integer projectCount = projectDao.getProjectOpenCount(queryMap);
		return projectCount;
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
	public List getProjectByCustomerProjectId(Integer customerProjectId) {
		
		return this.projectDao.getProjectByCustomerProjectId(customerProjectId);
	}

	/* (non-Javadoc)
	 * @see com.sales.service.ProjectService#statisPlanReport(java.lang.Integer, java.util.Date, java.util.Date)
	 */
	@Override
	public List<SalePlanStatistics> statisPlanReport(String childrenUserIds,
			Date startTime, Date endTime) {
		if(childrenUserIds==null || childrenUserIds.trim().equals("")){
			childrenUserIds = "(-1)";
		}
		else if(Constants.USER_FILTER_MARK.equals(childrenUserIds)){
			childrenUserIds = null;
		}
		List<SalePlanStatistics> list = projectDao.getPlanExecuteStatistics(
				childrenUserIds, startTime, endTime);
		return list;
	}

	/* (non-Javadoc)
	 * @see com.sales.service.ProjectService#statisPersonPlanReport(java.lang.Integer, java.util.Date, java.util.Date)
	 */
	@Override
	public List<SalePlanWithActivityVO> statisPersonPlanReport(Integer userId,
			Date startTime, Date endTime) {
		List<SalePlanWithActivityVO> list = projectDao.getPersonPlanExecuteStatistics(
				userId, startTime, endTime);
		return list;
	}
	

}
