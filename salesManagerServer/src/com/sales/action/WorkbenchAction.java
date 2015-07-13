package com.sales.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;

import com.opensymphony.xwork2.ActionContext;
import com.sales.common.Constants;
import com.sales.model.SAction;
import com.sales.model.SModule;
import com.sales.model.SRoleAction;
import com.sales.model.SUser;
import com.sales.service.ActionService;
import com.sales.service.InformationService;
import com.sales.service.RoleActionService;
import com.sales.service.WorkbenchService;

/** 
 * @author  
 * @version 创建时间：2013-5-21 下午02:47:19 
 *  
 */
public class WorkbenchAction extends BaseAction {
	
	private static final long serialVersionUID = 466501280539092004L;
	private List<SModule> moduleList;
	private Integer actionId;
	private String moduleIds;
	private int infoCount;
	
	private Integer moduleId;
	private String moduleNameEnglish;
	private String moduleNameChinese;
	private String moduleUrl;
	private List<SModule> userModuleList;
	private List<SRoleAction> roleActionList;
	private RoleActionService roleActionService;
	private InformationService informationService;
	private List<SAction> actionList;
	private ActionService actionService;
	
	public List<SAction> getActionList() {
		return actionList;
	}

	public void setActionList(List<SAction> actionList) {
		this.actionList = actionList;
	}

	public ActionService getActionService() {
		return actionService;
	}

	public void setActionService(ActionService actionService) {
		this.actionService = actionService;
	}

	public List<SModule> getUserModuleList() {
		return userModuleList;
	}

	public int getInfoCount() {
		return infoCount;
	}

	public void setInfoCount(int infoCount) {
		this.infoCount = infoCount;
	}

	public InformationService getInformationService() {
		return informationService;
	}

	public void setInformationService(InformationService informationService) {
		this.informationService = informationService;
	}

	public void setUserModuleList(List<SModule> userModuleList) {
		this.userModuleList = userModuleList;
	}

	public List<SRoleAction> getRoleActionList() {
		return roleActionList;
	}

	public void setRoleActionList(List<SRoleAction> roleActionList) {
		this.roleActionList = roleActionList;
	}

	public RoleActionService getRoleActionService() {
		return roleActionService;
	}

	public void setRoleActionService(RoleActionService roleActionService) {
		this.roleActionService = roleActionService;
	}

	/** 消息提示 */
	private String msg;
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getModuleIds() {
		return moduleIds;
	}

	public void setModuleIds(String moduleIds) {
		this.moduleIds = moduleIds;
	}

	public Integer getModuleId() {
		return moduleId;
	}

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}

	public String getModuleNameEnglish() {
		return moduleNameEnglish;
	}

	public void setModuleNameEnglish(String moduleNameEnglish) {
		this.moduleNameEnglish = moduleNameEnglish;
	}

	public String getModuleNameChinese() {
		return moduleNameChinese;
	}

	public void setModuleNameChinese(String moduleNameChinese) {
		this.moduleNameChinese = moduleNameChinese;
	}

	public String getModuleUrl() {
		return moduleUrl;
	}

	public void setModuleUrl(String moduleUrl) {
		this.moduleUrl = moduleUrl;
	}

	public Integer getActionId() {
		return actionId;
	}

	public void setActionId(Integer actionId) {
		this.actionId = actionId;
	}

	public List<SModule> getModuleList() {
		return moduleList;
	}

	public void setModuleList(List<SModule> moduleList) {
		this.moduleList = moduleList;
	}

	private WorkbenchService workbenchService;

	public WorkbenchService getWorkbenchService() {
		return workbenchService;
	}

	public void setWorkbenchService(WorkbenchService workbenchService) {
		this.workbenchService = workbenchService;
	}
	
	public String workbenchIndex(){
		SUser sUser = (SUser) this.session.get(Constants.USER_INFO);
		this.actionList = this.actionService.getActionList();
		this.moduleList = workbenchService.getModuleListByUserId(sUser.getUserId());
		List infoList = this.informationService.getInfoListByStatus(Constants.INFO_STATUS_NEW, sUser.getUserId());
		this.infoCount = 0;
		if(infoList.size()>0){
			this.infoCount = infoList.size();				
		}
		return "workbenchIndex";
	}
	
	public String setWorkbenchInit(){
		SModule sModule = new SModule();
		SModule uModule = new SModule();
		try{
			SUser sUser = (SUser) this.session.get(Constants.USER_INFO);
			this.actionList = this.actionService.getActionList();
			//this.moduleList = this.workbenchService.getModuleList(sUser.getRoleId());
			this.userModuleList = workbenchService.getModuleListByUserId(sUser.getUserId());
			Map permissionMap = (Map) this.session.get(Constants.USER_PERMISSION_MAP);
			String roleModuleFilter = (String) permissionMap.get("roleModuleFilter");
			SAction sAction = new SAction();
			for(int k = 0; k < actionList.size(); k++ ){
				sAction = actionList.get(k);
				List<SModule> mList;
				//List<SModule> mList = this.workbenchService.getModuleListByActionId(sAction.getActionId());
				if(sUser.getUserId()==1){
					mList = this.workbenchService.getModuleListByActionId(sAction.getActionId());
				}else{
					mList = this.workbenchService.getModuleListByFilter(sAction.getActionId(), roleModuleFilter);
				}
				for (int i = 0; i < mList.size(); i++) {
					sModule = mList.get(i);
					sModule.setIsCheck("nochecked");
					for (int j = 0; j < userModuleList.size(); j++) {
						uModule = userModuleList.get(j);
						int smId = sModule.getModuleId();
						int umId = uModule.getModuleId();
						if(smId == umId){
							sModule.setIsCheck("checked");
							break;
						}
					}	
			    }
				sAction.setModuleList(mList);
			}
//					
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "setWorkbenchInit";
	}
	
	public String setWorkbench(){
		Boolean flag = false;
		try {
			if (null != this.moduleIds && !"".equals(this.moduleIds)) {
				SUser sUser = (SUser) this.session.get(Constants.USER_INFO);
				this.workbenchService.setUserModule(moduleIds, sUser.getUserId());

			}
			response = (HttpServletResponse) ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE); 
			response.setCharacterEncoding("UTF-8"); 
			response.getWriter().print("0"); 
			//this.msgWriter(flag.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String changeList() throws IOException{
		try {
			List<SModule> moduleList = new ArrayList<SModule>();
			SUser sUser = (SUser) this.session.get(Constants.USER_INFO);
			Map permissionMap = (Map) this.session.get(Constants.USER_PERMISSION_MAP);
			String roleModuleFilter = (String) permissionMap.get("roleModuleFilter");
			//moduleList = this.workbenchService.getModuleListByActionId(actionId);
			if(sUser.getUserId()==1){
				moduleList = this.workbenchService.getModuleListByActionId(actionId);
			}else{
				moduleList = this.workbenchService.getModuleListByFilter(actionId, roleModuleFilter);
			}
//			result = true;
//			msgWriter(result.toString());
			JSONArray jsonArray = JSONArray.fromObject(moduleList); 
			 response = (HttpServletResponse) ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE); 
			response.setCharacterEncoding("UTF-8"); 
			response.getWriter().print(jsonArray); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	public String gotoIndex() throws IOException{
		try {
			List<SModule> moduleList = new ArrayList<SModule>();
			SUser sUser = (SUser) this.session.get(Constants.USER_INFO);
			moduleList = workbenchService.getModuleListByUserId(sUser.getUserId());		 
			JSONArray jsonArray = JSONArray.fromObject(moduleList); 
			 response = (HttpServletResponse) ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE); 
			response.setCharacterEncoding("UTF-8"); 
			response.getWriter().print(jsonArray); 
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return null;
	}

}
