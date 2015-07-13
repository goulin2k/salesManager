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
<title>参数设置</title>

<script type="text/javascript" language="javascript" src="script/common/pager/jquery.pager.js"></script>
<link type="text/css" rel="stylesheet" href="script/common/pager/Pager.css" />
<script type="text/javascript">
			var basePath = "<%=path%>/";
			$(document).ready(function(){
				$("#pager").pager({
						    pagenumber:${parameter.pageNo},                         /* 表示初始页数 */
						    pagecount:${parameter.pageCount},                      /* 表示总页数 */
						    totalCount:${parameter.totalCount},
						    buttonClickCallback:PageClick                     /* 表示点击分页数按钮调用的方法 */                  
				});
				
			});
	
			/*
		        PageClick = function(pageclickednumber) {}部分
		        PageClick，表示自定义点击分页数时的function方法，如：function(pageclickednumber){}
		        jQuery插件JQuery Pager分页器只需要起始页数pagenumber，最大页数pagecount，
		             点击页数时的调用buttonClickCallback的 function方法就可实现javascript分页功能，
		             实际应用中只需对PageClick方法进行简单修改就可使用，如将pagenumber和 pagecount设为变量，
		             可通过GET的方法进行页数值传递，JQuery Pager就可实现javascript分页功能
		    */
		    PageClick = function(pageclickednumber) {
				$("#pager").pager({
				    pagenumber:pageclickednumber,                 /* 表示启示页 */
				    pagecount:${parameter.pageCount},                  /* 表示最大页数pagecount */
				    buttonClickCallback:PageClick                 /* 表示点击页数时的调用的方法就可实现javascript分页功能 */            
				});
				
				$("#pageNumber").val(pageclickednumber);          /* 给pageNumber从新赋值 */
				/* 执行Action */
				pagesearch();
			}
			
	function search(){
		$("#pageNumber").val("1");
		pagesearch();
	}
	
	function pagesearch(){
		paramForm.submit();
	}
</script>
</head>
<body class="ui-lv2bg">
     <nav class="navbar navbar-default" role="navigation">
		
	    <ul class="nav navbar-nav">
	    	<li class="active"><a href="parameter!toEdit"><span class="glyphicon glyphicon-star"></span>&nbsp;新增</a></li>
	    </ul>
	      <form class="navbar-form navbar-right form-inline" role="search" id="paramForm" name="paramForm" action="parameter!paramList" method="post">
	      <div class="form-group">	        
	        <input class="form-control" placeholder="搜索名称" name="parameter.name" type="text" value="${parameter.name}"/>
            <input type="hidden" id="pageNumber" name="parameter.pageNo" value="${parameter.pageNo}" />
	      </div>
	      	<button type="submit"  class="btn btn-primary">搜索</button>
	    </form>
    </nav>
	 <div class="panel panel-default" id="searchResult" style="padding:2px;">
     <div class="panel-heading crm-table-title"><B>用户查询列表</B></div>
		<table class="table table-hover" cellspacing="0" cellpadding="0" >
              <thead>
                <th>参数名称</th>
                <th>参数值</th>
                <th>参数描述</th>
                <th>修改</th>
                <th>删除</th>
              </thead>
              <s:iterator id="sparameter" value="parameterList" status="dl">
              <tr class="ui-table-style1-tr2">
                <td>${sparameter.name }</td>
                <td>${sparameter.value }</td>
                <td>${sparameter.comment }</td>
                <td><a href="parameter!toEdit?id=${sparameter.parameterId}">修改</a></td>
                <td><a href="parameter!delete?id=${sparameter.parameterId}">删除</a></td>
                
              </tr> 
              </s:iterator>
              
            </table>
            </div>
  
    <div class="ui-pagelist" id="pager"></div>
    <div class="clear"></div>
	
</body>
</html>