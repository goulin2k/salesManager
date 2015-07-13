package com.sales.dao.impl;

import java.util.List; 
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.sales.dao.TStockBillOutDAO;
import com.sales.model.TStockBillOut;

public class TStockBillOutDAOImpl extends SqlMapClientDaoSupport implements TStockBillOutDAO {
	
	public TStockBillOut getStockBillOutById(Map queryMap){
		TStockBillOut stockBillOut = (TStockBillOut) getSqlMapClientTemplate().queryForObject("t_stock_bill_out.getStockBillOutById", queryMap);
		return stockBillOut;
	}
	
	public List getStockBillOutList(TStockBillOut stockBillOut){
		List stockBillOutList = (List) getSqlMapClientTemplate().queryForList("t_stock_bill_out.getStockBillOutList", stockBillOut);
		return stockBillOutList;
	}
	
	public Integer getStockBillOutCount(TStockBillOut stockBillOut){
		Integer stockBillOutCount = (Integer) getSqlMapClientTemplate().queryForObject("t_stock_bill_out.getStockBillOutCount", stockBillOut);
		return stockBillOutCount;
	}
	
	public List getStockBillOutAllList(String billIds){
		List stockBillOutList = (List) getSqlMapClientTemplate().queryForList("t_stock_bill_out.getStockBillOutAllList", billIds);
		return stockBillOutList;
	}

}
