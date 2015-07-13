<%@ page language="java" import="com.sales.model.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sales" uri="/WEB-INF/sales-util.tld"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<htmlxmlns="http://www.w3.org/1999/xhtml">
<head>
<title> 用户列表 </title>

<script type="text/javascript" src="<%=basePath %>dwr/engine.js"></script>     
<script type="text/javascript" src="<%=basePath %>dwr/util.js"></script>
<script type="text/javascript" src="<%=basePath %>dwr/interface/msgPushManager.js"></script> 
<script type="text/javascript" src="<%=basePath %>script/common/jquery-1.4.2.min.js" ></script>
<link rel="stylesheet" href="<%=basePath %>script/common/autocomplete/jquery.autocomplete.css" type="text/css"></link>
<script type="text/javascript" src="<%=basePath %>script/common/autocomplete/jquery.autocomplete.js" ></script>
<script type="text/javascript">

dwr.engine.setActiveReverseAjax(true); // 激活反转 重要
dwr.engine.setNotifyServerOnPageUnload(true);
//msgPushManager.dwrSessionBinding('<s:property value="user.id"/>');
function sendMsg() {
	var receiver = document.getElementById("receiver").value;
	//alert(receiver);
	msgPushManager.send('<s:property value="user.id"/>', receiver, "消息测试"); // 发送消息
}
function showMsg(msg) {
	alert(msg);
}

</script>
</head>

<body>

<table>
<tr>
<th> ID</th>
<th> 姓名</th>
<th> 描述 </th>

</tr>
<s:iterator value="userList" status="dl">
<tr>
<td><s:property value="id"/></td>
<td><s:property value="name"/></td>
<td><s:property value="desc"/></td>
<td><a href="<%=basePath %>user/<s:property value="id"/>"> 查看 </a> |
<a href="<%=basePath %>user/<s:property value="id"/>/delete"> 删除 </a></td>
</tr>
</s:iterator>
</table>
<table>
<tr>
<td>
autocomplete:<sales:autoComplete url="user!autoComplete" displayName="name" textName="username" textId="test"/><sales:autoComplete url="user!autoComplete" displayName="desc" textName="userdesc" textId="test1"/>
</td>
</tr>
<tr>
<td>
dwr消息push：
<input  type="text" name="receiver" id="receiver"></input>     
<input name="savein" type="button" class="button" value="发送" onClick="sendMsg();">
</td>
</tr>
<tr>
<td>
<a href="<%=basePath %>user/new"> 创建新用户 </a>
</td>
</tr>
</table>          


</body>
</html>