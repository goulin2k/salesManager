package com.sales.model;

import com.sales.common.DisplayAnnotation;

/**
 * 外购产品出库单单据体
 */
public class TStockBillOutEntry {

	private Integer fInterId; 
	private Integer fEntryID;
	private Integer fItemID;
	@DisplayAnnotation(name="产品名称")
	private String productName;
	private String productNumber;
	@DisplayAnnotation(name="规格型号")
	private String productModel;
	@DisplayAnnotation(name="出库数量")
	private Double fQty;
	private Double fPrice;
	private Double fAmount;
	private Integer fUnitID;
	@DisplayAnnotation(name="计量单位")
	private String unitName;
	private String fOrderBillNo;
	private Integer fInStockID;        //入库单内码
	private String fSourceBillNo;
	private Integer fSourceEntryID;
	private Integer fSourceInterId;
	private Integer fSourceTranType;
	private String tranTypeName;
	
	public Integer getFInterId() {
		return fInterId;
	}
	public void setFInterId(Integer fInterId) {
		this.fInterId = fInterId;
	}
	public Integer getFEntryID() {
		return fEntryID;
	}
	public void setFEntryID(Integer fEntryID) {
		this.fEntryID = fEntryID;
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
	public Double getFAmount() {
		return fAmount;
	}
	public void setFAmount(Double fAmount) {
		this.fAmount = fAmount;
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
	public String getFOrderBillNo() {
		return fOrderBillNo;
	}
	public void setFOrderBillNo(String fOrderBillNo) {
		this.fOrderBillNo = fOrderBillNo;
	}
	public Integer getFInStockID() {
		return fInStockID;
	}
	public void setFInStockID(Integer fInStockID) {
		this.fInStockID = fInStockID;
	}
	public String getFSourceBillNo() {
		return fSourceBillNo;
	}
	public void setFSourceBillNo(String fSourceBillNo) {
		this.fSourceBillNo = fSourceBillNo;
	}
	public Integer getFSourceEntryID() {
		return fSourceEntryID;
	}
	public void setFSourceEntryID(Integer fSourceEntryID) {
		this.fSourceEntryID = fSourceEntryID;
	}
	public Integer getFSourceInterId() {
		return fSourceInterId;
	}
	public void setFSourceInterId(Integer fSourceInterId) {
		this.fSourceInterId = fSourceInterId;
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

}
