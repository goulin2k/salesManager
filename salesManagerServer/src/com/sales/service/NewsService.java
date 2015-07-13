package com.sales.service;
 
import java.util.List; 
 
import com.sales.dao.SNewsDAO;
import com.sales.model.SNews;

public interface NewsService {
	
	public void deleteNews(Integer newsId);
	
	public void insertNews(SNews news);
	
	public SNews getNewsById(Integer newsId);
	
	public void updateNewsById(SNews news);
	
	public Integer getNewsCount(String title, Integer status, Integer type);
	
	public List getNewsList(String title, Integer status, Integer type, Integer pageNumber, Integer pageSize);

	public SNewsDAO getNewsDao();

	public void setNewsDao(SNewsDAO newsDao);

}
