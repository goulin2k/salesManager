package com.sales.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import my.com.ibatis.core.dao.support.Page;
 
import com.sales.common.Constants; 
import com.sales.model.SaleBillStatus;
import com.sales.model.TRequest;
import com.sales.service.K3EntryService;
import com.sales.service.RequestService;
import com.sales.service.SaleBillService;

public class RequestAction extends BaseAction {
	
	private RequestService requestService;
	private TRequest request;
	private List requestList;
	private Long pageNumber = 1L; // 页数
	private int pageSize = Page.DEFAULT_PAGE_SIZE; // 页面大小
	private Page page;
	private Map statusMap;
	private SaleBillService saleBillService;
	private List saleBillList;
	private SaleBillStatus saleBill;
	private K3EntryService k3Service;
	private List empList;

	public String list(){
		Map permissionMap = (Map) this.session.get(Constants.USER_PERMISSION_MAP);
		String customerIds = (String) permissionMap.get("customerOwnerFilter");
		if (this.pageNumber == null || this.pageNumber == 0L) {
			this.pageNumber = 1L;
		}
		if(this.request == null){
			this.request = new TRequest();
		}
		else{
			if(this.request.getStartTime()!=null && this.request.getStartTime().trim().equals("")){
				this.request.setStartTime(null);
			}
			
			if(this.request.getEndTime()!=null && this.request.getEndTime().trim().equals("")){
				this.request.setEndTime(null);
			}
		}
		Integer totalCount = this.requestService.getRequestCount(this.request, customerIds);
		if(totalCount > 0){
			this.requestList = this.requestService.getRequestList(pageNumber.intValue(), pageSize, this.request, customerIds); 
		}
		this.page = new Page();
		this.page.setData(this.requestList);
		this.page.setPageSize(this.pageSize);
		this.page.setTotalCount(Long.valueOf(totalCount));
		this.page.setStart(this.pageNumber.intValue());
		this.statusMap = Constants.K3STATUS; 
		this.empList = this.k3Service.getEmpList();
		return "list";
	}
	 
	public String show() {
		Map permissionMap = (Map) this.session.get(Constants.USER_PERMISSION_MAP);
		String customerIds = (String) permissionMap.get("customerOwnerFilter");
		this.request = this.requestService.getRequestById(this.request.getFInterId(), customerIds);
		if(request != null)
			this.saleBill = this.saleBillService.getSaleBillListByBillId(Constants.ORDER_RELATION_TYPE_REQUEST, request.getFInterId()); 
//		this.saleBillList = NormalFun.getSaleBillList(this.saleBillService.getSaleBillStatusByOrderId(saleBill.getOrderId()));
		return "show";
	} 
	 
	public String showAll() throws IOException {
		String billIdStr = super.request.getParameter("billIdStr");
		String[] billIds = null;
		if(billIdStr != null){
			billIds = billIdStr.split(",");
		}
		if(billIds.length == 1){
			this.response.sendRedirect("request!show?request.fInterId=" + billIds[0]);
			return null;
		}
		else{
			this.requestList = this.requestService.getRequestAllList(billIdStr); 
			return "showAll";			
		}
	} 
	
	public RequestService getRequestService() {
		return requestService;
	}
	public void setRequestService(RequestService requestService) {
		this.requestService = requestService;
	}
	public TRequest getRequest() {
		return request;
	}
	public void setRequest(TRequest request) {
		this.request = request;
	}
	public List getRequestList() {
		return requestList;
	}
	public void setRequestList(List requestList) {
		this.requestList = requestList;
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
	public Map getStatusMap() {
		return statusMap;
	}
	public void setStatusMap(Map statusMap) {
		this.statusMap = statusMap;
	}
	
	public SaleBillService getSaleBillService() {
		return saleBillService;
	}

	public void setSaleBillService(SaleBillService saleBillService) {
		this.saleBillService = saleBillService;
	}

	public List getSaleBillList() {
		return saleBillList;
	}

	public void setSaleBillList(List saleBillList) {
		this.saleBillList = saleBillList;
	}

	public SaleBillStatus getSaleBill() {
		return saleBill;
	}

	public void setSaleBill(SaleBillStatus saleBill) {
		this.saleBill = saleBill;
	}

	public K3EntryService getK3Service() {
		return k3Service;
	}

	public void setK3Service(K3EntryService k3Service) {
		this.k3Service = k3Service;
	}

	public List getEmpList() {
		return empList;
	}

	public void setEmpList(List empList) {
		this.empList = empList;
	} 

}
