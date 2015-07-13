package com.sales.service;
 
import java.util.List; 
import com.sales.model.SSalesActivity;

public interface ActivityService {
	
	public void addActivity(SSalesActivity activity, String childUserIds);
	
	public void updateActivity(SSalesActivity activity);
	
	public SSalesActivity getActivityById(Integer activityId, String childUserIds);
	
	public void deleteActivity(Integer activityId);
	
	public List getActivityList(Integer projectId, Integer customerId, Integer responseUserId, String responseUserName, String topical,
			String startTime, String endTime, Integer pageNumber, Integer pageSize, Integer enumerationId, String childUserIds);
	
	public Integer getActivityCount(Integer projectId, Integer customerId, Integer responseUserId, String responseUserName, 
			String topical, String startTime, String endTime, Integer enumerationId, String childUserIds);
	
	public List getActivityByCustomerId(Integer customerId);
	
	public List getActivityByCustomerProId(Integer customerProId);

}
