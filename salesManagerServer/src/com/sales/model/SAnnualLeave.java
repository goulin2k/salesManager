package com.sales.model;

import java.util.Date;

public class SAnnualLeave {

	private Integer id;
	
	private Integer userId;
	
	private Integer annualHours;
	
	private Date updateTime;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
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
	 * @return the annualHours
	 */
	public Integer getAnnualHours() {
		return annualHours;
	}

	/**
	 * @param annualHours the annualHours to set
	 */
	public void setAnnualHours(Integer annualHours) {
		this.annualHours = annualHours;
	}

	/**
	 * @return the updateTime
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
