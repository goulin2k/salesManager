/**
 * 
 */
package com.sales.model;

import java.sql.Timestamp;
import java.util.Date;

import com.sales.common.NormalFun;

/**
 * 客户潜力产品实体类
 * @author apple
 *
 */
public class SCustomerPProduct extends BaseObject {

	private Integer potentialProductId;
	private int customerId;
	private String productDescription;
	private String userName;
	private Timestamp lastUpdateDate;
	private boolean isValidate;
	
	private String customerName;
	
	
	
	
	public SCustomerPProduct() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SCustomerPProduct(int potentialProductId, int customerId,
			String product_decription, String userName) {
		super();
		this.potentialProductId = potentialProductId;
		this.customerId = customerId;
		this.productDescription = product_decription;
		this.userName = userName;
		
		this.isValidate = true;
		this.lastUpdateDate = new Timestamp((new Date()).getTime());
	}
	
	/**
	 * 标识字段
	 * @return the potentialProductId
	 */
	public Integer getPotentialProductId() {
		return potentialProductId;
	}
	/**
	 * 标识字段
	 * @param potentialProductId the potentialProductId to set
	 */
	public void setPotentialProductId(Integer potentialProductId) {
		this.potentialProductId = potentialProductId;
	}
	/**
	 * 客户id
	 * @return the customerId
	 */
	public int getCustomerId() {
		return customerId;
	}
	/**
	 * 客户id
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	/**
	 * 客户名称
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * 客户名称
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * 
	 * @return the product_decription
	 */
	public String getProductDescription() {
		return productDescription;
	}
	/**
	 * @param product_decription the product_decription to set
	 */
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	/**
	 * 修改用户名
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * 修改用户名
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 最后修改时间
	 * @return the lastUpdateDate
	 */
	public Timestamp getLastUpdateDate() {
		return lastUpdateDate;
	}
	
	/**
	 * 格式化最后修改时间字符串
	 * @return
	 */
	public String getLastUpdateDateFormat() {
		return NormalFun.formatDateString(lastUpdateDate);
	}
	
	/**
	 * 最后修改时间
	 * @param lastUpdateDate the lastUpdateDate to set
	 */
	public void setLastUpdateDate(Timestamp lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	/**
	 * 是否最新
	 * @return the isValidate
	 */
	public boolean isValidate() {
		return isValidate;
	}
	/**
	 * 是否最新有效
	 * @param isValidate the isValidate to set
	 */
	public void setValidate(boolean isValidate) {
		this.isValidate = isValidate;
	}
	
	
	
}
