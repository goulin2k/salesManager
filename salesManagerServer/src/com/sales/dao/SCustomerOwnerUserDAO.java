package com.sales.dao;

import java.util.List;

import com.sales.model.K3OrderCustomerStatistics;
import com.sales.model.SCustomerOwnerUser;

public interface SCustomerOwnerUserDAO {
    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table s_customer_owner_user
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    void insert(SCustomerOwnerUser record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table s_customer_owner_user
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    int updateByPrimaryKey(SCustomerOwnerUser record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table s_customer_owner_user
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    int updateByPrimaryKeySelective(SCustomerOwnerUser record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table s_customer_owner_user
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    SCustomerOwnerUser selectByPrimaryKey(Integer customerOwnerUserId);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table s_customer_owner_user
     *
     * @abatorgenerated Tue Apr 23 17:36:30 CST 2013
     */
    int deleteByPrimaryKey(Integer customerOwnerUserId);
    
    public List<SCustomerOwnerUser> getCOUserListByUserId(Integer userId);
    
    public List<SCustomerOwnerUser> getCOUList(Long pageNumber, int pageSize, 
    		SCustomerOwnerUser couser, String customerIds, int oweredStatus);
	
	public Integer getCOUCount(SCustomerOwnerUser couser, String customerIds, int oweredStatus); 
	
	public List<SCustomerOwnerUser> getUserListByCustomerId(Integer customerId);
	
	public void deleteByCustomerId(Integer customerId);
	
	public List<SCustomerOwnerUser> getHistoryCOUList(Long pageNumber, int pageSize, 
    		SCustomerOwnerUser couser, String customerIds, int oweredStatus);
	
	public Integer getHistoryCOUCount(SCustomerOwnerUser couser, String customerIds, int oweredStatus);
	
	public void updateCustomerOwerUser(SCustomerOwnerUser record);
	
	
	
}