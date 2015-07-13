/**
 * 
 */
package com.sales.model;

import java.sql.Timestamp;
import java.util.Date;

/**
 * 销售计划关联销售活动视图类
 * @author Administrator
 *
 */
public class SalePlanWithActivityVO extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5064113355751410542L;
	
	private Integer planId;
	private String planTopical;
	private Date planTime;
	private Integer saleProjectId;
	private String saleProjectName;
	private Integer customerId;
	private String customerName;
	private Integer activityId;
	private String activityTopical;
	private Date activityTime;
	private String activityTypeName;
	private String visitPerson;
	private String customerLevel;
	private Timestamp createTime;
	
	/**
	 * @return the planId
	 */
	public Integer getPlanId() {
		return planId;
	}
	/**
	 * @param planId the planId to set
	 */
	public void setPlanId(Integer planId) {
		this.planId = planId;
	}
	/**
	 * @return the planTopical
	 */
	public String getPlanTopical() {
		return planTopical;
	}
	/**
	 * @param planTopical the planTopical to set
	 */
	public void setPlanTopical(String planTopical) {
		this.planTopical = planTopical;
	}
	/**
	 * @return the planTime
	 */
	public Date getPlanTime() {
		return planTime;
	}
	/**
	 * @param planTime the planTime to set
	 */
	public void setPlanTime(Date planTime) {
		this.planTime = planTime;
	}
	/**
	 * @return the saleProjectId
	 */
	public Integer getSaleProjectId() {
		return saleProjectId;
	}
	/**
	 * @param saleProjectId the saleProjectId to set
	 */
	public void setSaleProjectId(Integer saleProjectId) {
		this.saleProjectId = saleProjectId;
	}
	/**
	 * @return the saleProjectName
	 */
	public String getSaleProjectName() {
		return saleProjectName;
	}
	/**
	 * @param saleProjectName the saleProjectName to set
	 */
	public void setSaleProjectName(String saleProjectName) {
		this.saleProjectName = saleProjectName;
	}
	/**
	 * @return the customerId
	 */
	public Integer getCustomerId() {
		return customerId;
	}
	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}
	/**
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	/**
	 * @return the activityId
	 */
	public Integer getActivityId() {
		return activityId;
	}
	/**
	 * @param activityId the activityId to set
	 */
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}
	/**
	 * @return the activityTopical
	 */
	public String getActivityTopical() {
		return activityTopical;
	}
	/**
	 * @param activityTopical the activityTopical to set
	 */
	public void setActivityTopical(String activityTopical) {
		this.activityTopical = activityTopical;
	}
	/**
	 * @return the activityTime
	 */
	public Date getActivityTime() {
		return activityTime;
	}
	/**
	 * @param activityTime the activityTime to set
	 */
	public void setActivityTime(Date activityTime) {
		this.activityTime = activityTime;
	}
	/**
	 * @return the activityTypeName
	 */
	public String getActivityTypeName() {
		return activityTypeName;
	}
	/**
	 * @param activityTypeName the activityTypeName to set
	 */
	public void setActivityTypeName(String activityTypeName) {
		this.activityTypeName = activityTypeName;
	}
	/**
	 * @return the visitPerson
	 */
	public String getVisitPerson() {
		return visitPerson;
	}
	/**
	 * @param visitPerson the visitPerson to set
	 */
	public void setVisitPerson(String visitPerson) {
		this.visitPerson = visitPerson;
	}
	/**
	 * @return 客户等级
	 */
	public String getCustomerLevel() {
		return customerLevel;
	}
	/**
	 * @return the 计划创建时间
	 */
	public Timestamp getCreateTime() {
		return createTime;
	}
	/**
	 * @param 计划创建时间 to set
	 */
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	
	

}
