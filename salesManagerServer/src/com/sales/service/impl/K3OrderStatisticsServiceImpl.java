/**
 * 
 */
package com.sales.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.sales.common.NormalFun;
import com.sales.common.WorkCalendar;
import com.sales.dao.K3OrderStatisticsDAO;
import com.sales.model.K3OrderStatistics;
import com.sales.service.K3OrderStatisticsService;
import com.sales.service.SUserService;

/**
 * @author goulin
 *
 */
public class K3OrderStatisticsServiceImpl implements K3OrderStatisticsService {
	private K3OrderStatisticsDAO statisticsDAO;
	private SUserService userService;
	
	/**
	 * @param userService the userService to set
	 */
	public void setUserService(SUserService userService) {
		this.userService = userService;
	}

	/**
	 * @param statisticsDAO the statisticsDAO to set
	 */
	public void setStatisticsDAO(K3OrderStatisticsDAO statisticsDAO) {
		this.statisticsDAO = statisticsDAO;
	}

	/* (non-Javadoc)
	 * @see com.sales.service.K3OrderStatisticsService#statThisMonth(int)
	 */
	@Override
	public double statThisMonth(int userId) {
		Calendar calendar = Calendar.getInstance();
		
		calendar.setTime(new Date());
		int yearBegin = calendar.get(Calendar.YEAR);
		int monthBegin = calendar.get(Calendar.MONTH) + 1;
		int dayBegin = 1;
		
		int yearEnd = calendar.get(Calendar.YEAR);
		int monthEnd = calendar.get(Calendar.MONTH) + 1;
		int dayEnd = calendar.getMaximum(Calendar.DAY_OF_MONTH);
		
		String empNames = getK3EmpNames(userId);
		
		return statisticsDAO.statBetweenDate(yearBegin, monthBegin, dayBegin, yearEnd, 
				monthEnd, dayEnd, empNames);
	}

	/* (non-Javadoc)
	 * @see com.sales.service.K3OrderStatisticsService#statMonth(int, int, int)
	 */
	@Override
	public double statMonth(int year, int month, int userId) {
		Calendar calendar = Calendar.getInstance();
		int dayBegin = 1;
		int dayEnd = calendar.getMaximum(Calendar.DAY_OF_MONTH);
		
		String empNames = getK3EmpNames(userId);
		
		return statisticsDAO.statBetweenDate(year, month, dayBegin, year, 
				month, dayEnd, empNames);
	}
	
	

	/* (non-Javadoc)
	 * @see com.sales.service.K3OrderStatisticsService#statQuarter(int, int, int)
	 */
	@Override
	public double statQuarter(int year, int quarter, int userId) {
		Date begin = WorkCalendar.getFirstDayOfQuarter(year, quarter);
		Date end = WorkCalendar.getLastDayOfQuarter(year, quarter);
		
		
		return statBetweenDate(begin, end, userId);
	}

	/* (non-Javadoc)
	 * @see com.sales.service.K3OrderStatisticsService#statMonth(int, int)
	 */
	@Override
	public double statYear(int year, int userId) {
		Calendar calendar = Calendar.getInstance();
		int monthBegin = 1;
		int monthEnd = calendar.getMaximum(Calendar.MONTH)+1;		//12
		int dayBegin = 1;
		int dayEnd = calendar.getMaximum(Calendar.DAY_OF_MONTH);	//31
		
		String empNames = getK3EmpNames(userId);
		return statisticsDAO.statBetweenDate(year, monthBegin, dayBegin, year, 
				monthEnd, dayEnd, empNames);
	}

	/* (non-Javadoc)
	 * @see com.sales.service.K3OrderStatisticsService#statBetween(java.util.Date, java.util.Date, int)
	 */
	@Override
	public double statBetweenDate(Date beginDate, Date endDate, int userId) {
		Calendar calendar = Calendar.getInstance();
		
		calendar.setTime(beginDate);
		int yearBegin = calendar.get(Calendar.YEAR);
		int monthBegin = calendar.get(Calendar.MONTH) + 1;
		int dayBegin = calendar.get(Calendar.DAY_OF_MONTH);
		
		calendar.setTime(endDate);
		int yearEnd = calendar.get(Calendar.YEAR);
		int monthEnd = calendar.get(Calendar.MONTH) + 1;
		int dayEnd = calendar.get(Calendar.DAY_OF_MONTH);
		
		
		String empNames = getK3EmpNames(userId);
		
		return statisticsDAO.statBetweenDate(yearBegin, monthBegin, dayBegin, 
				yearEnd, monthEnd, dayEnd, empNames);
	}

	/* (non-Javadoc)
	 * @see com.sales.service.K3OrderStatisticsService#statByDay(java.util.Date, java.util.Date, int)
	 */
	@Override
	public List<K3OrderStatistics> statByDay(Date beginDate, Date endDate,
			int userId) {
		Calendar calendar = Calendar.getInstance();
		
		calendar.setTime(beginDate);
		int yearBegin = calendar.get(Calendar.YEAR);
		int monthBegin = calendar.get(Calendar.MONTH) + 1;
		int dayBegin = calendar.get(Calendar.DAY_OF_MONTH);
		
		calendar.setTime(endDate);
		int yearEnd = calendar.get(Calendar.YEAR);
		int monthEnd = calendar.get(Calendar.MONTH) + 1;
		int dayEnd = calendar.get(Calendar.DAY_OF_MONTH);
		
		
		String empNames = getK3EmpNames(userId);
		
		List<K3OrderStatistics> list = statisticsDAO.statByDay(
				yearBegin, monthBegin, dayBegin, yearEnd, monthEnd, dayEnd, empNames);
		return list;
	}

	/* (non-Javadoc)
	 * @see com.sales.service.K3OrderStatisticsService#statByWeek(java.util.Date, java.util.Date, int)
	 */
	@Override
	public List<K3OrderStatistics> statByWeek(Date beginDate, Date endDate,
			int userId) {
		Calendar calendar = Calendar.getInstance();
		
		calendar.setTime(beginDate);
		int yearBegin = calendar.get(Calendar.YEAR);
		int monthBegin = calendar.get(Calendar.MONTH) + 1;
		int dayBegin = calendar.get(Calendar.DAY_OF_MONTH);
		
		calendar.setTime(endDate);
		int yearEnd = calendar.get(Calendar.YEAR);
		int monthEnd = calendar.get(Calendar.MONTH) + 1;
		int dayEnd = calendar.get(Calendar.DAY_OF_MONTH);
		
		
		String empNames = getK3EmpNames(userId);
		
		return statisticsDAO.statByWeek(yearBegin, monthBegin, dayBegin, yearEnd, monthEnd, dayEnd, empNames);
	}

	/* (non-Javadoc)
	 * @see com.sales.service.K3OrderStatisticsService#statByMonth(java.util.Date, java.util.Date, int)
	 */
	@Override
	public List<K3OrderStatistics> statByMonth(Date beginDate, Date endDate,
			int userId) {
		Calendar calendar = Calendar.getInstance();
		
		calendar.setTime(beginDate);
		int yearBegin = calendar.get(Calendar.YEAR);
		int monthBegin = calendar.get(Calendar.MONTH) + 1;
		int dayBegin = calendar.get(Calendar.DAY_OF_MONTH);
		
		calendar.setTime(endDate);
		int yearEnd = calendar.get(Calendar.YEAR);
		int monthEnd = calendar.get(Calendar.MONTH) + 1;
		int dayEnd = calendar.get(Calendar.DAY_OF_MONTH);
		
		
		String empNames = getK3EmpNames(userId);
		
		return statisticsDAO.statByMonth(yearBegin, monthBegin, dayBegin, yearEnd, monthEnd, dayEnd, empNames);
	}
	
	/**
	 * 获取指定的用户对应K3及下属的用户名（多个用户名用“,”分隔）
	 * @param userId	crm用户的id
	 * @return
	 */
	private String getK3EmpNames(int userId) {
		String empNames = null;
		List<String> list = userService.getK3EmpNamesByParentId(userId);
		if (list.size() > 0) { 
			int index = 0;
			empNames = new String();
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				String emp = "'" + (String) iterator.next() + "'";
				if(index == 0 )
					empNames = emp;
				else
					empNames = empNames + "," + emp;
				index++;
			}
		}
		return empNames;
	}

}
