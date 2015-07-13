package com.sales.model;

import com.sales.common.DisplayAnnotation;

/**
 * 外购产品入库单单据体
 */
public class TStockBillEntry {
	
	private Integer fEntryID;
	private Integer fInterId; 
	private Integer fItemID;
	@DisplayAnnotation(name="产品明后")
	private String productName;
	private String productNumber;
	@DisplayAnnotation(name="规格型号")
	private String productMode;
	private Integer fUnitID;
	@DisplayAnnotation(name="计量单位")
	private String unitName;
	@DisplayAnnotation(name="数量")
	private Double fQty;
	private Double fAmount;
	@DisplayAnnotation(name="批号")
	private String fBatchNo;
	private Integer fSourceTranType;
	private String sourceTranType;
	private Integer fSourceInterId;
	private String fSourceBillNo;
	
	public Integer getFEntryID() {
		return fEntryID;
	}
	public void setFEntryID(Integer fEntryID) {
		this.fEntryID = fEntryID;
	}
	public Integer getFInterId() {
		return fInterId;
	}
	public void setFInterId(Integer fInterId) {
		this.fInterId = fInterId;
	}
	public Integer getFItemID() {
		return fItemID;
	}
	public void setFItemID(Integer fItemID) {
		this.fItemID = fItemID;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductNumber() {
		return productNumber;
	}
	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber;
	}
	public String getProductMode() {
		return productMode;
	}
	public void setProductMode(String productMode) {
		this.productMode = productMode;
	}
	public Integer getFUnitID() {
		return fUnitID;
	}
	public void setFUnitID(Integer fUnitID) {
		this.fUnitID = fUnitID;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public Double getFQty() {
		return fQty;
	}
	public void setFQty(Double fQty) {
		this.fQty = fQty;
	}
	public Double getFAmount() {
		return fAmount;
	}
	public void setFAmount(Double fAmount) {
		this.fAmount = fAmount;
	}
	public String getFBatchNo() {
		return fBatchNo;
	}
	public void setFBatchNo(String fBatchNo) {
		this.fBatchNo = fBatchNo;
	}
	public Integer getFSourceTranType() {
		return fSourceTranType;
	}
	public void setFSourceTranType(Integer fSourceTranType) {
		this.fSourceTranType = fSourceTranType;
	}
	public String getSourceTranType() {
		return sourceTranType;
	}
	public void setSourceTranType(String sourceTranType) {
		this.sourceTranType = sourceTranType;
	}
	public Integer getFSourceInterId() {
		return fSourceInterId;
	}
	public void setFSourceInterId(Integer fSourceInterId) {
		this.fSourceInterId = fSourceInterId;
	}
	public String getFSourceBillNo() {
		return fSourceBillNo;
	}
	public void setFSourceBillNo(String fSourceBillNo) {
		this.fSourceBillNo = fSourceBillNo;
	}

}
