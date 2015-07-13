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
<title>客户分配人列表</title>

<script type="text/javascript" language="javascript" src="<%=basePath %>script/common/pager/jquery.pager.js"></script>
<link type="text/css" rel="stylesheet" href="<%=basePath %>script/common/pager/Pager.css" />
<script type="text/javascript" language="javascript" src="<%=basePath %>script/common/pages.js"></script>
<script type="text/javascript">
	var basePath = "<%=path%>/";
</script>
</head>
<body class="ui-lv2bg">
	<!-- 简单风格的工具查询栏	-->	
	<nav class="navbar navbar-default crm-toolbar" role="navigation">
		<ul class="nav navbar-nav">
	    	<li class="active"><a href="customer!addCOUserIndex"><span class="glyphicon glyphicon-star"></span>&nbsp;分配客户</a></li>
	    	<li class="active"><a href="javascript:deleteSearch(basePath + '/customer!selectCOUList');">
	    		<span class="glyphicon glyphicon-refresh"></span>&nbsp;清空查询条件</a></li>
	    </ul>
	 </nav>
	 
   <!-- 查询输入栏 -->
	 <div class="crm-querybar panel panel-info ">
	    <s:form id="searchForm"  name="searchForm" action="customer!selectCOUList"
	    		enctype="multipart/form-data"  cssClass="form-horizontal" style="margin-bottom: 2px;">
	      <input type="hidden" id="pageCount" name="pageCount" value="${page.pageCount}" />
			<input type="hidden" id="pageNumber" name="pageNumber" value="${page.start}" />
			<input type="hidden" id="totalCount" name="totalCount" value="${page.totalCount}" />
	      <div class="form-group">
	      	<div class="col-sm-3">	      	
		      	<input type="text" id="couser.customerName" name="couser.customerName"  
		        	value="<s:property value="couser.customerName"/>" class="form-control" placeholder="客户名称">
		      </div>
		      <div class="col-sm-3">
		      	<input type="text" id="couser.userName" name="couser.userName"  
		        	value="<s:property value="couser.userName"/>" class="form-control " placeholder="业务员">
		      </div>
		      <div class="col-sm-3">
		      	<input id="couser.salegenName" name="couser.salegenName"  
		        	value="<s:property value="couser.salegenName"/>" class="form-control"  placeholder="(副)总经理">
		      </div>
		      <div class="col-sm-3">
		      	<input id="couser.finmanagerName" name="couser.finmanagerName"  
		        	value="<s:property value="couser.finmanagerName"/>" class="form-control " placeholder="部门经理"/>
		      </div>    
		      
		   </div>
		   <div class="form-group">
		   <div class="col-sm-3">
		      	<input id="couser.customerNumber" name="couser.customerNumber"  
		        	value="<s:property value="couser.customerNumber"/>" class="form-control " placeholder="客户编码"/>
		      </div>
		   		<div class="col-sm-3" >	      	
		      	<s:select
	                        id="oweredStatus"
	                        tooltip="分配状态"
	                        name="oweredStatus"
	                        emptyOption="false"
	                        listKey="enumerationId" listValue="enumerationName" list="oweredStatusList" 
	                        cssClass="form-control"/>
		      	
		      </div>
			   <!--提交按钮	-->
			   <div class="col-sm-offset-6 col-sm-3">
		      		<button type="submit" class="btn btn-primary">查询</button>			
		      </div>
		   </div>
	   </s:form>
	</div>

    
 
   <!--  查询结果列表 -->
    <div class="panel panel-default" id="searchResult" style="padding:2px;">
     <div class="panel-heading crm-table-title"><B>客户查询记录</B></div>
		<table class="table table-hover" cellspacing="0" cellpadding="0" >
              <thead>
              	<input type="hidden" id="pageNumber" name="pageNumber" value="${page.start}" />
                <th>客户名称</th>
                <th>总经理(副)</th>
                <th>部门经理</th>
                <th>业务员</th>
               <th>分配</th>
               <th>修改</th>
               <th>历史分配记录</th>
              </thead>
        <!--      =================表格循环===============-->
              <s:iterator id="cou" value="couList" status="dl">
              <tr class="ui-table-style1-tr2">
                <td>${cou.customerName }</td>
                <td>${cou.salegenName }</td>
                <td>${cou.finmanagerName }</td>
                <td>${cou.userName }</td>
                <td>
                	<s:if test="userId==null">
                		<a href="customer!addCOUserIndex?customerId=${cou.customerId }&customerName=${cou.customerName }">分配</a>
                	</s:if>
                	<s:if test="userId!=null">
                		<a href="customer!deleteCOUser?couId=${cou.customerOwnerUserId }&userId=${cou.userId }">取消分配</a>
                	</s:if>
                	
                </td>
                <td>
                	<s:if test="userId==null">
                		修改
                	</s:if>
                	<s:if test="userId!=null">
                		<a href="customer!updateCOUserIndex?couId=${cou.customerOwnerUserId }&customerId=${cou.customerId }&customerName=${cou.customerName }
                		&userId=${cou.userId }&userName=${cou.userName }&finmanagerId=${cou.finmanagerId }&finmanagerName=${cou.finmanagerName }
                		&salegenId=${cou.salegenId }&salegenName=${cou.salegenName }">修改</a>
                	</s:if>
                	
                </td>
                <td><a href="customer!selectHistoryCOUList?customerId=${cou.customerId }">历史分配记录</a></td>
              </tr> 
              </s:iterator>
          <!--     =================表格循环over===============-->
          
            </table>
   </div>
   <!--      =================页=、==============-->


	<div class="ui-pagelist" id="pager">
    </div>

	
</body>
</html>