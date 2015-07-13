/**
 * 
 */
package com.sales.workflow;

import java.util.List;

/**
 * 实现工作流中任务指定角色-到指定用户关系的处理
 * @author Administrator
 *
 */
public interface TaskAssignActorHandler {

	/**
	 * @param startUser	工作流发起人用户名
	 * @param startDepartmentId	工作流发起人所在部门id
	 * @param roleName		工作流中定义的处理角色，格式:${roleName}
	 * @return
	 */
	public String getAssignUserName(String startUser,
				int startDepartmentId, String wfRoleName);
	
	
	/**  如果一个审批节点有多个用户，返回该节点的用户列表，供提交选择
	 * @param startUser				工作流发起人用户名
	 * @param startDepartmentId		工作流发起人所在部门id
	 * @param wfRoleName		工作流中定义的处理角色，格式:${roleName}
	 * @return
	 */
	public List<String> getRoleUsers(String startUser,
			int startDepartmentId, String wfRoleName);
}
