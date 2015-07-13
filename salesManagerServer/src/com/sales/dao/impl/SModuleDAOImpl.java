package com.sales.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.sales.dao.SModuleDAO;
import com.sales.model.SModule;

/** 
 * @author  
 * @version 创建时间：2013-5-18 下午02:58:31 
 *  
 */
public class SModuleDAOImpl extends SqlMapClientDaoSupport implements SModuleDAO{

	@Override
	public List<SModule> getModuleList(Integer roleId) {
		// TODO Auto-generated method stub
		List moduleList = getSqlMapClientTemplate().queryForList("s_module.getModuleList",roleId);
		return moduleList;
	}

	@Override
	public List<SModule> getModuleListByActionId(Integer actionId) {
		// TODO Auto-generated method stub
		List moduleList = getSqlMapClientTemplate().queryForList("s_module.getModuleListByActionId",actionId);
		return moduleList;
	}

	@Override
	public List<SModule> getModuleListByFilter(Integer actionId,
			String roleModuleFilter) {
		// TODO Auto-generated method stub
		Map<String,Comparable> queryMap = new HashMap<String,Comparable>();
		queryMap.put("actionId", actionId);
		queryMap.put("roleModuleFilter", roleModuleFilter);
		List moduleList = getSqlMapClientTemplate().queryForList("s_module.getModuleListByFilter",queryMap);
		return moduleList;
	}

}
