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
<title> 销售订单列表 </title>

	
	<!-- 日期选择	-->
	<link type="text/css" href="<%=basePath %>skin/Default/css/jquery-ui-1.10.0.custom.css" rel="stylesheet"  />
	<!-- Le HTML5 shim, for IE6-8 support of HTML elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
	<script type="text/javascript" language="javascript" src="<%=basePath %>script/common/jquery-ui-1.10.0.custom.min.js"></script>
	
	<!-- 修改重构分页js	-->
	<script type="text/javascript" language="javascript" src="<%=basePath %>script/common/pager/jquery.pager.js"></script>
	<script type="text/javascript" src="<%=basePath %>script/common/pages.js"></script>
	<link type="text/css" rel="stylesheet" href="<%=basePath %>script/common/pager/Pager.css" />
	
<script type="text/javascript">
var basePath = "<%=path%>/";


function getCustomer(){ 
	var array = window.showModalDialog("<%=basePath %>/customer!customerOpenWindow", "", "dialogWidth:800px; dialogHeight:530px; status:no;help:no");
	//for chrome
    if (array == undefined) {
    	array = window.returnValue;
    }
	$("#fCustID").val(array[0]);
	$("#customerName").val(array[1]);  
}

function showOrder(interId) {
	var winHeight = $(window).height();
	var winWidth = $(window).width();
	var returnValue = window.showModalDialog("<%=basePath %>/order!show?order.fInterID=" + interId, 
			window, "dialogWidth:" + winWidth + "px; dialogHeight:" + winHeight + "; status:no;help:no;resizable:yes");
	//for chrome
	if (!returnValue) {
	    returnValue = window.returnValue;
	}
}

$(function(){
	$( "#startTime" ).datepicker({
		dateFormat: "yy-mm-dd"
	});
	$( "#endTime" ).datepicker({
		dateFormat: "yy-mm-dd"
	});
});
</script>
</head>

<body class="ui-lv2bg">
	<!-- 标准工具栏 -->
	<nav class="navbar navbar-default crm-toolbar" role="navigation">
		<ul class="nav navbar-nav">
	    	<li class="active"><a href="javascript:deleteSearch('order!index');"><span class="glyphicon glyphicon-refresh"></span>&nbsp;清空查询条件</a></li>
	    </ul>
	</nav>
		
<!-- 查询输入栏 -->
 <div class="crm-querybar panel panel-info ">
    <s:form id="searchForm"  name="searchForm" action="order!index"
    		cssClass="form-horizontal" style="margin-bottom: 2px;">
      <input type="hidden" id="pageCount" name="pageCount" value="${page.pageCount}" />
	  <input type="hidden" id="pageNumber" name="pageNumber" value="${page.start}" />
	  <input type="hidden" id="totalCount" name="totalCount" value="${page.totalCount}" />
      <div class="form-group">
	      	<div class="col-sm-4">
	     		 <input type="hidden" id="fCustID" name="order.fCustID" value="<s:property value="order.fCustID"/>" />
	     		 <input type="text" class="form-control" id="customerName" name="order.customerName" 
	     		 	onClick="getCustomer();" value="<s:property value="order.customerName"/>" placeholder="客户选择"/>	      	
	      	</div>
	      	<div class="col-sm-4">
	      		<s:select
	                       id="fEmpID"
	                       tooltip="订单业务员"
	                       name="order.fEmpID"
	                       emptyOption="false"
	                       headerKey="" headerValue="选择业务员" 
	                       listKey="staffId" listValue="name" list="staffList"
	                       cssClass="form-control"/>
	      	</div>
	      	<div class="col-sm-4">
	      		<s:select
	                       id="fStatus"
	                       tooltip="单据状态"
	                       name="order.fStatus"
	                       emptyOption="false"
	                       headerKey="" headerValue="选择单据状态" 
	                       listKey="key" listValue="value" list="statusMap"
	                       cssClass="form-control"/>
	      	</div>
	  </div>
	  <div class="form-group"> 
	  		<div class="col-sm-4">
	      		<input type="text" id="startTime" name="order.startTime" 
	      			value="<s:property value="order.startTime"/>"
	      			class="form-control"  placeholder="开始时间" />
	      	</div>
	      	<div class="col-sm-4">
	      		<input type="text" id="endTime" name="order.endTime" value="<s:property value="order.endTime"/>"
	      			class="form-control"  placeholder="结束时间" />
	      	</div>
		   	<!--提交按钮	-->
		   	<div class="col-sm-4" align="left">
	      		<button type="submit" class="btn btn-primary">查询</button>			
	      	</div>
	   </div>
   </s:form>
</div>
	
<!--  查询结果列表 -->
<div class="panel panel-default" id="searchResult" style="padding:2px;">
     <div class="panel-heading crm-table-title"><B>订单查询记录</B></div>
		<table  class="table table-hover" cellspacing="0" cellpadding="0" >
		  <thead>	  
		    <th >订单编号</th>
		    <th >购货单位</th>  
		    <th >部门</th>
		    <th >业务员</th>  
		    <th >审核人</th>
		    <th >日期</th>
		    <th >是否关闭</th>
		    <th >状态</th>
		    <th >操作</th>
		  </thead>
			<!--      =================表格循环===============-->
		  <s:iterator value="orderList" status="dl">
			<tr class="ui-table-style1-tr2">
			  <td><s:property value="fBillNo"/></td>
			  <td><a href="#" title="<s:property value='customerName'/>" class="easyui-tooltip">
			  		<s:property value="customerName.substring(0,6)+' ...'"/></a></td>  
			  <td><s:property value="departName"/></td>
			  <td><s:property value="employeeName"/></td> 
			  <td><s:property value="checkerName"/></td>
			  <td><s:date name="fDate" format="yyyy-MM-dd"/></td>
			  <td><s:property value="closedName"/></td> 
			  <td><s:property value="statusName"/></td> 
			  <td><a href="javascript:showOrder(<s:property value='fInterID'/>);"> 查看 </a> </td>
			</tr>
		  </s:iterator>
		</table>
</div>
<div class="ui-pagelist mt10 mb50">
	<div style="float: right;" id="pager"></div>
</div>
</body>
</html>