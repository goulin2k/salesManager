/**
 * 
 */
package com.sales.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.sales.dao.K3SalerQuotaDAO;
import com.sales.model.K3SalerQuota;

/**
 * @author Administrator
 *
 */
public class K3SalerQuotaDAOImpl extends SqlMapClientDaoSupport implements K3SalerQuotaDAO {

	/* (non-Javadoc)
	 * @see com.sales.dao.K3SalerQuotaDAO#query(java.util.Map)
	 */
	@Override
	public List<K3SalerQuota> query(Map<String, Object> queryMap) {
		return getSqlMapClientTemplate().queryForList("s_saler_quota.query", queryMap);
	}

}
