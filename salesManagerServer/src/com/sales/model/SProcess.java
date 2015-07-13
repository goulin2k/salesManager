/**
 * 
 */
package com.sales.model;

import java.sql.Timestamp;
import java.util.Date;

import com.sales.common.Constants;

/**
 * @author Leo
 *
 */
public class SProcess extends Page {
	
	private Integer processId;
	
	private Integer processTypeId;
	
	private String processType;
	
	private String deptName;
	
	private Integer userId;
	
	private String userName;
	
	private Timestamp appTime;
	
	private String executionId;
	
	private String detailLink;
	
	private String operation;
	
	private Integer opUserId;
	
    private String startTime;
	
	private String endTime;

	/**
	 * @return the processTypeId
	 */
	public Integer getProcessTypeId() {
		return processTypeId;
	}

	/**
	 * @param processTypeId the processTypeId to set
	 */
	public void setProcessTypeId(Integer processTypeId) {
		this.processTypeId = processTypeId;
	}

	/**
	 * @return the deptName
	 */
	public String getDeptName() {
		return deptName;
	}

	/**
	 * @param deptName the deptName to set
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
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

	/**
	 * @return the appTime
	 */
	public Timestamp getAppTime() {
		return appTime;
	}

	/**
	 * @param appTime the appTime to set
	 */
	public void setAppTime(Timestamp appTime) {
		this.appTime = appTime;
	}

	/**
	 * @return the processId
	 */
	public Integer getProcessId() {
		return processId;
	}

	/**
	 * @param processId the processId to set
	 */
	public void setProcessId(Integer processId) {
		this.processId = processId;
	}

	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * @return the processType
	 */
	public String getProcessType() {
		processType = "";
		if (this.processTypeId != null) {
			if (this.processTypeId.intValue() == Constants.LEAVE_TYPE) {
				processType = "休假单";
			}else if (this.processTypeId.intValue() == Constants.OVERTIME_TYPE) {
				processType = "加班申请单";
			}else if (this.processTypeId.intValue() == Constants.TRIP_APP_TYPE) {
				processType = "出差申请单";
			}else if (this.processTypeId.intValue() == Constants.EXPENSE_APP_TYPE) {
				processType = "费用申请单";
			}else if (this.processTypeId.intValue() == Constants.EXPENSE_REI_TYPE) {
				processType = "费用报销单";
			}else if (this.processTypeId.intValue() == Constants.CAR_APP_TYPE) {
				processType = "车辆维修申请单";
			}else if (this.processTypeId.intValue() == Constants.CAR_REI_TYPE) {
				processType = "车辆维修报销单";
			}
		}
		return processType;
	}

	/**
	 * @return the executionId
	 */
	public String getExecutionId() {
		if (this.processTypeId != null) {
			if (this.processTypeId.intValue() == Constants.LEAVE_TYPE) {
				executionId = "leave.";
			}else if (this.processTypeId.intValue() == Constants.OVERTIME_TYPE) {
				executionId = "overtime.";
			}else if (this.processTypeId.intValue() == Constants.TRIP_APP_TYPE) {
				executionId = "tripApp.";
			}else if (this.processTypeId.intValue() == Constants.EXPENSE_APP_TYPE) {
				executionId = "expenseApp.";
			}else if (this.processTypeId.intValue() == Constants.EXPENSE_REI_TYPE) {
				executionId = "expenseRei.";
			}else if (this.processTypeId.intValue() == Constants.CAR_APP_TYPE) {
				executionId = "carRepairApp.";
			}else if (this.processTypeId.intValue() == Constants.CAR_REI_TYPE) {
				executionId = "carRepairRei.";
			}
			executionId = executionId + String.valueOf(this.processId);
		}
		return executionId;
	}

	/**
	 * @return the detailLink
	 */
	public String getDetailLink() {
		if (this.processTypeId != null) {
			if (this.processTypeId.intValue() == Constants.LEAVE_TYPE) {
				detailLink = "leave!leaveDetail?leaveId=";
			}else if (this.processTypeId.intValue() == Constants.OVERTIME_TYPE) {
				detailLink = "overtime!otDetail?otId=";
			}else if (this.processTypeId.intValue() == Constants.TRIP_APP_TYPE) {
				detailLink = "trip!tripDetail?tripId=";
			}else if (this.processTypeId.intValue() == Constants.EXPENSE_APP_TYPE) {
				detailLink = "expense!eaDetail?eaId=";
			}else if (this.processTypeId.intValue() == Constants.EXPENSE_REI_TYPE) {
				detailLink = "expense!erDetail?erId=";
			}else if (this.processTypeId.intValue() == Constants.CAR_APP_TYPE) {
				detailLink = "car!caDetail?caId=";
			}else if (this.processTypeId.intValue() == Constants.CAR_REI_TYPE) {
				detailLink = "car!crDetail?crId=";
			}
			detailLink = detailLink + String.valueOf(this.processId);
		}
		return detailLink;
	}

	/**
	 * @return the operation
	 */
	public String getOperation() {
		return operation;
	}

	/**
	 * @param operation the operation to set
	 */
	public void setOperation(String operation) {
		this.operation = operation;
	}

	/**
	 * @return the opUserId
	 */
	public Integer getOpUserId() {
		return opUserId;
	}

	/**
	 * @param opUserId the opUserId to set
	 */
	public void setOpUserId(Integer opUserId) {
		this.opUserId = opUserId;
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

}
