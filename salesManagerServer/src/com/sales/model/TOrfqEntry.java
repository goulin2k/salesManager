package com.sales.model;

import com.sales.common.DisplayAnnotation;

/**
 * 销售报价单单据体
 */
public class TOrfqEntry extends BaseObject{
	
	private Integer fEntryID;
	private Integer fInterID;
	private Integer fSourceEntryID;
	private Integer fItemID;
	private String iCNumber;
	@DisplayAnnotation(name="产品名称")
	private String iCItemName;
	@DisplayAnnotation(name="型号")
	private String fModel;
	private String fUnitID;
	@DisplayAnnotation(name="计量单位")
	private String unitName;
	@DisplayAnnotation(name="单价号")
	private Double fPrice;
	@DisplayAnnotation(name="数量")
	private Double fQtyFrom;
	private Double fQtyTo;
	private Double fAuxQtyFrom;
	private Double fAuxQtyTo;
	private Double fAuxPrice;
	@DisplayAnnotation(name="折扣")
	private Double fDescount;
	private String fNote;
	
	private Double fAuxQty;               //数量
	private Double fAuxPriceIncludeTax;   //含税单价
	private Double fAmount;               //金额
	private Double fCess;                 //税率 %
	private Double fDiscountAmt;          //折扣金额
	private Double fAuxTaxPriceDiscount;  //含税折扣后单价（等于含税单价*（1-折扣率））
	private Double fTaxAmount;            //税额	（FAuxPriceIncludeTax-FAuxPrice）* FAuxQty
	private Double fAmountIncludeTax;     //价税合计 FAuxPriceIncludeTax* FAuxQty
	private String fText;
	public Double getFAmountIncludeTax() {
		return fAmountIncludeTax;
	}
	public void setFAmountIncludeTax(Double fAmountIncludeTax) {
		this.fAmountIncludeTax = fAmountIncludeTax;
	}
	public Double getFAuxQty() {
		return fAuxQty;
	}
	public void setFAuxQty(Double fAuxQty) {
		this.fAuxQty = fAuxQty;
	}
	public Double getFAuxPriceIncludeTax() {
		return fAuxPriceIncludeTax;
	}
	public void setFAuxPriceIncludeTax(Double fAuxPriceIncludeTax) {
		this.fAuxPriceIncludeTax = fAuxPriceIncludeTax;
	}
	public Double getFAmount() {
		return fAmount;
	}
	public void setFAmount(Double fAmount) {
		this.fAmount = fAmount;
	}
	public Double getFCess() {
		return fCess;
	}
	public void setFCess(Double fCess) {
		this.fCess = fCess;
	}
	public Double getFDiscountAmt() {
		return fDiscountAmt;
	}
	public void setFDiscountAmt(Double fDiscountAmt) {
		this.fDiscountAmt = fDiscountAmt;
	}
	
	public Double getFAuxTaxPriceDiscount() {
		if(fAuxTaxPriceDiscount == null)
			fAuxTaxPriceDiscount = new Double(0.0);
		return fAuxTaxPriceDiscount;
	}
	
	public void setFAuxTaxPriceDiscount(Double fAuxTaxPriceDiscount) {
		this.fAuxTaxPriceDiscount = fAuxTaxPriceDiscount;
	}
	public Double getFTaxAmount() {
		return fTaxAmount;
	}
	public void setFTaxAmount(Double fTaxAmount) {
		this.fTaxAmount = fTaxAmount;
	}
	public String getFText() {
		return fText;
	}
	public void setFText(String fText) {
		this.fText = fText;
	}
	
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
	public Integer getFSourceEntryID() {
		return fSourceEntryID;
	}
	public void setFSourceEntryID(Integer fSourceEntryID) {
		this.fSourceEntryID = fSourceEntryID;
	}
	public Integer getFItemID() {
		return fItemID;
	}
	public void setFItemID(Integer fItemID) {
		this.fItemID = fItemID;
	}
	public String getICNumber() {
		return iCNumber;
	}
	public void setICNumber(String iCNumber) {
		this.iCNumber = iCNumber;
	}
	public String getICItemName() {
		return iCItemName;
	}
	public void setICItemName(String iCItemName) {
		this.iCItemName = iCItemName;
	}
	public String getFModel() {
		return fModel;
	}
	public void setFModel(String fModel) {
		this.fModel = fModel;
	}
	public String getFUnitID() {
		return fUnitID;
	}
	public void setFUnitID(String fUnitID) {
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
	public Double getFQtyFrom() {
		return fQtyFrom;
	}
	public void setFQtyFrom(Double fQtyFrom) {
		this.fQtyFrom = fQtyFrom;
	}
	public Double getFQtyTo() {
		return fQtyTo;
	}
	public void setFQtyTo(Double fQtyTo) {
		this.fQtyTo = fQtyTo;
	}
	public Double getFAuxQtyFrom() {
		return fAuxQtyFrom;
	}
	public void setFAuxQtyFrom(Double fAuxQtyFrom) {
		this.fAuxQtyFrom = fAuxQtyFrom;
	}
	public Double getFAuxQtyTo() {
		return fAuxQtyTo;
	}
	public void setFAuxQtyTo(Double fAuxQtyTo) {
		this.fAuxQtyTo = fAuxQtyTo;
	}
	public Double getFAuxPrice() {
		return fAuxPrice;
	}
	public void setFAuxPrice(Double fAuxPrice) {
		this.fAuxPrice = fAuxPrice;
	}
	public Double getFDescount() {
		if(fDescount == null)
			fDescount = new Double(0.0);
		return fDescount;
	}
	
	public void setFDescount(Double fDescount) {
		this.fDescount = fDescount;
	}
	public String getFNote() {
		return fNote;
	}
	public void setFNote(String fNote) {
		this.fNote = fNote;
	}

}
