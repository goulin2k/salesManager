package com.sales.service;
 
import java.util.List; 

import com.sales.model.TPOOrder;
import com.sales.model.TRequest;

/**
 * 采购订单业务类
 * @author Goulin
 *
 */
public interface POOrderService {
	
	/**
	 * 根据id获取采购订单
	 * @param requestId
	 * @param customerIds
	 * @return
	 */
	public TPOOrder getOrderById(Integer orderId, String customerIds);
	
	/**
	 * 根据查询条件获取采购订单列表
	 * @param pageNumber
	 * @param pageSize
	 * @param request
	 * @param customerIds
	 * @return
	 */
	public List<TPOOrder> getOrderList(Integer pageNumber, Integer pageSize, TPOOrder order, String customerIds);
	
	/**
	 * 根据查询条件获取采购订单记录数
	 * @param request
	 * @param customerIds
	 * @return
	 */
	public Integer getOrderCount(TPOOrder order, String customerIds);
	
	/**
	 * 根据订单id获取采购订单列表
	 * @param orderIds
	 * @param customerIds
	 * @return
	 */
	public List<TPOOrder> getOrderList(String orderIds, String customerIds);

}
