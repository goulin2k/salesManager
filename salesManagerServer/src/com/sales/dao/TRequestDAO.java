package com.sales.dao;

import java.util.List; 
import java.util.Map;

import com.sales.model.TRequest;

public interface TRequestDAO {
	
	public TRequest getRequestById(Map queryMap);
	
	public List getRequestList(TRequest request);
	
	public Integer getRequestCount(TRequest request);
	
	public List getRequestAllList(String billIds);

}
