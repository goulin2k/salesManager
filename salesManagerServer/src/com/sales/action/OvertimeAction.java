/**
 * 
 */
package com.sales.action;

import java.util.List;

import net.sf.json.JSONObject;

import com.sales.common.Constants;
import com.sales.common.NormalFun;
import com.sales.common.Result;
import com.sales.model.SOvertime;
import com.sales.service.SOvertimeService;

/**
 * @author Leo
 *
 */
public class OvertimeAction extends BaseAction {
	
	private SOvertimeService overtimeService;
	
	private SOvertime ot;
	
	private List<SOvertime> otList;
	
	private Integer otId;
	
	public String otList() {
		if (this.ot == null) {
			this.ot = new SOvertime();
		}
		if (this.ot.getPageNo() == null) {
			this.ot.setPageNo(1);
		}
		this.ot.setPageSize(Constants.DEFAULT_PAGE_SIZE);
		this.ot.setUserId(this.getLoginUser().getUserId());
		this.otList = this.overtimeService.getOvertimeListPage(this.ot);
		int totalCount = this.overtimeService.getOvertimeListPageCount(this.ot);
		this.ot.setTotalCount(totalCount);
		return "overtimeList";
	}
	
	public String toEditOt() {
		if (this.otId != null) {
			this.ot = this.overtimeService.getOvertimeReceipt(this.otId);
		}
		return "overtimeEdit";
	}
	
	public String editOt() {
		if(isFromMobile()) {
			ot.setComment(NormalFun.getUTF8(ot.getComment()));
		}
		this.overtimeService.addOvertimeReceipt(this.ot, this.getLoginUser());
		if(isFromMobile()) {
			Result res = new Result(Result.SUCESSED, "加班申请流程提交成功！");
			JSONObject json = JSONObject.fromObject(res);
			writeJsonP(json);
			return null;
		}
		return "toOvertimeList";
	}
	
	public String otDetail() {
		this.ot = this.overtimeService.getOvertimeReceipt(otId);
		return "overtimeDetail";
	}
	
	public String toReSubmitOt() {
		this.ot = this.overtimeService.getOvertimeReceipt(otId);
		return "overtimeReSubmit";
	}
	
	public String reSubmitOt() {
		this.ot.setUserId(this.getLoginUser().getUserId());
		this.overtimeService.updateOvertimeReceipt(this.ot);
		return "toOvertimeList";
	}
	
	public String deleteOvertime() {
		this.overtimeService.deleteOvertime(this.otId);
		return "toOvertimeList";
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
	 * @return the otList
	 */
	public List<SOvertime> getOtList() {
		return otList;
	}

	/**
	 * @param otList the otList to set
	 */
	public void setOtList(List<SOvertime> otList) {
		this.otList = otList;
	}

	/**
	 * @param overtimeService the overtimeService to set
	 */
	public void setOvertimeService(SOvertimeService overtimeService) {
		this.overtimeService = overtimeService;
	}

	/**
	 * @return the otId
	 */
	public Integer getOtId() {
		return otId;
	}

	/**
	 * @param otId the otId to set
	 */
	public void setOtId(Integer otId) {
		this.otId = otId;
	}

}
