<%@ page language="java" import="java.util.*,org.jbpm.api.*,org.jbpm.api.task.*,com.sales.model.*,org.jbpm.api.history.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>出差申请审批</title>
		<link href="skin/Default/css/base.css" rel="stylesheet" type="text/css" />
		<link href="skin/Default/css/global.css" rel="stylesheet" type="text/css" />
		
		<script type="text/javascript" language="javascript" src="<%=basePath %>script/common/common.js"></script>
		<script type="text/javascript" src="<%=basePath %>script/common/easyui-1.3.5/plugins/jquery.parser.js"></script>
		<script type="text/javascript" src="<%=basePath %>script/common/easyui-1.3.5/jquery.easyui.min.js"></script>
		
		<link rel="stylesheet" type="text/css" href="<%=basePath %>script/common/easyui-1.3.5/themes/icon.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath %>script/common/easyui-1.3.5/themes/bootstrap/easyui.css" /> 
		<script type="text/javascript" src="script/common/easyui/validate.js"></script>
		<script type="text/javascript" src="script/common/easyui/locale/easyui-lang-zh_CN.js"></script>
	
		<script type="text/javascript">
              function submitApproval(type) {
            	  var msg = "";
            	  if (type == 1) {
            		  msg = "确认批准当前单据?";
            	  } else {
            		  msg = "确认驳回当前单据?";
            	  }
            	  $.messager.confirm('审批确认', msg, function(r){
						if (r){
							$('#typeId').val(type);
            	            myform.submit();
						}
					});
            	  
              }
		</script>
	</head>
	<body class="ui-lv2bg">
		  <div class="ui-table ml15">
		  <form class="form-horizontal" id="myform" name="myform" action="process!approval" method="post">
			<table class="ui-table-one" cellspacing="2" cellpadding="0" >
			  <caption class="mt30">出差申请审批</caption>  
			   <% 
                ProcessEngine processEngine = Configuration.getProcessEngine();
            	TaskService taskService = processEngine.getTaskService();
                DataProcess process = (DataProcess)request.getAttribute("process");
                String taskId = process.getTask().getId();
                %>
              <tr>
			    <td  class="ui-table-title">单据号：</td>
			    <td colspan="3">
				    ${process.trip.tripCode}
				</td>
			  </tr>
			  <tr>
			    <td  class="ui-table-title">申请部门：</td>
			    <td>
					<%=taskService.getVariable(taskId, "deptName")%>
				</td>
				<td  class="ui-table-title">申请人：</td>
			    <td>
			          <%=taskService.getVariable(taskId, "userName")%>
			    </td>
			  </tr>
			  <tr>
			    <td  class="ui-table-title">出差地点：</td>
			    <td colspan="1">
					${process.trip.tripLocation }
				</td>
				<td  class="ui-table-title"></td>
			    <td colspan="1">
				</td>
			  </tr>
			  <tr>
			    <td  class="ui-table-title">开始日期：</td>
			    <td>
			        <s:date name="process.trip.startTime" format="yyyy-MM-dd" />
				</td>
				<td  class="ui-table-title">结束日期：</td>
			    <td>
			        <s:date name="process.trip.endTime" format="yyyy-MM-dd" />
			    </td>
			  </tr>
			  
			  <tr>
			        <td class="ui-table-title">出差事由：</td>
					<td colspan="3">
					    ${process.trip.tripComment}
					</td>
			  </tr>
			     <tr>
			        <td class="ui-table-title">历史审批意见：</td>
					<td colspan="3">
			  <%
			  List<HistoryTask> list = processEngine.getHistoryService().createHistoryTaskQuery().list();
              Task task = taskService.getTask(taskId);
              String processInstanceId = task.getExecutionId();
              for(HistoryTask ht: list) {
                  String insId = ht.getExecutionId();
                  //只取此流程实例的审批信息
                  if(insId.equals(processInstanceId)) {
                  	String assignee = ht.getAssignee();
                      String historyTaskId = ht.getId();
                      List<HistoryComment> hList = taskService.getTaskComments(historyTaskId);
                      if(null != hList && hList.size() > 0) {
                          System.out.println(assignee+";"+hList.get(0).getMessage());
              %>
                      <%=hList.get(0).getMessage() %><br>
              <%
                      }
                  }
              }
			  %>
			         </td>
			  </tr>
			  <tr>
			        <td class="ui-table-title">审批意见：</td>
					<td colspan="3">
						<textarea rows="6" style="width:98%"  name="comment" class="form-control easyui-validatebox" validType="maxLength['审批意见',50]"></textarea>
						<input type="hidden" id="typeId" name="processType"/>
						<input type="hidden" name="taskId" value="${process.task.id }"/>
					</td>
			  </tr>
			</table>
			</form>
			<!--  按钮面板  -->
			<div class="crm-button-panel form-group ">
				<div class="col-sm-11 controls" align="center">
					<a class="btn btn-success " href="javascript:submitApproval(1);" id="cancel">批准</a>
					<a class="btn btn-warning " href="javascript:submitApproval(0);" id="cancel">驳回</a>
				</div>
			</div>
		  </div>
		
	</body>

</html>