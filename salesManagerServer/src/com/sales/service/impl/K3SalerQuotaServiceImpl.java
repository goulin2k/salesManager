/**
 * 
 */
package com.sales.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sales.dao.K3SalerQuotaDAO;
import com.sales.model.K3SalerQuota;
import com.sales.service.K3SalerQuotaService;

/**
 * @author Administrator
 *
 */
public class K3SalerQuotaServiceImpl implements K3SalerQuotaService {
	
	private K3SalerQuotaDAO quotaDAO;
	

	/**
	 * @param quotaDAO the quotaDAO to set
	 */
	public void setQuotaDAO(K3SalerQuotaDAO quotaDAO) {
		this.quotaDAO = quotaDAO;
	}

	/* (non-Javadoc)
	 * @see com.sales.service.K3SalerQuotaService#query(com.sales.model.K3SalerQuota)
	 */
	/* (non-Javadoc)
	 * @see com.sales.service.K3SalerQuotaService#query(com.sales.model.K3SalerQuota)
	 */
	@Override
	public List<K3SalerQuota> query(K3SalerQuota quota) {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		Integer quotaType = quota.getQuotaType();
		queryMap.put("userId", quota.getUserId());
		queryMap.put("quotaType", quotaType);
		queryMap.put("quotaClass", quota.getQuotaClass());
		queryMap.put("year", quota.getYear());
		
		if(quotaType==K3SalerQuota.QUOTA_TYPE_YEAR) {
			queryMap.put("quarter", null);
			queryMap.put("month", null);
		}else if(quotaType == K3SalerQuota.QUOTA_TYPE_QUARTER) {
			queryMap.put("quarter", quota.getQuarter());
			queryMap.put("month", null);
		}else if(quotaType == K3SalerQuota.QUOTA_TYPE_MONTH) {
			queryMap.put("quarter", null);
			queryMap.put("month", quota.getMonth());
		} else {
			queryMap.put("quarter", quota.getQuarter());
			queryMap.put("month", quota.getMonth());
		}
		
		return quotaDAO.query(queryMap);
	}

	/* (non-Javadoc)
	 * @see com.sales.service.K3SalerQuotaService#queryThisMonon(int, int, int)
	 */
	@Override
	public Double queryThisMonth(int year, int month, int userId, Integer quotaClass) {
		K3SalerQuota quota = new K3SalerQuota();
		quota.setQuotaClass(quotaClass);
		quota.setQuotaType(K3SalerQuota.QUOTA_TYPE_MONTH);
		quota.setUserId(userId);
		quota.setYear(year);
		quota.setMonth(month);
		
		List<K3SalerQuota> list = query(quota);
		if(list == null || list.size() == 0) {
			return null;
		}else if(list.size() == 1) {
			return ((K3SalerQuota)list.get(0)).getTarget();
		}else {
			throw new RuntimeException("指标数据错误：相同月份下存在重复的业务指标数据！");
		}
	}

	/* (non-Javadoc)
	 * @see com.sales.service.K3SalerQuotaService#queryThisQuarter(int, int, int)
	 */
	@Override
	public Double queryThisQuarter(int year, int quarter, int userId, Integer quotaClass) {
		K3SalerQuota quota = new K3SalerQuota();
		quota.setQuotaType(K3SalerQuota.QUOTA_TYPE_QUARTER);
		quota.setQuotaClass(quotaClass);
		quota.setUserId(userId);
		quota.setYear(year);
		quota.setQuarter(quarter);
		
		List<K3SalerQuota> list = query(quota);
		if(list == null || list.size() == 0) {
			return null;
		}else if(list.size() == 1) {
			return ((K3SalerQuota)list.get(0)).getTarget();
		}else {
			throw new RuntimeException("指标数据错误：相同季度下存在重复的业务指标数据！");
		}
	}

	/* (non-Javadoc)
	 * @see com.sales.service.K3SalerQuotaService#queryThisYear(int, int)
	 */
	@Override
	public Double queryThisYear(int year, int userId, Integer quotaClass) {
		K3SalerQuota quota = new K3SalerQuota();
		quota.setQuotaType(K3SalerQuota.QUOTA_TYPE_YEAR);
		quota.setQuotaClass(quotaClass);
		quota.setUserId(userId);
		quota.setYear(year);
		
		List<K3SalerQuota> list = query(quota);
		if(list == null || list.size() == 0) {
			return null;
		}else if(list.size() == 1) {
			return ((K3SalerQuota)list.get(0)).getTarget();
		}else {
			throw new RuntimeException("指标数据错误：相同年度下存在重复的业务指标数据！");
		}
	}

}
