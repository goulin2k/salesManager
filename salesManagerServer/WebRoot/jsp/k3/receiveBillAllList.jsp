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
<title> 销售收款单列表 </title>

<script type="text/javascript" src="<%=basePath %>dwr/engine.js"></script>     
<script type="text/javascript" src="<%=basePath %>dwr/util.js"></script>
<script type="text/javascript" src="<%=basePath %>dwr/interface/msgPushManager.js"></script> 
<script type="text/javascript" src="<%=basePath %>script/common/jquery-1.4.2.min.js" ></script>
<link rel="stylesheet" href="<%=basePath %>script/common/autocomplete/jquery.autocomplete.css" type="text/css"></link> 
<link href="skin/Default/css/base.css" rel="stylesheet" type="text/css" />
<link href="skin/Default/css/global.css" rel="stylesheet" type="text/css" /> 
</head>

<body class="ui-lv2bg">
	<div class="ui-content-title"><p><img src="skin/Default/images/ui-content-ico/list.png" />销售收款单列表</p></div>
<div class="ui-table-style1">
	<table  cellspacing="0" cellpadding="0" >
	  <tr class="ui-table-style1-tr1">	   
	    <td class="td2">单据号</td>
	    <td class="td5">结算核算项目</td>  
	    <td class="td3">客户名称</td>
	    <td class="td4">部门</td>
	    <td class="td3">业务员</td>
	    <td class="td3">币别</td>
	    <td class="td3">单据金额</td>
	    <td class="td3">审核人</td>
	    <td class="td2">状态</td>  
	    <td class="td4">日期</td>
	    <td class="td2">操作</td>
	  </tr>
		<!--      =================表格循环===============-->
	  <s:iterator value="receiveBillList" status="dl">
		<tr class="ui-table-style1-tr2">
		  <td><s:property value="fNumber"/></td>
		  <td><s:property value="fItemClassID_DSPName"/></td> 
		  <td><s:property value="customerName"/></td>
		  <td><s:property value="departName"/></td>
		  <td><s:property value="employeeName"/></td> 
		  <td><s:property value="currencyName"/></td> 
		  <td><s:property value="fAmountFor"/></td> 
		  <td><s:property value="checkerName"/></td> 
		  <td><s:property value="billStatusName"/></td>  
		  <td><s:date name="fDate" format="yyyy-MM-dd"/></td>
		  <td><a href="<%=basePath %>receiveBill!show?receiveBill.fBillID=<s:property value="fBillID"/>"> 查看 </a> </td>
		</tr>
	  </s:iterator>
	</table>
</div>
<div class="ui-pagelist mt10 mb50">		
	<div style="float: right;" id="pager"></div>
</div>
</body>
</html>