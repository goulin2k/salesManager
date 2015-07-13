package com.sales.model;

import java.util.Date;
import java.util.List;

import com.sales.common.Constants;
import com.sales.common.DisplayAnnotation;

/**
 * 外购产品入库单
 */
public class TStockBill extends BaseObject {
	
	private Integer fInterID;
	private String fNote;
	@DisplayAnnotation(name="单据日期")
	private Date fDate;
	@DisplayAnnotation(name="单据编号")
	private String fBillNo;
	private Integer fDeptID;
	private String departmentName;
	private Integer fEmpID;
	@DisplayAnnotation(name="业务员")
	private String employeeName;
	private Integer fBillerID;
	@DisplayAnnotation(name="制单人")
	private String billerName;
	private Integer fStatus;
	private Integer fSelTranType;
	private String sourceTranType;
	@DisplayAnnotation(name="入库明细", isChildrenList=true)
	private List stockBillEntryList;
	@DisplayAnnotation(name="审核状态")
	private String statusName;
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
	public String getStatusName() {
		if(fStatus != null){
			return Constants.K3STATUS.get(fStatus).toString();
		}
		else{
			return "";
		}
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public Integer getFInterID() {
		return fInterID;
	}
	public void setFInterID(Integer fInterID) {
		this.fInterID = fInterID;
	}
	public String getFNote() {
		return fNote;
	}
	public void setFNote(String fNote) {
		this.fNote = fNote;
	}
	public Date getFDate() {
		return fDate;
	}
	public void setFDate(Date fDate) {
		this.fDate = fDate;
	}
	public String getFBillNo() {
		return fBillNo;
	}
	public void setFBillNo(String fBillNo) {
		this.fBillNo = fBillNo;
	}
	public Integer getFDeptID() {
		return fDeptID;
	}
	public void setFDeptID(Integer fDeptID) {
		this.fDeptID = fDeptID;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public Integer getFEmpID() {
		return fEmpID;
	}
	public void setFEmpID(Integer fEmpID) {
		this.fEmpID = fEmpID;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public Integer getFBillerID() {
		return fBillerID;
	}
	public void setFBillerID(Integer fBillerID) {
		this.fBillerID = fBillerID;
	}
	public String getBillerName() {
		return billerName;
	}
	public void setBillerName(String billerName) {
		this.billerName = billerName;
	}
	public Integer getFStatus() {
		return fStatus;
	}
	public void setFStatus(Integer fStatus) {
		this.fStatus = fStatus;
	}
	public Integer getFSelTranType() {
		return fSelTranType;
	}
	public void setFSelTranType(Integer fSelTranType) {
		this.fSelTranType = fSelTranType;
	}
	public String getSourceTranType() {
		return sourceTranType;
	}
	public void setSourceTranType(String sourceTranType) {
		this.sourceTranType = sourceTranType;
	}
	public List getStockBillEntryList() {
		return stockBillEntryList;
	}
	public void setStockBillEntryList(List stockBillEntryList) {
		this.stockBillEntryList = stockBillEntryList;
	}

}
