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
		<title>出差申请编辑</title>
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
        $(function() {
        	$( "#startTimeId" ).datepicker({
	    		dateFormat: "yy-mm-dd"
	        });  
			$( "#endTimeId" ).datepicker({
	    		dateFormat: "yy-mm-dd"
	        });   
		});
        
        function editTrip () {
			if ($('#tripForm').form('validate')) {
				if (!dateCompare($('#startTimeId').val(), $('#endTimeId').val())) {
					$.messager.alert('提示','出差起止日期填报错误。','info');
				    return;
				}
				tripForm.submit();
			}
		}
        function dateCompare(a, b) {
		    var arr = a.split("-");
		    var starttime = new Date(arr[0], arr[1], arr[2]);
		    var starttimes = starttime.getTime();
		
		    var arrs = b.split("-");
		    var lktime = new Date(arrs[0], arrs[1], arrs[2]);
		    var lktimes = lktime.getTime();
		
		    if (starttimes > lktimes) {
		        return false;
		    }
		    else
		        return true;
		
		}
		</script>
	</head>
	<body class="ui-lv2bg">
	    <form class="form-horizontal" id="tripForm" name="tripForm" action="trip!reSubmitTrip" method="post">
		  <div class="ui-table ml15">
			<table class="ui-table-one" cellspacing="2" cellpadding="0" >
			<input name="tripApplication.tripId" type="hidden" value="${tripApplication.tripId}"/>
			  <caption class="mt30">出差申请编辑</caption> 
			  <tr>
			    <td  class="ui-table-title">单据号：</td>
			    <td colspan="3">
				    ${tripApplication.tripCode}
				</td>
			  </tr>
			  <tr>
			    <td  class="ui-table-title">出差地点：</td>
			    <td  colspan="3">
					<input name="tripApplication.tripLocation" type="text" value="${tripApplication.tripLocation}" class="form-control easyui-validatebox"  required="true"/>
				</td>
				
			  </tr>
			  <tr>
			    <td  class="ui-table-title">开始日期：</td>
			    <td>
			        <input id="startTimeId" name="tripApplication.startTime" value="<s:date name="tripApplication.startTime" format="yyyy-MM-dd" />" 
                       	 class="easyui-validatebox form-control" placeholder="开始日期" required="true"/>
					
				</td>
				<td  class="ui-table-title">结束日期：</td>
			    <td>
			          <input id="endTimeId" name="tripApplication.endTime" value="<s:date name="tripApplication.endTime" format="yyyy-MM-dd" />"  
			        class="easyui-validatebox form-control" placeholder="结束日期" required="true"/>
			    </td>
			  </tr>
			  
			  <tr>
			        <td class="ui-table-title">出差事由：</td>
					<td colspan="3">
						<textarea rows="6" name="tripApplication.tripComment" class="form-control easyui-validatebox" validType="maxLength['出差事由',100]">${tripApplication.tripComment}</textarea>
					</td>
			  </tr>
			    <tr>
			        <td class="ui-table-title">历史审批意见：</td>
					<td colspan="3">
			    <% 
			    STripApplication ta = (STripApplication)request.getAttribute("tripApplication");
                ProcessEngine processEngine = Configuration.getProcessEngine();
            	TaskService taskService = processEngine.getTaskService();
            	ExecutionService executionService = processEngine.getExecutionService();
            	String processInstanceId = "tripApp."+ta.getTripId();
            	List<HistoryTask> list = processEngine.getHistoryService().createHistoryTaskQuery().list();
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
			</table>
			<!--  按钮面板  -->
			<div class="crm-button-panel form-group ">
				<div class="col-sm-11 controls" align="center">
					<a class="btn btn-success " href="javascript:editTrip();" id="cancel">提交</a>
					<a class="btn btn-warning " href="javascript:history.back();" id="cancel">取消</a>
				</div>
			</div>
			
		  </div>
		</form>
	</body>

</html>