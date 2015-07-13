/**
 * 
 */
package com.sales.model;

/**
 * 销售询价单显示扩展
 * @author Administrator
 *
 */
public class SSalesQuotationView extends SSalesQuotation {
	
	private String productName;
	private String productModel;
	private String packModel;
	private String num;
	private String price;
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
	 * @return the productModel
	 */
	public String getProductModel() {
		return productModel;
	}
	/**
	 * @param productModel the productModel to set
	 */
	public void setProductModel(String productModel) {
		this.productModel = productModel;
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
	/**
	 * @return the num
	 */
	public String getNum() {
		return num;
	}
	/**
	 * @param num the num to set
	 */
	public void setNum(String num) {
		this.num = num;
	}
	/**
	 * @return the price
	 */
	public String getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(String price) {
		this.price = price;
	}
	
	
}
