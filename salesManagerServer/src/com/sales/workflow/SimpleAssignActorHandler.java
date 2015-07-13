/**
 * 
 */
package com.sales.workflow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Administrator
 *
 */
public class SimpleAssignActorHandler implements TaskAssignActorHandler {
	private static Log logger = LogFactory.getLog(UserAssignHandler.class);
	
	private static HashMap<String, String> userRoleMap;
	
	/**
	 *  模拟角色用户数据的加载
	 */
	public SimpleAssignActorHandler() {
		super();
		userRoleMap = new HashMap<String, String>();
		userRoleMap.put("users", "goulin");
		userRoleMap.put("manager", "like,wanghong");
		userRoleMap.put("boss", "zhangdx");
	}

	/* (non-Javadoc)
	 * @see com.sales.workflow.TaskAssignActorHandler#getAssignUserName(int, int, java.lang.String)
	 */
	@Override
	public String getAssignUserName(String startUser, int startDepartmentId,
			String wfRoleName) {
		String role = convertRoleName(wfRoleName);
		int userId = 0;
		
		String assignUsers = getAssignUsers(role, startDepartmentId);
		
		return assignUsers;
	}
	
	
	
	
	/* (non-Javadoc)
	 * @see com.sales.workflow.TaskAssignActorHandler#getRoleUsers(java.lang.String, int, java.lang.String)
	 */
	@Override
	public List<String> getRoleUsers(String startUser, int startDepartmentId,
			String wfRoleName) {
		
		String role = convertRoleName(wfRoleName);
		
		String assignUsers = getAssignUsers(role, startDepartmentId);
		List<String> users = new ArrayList<String>();
		if(assignUsers != null) {
			String us[] = assignUsers.split(",");
			for (String u : us) {
				users.add(u);
			}
		}
		
		return users;
	}

	/**
	 * @param wfRoleName	格式 ${wfRoleName}
	 * @return
	 */
	private String convertRoleName(String wfRoleName) {
		int start = 0;
		int end = 0;
		
		start = wfRoleName.indexOf("${");
		if(start >= 0) {
			end = wfRoleName.indexOf("}");
			if(end > 2)
				return wfRoleName.substring(2, end);
		}
		
		return null;
	}

	/**
	 * @param roleName
	 * @return
	 */
	private String getAssignUsers(String roleName, int departId) {
		
		//不同部门有不同的Manager演示
		String users = userRoleMap.get(roleName);
		if(users != null && roleName.equalsIgnoreCase("manager")) {
			String us[] = users.split(",");
			users = us[departId-1];
		}
		
		return users;
	}
	
}
