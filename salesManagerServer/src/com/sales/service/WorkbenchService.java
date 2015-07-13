package com.sales.service;
/** 
 * @author  
 * @version 创建时间：2013-5-18 下午02:45:01 
 *  
 */

import java.util.List;

import com.sales.model.SModule;

public interface WorkbenchService {
	
	public List<SModule> getModuleListByUserId(Integer userId);
	
	public List<SModule> getModuleList(Integer roleId);
	
	public List<SModule> getModuleListByActionId(Integer actionId);
	
	public void setUserModule(String moduleIds, Integer userId);
	
	/**
	 * 根据Action获取其所在模块的所有功能菜单
	 * @param actionName
	 * @return
	 */
	public List<SModule> getModuleListByActionName(String action,  Integer userId);
	public List<SModule> getModuleListByActionNameInFilter(String action,String roleModuleFilter);
	public List<SModule> getModuleListByFilter(Integer actionId,String roleModuleFilter);

}
