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
<title>客户潜力产品</title>

<script type="text/javascript" src="<%=basePath %>script/common/jquery-1.4.2.min.js" ></script>
<link rel="stylesheet" href="<%=basePath %>script/common/autocomplete/jquery.autocomplete.css" type="text/css"></link> 
<link href="skin/Default/css/base.css" rel="stylesheet" type="text/css" />
<link href="skin/Default/css/global.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="script/common/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="script/common/easyui/validate.js"></script>
<script type="text/javascript" src="script/common/easyui/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" type="text/css" href="script/common/easyui/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="script/common/easyui/themes/default/easyui.css" />

<script type="text/javascript">
	$(document).ready(function(){  
	    $("#submit").click(function(checkEvent){
	    	check();
	    });
		$("#cancel").click(function(checkEvent){
	    	checkEvent.preventDefault();
	    	history.back();	
		});
	});
    
	function check() {
		if ($('#pproductForm').form('validate')) {
			pproductForm.submit();
		}
	}
</script>

</head>
<!-- jQuery核心JS -->

<body class="ui-lv2bg">
	<div class="ui-content-title"><p><img src="skin/Default/images/ui-content-ico/man.gif" />修改客户潜力产品信息</p></div>
   <!--======内容区域子导航======--> 
  
<!--======搜索======-->
        <div class="clear"></div>
       

 <!--======表格样式1======--> 
 <div class="ui-table ml15">
  <s:form id="pproductForm" name="pproductForm" action="pproduct!save" theme="simple" namespace="/" method="post" validate="false">
	<table class="ui-table-one" cellspacing="2" cellpadding="0" >
	<caption class="mt30"><img src="skin/Default/images/ui-content-ico/add.png"/ class="mr8">客户潜力产品信息</caption>
	  <input type="hidden" name="pproduction.potentialProductId" id="potentialProductId" value="<s:property value="pproduction.potentialProductId"/>"/>
	  <input type="hidden" name="pproduction.customerId" id="customerId" value="<s:property value="pproduction.customerId"/>"/>  
	  <input type="hidden" name="pproduction.customerName" id="customerName" value="<s:property value="pproduction.customerName"/>"/>
	  <tr>
	  	<td class="ui-table-title">客户名称</td>
	  	<td><s:property value="pproduction.customerName"/></td>
	  </tr>
	  <tr>
	  	<td class="ui-table-title">潜力产品描述</td>
	    <td  class="ui-table-textarea" colspan="1">
	  		<textarea name="pproduction.productDescription" rows="6" class="easyui-validatebox" 
	  			type="text" required="true" validType="maxLength['备注',500]"><s:property value="pproduction.productDescription"/></textarea>
	    </td>
	  </tr>
	  <tr>
	  	<td class="ui-table-title">历史记录</td>
	  	<td></td>
	  </tr>
	
	</table>
	
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



