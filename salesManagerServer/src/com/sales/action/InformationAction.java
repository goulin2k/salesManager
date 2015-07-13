package com.sales.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import my.com.ibatis.core.dao.support.Page;

import com.opensymphony.xwork2.ActionContext;
import com.sales.common.Constants;
import com.sales.common.MsgPushManager;
import com.sales.common.Result;
import com.sales.model.SInformation;
import com.sales.model.SLog;
import com.sales.model.SUser;
import com.sales.service.InformationService;

/** 
 * @author  
 * @version 创建时间：2013-6-7 下午04:29:22 
 *  
 */
public class InformationAction extends BaseAction {
	
	private InformationService informationService;
	private List<SInformation> infoList;
	private Integer userId;
	private Long pageNumber = 1L; // 页数
	private int pageSize = Page.DEFAULT_PAGE_SIZE;; // 页面大小
	private Page page ;
	private Integer infoId;
	private SInformation oneInfo;
	private MsgPushManager msgPushManager;
	private SInformation info;
	
	
	
	/**
	 * @return the info
	 */
	public SInformation getInfo() {
		return info;
	}

	/**
	 * @param info the info to set
	 */
	public void setInfo(SInformation info) {
		this.info = info;
	}

	public MsgPushManager getMsgPushManager() {
		return msgPushManager;
	}

	public void setMsgPushManager(MsgPushManager msgPushManager) {
		this.msgPushManager = msgPushManager;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	private String title;
	private Date addTime;
	public JSONArray getJsonArray() {
		return jsonArray;
	}

	public void setJsonArray(JSONArray jsonArray) {
		this.jsonArray = jsonArray;
	}

	private JSONArray jsonArray;

	public SInformation getOneInfo() {
		return oneInfo;
	}

	public void setOneInfo(SInformation oneInfo) {
		this.oneInfo = oneInfo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getInfoId() {
		return infoId;
	}

	public void setInfoId(Integer infoId) {
		this.infoId = infoId;
	}
	
	public InformationService getInformationService() {
		return informationService;
	}
	public void setInformationService(InformationService informationService) {
		this.informationService = informationService;
	}
	public List<SInformation> getInfoList() {
		return infoList;
	}
	public void setInfoList(List<SInformation> infoList) {
		this.infoList = infoList;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
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
	
	public String infoList(){
		if (this.info == null) {
			this.info = new SInformation();
			info.setStatus(1);
		}
		
		if (this.pageNumber == null || this.pageNumber == 0L) {
			this.pageNumber = 1L;
		}
		SUser sUser = (SUser) this.session.get(Constants.USER_INFO);
		Integer totalCount = new Integer(0);
		if(info.getType() !=null && info.getType() == -1)
			info.setType(null);
		this.infoList = this.informationService.getInfoListByUserId(
				sUser.getUserId(), info.getType(), info.getStatus(), 
				pageNumber, pageSize);
		totalCount = this.informationService.getInfoCountByUserId(
				sUser.getUserId(), info.getType(), info.getStatus());
		
		this.page = new Page();
		this.page.setData(infoList);
		this.page.setPageSize(this.pageSize);
		this.page.setTotalCount(Long.valueOf(totalCount));
		this.page.setStart(this.pageNumber.intValue());
		return "infoList";
	}
	
	public String queryInfo(){
		this.oneInfo = this.informationService.getInformationById(this.infoId);
		SInformation info = new SInformation();
		info.setInformationId(this.infoId);
		info.setStatus(Constants.INFO_STATUS_READE);
		info.setType(oneInfo.getType());
		this.informationService.updateInfo(info);
		if(oneInfo.getAddTime()!=null){
			this.addTime = oneInfo.getAddTime();
		}
		return "queryInfo";
	}
	
	public String deleteInfo(){
		this.informationService.deleteInfo(infoId);		
		return infoList();
	}
	
	/**
	 * @return
	 */
	public String jsonInfos(){ 
		try{
			SUser sUser = (SUser) this.session.get(Constants.USER_INFO);
			infoList = this.informationService.getInfoListByUserId(sUser.getUserId(), null, null, 1L, 4);
			
			this.jsonArray = JSONArray.fromObject(infoList);
			response = (HttpServletResponse) ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE); 
			response.setCharacterEncoding("UTF-8"); 
			response.getWriter().print(jsonArray); 
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public String jsonUpdateInfo(){ 
		try{
			
			this.oneInfo = this.informationService.getInformationById(this.infoId);
			SInformation info = new SInformation();
			info.setInformationId(this.infoId);
			info.setType(oneInfo.getType());
			info.setStatus(Constants.INFO_STATUS_READE);
			this.informationService.updateInfo(info);
			if(isFromMobile()) {
				Result res=new Result(1, "");
				JSONObject json = JSONObject.fromObject(res);
				writeJsonP(json);
			}else {
				response = (HttpServletResponse) ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE); 
				response.setCharacterEncoding("UTF-8"); 
				response.getWriter().print("0");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取指定id的通知消息，并设置为已读状态
	 * @return
	 */
	public String getInfoJson() {
		try {
			this.oneInfo = this.informationService.getInformationById(this.infoId);
			JSONObject json = JSONObject.fromObject(oneInfo);
			if(oneInfo.getStatus() == Constants.INFO_STATUS_NEW) {
				oneInfo.setStatus(Constants.INFO_STATUS_READE);
				informationService.updateInfo(oneInfo);
			}
			
			response = (HttpServletResponse) ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE); 
			response.setCharacterEncoding("UTF-8"); 
			response.getWriter().print(json);
		} catch (Exception e) {
			actionLog.error(e.getMessage());
		}
		return null;
	}
	
	public String msgPush(){
		try{
			SUser sUser = (SUser) this.session.get(Constants.USER_INFO);
			infoList = this.informationService.getInfoListByUserId(sUser.getUserId(), null, null, 1L, 5);
			List infoList = this.informationService.getInfoListByStatus(Constants.INFO_STATUS_NEW, sUser.getUserId());
			if(infoList.size()>0){
				SInformation info = info = (SInformation) infoList.get(0);
				String receiverid = info.getSendUserId().toString();
				String msg = "你当前有"+infoList.size()+"条新消息，请注意查收！";
				msgPushManager.send("后台系统", receiverid, msg);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 直接返回消息信息，并增加消息链接到消息中心
	 * @return
	 */
	public String msgPush2(){
		try{
			SUser sUser = (SUser) this.session.get(Constants.USER_INFO);
			infoList = this.informationService.getInfoListByUserId(sUser.getUserId(), null, null, 1L, 5);
			List infoList = this.informationService.getInfoListByStatus(Constants.INFO_STATUS_NEW, sUser.getUserId());
			if(infoList.size()>0){
				SInformation info = info = (SInformation) infoList.get(0);
				String receiverid = info.getSendUserId().toString();
				String msg = "<A href='info!infoList'>" +
						"你当前有"+infoList.size()+"条新消息，请注意查收！</A>";
				response = (HttpServletResponse) ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE); 
				response.setCharacterEncoding("UTF-8"); 
				response.getWriter().print(msg); 
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Ajax获取当前登录用户未读消息数
	 * @return
	 */
	public String getMessageCount() {
		try{
			SUser sUser = (SUser) this.session.get(Constants.USER_INFO);
			Integer infoCount = informationService.getInfoCountByUserId(
					sUser.getUserId(), null, Constants.INFO_STATUS_NEW);
			session.put("infoCount", infoCount);
			
			response.setCharacterEncoding("UTF-8"); 
			response.getWriter().print(infoCount.toString()); 
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 设置当前用户所有所有消息为已读
	 * @return
	 */
	public String setAllReaded() {
		SUser sUser = (SUser) this.session.get(Constants.USER_INFO);
		informationService.setAllReaded(sUser.getUserId());
		return infoList();
	}

}
