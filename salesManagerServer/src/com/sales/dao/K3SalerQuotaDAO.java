/**
 * 
 */
package com.sales.dao;

import java.util.List;
import java.util.Map;

import com.sales.model.K3SalerQuota;

/**
 * 销售指标数据访问接口
 * @author Administrator
 *
 */
public interface K3SalerQuotaDAO {

	/**
	 * 指标查询接口
	 * @param queryMap
	 * @return
	 */
	public List<K3SalerQuota> query(Map<String, Object> queryMap);
}
