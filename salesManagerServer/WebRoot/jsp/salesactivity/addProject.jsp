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
<title>新增销售计划</title>
<script type="text/javascript" src="<%=basePath %>script/common/jquery-1.4.2.min.js" ></script>
<script type="text/javascript" src="<%=basePath %>script/common/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath %>script/common/easyui/validate.js"></script>
<script type="text/javascript" src="<%=basePath %>script/common/easyui/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath %>script/common/easyui/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>script/common/easyui/themes/default/easyui.css" />
<link href="<%=basePath %>skin/Default/css/base.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>skin/Default/css/global.css" rel="stylesheet" type="text/css" />
	<link rel="icon" href="<%=basePath %>/jsp/lhgcalendar/favicon.ico" type="image/x-icon"/>
	<link rel="shortcut icon" href="<%=basePath %>/jsp/lhgcalendar/favicon.ico" type="image/x-icon"/>
	<link href="<%=basePath %>/jsp/lhgcalendar/_doc/common.css" type="text/css" rel="stylesheet"/>
	<link href="<%=basePath %>/jsp/lhgcalendar/_doc/prettify/prettify.css" type="text/css" rel="stylesheet"/>
	<script type="text/javascript" src="<%=basePath %>/jsp/lhgcalendar/_doc/prettify/prettify.js"></script>
	<script type="text/javascript" src="<%=basePath %>/jsp/lhgcalendar/lhgcalendar.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>/jsp/lhgcalendar/_demo/demo.js"></script>

<script type="text/javascript">
$(function(){
    $('#startTime').calendar();
    $('#endTime').calendar();
	$('#img').calendar({ id:'#cal3' });
});

function addProject() {
	if ($('#project').form('validate')) {
		project.submit();
	}
}
</script>
</head>
<body>
<div class="ui-table ml15">
<form id="project" name="project" action="<%=basePath %>project!add" method="post">
<table class="ui-table-one" cellspacing="2" cellpadding="0" >
<caption>新增销售计划</caption> 
  <tr>
    <td class="ui-table-title">主题：</td>
    <td class="ui-table-input-r"><input name="project.topical" type="text" /></td>
  </tr>
  <tr>
    <td class="ui-table-title">负责人：</td>
    <td class="ui-table-input-r"><input name="project.responseUserId" type="text" /></td> 
  </tr> 
  <tr>
    <td class="ui-table-title">目标：</td>
    <td class="ui-table-textarea">
  		<textarea name="project.goal" rows="4"></textarea>
    </td>
  </tr> 
  <tr>
    <td class="ui-table-title">开始时间：</td>
    <td class="ui-table-input-r"><input class="ui-com-search-year" id="startTime" name="project.startTime"/></td>
  </tr>
  <tr>
    <td class="ui-table-title">结束时间：</td>
    <td class="ui-table-input-r"><input class="ui-com-search-year" id="endTime" name="project.endTime"/></td> 
  </tr> 
  <tr>
    <td class="ui-table-title">客户：</td>
    <td class="ui-table-input-r"><input type="text" name="project.customerId" validType="maxLength['customerId', 20]" /></td> 
  </tr> 
  <tr>
    <td class="ui-table-title">计划类型：</td>
    <td class="ui-table-input-r">
    <s:select name="project.enumerationId" listKey="enumerationId" listValue="enumerationName" list="enumerationList" />
    </td> 
  </tr> 
  <tr>
    <td  class="ui-table-title">内容：</td>
    <td  class="ui-table-textarea">
  		<textarea name="project.comment" rows="4"></textarea>
    </td>
  </tr>
  <tr>
    <td  class="ui-table-title"><input name="savein" type="button" class="button" value="新增" onClick="project.submit();"></td> 
  </tr>
</table>
</form>
</div>

</body>
</html>