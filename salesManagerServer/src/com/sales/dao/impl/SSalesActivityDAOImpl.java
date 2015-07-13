package com.sales.dao.impl;

import java.util.List;
import java.util.Map;

import com.sales.dao.SSalesActivityDAO;
import com.sales.model.SSalesActivity;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class SSalesActivityDAOImpl extends SqlMapClientDaoSupport implements SSalesActivityDAO {

    public SSalesActivityDAOImpl() {
        super();
    }
 
    public Integer insertActivity(SSalesActivity activity) {
    	Integer activityId = (Integer) getSqlMapClientTemplate().insert("s_sales_activity.insertActivity", activity);
    	return activityId;
    }
 
    public SSalesActivity getActivityById(Map queryMap){
        SSalesActivity activity = (SSalesActivity) getSqlMapClientTemplate().queryForObject("s_sales_activity.getActivityById", queryMap);
        return activity;
    }
 
    public void deleteActivity(Integer activityId) { 
        getSqlMapClientTemplate().delete("s_sales_activity.deleteActivity", activityId); 
    }
    
    public List getActivityList(Map queryMap){
    	List activityList = getSqlMapClientTemplate().queryForList("s_sales_activity.getActivityList", queryMap);
    	return activityList;
    }
    
    public Integer getActivityCount(Map queryMap){
    	Integer activityCount = (Integer) getSqlMapClientTemplate().queryForObject("s_sales_activity.getActivityCount", queryMap);
    	return activityCount;
    }
 
    public void updateActivity(SSalesActivity activity) {
        getSqlMapClientTemplate().update("s_sales_activity.updateActivity", activity);
    }
    
    public List getActivityByCustomerId(Integer customerId){
    	List projectList = getSqlMapClientTemplate().queryForList("s_sales_activity.getActivityByCustomerId", customerId);
    	return projectList;
    }

	@Override
	public List getActivityByCustomerProId(Integer customerProjectId) {
		// TODO Auto-generated method stub
		List projectList = getSqlMapClientTemplate().queryForList("s_sales_activity.getActivityByCustomerProId", customerProjectId);
		return projectList;
	}
    
}