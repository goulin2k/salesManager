<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html lang="zh-cn">
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
	<title>凯扬工业——客户管理系统工作台</title>
	
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
    <link type="text/css" href="<%=basePath %>skin/Default/css/fisheye.css" rel="stylesheet"  />
    <link type="text/css" href="<%=basePath %>skin/bootstrap/css/jquery-ui-1.10.0.custom.css" rel="stylesheet"  />
    <link type="text/css" href="<%=basePath %>skin/bootstrap/css/jquery.ui.1.10.0.ie.css" rel="stylesheet"  />
    <link type="text/css" href="<%=basePath %>skin/Default/css/base.css" rel="stylesheet"  />
    <link type="text/css" href="<%=basePath %>skin/Default/css/global.css" rel="stylesheet"  />
    
    

	<script type="text/javascript" src="<%=basePath %>skin/bootstrap/js/jquery-1.8.3.min.js"></script>	
	<script type="text/javascript" src="<%=basePath %>skin/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>skin/bootstrap/js/jquery-ui-1.10.0.custom.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>script/fisheye/interface.js"></script>
	
	<!--  pnotify options -->
	<script src="<%=basePath %>script/pnotify/jquery.pnotify.js" type="text/javascript"></script>
    <link type="text/css" media="all"  href="<%=basePath %>script/pnotify/jquery.pnotify.default.css" rel="stylesheet" />
	<link type="text/css" media="all"  href="<%=basePath %>script/pnotify/jquery.pnotify.default.icons.css" rel="stylesheet" />
	<link type="text/css" media="all"  href="<%=basePath %>script/pnotify/oxygen/icons.css" rel="stylesheet" />
	<link type="text/css" href="<%=basePath %>script/pnotify/notify.css" rel="stylesheet"  />
	<!--  
	<script type="text/javascript" src="<%=basePath %>script/common/easyui/jquery.easyui.min.js"></script>
	<link type="text/css" href="<%=basePath %>script/common/easyui/themes/default/easyui.css" rel="stylesheet" />
	<link type="text/css" href="<%=basePath %>script/common/easyui/themes/icon.css" rel="stylesheet" />
-->		
<style type="text/css">
	body{
	    background: url(skin/Default/images/loginback.png);
		background-color: #444;
	        
	 
	 		}
		.vertical-offset-100{
			padding-top:100px;
		}
		
</style>

	
</head>

<body>
	<!--  Header	 -->
	<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <%@ include file="/decorators/header.jsp"%>
    </div>
    <!--  End Headers -->
    <!-- Main jumbotron for a primary marketing message or call to action -->
    <div class="jumbotron" style="padding-top:36px;padding-bottom:12px;"></div>
    <div class="container">
      
      <div class="content ui-widget-content ui-corner-all" id="getModule">
		<ul class="ui-widget-content ui-corner-all" style="border:0px;">
			<s:iterator id="modul" value="moduleList" status="uml">
				<li class="content-li"><a href="${modul.moduleUrl}" target="_blank"><img
						src="skin/Default/images/ui-content-ico/${modul.moduleNameEnglish}.png" />
					<p>${modul.moduleNameChinese}</p>
				</a>
				</li>
			</s:iterator>
		</ul>
		
	</div>
	<div class="clear"></div>
	

	<div class="dock" id="dock2">
		<div id="container" class="dock-container2">
			<a class="dock-item2" href="javascript:gotoIndex(1)"><span>工作台</span><img
				src="skin/Default/images/ui-footico-6.png" />
			</a>
			<s:iterator id="ra" value="actionList" status="status">
				<a class="dock-item2" href="javascript:changeList(${ra.actionId})"><span>${ra.name}</span><img
					src="skin/Default/images/${ra.url}.png" alt="home" />
				</a>
			</s:iterator>
		</div>
		
	</div>
	
    </div> <!-- /container -->
	
	
	<script type="text/javascript" src="<%=basePath %>script/jsp/workbench.js"></script>
	
</body>
	
</html>



