/**
 * 
 */
package com.sales.model;

import org.jbpm.api.task.Task;

/**
 * @author Leo
 *
 */
public class DataProcess extends Page {
	
	private Task task;
	
	private String typeId;
	
	private SLeave leave;
	
	private SExpenseApplication ea;
	
	private SExpenseReimbursement er;
	
	private SOvertime ot;
	
	private STripApplication trip;
	
	private SCarRepairApplication ca;
	
	private SCarRepairReimbursement cr;
	
	private String opType;

    private String startTime;
	
	private String endTime;
	
	private String userName;
	/**
	 * @return the er
	 */
	public SExpenseReimbursement getEr() {
		return er;
	}

	/**
	 * @param er the er to set
	 */
	public void setEr(SExpenseReimbursement er) {
		this.er = er;
	}

	/**
	 * @return the leave
	 */
	public SLeave getLeave() {
		return leave;
	}

	/**
	 * @param leave the leave to set
	 */
	public void setLeave(SLeave leave) {
		this.leave = leave;
	}

	/**
	 * @return the task
	 */
	public Task getTask() {
		return task;
	}

	/**
	 * @param task the task to set
	 */
	public void setTask(Task task) {
		this.task = task;
	}

	/**
	 * @return the typeId
	 */
	public String getTypeId() {
		return typeId;
	}

	/**
	 * @param typeId the typeId to set
	 */
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	/**
	 * @return the ea
	 */
	public SExpenseApplication getEa() {
		return ea;
	}

	/**
	 * @param ea the ea to set
	 */
	public void setEa(SExpenseApplication ea) {
		this.ea = ea;
	}

	/**
	 * @return the ot
	 */
	public SOvertime getOt() {
		return ot;
	}

	/**
	 * @param ot the ot to set
	 */
	public void setOt(SOvertime ot) {
		this.ot = ot;
	}

	/**
	 * @return the trip
	 */
	public STripApplication getTrip() {
		return trip;
	}

	/**
	 * @param trip the trip to set
	 */
	public void setTrip(STripApplication trip) {
		this.trip = trip;
	}

	/**
	 * @return the ca
	 */
	public SCarRepairApplication getCa() {
		return ca;
	}

	/**
	 * @param ca the ca to set
	 */
	public void setCa(SCarRepairApplication ca) {
		this.ca = ca;
	}

	/**
	 * @return the cr
	 */
	public SCarRepairReimbursement getCr() {
		return cr;
	}

	/**
	 * @param cr the cr to set
	 */
	public void setCr(SCarRepairReimbursement cr) {
		this.cr = cr;
	}

	/**
	 * @return the opType
	 */
	public String getOpType() {
		return opType;
	}

	/**
	 * @param opType the opType to set
	 */
	public void setOpType(String opType) {
		this.opType = opType;
	}

	/**
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

}
