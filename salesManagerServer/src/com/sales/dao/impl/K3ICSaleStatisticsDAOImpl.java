/**
 * 
 */
package com.sales.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.sales.dao.K3ICSaleStatisticsDAO;
import com.sales.dao.K3OrderStatisticsDAO;
import com.sales.model.K3OrderStatistics;

/**
 * 销售统计(销售发票）数据访问实现类
 * @author glin
 *
 */
public class K3ICSaleStatisticsDAOImpl extends SqlMapClientDaoSupport implements K3ICSaleStatisticsDAO {

	/* (non-Javadoc)
	 * @see com.sales.dao.K3OrderStatisticsDAO#statBetweenDate(int, int, int, int, int, java.lang.String)
	 */
	@Override
	public double statBetweenDate(int yearBegin, int monthBegin, int dayBegin, 
			int yearEnd, int monthEnd, int dayEnd, String empNames) {
		Map<String, Object> queryMap = new HashedMap();
		queryMap.put("start", yearBegin *10000 + monthBegin * 100 + dayBegin);
		queryMap.put("end", yearEnd *10000 + monthEnd * 100 + dayEnd);
		
		if(empNames == null || empNames.trim().length() == 0)
			empNames = null;
		else
			empNames = "(" + empNames + ")";
		
		queryMap.put("empNames", empNames);
		double amount = (Double)getSqlMapClientTemplate()
			.queryForObject("t_order_statistics.statBetweenDate", queryMap);
		return amount;
	}

	/* (non-Javadoc)
	 * @see com.sales.dao.K3OrderStatisticsDAO#statByDay(int, int, int, int, int, java.lang.String)
	 */
	@Override
	public List<K3OrderStatistics> statByDay(int yearBegin, int monthBegin, int dayBegin,
			int yearEnd, int monthEnd, int dayEnd, String empNames) {
		List<K3OrderStatistics> list = null;
		
		Map<String, Object> queryMap = new HashedMap();
		queryMap.put("start", yearBegin *10000 + monthBegin * 100 + dayBegin);
		queryMap.put("end", yearEnd *10000 + monthEnd * 100 + dayEnd);
		
		if(empNames == null || empNames.trim().length() == 0)
			empNames = null;
		else
			empNames = "(" + empNames + ")";
		
		queryMap.put("empNames", empNames);
		list = getSqlMapClientTemplate()
			.queryForList("t_order_statistics.statByDay", queryMap);
		return list;
	}

	/* (non-Javadoc)
	 * @see com.sales.dao.K3OrderStatisticsDAO#statByWeek(int, int, int, int, java.lang.String)
	 */
	@Override
	public List<K3OrderStatistics> statByWeek(int yearBegin, int monthBegin, int dayBegin,
			int yearEnd, int monthEnd, int dayEnd, String empNames) {
		List<K3OrderStatistics> list = null;
		
		Map<String, Object> queryMap = new HashedMap();
		queryMap.put("start", yearBegin *10000 + monthBegin * 100 + dayBegin);
		queryMap.put("end", yearEnd *10000 + monthEnd * 100 + dayEnd);
		
		if(empNames == null || empNames.trim().length() == 0)
			empNames = null;
		else
			empNames = "(" + empNames + ")";
		
		queryMap.put("empNames", empNames);
		list = getSqlMapClientTemplate()
			.queryForList("t_order_statistics.statByWeek", queryMap);
		return list;
	}

	/* (non-Javadoc)
	 * @see com.sales.dao.K3OrderStatisticsDAO#statByMonth(int, int, int, int, java.lang.String)
	 */
	@Override
	public List<K3OrderStatistics> statByMonth(int yearBegin, int monthBegin, int dayBegin,
			int yearEnd, int monthEnd, int dayEnd, String empNames) {
		List<K3OrderStatistics> list = null;
		
		Map<String, Object> queryMap = new HashedMap();
		queryMap.put("start", yearBegin *10000 + monthBegin * 100 + dayBegin);
		queryMap.put("end", yearEnd *10000 + monthEnd * 100 + dayEnd);
		
		if(empNames == null || empNames.trim().length() == 0)
			empNames = null;
		else
			empNames = "(" + empNames + ")";
		
		queryMap.put("empNames", empNames);
		list = getSqlMapClientTemplate()
			.queryForList("t_order_statistics.statByMonth", queryMap);
		return list;
	}

}
