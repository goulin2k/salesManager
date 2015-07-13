package com.sales.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import my.com.ibatis.core.dao.support.Page;

import com.sales.common.Constants;
import com.sales.model.K3OrderCustomerStatistics;
import com.sales.model.SCustomerLevel;
import com.sales.model.SCustomerOwnerUser;
import com.sales.model.SCustomerUser;
import com.sales.model.TCustomer;

/** 
 * @author  
 * @version 创建时间：2013-6-14 下午03:40:26 
 *  
 */
public interface CustomerService {
	
	public List<TCustomer> getCustomerList(Long pageNumber, int pageSize);
	public List<TCustomer> selectCustomerList(Long pageNumber, int pageSize,TCustomer tcustomer);
	
	public Integer getCustomerCount();
	public Integer getCustomerCountByObj(TCustomer tcustomer);
	
	public TCustomer getCustomerInfoById(Integer customerId);
	
	public List<SCustomerOwnerUser> getCOUList(Long pageNumber, int pageSize, 
			SCustomerOwnerUser couser, String customerIds,  int oweredStatus);
	
	public Integer getCOUCount(SCustomerOwnerUser couser, String customerIds,  int oweredStatus);
	
	public void deleteCOUser(Integer id,Integer userId);
	
	public void addCOUser(SCustomerOwnerUser couser);
	
	public void addCustomerUser(SCustomerUser customerUser);
	
	public List<SCustomerUser> getCustomerUserListByCustomerId(Integer customerId);
	
	public List<SCustomerUser> getCustomerUserListByUserId(Integer userId);
	public void deleteCustomerUser(Integer id);
	public List<SCustomerUser> selectCustomerUserList(Long pageNumber, int pageSize, SCustomerUser customerUser);
	public Integer getCustomerUserCount(SCustomerUser customerUser);
	public void updateCustomerLevel(SCustomerLevel customerLevel);
	public SCustomerLevel selectCustomerLevelByCustomerId(Integer customerId);
	
	public List<TCustomer> getOpenCustomerList(String fname, String fnumber, String customerIds, Long pageNumber, int pageSize);
	
	public Integer getOpenCustomerCount(String fname, String fnumber, String customerIds);
	
	public List<TCustomer> getCustomerListByFilter(TCustomer tcustomer, String customerIds, Long pageNumber, int pageSize);
	public Integer getCustomerCountByFilter(TCustomer tcustomer, String customerIds);
	
	public List<SCustomerOwnerUser> getCOUserListByCustomerId(Integer customerId);
	
	public void deleteCOUserByCustomerId(Integer customerId);
	
	public void deleteCustomerUserByCustomerId(Integer customerId, Integer userId);
	
	public List<SCustomerOwnerUser> getHistoryCOUList(Long pageNumber, int pageSize, 
			SCustomerOwnerUser couser, String customerIds,  int oweredStatus);
	
	public Integer getHistoryCOUCount(SCustomerOwnerUser couser, String customerIds,  int oweredStatus);
	
	public List<TCustomer> getOpenDistributionCustomerList(String fname, String fnumber, String customerIds, Long pageNumber, int pageSize);
	
	public Integer getOpenDistributionCustomerCount(String fname, String fnumber, String customerIds);
	
	

}
