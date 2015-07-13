package com.sales.dao;

import java.util.List;

import com.sales.model.SSalesAssess;

public interface SSalesAssessDAO {
	
	public Integer insertSalesAssess(SSalesAssess assess);
  
    public SSalesAssess getSalesAssessById(Integer assessId);
 
    public void deleteSalesAssess(Integer assessId);
    
    public void updateSalesAssess(SSalesAssess assess);
    
    public Integer getSaleAssessCountByPlan(Integer projectId);
    public List<SSalesAssess> getSaleAssessListByPlan(Integer projectId);
    
}