package com.sales.model;

import java.util.Date;
import java.util.List;

import com.sales.common.Constants;
import com.sales.common.DisplayAnnotation;

/**
 * 销售收款单
 */
public class TReceiveBill extends BaseObject{
	
	private Integer fBillID;
	@DisplayAnnotation(name="单据状态")
	private Integer fBillStatus;
	@DisplayAnnotation(name="收款单号")
	private String fNumber;
	@DisplayAnnotation(name="单据日期")
	private Date fDate;
	private Date fFincDate;
	private String fItemClassID_DSPName;
	private Integer customerNumber;
	@DisplayAnnotation(name="客户名称")
	private String customerName;
	private String departNumber;
	private String departName;
	private String employeeNumber;
	@DisplayAnnotation(name="业务员")
	private String employeeName;
	private String currencyName;
	@DisplayAnnotation(name="收款金额")
	private Double fAmountFor;
	private String checkerName;
	@DisplayAnnotation(name="收款明细", isChildrenList=true)
	private List receiveBillEntryList;
	private String billStatusName;
	private int pageSize;
	private int startNumber;
	private String startTime;
	private String endTime;
	private String customerIds;
	
	public String getCustomerIds() {
		return customerIds;
	}
	public void setCustomerIds(String customerIds) {
		this.customerIds = customerIds;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getStartNumber() {
		return startNumber;
	}
	public void setStartNumber(int startNumber) {
		this.startNumber = startNumber;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getBillStatusName() {
		if(fBillStatus != null){
			return Constants.K3STATUS.get(fBillStatus).toString();
		}
		else{
			return "";
		}
	}
	public void setBillStatusName(String billStatusName) {
		this.billStatusName = billStatusName;
	}
	public Integer getFBillID() {
		return fBillID;
	}
	public void setFBillID(Integer fBillID) {
		this.fBillID = fBillID;
	}
	public Integer getFBillStatus() {
		return fBillStatus;
	}
	public void setFBillStatus(Integer fBillStatus) {
		this.fBillStatus = fBillStatus;
	}
	public String getFNumber() {
		return fNumber;
	}
	public void setFNumber(String fNumber) {
		this.fNumber = fNumber;
	}
	public Date getFDate() {
		return fDate;
	}
	public void setFDate(Date fDate) {
		this.fDate = fDate;
	}
	public Date getFFincDate() {
		return fFincDate;
	}
	public void setFFincDate(Date fFincDate) {
		this.fFincDate = fFincDate;
	}
	public String getFItemClassID_DSPName() {
		return fItemClassID_DSPName;
	}
	public void setFItemClassID_DSPName(String fItemClassIDDSPName) {
		fItemClassID_DSPName = fItemClassIDDSPName;
	}
	public Integer getCustomerNumber() {
		return customerNumber;
	}
	public void setCustomerNumber(Integer customerNumber) {
		this.customerNumber = customerNumber;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getDepartNumber() {
		return departNumber;
	}
	public void setDepartNumber(String departNumber) {
		this.departNumber = departNumber;
	}
	public String getDepartName() {
		return departName;
	}
	public void setDepartName(String departName) {
		this.departName = departName;
	}
	public String getEmployeeNumber() {
		return employeeNumber;
	}
	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getCurrencyName() {
		return currencyName;
	}
	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}
	public Double getFAmountFor() {
		return fAmountFor;
	}
	public void setFAmountFor(Double fAmountFor) {
		this.fAmountFor = fAmountFor;
	} 
	public String getCheckerName() {
		return checkerName;
	}
	public void setCheckerName(String checkerName) {
		this.checkerName = checkerName;
	}
	public List getReceiveBillEntryList() {
		return receiveBillEntryList;
	}
	public void setReceiveBillEntryList(List receiveBillEntryList) {
		this.receiveBillEntryList = receiveBillEntryList;
	}	

}
