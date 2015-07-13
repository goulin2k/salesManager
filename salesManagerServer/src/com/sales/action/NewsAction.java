package com.sales.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import my.com.ibatis.core.dao.support.Page;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.google.gson.JsonObject;
import com.opensymphony.xwork2.ActionContext;
import com.sales.common.Constants;
import com.sales.model.SNews;
import com.sales.model.SUser;
import com.sales.service.NewsService;

public class NewsAction extends BaseAction {
	
	private NewsService newsService;
	private SNews news;
	private List newsList;
	private Map statusMap;
	private Long pageNumber = 1L; // 页数
	private int pageSize = Page.DEFAULT_PAGE_SIZE; // 页面大小
	private Page page ;
	private JSONArray jsonArray;
	private File file;
	private String fileFileName;
	
	public String list(){
		if(this.news == null){
			this.news = new SNews();
		}
		if (this.pageNumber == null || this.pageNumber == 0L) {
			this.pageNumber = 1L;
		}
		Integer totalCount = this.newsService.getNewsCount(this.news.getTitle(), this.news.getStatus(), Constants.NEWS_TYPE_NEWS);
		if(totalCount > 0){
			this.newsList = this.newsService.getNewsList(this.news.getTitle(), this.news.getStatus(), Constants.NEWS_TYPE_NEWS, pageNumber.intValue(), pageSize);
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
	
	public String add() throws IOException{
		String imgUrl = null;
		if(file != null){
			String root = ServletActionContext.getRequest().getRealPath("/jsp/upload/news");
			InputStream is = new FileInputStream(file);
			String fileName = String.valueOf(System.currentTimeMillis())+fileFileName.substring(fileFileName.indexOf("."));
			File destFile = new File(root, fileName);
			imgUrl = "jsp/upload/news/" + fileName;
			//把图片写入到上面设置的路径里
	        OutputStream os = new FileOutputStream(destFile);
	        byte[] buffer = new byte[400];
	        int length  = 0 ;
	        while((length = is.read(buffer))>0){
	            os.write(buffer, 0, length);
	        }
	        is.close();
	        os.close();
		}
		SUser sUser = getLoginUser();
		this.news.setImgUrl(imgUrl);
		this.news.setUserId(sUser.getUserId());
		this.news.setAddTime(new Date());
		this.news.setType(Constants.NEWS_TYPE_NEWS);
		this.newsService.insertNews(this.news);
		return "add";
	}
	
	public String show(){		
		this.news = this.newsService.getNewsById(this.news.getNewsId());
		this.statusMap = Constants.NEWSSTATUS;
		if(isFromMobile()) {
			JSONObject json = JSONObject.fromObject(news);
			writeJsonP(json);
			return null;
		}else
			return "show";
	}
	
	public String showNews(){
		this.news = this.newsService.getNewsById(this.news.getNewsId()); 
		return "showNews";
	}
	
	public String update() throws IOException{
		String imgUrl = null;
		if(file != null){
			String root = ServletActionContext.getRequest().getRealPath("/jsp/upload/news");
			InputStream is = new FileInputStream(file);
			String fileName = String.valueOf(System.currentTimeMillis())+fileFileName.substring(fileFileName.indexOf("."));
			File destFile = new File(root, fileName);
			imgUrl = "jsp/upload/news/" + fileName;
			//把图片写入到上面设置的路径里
	        OutputStream os = new FileOutputStream(destFile);
	        byte[] buffer = new byte[400];
	        int length  = 0 ;
	        while((length = is.read(buffer))>0){
	            os.write(buffer, 0, length);
	        }
	        is.close();
	        os.close();
		}
		SUser sUser = getLoginUser();
		this.news.setImgUrl(imgUrl);
		this.news.setUserId(sUser.getUserId()); 
		this.newsService.updateNewsById(news);
		return "update";
	}
	
	public String delete(){
		this.newsService.deleteNews(this.news.getNewsId());
		return "delete";
	}
	
	public String jsonNews(){ 
		try{
			List newsList = this.newsService.getNewsList(null, null, Constants.NEWS_TYPE_NEWS, 1, 10);
			this.jsonArray = JSONArray.fromObject(newsList);
			response = (HttpServletResponse) ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE); 
			response.setCharacterEncoding("UTF-8"); 
			response.getWriter().print(jsonArray); 
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
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

	public JSONArray getJsonArray() {
		return jsonArray;
	}

	public void setJsonArray(JSONArray jsonArray) {
		this.jsonArray = jsonArray;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

}
