package com.sales.model;

import java.util.Date;

import com.sales.common.DisplayAnnotation;

/**
 * 销售发货通知单单据体
 */
public class TOutStockEntry {

	private Integer fInterID; 
	private Integer fEntryID;
	private Date fDate;
	private String fNote;
	private Integer fItemID;
	@DisplayAnnotation(name="产品名称")
	private String productName;
	private String productNumber;
	@DisplayAnnotation(name="规格型号")
	private String productModel;
	@DisplayAnnotation(name="出库数量")
	private Double fQty;
	@DisplayAnnotation(name="单价")
	private Double fPrice;
	private Integer fUnitID;
	@DisplayAnnotation(name="计量单位")
	private String unitName;
	private Double fAmount;
	private String fOrderBillNo;
	private Integer fOrderInterID;
	private Integer fOrderEntryID;
	private Integer fSourceTranType; 
	private String tranTypeName;
	private Integer fSourceInterId;
	private String fSourceBillNo;
	 
	public Integer getFInterID() {
		return fInterID;
	}
	public void setFInterID(Integer fInterID) {
		this.fInterID = fInterID;
	}
	public Integer getFEntryID() {
		return fEntryID;
	}
	public void setFEntryID(Integer fEntryID) {
		this.fEntryID = fEntryID;
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
	public String getProductModel() {
		return productModel;
	}
	public void setProductModel(String productModel) {
		this.productModel = productModel;
	}
	public Double getFQty() {
		return fQty;
	}
	public void setFQty(Double fQty) {
		this.fQty = fQty;
	}
	public Double getFPrice() {
		return fPrice;
	}
	public void setFPrice(Double fPrice) {
		this.fPrice = fPrice;
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
	public Double getFAmount() {
		return fAmount;
	}
	public void setFAmount(Double fAmount) {
		this.fAmount = fAmount;
	}
	public String getFOrderBillNo() {
		return fOrderBillNo;
	}
	public void setFOrderBillNo(String fOrderBillNo) {
		this.fOrderBillNo = fOrderBillNo;
	}
	public Integer getFOrderInterID() {
		return fOrderInterID;
	}
	public void setFOrderInterID(Integer fOrderInterID) {
		this.fOrderInterID = fOrderInterID;
	}
	public Integer getFOrderEntryID() {
		return fOrderEntryID;
	}
	public void setFOrderEntryID(Integer fOrderEntryID) {
		this.fOrderEntryID = fOrderEntryID;
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
