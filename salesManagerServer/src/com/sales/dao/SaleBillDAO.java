package com.sales.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.sales.model.OrfqSale;
import com.sales.model.SaleBillStatus;

public interface SaleBillDAO {
	
	public List getSaleBillStatusByOrderId(Integer orderId);
    
    public SaleBillStatus getSaleBillStatusByBillId(Map queryMap);
    
    public List getSaleBillListByBillId(Map queryMap);
    
    public List getSaleBillListNotRead();
	
	public void updateSaleBillRead(String billStatusIds);
	
	public List getSaleCheck(String checkDate);
	
	public List getSaleDelete(String deleteDate);
	
	public Date getOrfqDealDate(String billType);
	
	public void updateOrfqDealDate(Map orfqMap);
	
	/**
	 * 执行K3单据数据计算
	 */
	public void synK3BillStatus();

}
