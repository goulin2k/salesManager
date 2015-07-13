/**
 * 
 */
package com.sales.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.sales.dao.K3ReciveBilInfoDAO;
import com.sales.model.TReceiveBillInfo;

/**
 * @author apple
 *
 */
public class K3ReciveBilInfoDAOImpl extends SqlMapClientDaoSupport implements K3ReciveBilInfoDAO {

	/* (non-Javadoc)
	 * @see com.sales.dao.K3ReciveBilInfoDAO#getInsertedBills()
	 */
	@Override
	public List<TReceiveBillInfo> getInsertedBills() {
		List<TReceiveBillInfo> list = getSqlMapClientTemplate()
				.queryForList("t_receive_bill_msg.getReceiveBillsInserted");
		getSqlMapClientTemplate().update("t_receive_bill_msg.updateSynTime");
		return list;
	}

}
