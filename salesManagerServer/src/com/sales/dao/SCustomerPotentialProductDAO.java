/**
 * 
 */
package com.sales.dao;

import java.util.List;

import com.sales.model.SCustomerPProduct;

/**
 * @author apple
 *
 */
public interface SCustomerPotentialProductDAO {
	
	/**
	 * 查询制定客户的当前潜力产品信息
	 * @param customer
	 * @return
	 */
	public SCustomerPProduct queryCustomerPProduct(int customerId);
	
	/**
	 * 查询指定客户的历史潜力产品信息
	 * @param customer
	 * @return
	 */
	public List<SCustomerPProduct> queryCustomerHistoryPProduct(int customerId);
	
	/**
	 * 新增客户潜力产品信息记录
	 * @param productInfo
	 */
	public void insert(SCustomerPProduct productInfo);
	
	/**
	 * 作废原来的潜力产品信息记录
	 * @param potentialId
	 */
	public void invalide(int potentialId);

}
