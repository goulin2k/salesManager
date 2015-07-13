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
<title>采购订单信息查看</title>
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
<div class="panel-heading crm-table-title"><B>采购订单基本信息</B></div>
<table class="ui-table-one" cellspacing="2" cellpadding="0" >

  <tr> 
    <td  class="ui-table-title">单据编号：</td>
    <td><s:property value="order.fBillNo"/></td>
    <td  class="ui-table-title">单据类型：</td>
    <td><s:property value="order.fTranType"/></td>
  </tr>
  <tr> 
    <td  class="ui-table-title">供应商：</td>
    <td><s:property value="order.supplierName"/></td>
    <td  class="ui-table-title">日期：</td>
    <td><s:date name="order.fDate" format="yyyy-MM-dd"/></td> 
  </tr>
  <tr>
    <td  class="ui-table-title">部门：</td>
    <td><s:property value="order.departName"/></td>
    <td  class="ui-table-title">申请人：</td>
    <td><s:property value="order.requsterUserName"/></td>
  </tr>
  <tr>
    <td  class="ui-table-title">制单人：</td>
    <td><s:property value="order.billerName"/></td>
    <td  class="ui-table-title">业务员：</td>
    <td><s:property value="order.requsterUserName"/></td>
  </tr> 
  <tr>
    <td  class="ui-table-title">审核人：</td>
    <td><s:property value="order.checkerName"/></td>
    <td  class="ui-table-title">审核日期：</td>
    <td><s:date name="order.fCheckTime" format="yyyy-MM-dd"/></td>
  </tr>
  <tr>
    <td  class="ui-table-title">源单类型：</td>
    <td><s:property value="order.fSelTranType"/></td>
    <td  class="ui-table-title">源单分录号：</td>
    <td><s:property value="order.fSCBillInterID"/></td>
  </tr>
  <tr>
    <td  class="ui-table-title">关联标志：</td>
    <td><s:property value="order.fChildren"/></td>
    <td  class="ui-table-title">作废标志：</td>
    <td><s:property value="order.cancellationName"/></td>
  </tr> 
  <tr>
    <td  class="ui-table-title">是否关闭：</td>
    <td><s:property value="order.closedName"/></td>
    <td  class="ui-table-title">单据状态：</td>
    <td><s:property value="order.statusName"/></td>
  </tr>  
</table>
</div>

<!--  单据体列表	 -->
<div class="panel panel-default" id="searchResult" style="padding:2px;">
<div class="panel-heading crm-table-title"><B>订单物料列表</B></div>
    <table  class="table table-hover" cellspacing="2" cellpadding="0" >
      <thead>
        <th>物料名称</th>
        <th>规格型号</th>
        <th>申请数量</th>
        <th>计量单位</th>
        <th>审批数量</th>
        <th>单价</th>
        <th>价税合计</th>
        
        <th>当前库存</th>
        
        <th>源单单号</th>
        <th>源单类型</th>
        <th>客户名称</th>
        <th>申请人</th>  
      </thead>
      <s:iterator value="order.orderEntryList" status="dl">
	      <tr> 
	        <td><s:property value="pruductModel"/></td>
	        <td><s:property value="productName"/></td>
	        <td><s:property value="fQty"/></td>
	        <td><s:property value="unitName"/></td>
	        <td><s:property value="fCommitQty"/></td>
	        <td><s:text name="global.format.money02"><s:param value="fPrice"/></s:text></td>
	        <td><s:text name="global.format.money02"><s:param value="allAmount"/></s:text></td> 
	        
	        <td><s:property value="inventoryAmount"/></td>
	        <td><s:property value="fSourceBillNo"/></td>
	        <td><s:property value="tranTypeName"/></td> 
	        <td><s:if test="customerName != null && customerName.length>8)">
	        		<s:property value="customerName.substring(0,8)+'...'"/>
	        	</s:if><s:else><s:property value="customerName"/></s:else></td> 
	        <td><s:property value="requestUserName"/></td> 
	      </tr>
      </s:iterator>
    </table>
</div>

</body>
</html>