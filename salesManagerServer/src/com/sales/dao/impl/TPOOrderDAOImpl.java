/**
 * 
 */
package com.sales.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.sales.dao.TPOOrderDAO;
import com.sales.model.TPOOrder;

/**
 * 采购订单数据访问实现类
 * @author Goulin
 *
 */
public class TPOOrderDAOImpl extends SqlMapClientDaoSupport implements TPOOrderDAO {

	/* (non-Javadoc)
	 * @see com.sales.dao.TPOOrderDAO#getOrderById(java.util.Map)
	 */
	@Override
	public TPOOrder getOrderById(Map queryMap) {
		TPOOrder order = (TPOOrder)getSqlMapClientTemplate()
				.queryForObject("t_poOrder.getOrderById", queryMap);
		return order;
	}

	/* (non-Javadoc)
	 * @see com.sales.dao.TPOOrderDAO#getOrderList(com.sales.model.TPOOrder)
	 */
	@Override
	public List<TPOOrder> getOrderList(TPOOrder order) {
		List<TPOOrder> list = getSqlMapClientTemplate()
				.queryForList("t_poOrder.getOrderList", order);
		return list;
	}

	/* (non-Javadoc)
	 * @see com.sales.dao.TPOOrderDAO#getOrderCount(com.sales.model.TPOOrder)
	 */
	@Override
	public Integer getOrderCount(TPOOrder order) {
		Integer count = (Integer)getSqlMapClientTemplate()
				.queryForObject("t_poOrder.getOrdersCount", order);
		
		return count;
	}

	/* (non-Javadoc)
	 * @see com.sales.dao.TPOOrderDAO#getOrderList(java.lang.String)
	 */
	@Override
	public List<TPOOrder> getOrderList(String billIds, String customerIds) {
		Map<String, String> queryMap = new HashMap<String, String>();
		queryMap.put("billIds", billIds);
		queryMap.put("customerIds", customerIds);
		List<TPOOrder> list = getSqlMapClientTemplate()
				.queryForList("t_poOrder.getOrderListByIds", queryMap);
		return list;
	}

}
