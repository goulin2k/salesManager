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
	<title>询价单回复</title>
	
	<script type="text/javascript" language="javascript" src="<%=basePath %>script/common/common.js"></script>
	<script type="text/javascript" src="<%=basePath %>script/common/easyui-1.3.5/plugins/jquery.parser.js"></script>
	<script type="text/javascript" src="<%=basePath %>script/common/easyui-1.3.5/jquery.easyui.min.js"></script>
	
	<link rel="stylesheet" type="text/css" href="<%=basePath %>script/common/easyui-1.3.5/themes/icon.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath %>script/common/easyui-1.3.5/themes/bootstrap/easyui.css" /> 
	<script type="text/javascript" src="script/common/easyui/validate.js"></script>
	<script type="text/javascript" src="script/common/easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript">
		
	</script>
</head>
<body class="ui-lv2bg">
	<div class="ui-content-title" align="center"><H3>新增询价单</H3></div>
	
	<form id="quotation" name="quotation" action="<%=basePath %>quotation!update" method="post">
	<div class="ui-table ml15">
	<table class="ui-table-one" cellspacing="2" cellpadding="0" >
	  <tr>
	    <td  class="ui-table-title">询价单号：</td>
	    <td><s:property value="quotation.quotationCode"/>
	    <input type="hidden" name="quotation.quotationId" value="<s:property value="quotation.quotationId"/>"></input>
	    <input type="hidden" name="quotation.quotationCode" value="<s:property value="quotation.quotationCode"/>"></input></td>
	    <td  class="ui-table-title">购货单位：</td>
	    <td><s:property value="quotation.customerName"/></td>  
	  </tr>
	  <tr>
	    <td  class="ui-table-title">是否开票：</td>
	    <td><div class="ui-button-big fl ">
	    	<s:if test="quotation.issued == 1">开票</s:if><s:if test="quotation.issued == 0">不开票</s:if></div></td>
	  </tr> 
	  <tr>
	    <td  class="ui-table-title">询价人：</td>
	    <td><s:property value="quotation.quotationUserName"/></td>
	    <td  class="ui-table-title">询价时间：</td>
	    <td><s:date name="quotation.quotationTime" format="yyyy-MM-dd HH:mm:ss"/></td>
	  </tr>
	  <tr>
	    <td  class="ui-table-title">备注：</td>
	    <td colspan="4"  class="ui-table-textarea">
	  		<textarea name="quotation.comment" rows="4" class="easyui-validatebox" validType="maxLength['备注',200]"><s:property value="quotation.comment"/></textarea>
	    </td>
	  </tr>
	  <tr> 
	    <td colspan="4">
	    <!-- ==============嵌入的子表格样式================-->
	    <table id="productTable" class="ui-table-4" cellspacing="2" cellpadding="0" >
	      <tr class="ui-table-title-4">
	        <td>物料名称</td> 
	        <td>规格型号</td>
	        <td>单位</td>
	        <td>数量</td>  
	        <td>含税价格</td> 
	        <td>包装规格</td>
	        <td>预估物流费用</td>
	        <td>最小起订量</td> 
	        <td>货期</td>  
	      </tr> 
			<!--      =================表格循环===============-->
		  <s:iterator value="quotation.quotationProductList" status="dl">
			<tr class="ui-table-style1-tr2"><input type="hidden" name="products[<s:property value="#dl.index"/>].quotationProductId" value="<s:property value="quotationProductId"/>"/>
			  <td id="demo-tip-darkgray" title='<s:property value="productName"/>+<s:property value="productName"/>'><s:property value="productName"/></td>
			  <td id="demo-tip-darkgray" title='<s:property value="productModel"/>+<s:property value="productModel"/>'><s:property value="productModel"/></td>
			  <td><s:property value="measureUnitName"/></td>
			  <td><s:property value="num"/></td> 
			  <td><input type="text" name="products[<s:property value="#dl.index"/>].price" class='easyui-numberbox w40' required='true' precision='2' min='0' max='1000000000' value="<s:property value="price"/>"/></td> 
			  <td><input type="text" name="products[<s:property value="#dl.index"/>].packModel" class="easyui-validatebox w60" validType="maxLength['包装规格', 50]" required='true' value="<s:property value="packModel"/>"/></td> 
			  <td><input type="text" name="products[<s:property value="#dl.index"/>].logisticsCost" class='easyui-numberbox w40' required='true' precision='2' min='0' max='1000000000' value="<s:property value="logisticsCost"/>"/></td> 
			  <td ><input type="text" name="products[<s:property value="#dl.index"/>].minQty" class='easyui-numberbox w40' precision='0' min='0' max='1000000' required='true' value="<s:property value="minQty"/>"/></td> 
			  <td><input type="text" name="products[<s:property value="#dl.index"/>].futures" class="easyui-validatebox w40" validType="maxLength['货期', 50]" value="<s:property value="futures"/>"/></td>  
			</tr>
		  </s:iterator>
	    </table> 
	    </td>
	  </tr>  
	</table>
	</div>
	
	<!--  按钮面板  -->
	<div class="crm-button-panel form-group ">
		<div class="col-sm-6">
			<a class="btn btn-primary col-sm-offset-9 col-sm-3" id="ok"/>确定</a>
		</div>
		<div class="col-sm-6">
			<a class="btn btn-warning col-sm-3" id="cancel">取消</a>
		</div>
	</div>
	</form>

<script type="text/javascript" language="javascript" src="<%=basePath %>script/jsp/quotationUpdate.js"></script>
</body>

</html>