package com.sales.dao;

import java.util.List;
import java.util.Map;

import com.sales.model.K3OrderCustomerStatistics;
import com.sales.model.SCustomerUser;
import com.sales.model.TCustomer;

public interface TCustomerDAO {
    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_customer
     *
     * @abatorgenerated Fri Jun 14 14:24:14 CST 2013
     */
    void insert(TCustomer record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_customer
     *
     * @abatorgenerated Fri Jun 14 14:24:14 CST 2013
     */
    int updateByPrimaryKey(TCustomer record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_customer
     *
     * @abatorgenerated Fri Jun 14 14:24:14 CST 2013
     */
    int updateByPrimaryKeySelective(TCustomer record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_customer
     *
     * @abatorgenerated Fri Jun 14 14:24:14 CST 2013
     */
    TCustomer selectByPrimaryKey(Integer flevel, Integer customerId);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table t_customer
     *
     * @abatorgenerated Fri Jun 14 14:24:14 CST 2013
     */
    int deleteByPrimaryKey(Integer flevel, Integer customerId);
    
    public List<TCustomer> getCustomerList(Long pageNumber, int pageSize);
    public List<TCustomer> selectCustomerList(Long pageNumber, int pageSize, TCustomer tcustomer);
    
    public Integer getCustomerCount();
    
    public TCustomer getCustomerInfoById(Integer customerId);
    
    public Integer getCustomerCountByObj(TCustomer tcustomer);
    
    public List<TCustomer> getOpenCustomerList(Map queryMap);
	
	public Integer getOpenCustomerCount(Map queryMap);
	
	public List<TCustomer> getCustomerListByFilter(Map queryMap);
	public Integer getCustomerCountByFilter(Map queryMap);
	
	public List<TCustomer> getOpenDistributionCustomerList(Map queryMap);
	
	public Integer getOpenDistributionCustomerCount(Map queryMap);
	
	public void updateLinkmanCompletely(int customerId, Double completely);
	    
}