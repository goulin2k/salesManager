package com.sales.model;

import java.util.Date;
import java.util.List;

import com.sales.common.Constants;
import com.sales.common.DisplayAnnotation;

/**
 * 采购申请单 
 */
public class TRequest extends BaseObject{
	
	private Integer fInterId;
	@DisplayAnnotation(name="申请日期")
	private Date fDate;
	@DisplayAnnotation(name="单据号")
	private String fBillNo;
	private Integer fTranType;
	@DisplayAnnotation(name="审批状态")
	private Integer fStatus;
	private String fNote;
	private String fExplanation;
	private Integer fBizType;
	private String bizType;
	private Integer fDeptID;
	private String departName;
	private Integer fRequesterID;
	@DisplayAnnotation(name="申请人")
	private String requsterName;
	private Integer fBillerID;
	private String billerName;
	private Integer fHeadSelfP0127;
	private String invoicerName;
	private Date fCheckTime;
	private Integer fCheckerID;
	private String checkerName;
	private Integer fSelTranType;
	private Integer fSCBillInterID;
	private Integer fChildren;
	private Integer fCancellation;
	private Integer fClosed;
	@DisplayAnnotation(name="申请明细", isChildrenList=true)
	private List requestEntryList; 
	private String statusName;
	private String closedName;
	private String cancellationName;	
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
	public String getClosedName() {
		if(fClosed != null){
			return Constants.K3Closed.get(fClosed).toString();
		}
		else{
			return "";
		}
	}
	public void setClosedName(String closedName) {
		this.closedName = closedName;
	}
	public String getCancellationName() {
		if(fCancellation != null){
			return Constants.K3Cancellation.get(fCancellation).toString();
		}
		else{
			return "";
		}
	}
	public void setCancellationName(String cancellationName) {
		this.cancellationName = cancellationName;
	}
	public Integer getFInterId() {
		return fInterId;
	}
	public void setFInterId(Integer fInterId) {
		this.fInterId = fInterId;
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
	public Integer getFTranType() {
		return fTranType;
	}
	public void setFTranType(Integer fTranType) {
		this.fTranType = fTranType;
	}
	public Integer getFStatus() {
		return fStatus;
	}
	public void setFStatus(Integer fStatus) {
		this.fStatus = fStatus;
	}
	public String getFNote() {
		return fNote;
	}
	public void setFNote(String fNote) {
		this.fNote = fNote;
	}
	public String getFExplanation() {
		return fExplanation;
	}
	public void setFExplanation(String fExplanation) {
		this.fExplanation = fExplanation;
	}
	public Integer getFBizType() {
		return fBizType;
	}
	public void setFBizType(Integer fBizType) {
		this.fBizType = fBizType;
	}
	public String getBizType() {
		return bizType;
	}
	public void setBizType(String bizType) {
		this.bizType = bizType;
	}
	public Integer getFDeptID() {
		return fDeptID;
	}
	public void setFDeptID(Integer fDeptID) {
		this.fDeptID = fDeptID;
	}
	public String getDepartName() {
		return departName;
	}
	public void setDepartName(String departName) {
		this.departName = departName;
	}
	public Integer getFRequesterID() {
		return fRequesterID;
	}
	public void setFRequesterID(Integer fRequesterID) {
		this.fRequesterID = fRequesterID;
	}
	public String getRequsterName() {
		return requsterName;
	}
	public void setRequsterName(String requsterName) {
		this.requsterName = requsterName;
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
	public Integer getFHeadSelfP0127() {
		return fHeadSelfP0127;
	}
	public void setFHeadSelfP0127(Integer fHeadSelfP0127) {
		this.fHeadSelfP0127 = fHeadSelfP0127;
	}
	public String getInvoicerName() {
		return invoicerName;
	}
	public void setInvoicerName(String invoicerName) {
		this.invoicerName = invoicerName;
	}
	public Date getFCheckTime() {
		return fCheckTime;
	}
	public void setFCheckTime(Date fCheckTime) {
		this.fCheckTime = fCheckTime;
	}
	public Integer getFCheckerID() {
		return fCheckerID;
	}
	public void setFCheckerID(Integer fCheckerID) {
		this.fCheckerID = fCheckerID;
	}
	public String getCheckerName() {
		return checkerName;
	}
	public void setCheckerName(String checkerName) {
		this.checkerName = checkerName;
	}
	public Integer getFSelTranType() {
		return fSelTranType;
	}
	public void setFSelTranType(Integer fSelTranType) {
		this.fSelTranType = fSelTranType;
	}
	public Integer getFSCBillInterID() {
		return fSCBillInterID;
	}
	public void setFSCBillInterID(Integer fSCBillInterID) {
		this.fSCBillInterID = fSCBillInterID;
	}
	public Integer getFChildren() {
		return fChildren;
	}
	public void setFChildren(Integer fChildren) {
		this.fChildren = fChildren;
	}
	public Integer getFCancellation() {
		return fCancellation;
	}
	public void setFCancellation(Integer fCancellation) {
		this.fCancellation = fCancellation;
	}
	public Integer getFClosed() {
		return fClosed;
	}
	public void setFClosed(Integer fClosed) {
		this.fClosed = fClosed;
	}
	public List getRequestEntryList() {
		return requestEntryList;
	}
	public void setRequestEntryList(List requestEntryList) {
		this.requestEntryList = requestEntryList;
	}  

}
