<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.thoughtworks.xstream.io.path.Path"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

Cookie[] cookies = request.getCookies();
String cookieUserName = "";
String cookieAutoLoginFlag = "";
String cookiePwd = "";
if (cookies != null) {
    boolean flag1 = false;
    boolean flag2 = false;
    boolean flag3 = false;
	for (int i = 0; i < cookies.length; i++) {
		if ("cookieUserName".equals(cookies[i].getName())
				&& cookies[i].getValue() != null) {
			cookieUserName = cookies[i].getValue();
			flag1 = true;
		}else if ("cookieLoginFlag".equals(cookies[i].getName())
		        && cookies[i].getValue() != null) {
		    cookieAutoLoginFlag = cookies[i].getValue();
		    flag2 = true;
		} else if ("cookiePassword".equals(cookies[i].getName())
				&& cookies[i].getValue() != null) {
			cookiePwd = cookies[i].getValue();
			flag3 = true;
		} 
		
		if (flag1 && flag2 && flag3) {
		    break;
		}
	}
	
}

Date d = new Date(System.currentTimeMillis());
%>

<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	
	<title>登录</title>
	<link rel="shortcut icon" href="<%=basePath %>skin/Default/images/cssui-footico-4.png">
	<link href="<%=basePath %>skin/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css" />
	<link href="<%=basePath %>skin/Default/css/signin.css" rel="stylesheet" type="text/css" />
	
	<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.0/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
	
	<!-- jQuery核心JS -->
	<script type="text/javascript" src="<%=basePath %>skin/bootstrap/js/jquery-1.9.0.min.js" ></script>
	<script src="<%=basePath %>skin/bootstrap/js/TweenLite.min.js"></script>
	<script type="text/javascript" >
		$(document).ready(function(){
		  $(document).mousemove(function(e){
		     TweenLite.to($('body'), 
		        .5, 
		        { css: 
		            {
		                'background-position':parseInt(event.pageX/8) + "px "+parseInt(event.pageY/12)+"px, "+parseInt(event.pageX/15)+"px "+parseInt(event.pageY/15)+"px, "+parseInt(event.pageX/30)+"px "+parseInt(event.pageY/30)+"px"
		            }
		        });
		  });
		});
		
		function reset() {
			$("#userName").val('');
			$("#userName").val('');
		}
		
		var ieVersion = function(){
  		    var ver = 100,
  		    ie = (function(){
  		        var undef,
  		            v = 3,
  		            div = document.createElement('div'),
  		            all = div.getElementsByTagName('i');
  		        while (
  		            div.innerHTML = '<!--[if gt IE ' + (++v) + ']><i></i><![endif]-->',
  		            all[0]
  		        );
  		        return v > 4 ? v : undef;
  		    }());
  		    if(ie) ver = ie;
  		    return ver;
  		}
		
		function check()
		{
			var userName = $.trim($("#userName").val());
			if(userName =="")
			{
				
				$("#userName").focus();
				return;
			}
			var userPwd = $.trim($("#userPwd").val());
			if(userPwd =="")
			{
				$("#userPwd").focus();
				return;
			}
			//$("#showmsg").html("<img src='images/loading.gif' border='0' />登录处理中...");
			$("#userLoginForm").submit();;
		}
		
		
		// cookie获取
		  	$(document).ready(function(){
			  	
		  		if('<%=cookieUserName%>' != ''){
		  			$("#userName").val('<%=cookieUserName%>');
		  		}
		  		
		  		if ('<%=cookieAutoLoginFlag%>' != '') {
		  		    $("#userPwd").val('<%=cookiePwd%>');
		  		    document.getElementById("autoFlag").checked=true;
		  		    if($("#errormsg")) {
		  		    	return;
		  		    }
		  		  	check();
		  		}
		  		
		  		var isIECompatible = function(ver){
		  			var Sys = {};
		  	        var ua = navigator.userAgent.toLowerCase();
		  	        var s;
		  	        (s = ua.match(/msie ([\d.]+)/)) ? Sys.ie = s[1] :
		  	        (s = ua.match(/firefox\/([\d.]+)/)) ? Sys.firefox = s[1] :
		  	        (s = ua.match(/chrome\/([\d.]+)/)) ? Sys.chrome = s[1] :
		  	        (s = ua.match(/opera.([\d.]+)/)) ? Sys.opera = s[1] :
		  	        (s = ua.match(/version\/([\d.]+).*safari/)) ? Sys.safari = s[1] : 0;

		  	        //以下进行IE兼容性的测试
		  	        if (Sys.ie) {
		  	        	if(Sys.ie == ver) return true;
		  	        }
		  	        if (Sys.firefox) return false;
		  	        if (Sys.chrome) return false;
		  	        if (Sys.opera) return false;
		  	        if (Sys.safari) return false;
		  	        
		  		    var b = document.createElement('b')
		  		    b.innerHTML = '<!--[if IE ' + ver + ']><i></i><![endif]-->'
		  		    return b.getElementsByTagName('i').length === 1
		  		}
		  		
		  		if(isIECompatible(5.5) || isIECompatible(6.0) || isIECompatible(7.0) || isIECompatible(8.0)) {
		  			alert('不支持当前版本的浏览器版本，请升级到最新的浏览器版本！');
		  			location.href('http://windows.microsoft.com/zh-cn/internet-explorer/download-ie');
		  		}
		  			
		  	});
		  	
		  	$(document).keydown(function(e){
				 if(e.keyCode == 13) {
				 	check();
				 }
			 });
		  	
	</script>
	<style type="text/css">
		
	</style>
</head>
<body>
	<div class="container">
    <div class="row vertical-offset-100">
    	<div class="col-md-6 col-md-offset-3">
    		<div class="panel panel-info">
			  	<div class="panel-heading">
			    	<h3 class="panel-title">凯扬工业销售管理系统</h3>
			 	</div>
			  	<div class="panel-body">
			    	<s:form id="userLoginForm" name="userLoginForm"
						action="login!userLogin" theme="simple" namespace="/" method="post"
						validate="false" cssClass="form-signin form-horizontal">
                    <fieldset>
                    	<div class="form-group">
                    		<s:if test="#session.errorMsg != null && #session.errorMsg.trim()!=''">
								<div id="errormsg" class="alert alert-danger"><s:property value="#session.errorMsg"/></div>
							</s:if>
                    	</div>
			    	  	<div style="margin-bottom: 25px" class="input-group">
			    	  		<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
			    		    <input type="text" class="form-control" placeholder="用户名" name="userName" id="userName" required autofocus>
			    		</div>
			    		<div style="margin-bottom: 25px" class="input-group">
			    			<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
			    			<input type="password" class="form-control" placeholder="密码" name="userPwd" id="userPwd" required>
			    		</div>
			    		<div class="checkbox">
			    	    	<label>
			    	    		<input type="checkbox" id="autoFlag" name="autoLoginFlag"/> 记住我
			    	    	</label>
			    	    </div>
			    	    <div style="margin-top:10px" class="form-group">
                                    <!-- Button -->

                                    <div class="col-sm-12 controls">
                                      <button id="btn-login" href="#"  onclick="check();"; class="btn btn-success">登录  </button>
                                      <a id="btn-fblogin" href="#" onclick="reset();" class="btn btn-primary">取消</a>

                                    </div>
                                </div>
                                
			    	    
			    	</fieldset>
			      	</s:form>
			    </div>
			</div>
		</div>
	</div>
	
	
</body>
</html>