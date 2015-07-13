package com.sales.dao;

import java.util.List; 
import java.util.Map;

import com.sales.model.TSale;

public interface TSaleDAO {
	
	public TSale getSaleById(Map queryMap);
	
	public List getSaleList(TSale sale);
	
	public Integer getSaleCount(TSale sale);
	
	public List getSaleAllList(String billIds);
	
	public List getSaleByCustomerId(Integer customerId);

}
