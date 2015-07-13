<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>销售计划活动查看</title>

</head>
<body>

<%@include file="activity_fragment.jsp" %>

<!--  按钮面板  -->
	<div class="crm-button-panel form-group ">
		<div class="col-sm-offset-5 col-sm-4">
			<a class="btn btn-warning col-sm-3" id="cancel" onclick="history.back();">返回</a>
		</div>
	</div>

</body>
</html>