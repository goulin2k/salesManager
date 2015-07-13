/**
 * 
 */
package com.sales.service.impl;

import java.util.List;
import java.util.Map;

import com.sales.common.Constants;
import com.sales.dao.TPOOrderDAO;
import com.sales.model.TPOOrder;
import com.sales.service.POOrderService;

/**
 * 采购订单业务实现类
 * @author Administrator
 *
 */
public class POOrderServiceImpl implements POOrderService {
	private TPOOrderDAO orderDAO;
	
	

	/**
	 * @param orderDAO the orderDAO to set
	 */
	public void setOrderDAO(TPOOrderDAO orderDAO) {
		this.orderDAO = orderDAO;
	}

	/* (non-Javadoc)
	 * @see com.sales.service.POOrderService#getOrderById(java.lang.Integer, java.lang.String)
	 */
	@Override
	public TPOOrder getOrderById(Integer orderId, String customerIds) {
		Map queryMap = new java.util.HashMap();
		if(customerIds==null || customerIds.trim().equals("")){
			customerIds = "(-1)";
		}
		else if(Constants.USER_FILTER_MARK.equals(customerIds)){
			customerIds = null;
		}
		queryMap.put("orderId", orderId); 
		queryMap.put("customerIds", customerIds); 
		TPOOrder order = orderDAO.getOrderById(queryMap);
		return order;
	}

	/* (non-Javadoc)
	 * @see com.sales.service.POOrderService#getOrderList(java.lang.Integer, java.lang.Integer, com.sales.model.TPOOrder, java.lang.String)
	 */
	@Override
	public List<TPOOrder> getOrderList(Integer pageNumber, Integer pageSize,
			TPOOrder order, String customerIds) {
		Integer startNumber = (pageNumber - 1) * pageSize;
		order.setStartNumber(startNumber);
		order.setPageSize(pageSize);
		if(customerIds==null || customerIds.trim().equals("")){
			customerIds = "(-1)";
		}
		else if(Constants.USER_FILTER_MARK.equals(customerIds)){
			customerIds = null;
		}
		order.setCustomerIds(customerIds);
		List orderList = orderDAO.getOrderList(order);
		return orderList;
	}

	/* (non-Javadoc)
	 * @see com.sales.service.POOrderService#getOrderCount(com.sales.model.TPOOrder, java.lang.String)
	 */
	@Override
	public Integer getOrderCount(TPOOrder order, String customerIds) {
		if(customerIds==null || customerIds.trim().equals("")){
			customerIds = "(-1)";
		}
		else if(Constants.USER_FILTER_MARK.equals(customerIds)){
			customerIds = null;
		}
		order.setCustomerIds(customerIds);
		Integer count = orderDAO.getOrderCount(order);
		return count;
	}

	/* (non-Javadoc)
	 * @see com.sales.service.POOrderService#getOrderList(java.lang.String, java.lang.String)
	 */
	@Override
	public List<TPOOrder> getOrderList(String orderIds, String customerIds) {
		List<TPOOrder> list = orderDAO.getOrderList(orderIds, customerIds);
		return list;
	}
	
	

}
