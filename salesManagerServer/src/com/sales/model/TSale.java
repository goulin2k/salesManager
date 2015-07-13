package com.sales.model;

import java.util.Date;
import java.util.List;

import com.sales.common.Constants;
import com.sales.common.DisplayAnnotation;

/**
 * 销售发票
 */
public class TSale extends BaseObject {
	
	private Integer fInterID;
	@DisplayAnnotation(name="单据编号")
	private String fBillNo;
	@DisplayAnnotation(name="单据日期")
	private Date fDate;
	private Integer fTranType;
	private String tranTypeName;
	private Integer fCustID;
	@DisplayAnnotation(name="客户名")
	private String customerName;
	private Integer fCurrencyID;
	private String currencyName;
	@DisplayAnnotation(name="付款方式")
	private Integer fPayStyleID;
	private Integer fDeptID;
	private String departName;
	private Integer fEmpID;
	@DisplayAnnotation(name="业务员")
	private String employeeName;
	private Integer fBillerID;
	@DisplayAnnotation(name="单据制作人")
	private String billerName;
	private Integer fCheckerID;
	private String checkerName;
	private Date fCheckDate;
	private Integer fPosterID;
	private String posterName; 
	private Integer fManagerID;
	private String managerName;
	private Integer fClosed;
	private Integer frob;
	private Integer fStatus;
	private Integer fCancellation;
	private String fHeadSelfI0546;
	private String fHeadSelfI0547;
	private String fHeadSelfI0449;
	private String fHeadSelfI0450;
	@DisplayAnnotation(name="发票明细", isChildrenList=true)
	private List saleEntryList;
	@DisplayAnnotation(name="审核状态")
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
	public Integer getFTranType() {
		return fTranType;
	}
	public void setFTranType(Integer fTranType) {
		this.fTranType = fTranType;
	}
	public String getTranTypeName() {
		return tranTypeName;
	}
	public void setTranTypeName(String tranTypeName) {
		this.tranTypeName = tranTypeName;
	}
	public Integer getFCustID() {
		return fCustID;
	}
	public void setFCustID(Integer fCustID) {
		this.fCustID = fCustID;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Integer getFCurrencyID() {
		return fCurrencyID;
	}
	public void setFCurrencyID(Integer fCurrencyID) {
		this.fCurrencyID = fCurrencyID;
	}
	public String getCurrencyName() {
		return currencyName;
	}
	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}
	public Integer getFPayStyleID() {
		return fPayStyleID;
	}
	public void setFPayStyleID(Integer fPayStyleID) {
		this.fPayStyleID = fPayStyleID;
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
	public Date getFCheckDate() {
		return fCheckDate;
	}
	public void setFCheckDate(Date fCheckDate) {
		this.fCheckDate = fCheckDate;
	}
	public Integer getFPosterID() {
		return fPosterID;
	}
	public void setFPosterID(Integer fPosterID) {
		this.fPosterID = fPosterID;
	}
	public String getPosterName() {
		return posterName;
	}
	public void setPosterName(String posterName) {
		this.posterName = posterName;
	} 
	public Integer getFManagerID() {
		return fManagerID;
	}
	public void setFManagerID(Integer fManagerID) {
		this.fManagerID = fManagerID;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public Integer getFClosed() {
		return fClosed;
	}
	public void setFClosed(Integer fClosed) {
		this.fClosed = fClosed;
	}
	public Integer getFrob() {
		return frob;
	}
	public void setFrob(Integer frob) {
		this.frob = frob;
	}
	public Integer getFStatus() {
		return fStatus;
	}
	public void setFStatus(Integer fStatus) {
		this.fStatus = fStatus;
	}
	public Integer getFCancellation() {
		return fCancellation;
	}
	public void setFCancellation(Integer fCancellation) {
		this.fCancellation = fCancellation;
	}
	public String getFHeadSelfI0546() {
		return fHeadSelfI0546;
	}
	public void setFHeadSelfI0546(String fHeadSelfI0546) {
		this.fHeadSelfI0546 = fHeadSelfI0546;
	}
	public String getFHeadSelfI0547() {
		return fHeadSelfI0547;
	}
	public void setFHeadSelfI0547(String fHeadSelfI0547) {
		this.fHeadSelfI0547 = fHeadSelfI0547;
	}
	public String getFHeadSelfI0449() {
		return fHeadSelfI0449;
	}
	public void setFHeadSelfI0449(String fHeadSelfI0449) {
		this.fHeadSelfI0449 = fHeadSelfI0449;
	}
	public String getFHeadSelfI0450() {
		return fHeadSelfI0450;
	}
	public void setFHeadSelfI0450(String fHeadSelfI0450) {
		this.fHeadSelfI0450 = fHeadSelfI0450;
	}
	public List getSaleEntryList() {
		return saleEntryList;
	}
	public void setSaleEntryList(List saleEntryList) {
		this.saleEntryList = saleEntryList;
	}

}
