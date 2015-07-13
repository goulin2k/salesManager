package com.sales.dao;

import java.util.List;
import java.util.Map;

import com.sales.model.TCurrency;

public interface TK3EntryDAO {
	
	public List getPayCondition();
	
	public List getCurrency();
	
	public TCurrency getCurrencyById(Integer fCurrencyID);
	
	public List getEmp(Map queryMap);
	
	public List getDepart();
	
	public List getStaff();
	
	public List getUser();
	
	public List getEmpList();
	
	/**
	 * @return 销售报价单开票方式
	 */
	public List getInvoiceTypes();
	
	/**
	 * @return	销售报价单运输方式
	 */
	public List getTransTypes();

}
