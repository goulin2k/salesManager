package com.sales.model;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import com.sales.common.Constants;
import com.sales.common.DisplayAnnotation;

/**
 * 销售订单 
 */
public class TOrder extends BaseObject {
	
	private Integer fInterID;
	private String fFetchDate;
	@DisplayAnnotation(name="订单日期")
	private Date fDate;
	@DisplayAnnotation(name="关联采购订单号")
	private String fPOOrdBillNo;
	@DisplayAnnotation(name="订单编号")
	private String fBillNo;
	private Integer fCustID;
	@DisplayAnnotation(name="客户名称")
	private String customerName;
	private Integer fDeptID;
	private String departName;
	private Integer fEmpID;
	@DisplayAnnotation(name="业务员")
	private String employeeName;
	private Integer fMangerID;
	private String managerName;
	private Integer fBillerID;
	private String billerName;
	private Integer fCheckerID;
	private String checkerName;
	private Date fPayDate;
	private String fPayStyle;
	private Integer fInvoiceClosed;
	private Integer fTranType;
	@DisplayAnnotation(name="运输方式")
	private String transTypeName;
	private Integer fCurrencyID;
	private String currencyName;
	private Integer fCancellation;
	private Integer fClosed;
	
	private Integer fStatus;
	@DisplayAnnotation(name="订单明细", isChildrenList=true)
	private List orderEntryList;
	@DisplayAnnotation(name="审核状态")
	private String statusName;
	private String closedName;
	private String cancellationName;	
	private String invoiceClosedName;
	private int pageSize;
	private int startNumber;
	private String startTime;
	private String endTime;
	private Double amount;
	private String customerIds;
	@DisplayAnnotation(name="订单金额")
	private String amountString;

	public String getAmountString() {
		if(amount!=null){
			DecimalFormat myformat = new DecimalFormat();
			myformat.applyPattern("##,###.00");
			amountString=(myformat.format(amount));
		}
		return amountString;
	}
	public String getfBillNo() {
		return fBillNo;
	}
	public void setfBillNo(String fBillNo) {
		this.fBillNo = fBillNo;
	}
	public void setAmountString(String amountString) {
		this.amountString = amountString;
	}
	public String getCustomerIds() {
		return customerIds;
	}
	public void setCustomerIds(String customerIds) {
		this.customerIds = customerIds;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
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
	public String getInvoiceClosedName() {
		if(fInvoiceClosed!=null && fInvoiceClosed==1){
			return "已开票";
		}
		else if(fInvoiceClosed!=null && fInvoiceClosed==0){
			return "未开票";
		}  
		else{
			return "";
		}
	}
	public void setInvoiceClosedName(String invoiceClosedName) {
		this.invoiceClosedName = invoiceClosedName;
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
	public List getOrderEntryList() {
		return orderEntryList;
	}
	public void setOrderEntryList(List orderEntryList) {
		this.orderEntryList = orderEntryList;
	}
	public Integer getFInterID() {
		return fInterID;
	}
	public void setFInterID(Integer fInterID) {
		this.fInterID = fInterID;
	}
	public String getFFetchDate() {
		return fFetchDate;
	}
	public void setFFetchDate(String fFetchDate) {
		this.fFetchDate = fFetchDate;
	}
	public Date getFDate() {
		return fDate;
	}
	public void setFDate(Date fDate) {
		this.fDate = fDate;
	}
	public String getFPOOrdBillNo() {
		return fPOOrdBillNo;
	}
	public void setFPOOrdBillNo(String fPOOrdBillNo) {
		this.fPOOrdBillNo = fPOOrdBillNo;
	}
	public String getFBillNo() {
		return fBillNo;
	}
	public void setFBillNo(String fBillNo) {
		this.fBillNo = fBillNo;
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
	public Integer getFMangerID() {
		return fMangerID;
	}
	public void setFMangerID(Integer fMangerID) {
		this.fMangerID = fMangerID;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
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
	public Date getFPayDate() {
		return fPayDate;
	}
	public void setFPayDate(Date fPayDate) {
		this.fPayDate = fPayDate;
	}
	public String getFPayStyle() {
		return fPayStyle;
	}
	public void setFPayStyle(String fPayStyle) {
		this.fPayStyle = fPayStyle;
	}
	public Integer getFInvoiceClosed() {
		return fInvoiceClosed;
	}
	public void setFInvoiceClosed(Integer fInvoiceClosed) {
		this.fInvoiceClosed = fInvoiceClosed;
	}
	public Integer getFTranType() {
		return fTranType;
	}
	public void setFTranType(Integer fTranType) {
		this.fTranType = fTranType;
	}
	public String getTransTypeName() {
		return transTypeName;
	}
	public void setTransTypeName(String transTypeName) {
		this.transTypeName = transTypeName;
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
	public Integer getFStatus() {
		return fStatus;
	}
	public void setFStatus(Integer fStatus) {
		this.fStatus = fStatus;
	}	

}
