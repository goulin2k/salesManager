/**
 * 
 */
package com.sales.action.app;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.jbpm.api.Configuration;
import org.jbpm.api.ExecutionService;
import org.jbpm.api.HistoryService;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.TaskService;
import org.jbpm.api.history.HistoryTask;
import org.jbpm.api.task.Task;

import com.opensymphony.xwork2.ActionContext;
import com.sales.action.BaseAction;
import com.sales.common.Constants;
import com.sales.common.LabelStringValueBean;
import com.sales.common.NormalFun;
import com.sales.common.Result;
import com.sales.model.BaseObject;
import com.sales.model.BuzViewObject;
import com.sales.model.DataProcess;
import com.sales.model.MessageTypeCount;
import com.sales.model.PaginationData;
import com.sales.model.ProcessHistory;
import com.sales.model.ProcessInfo;
import com.sales.model.ProcessTask;
import com.sales.model.SAnnualLeave;
import com.sales.model.SCarRepairApplication;
import com.sales.model.SCarRepairReimbursement;
import com.sales.model.SExpenseApplication;
import com.sales.model.SExpenseReimbursement;
import com.sales.model.SInformation;
import com.sales.model.SLeave;
import com.sales.model.SOvertime;
import com.sales.model.SOvertimeCollection;
import com.sales.model.SProcess;
import com.sales.model.SSalesActivity;
import com.sales.model.SSalesProject;
import com.sales.model.SSalesQuotation;
import com.sales.model.STripApplication;
import com.sales.model.SUser;
import com.sales.model.SimpleNameValue;
import com.sales.model.TOrder;
import com.sales.model.TOrfq;
import com.sales.model.TOutStock;
import com.sales.model.TPOOrder;
import com.sales.model.TReceiveBill;
import com.sales.model.TRequest;
import com.sales.model.TSale;
import com.sales.model.TStockBill;
import com.sales.model.TStockBillOut;
import com.sales.service.ActivityService;
import com.sales.service.InformationService;
import com.sales.service.NewsService;
import com.sales.service.OrderService;
import com.sales.service.OrfqService;
import com.sales.service.OutStockService;
import com.sales.service.POOrderService;
import com.sales.service.ProjectService;
import com.sales.service.QuotationService;
import com.sales.service.ReceiveBillService;
import com.sales.service.RequestService;
import com.sales.service.SCarRepairApplicationService;
import com.sales.service.SCarRepairReimbursementService;
import com.sales.service.SExpenseApplicationService;
import com.sales.service.SExpenseReimbursementService;
import com.sales.service.SLeaveService;
import com.sales.service.SOvertimeService;
import com.sales.service.STripApplicationService;
import com.sales.service.SaleBillService;
import com.sales.service.SaleService;
import com.sales.service.StockBillOutService;
import com.sales.service.StockBillService;

/**
 * @author apple
 * 
 */
public class MessageAction extends BaseAction {

	private InformationService informationService;
	private Integer pageSize = 10;
	private Long pages = 0l;

	private ProcessEngine processEngine = Configuration.getProcessEngine();

	private TaskService taskService = Configuration.getProcessEngine()
			.getTaskService();

	private HistoryService historyService = Configuration.getProcessEngine()
			.getHistoryService();

	private List taskList;
	private SLeaveService leaveService;
	private SExpenseApplicationService expenseApplicationService;
	private SExpenseReimbursementService expenseReimbursementService;
	private SOvertimeService overtimeService;
	private NewsService newsService;
	
	/**	消息关联的各类单据信息查询业务	*/
	private ProjectService salePlanService;
	private ActivityService activityService;
	private QuotationService quotationService;
	private OrfqService orfqService;
	private OrderService orderService;
	private RequestService requestService;
	private POOrderService poOrderservice;
	private StockBillService stockBillService;
	private OutStockService outStockService;
	private StockBillOutService stockBillOutService;
	private SaleService saleBillService;
	private ReceiveBillService receiveBillService;

	/**
	 * @param newsService
	 *            the newsService to set
	 */
	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}

	/**
	 * @param historyService
	 *            the historyService to set
	 */
	public void setHistoryService(HistoryService historyService) {
		this.historyService = historyService;
	}

	/**
	 * @param expenseApplicationService
	 *            the expenseApplicationService to set
	 */
	public void setExpenseApplicationService(
			SExpenseApplicationService expenseApplicationService) {
		this.expenseApplicationService = expenseApplicationService;
	}

	/**
	 * @param expenseReimbursementService
	 *            the expenseReimbursementService to set
	 */
	public void setExpenseReimbursementService(
			SExpenseReimbursementService expenseReimbursementService) {
		this.expenseReimbursementService = expenseReimbursementService;
	}

	/**
	 * @param overtimeService
	 *            the overtimeService to set
	 */
	public void setOvertimeService(SOvertimeService overtimeService) {
		this.overtimeService = overtimeService;
	}

	/**
	 * @param tripApplicationService
	 *            the tripApplicationService to set
	 */
	public void setTripApplicationService(
			STripApplicationService tripApplicationService) {
		this.tripApplicationService = tripApplicationService;
	}

	/**
	 * @param carRepairApplicationService
	 *            the carRepairApplicationService to set
	 */
	public void setCarRepairApplicationService(
			SCarRepairApplicationService carRepairApplicationService) {
		this.carRepairApplicationService = carRepairApplicationService;
	}

	/**
	 * @param carRepairReimbursementService
	 *            the carRepairReimbursementService to set
	 */
	public void setCarRepairReimbursementService(
			SCarRepairReimbursementService carRepairReimbursementService) {
		this.carRepairReimbursementService = carRepairReimbursementService;
	}

	private STripApplicationService tripApplicationService;
	private SCarRepairApplicationService carRepairApplicationService;
	private SCarRepairReimbursementService carRepairReimbursementService;

	/**
	 * @param leaveService
	 *            the leaveService to set
	 */
	public void setLeaveService(SLeaveService leaveService) {
		this.leaveService = leaveService;
	}

	/**
	 * @param informationService
	 *            the informationService to set
	 */
	public void setInformationService(InformationService informationService) {
		this.informationService = informationService;
	}

	/**
	 * 查询当前登录用户消息记录
	 * 
	 * @return
	 */
	public String getMessageList() {
		List infoList = new ArrayList();
		int messageType = 0;
		try {
			pageSize = new Integer(request.getParameter("pageSize"));
			pages = new Long(request.getParameter("pages"));
			String strType = request.getParameter("messageType"); // 消息类型
			if (strType != null)
				messageType = new Integer(strType);
			if (pages == null || pages == 0) {
				pages = 1l;
			}
			SUser sUser = getLoginUser();
			Integer totalCount = informationService.getProcInfoCountByUserId(
					sUser.getUserId(), messageType, null);

			infoList = informationService.getProcInfoListByUserId(
					sUser.getUserId(), messageType, null, pages, pageSize);

			PaginationData page = new PaginationData(totalCount, pageSize,
					pages.intValue(), infoList, messageType);

			JSONObject json = JSONObject.fromObject(page);
			writeJsonP(json);
		} catch (Exception e) {
			e.printStackTrace();
			actionLog.error(e.getMessage());
		}
		return null;
	}
	
	/**
	 * 根据类型获取流程列表
	 * 
	 * @return
	 */
	public String processList() {
		DataProcess process = new DataProcess();
		List processList = new ArrayList();
		Integer totalCount = 0;
		PaginationData page = null;

		pageSize = new Integer(request.getParameter("pageSize"));
		pages = new Long(request.getParameter("pages"));
		if (pages == null || pages == 0) {
			pages = 1l;
		}

		process.setPageNo(pages.intValue());
		process.setOpType(request.getParameter("opType"));
		process.setTypeId(request.getParameter("typeId"));

		process.setPageSize(Constants.DEFAULT_PAGE_SIZE);
		List<HistoryTask> htList = historyService.createHistoryTaskQuery()
				.state("completed")
				.assignee(String.valueOf(this.getLoginUser().getUserId()))
				.list();
		// 根据用户ID查找该用户指定的待办任务
		if (process.getOpType() == null) {
			process.setOpType("1");
		}
		if ("1".equals(process.getOpType())) {
			if (process.getTypeId() == null || "-1".equals(process.getTypeId())) {
				totalCount = (int) taskService
						.createTaskQuery()
						.assignee(
								String.valueOf(this.getLoginUser().getUserId()))
						.count();
				process.setTotalCount(totalCount);
				this.taskList = taskService
						.createTaskQuery()
						.assignee(
								String.valueOf(this.getLoginUser().getUserId()))
						.page(process.getPageStart(), process.getPageEnd())
						.list();
			} else {
				totalCount = (int) taskService
						.createTaskQuery()
						.processDefinitionId(process.getTypeId())
						.assignee(
								String.valueOf(this.getLoginUser().getUserId()))
						.count();
				process.setTotalCount(totalCount);
				// process.setTotalCount((int)taskService.createTaskQuery().processInstanceId(process.getTypeId()).assignee(String.valueOf(this.getLoginUser().getUserId())).count());
				// process.setTotalCount((int)taskService.createTaskQuery().executionId(process.getTypeId()).assignee(String.valueOf(this.getLoginUser().getUserId())).count());
				this.taskList = taskService
						.createTaskQuery()
						.processDefinitionId(process.getTypeId())
						.assignee(
								String.valueOf(this.getLoginUser().getUserId()))
						.page(process.getPageStart(), process.getPageEnd())
						.list();
			}

			List procTaskList = new ArrayList();
			for (Iterator iterator = taskList.iterator(); iterator.hasNext();) {
				Task task = (Task) iterator.next();
				ProcessTask pTask = new ProcessTask();
				String taskId = task.getId();
				String executionId = task.getExecutionId();
				pTask.setTaskId(taskId);

				pTask.setExcuteId(executionId);
				pTask.setpId(executionId.substring(executionId.indexOf(".") + 1));
				pTask.setTypeName(taskService.getVariable(taskId, "typeName")
						.toString());
				// pTask.setTypeId(taskService.getVariable(taskId,
				// "typeId").toString());
				pTask.setDeptName(taskService.getVariable(taskId, "deptName")
						.toString());
				pTask.setUserName(taskService.getVariable(taskId, "userName")
						.toString());
				pTask.setCreateTime(task.getCreateTime().toString());
				pTask.setFormResourceName(task.getFormResourceName()
						+ "?taskId=" + taskId);

				procTaskList.add(pTask);
			}

			page = new PaginationData(totalCount, pageSize.intValue(),
					pages.intValue(), procTaskList, Integer.parseInt(process
							.getOpType()));
		} else {
			SProcess sp = new SProcess();
			sp.setPageNo(process.getPageNo());
			if (sp.getPageNo() == null) {
				sp.setPageNo(1);
			}
			sp.setPageSize(process.getPageSize());
			if ("leave-1".equals(process.getTypeId())) {
				sp.setProcessTypeId(1);
			} else if ("overtime-1".equals(process.getTypeId())) {
				sp.setProcessTypeId(2);
			} else if ("tripApp-1".equals(process.getTypeId())) {
				sp.setProcessTypeId(3);
			} else if ("expenseApp-1".equals(process.getTypeId())) {
				sp.setProcessTypeId(4);
			} else if ("expenseRei-1".equals(process.getTypeId())) {
				sp.setProcessTypeId(5);
			} else if ("carRepairApp-1".equals(process.getTypeId())) {
				sp.setProcessTypeId(6);
			} else if ("carRepairRei-1".equals(process.getTypeId())) {
				sp.setProcessTypeId(7);
			}

			if ("2".equals(process.getOpType())) {
				sp.setOpUserId(this.getLoginUser().getUserId());
				sp.setUserName(process.getUserName());
				sp.setStartTime(process.getStartTime());
				sp.setEndTime(process.getEndTime());
				totalCount = this.leaveService
						.getProcessHistoryListPageCount(sp);
				process.setTotalCount(totalCount);
				processList = leaveService.getProcessHistoryListPage(sp);
			} else if ("3".equals(process.getOpType())) {
				sp.setUserId(this.getLoginUser().getUserId());
				totalCount = this.leaveService
						.getProcessHistoryListPageCount(sp);
				process.setTotalCount(totalCount);
				processList = leaveService.getProcessListPage(sp);
			}

			List processHisList = new ArrayList();
			ExecutionService executionService = processEngine
					.getExecutionService();
			for (Iterator iterator = processList.iterator(); iterator.hasNext();) {
				SProcess proc = (SProcess) iterator.next();
				ProcessHistory ph = new ProcessHistory();
				ph.setTypeId(proc.getProcessTypeId());
				ph.setProcessType(proc.getProcessType());
				ph.setDeptName(proc.getDeptName());
				ph.setAppTime(proc.getAppTime().toString());
				ph.setUserName(proc.getUserName());
				ph.setDetailLink(proc.getDetailLink());
				

				String activityName = "";
				ProcessInstance processInstance = executionService
						.findProcessInstanceById(proc.getExecutionId());
				if (processInstance == null) {
					activityName = Constants.PROCESS_END;
				} else {
					String tempstr = processInstance.findActiveActivityNames()
							.toString();
					activityName = tempstr.substring(1, tempstr.length() - 1);
				}
				ph.setActivityName(activityName);

				processHisList.add(ph);
			}

			page = new PaginationData(totalCount, pageSize.intValue(),
					pages.intValue(), processHisList, Integer.parseInt(process
							.getOpType()));
		}

		JSONObject json = JSONObject.fromObject(page);
		try {
			writeJsonP(json);
		} catch (Exception e) {
			e.printStackTrace();
			actionLog.error(e.getMessage());
		}

		return null;
	}

	/**
	 * 根据工作流程taskid，获取审批流程单据详细信息
	 * 
	 * @return 系统处理结果
	 */
	public String getProcessDetail() {
		JSONObject json = null;
		ProcessInfo info = null;
		String taskId = request.getParameter("taskId");
		int typeId = Integer.parseInt(request.getParameter("typeId"));

		String deptName = (String) taskService.getVariable(taskId, "deptName");
		Task task = taskService.getTask(taskId);
		switch (typeId) {
		case 0:
			break;
		case 1: // 休假
			info = convertLeaveProc(deptName, task);
			break;
		case 2: // 出差申请
			info = convertTripProc(deptName, task);
			break;
		case 3: // 加班申请
			info = convertOvertimeProc(deptName, task);
			break;
		case 4: // 费用申请
			info = convertExpenceAppProc(deptName, task);
			break;
		case 5: // 费用报销申请
			info = convertExpenceRembProc(deptName, task);
			break;
		case 6: // 车辆维修申请
			info = convertCarRepairAppProc(deptName, task);
			break;
		case 7: // 车辆维修报销申请
			info = convertCarRepairRembProc(deptName, task);
			break;
		}

		json = JSONObject.fromObject(info);
		try {
			writeJsonP(json);
		} catch (Exception e) {
			e.printStackTrace();
			actionLog.error(e.getMessage());
		}
		return null;
	}

	/**
	 * 请假流程信息转换
	 * 
	 * @param deptName
	 * @param task
	 * @return
	 */
	private ProcessInfo convertLeaveProc(String deptName, Task task) {
		ProcessInfo info;
		if (task == null) {
			// this.leave =
			// this.leaveService.getLeaveReceipt(Integer.valueOf(id));
			// return "leaveDetail";
		}
		Integer leaveId = getReceiptId(task);
		SLeave leave = leaveService.getLeaveReceipt(leaveId);
		info = new ProcessInfo(task, leave, deptName);
		return info;
	}

	/**
	 * 出差申请流程信息转换
	 * 
	 * @param deptName
	 * @param task
	 * @return
	 */
	private ProcessInfo convertTripProc(String deptName, Task task) {
		ProcessInfo info;
		if (task == null) {
			// this.leave =
			// this.leaveService.getLeaveReceipt(Integer.valueOf(id));
			// return "leaveDetail";
		}
		Integer tripId = getReceiptId(task);
		STripApplication trip = tripApplicationService
				.getTripApplication(tripId);
		info = new ProcessInfo(task, trip, deptName);
		return info;
	}

	/**
	 * 加班申请流程信息转换
	 * 
	 * @param deptName
	 * @param task
	 * @return
	 */
	private ProcessInfo convertOvertimeProc(String deptName, Task task) {
		ProcessInfo info;
		if (task == null) {
			// this.leave =
			// this.leaveService.getLeaveReceipt(Integer.valueOf(id));
			// return "leaveDetail";
		}
		Integer overId = getReceiptId(task);
		SOvertime overApp = overtimeService.getOvertimeReceipt(overId);
		info = new ProcessInfo(task, overApp, deptName);
		return info;
	}

	/**
	 * 费用报销流程申请信息转换
	 * 
	 * @param deptName
	 * @param task
	 * @return
	 */
	private ProcessInfo convertExpenceAppProc(String deptName, Task task) {
		ProcessInfo info;
		if (task == null) {
			// this.leave =
			// this.leaveService.getLeaveReceipt(Integer.valueOf(id));
			// return "leaveDetail";
		}
		Integer expAppId = getReceiptId(task);
		SExpenseApplication expApp = expenseApplicationService
				.getExpenseApplication(expAppId);
		info = new ProcessInfo(task, expApp, deptName);
		return info;
	}

	/**
	 * 费用报销流程信息转换
	 * 
	 * @param deptName
	 * @param task
	 * @return
	 */
	private ProcessInfo convertExpenceRembProc(String deptName, Task task) {
		ProcessInfo info;
		if (task == null) {
			// this.leave =
			// this.leaveService.getLeaveReceipt(Integer.valueOf(id));
			// return "leaveDetail";
		}
		Integer rmbId = getReceiptId(task);
		SExpenseReimbursement expRemb = expenseReimbursementService
				.getExpenseReimbursement(rmbId);
		info = new ProcessInfo(task, expRemb, deptName);
		return info;
	}

	/**
	 * 车辆维修申请流程信息转换
	 * 
	 * @param deptName
	 * @param task
	 * @return
	 */
	private ProcessInfo convertCarRepairAppProc(String deptName, Task task) {
		ProcessInfo info;
		if (task == null) {
			// this.leave =
			// this.leaveService.getLeaveReceipt(Integer.valueOf(id));
			// return "leaveDetail";
		}
		Integer repAppId = getReceiptId(task);
		SCarRepairApplication repApp = carRepairApplicationService
				.getCarApplication(repAppId);
		info = new ProcessInfo(task, repApp, deptName);
		return info;
	}

	/**
	 * 车辆维修费用报销流程信息转换
	 * 
	 * @param deptName
	 * @param task
	 * @return
	 */
	private ProcessInfo convertCarRepairRembProc(String deptName, Task task) {
		ProcessInfo info;
		if (task == null) {
			// this.leave =
			// this.leaveService.getLeaveReceipt(Integer.valueOf(id));
			// return "leaveDetail";
		}
		Integer carRembId = getReceiptId(task);
		SCarRepairReimbursement carRemb = carRepairReimbursementService
				.getCarReimbursement(carRembId);
		info = new ProcessInfo(task, carRemb, deptName);
		return info;
	}

	/**
	 * 根据消息类型，审核流程消息（通过、拒绝）
	 * 
	 * @return 系统处理结果
	 */
	public String auditProcess() {
		String taskId = request.getParameter("audit_taskId"); // 流程任务id
		String processType = request.getParameter("audit_action"); // 审批结果
		String comment = NormalFun.getUTF8(request
				.getParameter("audit_content")); // 审核批注信息

		Task task = taskService.getTask(taskId);
		String processInstanceId = task.getExecutionId();
		Integer receiptId = this.getReceiptId(task);

		String deptName = (String) taskService.getVariable(taskId, "deptName");
		Integer userId = Integer.valueOf((String) taskService.getVariable(
				taskId, "userId"));

		String result = "";
		if ("1".equals(processType)) {
			result = "批准";
		} else {
			result = "驳回";
		}
		taskService.completeTask(taskId, result);

		// 保存驳回或批准备注
		if ("1".equals(processType)) {
			if (comment != null && !"".equals(comment.trim())) {
				taskService.addTaskComment(taskId, task.getActivityName()
						+ "批准意见：" + comment);
			}
		} else {
			if (comment != null && !"".equals(comment.trim())) {
				taskService.addTaskComment(taskId, task.getActivityName()
						+ "驳回原因：" + comment);
			}
			// 如果是调休被驳回，需把加班时数恢复
			if (processInstanceId.startsWith("leave.")) {
				SLeave leave = leaveService.getLeaveReceipt(receiptId);
				if (leave.getTypeId().intValue() == 7) {
					SOvertimeCollection oc = overtimeService
							.getOtCollection(leave.getUserId());
					oc.setHoursCollection(oc.getHoursCollection().floatValue()
							+ leave.getLeaveDays().floatValue());
					overtimeService.updateOvertimeCollection(oc);
				}
			}
			// 如果是年假被驳回，需把年假时数恢复
			if (processInstanceId.startsWith("leave.")) {
				SLeave leave = leaveService.getLeaveReceipt(receiptId);
				if (leave.getTypeId().intValue() == 3) {
					SAnnualLeave sal = leaveService.getAnnualLeave(leave
							.getUserId());
					sal.setAnnualHours(sal.getAnnualHours().intValue()
							+ leave.getLeaveDays().intValue());
					this.leaveService.updateAnnualLeaveHours(sal);
				}
			}
		}

		// 保存审批历史记录
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
		} else if (processInstanceId.startsWith("carRepairApp.")) {
			sp.setProcessTypeId(6);
		} else if (processInstanceId.startsWith("carRepairRei.")) {
			sp.setProcessTypeId(7);
		}
		sp.setDeptName(deptName);
		sp.setUserId(userId);
		sp.setOperation(result);
		sp.setAppTime(new Timestamp((new Date()).getTime()));
		sp.setOpUserId(this.getLoginUser().getUserId());
		this.leaveService.insertProcessHistory(sp);

		ProcessInstance processInstance = processEngine.getExecutionService()
				.findProcessInstanceById(processInstanceId);
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
				SOvertime overtime = overtimeService
						.getOvertimeReceipt(receiptId);
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

		Result res = new Result(1, "流程审批操作成功！");
		JSONObject json = JSONObject.fromObject(res);
		try {
			writeJsonP(json);
		} catch (Exception e) {
			e.printStackTrace();
			actionLog.error(e.getMessage());
		}
		return null;
	}

	/**
	 * 查询获取指定id的消息详细信息
	 * 
	 * @return
	 */
	public String getMessageDetail() {
		SInformation info = null;
		BuzViewObject buzObject = new BuzViewObject();
		List<LabelStringValueBean> lvs;
		ListViewObject lvo;
		Integer infoId = null;
		Integer type = null;
		Integer buzId = null;
		try {
			infoId = Integer.parseInt(request.getParameter("infoId"));
			type = Integer.parseInt(request.getParameter("type"));
			buzId = Integer.parseInt(request.getParameter("buzId"));
			info = informationService.getInformationById(infoId);
			
			if (info.getStatus() == Constants.INFO_STATUS_NEW) { // 修改消息状态已读
				info.setStatus(Constants.INFO_STATUS_READE);
				informationService.updateInfo(info);
			}
			
			switch (type) {
			case 0:
				break;
			case 1:
				SSalesProject sp = salePlanService.getProjectById(buzId, Constants.USER_FILTER_MARK);
				lvs = sp.toLabelValues();
				buzObject.setData(lvs);
				buzObject.setTitle("销售计划");
				break;
			case 2:
				SSalesActivity sa = activityService.getActivityById(buzId, Constants.USER_FILTER_MARK);
				lvs = sa.toLabelValues();
				buzObject.setData(lvs);
				buzObject.setTitle("销售活动");
				break;
			case 3:
				break;
			case 4:
				SSalesQuotation sq = quotationService.getQuotationById(buzId);
				lvs = sq.toLabelValues();
				lvo = ListViewObject.getListViewObject(sq);
				buzObject.setData(lvs);
				buzObject.setTitle("销售询价");
				buzObject.setListData(lvo);
				break;
			case 5:
				TOrfq to = orfqService.getSOrfqById(buzId, Constants.USER_FILTER_MARK);
				lvs = to.toLabelValues();
				lvo = ListViewObject.getListViewObject(to);
				buzObject.setData(lvs);
				buzObject.setTitle("销售报价");
				buzObject.setListData(lvo);
				break;
			case 6:
				TOrder od = orderService.getOrderById(buzId, Constants.USER_FILTER_MARK);
				lvs = od.toLabelValues();
				buzObject.setData(lvs);
				buzObject.setTitle("销售订单");
				lvo = ListViewObject.getListViewObject(od);
				buzObject.setListData(lvo);
				break;
			case 7:
				TRequest rs = requestService.getRequestById(buzId, Constants.USER_FILTER_MARK);
				lvs = rs.toLabelValues();
				buzObject.setData(lvs);
				buzObject.setTitle("采购申请");
				lvo = ListViewObject.getListViewObject(rs);
				buzObject.setListData(lvo);
				break;
			case 8:
				TPOOrder tpo = poOrderservice.getOrderById(buzId, Constants.USER_FILTER_MARK);
				lvs = tpo.toLabelValues();
				buzObject.setData(lvs);
				buzObject.setTitle("采购订单");
				lvo = ListViewObject.getListViewObject(tpo);
				buzObject.setListData(lvo);
				break;
			case 9:
				TStockBill tsb = stockBillService.getStockBillById(buzId, Constants.USER_FILTER_MARK);
				lvs = tsb.toLabelValues();
				buzObject.setData(lvs);
				buzObject.setTitle("采购入库");
				lvo = ListViewObject.getListViewObject(tsb);
				buzObject.setListData(lvo);
				break;
			case 10:
				TOutStock tos = outStockService.getOutStockById(buzId, Constants.USER_FILTER_MARK);
				lvs = tos.toLabelValues();
				buzObject.setData(lvs);
				buzObject.setTitle("销售出库通知");
				lvo = ListViewObject.getListViewObject(tos);
				buzObject.setListData(lvo);
				break;
			case 11:
				TStockBillOut tbo =stockBillOutService.getStockBillOutById(buzId, Constants.USER_FILTER_MARK);
				lvs = tbo.toLabelValues();
				buzObject.setData(lvs);
				buzObject.setTitle("销售出库");
				lvo = ListViewObject.getListViewObject(tbo);
				buzObject.setListData(lvo);
				break;
			case 12:
				TSale tsa = saleBillService.getSaleById(buzId, Constants.USER_FILTER_MARK);
				lvs = tsa.toLabelValues();
				buzObject.setData(lvs);
				buzObject.setTitle("销售发票");
				lvo = ListViewObject.getListViewObject(tsa);
				buzObject.setListData(lvo);
				break;
			case 13:
				TReceiveBill trb = receiveBillService.getReceiveBillById(buzId, Constants.USER_FILTER_MARK);
				lvs = trb.toLabelValues();
				buzObject.setData(lvs);
				buzObject.setTitle("收款单");
				lvo = ListViewObject.getListViewObject(trb);
				buzObject.setListData(lvo);
				break;
			default:
				break;
			} 
			
			JSONObject json = JSONObject.fromObject(buzObject);	
			writeJsonP(json);
		} catch (Exception e) {
			actionLog.error(e.getMessage());
		}
		return null;
	}

	/**
	 * 获取各类流程数
	 * 
	 * @return
	 */
	public String getProcessCount() {
		int total_undealed = 0;
		int total_mysubmit = 0;
		int total_dealed = 0;

		DataProcess process = new DataProcess();
		process.setOpType("1");
		process.setTypeId(null);
		total_undealed = (int) taskService.createTaskQuery()
				.processDefinitionId(process.getTypeId())
				.assignee(String.valueOf(this.getLoginUser().getUserId()))
				.count();

		process.setOpType("2");
		SProcess sp = new SProcess();
		sp.setOpUserId(this.getLoginUser().getUserId());
		sp.setUserName(process.getUserName());
		total_dealed = this.leaveService.getProcessHistoryListPageCount(sp);

		process.setOpType("3");
		sp = new SProcess();
		sp.setUserId(this.getLoginUser().getUserId());
		total_mysubmit = this.leaveService.getProcessHistoryListPageCount(sp);

		ProcessCountResult result = new ProcessCountResult(total_undealed,
				total_mysubmit, total_dealed);
		JSONObject json = JSONObject.fromObject(result);
		writeJsonP(json);
		return null;
	}

	/**
	 * 获取各类消息未读数量
	 * 
	 * @return
	 */
	public String getMessageGroupCounts() {
		List<SimpleNameValue> nv = informationService
				.getUnreadedInfoGroupCounts(getLoginUser().getUserId());
		MessageTypeCount mt = new MessageTypeCount();

		for (Iterator iterator = nv.iterator(); iterator.hasNext();) {
			SimpleNameValue val = (SimpleNameValue) iterator.next();
			if (val.getName().equalsIgnoreCase("salePlan"))
				mt.setPlanMessageCount(val.getValue());
			else if (val.getName().equalsIgnoreCase("saleQuota"))
				mt.setQuoteMessageCount(val.getValue());
			else if (val.getName().equalsIgnoreCase("customer"))
				mt.setCustomerMessageCount(val.getValue());
			else if (val.getName().equalsIgnoreCase("saleProc"))
				mt.setSalesMessageCount(val.getValue());
		}
		JSONObject json = JSONObject.fromObject(mt);
		writeJsonP(json);
		return null;
	}

	/**
	 * 查询获取公司公告消息列表
	 * 
	 * @return
	 */
	public String getCorpInfomationlist() {
		List newsList = new ArrayList();
		int messageType = 0;

		pageSize = new Integer(request.getParameter("pageSize"));
		pages = new Long(request.getParameter("pages"));

		if (pages == null || pages == 0) {
			pages = 1l;
		}
		SUser sUser = getLoginUser();
		Integer totalCount = this.newsService.getNewsCount(null, 1,
				Constants.NEWS_TYPE_NEWS);
		if (totalCount > 0) {
			newsList = this.newsService.getNewsList(null, 1,
					Constants.NEWS_TYPE_NEWS, pages.intValue(), pageSize);
		}

		PaginationData page = new PaginationData(totalCount, pageSize,
				pages.intValue(), newsList, messageType);

		JSONObject json = JSONObject.fromObject(page);
		writeJsonP(json);

		return null;
	}

	/**
	 * @param task
	 * @return
	 */
	private Integer getReceiptId(Task task) {
		String executionId = task.getExecutionId();
		String tempId = executionId.substring(executionId.indexOf(".") + 1);
		return Integer.valueOf(tempId);
	}

	public class ProcessCountResult {
		int total_undealed = 0;
		int total_mysubmit = 0;
		int total_dealed = 0;

		/**
		 * @param total_undealed
		 * @param total_mysubmit
		 * @param total_dealed
		 */
		public ProcessCountResult(int total_undealed, int total_mysubmit,
				int total_dealed) {
			super();
			this.total_undealed = total_undealed;
			this.total_mysubmit = total_mysubmit;
			this.total_dealed = total_dealed;
		}

		/**
		 * @return the total_undealed
		 */
		public int getTotal_undealed() {
			return total_undealed;
		}

		/**
		 * @param total_undealed
		 *            the total_undealed to set
		 */
		public void setTotal_undealed(int total_undealed) {
			this.total_undealed = total_undealed;
		}

		/**
		 * @return the total_mysubmit
		 */
		public int getTotal_mysubmit() {
			return total_mysubmit;
		}

		/**
		 * @param total_mysubmit
		 *            the total_mysubmit to set
		 */
		public void setTotal_mysubmit(int total_mysubmit) {
			this.total_mysubmit = total_mysubmit;
		}

		/**
		 * @return the total_dealed
		 */
		public int getTotal_dealed() {
			return total_dealed;
		}

		/**
		 * @param total_dealed
		 *            the total_dealed to set
		 */
		public void setTotal_dealed(int total_dealed) {
			this.total_dealed = total_dealed;
		}

	}

	/**
	 * @param salePlanService the salePlanService to set
	 */
	public void setSalePlanService(ProjectService salePlanService) {
		this.salePlanService = salePlanService;
	}

	/**
	 * @param activityService the activityService to set
	 */
	public void setActivityService(ActivityService activityService) {
		this.activityService = activityService;
	}

	/**
	 * @param requestService the requestService to set
	 */
	public void setRequestService(RequestService requestService) {
		this.requestService = requestService;
	}

	/**
	 * @param orfqService the orfqService to set
	 */
	public void setOrfqService(OrfqService orfqService) {
		this.orfqService = orfqService;
	}

	/**
	 * @param quotationService the quotationService to set
	 */
	public void setQuotationService(QuotationService quotationService) {
		this.quotationService = quotationService;
	}

	/**
	 * @param poOrderservice the poOrderservice to set
	 */
	public void setPoOrderservice(POOrderService poOrderservice) {
		this.poOrderservice = poOrderservice;
	}

	/**
	 * @param stockBillService the stockBillService to set
	 */
	public void setStockBillService(StockBillService stockBillService) {
		this.stockBillService = stockBillService;
	}

	/**
	 * @param stockBillOutService the stockBillOutService to set
	 */
	public void setStockBillOutService(StockBillOutService stockBillOutService) {
		this.stockBillOutService = stockBillOutService;
	}

	/**
	 * @param outStockService the outStockService to set
	 */
	public void setOutStockService(OutStockService outStockService) {
		this.outStockService = outStockService;
	}

	/**
	 * @param saleBillService the saleBillService to set
	 */
	public void setSaleBillService(SaleService saleBillService) {
		this.saleBillService = saleBillService;
	}

	/**
	 * @param receiveBillService the receiveBillService to set
	 */
	public void setReceiveBillService(ReceiveBillService receiveBillService) {
		this.receiveBillService = receiveBillService;
	}

	/**
	 * @param orderService the orderService to set
	 */
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
}
