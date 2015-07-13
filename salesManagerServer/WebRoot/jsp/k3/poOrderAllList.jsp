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
<title> 采购申请单列表2 </title>

<!-- 日期选择	-->
	<link type="text/css" href="<%=basePath %>skin/Default/css/jquery-ui-1.10.0.custom.css" rel="stylesheet"  />
	<!-- Le HTML5 shim, for IE6-8 support of HTML elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
	<script type="text/javascript" language="javascript" src="<%=basePath %>script/common/jquery-ui-1.10.0.custom.min.js"></script>
	<!-- 分页重构js	-->
	<script type="text/javascript" language="javascript" src="<%=basePath %>script/common/pager/jquery.pager.js"></script>
	<script type="text/javascript" src="<%=basePath %>script/common/pages.js"></script>
	<link type="text/css" rel="stylesheet" href="<%=basePath %>script/common/pager/Pager.css" />  
</head>

<body class="ui-lv2bg">
	<!--  查询结果列表 -->
<div class="panel panel-default" id="searchResult" style="padding:2px;">
<div class="panel-heading crm-table-title"><B>采购订单查询记录</B></div>
	<table class="table table-hover" cellspacing="0" cellpadding="0" >
	  <thead>	  
	    <th>订单日期</th>
	    <th>单据编号	</th> 
	    <th>部门</th>
	    <th>业务员</th>  
	    <th>制单人</th>
	    <th>供应商</th>
	    <th>单据状态</th>
	    <th>操作</th>
	  </thead>
		<!--      =================表格循环===============-->
	  <s:iterator value="ordertList" status="dl">
		<tr class="ui-table-style1-tr2">
		  <td><s:date name="fDate" format="yyyy-MM-dd"/></td>
		  <td><s:property value="fBillNo"/></td> 
		  <td><s:property value="departName"/></td>
		  <td><s:property value="requsterUserName"/></td> 
		  <td><s:property value="billerName"/></td>
		  <td><s:property value="supplierName"/></td>
		  <td></td> 
		  <td><a href="javascript:showPOOrder(<s:property value="fInterId"/>);"> 查看 </a> </td>
		</tr>
	  </s:iterator>
	</table>
</div>
<!--  分页标签 -->
<div class="ui-pagelist mt10 mb50">	
	<div style="float: right;" id="pager"></div>
</div>
</body>
</html>