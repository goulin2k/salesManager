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
<title>新增用户</title>
<link href="skin/Default/css/base.css" rel="stylesheet" type="text/css" />
<link href="skin/Default/css/global.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath %>script/common/jquery-1.4.2.min.js" ></script>
<script type="text/javascript" >
	function check()
	{
		var name = $.trim($("#name").val());
		if(name =="")
		{
			alert("角色名不能为空！");
			$("#name").focus();
			return false;
		}
		var comment = $.trim($("#comment").val());
		if(comment =="")
		{
			alert("角色说明不能为空！");
			$("#comment").focus();
			return false;
		}
		//$("#showmsg").html("<img src='images/loading.gif' border='0' />登录处理中...");
		$("#roleForm").submit();
	}
	</script>
</head>
<!-- jQuery核心JS -->

<body class="ui-lv2bg">
	<div class="ui-content-title"><p><img src="skin/Default/images/ui-content-ico/addman.png" />新增用户</p></div>
   <!--======内容区域子导航======--> 

   <!--===== 分割线======-->     
<hr class="hr-two" />
    
    
    
    
<!--======搜索======-->
        <div class="clear"></div>
       

 <!--======表格样式1======--> 
 <div class="ui-table ml15">
  <s:form id="roleForm" name="userForm" action="role!addRole" theme="simple" namespace="/" method="post" validate="false">
<table class="ui-table-one" cellspacing="2" cellpadding="0" >
<caption><img src="skin/Default/images/ui-content-ico/set-man.png" />新增用户角色</caption>
  <tr>
    <td class="ui-table-title">角色名称：</td>
    <td class="ui-table-input-r"><input name="name" id="name" type="text" /></td>
  </tr>
  <tr>
    <td class="ui-table-title">角色说明：</td>
    <td class="ui-table-input-r"><input name="comment" id="comment" type="text" /></td>
  </tr>
	<tr>
    <td class="ui-table-title">是否控制客户访问：</td>
    <td class="ui-table-input-l" align="left"><input name="customerChecked" id="customerChecked" type="checkbox" /></td>
  </tr>
</table>

<div class="ui-button-big center mt10 mb50 "><a href="#" onclick="check();">确定</a></div>
</s:form>

<div class="clear"></div>
</div>
</body>
</html>



