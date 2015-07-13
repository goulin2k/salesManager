package com.sales.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import my.com.ibatis.core.dao.support.Page;

import com.sales.common.Constants;
import com.sales.model.SEnumeration;
import com.sales.model.SSalesProject;
import com.sales.model.SUser;
import com.sales.service.CustomerProjectService;
import com.sales.service.EnumerationService;
import com.sales.service.ProjectService;

/**
 * 销售计划MVC控制实现类
 * @author Administrator
 *
 */
public class ProjectAction extends BaseAction {
	
	private ProjectService projectService; 
	private List projectList;
	private SSalesProject project;
	private String attentionUserIds;
	private Long pageNumber = 1L; // 页数
	private int pageSize = Page.DEFAULT_PAGE_SIZE; // 页面大小
	private Page page ;
	private EnumerationService enumerationService;
	private List<SEnumeration> enumerationList; 
	private List attentionUserList;
	private CustomerProjectService customerProjectService;
	private List customerProjectList;
	private String assessUser;				//查询条件，督办人
	 
	public List getCustomerProjectList() {
		return customerProjectList;
	}

	public void setCustomerProjectList(List customerProjectList) {
		this.customerProjectList = customerProjectList;
	}

	public CustomerProjectService getCustomerProjectService() {
		return customerProjectService;
	}

	public void setCustomerProjectService(
			CustomerProjectService customerProjectService) {
		this.customerProjectService = customerProjectService;
	}

	public List getAttentionUserList() {
		return attentionUserList;
	}

	public void setAttentionUserList(List attentionUserList) {
		this.attentionUserList = attentionUserList;
	}

	public List<SEnumeration> getEnumerationList() {
		return enumerationList;
	}

	public void setEnumerationList(List<SEnumeration> enumerationList) {
		this.enumerationList = enumerationList;
	}

	public EnumerationService getEnumerationService() {
		return enumerationService;
	}

	public void setEnumerationService(EnumerationService enumerationService) {
		this.enumerationService = enumerationService;
	} 

	public String getAttentionUserIds() {
		return attentionUserIds;
	}

	public void setAttentionUserIds(String attentionUserIds) {
		this.attentionUserIds = attentionUserIds;
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
	 
	public ProjectService getProjectService() {
		return projectService;
	}

	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	} 

	public List getProjectList() {
		return projectList;
	}

	public void setProjectList(List projectList) {
		this.projectList = projectList;
	}

	public SSalesProject getProject() {
		return project;
	}

	public void setProject(SSalesProject project) {
		this.project = project;
	}
 
	/**
	 * @return the assessUser
	 */
	public String getAssessUser() {
		return assessUser;
	}

	/**
	 * @param assessUser the assessUser to set
	 */
	public void setAssessUser(String assessUser) {
		this.assessUser = assessUser;
	}

	public String editNew() { 
		SUser sUser = getLoginUser();
		this.enumerationList = this.enumerationService.getEnumerationByType(Constants.ENUMERATION_PROJECT_TYPE);
		if(this.project == null){
			this.project = new SSalesProject();
		}
		this.customerProjectList = new ArrayList();
		
		this.project.setResponseUserId(sUser.getUserId());
		this.project.setResponseUserName(sUser.getUserName());
		Calendar cal = Calendar.getInstance();   			  
		SimpleDateFormat datef=new SimpleDateFormat("yyyy-MM-dd");  
		//今天           
		Date startTime = cal.getTime();   
		this.project.setStartTime(datef.format(startTime));
		//本周末 
		int day_of_week = cal.get(Calendar.DAY_OF_WEEK) - 2; 
		cal.add(Calendar.DATE, -day_of_week); 
		cal.add(Calendar.DATE, 6);
		Date endTime = cal.getTime();  
		this.project.setEndTime(datef.format(endTime));
		return "add";
	}
	
	public String add() { 
		if(this.project.getProjectId() != null)
			return update();
		 
		SUser sUser = getLoginUser();
		this.project.setCreateUserId(sUser.getUserId());
		this.project.setCreateUserName(sUser.getUserName());
		if(this.project.getStartTime()!=null && this.project.getStartTime().trim().equals("")){
			this.project.setStartTime(null);
		}
		if(this.project.getEndTime()!=null && this.project.getEndTime().trim().equals("")){
			this.project.setEndTime(null);
		}
		
		if(project.getProjectId()!= null && project.getProjectId() == 0)
			project.setProjectId(null);
		
		this.projectService.addProject(this.project, attentionUserIds);
		if(project.getStatus() == 1)
			projectService.commitProject(project);
		
    	return "projectlist";
    }
 
	public String show() {
		String childUserIds = getChildrenUserIds(); 
		this.project = this.projectService.getProjectById(this.project.getProjectId(), childUserIds);
		return "show";
	} 
 
	public String get() {
		
		String childUserIds = getChildrenUserIds(); 
		this.project = this.projectService.getProjectById(this.project.getProjectId(), childUserIds);
		if(project == null)
			return "get";
		this.attentionUserList = this.projectService.getAttentionUserByProjectId(this.project.getProjectId());
		this.enumerationList = this.enumerationService.getEnumerationByType(Constants.ENUMERATION_PROJECT_TYPE);
		if(this.project.getCustomerId()!=null && this.project.getCustomerId().intValue()>0){
			this.customerProjectList = this.customerProjectService.getCPListByCustomerId(this.project.getCustomerId());			
		}
		else{
			this.customerProjectList = new ArrayList();
		}
		return "get";
	} 
	
	/**
	 * @return
	 */
	public String edit() {
		
		String childUserIds = getChildrenUserIds(); 
		this.project = this.projectService.getProjectById(this.project.getProjectId(), childUserIds);
		this.attentionUserList = this.projectService.getAttentionUserByProjectId(this.project.getProjectId());
		this.enumerationList = this.enumerationService.getEnumerationByType(Constants.ENUMERATION_PROJECT_TYPE);
		if(this.project.getCustomerId()!=null && this.project.getCustomerId().intValue()>0){
			this.customerProjectList = this.customerProjectService.getCPListByCustomerId(this.project.getCustomerId());			
		}
		else{
			this.customerProjectList = new ArrayList();
		}
		return "add";
	} 
	
	/**
	 * 保存修改计划信息
	 * @return
	 */
	public String update() { 
		 
		String childUserIds = getChildrenUserIds(); 
		SUser sUser = getLoginUser(); 
		this.project.setCreateUserId(sUser.getUserId());
		this.project.setCreateUserName(sUser.getUserName());
		if(this.project.getStartTime()!=null && this.project.getStartTime().trim().equals("")){
			this.project.setStartTime(null);
		}
		if(this.project.getEndTime()!=null && this.project.getEndTime().trim().equals("")){
			this.project.setEndTime(null);
		}
		
		if(project.getProjectId()!= null && project.getProjectId() == 0)
			project.setProjectId(null);
		this.projectService.updateProject(this.project, attentionUserIds, childUserIds);
		
		if(project.getStatus() == 1)
			projectService.commitProject(project);
    	return get();
    }
	
	/**
	 * 提交暂存状态的销售计划
	 * @return
	 */
	public String commit() {
		Integer projectId = project.getProjectId();
		if(projectId != null)
			projectService.commitProject(projectId);
		return index();
	}
	
	public String delete() {
		Integer projectId = project.getProjectId();
		if(projectId != null)
			projectService.removeProject(projectId);
		return index();
	}
	
	
	public String index(){ 
		String childUserIds = getChildrenUserIds(); 
		if (this.pageNumber == null || this.pageNumber == 0L) {
			this.pageNumber = 1L;
		}
		SUser sUser = getLoginUser();
		if(this.project == null){
			this.project = new SSalesProject();
			project.setStatus(1);
			Calendar cal = Calendar.getInstance();   			  
			SimpleDateFormat datef=new SimpleDateFormat("yyyy-MM-dd");  
			//当前月的最后一天      
			cal.set( Calendar.DATE, 1 );  
			cal.roll(Calendar.DATE, - 1 );  
			Date endTime = cal.getTime();  
			//当前月的第一天             
			cal.set(GregorianCalendar.DAY_OF_MONTH, 1);   
			Date startTime = cal.getTime();   
			this.project.setStartTime(datef.format(startTime));
			this.project.setEndTime(datef.format(endTime));
		}
		Integer totalCount = this.projectService.getProjectCount(childUserIds, this.project.getTopical(), 
				this.project.getEnumerationId(), project.getResponseUserName(), project.getCreateUserName(),
				this.project.getStartTime(), 
				this.project.getEndTime(), assessUser, getLoginUser().getUserId(), project.getStatus(),this.project.getCustomerId());
		if(totalCount > 0){
			this.projectList = this.projectService.getProjectList(childUserIds, 
					this.project.getTopical(), this.project.getEnumerationId(), project.getResponseUserName(),
					project.getCreateUserName(), this.project.getStartTime(), this.project.getEndTime(), assessUser,
					getLoginUser().getUserId(), project.getStatus(),
			pageNumber.intValue(), pageSize, this.project.getCustomerId(), sUser.getUserId()); 
		}
		this.page = new Page();
		this.page.setData(this.projectList);
		this.page.setPageSize(this.pageSize);
		this.page.setTotalCount(Long.valueOf(totalCount));
		this.page.setStart(this.pageNumber.intValue());
		this.enumerationList = this.enumerationService.getEnumerationByType(Constants.ENUMERATION_PROJECT_TYPE);
		return "list";
	}

	public String openlist(){ 
		if (this.pageNumber == null || this.pageNumber == 0L) {
			this.pageNumber = 1L;
		}
		SUser sUser = getLoginUser();
		if(this.project == null){
			this.project = new SSalesProject();
			Calendar cal = Calendar.getInstance();   			  
			SimpleDateFormat datef=new SimpleDateFormat("yyyy-MM-dd");  
			//当前月的最后一天      
			cal.set( Calendar.DATE, 1 );  
			cal.roll(Calendar.DATE, - 1 );  
			Date endTime = cal.getTime();  
			//当前月的第一天             
			cal.set(GregorianCalendar.DAY_OF_MONTH, 1);   
			Date startTime = cal.getTime();   
			this.project.setStartTime(datef.format(startTime));
			this.project.setEndTime(datef.format(endTime));
		}
		Integer totalCount = this.projectService.getProjectOpenCount(sUser.getUserId(), this.project.getTopical(), 
				this.project.getEnumerationId(), this.project.getStartTime(), this.project.getEndTime(), this.project.getCustomerId());
		if(totalCount > 0){
			this.projectList = this.projectService.getProjectOpenList(sUser.getUserId(), 
			this.project.getTopical(), this.project.getEnumerationId(), this.project.getStartTime(), this.project.getEndTime(),
			pageNumber.intValue(), pageSize, this.project.getCustomerId()); 
		}
		this.page = new Page();
		this.page.setData(this.projectList);
		this.page.setPageSize(this.pageSize);
		this.page.setTotalCount(Long.valueOf(totalCount));
		this.page.setStart(this.pageNumber.intValue());
		this.enumerationList = this.enumerationService.getEnumerationByType(Constants.ENUMERATION_PROJECT_TYPE);
		return "openlist";
	}
	 
}
