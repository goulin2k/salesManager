/**
 * 
 */
package com.sales.model;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.sales.common.Constants;
import com.sales.common.DisplayAnnotation;

/**
 * 采购订单单据头
 * @author Administrator
 *
 */
public class TPOOrder extends BaseObject {
	private int fInterId;
	@DisplayAnnotation(name="单据日期")
	private Date fDate;
	@DisplayAnnotation(name="单据编号")
	private String fBillNo;
	private Integer fTranType;
	private Integer fStatus;
	private Integer fDeptID;
	private String departName;
	private Integer fSupplyID;
	@DisplayAnnotation(name="供应商")
	private String supplierName;
	private Integer requestUserId;
	@DisplayAnnotation(name="申请人")
	private String requsterUserName;
	private Integer fBillerID;
	@DisplayAnnotation(name="制单人")
	private String billerName;
	private Integer fCheckerID;
	private Date fCheckDate;
	private String checkerName;
	private Integer fSelTranType;
	@DisplayAnnotation(name="单据金额")
	private Double totalCostFor;
	private Integer fChildren;
	private Integer fCancellation;
	private Integer fClosed;
	@DisplayAnnotation(name="订单明细", isChildrenList=true)
	private List<TPOOrderEntry> orderEntryList;
	
	private List<K3BillStatus> stockinBillList;
	
	private int pageSize;
	private int startNumber;
	private String customerIds;
	private String startTime;
	private String endTime; 
	
	
	
	/**
	 * @return the fInterId
	 */
	public int getFInterId() {
		return fInterId;
	}
	/**
	 * @param fInterId the fInterId to set
	 */
	public void setFInterId(int fInterId) {
		this.fInterId = fInterId;
	}
	/**
	 * @return the fDate
	 */
	public Date getFDate() {
		return fDate;
	}
	/**
	 * @param fDate the fDate to set
	 */
	public void setFDate(Date fDate) {
		this.fDate = fDate;
	}
	/**
	 * @return the fBillNo
	 */
	public String getFBillNo() {
		return fBillNo;
	}
	/**
	 * @param fBillNo the fBillNo to set
	 */
	public void setFBillNo(String fBillNo) {
		this.fBillNo = fBillNo;
	}
	/**
	 * @return the fTranType
	 */
	public Integer getFTranType() {
		return fTranType;
	}
	/**
	 * @param fTranType the fTranType to set
	 */
	public void setFTranType(Integer fTranType) {
		this.fTranType = fTranType;
	}
	/**
	 * @return the fStatus
	 */
	public Integer getFStatus() {
		return fStatus;
	}
	/**
	 * @param fStatus the fStatus to set
	 */
	public void setFStatus(Integer fStatus) {
		this.fStatus = fStatus;
	}
	
	/**
	 * @return the departName
	 */
	public String getDepartName() {
		return departName;
	}
	/**
	 * @param departName the departName to set
	 */
	public void setDepartName(String departName) {
		this.departName = departName;
	}
	/**
	 * @return the fSupplyID
	 */
	public Integer getFSupplyID() {
		return fSupplyID;
	}
	/**
	 * @param fSupplyID the fSupplyID to set
	 */
	public void setFSupplyID(Integer fSupplyID) {
		this.fSupplyID = fSupplyID;
	}
	/**
	 * @return the supplierName
	 */
	public String getSupplierName() {
		return supplierName;
	}
	/**
	 * @param supplierName the supplierName to set
	 */
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	/**
	 * @return the requestUserId
	 */
	public Integer getRequestUserId() {
		return requestUserId;
	}
	/**
	 * @param requestUserId the requestUserId to set
	 */
	public void setRequestUserId(Integer requestUserId) {
		this.requestUserId = requestUserId;
	}
	/**
	 * @return the requsterUserName
	 */
	public String getRequsterUserName() {
		return requsterUserName;
	}
	/**
	 * @param requsterUserName the requsterUserName to set
	 */
	public void setRequsterUserName(String requsterUserName) {
		this.requsterUserName = requsterUserName;
	}
	/**
	 * @return the fBillerID
	 */
	public Integer getFBillerID() {
		return fBillerID;
	}
	/**
	 * @param fBillerID the fBillerID to set
	 */
	public void setFBillerID(Integer fBillerID) {
		this.fBillerID = fBillerID;
	}
	/**
	 * @return the billerName
	 */
	public String getBillerName() {
		return billerName;
	}
	/**
	 * @param billerName the billerName to set
	 */
	public void setBillerName(String billerName) {
		this.billerName = billerName;
	}
	/**
	 * @return the fCheckerID
	 */
	public Integer getFCheckerID() {
		return fCheckerID;
	}
	/**
	 * @param fCheckerID the fCheckerID to set
	 */
	public void setFCheckerID(Integer fCheckerID) {
		this.fCheckerID = fCheckerID;
	}
	/**
	 * @return the checkerName
	 */
	public String getCheckerName() {
		return checkerName;
	}
	/**
	 * @param checkerName the checkerName to set
	 */
	public void setCheckerName(String checkerName) {
		this.checkerName = checkerName;
	}
	/**
	 * @return the fDeptID
	 */
	public Integer getFDeptID() {
		return fDeptID;
	}
	/**
	 * @param fDeptID the fDeptID to set
	 */
	public void setFDeptID(Integer fDeptID) {
		this.fDeptID = fDeptID;
	}
	/**
	 * @return the fCheckDate
	 */
	public Date getFCheckDate() {
		return fCheckDate;
	}
	/**
	 * @param fCheckDate the fCheckDate to set
	 */
	public void setFCheckDate(Date fCheckDate) {
		this.fCheckDate = fCheckDate;
	}
	/**
	 * @return the fSelTranType
	 */
	public Integer getFSelTranType() {
		return fSelTranType;
	}
	/**
	 * @param fSelTranType the fSelTranType to set
	 */
	public void setFSelTranType(Integer fSelTranType) {
		this.fSelTranType = fSelTranType;
	}
	/**
	 * @return the totalCostFor
	 */
	public Double getTotalCostFor() {
		return totalCostFor;
	}
	/**
	 * @param totalCostFor the totalCostFor to set
	 */
	public void setTotalCostFor(Double totalCostFor) {
		this.totalCostFor = totalCostFor;
	}
	/**
	 * @return the fChildren
	 */
	public Integer getFChildren() {
		return fChildren;
	}
	/**
	 * @param fChildren the fChildren to set
	 */
	public void setFChildren(Integer fChildren) {
		this.fChildren = fChildren;
	}
	/**
	 * @return the fCancellation
	 */
	public Integer getFCancellation() {
		return fCancellation;
	}
	/**
	 * @param fCancellation the fCancellation to set
	 */
	public void setFCancellation(Integer fCancellation) {
		this.fCancellation = fCancellation;
	}
	/**
	 * @return the fClosed
	 */
	public Integer getFClosed() {
		return fClosed;
	}
	/**
	 * @param fClosed the fClosed to set
	 */
	public void setFClosed(Integer fClosed) {
		this.fClosed = fClosed;
	}
	/**
	 * @return the orderEntryList
	 */
	public List<TPOOrderEntry> getOrderEntryList() {
		return orderEntryList;
	}
	/**
	 * @param orderEntryList the orderEntryList to set
	 */
	public void setOrderEntryList(List<TPOOrderEntry> orderEntryList) {
		this.orderEntryList = orderEntryList;
	}
	/**
	 * @return the stockinBillList
	 */
	public List<K3BillStatus> getStockinBillList() {
		return stockinBillList;
	}
	/**
	 * @param stockinBillList the stockinBillList to set
	 */
	public void setStockinBillList(List<K3BillStatus> stockinBillList) {
		this.stockinBillList = stockinBillList;
	}
	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}
	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	/**
	 * @return the startNumber
	 */
	public int getStartNumber() {
		return startNumber;
	}
	/**
	 * @param startNumber the startNumber to set
	 */
	public void setStartNumber(int startNumber) {
		this.startNumber = startNumber;
	}
	/**
	 * @return the customerIds
	 */
	public String getCustomerIds() {
		return customerIds;
	}
	/**
	 * @param customerIds the customerIds to set
	 */
	public void setCustomerIds(String customerIds) {
		this.customerIds = customerIds;
	}
	/**
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}
	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	/**
	 * @return the endTime
	 */
	public String getEndTime() {
		return endTime;
	}
	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	/**
	 * 单据状态名
	 * @return
	 */
	public String getStatusName() {		
		Integer status = fStatus;
		if(status == 2 || status == 3) {			//订单已执
			if(getInstockStatus() == 3)
				status = 5;   //订单已全部执行入库，关闭
			else if(getInstockStatus() == 1)
				status = 4;			//订单部分入库
			else
				status = 3;			//订单已执行，未入库
		}
			
		String inStatusName = (String)Constants.K3_POORDER_STATUS.get(status);
		return inStatusName;
	}
	
	/**
	 * 采购订单关联的入库通知单执行状态
	 * @return
	 */
	private int getInstockStatus() {
		if (stockinBillList.size() == 0)
				return 0;		//全部未入库
		for (Iterator iterator = stockinBillList.iterator(); iterator.hasNext();) {
			K3BillStatus billStatus = (K3BillStatus) iterator.next();
			if(billStatus.getStatus()<3)
				return 1;			//部分入库
		}
		return 3;					//全部入库
	}
	
	/**
	 * 单据作废标识
	 * @return
	 */
	public String getCancellationName() {
		return (String)Constants.K3Cancellation.get(fCancellation);
	}
	
	/**
	 * 单据关闭标识
	 * @return
	 */
	public String getClosedName() {
		return (String)Constants.K3Cancellation.get(fClosed);
	}
	
	
	
}
