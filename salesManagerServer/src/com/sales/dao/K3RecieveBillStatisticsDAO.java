/**
 * 
 */
package com.sales.dao;

import java.util.List;

import com.sales.model.K3OrderStatistics;
import com.sales.model.K3RecieveBillStatistics;

/**
 * 销售收款统计数据访问类
 * @author goulin
 *
 */
public interface K3RecieveBillStatisticsDAO {
	
	/**
	 * 统计时间段内销售收款指标
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
	 * 按天、业务员统计销售收款业绩
	 * @param yearBegin
	 * @param monthBegin
	 * @param yearEnd
	 * @param monthEnd
	 * @param dayEnd
	 * @param empNames
	 * @return
	 */
	public List<K3RecieveBillStatistics> statByDay(int yearBegin, int monthBegin, int dayBegin,
			int yearEnd, int monthEnd, int dayEnd, String empNames);
	
	
	/**
	 * 按周、业务员统计销售收款业绩
	 * @param yearBegin
	 * @param weekBegin
	 * @param yearEnd
	 * @param weekEnd
	 * @param empNames
	 * @return
	 */
	public List<K3RecieveBillStatistics> statByWeek(int yearBegin, int monthBegin, int dayBegin, 
			int yearEnd, int monthEnd, int dayEnd, String empNames);
	
	/**
	 * 按月、业务员统计销售收款业绩
	 * @param yearBegin
	 * @param monthBegin
	 * @param yearEnd
	 * @param monthEnd
	 * @param empNames
	 * @return
	 */
	public List<K3RecieveBillStatistics> statByMonth(int yearBegin, int monthBegin, int dayBegin,
			int yearEnd, int monthEnd, int dayEnd, String empNames);

}
