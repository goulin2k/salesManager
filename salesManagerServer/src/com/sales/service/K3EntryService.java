package com.sales.service;

import java.util.List;

import com.sales.model.TCurrency;

public interface K3EntryService {
	
	public List getPayCondition();
	
	public List getCurrency();
	
	public TCurrency getCurrencyById(Integer fCurrencyID);
	
	public List getEmp(String fName);
	
	public List getDepartList();
	
	public List getStaff();
	
	public List getUser();
	
	public List getEmpList();
	
	/**
	 * @return 销售报价单开票方式
	 */
	public List getInvoiceTypeList();
	
	/**
	 * @return	销售报价单运输方式
	 */
	public List getTransTypeList();

}
