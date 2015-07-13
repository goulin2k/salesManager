package com.sales.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import my.com.ibatis.core.dao.support.Page;

import com.sales.common.Constants;
import com.sales.model.SNews;
import com.sales.model.SUser;
import com.sales.service.NewsService;

public class DispatchAction extends BaseAction {
	
	private NewsService newsService;
	private SNews news;
	private List newsList;
	private Map statusMap;
	private Long pageNumber = 1L; // 页数
	private int pageSize = Page.DEFAULT_PAGE_SIZE; // 页面大小
	private Page page ;
	
	public String list(){
		if(this.news == null){
			this.news = new SNews();
		}
		if (this.pageNumber == null || this.pageNumber == 0L) {
			this.pageNumber = 1L;
		}
		Integer totalCount = this.newsService.getNewsCount(this.news.getTitle(), this.news.getStatus(), Constants.NEWS_TYPE_DISPATCH);
		if(totalCount > 0){
			this.newsList = this.newsService.getNewsList(this.news.getTitle(), this.news.getStatus(), Constants.NEWS_TYPE_DISPATCH, pageNumber.intValue(), pageSize);
		}
		this.page = new Page();
		this.page.setData(this.newsList);
		this.page.setPageSize(this.pageSize);
		this.page.setTotalCount(Long.valueOf(totalCount));
		this.page.setStart(this.pageNumber.intValue());
		this.statusMap = Constants.NEWSLISTSTATUS;
		return "list";
	}
	
	public String editNew(){
		this.statusMap = Constants.NEWSSTATUS;
		return "editNew";
	}
	
	public String add(){
		SUser sUser = getLoginUser();
		this.news.setUserId(sUser.getUserId());
		this.news.setAddTime(new Date());
		this.news.setType(Constants.NEWS_TYPE_DISPATCH);
		this.newsService.insertNews(this.news);
		return "add";
	}
	
	public String show(){
		this.news = this.newsService.getNewsById(this.news.getNewsId());
		this.statusMap = Constants.NEWSSTATUS;
		return "show";
	}
	
	public String update(){
		SUser sUser = getLoginUser();
		this.news.setUserId(sUser.getUserId()); 
		this.newsService.updateNewsById(news);
		return "update";
	}
	
	public String delete(){
		this.newsService.deleteNews(this.news.getNewsId());
		return "delete";
	}

	public NewsService getNewsService() {
		return newsService;
	}

	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}

	public SNews getNews() {
		return news;
	}

	public void setNews(SNews news) {
		this.news = news;
	}

	public List getNewsList() {
		return newsList;
	}

	public void setNewsList(List newsList) {
		this.newsList = newsList;
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

}
