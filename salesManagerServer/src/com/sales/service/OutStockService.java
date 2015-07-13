package com.sales.service;

import java.util.List;

import com.sales.model.TOutStock;

/**
 * 销售发货通知
 * @author apple
 *
 */
public interface OutStockService {
	
	public TOutStock getOutStockById(Integer outStockId, String customerIds);
	
	public List getOutStockList(Integer pageNumber, Integer pageSize, TOutStock outStock, String customerIds);
	
	public Integer getOutStockCount(TOutStock outStock, String customerIds);
	
	public List getOutStockAllList(String billIds);

}
