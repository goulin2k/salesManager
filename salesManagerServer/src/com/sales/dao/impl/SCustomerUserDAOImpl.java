package com.sales.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sales.dao.SCustomerUserDAO;
import com.sales.model.SCustomerUser;

import my.com.ibatis.core.dao.support.Page;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class SCustomerUserDAOImpl extends SqlMapClientDaoSupport implements SCustomerUserDAO {

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table s_customer_user
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    public SCustomerUserDAOImpl() {
        super();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table s_customer_user
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    public void insert(SCustomerUser record) {
        getSqlMapClientTemplate().insert("s_customer_user.abatorgenerated_insert", record);
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table s_customer_user
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    public int updateByPrimaryKey(SCustomerUser record) {
        int rows = getSqlMapClientTemplate().update("s_customer_user.abatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table s_customer_user
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    public int updateByPrimaryKeySelective(SCustomerUser record) {
        int rows = getSqlMapClientTemplate().update("s_customer_user.abatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table s_customer_user
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    public SCustomerUser selectByPrimaryKey(Integer customerUserId) {
        SCustomerUser key = new SCustomerUser();
        key.setCustomerUserId(customerUserId);
        SCustomerUser record = (SCustomerUser) getSqlMapClientTemplate().queryForObject("s_customer_user.abatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table s_customer_user
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    public int deleteByPrimaryKey(Integer customerUserId) {
        SCustomerUser key = new SCustomerUser();
        key.setCustomerUserId(customerUserId);
        int rows = getSqlMapClientTemplate().delete("s_customer_user.abatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

	@Override
	public List<SCustomerUser> getCustomerUserListByUserId(Integer userId) {
		// TODO Auto-generated method stub
		List cUserList = getSqlMapClientTemplate().queryForList("s_customer_user.getCustomerUserListByUserId", userId);
		return cUserList;
	}
	
	public List<SCustomerUser> getCustomerUserListByCustomerId(Integer customerId){
		List customerList = getSqlMapClientTemplate().queryForList("s_customer_user.getCustomerUserListByCustomerId",customerId);
		return customerList;
	}

	@Override
	public Integer getCustomerUserCount(SCustomerUser customerUser) {
		// TODO Auto-generated method stub
		return (Integer) getSqlMapClientTemplate().queryForObject("s_customer_user.getCustomerUserCount",customerUser);
	}

	@Override
	public List<SCustomerUser> selectCustomerUserList(Long pageNumber,
			int pageSize, SCustomerUser customerUser) {
		// TODO Auto-generated method stub
		long startRow = Page.getStartOfPage(pageNumber, pageSize);
		long endRow = pageSize;
		Map<String,Comparable> queryMap = new HashMap<String,Comparable>();
		queryMap.put("startRow", startRow);
		queryMap.put("endRow",endRow);
		queryMap.put("customerId",customerUser.getCustomerId());
		queryMap.put("userId",customerUser.getUserId());
		List customerUserList = getSqlMapClientTemplate().queryForList("s_customer_user.selectCustomerUserList",queryMap);
		return customerUserList;
	}

	@Override
	public void deleteByCustomerId(Integer customerId, Integer userId) {
		// TODO Auto-generated method stub
		Map<String,Comparable> queryMap = new HashMap<String,Comparable>();
		queryMap.put("customerId", customerId);
		queryMap.put("userId", userId);
		int rows = getSqlMapClientTemplate().delete("s_customer_user.deleteByCustomerId", queryMap);
	}
}