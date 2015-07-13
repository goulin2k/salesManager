package com.sales.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.sales.dao.TK3EntryDAO;
import com.sales.model.TCurrency;

public class TK3EntryDAOImpl extends SqlMapClientDaoSupport implements TK3EntryDAO {
	
	public List getPayCondition(){
		List payConditionList = (List) getSqlMapClientTemplate().queryForList("t_k3_entry.getPayCondition");
		return payConditionList;
	}
	
	public List getCurrency(){
		List payCurrencyList = (List) getSqlMapClientTemplate().queryForList("t_k3_entry.getCurrency");
		return payCurrencyList;
	}
	
	public TCurrency getCurrencyById(Integer fCurrencyID){
		TCurrency currency = (TCurrency) getSqlMapClientTemplate().queryForObject("t_k3_entry.getCurrencyById", fCurrencyID);
		return currency;
	}
	
	public List getEmp(Map queryMap){
		List empList = (List) getSqlMapClientTemplate().queryForList("t_k3_entry.getEmp", queryMap);
		return empList;
	}
	
	public List getDepart(){
		List departList = (List) getSqlMapClientTemplate().queryForList("t_k3_entry.getDepart");
		return departList;
	}
	
	public List getStaff(){
		List staffList = (List) getSqlMapClientTemplate().queryForList("t_k3_entry.getStaff");
		return staffList;
	}
	
	public List getUser(){
		List userList = (List) getSqlMapClientTemplate().queryForList("t_k3_entry.getUser");
		return userList;
	}
	
	public List getEmpList(){
		List empList = (List) getSqlMapClientTemplate().queryForList("t_k3_entry.getEmpList");
		return empList;
	}

	@Override
	public List getInvoiceTypes() {
		List invoiceTypeList = (List) getSqlMapClientTemplate()
				.queryForList("t_k3_entry.selectInvoiceType");
		return invoiceTypeList;
	}

	@Override
	public List getTransTypes() {
		List transTypeList = (List) getSqlMapClientTemplate()
				.queryForList("t_k3_entry.selectTransType");
		return transTypeList;
	}
	
	

}
