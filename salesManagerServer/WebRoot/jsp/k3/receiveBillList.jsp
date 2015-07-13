<%@ page language="java" import="com.sales.model.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title> 销售收款单 </title>
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

function getCustomer(){ 
	var array = window.showModalDialog("<%=basePath %>/customer!customerOpenWindow", "", "dialogWidth:800px; dialogHeight:530px; status:no;help:no");
	//for chrome
    if (array == undefined) {
    	array = window.returnValue;
    }
	$("#customerNumber").val(array[0]);
	$("#customerName").val(array[1]); 
}

<!--  添加日期输入控件 -->
$(function() {
    $( "#startTime" ).datepicker({
    	dateFormat: "yy-mm-dd"
    });
    $( "#endTime" ).datepicker({
    	dateFormat: "yy-mm-dd"
    });
 });

function showReceiveBill(interId) {
	var winHeight = $(window).height();
	var winWidth = $(window).width();
	var returnValue = window.showModalDialog("<%=basePath %>/receiveBill!show?receiveBill.fBillID=" + interId, 
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
    	<li class="active"><a href="javascript:deleteSearch('receiveBill!list');">
    		<span class="glyphicon glyphicon-refresh"></span>&nbsp;清空查询条件</a></li>
    </ul>
</nav>

<!-- 查询输入栏 -->
<div class="crm-querybar panel panel-info ">
    <s:form id="searchForm"  name="searchForm" action="receiveBill!list"
    		cssClass="form-horizontal" style="margin-bottom: 2px;">
      <input type="hidden" id="pageCount" name="pageCount" value="${page.pageCount}" />
	  <input type="hidden" id="pageNumber" name="pageNumber" value="${page.start}" />
	  <input type="hidden" id="totalCount" name="totalCount" value="${page.totalCount}" />
      <div class="form-group">
      		<div class="col-sm-2">
	      		<input type="hidden" id="customerNumber" name="receiveBill.customerNumber" value="<s:property value="receiveBill.customerNumber"/>" />
	      		<input type="text" class="form-control" id="customerName" name="receiveBill.customerName" 
	      			onClick="getCustomer();" value="<s:property value="receiveBill.customerName"/>" placeholder="选择客户" />
	      	</div>
	      	<div class="col-sm-2">
	     		<s:select
	                       id="fEmpID" name="receiveBill.employeeNumber"
	                       tooltip="单据业务员"
	                       emptyOption="false"
	                       headerKey="" headerValue="选择业务员" 
	                       listKey="fNumber" listValue="fName" list="staffList"
	                       cssClass="form-control"/>  	
	      	</div>
	      	<div class="col-sm-2">
	      		<s:select
	                       id="fStatus" name="receiveBill.fBillStatus"
	                       tooltip="单据状态"
	                       emptyOption="false"
	                       headerKey="" headerValue="单据状态" 
	                       listKey="key" listValue="value" list="statusMap"
	                       cssClass="form-control"/> 
	      	</div>
	      	<div class="col-sm-2">
	      		<input type="text" id="startTime" name="receiveBill.startTime" value="<s:property value="receiveBill.startTime"/>"
	      			class="form-control"  placeholder="收款单时间开始" />
	      	</div>
	      	<div class="col-sm-2">
	      		<input type="text" id="endTime" name="receiveBill.endTime" value="<s:property value="receiveBill.endTime"/>"
	      			class="form-control"  placeholder="收款单时间结束" />
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
<div class="panel-heading crm-table-title"><B>销售收款查询记录</B></div>
	<table class="table table-hover" cellspacing="0" cellpadding="0" >
	  <thead>
	    <th>单据号</th>
	    <th>结算核算项目</th>  
	    <th>客户名称</th>
	    <th>部门</th>
	    <th>业务员</th>
	    
	    <th>单据金额</th>
	    <th>审核人</th>
	    <th>状态</th>  
	    <th>日期</th>
	    <th>操作</th>
	  </thead>
		<!--      =================表格循环===============-->
	  <s:iterator value="receiveBillList" status="dl">
		<tr class="ui-table-style1-tr2">
		  <td><s:property value="fNumber"/></td>
		  <td><s:property value="fItemClassID_DSPName"/></td> 
		  <td><a href="#" title="<s:property value='customerName'/>" class="easyui-tooltip">
		  		<s:property value="customerName.substring(0,6)+' ...'"/></a></td>
		  <td><s:property value="departName"/></td>
		  <td><s:property value="employeeName"/></td> 
		  
		  <td><s:property value="fAmountFor"/></td> 
		  <td><s:property value="checkerName"/></td> 
		  <td><s:property value="billStatusName"/></td>  
		  <td><s:date name="fDate" format="yyyy-MM-dd"/></td>
		  <td><a href="javascript:showReceiveBill(<s:property value="fBillID"/>);"> 查看 </a> </td>
		</tr>
	  </s:iterator>
	</table>
</div>

<div class="ui-pagelist mt10 mb50">	
	<div style="float: right;" id="pager"></div>
</div>
</body>
</html>