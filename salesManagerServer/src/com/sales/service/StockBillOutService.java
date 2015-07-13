package com.sales.service;
 
import java.util.List; 

import com.sales.model.TStockBillOut;

/**
 * 销售出库单
 * @author apple
 *
 */
public interface StockBillOutService {
	
	public TStockBillOut getStockBillOutById(Integer stockBillOutId, String customerIds);
	
	public List getStockBillOutList(Integer pageNumber, Integer pageSize, TStockBillOut stockBillOut, String customerIds);
	
	public Integer getStockBillOutCount(TStockBillOut stockBillOut, String customerIds);
	
	public List getStockBillOutAllList(String billIds);

}
