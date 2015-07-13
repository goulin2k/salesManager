/**
 * 
 */
package com.sales.dao;

import java.util.List;

import com.sales.model.K3OrderStatistics;

/**
 * 销售发票（开票）统计数据访问类
 * @author goulin
 *
 */
public interface K3ICSaleStatisticsDAO {
	
	/**
	 * 统计时间段内销售指标
	 * @param yearBegin
	 * @param monthBegin
	 * @param yearEnd
	 * @param monthEnd
	 * @param dayEnd
	 * @param empNames
	 * @return
	 */
	public double statBetweenDate(int yearBegin, int monthBegin, int dayBegin, 
			int yearEnd, int monthEnd, int dayEnd, String empNames);
	
	
	/**
	 * 按天、业务员统计销售业绩
	 * @param yearBegin
	 * @param monthBegin
	 * @param yearEnd
	 * @param monthEnd
	 * @param dayEnd
	 * @param empNames
	 * @return
	 */
	public List<K3OrderStatistics> statByDay(int yearBegin, int monthBegin, int dayBegin,
			int yearEnd, int monthEnd, int dayEnd, String empNames);
	
	
	/**
	 * 按周、业务员统计销售业绩
	 * @param yearBegin
	 * @param weekBegin
	 * @param yearEnd
	 * @param weekEnd
	 * @param empNames
	 * @return
	 */
	public List<K3OrderStatistics> statByWeek(int yearBegin, int monthBegin, int dayBegin, 
			int yearEnd, int monthEnd, int dayEnd, String empNames);
	
	/**
	 * 按月、业务员统计销售业绩
	 * @param yearBegin
	 * @param monthBegin
	 * @param yearEnd
	 * @param monthEnd
	 * @param empNames
	 * @return
	 */
	public List<K3OrderStatistics> statByMonth(int yearBegin, int monthBegin, int dayBegin,
			int yearEnd, int monthEnd, int dayEnd, String empNames);

}
