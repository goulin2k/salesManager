package com.sales.model;

import java.util.Date;

import com.sales.common.DisplayAnnotation;

/**
 * 采购申请单单据体
 */
public class TRequestEntry {
	
	private Integer fInterId;
	private Integer fEntryId;
	private Integer fItemId;
	private String productNumber;
	@DisplayAnnotation(name="产品名称")
	private String productName;
	@DisplayAnnotation(name="型号")
	private String pruductModel;
	@DisplayAnnotation(name="数量")
	private Double fQty;
	private Double fCommitQty;
	@DisplayAnnotation(name="单价")
	private Double fPrice;
	private String fUse;
	private Double fAuxQty;
	private Double fAuxCommitQty;
	private Double fAuxPrice;
	private Double fOrderQty;
	private Integer fPlanMode;
	private String planMode;
	private Date fFetchTime;
	private Integer fSupplyID;
	@DisplayAnnotation(name="供应商")
	private String supplierName;
	private Integer fUnitId;
	@DisplayAnnotation(name="计量单位")
	private String unitName;
	private String fSourceBillNo;
	private Integer fSourceInterId;
	private Integer fSourceEntryID;
	private Integer fSourceTranType;
	private String tranTypeName;
	private String fEntrySelfP0129;
	private Integer fEntrySelfP0130;
	private String item130Name;
	private String fEntrySelfP0131;
	private String item131Name;
	
	public Integer getFInterId() {
		return fInterId;
	}
	public void setFInterId(Integer fInterId) {
		this.fInterId = fInterId;
	}
	public Integer getFEntryId() {
		return fEntryId;
	}
	public void setFEntryId(Integer fEntryId) {
		this.fEntryId = fEntryId;
	}
	public Integer getFItemId() {
		return fItemId;
	}
	public void setFItemId(Integer fItemId) {
		this.fItemId = fItemId;
	}
	public String getProductNumber() {
		return productNumber;
	}
	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getPruductModel() {
		return pruductModel;
	}
	public void setPruductModel(String pruductModel) {
		this.pruductModel = pruductModel;
	}
	public Double getFQty() {
		return fQty;
	}
	public void setFQty(Double fQty) {
		this.fQty = fQty;
	}
	public Double getFCommitQty() {
		return fCommitQty;
	}
	public void setFCommitQty(Double fCommitQty) {
		this.fCommitQty = fCommitQty;
	}
	public Double getFPrice() {
		return fPrice;
	}
	public void setFPrice(Double fPrice) {
		this.fPrice = fPrice;
	}
	public String getFUse() {
		return fUse;
	}
	public void setFUse(String fUse) {
		this.fUse = fUse;
	}
	public Double getFAuxQty() {
		return fAuxQty;
	}
	public void setFAuxQty(Double fAuxQty) {
		this.fAuxQty = fAuxQty;
	}
	public Double getFAuxCommitQty() {
		return fAuxCommitQty;
	}
	public void setFAuxCommitQty(Double fAuxCommitQty) {
		this.fAuxCommitQty = fAuxCommitQty;
	}
	public Double getFAuxPrice() {
		return fAuxPrice;
	}
	public void setFAuxPrice(Double fAuxPrice) {
		this.fAuxPrice = fAuxPrice;
	}
	public Double getFOrderQty() {
		return fOrderQty;
	}
	public void setFOrderQty(Double fOrderQty) {
		this.fOrderQty = fOrderQty;
	}
	public Integer getFPlanMode() {
		return fPlanMode;
	}
	public void setFPlanMode(Integer fPlanMode) {
		this.fPlanMode = fPlanMode;
	}
	public String getPlanMode() {
		return planMode;
	}
	public void setPlanMode(String planMode) {
		this.planMode = planMode;
	}
	public Date getFFetchTime() {
		return fFetchTime;
	}
	public void setFFetchTime(Date fFetchTime) {
		this.fFetchTime = fFetchTime;
	}
	public Integer getFSupplyID() {
		return fSupplyID;
	}
	public void setFSupplyID(Integer fSupplyID) {
		this.fSupplyID = fSupplyID;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public Integer getFUnitId() {
		return fUnitId;
	}
	public void setFUnitId(Integer fUnitId) {
		this.fUnitId = fUnitId;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getFSourceBillNo() {
		return fSourceBillNo;
	}
	public void setFSourceBillNo(String fSourceBillNo) {
		this.fSourceBillNo = fSourceBillNo;
	}
	public Integer getFSourceInterId() {
		return fSourceInterId;
	}
	public void setFSourceInterId(Integer fSourceInterId) {
		this.fSourceInterId = fSourceInterId;
	}
	public Integer getFSourceEntryID() {
		return fSourceEntryID;
	}
	public void setFSourceEntryID(Integer fSourceEntryID) {
		this.fSourceEntryID = fSourceEntryID;
	}
	public Integer getFSourceTranType() {
		return fSourceTranType;
	}
	public void setFSourceTranType(Integer fSourceTranType) {
		this.fSourceTranType = fSourceTranType;
	}
	public String getTranTypeName() {
		return tranTypeName;
	}
	public void setTranTypeName(String tranTypeName) {
		this.tranTypeName = tranTypeName;
	}
	public String getFEntrySelfP0129() {
		return fEntrySelfP0129;
	}
	public void setFEntrySelfP0129(String fEntrySelfP0129) {
		this.fEntrySelfP0129 = fEntrySelfP0129;
	}
	public Integer getFEntrySelfP0130() {
		return fEntrySelfP0130;
	}
	public void setFEntrySelfP0130(Integer fEntrySelfP0130) {
		this.fEntrySelfP0130 = fEntrySelfP0130;
	}
	public String getItem130Name() {
		return item130Name;
	}
	public void setItem130Name(String item130Name) {
		this.item130Name = item130Name;
	}
	public String getFEntrySelfP0131() {
		return fEntrySelfP0131;
	}
	public void setFEntrySelfP0131(String fEntrySelfP0131) {
		this.fEntrySelfP0131 = fEntrySelfP0131;
	}
	public String getItem131Name() {
		return item131Name;
	}
	public void setItem131Name(String item131Name) {
		this.item131Name = item131Name;
	}

}
