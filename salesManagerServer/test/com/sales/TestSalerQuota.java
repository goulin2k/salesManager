/**
 * 
 */
package com.sales;

import java.util.Iterator;
import java.util.List;

import com.sales.common.NormalFun;
import com.sales.model.K3SalerQuota;
import com.sales.service.K3OrderStatisticsService;
import com.sales.service.K3SalerQuotaService;

/**
 * @author Administrator
 *
 */
public class TestSalerQuota extends BaseTestCase {

	private K3SalerQuotaService service;
	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		service = (K3SalerQuotaService) ctx.getBean("k3SalerQuotaService");
		super.setUp();
	}
	
	public void testQuery() {
		K3SalerQuota quota = new K3SalerQuota();
		quota.setUserId(26);
		quota.setYear(2014);
		quota.setQuotaType(K3SalerQuota.QUOTA_TYPE_QUARTER);
		quota.setQuotaClass(K3SalerQuota.QUOTA_CLASS_RECIEVEBILL);
		
		List<K3SalerQuota> list = service.query(quota);
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			K3SalerQuota k3SalerQuota = (K3SalerQuota) iterator.next();
			logger.info(k3SalerQuota);			
		}
	}
	
	public void testMonth() {
		int userId = 26;
		int year = 2014;
		int month = 5;
		
		Double val = service.queryThisMonth(year, month, userId, K3SalerQuota.QUOTA_CLASS_RECIEVEBILL);
		logger.info("获取业务员当月销售指标值：\t" + NormalFun.formatCurrency(val));
	}
	
	public void testQuarter() {
		int userId = 26;
		int year = 2014;
		int quarter = 2;
		
		Double val = service.queryThisQuarter(year, quarter, userId, K3SalerQuota.QUOTA_CLASS_RECIEVEBILL);
		logger.info("获取业务员当季度业务指标值：\t" + NormalFun.formatCurrency(val));
	}
	
	public void testYear() {
		int userId = 26;
		int year = 2015;
		
		Double val = service.queryThisYear(year, userId, K3SalerQuota.QUOTA_CLASS_RECIEVEBILL);
		logger.info("获取业务员当年度业务指标值：\t" + NormalFun.formatCurrency(val));
	}
}
