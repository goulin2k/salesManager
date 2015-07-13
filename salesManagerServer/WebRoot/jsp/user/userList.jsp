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
<title>用户列表</title>

<script type="text/javascript" language="javascript" src="<%=basePath %>script/common/pager/jquery.pager.js"></script>
<link type="text/css" rel="stylesheet" href="<%=basePath %>script/common/pager/Pager.css" />
<script type="text/javascript">
			var basePath = "<%=path%>/";
			var nSearchPanelHeight = 120;				//定义查询框的高度（px）
			$(document).ready(function() {
				$("#pager").pager({
						    pagenumber:${page.start},                         /* 表示初始页数 */
						    pagecount:${page.pageCount},                      /* 表示总页数 */
						    totalCount:${page.totalCount},						/* 记录总数		*/ 
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
				    pagecount:${page.pageCount},                  /* 表示最大页数pagecount */
				    totalCount:${page.totalCount},						/* 记录总数		*/ 
				    buttonClickCallback:PageClick                 /* 表示点击页数时的调用的方法就可实现javascript分页功能 */            
				});
				
				$("#pageNumber").val(pageclickednumber);          /* 给pageNumber从新赋值 */
				/* 执行Action */
				pagesearch();
			}
			
	function search(){
		$("#pageNumber").val("1");
		//pagesearch();
		$("#userForm").submit();
	}
	
	function deleteSearch() {	//清除查询条件
		window.location.href = basePath + '/sUser!userList';
	}
	
	function pagesearch(){
		var pageNumber = $("#pageNumber").val();
		var url= basePath+"sUser!userList?pageNumber="+pageNumber;
		url = encodeURI(url);
		window.location.href=url;
	}
</script>
</head>
<body class="ui-lv2bg">
	<nav class="navbar navbar-default" role="navigation">
		
	    <ul class="nav navbar-nav">
	    	<li class="active"><a href="sUser!addUserInit"><span class="glyphicon glyphicon-star"></span>&nbsp;新增</a></li>
	    </ul>
	    <form class="navbar-form navbar-right form-inline" role="search" id="userForm" 
	    	name="userForm" action="<%=basePath %>sUser!userList" method="post">
	      <div class="form-group">
	      	<input type="hidden" id="pageNumber" name="pageNumber" value="${page.start}" />
	        <input type="text" id="userName" name="userName" class="form-control" placeholder="用户姓名">
	      </div>
	      	<button type="submit"  class="btn btn-primary">搜索</button>
	    </form>
    </nav>
    
    
    

    <div class="panel panel-default" id="searchResult" style="padding:2px;">
     <div class="panel-heading crm-table-title"><B>用户查询列表</B></div>
		<table class="table table-hover" cellspacing="0" cellpadding="0" >
              <thead>
              	<input type="hidden" id="pageNumber" name="pageNumber" value="${page.start}" />
                <th >登录名</th>
                <th >真实姓名</th>
                <th >用户角色</th>
                <th > 部门</th>
                <th >业务岗位</th>
               	<th >查看</th>
               	<th >删除</th>
              </thead>
        <!--      =================表格循环===============-->
              <s:iterator id="temp" value="userList" status="dl">
              <tr class="ui-table-style1-tr2">
                <td>${temp.loginName }</td>
                <td>${temp.userName }</td>
                <td>${temp.roleName }</td>
                <td>${temp.departmentName }</td>
                <td>${temp.positionBsName }</td>
                <td><a href="sUser!updateUserInit?userId=${temp.userId }">查看</a></td>
                <td><a href="sUser!deleteUser?userId=${temp.userId }">删除</a></td>
              </tr> 
              </s:iterator>
          <!--     =================表格循环over===============-->
          
            </table>
   <!--      =================页=、==============-->

<!-- <table><tr><td>
    	<p>共&nbsp;${page.totalCount}&nbsp;条记录</p>
    	    <div style="float: right;" id="pager"> fff</div>
</td></tr></table> -->
<div class="ui-pagelist" id="pager">
    	<!-- <ul>
        	<li><a href="#">首页</a></li>
            <li><a href="#">上一页</a></li>
            <li class="ui-pagelist-current"><a href="#">1</a></li>
            <li><a href="#">2</a></li>
            <li><a href="#">3</a></li>
            <p >...</p>
            <li><a href="#">下一页</a></li>
            <li><a href="#">末页</a></li>
        </ul><p>共${page.totalCount}条记录</p>   
        -->
        
    </div>
</div>

</body>
</html>