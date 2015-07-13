package com.sales.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import my.com.ibatis.core.dao.support.Page;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.sales.common.Constants;
import com.sales.model.SUser;
import com.sales.model.SaleBillStatus;
import com.sales.model.TCurrency;
import com.sales.model.TEmp;
import com.sales.model.TOrfq;
import com.sales.model.TOrfqEntry;
import com.sales.model.TPayColConditionEntry;
import com.sales.service.K3EntryService;
import com.sales.service.OrfqService;
import com.sales.service.SaleBillService;

public class OrfqAction extends BaseAction {
	
	private OrfqService orfqService;
	private List orfqList; 
	private TOrfq orfq;
	private Long pageNumber = 1L; // 页数
	private int pageSize = Page.DEFAULT_PAGE_SIZE; // 页面大小
	private Page page;
	private List<TPayColConditionEntry> payConditionList; 
	private List<TOrfqEntry> orfqEntrys; 
	private List<TCurrency> currencyList; 
	private JSONObject jsonStr;
	private List<TEmp> jsonAutoList;
	private Map statusMap;
	private SaleBillService saleBillService;
	private List saleBillList;
	private SaleBillStatus saleBill;
	private K3EntryService k3Service;
	private List departList;
	private List staffList;
	private List userList;
	private Map verifyMap;
	private Integer verifyName;
	private Integer roleId; 
	
	//add by goulin
	private List invoiceTypeList;		//开票方式
	private List transTypeList;			//运输方式
 
	public String editNew() { 
		if(this.orfq == null){
			this.orfq = new TOrfq();
		} 
		this.orfq.setFBillNo("");
		SUser sUser = getLoginUser();
		this.orfq.setBillUserName(sUser.getK3UserName());
		this.payConditionList = this.k3Service.getPayCondition();
		this.currencyList = this.k3Service.getCurrency();
		this.departList = this.k3Service.getDepartList();
		this.staffList = this.k3Service.getStaff();
		
		this.invoiceTypeList = k3Service.getInvoiceTypeList();
		this.transTypeList = k3Service.getTransTypeList();
//		this.userList = this.k3Service.getUser();
		return "add";
	}
 
	public String add() { 
		//制单人，从session获取
		SUser sUser = getLoginUser();
		this.orfq.setFBillerID(sUser.getK3UserId());
		this.orfqService.insertOrfq(this.orfq, this.orfqEntrys);
		return "list";
	}
	 
	public String show() {
		Map permissionMap = (Map) this.session.get(Constants.USER_PERMISSION_MAP);
		String customerIds = (String) permissionMap.get("customerOwnerFilter");
		this.orfq = this.orfqService.getOrfqById(this.orfq.getFInterID(), customerIds);
		this.saleBill = this.saleBillService.getSaleBillListByBillId(Constants.ORDER_RELATION_TYPE_ORFQ, orfq.getFInterID()); 
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
			this.response.sendRedirect("orfq!show?orfq.fInterID=" + billIds[0]);
			return null;
		}
		else{
			this.orfqList = this.orfqService.getOrfqAllList(billIdStr); 
			return "showAll";			
		}
	} 
	
	public String index(){ 
		//一级审核
		if(this.verifyName == null || this.verifyName.intValue() == Constants.ORFQ_VERIFY_ONE.intValue()){
			SUser sUser = getLoginUser();
			Map permissionMap = (Map) this.session.get(Constants.USER_PERMISSION_MAP);
			String customerIds = (String) permissionMap.get("customerOwnerFilter");
			if (this.pageNumber == null || this.pageNumber == 0L) {
				this.pageNumber = 1L;
			}
			if(this.orfq == null){
				this.orfq = new TOrfq();
			}
			else{
				if(this.orfq.getStartTime()!=null && this.orfq.getStartTime().trim().equals("")){
					this.orfq.setStartTime(null);
				}
				
				if(this.orfq.getEndTime()!=null && this.orfq.getEndTime().trim().equals("")){
					this.orfq.setEndTime(null);
				}
				
				if(this.orfq.getFEmpID()!=null && this.orfq.getFEmpID().trim().equals("")){
					this.orfq.setFEmpID(null);
				}
			}
			Integer totalCount = this.orfqService.getSOrfqCount(this.orfq, customerIds);
			if(totalCount > 0){ 
				this.orfqList = this.orfqService.getSOrfqList(pageNumber.intValue(), pageSize, this.orfq, customerIds);
			}
			this.page = new Page();
			this.page.setData(this.orfqList);
			this.page.setPageSize(this.pageSize);
			this.page.setTotalCount(Long.valueOf(totalCount));
			this.page.setStart(this.pageNumber.intValue()); 
			this.statusMap = Constants.K3STATUS;
			this.staffList = this.k3Service.getStaff();
			this.verifyMap = Constants.ORFQ_VERIFY_NAME;
			this.roleId = sUser.getRoleId();
			return "salesPageList";
		}
		else{
			Map permissionMap = (Map) this.session.get(Constants.USER_PERMISSION_MAP);
			String customerIds = (String) permissionMap.get("customerOwnerFilter");
			if (this.pageNumber == null || this.pageNumber == 0L) {
				this.pageNumber = 1L;
			}
			if(this.orfq == null){
				this.orfq = new TOrfq();
			}
			else{
				if(this.orfq.getStartTime()!=null && this.orfq.getStartTime().trim().equals("")){
					this.orfq.setStartTime(null);
				}
				
				if(this.orfq.getEndTime()!=null && this.orfq.getEndTime().trim().equals("")){
					this.orfq.setEndTime(null);
				}
				
				if(this.orfq.getFEmpID()!=null && this.orfq.getFEmpID().trim().equals("")){
					this.orfq.setFEmpID(null);
				}
			}
			Integer totalCount = this.orfqService.getOrfqCount(this.orfq, customerIds);
			if(totalCount > 0){ 
				this.orfqList = this.orfqService.getOrfqList(pageNumber.intValue(), pageSize, this.orfq, customerIds);
			}
			this.page = new Page();
			this.page.setData(this.orfqList);
			this.page.setPageSize(this.pageSize);
			this.page.setTotalCount(Long.valueOf(totalCount));
			this.page.setStart(this.pageNumber.intValue());
			this.statusMap = Constants.K3STATUS;
			this.staffList = this.k3Service.getStaff();
			this.verifyMap = Constants.ORFQ_VERIFY_NAME;
			return "pageList";
		}
	}
	
	public String jsonCurrency() { 
		String fCurrencyID = request.getParameter("fCurrencyID");
		TCurrency currency = new TCurrency();
		if(fCurrencyID!=null && !fCurrencyID.trim().equals("") && Integer.parseInt(fCurrencyID)>0){
			currency = this.k3Service.getCurrencyById(Integer.parseInt(fCurrencyID));
		} 
		this.jsonStr = JSONObject.fromObject(currency);
		return Constants.FORWARD_STR_JSON_RESULT;
	}
	
	public String autoComplete() {
		String searchtxt = this.request.getParameter("q");
		jsonAutoList = this.k3Service.getEmp(searchtxt);
		//this.jsonArray = JSONArray.fromObject(jsonUserList);
		return Constants.FORWARD_STR_JSON_RESULT;
	}
 
	public String delete() { 
		//删除销售报价单人，从session获取
		SUser sUser = getLoginUser(); 
		Map permissionMap = (Map) this.session.get(Constants.USER_PERMISSION_MAP);
		String customerIds = (String) permissionMap.get("customerOwnerFilter");
		this.orfqService.deleteOrfqById(this.orfq.getFInterID(), customerIds, sUser.getUserId());
		return "list";
	}
	
	public String pageList(){ 
		Map permissionMap = (Map) this.session.get(Constants.USER_PERMISSION_MAP);
		String customerIds = (String) permissionMap.get("customerOwnerFilter");
		if (this.pageNumber == null || this.pageNumber == 0L) {
			this.pageNumber = 1L;
		}
		if(this.orfq == null){
			this.orfq = new TOrfq();
		}
		else{
			if(this.orfq.getStartTime()!=null && this.orfq.getStartTime().trim().equals("")){
				this.orfq.setStartTime(null);
			}
			else if(this.orfq.getStartTime()!=null){
				this.orfq.setStartTime(this.orfq.getStartTime() + " 00:00:00");
			}
			if(this.orfq.getEndTime()!=null && this.orfq.getEndTime().trim().equals("")){
				this.orfq.setEndTime(null);
			}
			else if(this.orfq.getEndTime()!=null){
				this.orfq.setEndTime(this.orfq.getEndTime() + " 23:59:59");
			}
			if(this.orfq.getFEmpID()!=null && this.orfq.getFEmpID().trim().equals("")){
				this.orfq.setFEmpID(null);
			}
		}
		Integer totalCount = this.orfqService.getSOrfqCount(this.orfq, customerIds);
		if(totalCount > 0){ 
			this.orfqList = this.orfqService.getSOrfqList(pageNumber.intValue(), pageSize, this.orfq, customerIds);
		}
		this.page = new Page();
		this.page.setData(this.orfqList);
		this.page.setPageSize(this.pageSize);
		this.page.setTotalCount(Long.valueOf(totalCount));
		this.page.setStart(this.pageNumber.intValue()); 
		this.statusMap = Constants.K3STATUS;
		this.staffList = this.k3Service.getStaff();
		return "salesPageList";
	}
 
	public String initSOrfq() { 
		if(this.orfq == null){
			this.orfq = new TOrfq();
		} 
		this.orfq.setFBillNo("");
		SUser sUser = getLoginUser();
		this.orfq.setBillUserName(sUser.getK3UserName());
		this.payConditionList = this.k3Service.getPayCondition();
		this.currencyList = this.k3Service.getCurrency();
		this.departList = this.k3Service.getDepartList();
		this.staffList = this.k3Service.getStaff();
		
		this.invoiceTypeList = k3Service.getInvoiceTypeList();
		this.transTypeList = k3Service.getTransTypeList();
//		this.userList = this.k3Service.getUser();
		return "addSOrfq";
	}
 
	public String addSOrfq() throws IOException { 
		ActionContext ctx = ActionContext.getContext();
		HttpServletResponse response = (HttpServletResponse)ctx.get(ServletActionContext.HTTP_RESPONSE);
		String script = "";
		
		if(orfq.getFInterID() != null)
			return dealSOrfq();
		
		//制单人，从session获取
		SUser sUser = getLoginUser();
		this.orfq.setFBillerID(sUser.getK3UserId());
		this.orfq.setUserId(sUser.getUserId());
		try {
			this.orfqService.insertSOrfq(this.orfq, this.orfqEntrys, sUser.getUserName());
			script = "" +
					"<script>alert('单据保存成功！');" +
					"x = window.open('','_parent','');" +
					"if (window.opener) {window.opener.returnValue = 1;} else {window.returnValue = 1;}" +
					"x.close();</script>"; 
		}catch(Exception e) {
			String error = "单据保存时发生错误！";
			script =  "<script>alert(\"" + error + "\");</script>";
		}
		
		 
        response.getOutputStream().write(script.getBytes("utf-8"));
		return null;
	}
	
	public String showSorfq() {
		Map permissionMap = (Map) this.session.get(Constants.USER_PERMISSION_MAP);
		String customerIds = (String) permissionMap.get("customerOwnerFilter");
		SUser sUser = getLoginUser();
		if(Constants.ROLE_FINANCIAL.intValue() == sUser.getRoleId().intValue() || 
				Constants.ROLE_LOGISTICS.intValue() == sUser.getRoleId().intValue()){
			customerIds = Constants.USER_FILTER_MARK;
		}
		this.orfq = this.orfqService.getSOrfqById(this.orfq.getFInterID(), customerIds); 
//		this.saleBillList = NormalFun.getSaleBillList(this.saleBillService.getSaleBillStatusByOrderId(saleBill.getOrderId()));
		return "showSorfq";
	} 
	
	public String verify() {
		Map permissionMap = (Map) this.session.get(Constants.USER_PERMISSION_MAP);
		String customerIds = (String) permissionMap.get("customerOwnerFilter");
		this.orfq = this.orfqService.getSOrfqById(this.orfq.getFInterID(), customerIds); 
		//如果已经审核通过的不允许再次审核
		if(this.orfq!=null && this.orfq.getFStatus()!=null && this.orfq.getFStatus().intValue()==Constants.ORFQ_VERIFY_YES.intValue()){
			this.saleBill = this.saleBillService.getSaleBillListByBillId(Constants.ORDER_RELATION_TYPE_ORFQ, this.orfq.getFInterID());
			return "showSorfq";
		}
		else{
			this.statusMap = Constants.ORFQ_VERIFY_STATUS;
			return "verify";
		}
	} 
 
	public String verifySOrfq() throws IOException{ 
		Map permissionMap = (Map) this.session.get(Constants.USER_PERMISSION_MAP);
		String customerIds = (String) permissionMap.get("customerOwnerFilter");
		ActionContext ctx = ActionContext.getContext();
		HttpServletResponse response = (HttpServletResponse)ctx.get(ServletActionContext.HTTP_RESPONSE);
		String script = null;
		
		//如果该报价单已审核通过不允许再次审核在k3形成重复数据
		TOrfq oldOrfq = this.orfqService.getSOrfqById(this.orfq.getFInterID(), customerIds);
		
		try {
			if(oldOrfq!=null && oldOrfq.getFStatus()!=null && oldOrfq.getFStatus().intValue()!=Constants.ORFQ_VERIFY_YES.intValue()){
				//审核人，从session获取
				SUser sUser = getLoginUser(); 
				this.orfqService.verifySOrfq(this.orfq.getFInterID(), this.orfq.getFStatus(), sUser, customerIds);
			}
			script = "" +
					"<script>alert('单据审核操作成功！');" +
					"x = window.open('','_parent','');" +
					"if (window.opener) {window.opener.returnValue = 1;} else {window.returnValue = 1;}" +
					"x.close();</script>"; 
		}catch(Exception e) {
			e.printStackTrace();
			String error = "单据审核保存时发生错误:" + e.getMessage();
			actionLog.error(error);
			script =  "<script>alert(\"" + error + "\");</script>";
		}
		
		 
        response.getOutputStream().write(script.getBytes("utf-8"));
		return null;
		
	}
 
	public String deleteSOrfq() { 
		//删除销售报价单人，从session获取
		SUser sUser = getLoginUser(); 
		Map permissionMap = (Map) this.session.get(Constants.USER_PERMISSION_MAP);
		String customerIds = (String) permissionMap.get("customerOwnerFilter");
		this.orfqService.deleteSOrfqById(this.orfq.getFInterID(), customerIds, sUser.getUserId());
		return "addOkList";
	}
	
	public String updateSOrfq(){
		Map permissionMap = (Map) this.session.get(Constants.USER_PERMISSION_MAP);
		String customerIds = (String) permissionMap.get("customerOwnerFilter");
		this.orfq = this.orfqService.getSOrfqById(this.orfq.getFInterID(), customerIds); 
		this.payConditionList = this.k3Service.getPayCondition();
		this.currencyList = this.k3Service.getCurrency();
		this.departList = this.k3Service.getDepartList();
		this.staffList = this.k3Service.getStaff();
		this.invoiceTypeList = k3Service.getInvoiceTypeList();
		this.transTypeList = k3Service.getTransTypeList();
		
		return "updateSOrfq";
	}

	public String dealSOrfq() throws IOException {
		ActionContext ctx = ActionContext.getContext();
		HttpServletResponse response = (HttpServletResponse)ctx.get(ServletActionContext.HTTP_RESPONSE);
		String script = "";
		
		//制单人，从session获取
		SUser sUser = getLoginUser();
		this.orfq.setFBillerID(sUser.getK3UserId());
		Map permissionMap = (Map) this.session.get(Constants.USER_PERMISSION_MAP);
		String customerIds = (String) permissionMap.get("customerOwnerFilter");
		if(customerIds==null || customerIds.trim().equals("")){
			customerIds = "(-1)";
		}
		else if(Constants.USER_FILTER_MARK.equals(customerIds)){
			customerIds = null;
		}
		try {
			this.orfq.setCustomerIds(customerIds);
			this.orfqService.dealSOrfq(this.orfq, this.orfqEntrys);
			script = "" +
					"<script>alert('单据保存成功！');" +
					"x = window.open('','_parent','');" +
					"if (window.opener) {window.opener.returnValue = 1;} else {window.returnValue = 1;}" +
					"x.close();</script>"; 
		}catch(Exception e) {
			String error = "单据保存时发生错误！";
			script =  "<script>alert(\"" + error + "\");</script>";
		}
		
		response.getOutputStream().write(script.getBytes("utf-8"));
		return null;
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
	public Map getStatusMap() {
		return statusMap;
	}
	public void setStatusMap(Map statusMap) {
		this.statusMap = statusMap;
	}
	public List<TEmp> getJsonAutoList() {
		return jsonAutoList;
	}
	public void setJsonAutoList(List<TEmp> jsonAutoList) {
		this.jsonAutoList = jsonAutoList;
	}
	public JSONObject getJsonStr() {
		return jsonStr;
	}
	public void setJsonStr(JSONObject jsonStr) {
		this.jsonStr = jsonStr;
	}
	public List<TCurrency> getCurrencyList() {
		return currencyList;
	}
	public void setCurrencyList(List<TCurrency> currencyList) {
		this.currencyList = currencyList;
	}
	public List<TOrfqEntry> getOrfqEntrys() {
		return orfqEntrys;
	}
	public void setOrfqEntrys(List<TOrfqEntry> orfqEntrys) {
		this.orfqEntrys = orfqEntrys;
	}
	public List<TPayColConditionEntry> getPayConditionList() {
		return payConditionList;
	}
	public void setPayConditionList(List<TPayColConditionEntry> payConditionList) {
		this.payConditionList = payConditionList;
	}
	public OrfqService getOrfqService() {
		return orfqService;
	}
	public void setOrfqService(OrfqService orfqService) {
		this.orfqService = orfqService;
	} 
	public List getOrfqList() {
		return orfqList;
	}
	public void setOrfqList(List orfqList) {
		this.orfqList = orfqList;
	}
	public TOrfq getOrfq() {
		return orfq;
	}
	public void setOrfq(TOrfq orfq) {
		this.orfq = orfq;
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

	public Map getVerifyMap() {
		return verifyMap;
	}

	public void setVerifyMap(Map verifyMap) {
		this.verifyMap = verifyMap;
	}

	public Integer getVerifyName() {
		return verifyName;
	}

	public void setVerifyName(Integer verifyName) {
		this.verifyName = verifyName;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
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

}
