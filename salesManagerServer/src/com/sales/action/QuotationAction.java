package com.sales.action;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import my.com.ibatis.core.dao.support.Page;

import com.sales.common.Constants;
import com.sales.model.SSalesQuotation;
import com.sales.model.SSalesQuotationProduct;
import com.sales.model.SUser;
import com.sales.model.TCurrency;
import com.sales.model.TCustomer;
import com.sales.model.TOrfq; 
import com.sales.model.TPayColConditionEntry;
import com.sales.service.CustomerService;
import com.sales.service.K3EntryService;
import com.sales.service.QuotationService;
import com.sales.service.SUserService;

public class QuotationAction extends BaseAction {
	
	private QuotationService quotationService;
	private CustomerService customerService;
	private SUserService sUserService;
	private List quotationList;
	private SSalesQuotation quotation; 
	private Long pageNumber = 1L; // 页数
	private int pageSize = Page.DEFAULT_PAGE_SIZE;; // 页面大小
	private Page page ;
	private List<SSalesQuotationProduct> products; 
	private Integer roleId;
	private List purchaseList;
	private TOrfq orfq;
	private K3EntryService k3Service;
	private List departList;
	private List staffList;
	private List userList; 	
	private List<TPayColConditionEntry> payConditionList;  
	private List<TCurrency> currencyList; 
	//add by goulin
	private List invoiceTypeList;		//开票方式
	private List transTypeList;			//运输方式
	
	private TCustomer customer;
	
	public List<SSalesQuotationProduct> getProducts() {
		return products;
	}

	public void setProducts(List<SSalesQuotationProduct> products) {
		this.products = products;
	}

	public String editNew() { 
		this.quotation = new SSalesQuotation();
		Calendar cal = Calendar.getInstance();   	
		String quotationCode = this.quotationService.getQuotationCode(cal.get(Calendar.YEAR));
		this.quotation.setQuotationCode(quotationCode);
		this.purchaseList = sUserService.getUserListByPositionId(Constants.ROLE_PURCHARSE);
		return "add";
	}
	
	public String add() throws Exception{ 
		if(this.quotation == null){
			this.quotation = new SSalesQuotation();			
		}
		SUser sUser = getLoginUser();
		this.quotation.setQuotationUserId(sUser.getUserId());
		this.quotation.setQuotationUserName(sUser.getUserName());
		this.quotation.setStatus(Constants.QUOTATION_NOT_REPLY);
		this.quotationService.addQuotation(this.quotation, this.products); 
    	return "list";
    }
 
	public String show() {
		SUser sUser = getLoginUser();
		Map permissionMap = (Map) this.session.get(Constants.USER_PERMISSION_MAP);
		String childUserIds = (String) permissionMap.get("childUserFilter"); 
		this.quotation = this.quotationService.getQuotationById(this.quotation.getQuotationId(), 
				childUserIds, sUser.getUserId(), sUser.getRoleId());
		return "show";
	}
 
	public String reply() {
		SUser sUser = getLoginUser(); 
		Map permissionMap = (Map) this.session.get(Constants.USER_PERMISSION_MAP);
		String childUserIds = (String) permissionMap.get("childUserFilter"); 
		this.quotation = this.quotationService.getQuotationById(this.quotation.getQuotationId(), 
				childUserIds, sUser.getUserId(), sUser.getRoleId());
		return "reply";
	}
	
	public String update(){
		SUser sUser = getLoginUser();
		this.quotation.setPurchaseUserId(sUser.getUserId()); 
		this.quotation.setPurchaseUserName(sUser.getUserName());
		this.quotation.setStatus(Constants.QUOTATION_REPLY);
		this.quotationService.updateQuotation(this.quotation, this.products); 
		return "list";
	}
	
	public String index(){ 
		Map permissionMap = (Map) this.session.get(Constants.USER_PERMISSION_MAP);
		String childUserIds = (String) permissionMap.get("childUserFilter"); 
		SUser sUser = getLoginUser();
		if (this.pageNumber == null || this.pageNumber == 0L) {
			this.pageNumber = 1L;
		}
		if(this.quotation == null){
			this.quotation = new SSalesQuotation();
			Calendar cal = Calendar.getInstance(Locale.CHINA);   			  
			SimpleDateFormat datef=new SimpleDateFormat("yyyy-MM-dd");  
			//今天 
			Date endTime = cal.getTime();  
			this.quotation.setEndTime(datef.format(endTime));
			//前一周           
			cal.add(Calendar.DATE, -6);
			Date startTime = cal.getTime();   
			this.quotation.setStartTime(datef.format(startTime)); 
		}
//		this.quotation.setPurchaseUserId(sUser.getUserId());
		if(this.quotation!=null && this.quotation.getProductCode()!=null && this.quotation.getProductCode().trim().equals("")){
			this.quotation.setProductCode(null);
		}
		if(this.quotation!=null && this.quotation.getQuotationUserName()!=null && this.quotation.getQuotationUserName().trim().equals("")){
			this.quotation.setQuotationUserName(null);
		}
		Integer totalCount = this.quotationService.getQuotationCount(this.quotation, childUserIds, sUser.getRoleId());
		if(totalCount.intValue() > 0){
			this.quotationList = this.quotationService.getQuotationList(this.quotation, pageNumber.intValue(), pageSize, childUserIds, sUser.getRoleId());
		}
		this.page = new Page();
		this.page.setData(this.quotationList);
		this.page.setPageSize(this.pageSize);
		this.page.setTotalCount(Long.valueOf(totalCount));
		this.page.setStart(this.pageNumber.intValue());
		this.roleId = sUser.getRoleId();
		return "pageList";
	}
	 
	public String toOrfq() { 
		this.quotation = this.quotationService.toOrfq(this.quotation.getQuotationId()); 
		if(this.orfq == null){
			this.orfq = new TOrfq();
		} 
		this.orfq.setFBillNo("");
		Integer customerId = quotation.getCustomerId();
		this.customer = customerService.getCustomerInfoById(customerId);
		if(customer == null) {
			this.actionLog.error("toOrfq error:客户资料未找到[id=" + customerId + "]");
			throw(new RuntimeException("toOrfq error:客户资料未找到[id=" + customerId + "]"));
		}
		this.orfq.setPayCondition(customer.getPayCondition());
		this.orfq.setFPayCondition(customer.getPayConditionId());
		
		SUser sUser = getLoginUser();
		this.orfq.setBillUserName(sUser.getK3UserName());
		this.payConditionList = this.k3Service.getPayCondition();
		this.currencyList = this.k3Service.getCurrency();
		this.departList = this.k3Service.getDepartList();
		this.staffList = this.k3Service.getStaff();		
		this.invoiceTypeList = k3Service.getInvoiceTypeList();
		this.transTypeList = k3Service.getTransTypeList();
    	return "toOrfq";
    }
	
	private String getQuotationCode(){
		return String.valueOf(System.currentTimeMillis());
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
	public QuotationService getQuotationService() {
		return quotationService;
	}
	public void setQuotationService(QuotationService quotationService) {
		this.quotationService = quotationService;
	}
	public List getQuotationList() {
		return quotationList;
	}
	public void setQuotationList(List quotationList) {
		this.quotationList = quotationList;
	}
	public SSalesQuotation getQuotation() {
		return quotation;
	}
	public void setQuotation(SSalesQuotation quotation) {
		this.quotation = quotation;
	}
	public Long getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(Long pageNumber) {
		this.pageNumber = pageNumber;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public List getPurchaseList() {
		return purchaseList;
	}

	public void setPurchaseList(List purchaseList) {
		this.purchaseList = purchaseList;
	}

	public SUserService getsUserService() {
		return sUserService;
	}

	public void setsUserService(SUserService sUserService) {
		this.sUserService = sUserService;
	}

	public K3EntryService getK3Service() {
		return k3Service;
	}

	public void setK3Service(K3EntryService k3Service) {
		this.k3Service = k3Service;
	}

	public TOrfq getOrfq() {
		return orfq;
	}

	public void setOrfq(TOrfq orfq) {
		this.orfq = orfq;
	}

	public List getDepartList() {
		return departList;
	}

	public void setDepartList(List departList) {
		this.departList = departList;
	}

	public List getStaffList() {
		return staffList;
	}

	public void setStaffList(List staffList) {
		this.staffList = staffList;
	}

	public List getUserList() {
		return userList;
	}

	public void setUserList(List userList) {
		this.userList = userList;
	}

	public List<TPayColConditionEntry> getPayConditionList() {
		return payConditionList;
	}

	public void setPayConditionList(List<TPayColConditionEntry> payConditionList) {
		this.payConditionList = payConditionList;
	}

	public List<TCurrency> getCurrencyList() {
		return currencyList;
	}

	public void setCurrencyList(List<TCurrency> currencyList) {
		this.currencyList = currencyList;
	}

	public List getInvoiceTypeList() {
		return invoiceTypeList;
	}

	public void setInvoiceTypeList(List invoiceTypeList) {
		this.invoiceTypeList = invoiceTypeList;
	}

	public List getTransTypeList() {
		return transTypeList;
	}

	public void setTransTypeList(List transTypeList) {
		this.transTypeList = transTypeList;
	}

	/**
	 * @return the customer
	 */
	public TCustomer getCustomer() {
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(TCustomer customer) {
		this.customer = customer;
	}

	/**
	 * @param customerService the customerService to set
	 */
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	} 

}
