/**
 * 
 */
package com.sales.service;

import java.util.Date;
import java.util.List;

import com.sales.model.K3OrderStatistics;

/**
 * 销售发票（开票金额）统计逻辑处理类
 * @author Goulin
 *
 */
public interface K3OrderStatisticsService {
	
	/**
	 * 统计当月销售指标完成情况
	 * @param userId
	 * @return
	 */
	public double statThisMonth(int userId);
	
	/**
	 * 统计制定年月的销售指标完成情况
	 * @param year
	 * @param month
	 * @param userId
	 * @return
	 */
	public double statMonth(int year, int month, int userId);
	
	/**
	 * 统计制定季度的销售指标完成情况
	 * @param year
	 * @param quarter
	 * @param userId
	 * @return
	 */
	public double statQuarter(int year, int quarter, int userId);
	
	/**
	 * 统计指定年份的销售指标完成情况
	 * @param year
	 * @param userId
	 * @return
	 */
	public double statYear(int year, int userId);
	
	/**
	 * 统计指定日期区间内的销售指标完成情况
	 * @param beginDate
	 * @param endDate
	 * @param userId
	 * @return
	 */
	public double statBetweenDate(Date beginDate, Date endDate, int userId);
	
	/**
	 * 按天统计指定日期范围内销售情况
	 * @param beginDate
	 * @param endDate
	 * @param userId
	 * @return
	 */
	public List<K3OrderStatistics> statByDay(Date beginDate, Date endDate, int userId);
	
	/**
	 * 按周统计指定日期范围内销售情况
	 * @param beginDate
	 * @param endDate
	 * @param userId
	 * @return
	 */
	public List<K3OrderStatistics> statByWeek(Date beginDate, Date endDate, int userId);
	
	/**
	 * 按月统计指定日期范围内销售情况
	 * @param beginDate
	 * @param endDate
	 * @param userId
	 * @return
	 */
	public List<K3OrderStatistics> statByMonth(Date beginDate, Date endDate, int userId);
}
