package com.sales.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import my.com.ibatis.core.dao.support.Page;
 
import com.sales.common.Constants;
import com.sales.model.SaleBillStatus;
import com.sales.model.TReturnGoods; 
import com.sales.service.K3EntryService;
import com.sales.service.ReturnGoodsService; 
import com.sales.service.SaleBillService;

public class ReturnGoodsAction extends BaseAction { 
	
	private ReturnGoodsService returnGoodsService;
	private List returnGoodsList; 
	private TReturnGoods returnGoods;
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
		this.returnGoods = this.returnGoodsService.getReturnGoodsById(this.returnGoods.getFInterID());
		this.saleBill = this.saleBillService.getSaleBillListByBillId(Constants.ORDER_RELATION_TYPE_RETURNGOODS, returnGoods.getFInterID()); 
		return "show";
	} 
	 
	public String showAll() throws IOException {
		String billIdStr = super.request.getParameter("billIdStr");
		String[] billIds = null;
		if(billIdStr != null){
			billIds = billIdStr.split(",");
		}
		if(billIds.length == 1){
			this.response.sendRedirect("returnGoods!show?returnGoods.fInterID=" + billIds[0]);
			return null;
		}
		else{
			this.returnGoodsList = this.returnGoodsService.getReturnGoodsAllList(billIdStr); 
			return "showAll";			
		}
	} 
	
	public String list(){ 
		if (this.pageNumber == null || this.pageNumber == 0L) {
			this.pageNumber = 1L;
		}
		if(this.returnGoods == null){
			this.returnGoods = new TReturnGoods();
		}
		else{
			if(this.returnGoods.getStartTime()!=null && this.returnGoods.getStartTime().trim().equals("")){
				this.returnGoods.setStartTime(null);
			}
			if(this.returnGoods.getEndTime()!=null && this.returnGoods.getEndTime().trim().equals("")){
				this.returnGoods.setEndTime(null);
			}
		}
		Integer totalCount = this.returnGoodsService.getReturnGoodsCount(this.returnGoods);
		if(totalCount > 0){
			this.returnGoodsList = this.returnGoodsService.getReturnGoodsList(pageNumber.intValue(), pageSize, this.returnGoods); 
		}
		this.page = new Page();
		this.page.setData(this.returnGoodsList);
		this.page.setPageSize(this.pageSize);
		this.page.setTotalCount(Long.valueOf(totalCount));
		this.page.setStart(this.pageNumber.intValue());
		this.statusMap = Constants.K3STATUS; 
		this.empList = this.k3Service.getEmpList();
		return "list";
	}

	public ReturnGoodsService getReturnGoodsService() {
		return returnGoodsService;
	}

	public void setReturnGoodsService(ReturnGoodsService returnGoodsService) {
		this.returnGoodsService = returnGoodsService;
	}

	public List getReturnGoodsList() {
		return returnGoodsList;
	}

	public void setReturnGoodsList(List returnGoodsList) {
		this.returnGoodsList = returnGoodsList;
	}

	public TReturnGoods getReturnGoods() {
		return returnGoods;
	}

	public void setReturnGoods(TReturnGoods returnGoods) {
		this.returnGoods = returnGoods;
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
