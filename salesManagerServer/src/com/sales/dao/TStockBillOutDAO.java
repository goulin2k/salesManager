package com.sales.dao;

import java.util.List; 
import java.util.Map;

import com.sales.model.TStockBillOut;

public interface TStockBillOutDAO {
	
	public TStockBillOut getStockBillOutById(Map queryMap);
	
	public List getStockBillOutList(TStockBillOut stockBillOut);
	
	public Integer getStockBillOutCount(TStockBillOut stockBillOut);
	
	public List getStockBillOutAllList(String billIds);

}
