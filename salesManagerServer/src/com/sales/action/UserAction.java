package com.sales.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;

import my.com.ibatis.core.dao.support.Page;

import com.opensymphony.xwork2.ActionContext;
import com.sales.common.Constants;
import com.sales.common.MsgPushManager;
import com.sales.model.SUser;
import com.sales.model.User;
import com.sales.service.UserService;

public class UserAction  extends BaseAction {
	
	private String param;
	
	private UserService userService;
	
	private List<User> userList;
	
	private User user;
	
	private MsgPushManager msgPushManager;
	
	//private JSONArray jsonArray;
	
	private List<User> jsonAutoList;
	
	private Long pageNumber = 1L; // 页数
	private int pageSize = Page.DEFAULT_PAGE_SIZE;; // 页面大小
	private Page page ;
	
	public String index() {
		userList = this.userService.getUserList();
		user = new User();
		this.session.put(Constants.USER_INFO, user);
		return "userList";
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

	public String editNew() {
		return "add";
	}

	
	public String show() {
		return "show";
	}
	
	public String add() {
//		Integer id = this.userService.addUser(this.getModel());
    	return "list";
    }
  
	public String delete() {
//		this.userService.deleteUser(this.getModel().getId());
		return "list";
	}
	
	public String smdemo() {
		return "smdemo";
	}
	
	public String autoComplete() {
		String searchtxt = this.request.getParameter("q");
		jsonAutoList = this.userService.getUserListByConditions(searchtxt);
		//this.jsonArray = JSONArray.fromObject(jsonUserList);
		return Constants.FORWARD_STR_JSON_RESULT;
	}
	
	public String userPageList() {
		if (this.pageNumber == null || this.pageNumber == 0L) {
			this.pageNumber = 1L;
		}
		Integer totalCount = new Integer(0);
		userList = this.userService.getUserList();
		totalCount = userList.size();
		
		this.page = new Page();
		this.page.setData(this.userList);
		this.page.setPageSize(this.pageSize);
		this.page.setTotalCount(Long.valueOf(totalCount));
		this.page.setStart(this.pageNumber.intValue());
		
		return "userPageList";
	}
  
    
    

	public void setParam(String param) {
		this.param = param;
	}

	public String getParam() {
		return param;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setMsgPushManager(MsgPushManager msgPushManager) {
		this.msgPushManager = msgPushManager;
	}

	public List<User> getJsonAutoList() {
		return jsonAutoList;
	}

	public void setJsonAutoList(List<User> jsonAutoList) {
		this.jsonAutoList = jsonAutoList;
	}


	
}
