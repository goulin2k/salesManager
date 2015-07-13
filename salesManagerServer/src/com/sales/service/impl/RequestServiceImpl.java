package com.sales.service.impl;
 
import java.util.HashMap;
import java.util.List; 
import java.util.Map;

import com.sales.common.Constants;
import com.sales.dao.TRequestDAO;
import com.sales.model.TRequest;
import com.sales.service.RequestService;

public class RequestServiceImpl implements RequestService {
	
	private TRequestDAO requestDao;
	
	public TRequest getRequestById(Integer requestId, String customerIds){
		if(customerIds==null || customerIds.trim().equals("")){
			customerIds = "(-1)";
		}
		else if(Constants.USER_FILTER_MARK.equals(customerIds)){
			customerIds = null;
		}
		Map queryMap = new HashMap();
		queryMap.put("requestId", requestId); 
		queryMap.put("customerIds", customerIds); 
		TRequest request = requestDao.getRequestById(queryMap);
		return request;
	}
	
	public List getRequestList(Integer pageNumber, Integer pageSize, TRequest request, String customerIds){ 
		Integer startNumber = (pageNumber - 1) * pageSize;
		request.setStartNumber(startNumber);
		request.setPageSize(pageSize);
		if(customerIds==null || customerIds.trim().equals("")){
			customerIds = "(-1)";
		}
		else if(Constants.USER_FILTER_MARK.equals(customerIds)){
			customerIds = null;
		}
		request.setCustomerIds(customerIds);
		List requestList = requestDao.getRequestList(request);
		return requestList;
	}
	
	public Integer getRequestCount(TRequest request, String customerIds){
		if(customerIds==null || customerIds.trim().equals("")){
			customerIds = "(-1)";
		}
		else if(Constants.USER_FILTER_MARK.equals(customerIds)){
			customerIds = null;
		}
		request.setCustomerIds(customerIds);
		Integer requestCount = requestDao.getRequestCount(request);
		return requestCount;
	}
	
	public List getRequestAllList(String billIds){
		List requestList = requestDao.getRequestAllList(("(" + billIds + ")")); 
		return requestList;
	}

	public TRequestDAO getRequestDao() {
		return requestDao;
	}

	public void setRequestDao(TRequestDAO requestDao) {
		this.requestDao = requestDao;
	}

}
