/**
 * 
 */
package com.sales;

import java.util.Iterator;
import java.util.List;

import com.sales.model.TPOOrder;
import com.sales.service.K3OrderStatisticsService;
import com.sales.service.POOrderService;

/**
 * @author Administrator
 *
 */
public class TestPoOrder extends BaseTestCase {
	private POOrderService service;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		service = (POOrderService) ctx.getBean("poOrderService");
		super.setUp();
	}
	
	public void testGetById() {
		TPOOrder order = service.getOrderById(1149, "-1");
		logger.info(order.toString());
	}
	
	public void testGetList() {
		TPOOrder order = new TPOOrder();
		List<TPOOrder> list = service.getOrderList(1, 10, order, "-1");
		int count = service.getOrderCount(order, "-1");
		logger.info("*****************Count:\t" + count + "******************");
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			TPOOrder tpoOrder = (TPOOrder) iterator.next();
			logger.info(tpoOrder.toString());
		}
	}

}
