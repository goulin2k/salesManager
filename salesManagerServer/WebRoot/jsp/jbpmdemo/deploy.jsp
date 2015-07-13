<%@page import="java.util.*,org.jbpm.api.*,java.util.zip.*"%>
<%@page import="org.apache.struts2.ServletActionContext"%>
<%
	ProcessEngine processEngine = Configuration.getProcessEngine();
	RepositoryService repositoryService = processEngine
			.getRepositoryService();
	String jpdl = (String)request.getParameter("jpdlName");
	System.out.println(jpdl);
	repositoryService.createDeployment().
		addResourceFromClasspath(jpdl+".jpdl.xml").
		addResourceFromClasspath(jpdl+".png").deploy();
	//ZipInputStream zis = new ZipInputStream(this.getClass()
	//		.getResourceAsStream("/" + jpdl + ".zip"));
	//repositoryService.createDeployment()
	//		.addResourcesFromZipInputStream(zis).deploy();
	response.sendRedirect("index.jsp");
%>