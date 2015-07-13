/**
 * 
 */
package com.sales.workflow;

/**
 * 工作流异常
 * @author Administrator
 *
 */
public class WorkFlowException extends RuntimeException {
	private String taskId;
	private String workflowRole;
	
	/**
	 * 发生异常的工作流id
	 * @return the taskId
	 */
	public String getTaskId() {
		return taskId;
	}

	/**
	 * 发生异常的工作流提交角色
	 * @return the workflowRole
	 */
	public String getWorkflowRole() {
		return workflowRole;
	}

	public WorkFlowException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public WorkFlowException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public WorkFlowException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public WorkFlowException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public WorkFlowException(String message, String taskId, String workflowRole) {
		super(message);
		this.taskId = taskId;
		this.workflowRole = workflowRole;
	}

	
}
