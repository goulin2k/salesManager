package com.sales.service;
 
import java.util.List; 

import com.sales.model.TRequest;

public interface RequestService {
	
	public TRequest getRequestById(Integer requestId, String customerIds);
	
	public List getRequestList(Integer pageNumber, Integer pageSize, TRequest request, String customerIds);
	
	public Integer getRequestCount(TRequest request, String customerIds);
	
	public List getRequestAllList(String billIds);

}
