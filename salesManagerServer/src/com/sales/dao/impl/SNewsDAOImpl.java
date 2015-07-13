package com.sales.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.sales.dao.SNewsDAO;
import com.sales.model.SNews;

public class SNewsDAOImpl extends SqlMapClientDaoSupport implements SNewsDAO {
	
	public void deleteNews(Integer newsId){
		getSqlMapClientTemplate().delete("s_news.deleteNews", newsId);	
	}
	
	public void insertNews(SNews news){
		getSqlMapClientTemplate().insert("s_news.insertNews", news);	
	}
	
	public SNews getNewsById(Integer newsId){
		SNews news = (SNews) getSqlMapClientTemplate().queryForObject("s_news.getNewsById", newsId);
		return news;
	}
	
	public void updateNewsById(SNews news){
		getSqlMapClientTemplate().update("s_news.updateNewsById", news);
	}
	
	public Integer getNewsCount(Map queryMap){
		Integer count = (Integer) getSqlMapClientTemplate().queryForObject("s_news.getNewsCount", queryMap);
		return count;
	}
	
	public List getNewsList(Map queryMap){
		List newsList = (List) getSqlMapClientTemplate().queryForList("s_news.getNewsList", queryMap);
		return newsList;
	}

}
