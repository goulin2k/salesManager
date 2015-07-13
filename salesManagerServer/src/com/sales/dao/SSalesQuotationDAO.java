package com.sales.dao;

import java.util.List;
import java.util.Map;

import com.sales.model.SSalesQuotation;

public interface SSalesQuotationDAO {
	
	public Integer insertQuotation(SSalesQuotation quotation);

    public void updateQuotation(SSalesQuotation quotation);
 
    public SSalesQuotation getQuotationById(Map queryMap);
    
    public List getQuotationList(Map queryMap);
   
    public Integer getQuotationCount(Map queryMap);    
    
    public SSalesQuotation getQuotationById(Integer quotationId);
    public List getAllQuotationList(Map queryMap);
    
    public String getMaxCode();
    
}