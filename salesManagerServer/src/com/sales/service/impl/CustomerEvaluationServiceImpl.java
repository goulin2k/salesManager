package com.sales.service.impl;

import com.sales.service.CustomerEvaluationService;
import com.sales.dao.SCustomerEvaluationDAO;

/** 
 * @author  
 * @version 创建时间：2013-6-27 下午05:05:32 
 *  
 */
public class CustomerEvaluationServiceImpl implements CustomerEvaluationService{
	
	private SCustomerEvaluationDAO customerEvaluationDao;

	public SCustomerEvaluationDAO getCustomerEvaluationDao() {
		return customerEvaluationDao;
	}

	public void setCustomerEvaluationDao(SCustomerEvaluationDAO customerEvaluationDao) {
		this.customerEvaluationDao = customerEvaluationDao;
	}

}
