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
<title>发货通知单</title>
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
    <link type="text/css" href="<%=basePath %>skin/Default/css/fisheye.css" rel="stylesheet"  />
    <link type="text/css" href="<%=basePath %>skin/bootstrap/css/jquery-ui-1.10.0.custom.css" rel="stylesheet"  />
    <link type="text/css" href="<%=basePath %>skin/bootstrap/css/jquery.ui.1.10.0.ie.css" rel="stylesheet"  />
    <link type="text/css" href="<%=basePath %>skin/Default/css/base.css" rel="stylesheet"  />
    <link type="text/css" href="<%=basePath %>skin/Default/css/global.css" rel="stylesheet"  />
    <link href="skin/bootstrap/css/bootstrap-dialog.min.css" rel="stylesheet">
    

	
	<script type="text/javascript" src="<%=basePath %>skin/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>skin/bootstrap/js/jquery-ui-1.10.0.custom.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>skin/bootstrap/js/bootstrap-dialog.min.js"></script>
</head>
<body class="ui-lv2bg">
<!-- 标准工具栏 -->
<nav class="navbar navbar-default crm-toolbar" role="navigation">
	<ul class="nav navbar-nav">	
		<s:if test="saleBill.orderIdStr!=null">
			<li class="active"><a href="<%=basePath %>order!showAll?orderIdStr=<s:property value="saleBill.orderIdStr"/>">
						<span class="glyphicon glyphicon-arrow-left"></span>&nbsp;销售订单</a></li>
		</s:if>
		<li><a href="javascript:this.close();"><span class="glyphicon glyphicon-arrow-left"></span>&nbsp;关闭</a></li>
	</ul>
</nav>
<!--  单据头	 -->
<div class="panel panel-default" id="searchResult" style="padding:2px;">
<div class="panel-heading crm-table-title"><B>发货通知单基本信息</B></div>
<table class="ui-table-one" cellspacing="2" cellpadding="0" >

  <tr> 
    <td  class="ui-table-title">单据编号：</td>
    <td><s:property value="outStock.fBillNo"/></td>
    <td  class="ui-table-title">销售方式：</td>
    <td><s:property value="outStock.saleTypeName"/></td>
  </tr>
  <tr>
    <td  class="ui-table-title">购货单位：</td>
    <td><s:property value="outStock.customerName"/></td>
    <td  class="ui-table-title">收货仓库：</td>
    <td><s:property value="outStock.stockName"/></td>
  </tr>
  <tr>
    <td  class="ui-table-title">业务员：</td>
    <td><s:property value="outStock.employeeName"/></td>
    <td  class="ui-table-title">制单人：</td>
    <td><s:property value="outStock.billerName"/></td>
  </tr>
  <tr>
    <td  class="ui-table-title">审核人：</td>
    <td><s:property value="outStock.checkerName"/></td>
    <td  class="ui-table-title">审核日期：</td>
    <td><s:date name="outStock.fCheckDate" format="yyyy-MM-dd"/></td>
  </tr>  
  <tr>
    <td  class="ui-table-title">主管：</td>
    <td><s:property value="outStock.managerName"/></td>
    <td  class="ui-table-title">是否关闭：</td>
    <td><s:property value="outStock.closedName"/></td>
  </tr>
  <tr>
    <td  class="ui-table-title">发票关闭：</td>
    <td><s:property value="outStock.fInvoiceClosed"/></td>
    <td  class="ui-table-title">运输状态：</td>
    <td><s:property value="outStock.fTranStatus"/></td>
  </tr>
  <tr>
    <td  class="ui-table-title">单据状态：</td>
    <td><s:property value="outStock.statusName"/></td>
    <td  class="ui-table-title">作废标志：</td>
    <td><s:property value="outStock.cancellationName"/></td>
  </tr>  
  <tr>
    <td  class="ui-table-title">源单类型：</td>
    <td><s:property value="outStock.fPOOrdBillNo"/></td>
    <td  class="ui-table-title">承运单号：</td>
    <td><s:property value="outStock.fHeadSelfS0236"/></td>
  </tr>  
  <tr>
    <td  class="ui-table-title">承运商：</td>
    <td><s:property value="outStock.fHeadSelfS0237"/></td>
    <td  class="ui-table-title">日期：</td>
    <td><s:date name="outStock.fDate" format="yyyy-MM-dd"/></td>
  </tr>  
  <tr> 
    <td  class="ui-table-title">备注：</td>
    <td><s:property value="outStock.saleTypeName"/></td>
  </tr>   
  </table>
</div>

<!--  单据体列表	 -->
<div class="panel panel-default" id="searchResult" style="padding:2px;">
<div class="panel-heading crm-table-title"><B>发货通知单物料列表</B></div>
    <table  class="table table-hover" cellspacing="2" cellpadding="0" >  
      <thead>              
        <th>物料名称</th>
        <th>规格型号</th>
        <th>单位</th>
        <th>单价</th>
        <th>数量</th>
        <th>金额</th>
        <th>订单单号</th> 
        <th>源单类型</th>
        <th>源单单号</th>   
        <th>日期</th>
        <th>备注</th>   
      </thead>
      <s:iterator value="outStock.outStockEntryList" status="dl">
      <tr> 
        <td><s:property value="productModel"/></td>
        <td><s:property value="productName.substring(0,6)+'...'"/></td>
        
        <td><s:property value="unitName"/></td>
        <td><s:text name="global.format.money02"><s:param value="fPrice"/></s:text></td>
        <td><s:property value="fQty"/></td>
        <td><s:text name="global.format.money02"><s:param value="fAmount"/></s:text></td>
        <td><s:property value="fOrderBillNo"/></td> 
        <td><s:property value="tranTypeName"/></td>
        <td><s:property value="fSourceBillNo"/></td>  
        <td><s:date name="fDate" format="yyyy-MM-dd"/></td>
        <td><s:property value="fNote.substring(0,6)+'...'"/></td>  
      </tr>
      </s:iterator>
    </table>
</div>
</body>
</html>