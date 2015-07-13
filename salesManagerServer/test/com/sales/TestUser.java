/**
 * 	File Name: TestUser.java
 *	Product Name: SalesManager
 * 	Create on: 2007-10-22
 * 	@author:	Brian
 * 	Copyright(c) This file is @copyright by Kaiyang Corp.
 *  All rights reserved.
 *	This software is the confidential and proprietary information of SiChuan
 *  TianYi Net corp, Inc. ("Confidential Information").  You shall not
 *  disclose such Confidential Information and shall use it only in
 *  accordance with the terms of the license agreement you entered into
 *  with Tianyi.
 */
package com.sales;

import java.util.Iterator;
import java.util.List;

import com.sales.model.SUser;
import com.sales.model.User;
import com.sales.service.SUserService;
import com.sales.service.UserService;



/**
 * @author Administrator
 *	用户测试类
 */
public class TestUser extends BaseTestCase {
	private UserService manager;
	private SUserService userService;
	/**
	 * 
	 */
	public TestUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param name
	 */
	public TestUser(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		//manager = (UserService)ctx.getBean("userService");
		userService = (SUserService)ctx.getBean("sUserService");
		super.setUp();
	}

	
	public void estGetUserList() {
		List<User> list = manager.getUserList();
		for (Iterator<User> iter = list.iterator(); iter.hasNext();) {
			User user = iter.next();
			logger.info(user);
		}
	}
	
	/**
	 * 
	 */
	public void estAdd() {
		User user = new User();
		user.setName("user4");
		user.setName("User10");
		manager.addUser(user);
	}
	
	public void testK3ChildrenUser() {
		List<String> ls = userService.getK3EmpNamesByParentId(23);
		for (Iterator iterator = ls.iterator(); iterator.hasNext();) {
			String object = (String) iterator.next();
		}
	}

	public void testSelectParentUsers() {
		List<SUser> ls = userService.selectParentSaleUsers();
		for (Iterator iterator = ls.iterator(); iterator.hasNext();) {
			SUser u = (SUser)iterator.next();
			logger.warn(u);
		}
	}
}
