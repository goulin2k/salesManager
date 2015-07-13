package com.sales.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import my.com.ibatis.core.dao.support.Page;

import com.sales.common.Constants;
import com.sales.model.SSalesAssess;
import com.sales.model.SSalesProject;
import com.sales.model.SUser;
import com.sales.service.AssessService;
import com.sales.service.ProjectService;

public class AssessAction extends BaseAction {
	
	private AssessService assessService;
	private List activityList;
	private SSalesAssess assess;
	private ProjectService projectService; 
	private SSalesProject project;
	private Long pageNumber = 1L; // 页数	
	private int pageSize = Page.DEFAULT_PAGE_SIZE;; // 页面大小
	private Page page ;
	private int projectId;
	  
	/**
	 * @return the projectId
	 */
	public int getProjectId() {
		return this.project.getProjectId();
	}

	/**
	 * @param projectId the projectId to set
	 */
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String editNew() { 
		Map permissionMap = (Map) this.session.get(Constants.USER_PERMISSION_MAP);
		String childUserIds = (String) permissionMap.get("childUserFilter"); 
		this.project = this.projectService.getProjectById(this.assess.getProjectId(), childUserIds);
		return "add";
	}
	
	public String add() throws IOException {
		Map permissionMap = (Map) this.session.get(Constants.USER_PERMISSION_MAP);
		String childUserIds = (String) permissionMap.get("childUserFilter"); 
		SUser sUser = getLoginUser();
		if(assess == null)
			assess = new SSalesAssess();
		this.assess.setAssessUserId(sUser.getUserId());
		this.assess.setAssessUserName(sUser.getUserName());
		this.assessService.addSalesAssess(this.assess, childUserIds);
		
//		if(this.assess.getProjectId()!=null && this.assess.getProjectId().intValue()>0){
//			//this.response.sendRedirect("assess!editNew?assess.projectId=" + this.assess.getProjectId());
//			return "project";
//		}
//		else{
//	    	return "project";
//		}
		
		this.response.sendRedirect("project!get?project.projectId=" + this.assess.getProjectId() + "#activityList");
		return null;
    }
 
	public String show() {
		this.assess = this.assessService.getSalesAssessById(this.assess.getAssessId());
		return "show";
	}
 
	public String get() {
		this.assess = this.assessService.getSalesAssessById(this.assess.getAssessId());
		return "get";
	}
 
	public String update() {
		SUser sUser = getLoginUser();
		this.assess.setAssessUserId(sUser.getUserId());
		this.assessService.updateSalesAssess(this.assess);
		return "update";
	}

	public AssessService getAssessService() {
		return assessService;
	}

	public void setAssessService(AssessService assessService) {
		this.assessService = assessService;
	}

	public List getActivityList() {
		return activityList;
	}

	public void setActivityList(List activityList) {
		this.activityList = activityList;
	}

	public SSalesAssess getAssess() {
		return assess;
	}

	public void setAssess(SSalesAssess assess) {
		this.assess = assess;
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
	
}
