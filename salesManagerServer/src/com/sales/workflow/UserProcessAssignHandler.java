/**
 * 
 */
package com.sales.workflow;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.jbpm.api.Configuration;
import org.jbpm.api.model.OpenExecution;
import org.jbpm.api.task.Assignable;
import org.jbpm.api.task.AssignmentHandler;
import org.jbpm.api.task.Task;
import org.springframework.context.ApplicationContext;

import com.sales.common.Constants;
import com.sales.common.MyApplicationContextUtil;
import com.sales.dao.SInformationDAO;
import com.sales.model.SInformation;
import com.sales.model.SPosition;
import com.sales.model.SUser;
import com.sales.service.SUserService;

/**
 * @author Leo
 *
 */
public class UserProcessAssignHandler implements AssignmentHandler {

	// 工作流配置文件授权角色标识
	private String role;
	
	private static final String FLOW_ASSIGN_NAME = "AssignedUser";
	
	private static final String STARTER = "userId";
	
	@Override
	public void assign(Assignable assignable, OpenExecution execution) throws Exception {
		String assignId = (String) execution.getVariable(FLOW_ASSIGN_NAME);
		Integer userId = Integer.valueOf((String) execution.getVariable(STARTER));
		String name = (String)execution.getVariable("userName");
		String type = (String)execution.getVariable("typeName");
		String dept = (String)execution.getVariable("deptName");
		
		// 在表单提交中指定了固定的执行人员
		if (StringUtils.isNotEmpty(assignId)) {
			assignable.setAssignee(assignId);
			return;
		}
		
		List<SUser> userList = this.getUserListByPosition(this.role,userId);
		if (userList == null || userList.size() == 0) {
			throw(new WorkFlowException("there is not user.", execution.getId(), role));
		}
		if (userList != null && userList.size() > 1) {		// 如果处理用户多于一个人的
			throw(new WorkFlowException("user is not unique.", execution.getId(), role));
//			for (String user : users) {
//				assignable.addCandidateUser(user);
//			}
		} else {
			assignable.setAssignee(String.valueOf(userList.get(0).getUserId()));
			if (userId.intValue() != userList.get(0).getUserId().intValue()) {
				//消息通知审批人
				String processInstanceId = execution.getProcessInstance().getId();
				List<Task> myTaskList = Configuration.getProcessEngine().getTaskService().findPersonalTasks(String.valueOf(userList.get(0).getUserId()));
				String taskId = "";
				for(Task task: myTaskList) {
					if (task.getExecutionId().equals(processInstanceId)) {
						taskId = task.getId();
						break;
					}
				}
				String link = "";
				if (processInstanceId.startsWith("leave.")) {
			    	link = "process!toApprovalLeave?taskId="+taskId+"&id="+processInstanceId.substring(processInstanceId.indexOf(".")+1);
				} else if (processInstanceId.startsWith("overtime.")) {
					link = "process!toApprovalOvertime?taskId="+taskId+"&id="+processInstanceId.substring(processInstanceId.indexOf(".")+1);
				} else if (processInstanceId.startsWith("tripApp.")) {
					link = "process!toApprovalTrip?taskId="+taskId+"&id="+processInstanceId.substring(processInstanceId.indexOf(".")+1);
				} else if (processInstanceId.startsWith("expenseApp.")) {
					link = "process!toApprovalEa?taskId="+taskId+"&id="+processInstanceId.substring(processInstanceId.indexOf(".")+1);
				} else if (processInstanceId.startsWith("expenseRei.")) {
					link = "process!toApprovalEr?taskId="+taskId+"&id="+processInstanceId.substring(processInstanceId.indexOf(".")+1);
				}  else if (processInstanceId.startsWith("carRepairApp.")) {
					link = "process!toApprovalCarApp?taskId="+taskId+"&id="+processInstanceId.substring(processInstanceId.indexOf(".")+1);
				} else if (processInstanceId.startsWith("carRepairRei.")) {
					link = "process!toApprovalCarRei?taskId="+taskId+"&id="+processInstanceId.substring(processInstanceId.indexOf(".")+1);
				} 
				this.insertInfor(dept+"的"+name+"提交了"+type+",需要您审批。", userList.get(0).getUserId(), "流程审批", link);
			}
		}
	}

	private List<SUser> getUserListByPosition(String role,Integer userId) {
		ApplicationContext context = MyApplicationContextUtil.getContext();
		SUserService userService = (SUserService) context.getBean("sUserService");
		String positionName = this.convertRoleName(role);
		List<SUser> userList = new ArrayList<SUser>();
		if ("发起人".equals(positionName)) {
			SUser user = new SUser();
			user.setUserId(userId);
			userList.add(user);
		}else if ("上级主管".equals(positionName)) {
			SUser user = userService.getUserById(userId);
			if (user.getParentUserId() != null) {
				SUser parentUser = new SUser();
				parentUser.setUserId(user.getParentUserId());
				userList.add(parentUser);
			}
		}else if ("分管副总".equals(positionName)) {
			SUser user = userService.getUserById(userId);
			if (user.getParentUserId() != null) {
				SUser upUser = userService.getUserById(user.getParentUserId());
				if ("副总经理".equals(upUser.getPositionOrgName())) {
					userList.add(upUser);
				} else {
					if(upUser.getParentUserId() != null) {
//						SUser ppUser = new SUser();
//						ppUser.setUserId(upUser.getParentUserId());
//						userList.add(ppUser);
						if ("副总经理".equals(upUser.getPositionOrgName())) {
							SUser ppUser = userService.getUserById(upUser.getParentUserId());
							userList.add(ppUser);
						}
					}
				}
				
			}
		} else {
			SPosition position = new SPosition();
			position.setName(positionName);
			userList = userService.getUserListByPosition(position);			
		}
		return userList;
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
	 * 消息通知审批人
	 * @return
	 */
	private void insertInfor(String content, Integer sendUserId, String title, String url){
		ApplicationContext context = MyApplicationContextUtil.getContext();
		SInformationDAO informationDao = (SInformationDAO) context.getBean("informationDao");
		SInformation infor = new SInformation();
		infor.setAddTime(new Timestamp(System.currentTimeMillis()));
		infor.setContent(content);
		infor.setSendUserId(sendUserId);
		infor.setStatus(Constants.INFO_STATUS_NEW);
		infor.setTitle(title);
		infor.setType(SInformation.BuzType.PROCESS_ASS.ordinal());
		infor.setImageUrl(url);
		infor.setBuzId(null);
		informationDao.insert(infor);
	}
	
}
