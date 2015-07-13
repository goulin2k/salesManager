<%@ page language="java" import="com.sales.model.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sales" uri="/WEB-INF/sales-util.tld"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title> 订单列表 </title>
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
    <link type="text/css" href="<%=basePath %>skin/Default/css/fisheye.css" rel="stylesheet"  />
    <link type="text/css" href="<%=basePath %>skin/bootstrap/css/jquery-ui-1.10.0.custom.css" rel="stylesheet"  />
    <link type="text/css" href="<%=basePath %>skin/bootstrap/css/jquery.ui.1.10.0.ie.css" rel="stylesheet"  />
    <link type="text/css" href="<%=basePath %>skin/Default/css/base.css" rel="stylesheet"  />
    <link type="text/css" href="<%=basePath %>skin/Default/css/global.css" rel="stylesheet"  />
    <link href="skin/bootstrap/css/bootstrap-dialog.min.css" rel="stylesheet">
    

	
	<script type="text/javascript" src="<%=basePath %>skin/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>skin/bootstrap/js/jquery-ui-1.10.0.custom.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>skin/bootstrap/js/bootstrap-dialog.min.js"></script>
</head>

<body class="ui-lv2bg">
<!--  查询结果列表 -->
<div class="panel panel-default" id="searchResult" style="padding:2px;">
<div class="panel-heading crm-table-title"><B>销售订单查询记录</B></div>
	<table class="table table-hover" cellspacing="0" cellpadding="0" >
	  <thead>	  
	    <th>订单编号</th>
	    <th>购货单位</th>  
	    <th>部门</th>
	    <th>业务员</th>  
	    <th>审核人</th>
	    <th>日期</th>
	    <th>是否关闭</th>
	    <th>状态</th>
	    <th>操作</th>
	  </thead>
		<!--      =================表格循环===============-->
	  <s:iterator value="orderList" status="dl">
		<tr class="ui-table-style1-tr2">
		  <td><s:property value="fBillNo"/></td>
		  <td><s:property value="customerName"/></td>  
		  <td><s:property value="departName"/></td>
		  <td><s:property value="employeeName"/></td> 
		  <td><s:property value="checkerName"/></td>
		  <td><s:date name="fDate" format="yyyy-MM-dd"/></td>
		  <td><s:property value="closedName"/></td> 
		  <td><s:property value="statusName"/></td> 
		  <td><a href="<%=basePath %>order!show?order.fInterID=<s:property value="fInterID"/>"> 查看 </a> </td>
		</tr>
	  </s:iterator>
	</table>
</div>
<div class="ui-pagelist mt10 mb50">	
	<div style="float: right;" id="pager"></div>
</div>
</body>
</html>