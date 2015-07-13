<%@ page language="java" import="com.sales.model.*, com.sales.common.Constants" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title> 报价单列表 - 2 </title>
<script type="text/javascript" language="javascript" src="<%=basePath %>script/common/pager/jquery.pager.js"></script>
<link type="text/css" rel="stylesheet" href="<%=basePath %>script/common/pager/Pager.css" />
<script type="text/javascript" src="<%=basePath %>script/common/pages.js"></script>

<script type="text/javascript">
var basePath = "<%=path%>/";
var winHeight = $(window).height();
var winWidth = $(window).width();

$(function(){
	$( "#startTime" ).datepicker({
		dateFormat: "yy-mm-dd"
	});
	$( "#endTime" ).datepicker({
		dateFormat: "yy-mm-dd"
	});
});

function getCustomer(){ 
	var array = window.showModalDialog("<%=basePath %>/customer!customerOpenWindow", "", "dialogWidth:800px; dialogHeight:530px; status:no;help:no");
	//for chrome
    if (array == undefined) {
    	array = window.returnValue;
    }
	$("#fCustID").val(array[0]);
	$("#customerName").val(array[1]);  
}

function deleteSearch() {	//清除查询条件
	window.location.href = basePath + '/orfq!index';
}

function showOrfq(interId) {
	var returnValue = window.showModalDialog("<%=basePath %>/orfq!show?orfq.fInterID=" + interId, 
			window, "dialogWidth:" + winWidth + "px; dialogHeight:" + winHeight + "; status:no;help:no;resizable:yes");
	//for chrome
	if (!returnValue) {
	    returnValue = window.returnValue;
	}
	
	//window.location.reload();
}
</script> 
</head>

<body class="ui-lv2bg">
<nav class="navbar navbar-default crm-toolbar" role="navigation">
	<ul class="nav navbar-nav"> 
    	<li class="active"><a href="orfq!index?verifyName=2"><span class="glyphicon glyphicon-refresh"></span>&nbsp;清空查询条件</a></li>
    </ul>
</nav>
<!-- 查询输入栏 -->
 <div class="crm-querybar panel panel-info ">
    <s:form id="searchForm"  name="searchForm" action="orfq!index"
    		enctype="multipart/form-data"  cssClass="form-horizontal" style="margin-bottom: 2px;">
      	<input type="hidden" id="pageCount" name="pageCount" value="${page.pageCount}" />
	  	<input type="hidden" id="pageNumber" name="pageNumber" value="${page.start}" />
	  	<input type="hidden" id="totalCount" name="totalCount" value="${page.totalCount}" />
      <div class="form-group">
      	<div class="col-sm-3">	      	
	      	<s:select
                        id="verifyName"
                        tooltip="审核状态"
                        name="verifyName"
                        emptyOption="true"
                        headerKey="" headerValue="审核状态" 
                        listKey="key" listValue="value" list="verifyMap" 
                        cssClass="form-control"/>
	    	</div>
	      <div class="col-sm-3">
	      		<input type="hidden" id="fCustID" name="orfq.fCustID" value="<s:property value="orfq.fCustID"/>">
	      		<input type="text" id="customerName" 
	      			name="orfq.customerName" onClick="getCustomer();" value="<s:property value="orfq.customerName"/>" 
	      			class="form-control"  placeholder="选择客户" />
	      </div>
	      <div class="col-sm-3">
	      		<s:select
                        id="fEmpID"
                        tooltip="业务员"
                        name="orfq.fEmpID"
                        emptyOption="true"
                        headerKey="" headerValue="选择业务员" 
                        listKey="staffId" listValue="name" list="staffList" 
                        cssClass="form-control"/>
	      </div>
	      
	   </div>
	   <div class="form-group">
	   		<div class="col-sm-3">
	      		<s:select
                        id="fStatus"
                        tooltip="单据状态"
                        name="orfq.fStatus"
                        emptyOption="false"
                        headerKey="" headerValue="单据状态" 
                        listKey="key" listValue="value" list="statusMap"
                        cssClass="form-control"/>
	      </div>
	      <div class="col-sm-3">
	      		<input type="text" id="startTime" name="orfq.startTime" value="<s:property value="orfq.startTime"/>"
	      			class="form-control"  placeholder="开始时间" />
	      </div>
	      <div class="col-sm-3">
	      		<input type="text" id="endTime" name="orfq.endTime" value="<s:property value="orfq.endTime"/>"
	      			class="form-control"  placeholder="结束时间" />
	      </div>
	      
		   <!--提交按钮	-->
		   <div class="col-sm-3" align="right">
	      		<button type="submit" class="btn btn-primary">查询</button>			
	      </div>
      </div>
   </s:form>
</div>

<!--  查询结果列表 -->
<div class="panel panel-default" id="searchResult" style="padding:2px;">
     <div class="panel-heading crm-table-title"><B>报价单查询记录</B></div>
	<table  class="table table-hover" cellspacing="0" cellpadding="0" >
	  <thead>	   
	    <th>对应客户</th>  
	    <th>部门</th>
	    <th>业务员</th>  
	    <th>审核人</th>
	    <th>日期</th>
	    <th>一级审核</th>
	    
	    <th>报价单号</th>
	    <th>询价单号</th>
	    <th>操作</th>
	  </thead>
	  <!--      =================表格循环已有的物料列表===============-->

	  <s:iterator value="orfqList" status="dl">
		<tr class="ui-table-style1-tr2"> 
		  <td><a href="#" title="<s:property value='customerName'/>" class="easyui-tooltip">
		  		<s:property value="customerName.substring(0,6)+' ...'"/></a></td>  
		  <td><s:property value="departName"/></td>
		  <td><s:property value="employeeName"/></td> 
		  <td><s:property value="checkerName"/></td>
		  <td><s:date name="fdate" format="yyyy-MM-dd HH:mm:ss"/></td>
		  <td><s:property value="verifyStatusName"/></td> 
		  
		  <td><s:property value="fBillNo"/></td>
		  <td><s:property value="quotationCode"/></td>
		  <td><a href="javascript:showOrfq(<s:property value="fInterID"/>);"> 查看 </a></td>
		</tr>
	  </s:iterator>
	</table>
</div>
<div class="ui-pagelist mt10 mb50">	
	<div style="float: right;" id="pager"></div>
</div>
<script type="text/javascript"> 
$(function(){
	$( "#startTime" ).datepicker({
		dateFormat: "yy-mm-dd"
	});
	$( "#endTime" ).datepicker({
		dateFormat: "yy-mm-dd"
	});
}); 
</script> 
</body>
</html>