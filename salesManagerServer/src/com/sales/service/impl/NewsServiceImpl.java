package com.sales.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
import com.sales.dao.SNewsDAO;
import com.sales.model.SNews;
import com.sales.service.NewsService;

public class NewsServiceImpl implements NewsService {
	
	private SNewsDAO newsDao;
	
	public void deleteNews(Integer newsId){
		newsDao.deleteNews(newsId);	
	}
	
	public void insertNews(SNews news){
		newsDao.insertNews(news);	
	}
	
	public SNews getNewsById(Integer newsId){
		SNews news = newsDao.getNewsById(newsId);
		return news;
	}
	
	public void updateNewsById(SNews news){
		newsDao.updateNewsById(news);
	}
	
	public Integer getNewsCount(String title, Integer status, Integer type){
		Map queryMap = new HashMap();
		queryMap.put("title", title); 
		queryMap.put("status", status);
		queryMap.put("type", type);  
		Integer count = newsDao.getNewsCount(queryMap);
		return count;
	}
	
	public List getNewsList(String title, Integer status, Integer type, Integer pageNumber, Integer pageSize){
		Map queryMap = new HashMap();
		queryMap.put("title", title); 
		queryMap.put("status", status);
		queryMap.put("type", type);  
		Integer startNumber = (pageNumber - 1) * pageSize; 
		queryMap.put("startNumber", startNumber);
		queryMap.put("pageSize", pageSize);
		List newsList = newsDao.getNewsList(queryMap);
		return newsList;
	}

	public SNewsDAO getNewsDao() {
		return newsDao;
	}

	public void setNewsDao(SNewsDAO newsDao) {
		this.newsDao = newsDao;
	}

}
