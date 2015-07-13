/**
 * 
 */
package com.sales.model;

/**
 * @author apple
 *
 */
public class MessageTypeCount extends BaseObject {
	private String planMessageCount;
	private String quoteMessageCount;
	private String customerMessageCount;
	private String salesMessageCount;
	
	
	/**
	 * 
	 */
	public MessageTypeCount() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param planMessageCount
	 * @param quoteMessageCount
	 * @param customerMessageCount
	 * @param salesMessageCoun
	 */
	public MessageTypeCount(String planMessageCount, String quoteMessageCount,
			String customerMessageCount, String salesMessageCount) {
		super();
		this.planMessageCount = planMessageCount;
		this.quoteMessageCount = quoteMessageCount;
		this.customerMessageCount = customerMessageCount;
		this.salesMessageCount = salesMessageCount;
	}
	/**
	 * @return the planMessageCount
	 */
	public String getPlanMessageCount() {
		return planMessageCount;
	}
	/**
	 * @param planMessageCount the planMessageCount to set
	 */
	public void setPlanMessageCount(String planMessageCount) {
		this.planMessageCount = planMessageCount;
	}
	/**
	 * @return the quoteMessageCount
	 */
	public String getQuoteMessageCount() {
		return quoteMessageCount;
	}
	/**
	 * @param quoteMessageCount the quoteMessageCount to set
	 */
	public void setQuoteMessageCount(String quoteMessageCount) {
		this.quoteMessageCount = quoteMessageCount;
	}
	/**
	 * @return the customerMessageCount
	 */
	public String getCustomerMessageCount() {
		return customerMessageCount;
	}
	/**
	 * @param customerMessageCount the customerMessageCount to set
	 */
	public void setCustomerMessageCount(String customerMessageCount) {
		this.customerMessageCount = customerMessageCount;
	}
	/**
	 * @return the salesMessageCount
	 */
	public String getSalesMessageCount() {
		return salesMessageCount;
	}
	/**
	 * @param salesMessageCount the salesMessageCount to set
	 */
	public void setSalesMessageCount(String salesMessageCount) {
		this.salesMessageCount = salesMessageCount;
	}
	
	
	
}
