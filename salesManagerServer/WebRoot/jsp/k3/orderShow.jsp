<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>销售订单信息查看</title>
	<!-- Bootstrap core CSS -->
    <link href="skin/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="skin/bootstrap/css/bootstrap-theme.css" rel="stylesheet">
    
    <!-- Just for debugging purposes. Don't actually copy this line! -->
    <!--[if lt IE 9]><script src="../../docs-assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.0/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
 
    <link type="text/css" href="<%=basePath %>skin/bootstrap/css/jquery-ui-1.10.0.custom.css" rel="stylesheet"  />
    <link type="text/css" href="<%=basePath %>skin/bootstrap/css/jquery.ui.1.10.0.ie.css" rel="stylesheet"  />
    <link type="text/css" href="<%=basePath %>skin/Default/css/base.css" rel="stylesheet"  />
    <link type="text/css" href="<%=basePath %>skin/Default/css/global.css" rel="stylesheet"  />
    <link type="text/css" href="<%=basePath %>skin/bootstrap/css/bootstrap-dialog.min.css" rel="stylesheet">
	
	<!-- jQuery核心JS -->
	<script type="text/javascript" src="<%=basePath %>skin/bootstrap/js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>skin/bootstrap/js/TweenLite.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>skin/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>skin/bootstrap/js/jquery-ui-1.10.0.custom.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>skin/bootstrap/js/bootstrap-dialog.min.js"></script>
</head>
<body class="ui-lv2bg">

<jsp:include page="/jsp/common/shopping_order_status.jsp" flush="true" />

<!--  单据头	 -->
<div class="panel panel-default" id="searchResult" style="padding:2px;">
<div class="panel-heading crm-table-title"><B>订单基本信息</B></div>
	<table class="ui-table-one" cellspacing="2" cellpadding="0" >
	  <tr> 
	    <td  class="ui-table-title">销售订单号：</td>
	    <td><s:property value="order.fBillNo"/></td>
	    <td  class="ui-table-title">购货单位：</td>
	    <td><s:property value="order.customerName"/></td>
	  </tr>
	  <tr>
	    <td  class="ui-table-title">交货日期：</td>
	    <td><s:date name="order.fFetchDate" format="yyyy-MM-dd"/></td>
	    <td  class="ui-table-title">日期：</td>
	    <td><s:date name="order.fDate" format="yyyy-MM-dd"/></td>
	  </tr>
	  <tr>
	    <td  class="ui-table-title">部门：</td>
	    <td><s:property value="order.departName"/></td>
	    <td  class="ui-table-title">业务员：</td>
	    <td><s:property value="order.employeeName"/></td>
	  </tr>
	  <tr>
	    <td  class="ui-table-title">主管：</td>
	    <td><s:property value="order.managerName"/></td>
	    <td  class="ui-table-title">审核人：</td>
	    <td><s:property value="order.checkerName"/></td>
	  </tr> 
	  <tr>
	    <td  class="ui-table-title">付款方式：</td>
	    <td><s:property value="order.fPayStyle"/></td>
	    <td  class="ui-table-title">付款日期：</td>
	    <td><s:date name="order.fPayDate" format="yyyy-MM-dd"/></td>
	  </tr>
	  <tr>
	    <td  class="ui-table-title">制单人：</td>
	    <td><s:property value="order.billerName"/></td>
	    <td  class="ui-table-title">是否开票：</td>
	    <td><s:property value="order.invoiceClosedName"/></td>
	  </tr>
	  <tr>
	    <td  class="ui-table-title">单据类型：</td>
	    <td><s:property value="order.transTypeName"/></td>
	    <td  class="ui-table-title">币种：</td>
	    <td><s:property value="order.currencyName"/></td>
	  </tr> 
	  <tr>
	    <td  class="ui-table-title">是否作废：</td>
	    <td><s:property value="order.cancellationName"/></td>
	    <td  class="ui-table-title">状态：</td>
	    <td><s:property value="order.statusName"/></td>
	  </tr>
	  <tr>
	    <td  class="ui-table-title">是否关闭：</td>
	    <td><s:property value="order.closedName"/></td> 
	    <td  class="ui-table-title">采购订单号：</td>
	    <td><s:property value="order.fPOOrdBillNo"/></td> 
	  </tr>
  </table>
</div>
<!--  查询结果列表 -->
<div class="panel panel-default" id="searchResult" style="padding:2px;">
<div class="panel-heading crm-table-title"><B>订单物料列表</B></div>
    <table class="table table-hover" cellspacing="2" cellpadding="0" >
      <thead>
        <th>物料名称</th>
        <th>规格型号</th>
        <th>单位</th>
        <th>单价</th>
        <th>数量</th>
        <th>税率(%)</th> 
        <th>折扣</th>
        <th>折扣金额</th>
        <th>折后金额</th> 
        <th>发票数量</th>
        <th>摘要</th>
        <th>日期</th> 
        <th>建议交货日期</th>
        <th>发货数量</th> 
        <th>出库数量</th>
      </thead>
      <s:iterator value="order.orderEntryList" status="dl">
      <tr> 
        <td><s:property value="productModel"/></td>
        <td><s:property value="productName"/></td>
        <td><s:property value="unitName"/></td>
        <td><s:text name="global.format.money02"><s:param value="fPrice"/></s:text></td>
        <td><s:property value="fQty" /></td>
        <td><s:property value="fCess"/></td> 
        <td><s:property value="fDiscount"/></td>
        <td><s:property value="fDiscountAmount"/></td> 
        <td><s:property value="fFinalAmount"/></td>
        <td><s:property value="fInvoiceQty"/></td> 
        <td><s:property value="fNote"/></td> 
        <td><s:date name="fDate" format="yyyy-MM-dd"/></td>
        <td><s:date name="fAdviceConsignDate" format="yyyy-MM-dd"/></td> 
        <td><s:property value="fCommitQty"/></td>
        <td><s:property value="fStockQty"/></td> 
      </tr>
      </s:iterator>
    </table>
</div>

</body>
</html>