/**
 * 
 */
package com.sales.model;

import org.jbpm.api.task.Task;

import com.sales.common.NormalFun;

/**
 * @author apple
 * 审批工作流查看信息类，对所有工作流信息适用
 *
 */
public class ProcessInfo extends BaseObject {
	private String userName;
	private String deptName;
	private String taskId;
	private String typeName;
	private String status;
	private String applyTime;
	private String applyInfo;
	
	
	/**
	 * 
	 */
	public ProcessInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param userName
	 * @param deptName
	 * @param taskId
	 * @param typeName
	 * @param status
	 * @param applyTime
	 * @param applyInfo
	 */
	public ProcessInfo(String userName, String deptName, String taskId,
			String typeName, String status, String applyTime, String applyInfo) {
		super();
		this.userName = userName;
		this.deptName = deptName;
		this.taskId = taskId;
		this.typeName = typeName;
		this.status = status;
		this.applyTime = applyTime;
		this.applyInfo = applyInfo;
	}
	
	/**
	 * @param task
	 * @param leave
	 */
	public ProcessInfo(Task task, SLeave leave, String deptName) {
		this.userName = leave.getUserName();
		this.deptName = deptName;
		this.taskId = task.getId();
		this.typeName = leave.getTypeName();
		this.status = task.getActivityName();
		this.applyTime = NormalFun.formatDateTimeString(task.getCreateTime());
		this.applyInfo = "<p>休假类型：" + typeName + "</p>"
				+ "<p>休假天数：" + leave.getLeaveDays() + "</p>"
				+ "<p>休假日期：" + NormalFun.formatDateString(leave.getStartTime())
				+ " 到 " + NormalFun.formatDateString(leave.getEndTime()) + "</p>"
				+ "<p>休假单号：" + leave.getLeaveCode() + "</p>"
				;
	}
	
	/**
	 * @param task
	 * @param trip
	 * @param deptName
	 */
	public ProcessInfo(Task task, STripApplication trip, String deptName) {
		this.userName = trip.getUserName();
		this.deptName = deptName;
		this.taskId = task.getId();
		this.typeName = "出差申请单";
		this.status = task.getActivityName();
		this.applyTime = NormalFun.formatDateTimeString(task.getCreateTime());
		this.applyInfo = "<p>出差地点：" + trip.getTripLocation() + "</p>"
				+ "<p>出差事由：" + trip.getTripComment() + "</p>"
				+ "<p>出差日期：" + NormalFun.formatDateString(trip.getStartTime())
				+ "<p>到 " + NormalFun.formatDateString(trip.getEndTime()) + "</p>"
				+ "<p>单据号：" + trip.getTripCode() + "</p>"
				;
	}
	
	/**
	 * 加班流程信息构造
	 * @param task
	 * @param overApp
	 * @param deptName
	 */
	public ProcessInfo(Task task, SOvertime overApp, String deptName) {
		this.userName = overApp.getUserName();
		this.deptName = deptName;
		this.taskId = task.getId();
		this.typeName = "加班申请单";
		this.status = task.getActivityName();
		this.applyTime = NormalFun.formatDateTimeString(task.getCreateTime());
		
		this.applyInfo = "<p>加班时间：" + NormalFun.formatDateString(overApp.getOvertimeTime()) + "</p>"
				+ "<p>加班事由：" + overApp.getComment() + "</p>"
				+ "<p>加班时长：" + overApp.getHours() + " 小时</p>"
				+ "<p>加班单号：" + overApp.getOvertimeCode() + "</p>"
				;
	}
	
	/**
	 * 费用报销申请流程构造
	 * @param task
	 * @param expApp
	 * @param deptName
	 */
	public ProcessInfo(Task task, SExpenseApplication expApp, String deptName) {
		this.userName = expApp.getUserName();
		this.deptName = deptName;
		this.taskId = task.getId();
		this.typeName = "费用申请单";
		this.status = task.getActivityName();
		this.applyTime = NormalFun.formatDateTimeString(task.getCreateTime());
		
		this.applyInfo = "<p>借款金额：" + NormalFun.formatCurrency(expApp.getLoanSum().doubleValue()) + "</p>"
				+ "<p>借款用途：" + expApp.getComment() + "</p>"
				+ "<p>借款时间：" + NormalFun.formatDateString(expApp.getExpenseTime()) + "</p>"
				+ "<p>单据号：" + expApp.getExpenseApplicationCode() + "</p>"
				;
	}
	
	/**
	 * 费用报销申请信息构造
	 * @param task
	 * @param exprmb
	 * @param deptName
	 */
	public ProcessInfo(Task task, SExpenseReimbursement exprmb, String deptName) {
		this.userName = exprmb.getUserName();
		this.deptName = deptName;
		this.taskId = task.getId();
		this.typeName = "加班申请单";
		this.status = task.getActivityName();
		this.applyTime = NormalFun.formatDateTimeString(task.getCreateTime());
		
		this.applyInfo = "<p>报销金额：" + NormalFun.formatCurrency(exprmb.getReimbursementSum().doubleValue()) + "</p>"
				+ "<p>报销事由：" + exprmb.getReimbursementComment() + "</p>"
				+ "<p>报销类型：" + exprmb.getTypeName() + " 小时</p>"
				+ "<p>报销单号：" + exprmb.getReimbursementCode() + "</p>"
				;
	}
	
	/**
	 * 车辆维修申请流程信息构造
	 * @param task
	 * @param carApp
	 * @param deptName
	 */
	public ProcessInfo(Task task, SCarRepairApplication carApp, String deptName) {
		this.userName = carApp.getUserName();
		this.deptName = deptName;
		this.taskId = task.getId();
		this.typeName = "加班申请单";
		this.status = task.getActivityName();
		this.applyTime = NormalFun.formatDateTimeString(task.getCreateTime());
		
		this.applyInfo = "<p>预计维修时间：" + NormalFun.formatDateString(carApp.getRepairTime()) + "</p>"
				+ "<p>车牌号码：" + carApp.getPlateNumber() + "</p>"
				+ "<p>是否报保险：" + carApp.getIsInsuranceName() + "</p>"
				+ "<p>维修事由：" + carApp.getRepairComment() + "</p>"
				+ "<p>预计维修费用：" + NormalFun.formatCurrency(carApp.getRepairSum().doubleValue()) + " 元</p>"
				+ "<p>维修申请单号：" + carApp.getCarApplicationCode() + "</p>"
				;
	}
	
	/**
	 * 车辆维修费用报销流程申请信息转换
	 * @param task
	 * @param carRmb
	 * @param deptName
	 */
	public ProcessInfo(Task task, SCarRepairReimbursement carRmb, String deptName) {
		this.userName = carRmb.getUserName();
		this.deptName = deptName;
		this.taskId = task.getId();
		this.typeName = "加班申请单";
		this.status = task.getActivityName();
		this.applyTime = NormalFun.formatDateTimeString(task.getCreateTime());
		
		this.applyInfo =  "<p>车牌号码：" + carRmb.getPlateNumber() + "</p>"
				+ "<p>维修单号：" + carRmb.getCarReimbursementCode() + "</p>"
				+ "<p>维修申请单号：" + (carRmb.getCa()==null ? "" : carRmb.getCa().getCarApplicationCode()) + "</p>"
				+ "<p>维修厂电话：" + carRmb.getRepairFactoryPhone() + "</p>"
				+ "<p>维修事由：" + carRmb.getReimbursementComment() + "</p>"
				+ "<p>维修费用：" + NormalFun.formatCurrency(carRmb.getReimbursementSum().doubleValue()) + " 元</p>"
				;
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
	 * @return the taskId
	 */
	public String getTaskId() {
		return taskId;
	}
	/**
	 * @param taskId the taskId to set
	 */
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	/**
	 * @return the typeName
	 */
	public String getTypeName() {
		return typeName;
	}
	/**
	 * @param typeName the typeName to set
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the applyTime
	 */
	public String getApplyTime() {
		return applyTime;
	}
	/**
	 * @param applyTime the applyTime to set
	 */
	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}
	/**
	 * @return the applyInfo
	 */
	public String getApplyInfo() {
		return applyInfo;
	}
	/**
	 * @param applyInfo the applyInfo to set
	 */
	public void setApplyInfo(String applyInfo) {
		this.applyInfo = applyInfo;
	}
	
	
}
