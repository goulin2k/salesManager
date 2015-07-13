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
<title>考核项目管理</title>
<script type="text/javascript" src="script/common/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" language="javascript" src="<%=basePath %>script/common/pager/jquery.pager.js"></script>
<link type="text/css" rel="stylesheet" href="<%=basePath %>script/common/pager/Pager.css" />
<script type="text/javascript" src="<%=basePath %>script/common/pages.js"></script>
<script type="text/javascript">

	function search(){
		$("#pageNumber").val("1");
		pagesearch();
	}
	
	function pagesearch(){
		itemForm.submit();
	}

	function deleteSearch() {	//清除查询条件
		window.location.href = basePath + 'performance!itemList';
	}

	function deleteItem(itemId) {
		$.messager.confirm('考核项目管理', '确认删除?', function(r){

			if (r){
				$.get("performance!deleteItem", {'itemId': itemId}, function(data) {

					if (data.jsonReturn == "SUCCESS") {
						  //$.messager.alert('考核项目管理','删除成功','info');
						  search();
					}
					else if (data.jsonReturn == "IN_USED") {
						$.messager.alert('考核项目管理','该项目已经被使用，不能删除','warning');
					} 
					else {
						$.messager.alert('考核项目管理','删除失败','warning');
					} 
				});
			}
		});
	} 
</script>
</head>
<body class="ui-lv2bg">
<!-- 标准工具栏 -->
	<nav class="navbar navbar-default crm-toolbar" role="navigation">
		<ul class="nav navbar-nav">
	    	<li class="active"><a href="performance!toEditItem"><span class="glyphicon glyphicon-star"></span>&nbsp;新增</a></li>
	    	<li class="active"><a href="performance!itemList"><span class="glyphicon glyphicon-refresh"></span>&nbsp;清空查询条件</a></li>
	    </ul>
	</nav>  
    <!-- 查询输入栏 -->
         <div class="crm-querybar panel panel-info ">
            <form id="itemForm" name="itemForm" class="form-horizontal" action="performance!itemList" method="post">
            <input type="hidden" id="pageCount" name="pageCount" value="${page.pageCount}" />
		  	<input type="hidden" id="pageNumber" name="pageNumber" value="${page.start}" />
		  	<input type="hidden" id="totalCount" name="totalCount" value="${page.totalCount}" />
            <div class="form-group">
	      		<div class="col-sm-2">	 
	                <input name="item.itemName" type="text" value="${item.itemName}" class="form-control" placeholder="考核项目名称"/>
	            </div> 
	            <!--提交按钮	-->
			    <div class="col-sm-offset-6 col-sm-3">
		      		<button type="submit" class="btn btn-primary">查询</button>			
		        </div>	
            </div>
           </form>
     </div> 

 <!--  查询结果列表 -->
<div class="panel panel-default" id="searchResult" style="padding:2px;">
     <div class="panel-heading crm-table-title"><B>考核项目查询记录</B></div>
		<table  class="table table-hover" cellspacing="0" cellpadding="0" > 
	       <thead>	   
		    <th>考核项目</th>  
		    <th>来源</th>
		    <th>评价岗位</th>  
		    <th>度量单位</th>
		    <th>查看</th>
		    <th>删除</th> 
		  </thead>
              <s:iterator id="sitem" value="itemList" status="dl">
              <tr class="ui-table-style1-tr2">
                <td>${sitem.itemName }</td>
                <td>${sitem.resourceName }</td>
                <td>${sitem.accessPostName }</td>
                <td>${sitem.measurementUnit }</td>
                <td><a href="performance!itemDetail?itemId=${sitem.itemId}">查看</a></td>
                <td><a href="javascript:deleteItem(${sitem.itemId});">删除</a></td>
                
              </tr> 
              </s:iterator>
              
            </table>
            </div> 
<div class="ui-pagelist mt10 mb50">	
	<div style="float: right;" id="pager"></div>
</div>
</body>
</html>