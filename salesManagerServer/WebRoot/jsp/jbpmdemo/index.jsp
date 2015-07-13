<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="checkLogin.jsp" %>
<%@page import="java.util.*,org.jbpm.api.*,org.jbpm.api.task.*" %>
<%
	ProcessEngine processEngine = Configuration.getProcessEngine();
	RepositoryService repositoryService = processEngine.getRepositoryService();
	ExecutionService executionService = processEngine.getExecutionService();
	TaskService taskService = processEngine.getTaskService();
	
	String username = (String) session.getAttribute("username");
	
	
	List<ProcessDefinition> pdList = repositoryService.createProcessDefinitionQuery().list();
	List<ProcessInstance> piList = executionService.createProcessInstanceQuery().list();
	//根据用户名查找该用户指定的待办任务
	List<Task> taskList = taskService.findPersonalTasks(username);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>index</title>
  </head>
  <body>
  			<li>发布新流程:</li> 
  			<a href="deploy.jsp?jpdlName=tripApp">出差申请</a>
  			<a href="deploy.jsp?jpdlName=overtime">加班申请</a>
    		<a href="deploy.jsp?jpdlName=leave">请假申请</a>
    		<a href="deploy.jsp?jpdlName=expenseApp">费用报销申请</a>
    		<a href="deploy.jsp?jpdlName=expenseRei">费用报销</a>
    		<a href="deploy.jsp?jpdlName=carRepairApp">车辆维修申请</a>
    		<a href="deploy.jsp?jpdlName=carRepairRei">车辆维修报销</a>
    		&nbsp;[username: <%=username %>]
    		<a href="login.jsp">登陆</a>

    <table border="1" width="100%">
      <caption>流程定义</caption>
      <thead>
        <tr>
          <td>i
          d</td>
          <td>name</td>
          <td>version</td>
          <td>&nbsp;</td>
        </tr>
      </thead>
      <tbody>
<%
	for (ProcessDefinition pd : pdList) {
%>
	    <tr>
	      <td><%=pd.getId() %></td>
	      <td><%=pd.getName() %></td>
	      <td><%=pd.getVersion() %></td>
	      <td>
	        <a href="remove.jsp?id=<%=pd.getDeploymentId() %>">remove</a>
	        &nbsp;|&nbsp;
	        <a href="start.jsp?id=<%=pd.getId() %>">start</a>
	      </td>
	    </tr>
<%
	}
%>
	  </tbody>
	</table> 

    <table border="1" width="100%">
      <caption>流程实例</caption>
      <thead>
        <tr>
          <td>id</td>
          <td>activity</td>
          <td>state</td>
          <td>&nbsp;</td>
        </tr>
      </thead>
      <tbody>
<%
	for (ProcessInstance pi : piList) {
%>
	    <tr>
	      <td><%=pi.getId() %></td>
	      <td><%=pi.findActiveActivityNames() %></td>
	      <td><%=pi.getState() %></td>
	      <td><a href="view.jsp?id=<%=pi.getId() %>">view</a></td>
	    </tr>
<%
	}
%>
	  </tbody>
	</table> 

    <table border="1" width="100%">
      <caption>待办任务</caption>
      <thead>
        <tr>
          <td>id</td>
          <td>name</td>
          <td>&nbsp;</td>
        </tr>
      </thead>
      <tbody>
<%
	for (Task task : taskList) {
%>
	    <tr>
	      <td><%=task.getId() %></td>
	      <td><%=task.getName() %></td>
	      <td><a href="<%=task.getFormResourceName() %>?id=<%=task.getId() %>">view</a></td>
	    </tr>
<%
	}
%>
	  </tbody>
	</table> 
  </body>
</html>
