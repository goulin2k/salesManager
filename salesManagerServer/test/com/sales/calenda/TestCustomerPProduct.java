package com.sales.calenda;

import com.sales.model.SCustomerPProduct;
import com.sales.service.CustomerPotentialProductService;
import com.sales.service.CustomerSyncService;

/**
 * 
 */

/**
 * @author apple
 *
 */
public class TestCustomerPProduct extends com.sales.BaseTestCase {
	
	private CustomerPotentialProductService service;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		service = (CustomerPotentialProductService) ctx.getBean("customerPotentialProductService");
		super.setUp();
	}
	
	public void testSave() {
		SCustomerPProduct pproduct = new SCustomerPProduct();
		pproduct.setCustomerId(12);
		pproduct.setCustomerName("测试公司");
		pproduct.setUserName("gouin");
		pproduct.setProductDescription("3M饮水机");
		
		service.save(pproduct);
	}

}
