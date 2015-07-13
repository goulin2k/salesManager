<%@ page language="java" import="com.sales.model.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sales" uri="/WEB-INF/sales-util.tld"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <base target="_self">
	<title>凯扬工业——客户管理系统工作台</title>
	
	
	<!-- Bootstrap core CSS -->
    <link href="skin/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="skin/bootstrap/css/bootstrap-theme.css" rel="stylesheet">
    
    <!-- Just for debugging purposes. Don't actually copy this line! -->
    <!--[if lt IE 9]><script src="../../docs-assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.0/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
    
    <link type="text/css" href="<%=basePath %>skin/bootstrap/css/jquery-ui-1.10.0.custom.css" rel="stylesheet"  />
    <link type="text/css" href="<%=basePath %>skin/Default/css/jquery-ui.css" rel="stylesheet"  />
    <link type="text/css" href="<%=basePath %>skin/bootstrap/css/jquery.ui.1.10.0.ie.css" rel="stylesheet"  />
    <link type="text/css" href="<%=basePath %>skin/Default/css/base.css" rel="stylesheet"  />
    <link type="text/css" href="<%=basePath %>skin/Default/css/global.css" rel="stylesheet"  />
    <link type="text/css" rel="stylesheet" href="<%=basePath %>script/common/pager/Pager.css" />
    

	<script type="text/javascript" src="<%=basePath %>skin/bootstrap/js/jquery-1.8.3.min.js"></script>	
	<script type="text/javascript" src="<%=basePath %>skin/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>skin/bootstrap/js/jquery-ui-1.10.0.custom.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>script/common/pager/jquery.pager.js"></script>
	<script type="text/javascript" src="<%=basePath %>script/common/pages.js"></script>
	
	<script type="text/javascript">
		<!--  添加日期输入控件 -->
		$(function() {
		    $( "#startTime" ).datepicker({
		    	dateFormat: "yy-mm-dd"
		    });
		    $( "#endTime" ).datepicker({
		    	dateFormat: "yy-mm-dd"
		    });
		 });
	
		function returnValue1(trValue){
			var array = new Array(); 
			array[0] = trValue.find('td').eq(3).find('input').val(); 
			array[1] = trValue.find('td').eq(0).text();
			array[2] = trValue.find('td').eq(2).find('input').val();  
			array[3] = trValue.find('td').eq(1).text();
			if (window.opener) {
			       //for chrome
			       window.opener.returnValue = array;
			}
			else {
			       window.returnValue = array;
			}
		    window.close();
		}
	</script>
</head>

<body class="ui-lv2bg2">

<!-- 简单风格的工具查询栏	-->	
	<nav class="navbar navbar-default" role="navigation">
		
	    <ul class="nav navbar-nav">
	    	<li class="active"><a href="project!openlist"><span class="glyphicon glyphicon-refresh"></span>&nbsp;清空查询条件</a></li>
	    	<li class="active"><a href="javascript:this.close();"><span class="glyphicon glyphicon-remove-sign"></span>&nbsp;关闭</a></li>
	    </ul>
	    <form class="navbar-form navbar-right form-inline" role="search" id="searchForm" name="searchForm" 
	    		action="<%=basePath %>project!openlist" method="post">	    		
	      <input type="hidden" id="pageCount" name="pageCount" value="${page.pageCount}" />
		  <input type="hidden" id="pageNumber" name="pageNumber" value="${page.start}" />
		  <input type="hidden" id="totalCount" name="totalCount" value="${page.totalCount}" />
	      
	      <div class="form-group">
      		<input type="text" id="startTime" name="project.startTime" value="<s:property value="project.startTime"/>"
      			class="form-control"  placeholder="计划时间开始" />
      	  </div>
      	  <div class="form-group">
      		<input type="text" id="endTime" name="project.endTime" value="<s:property value="project.endTime"/>"
      			class="form-control"  placeholder="计划时间结束" />
      	  </div>
	      
	      <button onclick="submit();"  class="btn btn-primary">搜索</button>
	    </form>
    </nav>

 <div class="panel panel-default" id="searchResult" style="padding:2px;">
     <div class="panel-heading crm-table-title"><B>销售计划查询记录</B></div> 
	<table class="table table-hover"  cellspacing="0" cellpadding="0" >
	  <thead>	  
	    <th>主题</th>
	    <th>对应客户</th> 
	    <th>目标</th>
	    <th>负责人</th> 
	    <th>开始时间</th>
	    <th>结束时间</th> 
	    <th>计划类型</th> 
	  </tr>
		<!--      =================表格循环===============-->
	  <s:iterator value="projectList" status="dl">
		<tr class="ui-table-style1-tr2" onclick="returnValue1($(this))"> 
		  <td><s:property value="topical"/></td>
		  <td><s:property value="customerName"/></td> 
		  <td><input type="hidden" id="customerId" name="customerId" value="<s:property value="customerId"/>" />
		  	<s:property value="goal"/></td>
		  <td><input type="hidden" id="projectId" name="projectId" value="<s:property value="projectId"/>" />
		  	<s:property value="responseUserName"/></td>
		  <td><s:property value="startTime"/></td>
		  <td><s:property value="endTime"/></td>
		  <td><s:property value="enumerationName"/></td> 
		</tr>
	  </s:iterator>
	</table>
</div>
<div class="ui-pagelist">
	
		<div style="float: right;" id="pager"></div>
</div>
</body>
</html>