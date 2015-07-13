package com.sales.service.impl;
  
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sales.common.Constants;
import com.sales.dao.TStockBillOutDAO;
import com.sales.model.TStockBillOut;
import com.sales.service.StockBillOutService;

public class StockBillOutServiceImpl implements StockBillOutService {
	
	private TStockBillOutDAO stockBillOutDao;

	public TStockBillOut getStockBillOutById(Integer stockBillOutId, String customerIds){
		if(customerIds==null || customerIds.trim().equals("")){
			customerIds = "(-1)";
		}
		else if(Constants.USER_FILTER_MARK.equals(customerIds)){
			customerIds = null;
		}
		Map queryMap = new HashMap();
		queryMap.put("stockBillOutId", stockBillOutId); 
		queryMap.put("customerIds", customerIds); 
		TStockBillOut stockBillOut = stockBillOutDao.getStockBillOutById(queryMap);
		return stockBillOut;
	}
	
	public List getStockBillOutList(Integer pageNumber, Integer pageSize, TStockBillOut stockBillOut, String customerIds){ 
		Integer startNumber = (pageNumber - 1) * pageSize;
		stockBillOut.setStartNumber(startNumber);
		stockBillOut.setPageSize(pageSize);
		if(customerIds==null || customerIds.trim().equals("")){
			customerIds = "(-1)";
		}
		else if(Constants.USER_FILTER_MARK.equals(customerIds)){
			customerIds = null;
		}
		stockBillOut.setCustomerIds(customerIds);
		List stockBillOutList = (List) stockBillOutDao.getStockBillOutList(stockBillOut);
		return stockBillOutList;
	}
	
	public Integer getStockBillOutCount(TStockBillOut stockBillOut, String customerIds){
		if(customerIds==null || customerIds.trim().equals("")){
			customerIds = "(-1)";
		}
		else if(Constants.USER_FILTER_MARK.equals(customerIds)){
			customerIds = null;
		}
		stockBillOut.setCustomerIds(customerIds);
		Integer stockBillOutCount = stockBillOutDao.getStockBillOutCount(stockBillOut);
		return stockBillOutCount;
	}
	
	public List getStockBillOutAllList(String billIds){
		List stockBillOutList = stockBillOutDao.getStockBillOutAllList(("(" + billIds + ")")); 
		return stockBillOutList;
	}

	public TStockBillOutDAO getStockBillOutDao() {
		return stockBillOutDao;
	}

	public void setStockBillOutDao(TStockBillOutDAO stockBillOutDao) {
		this.stockBillOutDao = stockBillOutDao;
	} 
	
}
