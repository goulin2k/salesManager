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
<title>联系人管理</title>

<script type="text/javascript" language="javascript" src="<%=basePath %>script/common/pager/jquery.pager.js"></script>
<script type="text/javascript" src="<%=basePath %>script/common/pages.js"></script>

<link type="text/css" rel="stylesheet" href="<%=basePath %>script/common/pager/Pager.css" />
<script type="text/javascript">
	var basePath = "<%=path%>/";
</script>
</head>
<body class="ui-lv2bg">

	<!-- 工具栏 -->
	<nav class="navbar navbar-default crm-toolbar" role="navigation">
		<ul class="nav navbar-nav">
	    	<li class="active"><a href="customerLinkman!addLinkmanInit"><span class="glyphicon glyphicon-star"></span>&nbsp;新增</a></li>
	    	<li class="active"><a href="javascript:deleteSearch(basePath + '/customerLinkman!selectCustomerLinkmanList');"><span class="glyphicon glyphicon-refresh"></span>&nbsp;清空查询条件</a></li>
	    </ul>
	 </nav>
	
	
	<!-- 查询栏 -->
	 <div class="crm-querybar panel panel-info ">
	    <s:form id="searchForm"  name="searchForm" action="customerLinkman!selectCustomerLinkmanList"
	    		enctype="multipart/form-data"  cssClass="form-horizontal" style="margin-bottom: 2px;">
	    	<input type="hidden" id="pageNumber" name="pageNumber" value="${page.start}" />
	    	<input type="hidden" id="pageCount" name="pageCount" value="${page.pageCount}" />
	      	<input type="hidden" id="totalCount" name="totalCount" value="${page.totalCount}" />
	    	<div class="form-group">
	    		<div class="col-sm-3">
		      		<input type="text" id="customerName" name="customerName"  value="${customerName}" class="form-control " placeholder="客户名">
		      	</div>
		      	<div class="col-sm-3">
		      		<input type="text" id="name" name="name"  value="${name}" class="form-control " placeholder="联系人">
		      	</div>
		      	
		      	<div class="col-sm-3">
		      		<button onclick="search();" class="btn btn-primary">查询</button>
		      	</div>
	    	</div>
	    </s:form>
    </div>
    
    <!--  查询结果列表 -->
    <div class="panel panel-default" id="searchResult" style="padding:2px;">
     <div class="panel-heading crm-table-title"><B>联系人查询记录</B></div>
		<table  class="table table-hover" cellspacing="0" cellpadding="0" >
              <thead>
              <input type="hidden" id="pageNumber" name="pageNumber" value="${page.start}" />
                <th>姓名</th>
                <th>部门</th>
                <th>所属客户</th>
                <th>传真</th>
                <th>与公司关系</th>
               <th>与竞争对手关系</th>
               <th>修改</th>
               <th>删除</th>
              </thead>
        <!--      =================表格循环===============-->
              <s:iterator id="customerLinkman" value="customerLinkmanList" status="dl">
              <tr class="ui-table-style1-tr2">
                <td>${customerLinkman.name }</td>
                <td>${customerLinkman.department }</td>
                <td>${customerLinkman.customerName }</td>
                <td>${customerLinkman.fax }</td>
                <td>${customerLinkman.relationUsName }</td>
                <td>${customerLinkman.relationCompName }</td>
                <td><a href="customerLinkman!updateLinkmanInit?linkmanId=${customerLinkman.linkmanId }">修改</a></td>
                <td><s:if test="userPositionOrgId == 2 || userPositionOrgId==1">
                		<a href="customerLinkman!deleteLinkman?linkmanId=${customerLinkman.linkmanId }">删除</a>
                	</s:if><s:else>
                		<a href="customerLinkman!updateLinkmanInit?linkmanId=${customerLinkman.linkmanId }">查看</a>
                	</s:else>
                </td>
              </tr> 
              </s:iterator>
          <!--     =================表格循环over===============-->
          
            </table>
            </div>
   <!--      =================页=、==============-->

	
	<!--  分页标签 -->
	<div class="ui-pagelist">
    	<div style="float: right;" id="pager"></div>
    </div>
   
  
</body>
</html>