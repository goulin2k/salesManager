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
<title>询价单查看</title>
<script type="text/javascript">
	function addOrfq(quotationId, status) {
		if(status == 0){
			alert("采购回复后方可生成报价单！");
		}
		else if(status == 1){
			$("#toOrfq").attr("disabled", true);
			var returnValue = window.showModalDialog("<%=basePath %>/quotation!toOrfq?quotation.quotationId=" + quotationId, window, 
				"dialogWidth:800px; dialogHeight:600px; status:no;help:no;resizable:yes");
			//for chrome
			if (!returnValue) {
			    returnValue = window.returnValue;
			}		
			window.location.reload();
		}
	}
</script>
</head>
<body class="ui-lv2bg">
<!-- 标准工具栏 -->
<nav class="navbar navbar-default crm-toolbar" role="navigation">
	<ul class="nav navbar-nav">
    	<li class="active"><a href="javascript:history.back();"><span class="glyphicon glyphicon-arrow-left"></span>&nbsp;返回</a></li>
    	<li class="active"><a id="toOrfq" href="javascript:addOrfq(<s:property value="quotation.quotationId"/>,<s:property value="quotation.status"/>)"><span class="glyphicon glyphicon-usd"></span>&nbsp;下推报价单</a></li>
    	<li class="active"><a href="#"><span class="glyphicon glyphicon-remove"></span>&nbsp;删除</a></li>
    </ul>
</nav>

<!-- 基本信息面板 -->
<div class="panel panel-default" id="searchResult" style="padding:2px;">
<div class="panel-heading crm-table-title"><B>基本信息</B></div>	
<table class="ui-table-one" cellspacing="2" cellpadding="0" >

  <tr>
    <td  class="ui-table-title">询价单号：</td>
    <td><s:property value="quotation.quotationCode"/></td> 
    <td  class="ui-table-title">购货单位：</td>
    <td><s:property value="quotation.customerName"/></td> 
  </tr>
  <tr>
    <td  class="ui-table-title">询价人：</td>
    <td><s:property value="quotation.quotationUserName"/></td>
    <td  class="ui-table-title">询价时间：</td>
    <td><s:date name="quotation.quotationTime" format="yyyy-MM-dd HH:mm:ss"/></td>
  </tr>
  <tr>
    <td  class="ui-table-title">是否开票：</td>
    <td><s:if test="quotation.issued == 1">开票</s:if><s:if test="quotation.issued == 0">不开票</s:if></td> 
    <td  class="ui-table-title">状态：</td>
    <td><s:property value="quotation.statusName"/></td> 
  </tr>
  <tr>
    <td  class="ui-table-title">回复人：</td>
    <td><s:property value="quotation.purchaseUserName"/></td>
    <td  class="ui-table-title">回复时间：</td>
    <td><s:date name="quotation.replyTime" format="yyyy-MM-dd HH:mm:ss"/></td>
  </tr>
  <tr>
    <td  class="ui-table-title">备注：</td>
    <td  class="ui-table-textarea" colspan="3">
  		<textarea name="quotation.comment" rows="4" readonly><s:property value="quotation.comment"/></textarea>
    </td>
  </tr>
  <tr>
</table>
</div>
<div class="panel panel-default" id="searchResult" style="padding:2px;">
     <div class="panel-heading crm-table-title"><B>物料列表</B></div> 
    <!-- ==============嵌入的子表格样式================-->
    <table id="productTable" class="ui-table-two" cellspacing="2" cellpadding="0" >
      <tr class="ui-table-title-two">
        <td>物料代码</td>
        <td>产品名称</td>
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
		<tr class="ui-table-style1-tr2">
		  <td><s:property value="productCode"/></td>
		  <td><s:property value="productName"/></td> 
		  <td><s:property value="productModel"/></td>
		  <td><s:property value="measureUnitName"/></td>
		  <td><s:property value="num"/></td> 
		  <td><s:property value="price"/></td>  
		  <td><s:property value="packModel"/></td> 
		  <td><s:property value="logisticsCost"/></td> 
		  <td><s:property value="minQty"/></td> 
		  <td><s:property value="futures"/></td>    
		</tr>
	  </s:iterator>
    </table> 
</div> 
</body>
</html>