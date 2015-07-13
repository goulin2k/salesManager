package com.sales.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.sales.model.SProjectAttentionUser;
import com.sales.model.SSalesProject;
import com.sales.model.SalePlanStatistics;
import com.sales.model.SalePlanWithActivityVO;

public interface SSalesProjectDAO {

	public Integer insertSalesProject(SSalesProject project);
	
	/**
	 * 修改指定销售计划状态为提交
	 * @param project
	 */
	public void commitSalesProject(SSalesProject project);
    
    public void insertProjectAttentionUser(SProjectAttentionUser user); 
    
    public int deleteByPrimaryKey(Integer projectId);
    
    public SSalesProject getProjectById(Map queryMap);
    
    public List getProjectList(Map queryMap); 
    
    public Integer getProjectCount(Map queryMap);
    
    public List getProjectListMobile(Map queryMap); 
    
    public Integer getProjectCountMobile(Map queryMap);
    
    public void updateProject(SSalesProject project);
    
    public void deleteAttentionUserByProject(Integer projectId);
    
    public void updateSalesProjectRate(SSalesProject project);
    
    public List getAttentionUserByProjectId(Integer projectId);
    
    public List getProjectOpenList(Map queryMap);
    
    public Integer getProjectOpenCount(Map queryMap);
    
    public List getProjectByCustomerProjectId(Integer customerProjectId);
    
    /**
     * 查询指定用户及其下级计划执行情况统计
     * @param childrenUserIds
     * @param startTime
     * @param endTime
     * @return
     */
    public List<SalePlanStatistics> getPlanExecuteStatistics(String childrenUserIds, Date startTime, Date endTime);
    
    /**
     * 查询指定的用户时间段内计划执行详细情况
     * @param userId
     * @param startTime
     * @param endTime
     * @return
     */
    public List<SalePlanWithActivityVO> getPersonPlanExecuteStatistics(Integer userId, Date startTime, Date endTime);
    
}