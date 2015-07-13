package com.sales.action;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import org.dom4j.io.XMLWriter;

import my.com.ibatis.core.dao.support.Page;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.ibatis.common.util.PaginatedArrayList;
import com.ibatis.common.util.PaginatedList;
import com.ibatis.sqlmap.engine.mapping.statement.PaginatedDataList;
import com.sales.common.Constants;
import com.sales.common.MD5;
import com.sales.model.PaginationData;
import com.sales.model.SDepartment;
import com.sales.model.SPosition;
import com.sales.model.SRole;
import com.sales.model.SUser;
import com.sales.service.DepartmentService;
import com.sales.service.PositionService;
import com.sales.service.RoleService;
import com.sales.service.SUserService;
import com.sales.service.impl.K3EntryServiceImpl;

/** 
 * @author  
 * @version 创建时间：2013-5-16 下午03:17:08 
 *  
 */
public class SUserAction extends BaseAction {
	
	private Long pageNumber = 1L; // 页数
	private int pageSize = Page.DEFAULT_PAGE_SIZE;; // 页面大小
	private Page page ;
	private List<SUser> userList;
	private RoleService roleService;
	private PositionService positionService;
	private List<SPosition> positionOrgList;
	private List<SPosition> positionBsList;
	private List<SRole> roleList;
	private String userName;
	private String userPwd;
	private String mobile;
	private Integer userId;
	private SUser userInfo;
	private List<SDepartment> departList;
	private DepartmentService departmentService;
	private K3EntryServiceImpl k3Service;
	private List k3UserList;
	private Integer userPositionBsId;
	private String parentUserName;
	private String msg;
	private int isNotControl;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getParentUserName() {
		return parentUserName;
	}

	public void setParentUserName(String parentUserName) {
		this.parentUserName = parentUserName;
	}

	public Integer getUserPositionBsId() {
		return userPositionBsId;
	}

	public void setUserPositionBsId(Integer userPositionBsId) {
		this.userPositionBsId = userPositionBsId;
	}

	public K3EntryServiceImpl getK3Service() {
		return k3Service;
	}

	public void setK3Service(K3EntryServiceImpl k3Service) {
		this.k3Service = k3Service;
	}

	public List<SDepartment> getDepartList() {
		return departList;
	}

	public void setDepartList(List<SDepartment> departList) {
		this.departList = departList;
	}

	public DepartmentService getDepartmentService() {
		return departmentService;
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	
	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public PositionService getPositionService() {
		return positionService;
	}

	public void setPositionService(PositionService positionService) {
		this.positionService = positionService;
	}

	public List<SPosition> getPositionOrgList() {
		return positionOrgList;
	}

	public void setPositionOrgList(List<SPosition> positionOrgList) {
		this.positionOrgList = positionOrgList;
	}

	public List<SPosition> getPositionBsList() {
		return positionBsList;
	}

	public void setPositionBsList(List<SPosition> positionBsList) {
		this.positionBsList = positionBsList;
	}

	public List<SRole> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<SRole> roleList) {
		this.roleList = roleList;
	}
	
	public SUser getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(SUser userInfo) {
		this.userInfo = userInfo;
	}
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
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

	private SUserService sUserService;
	
	public String userList(){
		Map permissionMap = (Map) this.session.get(Constants.USER_PERMISSION_MAP);
		String childUserIds = (String) permissionMap.get("childUserFilter");
		if (this.pageNumber == null || this.pageNumber == 0L) {
			this.pageNumber = 1L;
		}
		Integer totalCount = new Integer(0);
		SUser sUser = new SUser();
		if(userName!=null){
			sUser.setUserName(userName);
		}
		SUser sUserInfo = (SUser) this.session.get(Constants.USER_INFO);
		if(sUserInfo.getRoleId()==1){
			//this.userList = this.sUserService.getUserList(pageNumber, pageSize);
			//totalCount = this.sUserService.getUserCount();
			this.userList = this.sUserService.getUserPageList(pageNumber, pageSize, sUser);
			totalCount = this.sUserService.getUserPageCount(sUser);
		}else{
			totalCount = this.sUserService.getOpenUserCount(sUser.getDepartmentId(), userName, childUserIds); 
			if(totalCount!=null && totalCount.intValue()>0){
				this.userList = this.sUserService.getOpenUserList(sUser.getDepartmentId(), userName, childUserIds, pageNumber, pageSize); 
			}
		}
		//this.userList = this.sUserService.getUserPageList(pageNumber, pageSize, sUser);		
		this.page = new Page();
		this.page.setData(userList);
		this.page.setPageSize(this.pageSize);
		this.page.setTotalCount(Long.valueOf(totalCount));
		this.page.setStart(this.pageNumber.intValue());
		
		return "userList";
	}
	
	public String addUserInit(){
		this.roleList = this.roleService.getRoleList();
		SUser sUser = (SUser) this.session.get(Constants.USER_INFO);
		this.positionOrgList = this.positionService.positionListByType(1);
		this.positionBsList = this.positionService.positionListByType(2);
		//this.departList = this.departmentService.getUnderDepartmentListById(sUser.getDepartmentId());
		this.departList = this.departmentService.getAllDepartmentListById(sUser.getDepartmentId());
		return "addUserInit";
	}
	
	public String addUser(){
		if(userPositionBsId!=null && !("").equals(userPositionBsId)){
			this.userInfo.setPositionBsId(userPositionBsId);
		}
		MD5 md5 = new MD5();
		if(userInfo.getLoginPasswor()!=null){
			String md5PW = md5.getMD5ofStr(this.userInfo.getLoginPasswor());
			userInfo.setLoginPasswor(md5PW);
		}
		this.sUserService.insertUser(this.userInfo);
		
		
		return "addUser";
	}
	
	public String updateUserInit(){
		this.userInfo = this.sUserService.getUserById(userId);
		if(this.userInfo.getParentUserId()!=null){
			if(userInfo.getParentUserId() == 0){
				userInfo.setParentUserId(1);
			}
			SUser parentUser = this.sUserService.getUserById(userInfo.getParentUserId());
			this.parentUserName = parentUser.getUserName();
		}
		this.roleList = this.roleService.getRoleList();
		if(userInfo.getRoleId()!=null){
			for (int i = 0; i < roleList.size(); i++) {
				SRole role = roleList.get(i);
				role.setIsSelect("noselected");
				int uRoleId = userInfo.getRoleId();
				int roleId = role.getRoleId();
				if(uRoleId == roleId){
					role.setIsSelect("selected");
					break;
				}
			}
		}
		this.positionOrgList = this.positionService.positionListByType(1);
		if(userInfo.getPositionOrgId()!=null){
			for (int i = 0; i < positionOrgList.size(); i++) {
				SPosition sporg = positionOrgList.get(i);
				sporg.setIsSelect("noselected");
				int upogId = userInfo.getPositionOrgId();
				int spogrId = sporg.getPositionId();
				if(upogId == spogrId){
					sporg.setIsSelect("selected");
					break;
				}
			}
		}
		this.positionBsList = this.positionService.positionListByType(2);
		if(userInfo.getPositionBsId()!=null){
			for (int i = 0; i < positionBsList.size(); i++) {
				SPosition spbs = positionBsList.get(i);
				spbs.setIsSelect("noselected");
				int uspbsId = userInfo.getPositionBsId();
				int spbsId = spbs.getPositionId();
				if(uspbsId == spbsId){
					spbs.setIsSelect("selected");
					break;
				}
			}
		}
		//this.departList = this.departmentService.getUnderDepartmentListById(userInfo.getDepartmentId());
		SUser sUser = (SUser) this.session.get(Constants.USER_INFO);
		this.departList = this.departmentService.getAllDepartmentListById(sUser.getDepartmentId());
		if(userInfo.getDepartmentId()!=null){
			for (int i = 0; i < departList.size(); i++) {
				SDepartment depar = departList.get(i);
				depar.setIsSelect("noselected");
				int udeparId = userInfo.getDepartmentId();
				int deparId = depar.getDepartmentId();
				if(udeparId == deparId){
					depar.setIsSelect("selected");
					break;
				}
			}
		}
		return "updateUserInit";
	}
	
	public String userSetInit(){
		SUser sUser = (SUser) this.session.get(Constants.USER_INFO);
		this.userInfo = this.sUserService.getUserById(sUser.getUserId());
	    return "userSetInit";
	}
	public String userSet(){
		MD5 md5 = new MD5();
		if(userInfo.getLoginPasswor()!=null && !"".equals(userInfo.getLoginPasswor())){
			String md5PW = md5.getMD5ofStr(this.userInfo.getLoginPasswor());
			userInfo.setLoginPasswor(md5PW);
		}
		this.sUserService.updateUserById(this.userInfo);
		this.msg = "addsuccuss";
		return userSetInit();
	}
	
	public String updateUser(){
		if(this.userInfo.getUserId()!=null && this.userInfo.getUserId()==1){
			this.userInfo.setParentUserId(0);
		}
		MD5 md5 = new MD5();
		if(userInfo.getLoginPasswor()!=null && !"".equals(userInfo.getLoginPasswor())){
			String md5PW = md5.getMD5ofStr(this.userInfo.getLoginPasswor());
			userInfo.setLoginPasswor(md5PW);
		}
		this.sUserService.updateUserById(this.userInfo);
		return "updateUser";
	}
	
	public String deleteUser(){
		this.sUserService.deleteUser(userId);
		return "deleteUser";
	}
	
	public String jsonUser() {   
		try{ 
			SUser sUser = getLoginUser();
			List userList = sUserService.getSUserByParentId(sUser.getUserId(), sUser.getUserName()); 
			XMLWriter out = new XMLWriter(response.getOutputStream());
			String json = JSONArray.fromObject(userList).toString().replaceAll("\\,\"children\":\\[\\]", ""); 
			out.write(json);
			out.flush();
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 用户列表查询json
	 * @return SUser对象列表（json）
	 */
	public String jsonUserAll() {   
		try{ 
			List userList = sUserService.getUserList(1L, 50); 
			PaginationData pager = new PaginationData(50,10, 1, userList);
			XMLWriter out = new XMLWriter(response.getOutputStream());
			String json = JSONArray.fromObject(pager).toString(); 
			
			String result = json.substring(1, json.length()-1); //去掉一头一尾的"["、"]"
			out.write(result);
			out.flush();
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public String userOpenWindowList() throws UnsupportedEncodingException{
		Map permissionMap = (Map) this.session.get(Constants.USER_PERMISSION_MAP);
		String childUserIds = (String) permissionMap.get("childUserFilter"); 
		if(this.userInfo == null){
			this.userInfo = new SUser();
		}
		if (this.pageNumber == null || this.pageNumber == 0L) {
			this.pageNumber = 1L;
		}
		if(this.userName != null){
			String strPtname = request.getParameter("userName");
			this.userName = new String(strPtname.getBytes("ISO-8859-1"), "UTF-8"); 
			this.userInfo.setUserName(this.userName);
		}
		Integer totalCount = 0;
		SUser sUser = (SUser) this.session.get(Constants.USER_INFO);
		if(sUser.getRoleId()==1  || isNotControl == 1){
			//this.userList = this.sUserService.getUserList(pageNumber, pageSize);
			//totalCount = this.sUserService.getUserCount();
			this.userList = this.sUserService.getUserPageList(pageNumber, pageSize, this.userInfo);
			totalCount = this.sUserService.getUserPageCount(this.userInfo);
		}else{
			totalCount = this.sUserService.getOpenUserCount(this.userInfo.getDepartmentId(), this.userInfo.getUserName(), childUserIds); 
			if(totalCount!=null && totalCount.intValue()>0){
				this.userList = this.sUserService.getOpenUserList(this.userInfo.getDepartmentId(), this.userInfo.getUserName(), childUserIds, pageNumber, pageSize); 
			}
		}
		
		this.page = new Page();
		this.page.setData(userList);
		this.page.setPageSize(this.pageSize);
		this.page.setTotalCount(Long.valueOf(totalCount));
		this.page.setStart(this.pageNumber.intValue());  
		return "userOpenWindowList";
	}
	
	public String k3UserOpenWindowList(){
		k3UserList = this.k3Service.getUser();
		return "k3UserOpenWindowList";
	}
	
	public String attentionUserList() throws UnsupportedEncodingException{
		Map permissionMap = (Map) this.session.get(Constants.USER_PERMISSION_MAP);
		String childUserIds = (String) permissionMap.get("childUserFilter"); 
		if(this.userInfo == null){
			this.userInfo = new SUser();
		}
		else{
			this.userInfo.setUserName(new String(this.userInfo.getUserName().getBytes("ISO-8859-1"), "UTF-8"));
		}
		if (this.pageNumber == null || this.pageNumber == 0L) {
			this.pageNumber = 1L;
		} 
		Integer totalCount = 0;
		SUser sUser = (SUser) this.session.get(Constants.USER_INFO);
		this.userList = this.sUserService.getUserPageList(pageNumber, pageSize, this.userInfo);
		totalCount = this.sUserService.getUserPageCount(this.userInfo);
		this.page = new Page();
		this.page.setData(userList);
		this.page.setPageSize(this.pageSize);
		this.page.setTotalCount(Long.valueOf(totalCount));
		this.page.setStart(this.pageNumber.intValue());  
		
		isNotControl = 1;
		return "userOpenWindowList";
	}

	public List getK3UserList() {
		return k3UserList;
	}

	public void setK3UserList(List k3UserList) {
		this.k3UserList = k3UserList;
	}

	public List<SUser> getUserList() {
		return userList;
	}

	public void setUserList(List<SUser> userList) {
		this.userList = userList;
	}

	public SUserService getsUserService() {
		return sUserService;
	}

	public void setsUserService(SUserService sUserService) {
		this.sUserService = sUserService;
	}

	/**
	 * @return the isNotControl
	 */
	public int getIsNotControl() {
		return isNotControl;
	}

	/**
	 * @param isNotControl the isNotControl to set
	 */
	public void setIsNotControl(int isNotControl) {
		this.isNotControl = isNotControl;
	}

	

}
