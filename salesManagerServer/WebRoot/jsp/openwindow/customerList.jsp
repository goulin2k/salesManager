<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html lang="zh-cn">
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
	<title>凯扬工业——客户管理系统工作台</title>
	<base target="_self">
	
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
    <link type="text/css" rel="stylesheet" href="<%=basePath %>script/common/pager/Pager.css" />
    

	<script type="text/javascript" src="<%=basePath %>skin/bootstrap/js/jquery-1.8.3.min.js"></script>	
	<script type="text/javascript" src="<%=basePath %>skin/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>skin/bootstrap/js/jquery-ui-1.10.0.custom.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>script/common/pager/jquery.pager.js"></script>
	<script type="text/javascript" src="<%=basePath %>script/common/pages.js"></script>
		
	<script type="text/javascript">
	var basePath = "<%=path%>/";
	
	function returnValue1(trValue){
		var array = new Array(); 
		array[0] = trValue.find('td').eq(1).find('input').val(); 
		array[1] = trValue.find('td').eq(0).text();
		array[2] = trValue.find('td').eq(2).find('input').val(); 
		array[3] = trValue.find('td').eq(2).text();
		array[4] = trValue.find('td').eq(3).find('input').val(); 
		array[5] = trValue.find('td').eq(3).text();
		array[6] = trValue.find('td').eq(4).find('input').val(); 
		array[7] = trValue.find('td').eq(4).text();
		array[8] = trValue.find('td').eq(5).find('input').val();
		//array[9] = trValue.find('td').eq(6).text();
		array[9] = trValue.find('td').eq(6).find('input').val();
		array[10] = trValue.find('td').eq(6).text();
		
		if (window.opener) {
		       //for chrome
		       window.opener.returnValue = array;
		}
		else {
		       window.returnValue = array;
		}
	    window.close();
	}
	
	
	</script>
</head>
<body class="ui-lv2bg2">
	<!-- 简单风格的工具查询栏	-->	
	<nav class="navbar navbar-default" role="navigation">
		
	    <ul class="nav navbar-nav">
	    	<li class="active"><a href="customer!customerOpenWindow"><span class="glyphicon glyphicon-refresh"></span>&nbsp;清空查询条件</a></li>
	    	<li class="active"><a href="javascript:this.close();"><span class="glyphicon glyphicon-remove-sign"></span>&nbsp;关闭</a></li>
	    </ul>
	    <form class="navbar-form navbar-right form-inline" role="search" id="searchForm" name="searchForm" 
	    		action="<%=basePath %>customer!customerOpenWindow" method="post">	    		
	      <input type="hidden" id="pageCount" name="pageCount" value="${page.pageCount}" />
		  <input type="hidden" id="pageNumber" name="pageNumber" value="${page.start}" />
		  <input type="hidden" id="totalCount" name="totalCount" value="${page.totalCount}" />
	      <div class="form-group">
	        <s:textfield id="fname" name="tcustomer.fname"  cssClass="form-control" placeholder="客户名"/>
	      </div>
	      <div class="form-group">
	      	<s:textfield id="fnumber" name="tcustomer.fnumber" cssClass="form-control" placeholder="客户编码"/>
	      </div>
	      	<button onclick="submit();"  class="btn btn-primary">搜索</button>
	    </form>
    </nav>
    
	 <!--  查询结果列表 -->
    <div class="panel panel-default" id="searchResult" style="padding:2px;">
     
     	<table  class="table table-hover" cellspacing="0" cellpadding="0" >
              <thead> 
                <th class="crm-dialog-th">客户名称</th>
                <th class="crm-dialog-th">客户编码</th>
                <th class="crm-dialog-th">已分配业务员</th>
                <th class="crm-dialog-th">已分配副总</th>
                <th class="crm-dialog-th">已分配部门经理</th>
                <th class="crm-dialog-th">联系人</th>
                <th class="crm-dialog-th">付款条件</th>
              </thead>
        <!--      =================表格循环===============-->
              <s:iterator id="customer" value="customerList" status="dl">
              <tr class="ui-table-style1-tr2" onclick="returnValue1($(this))"> 
                <td>${customer.ffullname }</td>
                <td><input type="hidden" id="customerId" name="customerId" value="${customer.customerId}" />${customer.fnumber }</td>
                <td><input type="hidden" id="ownerUserId" name="ownerUserId" value="${customer.ownerUserId}" />${customer.ownerUserName }</td>
                <td><input type="hidden" id="salegenId" name="salegenId" value="${customer.salegenId}" />${customer.salegenName }</td>
                <td><input type="hidden" id="finmanagerId" name="finmanagerId" value="${customer.finmanagerId}" />${customer.finmanagerName }</td>
                <td><input type="hidden" id="customerWonerUserId" name="customerWonerUserId" value="${customer.customerWonerUserId}" />
                	${customer.fcontact }</td>
                <td><input type="hidden" id="payCondition" name="payConditionId" value="${customer.payConditionId}" />
                	${customer.payCondition }</td>
                
                
              </tr> 
              </s:iterator>
          <!--     =================表格循环over===============-->
          
            </table>
            </div> 
<div class="ui-pagelist" id="pager">        
    </div>
    <div class="clear"></div>
</body>
</html>