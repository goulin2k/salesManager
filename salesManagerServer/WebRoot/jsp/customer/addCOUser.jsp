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
<title>新增客户分配人</title>
<link href="skin/Default/css/base.css" rel="stylesheet" type="text/css" />
<link href="skin/Default/css/global.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath %>script/common/jquery-1.4.2.min.js" ></script>
<script type="text/javascript" >
	function check()
	{
		var customerName = $.trim($("#customerName").val());
		if(customerName =="")
		{
			alert("客户不能为空！");
			$("#customerName").focus();
			return false;
		}
		var userName = $.trim($("#userName").val());
		if(userName =="")
		{
			alert("分配人不能为空！");
			$("#userName").focus();
			return false;
		}
		$("#couserForm").submit();
	}
	
	function getCustomer(){ 
	var array = window.showModalDialog("<%=basePath %>/customer!customerOpenWindow", "", "dialogWidth:800px; dialogHeight:600px; status:no;help:no");
	//for chrome
    if (array == undefined) {
    	array = window.returnValue;
    }
	$("#customerId").val(array[0]);
	$("#customerName").val(array[1]);
}

function getUser(){ 
	var array = window.showModalDialog("<%=basePath %>/sUser!userOpenWindowList", "", "dialogWidth:800px; dialogHeight:500px; status:no;help:no");
	//for chrome
    if (array == undefined) {
    	array = window.returnValue;
    }
	$("#userId").val(array[0]);
	$("#userName").val(array[1]);
}

function getSalegen(){ 
	var array = window.showModalDialog("<%=basePath %>/sUser!userOpenWindowList", "", "dialogWidth:800px; dialogHeight:500px; status:no;help:no");
	//for chrome
    if (array == undefined) {
    	array = window.returnValue;
    }
	$("#salegenId").val(array[0]);
	$("#salegenName").val(array[1]);
}

function getFinmanager(){ 
	var array = window.showModalDialog("<%=basePath %>/sUser!userOpenWindowList", "", "dialogWidth:800px; dialogHeight:500px; status:no;help:no");
	//for chrome
    if (array == undefined) {
    	array = window.returnValue;
    }
	$("#finmanagerId").val(array[0]);
	$("#finmanagerName").val(array[1]);
}
	</script>
</head>
<!-- jQuery核心JS -->

<body class="ui-lv2bg">
	<div class="ui-content-title"><p><img src="skin/Default/images/ui-content-ico/man.gif" />新增客户分配人</p></div>
   <!--======内容区域子导航======-->   
    
<!--======搜索======-->
        <div class="clear"></div>
       

 <!--======表格样式1======--> 
 <div class="ui-table ml15">
  <s:form id="couserForm" name="couserForm" action="customer!addCOUser" theme="simple" namespace="/" method="post" validate="false">
<table class="ui-table-one" cellspacing="2" cellpadding="0" >
<caption class="mt30"><img src="skin/Default/images/ui-content-ico/add.png"/ class="mr8">新增客户分配人</caption>
  <tr>
  <td class="ui-table-title">客户：</td>
    <td class="ui-table-input-r">
    <input name="couser.customerId" id="customerId" type="hidden"/>
    <input name="customerName" id="customerName" type="text" onClick="getCustomer();" readonly="readonly"/>
    </td> 
  <td class="ui-table-title">总经理（副）：</td>
    <td class="ui-table-input-r">
    <input name="couser.salegenId" id="salegenId" type="hidden"/>
    <input name="couser.salegenName" id="salegenName" type="text" onClick="getSalegen();" readonly="readonly"/>
    </td> 
  </tr>
  <tr>
  
    <td class="ui-table-title">部门经理：</td>
    <td class="ui-table-input-r">
    <input name="couser.finmanagerId" id="finmanagerId" type="hidden"/>
    <input name="finmanagerName" id="finmanagerName" type="text" onClick="getFinmanager();" readonly="readonly"/>
    </td> 
    
    <td class="ui-table-title">业务员：</td>
    <td class="ui-table-input-r">
    <input name="couser.userId" id="userId" type="hidden" value="${userId }" />
    <input name="userName" id="userName" type="text"  readonly="readonly" value="${userName }" />
    </td> 
  </tr>
</table>

<div class="ui-button-big center mt30"><a href="#" onclick="check();">确定</a></div>
</s:form>
</div>
</body>
</html>



