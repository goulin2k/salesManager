package com.sales.model;

import java.util.Date;

public class TSaleBillStatusChange {
	
	private Integer billStatusId;
	private Integer billType;
	private Integer billId;
	private Integer oldStatus;
	private Integer newStatus;
	private Date updateTime;
	private Integer haveRead;
	public Integer getBillStatusId() {
		return billStatusId;
	}
	public void setBillStatusId(Integer billStatusId) {
		this.billStatusId = billStatusId;
	}
	public Integer getBillType() {
		return billType;
	}
	public void setBillType(Integer billType) {
		this.billType = billType;
	}
	public Integer getBillId() {
		return billId;
	}
	public void setBillId(Integer billId) {
		this.billId = billId;
	}
	public Integer getOldStatus() {
		return oldStatus;
	}
	public void setOldStatus(Integer oldStatus) {
		this.oldStatus = oldStatus;
	}
	public Integer getNewStatus() {
		return newStatus;
	}
	public void setNewStatus(Integer newStatus) {
		this.newStatus = newStatus;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getHaveRead() {
		return haveRead;
	}
	public void setHaveRead(Integer haveRead) {
		this.haveRead = haveRead;
	}

}
