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
import com.sales.model.SLog;
import com.sales.model.SPerformanceItem;
import com.sales.model.SPerformancePlan;
import com.sales.model.SPlanItem;
import com.sales.service.SPerformanceItemService;
import com.sales.service.SPerformancePlanService;

/**
 * @author Leo
 *
 */
public class PerformanceAction extends BaseAction {
	
	private SPerformancePlanService performancePlanService;
	
	private SPerformanceItemService performanceItemService;
	
	private SPerformancePlan plan;
	
	private List<SPerformancePlan> planList;
	
	private SPerformanceItem item;
	
	private List<SPerformanceItem> itemList;
	
	private Integer itemId;
	
	private Integer planId;
	
	private Page page;
	private Long pageNumber = 1L; // 页数
	private int pageSize = Page.DEFAULT_PAGE_SIZE; // 页面大小

	
	/** ajax提交返回结果 */
	private String jsonReturn;
	private JSONObject jsonObject;
	private JSONArray jsonArray;
	
	public String planList() {
		if (this.plan == null) {
			this.plan = new SPerformancePlan();
		}
		if (this.plan.getPageNo() == null) {
			this.plan.setPageNo(1);
		}
		this.plan.setPageSize(Constants.DEFAULT_PAGE_SIZE);
		this.planList = this.performancePlanService.getPlanListPage(this.plan);
		int totalCount = this.performancePlanService.getPlanListPageCount(this.plan); 
		this.page = new Page();
		this.page.setData(this.planList);
		this.page.setPageSize(this.pageSize);
		this.page.setTotalCount(Long.valueOf(totalCount));
		this.page.setStart(this.pageNumber.intValue()); 
		return "planList";
	}
	
	public String itemList() {
		if (this.item == null) {
			this.item = new SPerformanceItem();
		}
		if (this.pageNumber == null || this.pageNumber == 0L) {
			this.pageNumber = 1L;
		}
		this.item.setPageSize(Constants.DEFAULT_PAGE_SIZE);
		this.itemList = this.performanceItemService.getItemListPage(this.item);
		int totalCount = this.performanceItemService.getItemListPageCount(this.item); 
		this.page = new Page();
		this.page.setData(this.itemList);
		this.page.setPageSize(this.pageSize);
		this.page.setTotalCount(Long.valueOf(totalCount));
		this.page.setStart(this.pageNumber.intValue()); 
		return "itemList";
	}
	
	public String toEditItem() {
		return "itemEdit";
	}
	
	public String editItem() {
		this.performanceItemService.addItem(this.item);
		return "toItemList";
	}
	
	public String itemDetail() {
		this.item = this.performanceItemService.getItem(this.itemId);
		return "itemDetail";
	}
	
	public String deleteItem() {
		SPlanItem pi = new SPlanItem();
		pi.setItemId(this.itemId);
		List<SPlanItem> piList = this.performancePlanService.getPlanItemListByConditions(pi);
		
		if (piList != null && piList.size() > 0) {
			this.jsonReturn = "IN_USED";
		} else {
			this.performanceItemService.deleteItem(this.itemId);
			this.jsonReturn = "SUCCESS";
		}
		return  Constants.FORWARD_STR_JSON_RESULT;
	}
	
	public String toEditPlan() {
		return "planEdit";
	}
	
	public String editPlan() {	
		this.plan.setUserId(this.getLoginUser().getUserId());
		this.performancePlanService.addPerformancePlan(this.plan);
		return "toPlanList";
	}
	
	public String planDetail() {
		this.plan = this.performancePlanService.getPerformancePlan(this.planId);
		return "planDetail";
	}
	
	public String deletePlan() {
		//判断方案是否已使用
		if (false) {
			this.jsonReturn = "IN_USED";
		} else {
			this.performancePlanService.deletePlan(this.planId);
			this.jsonReturn = "SUCCESS";
		}
		return  Constants.FORWARD_STR_JSON_RESULT;
	}

	/**
	 * @param performancePlanService the performancePlanService to set
	 */
	public void setPerformancePlanService(SPerformancePlanService performancePlanService) {
		this.performancePlanService = performancePlanService;
	}

	/**
	 * @return the plan
	 */
	public SPerformancePlan getPlan() {
		return plan;
	}

	/**
	 * @param plan the plan to set
	 */
	public void setPlan(SPerformancePlan plan) {
		this.plan = plan;
	}

	/**
	 * @return the planList
	 */
	public List<SPerformancePlan> getPlanList() {
		return planList;
	}

	/**
	 * @param planList the planList to set
	 */
	public void setPlanList(List<SPerformancePlan> planList) {
		this.planList = planList;
	}

	/**
	 * @param performanceItemService the performanceItemService to set
	 */
	public void setPerformanceItemService(SPerformanceItemService performanceItemService) {
		this.performanceItemService = performanceItemService;
	}

	/**
	 * @return the item
	 */
	public SPerformanceItem getItem() {
		return item;
	}

	/**
	 * @param item the item to set
	 */
	public void setItem(SPerformanceItem item) {
		this.item = item;
	}

	/**
	 * @return the itemList
	 */
	public List<SPerformanceItem> getItemList() {
		return itemList;
	}

	/**
	 * @param itemList the itemList to set
	 */
	public void setItemList(List<SPerformanceItem> itemList) {
		this.itemList = itemList;
	}

	/**
	 * @return the itemId
	 */
	public Integer getItemId() {
		return itemId;
	}

	/**
	 * @param itemId the itemId to set
	 */
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	/**
	 * @return the planId
	 */
	public Integer getPlanId() {
		return planId;
	}

	/**
	 * @param planId the planId to set
	 */
	public void setPlanId(Integer planId) {
		this.planId = planId;
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
