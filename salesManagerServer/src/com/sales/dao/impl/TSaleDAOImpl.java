package com.sales.dao.impl;

import java.util.List; 
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.sales.dao.TSaleDAO; 
import com.sales.model.TSale;

public class TSaleDAOImpl extends SqlMapClientDaoSupport implements TSaleDAO {
	
	public TSale getSaleById(Map queryMap){
		TSale sale = (TSale) getSqlMapClientTemplate().queryForObject("t_sale.getSaleById", queryMap);
		return sale;
	}
	
	public List getSaleList(TSale sale){
		List saleList = (List) getSqlMapClientTemplate().queryForList("t_sale.getSaleList", sale);
		return saleList;
	}
	
	public Integer getSaleCount(TSale sale){
		Integer saleCount = (Integer) getSqlMapClientTemplate().queryForObject("t_sale.getSaleCount", sale);
		return saleCount;
	}
	
	public List getSaleAllList(String billIds){
		List saleList = (List) getSqlMapClientTemplate().queryForList("t_sale.getSaleAllList", billIds);
		return saleList;
	}
	
	public List getSaleByCustomerId(Integer customerId){
		List saleList = (List) getSqlMapClientTemplate().queryForList("t_sale.getSaleByCustomerId", customerId);
		return saleList;
	}

}
