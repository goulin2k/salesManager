package com.sales.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import my.com.ibatis.core.dao.support.Page;

import com.sales.common.Constants;  
import com.sales.model.SUser;
import com.sales.model.SaleBillStatus;
import com.sales.model.TOutStock;
import com.sales.service.K3EntryService;
import com.sales.service.OutStockService;
import com.sales.service.SaleBillService;

public class OutStockAction extends BaseAction { 
	
	private OutStockService outStockService;
	private List outStockList; 
	private TOutStock outStock;
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
		this.outStock = this.outStockService.getOutStockById(this.outStock.getFInterID(), customerIds);
		this.saleBill = this.saleBillService.getSaleBillListByBillId(Constants.ORDER_RELATION_TYPE_OUTSTOCK, outStock.getFInterID()); 
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
			this.response.sendRedirect("outStock!show?outStock.fInterID=" + billIds[0]);
			return null;
		}
		else{
			this.outStockList = this.outStockService.getOutStockAllList(billIdStr); 
			return "showAll";			
		}
	} 
	
	public String list(){ 
		Map permissionMap = (Map) this.session.get(Constants.USER_PERMISSION_MAP);
		String customerIds = (String) permissionMap.get("customerOwnerFilter");
		if (this.pageNumber == null || this.pageNumber == 0L) {
			this.pageNumber = 1L;
		}
		if(this.outStock == null){
			this.outStock = new TOutStock();
		}
		else{
			if(this.outStock.getStartTime()!=null && this.outStock.getStartTime().trim().equals("")){
				this.outStock.setStartTime(null);
			}
			if(this.outStock.getEndTime()!=null && this.outStock.getEndTime().trim().equals("")){
				this.outStock.setEndTime(null);
			}
		}
		Integer totalCount = this.outStockService.getOutStockCount(outStock, customerIds);
		if(totalCount > 0){
			this.outStockList = this.outStockService.getOutStockList(pageNumber.intValue(), pageSize, outStock, customerIds);
		}
		this.page = new Page();
		this.page.setData(this.outStockList);
		this.page.setPageSize(this.pageSize);
		this.page.setTotalCount(Long.valueOf(totalCount));
		this.page.setStart(this.pageNumber.intValue());
		this.statusMap = Constants.K3STATUS; 
		this.empList = this.k3Service.getEmpList();
		return "list";
	}
	
	public OutStockService getOutStockService() {
		return outStockService;
	}
	public void setOutStockService(OutStockService outStockService) {
		this.outStockService = outStockService;
	}
	public List getOutStockList() {
		return outStockList;
	}
	public void setOutStockList(List outStockList) {
		this.outStockList = outStockList;
	}
	public TOutStock getOutStock() {
		return outStock;
	}
	public void setOutStock(TOutStock outStock) {
		this.outStock = outStock;
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
