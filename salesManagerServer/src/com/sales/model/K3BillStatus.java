/**
 * 
 */
package com.sales.model;

/**
 * K3业务单据状态实体类
 * @author Administrator
 *
 */
public class K3BillStatus extends BaseObject {
	private static final long serialVersionUID = -5617090621481181394L;
	private int interId;
	private String billNo;
	private Integer status;
	private Integer tranType;
	
	/**
	 * @return the interId
	 */
	public int getInterId() {
		return interId;
	}
	/**
	 * @param interId the interId to set
	 */
	public void setInterId(int interId) {
		this.interId = interId;
	}
	/**
	 * @return the billNo
	 */
	public String getBillNo() {
		return billNo;
	}
	/**
	 * @param billNo the billNo to set
	 */
	public void setBillNo(String billNo) {
		this.billNo = billNo;
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
	 * @return the tranType
	 */
	public Integer getTranType() {
		return tranType;
	}
	/**
	 * @param tranType the tranType to set
	 */
	public void setTranType(Integer tranType) {
		this.tranType = tranType;
	}
	
	
}
