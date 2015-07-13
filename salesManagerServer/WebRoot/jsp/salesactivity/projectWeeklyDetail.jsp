<%@ page language="java" import="com.sales.model.*" contentType="text/html; charset=UTF-8"%>
<%@page import="com.thoughtworks.xstream.io.path.Path"%>
<%@ taglib prefix="s" uri="/struts-tags"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title> 销售计划个人周报 </title>
 
<script type="text/javascript" src="<%=basePath %>script/common/pager/jquery.pager.js"></script>
<script type="text/javascript" language="javascript" src="<%=basePath %>script/common/pages.js"></script>
<link type="text/css" rel="stylesheet" href="<%=basePath %>script/common/pager/Pager.css" />
<script type="text/javascript">
var basePath = "<%=path%>/";


function getCustomer(){ 
	var array = window.showModalDialog("<%=basePath %>/customer!customerOpenWindow", "", "dialogWidth:800px; dialogHeight:530px; status:no;help:no");
	//for chrome
    if (array == undefined) {
    	array = window.returnValue;
    }
	$("#customerId").val(array[0]);
	$("#customerName").val(array[1]); 
} 

function deleteSearch(url) {	//清除查询条件
	window.location.href = basePath + url;
}

$(document).ready(function(){  
	var startTime;
    var endTime;
    
	$( "#startTime" ).datepicker({
		//regional: "zh-TW",
		dateFormat: "yy-mm-dd",
		onSelect: function(dateText, inst) { 
            var date = $(this).datepicker('getDate');
            startTime = new Date(date.getFullYear(), date.getMonth(), date.getDate() - date.getDay() + 1);
            endTime = new Date(date.getFullYear(), date.getMonth(), date.getDate() - date.getDay() + 7);
            var dateFormat = inst.settings.dateFormat || $.datepicker._defaults.dateFormat;
            $('#startTime').val($.datepicker.formatDate( dateFormat, startTime, inst.settings ));
            $('#endTime').val($.datepicker.formatDate( dateFormat, endTime, inst.settings ));
            
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
</script>
</head>

<body class="ui-lv2bg">
	<!-- 标准工具栏 -->
	<nav class="navbar navbar-default crm-toolbar" role="navigation">
		<ul class="nav navbar-nav">
	    	<li class="active"><a href="salePlanReport!reportWeekly">
	    		<span class="glyphicon glyphicon-circle-arrow-left"></span>&nbsp;&nbsp;返回</a></li>
	    </ul>
	 </nav>
	 
	 
	 <!-- 查询输入栏 -->
	 <div class="crm-querybar panel panel-info ">
	    <s:form id="searchForm"  name="searchForm" action="salePlanReport!detailWeekly"
	    		enctype="multipart/form-data"  cssClass="form-horizontal" style="margin-bottom: 2px;">
	      <input type="hidden" id="userId" name="userId" value="${userId}" />
	      <input type="hidden" id="userName" name="userName" value="${userName}" />		
	      <input type="hidden" id="pageCount" name="pageCount" value="${page.pageCount}" />
		  <input type="hidden" id="pageNumber" name="pageNumber" value="${page.start}" />
		  <input type="hidden" id="totalCount" name="totalCount" value="${page.totalCount}" />
	      <div class="form-group">
	      	
		     
		      <div class="col-sm-2" >	 
		      	<input class="form-control" placeholder="开始日期" id="startTime" 
		      		name="startTime" value="<s:date name="startTime" format="yyyy-MM-dd"/>"/>
		      </div>  
		      <div class="col-sm-2" >   
        		<input class="form-control" placeholder="结束日期" id="endTime" 
        		name="endTime" value="<s:date name="endTime" format="yyyy-MM-dd"/>"/>
		      </div>
		      <!--提交按钮	-->
			   <div class="col-sm-1">
		      		<button type="submit" class="btn btn-primary">查询</button>			
		      </div>
		   </div>
		   
		   
	   </s:form>
	</div>

<!--  查询结果列表 -->
    <div class="panel panel-default" id="searchResult" style="padding:2px;">
     	<div class="panel-heading crm-table-title"><B>【${userName}】销售周计划完成统计</B></div>
		<table  class="table table-hover" cellspacing="0" cellpadding="0" >
			 <thead>
			   <th>创建时间</th>
			   <th>计划日期</th>	  
			   <th>任务主题</th>
			   <th>销售项目</th>
			   <th>客户名称</th>
			   <th>活动日期</th>
			   <th>活动类型</th>
			   <th>活动主题</th> 
			   <th>操作</th>
			 </thead>
			<s:iterator value="planWithActivityVOs" status="dl">
				<tr>
				    <td><s:date  name="createTime" format="yyyy-MM-dd hh:mm"/></td>
					<td><s:property value="planTime"/></td>
					<td><s:property value="planTopical"/></td>
					<td><s:property value="customerLevel"/></td>
					<td><s:property value="customerName"/></td>
					<td><s:property value="activityTime"/></td>
					<td><s:property value="activityTypeName"/></td>
					<td><s:property value="activityTopical"/></td>
					<s:if test="activityId != null">
						<td><a href='<%=basePath %>activity!show?activity.activityId=<s:property value="activityId"/>'>详情</td>
					</s:if>
				</tr>
			</s:iterator>
		</table>
	</div>

	
</body>
</html>