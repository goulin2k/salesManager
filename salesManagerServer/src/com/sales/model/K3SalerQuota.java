/**
 * 
 */
package com.sales.model;

/**
 * @author Administrator
 *
 */
public class K3SalerQuota extends BaseObject {
	
	/**
	 * 指标类型：年指标
	 */
	public static final int QUOTA_TYPE_YEAR = 1;			
	/**
	 * 指标类型：季度指标
	 */
	public static final int QUOTA_TYPE_QUARTER = 2;
	/**
	 * 指标类型：月份指标
	 */
	public static final int QUOTA_TYPE_MONTH = 3;
	
	/**
	 * 指标类别：销售指标
	 */
	public static final int QUOTA_CLASS_ORDER = 1;			
	/**
	 * 指标类别：收款指标
	 */
	public static final int QUOTA_CLASS_RECIEVEBILL = 2;
	

	private Integer quotaId;
	private Integer quotaClass;
	private Integer quotaType;
	private Integer year;
	private Integer quarter;
	private Integer month;
	private Integer userId;
	private Double target;
	private String description;
	
	private String userName;

	/**
	 * @return the quotaId
	 */
	public Integer getQuotaId() {
		return quotaId;
	}

	/**
	 * @param quotaId the quotaId to set
	 */
	public void setQuotaId(Integer quotaId) {
		this.quotaId = quotaId;
	}

	/**
	 * @return the quotaType
	 */
	public Integer getQuotaType() {
		return quotaType;
	}

	/**
	 * @param quotaType the quotaType to set
	 */
	public void setQuotaType(Integer quotaType) {
		this.quotaType = quotaType;
	}

	/**
	 * @return the year
	 */
	public Integer getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(Integer year) {
		this.year = year;
	}

	/**
	 * @return the quarter
	 */
	public Integer getQuarter() {
		return quarter;
	}

	/**
	 * @param quarter the quarter to set
	 */
	public void setQuarter(Integer quarter) {
		this.quarter = quarter;
	}

	/**
	 * @return the month
	 */
	public Integer getMonth() {
		return month;
	}

	/**
	 * @param month the month to set
	 */
	public void setMonth(Integer month) {
		this.month = month;
	}

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
	 * @return the target
	 */
	public Double getTarget() {
		return target;
	}

	/**
	 * @param target the target to set
	 */
	public void setTarget(Double target) {
		this.target = target;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
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
	 * 指标类别
	 * @return the quotaClass
	 */
	public Integer getQuotaClass() {
		return quotaClass;
	}

	/**
	 * 指标类别
	 * @param quotaClass the quotaClass to set
	 */
	public void setQuotaClass(Integer quotaClass) {
		this.quotaClass = quotaClass;
	}
	
	
}
