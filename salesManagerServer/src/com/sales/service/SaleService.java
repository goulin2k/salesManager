package com.sales.service;
 
import java.util.List; 

import com.sales.model.TSale;

/**
 * 销售发票
 * @author apple
 *
 */
public interface SaleService {
	
	public TSale getSaleById(Integer saleId, String customerIds);
	
	public List getSaleList(Integer pageNumber, Integer pageSize, TSale sale, String customerIds);
	
	public Integer getSaleCount(TSale sale, String customerIds);
	
	public List getSaleAllList(String billIds);
	
	public List getSaleBillList(Integer customerId);

}
