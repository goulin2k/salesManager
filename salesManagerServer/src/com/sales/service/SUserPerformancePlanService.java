/**
 * 
 */
package com.sales.service;

import java.util.List;

import com.sales.model.SUserPerformanceItem;
import com.sales.model.SUserPerformancePlan;

/**
 * @author Leo
 *
 */
public interface SUserPerformancePlanService {
	
    public List<SUserPerformancePlan> getUserPlanListPage(SUserPerformancePlan upp);
    
    public Integer getUserPlanListPageCount(SUserPerformancePlan upp);

    public void addUserPlan(SUserPerformancePlan upp);
    
    public void deleteUserPlan(Integer upid);
    
    public SUserPerformancePlan getUserPlan(Integer upid);
    
    public void updateUserPlanItemScore(SUserPerformancePlan upp);
    
    public List<SUserPerformanceItem> getUserItemListPage(SUserPerformanceItem upi);
    
    public Integer getUserItemListPageCount(SUserPerformanceItem upi);
    
    public SUserPerformanceItem getUserItem(Integer userItemId);
    
    public void updateUserItemScore(SUserPerformanceItem upi);
    
    public double calSqlScore(Integer userItemId,Integer userId, String userIds);
    
    public List<SUserPerformancePlan> getUserPlanListByCircle(SUserPerformancePlan upp);
}
