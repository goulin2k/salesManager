package com.sales.service;

import java.util.List;

import com.sales.model.SRoleAction;

/** 
 * @author  
 * @version 创建时间：2013-5-4 上午09:59:41 
 *  
 */
public interface RoleActionService {
	
	List<SRoleAction> getRoleActionListByRoleId(Integer roleId);
	
	public void setRoleAction(String actionIds,Integer roleId);
	public void setRoleModule(String moduleIds,Integer roleId);

}
