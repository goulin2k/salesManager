package com.sales.dao;

import java.util.List;
import java.util.Map;

import com.sales.model.SSalesActivity;

public interface SSalesActivityDAO {

	public Integer insertActivity(SSalesActivity activity);
 
    public SSalesActivity getActivityById(Map queryMap);
 
    public void deleteActivity(Integer activityId);
    
    public List getActivityList(Map queryMap);
    
    public Integer getActivityCount(Map queryMap);
    
    public void updateActivity(SSalesActivity activity);
    
    public List getActivityByCustomerId(Integer customerId);
    
    public List getActivityByCustomerProId(Integer customerProjectId);
}