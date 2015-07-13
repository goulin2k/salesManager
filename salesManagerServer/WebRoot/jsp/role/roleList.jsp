<%@ page language="java" import="com.sales.model.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sales" uri="/WEB-INF/sales-util.tld"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<htmlxmlns="http://www.w3.org/1999/xhtml">
<head>
<title>角色管理</title>

<script type="text/javascript" src="<%=basePath %>dwr/engine.js"></script>     
<script type="text/javascript" src="<%=basePath %>dwr/util.js"></script>
<script type="text/javascript" src="<%=basePath %>dwr/interface/msgPushManager.js"></script> 
<script type="text/javascript" src="<%=basePath %>script/common/jquery-1.4.2.min.js" ></script>
<link rel="stylesheet" href="<%=basePath %>script/common/autocomplete/jquery.autocomplete.css" type="text/css"></link> 
<link href="skin/Default/css/base.css" rel="stylesheet" type="text/css" />
<link href="skin/Default/css/global.css" rel="stylesheet" type="text/css" />
</head>

<body class="ui-lv2bg">
    <nav class="navbar navbar-default" role="navigation">
	    <ul class="nav navbar-nav">
	    	<li class="active"><a href="role!addRoleInit"><span class="glyphicon glyphicon-star"></span>&nbsp;新增</a></li>
	    </ul>
    </nav>

<div class="panel panel-default" id="searchResult" style="padding:2px;">
    <div class="panel-heading crm-table-title"><B>角色查询列表</B></div>
	<table class="table table-hover" cellspacing="0" cellpadding="0" >
	  <thead>
	    
	    <th >角色名称</th>
	    <th >角色说明</th>
	    <th >客户访问控制</th>
	    <th >角色权限</th> 
	    <th >删除</th> 
	  </thead>
		<!--      =================表格循环===============-->
	  <s:iterator id="temp" value="roleList" status="dl">
		<tr class="ui-table-style1-tr2">
		  
		  <td><s:property value="name"/></td>
		  <td><s:property value="comment"/></td>
		  <td><s:property value="customerChecked?'是':'否'"/></td>
		  <td><a href="role!getRoleAction?roleId=${temp.roleId }">角色权限</a></td>
		  <td><a href="sUser!removeRole?roleId=${temp.roleId }">删除</a></td>
		</tr>
	  </s:iterator>
	</table>
</div>

</body>
</html>