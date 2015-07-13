/**
 * 
 */
package com.sales;

import java.text.ParseException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.sales.common.NormalFun;
import com.sales.model.K3RecieveBillStatistics;
import com.sales.service.K3RecieveBillStatisticsService;

/**
 * @author Administrator
 *
 */
public class TestRecieveBillReport extends com.sales.BaseTestCase {

	private K3RecieveBillStatisticsService service;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		service = (K3RecieveBillStatisticsService) ctx.getBean("k3RecieveBillStatisticsService");
		super.setUp();
	}
	
	public void testBetweenDate() throws ParseException {
		int userId = 25;
		Date beginDate = NormalFun.toDate("2013-01-02");
		Date endDate = NormalFun.toDate("2013-12-31");
		double result = 0.0;
		
		result = service.statBetweenDate(beginDate, endDate, userId);
		logger.info("销售业绩统计结果：\t" + NormalFun.formatCurrency(result));
	}
	
	public void testStatThisMonth() {
		int userId = 25;
		double result = 0.0;
		
		result = service.statThisMonth(userId);
		logger.info("当月业务人员销售业绩统计结果：\t" + NormalFun.formatCurrency(result));
	}
	
	public void testStatMonth() {
		int userId = 25;
		int year = 2013;
		int month = 2;
		double result = 0.0;
		
		result = service.statMonth(year, month, userId);
		logger.info("指定月份的业务人员销售业绩统计结果：\t" + NormalFun.formatCurrency(result));
	}
	
	public void testStatYear() {
		int userId = 25;
		int year = 2013;
		double result = 0.0;
		
		result = service.statYear(year, userId);
		logger.info("指定年份的业务人员销售业绩统计结果：\t" + NormalFun.formatCurrency(result));
	}
	
	public void testStatByDay() throws ParseException{
		int userId = 25;
		Date beginDate = NormalFun.toDate("2013-01-02");
		Date endDate = NormalFun.toDate("2013-01-31");
		
		List<K3RecieveBillStatistics> result = service.statByDay(beginDate, endDate, userId);
		logger.info("指定日期区间的业务人员销售业绩日统计：\n------------------------------------------");
		for (Iterator iterator = result.iterator(); iterator.hasNext();) {
			K3RecieveBillStatistics k3OrderStatistics = (K3RecieveBillStatistics) iterator
					.next();
			logger.info(k3OrderStatistics);
		}
		logger.info("\n------------------------------------------");
	}
	
	public void testStatByWeek() throws ParseException{
		int userId = 25;
		Date beginDate = NormalFun.toDate("2013-01-02");
		Date endDate = NormalFun.toDate("2013-02-31");
		
		List<K3RecieveBillStatistics> result = service.statByWeek(beginDate, endDate, userId);
		logger.info("指定日期区间的业务人员销售业绩周统计：\n------------------------------------------");
		for (Iterator iterator = result.iterator(); iterator.hasNext();) {
			K3RecieveBillStatistics k3OrderStatistics = (K3RecieveBillStatistics) iterator
					.next();
			logger.info(k3OrderStatistics);
		}
		logger.info("\n------------------------------------------");
	}
	
	public void testStatByMonth() throws ParseException{
		int userId = 25;
		Date beginDate = NormalFun.toDate("2013-01-01");
		Date endDate = NormalFun.toDate("2013-07-01");
		
		List<K3RecieveBillStatistics> result = service.statByMonth(beginDate, endDate, userId);
		logger.info("指定日期区间的业务人员销售业绩月统计：\n------------------------------------------");
		for (Iterator iterator = result.iterator(); iterator.hasNext();) {
			K3RecieveBillStatistics k3OrderStatistics = (K3RecieveBillStatistics) iterator
					.next();
			logger.info(k3OrderStatistics);
		}
		logger.info("\n------------------------------------------");
	}
}
