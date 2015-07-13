<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
    <a href="user/1/index">demo1</a>
    <a href="user/2/index">demo2</a>
    <a href="user/3/index">demo3</a>
    <a href="user/1/smdemo">布局示例</a>
    |
    <a href="jsp/jbpmdemo/index.jsp">jbpm demo</a>
    <a href="user!userPageList">分页查询</a>
  </body>
</html>
