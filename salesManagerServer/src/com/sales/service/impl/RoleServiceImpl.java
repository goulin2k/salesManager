package com.sales.service.impl;

import java.util.List;

import com.sales.dao.SRoleDAO;
import com.sales.model.SRole;
import com.sales.model.SUser;
import com.sales.service.RoleService;

/** 
 * @author  
 * @version 创建时间：2013-5-4 上午09:22:06 
 *  
 */
public class RoleServiceImpl implements RoleService {
	
	private SRoleDAO roleDao;

	public SRoleDAO getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(SRoleDAO roleDao) {
		this.roleDao = roleDao;
	}

	@Override
	public List<SRole> getRoleList() {
		
		List roleList = roleDao.getRoleList();
		return roleDao.getRoleList();
	}

	@Override
	public void deleteRole(Integer roleId) {
		
		
		this.roleDao.deleteByPrimaryKey(roleId);
		
	}

	@Override
	public void insertRole(SRole sRole) {
		
		
		this.roleDao.insert(sRole);
		
	}

	/* (non-Javadoc)
	 * @see com.sales.service.RoleService#getRole(java.lang.Integer)
	 */
	@Override
	public SRole getRole(Integer roleId) {
		return roleDao.selectByPrimaryKey(roleId);
		
	}

	@Override
	public List<SUser> getUsers(Integer positionId) {
		return roleDao.getUsers(positionId);
	}
	
	
	
}
