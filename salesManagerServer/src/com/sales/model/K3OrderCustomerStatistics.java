/**
 * 
 */
package com.sales.model;

/**
 * 客户产品销售情况统计
 * @author Goulin
 *
 */
public class K3OrderCustomerStatistics extends BaseObject {
	private static final long serialVersionUID = 7127931376482135852L;
	
	private Integer customerId;
	private Integer productId;
	private String  productName;
	private Double	totalQuantity;
	private String unitName;
	
	/**
	 * @return the customerId
	 */
	public Integer getCustomerId() {
		return customerId;
	}
	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	/**
	 * @return the productId
	 */
	public Integer getProductId() {
		return productId;
	}
	/**
	 * @param productId the productId to set
	 */
	public void setProductId(Integer productId) {
		this.productId = productId;
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
	 * @return the totalQuantity
	 */
	public Double getTotalQuantity() {
		return totalQuantity;
	}
	/**
	 * @param totalQuantity the totalQuantity to set
	 */
	public void setTotalQuantity(Double totalQuantity) {
		this.totalQuantity = totalQuantity;
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
	
	
}
