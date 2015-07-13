package com.sales.dao;

import java.util.List; 
import java.util.Map;

import com.sales.model.TPOOrder;

/**采购订单数据访问接口
 * 
 * @author Goulin
 *
 */
public interface TPOOrderDAO {
	
	/**
	 * 根据id获取采购订单实体对象
	 * @param queryMap
	 * @return
	 */
	public TPOOrder getOrderById(Map queryMap);
	
	/**
	 * 根据查询条件获取采购订单列表
	 * @param Order
	 * @return
	 */
	public List<TPOOrder> getOrderList(TPOOrder order);
	
	/**
	 * 根据查询条件获取采购订单记录数
	 * @param Order
	 * @return
	 */
	public Integer getOrderCount(TPOOrder order);
	
	/**
	 * 根据订单id数组获取采购订单列表
	 * @param billIds
	 * @return
	 */
	public List<TPOOrder> getOrderList(String billIds, String customerIds);

}
