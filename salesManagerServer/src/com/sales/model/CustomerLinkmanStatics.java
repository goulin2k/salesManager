/**
 * 
 */
package com.sales.model;

/**
 * 客户联系人数统计
 * @author Goulin
 *
 */
public class CustomerLinkmanStatics extends BaseObject {

	private int customerId;
	private int linkType;
	private int count;
	/**
	 * @return the customerId
	 */
	public int getCustomerId() {
		return customerId;
	}
	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	/**
	 * @return the linkType
	 */
	public int getLinkType() {
		return linkType;
	}
	/**
	 * @param linkType the linkType to set
	 */
	public void setLinkType(int linkType) {
		this.linkType = linkType;
	}
	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}
	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}
}
