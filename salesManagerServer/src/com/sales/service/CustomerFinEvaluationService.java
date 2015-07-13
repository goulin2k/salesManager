package com.sales.service;

import com.sales.model.SCustomerFinEvaluation;

/** 
 * @author  
 * @version 创建时间：2013-6-27 下午09:38:18 
 *  
 */
public interface CustomerFinEvaluationService {
	
	public void updateCustomerFinEvaluation(SCustomerFinEvaluation customerFinEvaluation);
	
	public SCustomerFinEvaluation getCustomerFinEvaluationByCustomerId(Integer customerId);
	
	public void deleteCustomerFinEvaluationByCustomerId(Integer customerId);

}
