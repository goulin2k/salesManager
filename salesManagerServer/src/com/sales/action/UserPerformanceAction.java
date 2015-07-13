/**
 * 
 */
package com.sales.action;

import java.util.List;
import java.util.Map;

import my.com.ibatis.core.dao.support.Page;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.sales.common.Constants;
import com.sales.common.NormalFun;
import com.sales.model.SUser;
import com.sales.model.SUserPerformanceItem;
import com.sales.model.SUserPerformancePlan;
import com.sales.service.SUserPerformancePlanService;

/**
 * @author Leo
 *
 */
public class UserPerformanceAction extends BaseAction {

	private SUserPerformancePlanService userPerformancePlanService;
	
	private SUserPerformancePlan userPlan;
	
	private List<SUserPerformancePlan> userPlanList;
	
	private Integer userPlanId;
	
	private SUserPerformanceItem userItem;
	
	private List<SUserPerformanceItem> userItemList;
	
	private Integer userItemId;
	
	/** ajax提交返回结果 */
	private String jsonReturn;
	private JSONObject jsonObject;
	private JSONArray jsonArray;
	
	private Page page;
	private Long pageNumber = 1L; // 页数
	private int pageSize = Page.DEFAULT_PAGE_SIZE; // 页面大小
	
	public String planList() {
		if (this.userPlan == null) {
			this.userPlan = new SUserPerformancePlan();
		}
		if (this.pageNumber == null || this.pageNumber == 0L) {
			this.pageNumber = 1L;
		}
		this.userPlan.setPageSize(Constants.DEFAULT_PAGE_SIZE);
		this.userPlanList = this.userPerformancePlanService.getUserPlanListPage(this.userPlan);
		int totalCount = this.userPerformancePlanService.getUserPlanListPageCount(this.userPlan); 
		this.page = new Page();
		this.page.setData(this.userPlanList);
		this.page.setPageSize(this.pageSize);
		this.page.setTotalCount(Long.valueOf(totalCount));
		this.page.setStart(this.pageNumber.intValue()); 
		return "userPlanList";
	}
	
	public String toEditPlan() {
		return "userPlanEdit";
	}
	
	public String judgePlan() {
		SUserPerformancePlan upp = new SUserPerformancePlan();
		upp.setUserId(this.userPlan.getUserId());
		upp.setYear(this.userPlan.getYear());
		upp.setSeason(this.userPlan.getSeason());
		upp.setMonth(this.userPlan.getMonth());
		upp.setPlanId(this.userPlan.getPlanId());
		
		List<SUserPerformancePlan> upList = this.userPerformancePlanService.getUserPlanListByCircle(upp);
		if (upList == null || upList.size() == 0) {
			this.jsonReturn = "YES";
		} else {
			this.jsonReturn = "NO";
		}
		return Constants.FORWARD_STR_JSON_RESULT;
	}
	
	public String editPlan() {
		SUser loginUser = this.getLoginUser();
		this.userPlan.setCreateUserId(loginUser.getUserId());
		this.userPerformancePlanService.addUserPlan(this.userPlan);
		return "toUserPlanList";
	}
	
	public String deletePlan() {
		this.userPerformancePlanService.deleteUserPlan(this.userPlanId);
		this.jsonReturn = "SUCCESS";
		return  Constants.FORWARD_STR_JSON_RESULT;
	}
	
	public String planDetail() {
		this.userPlan = this.userPerformancePlanService.getUserPlan(this.userPlanId);
		return "userPlanDetail";
	}

	public String planScoreList() {
		if (this.userPlan == null) {
			this.userPlan = new SUserPerformancePlan();
		}
		if (this.userPlan.getPageNo() == null) {
			this.userPlan.setPageNo(1);
		}
		this.userPlan.setPageSize(Constants.DEFAULT_PAGE_SIZE);
		this.userPlan.setCreateUserId(this.getLoginUser().getUserId());
		this.userPlan.setStatus(0);
		this.userPlanList = this.userPerformancePlanService.getUserPlanListPage(this.userPlan);
		int totalCount = this.userPerformancePlanService.getUserPlanListPageCount(this.userPlan);
		this.page = new Page();
		this.page.setData(this.userPlanList);
		this.page.setPageSize(this.pageSize);
		this.page.setTotalCount(Long.valueOf(totalCount));
		this.page.setStart(this.pageNumber.intValue()); 
		return "userPlanScoreList";
	}
	
	public String planScore() {
		this.userPlan = this.userPerformancePlanService.getUserPlan(this.userPlanId);
		return "userPlanScore";
	}
	
	public String planScoreDetail() {
		this.userPlan = this.userPerformancePlanService.getUserPlan(this.userPlanId);
		return "userPlanScore";
	}
	
	public String editScoreList() {
		this.userPerformancePlanService.updateUserPlanItemScore(this.userPlan);
		return "toUserPlanScoreList";
	}
	
	public String myScoreList() {
		if (this.userPlan == null) {
			this.userPlan = new SUserPerformancePlan();
		}
		if (this.userPlan.getPageNo() == null) {
			this.userPlan.setPageNo(1);
		}
		this.userPlan.setPageSize(Constants.DEFAULT_PAGE_SIZE);
//		this.userPlan.setUserId(this.getLoginUser().getUserId());
		this.userPlan.setStatus(1);
		if (this.userPlan.getUserId() == null) {
			//从session中获取所有下属
			Map map = (Map) this.session.get(Constants.USER_PERMISSION_MAP);
			String userIdsTemp = (String) map.get("childUserFilter");
			String userIds = userIdsTemp.substring(1, userIdsTemp.length()-1);
//			String userIds = "1,2,3";
			this.userPlan.setUserIds(userIds);
		}
		this.userPlanList = this.userPerformancePlanService.getUserPlanListPage(this.userPlan);
		int totalCount = this.userPerformancePlanService.getUserPlanListPageCount(this.userPlan);
		this.page = new Page();
		this.page.setData(this.userPlanList);
		this.page.setPageSize(this.pageSize);
		this.page.setTotalCount(Long.valueOf(totalCount));
		this.page.setStart(this.pageNumber.intValue());
		return "myScoreList";
	}
	
	public String myScoreDetail() {
		this.userPlan = this.userPerformancePlanService.getUserPlan(this.userPlanId);
		return "myScoreDetail";
	}
	
	public String itemScoreList() {
		if (this.userItem == null) {
			this.userItem = new SUserPerformanceItem();
		}
		if (this.userItem.getPageNo() == null) {
			this.userItem.setPageNo(1);
		}
		this.userItem.setPageSize(Constants.DEFAULT_PAGE_SIZE);
		this.userItem.setAccessUserId(this.getLoginUser().getUserId());
		this.userItemList = this.userPerformancePlanService.getUserItemListPage(this.userItem);
		int totalCount = this.userPerformancePlanService.getUserItemListPageCount(this.userItem);
		this.page = new Page();
		this.page.setData(this.userPlanList);
		this.page.setPageSize(this.pageSize);
		this.page.setTotalCount(Long.valueOf(totalCount));
		this.page.setStart(this.pageNumber.intValue());
		return "userItemScoreList";
	}
	
	public String itemScore() {
		this.userItem = this.userPerformancePlanService.getUserItem(userItemId);
		return "userItemScore";
	}
	
	public String editItemScore() {
		this.userPerformancePlanService.updateUserItemScore(this.userItem);
		return "toUserItemScoreList";
	}
	
	public String calSqlScore() {
		//从session中获取所有下属
		Map map = (Map) this.session.get(Constants.USER_PERMISSION_MAP);
		String userIdsTemp = (String) map.get("childUserFilter");
		String userIds = userIdsTemp.substring(1, userIdsTemp.length()-1);
//		String userIds = "1,2,3";
		this.jsonReturn = NormalFun.formatDouble(
				this.userPerformancePlanService.calSqlScore(
						this.userItemId, getLoginUser().getUserId(), userIds));
		return Constants.FORWARD_STR_JSON_RESULT;
	}
	/**
	 * @param userPerformancePlanService the userPerformancePlanService to set
	 */
	public void setUserPerformancePlanService(SUserPerformancePlanService userPerformancePlanService) {
		this.userPerformancePlanService = userPerformancePlanService;
	}

	/**
	 * @return the userPlan
	 */
	public SUserPerformancePlan getUserPlan() {
		return userPlan;
	}

	/**
	 * @param userPlan the userPlan to set
	 */
	public void setUserPlan(SUserPerformancePlan userPlan) {
		this.userPlan = userPlan;
	}

	/**
	 * @return the userPlanList
	 */
	public List<SUserPerformancePlan> getUserPlanList() {
		return userPlanList;
	}

	/**
	 * @param userPlanList the userPlanList to set
	 */
	public void setUserPlanList(List<SUserPerformancePlan> userPlanList) {
		this.userPlanList = userPlanList;
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
	 * @return the jsonObject
	 */
	public JSONObject getJsonObject() {
		return jsonObject;
	}

	/**
	 * @param jsonObject the jsonObject to set
	 */
	public void setJsonObject(JSONObject jsonObject) {
		this.jsonObject = jsonObject;
	}

	/**
	 * @return the jsonArray
	 */
	public JSONArray getJsonArray() {
		return jsonArray;
	}

	/**
	 * @param jsonArray the jsonArray to set
	 */
	public void setJsonArray(JSONArray jsonArray) {
		this.jsonArray = jsonArray;
	}

	/**
	 * @return the userPlanId
	 */
	public Integer getUserPlanId() {
		return userPlanId;
	}

	/**
	 * @param userPlanId the userPlanId to set
	 */
	public void setUserPlanId(Integer userPlanId) {
		this.userPlanId = userPlanId;
	}

	/**
	 * @return the userItem
	 */
	public SUserPerformanceItem getUserItem() {
		return userItem;
	}

	/**
	 * @param userItem the userItem to set
	 */
	public void setUserItem(SUserPerformanceItem userItem) {
		this.userItem = userItem;
	}

	/**
	 * @return the userItemList
	 */
	public List<SUserPerformanceItem> getUserItemList() {
		return userItemList;
	}

	/**
	 * @param userItemList the userItemList to set
	 */
	public void setUserItemList(List<SUserPerformanceItem> userItemList) {
		this.userItemList = userItemList;
	}

	/**
	 * @return the userItemId
	 */
	public Integer getUserItemId() {
		return userItemId;
	}

	/**
	 * @param userItemId the userItemId to set
	 */
	public void setUserItemId(Integer userItemId) {
		this.userItemId = userItemId;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public Long getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Long pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
