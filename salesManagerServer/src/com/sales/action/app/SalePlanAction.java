/**
 * 
 */
package com.sales.action.app;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ActionContext;
import com.sales.action.BaseAction;
import com.sales.common.Constants;
import com.sales.common.NormalFun;
import com.sales.common.Result;
import com.sales.model.PaginationData;
import com.sales.model.SSalesActivity;
import com.sales.model.SSalesAssess;
import com.sales.model.SSalesProject;
import com.sales.model.SUser;
import com.sales.service.ActivityService;
import com.sales.service.AssessService;
import com.sales.service.ProjectService;

/**
 * 
 * @author apple
 *
 */
public class SalePlanAction extends BaseAction {
	private ProjectService salePlanService;
	private ActivityService activityService;
	private AssessService assessService;

	/**
	 * @param assessService the assessService to set
	 */
	public void setAssessService(AssessService assessService) {
		this.assessService = assessService;
	}

	/**
	 * @param projectService the projectService to set
	 */
	public void setSalePlanService(ProjectService projectService) {
		this.salePlanService = projectService;
	}
	
	/**
	 * @param activityService the activityService to set
	 */
	public void setActivityService(ActivityService activityService) {
		this.activityService = activityService;
	}

	/**
	 * 分页查询获取当前登录用户工作计划
	 * @return
	 */
	public String getSalePlanJsonList() {
		Long pageSize = 10l;
		Long pages = null;
		Integer userName = null;
		String planStart = null;
		String planEnd = null;
		String completStart = null;
		String completEnd = null;
		String orderBy = null;
		String orderType = null;
		List projectList = new ArrayList();
		
		try {
			pageSize = new Long(request.getParameter("pageSize"));
			pages = new Long(request.getParameter("pages"));
			if(request.getParameter("responseUserId") != null)
				userName = new Integer(request.getParameter("responseUserId"));
			if(request.getParameter("planStart") != null)
				planStart = request.getParameter("planStart");
			if(request.getParameter("planEnd") != null)
				planEnd = request.getParameter("planEnd");
			if(request.getParameter("completStart") != null)
				completStart = request.getParameter("completStart");
			if(request.getParameter("completEnd") != null)
				completEnd = request.getParameter("completEnd");
			orderBy = request.getParameter("orderBy");
			orderType = request.getParameter("orderType");
			
			String childUserIds = getChildrenUserIds(); 
			if (pages == null || pages == 0L) {
				pages = 1L;
			}
			SUser sUser = getLoginUser();
			Integer totalCount = salePlanService.getProjectCount(childUserIds,userName, 
					planStart,planEnd,
					completStart, completEnd);
			if(totalCount > 0){
				projectList = salePlanService.getProjectList(childUserIds, userName, 
						planStart,planEnd,
						completStart, completEnd,
						pages.intValue(), pageSize.intValue(), 
						orderBy, orderType, sUser.getUserId()); 
			}
			PaginationData page = new PaginationData(totalCount, pageSize.intValue(), pages.intValue(), 
					projectList, 0);
			JSONObject json = JSONObject.fromObject(page);
			writeJsonP(json);
		}catch (Exception e) {
			e.printStackTrace();
			actionLog.error(e.getMessage());
		}
		return null;
	}
	
	/**
	 * 新增销售活动记录
	 * @return
	 */
	public String addSaleActivityJson() {
		SSalesActivity activity = new SSalesActivity();
		Integer userId = getLoginUser().getUserId();
		String childUserIds = getChildrenUserIds();
		
		Integer planId = null;
		Integer planRole = null;
		String completionRate = null;
		Integer enumerationId = 22;
		String locAddress = null;
		String locLatitude = null;
		String locLongitude = null;
		String topical = null;
		Integer customerId = null;
		Integer customerProjectId = null;
		Result res = new Result();
		
		try {
			if(request.getParameter("planId") != null && request.getParameter("planId").length() > 0)
				planId = Integer.parseInt(request.getParameter("planId"));
			completionRate = (request.getParameter("completionRate") == null) ? "0" : request.getParameter("completionRate");
			locAddress = NormalFun.getUTF8(request.getParameter("locAddress"));
			locLatitude = request.getParameter("locLatitude");
			locLongitude = request.getParameter("locLongitude");
			topical = NormalFun.getUTF8(request.getParameter("topical"));
			planRole = new Integer(request.getParameter("planRoleId"));
			
			if(planRole > 1)
				return addAssign(childUserIds, planId, topical);
			
			//如果关联销售计划，设置活动关联的客户和销售项目id
			if(planId != null) {
				SSalesProject plan = salePlanService.getProjectById(planId, childUserIds);
				if(plan != null) {
					customerId = plan.getCustomerId();
					customerProjectId = plan.getCustomerProjectId();
				}
			}
			
			activity.setActivityDate(new Timestamp(
					(new Date()).getTime()));
			activity.setComment("移动终端提交：");
			activity.setCompletionRate(completionRate);
			activity.setCustomerId(customerId);
			activity.setCustomerProjectId(customerProjectId);
			activity.setEnumerationId(enumerationId);
			activity.setLocAddress(locAddress);
			activity.setLocLatitude(locLatitude);
			activity.setLocLongitude(locLongitude);
			activity.setProjectId(planId);
			activity.setResponseUserId(userId);
			activity.setTopical(topical);
			
			activityService.addActivity(activity, childUserIds);
			res = new Result(Result.SUCESSED, "添加新销售活动成功：{" 
					+ activity.getActivityId() + "}");
			
		} catch (Exception e) {
			e.printStackTrace();
			res.setResult(Result.FAILED);
			res.setMessage(e.getMessage());			
		}
		
		
		//返回结果给app客户端
		JSONArray json = JSONArray.fromObject(res);	
		try {
			writeJsonP(json);
		}catch (Exception e) {
			e.printStackTrace();
			actionLog.error(e.getMessage());
		}
		
		return null;
	}
	
	/**
	 * 分页查询当前登录用户活动记录列表
	 * @return
	 */
	public String getSaleActivityListJson() {
		Integer pageSize = 10;
		Integer pages = 0;
		Integer projectId = null;
		List activityList = new ArrayList();
		try {
			pageSize = new Integer(request.getParameter("pageSize"));
			pages = new Integer(request.getParameter("pages"));
			projectId = new Integer(request.getParameter("projectId"));
			String childUserIds = getChildrenUserIds(); 
			if (pages == null || pages == 0) {
				pages = 1;
			}
			SUser sUser = getLoginUser();
			Integer totalCount = activityService.getActivityCount(projectId,null, sUser.getUserId(), 
					null, null, null, null, null, childUserIds);
			if(totalCount > 0){
				activityList = activityService.getActivityList(projectId,null, sUser.getUserId(), 
						null, null,null, null, pages, pageSize, null, childUserIds); 
			}
			
			PaginationData page = new PaginationData(totalCount, pageSize.intValue(), pages.intValue(), 
					activityList, 0);
			JSONObject json = JSONObject.fromObject(page);
			writeJsonP(json);
		}catch (Exception e) {
			e.printStackTrace();
			actionLog.error(e.getMessage());
		}
		return null;
	}

	/**
	 * 分页查询当前登录用户活动记录列表
	 * @return
	 */
	public String getSalePlanAssessListJson() {
		Integer pageSize = 10;
		Integer pages = 0;
		Integer projectId = null;
		List assessList = new ArrayList();
		try {
			pageSize = new Integer(request.getParameter("pageSize"));
			pages = new Integer(request.getParameter("pages"));
			projectId = new Integer(request.getParameter("projectId"));
			String childUserIds = getChildrenUserIds(); 
			if (pages == null || pages == 0) {
				pages = 1;
			}
			SUser sUser = getLoginUser();
			Integer totalCount = assessService.getSaleAssessCountByPlan(projectId);
			if(totalCount > 0){
				assessList = assessService.getSaleAssessListByPlan(projectId); 
			}
			
			PaginationData page = new PaginationData(totalCount, pageSize.intValue(), pages.intValue(), 
					assessList, 0);
			JSONObject json = JSONObject.fromObject(page);
			writeJsonP(json);
		}catch (Exception e) {
			e.printStackTrace();
			actionLog.error(e.getMessage());
		}
		return null;
	}
	
	
	
	
	/**
	 * @param childUserIds
	 * @param planId
	 * @return
	 */
	private String addAssign(String childUserIds, int planId, String comment) {
		SSalesAssess assess = new SSalesAssess();
		SUser sUser = getLoginUser();
		
		assess.setProjectId(planId);
		assess.setComment(comment);
		assess.setAssessUserId(sUser.getUserId());
		assess.setAssessUserName(sUser.getUserName());
		assess.setCommentTime(new Date());
		
		assessService.addSalesAssess(assess, childUserIds);
		Result res = new Result(Result.SUCESSED, "添加新销售计划督办信息成功：{" 
				+ planId + "}");
		
		//返回结果给app客户端
		JSONArray json = JSONArray.fromObject(res);
		writeJsonP(json);
		return null;
	}
}
