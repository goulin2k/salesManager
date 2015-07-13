package com.sales.dao;

import java.util.List; 
import java.util.Map;

import com.sales.model.TStockBill;

public interface TStockBillDAO {
	
	public TStockBill getStockBillById(Map queryMap);
	
	public List getStockBillList(TStockBill stockBill);
	
	public Integer getStockBillCount(TStockBill stockBill);
	
	public List getStockBillAllList(String billIds);

}
