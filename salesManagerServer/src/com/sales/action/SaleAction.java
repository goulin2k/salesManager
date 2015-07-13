package com.sales.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import my.com.ibatis.core.dao.support.Page;

import com.sales.common.Constants;  
import com.sales.model.SaleBillStatus;
import com.sales.model.TSale;
import com.sales.service.K3EntryService;
import com.sales.service.SaleBillService;
import com.sales.service.SaleService;

public class SaleAction extends BaseAction {
	
	private SaleService saleService;
	private List saleList; 
	private TSale sale;
	private Long pageNumber = 1L; // 页数
	private int pageSize = Page.DEFAULT_PAGE_SIZE; // 页面大小
	private Page page;
	private Map statusMap;
	private SaleBillService saleBillService;
	private List saleBillList;
	private SaleBillStatus saleBill;
	private K3EntryService k3Service;
	private List empList;

	public String show() {
		Map permissionMap = (Map) this.session.get(Constants.USER_PERMISSION_MAP);
		String customerIds = (String) permissionMap.get("customerOwnerFilter");
		this.sale = this.saleService.getSaleById(this.sale.getFInterID(), customerIds);
		this.saleBill = this.saleBillService.getSaleBillListByBillId(Constants.ORDER_RELATION_TYPE_SALE, this.sale.getFInterID()); 
//		this.saleBillList = NormalFun.getSaleBillList(this.saleBillService.getSaleBillStatusByOrderId(saleBill.getOrderId()));
		return "show";
	} 
	 
	public String showAll() throws IOException {
		String billIdStr = this.request.getParameter("billIdStr");
		String[] billIds = null;
		if(billIdStr != null){
			billIds = billIdStr.split(",");
		}
		if(billIds.length == 1){
			this.response.sendRedirect("sale!show?sale.fInterID=" + billIds[0]);
			return null;
		}
		else{
			this.saleList = this.saleService.getSaleAllList(billIdStr); 
			return "showAll";			
		}
	} 
	
	public String list(){ 
		Map permissionMap = (Map) this.session.get(Constants.USER_PERMISSION_MAP);
		String customerIds = (String) permissionMap.get("customerOwnerFilter");
		if (this.pageNumber == null || this.pageNumber == 0L) {
			this.pageNumber = 1L;
		}
		if(this.sale == null){
			this.sale = new TSale();
		}
		else{
			if(this.sale.getStartTime()!=null && this.sale.getStartTime().trim().equals("")){
				this.sale.setStartTime(null);
			}
			
			if(this.sale.getEndTime()!=null && this.sale.getEndTime().trim().equals("")){
				this.sale.setEndTime(null);
			}
			
		}
		Integer totalCount = this.saleService.getSaleCount(this.sale, customerIds);
		if(totalCount > 0){
			this.saleList = this.saleService.getSaleList(pageNumber.intValue(), pageSize, this.sale, customerIds);
		}
		this.page = new Page();
		this.page.setData(this.saleList);
		this.page.setPageSize(this.pageSize);
		this.page.setTotalCount(Long.valueOf(totalCount));
		this.page.setStart(this.pageNumber.intValue());
		this.statusMap = Constants.K3STATUS; 
		this.empList = this.k3Service.getEmpList();
		return "list";
	}
	
	public SaleService getSaleService() {
		return saleService;
	}
	public void setSaleService(SaleService saleService) {
		this.saleService = saleService;
	}
	public List getSaleList() {
		return saleList;
	}
	public void setSaleList(List saleList) {
		this.saleList = saleList;
	}
	public TSale getSale() {
		return sale;
	}
	public void setSale(TSale sale) {
		this.sale = sale;
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
