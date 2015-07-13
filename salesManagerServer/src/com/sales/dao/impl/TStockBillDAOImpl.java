package com.sales.dao.impl;

import java.util.List; 
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.sales.dao.TStockBillDAO;
import com.sales.model.TStockBill;

public class TStockBillDAOImpl extends SqlMapClientDaoSupport implements TStockBillDAO {
	
	public TStockBill getStockBillById(Map queryMap){
		TStockBill stockBill = (TStockBill) getSqlMapClientTemplate()
				.queryForObject("t_stock_bill.getStockBillById", queryMap);
		return stockBill;
	}
	
	public List getStockBillList(TStockBill stockBill){
		List stockBillList = (List) getSqlMapClientTemplate()
				.queryForList("t_stock_bill.getStockBillList", stockBill);
		return stockBillList;
	}
	
	public Integer getStockBillCount(TStockBill stockBill){
		Integer stockBillCount = (Integer) getSqlMapClientTemplate()
				.queryForObject("t_stock_bill.getStockBillCount", stockBill);
		return stockBillCount;
	}
	
	public List getStockBillAllList(String billIds){
		List stockBillList = (List) getSqlMapClientTemplate()
				.queryForList("t_stock_bill.getStockBillAllList", billIds);
		return stockBillList;
	}

}
