/**
 * 
 */
package com.sales.workflow;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import junit.framework.AssertionFailedError;
import junit.framework.TestCase;

/**
 * @author Administrator
 *
 */
public class TestSimpleAssignActorHandler extends TestCase {
	private static Log logger = LogFactory.getLog(UserAssignHandler.class);
	
	
	public void testGetAssignUserName() {
		SimpleAssignActorHandler sa = new SimpleAssignActorHandler();
		String user = sa.getAssignUserName("goulin", 1, "${manager}");
		logger.info("Manager user: \t" + user);
		user = sa.getAssignUserName("wuming", 2, "${manager}");
		logger.info("Manager user: \t" + user);
		//assertEquals("like", user);
		user = sa.getAssignUserName("goulin", 1, "${ceo}");
		logger.info("CEO user: \t" + user);
		
		List<String> users = sa.getRoleUsers("goulin", 1, "${boss}");
		for (Iterator iterator = users.iterator(); iterator.hasNext();) {
			String usr = (String) iterator.next();
			logger.info("User in boss:" + usr);
		}
	}
}
