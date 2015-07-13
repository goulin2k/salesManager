/**
 * 
 */
package com.sales.action;

import java.util.List;

import net.sf.json.JSONObject;

import com.sales.common.Constants;
import com.sales.common.NormalFun;
import com.sales.common.Result;
import com.sales.model.STripApplication;
import com.sales.service.STripApplicationService;

/**
 * @author Leo
 * 
 */
public class TripAction extends BaseAction {

	private STripApplicationService tripApplicationService;

	private STripApplication tripApplication;

	private List<STripApplication> tripApplicationList;

	private Integer tripId;

	public String tripList() {
		if (this.tripApplication == null) {
			this.tripApplication = new STripApplication();
		}
		if (this.tripApplication.getPageNo() == null) {
			this.tripApplication.setPageNo(1);
		}
		this.tripApplication.setPageSize(Constants.DEFAULT_PAGE_SIZE);
		this.tripApplication.setUserId(this.getLoginUser().getUserId());
		this.tripApplicationList = this.tripApplicationService.getTripApplicationListPage(this.tripApplication);
		int totalCount = this.tripApplicationService.getTripApplicationListPageCount(this.tripApplication);
		this.tripApplication.setTotalCount(totalCount);
		return "tripList";
	}

	public String toEditTrip() {
		if (this.tripId != null) {
			this.tripApplication = this.tripApplicationService.getTripApplication(this.tripId);
		}
		return "tripEdit";
	}

	public String editTrip() {
		if(isFromMobile()) {
			tripApplication.setTripComment(
					NormalFun.getUTF8(tripApplication.getTripComment()));
			tripApplication.setTripLocation(
					NormalFun.getUTF8(tripApplication.getTripLocation()));
		}
		this.tripApplicationService.addTripApplication(this.tripApplication,this.getLoginUser());
		if(isFromMobile()) {
			Result res = new Result(Result.SUCESSED, "出差申请流程提交成功！");
			JSONObject json = JSONObject.fromObject(res);
			writeJsonP(json);
			return null;
		}
		return "toTripList";
	}

	public String tripDetail() {
		this.tripApplication = this.tripApplicationService.getTripApplication(this.tripId);
		return "tripDetail";
	}

	public String toReSubmitTrip() {
		this.tripApplication = this.tripApplicationService.getTripApplication(this.tripId);
		return "tripReSubmit";
	}

	public String reSubmitTrip() {
		this.tripApplication.setUserId(this.getLoginUser().getUserId());
		this.tripApplicationService.updateTripApplication(this.tripApplication);
		return "toTripList";
	}

	public String deleteTrip() {
		this.tripApplicationService.deleteTrip(this.tripId);
		return "toTripList";
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
	 * @return the tripApplicationList
	 */
	public List<STripApplication> getTripApplicationList() {
		return tripApplicationList;
	}

	/**
	 * @param tripApplicationList the tripApplicationList to set
	 */
	public void setTripApplicationList(List<STripApplication> tripApplicationList) {
		this.tripApplicationList = tripApplicationList;
	}

	/**
	 * @param tripApplicationService the tripApplicationService to set
	 */
	public void setTripApplicationService(STripApplicationService tripApplicationService) {
		this.tripApplicationService = tripApplicationService;
	}

	/**
	 * @return the tripId
	 */
	public Integer getTripId() {
		return tripId;
	}

	/**
	 * @param tripId the tripId to set
	 */
	public void setTripId(Integer tripId) {
		this.tripId = tripId;
	}

}
