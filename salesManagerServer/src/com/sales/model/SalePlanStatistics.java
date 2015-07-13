/**
 * 
 */
package com.sales.model;

import com.sales.common.NormalFun;

/**
 * 销售计划执行报表
 * @author Goulin
 *
 */
public class SalePlanStatistics extends BaseObject {
	
	private static final long serialVersionUID = -158642354057786110L;
	
	private Integer userId;
	private String userName;
	private String departmentName;
	private Integer plans;
	private Integer completes;
	private Double completeRate;
	
	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the departmentName
	 */
	public String getDepartmentName() {
		return departmentName;
	}
	/**
	 * @param departmentName the departmentName to set
	 */
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	/**
	 * @return the plans
	 */
	public Integer getPlans() {
		return plans;
	}
	/**
	 * @param plans the plans to set
	 */
	public void setPlans(Integer plans) {
		this.plans = plans;
	}
	/**
	 * @return the completes
	 */
	public Integer getCompletes() {
		return completes;
	}
	/**
	 * @param completes the completes to set
	 */
	public void setCompletes(Integer completes) {
		this.completes = completes;
	}
	
	/**
	 * @return the completeRate
	 */
	public Double getCompleteRate() {
		if(plans == null || completes == null || plans == 0)
			return null;
		return new Double(completes)/new Double(plans);
	}
	
	/**
	 * @return the completeRate
	 */
	public String getCompleteRateFormat() {
		return NormalFun.formatPercent(getCompleteRate());
	}
	
}
