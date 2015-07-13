<%@ page language="java" import="com.sales.model.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sales" uri="/WEB-INF/sales-util.tld"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<base target="_self"></base>
	<title> 产品列表 </title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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
  
	<script type="text/javascript" src="<%=basePath %>skin/bootstrap/js/jquery-1.8.3.min.js"></script>	
	<script type="text/javascript" src="<%=basePath %>skin/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>skin/bootstrap/js/jquery-ui-1.10.0.custom.min.js"></script>

	<script type="text/javascript" language="javascript" src="<%=basePath %>script/common/pager/jquery.pager.js"></script>
	<link type="text/css" rel="stylesheet" href="<%=basePath %>script/common/pager/Pager.css" />
	<script type="text/javascript" src="<%=basePath %>script/common/pages.js"></script>

<script type="text/javascript">
var basePath = "<%=path%>/";

function returnValue1(trValue){
	var array = new Array(); 
	array[0] = trValue.find('td').eq(0).text();
	array[1] = trValue.find('td').eq(1).text();
	array[2] = trValue.find('td').eq(2).text();
	array[3] = trValue.find('td').eq(4).find('input').val(); 
	array[4] = trValue.find('td').eq(3).text(); 
	array[5] = trValue.find('td').eq(5).find('input').val(); 
	array[6] = trValue.find('td').eq(6).find('input').val();
	array[7] = trValue.find('td').eq(7).find('input').val();
	if (window.opener) {
	       //for chrome
	       window.opener.returnValue = array;
	}
	else {
	       window.returnValue = array;
	}
    window.close();
}

function go_link(url, page) { 
	var link = document.createElement("a");
	link.href = url + "?product.fNumber=" + $("#fNumber").val() + "&product.fName=" + $("#fName").val() + "&pageNumber=" + page;
	document.body.appendChild(link);
	link.click();
}
</script>
</head>
<body class="ui-lv2bg2">
<!-- 标准工具栏 -->
<nav class="navbar navbar-default" role="navigation">
	<ul class="nav navbar-nav">
    	<li class="active"><a href="product!list"><span class="glyphicon glyphicon-refresh"></span>&nbsp;清空查询条件</a></li>
    	<li class="active"><a href="javascript:this.close();"><span class="glyphicon glyphicon-remove-sign"></span>&nbsp;关闭</a></li>
    </ul>
    
	<form id="searchForm"  name="searchForm" action="<%=basePath %>product!list" method="post" 
		class="navbar-form navbar-right" role="search" style="margin-bottom: 2px;" target="_self">
	    <input type="hidden" id="pageCount" name="pageCount" value="${page.pageCount}" />
		<input type="hidden" id="pageNumber" name="pageNumber" value="${page.start}" />
		<input type="hidden" id="totalCount" name="totalCount" value="${page.totalCount}" />
	    <div class="form-group">
		    <input class="form-control" id="fNumber" name="product.fNumber" 
		        	value="<s:property value="product.fNumber"/>" type="text" placeholder="物料编码"/>
		</div>
		<div class="form-group">
		   	<input class="form-control" id="fName" name="product.fName" 
		   			value="<s:property value="product.fName"/>" type="text" placeholder="物料名称"/>
	     </div>
	     <button type="submit"  class="btn btn-primary">搜索</button>
	</form>
</nav>



<!--  查询结果列表 -->
<div class="panel panel-default" id="searchResult" style="padding:2px;">
	<div class="panel-heading crm-table-title"><B>物料查询记录</B></div>
	<table class="table table-hover" cellspacing="0" cellpadding="0" >
	  <thead>	   
        <th>物料编码</th>
        <th>物料名称</th>
        <th>规格型号</th>
        <th>单位</th>
        <th>购买方式</th>  
	  </thead>
		<!--      =================表格循环===============-->
	  <s:iterator value="productList" status="dl">
		<tr class="ui-table-style1-tr2" onClick="returnValue1($(this))"> 
		  <td><s:property value="fNumber"/></td>
		  <td><s:property value="fModel"/></td>  
		  <td><s:property value="fName"/></td>
		  <td><s:property value="measureUnitName"/></td> 
		  <td><input type="hidden" id="fUnitID" name="fUnitID" value="<s:property value="fUnitID"/>" /><s:property value="productPropery"/></td> 
		  <td><input type="hidden" id="fItemId" name="fItemId" value="<s:property value="fItemId"/>" /></td>
		  <td><input type="hidden" id="minBuy" name="minBuy" value="<s:property value="minBuy"/>" /></td>
		  <td><input type="hidden" id="packModel" name="packModel" value="<s:property value="packModel"/>" /></td>
		</tr>
	  </s:iterator>
	</table>
</div>
<div class="ui-pagelist">	
	<div style="float: right;" id="pager"></div>
</div>
</body>
</html>