/**
 * 
 */
package com.sales.service.impl;

import java.util.List;

import com.sales.dao.SCustomerEvaluationDAO;
import com.sales.dao.SCustomerPotentialProductDAO;
import com.sales.model.SCustomerPProduct;
import com.sales.service.CustomerPotentialProductService;

/**
 * 客户潜力产品服务实现类
 * @author apple
 *
 */
public class CustomerPotentialProductServiceImpl implements CustomerPotentialProductService {
	
	private SCustomerPotentialProductDAO customerPotentialProductDao;
	

	/**
	 * @param customerPotentialProductDao the customerPotentialProductDao to set
	 */
	public void setCustomerPotentialProductDao(
			SCustomerPotentialProductDAO customerPotentialProductDao) {
		this.customerPotentialProductDao = customerPotentialProductDao;
	}

	/* (non-Javadoc)
	 * @see com.sales.service.CustomerPotentialProduct#getPProduct(int)
	 */
	@Override
	public SCustomerPProduct getPProduct(int customerId) {
		return customerPotentialProductDao.queryCustomerPProduct(customerId);
	}

	/* (non-Javadoc)
	 * @see com.sales.service.CustomerPotentialProduct#getPProductHistory(int)
	 */
	@Override
	public List<SCustomerPProduct> getPProductHistory(int customerId) {
		
		return customerPotentialProductDao.queryCustomerHistoryPProduct(customerId);
	}

	/* (non-Javadoc)
	 * @see com.sales.service.CustomerPotentialProduct#save(com.sales.model.SCustomerPProduct)
	 */
	@Override
	public void save(SCustomerPProduct productInfo) {
		//如果有历史潜力产品，先进行无效操作，再插入新潜力产品记录
		if(productInfo.getPotentialProductId() != null)
			customerPotentialProductDao.invalide(productInfo.getPotentialProductId());
		customerPotentialProductDao.insert(productInfo);

	}

}
