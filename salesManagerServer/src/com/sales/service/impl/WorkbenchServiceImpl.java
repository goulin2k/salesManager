package com.sales.service.impl;

import java.util.List;

import com.sales.dao.SModuleDAO;
import com.sales.dao.SUserModuleDAO;
import com.sales.model.SModule;
import com.sales.service.WorkbenchService;

/** 
 * @author  
 * @version 创建时间：2013-5-18 下午03:40:50 
 *  
 */
public class WorkbenchServiceImpl implements WorkbenchService {
	
	private SModuleDAO sModuleDao;
	private SUserModuleDAO sUserModuleDao;

	public SModuleDAO getsModuleDao() {
		return sModuleDao;
	}

	public void setsModuleDao(SModuleDAO sModuleDao) {
		this.sModuleDao = sModuleDao;
	}

	public SUserModuleDAO getsUserModuleDao() {
		return sUserModuleDao;
	}

	public void setsUserModuleDao(SUserModuleDAO sUserModuleDao) {
		this.sUserModuleDao = sUserModuleDao;
	}

	@Override
	public List<SModule> getModuleListByUserId(Integer userId) {
		// TODO Auto-generated method stub
		List userModuleList = sUserModuleDao.getModuleListByUserId(userId);
		return userModuleList;
	}

	@Override
	public List<SModule> getModuleList(Integer roleId) {
		// TODO Auto-generated method stub
		List moduleList = sModuleDao.getModuleList(roleId);
		return moduleList;
	}

	@Override
	public List<SModule> getModuleListByActionId(Integer actionId) {
		// TODO Auto-generated method stub
		List moduleList = sModuleDao.getModuleListByActionId(actionId);
		return moduleList;
	}

	@Override
	public void setUserModule(String moduleIds, Integer userId) {
		// TODO Auto-generated method stub
		
		this.sUserModuleDao.deleteModuleByUserId(userId);
		String[] mIds = moduleIds.split(",");
		for (String mId : mIds){
			Integer moduleId = Integer.valueOf(mId);
			this.sUserModuleDao.addUserModule(moduleId, userId);
		}			
	}

	/* (non-Javadoc)
	 * @see com.sales.service.WorkbenchService#getModuleListByActionName(java.lang.String)
	 */
	@Override
	public List<SModule> getModuleListByActionName(String actionName, Integer userId) {
		if(actionName.equals("assess") || actionName.equalsIgnoreCase("salePlanReport"))
			actionName = "project";
		List moduleList = sUserModuleDao.getModuleListByActionName(actionName, userId);
		return moduleList;
	}

	@Override
	public List<SModule> getModuleListByFilter(Integer actionId,
			String roleModuleFilter) {
		// TODO Auto-generated method stub
		return this.sModuleDao.getModuleListByFilter(actionId, roleModuleFilter);
	}

	@Override
	public List<SModule> getModuleListByActionNameInFilter(String actionName,
			String roleModuleFilter) {
		// TODO Auto-generated method stub
		List moduleList = sUserModuleDao.getModuleListByActionName(actionName, null);
		return moduleList;
	}

}
