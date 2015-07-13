<%@ page language="java" import="com.sales.model.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>用户查看</title>
</head>
<body>
<div style="background:#fafafa;padding:10px;width:300px;height:300px;">
    
        <div>
            <label for="name">名称：</label>
            <s:property value="user.name"/>
        </div>
        <div>
            <label for="desc">描述：</label>
            <s:property value="user.desc"/>
        </div>
      
</div>

</body>
</html>