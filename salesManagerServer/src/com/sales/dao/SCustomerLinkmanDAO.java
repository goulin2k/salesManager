package com.sales.dao;

import java.util.List;

import com.sales.model.CustomerLinkmanStatics;
import com.sales.model.SCustomerLinkman;

public interface SCustomerLinkmanDAO {
    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table s_customer_linkman
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    void insert(SCustomerLinkman record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table s_customer_linkman
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    int updateByPrimaryKey(SCustomerLinkman record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table s_customer_linkman
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    int updateByPrimaryKeySelective(SCustomerLinkman record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table s_customer_linkman
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    SCustomerLinkman selectByPrimaryKey(Integer linkmanId);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table s_customer_linkman
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    int deleteByPrimaryKey(Integer linkmanId);
    
    public List<SCustomerLinkman> getLinkmanList(Long pageNumber, int pageSize, SCustomerLinkman linkman);
	
	public Integer getLinkmanCount(SCustomerLinkman linkman);
	
	public List<SCustomerLinkman> getLinkmanListByCustomerId(Integer customerId);
	public List<SCustomerLinkman> getLinkmanListByCustomerIds(Long pageNumber, int pageSize, String customerIds, SCustomerLinkman linkman);
	public Integer getLinkmanCountByCustomerIds(String customerIds,SCustomerLinkman linkman);
	
	/**
	 * 查询获取有效的联系人填写统计
	 * @param customerId
	 * @return
	 */
	public List<CustomerLinkmanStatics> getCustomerLinkmanStatis(Integer customerId);
    
}