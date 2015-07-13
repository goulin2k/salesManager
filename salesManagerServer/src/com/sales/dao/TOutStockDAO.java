package com.sales.dao;

import java.util.List; 
import java.util.Map;

import com.sales.model.TOutStock;

public interface TOutStockDAO {
	
	public TOutStock getOutStockById(Map queryMap);
	
	public List getOutStockList(TOutStock outStock);
	
	public Integer getOutStockCount(TOutStock outStock);
	
	public List getOutStockAllList(String billIds);

}
