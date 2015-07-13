package com.sales.action;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.attributes.RuntimeAttributeRepository;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.opensymphony.xwork2.ActionContext;
import com.sales.common.Constants;
import com.sales.model.SModule;
import com.sales.model.SUser;
import com.sales.service.WorkbenchService;

/**
 * @author Leo
 * 
 */
public class BaseAction implements Serializable,
	    ServletRequestAware,
		ServletResponseAware , SessionAware , ApplicationContextAware{

	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected Map<String, Object> session;
	protected ApplicationContext context;
	
	/**
	 *  页面级别的业务异常信息存储，供页面访问
	 */
	protected String errorMsg;	
	
	protected static Logger actionLog = Logger.getLogger(BaseAction.class);
	
	/**
	 * 	默认构造函数
	 */
	public BaseAction() {
		super();
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
		
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}  
	
	
	@Override
	public void setApplicationContext(ApplicationContext ctx)
			throws BeansException {
		this.context = ctx;
		
	}

	/**
	 * 从session取出当前登录用户信息
	 * @return
	 */
	protected SUser getLoginUser() {
		if (this.session != null) {
			if (this.session.get(Constants.USER_INFO) != null) {
				SUser loginUser = (SUser) this.session.get(Constants.USER_INFO);
				return loginUser;
			}
		}
		return null;
	}
	
	/**
	 * 保存当前登录用户信息到session
	 * @param loginUser
	 */
	protected void setLoginUser(SUser loginUser){
		this.session.put(Constants.USER_INFO, loginUser);
	}

	/**
	 * @return		页面级业务逻辑异常
	 */
	public String getErrorMsg() {
		return errorMsg;
	}

	/**
	 * 获取当前用户会话授权客户id数组
	 * @param permissionMap
	 * @return
	 */
	protected String getPermissionCustomerIds() {
		Map permissionMap = (Map) this.session.get(Constants.USER_PERMISSION_MAP);
		String customerIds = (String) permissionMap.get("customerOwnerFilter");
		return customerIds;
	}

	/**
	 * @return
	 */
	protected String getChildrenUserIds() {
		Map permissionMap = (Map) this.session.get(Constants.USER_PERMISSION_MAP);
		String childUserIds = (String) permissionMap.get("childUserFilter");
		if(childUserIds.equalsIgnoreCase(Constants.USER_FILTER_MARK))
			return null;
		return childUserIds;
	}

	/**
	 * @param json
	 * @throws IOException
	 */
	protected void writeJsonP(JSONObject json) {
		String strJsonp = "callback(" +json.toString() + ")";
		response = (HttpServletResponse) ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE); 
		response.setCharacterEncoding("UTF-8"); 
		try {
			response.getWriter().print(strJsonp);
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param jsonArray
	 * @throws IOException
	 */
	protected void writeJsonP(JSONArray jsonArray) {
		String strJsonp = "callback(" +jsonArray.toString() + ")";
		response = (HttpServletResponse) ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE); 
		response.setCharacterEncoding("UTF-8"); 
		try {
			response.getWriter().print(strJsonp);
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 判断请求是否来自于移动手机终端
	 * @param request
	 * @return
	 */
	protected boolean isFromMobile() {
		String requestFrom = request.getParameter("requestFrom");
		if(requestFrom != null && requestFrom.equalsIgnoreCase("mobile"))
			return true;
		else
			return false;
	}
	
}