<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新闻公告</title>
<script type="text/javascript" language="javascript" src="<%=basePath %>script/common/pager/jquery.pager.js"></script>
<link type="text/css" rel="stylesheet" href="<%=basePath %>script/common/pager/Pager.css" />
<script type="text/javascript" src="<%=basePath %>script/common/pages.js"></script>
</head>
<body class="ui-lv2bg">
<!-- 标准工具栏 -->
<nav class="navbar navbar-default crm-toolbar" role="navigation">
	<ul class="nav navbar-nav">
    	<li class="active"><a href="news!editNew"><span class="glyphicon glyphicon-star"></span>&nbsp;新增</a></li>
    	<li class="active"><a href="news!list"><span class="glyphicon glyphicon-refresh"></span>&nbsp;清空查询条件</a></li>
    </ul>
</nav>
<!--  查询结果列表 -->
<div class="panel panel-default" id="searchResult" style="padding:2px;">
     <div class="panel-heading crm-table-title"><B>新闻公告</B></div>
	<table  class="table table-hover" cellspacing="0" cellpadding="0" > 
            <input type="hidden" id="pageCount" name="pageCount" value="${page.pageCount}" />
		  	<input type="hidden" id="pageNumber" name="pageNumber" value="${page.start}" />
		  	<input type="hidden" id="totalCount" name="totalCount" value="${page.totalCount}" />
	  <thead>	   
	    <th>标题</th>  
	    <th>发布人</th>
	    <th>发布时间</th>  
	    <th>状态</th>
	    <th>操作</th> 
	  </thead>
	  <!--      =================表格循环已有的物料列表===============-->
	  <s:iterator value="newsList" status="dl" var="aNew">
		<tr class="ui-table-style1-tr2"> 
          <td width="40%"><a href="<%=basePath %>news!showNews?news.newsId=<s:property value="newsId"/>"><s:property value="title"/></a></td>
          <td><s:property value="userName"/></td>
          <td><s:date name="addTime" format="yyyy-MM-dd" /></td>
          <td><s:property value="statusName"/></td>  
          
          <!-- 只允许作者编辑、删除新闻	-->
		  <s:if test='#session.USER_INFO.userId == #aNew.userId' >
			  <td>
			  		<a href="<%=basePath %>news!show?news.newsId=<s:property value="newsId"/>"> 修改 </a>
			  		<a href="<%=basePath %>news!delete?news.newsId=<s:property value="newsId"/>"> 删除  </a>
			  </td>
		  </s:if>
		</tr>
	  </s:iterator>
	</table>
</div>
<div class="ui-pagelist mt10 mb50">	
	<div style="float: right;" id="pager"></div>
</div>
</body> 
</html>


