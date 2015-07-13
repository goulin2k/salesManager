package com.sales.dao;

import java.util.List; 
import java.util.Map;

import com.sales.model.K3OrderCustomerStatistics;
import com.sales.model.TOrder;

public interface TOrderDAO {
	
	public TOrder getOrderById(Map queryMap);
	
	public List getOrderList(TOrder order);
	
	public Integer getOrderCount(TOrder order);
	
	public List getOrderAllList(String orderIds);
	
	public List getOrderByCustomerId(Integer customerId);
	
	/**
	 * 查询统计指定客户订单的产品信息
	 * @param customerId	指定的客户id
	 * @param top		统计前几名
	 * @param months	统计前几个月
	 * @return
	 */
	public List<K3OrderCustomerStatistics> getOrderProductStatisticsList(
			Integer customerId, Integer top, Integer months);


}
