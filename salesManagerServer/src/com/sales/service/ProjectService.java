package com.sales.service;
  
import java.util.Date;
import java.util.List;  
import com.sales.model.SSalesProject;
import com.sales.model.SalePlanStatistics;
import com.sales.model.SalePlanWithActivityVO;

public interface ProjectService {
	
	public void addProject(SSalesProject project, String attentionUserIds);
	
	/**
	 * 提交销售计划
	 * @param project
	 */
	public void commitProject(SSalesProject project);
	
	/**
	 * 提交销售计划
	 * @param projectId
	 */
	public void commitProject(Integer projectId);
	
	/**
	 * 删除指定id的项目计划
	 * @param projectId
	 */
	public void removeProject(Integer projectId);
	
	public void updateProject(SSalesProject project, String attentionUserIds, String childUserIds);
	
	public List getProjectList(String childUserIds, Integer responseUserId, String planStartTime, String planEndTime,
			String completStartTime, String completEndTime, Integer pageNumber, Integer pageSize, String orderBy,
			String orderType, Integer sessionUserId);
	
	public Integer getProjectCount(String childUserIds, Integer responseUserId, String planStartTime, String planEndTime,
			String completStartTime, String completEndTime);
	
	public List getProjectList(String childUserIds, String topical, Integer enumerationId, String responseUserName,
			String createUserName, String startTime, String endTime, String assessUser, Integer createUserId, int status,
			Integer pageNumber, Integer pageSize, Integer customerId, Integer sessionUserId);
	
	/**
	 * 查询指定时间段的个人工作计划
	 * @param sessionUserId
	 * @param startTime
	 * @param endTimehaveChildren
	 * @param endTime
	 * @return hasChildren  是否包含下级用户计划
	 */
	public List<SSalesProject> getProjectList(Integer sessionUserId, 
			String startTime, String endTime, boolean hasChildren);
	
	public Integer getProjectCount(String childUserIds, String topical, Integer enumerationId, String responseUserName, String createUserName,
			String startTime, String endTime, String assessUser, Integer createUserId, int status, Integer customerId);
	
	public SSalesProject getProjectById(Integer projectId, String childUserIds);
	
	public List getAttentionUserByProjectId(Integer projectId);
	
	public List getProjectOpenList(Integer responseUserId, String topical, Integer enumerationId, 
			String startTime, String endTime, Integer pageNumber, Integer pageSize, Integer customerId);
	
	public Integer getProjectOpenCount(Integer responseUserId, String topical, Integer enumerationId, String startTime, String endTime, Integer customerId);
	
	public List getProjectByCustomerProjectId(Integer customerProjectId);
	
	/** 
	 * 查询指定用户及其下级计划执行情况统计
	 * @param childrenUserIds
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public List<SalePlanStatistics> statisPlanReport(String childrenUserIds, Date startTime, Date endTime);
	
	/**
	 * 查询指定用户的计划执行详细情况
	 * @param userId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public List<SalePlanWithActivityVO> statisPersonPlanReport(Integer userId, Date startTime, Date endTime);
}
