package com.sales.calenda;


import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.sales.BaseTestCase;
import com.sales.model.SSalesProject;
import com.sales.service.CustomerSyncService;
import com.sales.service.ProjectService;

/**
 * @author Administrator
 * 
 */
public class TestCustomerSync extends com.sales.BaseTestCase {
	private CustomerSyncService service;

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		service = (CustomerSyncService) ctx.getBean("customerSyncService");
		super.setUp();
	}

	/**
	 * 
	 */
	public void testSync() {
		//浠嶬3鍚屾瀹㈡埛鏁版嵁鍒癱rm绯荤粺
		int i = service.syncK3Customers();
		logger.info("K3 Custormer sync [" + i + "]");
	}
	
	public void testRefeshSaleProc() {
		//鍒锋柊K3閿�敭杩囩▼缁熻鏁版嵁
		service.syncK3ProcessData();
	}

	
	public void testSelectUserWithParent() {
		//鍒锋柊K3閿�敭杩囩▼缁熻鏁版嵁
		Map<String, List<String>> map = service.getUsersWithParent();
		for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();) {
			String userId = (String) iterator.next();
			String childId = "[" + userId.toString() + "]:\t{";
			List children = map.get(userId);
			for (Iterator iterator2 = children.iterator(); iterator2.hasNext();) {
				String child = (String) iterator2.next();
				childId += child.toString();
				if(iterator2.hasNext())
					childId += ",";
			}
			childId += "}";
			logger.warn(childId);
		}
		
	}
}
