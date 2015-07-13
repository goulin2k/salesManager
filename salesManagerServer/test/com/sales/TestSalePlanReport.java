/**
 * 
 */
package com.sales;

import java.text.ParseException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.sales.common.NormalFun;
import com.sales.model.SalePlanStatistics;
import com.sales.model.SalePlanWithActivityVO;
import com.sales.service.K3RecieveBillStatisticsService;
import com.sales.service.ProjectService;

/**
 * @author Administrator
 *
 */
public class TestSalePlanReport extends BaseTestCase {
	private ProjectService service;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		service = (ProjectService) ctx.getBean("projectService");
		super.setUp();
	}
	
	public void testReport() throws ParseException {
		Date startTime = NormalFun.toDate("2014-02-01");
		Date endTime = NormalFun.toDate("2014-05-30");
		List<SalePlanStatistics> list = service.statisPlanReport("(22,31,36,52)", 
				startTime, endTime);
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			SalePlanStatistics salePlanStatistics = (SalePlanStatistics) iterator
					.next();
			logger.info(salePlanStatistics);
		}
	}
	
	public void testPersonReport() throws ParseException {
		Date startTime = NormalFun.toDate("2014-02-01");
		Date endTime = NormalFun.toDate("2014-05-30");
		List<SalePlanWithActivityVO> list = service.statisPersonPlanReport(
				36, startTime, endTime);
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			SalePlanWithActivityVO salePlanWithActivityVO = (SalePlanWithActivityVO) iterator
					.next();
			logger.info(salePlanWithActivityVO);
		}
	}
}
