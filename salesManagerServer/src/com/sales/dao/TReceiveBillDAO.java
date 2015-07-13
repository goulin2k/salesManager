package com.sales.dao;

import java.util.List; 
import java.util.Map;

import com.sales.model.TReceiveBill;

public interface TReceiveBillDAO {
	
	public TReceiveBill getReceiveBillById(Map queryMap);
	
	public List getReceiveBillList(TReceiveBill receiveBill);
	
	public Integer getReceiveBillCount(TReceiveBill receiveBill);
	
	public List getReceiveBillAllList(String billIds);

}
