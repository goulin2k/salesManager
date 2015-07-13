/**
 * 
 */
package com.sales.dao;

import java.util.List;

import com.sales.model.TCustomer;

/**
 * @author Administrator
 *
 */
public interface TK3CustomerDAO {

	/**
	 * 获取K3发生变化的客户数据
	 * @return
	 */
	public List<TCustomer> getSyncCustomers();
}
