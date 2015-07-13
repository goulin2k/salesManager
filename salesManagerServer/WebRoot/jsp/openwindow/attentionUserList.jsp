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
<base target="_self" />
<title>客户列表</title>
<base target="_self">

	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
	<title>凯扬工业——客户管理系统工作台</title>
	<base target="_self">
	
	<!-- Bootstrap core CSS -->
    <link href="skin/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="skin/bootstrap/css/bootstrap-theme.css" rel="stylesheet">
    
    <!-- Just for debugging purposes. Don't actually copy this line! -->
    <!--[if lt IE 9]><script src="../../docs-assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.0/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
    
    <link type="text/css" href="<%=basePath %>skin/bootstrap/css/jquery-ui-1.10.0.custom.css" rel="stylesheet"  />
    <link type="text/css" href="<%=basePath %>skin/bootstrap/css/jquery.ui.1.10.0.ie.css" rel="stylesheet"  />
    <link type="text/css" href="<%=basePath %>skin/Default/css/base.css" rel="stylesheet"  />
    <link type="text/css" href="<%=basePath %>skin/Default/css/global.css" rel="stylesheet"  />
    <link type="text/css" rel="stylesheet" href="<%=basePath %>script/common/pager/Pager.css" />
    

	<script type="text/javascript" src="<%=basePath %>skin/bootstrap/js/jquery-1.8.3.min.js"></script>	
	<script type="text/javascript" src="<%=basePath %>skin/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>skin/bootstrap/js/jquery-ui-1.10.0.custom.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>script/common/pager/jquery.pager.js"></script>
	<script type="text/javascript" src="<%=basePath %>script/common/pages.js"></script>
	
	<script type="text/javascript">
		var basePath = "<%=path%>/";
		
		
		function returnValue1(trValue){
			var array = new Array(); 
			array[0] = trValue.find('td').eq(0).find('input').val(); 
			array[1] = trValue.find('td').eq(1).text();
			if (window.opener) {
			       //for chrome
			       window.opener.returnValue = array;
			}
			else {
			       window.returnValue = array;
			}
		    window.close();
		}
		
	</script>
</head>
<body class="ui-lv2bg2">
	<!-- 简单风格的工具查询栏	-->	
	<nav class="navbar navbar-default" role="navigation">
	    <ul class="nav navbar-nav">
	    	<li class="active"><a href="sUser!attentionUserList"><span class="glyphicon glyphicon-refresh"></span>&nbsp;清空查询条件</a></li>
	    	<li class="active"><a href="javascript:this.close();"><span class="glyphicon glyphicon-remove-sign"></span>&nbsp;关闭</a></li>
	    </ul>
	    <form class="navbar-form navbar-right" role="search" id="userForm" name="userForm" 
	    		action="<%=basePath %>sUser!attentionUserList" method="post">
	      <div class="form-group">
	      	<input type="hidden" id="pageCount" name="pageCount" value="${page.pageCount}" />
		  	<input type="hidden" id="pageNumber" name="pageNumber" value="${page.start}" />
		  	<input type="hidden" id="totalCount" name="totalCount" value="${page.totalCount}" />
	        <input type="text" id="userName" name="userInfo.userName" value="${userInfo.userName}"
	        		class="form-control" placeholder="用户姓名">
	      </div>
	      	<button type="submit"  class="btn btn-primary">搜索</button>
	    </form>
    </nav>
    
	<!--  查询结果列表 -->
    <div class="panel panel-default" id="searchResult" style="padding:2px;">
     <div class="panel-heading crm-table-title"><B>用户查询记录</B></div>
		<table class="table table-hover" cellspacing="0" cellpadding="0" >
              <thead> 
                <th>登录名</th>
                <th>真实姓名</th>
                <th>用户角色</th>
                <th>联系电话</th> 
              </thead>
        <!--      =================表格循环===============-->
              <s:iterator id="temp" value="userList" status="dl">
              <tr class="ui-table-style1-tr2" onclick="returnValue1($(this))"> 
                <td><input type="hidden" id="userId" name="userId" value="<s:property value="userId"/>" />${temp.loginName }</td>
                <td>${temp.userName }</td>
                <td>${temp.roleName }</td>
                <td>${temp.mobile }</td>
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