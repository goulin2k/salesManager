package com.sales.model;

import java.util.Date;

import com.sales.common.DisplayAnnotation;

/**
 * 销售订单单据体
 */
public class TOrderEntry {
	
	private Integer fEntryID;
	private Integer fInterID;
	private Integer fItemID;
	@DisplayAnnotation(name="产品名称")
	private String productName;
	@DisplayAnnotation(name="型号")
	private String productModel;
	private Integer fUnitID;
	@DisplayAnnotation(name="计量单位")
	private String unitName;
	@DisplayAnnotation(name="单价")
	private Double fPrice; 
	@DisplayAnnotation(name="数量")
	private Double fQty;   
	private Double fCess; 
	private Double fDiscount;
	private Double fDiscountAmount;
	private Double fFinalAmount;
	private Double fAmount;
	private Double fInvoiceQty;
	private String fNote;
	private Integer fSourceEntryID;
	private Date fDate;
	private Date fAdviceConsignDate;
	private Double fCommitQty;
	private Double fStockQty;
	
	public Integer getFEntryID() {
		return fEntryID;
	}
	public void setFEntryID(Integer fEntryID) {
		this.fEntryID = fEntryID;
	}
	public Integer getFInterID() {
		return fInterID;
	}
	public void setFInterID(Integer fInterID) {
		this.fInterID = fInterID;
	}
	public Integer getFItemID() {
		return fItemID;
	}
	public void setFItemID(Integer fItemID) {
		this.fItemID = fItemID;
	}
	public String getProductName() {
		if(productName != null && productName.length() > 12)
			return productName.substring(0, 8) + "...";
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductModel() {
		if(productModel != null && productModel.length() > 12)
			return productModel.substring(0, 8) + "...";
		return productModel;
	}
	public void setProductModel(String productModel) {
		this.productModel = productModel;
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
	public Double getFPrice() {
		return fPrice;
	}
	public void setFPrice(Double fPrice) {
		this.fPrice = fPrice;
	}
	public Double getFQty() {
		return fQty;
	}
	public void setFQty(Double fQty) {
		this.fQty = fQty;
	}
	public Double getFCess() {
		return fCess;
	}
	public void setFCess(Double fCess) {
		this.fCess = fCess;
	}
	public Double getFDiscount() {
		return fDiscount;
	}
	public void setFDiscount(Double fDiscount) {
		this.fDiscount = fDiscount;
	}
	public Double getFDiscountAmount() {
		return fDiscountAmount;
	}
	public void setFDiscountAmount(Double fDiscountAmount) {
		this.fDiscountAmount = fDiscountAmount;
	}
	public Double getFFinalAmount() {
		return fFinalAmount;
	}
	public void setFFinalAmount(Double fFinalAmount) {
		this.fFinalAmount = fFinalAmount;
	}
	public Double getFAmount() {
		return fAmount;
	}
	public void setFAmount(Double fAmount) {
		this.fAmount = fAmount;
	}
	public Double getFInvoiceQty() {
		return fInvoiceQty;
	}
	public void setFInvoiceQty(Double fInvoiceQty) {
		this.fInvoiceQty = fInvoiceQty;
	}
	public String getFNote() {
		return fNote;
	}
	public void setFNote(String fNote) {
		this.fNote = fNote;
	}
	public Integer getFSourceEntryID() {
		return fSourceEntryID;
	}
	public void setFSourceEntryID(Integer fSourceEntryID) {
		this.fSourceEntryID = fSourceEntryID;
	}
	public Date getFDate() {
		return fDate;
	}
	public void setFDate(Date fDate) {
		this.fDate = fDate;
	}
	public Date getFAdviceConsignDate() {
		return fAdviceConsignDate;
	}
	public void setFAdviceConsignDate(Date fAdviceConsignDate) {
		this.fAdviceConsignDate = fAdviceConsignDate;
	}
	public Double getFCommitQty() {
		return fCommitQty;
	}
	public void setFCommitQty(Double fCommitQty) {
		this.fCommitQty = fCommitQty;
	}
	public Double getFStockQty() {
		return fStockQty;
	}
	public void setFStockQty(Double fStockQty) {
		this.fStockQty = fStockQty;
	}

}
