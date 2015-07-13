/**
 * 
 */
package com.sales.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.sales.dao.TK3CustomerDAO;
import com.sales.model.TCustomer;

/**
 * @author Administrator
 *
 */
public class TK3CustomerDAOImpl extends SqlMapClientDaoSupport implements TK3CustomerDAO {

	
	/**
	 * 
	 */
	public TK3CustomerDAOImpl() {
		super();
		
	}

	/* (non-Javadoc)
	 * @see com.sales.dao.TK3CustomerDAO#getSynCustomers()
	 */
	@Override
	public List<TCustomer> getSyncCustomers() {
		List<TCustomer> list =  (List<TCustomer>)getSqlMapClientTemplate()
				.queryForList("t_k3_entry.getSyncCustomers");
		return list;
	}

}
