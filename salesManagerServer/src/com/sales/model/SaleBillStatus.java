package com.sales.model;

import java.util.Date; 

public class SaleBillStatus implements Comparable<SaleBillStatus> {
	
	private Integer billStatusId;
	private Integer orderId;
	private String orderNo;
	private Integer relationType;
	private Integer billId;
	private String billNo;
	private Integer status;
	private Date modifyTime;
	private String billIdStr;
	private String orderIdStr;
	private Integer haveRead;
	
	public String getOrderIdStr() {
		return orderIdStr;
	}
	public void setOrderIdStr(String orderIdStr) {
		this.orderIdStr = orderIdStr;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getBillIdStr() {
		return billIdStr;
	}
	public void setBillIdStr(String billIdStr) {
		this.billIdStr = billIdStr;
	}
	public Integer getBillStatusId() {
		return billStatusId;
	}
	public void setBillStatusId(Integer billStatusId) {
		this.billStatusId = billStatusId;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public Integer getRelationType() {
		return relationType;
	}
	public void setRelationType(Integer relationType) {
		this.relationType = relationType;
	}
	public Integer getBillId() {
		return billId;
	}
	public void setBillId(Integer billId) {
		this.billId = billId;
	}
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}	
	public Integer getHaveRead() {
		return haveRead;
	}
	public void setHaveRead(Integer haveRead) {
		this.haveRead = haveRead;
	}
	
	public int compareTo(SaleBillStatus arg0) {
		return this.getRelationType().compareTo(arg0.getRelationType());
	}
	
}
