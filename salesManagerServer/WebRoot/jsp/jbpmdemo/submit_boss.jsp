<%@page import="java.util.*,org.jbpm.api.*"%>
<%
	ProcessEngine processEngine = Configuration.getProcessEngine();
	TaskService taskService = processEngine.getTaskService();

	String taskId = request.getParameter("taskId");
	String result = request.getParameter("result");
	taskService.completeTask(taskId, result);
	response.sendRedirect("index.jsp");
%>