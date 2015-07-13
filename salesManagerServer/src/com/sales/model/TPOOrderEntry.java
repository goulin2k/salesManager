/**
 * 
 */
package com.sales.model;

import java.util.Date;

import com.sales.common.DisplayAnnotation;

/**
 * 采购订单明细
 * @author Administrator
 *
 */
public class TPOOrderEntry extends BaseObject {

	private Integer fInterId;
	private Integer fEntryId;
	private Integer fItemId;
	private String productNumber;
	@DisplayAnnotation(name="产品名称")
	private String productName;
	@DisplayAnnotation(name="型号")
	private String pruductModel;
	@DisplayAnnotation(name="订单数量")
	private Double fQty;
	private Double fCommitQty;
	@DisplayAnnotation(name="单价")
	private Double fPrice;
	
	private Double fAuxQty;
	private Double fAuxCommitQty;
	private Double fAuxPrice;
	private Double allAmount;
	
	private Integer fPlanMode;
	private String planMode;
	
	private Integer fUnitId;
	@DisplayAnnotation(name="计量单位")
	private String unitName;
	private String fSourceBillNo;
	private Integer fSourceInterId;
	private Integer fSourceEntryID;
	private Integer fSourceTranType;
	private String tranTypeName;
	@DisplayAnnotation(name="采购申请数量")
	private Integer requestAmount;
	private String customerName;
	private Integer requestUserId;
	@DisplayAnnotation(name="采购申请人")
	private String requestUserName;
	
	private Double inventoryAmount;
	
	/**
	 * @return the fInterId
	 */
	public Integer getFInterId() {
		return fInterId;
	}
	/**
	 * @param fInterId the fInterId to set
	 */
	public void setFInterId(Integer fInterId) {
		this.fInterId = fInterId;
	}
	/**
	 * @return the fEntryId
	 */
	public Integer getFEntryId() {
		return fEntryId;
	}
	/**
	 * @param fEntryId the fEntryId to set
	 */
	public void setFEntryId(Integer fEntryId) {
		this.fEntryId = fEntryId;
	}
	/**
	 * @return the fItemId
	 */
	public Integer getFItemId() {
		return fItemId;
	}
	/**
	 * @param fItemId the fItemId to set
	 */
	public void setFItemId(Integer fItemId) {
		this.fItemId = fItemId;
	}
	/**
	 * @return the productNumber
	 */
	public String getProductNumber() {
		return productNumber;
	}
	/**
	 * @param productNumber the productNumber to set
	 */
	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber;
	}
	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}
	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	/**
	 * @return the pruductModel
	 */
	public String getPruductModel() {
		return pruductModel;
	}
	/**
	 * @param pruductModel the pruductModel to set
	 */
	public void setPruductModel(String pruductModel) {
		this.pruductModel = pruductModel;
	}
	/**
	 * @return the fQty
	 */
	public Double getFQty() {
		return fQty;
	}
	/**
	 * @param fQty the fQty to set
	 */
	public void setFQty(Double fQty) {
		this.fQty = fQty;
	}
	/**
	 * @return the fCommitQty
	 */
	public Double getFCommitQty() {
		return fCommitQty;
	}
	/**
	 * @param fCommitQty the fCommitQty to set
	 */
	public void setFCommitQty(Double fCommitQty) {
		this.fCommitQty = fCommitQty;
	}
	/**
	 * @return the fPrice
	 */
	public Double getFPrice() {
		return fPrice;
	}
	/**
	 * @param fPrice the fPrice to set
	 */
	public void setFPrice(Double fPrice) {
		this.fPrice = fPrice;
	}
	/**
	 * @return the fAuxQty
	 */
	public Double getFAuxQty() {
		return fAuxQty;
	}
	/**
	 * @param fAuxQty the fAuxQty to set
	 */
	public void setFAuxQty(Double fAuxQty) {
		this.fAuxQty = fAuxQty;
	}
	/**
	 * @return the fAuxCommitQty
	 */
	public Double getFAuxCommitQty() {
		return fAuxCommitQty;
	}
	/**
	 * @param fAuxCommitQty the fAuxCommitQty to set
	 */
	public void setFAuxCommitQty(Double fAuxCommitQty) {
		this.fAuxCommitQty = fAuxCommitQty;
	}
	/**
	 * @return the fAuxPrice
	 */
	public Double getFAuxPrice() {
		return fAuxPrice;
	}
	/**
	 * @param fAuxPrice the fAuxPrice to set
	 */
	public void setFAuxPrice(Double fAuxPrice) {
		this.fAuxPrice = fAuxPrice;
	}
	/**
	 * @return the allAmount
	 */
	public Double getAllAmount() {
		return allAmount;
	}
	/**
	 * @param allAmount the allAmount to set
	 */
	public void setAllAmount(Double allAmount) {
		this.allAmount = allAmount;
	}
	/**
	 * @return the fPlanMode
	 */
	public Integer getFPlanMode() {
		return fPlanMode;
	}
	/**
	 * @param fPlanMode the fPlanMode to set
	 */
	public void setFPlanMode(Integer fPlanMode) {
		this.fPlanMode = fPlanMode;
	}
	/**
	 * @return the planMode
	 */
	public String getPlanMode() {
		return planMode;
	}
	/**
	 * @param planMode the planMode to set
	 */
	public void setPlanMode(String planMode) {
		this.planMode = planMode;
	}
	/**
	 * @return the fUnitId
	 */
	public Integer getFUnitId() {
		return fUnitId;
	}
	/**
	 * @param fUnitId the fUnitId to set
	 */
	public void setFUnitId(Integer fUnitId) {
		this.fUnitId = fUnitId;
	}
	/**
	 * @return the unitName
	 */
	public String getUnitName() {
		return unitName;
	}
	/**
	 * @param unitName the unitName to set
	 */
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	/**
	 * @return the fSourceBillNo
	 */
	public String getFSourceBillNo() {
		return fSourceBillNo;
	}
	/**
	 * @param fSourceBillNo the fSourceBillNo to set
	 */
	public void setFSourceBillNo(String fSourceBillNo) {
		this.fSourceBillNo = fSourceBillNo;
	}
	/**
	 * @return the fSourceInterId
	 */
	public Integer getFSourceInterId() {
		return fSourceInterId;
	}
	/**
	 * @param fSourceInterId the fSourceInterId to set
	 */
	public void setFSourceInterId(Integer fSourceInterId) {
		this.fSourceInterId = fSourceInterId;
	}
	/**
	 * @return the fSourceEntryID
	 */
	public Integer getFSourceEntryID() {
		return fSourceEntryID;
	}
	/**
	 * @param fSourceEntryID the fSourceEntryID to set
	 */
	public void setFSourceEntryID(Integer fSourceEntryID) {
		this.fSourceEntryID = fSourceEntryID;
	}
	/**
	 * @return the fSourceTranType
	 */
	public Integer getFSourceTranType() {
		return fSourceTranType;
	}
	/**
	 * @param fSourceTranType the fSourceTranType to set
	 */
	public void setFSourceTranType(Integer fSourceTranType) {
		this.fSourceTranType = fSourceTranType;
	}
	/**
	 * @return the tranTypeName
	 */
	public String getTranTypeName() {
		return tranTypeName;
	}
	/**
	 * @param tranTypeName the tranTypeName to set
	 */
	public void setTranTypeName(String tranTypeName) {
		this.tranTypeName = tranTypeName;
	}
	/**
	 * @return the requestAmount
	 */
	public Integer getRequestAmount() {
		return requestAmount;
	}
	/**
	 * @param requestAmount the requestAmount to set
	 */
	public void setRequestAmount(Integer requestAmount) {
		this.requestAmount = requestAmount;
	}
	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}
	/**
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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
	 * @return the requestUserName
	 */
	public String getRequestUserName() {
		return requestUserName;
	}
	/**
	 * @param requestUserName the requestUserName to set
	 */
	public void setRequestUserName(String requestUserName) {
		this.requestUserName = requestUserName;
	}
	
	/**
	 * 及时库存
	 * @return
	 */
	public Double getInventoryAmount() {
		return inventoryAmount;
	}
	public void setInventoryAmount(Double inventoryAmount) {
		this.inventoryAmount = inventoryAmount;
	}
}
