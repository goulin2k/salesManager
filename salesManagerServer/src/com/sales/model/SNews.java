package com.sales.model;

import java.util.Date; 

import com.sales.common.NormalFun;

public class SNews {
	
	private Integer newsId;
	private String title;
	private String content;
	private Integer userId;
	private String userName;
	private Date addTime;
	private Integer status;
	private String statusName;
	private Integer type;
	private String imgUrl;

	
	public Integer getNewsId() {
		return newsId;
	}
	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getUserId() {
		return userId;
	}
	public String getStatusName() {
		if(this.status!=null && this.status.intValue()==1){
			return "已发布";
		}
		else if(this.status!=null && this.status.intValue()==2){
			return "未发布";
		}
		else{
			return "";
		}
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
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
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	public String getAddTimeString() {
		return NormalFun.formatDateString(this.addTime);
	}

}
