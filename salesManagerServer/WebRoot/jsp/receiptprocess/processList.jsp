<%@ page language="java"
	import="java.util.*,org.jbpm.api.*,org.jbpm.api.task.*,org.jbpm.api.history.*,com.sales.model.*,com.sales.common.*"
	pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>我的流程</title>
<script type="text/javascript" language="javascript"
	src="script/common/pager/jquery.pager.js"></script>
<link type="text/css" rel="stylesheet"
	href="script/common/pager/Pager.css" />
<link type="text/css"
	href="<%=basePath%>skin/Default/css/jquery-ui-1.10.0.custom.css"
	rel="stylesheet" />
<script type="text/javascript" language="javascript"
	src="script/common/jquery-ui-1.10.0.custom.min.js"></script>
<script type="text/javascript">
			var basePath = "<%=path%>/";
			var nSearchPanelHeight = 100;				//定义查询框的高度（px）
			$(document).ready(function(){
				$("#pager").pager({
						    pagenumber:${process.pageNo},                         /* 表示初始页数 */
						    pagecount:${process.pageCount},                      /* 表示总页数 */
						    totalCount:${process.totalCount},
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
				    pagecount:${process.pageCount},                  /* 表示最大页数pagecount */
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
		processForm.submit();
	}

	function deleteSearch() {	//清除查询条件
		window.location.href = basePath + 'process!processList';
	}
	
	$(function() {
		$( "#startTimeId" ).datepicker({
    		dateFormat: "yy-mm-dd"
        }); 
		$( "#endTimeId" ).datepicker({
    		dateFormat: "yy-mm-dd"
        }); 
		if ('<s:property value="process.opType" />'==2) {
			$('#conditions').show();
		}
	});
	
	function typeChange(obj) {
		if(obj.value=="2") {
			$('#conditions').show();
		} else {
			$('#conditions').hide();
		}
	}
</script>
</head>
<body class="ui-lv2bg">
    <div class="crm-querybar panel panel-info ">
	    <s:form id="processForm"  name="processForm" action="process!processList"
	    		  cssClass="form-horizontal" style="margin-bottom: 2px;">
	      <input type="hidden" id="pageNumber" name="process.pageNo" value="${process.pageNo}" />
	      <div class="form-group">
	      	<div class="col-sm-3">	      	
		      	<s:select id="typeId" tooltip="单据" name="process.typeId"
						emptyOption="false" headerKey="-1" headerValue="选择单据"
						list="#{'leave-1':'休假单','overtime-1':'加班申请单','expenseApp-1':'费用申请单','expenseRei-1':'费用报销单','tripApp-1':'出差申请单','carRepairApp-1':'车辆维修申请单','carRepairRei-1':'车辆维修报销单' }"
						cssClass="form-control" />
		      </div>
		      <div class="col-sm-3">
		      	<s:select id="opType" tooltip="类型" name="process.opType"
						emptyOption="false"
						list="#{'1':'等待我审批的单据','2':'我审批过的单据','3':'我提交的单据' }"
						cssClass="form-control" onchange="typeChange(this);" />
		      </div>
		      <div class="col-sm-3">
			      <!--提交按钮	-->
			      <button type="submit" class="btn btn-primary">查询</button>	
		      </div>
		   </div>
		   <div class="form-group" id="conditions" style="display:none">
		      	<div class="col-sm-3">
		      	    <input type="text" id="userName" name="process.userName"
						value="${process.userName}" class="form-control" placeholder="提交人">	 
		      	</div>
		      	<div class="col-sm-3">	
		      	<s:date name="process.startTime" format="yyyy-MM-dd" />
		      	    <input type="text" id="startTimeId" name="process.startTime"
						value="${process.startTime}"
						class="form-control" placeholder="开始日期" />- 
		      	</div>
		      	<div class="col-sm-3">
		      	    <input type="text"
						id="endTimeId" name="process.endTime"
						value="${process.endTime}"
						class="form-control" placeholder="结束日期" />	 
		      	</div>
	       </div>
		   
	   </s:form>
	</div>

	
				<!-- 
                                     单据：<select class="ui-com-search-options" name="process.typeId">
                      <option value="-1">全部</option>
                      <option value="leave-1" <s:if test="process.typeId == 'leave-1'">selected</s:if>>休假单</option>
                      <option value="overtime-1" <s:if test="process.typeId == 'overtime-1'">selected</s:if>>加班申请单</option>
                      <option value="expenseApp-1" <s:if test="process.typeId == 'expenseApp-1'">selected</s:if>>费用申请单</option>
                      <option value="expenseRei-1" <s:if test="process.typeId == 'expenseRei-1'">selected</s:if>>费用报销单</option>
                      <option value="tripApp-1" <s:if test="process.typeId == 'tripApp-1'">selected</s:if>>出差申请单</option>
                      <option value="carRepairApp-1" <s:if test="process.typeId == 'carRepairApp-1'">selected</s:if>>车辆维修申请单</option>
                      <option value="carRepairRei-1" <s:if test="process.typeId == 'carRepairRei-1'">selected</s:if>>车辆维修报销单</option>
                   </select>
                 <input type="hidden" id="pageNumber" name="process.pageNo" value="${process.pageNo}" />
                                    类型：<select class="ui-com-search-options" name="process.opType" onchange="typeChange(this);">
                      <option value="1" <s:if test="process.opType == 1">selected</s:if>>等待我审批的单据</option>
                      <option value="2" <s:if test="process.opType == 2">selected</s:if>>我审批过的单据</option>
                      <option value="3" <s:if test="process.opType == 3">selected</s:if>>我提交的单据</option>
                   </select>
               <br/><h id="conditions" style="display:none">
				    提交人：<input  name="process.userName" type="text" value="${process.userName}"/>
				    时间范围：<input id="startTimeId" name="process.startTime" class="easyui-datebox" value="${process.startTime}"></input>-
				    <input id="endTimeId" name="process.endTime" class="easyui-datebox" value="${process.endTime}"></input>
			   </h>   -->
			


	<div class="panel panel-default" id="searchResult" style="padding:2px;">
		<div class="panel-heading crm-table-title">
			<B>查询列表</B>
		</div>
		<table class="table table-hover" cellspacing="0" cellpadding="0">
			<thead>
				<th>单据类型</th>
				<th>申请部门</th>
				<th>申请人</th>
				<th>流程节点</th>
				<th>申请时间</th>
				<th>操作</th>

			</thead>
			<%
				ProcessEngine processEngine = Configuration.getProcessEngine();
				TaskService taskService = processEngine.getTaskService();
				ExecutionService executionService = processEngine
						.getExecutionService();
			%>

			<s:if test="process.opType == 1">
				<s:iterator id="tl" value="taskList" status="dl">
					<tr class="ui-table-style1-tr2">
						<%
							Task task = (Task) request.getAttribute("tl");
									String taskId = task.getId();
									String excuteId = task.getExecutionId();
									String pId = excuteId.substring(excuteId.indexOf(".") + 1);
						%>
						<td><%=taskService.getVariable(taskId, "typeName")%></td>
						<td><%=taskService.getVariable(taskId, "deptName")%></td>
						<td><%=taskService.getVariable(taskId, "userName")%></td>
						<td>${tl.name }</td>
						<td><s:date name="createTime" format="yyyy-MM-dd HH:mm" /></td>
						<%
							if ("休假申请".equals(task.getName())) {
						%>
						<td><a href="leave!toReSubmitLeave?leaveId=<%=pId%>">申请</a></td>
						<%
							} else if ("车辆维修申请".equals(task.getName())) {
						%>
						<td><a href="car!toReSubmitCa?caId=<%=pId%>">申请</a></td>
						<%
							} else if ("车辆维修报销".equals(task.getName())) {
						%>
						<td><a href="car!toReSubmitCr?crId=<%=pId%>">申请</a></td>
						<%
							} else if ("费用申请".equals(task.getName())) {
						%>
						<td><a href="expense!toReSubmitEa?eaId=<%=pId%>">申请</a></td>
						<%
							} else if ("费用报销".equals(task.getName())) {
						%>
						<td><a href="expense!toReSubmitEr?erId=<%=pId%>">申请</a></td>
						<%
							} else if ("加班申请".equals(task.getName())) {
						%>
						<td><a href="overtime!toReSubmitOt?otId=<%=pId%>">申请</a></td>
						<%
							} else if ("出差申请".equals(task.getName())) {
						%>
						<td><a href="trip!toReSubmitTrip?tripId=<%=pId%>">申请</a></td>

						<%
							} else {
						%>
						<td><a href="${tl.formResourceName }?taskId=${tl.id }">审批</a></td>
						<%
							}
						%>
					</tr>
				</s:iterator>
			</s:if>
			<s:else>
				<s:iterator id="pl" value="processList" status="dl">
					<tr class="ui-table-style1-tr2">
						<%
							SProcess sp = (SProcess) request.getAttribute("pl");
									ProcessInstance processInstance = executionService
											.findProcessInstanceById(sp.getExecutionId());
									String activityName = "";
									if (processInstance == null) {
										activityName = Constants.PROCESS_END;
									} else {
										String tempstr = processInstance
												.findActiveActivityNames().toString();
										activityName = tempstr.substring(1,
												tempstr.length() - 1);
									}
						%>
						<td>${pl.processType }</td>
						<td>${pl.deptName }</td>
						<td>${pl.userName }</td>
						<td><%=activityName%></td>
						<td><s:date name="appTime" format="yyyy-MM-dd HH:mm" /></td>
						<td><a href="${pl.detailLink }">查看</a></td>
					</tr>
				</s:iterator>

			</s:else>
		</table>
	</div>

	<div class="ui-pagelist" id="pager"></div>


</body>
</html>