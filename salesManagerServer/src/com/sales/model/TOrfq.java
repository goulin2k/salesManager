package com.sales.model;

import java.util.Date; 
import java.util.List; 

import com.sales.common.Constants;
import com.sales.common.DisplayAnnotation;

/**
 * 销售报价单
 */
public class TOrfq extends BaseObject{
	
	private Integer fInterID;
	@DisplayAnnotation(name="单据号")
	private String fBillNo;
	private Integer fCustID;
	@DisplayAnnotation(name="客户名称")
	private String customerName;
	private Integer fBillerID;
	private String billUserName;
	@DisplayAnnotation(name="单据日期")
	private Date fdate;
	private Integer fDeptID;
	private String departName;
	private String fEmpID;
	@DisplayAnnotation(name="业务员")
	private String employeeName;
	private Integer fCurrencyID;
	private String currencyName;
	private String fExchangeRate;
	private String fMangerID;
	private String managerName;
	private Integer fCheckerID;
	private String checkerName;
	private Date fCheckDate;
	private Integer fCancellation;
	
	private Integer fStatus;
	private Integer fTranType;
	private String transType;
	@DisplayAnnotation(name="报价产品列表",isChildrenList=true)
	private List orfqEntryList;
	@DisplayAnnotation(name="审核状态")
	private String statusName; 
	private String cancellationName;	
	private Integer fPayCondition;
	@DisplayAnnotation(name="付款条件")
	private String payCondition;
	private int pageSize;
	private int startNumber;
	private String startTime;
	private String endTime;
	private String customerIds;
	private String verifyStatusName;
	private Integer userId;
	@DisplayAnnotation(name="报价单号")
	private String quotationCode;
	private List quotationList;
	
	//add by goulin
	private Integer invoiceTypeId;		//开票方式
	private Integer transTypeId;		//运输方式
	private String invoiceTypeName; // 开票方式名
	private String transTypeName;		//运输方式名

	public String getInvoiceTypeName() {
		return invoiceTypeName;
	}
	public void setInvoiceTypeName(String invoiceTypeName) {
		this.invoiceTypeName = invoiceTypeName;
	}
	public String getTransTypeName() {
		return transTypeName;
	}
	public void setTransTypeName(String transTypeName) {
		this.transTypeName = transTypeName;
	}
	public Integer getInvoiceTypeId() {
		return invoiceTypeId;
	}
	public void setInvoiceTypeId(Integer invoiceTypeId) {
		this.invoiceTypeId = invoiceTypeId;
	}
	public Integer getTransTypeId() {
		return transTypeId;
	}
	public void setTransTypeId(Integer transTypeId) {
		this.transTypeId = transTypeId;
	}
	public String getCustomerIds() {
		return customerIds;
	}
	public void setCustomerIds(String customerIds) {
		this.customerIds = customerIds;
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
	public Integer getFPayCondition() {
		return fPayCondition;
	}
	public void setFPayCondition(Integer fPayCondition) {
		this.fPayCondition = fPayCondition;
	}
	public String getPayCondition() {
		return payCondition;
	}
	public void setPayCondition(String payCondition) {
		this.payCondition = payCondition;
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
	
	public List getOrfqEntryList() {
		return orfqEntryList;
	}
	public void setOrfqEntryList(List orfqEntryList) {
		this.orfqEntryList = orfqEntryList;
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
	public Integer getFBillerID() {
		return fBillerID;
	}
	public void setFBillerID(Integer fBillerID) {
		this.fBillerID = fBillerID;
	}
	public String getBillUserName() {
		return billUserName;
	}
	public void setBillUserName(String billUserName) {
		this.billUserName = billUserName;
	}
	public Date getFdate() {
		return fdate;
	}
	public void setFdate(Date fdate) {
		this.fdate = fdate;
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
	public String getFEmpID() {
		return fEmpID;
	}
	public void setFEmpID(String fEmpID) {
		this.fEmpID = fEmpID;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
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
	public String getFExchangeRate() {
		return fExchangeRate;
	}
	public void setFExchangeRate(String fExchangeRate) {
		this.fExchangeRate = fExchangeRate;
	}
	public String getFMangerID() {
		return fMangerID;
	}
	public void setFMangerID(String fMangerID) {
		this.fMangerID = fMangerID;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
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
	public Integer getFCancellation() {
		return fCancellation;
	}
	public void setFCancellation(Integer fCancellation) {
		this.fCancellation = fCancellation;
	}
	public Integer getFStatus() {
		return fStatus;
	}
	public void setFStatus(Integer fStatus) {
		this.fStatus = fStatus;
	}
	public Integer getFTranType() {
		return fTranType;
	}
	public void setFTranType(Integer fTranType) {
		this.fTranType = fTranType;
	}
	public String getTransType() {
		return transType;
	}
	public void setTransType(String transType) {
		this.transType = transType;
	}
	public String getVerifyStatusName() {
		if(fStatus != null){
			return Constants.ORFQ_VERIFY.get(fStatus).toString();
		}
		else{
			return "";
		}
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getQuotationCode() {
		return quotationCode;
	}
	public void setQuotationCode(String quotationCode) {
		this.quotationCode = quotationCode;
	}
	public List getQuotationList() {
		return quotationList;
	}
	public void setQuotationList(List quotationList) {
		this.quotationList = quotationList;
	} 

}
