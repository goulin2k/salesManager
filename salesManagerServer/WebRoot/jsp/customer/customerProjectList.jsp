<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>客户项目管理</title>
	<script type="text/javascript" language="javascript" src="<%=basePath %>script/common/pager/jquery.pager.js"></script>
	<link type="text/css" rel="stylesheet" href="<%=basePath %>script/common/pager/Pager.css" />
	<!-- 修改重构分页js	-->
	<script type="text/javascript" src="<%=basePath %>script/common/pages.js"></script>
	<script type="text/javascript">
		var basePath = "<%=path%>/";		
	</script>
</head>

<body class="ui-lv2bg">
	<!-- 简单风格的工具查询栏	-->	
	<nav class="navbar navbar-default crm-toolbar" role="navigation">
	    <ul class="nav navbar-nav">
	    	<li class="active"><a href="customerProject!addCustomerProjectInit">
	    		<span class="glyphicon glyphicon-star"></span>&nbsp;新增</a></li>
	    </ul>
	</nav>
	
	<div class="crm-querybar panel panel-info ">
	    <s:form id="searchForm"  name="searchForm" action="customerProject!selectCustomerProjectList"
	    		enctype="multipart/form-data"  cssClass="form-horizontal" style="margin-bottom: 2px;">
	    	<input type="hidden" id="pageNumber" name="pageNumber" value="${page.start}" />
		    <input type="hidden" id="pageCount" name="pageCount" value="${page.pageCount}" />
			<input type="hidden" id="totalCount" name="totalCount" value="${page.totalCount}" />
	      	<div class="form-group">
				<div class="col-sm-3">
					<input type="text" id="name" name="customerProject.name" 
			        	class="form-control" placeholder="项目名称" value='<s:property value="customerProject.name"/>' />
		        </div>
		        <div class="col-sm-4">
			        <input type="text" id="customerName" name="customerProject.customerName" 
			        	class="form-control" placeholder="客户名" value='<s:property value="customerProject.customerName"/>' />
		        </div>
		        <div class="col-sm-3">	
			        <input type="text" id="createUserName" name="customerProject.createUserName" 
			        	class="form-control" placeholder="项目负责人" value='<s:property value="customerProject.createUserName"/>' />
			    </div>
			    
			    <button type="submit"  class="btn btn-primary" onclick="$('#pageNumber').val(1);">搜索</button>
	      	</div>
	      		
	    </s:form>
    </div>
	 <div class="panel panel-default" id="searchResult" style="padding:2px;">
     <div class="panel-heading crm-table-title"><B>客户查询记录</B></div>
		<table class="table table-hover" cellspacing="0" cellpadding="0" >
              <thead>
                <th>客户</th>
                <th >项目名称</th>
                <th>负责人</th>
                <th >启动时间</th>
                <th >预计结束时间</th>
                <th >预计年销售额</th>
               	<th>操作</th>
              </thead>
        <!--      =================表格循环===============-->
              <s:iterator id="cp" value="customerProjectList" status="dl">
              <tr class="ui-table-style1-tr2">
                <td>${cp.customerName }</td>
                <td>${cp.name }</td>
                <td>${cp.createUserName }</td>
                <td><s:date name="#cp.startTime" format="yyyy-MM-dd" /></td>
                <td><s:date name="#cp.planEndTime" format="yyyy-MM-dd" /></td>
                <td>${cp.amount }</td>
                <td>
                	<a href="customerProject!updateCustomerProjectInit?projectId=${cp.projectId }">查看</a>
                	<!-- 
                	<a href="customerProject!deleteCustomerProject?projectId=${cp.projectId }">删除</a>
                	 -->
                </td>
              </tr> 
              </s:iterator>
          <!--     =================表格循环over===============-->
          
            </table>
            </div>


	<div class="ui-pagelist" id="pager">
    	
        
   
	</div>
	
</body>
</html>