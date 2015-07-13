package com.sales.service.impl;

import java.util.List;

import com.sales.dao.SRoleActionDAO;
import com.sales.model.SRoleAction;
import com.sales.service.RoleActionService;

/** 
 * @author  
 * @version 创建时间：2013-5-4 上午10:00:12 
 *  
 */
public class RoleActionServiceImpl implements RoleActionService {
	
	private SRoleActionDAO roleActionDao;

	public SRoleActionDAO getRoleActionDao() {
		return roleActionDao;
	}

	public void setRoleActionDao(SRoleActionDAO roleActionDao) {
		this.roleActionDao = roleActionDao;
	}

	@Override
	public List<SRoleAction> getRoleActionListByRoleId( Integer roleId) {
		// TODO Auto-generated method stub
		return roleActionDao.getRoleActionListByRoleId(roleId);
	}

	@Override
	public void setRoleAction(String actionIds, Integer roleId) {
		// TODO Auto-generated method stub
		this.roleActionDao.deleteByRoleId(roleId);
		String[] aIds = actionIds.split(",");
		SRoleAction roleAction = new SRoleAction();
		for (String aId : aIds){
			Integer actionId = Integer.valueOf(aId);
			roleAction.setActionId(actionId);
			roleAction.setRoleId(roleId);
			this.roleActionDao.insert(roleAction);
		}	
	}

	@Override
	public void setRoleModule(String moduleIds, Integer roleId) {
		// TODO Auto-generated method stub
		this.roleActionDao.deleteByRoleId(roleId);
		String[] mIds = moduleIds.split(",");
		SRoleAction roleAction = new SRoleAction();
		for (String mId : mIds){
			//Integer method = Integer.valueOf(mId);
			roleAction.setMethod(mId);
			roleAction.setRoleId(roleId);
			roleAction.setActionId(1);
			this.roleActionDao.insert(roleAction);
		}
		
	}

}
