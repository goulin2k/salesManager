<%@ page language="java" import="com.sales.model.*" contentType="text/html; charset=UTF-8"%>
<%@page import="com.thoughtworks.xstream.io.path.Path"%>
<%@ taglib prefix="s" uri="/struts-tags"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title> 销售计划列表 </title>
	 
	<script type="text/javascript" src="<%=basePath %>script/common/pager/jquery.pager.js"></script>
	<script type="text/javascript" language="javascript" src="<%=basePath %>script/common/pages.js"></script>
	<link type="text/css" rel="stylesheet" href="<%=basePath %>script/common/pager/Pager.css" />
	<script type="text/javascript">
		var basePath = "<%=path%>/";
		function getCustomer(){ 
			var array = window.showModalDialog("<%=basePath %>/customer!customerOpenWindow", "", "dialogWidth:800px; dialogHeight:530px; status:no;help:no");
			//for chrome
		    if (array == undefined) {
		    	array = window.returnValue;
		    }
			$("#customerId").val(array[0]);
			$("#customerName").val(array[1]); 
		} 
		
		function deleteSearch() {	//清除查询条件
			window.location.href = basePath + '/project!index';
		}
		
		$(document).ready(function(){      
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
	    	<li class="active"><a href="project!editNew"><span class="glyphicon glyphicon-star"></span>&nbsp;新增</a></li>
	    	<li class="active"><a href="javascript:deleteSearch(basePath + '/project!index');">
	    		<span class="glyphicon glyphicon-refresh"></span>&nbsp;清空查询条件</a></li>
	    </ul>
	 </nav>
	 
	 <!-- 查询输入栏 -->
	 <div class="crm-querybar panel panel-info ">
	    <s:form id="searchForm"  name="searchForm" action="project!index"
	    		enctype="multipart/form-data"  cssClass="form-horizontal" style="margin-bottom: 2px;">
	      <input type="hidden" id="pageCount" name="pageCount" value="${page.pageCount}" />
		  <input type="hidden" id="pageNumber" name="pageNumber" value="${page.start}" />
		  <input type="hidden" id="totalCount" name="totalCount" value="${page.totalCount}" />
	      <div class="form-group">
	      	<div class="col-sm-3">	      	
		      	<input type="text" id="project.topical" name="project.topical" value="<s:property value="project.topical"/>" class="form-control" placeholder="计划主题">
		      </div>
		      <div class="col-sm-3">
		      	<s:select
	                        id="enumerationId"
	                        tooltip="计划类型"
	                        name="project.enumerationId"
	                        emptyOption="false"
	                        headerKey="" headerValue="选择计划类型" 
	                        listKey="enumerationId" listValue="enumerationName" list="enumerationList" 
	                        cssClass="form-control"/>
		      </div>
		      <div class="col-sm-3">
		      	<input id="createUserName" name="project.createUserName"  
		      		class="form-control"  placeholder="下达人" value="<s:property value="project.createUserName"/>"/> 
		      </div>
		      <div class="col-sm-3">
		      	<input id="responseUserName" name="project.responseUserName"  
		      		class="form-control"  placeholder="负责人" value="<s:property value="project.responseUserName"/>"/> 
		      </div>
		   </div>
		   <div class="form-group">
		      <div class="col-sm-3" >	 
		      	<input class="form-control" placeholder="开始日期" id="startTime" name="project.startTime" value="<s:property value="project.startTime"/>"/>
		      </div>  
		      <div class="col-sm-3" >   
        		<input class="form-control" placeholder="结束日期" id="endTime" name="project.endTime" value="<s:property value="project.endTime"/>"/>
		      </div>
		      <div class="col-sm-3" >	 
		      	<input class="form-control" placeholder="督办人" id="assessUser" name="assessUser" value="<s:property value="assessUser"/>"/>
		      </div>
		      <div class="col-sm-2" >	 
		      	<select  class="form-control" id="status" name="project.status" value="<s:property value="project.status"/>">
		      		<option value="1" >提交状态</option>
		      		<option value="0" <s:if test="project.status==0">selected</s:if>>暂存（本人）</option>
		      	</select>
		      </div>   
		      <!--提交按钮	-->
			   <div class="col-sm-1">
		      		<button type="submit" class="btn btn-primary" onclick="$('#pageNumber').val(1);">查询</button>			
		      </div>
		   </div>
		   
		   
	   </s:form>
	</div>

<!--  查询结果列表 -->
    <div class="panel panel-default" id="searchResult" style="padding:2px;">
     	<div class="panel-heading crm-table-title"><B>销售计划列表</B></div>
		<table  class="table table-hover" cellspacing="0" cellpadding="0" >
			 <thead>
			   <th >创建时间</th>	  
			   <th >主题</th>
			   <th >计划类型</th>
			   <th >对应客户</th> 
			   <th >客户等级</th>
			   <th >下达人</th>
			   <th >负责人</th> 
			   <th >开始时间</th>
			   <th >结束时间</th> 
			   <th >督办条数</th>
			   <th >操作</th>
			 </thead>
			<!--      =================表格循环===============-->
			  <s:iterator value="projectList" status="dl">
				<tr class="ui-table-style1-tr2">
				  <td><s:date  name="createTime" format="yyyy-MM-dd HH:mm"/></td>
				  <td><s:property value="topical"/></td>
				  <td><s:property value="enumerationName"/></td>
				  <td><a href="#" title="<s:property value='customerName'/>" class="easyui-tooltip">
				  		<s:if test="customerName!=null"><s:property value="customerName.substring(0,6)+' ...'"/></s:if></a></td>
				  <td><s:property value="customerLevel"/></td>
				  <td><s:property value="createUserName"/></td>
				  <td><s:property value="responseUserName"/></td>
				  <td><s:property value="startTime"/></td>
				  <td><s:property value="endTime"/></td>
				   <td><s:property value="assessListCount"/></td>
				  <td>
				  <s:if test='projectRole == 1'>
					  <a href="<%=basePath %>project!get?project.projectId=<s:property value="projectId"/>"> 查看  </a>
					  <a href="<%=basePath %>activity!editNew?projectId=<s:property value="projectId"/>"> 活动 </a>
				  </s:if>
				  <s:elseif test='projectRole == 2'>
				  	  <a href="<%=basePath %>project!show?project.projectId=<s:property value="projectId"/>"> 查看 </a> 
					  <a href="<%=basePath %>activity!editNew?projectId=<s:property value="projectId"/>"> 活动 </a> 
				  </s:elseif>
				  <s:elseif test='projectRole == 3'>
				  	  <a href="<%=basePath %>project!show?project.projectId=<s:property value="projectId"/>"> 查看 </a> 
				  </s:elseif> 
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