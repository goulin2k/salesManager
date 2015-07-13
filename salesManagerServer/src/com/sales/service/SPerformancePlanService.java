/**
 * 
 */
package com.sales.service;

import java.util.List;

import com.sales.model.SPerformancePlan;
import com.sales.model.SPerformanceSql;
import com.sales.model.SPlanItem;
import com.sales.model.SPosition;
import com.sales.model.SUser;
import com.sales.model.SUserJson;

/**
 * @author Leo
 *
 */
public interface SPerformancePlanService {
	
    public int addPerformancePlan(SPerformancePlan spp);
    
    public List<SPerformancePlan> getPlanListPage(SPerformancePlan spp);
    
    public Integer getPlanListPageCount(SPerformancePlan spp);
    
    public List<SPlanItem> getPlanItemListByConditions(SPlanItem spi);
    
    public List<SPerformanceSql> getSqlList();
    
    public List<SPosition> getPostList(Integer postType);
    
    public SPerformancePlan getPerformancePlan(Integer planId);
    
    public void deletePlan(Integer planId);
    
    public List<SUser> getUserList(Integer postType,Integer postId);
    
    public List<SPerformancePlan> getPlanListByUserPosition(Integer userId);
    
    public List<SUserJson> getChildrenUserList(Integer parentId);
}
