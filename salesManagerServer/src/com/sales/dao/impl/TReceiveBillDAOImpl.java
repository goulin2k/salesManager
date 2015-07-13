package com.sales.dao.impl;

import java.util.List; 
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.sales.dao.TReceiveBillDAO;
import com.sales.model.TReceiveBill;

public class TReceiveBillDAOImpl extends SqlMapClientDaoSupport implements TReceiveBillDAO {
	
	public TReceiveBill getReceiveBillById(Map queryMap){
		TReceiveBill bill = (TReceiveBill) getSqlMapClientTemplate().queryForObject("t_receive_bill.getReceiveBillById", queryMap);
		return bill;
	}
	
	public List getReceiveBillList(TReceiveBill receiveBill){
		List billList = (List) getSqlMapClientTemplate().queryForList("t_receive_bill.getReceiveBillList", receiveBill);
		return billList;
	}
	
	public Integer getReceiveBillCount(TReceiveBill receiveBill){
		Integer billCount = (Integer) getSqlMapClientTemplate().queryForObject("t_receive_bill.getReceiveBillCount", receiveBill);
		return billCount;
	}
	
	public List getReceiveBillAllList(String billIds){
		List billList = (List) getSqlMapClientTemplate().queryForList("t_receive_bill.getReceiveBillAllList", billIds);
		return billList;
	}

}
