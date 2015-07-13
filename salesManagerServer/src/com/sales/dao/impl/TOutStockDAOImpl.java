package com.sales.dao.impl;

import java.util.List; 
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.sales.dao.TOutStockDAO;
import com.sales.model.TOutStock;

public class TOutStockDAOImpl extends SqlMapClientDaoSupport implements TOutStockDAO {
	
	public TOutStock getOutStockById(Map queryMap){
		TOutStock stock = (TOutStock) getSqlMapClientTemplate().queryForObject("t_out_stock.getOutStockById", queryMap);
		return stock;
	}
	
	public List getOutStockList(TOutStock outStock){
		List outStockList = (List) getSqlMapClientTemplate().queryForList("t_out_stock.getOutStockList", outStock);
		return outStockList;
	}
	
	public Integer getOutStockCount(TOutStock outStock){
		Integer outStockCount = (Integer) getSqlMapClientTemplate().queryForObject("t_out_stock.getOutStockCount", outStock);
		return outStockCount;
	}
	
	public List getOutStockAllList(String billIds){
		List outStockList = (List) getSqlMapClientTemplate().queryForList("t_out_stock.getOutStockAllList", billIds);
		return outStockList;
	}

}
