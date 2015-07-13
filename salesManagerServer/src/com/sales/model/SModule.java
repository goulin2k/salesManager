package com.sales.model;
/** 
 * @author  
 * @version 创建时间：2013-5-18 下午02:52:12 
 *  
 */
public class SModule  extends BaseObject {
	
	private Integer moduleId;
	private String moduleNameEnglish;
	private String moduleNameChinese;
	private String enumerationCode;
	private Integer userId;
	private Integer userModuleId;
	private String moduleUrl;
	private Integer actionId;
	private Integer roleId;
	private String isCheck;
	public String getIsCheck() {
		return isCheck;
	}
	public void setIsCheck(String isCheck) {
		this.isCheck = isCheck;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Integer getActionId() {
		return actionId;
	}
	public void setActionId(Integer actionId) {
		this.actionId = actionId;
	}
	public String getModuleUrl() {
		return moduleUrl;
	}
	public void setModuleUrl(String moduleUrl) {
		this.moduleUrl = moduleUrl;
	}
	public Integer getUserModuleId() {
		return userModuleId;
	}
	public void setUserModuleId(Integer userModuleId) {
		this.userModuleId = userModuleId;
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
	public String getEnumerationCode() {
		return enumerationCode;
	}
	public void setEnumerationCode(String enumerationCode) {
		this.enumerationCode = enumerationCode;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
