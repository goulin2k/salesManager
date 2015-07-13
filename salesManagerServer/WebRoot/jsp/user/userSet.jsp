<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.thoughtworks.xstream.io.path.Path"%>
<%@taglib prefix="s" uri="/struts-tags"%>


<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>个人信息</title>
<link href="skin/Default/css/base.css" rel="stylesheet" type="text/css" />
<link href="skin/Default/css/global.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath %>script/common/jquery-1.4.2.min.js" ></script>
<script type="text/javascript" >
	$(document).ready(function(){  
	    $("#submit").click(function(checkEvent){
	    	check();
	    });
			
		$("#cancel").click(function(checkEvent){
		    	checkEvent.preventDefault();
		    	history.back();	
	    });
	  	
	    if ('${msg}' == 'addsuccuss') {
	        alert('个人信息设置成功！');
        } 
	});

	function check()
	{
		var loginName = $.trim($("#loginName").val());
		if(loginName =="")
		{
			alert("用户名不能为空！");
			$("#loginName").focus();
			return false;
		}
		
		$("#userForm").submit();
	}
	</script>
</head>
<!-- jQuery核心JS -->

<body class="ui-lv2bg">
	<div class="ui-content-title"><p><img src="skin/Default/images/ui-content-ico/1.png" />个人信息</p></div>
   <!--======内容区域子导航======--> 

   <!--===== 分割线======-->     
<hr class="hr-two" />
    
    
    
    
<!--======搜索======-->
        <div class="clear"></div>
       

 <!--======表格样式1======--> 
 <div class="ui-table ml15">
  <s:form id="userForm" name="userForm" action="sUser!userSet" theme="simple" namespace="/" method="post" validate="false">
<table class="ui-table-one" cellspacing="2" cellpadding="0" >
<caption class="mt10 mb10">用户信息</caption>
 
   <tr>
    <td class="ui-table-title">登录名：</td>
    <td class="ui-table-input-r">
    <input name="userInfo.userId" id="userId" type="hidden" value="${userInfo.userId}"/>
    <input name="userInfo.loginName" id="loginName" type="text" value="${userInfo.loginName}"/></td>
    <td class="ui-table-title">真实名：</td>
    <td class="ui-table-input-r"><input name="userInfo.userName" id="userName" type="text" value="${userInfo.userName}"/></td>
  </tr>
  <tr>
    <td class="ui-table-title">用户密码：</td>
    <td class="ui-table-input-r"><input name="userInfo.loginPasswor" id="loginPasswor" type="password"/></td>
    <td class="ui-table-title">手机号码：</td>
    <td class="ui-table-input-r"><input name="userInfo.mobile" id="mobile" type="text" value="${userInfo.mobile}" /></td>
  </tr>
  <tr>
    <td class="ui-table-title">固定电话：</td>
    <td class="ui-table-input-r"><input name="userInfo.phone" id="phone" type="text" value="${userInfo.phone}" /></td>
    <td class="ui-table-title">email：</td>
    <td class="ui-table-input-r"><input name="userInfo.email" id="email" type="text" value="${userInfo.email}"/></td>
  </tr>
  <tr>
    <td class="ui-table-title">所属部门：</td>
    <td class="ui-table-input-r">
    ${userInfo.departmentName }
    </td>
    <td class="ui-table-title">用户角色：</td>
    <td class="ui-table-input-r">
    ${userInfo.roleName }</td>
  </tr>
  <tr>
    <td class="ui-table-title">行政岗位：</td>
    <td class="ui-table-input-r">
    ${userInfo.positionOrgName}</td>
    <td class="ui-table-title">业务岗位：</td>
    <td class="ui-table-input-r">
    ${userInfo.positionBsName }</td>
  </tr>

</table>

	<!--  按钮面板  -->
	<div class="crm-button-panel form-group ">
		<div class="col-sm-6">
			<button class="btn btn-primary col-sm-offset-9 col-sm-3" id="submit">确定</button>
		</div>
		<div class="col-sm-6">
			<a class="btn btn-warning col-sm-3" id="cancel">取消</a>
		</div>
	</div>
</s:form> 

<div class="clear"></div>
</div>
</body>
</html>



