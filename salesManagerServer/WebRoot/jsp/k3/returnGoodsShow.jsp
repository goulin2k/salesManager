<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>退货通知单</title>
<script type="text/javascript" src="<%=basePath %>script/common/jquery-1.4.2.min.js" ></script>
<script type="text/javascript" src="<%=basePath %>script/common/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath %>script/common/easyui/validate.js"></script>
<script type="text/javascript" src="<%=basePath %>script/common/easyui/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath %>script/common/easyui/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>script/common/easyui/themes/default/easyui.css" />
<link href="<%=basePath %>skin/Default/css/base.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>skin/Default/css/global.css" rel="stylesheet" type="text/css" /> 
</head>
<body>
<div class="ui-content-subnav">
	<ul>
    	<li><a href="#"><img src="skin/Default/images/ui-content-ico/Summary.jpg" /><p>销售报价单</p></a></li>
    	<li><a href="#"><img src="skin/Default/images/ui-content-ico/Summary.jpg" /><p>销售订单</p></a></li> 
        <li><a href="#"><img src="skin/Default/images/ui-content-ico/verify.jpg" /><p>采购申请单</p></a></li>
        <li><a href="#"><img src="skin/Default/images/ui-content-ico/add.jpg" /><p>采购入库单</p></a></li> 
        <li><a href="#"><img src="skin/Default/images/ui-content-ico/add.jpg" /><p>发货通知单</p></a></li>
        <li><a href="#"><img src="skin/Default/images/ui-content-ico/add2.jpg" /><p>采购出库单</p></a></li>
        <li><a href="#"><img src="skin/Default/images/ui-content-ico/set.jpg" /><p>销售发票</p></a></li>
        <li><a href="#"><img src="skin/Default/images/ui-content-ico/add2.jpg" /><p>销售收款单</p></a></li> 
    </ul>
</div>
<div class="ui-table ml15">
<table class="ui-table-one" cellspacing="2" cellpadding="0" >
<caption>退货通知单</caption>
  <tr> 
    <td  class="ui-table-title">单据编号：</td>
    <td><s:property value="returnGoods.fBillNo"/></td>
    <td  class="ui-table-title">部门：</td>
    <td><s:property value="returnGoods.departName"/></td>
  </tr>
  <tr>
    <td  class="ui-table-title">业务员：</td>
    <td><s:property value="returnGoods.employeeName"/></td>
    <td  class="ui-table-title">制单人：</td>
    <td><s:property value="returnGoods.billerName"/></td>
  </tr>
  <tr>
    <td  class="ui-table-title">审核人：</td>
    <td><s:property value="returnGoods.checkerName"/></td>
    <td  class="ui-table-title">审核日期：</td>
    <td><s:date name="returnGoods.fCheckDate" format="yyyy-MM-dd"/></td>
  </tr>
  <tr>
    <td  class="ui-table-title">记账人：</td>
    <td><s:property value="returnGoods.posterName"/></td>
    <td  class="ui-table-title">验收人：</td>
    <td><s:property value="returnGoods.fmgrName"/></td>
  </tr>
  <tr>
    <td  class="ui-table-title">保管人：</td>
    <td><s:property value="returnGoods.smgrName"/></td>
    <td  class="ui-table-title">币别：</td>
    <td><s:property value="returnGoods.currencyName"/></td>
  </tr>
  <tr>
    <td  class="ui-table-title">销售方式：</td>
    <td><s:property value="returnGoods.saleStyleName"/></td>
    <td  class="ui-table-title">作废标志：</td>
    <td><s:property value="returnGoods.cancellationName"/></td>
  </tr>
  <tr>
    <td  class="ui-table-title">主管：</td>
    <td><s:property value="returnGoods.mananerName"/></td>
    <td  class="ui-table-title">fDate：</td>
    <td><s:date name="returnGoods.fDate" format="yyyy-MM-dd"/></td>
  </tr> 
  <tr>
    <td  class="ui-table-title">备注：</td>
    <td><s:property value="returnGoods.fNote"/></td>
  </tr>  
  <tr>
    <td  class="ui-table-title">单据体：</td>
    <td colspan="3">
    <!-- ==============嵌入的子表格样式================-->
    <table  class="ui-table-two" cellspacing="2" cellpadding="0" >
      <tr class="ui-table-title-two">
        <td>物料编码</td>
        <td>物料名称</td>
        <td>规格型号</td>
        <td>单位</td>
        <td>单价</td>
        <td>数量</td>
        <td>金额</td>
        <td>订单单号</td> 
        <td>入库单内码</td>
        <td>源单单号</td>   
        <td>源单类型</td>   
      </tr>
      <s:iterator value="returnGoods.returnGoodsEntryList" status="dl">
      <tr> 
        <td><s:property value="productNumber"/></td>
        <td><s:property value="productName"/></td>
        <td><s:property value="productModel"/></td>
        <td><s:property value="unitName"/></td>
        <td><s:property value="fPrice"/></td>
        <td><s:property value="fQty"/></td>
        <td><s:property value="fAmount"/></td>
        <td><s:property value="fOrderBillNo"/></td> 
        <td><s:property value="fInStockID"/></td>
        <td><s:property value="fSourceBillNo"/></td>  
        <td><s:property value="tranTypeName"/></td>  
      </tr>
      </s:iterator>
    </table>

    </td>
  </tr>  
</table>
</div>

</body>
</html>