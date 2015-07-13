<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>销售活动</title> 
<script type="text/javascript" language="javascript" src="<%=basePath %>script/common/pages.js"></script>
<script type="text/javascript" language="javascript" src="<%=basePath %>script/common/pager/jquery.pager.js"></script>
<link type="text/css" rel="stylesheet" href="<%=basePath %>script/common/pager/Pager.css" />

<script type="text/javascript">
	var basePath = "<%=path%>/";
	
	$(function(){
		$( "#startTime" ).datepicker({
			dateFormat: "yy-mm-dd"
		});
		$( "#endTime").datepicker({
			dateFormat: "yy-mm-dd"
		});
	});
	
	function getCustomer(){ 
		var array = window.showModalDialog("<%=basePath %>/customer!customerOpenWindow", "", "dialogWidth:800px; dialogHeight:530px; status:no;help:no");
		//for chrome
	    if (array == undefined) {
	    	array = window.returnValue;
	    }
		$("#customerId").val(array[0]);
		$("#customerName").val(array[1]); 
	}
</script>
</head>
<body class="ui-lv2bg">
	<!-- 标准工具栏 -->
	<nav class="navbar navbar-default crm-toolbar" role="navigation">
		<ul class="nav navbar-nav">
	    	<li class="active"><a href="activity!editNew">
	    		<span class="glyphicon glyphicon-star"></span>&nbsp;新增活动</a></li>
	    	<li class="active"><a href="javascript:deleteSearch(basePath +'/activity!index');">
	    		<span class="glyphicon glyphicon-refresh"></span>&nbsp;清空查询条件</a></li>
	    </ul>
	 </nav>	
	<!-- 查询输入栏 -->
	 <div class="crm-querybar panel panel-info ">
	    <s:form id="searchForm"  name="searchForm" action="activity!index"
	    		enctype="multipart/form-data"  cssClass="form-horizontal" style="margin-bottom: 2px;">
	      	<input type="hidden" id="pageCount" name="pageCount" value="${page.pageCount}" />
		  	<input type="hidden" id="pageNumber" name="pageNumber" value="${page.start}" />
		  	<input type="hidden" id="totalCount" name="totalCount" value="${page.totalCount}" />
	      <div class="form-group">
	      	  <div class="col-sm-4">	      	
		      	<input type="text" id="activity.topical" name="activity.topical" 
		      		 class="form-control" placeholder="活动主题">
		      </div>
		      <div class="col-sm-4">
		      	<s:select
	                        id="enumerationId"
	                        tooltip="活动类型"
	                        name="activity.enumerationId"
	                        emptyOption="true"
	                        headerKey="" headerValue="销售活动类型" 
	                        listKey="enumerationId" listValue="enumerationName" list="enumerationList"
	                        cssClass="form-control"/>
		      </div>
		      <div class="col-sm-4">
		      	<!--  <input type="hidden" id="customerId" name="activity.customerId" value="<s:property value="activity.customerId"/>" />
		      	<input id="customerName" name="activity.customerName" onClick="getCustomer();" 
		      		class="form-control"  placeholder="选择客户" value="<s:property value="activity.customerName"/>"/> 
		       	-->
		       	<input id="responseUserName" name="activity.responseUserName"  
		      		class="form-control"  placeholder="负责人" value="<s:property value="activity.responseUserName"/>"/> 
		      </div>
		   </div>
		   <div class="form-group">
		   	   <div class="col-sm-4">	      	
		      		<input class="form-control" id="startTime" name="activity.startTime" 
		      		value="<s:property value="activity.startTime"/>" placeholder="活动时间从"/>
		      	</div>	
		      	<div class="col-sm-4">	      	
		      		<input class="form-control" id="endTime" name="activity.endTime" 
		      		value="<s:property value="activity.endTime"/>" placeholder="活动时间到"/>
		      	</div>
			   <!--提交按钮	-->
			   <div class="col-sm-4" align="right">
		      		<button type="submit" class="btn btn-primary" onclick="$('#pageNumber').val(1);">查询</button>			
		      </div>
	      </div>
	   </s:form>
	</div>

 <div class="panel panel-default" id="searchResult" style="padding:2px;">
     <div class="panel-heading crm-table-title"><B>销售活动查询记录</B></div> 
	<table class="table table-hover"  cellspacing="0" cellpadding="0" >
	  <thead>
	 	<th>活动日期</th>
	 	<th>负责人</th>
        <th>活动主题</th>
        <th>对应计划</th>
        <th>对应客户</th>
        <th>活动类型</th>
        <th>活动地点</th>
	    <th>操作</th>
	  </thead>
		<!--      =================表格循环===============-->
	  <s:iterator value="activityList" status="dl">
		<tr class="ui-table-style1-tr2"> 
          <td><s:date name="activityDate" format="yyyy-MM-dd" /></td>
          <td><s:property value="responseUserName" /></td>
          <td><s:property value="topical"/></td>
          <td><a href="<%=basePath %>project!get?project.projectId=<s:property value="projectId"/>">
          		<s:property value="projectTopical"/></a></td>
          <td><a href="#" title="<s:property value='customerName'/>" class="easyui-tooltip">
		  		<s:if test="customerName!=null"><s:property value="customerName.substring(0,6)+' ...'"/></s:if></a></td>
          <td><s:property value="enumerationName"/></td>
          <td><s:property value="locAddress"/></td>
		  <td>
		  	<a href="<%=basePath %>activity!show?activity.activityId=<s:property value="activityId"/>"> 查看 </a>
		  	<s:if test="#session.USER_INFO.userId == responseUserId">
			  	<a href="<%=basePath %>activity!get?activity.activityId=<s:property value="activityId"/>"> 修改  </a>
			  	<a href="<%=basePath %>customerProject!addCustomerProjectInit?activityId=<s:property value="activityId"/>">新增项目  </a>
		  	</s:if>
		  	
		  </td>
		</tr>
	  </s:iterator>
	</table>
</div>

<div class="ui-pagelist">
		<div style="float: right;" id="pager"></div>
</div>
</body> 
</html>


