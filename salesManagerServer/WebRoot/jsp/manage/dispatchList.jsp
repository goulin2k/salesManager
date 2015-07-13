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
<title>lv2</title>
<link href="skin/Default/css/base.css" rel="stylesheet" type="text/css" />
<link href="skin/Default/css/global.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath %>script/common/jquery-1.4.2.min.js" ></script>
<script type="text/javascript" language="javascript" src="<%=basePath %>script/common/pager/jquery.pager.js"></script>
<link type="text/css" rel="stylesheet" href="<%=basePath %>script/common/pager/Pager.css" />
	<link rel="icon" href="<%=basePath %>/jsp/lhgcalendar/favicon.ico" type="image/x-icon"/>
	<link rel="shortcut icon" href="<%=basePath %>/jsp/lhgcalendar/favicon.ico" type="image/x-icon"/>
	<link href="<%=basePath %>/jsp/lhgcalendar/_doc/common.css" type="text/css" rel="stylesheet"/>
	<link href="<%=basePath %>/jsp/lhgcalendar/_doc/prettify/prettify.css" type="text/css" rel="stylesheet"/>
	<script type="text/javascript" src="<%=basePath %>/jsp/lhgcalendar/_doc/prettify/prettify.js"></script>
	<script type="text/javascript" src="<%=basePath %>/jsp/lhgcalendar/lhgcalendar.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>/jsp/lhgcalendar/_demo/demo.js"></script>
<script type="text/javascript">
var basePath = "<%=path%>/";
$(document).ready(function(){
	$("#pager").pager({
	    pagenumber:${page.start},                         /* 表示初始页数 */
	    pagecount:${page.pageCount},                      /* 表示总页数 */
	    totalCount:${page.totalCount},
	    buttonClickCallback:PageClick                     /* 表示点击分页数按钮调用的方法 */                     
	});
	
});

/*
       PageClick = function(pageclickednumber) {}部分
       PageClick，表示自定义点击分页数时的function方法，如：function(pageclickednumber){}
       jQuery插件JQuery Pager分页器只需要起始页数pagenumber，最大页数pagecount，
            点击页数时的调用buttonClickCallback的 function方法就可实现javascript分页功能，
            实际应用中只需对PageClick方法进行简单修改就可使用，如将pagenumber和 pagecount设为变量，
            可通过GET的方法进行页数值传递，JQuery Pager就可实现javascript分页功能
   */
   PageClick = function(pageclickednumber) {
	$("#pager").pager({
	    pagenumber:pageclickednumber,                 /* 表示启示页 */
	    pagecount:${page.pageCount},                  /* 表示最大页数pagecount */
	    buttonClickCallback:PageClick                 /* 表示点击页数时的调用的方法就可实现javascript分页功能 */            
	});
	
	$("#pageNumber").val(pageclickednumber);          /* 给pageNumber从新赋值 */
	/* 执行Action */
	pagesearch();
}
		
function search(){
	$("#pageNumber").val("1");
	pagesearch();
}

function pagesearch(){
	news.submit();
}  
</script>
</head>
<body class="ui-lv2bg">
<div class="ui-content-subnav">
	<div class="ui-content-title"><p><img src="skin/Default/images/ui-content-ico/1.png" />公司发文管理</p></div>
	<ul>
    	<li><a href="<%=basePath %>dispatch!editNew"><img src="skin/Default/images/ui-content-ico/Summary.jpg" /><p>新增</p></a></li> 
    </ul>
</div>
<form id="news" name="news" action="<%=basePath %>dispatch!list" method="post">
<div class="ui-com-search">
    <div class="ui-com-search-title ">
	  <input type="hidden" id="pageNumber" name="pageNumber" value="${page.start}" /> 
        标题：<input class="ui-com-search-input" name="news.title" type="text" value="<s:property value="news.title"/>" />
        状态： <s:select name="news.status" headerKey="" headerValue="状态" listKey="key" listValue="value" list="statusMap"/>    
        <div class="ui-combottom fr"><input name="savein" type="button" class="button" value="查询" onClick="news.submit();"></div>
    </div>   
</div>
</form>
<div class="ui-table-style1">
	<table  cellspacing="0" cellpadding="0" >
	  <tr class="ui-table-style1-tr1">
        <td class="td2">标题</td>
        <td class="td3">发布人</td>
        <td class="td4">发布时间</td>
        <td class="td5">状态</td> 
	    <td class="td2">操作</td>
	  </tr>
		<!--      =================表格循环===============-->
	  <s:iterator value="newsList" status="dl">
		<tr class="ui-table-style1-tr2"> 
          <td><s:property value="title"/></td>
          <td><s:property value="userName"/></td>
          <td><s:date name="addTime" format="yyyy-MM-dd" /></td>
          <td><s:property value="statusName"/></td>  
		  <td><a href="<%=basePath %>dispatch!show?news.newsId=<s:property value="newsId"/>"> 修改 </a>
		  <a href="<%=basePath %>dispatch!delete?news.newsId=<s:property value="newsId"/>"> 删除  </a></td>
		</tr>
	  </s:iterator>
	</table>
</div>
<div class="ui-pagelist">
	
		<div style="float: right;" id="pager"></div>
</div>
</body> 
</html>


