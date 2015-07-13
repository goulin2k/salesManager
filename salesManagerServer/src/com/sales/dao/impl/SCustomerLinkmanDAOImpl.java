package com.sales.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sales.common.NormalFun;
import com.sales.dao.SCustomerLinkmanDAO;
import com.sales.model.CustomerLinkmanStatics;
import com.sales.model.SCustomerLinkman;

import my.com.ibatis.core.dao.support.Page;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class SCustomerLinkmanDAOImpl extends SqlMapClientDaoSupport implements SCustomerLinkmanDAO {

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table s_customer_linkman
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    public SCustomerLinkmanDAOImpl() {
        super();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table s_customer_linkman
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    public void insert(SCustomerLinkman record) {
        getSqlMapClientTemplate().insert("s_customer_linkman.abatorgenerated_insert", record);
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table s_customer_linkman
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    public int updateByPrimaryKey(SCustomerLinkman record) {
        int rows = getSqlMapClientTemplate().update("s_customer_linkman.abatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table s_customer_linkman
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    public int updateByPrimaryKeySelective(SCustomerLinkman record) {
        int rows = getSqlMapClientTemplate().update("s_customer_linkman.abatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table s_customer_linkman
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    public SCustomerLinkman selectByPrimaryKey(Integer linkmanId) {
        SCustomerLinkman key = new SCustomerLinkman();
        key.setLinkmanId(linkmanId);
        SCustomerLinkman record = (SCustomerLinkman) getSqlMapClientTemplate().queryForObject("s_customer_linkman.abatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table s_customer_linkman
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    public int deleteByPrimaryKey(Integer linkmanId) {
        SCustomerLinkman key = new SCustomerLinkman();
        key.setLinkmanId(linkmanId);
        int rows = getSqlMapClientTemplate().delete("s_customer_linkman.abatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

	@Override
	public Integer getLinkmanCount(SCustomerLinkman linkman) {
		
		Map<String,Comparable> queryMap = new HashMap<String,Comparable>();
		if(linkman.getName()!=null){
			queryMap.put("name", linkman.getName());
		}
		if(linkman.getCustomerName()!=null){
			queryMap.put("customerName", linkman.getCustomerName());
		}
		if(linkman.getCustomerOwnerUserName()!=null){
			queryMap.put("customerOwnerUserName", linkman.getCustomerOwnerUserName());
		}
		Integer temp = (Integer) getSqlMapClientTemplate().queryForObject("s_customer_linkman.getLinkmanCount",queryMap);
		return temp;
	}

	@Override
	public List<SCustomerLinkman> getLinkmanList(Long pageNumber, int pageSize, SCustomerLinkman linkman) {
		
		long startRow = Page.getStartOfPage(pageNumber, pageSize);
		long endRow = startRow + pageSize;
		Map<String,Comparable> queryMap = new HashMap<String,Comparable>();
		queryMap.put("startRow", startRow);
		queryMap.put("endRow", endRow);
		if(linkman.getName()!=null){
			queryMap.put("name", linkman.getName());
		}
		if(linkman.getCustomerName()!=null){
			queryMap.put("customerName", linkman.getCustomerName());
		}
		if(linkman.getCustomerOwnerUserName()!=null){
			queryMap.put("customerOwnerUserName", linkman.getCustomerOwnerUserName());
		}
		List linkmanList = getSqlMapClientTemplate().queryForList("s_customer_linkman.getLinkmanList",queryMap);
		return linkmanList;
	}

	@Override
	public List<SCustomerLinkman> getLinkmanListByCustomerId(Integer customerId) {
		
		List linkmanList = getSqlMapClientTemplate().queryForList("s_customer_linkman.getLinkmanListByCustomerId",customerId);
		return linkmanList;
	}

	@Override
	public List<SCustomerLinkman> getLinkmanListByCustomerIds(Long pageNumber,
			int pageSize, String customerIds,SCustomerLinkman linkman) {
		
		long startRow = Page.getStartOfPage(pageNumber, pageSize);
		//long endRow = startRow + pageSize;
		Map<String,Comparable> queryMap = new HashMap<String,Comparable>();
		queryMap.put("startRow", startRow);
		queryMap.put("endRow", pageSize);
		queryMap.put("customerIds", customerIds);
		queryMap.put("customerOwnerUserName", NormalFun.trimNull(linkman.getCustomerOwnerUserName()));
		if(linkman.getName()!=null){
			queryMap.put("name", linkman.getName());
		}
		if(linkman.getCustomerName()!=null){
			queryMap.put("customerName", linkman.getCustomerName());
		}
		List linkmanList = getSqlMapClientTemplate().queryForList("s_customer_linkman.getLinkmanListByCustomerIds",queryMap);
		return linkmanList;
	}

	@Override
	public Integer getLinkmanCountByCustomerIds(String customerIds, SCustomerLinkman linkman) {
		
		Map<String,Comparable> queryMap = new HashMap<String,Comparable>();
		queryMap.put("customerIds", customerIds);
		if(linkman.getName()!=null){
			queryMap.put("name", linkman.getName());
		}
		if(linkman.getCustomerName()!=null){
			queryMap.put("customerName", linkman.getCustomerName());
		}
		queryMap.put("customerIds", customerIds);
		Integer temp = (Integer) getSqlMapClientTemplate().queryForObject("s_customer_linkman.getLinkmanCountByCustomerIds",queryMap);
		return temp;
	}

	/* (non-Javadoc)
	 * @see com.sales.dao.SCustomerLinkmanDAO#getCustomerLinkmanStatis(java.lang.Integer)
	 */
	@Override
	public List<CustomerLinkmanStatics> getCustomerLinkmanStatis(
			Integer customerId) {
		if (customerId == null)
			return new ArrayList<CustomerLinkmanStatics>();
		return getSqlMapClientTemplate().
				queryForList("s_customer_linkman.getCustomerLinkmanStatis", customerId);
	}
	
	
}