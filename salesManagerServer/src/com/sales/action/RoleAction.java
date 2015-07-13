package com.sales.action;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.sales.common.Constants;
import com.sales.model.SAction;
import com.sales.model.SModule;
import com.sales.model.SRole;
import com.sales.model.SRoleAction;
import com.sales.model.SUser;
import com.sales.service.ActionService;
import com.sales.service.RoleActionService;
import com.sales.service.RoleService;
import com.sales.service.WorkbenchService;

/** 
 * @author  
 * @version 创建时间：2013-5-31 下午05:11:41 
 *  
 */
public class RoleAction extends BaseAction{
	
	private SRole sRole;
	private String actionIds;
	private Integer roleId;
	private String name;
	private String comment;
	private Boolean customerChecked;
	private String moduleIds;
	
	public String getModuleIds() {
		return moduleIds;
	}

	public void setModuleIds(String moduleIds) {
		this.moduleIds = moduleIds;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	private RoleService roleService;
	private ActionService actionService;
	private List<SRole> roleList;
	private List<SAction> actionList;
	private RoleActionService roleActionService;
	private List<SModule> moduleList;
	private WorkbenchService workbenchService;

	public List<SModule> getModuleList() {
		return moduleList;
	}

	public void setModuleList(List<SModule> moduleList) {
		this.moduleList = moduleList;
	}

	public WorkbenchService getWorkbenchService() {
		return workbenchService;
	}

	public void setWorkbenchService(WorkbenchService workbenchService) {
		this.workbenchService = workbenchService;
	}

	public SRole getsRole() {
		return sRole;
	}

	public RoleActionService getRoleActionService() {
		return roleActionService;
	}

	public void setRoleActionService(RoleActionService roleActionService) {
		this.roleActionService = roleActionService;
	}

	public ActionService getActionService() {
		return actionService;
	}

	public void setActionService(ActionService actionService) {
		this.actionService = actionService;
	}

	public List<SAction> getActionList() {
		return actionList;
	}

	public void setActionList(List<SAction> actionList) {
		this.actionList = actionList;
	}

	public void setsRole(SRole sRole) {
		this.sRole = sRole;
	}

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public String getActionIds() {
		return actionIds;
	}

	public void setActionIds(String actionIds) {
		this.actionIds = actionIds;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String roleList(){
		this.roleList = this.roleService.getRoleList();
		return "roleList";
	}
	
	public List<SRole> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<SRole> roleList) {
		this.roleList = roleList;
	}
	
	public String addRoleInit(){
		return "addRoleInit";
	}

	public String addRole(){
		sRole = new SRole();
		sRole.setName(name);
		sRole.setComment(comment);
		sRole.setCustomerChecked(customerChecked);
		sRole.setStatus(1);
		this.roleService.insertRole(sRole);
		return roleList();
	}
	
	public String getRoleAction(){
		//SAction action = new SAction();
		SRoleAction roleAction = new SRoleAction();
		SModule sModule = new SModule();
		try {
			this.actionList = this.actionService.getActionList();
			List<SRoleAction> sRAList = this.roleActionService.getRoleActionListByRoleId(roleId);
//			for (int i = 0; i < actionList.size(); i++) {
//				action = actionList.get(i);
//				action.setIsCheck("nochecked");
//				for (int j = 0; j < sRAList.size(); j++) {
//					roleAction = sRAList.get(j);
//					int aId = action.getActionId();
//					int raId = roleAction.getActionId();
//					if(aId==raId){						
//						action.setIsCheck("checked");
//						break;
//					}
//				}
//				
//			}
			SAction sAction = new SAction();
			for(int k = 0; k < actionList.size(); k++ ){
				sAction = actionList.get(k);
				List<SModule> mList = this.workbenchService.getModuleListByActionId(sAction.getActionId());
				for (int i = 0; i < mList.size(); i++) {
					sModule = mList.get(i);
					sModule.setIsCheck("nochecked");
					for (int j = 0; j < sRAList.size(); j++) {
						roleAction = sRAList.get(j);
						int umId = 0;
						if(roleAction.getMethod()!=null){
							umId = Integer.parseInt(roleAction.getMethod());
						}
						int smId = sModule.getModuleId();
						if(smId == umId){
							sModule.setIsCheck("checked");
							break;
						}
					}	
			    }
				sAction.setModuleList(mList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "getRoleAction";
	}
	
	public String setRoleAction(){
			try {
//				if (null != this.actionIds && !"".equals(this.actionIds)) {
//					this.roleActionService.setRoleAction(actionIds, this.roleId);
//					//flag = this.roleActionService.addActionBatch(ids, rId);
//
//				}
				if (null != this.moduleIds && !"".equals(this.moduleIds)) {
					this.roleActionService.setRoleModule(moduleIds, this.roleId);
					//flag = this.roleActionService.addActionBatch(ids, rId);

				}
				response = (HttpServletResponse) ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE); 
				response.setCharacterEncoding("UTF-8"); 
				response.getWriter().print("0");

			} catch (Exception e) {
				e.printStackTrace();
			}

		return null;
	}
	
	public String removeRole() {
			try {
				//int result = this.roleService.delete(roleId);
				return roleList();

			} catch (Exception e) {

				e.printStackTrace();
			}
		return roleList();

	}

}
