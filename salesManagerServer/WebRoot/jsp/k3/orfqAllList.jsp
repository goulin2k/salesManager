<%@ page language="java" import="com.sales.model.*, com.sales.common.Constants" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sales" uri="/WEB-INF/sales-util.tld"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title> 报价单列表 - 3 </title>
<script type="text/javascript" src="<%=basePath %>script/common/jquery-1.4.2.min.js" ></script>
<script type="text/javascript" src="<%=basePath %>script/common/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath %>script/common/easyui/plugins/jquery.parser.js"></script>
<script type="text/javascript" src="<%=basePath %>script/common/easyui/validate.js"></script>
<script type="text/javascript" src="<%=basePath %>script/common/easyui/plugins/jquery.combotree.js"></script>
<script type="text/javascript" src="<%=basePath %>script/common/easyui/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath %>script/common/easyui/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>script/common/easyui/themes/default/easyui.css" />
<link rel="stylesheet" href="<%=basePath %>script/common/autocomplete/jquery.autocomplete.css" type="text/css"></link>
<script type="text/javascript" src="<%=basePath %>script/common/autocomplete/jquery.autocomplete.js" ></script>
<link href="<%=basePath %>skin/Default/css/base.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>skin/Default/css/global.css" rel="stylesheet" type="text/css" />  
</head>

<body>
<div class="ui-table-style1">
	<table  cellspacing="0" cellpadding="0" >
	  <tr class="ui-table-style1-tr1">	   
	    <td class="td2">报价单号</td>
	    <td class="td5">对应客户</td>  
	    <td class="td5">部门</td>
	    <td class="td4">业务员</td>  
	    <td class="td3">审核人</td>
	    <td class="td4">日期</td>
	    <td class="td3">状态</td>
	    <td class="td2">操作</td>
	  </tr>
		<!--      =================表格循环===============-->
	  <s:iterator value="orfqList" status="dl">
		<tr class="ui-table-style1-tr2">
		  <td><s:property value="fBillNo"/></td>
		  <td><a href="#" title="<s:property value='customerName'/>" class="easyui-tooltip">
		  		<s:property value="customerName.substring(0,6)+' ...'"/></a></td>   
		  <td><s:property value="departName"/></td>
		  <td><s:property value="employeeName"/></td> 
		  <td><s:property value="checkerName"/></td>
		  <td><s:date name="fdate" format="yyyy-MM-dd"/></td>
		  <td><s:property value="statusName"/></td> 
		  <td><a href="<%=basePath %>orfq!show?orfq.fInterID=<s:property value="fInterID"/>"> 查看 </a> </td>
		</tr>
	  </s:iterator>
	</table>
</div>
<div class="ui-pagelist">	
	<div style="float: right;" id="pager"></div>
</div>
</body>
</html>