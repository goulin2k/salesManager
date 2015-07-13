package com.sales.service;

import java.util.List;

import com.sales.model.SaleBillStatus;

/**
 * 销售单据关联
 * @author apple
 *
 */
public interface SaleBillService {
	
	public List getSaleBillStatusByOrderId(Integer orderId);
    
    public SaleBillStatus getSaleBillStatusByBillId(Integer relationType, Integer billId);
    
    public SaleBillStatus getSaleBillListByBillId(Integer relationType, Integer billId);
    
    /**
     * ִ��K3������ݼ���
     */
    public void synK3BillStatus();

}
