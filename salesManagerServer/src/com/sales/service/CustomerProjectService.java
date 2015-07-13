package com.sales.service;
/** 
 * @author  
 * @version 创建时间：2013-6-27 下午05:04:18 
 *  
 */

import java.util.List;

import com.sales.model.SCustomerProject;
public interface CustomerProjectService {
	
	public List<SCustomerProject> getCPListByCustomerId(Integer customerId);
	
	public void addCustomerProject(SCustomerProject customerProject);
	
	public SCustomerProject getCustomerProjectById(Integer id);
	
	public void updateCustomerProject(SCustomerProject customerProject);
	
	public void deleteCustomerProject(Integer id);
	
	public List<SCustomerProject> selectCPlist(Long pageNumber, int pageSize, 
			SCustomerProject project);
	public Integer getCustomerProjectCount(SCustomerProject project);

	public List<SCustomerProject> selectCPlistByCustomerIds(Long pageNumber, int pageSize, 
			SCustomerProject project, String customerIds);
	public Integer getCPCountByCustomerIds(SCustomerProject project, String customerIds);
}
