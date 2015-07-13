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
<title>销售统计日报表</title>

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
				var startTime;
			    var endTime;
			    
				$( "#startTime" ).datepicker({
					//regional: "zh-TW",
					dateFormat: "yy-mm-dd",
					onSelect: function(dateText, inst) { 
			            
			            //selectCurrentWeek();
			        },
			        beforeShowDay: function(date) {
			            var cssClass = '';
			            if(date >= startTime && date <= endTime)
			                cssClass = 'ui-datepicker-current-day';
			            return [true, cssClass];
			        },
			        onChangeMonthYear: function(year, month, inst) {
			            //selectCurrentWeek();
			        }
				});
				
			    $( "#endTime" ).datepicker({
			    	dateFormat: "yy-mm-dd"
			    });
				
			});
			
	function search(){
		$("#pageNumber").val("1");
		//pagesearch();
		$("#reportForm").submit();
	}
	
	function deleteSearch() {	//清除查询条件
		window.location.href = basePath + '/report!orderDaily';
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
	    	id="reportForm" name="reportForm" action="<%=basePath %>report!orderDaily" method="post">
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
                <td>统计日期：</td>
                <td>从<b><s:date name="startTime" format="yyyy-MM-dd"/> </b>
                		到<b><s:date name="endTime" format="yyyy-MM-dd"/></b></td>
                <!-- <td>销售金额合计：</td>
                <td><b><s:text name="global.format.money"><s:param value="amountAll"/></s:text></b>元</td>
                -->
              </tr>
         </table>
   </div>
   
    <div class="panel panel-default" id="searchResult" style="padding:2px;">
     <div class="panel-heading crm-table-title"><B>报表统计明细</B></div>
     	<input type="hidden" id="pageNumber" name="pageNumber" value="${page.start}" />
    	<table class="table table-hover" cellspacing="0" cellpadding="0" >
 			<thead>
               <th>业务员</th>
                <s:iterator value="columns" id="cl" status="dl">
                	<th><s:property value="cl"/></th>
                </s:iterator>
              </thead>
             
              <s:iterator id="col" value="mapStatistics" status="dl">
              <tr>
                <td><s:property value="#col.key"/></td>
                <s:iterator value="#col.value" var="t" begin="0" end="#col.value.length-1" step="1" status="st">
                	<td><s:text name="global.format.money"><s:param value="t"/></s:text></td>
                </s:iterator>
              </tr> 
              
          	  </s:iterator>
          
   </table>
</div>
   
<!-- 分页标签
	 <div class="ui-pagelist" style="width:100%; background-color:white;margin-top:-18px;" id="pager">
		<div style="float: right;" ></div>
	</div>
 -->

</body>
</html>