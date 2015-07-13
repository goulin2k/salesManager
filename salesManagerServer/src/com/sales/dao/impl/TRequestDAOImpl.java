package com.sales.dao.impl;

import java.util.List; 
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.sales.dao.TRequestDAO;
import com.sales.model.TRequest;

public class TRequestDAOImpl extends SqlMapClientDaoSupport implements TRequestDAO {
	
	public TRequest getRequestById(Map queryMap){
		TRequest request = (TRequest) getSqlMapClientTemplate().queryForObject("t_request.getRequestById", queryMap);
		return request;
	}
	
	public List getRequestList(TRequest request){
		List requestList = (List) getSqlMapClientTemplate().queryForList("t_request.getRequestList", request);
		return requestList;
	}
	
	public Integer getRequestCount(TRequest request){
		Integer requestCount = (Integer) getSqlMapClientTemplate().queryForObject("t_request.getRequestCount", request);
		return requestCount;
	}
	
	public List getRequestAllList(String billIds){
		List requestList = (List) getSqlMapClientTemplate().queryForList("t_request.getRequestAllList", billIds);
		return requestList;
	}

}
