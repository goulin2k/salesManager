package com.sales.service;
 
import java.util.List; 

import com.sales.model.SSalesQuotation;
import com.sales.model.SSalesQuotationProduct;

public interface QuotationService {
	
	public void addQuotation(SSalesQuotation quotation, List<SSalesQuotationProduct> products) throws Exception;

    public void updateQuotation(SSalesQuotation quotation, List<SSalesQuotationProduct> products);
 
    public SSalesQuotation getQuotationById(Integer quotationId, String childUserIds, Integer purchaseUserId, Integer roleId);
    
    public SSalesQuotation getReplyQuotationById(Integer quotationId);
    
    public List getQuotationList(SSalesQuotation quotation, Integer pageNumber,
			Integer pageSize, String childUserIds, Integer roleId);
    
    public Integer getQuotationCount(SSalesQuotation quotation, String childUserIds, Integer roleId); 
    
    public String getQuotationCode(Integer year);
    
    public SSalesQuotation getQuotationById(Integer quotationId);
    
    public SSalesQuotation toOrfq(Integer quotationId);

}
