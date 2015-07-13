package com.sales.dao;

import java.util.List;

import com.sales.model.SModule;

/** 
 * @author  
 * @version 创建时间：2013-5-18 下午02:58:05 
 *  
 */
public interface SModuleDAO {
	
	public List<SModule> getModuleList(Integer roleId);
	public List<SModule> getModuleListByActionId(Integer actionId);
	public List<SModule> getModuleListByFilter(Integer actionId,String roleModuleFilter);

}
