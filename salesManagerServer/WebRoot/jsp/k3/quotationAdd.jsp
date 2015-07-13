<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sales" uri="/WEB-INF/sales-util.tld"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>询价单新增</title>


<script type="text/javascript" language="javascript" src="<%=basePath %>script/common/common.js"></script>
<script type="text/javascript" src="<%=basePath %>script/common/easyui-1.3.5/plugins/jquery.parser.js"></script>
<script type="text/javascript" src="<%=basePath %>script/common/easyui-1.3.5/jquery.easyui.min.js"></script>

<link rel="stylesheet" type="text/css" href="<%=basePath %>script/common/easyui-1.3.5/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>script/common/easyui-1.3.5/themes/default/easyui.css" /> 

<script type="text/javascript"> 
    
</script>
</head>

<body class="ui-lv2bg">
<div class="ui-content-title" align="center"><H3>新增询价单</H3></div>
<input type="hidden" id="basepath" value="<%=basePath %>" />
<form id="quotation" class="form-horizontal" name="quotation" action="<%=basePath %>quotation!add" method="post" >
<div class="panel panel-default" id="searchResult" style="padding:2px;">
<div class="panel-heading crm-table-title"><B>基本信息</B></div>	


<table class="ui-table-one" cellspacing="2" cellpadding="0" >

  <tr>
    <td class="ui-table-title">询价单号：</td>
    <td><s:property value="quotation.quotationCode"/></td>  
  </tr>
  <tr> 
    <td  class="ui-table-title">购货单位：</td>
    <td><input type="hidden" name="quotation.customerId" id="customerId" value="" />
    <input class="form-control" placeholder="选择客户" id="customerName" value="" onClick="getCustomer();" required/></td>
  </tr>
  <tr>
    <td  class="ui-table-title">是否开票：</td>
    <td>
    	<div >
    	<div class="radio col-sm-3">
			<label><input type="radio" name="quotation.issued" id="optionsRadios1" value="1" checked>开票 </label>
		</div>
		<div class="radio col-sm-3">
		  	<label><input type="radio" name="quotation.issued" id="optionsRadios2" value="0">不开票</label>
		</div>
		</div>
    </td>
  </tr> 
  <tr>
    <td  class="ui-table-title">询价采购人员：</td>
    <td>
    	<s:select theme="simple" name="quotation.purchaseUserId" 
    		listKey="userId" listValue="userName" list="purchaseList" cssClass="form-control"/></td>
  </tr> 
  <tr>
    <td  class="ui-table-title">物料选择：</td>
    <td>
    	<input type="button" value="选择物料" id="newProduct" class="btn btn-warning col-sm-2" onClick="getProducts();"></input></td>
  </tr> 
  <tr>
    <td  class="ui-table-title">备注：</td>
    <td >
  		<textarea name="quotation.comment" rows="4" class="form-control" placeholder="输入询价申请备注信息"></textarea>
    </td>
  </tr>
  <tr> 
</table>
</div>

<div class="panel panel-default" id="searchResult" style="padding:2px;">
     <div class="panel-heading crm-table-title"><B>物料列表</B></div>
    
    <!-- ==============嵌入的子表格样式================-->
    <table id="productTable" class="ui-table-two" cellspacing="2" cellpadding="0" >
      <tr class="ui-table-title-two f13">
        <td>物料代码</td>
        <td>产品名称</td>
        <td>规格型号</td>
        <td>单位</td>
        <td>数量</td> 
	    <td>操作</td> 
      </tr> 
    </table> 
    
</div>

<!--  按钮面板  -->
	<div class="crm-button-panel form-group ">
		<div class="col-sm-6">
			<a class="btn btn-primary col-sm-offset-9 col-sm-3" id="submit">确定</a>
		</div>
		<div class="col-sm-6">
			<a class="btn btn-warning col-sm-3" id="cancel">取消</a>
		</div>
	</div>
</form>

<script type="text/javascript" language="javascript" src="<%=basePath %>script/jsp/quotationAdd.js"></script>
</body>
</html>