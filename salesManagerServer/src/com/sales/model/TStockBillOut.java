package com.sales.model;

import java.util.Date;
import java.util.List;

import com.sales.common.Constants;
import com.sales.common.DisplayAnnotation;

/**
 * 外购产品出库单
 */
public class TStockBillOut extends BaseObject {
	
	private Integer fInterID;
	@DisplayAnnotation(name="单据日期")
	private Date fDate;
	@DisplayAnnotation(name="单据编号")
	private String fBillNo;
	private String fNote;
	private Integer fDeptID;
	private String departName;
	private Integer fEmpID;
	@DisplayAnnotation(name="业务员")
	private String employeeName;
	private Integer fCheckerID;
	private String checkerName;
	private Integer fPosterID;
	private String posterName;
	private Integer fFManagerID;
	private String fmgrName;
	private Integer fSManagerID;
	private String smgrName;
	private Integer fBillerID;
	@DisplayAnnotation(name="制单人")
	private String billerName;
	private Integer fCurrencyID;
	private String currencyName;
	private Integer fSaleStyle;
	private String saleStyleName; 
	private Integer fStatus;
	private Integer fCancellation;
	private Date fCheckDate; 
	private Integer fManagerID;
	private String mananerName;
	private Integer fSelTranType;
	private String fHeadSelfB0148;
	@DisplayAnnotation(name="出库明细")
	private List stockBillOutEntryList;
	@DisplayAnnotation(name="审核状态")
	private String statusName; 
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
	public String getFNote() {
		return fNote;
	}
	public void setFNote(String fNote) {
		this.fNote = fNote;
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
	public Integer getFFManagerID() {
		return fFManagerID;
	}
	public void setFFManagerID(Integer fFManagerID) {
		this.fFManagerID = fFManagerID;
	}
	public String getFmgrName() {
		return fmgrName;
	}
	public void setFmgrName(String fmgrName) {
		this.fmgrName = fmgrName;
	}
	public Integer getFSManagerID() {
		return fSManagerID;
	}
	public void setFSManagerID(Integer fSManagerID) {
		this.fSManagerID = fSManagerID;
	}
	public String getSmgrName() {
		return smgrName;
	}
	public void setSmgrName(String smgrName) {
		this.smgrName = smgrName;
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
	public Integer getFSaleStyle() {
		return fSaleStyle;
	}
	public void setFSaleStyle(Integer fSaleStyle) {
		this.fSaleStyle = fSaleStyle;
	}
	public String getSaleStyleName() {
		return saleStyleName;
	}
	public void setSaleStyleName(String saleStyleName) {
		this.saleStyleName = saleStyleName;
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
	public Date getFCheckDate() {
		return fCheckDate;
	}
	public void setFCheckDate(Date fCheckDate) {
		this.fCheckDate = fCheckDate;
	}
	public Integer getFManagerID() {
		return fManagerID;
	}
	public void setFManagerID(Integer fManagerID) {
		this.fManagerID = fManagerID;
	}
	public String getMananerName() {
		return mananerName;
	}
	public void setMananerName(String mananerName) {
		this.mananerName = mananerName;
	}
	public Integer getFSelTranType() {
		return fSelTranType;
	}
	public void setFSelTranType(Integer fSelTranType) {
		this.fSelTranType = fSelTranType;
	}
	public String getFHeadSelfB0148() {
		return fHeadSelfB0148;
	}
	public void setFHeadSelfB0148(String fHeadSelfB0148) {
		this.fHeadSelfB0148 = fHeadSelfB0148;
	}
	public List getStockBillOutEntryList() {
		return stockBillOutEntryList;
	}
	public void setStockBillOutEntryList(List stockBillOutEntryList) {
		this.stockBillOutEntryList = stockBillOutEntryList;
	}

}
