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
<title> 入库单列表 </title>

<script type="text/javascript" src="<%=basePath %>dwr/engine.js"></script>     
<script type="text/javascript" src="<%=basePath %>dwr/util.js"></script>
<script type="text/javascript" src="<%=basePath %>dwr/interface/msgPushManager.js"></script> 
<script type="text/javascript" src="<%=basePath %>script/common/jquery-1.4.2.min.js" ></script>
<link rel="stylesheet" href="<%=basePath %>script/common/autocomplete/jquery.autocomplete.css" type="text/css"></link> 
<link href="skin/Default/css/base.css" rel="stylesheet" type="text/css" />
<link href="skin/Default/css/global.css" rel="stylesheet" type="text/css" /> 
</head>

<body class="ui-lv2bg">
	<div class="ui-content-title"><p><img src="skin/Default/images/ui-content-ico/list.png" />入库单列表</p></div>
<div class="ui-table-style1">
	<table  cellspacing="0" cellpadding="0" >
	  <tr class="ui-table-style1-tr1">	   
	    <td class="td2">单据编号</td>
	    <td class="td2">部门</td>  
	    <td class="td4">业务员</td>
	    <td class="td5">制单人</td>  
	    <td class="td3">单据状态</td>
	    <td class="td4">源单类型</td>
	    <td class="td3">日期</td> 
	    <td class="td2">操作</td>
	  </tr>
		<!--      =================表格循环===============-->
	  <s:iterator value="stockBillList" status="dl">
		<tr class="ui-table-style1-tr2">
		  <td><s:property value="fBillNo"/></td>
		  <td><s:property value="departmentName"/></td>  
		  <td><s:property value="employeeName"/></td>
		  <td><s:property value="billerName"/></td> 
		  <td><s:property value="statusName"/></td>
		  <td><s:property value="sourceTranType"/></td>
		  <td><s:date name="fDate" format="yyyy-MM-dd"/></td>  
		  <td><a href="<%=basePath %>stockBill!show?stockBill.fInterID=<s:property value="fInterID"/>"> 查看 </a> </td>
		</tr>
	  </s:iterator>
	</table>
</div>
<div class="ui-pagelist mt10 mb50">	
	<div style="float: right;" id="pager"></div>
</div>
</body>
</html>