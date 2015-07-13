package com.sales.model;

import java.util.Date;

public class TReceiveBillResult {
	
	private Integer fBillID; 
	private Date fDate;
	private Double receiveAmount;
	
	public Integer getFBillID() {
		return fBillID;
	}
	public void setFBillID(Integer fBillID) {
		this.fBillID = fBillID;
	} 
	public Date getFDate() {
		return fDate;
	}
	public void setFDate(Date fDate) {
		this.fDate = fDate;
	}
	public Double getReceiveAmount() {
		return receiveAmount;
	}
	public void setReceiveAmount(Double receiveAmount) {
		this.receiveAmount = receiveAmount;
	}

}
