/**
 * 
 */
package com.sales.workflow;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbpm.api.model.OpenExecution;
import org.jbpm.api.task.Assignable;
import org.jbpm.api.task.AssignmentHandler;

/**
 * @author Administrator
 * 
 */
public class UserAssignHandler implements AssignmentHandler {
	private TaskAssignActorHandler simpleActorHandler;
	private static final String FLOW_ASSIGN_NAME = "AssignedUser";
	private static final String FLOW_SIGN_USERROLES = "AssignedRole";
	private static final String STARTER = "owner";
	private static final String DEPART_ID = "departId";

	private Log logger = LogFactory.getLog(UserAssignHandler.class);
	
	// 工作流配置文件授权角色标识
	private String role;

	/*
	 * 一旦发生异常，可以从异常中获取工作流id和role名，以便进行下一步操作
	 * 如果有多个处理用户，则可以通过TaskAssignActorHandler.getRoleUsers查询用户列表后，
	 * 在提交参数中FLOW_ASSIGN_NAME指定用户，完成指定固定的用户工作流提交。
	 * @caution ：暂不支持多用户会签操作。
	 * 
	 * @see
	 * org.jbpm.api.task.AssignmentHandler#assign(org.jbpm.api.task.Assignable,
	 * org.jbpm.api.model.OpenExecution)
	 */
	@Override
	public void assign(Assignable assignable, OpenExecution execution)
			throws Exception {
		// TODO Auto-generated method stub
		String assignId = (String) execution.getVariable(FLOW_ASSIGN_NAME);
		String startUser = (String) execution.getVariable(STARTER);
		int departId = (Integer) execution.getVariable(DEPART_ID);

		logger.info("assignId:===========>" + assignId);
		logger.info("start user:===========>" + startUser);
		logger.info("start department:===========>" + DEPART_ID);

		// 在表单提交中指定了固定的执行人员
		if (StringUtils.isNotEmpty(assignId)) {
			assignable.setAssignee(assignId);
			return;
		}

		
		//從工作流配置文件動態獲取提交任務的處理人
		simpleActorHandler = new SimpleAssignActorHandler();
		String userNames = simpleActorHandler.getAssignUserName(
				startUser, departId, role);
		if (userNames == null)
			throw(new WorkFlowException("没有找到工作流任务节点处理角色的用户！", execution.getId(), role));
		
		String[] users = userNames.split("[,]");
		if (users != null && users.length > 1) {		// 如果处理用户多于一个人的
			throw(new WorkFlowException("工作流提交节点角色找到多个用户，无法继续流程！", execution.getId(), role));
//			for (String user : users) {
//				assignable.addCandidateUser(user);
//			}
		} else {
			assignable.setAssignee(userNames);
		}
		

	}
}
