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
<title> 退货通知单列表 </title>

<script type="text/javascript" src="<%=basePath %>dwr/engine.js"></script>     
<script type="text/javascript" src="<%=basePath %>dwr/util.js"></script>
<script type="text/javascript" src="<%=basePath %>dwr/interface/msgPushManager.js"></script> 
<script type="text/javascript" src="<%=basePath %>script/common/jquery-1.4.2.min.js" ></script>
<link rel="stylesheet" href="<%=basePath %>script/common/autocomplete/jquery.autocomplete.css" type="text/css"></link> 
<link href="skin/Default/css/base.css" rel="stylesheet" type="text/css" />
<link href="skin/Default/css/global.css" rel="stylesheet" type="text/css" />
	<link rel="icon" href="<%=basePath %>/jsp/lhgcalendar/favicon.ico" type="image/x-icon"/>
	<link rel="shortcut icon" href="<%=basePath %>/jsp/lhgcalendar/favicon.ico" type="image/x-icon"/>
	<link href="<%=basePath %>/jsp/lhgcalendar/_doc/common.css" type="text/css" rel="stylesheet"/>
	<link href="<%=basePath %>/jsp/lhgcalendar/_doc/prettify/prettify.css" type="text/css" rel="stylesheet"/>
	<script type="text/javascript" src="<%=basePath %>/jsp/lhgcalendar/_doc/prettify/prettify.js"></script>
	<script type="text/javascript" src="<%=basePath %>/jsp/lhgcalendar/lhgcalendar.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>/jsp/lhgcalendar/_demo/demo.js"></script>
<script type="text/javascript" language="javascript" src="<%=basePath %>script/common/pager/jquery.pager.js"></script>
<link type="text/css" rel="stylesheet" href="<%=basePath %>script/common/pager/Pager.css" />
<script type="text/javascript">
var basePath = "<%=path%>/";
$(document).ready(function(){
	$("#pager").pager({
	    pagenumber:${page.start},                         /* 表示初始页数 */
	    pagecount:${page.pageCount},                      /* 表示总页数 */
	    totalCount:${page.totalCount},
	    buttonClickCallback:PageClick                     /* 表示点击分页数按钮调用的方法 */                
	});
	
});

/*
       PageClick = function(pageclickednumber) {}部分
       PageClick，表示自定义点击分页数时的function方法，如：function(pageclickednumber){}
       jQuery插件JQuery Pager分页器只需要起始页数pagenumber，最大页数pagecount，
            点击页数时的调用buttonClickCallback的 function方法就可实现javascript分页功能，
            实际应用中只需对PageClick方法进行简单修改就可使用，如将pagenumber和 pagecount设为变量，
            可通过GET的方法进行页数值传递，JQuery Pager就可实现javascript分页功能
   */
   PageClick = function(pageclickednumber) {
	$("#pager").pager({
	    pagenumber:pageclickednumber,                 /* 表示启示页 */
	    pagecount:${page.pageCount},                  /* 表示最大页数pagecount */
	    buttonClickCallback:PageClick                 /* 表示点击页数时的调用的方法就可实现javascript分页功能 */            
	});
	
	$("#pageNumber").val(pageclickednumber);          /* 给pageNumber从新赋值 */
	/* 执行Action */
	pagesearch();
}
		
function search(){
	$("#pageNumber").val("1");
	pagesearch();
}

function pagesearch(){
	returnGoods.submit();
}

$(function(){
    $('#startTime').calendar();
    $('#endTime').calendar();
	$('#img').calendar({ id:'#cal3' });
});
</script>
</head>

<body class="ui-lv2bg">
	<div class="ui-content-title"><p><img src="skin/Default/images/ui-content-ico/list.png" />退货通知单列表</p></div>
<form id="returnGoods" name="returnGoods" action="<%=basePath %>returnGoods!list" method="post">
<div class="ui-com-search">
    <div class="ui-com-search-title ">
	  <input type="hidden" id="pageNumber" name="pageNumber" value="${page.start}" />
       业务员：<s:select name="returnGoods.fEmpID" listKey="fEmpID" listValue="fName" headerKey="" headerValue="请选择业务员" list="empList" />
        <br />
        状态： <s:select name="returnGoods.fStatus" headerKey="" headerValue="状态" listKey="key" listValue="value" list="statusMap"/>
        <br />
        时间范围： <input class="ui-com-search-year" id="startTime" name="returnGoods.startTime" value="<s:property value="returnGoods.startTime"/>"/>至
        <input class="ui-com-search-year" id="endTime" name="returnGoods.endTime" value="<s:property value="returnGoods.endTime"/>"/>
        <div class="ui-combottom fr mt5"><a href="#" onClick="returnGoods.submit();">查询</a></div>
    </div>   
</div>
<div class="clear"></div>
</form>
<div class="ui-table-style1">
	<table  cellspacing="0" cellpadding="0" >
	  <tr class="ui-table-style1-tr1">	  
	    <td class="td2">单据编号</td>
	    <td class="td2">部门</td>  
	    <td class="td4">业务员</td>
	    <td class="td5">制单人</td>  
	    <td class="td3">币别</td>
	    <td class="td4">销售方式</td>
	    <td class="td3">作废标志</td> 
	    <td class="td3">验收人</td>
	    <td class="td4">单据状态</td>
	    <td class="td3">日期</td> 
	    <td class="td2">操作</td>
	  </tr>
		<!--      =================表格循环===============-->
	  <s:iterator value="returnGoodsList" status="dl">
		<tr class="ui-table-style1-tr2">
		  <td><s:property value="fBillNo"/></td>
		  <td><s:property value="departName"/></td>  
		  <td><s:property value="employeeName"/></td>
		  <td><s:property value="billerName"/></td> 
		  <td><s:property value="currencyName"/></td>
		  <td><s:property value="saleStyleName"/></td>
		  <td><s:property value="cancellationName"/></td>  
		  <td><s:property value="fmgrName"/></td>
		  <td><s:property value="statusName"/></td>
		  <td><s:date name="fDate" format="yyyy-MM-dd"/></td>  
		  <td><a href="<%=basePath %>returnGoods!show?returnGoods.fInterID=<s:property value="fInterID"/>"> 查看 </a> </td>
		</tr>
	  </s:iterator>
	</table>
</div>
<div class="ui-pagelist mt10 mb50">
	<div style="float: right;" id="pager"></div>
</div>
</body>
</html>