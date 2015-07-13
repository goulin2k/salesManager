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
<title>新增客户联系人</title>
<link href="skin/Default/css/base.css" rel="stylesheet" type="text/css" />
<link href="skin/Default/css/global.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" language="javascript" src="<%=basePath %>script/common/common.js"></script>

<script type="text/javascript" >
$(document).ready(function(){ 
	$( "#birthday" ).datepicker({
		dateFormat: "yy-mm-dd"
	});
	
    $("#sumbit").click(function(checkEvent){
    	if($('#linkmanForm').form('validate')){
    		$('#linkmanForm').submit();
    	}
	});
    
    $("#cancel").click(function(checkEvent){
    	checkEvent.preventDefault();
    	history.back();
    });
});
	
	function getCustomer(){ 
	var array = window.showModalDialog("<%=basePath %>/customer!customerOpenWindow", "", "dialogWidth:800px; dialogHeight:600px; status:no;help:no");
	//for chrome
    if (array == undefined) {
    	array = window.returnValue;
    }
	$("#customerId").val(array[0]);
	$("#customerName").val(array[1]);
}
	</script>
</head>
<!-- jQuery核心JS -->

<body class="ui-lv2bg">
	
 <!--======表格样式1======--> 
 <div class="ui-table ml15">
  <s:form id="linkmanForm" name="linkmanForm" action="customerLinkman!addLinkmanFC" theme="simple" namespace="/" method="post" validate="false">
<table class="ui-table-one" cellspacing="2" cellpadding="0" >
<caption class="mt30">联系人信息</caption>
  <tr>
    <td class="ui-table-title">对应客户：</td>
    <td class="ui-table-input-r">
    <input name="linkman.customerId" id="customerId" type="hidden" value="${customerId }"/>
    <input name="customerName" id="customerName" type="text" readonly="readonly" value="${customerName }"/></td>
    <td class="ui-table-title">姓名：</td>
    <td class="ui-table-input-r">
    	<input name="linkman.name" id="name" type="text" class="easyui-validatebox" required="true"/></td>
  </tr>
  <tr>
    <td class="ui-table-title">职位：</td>
    <td class="ui-table-input-r"><input name="linkman.station" id="station" type="text" class="easyui-validatebox"/></td>
    <td class="ui-table-title">部门：</td>
    <td class="ui-table-input-r"><input name="linkman.department" id="department" type="text" class="easyui-validatebox"/></td>
  </tr>
  <tr>
    <td class="ui-table-title">手机：</td>
    <td class="ui-table-input-r"><input name="linkman.mobile" id="mobile" type="text" class="easyui-validatebox"/></td>
    <td class="ui-table-title">电话：</td>
    <td class="ui-table-input-r"><input name="linkman.phone" id="phone" type="text" class="easyui-validatebox"/></td>
  </tr>
  <tr>
  	<td class="ui-table-title">传真：</td>
    <td colspan="3" class="ui-table-input-r"><input name="linkman.fax" id="fax" type="text" class="easyui-validatebox"/></td>
  </tr>
  <tr>
    <td class="ui-table-title">邮件：</td>
    <td class="ui-table-input-r"><input name="linkman.email" id="email" type="text" class="easyui-validatebox"/></td>
    <td class="ui-table-title">生日：</td>
    <td class="ui-table-input-r"><input class="easyui-validatebox" name="linkman.birthday" id="birthday" type="text" /></td>
  </tr>
  <tr>
  	<td class="ui-table-title">兴趣爱好：</td>
  	<td colspan="3" class="ui-table-input-r">
  		<input class="easyui-validatebox" style="width:100%;" name="linkman.hobbies" id="hobbies" type="text" /></td>
  </tr>
  <tr>
    <td class="ui-table-title">与公司关系：</td>
    <td class="ui-table-input-r">
    <select name="linkman.relationUs" id="elationUs" class="easyui-validatebox form-control" required="true">
	    <option value="">请选择关系</option>
	    <s:iterator id="enumeration" value="enumerationList" status="dl">
	    <option value="${enumeration.enumerationId }">${enumeration.enumerationName }</option>
	    </s:iterator>
    </select>
    
    <td class="ui-table-title">与竞争对手关系：</td>
    <td class="ui-table-input-r">
     	<select name="linkman.relationComp" id="relationComp" class="easyui-validatebox form-control" required="true">
		    <option value="">请选择关系</option>
		    <s:iterator id="enumeration" value="enumerationList" status="dl">
		    <option value="${enumeration.enumerationId }">${enumeration.enumerationName }</option>
		    </s:iterator>
    	</select>
    </td>
  </tr>

</table>

	<div class="crm-button-panel form-group ">
		<div class="col-sm-6">
			<button class="btn btn-primary col-sm-offset-9 col-sm-3" id="sumbit">确定</button>
		</div>
		<div class="col-sm-6">
			<button class="btn btn-warning col-sm-3" id="cancel">取消</button>
		</div>
	</div>
</s:form>
</div>
</body>
</html>



