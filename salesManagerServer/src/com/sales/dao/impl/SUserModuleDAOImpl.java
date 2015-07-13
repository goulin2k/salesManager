package com.sales.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.sales.dao.SUserModuleDAO;
import com.sales.model.SModule;

/** 
 * @author  
 * @version 创建时间：2013-5-18 下午03:12:40 
 *  
 */
public class SUserModuleDAOImpl extends SqlMapClientDaoSupport implements SUserModuleDAO{

	@Override
	public List<SModule> getModuleListByUserId(Integer userId) {
		// TODO Auto-generated method stub
		List userModuleList = getSqlMapClientTemplate().queryForList("s_user_module.getModuleListByUserId",userId);
		return userModuleList;
	}
	
	

	/* (non-Javadoc)
	 * @see com.sales.dao.SUserModuleDAO#getModuleListByActionName(java.lang.String)
	 */
	@Override
	public List<SModule> getModuleListByActionName(String acitonName, Integer userId) {
		Map tempMap = new HashMap();
		tempMap.put("actionName",acitonName);
		tempMap.put("userId", userId);
		List userModuleList = getSqlMapClientTemplate().queryForList("s_module.getModuleListByActionName", 
				tempMap);
		return userModuleList;
	}



	@Override
	public void addUserModule(Integer moduleId, Integer userId) {
		// TODO Auto-generated method stub
		Map tempMap = new HashMap();
		tempMap.put("moduleId",moduleId);
		tempMap.put("userId", userId);
		getSqlMapClientTemplate().insert("s_user_module.insertUserModule", tempMap);		
	}

	@Override
	public void deleteModuleByUserId(Integer userId) {
		// TODO Auto-generated method stub	
		getSqlMapClientTemplate().delete("s_user_module.deleteModuleByUserId",userId);
	}



	@Override
	public List<SModule> getModuleListByActionNameInFilter(String acitonName,
			String roleModuleFilter) {
		// TODO Auto-generated method stub
		Map tempMap = new HashMap();
		tempMap.put("acitonName",acitonName);
		tempMap.put("roleModuleFilter", roleModuleFilter);
		List userModuleList = getSqlMapClientTemplate().queryForList("s_module.getModuleListByActionNameInFilter",tempMap);
		return userModuleList;
	}

}
