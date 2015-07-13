/**
 * 
 */
package com.sales.model;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author apple
 *
 */
public class TReceiveBillInfo extends BaseObject {
	private Integer billId;
	private String number;
	private Timestamp billDate;
	private Integer customerId;
	private String customerName;
	private Integer status;
	private Double receiveAmount;
	private Timestamp inserTime;
	/**
	 * @return the billId
	 */
	public Integer getBillId() {
		return billId;
	}
	/**
	 * @param billId the billId to set
	 */
	public void setBillId(Integer billId) {
		this.billId = billId;
	}
	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}
	/**
	 * @param number the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}
	/**
	 * @return the billDate
	 */
	public Timestamp getBillDate() {
		return billDate;
	}
	/**
	 * @param billDate the billDate to set
	 */
	public void setBillDate(Timestamp billDate) {
		this.billDate = billDate;
	}
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
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * @return the receiveAmount
	 */
	public Double getReceiveAmount() {
		return receiveAmount;
	}
	/**
	 * @param receiveAmount the receiveAmount to set
	 */
	public void setReceiveAmount(Double receiveAmount) {
		this.receiveAmount = receiveAmount;
	}
	/**
	 * @return the inserTime
	 */
	public Timestamp getInserTime() {
		return inserTime;
	}
	/**
	 * @param inserTime the inserTime to set
	 */
	public void setInserTime(Timestamp inserTime) {
		this.inserTime = inserTime;
	}
}
