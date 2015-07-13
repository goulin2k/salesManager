/**
 * 
 */
package com.sales.action;

import java.util.List;

import net.sf.json.JSONObject;

import com.sales.common.Constants;
import com.sales.common.NormalFun;
import com.sales.common.Result;
import com.sales.model.SLeave;
import com.sales.model.SOvertimeCollection;
import com.sales.service.SLeaveService;
import com.sales.service.SOvertimeService;

/**
 * @author Leo
 *
 */
public class LeaveAction extends BaseAction {
	
	private SLeaveService leaveService;
	
	private List<SLeave> leaveList;
	
	private SLeave leave;
	
	private Integer leaveId;
	
	private Integer annualHours;	//年假剩余小时数
	private Integer overtimeHours;	//调休剩余小时数
	
	/** ajax提交返回结果 */
	private String jsonReturn;
	
	public Integer getOvertimeHours() {
		return overtimeHours;
	}

	public void setOvertimeHours(Integer overtimeHours) {
		this.overtimeHours = overtimeHours;
	}

	public SOvertimeService getOvertimeService() {
		return overtimeService;
	}

	private SOvertimeService overtimeService;
	
	public String leaveList() {
		if (this.leave == null) {
			this.leave = new SLeave();
		}
		if (this.leave.getPageNo() == null) {
			this.leave.setPageNo(1);
		}
		this.leave.setPageSize(Constants.DEFAULT_PAGE_SIZE);
		this.leave.setUserId(this.getLoginUser().getUserId());
		this.leaveList = this.leaveService.getLeaveListPage(this.leave);
		int totalCount = this.leaveService.getLeaveListPageCount(this.leave);
		this.leave.setTotalCount(totalCount);
		return "leaveList";
	}
	
	public String leaveValidate() {
		SOvertimeCollection otCol = overtimeService.getOtCollection(this.getLoginUser().getUserId());
		if (otCol == null) {
			this.jsonReturn = "0";
		}
		else if (otCol.getHoursCollection() < this.leave.getLeaveDays()) {
			this.jsonReturn = String.valueOf(otCol.getHoursCollection());
		} else {
		    this.jsonReturn = "SUCCESS";
		}

		return Constants.FORWARD_STR_JSON_RESULT;
	}
	
	public Integer getAnnualHours() {
		return annualHours;
	}

	public String annualValidate() {
		Integer annualHours = leaveService.getAnnualLeaveHours(this.getLoginUser().getUserId());
		if (annualHours == null) {
			this.jsonReturn = "0";
		}
		else if (annualHours < this.leave.getLeaveDays()) {
			this.jsonReturn = String.valueOf(annualHours);
		} else {
		    this.jsonReturn = "SUCCESS";
		}

		return Constants.FORWARD_STR_JSON_RESULT;
	}
	
	public String toEditLeave() {
		
		if (this.leaveId != null) {
			this.leave = this.leaveService.getLeaveReceipt(this.leaveId);
		}
		annualHours = leaveService.getAnnualLeaveHours(this.getLoginUser().getUserId());
		
		SOvertimeCollection otCol = overtimeService.getOtCollection(this.getLoginUser().getUserId());
		if (otCol == null) 
			overtimeHours = 0;
		else 
			overtimeHours = otCol.getHoursCollection().intValue();
		if(isFromMobile()) {
			LeaveInitResult res = new LeaveInitResult(annualHours,overtimeHours);
			JSONObject json = JSONObject.fromObject(res);
			writeJsonP(json);
			return null;
		}
		else
			return "leaveEdit";
	}
	
	public String editLeave() {
		if(isFromMobile()) {
			leave.setComment(NormalFun.getUTF8(leave.getComment()));
		}
		leaveService.addLeaveReceipt(this.leave,this.getLoginUser());
		
		if(isFromMobile()) {
			Result res = new Result(Result.SUCESSED, "请假流程提交成功！");
			JSONObject json = JSONObject.fromObject(res);
			writeJsonP(json);
			return null;
		}
		return "toLeaveList";
	}
	
	public String deleteLeave() {
		this.leaveService.deleteLeaveReceipt(this.leaveId);
		return "toLeaveList";
	}
	
	public String toReSubmitLeave() {
		this.leave = this.leaveService.getLeaveReceipt(this.leaveId);
		return "leaveReSubmit";
	}
	
	public String reSubmitLeave() {
		this.leave.setUserId(this.getLoginUser().getUserId());
		this.leaveService.updateLeaveReceipt(this.leave);
		return "toLeaveList";
	}
	
	public String leaveDetail() {
		this.leave = this.leaveService.getLeaveReceipt(this.leaveId);
		return "leaveDetail";
	}

	/**
	 * @param leaveService the leaveService to set
	 */
	public void setLeaveService(SLeaveService leaveService) {
		this.leaveService = leaveService;
	}

	/**
	 * @return the leaveList
	 */
	public List<SLeave> getLeaveList() {
		return leaveList;
	}

	/**
	 * @param leaveList the leaveList to set
	 */
	public void setLeaveList(List<SLeave> leaveList) {
		this.leaveList = leaveList;
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
	 * @return the leaveId
	 */
	public Integer getLeaveId() {
		return leaveId;
	}


	/**
	 * @param leaveId the leaveId to set
	 */
	public void setLeaveId(Integer leaveId) {
		this.leaveId = leaveId;
	}

	/**
	 * @return the jsonReturn
	 */
	public String getJsonReturn() {
		return jsonReturn;
	}

	/**
	 * @param jsonReturn the jsonReturn to set
	 */
	public void setJsonReturn(String jsonReturn) {
		this.jsonReturn = jsonReturn;
	}

	/**
	 * @param overtimeService the overtimeService to set
	 */
	public void setOvertimeService(SOvertimeService overtimeService) {
		this.overtimeService = overtimeService;
	}
	
	
	/**
	 * @author apple
	 *
	 */
	public class LeaveInitResult {
		private Integer annualHours;	//年假剩余小时数
		private Integer overtimeHours;	//调休剩余小时数
		
		
		public LeaveInitResult(Integer annualHours, Integer overtimeHours) {
			super();
			this.annualHours = annualHours;
			this.overtimeHours = overtimeHours;
		}
		/**
		 * @return the annualHours
		 */
		public Integer getAnnualHours() {
			return annualHours;
		}
		/**
		 * @param annualHours the annualHours to set
		 */
		public void setAnnualHours(Integer annualHours) {
			this.annualHours = annualHours;
		}
		/**
		 * @return the overtimeHours
		 */
		public Integer getOvertimeHours() {
			return overtimeHours;
		}
		/**
		 * @param overtimeHours the overtimeHours to set
		 */
		public void setOvertimeHours(Integer overtimeHours) {
			this.overtimeHours = overtimeHours;
		}
		
		
	}

}
