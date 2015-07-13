package com.sales.dao;

import java.util.List;

import com.sales.model.SModule;

/** 
 * @author  
 * @version 创建时间：2013-5-18 下午03:12:17 
 *  
 */
public interface SUserModuleDAO {
	
	public List<SModule> getModuleListByUserId(Integer userId);
	
	/**
	 * 根据Action获取其所在模块的所有功能菜单
	 * @param acitonName
	 * @return
	 */
	public List<SModule> getModuleListByActionName(String acitonName, Integer userId); 
	
	public void deleteModuleByUserId(Integer userId);
	
	public void addUserModule(Integer moduleId, Integer userId);
	
	public List<SModule> getModuleListByActionNameInFilter(String action,String roleModuleFilter);

}
