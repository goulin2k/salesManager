package com.sales.service.impl;
 
import java.util.HashMap;
import java.util.List; 
import java.util.Map;

import com.sales.common.Constants;
import com.sales.dao.TReceiveBillDAO;
import com.sales.model.TReceiveBill;
import com.sales.service.ReceiveBillService;

public class ReceiveBillServiceImpl implements ReceiveBillService {
	
	private TReceiveBillDAO receiveBillDao;
	
	public TReceiveBill getReceiveBillById(Integer receiveBillId, String customerIds){
		if(customerIds==null || customerIds.trim().equals("")){
			customerIds = "(-1)";
		}
		else if(Constants.USER_FILTER_MARK.equals(customerIds)){
			customerIds = null;
		}
		Map queryMap = new HashMap();
		queryMap.put("receiveBillId", receiveBillId); 
		queryMap.put("customerIds", customerIds); 
		TReceiveBill bill = (TReceiveBill) receiveBillDao.getReceiveBillById(queryMap);
		return bill;
	}
	
	public List getReceiveBillList(Integer pageNumber, Integer pageSize, TReceiveBill receiveBill, String customerIds){ 
		Integer startNumber = (pageNumber - 1) * pageSize;
		receiveBill.setStartNumber(startNumber);
		receiveBill.setPageSize(pageSize);
		if(customerIds==null || customerIds.trim().equals("")){
			customerIds = "(-1)";
		}
		else if(Constants.USER_FILTER_MARK.equals(customerIds)){
			customerIds = null;
		}
		receiveBill.setCustomerIds(customerIds);
		List billList = receiveBillDao.getReceiveBillList(receiveBill);
		return billList;
	}
	
	public Integer getReceiveBillCount(TReceiveBill receiveBill, String customerIds){
		if(customerIds==null || customerIds.trim().equals("")){
			customerIds = "(-1)";
		}
		else if(Constants.USER_FILTER_MARK.equals(customerIds)){
			customerIds = null;
		}
		receiveBill.setCustomerIds(customerIds);
		Integer billCount = receiveBillDao.getReceiveBillCount(receiveBill);
		return billCount;
	}
	
	public List getReceiveBillAllList(String billIds){
		List billList = receiveBillDao.getReceiveBillAllList(("(" + billIds + ")")); 
		return billList;
	}

	public TReceiveBillDAO getReceiveBillDao() {
		return receiveBillDao;
	}

	public void setReceiveBillDao(TReceiveBillDAO receiveBillDao) {
		this.receiveBillDao = receiveBillDao;
	}

}
