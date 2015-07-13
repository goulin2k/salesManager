package com.sales.dao;

import java.util.List;
import java.util.Map;

import com.sales.model.SNews;

public interface SNewsDAO {
	
	public void deleteNews(Integer newsId);
	
	public void insertNews(SNews news);
	
	public SNews getNewsById(Integer newsId);
	
	public void updateNewsById(SNews news);
	
	public Integer getNewsCount(Map queryMap);
	
	public List getNewsList(Map queryMap);

}
