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
		<title>请假单编辑</title>
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
        
        function editLeave () {
			if ($('#leaveForm').form('validate')) {
				if (!dateCompare($('#startTimeId').val(), $('#endTimeId').val())) {
					$.messager.alert('提示','休假起止日期填报错误。','info');
				    return;
				} 
				var type = $('#leaveTypeId').val();
				if (type == 7) {
					$.post("leave!leaveValidate", $("#leaveForm").serializeArray(), function(data) {
						//alert(data.jsonReturn);
						if (data.jsonReturn == "SUCCESS") {
							leaveForm.submit();
						}else {
							$.messager.alert('提示','当前换休时间为'+data.jsonReturn+'小时,超过所填调休天数。','info');
							return;
						}
					});
				} 
				else if (type == 3) {
					$.post("leave!annualValidate", $("#leaveForm").serializeArray(), function(data) {
						//alert(data.jsonReturn);
						if (data.jsonReturn == "SUCCESS") {
							leaveForm.submit();
						}else {
							$.messager.alert('提示','当前剩余年假时间为'+data.jsonReturn+'小时,小于所填请假小时数。','info');
							return;
						}
					});
				}
				else {
				    leaveForm.submit();
				}
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
	    <form class="form-horizontal" id="leaveForm" name="leaveForm" action="leave!reSubmitLeave" method="post">
		  <div class="ui-table ml15">
			<table class="ui-table-one" cellspacing="2" cellpadding="0" >
			  <caption class="mt30">请假单编辑</caption>  
			  <tr>
			    <td  class="ui-table-title">单据号：</td>
			    <td colspan="3">
				    ${leave.leaveCode}
				</td>
			  </tr>
			  <tr>
			    <input name="leave.leaveId" type="hidden" value="${leave.leaveId}"/>
			    <td  class="ui-table-title">休假类型：</td>
			    <td>
					<s:select
	                        id="typeId"
	                        tooltip="休假类型"
	                        name="leave.typeId"
	                        emptyOption="false"
	                        list="#{'1':'事假','2':'病假','3':'年假','4':'婚假','5':'产假','6':'丧假','7':'调休' }"
	                        cssClass="form-control "/>
				</td>
				<td  class="ui-table-title">休假小时：</td>
			    <td>
			          <input id="daysId" name="leave.leaveDays" type="text" class="form-control easyui-validatebox"  required="true" validType="realNumber" value="${leave.leaveDays}"/>
			    </td>
			  </tr>
			  <tr>
			        <td  class="ui-table-title">开始日期：</td>
                    <td>
                    <input id="startTimeId" name="leave.startTime" value="<s:date name="leave.startTime" format="yyyy-MM-dd" />" 
                       	 class="easyui-validatebox form-control" placeholder="开始日期" required="true"/>
                    
                    </td>
                    <td class="ui-table-title">结束日期：</td>
				    <td>
				    <input id="endTimeId" name="leave.endTime" value="<s:date name="leave.endTime" format="yyyy-MM-dd" />"  
			        class="easyui-validatebox form-control" placeholder="结束日期" required="true"/>
				    </td>
			    
			  </tr>
			  
			  <tr>
			        <td class="ui-table-title">休假原因：</td>
					<td colspan="3">
						<textarea rows="6" name="leave.comment" class="form-control easyui-validatebox" validType="maxLength['休假原因',100]">${leave.comment}</textarea>
					</td>
			  </tr>
			   <tr>
			        <td class="ui-table-title">历史审批意见：</td>
					<td colspan="3">
			    <% 
			    SLeave sleave = (SLeave)request.getAttribute("leave");
                ProcessEngine processEngine = Configuration.getProcessEngine();
            	TaskService taskService = processEngine.getTaskService();
            	ExecutionService executionService = processEngine.getExecutionService();
            	String processInstanceId = "leave."+sleave.getLeaveId();
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
					<a class="btn btn-success " href="javascript:editLeave();" id="cancel">提交</a>
					<a class="btn btn-warning " href="javascript:history.back();" id="cancel">取消</a>
				</div>
			</div>
		  </div>
		</form>
	</body>

</html>