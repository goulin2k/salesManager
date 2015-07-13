<%@ page language="java" import="com.sales.model.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>采购入库单 </title>
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

function showStockInBill(interId) {
	var winHeight = $(window).height();
	var winWidth = $(window).width();
	var returnValue = window.showModalDialog("<%=basePath %>/stockBill!show?stockBill.fInterID=" + interId, 
			window, "dialogWidth:" + winWidth + "px; dialogHeight:" + winHeight + "; status:no;help:no;resizable:yes");
	//for chrome
	if (!returnValue) {
	    returnValue = window.returnValue;
	}
}
</script>
</head>

<body class="ui-lv2bg">
<!-- 标准工具栏 -->
<nav class="navbar navbar-default crm-toolbar" role="navigation">
	<ul class="nav navbar-nav">
    	<li class="active"><a href="javascript:deleteSearch('stockBill!list');">
    		<span class="glyphicon glyphicon-refresh"></span>&nbsp;清空查询条件</a></li>
    </ul>
</nav>

<!-- 查询输入栏 -->
 <div class="crm-querybar panel panel-info ">
    <s:form id="searchForm"  name="searchForm" action="stockBill!list"
    		cssClass="form-horizontal" style="margin-bottom: 2px;">
      <input type="hidden" id="pageCount" name="pageCount" value="${page.pageCount}" />
	  <input type="hidden" id="pageNumber" name="pageNumber" value="${page.start}" />
	  <input type="hidden" id="totalCount" name="totalCount" value="${page.totalCount}" />
      <div class="form-group">
	      	<div class="col-sm-2">
	     		<s:select
	                       id="fEmpID"
	                       tooltip="业务员"
	                       name="stockBill.fEmpID"
	                       emptyOption="false"
	                       headerKey="" headerValue="选择业务员" 
	                       listKey="staffId" listValue="name" list="staffList"
	                       cssClass="form-control"/>      	
	      	</div>
	      	<div class="col-sm-2">
	      		<s:select
	                       id="fStatus" name="stockBill.fStatus"
	                       tooltip="申请状态"
	                       emptyOption="false"
	                       headerKey="" headerValue="单据状态" 
	                       listKey="key" listValue="value" list="statusMap"
	                       cssClass="form-control"/> 
	      	</div>
	      	<div class="col-sm-3">
	      		<input type="text" id="startTime" name="stockBill.startTime" value="<s:property value="stockBill.startTime"/>"
	      			class="form-control"  placeholder="入库时间开始" />
	      	</div>
	      	<div class="col-sm-3">
	      		<input type="text" id="endTime" name="stockBill.endTime" value="<s:property value="stockBill.endTime"/>"
	      			class="form-control"  placeholder="入库时间结束" />
	      	</div>
	      	<!--提交按钮	-->
		   	<div class="col-sm-2" align="left">
	      		<button type="submit" class="btn btn-primary">查询</button>			
	      	</div>
	  </div>	
	</s:form>
</div>

<!--  查询结果列表 -->
<div class="panel panel-default" id="searchResult" style="padding:2px;">
<div class="panel-heading crm-table-title"><B>采购入库单查询记录</B></div>
	<table class="table table-hover" cellspacing="0" cellpadding="0" >
	  <thead>	  
	    <th>单据编号</th>
	    <th>部门</th>  
	    <th>业务员</th>
	    <th>制单人</th>  
	    <th>单据状态</th>
	    <th>源单类型</th>
	    <th>日期</th> 
	    <th>操作</th>
	  </thead>
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
		  <td><a href="javascript:showStockInBill(<s:property value="fInterID"/>);"> 查看 </a> </td>
		</tr>
	  </s:iterator>
	</table>
</div>
<!--  列表分页标签 -->
<div class="ui-pagelist mt10 mb50">	
	<div style="float: right;" id="pager"></div>
</div>
</body>
</html>