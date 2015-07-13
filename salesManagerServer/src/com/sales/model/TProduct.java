package com.sales.model;

import java.text.DecimalFormat;
import java.util.List;

/**
 * 产品 
 */
public class TProduct {
	
	private Integer fItemId;
	private String fNumber;
	private String fFullNumber;
	private Integer fLevel;
	private Integer fParentID;
	private String fDetail;
	private String fName;
	private String fDeleted;
	private String fShortNumber;
	private String fModel;
	private String fErpClsId;
	private String productPropery;
	private Integer fUnitID;
	private String measureUnitName;
	private List childProductList;
	
	private String minBuy;		//最小起定量
	private String packModel;  //包装规格
	
	public Integer getFUnitID() {
		return fUnitID;
	}
	public void setFUnitID(Integer fUnitID) {
		this.fUnitID = fUnitID;
	}
	public List getChildProductList() {
		return childProductList;
	}
	public void setChildProductList(List childProductList) {
		this.childProductList = childProductList;
	}
	public Integer getFItemId() {
		return fItemId;
	}
	public void setFItemId(Integer fItemId) {
		this.fItemId = fItemId;
	}
	public String getFNumber() {
		return fNumber;
	}
	public void setFNumber(String fNumber) {
		this.fNumber = fNumber;
	}
	public String getFFullNumber() {
		return fFullNumber;
	}
	public void setFFullNumber(String fFullNumber) {
		this.fFullNumber = fFullNumber;
	}
	public Integer getFLevel() {
		return fLevel;
	}
	public void setFLevel(Integer fLevel) {
		this.fLevel = fLevel;
	}
	public Integer getFParentID() {
		return fParentID;
	}
	public void setFParentID(Integer fParentID) {
		this.fParentID = fParentID;
	}
	public String getFDetail() {
		return fDetail;
	}
	public void setFDetail(String fDetail) {
		this.fDetail = fDetail;
	}
	public String getFName() {
		return fName;
	}
	public void setFName(String fName) {
		this.fName = fName;
	}
	public String getFDeleted() {
		return fDeleted;
	}
	public void setFDeleted(String fDeleted) {
		this.fDeleted = fDeleted;
	}
	public String getFShortNumber() {
		return fShortNumber;
	}
	public void setFShortNumber(String fShortNumber) {
		this.fShortNumber = fShortNumber;
	}
	public String getFModel() {
		return fModel;
	}
	public void setFModel(String fModel) {
		this.fModel = fModel;
	}
	public String getFErpClsId() {
		return fErpClsId;
	}
	public void setFErpClsId(String fErpClsId) {
		this.fErpClsId = fErpClsId;
	}
	public String getProductPropery() {
		return productPropery;
	}
	public void setProductPropery(String productPropery) {
		this.productPropery = productPropery;
	}
	public String getMeasureUnitName() {
		return measureUnitName;
	}
	public void setMeasureUnitName(String measureUnitName) {
		this.measureUnitName = measureUnitName;
	}
	/**
	 * @return the minBuy
	 */
	public String getMinBuy() {
		DecimalFormat fmt = new DecimalFormat("0.0");
		String rs = fmt.format(Double.parseDouble(minBuy));
	    return rs;
	}
	/**
	 * @param minBuy the minBuy to set
	 */
	public void setMinBuy(String minBuy) {
		this.minBuy = minBuy;
	}
	/**
	 * @return the packModel
	 */
	public String getPackModel() {
		return packModel;
	}
	/**
	 * @param packModel the packModel to set
	 */
	public void setPackModel(String packModel) {
		this.packModel = packModel;
	}

}
