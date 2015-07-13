package com.sales.action;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;

import com.sales.common.Constants;
import com.sales.common.MD5;
import com.sales.common.NormalFun;
import com.sales.common.WorkCalendar;
import com.sales.model.K3SalerQuota;
import com.sales.model.SAction;
import com.sales.model.SModule;
import com.sales.model.SRoleAction;
import com.sales.model.SUser;
import com.sales.service.ActionService;
import com.sales.service.InformationService;
import com.sales.service.K3ICSaleStatisticsService;
import com.sales.service.K3OrderStatisticsService;
import com.sales.service.K3RecieveBillStatisticsService;
import com.sales.service.K3SalerQuotaService;
import com.sales.service.PermissionService;
import com.sales.service.RoleActionService;
import com.sales.service.SUserService;
import com.sales.service.WorkbenchService;

/** 
 * @author  
 * @version 创建时间：2013-5-13 下午09:44:17 
 *  
 */
public class LoginAction extends BaseAction{
	
	
	private String userName;
	private String userPwd;
	private String validateCode;
	private int infoCount;
	private String autoLoginFlag;
	
	private List<SModule> moduleList;
	private List<SRoleAction> roleActionList;
	private List<SAction> actionList;
	
	private SUserService sUserService;
	private WorkbenchService workbenchService;
	private RoleActionService roleActionService;
	private InformationService informationService;
	private PermissionService permissionService;
	private ActionService actionService;
	
	private K3OrderStatisticsService statisticsOrderService;
	private K3RecieveBillStatisticsService statisticsRecieveService;
	private K3ICSaleStatisticsService statisticsICSaleService;
	private K3SalerQuotaService quotaService;
	
	/**
	 * @param statisticsICSaleService the statisticsICSaleService to set
	 */
	public void setStatisticsICSaleService(
			K3ICSaleStatisticsService statisticsICSaleService) {
		this.statisticsICSaleService = statisticsICSaleService;
	}
	
	
	/**
	 * @param statisticsService the statisticsService to set
	 */
	public void setStatisticsOrderService(K3OrderStatisticsService statisticsService) {
		this.statisticsOrderService = statisticsService;
	}
	/**
	 * @return the statisticsRecieveService
	 */
	public K3RecieveBillStatisticsService getStatisticsRecieveService() {
		return statisticsRecieveService;
	}
	/**
	 * @param statisticsRecieveService the statisticsRecieveService to set
	 */
	public void setStatisticsRecieveService(
			K3RecieveBillStatisticsService statisticsRecieveService) {
		this.statisticsRecieveService = statisticsRecieveService;
	}
	/**
	 * @param quotaService the quotaService to set
	 */
	public void setQuotaService(K3SalerQuotaService quotaService) {
		this.quotaService = quotaService;
	}
	public int getInfoCount() {
		return infoCount;
	}
	public void setInfoCount(int infoCount) {
		this.infoCount = infoCount;
	}
	public List<SModule> getModuleList() {
		return moduleList;
	}
	public void setModuleList(List<SModule> moduleList) {
		this.moduleList = moduleList;
	}

	
	
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
	public List<SRoleAction> getRoleActionList() {
		return roleActionList;
	}
	public void setRoleActionList(List<SRoleAction> roleActionList) {
		this.roleActionList = roleActionList;
	}

	
	
	public PermissionService getPermissionService() {
		return permissionService;
	}
	public void setPermissionService(PermissionService permissionService) {
		this.permissionService = permissionService;
	}
	public InformationService getInformationService() {
		return informationService;
	}
	public void setInformationService(InformationService informationService) {
		this.informationService = informationService;
	}
	public RoleActionService getRoleActionService() {
		return roleActionService;
	}
	public void setRoleActionService(RoleActionService roleActionService) {
		this.roleActionService = roleActionService;
	}
	public WorkbenchService getWorkbenchService() {
		return workbenchService;
	}
	public void setWorkbenchService(WorkbenchService workbenchService) {
		this.workbenchService = workbenchService;
	}
	public SUserService getsUserService() {
		return sUserService;
	}
	public void setsUserService(SUserService sUserService) {
		this.sUserService = sUserService;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getValidateCode() {
		return validateCode;
	}
	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}
	
	public String validateCode() {
		return "validateCode";
	}
	
	public String userLogin() {
		String resultForward = "workbench";
		try {
			SUser userInfo = new SUser();
			
			MD5 md5 = new MD5();
			String md5PW = md5.getMD5ofStr(userPwd);
			userInfo.setLoginName(userName);
			userInfo.setLoginPasswor(md5PW);
			boolean temp = sUserService.userLogin(userInfo);
			
			if(temp == true){
				SUser sUser = sUserService.getSUserByUserName(userName);
//				this.roleActionList = this.roleActionService.getRoleActionListByRoleId(sUser.getRoleId());
				this.actionList = this.actionService.getActionList();
				this.moduleList = workbenchService.getModuleListByUserId(sUser.getUserId());
				List infoList = this.informationService.getInfoListByStatus(Constants.INFO_STATUS_NEW, sUser.getUserId());
				Map permissionMap = this.permissionService.getPermissionByUserId(sUser);
				this.infoCount = 0;
				if(infoList.size()>0){
					this.infoCount = infoList.size();
				}
				session.put("infoCount", infoCount);
				resultForward = "workbench";
				this.session.put(Constants.USER_INFO, sUser);
				this.session.put(Constants.USER_PERMISSION_MAP, permissionMap);
				if(session.containsKey(Constants.SESSION_ERROR_MSG))
					session.remove(Constants.SESSION_ERROR_MSG);
				
				setUserIndicator(sUser);
				setLoginInfoCookie();
			}else{
				session.put(Constants.SESSION_ERROR_MSG, "登录用户名或密码错误.");
				//this.errorMsg = "登录用户名或密码错误.";
				resultForward = "fail";
			}
		}catch(Exception e) {
			resultForward = "fail";
			e.printStackTrace();
			actionLog.error(e.getMessage());
		}
		return resultForward;
		
	}
	/**
	 * 设置用户登录Cookie
	 */
	private void setLoginInfoCookie() {
		//将登录账号密码放入cookie
		Cookie cookieUserName = new Cookie("cookieUserName",this.userName);
		cookieUserName.setMaxAge(60*60*24*365);
		this.response.addCookie(cookieUserName);
		
		Cookie cookieAutoLoginFlag = new Cookie("cookieLoginFlag",this.autoLoginFlag);
		cookieAutoLoginFlag.setMaxAge(60*60*24*30);
		this.response.addCookie(cookieAutoLoginFlag);
			
		Cookie cookiePassword = new Cookie("cookiePassword",this.userPwd);
		cookiePassword.setMaxAge(60*60*24*30);
		this.response.addCookie(cookiePassword);
	}
	
	/**
	 * @param user
	 */
	private void setUserIndicator(SUser user) {
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			int userId = user.getUserId();
			int year = calendar.get(Calendar.YEAR);
			int quarter = WorkCalendar.getQuarter(new Date());
			int month = calendar.get(Calendar.MONTH) + 1;
			int day = calendar.get(Calendar.DAY_OF_MONTH);
			
			Double yearOrderTarget = quotaService.queryThisYear(year, userId, 
					K3SalerQuota.QUOTA_CLASS_ORDER); 
			Double quarterOrderTarget = quotaService.queryThisQuarter(year, 
					quarter, userId, K3SalerQuota.QUOTA_CLASS_ORDER);
			Double yearRecieveTarget = quotaService.queryThisYear(year, userId, 
					K3SalerQuota.QUOTA_CLASS_RECIEVEBILL); 
			Double quarterRecieveTarget = quotaService.queryThisQuarter(year, 
					quarter, userId, K3SalerQuota.QUOTA_CLASS_RECIEVEBILL);
			
			
			Double yearOrderAmount = statisticsOrderService.statYear(year, userId);
			Double quarterOrderAmount = statisticsOrderService.statQuarter(year, quarter, userId);
			Double yearRecieveAmount = statisticsRecieveService.statYear(year, userId);
			Double quarterRecieveAmount = statisticsRecieveService.statQuarter(year, quarter, userId);
			Double quarterICSaleAmount = statisticsICSaleService.statQuarter(year, quarter, userId);
			
			session.put("yearOrderTarget", NormalFun.formatCurrency(yearOrderTarget));
			session.put("quarterOrderTarget", NormalFun.formatCurrency(quarterOrderTarget));
			session.put("yearRecieveTarget", NormalFun.formatCurrency(yearRecieveAmount));
			session.put("quarterRecieveTarget", NormalFun.formatCurrency(quarterRecieveAmount));
			
			session.put("yearOrderAmount", NormalFun.formatCurrency(yearOrderAmount));
			session.put("quarterOrderAmount", NormalFun.formatCurrency(quarterOrderAmount));
			session.put("yearRecieveAmount", NormalFun.formatCurrency(yearRecieveAmount));
			session.put("quarterRecieveAmount", NormalFun.formatCurrency(quarterRecieveAmount));
			
			if(yearOrderAmount != null && yearOrderTarget != null)
				session.put("yearOrderPercent", NormalFun.formatPercent(yearOrderAmount/yearOrderTarget));
			
			if(quarterOrderAmount != null && quarterOrderTarget != null)
				session.put("quarterOrderPercent", NormalFun.formatPercent(quarterOrderAmount/quarterOrderTarget));
			
			if(yearRecieveAmount != null && yearRecieveTarget != null)
				session.put("yearRecievePercent", NormalFun.formatPercent(yearRecieveAmount/yearRecieveTarget));
			
			if(quarterRecieveAmount != null && quarterICSaleAmount != null)
				session.put("quarterRecievePercent", NormalFun.formatPercent(quarterRecieveAmount/quarterICSaleAmount));
		} catch (Exception e) {
			e.printStackTrace();
			actionLog.error(e.getMessage());
		}
		
	}
	
	public String userLogout() {
		
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
		return "userLogout";

	}
	/**
	 * @return the autoLoginFlag
	 */
	public String getAutoLoginFlag() {
		return autoLoginFlag;
	}
	/**
	 * @param autoLoginFlag the autoLoginFlag to set
	 */
	public void setAutoLoginFlag(String autoLoginFlag) {
		this.autoLoginFlag = autoLoginFlag;
	}

}
