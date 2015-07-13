package com.sales.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sales.dao.TK3EntryDAO;
import com.sales.model.TCurrency;
import com.sales.service.K3EntryService;

public class K3EntryServiceImpl implements K3EntryService {
	
	private TK3EntryDAO k3Dao;
	
	public List getPayCondition(){
		List payConditionList = k3Dao.getPayCondition();
		return payConditionList;
	}
	
	public List getCurrency(){
		List currencyList = k3Dao.getCurrency();
		return currencyList;
	}
	
	public TCurrency getCurrencyById(Integer fCurrencyID){
		TCurrency currency = k3Dao.getCurrencyById(fCurrencyID);
		return currency;
	}
	
	public List getEmp(String fName){
		Map queryMap = new HashMap();
		queryMap.put("fName", fName);
		List empList = k3Dao.getEmp(queryMap);
		return empList;
	}
	
	public List getDepartList(){
		List departList = k3Dao.getDepart();
		return departList;
	}
	
	public List getStaff(){
		List staffList = k3Dao.getStaff();
		return staffList;
	}
	
	public List getUser(){
		List userList = k3Dao.getUser();
		return userList;
	}
	
	public List getEmpList(){
		List empList = k3Dao.getEmpList();
		return empList;
	}
	
	

	@Override
	public List getInvoiceTypeList() {
		List invoiceTypeList = k3Dao.getInvoiceTypes();
		return invoiceTypeList;
	}

	@Override
	public List getTransTypeList() {
		List transTypeList = k3Dao.getTransTypes();
		return transTypeList;
	}

	public TK3EntryDAO getK3Dao() {
		return k3Dao;
	}

	public void setK3Dao(TK3EntryDAO k3Dao) {
		this.k3Dao = k3Dao;
	}

}
