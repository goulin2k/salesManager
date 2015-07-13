/**
 * 
 */
package com.sales.action;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;

import net.sf.json.JSONObject;

import org.jbpm.api.Configuration;
import org.jbpm.api.ExecutionService;
import org.jbpm.api.HistoryService;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.RepositoryService;
import org.jbpm.api.TaskService;
import org.jbpm.api.history.HistoryTask;
import org.jbpm.api.model.ActivityCoordinates;
import org.jbpm.api.task.Task;

import com.sales.common.Constants;
import com.sales.common.LabelStringValueBean;
import com.sales.model.BuzViewObject;
import com.sales.model.DataProcess;
import com.sales.model.SAnnualLeave;
import com.sales.model.SCarRepairApplication;
import com.sales.model.SCarRepairReimbursement;
import com.sales.model.SExpenseApplication;
import com.sales.model.SExpenseReimbursement;
import com.sales.model.SLeave;
import com.sales.model.SOvertime;
import com.sales.model.SOvertimeCollection;
import com.sales.model.SProcess;
import com.sales.model.STripApplication;
import com.sales.service.SCarRepairApplicationService;
import com.sales.service.SCarRepairReimbursementService;
import com.sales.service.SExpenseApplicationService;
import com.sales.service.SExpenseReimbursementService;
import com.sales.service.SLeaveService;
import com.sales.service.SOvertimeService;
import com.sales.service.STripApplicationService;

/**
 * @author Leo
 *
 */
public class ProcessAction extends BaseAction{
	
	private List<Task> taskList;
	
	private String taskId;
	
	private DataProcess process;
	
	private ProcessEngine processEngine = Configuration.getProcessEngine();
	
	private TaskService taskService = Configuration.getProcessEngine().getTaskService();
	
	private HistoryService historyService = Configuration.getProcessEngine().getHistoryService();
	
	private SLeaveService leaveService;
	
	private String processType;
	
	private SExpenseApplicationService expenseApplicationService;
	
	private SExpenseReimbursementService expenseReimbursementService;
	
	private SOvertimeService overtimeService;
	
	private STripApplicationService tripApplicationService;
	
    private SCarRepairApplicationService carRepairApplicationService;
	
	private SCarRepairReimbursementService carRepairReimbursementService;
	
	private String comment;
	
	private List<SProcess> processList;
	
	private String id;
	
	private SLeave leave;
	

	
	public String approval() {
		Task task = taskService.getTask(taskId);
		String processInstanceId = task.getExecutionId();
		Integer receiptId = this.getReceiptId(task);
		
		String deptName = (String) taskService.getVariable(taskId, "deptName");
		Integer userId = Integer.valueOf((String)taskService.getVariable(taskId, "userId"));
		
	    String result = "";
	    if ("1".equals(this.processType)) {
	    	result = "批准";
	    }else {
			result = "驳回";
	    }
		taskService.completeTask(taskId, result);	
		
		//保存驳回或批准备注
		if ("1".equals(this.processType)) {
			if (this.comment != null && !"".equals(this.comment.trim())) {
			    taskService.addTaskComment(taskId, task.getActivityName()+"批准意见："+comment);
			}
		}else {
			if (this.comment != null && !"".equals(this.comment.trim())) {
			    taskService.addTaskComment(taskId, task.getActivityName()+"驳回原因："+comment);
			}
			//如果是调休被驳回，需把加班时数恢复
			if (processInstanceId.startsWith("leave.")) {
				SLeave leave =  leaveService.getLeaveReceipt(receiptId);
				if (leave.getTypeId().intValue() == 7) {
					SOvertimeCollection oc = overtimeService.getOtCollection(leave.getUserId());
					oc.setHoursCollection(oc.getHoursCollection().floatValue() + leave.getLeaveDays().floatValue());
					overtimeService.updateOvertimeCollection(oc);
				}
			}
			//如果是年假被驳回，需把年假时数恢复
			if (processInstanceId.startsWith("leave.")) {
				SLeave leave =  leaveService.getLeaveReceipt(receiptId);
				if (leave.getTypeId().intValue() == 3) {
					SAnnualLeave sal = leaveService.getAnnualLeave(leave.getUserId());
					sal.setAnnualHours(sal.getAnnualHours().intValue() + leave.getLeaveDays().intValue());
					this.leaveService.updateAnnualLeaveHours(sal);
				}
			}
		}
		
		//保存审批历史记录
	    SProcess sp = new SProcess();
	    sp.setProcessId(receiptId);
	    if (processInstanceId.startsWith("leave.")) {
	    	sp.setProcessTypeId(1);
		} else if (processInstanceId.startsWith("overtime.")) {
			sp.setProcessTypeId(2);
		} else if (processInstanceId.startsWith("tripApp.")) {
			sp.setProcessTypeId(3);
		} else if (processInstanceId.startsWith("expenseApp.")) {
			sp.setProcessTypeId(4);
		} else if (processInstanceId.startsWith("expenseRei.")) {
			sp.setProcessTypeId(5);
		}  else if (processInstanceId.startsWith("carRepairApp.")) {
			sp.setProcessTypeId(6);
		} else if (processInstanceId.startsWith("carRepairRei.")) {
			sp.setProcessTypeId(7);
		} 
	    sp.setDeptName(deptName);
	    sp.setUserId(userId);
	    sp.setOperation(result);
	    sp.setAppTime(new Timestamp(
	    			(new Date()).getTime()));
	    sp.setOpUserId(this.getLoginUser().getUserId());
	    this.leaveService.insertProcessHistory(sp);
		
		
		ProcessInstance processInstance = processEngine.getExecutionService().findProcessInstanceById(processInstanceId);
		if (processInstance == null) {
			
			if (processInstanceId.startsWith("leave.")) {
				SLeave leave = new SLeave();
				leave.setLeaveId(receiptId);
				leave.setStatus(1);
				leaveService.updateLeaveReceiptById(leave);
			} else if (processInstanceId.startsWith("overtime.")) {
				SOvertime ot = new SOvertime();
				ot.setOvertimeId(receiptId);
				ot.setStatus(1);
				overtimeService.updateOvertimeReceiptById(ot);
				SOvertime overtime = overtimeService.getOvertimeReceipt(receiptId);
				SOvertimeCollection otc = new SOvertimeCollection();
				otc.setUserId(overtime.getUserId());
				otc.setHoursCollection(overtime.getHours());
				overtimeService.updateUserOvertimeCollection(otc);
				
			} else if (processInstanceId.startsWith("expenseApp.")) {
				SExpenseApplication ea = new SExpenseApplication();
				ea.setExpenseApplicationId(receiptId);
				ea.setStatus(1);
				expenseApplicationService.updateExpenseApplicationById(ea);
			} else if (processInstanceId.startsWith("expenseRei.")) {
				SExpenseReimbursement er = new SExpenseReimbursement();
				er.setReimbursementId(receiptId);
				er.setStatus(1);
				expenseReimbursementService.updateExpenseReimbursementById(er);
			} else if (processInstanceId.startsWith("tripApp.")) {
				STripApplication ta = new STripApplication();
				ta.setTripId(receiptId);
				ta.setStatus(1);
				tripApplicationService.updateTripApplicationById(ta);
			} else if (processInstanceId.startsWith("carRepairApp.")) {
				SCarRepairApplication ca = new SCarRepairApplication();
				ca.setCarApplicationId(receiptId);
				ca.setStatus(1);
				carRepairApplicationService.updateCarApplicationById(ca);
			} else if (processInstanceId.startsWith("carRepairRei.")) {
				SCarRepairReimbursement cr = new SCarRepairReimbursement();
				cr.setCarReimbursementId(receiptId);
				cr.setStatus(1);
				carRepairReimbursementService.updateCarReimbursementById(cr);
			} 
		} else {
		}
		
		return "toProcessList";
	}

	public String processList() {
		if (this.process == null) {
			this.process = new DataProcess();
		}
		if (this.process.getPageNo() == null) {
			this.process.setPageNo(1);
		}
		this.process.setPageSize(Constants.DEFAULT_PAGE_SIZE);
		List<HistoryTask> htList = historyService.createHistoryTaskQuery().state("completed").assignee(String.valueOf(this.getLoginUser().getUserId())).list();
		//根据用户ID查找该用户指定的待办任务
		if (this.process.getOpType() == null) {
			this.process.setOpType("1");
		}
		if ("1".equals(this.process.getOpType())) {
			if (this.process.getTypeId() == null || "-1".equals(this.process.getTypeId())) {
				this.process.setTotalCount((int)taskService.createTaskQuery().assignee(String.valueOf(this.getLoginUser().getUserId())).count());
				this.taskList = taskService.createTaskQuery().assignee(String.valueOf(this.getLoginUser().getUserId())).
				page(this.process.getPageStart(), this.process.getPageEnd()).list();
			} else {
				this.process.setTotalCount((int)taskService.createTaskQuery().processDefinitionId(this.process.getTypeId()).assignee(String.valueOf(this.getLoginUser().getUserId())).count());
//				this.process.setTotalCount((int)taskService.createTaskQuery().processInstanceId(this.process.getTypeId()).assignee(String.valueOf(this.getLoginUser().getUserId())).count());
//	            this.process.setTotalCount((int)taskService.createTaskQuery().executionId(this.process.getTypeId()).assignee(String.valueOf(this.getLoginUser().getUserId())).count());
				this.taskList = taskService.createTaskQuery().processDefinitionId(this.process.getTypeId()).assignee(String.valueOf(this.getLoginUser().getUserId())).
				page(this.process.getPageStart(), this.process.getPageEnd()).list();
			}
		}else { 
			SProcess sp = new SProcess();
			sp.setPageNo(this.process.getPageNo());
			if (sp.getPageNo() == null) {
				sp.setPageNo(1);
			}
			sp.setPageSize(this.process.getPageSize());
        	if ("leave-1".equals(this.process.getTypeId())) {
        		sp.setProcessTypeId(1);
        	}else if ("overtime-1".equals(this.process.getTypeId())) {
        		sp.setProcessTypeId(2);
        	}
        	else if ("tripApp-1".equals(this.process.getTypeId())) {
        		sp.setProcessTypeId(3);
        	}
        	else if ("expenseApp-1".equals(this.process.getTypeId())) {
        		sp.setProcessTypeId(4);
        	}
        	else if ("expenseRei-1".equals(this.process.getTypeId())) {
        		sp.setProcessTypeId(5);
        	}
        	else if ("carRepairApp-1".equals(this.process.getTypeId())) {
        		sp.setProcessTypeId(6);
        	}
        	else if ("carRepairRei-1".equals(this.process.getTypeId())) {
        		sp.setProcessTypeId(7);
        	}
			if ("2".equals(this.process.getOpType())) {
				sp.setOpUserId(this.getLoginUser().getUserId());
				sp.setUserName(this.process.getUserName());
				sp.setStartTime(this.process.getStartTime());
				sp.setEndTime(this.process.getEndTime());
				this.process.setTotalCount(this.leaveService.getProcessHistoryListPageCount(sp));
	        	this.processList = this.leaveService.getProcessHistoryListPage(sp);
	        } else if ("3".equals(this.process.getOpType())) {
	        	sp.setUserId(this.getLoginUser().getUserId());
	        	this.process.setTotalCount(this.leaveService.getProcessListPageCount(sp));
	        	this.processList = this.leaveService.getProcessListPage(sp);
	        }
		}
		
		
		return "processList";
	}
	
	public String toApprovalLeave() {
		Task task = taskService.getTask(taskId);
		
		if (task == null) {
			this.leave = this.leaveService.getLeaveReceipt(Integer.valueOf(id));
			return "leaveDetail";
		}
		Integer leaveId = this.getReceiptId(task);
		SLeave leave = this.leaveService.getLeaveReceipt(leaveId);
		this.process = new DataProcess();
		this.process.setLeave(leave);
		this.process.setTask(task);
		return "toApprovalLeave";
	}
	

	private SExpenseApplication ea;
	public String toApprovalEa() {
		Task task = taskService.getTask(taskId);
		if (task == null) {
			this.ea = this.expenseApplicationService.getExpenseApplication(Integer.valueOf(id));
			return "eaDetail";
		}
		Integer eaId = this.getReceiptId(task);
		SExpenseApplication ea = this.expenseApplicationService.getExpenseApplication(eaId);
		this.process = new DataProcess();
		this.process.setEa(ea);
		this.process.setTask(task);
		return "toApprovalEa";
	}
	
	private SExpenseReimbursement er;
	public String toApprovalEr() {
		Task task = taskService.getTask(taskId);
        if (task == null) {
        	this.er = this.expenseReimbursementService.getExpenseReimbursement(Integer.valueOf(id));
    		return "erDetail";
		}
		Integer erId = this.getReceiptId(task);
		SExpenseReimbursement er = this.expenseReimbursementService.getExpenseReimbursement(erId);
		this.process = new DataProcess();
		this.process.setEr(er);
		this.process.setTask(task);
		return "toApprovalEr";
	}
	
	private SOvertime ot;
	public String toApprovalOvertime() {
		Task task = taskService.getTask(taskId);
        if (task == null) {
        	this.ot = this.overtimeService.getOvertimeReceipt(Integer.valueOf(id));
    		return "overtimeDetail";
		}
		Integer otId = this.getReceiptId(task);
		SOvertime ot = this.overtimeService.getOvertimeReceipt(otId);
		this.process = new DataProcess();
		this.process.setOt(ot);
		this.process.setTask(task);
		return "toApprovalOt";
	}
	
	private STripApplication tripApplication;
	public String toApprovalTrip() {
		Task task = taskService.getTask(taskId);
        if (task == null) {
        	this.tripApplication = this.tripApplicationService.getTripApplication(Integer.valueOf(id));
    		return "tripDetail";
		}
		Integer tripId = this.getReceiptId(task);
		STripApplication ta = this.tripApplicationService.getTripApplication(tripId);
		this.process = new DataProcess();
		this.process.setTrip(ta);
		this.process.setTask(task);
		return "toApprovalTrip";
	}
	
	
	private SCarRepairApplication ca;
	public String toApprovalCarApp() {
		Task task = taskService.getTask(taskId);
        if (task == null) {
        	this.ca = this.carRepairApplicationService.getCarApplication(Integer.valueOf(id));
    		return "caDetail";
		}
		Integer caId = this.getReceiptId(task);
		SCarRepairApplication ca = this.carRepairApplicationService.getCarApplication(caId);
		this.process = new DataProcess();
		this.process.setCa(ca);
		this.process.setTask(task);
		return "toApprovalCarApp";
	}
	
	private SCarRepairReimbursement cr;
	public String toApprovalCarRei() {
		Task task = taskService.getTask(taskId);
        if (task == null) {
        	this.cr = this.carRepairReimbursementService.getCarReimbursement(Integer.valueOf(id));
    		return "crDetail";
		}
		Integer crId = this.getReceiptId(task);
		SCarRepairReimbursement cr = this.carRepairReimbursementService.getCarReimbursement(crId);
		this.process = new DataProcess();
		this.process.setCr(cr);
		this.process.setTask(task);
		return "toApprovalCarRei";
	}
	
	
	private Integer getReceiptId(Task task) {
		String executionId = task.getExecutionId();
		String tempId = executionId.substring(executionId.indexOf(".")+1);
		return Integer.valueOf(tempId);
	}
	
	/**
	 * 显示流程申请单详细信息（json）
	 */
	public String showDetail() {
		BuzViewObject buzObject = new BuzViewObject();
		List<LabelStringValueBean> lvs = null;
		Object res = null;
		Integer repId = Integer.parseInt(request.getParameter("taskId"));
		int type = Integer.parseInt(request.getParameter("typeId"));
		
		switch(type) {
		case 1:
			res =  this.leaveService.getLeaveReceipt(repId);
			lvs = ((SLeave)res).toLabelValues();
			buzObject.setTitle("请假申请单");
			
			break;
		case 2:
			res = this.tripApplicationService.getTripApplication(repId);
			lvs = ((STripApplication)res).toLabelValues();
			buzObject.setTitle("出差申请单");
			break;
		case 3:
			res = this.overtimeService.getOvertimeReceipt(repId);
			lvs = ((SOvertime)res).toLabelValues();
			buzObject.setTitle("加班申请单");
			break;
		case 4:
			res = this.expenseApplicationService.getExpenseApplication(repId);
			lvs = ((SExpenseApplication)res).toLabelValues();
			buzObject.setTitle("费用申请单");
			break;
		case 5:
			break;
		case 6:
			res = this.carRepairApplicationService.getCarApplication(repId);
			lvs = ((SCarRepairApplication)res).toLabelValues();
			buzObject.setTitle("车辆维修申请单");
			break;
		case 7:
			res = this.carRepairReimbursementService.getCarReimbursement(repId);
			lvs = ((SCarRepairReimbursement)res).toLabelValues();
			buzObject.setTitle("车辆维修报销申请单");
			break;
		default:
			actionLog.error("ERROR PROC TYPE:" + type);
		}
		buzObject.setData(lvs);
		JSONObject json = JSONObject.fromObject(buzObject);	
		writeJsonP(json);
		return null;
	}
	
	public void showProcessPic() {
//		ProcessEngine processEngine = Configuration.getProcessEngine();
//		RepositoryService repositoryService = processEngine
//				.getRepositoryService();
//		ExecutionService executionService = processEngine
//				.getExecutionService();
//		String id = request.getParameter("id");
//		ProcessInstance processInstance = executionService
//				.findProcessInstanceById(id);
//		String processDefinitionId = processInstance
//				.getProcessDefinitionId();
//		ProcessDefinition processDefinition = repositoryService
//				.createProcessDefinitionQuery().processDefinitionId(
//						processDefinitionId).uniqueResult();
//		String picName = id.substring(0,id.indexOf("."));
//		InputStream inputStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(),picName);
//		byte[] b = new byte[1024];
//		int len = -1;
//		try {
//			while ((len = inputStream.read(b, 0, 1024)) != -1) {
//				this.response.getOutputStream().write(b, 0, len);
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		ProcessEngine processEngine = Configuration.getProcessEngine();
    	ExecutionService executionService = processEngine.getExecutionService();
    	RepositoryService repositoryService = processEngine.getRepositoryService();
    	String pid = request.getParameter("id");
		ProcessInstance processInstance = executionService
		.findProcessInstanceById(pid);

		String processDefinitionId = processInstance.getProcessDefinitionId();
		ProcessDefinition processDefinition = repositoryService
		.createProcessDefinitionQuery().processDefinitionId(
		processDefinitionId).uniqueResult();

		Set<String> activityNames = processInstance.findActiveActivityNames();
		BufferedImage image = null;
		try {
			for (String activityName : activityNames) {
			    ActivityCoordinates ac = repositoryService.getActivityCoordinates(
				processInstance.getProcessDefinitionId(), activityName);
	
			    InputStream is = repositoryService.getResourceAsStream(
				processDefinition.getDeploymentId(), processDefinition
				.getImageResourceName());
	
			    image = ImageIO.read(is);
			    Graphics g = image.getGraphics();
			    g.setColor(new Color(255, 0, 0));
	
			    g.drawRect(ac.getX(), ac.getY(), ac.getWidth(), ac.getHeight());
			    g.dispose();
			}
	
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			ImageIO.write(image, "PNG", output);
			ByteArrayInputStream inputStream = new ByteArrayInputStream(output
			.toByteArray());
			byte[] b = new byte[1024];
			int len = -1;
		
			while ((len = inputStream.read(b, 0, 1024)) != -1) {
				this.response.getOutputStream().write(b, 0, len);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @return the taskList
	 */
	public List<Task> getTaskList() {
		return taskList;
	}

	/**
	 * @param taskList the taskList to set
	 */
	public void setTaskList(List<Task> taskList) {
		this.taskList = taskList;
	}

	/**
	 * @return the process
	 */
	public DataProcess getProcess() {
		return process;
	}

	/**
	 * @param process the process to set
	 */
	public void setProcess(DataProcess process) {
		this.process = process;
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
	 * @param leaveService the leaveService to set
	 */
	public void setLeaveService(SLeaveService leaveService) {
		this.leaveService = leaveService;
	}

	/**
	 * @return the processType
	 */
	public String getProcessType() {
		return processType;
	}

	/**
	 * @param processType the processType to set
	 */
	public void setProcessType(String processType) {
		this.processType = processType;
	}

	/**
	 * @param expenseApplicationService the expenseApplicationService to set
	 */
	public void setExpenseApplicationService(SExpenseApplicationService expenseApplicationService) {
		this.expenseApplicationService = expenseApplicationService;
	}

	/**
	 * @param expenseReimbursementService the expenseReimbursementService to set
	 */
	public void setExpenseReimbursementService(SExpenseReimbursementService expenseReimbursementService) {
		this.expenseReimbursementService = expenseReimbursementService;
	}

	/**
	 * @param overtimeService the overtimeService to set
	 */
	public void setOvertimeService(SOvertimeService overtimeService) {
		this.overtimeService = overtimeService;
	}

	/**
	 * @param tripApplicationService the tripApplicationService to set
	 */
	public void setTripApplicationService(STripApplicationService tripApplicationService) {
		this.tripApplicationService = tripApplicationService;
	}

	/**
	 * @param carRepairApplicationService the carRepairApplicationService to set
	 */
	public void setCarRepairApplicationService(SCarRepairApplicationService carRepairApplicationService) {
		this.carRepairApplicationService = carRepairApplicationService;
	}

	/**
	 * @param carRepairReimbursementService the carRepairReimbursementService to set
	 */
	public void setCarRepairReimbursementService(SCarRepairReimbursementService carRepairReimbursementService) {
		this.carRepairReimbursementService = carRepairReimbursementService;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return the processList
	 */
	public List<SProcess> getProcessList() {
		return processList;
	}

	/**
	 * @param processList the processList to set
	 */
	public void setProcessList(List<SProcess> processList) {
		this.processList = processList;
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
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

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
	 * @return the tripApplication
	 */
	public STripApplication getTripApplication() {
		return tripApplication;
	}

	/**
	 * @param tripApplication the tripApplication to set
	 */
	public void setTripApplication(STripApplication tripApplication) {
		this.tripApplication = tripApplication;
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

}
