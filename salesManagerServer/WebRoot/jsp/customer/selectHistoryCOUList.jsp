<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>客户分配历史</title>

<script type="text/javascript" language="javascript" src="<%=basePath %>script/common/pager/jquery.pager.js"></script>
<link type="text/css" rel="stylesheet" href="<%=basePath %>script/common/pager/Pager.css" />
<script type="text/javascript" language="javascript" src="<%=basePath %>script/common/pages.js"></script>
<script type="text/javascript">
	var basePath = "<%=path%>/";		
</script>
</head>
<body class="ui-lv2bg">
	<!-- 标准工具栏 -->
	<nav class="navbar navbar-default crm-toolbar" role="navigation">
		<ul class="nav navbar-nav">
	    	<li class="active"><a href="javascript:history.back();">
	    		<span class="glyphicon glyphicon-arrow-left"></span>&nbsp;返回</a></li>
	    </ul>
	 </nav>
    
     <div class="panel panel-default" id="searchResult" style="padding:2px;">
     	<div class="panel-heading crm-table-title"><B>客户分配历史记录</B></div>
		<table class="table table-hover" cellspacing="0" cellpadding="0" >
              <thead>
	              <input type="hidden" id="pageNumber" name="pageNumber" value="${page.start}" />
	                <th>客户名称</th>
	                <th>总经理（副）</th>
	                <th>部门经理</th>
	                <th>业务员</th>
	               <th>分配时间</th>
	               <th>结束分配时间</th>
              </thead>
        <!--      =================表格循环===============-->
              <s:iterator id="cou" value="couList" status="dl">
              <tr class="ui-table-style1-tr2">
                <td>${cou.customerName }</td>
                <td>${cou.salegenName }</td>
                <td>${cou.finmanagerName }</td>
                <td>${cou.userName }</td>
                <td><s:date name="#cou.addTime" format="yyyy-MM-dd" /></td>
                <td><s:date name="#cou.overTime" format="yyyy-MM-dd" /></td>
              </tr> 
              </s:iterator>
          <!--     =================表格循环over===============-->
          
        </table>
          

	<div class="ui-pagelist" id="pager">
    	
    </div>
 </div>
	
</body>
</html>