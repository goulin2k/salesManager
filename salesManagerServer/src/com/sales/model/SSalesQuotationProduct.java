package com.sales.model;

import java.math.BigDecimal;

import com.sales.common.DisplayAnnotation;

public class SSalesQuotationProduct extends BaseObject { 
	
    private Integer quotationProductId; 
    private Integer quotationId;  
    private String productCode; 
    @DisplayAnnotation(name="产品名称")
    private String productName; 
    @DisplayAnnotation(name="型号")
    private String productModel; 
    @DisplayAnnotation(name="计量单位")
    private String measureUnitName; 
    @DisplayAnnotation(name="单价")
    private String price; 
    private String num; 
    private BigDecimal total; 
    private String packModel; 
    private String logisticsCost; 
    private String minQty; 
    private String futures; 
    private Integer itemId;
    private Integer unitId;
    private TOrfqEntry orfqEntry;

	public TOrfqEntry getOrfqEntry() {
		if(orfqEntry == null){
			orfqEntry = new TOrfqEntry();
			//数量
			BigDecimal fAuxQty = BigDecimal.valueOf(Double.valueOf(num));
			//单价
			BigDecimal fAuxPrice = BigDecimal.valueOf(Double.valueOf(price));
			//含税单价
			BigDecimal fAuxPriceIncludeTax = fAuxPrice.multiply(BigDecimal.valueOf(1.17));;
			//金额
			BigDecimal fAmount = fAuxQty.multiply(fAuxPrice); 
			//税额
			BigDecimal fTaxAmount = fAuxPriceIncludeTax.subtract(fAuxPrice).multiply(fAuxQty); 
			//价税合计
			BigDecimal fAmountIncludeTax = fAuxPriceIncludeTax.multiply(fAuxQty);
			orfqEntry.setFAuxQty(fAuxQty.doubleValue());
			orfqEntry.setFAuxPrice(fAuxPrice.doubleValue());
			orfqEntry.setFAuxPriceIncludeTax(fAuxPriceIncludeTax.doubleValue());
			orfqEntry.setFAmount(fAmount.doubleValue());
			orfqEntry.setFTaxAmount(fTaxAmount.doubleValue());
			orfqEntry.setFAmountIncludeTax(fAmountIncludeTax.doubleValue());
		}
		return orfqEntry;
	}
	public void setOrfqEntry(TOrfqEntry orfqEntry) {
		this.orfqEntry = orfqEntry;
	}
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public Integer getUnitId() {
		return unitId;
	}
	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}
	public Integer getQuotationProductId() {
		return quotationProductId;
	}
	public void setQuotationProductId(Integer quotationProductId) {
		this.quotationProductId = quotationProductId;
	}
	public Integer getQuotationId() {
		return quotationId;
	}
	public void setQuotationId(Integer quotationId) {
		this.quotationId = quotationId;
	} 
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductModel() {
		return productModel;
	}
	public void setProductModel(String productModel) {
		this.productModel = productModel;
	}
	public String getMeasureUnitName() {
		return measureUnitName;
	}
	public void setMeasureUnitName(String measureUnitName) {
		this.measureUnitName = measureUnitName;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public String getPackModel() {
		return packModel;
	}
	public void setPackModel(String packModel) {
		this.packModel = packModel;
	}
	public String getLogisticsCost() {
		return logisticsCost;
	}
	public void setLogisticsCost(String logisticsCost) {
		this.logisticsCost = logisticsCost;
	}
	public String getMinQty() {
		return minQty;
	}
	public void setMinQty(String minQty) {
		this.minQty = minQty;
	}
	public String getFutures() {
		return futures;
	}
	public void setFutures(String futures) {
		this.futures = futures;
	} 

}