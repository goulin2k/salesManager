package com.sales.service;

import java.util.List;

import com.sales.model.SSalesAssess;

public interface AssessService {
	
	public void addSalesAssess(SSalesAssess assess, String childUserIds);
  
    public SSalesAssess getSalesAssessById(Integer assessId);
    
    public void updateSalesAssess(SSalesAssess assess);
    
    public Integer getSaleAssessCountByPlan(Integer projectId);
    public List<SSalesAssess> getSaleAssessListByPlan(Integer projectId);

}
