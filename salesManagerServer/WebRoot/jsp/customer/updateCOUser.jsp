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
<title>修改客户分配人</title>
<link href="skin/Default/css/base.css" rel="stylesheet" type="text/css" />
<link href="skin/Default/css/global.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" language="javascript" src="<%=basePath %>script/common/common.js"></script>
<script type="text/javascript" >
$(document).ready(function(){  
	$("#submit").click(function(checkEvent){
		if($('#couserForm').form('validate')){
			$("#couserForm").submit();
		}
	});
	
	$("#cancel").click(function(checkEvent){
    	checkEvent.preventDefault();
    	history.back();
    });
});
	
	function getCustomer(){ 
	var array = window.showModalDialog("<%=basePath %>/customer!customerOpenWindow", "", "dialogWidth:800px; dialogHeight:500px; status:no;help:no");
	//for chrome
    if (array == undefined) {
    	array = window.returnValue;
    }
	$("#customerId").val(array[0]);
	$("#customerName").val(array[1]);
	$("#userId").val(array[2]);
	$("#userName").val(array[3]);
	$("#salegenId").val(array[4]);
	$("#salegenName").val(array[5]);
	$("#finmanagerId").val(array[6]);
	$("#finmanagerName").val(array[7]);
	$("#customerOwnerUserId").val(array[8]);
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
	
 <!--======表格样式1======--> 
 <div class="ui-table ml15">
  <s:form id="couserForm" name="couserForm" action="customer!updateCOUser" theme="simple" namespace="/" method="post" validate="false">
<table class="ui-table-one" cellspacing="2" cellpadding="0" >
<caption class="mt30">修改客户分配人</caption>
  <tr>
  <td class="ui-table-title">客户：</td>
    <td class="ui-table-heed">
    <input name="couser.customerOwnerUserId" id="customerOwnerUserId"  type="hidden" 	value="<s:property value="couser.customerOwnerUserId"/>"/>
	    <input name="couser.customerId" id="customerId"  type="hidden" 	value="<s:property value="couser.customerId"/>"/>
	    <input name="customerName" id="customerName" type="text" value="<s:property value="couser.customerName"/>" 
	    	class="easyui-validatebox" required="true" readonly="readonly"/>
    </td>
    
    <td class="ui-table-title">总经理（副）：</td>
    <td class="ui-table-heed">
    <input name="couser.salegenId" id="salegenId" type="hidden" value="<s:property value="couser.salegenId"/>"/>
    <input name="couser.salegenName" id="salegenName" type="text" onClick="getSalegen();" value="<s:property value="couser.salegenName"/>" readonly="readonly"/>
    </td>
  
      
  </tr>
  <tr>
  
    <td class="ui-table-title">部门经理：</td>
    <td class="ui-table-heed">
    <input name="couser.finmanagerId" id="finmanagerId" type="hidden" value="<s:property value="couser.finmanagerId"/>"/>
    <input name="finmanagerName" id="finmanagerName" type="text" onClick="getFinmanager();" value="<s:property value="couser.finmanagerName"/>" readonly="readonly"/>
    </td> 
    
    <td class="ui-table-title">业务员：</td>
    <td class="ui-table-heed">
	    <input name="couser.userId" id="userId" type="hidden"  value="<s:property value="couser.userId"/>"/>
	    <input name="userName" id="userName" type="text" onClick="getUser();"  readonly="readonly"  value="<s:property value="couser.userName"/>" />
    </td> 
  </tr>
</table>

<!--  按钮面板  -->
	<div class="crm-button-panel form-group ">
		<div class="col-sm-6">
			<a class="btn btn-primary col-sm-offset-9 col-sm-3" id="submit">确定</a>
		</div>
		<div class="col-sm-6">
			<a class="btn btn-warning col-sm-3" id="cancel">取消</a>
		</div>
	</div>
</s:form>
</div>
</body>
</html>



