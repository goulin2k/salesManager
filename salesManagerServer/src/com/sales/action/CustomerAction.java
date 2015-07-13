package com.sales.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import my.com.ibatis.core.dao.support.Page;

import com.sales.service.ActivityService;
import com.sales.service.CustomerFinEvaluationService;
import com.sales.service.CustomerLinkmanService;
import com.sales.service.CustomerPotentialProductService;
import com.sales.service.CustomerProjectService;
import com.sales.service.CustomerService;
import com.sales.service.CustomerSyncService;
import com.sales.service.EnumerationService;
import com.sales.service.OrderService;
import com.sales.service.SUserService;
import com.sales.service.SaleService;
import com.sales.common.Constants;
import com.sales.common.NormalFun;
import com.sales.model.K3OrderCustomerStatistics;
import com.sales.model.SCustomerFinEvaluation;
import com.sales.model.SCustomerLevel;
import com.sales.model.SCustomerLinkman;
import com.sales.model.SCustomerOwnerUser;
import com.sales.model.SCustomerPProduct;
import com.sales.model.SCustomerProject;
import com.sales.model.SCustomerUser;
import com.sales.model.SEnumeration; 
import com.sales.model.SSalesActivity;
import com.sales.model.SUser;
import com.sales.model.TCustomer;
import com.sales.model.TOrder;
import com.sales.model.TSaleBill;

/** 
 * @author  
 * @version 创建时间：2013-6-14 下午04:29:25 
 *  
 */
public class CustomerAction extends BaseAction {
	
	private Long pageNumber = 1L; // 页数
	private int pageSize = Page.DEFAULT_PAGE_SIZE;; // 页面大小
	private Page page ;

	private Integer customerId;
	private Integer couId;
	private Integer customerUserId;
	private String customerName;
	private String fname;
	private String fnumber;
	private Integer userId;
	private String userName;
	private CustomerService customerService;
	private List<TCustomer> customerList;
	private CustomerLinkmanService customerLinkmanService;
	private CustomerProjectService customerProjectService;
	private CustomerPotentialProductService potentialProductService;
	private SUserService sUserService;
	private List<SCustomerLinkman> linkmanList;
	private List<SCustomerLinkman> customerLinkmanList;
	private TCustomer tcustomer;
	private List<TCustomer> alarmCustomerList;
	private SCustomerOwnerUser couser;
	private SCustomerUser customerUser;
	private List<SCustomerOwnerUser> couserList;
	private List<SCustomerUser> customerUserList;
	private List<SUser> userList;
	private CustomerFinEvaluationService customerFinEvaluationService;
	private SCustomerFinEvaluation customerFinEvaluation;
	private EnumerationService enumerationService;
	private List<SEnumeration> enumerationList;
	private List<SEnumeration> evaSalemanagerList;
	private List<SEnumeration> evaSalegenList;
	private List<SEnumeration> evaFinmanagerList;
	private List<SEnumeration> evaCreditList;
	private String userPositionOrgId;
	private SaleService saleService;
	private List<TSaleBill> saleBillList;
	private SCustomerLevel customerLevel;
	private ActivityService activityService;
	private OrderService orderService;
	private List<SSalesActivity> activityList;
	private List<TOrder> orderList;
	private String msg;
	private Integer finmanagerId;
	private Integer salegenId;
	private String salegenName;
	private String finmanagerName;
	
	private String linkmanPerfection;		//客户联系人填写完整度
	private List<K3OrderCustomerStatistics> orderStatisticsList;		//客户订单产品统计
	
	public String getSalegenName() {
		return salegenName;
	}
	public void setSalegenName(String salegenName) {
		this.salegenName = salegenName;
	}
	public String getFinmanagerName() {
		return finmanagerName;
	}
	public void setFinmanagerName(String finmanagerName) {
		this.finmanagerName = finmanagerName;
	}
	private List<SEnumeration> oweredStatusList; 		//分配状态列表
	private List<SEnumeration> linkmanWriteStatusList; 		//联系人填写状态列表
	private int oweredStatus = -1;				//客户分配状态
	private int loadK3Data = 0;					//是否从K3加载
	private CustomerSyncService synService;	//k3客户数据同步服务组件
	private List<SCustomerPProduct> pproductList;		//客户潜力产品
	
	/**
	 * @return	k3客户数据同步服务组件
	 */
	public CustomerSyncService getSynService() {
		return synService;
	}
	public Integer getFinmanagerId() {
		return finmanagerId;
	}
	public void setFinmanagerId(Integer finmanagerId) {
		this.finmanagerId = finmanagerId;
	}
	public Integer getSalegenId() {
		return salegenId;
	}
	public void setSalegenId(Integer salegenId) {
		this.salegenId = salegenId;
	}
	/**	k3客户数据同步服务组件
	 * @param service
	 */
	public void setSynService(CustomerSyncService service) {
		this.synService = service;
	}
	public int getLoadK3Data() {
		return loadK3Data;
	}
	public void setLoadK3Data(int loadK3Data) {
		this.loadK3Data = loadK3Data;
	}
	public String getMsg() {
		return msg;
	}
	public List<SEnumeration> getOweredStatusList() {
		return oweredStatusList;
	}
	public void setOweredStatusList(List<SEnumeration> oweredStatusList) {
		this.oweredStatusList = oweredStatusList;
	}
	public List<SEnumeration> getLinkmanWriteStatusList() {
		return linkmanWriteStatusList;
	}
	public void setLinkmanWriteStatusList(
			List<SEnumeration> linkmanWriteStatusList) {
		this.linkmanWriteStatusList = linkmanWriteStatusList;
	}
	public int getOweredStatus() {
		return oweredStatus;
	}
	public void setOweredStatus(int oweredStatus) {
		this.oweredStatus = oweredStatus;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public SaleService getSaleService() {
		return saleService;
	}
	/**
	 * @param potentialProductService the potentialProductService to set
	 */
	public void setPotentialProductService(
			CustomerPotentialProductService potentialProductService) {
		this.potentialProductService = potentialProductService;
	}
	public void setSaleService(SaleService saleService) {
		this.saleService = saleService;
	}
	public List<TOrder> getOrderList() {
		return orderList;
	}
	public void setOrderList(List<TOrder> orderList) {
		this.orderList = orderList;
	}
	public List<TSaleBill> getSaleBillList() {
		return saleBillList;
	}
	public void setSaleBillList(List<TSaleBill> saleBillList) {
		this.saleBillList = saleBillList;
	}
	public String getUserPositionOrgId() {
		return userPositionOrgId;
	}
	public void setUserPositionOrgId(String userPositionOrgId) {
		this.userPositionOrgId = userPositionOrgId;
	}
	public List<SEnumeration> getEvaSalemanagerList() {
		return evaSalemanagerList;
	}
	public void setEvaSalemanagerList(List<SEnumeration> evaSalemanagerList) {
		this.evaSalemanagerList = evaSalemanagerList;
	}
	public List<SEnumeration> getEvaSalegenList() {
		return evaSalegenList;
	}
	public void setEvaSalegenList(List<SEnumeration> evaSalegenList) {
		this.evaSalegenList = evaSalegenList;
	}
	public List<SEnumeration> getEvaFinmanagerList() {
		return evaFinmanagerList;
	}
	public void setEvaFinmanagerList(List<SEnumeration> evaFinmanagerList) {
		this.evaFinmanagerList = evaFinmanagerList;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getFnumber() {
		return fnumber;
	}
	public void setFnumber(String fnumber) {
		this.fnumber = fnumber;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Integer getCouId() {
		return couId;
	}
	public void setCouId(Integer couId) {
		this.couId = couId;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
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
	public Integer getCustomerUserId() {
		return customerUserId;
	}
	public void setCustomerUserId(Integer customerUserId) {
		this.customerUserId = customerUserId;
	}
	
	public ActivityService getActivityService() {
		return activityService;
	}
	public void setActivityService(ActivityService activityService) {
		this.activityService = activityService;
	}
	public OrderService getOrderService() {
		return orderService;
	}
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	public List<SSalesActivity> getActivityList() {
		return activityList;
	}
	public void setActivityList(List<SSalesActivity> activityList) {
		this.activityList = activityList;
	}
	public SCustomerLevel getCustomerLevel() {
		return customerLevel;
	}
	public void setCustomerLevel(SCustomerLevel customerLevel) {
		this.customerLevel = customerLevel;
	}
	public EnumerationService getEnumerationService() {
		return enumerationService;
	}
	public void setEnumerationService(EnumerationService enumerationService) {
		this.enumerationService = enumerationService;
	}
	
	
	/**
	 * @return	客户等级
	 */
	public List<SEnumeration> getEnumerationList() {
		return enumerationList;
	}
	public void setEnumerationList(List<SEnumeration> enumerationList) {
		this.enumerationList = enumerationList;
	}
	
	/**
	 * @return	财务评估等级
	 */
	public SCustomerFinEvaluation getCustomerFinEvaluation() {
		return customerFinEvaluation;
	}
	public void setCustomerFinEvaluation(
			SCustomerFinEvaluation customerFinEvaluation) {
		this.customerFinEvaluation = customerFinEvaluation;
	}
	public CustomerFinEvaluationService getCustomerFinEvaluationService() {
		return customerFinEvaluationService;
	}
	public void setCustomerFinEvaluationService(
			CustomerFinEvaluationService customerFinEvaluationService) {
		this.customerFinEvaluationService = customerFinEvaluationService;
	}
	/**
	 * @return the evaCreditList
	 */
	public List<SEnumeration> getEvaCreditList() {
		return evaCreditList;
	}
	/**
	 * @param evaCreditList the evaCreditList to set
	 */
	public void setEvaCreditList(List<SEnumeration> evaCreditList) {
		this.evaCreditList = evaCreditList;
	}
	public List<SUser> getUserList() {
		return userList;
	}
	public void setUserList(List<SUser> userList) {
		this.userList = userList;
	}
	public List<SCustomerOwnerUser> getCouserList() {
		return couserList;
	}
	public void setCouserList(List<SCustomerOwnerUser> couserList) {
		this.couserList = couserList;
	}
	public List<SCustomerUser> getCustomerUserList() {
		return customerUserList;
	}
	public void setCustomerUserList(List<SCustomerUser> customerUserList) {
		this.customerUserList = customerUserList;
	}
	public SCustomerOwnerUser getCouser() {
		return couser;
	}
	public void setCouser(SCustomerOwnerUser couser) {
		this.couser = couser;
	}
	public SCustomerUser getCustomerUser() {
		return customerUser;
	}
	public void setCustomerUser(SCustomerUser customerUser) {
		this.customerUser = customerUser;
	}
	public List<SCustomerOwnerUser> getCouList() {
		return couList;
	}
	public void setCouList(List<SCustomerOwnerUser> couList) {
		this.couList = couList;
	}
	private List<SCustomerOwnerUser> couList;
	public List<TCustomer> getAlarmCustomerList() {
		return alarmCustomerList;
	}
	public void setAlarmCustomerList(List<TCustomer> alarmCustomerList) {
		this.alarmCustomerList = alarmCustomerList;
	}
	public List<SCustomerLinkman> getCustomerLinkmanList() {
		return customerLinkmanList;
	}
	public void setCustomerLinkmanList(List<SCustomerLinkman> customerLinkmanList) {
		this.customerLinkmanList = customerLinkmanList;
	}
	/**
	 * @return the orderStatisticsList
	 */
	public List<K3OrderCustomerStatistics> getOrderStatisticsList() {
		return orderStatisticsList;
	}
	/**
	 * @param orderStatisticsList the orderStatisticsList to set
	 */
	public void setOrderStatisticsList(
			List<K3OrderCustomerStatistics> orderStatisticsList) {
		this.orderStatisticsList = orderStatisticsList;
	}
	private List<SCustomerProject> customerProjectList;
	
	public CustomerService getCustomerService() {
		return customerService;
	}
	public CustomerLinkmanService getCustomerLinkmanService() {
		return customerLinkmanService;
	}
	public void setCustomerLinkmanService(
			CustomerLinkmanService customerLinkmanService) {
		this.customerLinkmanService = customerLinkmanService;
	}
	public CustomerProjectService getCustomerProjectService() {
		return customerProjectService;
	}
	public void setCustomerProjectService(
			CustomerProjectService customerProjectService) {
		this.customerProjectService = customerProjectService;
	}
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	public List<TCustomer> getCustomerList() {
		return customerList;
	}
	public void setCustomerList(List<TCustomer> customerList) {
		this.customerList = customerList;
	}
	public TCustomer getTcustomer() {
		return tcustomer;
	}
	public void setTcustomer(TCustomer tcustomer) {
		this.tcustomer = tcustomer;
	}
	
	public String selectCustomerList(){
		
		if (this.pageNumber == null || this.pageNumber == 0L) {
			this.pageNumber = 1L;
		}
		Integer evaFinId = null;
		Integer cLevelId = null;
		Integer evaFinSalemagagerId = null;
		Integer evaFinManagerId = null;
		if(tcustomer!=null && tcustomer.getEvaFinGenId()!=null){
			evaFinId = tcustomer.getEvaFinGenId();
		}
		if(tcustomer!=null && tcustomer.getEvaFinManagerId()!=null){
			evaFinManagerId = tcustomer.getEvaFinManagerId();
		}
		if(tcustomer!=null && tcustomer.getEvaFinSalemagagerId()!=null){
			evaFinSalemagagerId = tcustomer.getEvaFinSalemagagerId();
		}
		if(tcustomer!=null && tcustomer.getCustomerLevel()!=null){
			cLevelId = tcustomer.getCustomerLevel();
		}
		
		Integer totalCount = new Integer(0);
		//this.customerList = this.customerService.getCustomerList(pageNumber, pageSize);
		if(fname!=null && !"".equals(fname)){
			tcustomer.setFname(fname);
		}
		if(fnumber!=null && !"".equals(fnumber)){
			tcustomer.setFnumber(fnumber);
		}
		if(this.tcustomer == null){
			this.tcustomer = new TCustomer();
		}
		
		// 提交强制加载K3客户数据
		if(loadK3Data == 1)
			synService.syncK3Customers();
		
		this.enumerationList = this.enumerationService.getEnumerationByType(
				Constants.ENUMERATION_CUSTOMER_LEVEL_TYPE);
		this.evaFinmanagerList = this.enumerationService.getEnumerationByType(
				Constants.ENUMERATION_CUSTOMER_FIN_EVALUATION_TYPE);
		this.evaSalegenList = this.enumerationService.getEnumerationByType(
				Constants.ENUMERATION_CUSTOMER_FIN_EVALUATION_TYPE);
		this.evaCreditList = this.enumerationService.getEnumerationByType(
				Constants.ENUMERATION_CUSTOMER_CDT_EVALUATION_TYPE);
		this.evaSalemanagerList = this.enumerationService.getEnumerationByType(
				Constants.ENUMERATION_CUSTOMER_FIN_EVALUATION_TYPE);
		linkmanWriteStatusList = new ArrayList<SEnumeration>();
		linkmanWriteStatusList.add(new SEnumeration(1,"已填写"));
		linkmanWriteStatusList.add(new SEnumeration(0,"未填写"));
		
		SUser user = (SUser) this.session.get(Constants.USER_INFO);
		Map permissionMap = (Map) this.session.get(Constants.USER_PERMISSION_MAP);
		String customerIds = (String) permissionMap.get("customerOwnerFilter");
		if(Constants.USER_FILTER_MARK.equals(customerIds)){
		//if(user.getRoleId()==1){
			this.customerList = this.customerService.selectCustomerList(pageNumber, pageSize, tcustomer);
			totalCount = this.customerService.getCustomerCountByObj(tcustomer);
		}else{
			if(customerIds==null){
				this.customerList = null;
			}else{
				
				this.customerList = this.customerService.getCustomerListByFilter(tcustomer, customerIds, pageNumber, pageSize);
				totalCount = this.customerService.getCustomerCountByFilter(tcustomer, customerIds);
			}
		}
		if(evaFinId!=null){
			tcustomer.setEvaFinGenId(evaFinId);
		}
		if(cLevelId!=null){
			tcustomer.setCustomerLevel(cLevelId);
		}
		if(evaFinSalemagagerId!=null){
			tcustomer.setEvaFinSalemagagerId(evaFinSalemagagerId);
		}
		if(evaFinManagerId!=null){
			tcustomer.setEvaFinManagerId(evaFinManagerId);
		}
		
		this.page = new Page();
		this.page.setData(customerList);
		this.page.setPageSize(this.pageSize);
		this.page.setTotalCount(Long.valueOf(totalCount));
		this.page.setStart(this.pageNumber.intValue());
		
		return "selectCustomerList";
	}
	
	public List<SCustomerLinkman> getLinkmanList() {
		return linkmanList;
	}
	public void setLinkmanList(List<SCustomerLinkman> linkmanList) {
		this.linkmanList = linkmanList;
	}
	public List<SCustomerProject> getCustomerProjectList() {
		return customerProjectList;
	}
	public void setCustomerProjectList(List<SCustomerProject> customerProjectList) {
		this.customerProjectList = customerProjectList;
	}
	/**
	 * @return the pproductList
	 */
	public List<SCustomerPProduct> getPproductList() {
		return pproductList;
	}
	/**
	 * @param pproductList the pproductList to set
	 */
	public void setPproductList(List<SCustomerPProduct> pproductList) {
		this.pproductList = pproductList;
	}
	/**
	 * @return the linkmanPerfection
	 */
	public String getLinkmanPerfection() {
		return linkmanPerfection;
	}
	/**
	 * @param linkmanPerfection the linkmanPerfection to set
	 */
	public void setLinkmanPerfection(double linkmanPerfection) {	
		this.linkmanPerfection = NormalFun.formatPercent(linkmanPerfection);
	}
	
	/**
	 * 根据客户id查询客户档案信息
	 * @return
	 */
	public String getCustomerInfoById(){
		this.tcustomer = this.customerService.getCustomerInfoById(customerId);
		this.linkmanList = this.customerLinkmanService.getLinkmanListByCustomerId(customerId);
		this.customerProjectList = this.customerProjectService.getCPListByCustomerId(customerId);
		this.customerFinEvaluation = this.customerFinEvaluationService.getCustomerFinEvaluationByCustomerId(customerId);
		this.activityList = this.activityService.getActivityByCustomerId(customerId);
		this.orderList = this.orderService.getOrderByCustomerId(customerId);
		this.saleBillList = this.saleService.getSaleBillList(customerId);
		this.customerLevel = this.customerService.selectCustomerLevelByCustomerId(customerId);
		
		//获取客户关注人及分配人
		this.couserList = this.customerService.getCOUserListByCustomerId(customerId);
		this.customerUserList = this.customerService.getCustomerUserListByCustomerId(customerId);
		
		this.pproductList = potentialProductService.getPProductHistory(customerId);
//		this.linkmanPerfection = NormalFun.formatPercent(
//				customerLinkmanService.getLinkmanPerfection(customerId));
		this.orderStatisticsList = orderService.getOrderProductStatisticsList(customerId);
		
		computePropertiesCompletely();
		
		
		return "getCustomerInfoById";
	}
	
	/**
	 * 计算客户资料完整度
	 */
	private void computePropertiesCompletely() {
		//计算客户资料完整度-客户等级评估
		if(customerLevel == null || customerLevel.getLevelId() == null)
			tcustomer.addUnCompletelyField(1);
		else
			tcustomer.addCompletelyField();
		
		//计算客户资料完整度-客户分配状态
		if(couserList == null || couserList.size() == 0)
			tcustomer.addUnCompletelyField(1);
		else
			tcustomer.addCompletelyField();
		
		//计算客户资料完整度-客户财务评价
		if (customerFinEvaluation != null) {
			if(customerFinEvaluation.getEvaSalegen() == null)
				tcustomer.addUnCompletelyField(1);
			else
				tcustomer.addCompletelyField();
			
			if(customerFinEvaluation.getEvaSalemanager() == null)
				tcustomer.addUnCompletelyField(1);
			else
				tcustomer.addCompletelyField();
			
			if(customerFinEvaluation.getEvaFinmanager() == null)
				tcustomer.addUnCompletelyField(1);
			else
				tcustomer.addCompletelyField();
			
		} else {
			tcustomer.addUnCompletelyField(3);
		}
	}
	
	
	public String selectCOUList(){
		if (this.pageNumber == null || this.pageNumber == 0L) {
			this.pageNumber = 1L;
		}
		Integer totalCount = new Integer(0);
		if(couser == null) {
			couser = new SCustomerOwnerUser();
		}
		this.couser.setUserId(getLoginUser().getUserId());
		
		Map permissionMap = (Map) this.session.get(Constants.USER_PERMISSION_MAP);
		String customerIds = (String) permissionMap.get("customerOwnerFilter");
		this.couList = this.customerService.getCOUList(pageNumber, pageSize, couser, customerIds, oweredStatus);
		totalCount = this.customerService.getCOUCount(couser, customerIds, oweredStatus);
		oweredStatusList = new ArrayList<SEnumeration>();
		oweredStatusList.add(new SEnumeration(-1,"全部"));
		oweredStatusList.add(new SEnumeration(1,"已分配"));
		oweredStatusList.add(new SEnumeration(0,"未分配"));
		
		linkmanWriteStatusList = new ArrayList<SEnumeration>();
		linkmanWriteStatusList.add(new SEnumeration(1,"已填写"));
		linkmanWriteStatusList.add(new SEnumeration(0,"未填写"));
		
		this.page = new Page();
		this.page.setData(couList);
		this.page.setPageSize(this.pageSize);
		this.page.setTotalCount(Long.valueOf(totalCount));
		this.page.setStart(this.pageNumber.intValue());
		
		return "selectCOUList";
	}
	
	public String selectHistoryCOUList(){
		if (this.pageNumber == null || this.pageNumber == 0L) {
			this.pageNumber = 1L;
		}
		Integer totalCount = new Integer(0);
		if(couser == null) {
			couser = new SCustomerOwnerUser();
		}
		this.couser.setUserId(getLoginUser().getUserId());
		if(this.customerId!=null){
			couser.setCustomerId(this.customerId);
		}
		
		Map permissionMap = (Map) this.session.get(Constants.USER_PERMISSION_MAP);
		String customerIds = (String) permissionMap.get("customerOwnerFilter");
		this.couList = this.customerService.getHistoryCOUList(pageNumber, pageSize, couser, customerIds, oweredStatus);
		totalCount = this.customerService.getHistoryCOUCount(couser, customerIds, oweredStatus);
		oweredStatusList = new ArrayList<SEnumeration>();
		oweredStatusList.add(new SEnumeration(-1,"全部"));
		oweredStatusList.add(new SEnumeration(1,"已分配"));
		oweredStatusList.add(new SEnumeration(0,"未分配"));
		
		linkmanWriteStatusList = new ArrayList<SEnumeration>();
		linkmanWriteStatusList.add(new SEnumeration(1,"已填写"));
		linkmanWriteStatusList.add(new SEnumeration(0,"未填写"));
		
		this.page = new Page();
		this.page.setData(couList);
		this.page.setPageSize(this.pageSize);
		this.page.setTotalCount(Long.valueOf(totalCount));
		this.page.setStart(this.pageNumber.intValue());
		
		return "selectHistoryCOUList";
	}
	
	public String selectSalerList(){
		if (this.pageNumber == null || this.pageNumber == 0L) {
			this.pageNumber = 1L;
		}
		Integer totalCount = new Integer(0);
		this.userList = this.sUserService.getUserList(pageNumber, pageSize);
		totalCount = this.sUserService.getUserCount();
		this.page = new Page();
		this.page.setData(userList);
		this.page.setPageSize(this.pageSize);
		this.page.setTotalCount(Long.valueOf(totalCount));
		this.page.setStart(this.pageNumber.intValue());
		
		return "selectSalerList";
	}
	
	public String selectCustomerUserList(){
		if (this.pageNumber == null || this.pageNumber == 0L) {
			this.pageNumber = 1L;
		}
		Integer totalCount = new Integer(0);
		customerUser = new SCustomerUser();
		if(customerId!=null){
			customerUser.setCustomerId(totalCount);
		}
		this.customerUserList = this.customerService.selectCustomerUserList(pageNumber, pageSize, customerUser);
		totalCount = this.customerService.getCustomerUserCount(customerUser);
		this.page = new Page();
		this.page.setData(customerUserList);
		this.page.setPageSize(this.pageSize);
		this.page.setTotalCount(Long.valueOf(totalCount));
		this.page.setStart(this.pageNumber.intValue());
		
		return "selectCustomerUserList";
	}
	
	public String selectAlarmCustomerList(){
		this.alarmCustomerList = this.customerService.getCustomerList(1L, 10);
		return "selectAlarmCustomerList";
	}
	
	public String deleteCOUser(){
		this.customerService.deleteCOUser(this.couId,this.userId);
		
		return selectCOUList();
	}
	
	public String deleteCustomerUser(){
		this.customerService.deleteCustomerUser(this.customerUserId);
		this.msg = "deletesuccess";
		return getCustomerInfoById();
	}
	
	public String customerOpenWindow(){
		Map permissionMap = (Map) this.session.get(Constants.USER_PERMISSION_MAP);
		String customerIds = (String) permissionMap.get("customerOwnerFilter");
		if (this.pageNumber == null || this.pageNumber == 0L) {
			this.pageNumber = 1L;
		}
		Integer totalCount = new Integer(0);
		if(this.tcustomer == null){
			this.tcustomer = new TCustomer();
		}
		if(this.tcustomer.getFnumber()==null || this.tcustomer.getFnumber().trim().equals("")){
			this.tcustomer.setFnumber(null);
		}
		if(this.tcustomer.getFname()==null || this.tcustomer.getFname().trim().equals("")){
			this.tcustomer.setFname(null);
		}
		else{
			this.tcustomer.setFname(this.tcustomer.getFname());
		} 
		
		SUser user = (SUser) this.session.get(Constants.USER_INFO);
		customerIds = (String) permissionMap.get("customerOwnerFilter");
		totalCount = this.customerService.getOpenCustomerCount(this.tcustomer.getFname(), this.tcustomer.getFnumber(), customerIds);
		if(totalCount!=null && totalCount.intValue()>0){
			this.customerList = this.customerService.getOpenCustomerList(this.tcustomer.getFname(), this.tcustomer.getFnumber(), customerIds, pageNumber, pageSize);
		}		
		this.page = new Page();
		this.page.setData(customerList);
		this.page.setPageSize(this.pageSize);
		this.page.setTotalCount(Long.valueOf(totalCount));
		this.page.setStart(this.pageNumber.intValue());
		return "customerOpenWindow";
	}
	
	public String customerDistributionOpenWindow(){
		Map permissionMap = (Map) this.session.get(Constants.USER_PERMISSION_MAP);
		String customerIds = (String) permissionMap.get("customerOwnerFilter");
		if (this.pageNumber == null || this.pageNumber == 0L) {
			this.pageNumber = 1L;
		}
		Integer totalCount = new Integer(0);
		if(this.tcustomer == null){
			this.tcustomer = new TCustomer();
		}
		if(this.tcustomer.getFnumber()==null || this.tcustomer.getFnumber().trim().equals("")){
			this.tcustomer.setFnumber(null);
		}
		if(this.tcustomer.getFname()==null || this.tcustomer.getFname().trim().equals("")){
			this.tcustomer.setFname(null);
		}
		else{
			this.tcustomer.setFname(NormalFun.getUTF8(this.tcustomer.getFname()));
		} 
		SUser user = (SUser) this.session.get(Constants.USER_INFO);
		customerIds = (String) permissionMap.get("customerOwnerFilter");
		totalCount = this.customerService.getOpenDistributionCustomerCount(this.tcustomer.getFname(), this.tcustomer.getFnumber(), customerIds);
		if(totalCount!=null && totalCount.intValue()>0){
			this.customerList = this.customerService.getOpenDistributionCustomerList(this.tcustomer.getFname(), this.tcustomer.getFnumber(), customerIds, pageNumber, pageSize);
		}		
		this.page = new Page();
		this.page.setData(customerList);
		this.page.setPageSize(this.pageSize);
		this.page.setTotalCount(Long.valueOf(totalCount));
		this.page.setStart(this.pageNumber.intValue());
		return "customerDistributionOpenWindow";
	}
	
	public String addCOUserInit(){
		return "addCOUserInit";
	}
	
	public String addCOUserIndex() throws UnsupportedEncodingException{
		return "addCOUserIndex";
	}
	
	public String addCOUser(){
		SUser user = (SUser) this.session.get(Constants.USER_INFO);
		this.couser.setCreateUserId(user.getUserId());
		this.couser.setCreateUserName(user.getUserName());
		//this.customerService.deleteCOUserByCustomerId(this.couser.getCustomerId());
		this.customerService.addCOUser(this.couser);
		this.userId = couser.getUserId();
		
		this.couser = null;			//清除条件
		return selectCOUList();
	}
	//修改客户分配
	public String updateCOUserIndex() throws UnsupportedEncodingException{
		this.couser = new SCustomerOwnerUser();
		if(request.getParameter("customerId") != null) {
			couser.setCustomerId(Integer.parseInt(request.getParameter("customerId")));
			couser.setCustomerOwnerUserId(Integer.parseInt(request.getParameter("couId")));
			if(this.customerName != null){
				String strPtname = request.getParameter("customerName");
				this.customerName = new String(strPtname.getBytes("ISO-8859-1"), "UTF-8"); 
				couser.setCustomerName(this.customerName);
			}
		}
		if(request.getParameter("finmanagerId") != null) {
			couser.setFinmanagerId(finmanagerId);
			if(this.finmanagerName != null){
				String strPtname = request.getParameter("finmanagerName");
				this.finmanagerName = new String(strPtname.getBytes("ISO-8859-1"), "UTF-8"); 
				couser.setFinmanagerName(this.finmanagerName);
			}
		}
		if(request.getParameter("salegenId") != null) {
			couser.setSalegenId(salegenId);
			if(this.salegenName != null){
				String strPtname = request.getParameter("salegenName");
				this.salegenName = new String(strPtname.getBytes("ISO-8859-1"), "UTF-8"); 
				couser.setSalegenName(this.salegenName);
			}
		}
		if(request.getParameter("userId") != null) {
			couser.setUserId(userId);
			if(this.userName != null){
				String strPtname = request.getParameter("userName");
				this.userName = new String(strPtname.getBytes("ISO-8859-1"), "UTF-8"); 
				couser.setUserName(this.userName);
			}
		}
		return "updateCOUserIndex";
	}
	
	public String updateCOUser(){
		SUser user = (SUser) this.session.get(Constants.USER_INFO);
		this.couser.setCreateUserId(user.getUserId());
		this.couser.setCreateUserName(user.getUserName());
		//this.customerService.deleteCOUserByCustomerId(this.couser.getCustomerId());
		this.customerService.addCOUser(this.couser);
		this.userId = couser.getUserId();
		
		this.couser = null;			//清除条件
		return selectCOUList();
	}
	
	public SUserService getsUserService() {
		return sUserService;
	}
	public void setsUserService(SUserService sUserService) {
		this.sUserService = sUserService;
	}
	public String addCustomerUserInit() throws UnsupportedEncodingException{
		
		this.customerUserList = this.customerService.getCustomerUserListByCustomerId(customerId);
		if(this.customerName != null){
			
			String strPtname = request.getParameter("customerName");
			this.customerName = new String(strPtname.getBytes("ISO-8859-1"), "UTF-8"); 
		}
		return "addCustomerUserInit";
	}
	
	public String addCustomerUser(){
		SUser user = (SUser) this.session.get(Constants.USER_INFO);
		this.customerUser.setCreateUserId(user.getUserId());
		this.customerUser.setCreateUserName(user.getUserName());
		this.customerId = this.customerUser.getCustomerId();
		Integer cuserId = this.customerUser.getUserId();
		this.customerService.deleteCustomerUserByCustomerId(customerId, cuserId);
		this.customerService.addCustomerUser(this.customerUser);
		this.msg = "addsuccuss";
		return getCustomerInfoById();
	}
	
	public String updateCustomerFinEvaluationInit() throws UnsupportedEncodingException{
		
		if(this.customerName != null){
			String strPtname = request.getParameter("customerName");
			this.customerName = new String(strPtname.getBytes("ISO-8859-1"), "UTF-8"); 
		}
		this.customerFinEvaluation = this.customerFinEvaluationService.getCustomerFinEvaluationByCustomerId(customerId);
//		this.enumerationList = this.enumerationService.getEnumerationByType(3);
		SUser user = (SUser) this.session.get(Constants.USER_INFO);
		if(user.getRoleId()!=null)
			this.userPositionOrgId = user.getRoleId().toString();
		SEnumeration enumeration = new SEnumeration();
		this.evaSalemanagerList = this.enumerationService.getEnumerationByType(
				Constants.ENUMERATION_CUSTOMER_FIN_EVALUATION_TYPE);
		if(customerFinEvaluation!=null && customerFinEvaluation.getEvaSalemanager() != null){
		for (int i = 0; i < evaSalemanagerList.size(); i++) {
			enumeration = evaSalemanagerList.get(i);
			enumeration.setIsSelect("noselected");
			int evaSalemanager = customerFinEvaluation.getEvaSalemanager();
			int enumerationId = enumeration.getEnumerationId();
			if(evaSalemanager == enumerationId){
				enumeration.setIsSelect("selected");
				this.customerFinEvaluation.setEvaSalemanagerName(enumeration.getEnumerationName());
				break;
			}
		}
		}
		this.evaSalegenList = this.enumerationService.getEnumerationByType(
				Constants.ENUMERATION_CUSTOMER_FIN_EVALUATION_TYPE);
		if(customerFinEvaluation!= null && customerFinEvaluation.getEvaSalegen() != null){
		for (int i = 0; i < evaSalegenList.size(); i++) {
			enumeration = evaSalegenList.get(i);
			enumeration.setIsSelect("noselected");
			int evaSalegen = customerFinEvaluation.getEvaSalegen();
			int enumerationId = enumeration.getEnumerationId();
			if(evaSalegen == enumerationId){
				enumeration.setIsSelect("selected");
				this.customerFinEvaluation.setEvaSalegenName(enumeration.getEnumerationName());
				break;
			}
		}
		}
//		this.evaFinmanagerList = this.enumerationService.getEnumerationByType(
//				Constants.ENUMERATION_CUSTOMER_FIN_EVALUATION_TYPE);
//		if(customerFinEvaluation!= null && customerFinEvaluation.getEvaFinmanager() != null){
//		for (int i = 0; i < evaFinmanagerList.size(); i++) {
//			enumeration = evaFinmanagerList.get(i);
//			enumeration.setIsSelect("noselected");
//			int evaFinmanager = customerFinEvaluation.getEvaFinmanager();
//			int enumerationId = enumeration.getEnumerationId();
//			if(evaFinmanager == enumerationId){
//				enumeration.setIsSelect("selected");
//				this.customerFinEvaluation.setEvaFinmanagerName(enumeration.getEnumerationName());
//				break;
//			}
//		}
//		}
		
		this.evaCreditList = this.enumerationService.getEnumerationByType(
				Constants.ENUMERATION_CUSTOMER_CDT_EVALUATION_TYPE);
		if(customerFinEvaluation!= null && customerFinEvaluation.getEvaFinmanager() != null){
			for (int i = 0; i < evaCreditList.size(); i++) {
				enumeration = evaCreditList.get(i);
				enumeration.setIsSelect("noselected");
				int evaFinmanager = customerFinEvaluation.getEvaFinmanager();
				int enumerationId = enumeration.getEnumerationId();
				if(evaFinmanager == enumerationId){
					enumeration.setIsSelect("selected");
					this.customerFinEvaluation.setEvaFinmanagerName(enumeration.getEnumerationName());
					break;
				}
			}
		}
		return "updateCustomerFinEvaluationInit";
	}
	
	public String updateCustomerFinEvaluation(){
		SUser user = (SUser) this.session.get(Constants.USER_INFO);
		this.customerFinEvaluation.setCreateUserId(user.getUserId());
		this.customerFinEvaluation.setCreateUserName(user.getUserName());
		this.customerFinEvaluationService.updateCustomerFinEvaluation(customerFinEvaluation);
		
		this.customerId = customerFinEvaluation.getCustomerId();
		return getCustomerInfoById();
	}
	
	public String updateCustomerLevelInit() throws UnsupportedEncodingException{
		this.customerLevel = this.customerService.selectCustomerLevelByCustomerId(customerId);
		if(this.customerName != null){
			String strPtname = request.getParameter("customerName");
			this.customerName = new String(strPtname.getBytes("ISO-8859-1"), "UTF-8"); 
		}
		this.enumerationList = this.enumerationService.getEnumerationByType(
				Constants.ENUMERATION_CUSTOMER_LEVEL_TYPE);
		if(customerLevel!=null){
			int levelId = customerLevel.getLevelId();
			for (int i = 0; i < enumerationList.size(); i++) {
				SEnumeration enumeration = enumerationList.get(i);
				enumeration.setIsSelect("noselected");
				
				int enumerationId = enumeration.getEnumerationId();
				if(levelId == enumerationId){
					enumeration.setIsSelect("selected");
					break;
				}
			}
		}
		return "updateCustomerLevelInit";
	}
	
	public String updateCustomerLevel(){
		SUser user = (SUser) this.session.get(Constants.USER_INFO);
		this.customerLevel.setCreateUserId(user.getUserId());
		this.customerLevel.setCreateUserName(user.getUserName());
		this.customerService.updateCustomerLevel(this.customerLevel);
		this.customerId = customerLevel.getCustomerId();
		return getCustomerInfoById();
	}

}
