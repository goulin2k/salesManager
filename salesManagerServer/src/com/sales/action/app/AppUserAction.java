/**
 * 
 */
package com.sales.action.app;

import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.sales.action.BaseAction;
import com.sales.common.Constants;
import com.sales.common.MD5;
import com.sales.model.BaseObject;
import com.sales.model.SUser;
import com.sales.service.InformationService;
import com.sales.service.PermissionService;
import com.sales.service.SUserService;

/**
 * @author apple
 *
 */
public class AppUserAction extends BaseAction {
	private SUserService userService;
	private PermissionService permissionService;
	private InformationService informationService;
	
	/**
	 * @param userService the userService to set
	 */
	public void setUserService(SUserService userService) {
		this.userService = userService;
	}
	
	

	/**
	 * @param informationService the informationService to set
	 */
	public void setInformationService(InformationService informationService) {
		this.informationService = informationService;
	}



	/**
	 * @param permissionService the permissionService to set
	 */
	public void setPermissionService(PermissionService permissionService) {
		this.permissionService = permissionService;
	}



	/**
	 * 用户登录接口
	 * @return
	 */
	public String login() {
		String userName = "";
		String password = "";
		
		userName = request.getParameter("username");
		password = request.getParameter("password");
		LoginResult loginResult = new LoginResult();
		try {
			SUser userInfo = new SUser();
			MD5 md5 = new MD5();
			String md5PW = md5.getMD5ofStr(password);
			userInfo.setLoginName(userName);
			userInfo.setLoginPasswor(md5PW);
			
			boolean res = userService.userLogin(userInfo);			
			if(res) {
				SUser user = userService.getSUserByUserName(userName);
				Map permissionMap = permissionService.getPermissionByUserId(user);
				List infoList = informationService.getInfoListByStatus(
						Constants.INFO_STATUS_NEW, user.getUserId());
				
				loginResult.setResult(LoginResult.SUCCESS);
				loginResult.setDepartment(user.getDepartmentName());
				loginResult.setName(user.getUserName());
				loginResult.setRoleId(user.getRoleId());
				loginResult.setUserId(user.getUserId());
				loginResult.setMobile(user.getMobile());
				loginResult.setRoleName(user.getRoleName());
				
				session.put(Constants.USER_INFO, user);
				session.put(Constants.USER_PERMISSION_MAP, permissionMap);
				if(session.containsKey(Constants.SESSION_ERROR_MSG))
					session.remove(Constants.SESSION_ERROR_MSG);
			}else {
				loginResult.setResult(LoginResult.INVALID_USERORPWD);
			}
			
			JSONObject json = JSONObject.fromObject(loginResult);
			String str = "callback(" +json.toString() + ")";
			
			response = (HttpServletResponse) ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE); 
			response.setCharacterEncoding("UTF-8"); 
			response.getWriter().print(str);
		} catch (Exception e) {
			actionLog.error(e.getMessage());
			e.printStackTrace();
			loginResult.setResult(LoginResult.OTHER_EXCEPTION);
			loginResult.setError(e.getMessage());
			JSONObject json = JSONObject.fromObject(loginResult);
			this.writeJsonP(json);
		}
		return null;
	}
	
	/**
	 * @return
	 */
	public String logout() {
		this.session.remove(Constants.USER_INFO);
		this.session.clear();
        
		Cookie[] cookies = this.request.getCookies();
		for (int i = 0; i < cookies.length; i++) {
			if ("cookieLoginFlag".equals(cookies[i].getName())
			        && cookies[i].getValue() != null) {
				cookies[i].setValue(null);
				this.response.addCookie(cookies[i]);
			    
			} else if ("cookiePassword".equals(cookies[i].getName())
					&& cookies[i].getValue() != null) {
				cookies[i].setValue(null);
				this.response.addCookie(cookies[i]);
			} 
			
		}
		
		LoginResult result= new LoginResult();
		result.setResult(1);
		JSONObject json = JSONObject.fromObject(result);
		
		writeJsonP(json);
		
		return null;
	}
	
	/**
	 * 查询获取所有的销售人人员
	 * @return
	 */
	public String getSalers() {
		List userList = userService.getUserList(1L, 50);
		JSONArray json = JSONArray.fromObject(userList);
		
		writeJsonP(json);
		
		return null;
		
	}
	
	public class LoginResult extends BaseObject {
		public static final int SUCCESS = 1;
		public static final int INVALID_USERORPWD = 0;
		public static final int OTHER_EXCEPTION = -1;
		
		private int result;
		private String error;
		
		private int userId;
		private String name;
		private int roleId;
		private String roleName;
		private String department;
		private String mobile;
		
		
		
		/**
		 * @return the roleName
		 */
		public String getRoleName() {
			return roleName;
		}
		/**
		 * @param roleName the roleName to set
		 */
		public void setRoleName(String roleName) {
			this.roleName = roleName;
		}
		/**
		 * @return the mobile
		 */
		public String getMobile() {
			return mobile;
		}
		/**
		 * @param mobile the mobile to set
		 */
		public void setMobile(String mobile) {
			this.mobile = mobile;
		}
		public int getResult() {
			return result;
		}
		public void setResult(int result) {
			this.result = result;
		}
		public String getError() {
			return error;
		}
		public void setError(String error) {
			this.error = error;
		}
		public int getUserId() {
			return userId;
		}
		public void setUserId(int userId) {
			this.userId = userId;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getRoleId() {
			return roleId;
		}
		public void setRoleId(int roleId) {
			this.roleId = roleId;
		}
		public String getDepartment() {
			return department;
		}
		public void setDepartment(String department) {
			this.department = department;
		}
		
		
	}

}
