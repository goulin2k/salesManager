<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/sitemesh-decorator.tld" prefix="decorator"%>
<%@ taglib uri="/WEB-INF/sitemesh-page.tld" prefix="page"%>


<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
		<title>客户管理系统——<decorator:title/></title>
	
	<!-- Bootstrap core CSS -->
    <link href="skin/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="skin/bootstrap/css/bootstrap-theme.css" rel="stylesheet">
    <link href="skin/bootstrap/css/bootstrap-dialog.min.css" rel="stylesheet">
    <!-- Just for debugging purposes. Don't actually copy this line! -->
    <!--[if lt IE 9]><script src="../../docs-assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.0/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
    <link type="text/css" href="<%=basePath %>skin/Default/css/jquery-ui.css" rel="stylesheet"  />
     
    <link type="text/css" href="<%=basePath %>skin/Default/css/base.css" rel="stylesheet"  />
    <link type="text/css" href="<%=basePath %>skin/Default/css/global.css" rel="stylesheet"  />
    
    
	<script type="text/javascript" src="<%=basePath %>skin/bootstrap/js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>skin/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>skin/bootstrap/js/bootstrap-dialog.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>skin/bootstrap/js/jquery-ui-1.10.0.custom.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>skin/bootstrap/js/jquery.ui.datepicker-zh-TW.js"></script>
	<script type="text/javascript" src="<%=basePath %>script/common/jquery.placeholder.js"></script>
	
	<!-- easyUI 相关验证js -->
	<script type="text/javascript" src="<%=basePath %>script/common/easyui-1.3.5/plugins/jquery.parser.js"></script>
	<script type="text/javascript" src="<%=basePath %>script/common/easyui-1.3.5/jquery.easyui.min.js"></script>
	
	<link rel="stylesheet" type="text/css" href="<%=basePath %>script/common/easyui-1.3.5/themes/icon.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath %>script/common/easyui-1.3.5/themes/bootstrap/easyui.css" /> 
	<script type="text/javascript" src="script/common/easyui/validate.js"></script>
	<script type="text/javascript" src="script/common/easyui/locale/easyui-lang-zh_CN.js"></script>
	
	<script type="text/javascript" src="script/common/modal.js"></script>
	
		
	<!-- 导入页面引用的特殊js和css文件 -->
	<decorator:head ></decorator:head>
	</head>
	
	<body class="ui-lv2bg">
		<!-- 页头 -->
		<!--  Header	 -->
		<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
			<%@ include file="/decorators/header.jsp"%>
		</div>
		
		<!-- Main jumbotron for a primary marketing message or call to action -->
	    <div class="jumbotron" style="padding-top:36px;padding-bottom:12px;"></div>
	    
		
		<div class="container" style="padding-top:6px;padding-bottom:6px; width:100%">
	      <!-- 内容区域 -->
	      <div class="row">
	        <div class="col-md-2">
	          <%@ include file="/decorators/leftMenu.jsp"%>
	        </div>
	        
	        <div class="col-md-8 " style="padding-right: 4px;padding-left: 4px;" >
	        	<div class="panel panel-primary">
				<div class="panel-heading"><span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<decorator:title/></div>
				<div class="panel-body" style="padding-top:0px;padding-bottom:6px;padding-right: 4px;padding-left: 4px">
	          	<decorator:body />
	          	</div>
	          	</div>
	       </div>
	       
	        <div class="col-md-2">
	          <%@ include file="/decorators/rightMenu.jsp"%>
	        </div>
	      </div>
	
	      <hr>
	
	      <footer align="center" style="color:white;">
	      	<p >&copy; 2013 - 2014 weibo. All Rights Reserved.<p >
	        <p >成都凯扬工业有限责任公司 版权所有 2013<p >
	        
	      </footer>
	    </div>
	    <!--  对话框 -->
	    <%@ include file="/jsp/common/modalDialog.jsp"%>
	</body>
</html>
