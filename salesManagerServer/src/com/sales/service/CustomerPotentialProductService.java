/**
 * 
 */
package com.sales.service;

import java.util.List;

import com.sales.model.SCustomerPProduct;

/**
 * 客户潜力产品服务接口
 * @author apple
 *
 */
public interface CustomerPotentialProductService {

	/**
	 * 根据客户id获取当前潜力产品信息
	 * @param customerId
	 * @return
	 */
	public SCustomerPProduct getPProduct(int customerId);
	
	/**
	 * 根据客户id获取客户潜力产品历史信息
	 * @param customerId
	 * @return
	 */
	public List<SCustomerPProduct> getPProductHistory(int customerId);
	
	/**
	 * 保存当前客户潜力产品信息
	 * @param productInfo
	 */
	public void save(SCustomerPProduct productInfo);
}
