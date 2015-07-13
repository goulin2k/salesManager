package com.sales.service;
/** 
 * @author  
 * @version 创建时间：2013-5-4 上午09:21:28 
 *  
 */

import java.util.List;

import com.sales.model.SRole;
import com.sales.model.SUser;

public interface RoleService {
  
	/**
	 * @return
	 */
	public List<SRole> getRoleList();
	
	/**
	 * @param sRole
	 */
	public void insertRole(SRole sRole);
	
	/**
	 * 删除指定的角色
	 * @param roleId
	 */
	public void deleteRole(Integer roleId);
	
	/**
	 * 获取角色对象
	 * @param roleId
	 */
	public SRole getRole(Integer roleId);
	
	/**
	 * 获取指定岗位的用户记录
	 * @param roleId
	 * @return
	 */
	public List<SUser> getUsers(Integer positionId);
}
