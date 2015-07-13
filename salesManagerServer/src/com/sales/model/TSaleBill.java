package com.sales.model;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

public class TSaleBill {
	
	private Integer fInterID;
	private String fBillNo;
	private Date fDate;
	private Double saleAmount;
	private List receiveBillList;
	private Date receiveDate;
	private Double receiveAmount;
	private String receiveAmountString;
	private String saleAmountString;
	
	public String getReceiveAmountString() {
		if(receiveAmount!=null){
			DecimalFormat myformat = new DecimalFormat();
			myformat.applyPattern("##,###.00");
			receiveAmountString=(myformat.format(receiveAmount));
		}
		return receiveAmountString;
	}
	public void setReceiveAmountString(String receiveAmountString) {
		this.receiveAmountString = receiveAmountString;
	}
	
	public String getfBillNo() {
		return fBillNo;
	}
	public void setfBillNo(String fBillNo) {
		this.fBillNo = fBillNo;
	}
	public String getSaleAmountString() {
		if(saleAmount!=null){
			DecimalFormat myformat = new DecimalFormat();
			myformat.applyPattern("##,###.00");
			saleAmountString=(myformat.format(saleAmount));
		}
		return saleAmountString;
	}
	public void setSaleAmountString(String saleAmountString) {
		this.saleAmountString = saleAmountString;
	}
	public Integer getFInterID() {
		return fInterID;
	}
	public void setFInterID(Integer fInterID) {
		this.fInterID = fInterID;
	}
	public String getFBillNo() {
		return fBillNo;
	}
	public void setFBillNo(String fBillNo) {
		this.fBillNo = fBillNo;
	}
	public Date getFDate() {
		return fDate;
	}
	public void setFDate(Date fDate) {
		this.fDate = fDate;
	}
	public Double getSaleAmount() {
		return saleAmount;
	}
	public void setSaleAmount(Double saleAmount) {
		this.saleAmount = saleAmount;
	}
	public List getReceiveBillList() {
		return receiveBillList;
	}
	public void setReceiveBillList(List receiveBillList) {
		this.receiveBillList = receiveBillList;
	}
	public Date getReceiveDate() {
		return receiveDate;
	}
	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}
	public Double getReceiveAmount() {
		return receiveAmount;
	}
	public void setReceiveAmount(Double receiveAmount) {
		this.receiveAmount = receiveAmount;
	}

}
