package com.sales.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import my.com.ibatis.core.dao.support.Page;

import com.sales.common.Constants;
import com.sales.common.NormalFun;
import com.sales.model.SUser;
import com.sales.model.TOrder;
import com.sales.service.K3EntryService;
import com.sales.service.OrderService;
import com.sales.service.SaleBillService;

public class OrderAction extends BaseAction {
	
	private OrderService orderService;
	private SaleBillService saleBillService;
	private List orderList; 
	private TOrder order;
	private Long pageNumber = 1L; // 页数
	private int pageSize = Page.DEFAULT_PAGE_SIZE; // 页面大小
	private Page page;
	private Map statusMap;
	private List saleBillList;
	private int  orderBillStatus;				//当前订单执行的最新状态
	private K3EntryService k3Service; 
	private List staffList;
	 
	public String show() {
		Map permissionMap = (Map) this.session.get(Constants.USER_PERMISSION_MAP);
		String customerIds = (String) permissionMap.get("customerOwnerFilter");
		this.order = this.orderService.getOrderById(this.order.getFInterID(), customerIds);
		if(order != null) {
			this.saleBillList = NormalFun.getSaleBillList(saleBillService.getSaleBillStatusByOrderId(this.order.getFInterID()));
			this.orderBillStatus = NormalFun.getSaleBillMaxStatus(saleBillList);
		}
		return "show";
	} 
	 
	public String showAll() throws IOException {
		String orderIdStr = this.request.getParameter("orderIdStr");
		String[] orderIds = null;
		if(orderIdStr != null){
			orderIds = orderIdStr.split(",");
		}
		if(orderIds.length == 1){
			this.response.sendRedirect("order!show?order.fInterID=" + orderIds[0]);
			return null;
		}
		else{
			this.orderList = this.orderService.getOrderAllList(orderIdStr); 
			return "showAll";			
		}
	} 
	
	public String index(){ 
		Map permissionMap = (Map) this.session.get(Constants.USER_PERMISSION_MAP);
		String customerIds = (String) permissionMap.get("customerOwnerFilter");
		if (this.pageNumber == null || this.pageNumber == 0L) {
			this.pageNumber = 1L;
		}
		if(this.order == null){
			this.order = new TOrder();
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
			this.orderList = this.orderService.getOrderList(pageNumber.intValue(), pageSize, order, customerIds); 
		}
		this.page = new Page();
		this.page.setData(this.orderList);
		this.page.setPageSize(this.pageSize);
		this.page.setTotalCount(Long.valueOf(totalCount));
		this.page.setStart(this.pageNumber.intValue());
		this.statusMap = Constants.K3STATUS; 
		this.staffList = this.k3Service.getStaff();
		return "pageList";
	}
	
	public List getSaleBillList() {
		return saleBillList;
	}

	public void setSaleBillList(List saleBillList) {
		this.saleBillList = saleBillList;
	}

	public SaleBillService getSaleBillService() {
		return saleBillService;
	}

	public void setSaleBillService(SaleBillService saleBillService) {
		this.saleBillService = saleBillService;
	}

	public Map getStatusMap() {
		return statusMap;
	}

	public void setStatusMap(Map statusMap) {
		this.statusMap = statusMap;
	}

	public OrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public List getOrderList() {
		return orderList;
	}

	public void setOrderList(List orderList) {
		this.orderList = orderList;
	}

	public TOrder getOrder() {
		return order;
	}

	public void setOrder(TOrder order) {
		this.order = order;
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

	/**
	 * @return the orderBillStatus
	 */
	public int getOrderBillStatus() {
		return orderBillStatus-1;
	}  

}
