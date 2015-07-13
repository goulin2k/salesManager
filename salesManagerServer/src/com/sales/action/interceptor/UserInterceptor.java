/**
 * 
 */
package com.sales.action.interceptor;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.apache.struts2.StrutsStatics;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.sales.common.Constants;
import com.sales.common.NormalFun;
import com.sales.model.SModule;
import com.sales.model.SUser;
import com.sales.service.InformationService;
import com.sales.service.WorkbenchService;

/**
 * @author Leo
 *
 */
public class UserInterceptor extends MethodFilterInterceptor {
	private static Logger actionLog = Logger.getLogger("UserInterceptor");
	
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.interceptor.MethodFilterInterceptor#doIntercept(com.opensymphony.xwork2.ActionInvocation)
	 */
	@Override
	protected String doIntercept(ActionInvocation invocation) {
		try {
            String dispatchPath = null;
			
			String classname = invocation.getAction().getClass()
					.getCanonicalName();
			String methodname = classname + "."
					+ invocation.getProxy().getMethod();
			
			ActionContext ac = invocation.getInvocationContext().getContext();
			HttpServletRequest request = (HttpServletRequest) ac
					.get(StrutsStatics.HTTP_REQUEST);
			HttpServletResponse response = (HttpServletResponse) ac
					.get(StrutsStatics.HTTP_RESPONSE);
			ac.getSession().remove("errorMsg");
			
			ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext((ServletContext) ac
					.get(StrutsStatics.SERVLET_CONTEXT));
			
			Map<String, Object> session = ac.getSession();
			SUser user = (SUser) session.get(Constants.USER_INFO);
			
			if (user == null) {
				ac.getSession().put(Constants.SESSION_ERROR_MSG, 
						"用户未登录或上次登录的凭据已失效.");
				String path = request.getContextPath();
				response.sendRedirect(path + "/index.jsp");
								
				return "unAuthriseUser";
				
			} else {
				//跳转到功能页面
				String actionName = getActionName(request);
				request.setAttribute("moduleList", getModuleList(context, actionName,user));
			}
			
			dealwithInfoStatus(request, user.getUserId(), context);
			dispatchPath = invocation.invoke();
			
			return dispatchPath;

		} catch (Exception e) {

			// 输出log信息
			actionLog.error(e.getMessage(), e);

			// 其他情况，跳转到系统错误页面
			return "sysError";
		}
	}
	
	private String getFullActionName(HttpServletRequest request) {
		String url = request.getRequestURL().toString();
		int i = url.lastIndexOf("/");
		String action = "";
		action = url.substring(i+1);
		
		return action;
	}
	
	/**
	 * 
	 * @return
	 */
	private String getActionName(HttpServletRequest request) {
		String modle;
		String url = request.getRequestURL().toString();
		int i = url.lastIndexOf("/");
		String action = "";
		action = url.substring(i+1);
		int j = url.lastIndexOf("!");
		if (j>0)
			modle = url.substring(i+1, j);
		else
			modle = url.substring(i+1);
		
		//actionLog.log(Priority.INFO, modle);
		if (!action.equalsIgnoreCase("info!msgPush2")&&
				!action.equalsIgnoreCase("news!jsonNews")
				&& !action.equalsIgnoreCase("info!jsonInfos"))
			request.getSession().setAttribute("action", action);
		return modle;
	}
	
	private String getFunctionName(HttpServletRequest request) {
		String functionName = "";
		String url = request.getRequestURL().toString();
		int i = url.lastIndexOf('!');
		if(i>0)
			functionName = url.substring(i+1);
		return functionName;
	}

	/** 
	 * @param actionName
	 * @return
	 */
	private List<SModule> getModuleList(ApplicationContext context, String actionName, SUser loginUser) {
		if(loginUser == null)
				return null;
		if(actionName.equalsIgnoreCase("pproduct"))
			actionName = "customer";
		WorkbenchService workbenchService = 
				(WorkbenchService)context.getBean("workbenchService");
		List<SModule> moduleList = workbenchService.getModuleListByActionName(
				actionName, loginUser.getUserId());
		return moduleList;
	}
	
	/**
	 * 打开流程页面，处理oa流程消息为已读
	 * @param request
	 */
	private void dealwithInfoStatus(HttpServletRequest request, int userId, ApplicationContext context ) {
		String url = request.getRequestURL().toString();
		String actionName = getActionName(request);
		String functionName = getFunctionName(request);
		
		if(functionName.trim().equalsIgnoreCase("showProcessPic"))
			return;
		
		if(actionName.equalsIgnoreCase("process")) {
			Integer taskId = null;
			Integer id = null;
			String sTaskId = (String)request.getAttribute("taskId");
			String sId = (String)request.getAttribute("id");
			
			actionLog.warn("dealwithInfoStatus() \t" + actionName + "\t" + url);
			actionLog.warn(request.getAttribute("taskId"));
			actionLog.warn(request.getAttribute("id"));
			
			if(NormalFun.trimNull(sTaskId) != null)
				taskId = new Integer(sTaskId);
			if(NormalFun.trimNull(sId) != null)
				id = new Integer(sId);
			
			String action = getFullActionName(request);
			url = action;
			if(taskId != null)
				url = url + "?" + "taskId=" + taskId.toString();
			if(id != null)
				url = url + "&" + "id=" + id.toString();
			
			//修改指定url的状态为已读
			updateInfoStatus(url, userId, context);
		}
	}
	
	private void updateInfoStatus(String url, int userId, ApplicationContext context ){
		InformationService informationService = 
				(InformationService)context.getBean("informationService");
		informationService.updateInfo(url, userId, Constants.INFO_STATUS_READE);
	}
}
