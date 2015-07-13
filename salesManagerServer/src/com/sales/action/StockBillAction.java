package com.sales.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import my.com.ibatis.core.dao.support.Page;

import com.sales.common.Constants;  
import com.sales.model.SaleBillStatus;
import com.sales.model.TStockBill;
import com.sales.service.K3EntryService;
import com.sales.service.SaleBillService;
import com.sales.service.StockBillService;

public class StockBillAction extends BaseAction { 
	
	private StockBillService stockBillService;
	private List stockBillList; 
	private TStockBill stockBill;
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
		this.stockBill = this.stockBillService.getStockBillById(this.stockBill.getFInterID(), customerIds);
		this.saleBill = this.saleBillService.getSaleBillListByBillId(Constants.ORDER_RELATION_TYPE_STOCKBILL, this.stockBill.getFInterID()); 
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
			this.response.sendRedirect("stockBill!show?stockBill.fInterID=" + billIds[0]);
			return null;
		}
		else{
			this.stockBillList = this.stockBillService.getStockBillAllList(billIdStr); 
			return "showAll";			
		}
	} 
	
	public String list(){ 
		Map permissionMap = (Map) this.session.get(Constants.USER_PERMISSION_MAP);
		String customerIds = (String) permissionMap.get("customerOwnerFilter");
		if (this.pageNumber == null || this.pageNumber == 0L) {
			this.pageNumber = 1L;
		}
		if(this.stockBill == null){
			this.stockBill = new TStockBill();
		}
		else{
			if(this.stockBill.getStartTime()!=null && this.stockBill.getStartTime().trim().equals("")){
				this.stockBill.setStartTime(null);
			}			
			if(this.stockBill.getEndTime()!=null && this.stockBill.getEndTime().trim().equals("")){
				this.stockBill.setEndTime(null);
			}
		}
		Integer totalCount = this.stockBillService.getStockBillCount(this.stockBill, customerIds);
		if(totalCount > 0){
			this.stockBillList = this.stockBillService.getStockBillList(pageNumber.intValue(), pageSize, this.stockBill, customerIds); 
		}
		this.page = new Page();
		this.page.setData(this.stockBillList);
		this.page.setPageSize(this.pageSize);
		this.page.setTotalCount(Long.valueOf(totalCount));
		this.page.setStart(this.pageNumber.intValue());
		this.statusMap = Constants.K3STATUS; 
		this.staffList = this.k3Service.getStaff();
		return "list";
	}

	public StockBillService getStockBillService() {
		return stockBillService;
	}

	public void setStockBillService(StockBillService stockBillService) {
		this.stockBillService = stockBillService;
	}

	public List getStockBillList() {
		return stockBillList;
	}

	public void setStockBillList(List stockBillList) {
		this.stockBillList = stockBillList;
	}

	public TStockBill getStockBill() {
		return stockBill;
	}

	public void setStockBill(TStockBill stockBill) {
		this.stockBill = stockBill;
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
