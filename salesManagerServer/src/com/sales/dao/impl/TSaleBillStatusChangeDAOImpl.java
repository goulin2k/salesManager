package com.sales.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.sales.dao.TSaleBillStatusChangeDAO;

public class TSaleBillStatusChangeDAOImpl extends SqlMapClientDaoSupport implements TSaleBillStatusChangeDAO {
	
	public List getSaleBillStatusChange(){
		List statusList = getSqlMapClientTemplate().queryForList("t_sale_bill_status_change.getSaleBillStatusChange");
		return statusList;
	}
	
	public void updateSaleBillStatusChangeRead(String billStatusIds){
		getSqlMapClientTemplate().update("t_sale_bill_status_change.updateSaleBillStatusChangeRead", billStatusIds);
	}

}
