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
<title>销售收款单信息</title>
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
<div class="panel-heading crm-table-title"><B>销售收款单基本信息</B></div>
<table class="ui-table-one" cellspacing="2" cellpadding="0" >
  <tr> 
    <td  class="ui-table-title">单据号：</td>
    <td><s:property value="receiveBill.fNumber"/></td>
    <td  class="ui-table-title">状态：</td>
    <td><s:property value="receiveBill.fBillStatus"/></td>
  </tr>
  <tr>
    <td  class="ui-table-title">日期：</td>
    <td><s:date name="receiveBill.fDate" format="yyyy-MM-dd"/></td>
    <td  class="ui-table-title">财务日期：</td>
    <td><s:date name="receiveBill.fFincDate" format="yyyy-MM-dd"/></td>
  </tr>
  <tr>
    <td  class="ui-table-title">结算核算项目：</td>
    <td><s:property value="receiveBill.fItemClassID_DSPName"/></td>
    <td  class="ui-table-title">客户名称：</td>
    <td><s:property value="receiveBill.customerName"/></td>
  </tr>
  <tr>
    <td  class="ui-table-title">部门：</td>
    <td><s:property value="receiveBill.departName"/></td>
    <td  class="ui-table-title">业务员：</td>
    <td><s:property value="receiveBill.employeeName"/></td>
  </tr> 
  <tr>
    <td  class="ui-table-title">币别：</td>
    <td><s:property value="receiveBill.currencyName"/></td>
    <td  class="ui-table-title">单据金额：</td>
    <td><s:property value="receiveBill.fAmountFor"/></td>
  </tr>  
  <tr>
    <td  class="ui-table-title">审核人：</td>
    <td><s:property value="receiveBill.checkerName"/></td>  
  </tr>
  </table>
</div>

<!--  单据体列表	 -->
<div class="panel panel-default" id="searchResult" style="padding:2px;">
<div class="panel-heading crm-table-title"><B>收款单物料明细</B></div>
    <table  class="table table-hover" cellspacing="2" cellpadding="0" > 
      <thead>  
        <th>订单号</th>
        <th>收付款金额</th>
        <th>退款单关联金额</th>
        <th>结算数量</th>  
        <th>实收金额</th>         
        <th>结算折扣</th>
        <th>物料名称</th>
        <th>规格型号</th>         
        <th>单位</th>
        <th>数量</th> 
        <th>含税单价</th>
        <th>选单单据金额</th> 
        <th>单据余额</th>         
      </tr>
      <s:iterator value="receiveBill.receiveBillEntryList" status="dl">
      <tr> 
        
        <td><s:property value="fEntryOrderNo"/></td>
        
        <td><s:property value="fReceiveAmountFor_3"/></td>
        
        <td><s:property value="fBackAmountFor_Relative"/></td>
        
        <td><s:property value="fSettleQuantity"/></td> 
        <td><s:property value="fSettleAmount_3"/></td>          
        <td><s:property value="fDiscount"/></td> 
        
        <td><s:property value="productModel"/></td> 
        <td><s:property value="productName.substring(0,4)+'...'"/></td> 
                 
        <td><s:property value="measureUnitName"/></td>
        <td><s:property value="fQuantity"/></td>  
        <td><s:property value="fTaxPrice"/></td> 
        <td><s:property value="famount_SRC"/></td>  
        <td><s:property value="fRemainAmountEntry"/></td>  
      </tr>
      </s:iterator>
    </table>
</table>
</div>

</body>
</html>