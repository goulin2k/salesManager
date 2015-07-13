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
<title>销售报价单审核</title>
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
    

		<script type="text/javascript" src="<%=basePath %>skin/bootstrap/js/jquery-1.8.3.min.js"></script>	
		<script type="text/javascript" src="<%=basePath %>skin/bootstrap/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="<%=basePath %>skin/bootstrap/js/jquery-ui-1.10.0.custom.min.js"></script>
		<script type="text/javascript" src="<%=basePath %>skin/bootstrap/js/bootstrap-dialog.min.js"></script>
<script type="text/javascript"> 
function disableA(){
	$("a").each(function() {
		$(this).replaceWith("<a style='cursor:hand'>"+$(this).html()+"</a>");
	});
}

function verify(isPassed) {
	
	if(isPassed) {
		$("#verifyStatus").val(isPassed);
	}else {
		$("#verifyStatus").val(isPassed);
	}
	//disableA();
	$("#saveButton").attr("href", "#");
	$("#orfqForm").submit();
	
	
}
</script>
</head>
<body class="ui-lv2bg">

<form id="orfqForm" name="orfqForm" action="<%=basePath %>orfq!verifySOrfq" method="post" target="hiddenFrame">
<!-- 标准工具栏 -->
<nav class="navbar navbar-default crm-toolbar" role="navigation">
	<input type="hidden" id="verifyStatus" name="orfq.fStatus"/>
	<ul class="nav navbar-nav">
		<li class="active"><a id="saveButton" href="javascript:verify(1);"><span class="glyphicon glyphicon-ok-sign"></span>&nbsp;通过</a></li>
	    <li class="active"><a href="javascript:verify(2);"><span class="glyphicon glyphicon-minus-sign"></span>&nbsp;不通过</a></li>
	    <li class="active"><a href="javascript:window.close();"><span class="glyphicon glyphicon-arrow-left"></span>&nbsp;退出</a></li>
    </ul>
   
</nav>	

<div class="panel panel-default" id="searchResult" style="padding:2px;">
	<div class="panel-heading crm-table-title"><B>报价单基本信息</B></div>
	<table class="ui-table-one" cellspacing="2" cellpadding="0" >
	  <tr>  
	    <td  class="ui-table-title">购货单位：</td>
	    <td><s:property value="orfq.customerName"/><input name="orfq.fInterID" id="fInterID" type="hidden" value="<s:property value="orfq.fInterID"/>" /></td>
	  </tr>
	  <tr>
	    <td  class="ui-table-title">制单人：</td>
	    <td><s:property value="orfq.billUserName"/></td>
	    <td  class="ui-table-title">日期：</td>
	    <td><s:date name="orfq.fdate" format="yyyy-MM-dd HH:mm:ss"/></td>
	  </tr>
	  <tr>
	    <td  class="ui-table-title">部门：</td>
	    <td><s:property value="orfq.departName"/></td>
	    <td  class="ui-table-title">业务员：</td>
	    <td><s:property value="orfq.employeeName"/></td>
	  </tr>
	  <tr>
	    <td  class="ui-table-title">币种：</td>
	    <td><s:property value="orfq.currencyName"/></td>
	    <td  class="ui-table-title">汇率：</td>
	    <td><s:property value="orfq.fExchangeRate"/></td>
	  </tr>
	  <tr>
	    <td  class="ui-table-title">开票方式：</td>
	    <td><s:property value="orfq.invoiceTypeName"/></td>
	    <td  class="ui-table-title">运输方式：</td>
	    <td><s:property value="orfq.transTypeName"/></td>
	  </tr>
	  <tr>
	    <td  class="ui-table-title">主管：</td>
	    <td><s:property value="orfq.managerName"/></td>
	    <td  class="ui-table-title">一级审核人：</td>
	    <td><s:property value="orfq.checkerName"/></td>
	  </tr>
	  <tr>
	    <td  class="ui-table-title">是否作废：</td>
	    <td><s:property value="orfq.cancellationName"/></td>
	    <td  class="ui-table-title">状态：</td>
	    <td><s:property value="orfq.statusName"/></td>
	  </tr>
	  <tr>
	    <td  class="ui-table-title">单据类型：</td>
	    <td><s:property value="orfq.transType"/></td>
	    <td  class="ui-table-title">付款条件：</td>
	    <td><s:property value="orfq.payCondition"/></td>
	  </tr> 
  	</table>
</div>
<div class="panel panel-default" id="searchResult" style="padding:2px;">
	<div class="panel-heading crm-table-title"><B>物料明细列表</B></div>
    <table class="table table-hover" cellspacing="2" cellpadding="0" >
      <thead>
	        <th>物料名称</th>
	        <th>规格型号</th>
	        <th>单位</th>
	        <th>单价</th>
	        <th>基本单位数量(从)</th>
	        <th>基本单位数量(到)</th> 
	        <th>数量段(从)</th>
	        <th>数量段(到)</th>
	        <th>单价</th> 
	        <th>折扣率</th>
      </thead>
      <s:iterator value="orfq.orfqEntryList" status="dl">
	      <tr> 
	        <td><s:property value="iCItemName"/></td>
	        <td><s:property value="fModel"/></td>
	        <td><s:property value="unitName"/></td>
	        <td><s:property value="fPrice"/></td>
	        <td><s:property value="fQtyFrom"/></td>
	        <td><s:property value="fQtyTo"/></td> 
	        <td><s:property value="fAuxQtyFrom"/></td>
	        <td><s:property value="fAuxQtyTo"/></td> 
	        <td><s:property value="fAuxPrice"/></td>
	        <td><s:property value="fDescount"/></td> 
	      </tr>
      </s:iterator>
    </table> 
</div>
<iframe name="hiddenFrame" style="display: none;"></iframe>
 </form>
</body>
</html>