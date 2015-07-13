package com.sales.dao.impl;

import java.util.HashMap;
import java.util.List; 
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.sales.dao.TOrderDAO;
import com.sales.model.K3OrderCustomerStatistics;
import com.sales.model.TOrder;

public class TOrderDAOImpl extends SqlMapClientDaoSupport implements TOrderDAO {
	
	public TOrder getOrderById(Map queryMap){
		TOrder order = (TOrder) getSqlMapClientTemplate().queryForObject("t_order.getOrderById", queryMap);
		return order;
	}
	
	public List getOrderList(TOrder order){
		List orderList = (List) getSqlMapClientTemplate().queryForList("t_order.getOrderList", order);
		return orderList;
	}
	
	public Integer getOrderCount(TOrder order){
		Integer orderCount = (Integer) getSqlMapClientTemplate().queryForObject("t_order.getOrderCount", order);
		return orderCount;
	}
	
	public List getOrderAllList(String orderIds){
		List orderList = (List) getSqlMapClientTemplate().queryForList("t_order.getOrderAllList", orderIds);
		return orderList;
	}
	
	public List getOrderByCustomerId(Integer customerId){
		List orderList = (List) getSqlMapClientTemplate().queryForList("t_order.getOrderByCustomerId", customerId);
		return orderList;
	}

	/* (non-Javadoc)
	 * @see com.sales.dao.SCustomerOwnerUserDAO#getOrderProductStatisticsList(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<K3OrderCustomerStatistics> getOrderProductStatisticsList(
			Integer customerId, Integer top, Integer months) {
		Map queryMap = new HashMap<String, Object>();
		queryMap.put("customerId", customerId);
		queryMap.put("top", top);
		queryMap.put("months", months * -1);
		List<K3OrderCustomerStatistics> list = getSqlMapClientTemplate()
				.queryForList("t_order.getTopProductByCustomer", queryMap);
		return list;
	}

}
