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
<title>数据字典</title>
<link href="skin/Default/css/base.css" rel="stylesheet" type="text/css" />
<link href="skin/Default/css/global.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath %>script/common/jquery-1.4.2.min.js" ></script>
<script type="text/javascript" >
	function check()
	{
		//var loginName = $.trim($("#loginName").val());
		//if(loginName =="")
		//{
			//alert("用户名不能为空！");
			//$("#loginName").focus();
			//return false;
		//}
		//var loginPasswor = $.trim($("#loginPasswor").val());
		//if(loginPasswor =="")
		//{
			//alert("密码不能为空！");
			//$("#loginPasswor").focus();
			//return false;
		//}
		return false;
		//$("#showmsg").html("<img src='images/loading.gif' border='0' />登录处理中...");
		//$("#userForm").submit();
	}
	</script>
</head>
<!-- jQuery核心JS -->

<body class="ui-lv2bg">
	<div class="ui-content-title"><p><img src="skin/Default/images/ui-content-ico/1.png" />数据字典</p></div>
   <!--======内容区域子导航======--> 

   <!--===== 分割线======-->     
<hr class="hr-two" />
    
    
    
    
<!--======搜索======-->
        <div class="clear"></div>
       

 <!--======表格样式1======--> 
 <div class="ui-table ml15">
  <s:form id="userForm" name="enumerationForm" action="enumeration!getEnumList" theme="simple" namespace="/" method="post" validate="false">
<table class="ui-table-one" cellspacing="2" cellpadding="0" >
<caption class="mt10 mb10">数据字典</caption>
 
   <tr>
    <td class="ui-table-title">字段类型</td>
    <td class="ui-table-input-r">字段内容</td>
    <td class="ui-table-input-r"></td>
    </tr>
     <tr>
    <td class="ui-table-title">
    <s:iterator id="enumerationIndex" value="enumerationIndexList" status="dl">
    <a href="enumeration!getEnumList?enumerationType=${enumerationIndex.enumerationId }">${enumerationIndex.enumerationName} </a><br/>
    </s:iterator>
    </td>
    <td class="ui-table-input-r">
    <s:iterator id="enumeration" value="enumerationList" status="dl">
    <input name="enumeration.enumerationId" id="enumerationId" type="hidden" value="${enumeration.enumerationId}"/>
    <input name="enumeration.enumerationName" id="enumerationName" type="text" value="${enumeration.enumerationName}"/><br/>
    </s:iterator>
    </td>
    <td class="ui-table-input-r"></td>
    </tr>

</table>

<div class="ui-button-big center mt10 mb50 "><a href="#" class="fl mr30" onclick="check();">修改</a></div>
</s:form> 

<div class="clear"></div>
</div>
</body>
</html>



