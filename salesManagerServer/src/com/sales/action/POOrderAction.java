package com.sales.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import my.com.ibatis.core.dao.support.Page;
 
import com.sales.common.Constants; 
import com.sales.model.SaleBillStatus;
import com.sales.model.TPOOrder;

import com.sales.service.K3EntryService;
import com.sales.service.POOrderService;
import com.sales.service.SaleBillService;

/**
 * 采购订单Action
 * @author Goulin
 *
 */
public class POOrderAction extends BaseAction {
	private static final long serialVersionUID = 2160746096061303740L;
	
	private POOrderService orderService;
	private TPOOrder order;
	private List<TPOOrder> ordertList;
	private Long pageNumber = 1L; // 页数
	private int pageSize = Page.DEFAULT_PAGE_SIZE; // 页面大小
	private Page page;
	private Map statusMap;
	private SaleBillService saleBillService;
	private List saleBillList;
	private SaleBillStatus saleBill;
	private K3EntryService k3Service;
	private List empList;
	
	
	/**
	 * @return the order
	 */
	public TPOOrder getOrder() {
		return order;
	}
	/**
	 * @param order the order to set
	 */
	public void setOrder(TPOOrder order) {
		this.order = order;
	}
	/**
	 * @return the ordertList
	 */
	public List<TPOOrder> getOrdertList() {
		return ordertList;
	}
	/**
	 * @param ordertList the ordertList to set
	 */
	public void setOrdertList(List<TPOOrder> ordertList) {
		this.ordertList = ordertList;
	}
	/**
	 * @param orderService the orderService to set
	 */
	public void setOrderService(POOrderService orderService) {
		this.orderService = orderService;
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
	
	/**
	 * 采购订单查询列表
	 * @return
	 */
	public String index(){
		String customerIds = getPermissionCustomerIds();
		if (this.pageNumber == null || this.pageNumber == 0L) {
			this.pageNumber = 1L;
		}
		if(this.order == null){
			this.order = new TPOOrder();
		}
		else{
			if(this.order.getStartTime()!=null && this.order.getStartTime().trim().equals("")){
				this.order.setStartTime(null);
			}
			
			if(this.order.getEndTime()!=null && this.order.getEndTime().trim().equals("")){
				this.order.setEndTime(null);
			}
		}
		Integer totalCount = this.orderService.getOrderCount(order, customerIds);
		if(totalCount > 0){
			this.ordertList = this.orderService.getOrderList(
					pageNumber.intValue(), pageSize, this.order, customerIds); 
		}
		this.page = new Page();
		this.page.setData(this.ordertList);
		this.page.setPageSize(this.pageSize);
		this.page.setTotalCount(Long.valueOf(totalCount));
		this.page.setStart(this.pageNumber.intValue());
		
		this.statusMap = Constants.K3_POORDER_STATUS; 
		this.empList = this.k3Service.getEmpList();
		return "list";
	}
	 
	/**
	 * 采购订单详细显示
	 * @return
	 */
	public String show() {
		String customerIds = getPermissionCustomerIds();
		this.order = this.orderService.getOrderById(order.getFInterId(), customerIds);
		if(order != null)
			this.saleBill = saleBillService.getSaleBillListByBillId(
					Constants.ORDER_RELATION_TYPE_POORDER, order.getFInterId()); 
		return "show";
	}

	
	 
	/**
	 * 展示被其它单据关联的采购订单列表
	 * @return
	 * @throws IOException
	 */
	public String showAll() throws IOException {
		String billIdStr = super.request.getParameter("billIdStr");
		String[] billIds = null;
		if(billIdStr != null){
			billIds = billIdStr.split(",");
		}
		if(billIds.length == 1){
			this.response.sendRedirect("poorder!show?order.fInterId=" + billIds[0]);
			return null;
		}
		else{
			this.ordertList = this.orderService.getOrderList(
					billIdStr, getPermissionCustomerIds()); 
			return "showAll";			
		}
	} 

}
