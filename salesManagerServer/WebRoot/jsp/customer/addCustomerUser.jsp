<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.thoughtworks.xstream.io.path.Path"%>
<%@taglib prefix="s" uri="/struts-tags"%>


<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>新增客户关注人</title>
	
	<script type="text/javascript" language="javascript" src="<%=basePath %>script/common/common.js"></script>

<script type="text/javascript" >
	$(document).ready(function(){  
	    $("#sumbit").click(function(checkEvent){  
	    	if($('#customerUserForm').form('validate')){
	    		$("#customerUserForm").submit();
	    	}
	    }); 
	    
	    $("#cancel").click(function(checkEvent){
	    	checkEvent.preventDefault();
	    	history.back();
	    });
	});

	
	function getCustomer(){ 
		var array = window.showModalDialog("<%=basePath %>/customer!customerOpenWindow", "", 
				"dialogWidth:800px; dialogHeight:600px; status:no;help:no");
		//for chrome
	    if (array == undefined) {
	    	array = window.returnValue;
	    }
		$("#customerId").val(array[0]);
		$("#customerName").val(array[1]);
	}

	function getUser(){ 
		var array = window.showModalDialog("<%=basePath %>/sUser!userOpenWindowList", "", 
				"dialogWidth:800px; dialogHeight:500px; status:no;help:no");
		//for chrome
	    if (array == undefined) {
	    	array = window.returnValue;
	    }
		$("#userId").val(array[0]);
		$("#userName").val(array[1]);
	}
	</script>
</head>

<body class="ui-lv2bg">
	
 <!--======表格样式1======--> 
<div class="panel panel-default" id="editForm" style="padding:2px;">
	<div class="panel-heading crm-table-title"><B>客户关注人编辑</B></div>
	 <div class="panel-body">
  	<s:form id="customerUserForm" name="customerUserForm" action="customer!addCustomerUser" theme="simple" namespace="/" method="post" validate="false">
	<table class="ui-table-one" cellspacing="2" cellpadding="0" >
	<caption class="mt30">新增客户关注人</caption>
	    <tr>
	    <td class="ui-table-title">客户：</td>
	    <td class="ui-table-input-r">
	    <input name="customerUser.customerId" id="customerId" type="hidden" value="${customerId }" />
	    <input name="customerName" id="customerName" type="text" readonly="readonly" 
	    	value="${customerName }"  class="easyui-validatebox" required="true"/>
	    </td>
	    <td class="ui-table-title">关注人：</td>
	    <td class="ui-table-input-r">
	    <input name="customerUser.userId" id="userId" type="hidden"/>
	    <input name="userName" id="userName" type="text" onClick="getUser();" readonly="readonly" 
	    	class="easyui-validatebox" required="true"/>
	    </td>
	  </tr>
	
	</table>

<!--  按钮面板  -->
	<div class="crm-button-panel form-group ">
		<div class="col-sm-6">
			<a class="btn btn-primary col-sm-offset-9 col-sm-3" id="sumbit">确定</a>
		</div>
		<div class="col-sm-6">
			<a class="btn btn-warning col-sm-3" id="cancel">取消</a>
		</div>
	</div>
	</div>
</s:form>
</div>

<div class="panel panel-default" id="searchResult" style="padding:2px;">
     <div class="panel-heading crm-table-title"><B>客户关注人列表</B></div>
		<table class="table table-hover" cellspacing="0" cellpadding="0" >
              <thead>
              <input type="hidden" id="pageNumber" name="pageNumber" value="${page.start}" />
                <th>客户名称</th>
                <th>关注人</th>
                <th>取消关注</th>
              </thead>
        <!--      =================表格循环===============-->
              <s:iterator id="customerUser" value="customerUserList" status="dl">
              <tr class="ui-table-style1-tr2">
                <td>${customerUser.customerName }</td>
                <td>${customerUser.userName }</td>
                <td><a href="customer!deleteCustomerUser?customerUserId=${customerUser.customerUserId }&customerId=${customerUser.customerId }">取消关注</a></td>
              </tr> 
              </s:iterator>
          <!--     =================表格循环over===============-->
          
            </table>
</div>

	
</body>
</html>



