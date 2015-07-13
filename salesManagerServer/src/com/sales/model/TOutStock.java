package com.sales.model;

import java.util.Date;
import java.util.List;

import com.sales.common.Constants;
import com.sales.common.DisplayAnnotation;

/**
 * 销售发货通知单
 */
public class TOutStock extends BaseObject {
	
	private Integer fInterID;
	@DisplayAnnotation(name="单据编号")
	private String fBillNo;
	@DisplayAnnotation(name="单据日期")
	private Date fDate;
	private String fNote;
	private Integer fSalType;
	private String saleTypeName;
	private Integer fCustID;
	@DisplayAnnotation(name="客户名称")
	private String customerName;
	private Integer fStockID;
	@DisplayAnnotation(name="库房")
	private String stockName;
	private Integer fEmpID;
	@DisplayAnnotation(name="业务员")
	private String employeeName;
	private Integer fCheckerID;
	private String checkerName;
	private Date fCheckDate;
	private Integer fBillerID;
	@DisplayAnnotation(name="制单人")
	private String billerName;
	private Integer fManagerID;
	private String managerName;
	private Integer fClosed;
	private Integer fInvoiceClosed;
	private Integer fTranStatus;
	private Integer fStatus;
	private Integer fCancellation;
	private Integer fSelTranType;
	private String fPOOrdBillNo;
	private String fHeadSelfS0236;
	private String fHeadSelfS0237;
	@DisplayAnnotation(name="出库明细", isChildrenList=true)
	private List outStockEntryList;
	@DisplayAnnotation(name="单据状态")
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
	public String getFNote() {
		return fNote;
	}
	public void setFNote(String fNote) {
		this.fNote = fNote;
	}
	public Integer getFSalType() {
		return fSalType;
	}
	public void setFSalType(Integer fSalType) {
		this.fSalType = fSalType;
	}
	public String getSaleTypeName() {
		return saleTypeName;
	}
	public void setSaleTypeName(String saleTypeName) {
		this.saleTypeName = saleTypeName;
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
	public Integer getFStockID() {
		return fStockID;
	}
	public void setFStockID(Integer fStockID) {
		this.fStockID = fStockID;
	}
	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
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
	public Date getFCheckDate() {
		return fCheckDate;
	}
	public void setFCheckDate(Date fCheckDate) {
		this.fCheckDate = fCheckDate;
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
	public Integer getFInvoiceClosed() {
		return fInvoiceClosed;
	}
	public void setFInvoiceClosed(Integer fInvoiceClosed) {
		this.fInvoiceClosed = fInvoiceClosed;
	}
	public Integer getFTranStatus() {
		return fTranStatus;
	}
	public void setFTranStatus(Integer fTranStatus) {
		this.fTranStatus = fTranStatus;
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
	public Integer getFSelTranType() {
		return fSelTranType;
	}
	public void setFSelTranType(Integer fSelTranType) {
		this.fSelTranType = fSelTranType;
	}
	public String getFPOOrdBillNo() {
		return fPOOrdBillNo;
	}
	public void setFPOOrdBillNo(String fPOOrdBillNo) {
		this.fPOOrdBillNo = fPOOrdBillNo;
	}
	public String getFHeadSelfS0236() {
		return fHeadSelfS0236;
	}
	public void setFHeadSelfS0236(String fHeadSelfS0236) {
		this.fHeadSelfS0236 = fHeadSelfS0236;
	}
	public String getFHeadSelfS0237() {
		return fHeadSelfS0237;
	}
	public void setFHeadSelfS0237(String fHeadSelfS0237) {
		this.fHeadSelfS0237 = fHeadSelfS0237;
	}
	public List getOutStockEntryList() {
		return outStockEntryList;
	}
	public void setOutStockEntryList(List outStockEntryList) {
		this.outStockEntryList = outStockEntryList;
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

}
