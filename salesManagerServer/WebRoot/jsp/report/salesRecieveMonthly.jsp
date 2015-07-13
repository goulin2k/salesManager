<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>销售收款统计报表</title>

<!-- Bootstrap core CSS -->
    <link href="skin/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="skin/bootstrap/css/bootstrap-theme.css" rel="stylesheet">
    <link href="skin/bootstrap/css/bootstrap-dialog.min.css" rel="stylesheet">
    <!-- Just for debugging purposes. Don't actually copy this line! -->
    <!--[if lt IE 9]><script src="../../docs-assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.0/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
    <link type="text/css" href="<%=basePath %>skin/Default/css/jquery-ui.css" rel="stylesheet"  />
     
    <link type="text/css" href="<%=basePath %>skin/Default/css/base.css" rel="stylesheet"  />
    <link type="text/css" href="<%=basePath %>skin/Default/css/global.css" rel="stylesheet"  />
    
    
	<script type="text/javascript" src="<%=basePath %>skin/bootstrap/js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>skin/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>skin/bootstrap/js/bootstrap-dialog.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>skin/bootstrap/js/jquery-ui-1.10.0.custom.min.js"></script>
	<script type="text/javascript" language="javascript" src="<%=basePath %>script/common/pager/jquery.pager.js"></script>
	
	<link type="text/css" rel="stylesheet" href="<%=basePath %>script/common/pager/Pager.css" />
<script type="text/javascript">
			var basePath = "<%=path%>/";
			var nSearchPanelHeight = 120;				//定义查询框的高度（px）
			$(document).ready(function() {
				$("#pager").pager({
						    pagenumber:${page.start},                         /* 表示初始页数 */
						    pagecount:${page.pageCount},                      /* 表示总页数 */
						    totalCount:${page.totalCount},						/* 记录总数		*/ 
						    buttonClickCallback:PageClick                     /* 表示点击分页数按钮调用的方法 */ 
						                 
				});
				
				$( "#startTime" ).datepicker({
		    		dateFormat: "yy-mm-dd"
			    });
			    $( "#endTime" ).datepicker({
			    	dateFormat: "yy-mm-dd"
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
				    totalCount:${page.totalCount},						/* 记录总数		*/ 
				    buttonClickCallback:PageClick                 /* 表示点击页数时的调用的方法就可实现javascript分页功能 */            
				});
				
				$("#pageNumber").val(pageclickednumber);          /* 给pageNumber从新赋值 */
				/* 执行Action */
				pagesearch();
			}
			
	function search(){
		$("#pageNumber").val("1");
		//pagesearch();
		$("#reportForm").submit();
	}
	
	function deleteSearch() {	//清除查询条件
		window.location.href = basePath + '/report!salesProcessReport';
	}
	
	function pagesearch(){
		var pageNumber = $("#pageNumber").val();
		var startTime = $("#startTime").val();
		var endTime = $("#endTime").val();
		var url= basePath+"report!salesProcessReport?pageNumber="+pageNumber+"&startTime="+startTime+"&endTime="+endTime;
		url = encodeURI(url);
		window.location.href=url;
	}
</script>
</head>
<body class="ui-lv2bg">
	<!-- 工具栏 -->
	<nav class="navbar navbar-default" role="navigation">
	    <ul class="nav navbar-nav">
	    	<li class="active"><a href="javascript:deleteSearch();"><span class="glyphicon glyphicon-refresh"></span>&nbsp;清空查询条件</a></li>	
	    	<li class="active"><a href="#"><span class="glyphicon glyphicon-print"></span>&nbsp;打印</a></li>
	    	<li class="active"><a href="javascript:window.close();"><span class="glyphicon glyphicon-eject"></span>&nbsp;关闭</a></li>
	    	
	    </ul>
	    <form class="navbar-form navbar-right form-inline" role="search" 
	    	id="reportForm" name="reportForm" action="<%=basePath %>report!salesProcessReport" method="post">
	      <div class="form-group">
	      	<input type="hidden" id="pageNumber" name="pageNumber" value="${page.start}" />
	        <input type="text" id="startTime" name="startTime" class="form-control" 
	        	value="<s:date name="startTime" format="yyyy-MM-dd" />" placeholder="开始日期"/>
	       </div>
	       <div class="form-group">
	        <input type="text" id="endTime" name="endTime" class="form-control" 
	        	value="<s:date name="endTime" format="yyyy-MM-dd" />" placeholder="结束日期"/>
	      </div>
	      	<button type="submit" onclick="search();" class="btn btn-primary">统计</button>
	    </form>
    </nav>

   <div class="panel panel-default" id="searchResult" style="padding:2px;">
     <div class="panel-heading crm-table-title"><B>报表统计信息</B></div>
		<table class="table table-hover"  cellspacing="0" cellpadding="0" >
		     <tr class="ui-table-style1-tr2">
                <td>报价单业务审核平均：${averageBillCheck } 小时</td>
                <td></td>
                <td>报价单财务审核平均：${averageFinCheck } 小时</td>
                <td></td>
                <td>订单审核审核平均：${averagePorequestCheck } 小时</td>
                <td></td>
                <td>采购审核平均：${averagePorequestCheck } 小时</td>
                <td></td>
                <td>采购入库平均：${averageStockin } 小时</td>
                <td></td>
                <td>领料通知平均：${averageStockout } 小时</td>
                <td></td>
                <td>销售出库平均：${averageSaleOut } 小时</td>
                <td></td>
                <td></td>
              </tr>
         </table>
   </div>
   
    <div class="panel panel-default" id="searchResult" style="padding:2px;">
     <div class="panel-heading crm-table-title"><B>报表统计明细</B></div>
     	<input type="hidden" id="pageNumber" name="pageNumber" value="${page.start}" />
    	<table class="table table-hover" cellspacing="0" cellpadding="0" >
 			<thead>
               <td>报价单号</td>
               <td>提交日期</td>
               <td>业务审核(小时)</td>
               <td>财务审核(小时)</td>
               <td>销售订单(小时)</td>
               <td>订单审核(小时)</td>
               <td>采购申请(小时)</td>
               <td>采购审核(小时)</td>
               <td>采购订单(小时)</td>
               <td>采购订单审核(小时)</td>
               <td>采购入库(天)</td>
               <td>领料通知(小时)</td>
               <td>销售出库(小时)</td>
              </thead>
        <!--      =================表格循环===============-->
              <s:iterator id="temp" value="saleProcList" status="dl">
              <tr class="ui-table-style1-tr2">
                <td>${temp.porfqBillNo }</td>
                <td><s:date name="#temp.porfqBillDate" format="yyyy-MM-dd HH:mm" /></td>
                <td>${temp.billCheckHours }</td>
                <td>${temp.finCheckHours }</td>
                <td>${temp.orderHours }</td>
                <td>${temp.orderCheckHours }</td>
                <td>${temp.poRequestHours }</td>
                <td>${temp.porequestCheckHours }</td>
                <td>${temp.poOrderHours }</td>
                <td>${temp.poOrderCheckHours }</td>
                <td><s:if test="stockinHours != null">${temp.stockinHours/8 }天</s:if></td>
                <td>${temp.stockoutHours }</td>
                <td>${temp.saleoutHours }</td>
              </tr> 
              </s:iterator>
          <!--     =================表格循环over===============-->
          
   </table>
</div>
   
<!-- 分页标签 -->
	 <div class="ui-pagelist" style="width:100%; background-color:white;margin-top:-18px;" id="pager">
		<div style="float: right;" ></div>
	</div>

</body>
</html>