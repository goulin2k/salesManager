package com.sales.service.impl;
 
import java.util.HashMap;
import java.util.List; 
import java.util.Map;

import com.sales.common.Constants;
import com.sales.dao.TStockBillDAO;
import com.sales.model.TStockBill;
import com.sales.service.StockBillService;

public class StockBillServiceImpl implements StockBillService {
	
	private TStockBillDAO stockBillDao;

	public TStockBill getStockBillById(Integer stockBillId, String customerIds){
		if(customerIds==null || customerIds.trim().equals("")){
			customerIds = "(-1)";
		}
		else if(Constants.USER_FILTER_MARK.equals(customerIds)){
			customerIds = null;
		}
		Map queryMap = new HashMap();
		queryMap.put("stockBillId", stockBillId); 
		queryMap.put("customerIds", customerIds); 
		TStockBill stockBill = stockBillDao.getStockBillById(queryMap);
		return stockBill;
	}
	
	public List getStockBillList(Integer pageNumber, Integer pageSize, TStockBill stockBill, String customerIds){ 
		Integer startNumber = (pageNumber - 1) * pageSize;
		stockBill.setStartNumber(startNumber);
		stockBill.setPageSize(pageSize);
		if(customerIds==null || customerIds.trim().equals("")){
			customerIds = "(-1)";
		}
		else if(Constants.USER_FILTER_MARK.equals(customerIds)){
			customerIds = null;
		}
		stockBill.setCustomerIds(customerIds);
		List stockBillList = stockBillDao.getStockBillList(stockBill);
		return stockBillList;
	}
	
	public Integer getStockBillCount(TStockBill stockBill, String customerIds){
		if(customerIds==null || customerIds.trim().equals("")){
			customerIds = "(-1)";
		}
		else if(Constants.USER_FILTER_MARK.equals(customerIds)){
			customerIds = null;
		}
		stockBill.setCustomerIds(customerIds);
		Integer stockBillCount = stockBillDao.getStockBillCount(stockBill);
		return stockBillCount;
	}
	
	public List getStockBillAllList(String billIds){
		List stockBillList = stockBillDao.getStockBillAllList(("(" + billIds + ")")); 
		return stockBillList;
	}
	
	public TStockBillDAO getStockBillDao() {
		return stockBillDao;
	}

	public void setStockBillDao(TStockBillDAO stockBillDao) {
		this.stockBillDao = stockBillDao;
	}

}
