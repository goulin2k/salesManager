package com.sales.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import my.com.ibatis.core.dao.support.Page;

import com.sales.common.Constants; 
import com.sales.model.SaleBillStatus;
import com.sales.model.TStockBillOut;
import com.sales.service.K3EntryService;
import com.sales.service.SaleBillService;
import com.sales.service.StockBillOutService;

public class StockBillOutAction extends BaseAction { 
	
	private StockBillOutService stockBillOutService;
	private SaleBillService saleBillService;
	private List stockBillOutList; 
	private TStockBillOut stockBillOut;
	private Long pageNumber = 1L; // 页数
	private int pageSize = Page.DEFAULT_PAGE_SIZE; // 页面大小
	private Page page;
	private Map statusMap;
	private List saleBillList;
	private SaleBillStatus saleBill;
	private K3EntryService k3Service; 
	private List empList;
	 
	public String show() {
		Map permissionMap = (Map) this.session.get(Constants.USER_PERMISSION_MAP);
		String customerIds = (String) permissionMap.get("customerOwnerFilter");
		this.stockBillOut = this.stockBillOutService.getStockBillOutById(this.stockBillOut.getFInterID(), customerIds);
		this.saleBill = this.saleBillService.getSaleBillListByBillId(Constants.ORDER_RELATION_TYPE_STOCKBILLOUT, stockBillOut.getFInterID()); 
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
			this.response.sendRedirect("stockBillOut!show?stockBillOut.fInterID=" + billIds[0]);
			return null;
		}
		else{
			this.stockBillOutList = this.stockBillOutService.getStockBillOutAllList(billIdStr); 
			return "showAll";			
		}
	} 
	
	public String list(){ 
		Map permissionMap = (Map) this.session.get(Constants.USER_PERMISSION_MAP);
		String customerIds = (String) permissionMap.get("customerOwnerFilter");
		if (this.pageNumber == null || this.pageNumber == 0L) {
			this.pageNumber = 1L;
		}
		if(this.stockBillOut == null){
			this.stockBillOut = new TStockBillOut();
		}
		else{
			if(this.stockBillOut.getStartTime()!=null && this.stockBillOut.getStartTime().trim().equals("")){
				this.stockBillOut.setStartTime(null);
			}
			
			if(this.stockBillOut.getEndTime()!=null && this.stockBillOut.getEndTime().trim().equals("")){
				this.stockBillOut.setEndTime(null);
			}
			
		}
		Integer totalCount = this.stockBillOutService.getStockBillOutCount(this.stockBillOut, customerIds);
		if(totalCount > 0){
			this.stockBillOutList = this.stockBillOutService.getStockBillOutList(pageNumber.intValue(), pageSize, this.stockBillOut, customerIds); 
		}
		this.page = new Page();
		this.page.setData(this.stockBillOutList);
		this.page.setPageSize(this.pageSize);
		this.page.setTotalCount(Long.valueOf(totalCount));
		this.page.setStart(this.pageNumber.intValue());
		this.statusMap = Constants.K3STATUS; 
		this.empList = this.k3Service.getEmpList();
		return "list";
	}

	public StockBillOutService getStockBillOutService() {
		return stockBillOutService;
	}

	public void setStockBillOutService(StockBillOutService stockBillOutService) {
		this.stockBillOutService = stockBillOutService;
	}

	public List getStockBillOutList() {
		return stockBillOutList;
	}

	public void setStockBillOutList(List stockBillOutList) {
		this.stockBillOutList = stockBillOutList;
	}

	public TStockBillOut getStockBillOut() {
		return stockBillOut;
	}

	public void setStockBillOut(TStockBillOut stockBillOut) {
		this.stockBillOut = stockBillOut;
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
