<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.thoughtworks.xstream.io.path.Path"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>客户管理</title>

<script type="text/javascript" language="javascript" src="<%=basePath %>script/common/pager/jquery.pager.js"></script>
<script type="text/javascript" src="<%=basePath %>script/common/pages.js"></script>

<link type="text/css" rel="stylesheet" href="<%=basePath %>script/common/pager/Pager.css" />
<script type="text/javascript">
	var basePath = "<%=path%>/";
			
	
	function loadfromk3() {
		$("#loadK3Data").val("1");
		$("#pageNumber").val("1");
		$("#searchForm").submit();
	}
	
	
	
</script>
</head>
<body class="ui-lv2bg">
	<!-- 工具栏 -->
	<nav class="navbar navbar-default crm-toolbar" role="navigation">
		<ul class="nav navbar-nav">
	    	<li class="active"><a href="javascript:loadfromk3();"><span class="glyphicon glyphicon-play"></span>&nbsp;同步K/3</a></li>
	    	<li class="active"><a href="javascript:deleteSearch(basePath + '/customer!selectCustomerList');"><span class="glyphicon glyphicon-refresh"></span>&nbsp;清空查询条件</a></li>
	    </ul>
	 </nav>
	 
	 <!-- 查询栏 -->
	 <div class="crm-querybar panel panel-info ">
	    <s:form id="searchForm"  name="searchForm" action="customer!selectCustomerList"
	    		enctype="multipart/form-data"  cssClass="form-horizontal" style="margin-bottom: 2px;">
	      <input type="hidden" id="pageCount" name="pageCount" value="${page.pageCount}" />
	      <input type="hidden" id="pageNumber" name="pageNumber" value="${page.start}" />
	      <input type="hidden" id="totalCount" name="totalCount" value="${page.totalCount}" />
	      <input type="hidden" id="loadK3Data" name="loadK3Data" value="0" />
	      <div class="form-group">
	      	<div class="col-sm-3">	      	
		      	<input type="text" id="fname" name="fname" value="${fname}" class="form-control" placeholder="客户名称">
		      </div>
		      <div class="col-sm-2">
		      	<input type="text" id="fnumber" name="fnumber"  value="${fnumber}" class="form-control " placeholder="客户代码">
		      </div>
		      <div class="col-sm-2">
		      	<s:textfield id="tcustomer.cuserName" name="tcustomer.cuserName" cssClass="form-control"  placeholder="关注人" />
		      </div>
		      <div class="col-sm-2">
		      	<s:textfield id="tcustomer.couserName" name="tcustomer.couserName"  cssClass="form-control " placeholder="分配人"/>
		      </div>
		      <div class="col-sm-3" >	      	
		      	<s:select
	                        id="customerLevel"
	                        tooltip="客户等级"
	                        name="tcustomer.customerLevel"
	                        emptyOption="false"
	                        headerKey="" headerValue="选择客户等级" 
	                        listKey="enumerationId" listValue="enumerationName" list="enumerationList" 
	                        cssClass="form-control"/>
		      	
		      </div>
		   </div>
		   <div class="form-group">
	      
	      <div class="col-sm-3">
	      	<s:select
                        id="linkmanWriteStatus"
                        tooltip="联系人填写状态"
                        name="tcustomer.linkmanWriteStatus"
                        emptyOption="false"
                        headerKey="" headerValue="联系人填写状态" 
                        listKey="enumerationId" listValue="enumerationName" list="linkmanWriteStatusList"
                        cssClass="form-control"/>
	      </div>
	      <div class="col-sm-2">
	      	<s:select
                        id="evaFinGenId"
                        tooltip="选择财务评价等级"
                        name="tcustomer.evaFinGenId"
                        emptyOption="false"
                        headerKey="" headerValue="财务评价等级（副总）" 
                        listKey="enumerationId" listValue="enumerationName" list="evaSalegenList"
                        cssClass="form-control"/>
	      </div>
	      <div class="col-sm-2">
	      	<s:select
	      				id="evaFinSalemagagerId"
                        tooltip="选择财务评价等级"
                        name="tcustomer.evaFinSalemagagerId"
                        emptyOption="false"
                        headerKey="" headerValue="财务经理评价等级" 
                        listKey="enumerationId" listValue="enumerationName" list="evaSalemanagerList"
                        cssClass="form-control"/>
	      </div>
	      <div class="col-sm-2">
	      	<s:select
	      				id="evaFinManagerId"
                        tooltip="选择信用评价等级"
                        name="tcustomer.evaFinManagerId"
                        emptyOption="false"
                        headerKey="" headerValue="信用评价等级" 
                        listKey="enumerationId" listValue="enumerationName" list="evaCreditList"
                        cssClass="form-control"/>
	      </div>
	      <div class="col-sm-3">
	      		<button type="submit" onclick="$('#pageNumber').val(1);" class="btn btn-primary">查询</button>
	      			
	      </div>
	      </div>
	    </s:form>
    </div>
     
           
     
	<!--  查询结果列表 -->
    <div class="panel panel-default" id="searchResult" style="padding:2px;">
     <div class="panel-heading crm-table-title"><B>客户查询记录</B></div>
	 
		<table  class="table table-hover" cellspacing="0" cellpadding="0" >
              <thead>
                <tr>
                <th>客户名称</th>
                <th>客户编码</th>
                <th>客户简码</th>
                <th>联系人</th>
                <th>联系电话</th>
                <th>资料完整率</th>
              </tr>
              </thead>
              <tbody>
        <!--      =================表格循环===============-->
              <s:iterator id="customer" value="customerList" status="dl">
              <tr class="ui-table-style1-tr2">
                <td><a href="customer!getCustomerInfoById?customerId=${customer.customerId }">${customer.ffullname }</a></td>
                <td>${customer.fnumber }</td>
                <td>${customer.fshortnumber }</td>
                <td>${customer.fcontact }</td>
                <td>${customer.fphone }</td>
                <td>
                	<s:if test="rateOfFieldNotNull < 0.5 "><font color="red"></s:if>
                	<s:else><font coluor="black"></s:else>
                	${customer.percentOfFieldNotNull }</font></td>
              </tr> 
              </s:iterator>
          <!--     =================表格循环over===============-->
          	</tbody>
            </table>
     
     
  </div>
	<!-- 分页标签 -->
	 <div class="ui-pagelist">
		<div style="float: right;" id="pager"></div>
	</div>
</body>
</html>