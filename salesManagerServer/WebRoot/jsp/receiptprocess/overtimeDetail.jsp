<%@ page language="java" import="java.util.*,org.jbpm.api.*,org.jbpm.api.task.*,com.sales.model.*,com.sales.common.*,org.jbpm.api.history.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>加班查看</title>
		<script type="text/javascript" src="script/common/easyui/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="script/common/easyui/validate.js"></script>
		<script type="text/javascript" src="script/common/easyui/locale/easyui-lang-zh_CN.js"></script>
		<link rel="stylesheet" type="text/css" href="script/common/easyui/themes/icon.css" />
		<link rel="stylesheet" type="text/css" href="script/common/easyui/themes/default/easyui.css" />
		
		<script type="text/javascript">
        
		</script>
	</head>
	<body class="ui-lv2bg">
	    <div class="ui-content-title"><p><img src="skin/Default/images/ui-content-ico/1.png" />加班查看</p></div>
	    
		  <div class="ui-table ml15">
			<table class="ui-table-one" cellspacing="2" cellpadding="0" >
			  <!--<caption>这里写入表格的标题名称</caption>  -->
			  <tr>
			    <td  class="ui-table-title">单据号：</td>
			    <td colspan="3">
				    ${ot.overtimeCode}
				</td>
			  </tr>
			  <tr>
			        <td  class="ui-table-title">加班日期：</td>
                    <td><s:date name="ot.startTime" format="yyyy-MM-dd" /></td>
                    <td class="ui-table-title">加班小时数：</td>
				    <td>${ot.hours}小时</td>
			    
			  </tr>
			  <% 
			    SOvertime sot = (SOvertime)request.getAttribute("ot");
                ProcessEngine processEngine = Configuration.getProcessEngine();
            	TaskService taskService = processEngine.getTaskService();
            	ExecutionService executionService = processEngine.getExecutionService();
            	String executionId = "overtime."+sot.getOvertimeId();
            	ProcessInstance processInstance = executionService.findProcessInstanceById(executionId);
            	String activityName = "";
            	if (processInstance == null) {
            		activityName = Constants.PROCESS_END;
            	} else {
            		String tempstr = processInstance.findActiveActivityNames().toString();
            		activityName = tempstr.substring(1,tempstr.length()-1);            		
            	}
              %>
			  <tr>
			        <td class="ui-table-title">当前状态：</td>
					<td colspan="3">
						<%=activityName%>
					</td>
			  </tr>
			  <tr>
			        <td class="ui-table-title">加班说明：</td>
					<td colspan="3">
						${ot.comment}
					</td>
			  </tr>
			   <tr>
			        <td class="ui-table-title">历史审批意见：</td>
					<td colspan="3">
			    <% 
            	String processInstanceId = "overtime."+sot.getOvertimeId();
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
			  <%if (processInstance != null) { %>
			  <tr>
					<td colspan="4">
						<img src="process!showProcessPic?id=<%=processInstance.getId() %>"/>
					</td>
			  </tr> 
			  <%} %>
			</table>
			<div class="ui-button-big center "><a href="javascript:history.back();">返回</a></div>
		  </div>
		
	</body>

</html>