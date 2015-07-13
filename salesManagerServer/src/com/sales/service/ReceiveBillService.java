package com.sales.service;

import java.util.List;

import com.sales.model.TReceiveBill;

/**
 * 销售收款单
 * @author apple
 *
 */
public interface ReceiveBillService {
	
	public TReceiveBill getReceiveBillById(Integer receiveBillId, String customerIds);
	
	public List getReceiveBillList(Integer pageNumber, Integer pageSize, TReceiveBill receiveBill, String customerIds);
	
	public Integer getReceiveBillCount(TReceiveBill receiveBill, String customerIds);
	
	public List getReceiveBillAllList(String billIds);

}
