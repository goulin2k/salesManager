/**
 * 
 */
package com.sales.service;

import java.util.List;

import com.sales.model.K3SalerQuota;

/**
 * 销售指标业务接口
 * @author 苟林
 *
 */
public interface K3SalerQuotaService {
	
	/**
	 * 通用查询方法
	 * @param quota
	 * @param quotaClass
	 * @return
	 */
	public List<K3SalerQuota> query(K3SalerQuota quota);
	
	/**
	 * 查询当月指标
	 * @param year
	 * @param month
	 * @param userId
	 * @param quotaClass
	 * @return
	 */
	public Double queryThisMonth(int year, int month, int userId, Integer quotaClass);
	
	/**
	 * 查询当季销售指标
	 * @param year
	 * @param quarter
	 * @param userId
	 * @param quotaClass
	 * @return
	 */
	public Double queryThisQuarter(int year, int quarter, int userId, Integer quotaClass);
	
	/**
	 * 查询当年销售指标
	 * @param year
	 * @param userId
	 * @param quotaClass
	 * @return
	 */
	public Double queryThisYear(int year, int userId, Integer quotaClass);

}
