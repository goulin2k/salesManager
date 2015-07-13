package com.sales.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import my.com.ibatis.core.dao.support.Page;
 
import com.sales.common.Constants;
import com.sales.model.SaleBillStatus;
import com.sales.model.TReceiveBill;
import com.sales.service.K3EntryService;
import com.sales.service.ReceiveBillService;
import com.sales.service.SaleBillService;

public class ReceiveBillAction extends BaseAction { 
	
	private ReceiveBillService receiveBillService;
	private List receiveBillList; 
	private TReceiveBill receiveBill;
	private Long pageNumber = 1L; // 页数
	private int pageSize = Page.DEFAULT_PAGE_SIZE; // 页面大小
	private Page page;
	private Map statusMap;
	private SaleBillService saleBillService;
	private List saleBillList;
	private SaleBillStatus saleBill;
	private K3EntryService k3Service; 
	private List staffList;
	 
	public String show() {
		Map permissionMap = (Map) this.session.get(Constants.USER_PERMISSION_MAP);
		String customerIds = (String) permissionMap.get("customerOwnerFilter");
		this.receiveBill = this.receiveBillService.getReceiveBillById(this.receiveBill.getFBillID(), customerIds);
		this.saleBill = this.saleBillService.getSaleBillListByBillId(Constants.ORDER_RELATION_TYPE_RECEIVEBILL, receiveBill.getFBillID()); 
		return "show";
	} 
	 
	public String showAll() throws IOException {
		String billIdStr = this.request.getParameter("billIdStr");
		String[] billIds = null;
		if(billIdStr != null){
			billIds = billIdStr.split(",");
		}
		if(billIds.length == 1){
			this.response.sendRedirect("receiveBill!show?receiveBill.fBillID=" + billIds[0]);
			return null;
		}
		else{
			this.receiveBillList = this.receiveBillService.getReceiveBillAllList(billIdStr); 
			return "showAll";			
		}
	} 
	
	public String list(){ 
		Map permissionMap = (Map) this.session.get(Constants.USER_PERMISSION_MAP);
		String customerIds = (String) permissionMap.get("customerOwnerFilter");
		if (this.pageNumber == null || this.pageNumber == 0L) {
			this.pageNumber = 1L;
		}
		if(this.receiveBill == null){
			this.receiveBill = new TReceiveBill();
		}
		else{
			if(this.receiveBill.getStartTime()!=null && this.receiveBill.getStartTime().trim().equals("")){
				this.receiveBill.setStartTime(null);
			}
			
			if(this.receiveBill.getEndTime()!=null && this.receiveBill.getEndTime().trim().equals("")){
				this.receiveBill.setEndTime(null);
			}
			
			if(receiveBill.getEmployeeNumber() != null ) {
				if(receiveBill.getEmployeeNumber().trim().equals(""))
					receiveBill.setEmployeeNumber(null);
			}
			
		}
		Integer totalCount = this.receiveBillService.getReceiveBillCount(receiveBill, customerIds);
		if(totalCount > 0){
			this.receiveBillList = this.receiveBillService.getReceiveBillList(pageNumber.intValue(), pageSize, receiveBill, customerIds);
		}
		this.page = new Page();
		this.page.setData(this.receiveBillList);
		this.page.setPageSize(this.pageSize);
		this.page.setTotalCount(Long.valueOf(totalCount));
		this.page.setStart(this.pageNumber.intValue());
		this.statusMap = Constants.K3STATUS;
		this.staffList = this.k3Service.getEmpList();
		return "list";
	}

	public ReceiveBillService getReceiveBillService() {
		return receiveBillService;
	}

	public void setReceiveBillService(ReceiveBillService receiveBillService) {
		this.receiveBillService = receiveBillService;
	}

	public List getReceiveBillList() {
		return receiveBillList;
	}

	public void setReceiveBillList(List receiveBillList) {
		this.receiveBillList = receiveBillList;
	}

	public TReceiveBill getReceiveBill() {
		return receiveBill;
	}

	public void setReceiveBill(TReceiveBill receiveBill) {
		this.receiveBill = receiveBill;
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

	public List getStaffList() {
		return staffList;
	}

	public void setStaffList(List staffList) {
		this.staffList = staffList;
	}

}
