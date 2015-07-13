/**
 * 
 */
package com.sales.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.sales.dao.SCustomerPotentialProductDAO;
import com.sales.model.SCustomerPProduct;

/**
 * @author apple
 *
 */
public class SCustomerPotentialProductDAOImpl extends SqlMapClientDaoSupport implements
		SCustomerPotentialProductDAO {

	/* (non-Javadoc)
	 * @see com.sales.dao.SCustomerPotentialProductDAO#queryCustomerPProduct(int)
	 */
	@Override
	public SCustomerPProduct queryCustomerPProduct(int customerId) {
		return (SCustomerPProduct)getSqlMapClientTemplate().queryForObject(
				"s_customer_pproduct.selectById",customerId);
	}

	/* (non-Javadoc)
	 * @see com.sales.dao.SCustomerPotentialProductDAO#queryCustomerHistoryPProduct(int)
	 */
	@Override
	public List<SCustomerPProduct> queryCustomerHistoryPProduct(int customerId) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList(
				"s_customer_pproduct.selectHostryByCustomerId", customerId);
	}

	/* (non-Javadoc)
	 * @see com.sales.dao.SCustomerPotentialProductDAO#insert(com.sales.model.SCustomerPProduct)
	 */
	@Override
	public void insert(SCustomerPProduct productInfo) {
		getSqlMapClientTemplate().insert("s_customer_pproduct.insert", productInfo);

	}

	/* (non-Javadoc)
	 * @see com.sales.dao.SCustomerPotentialProductDAO#invalide(int)
	 */
	@Override
	public void invalide(int potentialId) {
		getSqlMapClientTemplate().update("s_customer_pproduct.updateInValid", potentialId);

	}

}
