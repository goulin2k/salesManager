package com.sales.service.impl;
 
import java.util.HashMap;
import java.util.List; 
import java.util.Map;

import com.sales.common.Constants;
import com.sales.dao.TOutStockDAO;
import com.sales.model.TOutStock;
import com.sales.service.OutStockService;

public class OutStockServiceImpl implements OutStockService {
	
	private TOutStockDAO outStockDao;
	
	public TOutStock getOutStockById(Integer outStockId, String customerIds){
		if(customerIds==null || customerIds.trim().equals("")){
			customerIds = "(-1)";
		}
		else if(Constants.USER_FILTER_MARK.equals(customerIds)){
			customerIds = null;
		}
		Map queryMap = new HashMap();
		queryMap.put("outStockId", outStockId); 
		queryMap.put("customerIds", customerIds); 
		TOutStock stock = (TOutStock) outStockDao.getOutStockById(queryMap);
		return stock;
	}
	
	public List getOutStockList(Integer pageNumber, Integer pageSize, TOutStock outStock, String customerIds){  
		Integer startNumber = (pageNumber - 1) * pageSize;
		outStock.setStartNumber(startNumber);
		outStock.setPageSize(pageSize);
		if(customerIds==null || customerIds.trim().equals("")){
			customerIds = "(-1)";
		}
		else if(Constants.USER_FILTER_MARK.equals(customerIds)){
			customerIds = null;
		}
		outStock.setCustomerIds(customerIds);
		List outStockList = (List) outStockDao.getOutStockList(outStock);
		return outStockList;
	}
	
	public Integer getOutStockCount(TOutStock outStock, String customerIds){ 
		if(customerIds==null || customerIds.trim().equals("")){
			customerIds = "(-1)";
		}
		else if(Constants.USER_FILTER_MARK.equals(customerIds)){
			customerIds = null;
		}
		outStock.setCustomerIds(customerIds);
		Integer outStockCount = (Integer) outStockDao.getOutStockCount(outStock);
		return outStockCount;
	}
	
	public List getOutStockAllList(String billIds){
		List outStockList = outStockDao.getOutStockAllList("(" + billIds + ")");
		return outStockList;
	}

	public TOutStockDAO getOutStockDao() {
		return outStockDao;
	}

	public void setOutStockDao(TOutStockDAO outStockDao) {
		this.outStockDao = outStockDao;
	}

}
