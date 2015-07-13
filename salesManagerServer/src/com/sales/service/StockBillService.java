package com.sales.service;
 
import java.util.List; 

import com.sales.model.TStockBill;

/**
 * 采购入库
 * @author apple
 *
 */
public interface StockBillService {
	
	public TStockBill getStockBillById(Integer stockBillId, String customerIds);
	
	public List getStockBillList(Integer pageNumber, Integer pageSize, TStockBill stockBill, String customerIds);
	
	public Integer getStockBillCount(TStockBill stockBill, String customerIds);
	
	public List getStockBillAllList(String billIds);

}
