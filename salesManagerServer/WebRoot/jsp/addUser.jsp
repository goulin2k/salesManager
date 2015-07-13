<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>新增用户</title>
<script type="text/javascript" src="<%=basePath %>script/common/jquery-1.4.2.min.js" ></script>
<script type="text/javascript" src="<%=basePath %>script/common/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath %>script/common/easyui/validate.js"></script>
<script type="text/javascript" src="<%=basePath %>script/common/easyui/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath %>script/common/easyui/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>script/common/easyui/themes/default/easyui.css" />

<script type="text/javascript">
function addUser () {
	if ($('#user').form('validate')) {
		user.submit();
	}
}
</script>
</head>
<body>

<div style="background:#fafafa;padding:10px;width:300px;height:300px;">
    <form id="user" name="user" action="<%=basePath %>user!add" method="post">
        <div>
            <label for="name">名称：</label>
            <input  type="text" name="name" class="easyui-validatebox bor_height" required="true" validType="maxLength['名称',5]"></input>
        </div>
        <div>
            <label for="desc">描述：</label>
            <input  type="text" name="desc" class="easyui-validatebox bor_height" required="true" validType="maxLength['描述',10]"></input>
        </div>
        <div>
            <input name="savein" type="button" class="button" value="新增" onClick="addUser();">
        </div>
    </form>
</div>

</body>
</html>