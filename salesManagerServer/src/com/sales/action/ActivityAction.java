package com.sales.action;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import my.com.ibatis.core.dao.support.Page;

import com.sales.common.Constants;
import com.sales.model.SCustomerProject;
import com.sales.model.SSalesActivity;
import com.sales.model.SSalesProject;
import com.sales.model.SUser;
import com.sales.service.ActivityService;
import com.sales.service.CustomerProjectService;
import com.sales.service.EnumerationService;
import com.sales.service.ProjectService;

public class ActivityAction extends BaseAction {
	
	private ActivityService activityService;
	private List activityList;
	private SSalesActivity activity;
	private ProjectService projectService; 
	private SSalesProject project;
	private Long pageNumber = 1L; // 页数
	private int pageSize = Page.DEFAULT_PAGE_SIZE; // 页面大小
	private Page page ;
	private EnumerationService enumerationService;
	private List enumerationList;
	private CustomerProjectService customerProjectService;
	private List customerProjectList;
	private Integer projectId;
	private SCustomerProject customerProject;
	
	private String from;
	
	/**
	 * @return the from
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * @param from the from to set
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 * @return the projectId
	 */
	public Integer getProjectId() {
		return projectId;
	}

	/**
	 * @param projectId the projectId to set
	 */
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public CustomerProjectService getCustomerProjectService() {
		return customerProjectService;
	}

	public void setCustomerProjectService(
			CustomerProjectService customerProjectService) {
		this.customerProjectService = customerProjectService;
	}

	public List getCustomerProjectList() {
		return customerProjectList;
	}

	public void setCustomerProjectList(List customerProjectList) {
		this.customerProjectList = customerProjectList;
	}

	public List getEnumerationList() {
		return enumerationList;
	}

	public void setEnumerationList(List enumerationList) {
		this.enumerationList = enumerationList;
	}

	public EnumerationService getEnumerationService() {
		return enumerationService;
	}

	public void setEnumerationService(EnumerationService enumerationService) {
		this.enumerationService = enumerationService;
	}

	public ProjectService getProjectService() {
		return projectService;
	}

	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}

	public SSalesProject getProject() {
		return project;
	}

	public void setProject(SSalesProject project) {
		this.project = project;
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

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}
	 
	public ActivityService getActivityService() {
		return activityService;
	}

	public void setActivityService(ActivityService activityService) {
		this.activityService = activityService;
	}

	public List getActivityList() {
		return activityList;
	}

	public void setActivityList(List activityList) {
		this.activityList = activityList;
	}

	public SSalesActivity getActivity() {
		return activity;
	}

	public void setActivity(SSalesActivity activity) {
		this.activity = activity;
	}
 
	public SCustomerProject getCustomerProject() {
		return customerProject;
	}

	public void setCustomerProject(SCustomerProject customerProject) {
		this.customerProject = customerProject;
	}

	public String editNew() { 
		this.enumerationList = this.enumerationService.getEnumerationByType(Constants.ENUMERATION_ACTIVITY_TYPE);
		if(this.activity == null){
			this.activity = new SSalesActivity();
		}
		if(projectId != null) {
			activity.setProjectId(projectId);
			Map permissionMap = (Map) this.session.get(Constants.USER_PERMISSION_MAP);
			String childUserIds = (String) permissionMap.get("childUserFilter"); 
			SSalesProject project = projectService.getProjectById(projectId, childUserIds);
			activity.setProjectTopical(project.getTopical());
			activity.setCustomerId(project.getCustomerId());
			activity.setCustomerName(project.getCustomerName());
		}
		this.activity.setActivityDate(new Timestamp((new Date()).getTime())); 
		return "add";
	}
	
	public String add() throws IOException { 
		if(this.activity.getActivityId() != null)
			return update();
		
		Map permissionMap = (Map) this.session.get(Constants.USER_PERMISSION_MAP);
		String childUserIds = (String) permissionMap.get("childUserFilter"); 
		SUser sUser = getLoginUser();
		this.activity.setResponseUserId(sUser.getUserId());
		this.activity.setResponseUserName(sUser.getUserName());
		this.activityService.addActivity(this.activity, childUserIds);
		if(this.from != null &&  this.from.length() >0 && this.activity.getProjectId() != null) {
			this.response.sendRedirect(from + "?project.projectId=" + this.activity.getProjectId() + "#activityList");
			return null;
		}
    	return "list";
    }
 
	public String show() {
		Map permissionMap = (Map) this.session.get(Constants.USER_PERMISSION_MAP);
		String childUserIds = (String) permissionMap.get("childUserFilter"); 
		this.activity = this.activityService.getActivityById(this.activity.getActivityId(), childUserIds);
		return "show";
	} 
 
	public String get() {
		Map permissionMap = (Map) this.session.get(Constants.USER_PERMISSION_MAP);
		String childUserIds = (String) permissionMap.get("childUserFilter"); 
		this.activity = this.activityService.getActivityById(this.activity.getActivityId(), childUserIds);
		this.enumerationList = this.enumerationService.getEnumerationByType(Constants.ENUMERATION_ACTIVITY_TYPE);
		if(this.activity.getCustomerId()!=null && this.activity.getCustomerId().intValue()>0){
			this.customerProjectList = this.customerProjectService.getCPListByCustomerId(this.activity.getCustomerId());			
		}
		else{
			this.customerProjectList = new ArrayList();
		}
		return "get";
	} 
	
	public String update() { 
		SUser sUser = getLoginUser();
		this.activity.setResponseUserId(sUser.getUserId());
		this.activityService.updateActivity(this.activity);
    	return "list";
    }
	
	public String index(){ 
		Map permissionMap = (Map) this.session.get(Constants.USER_PERMISSION_MAP);
		String childUserIds = (String) permissionMap.get("childUserFilter"); 
		if(this.activity == null){
			this.activity = new SSalesActivity(); 
			Calendar cal = Calendar.getInstance();   			  
			SimpleDateFormat datef=new SimpleDateFormat("yyyy-MM-dd");  
			//当前月的最后一天      
			cal.set( Calendar.DATE, 1 );  
			cal.roll(Calendar.DATE, - 1 );  
			Date endTime = cal.getTime();  
			//当前月的第一天             
			cal.set(GregorianCalendar.DAY_OF_MONTH, 1);   
			Date startTime = cal.getTime();   
			this.activity.setStartTime(datef.format(startTime));
			this.activity.setEndTime(datef.format(endTime));
		}
		if (this.pageNumber == null || this.pageNumber == 0L) {
			this.pageNumber = 1L;
		}
		Integer totalCount = this.activityService.getActivityCount(this.activity.getProjectId(), 
				this.activity.getCustomerId(), this.activity.getResponseUserId(), activity.getResponseUserName(),  this.activity.getTopical(), 
				this.activity.getStartTime(), this.activity.getEndTime(), this.activity.getEnumerationId(), childUserIds);
		if(totalCount > 0){
			this.activityList = this.activityService.getActivityList(this.activity.getProjectId(), 
			this.activity.getCustomerId(), this.activity.getResponseUserId(), activity.getResponseUserName(), this.activity.getTopical(), this.activity.getStartTime(), 
			this.activity.getEndTime(), pageNumber.intValue(), pageSize, this.activity.getEnumerationId(), childUserIds); 
		}
		this.page = new Page();
		this.page.setData(this.activityList);
		this.page.setPageSize(this.pageSize);
		this.page.setTotalCount(Long.valueOf(totalCount));
		this.page.setStart(this.pageNumber.intValue());
		this.enumerationList = this.enumerationService.getEnumerationByType(Constants.ENUMERATION_ACTIVITY_TYPE);
		return "pageList";
	}
	
	public String showActivityProject() { 
		Map permissionMap = (Map) this.session.get(Constants.USER_PERMISSION_MAP);
		String childUserIds = (String) permissionMap.get("childUserFilter"); 
		this.project = this.projectService.getProjectById(this.activity.getProjectId(), childUserIds);
		this.enumerationList = this.enumerationService.getEnumerationByType(Constants.ENUMERATION_ACTIVITY_TYPE);
		if(this.activity == null){
			this.activity = new SSalesActivity();
		}
		this.activity.setActivityDate(new Timestamp(new Date().getTime()));
		return "showActivityProject";
	}
	
	public String addActivity() throws IOException {
		Map permissionMap = (Map) this.session.get(Constants.USER_PERMISSION_MAP);
		String childUserIds = (String) permissionMap.get("childUserFilter"); 
		SUser sUser = getLoginUser();
		this.activity.setResponseUserId(sUser.getUserId());
		this.activity.setResponseUserName(sUser.getUserName());
		this.activityService.addActivity(this.activity, childUserIds);
		this.enumerationList = this.enumerationService.getEnumerationByType(Constants.ENUMERATION_ACTIVITY_TYPE);
		if(this.activity.getProjectId()!=null && this.activity.getProjectId().intValue()>0){
			this.response.sendRedirect("activity!showActivityProject?activity.projectId=" + this.activity.getProjectId());
			return null;
		}
		else{
	    	return "projectlist";
		}
    }
	
	public String getActivityByProId() { 
		this.customerProject = this.customerProjectService.getCustomerProjectById(projectId);
		this.activityList = this.activityService.getActivityByCustomerProId(this.projectId);
		return "getActivityByProId";
	}

}
