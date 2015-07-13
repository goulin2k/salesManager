package com.sales.service;

import java.util.List;

import com.sales.model.SSalesQuotation;
import com.sales.model.SaleProcess;

/** 
 * @author  
 * @version 创建时间：2014-3-2 下午04:44:02 
 *  
 */
public interface ReportService {
	
	public List getQuotationList(SSalesQuotation quotation, Integer pageNumber,
			Integer pageSize, String childUserIds, Integer roleId);
	/**
	 * 查询所有已经回复的询价单报表记录
	 * @param quotation
	 * @return
	 */
	public List getAllQuotationListReplied(SSalesQuotation quotation);
	
	public Integer getQuotationCount(SSalesQuotation quotation, String childUserIds, Integer roleId);
	
	public List getSaleProcPageList(SaleProcess saleProcess,Integer pageNumber,Integer pageSize);
	public List getSaleProcList(SaleProcess saleProcess);
	
	public Integer getSaleProcCount(SaleProcess saleProcess);

}
