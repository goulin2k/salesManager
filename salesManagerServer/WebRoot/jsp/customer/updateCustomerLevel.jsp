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
<title>客户等级评价</title>

<script type="text/javascript" >
	$(document).ready(function(){  
	    $("#submit").click(function(checkEvent){
	    	if($('#customerLevelForm').form('validate')){
				$("#customerLevelForm").submit();
	    	}
		});
	
	    $("#cancel").click(function(checkEvent){
	    	checkEvent.preventDefault();
	    	history.back();
	    });
	});
	
	</script>
</head>
<!-- jQuery核心JS -->

<body class="ui-lv2bg">
	
 <!--======表格样式1======--> 
 <div class="ui-table ml15">
  <s:form id="customerLevelForm" name="customerLevelForm" cssClass="form-horizontal" action="customer!updateCustomerLevel" theme="simple" namespace="/" method="post" validate="false">
<table class="ui-table-one" cellspacing="2" cellpadding="0" >
<caption class="mt30">修改客户等级</caption>
  <tr>
    <td class="ui-table-title">客户：</td>
    <td class="ui-table-input-r">
    <input name="customerLevel.customerId" id="customerId" type="hidden" value ="${customerId }"/>
    <input name="customerName" id="customerName" type="text" readonly="readonly"/ value ="${customerName }" 
    	class="easyui-validatebox" required="true"></td>
    <td class="ui-table-title">客户等级：</td>
    <td class="ui-table-input-r">
    <select name="customerLevel.levelId" id="levelId" class="easyui-validatebox" required="true">
	    <option value="">请选择客户等级</option>
	    <s:iterator id="enumeration" value="enumerationList" status="dl">
	    <option value="${enumeration.enumerationId }" ${enumeration.isSelect }>${enumeration.enumerationName }</option>
	    </s:iterator>
    </select>
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
</div>
</s:form>
</body>
</html>



