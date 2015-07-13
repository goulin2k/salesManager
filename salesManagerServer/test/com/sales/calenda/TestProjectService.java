/**
 * 
 */
package com.sales.calenda;

import java.util.Iterator;
import java.util.List;

import com.sales.BaseTestCase;
import com.sales.model.SSalesProject;
import com.sales.service.ProjectService;

/**
 * @author Administrator
 *
 */
public class TestProjectService extends BaseTestCase {

	private ProjectService service;
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		service = (ProjectService)ctx.getBean("projectService");
		super.setUp();
	}

	
	/**
	 * 
	 */
	public void testGetUserList() {
		List<SSalesProject> list = service.getProjectList(2, 
				"2013-04-01", "2013-06-17", 
				false);
		for (Iterator<SSalesProject> iter = list.iterator(); iter.hasNext();) {
			SSalesProject proj = iter.next();
			logger.info(proj);
		}
	}
	
	public void testGetList() {
		
	}
}
