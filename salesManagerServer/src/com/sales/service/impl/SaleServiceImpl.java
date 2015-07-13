package com.sales.service.impl;
 
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sales.common.Constants;
import com.sales.dao.TSaleDAO;
import com.sales.model.TReceiveBillResult;
import com.sales.model.TSale;
import com.sales.model.TSaleBill;
import com.sales.service.SaleService;

public class SaleServiceImpl implements SaleService {
	
	private TSaleDAO saleDao; 
	
	public TSale getSaleById(Integer saleId, String customerIds){
		if(customerIds==null || customerIds.trim().equals("")){
			customerIds = "(-1)";
		}
		else if(Constants.USER_FILTER_MARK.equals(customerIds)){
			customerIds = null;
		}
		Map queryMap = new HashMap();
		queryMap.put("saleId", saleId); 
		queryMap.put("customerIds", customerIds); 
		TSale sale = (TSale) saleDao.getSaleById(queryMap);
		return sale;
	}
	
	public List getSaleList(Integer pageNumber, Integer pageSize, TSale sale, String customerIds){ 
		Integer startNumber = (pageNumber - 1) * pageSize;
		sale.setStartNumber(startNumber);
		sale.setPageSize(pageSize);
		if(customerIds==null || customerIds.trim().equals("")){
			customerIds = "(-1)";
		}
		else if(Constants.USER_FILTER_MARK.equals(customerIds)){
			customerIds = null;
		}
		sale.setCustomerIds(customerIds);
		List saleList = (List) saleDao.getSaleList(sale);
		return saleList;
	}
	
	public Integer getSaleCount(TSale sale, String customerIds){
		if(customerIds==null || customerIds.trim().equals("")){
			customerIds = "(-1)";
		}
		else if(Constants.USER_FILTER_MARK.equals(customerIds)){
			customerIds = null;
		}
		sale.setCustomerIds(customerIds);
		Integer saleCount = saleDao.getSaleCount(sale);
		return saleCount;
	}
	
	public List getSaleAllList(String billIds){
		List saleList = saleDao.getSaleAllList(("(" + billIds + ")")); 
		return saleList;
	}
	
	public List getSaleBillList(Integer customerId){
		List saleList = saleDao.getSaleByCustomerId(customerId);
		for (int i = 0; i < saleList.size(); i++) {
			TSaleBill sale = (TSaleBill) saleList.get(i);
			List receiveBillList = sale.getReceiveBillList();
			if(receiveBillList != null){
				for (int j = 0; j < receiveBillList.size(); j++) {
					TReceiveBillResult receive = (TReceiveBillResult) receiveBillList.get(j);
					if(sale.getReceiveAmount() == null){
						sale.setReceiveAmount(0.0);
					}
					if(receive.getReceiveAmount() != null){
						sale.setReceiveAmount(sale.getReceiveAmount() + receive.getReceiveAmount());
					}
					if(j == receiveBillList.size()-1){
						sale.setReceiveDate(receive.getFDate());
					}
				}
			}
		}
		return saleList;
	}

	public TSaleDAO getSaleDao() {
		return saleDao;
	}

	public void setSaleDao(TSaleDAO saleDao) {
		this.saleDao = saleDao;
	} 

}
