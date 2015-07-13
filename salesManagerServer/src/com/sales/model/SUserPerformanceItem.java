package com.sales.model;

public class SUserPerformanceItem extends Page {

	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column s_user_performance_item.performance_item_id
	 * @abatorgenerated  Fri May 17 13:48:43 CST 2013
	 */
	private Integer performanceItemId;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column s_user_performance_item.item_id
	 * @abatorgenerated  Fri May 17 13:48:43 CST 2013
	 */
	private Integer itemId;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column s_user_performance_item.performance_plan_id
	 * @abatorgenerated  Fri May 17 13:48:43 CST 2013
	 */
	private Integer performancePlanId;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column s_user_performance_item.goal
	 * @abatorgenerated  Fri May 17 13:48:43 CST 2013
	 */
	private Integer goal;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column s_user_performance_item.score
	 * @abatorgenerated  Fri May 17 13:48:43 CST 2013
	 */
	private String score;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column s_user_performance_item.comment
	 * @abatorgenerated  Fri May 17 13:48:43 CST 2013
	 */
	private String comment;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column s_user_performance_item.access_user_id
	 * @abatorgenerated  Fri May 17 13:48:43 CST 2013
	 */
	private Integer accessUserId;
	
	private String accessUserName;

	private SPerformanceItem item;
	
	private Integer planId;
	
	private SUserPerformancePlan userPlan;
	
	private Integer scoreEnter;
	
	private Integer statistics;
	
	private String checkBoxStatus;
	
	private String scoreHun;

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column s_user_performance_item.performance_item_id
	 * @return  the value of s_user_performance_item.performance_item_id
	 * @abatorgenerated  Fri May 17 13:48:43 CST 2013
	 */
	public Integer getPerformanceItemId() {
		return performanceItemId;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column s_user_performance_item.performance_item_id
	 * @param performanceItemId  the value for s_user_performance_item.performance_item_id
	 * @abatorgenerated  Fri May 17 13:48:43 CST 2013
	 */
	public void setPerformanceItemId(Integer performanceItemId) {
		this.performanceItemId = performanceItemId;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column s_user_performance_item.item_id
	 * @return  the value of s_user_performance_item.item_id
	 * @abatorgenerated  Fri May 17 13:48:43 CST 2013
	 */
	public Integer getItemId() {
		return itemId;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column s_user_performance_item.item_id
	 * @param itemId  the value for s_user_performance_item.item_id
	 * @abatorgenerated  Fri May 17 13:48:43 CST 2013
	 */
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column s_user_performance_item.performance_plan_id
	 * @return  the value of s_user_performance_item.performance_plan_id
	 * @abatorgenerated  Fri May 17 13:48:43 CST 2013
	 */
	public Integer getPerformancePlanId() {
		return performancePlanId;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column s_user_performance_item.performance_plan_id
	 * @param performancePlanId  the value for s_user_performance_item.performance_plan_id
	 * @abatorgenerated  Fri May 17 13:48:43 CST 2013
	 */
	public void setPerformancePlanId(Integer performancePlanId) {
		this.performancePlanId = performancePlanId;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column s_user_performance_item.goal
	 * @return  the value of s_user_performance_item.goal
	 * @abatorgenerated  Fri May 17 13:48:43 CST 2013
	 */
	public Integer getGoal() {
		return goal;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column s_user_performance_item.goal
	 * @param goal  the value for s_user_performance_item.goal
	 * @abatorgenerated  Fri May 17 13:48:43 CST 2013
	 */
	public void setGoal(Integer goal) {
		this.goal = goal;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column s_user_performance_item.score
	 * @return  the value of s_user_performance_item.score
	 * @abatorgenerated  Fri May 17 13:48:43 CST 2013
	 */
	public String getScore() {
		return score;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column s_user_performance_item.score
	 * @param score  the value for s_user_performance_item.score
	 * @abatorgenerated  Fri May 17 13:48:43 CST 2013
	 */
	public void setScore(String score) {
		this.score = score;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column s_user_performance_item.comment
	 * @return  the value of s_user_performance_item.comment
	 * @abatorgenerated  Fri May 17 13:48:43 CST 2013
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column s_user_performance_item.comment
	 * @param comment  the value for s_user_performance_item.comment
	 * @abatorgenerated  Fri May 17 13:48:43 CST 2013
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column s_user_performance_item.access_user_id
	 * @return  the value of s_user_performance_item.access_user_id
	 * @abatorgenerated  Fri May 17 13:48:43 CST 2013
	 */
	public Integer getAccessUserId() {
		return accessUserId;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column s_user_performance_item.access_user_id
	 * @param accessUserId  the value for s_user_performance_item.access_user_id
	 * @abatorgenerated  Fri May 17 13:48:43 CST 2013
	 */
	public void setAccessUserId(Integer accessUserId) {
		this.accessUserId = accessUserId;
	}

	/**
	 * @return the accessUserName
	 */
	public String getAccessUserName() {
		return accessUserName;
	}

	/**
	 * @param accessUserName the accessUserName to set
	 */
	public void setAccessUserName(String accessUserName) {
		this.accessUserName = accessUserName;
	}

	/**
	 * @return the item
	 */
	public SPerformanceItem getItem() {
		return item;
	}

	/**
	 * @param item the item to set
	 */
	public void setItem(SPerformanceItem item) {
		this.item = item;
	}

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
	 * @return the userPlan
	 */
	public SUserPerformancePlan getUserPlan() {
		return userPlan;
	}

	/**
	 * @param userPlan the userPlan to set
	 */
	public void setUserPlan(SUserPerformancePlan userPlan) {
		this.userPlan = userPlan;
	}

	/**
	 * @return the scoreEnter
	 */
	public Integer getScoreEnter() {
		if (this.score == null || this.item == null || this.item.getSpecificWeight() == null)
			return null;
		
		return Integer.parseInt(this.score)*100/this.item.getSpecificWeight();
	}

	/**
	 * @return the statistics
	 */
	public Integer getStatistics() {
		return statistics;
	}

	/**
	 * @param statistics the statistics to set
	 */
	public void setStatistics(Integer statistics) {
		this.statistics = statistics;
	}

	/**
	 * @return the checkBoxStatus
	 */
	public String getCheckBoxStatus() {
		return checkBoxStatus;
	}

	/**
	 * @param checkBoxStatus the checkBoxStatus to set
	 */
	public void setCheckBoxStatus(String checkBoxStatus) {
		this.checkBoxStatus = checkBoxStatus;
	}

	/**
	 * @return the scoreHun
	 */
	public String getScoreHun() {
		if (this.score == null) 
			return "";
		else {
			Integer s = Integer.parseInt(this.score)*100/this.item.getSpecificWeight();
			return String.valueOf(s);	
		}
		
	}

}