package com.sales.service;
 
import java.util.List; 

import com.sales.model.K3OrderCustomerStatistics;
import com.sales.model.TOrder;

public interface OrderService {
	public static final int STATISTICS_TOP = 5;		//统计前几名
	public static final int STATISTICS_MONTHS = 24;		//统计前几个月
	
	public TOrder getOrderById(Integer orderId, String customerIds);
	
	public List getOrderList(Integer pageNumber, Integer pageSize, TOrder order, String customerIds);
	
	public Integer getOrderCount(TOrder order, String customerIds);
	
	public List getOrderAllList(String orderIds);
	
	public List getOrderByCustomerId(Integer customerId);
	
	/**
	 * 获取指定客户近两年订购产品的统计信息
	 * @param customerId
	 * @return
	 */
	public List<K3OrderCustomerStatistics> getOrderProductStatisticsList(Integer customerId);

}
