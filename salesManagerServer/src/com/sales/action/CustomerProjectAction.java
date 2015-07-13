package com.sales.action;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import my.com.ibatis.core.dao.support.Page;

import com.opensymphony.xwork2.ActionContext;
import com.sales.common.Constants;
import com.sales.model.SCustomerLinkman;
import com.sales.model.SCustomerProject;
import com.sales.model.SEnumeration;
import com.sales.model.SSalesActivity;
import com.sales.model.SSalesProject;
import com.sales.model.SUser;
import com.sales.service.ActivityService;
import com.sales.service.CustomerLinkmanService;
import com.sales.service.CustomerProjectService;
import com.sales.service.EnumerationService;
import com.sales.service.ProjectService;

/** 
 * @author  
 * @version 创建时间：2013-7-9 
 *  
 */
public class CustomerProjectAction extends BaseAction{
	
	private Long pageNumber = 1L; // 页数
	private int pageSize = Page.DEFAULT_PAGE_SIZE;; // 页面大小
	private Page page ;
	
	private CustomerProjectService customerProjectService;
	private SCustomerProject customerProject;
	private Integer projectId;
	private List<SCustomerProject> customerProjectList;
	private EnumerationService enumerationService;
	private List<SEnumeration> enumerationList;
	private List<SEnumeration> evaluationSalerList;
	private List<SEnumeration> evaluationManagerList;
	private List<SEnumeration> evaluationGenList;
	private Integer customerId;
	private String userPositionOrgId;
	private CustomerLinkmanService customerLinkmanService;
	private List<SCustomerLinkman> customerLinkmanList;
	private ActivityService activityService;
	private ProjectService projectService;
	private String msg;
	private JSONArray jsonArray;
	
	private List<SSalesProject> projectList;
	private List<SSalesActivity> activityList;
	
	/**
	 * 客户项目所包含的销售计划
	 * @return
	 */
	public List<SSalesProject> getProjectList() {
		return projectList;
	}
	public void setProjectList(List<SSalesProject> projectList) {
		this.projectList = projectList;
	}
	/**
	 * 客户项目的所有活动
	 * @return
	 */
	public List<SSalesActivity> getActivityList() {
		return activityList;
	}
	public void setActivityList(List<SSalesActivity> activityList) {
		this.activityList = activityList;
	}
	public JSONArray getJsonArray() {
		return jsonArray;
	}
	public void setJsonArray(JSONArray jsonArray) {
		this.jsonArray = jsonArray;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public ActivityService getActivityService() {
		return activityService;
	}
	public void setActivityService(ActivityService activityService) {
		this.activityService = activityService;
	}
	public ProjectService getProjectService() {
		return projectService;
	}
	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}
	public CustomerLinkmanService getCustomerLinkmanService() {
		return customerLinkmanService;
	}
	public void setCustomerLinkmanService(
			CustomerLinkmanService customerLinkmanService) {
		this.customerLinkmanService = customerLinkmanService;
	}
	public List<SCustomerLinkman> getCustomerLinkmanList() {
		return customerLinkmanList;
	}
	public void setCustomerLinkmanList(List<SCustomerLinkman> customerLinkmanList) {
		this.customerLinkmanList = customerLinkmanList;
	}
	public String getUserPositionOrgId() {
		return userPositionOrgId;
	}
	public void setUserPositionOrgId(String userPositionOrgId) {
		this.userPositionOrgId = userPositionOrgId;
	}
	public List<SEnumeration> getEvaluationSalerList() {
		return evaluationSalerList;
	}
	public void setEvaluationSalerList(List<SEnumeration> evaluationSalerList) {
		this.evaluationSalerList = evaluationSalerList;
	}
	public List<SEnumeration> getEvaluationManagerList() {
		return evaluationManagerList;
	}
	public void setEvaluationManagerList(List<SEnumeration> evaluationManagerList) {
		this.evaluationManagerList = evaluationManagerList;
	}
	public List<SEnumeration> getEvaluationGenList() {
		return evaluationGenList;
	}
	public void setEvaluationGenList(List<SEnumeration> evaluationGenList) {
		this.evaluationGenList = evaluationGenList;
	}

	private String customerName;
	
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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
	public List<SCustomerProject> getCustomerProjectList() {
		return customerProjectList;
	}
	public void setCustomerProjectList(List<SCustomerProject> customerProjectList) {
		this.customerProjectList = customerProjectList;
	}
	public CustomerProjectService getCustomerProjectService() {
		return customerProjectService;
	}
	public void setCustomerProjectService(
			CustomerProjectService customerProjectService) {
		this.customerProjectService = customerProjectService;
	}
	public SCustomerProject getCustomerProject() {
		return customerProject;
	}
	public void setCustomerProject(SCustomerProject customerProject) {
		this.customerProject = customerProject;
	}
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	
	public String addCustomerProjectInit(){
		this.enumerationList = this.enumerationService.getEnumerationByType(Constants.ENUMERATION_PROJECT_CHANCE_EVALUATION_TYPE);
		SUser user = (SUser) this.session.get(Constants.USER_INFO);
		this.userPositionOrgId = user.getRoleId().toString();
		customerProject = new SCustomerProject();
		customerProject.setStartTime(new Date());
		//this.customerLinkmanList = this.customerLinkmanService.getLinkmanListByCustomerId(customerId);
		return "addCustomerProjectInit";
	}
	
	public String addCustomerProjectFromCu() throws UnsupportedEncodingException{
		this.enumerationList = this.enumerationService.getEnumerationByType(Constants.ENUMERATION_PROJECT_CHANCE_EVALUATION_TYPE);
		if(this.customerName != null){
			String strPtname = request.getParameter("customerName");
			this.customerName = new String(strPtname.getBytes("ISO-8859-1"), "UTF-8"); 
		}
		SUser user = (SUser) this.session.get(Constants.USER_INFO);
		this.userPositionOrgId = user.getRoleId().toString();
		this.customerLinkmanList = this.customerLinkmanService.getLinkmanListByCustomerId(customerId);
		return "addCustomerProjectFromCu";
	}
	
	public String addCustomerProject(){
		SUser user = (SUser) this.session.get(Constants.USER_INFO);
		this.customerProject.setCreateUserId(user.getUserId());
		this.customerProject.setCreateUserName(user.getUserName());
		this.customerProjectService.addCustomerProject(this.customerProject);
		this.customerId = this.customerProject.getCustomerId();
		if(customerName!=null){
			return "addCustomerProject";
		}
		return selectCustomerProjectList();
	}
	
	public String updateCustomerProjectInit(){
		this.customerProject = this.customerProjectService.getCustomerProjectById(projectId);
		SUser user = (SUser) this.session.get(Constants.USER_INFO);
		this.userPositionOrgId = user.getRoleId().toString();
		SEnumeration enumeration = new SEnumeration();
		SCustomerLinkman linkman = new SCustomerLinkman();
		this.evaluationSalerList = this.enumerationService.getEnumerationByType(Constants.ENUMERATION_PROJECT_CHANCE_EVALUATION_TYPE);
		if(customerProject.getEvaluationSaler()!= null){
		for (int i = 0; i < evaluationSalerList.size(); i++) {
			enumeration = evaluationSalerList.get(i);
			enumeration.setIsSelect("noselected");
			int evaluationSaler = customerProject.getEvaluationSaler();
			int enumerationId = enumeration.getEnumerationId();
			if(evaluationSaler == enumerationId){
				enumeration.setIsSelect("selected");
				this.customerProject.setEvaluationSalerName(enumeration.getEnumerationName());
				break;
			}
		}
		}
		this.evaluationManagerList = this.enumerationService.getEnumerationByType(Constants.ENUMERATION_PROJECT_CHANCE_EVALUATION_TYPE);
		if(customerProject.getEvaluationManager() != null){
		for (int i = 0; i < evaluationManagerList.size(); i++) {
			enumeration = evaluationManagerList.get(i);
			enumeration.setIsSelect("noselected");
			int evaluationManager = customerProject.getEvaluationManager();
			int enumerationId = enumeration.getEnumerationId();
			if(evaluationManager == enumerationId){
				enumeration.setIsSelect("selected");
				this.customerProject.setEvaluationManagerName(enumeration.getEnumerationName());
				break;
			}
		}
		}
		this.evaluationGenList = this.enumerationService.getEnumerationByType(Constants.ENUMERATION_PROJECT_CHANCE_EVALUATION_TYPE);
		if(customerProject.getEvaluationGen() != null){
		for (int i = 0; i < evaluationGenList.size(); i++) {
			enumeration = evaluationGenList.get(i);
			enumeration.setIsSelect("noselected");
			int evaluationGen = customerProject.getEvaluationGen();
			int enumerationId = enumeration.getEnumerationId();
			if(evaluationGen == enumerationId){
				enumeration.setIsSelect("selected");
				this.customerProject.setEvaluationGenName(enumeration.getEnumerationName());
				break;
			}
		}
		}
		this.customerLinkmanList = this.customerLinkmanService.getLinkmanListByCustomerId(this.customerProject.getCustomerId());
		if(customerProject.getLinkmanId() != null){
			for(int i = 0;i<customerLinkmanList.size(); i++){
				linkman = customerLinkmanList.get(i);
				linkman.setIsSelect("noselected");
				int proLinkmanId = customerProject.getLinkmanId();
				int linkId = linkman.getLinkmanId();
				if(proLinkmanId == linkId){
					linkman.setIsSelect("selected");
					this.customerProject.setLinkmanName(linkman.getName());
					break;
				}
			}
		}
		
		this.projectList = projectService.getProjectByCustomerProjectId(projectId);
		this.activityList = activityService.getActivityByCustomerProId(projectId);
		
		return "updateCustomerProjectInit";
	}
	
	public EnumerationService getEnumerationService() {
		return enumerationService;
	}
	public void setEnumerationService(EnumerationService enumerationService) {
		this.enumerationService = enumerationService;
	}
	public List<SEnumeration> getEnumerationList() {
		return enumerationList;
	}
	public void setEnumerationList(List<SEnumeration> enumerationList) {
		this.enumerationList = enumerationList;
	}
	public String updateCustomerProject(){
		SUser user = (SUser) this.session.get(Constants.USER_INFO);
		this.customerProject.setCreateUserId(user.getUserId());
		this.customerProject.setCreateUserName(user.getUserName());
		this.customerProjectService.updateCustomerProject(this.customerProject);
		if(this.customerId!=null){
			return "addCustomerProject";
		}
		this.customerProject.setCustomerName(null);
		this.customerProject.setName(null);
		return selectCustomerProjectList();
	}
	
	public String selectCustomerProjectList(){
		if (this.pageNumber == null || this.pageNumber == 0L) {
			this.pageNumber = 1L;
		}
		if(customerProject == null)
			customerProject = new SCustomerProject();
		Integer totalCount = new Integer(0);
		SUser user = (SUser) this.session.get(Constants.USER_INFO);
		Map permissionMap = (Map) this.session.get(Constants.USER_PERMISSION_MAP);
		String customerIds = (String) permissionMap.get("customerOwnerFilter");
		
		if(Constants.USER_FILTER_MARK.equals(customerIds)){
			this.customerProjectList = this.customerProjectService.selectCPlist(
					pageNumber, pageSize,customerProject);
			totalCount = this.customerProjectService.getCustomerProjectCount(customerProject);
		}else{
			if(customerIds==null){
				this.customerProjectList=null;
			}else{
				this.customerProjectList = this.customerProjectService.selectCPlistByCustomerIds(
						pageNumber, pageSize, customerProject, customerIds);
				totalCount = this.customerProjectService.getCPCountByCustomerIds(
						customerProject,customerIds);
			}
		}
		
		
		this.page = new Page();
		this.page.setData(customerProjectList);
		this.page.setPageSize(this.pageSize);
		this.page.setTotalCount(Long.valueOf(totalCount));
		this.page.setStart(this.pageNumber.intValue());
		return "selectCustomerProjectList";
	}
	
	public String deleteCustomerProject(){
		List activityList = this.activityService.getActivityByCustomerProId(this.projectId);
		List proList = this.projectService.getProjectByCustomerProjectId(this.projectId);
		if(activityList.size()>0 && proList.size()>0){
			this.msg = "deletefail";
		}else{
			this.customerProjectService.deleteCustomerProject(this.projectId);
		}
		if(this.customerId!=null){
			return "deleteCustomerProject";
		}
		return selectCustomerProjectList();
	}
	
	public String jsonLinkmans(){ 
		try{
			customerLinkmanList = this.customerLinkmanService.getLinkmanListByCustomerId(customerId);
			this.jsonArray = JSONArray.fromObject(customerLinkmanList);
			
			response = (HttpServletResponse) ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE); 
			response.setCharacterEncoding("UTF-8"); 
			response.getWriter().print(jsonArray); 
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

}
