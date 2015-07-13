package com.sales.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import com.sales.common.Constants;
import com.sales.model.SCustomerLinkman;
import com.sales.model.SEnumeration;
import com.sales.model.SUser;
import com.sales.service.CustomerLinkmanService;
import com.sales.service.EnumerationService;

import my.com.ibatis.core.dao.support.Page;

/** 
 * @author  
 * @version 创建时间：2013-6-25 下午04:50:45 
 *  
 */
public class CustomerLinkmanAction extends BaseAction {
	
	private Long pageNumber = 1L; // 页数
	private int pageSize = Page.DEFAULT_PAGE_SIZE;; // 页面大小
	private Page page;
	private List<SCustomerLinkman> customerLinkmanList;
	private SCustomerLinkman linkman;
	private Integer linkmanId;
	private EnumerationService enumerationService;
	private List<SEnumeration> enumerationList;
	private Integer customerId;
	private String customerName;
	private String name;
	private String customerOwnerUserName;
	private String userPositionOrgId;

	public String getCustomerOwnerUserName() {
		return customerOwnerUserName;
	}

	public void setCustomerOwnerUserName(String customerOwnerUserName) {
		this.customerOwnerUserName = customerOwnerUserName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private List<SEnumeration> relationUsList;
	private List<SEnumeration> relationCompList;

	public List<SEnumeration> getRelationUsList() {
		return relationUsList;
	}

	public void setRelationUsList(List<SEnumeration> relationUsList) {
		this.relationUsList = relationUsList;
	}

	public List<SEnumeration> getRelationCompList() {
		return relationCompList;
	}

	public void setRelationCompList(List<SEnumeration> relationCompList) {
		this.relationCompList = relationCompList;
	}

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

	public Integer getLinkmanId() {
		return linkmanId;
	}

	public void setLinkmanId(Integer linkmanId) {
		this.linkmanId = linkmanId;
	}

	public SCustomerLinkman getLinkman() {
		return linkman;
	}

	public void setLinkman(SCustomerLinkman linkman) {
		this.linkman = linkman;
	}

	public List<SCustomerLinkman> getCustomerLinkmanList() {
		return customerLinkmanList;
	}

	public void setCustomerLinkmanList(List<SCustomerLinkman> customerLinkmanList) {
		this.customerLinkmanList = customerLinkmanList;
	}

	private CustomerLinkmanService customerLinkmanService;

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

	public CustomerLinkmanService getCustomerLinkmanService() {
		return customerLinkmanService;
	}

	public void setCustomerLinkmanService(
			CustomerLinkmanService customerLinkmanService) {
		this.customerLinkmanService = customerLinkmanService;
	}
	
	/**
	 * @return the userPositionOrgId
	 */
	public String getUserPositionOrgId() {
		return userPositionOrgId;
	}

	/**
	 * @param userPositionOrgId the userPositionOrgId to set
	 */
	public void setUserPositionOrgId(String userPositionOrgId) {
		this.userPositionOrgId = userPositionOrgId;
	}

	public String selectCustomerLinkmanList(){
		
		
		if (this.pageNumber == null || this.pageNumber == 0L) {
			this.pageNumber = 1L;
		}
		Integer totalCount = new Integer(0);
		SUser user = (SUser) this.session.get(Constants.USER_INFO);
		Map permissionMap = (Map) this.session.get(Constants.USER_PERMISSION_MAP);
		String customerIds = (String) permissionMap.get("customerOwnerFilter");
		if(user.getRoleId()!=null)
			this.userPositionOrgId = user.getRoleId().toString();
		this.linkman = new SCustomerLinkman();
		if(name!=null){
			linkman.setName(name);
		}
		if(customerName!=null){
			linkman.setCustomerName(customerName);
		}
		if(customerOwnerUserName!=null){
			linkman.setCustomerOwnerUserName(customerOwnerUserName);
		}
		//if(user.getRoleId()==1){
		if(Constants.USER_FILTER_MARK.equals(customerIds)){
			this.customerLinkmanList = this.customerLinkmanService.getLinkmanList(pageNumber, pageSize, linkman);
	        totalCount = this.customerLinkmanService.getLinkmanCount(linkman);
		}else{
			if(customerIds==null){
				this.customerLinkmanList=null;
			}else{
				this.customerLinkmanList = this.customerLinkmanService.getLinkmanListByCustomerIds(pageNumber, pageSize, customerIds, linkman);
				totalCount = this.customerLinkmanService.getLinkmanCountByCustomerIds(customerIds, linkman);
			}
		}
		
		
		this.page = new Page();
		this.page.setData(customerLinkmanList);
		this.page.setPageSize(this.pageSize);
		this.page.setTotalCount(Long.valueOf(totalCount));
		this.page.setStart(this.pageNumber.intValue());
		
		return "selectCustomerLinkmanList";
	}
	
	public String addLinkmanInit(){
		this.enumerationList = this.enumerationService.getEnumerationByType(Constants.ENUMERATION_CUSTOMER_RP_EVALUATION_TYPE);
		return "addLinkmanInit";
	}
	
	public String addLinkmanFromCu() throws UnsupportedEncodingException{
		if(this.customerName != null){
			String strPtname = request.getParameter("customerName");
			this.customerName = new String(strPtname.getBytes("ISO-8859-1"), "UTF-8"); 
		}
		this.enumerationList = this.enumerationService.getEnumerationByType(Constants.ENUMERATION_CUSTOMER_RP_EVALUATION_TYPE);
		return "addLinkmanFromCu";
	}
	
	public String addLinkman(){
		SUser user = (SUser) this.session.get(Constants.USER_INFO);
		this.linkman.setCreateUserId(user.getUserId());
		this.linkman.setCreateUserName(user.getUserName());
		this.customerLinkmanService.addLinkman(this.linkman);
		return selectCustomerLinkmanList();
	}
	
	public String addLinkmanFC(){
		SUser user = (SUser) this.session.get(Constants.USER_INFO);
		this.linkman.setCreateUserId(user.getUserId());
		this.linkman.setCreateUserName(user.getUserName());
		this.customerLinkmanService.addLinkman(this.linkman);
		this.customerId = this.linkman.getCustomerId();
		return "addLinkmanFC";
	}
	
	public String updateLinkmanInit(){
		this.linkman = this.customerLinkmanService.getCustomerLinkmanById(this.linkmanId);
//		this.enumerationList = this.enumerationService.getEnumerationByType(4);
		SEnumeration enumeration = new SEnumeration();
		this.relationUsList = this.enumerationService.getEnumerationByType(Constants.ENUMERATION_CUSTOMER_RP_EVALUATION_TYPE);
			for (int i = 0; i < relationUsList.size(); i++) {
				enumeration = relationUsList.get(i);
				enumeration.setIsSelect("noselected");
				int relationUs = (linkman.getRelationUs() == null) ? 0 : linkman.getRelationUs();
				int enumerationId = enumeration.getEnumerationId();
				if(relationUs == enumerationId){
					enumeration.setIsSelect("selected");
					break;
				}
			}
			this.relationCompList = this.enumerationService.getEnumerationByType(Constants.ENUMERATION_CUSTOMER_RP_EVALUATION_TYPE);
			for (int i = 0; i < relationCompList.size(); i++) {
				enumeration = relationCompList.get(i);
				enumeration.setIsSelect("noselected");
				int relationComp = (linkman.getRelationComp() == null) ? 0 : linkman.getRelationComp();
				int enumerationId = enumeration.getEnumerationId();
				if(relationComp == enumerationId){
					enumeration.setIsSelect("selected");
					break;
				}
			}
		return "updateLinkmanInit";
	}
	
	public String updateLinkman(){
		SUser user = (SUser) this.session.get(Constants.USER_INFO);
		this.linkman.setCreateUserId(user.getUserId());
		this.linkman.setCreateUserName(user.getUserName());
		this.customerLinkmanService.updateLinkman(this.linkman);
		if(this.customerId!=null){
			return selectCustomerLinkmanList();
		}else{
			return selectCustomerLinkmanList();
		}
		
	}
	
	public String deleteLinkman(){
		this.customerLinkmanService.deleteLinkman(linkmanId);
		if(this.customerId!=null){
			return "deleteLinkman";
		}else{
			return selectCustomerLinkmanList();
		}
	}

}
