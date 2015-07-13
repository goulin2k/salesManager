package com.sales.model;

import com.sales.common.DisplayAnnotation;
  
/**
 * 销售收款单单据体
 */
public class TReceiveBillEntry {
	
	private Integer fClassID_SRC;
	private String fClassID_SRC_FNDName;
	private String fBillNo_SRC;
	private Integer fEntryID_SRC;
	private String fEntryContractNo;
	private String fEntryOrderNo;
	private String fReceiveCyName;
	private Double fReceiveAmountFor_3;
	private Double fReceiveAmount_3;
	private Double fBackAmountFor_Relative;
	private String fSettleCyName;
	@DisplayAnnotation(name="结算数量")
	private Double fSettleQuantity;
	private Double fSettleAmountFor_3;
	private Double fSettleAmount_3;
	private Double fDiscountFor_3;
	private Double fDiscount;
	private String fEntryAccountID_DSPName;
	private Integer fEntryID3;
	private Integer productID;
	private String productNumber;
	@DisplayAnnotation(name="产品名称")
	private String productName;
	@DisplayAnnotation(name="规格型号")
	private String productModel;
	private Integer fBase2;
	@DisplayAnnotation(name="计量单位")
	private String measureUnitName;
	@DisplayAnnotation(name="数量")
	private Double fQuantity;
	private String fID_SRC;
	private Double fTaxPrice;
	private Double famount_SRC;
	private Double fRemainAmountEntry;
	
	public Integer getFClassID_SRC() {
		return fClassID_SRC;
	}
	public void setFClassID_SRC(Integer fClassIDSRC) {
		fClassID_SRC = fClassIDSRC;
	}
	public String getFClassID_SRC_FNDName() {
		return fClassID_SRC_FNDName;
	}
	public void setFClassID_SRC_FNDName(String fClassIDSRCFNDName) {
		fClassID_SRC_FNDName = fClassIDSRCFNDName;
	}
	public String getFBillNo_SRC() {
		return fBillNo_SRC;
	}
	public void setFBillNo_SRC(String fBillNoSRC) {
		fBillNo_SRC = fBillNoSRC;
	}
	public Integer getFEntryID_SRC() {
		return fEntryID_SRC;
	}
	public void setFEntryID_SRC(Integer fEntryIDSRC) {
		fEntryID_SRC = fEntryIDSRC;
	}
	public String getFEntryContractNo() {
		return fEntryContractNo;
	}
	public void setFEntryContractNo(String fEntryContractNo) {
		this.fEntryContractNo = fEntryContractNo;
	}
	public String getFEntryOrderNo() {
		return fEntryOrderNo;
	}
	public void setFEntryOrderNo(String fEntryOrderNo) {
		this.fEntryOrderNo = fEntryOrderNo;
	}
	public String getFReceiveCyName() {
		return fReceiveCyName;
	}
	public void setFReceiveCyName(String fReceiveCyName) {
		this.fReceiveCyName = fReceiveCyName;
	}
	public Double getFReceiveAmountFor_3() {
		return fReceiveAmountFor_3;
	}
	public void setFReceiveAmountFor_3(Double fReceiveAmountFor_3) {
		this.fReceiveAmountFor_3 = fReceiveAmountFor_3;
	}
	public Double getFReceiveAmount_3() {
		return fReceiveAmount_3;
	}
	public void setFReceiveAmount_3(Double fReceiveAmount_3) {
		this.fReceiveAmount_3 = fReceiveAmount_3;
	}
	public Double getFBackAmountFor_Relative() {
		return fBackAmountFor_Relative;
	}
	public void setFBackAmountFor_Relative(Double fBackAmountForRelative) {
		fBackAmountFor_Relative = fBackAmountForRelative;
	}
	public String getFSettleCyName() {
		return fSettleCyName;
	}
	public void setFSettleCyName(String fSettleCyName) {
		this.fSettleCyName = fSettleCyName;
	}
	public Double getFSettleQuantity() {
		return fSettleQuantity;
	}
	public void setFSettleQuantity(Double fSettleQuantity) {
		this.fSettleQuantity = fSettleQuantity;
	}
	public Double getFSettleAmountFor_3() {
		return fSettleAmountFor_3;
	}
	public void setFSettleAmountFor_3(Double fSettleAmountFor_3) {
		this.fSettleAmountFor_3 = fSettleAmountFor_3;
	}
	public Double getFSettleAmount_3() {
		return fSettleAmount_3;
	}
	public void setFSettleAmount_3(Double fSettleAmount_3) {
		this.fSettleAmount_3 = fSettleAmount_3;
	}
	public Double getFDiscountFor_3() {
		return fDiscountFor_3;
	}
	public void setFDiscountFor_3(Double fDiscountFor_3) {
		this.fDiscountFor_3 = fDiscountFor_3;
	}
	public Double getFDiscount() {
		return fDiscount;
	}
	public void setFDiscount(Double fDiscount) {
		this.fDiscount = fDiscount;
	}
	public String getFEntryAccountID_DSPName() {
		return fEntryAccountID_DSPName;
	}
	public void setFEntryAccountID_DSPName(String fEntryAccountIDDSPName) {
		fEntryAccountID_DSPName = fEntryAccountIDDSPName;
	}
	public Integer getFEntryID3() {
		return fEntryID3;
	}
	public void setFEntryID3(Integer fEntryID3) {
		this.fEntryID3 = fEntryID3;
	}
	public Integer getProductID() {
		return productID;
	}
	public void setProductID(Integer productID) {
		this.productID = productID;
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
	public String getProductModel() {
		return productModel;
	}
	public void setProductModel(String productModel) {
		this.productModel = productModel;
	}
	public Integer getFBase2() {
		return fBase2;
	}
	public void setFBase2(Integer fBase2) {
		this.fBase2 = fBase2;
	}
	public String getMeasureUnitName() {
		return measureUnitName;
	}
	public void setMeasureUnitName(String measureUnitName) {
		this.measureUnitName = measureUnitName;
	}
	public Double getFQuantity() {
		return fQuantity;
	}
	public void setFQuantity(Double fQuantity) {
		this.fQuantity = fQuantity;
	}
	public String getFID_SRC() {
		return fID_SRC;
	}
	public void setFID_SRC(String fIDSRC) {
		fID_SRC = fIDSRC;
	}
	public Double getFTaxPrice() {
		return fTaxPrice;
	}
	public void setFTaxPrice(Double fTaxPrice) {
		this.fTaxPrice = fTaxPrice;
	}
	public Double getFamount_SRC() {
		return famount_SRC;
	}
	public void setFamount_SRC(Double famountSRC) {
		famount_SRC = famountSRC;
	}
	public Double getFRemainAmountEntry() {
		return fRemainAmountEntry;
	}
	public void setFRemainAmountEntry(Double fRemainAmountEntry) {
		this.fRemainAmountEntry = fRemainAmountEntry;
	}	

}
