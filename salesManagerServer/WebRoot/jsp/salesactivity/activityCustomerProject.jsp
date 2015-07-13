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
<title>项目活动记录</title> 
<script type="text/javascript" src="<%=basePath %>script/common/jquery-1.4.2.min.js" ></script>
<script type="text/javascript" src="<%=basePath %>script/common/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath %>script/common/easyui/validate.js"></script>
<script type="text/javascript" src="<%=basePath %>script/common/easyui/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath %>script/common/easyui/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>script/common/easyui/themes/default/easyui.css" />
<link href="<%=basePath %>skin/Default/css/base.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>skin/Default/css/global.css" rel="stylesheet" type="text/css" /> 
<script type="text/javascript"> 
var basePath = "<%=path%>/"; 

function submitActivity(){
	if ($('#activity').form('validate')) {
		activity.submit();
	}
}
 
function cancelActivity(){ 
	var url = basePath + "project!index";
	url = encodeURI(url);
	window.location.href = url;
}
</script>
</head>
<body>
<div class="panel panel-success" id="searchResult" style="padding:2px;">
     <div class="panel-heading crm-table-title"><B>客户项目</B></div>
	<table class="ui-table-one" cellspacing="2" cellpadding="0" >
	<tr>
    <td  class="ui-table-title">客户名称：</td>
    <td><s:property value="customerProject.customerName"/></td>
    <td  class="ui-table-title">项目名称：</td>
    <td><s:property value="customerProject.name"/></td> 
  </tr> 
  <tr>
    <td  class="ui-table-title">开始日期：</td>
    <td>
  		<s:date name="customerProject.startTime" format="yyyy-MM-dd" />
    </td>
    <td class="ui-table-title">预计结束日期：</td>
    <td>
  		<s:date name="customerProject.planEndTime" format="yyyy-MM-dd" />
    </td>
  </tr> 
  <tr>
    <td  class="ui-table-title">预计年销售金额：</td>
    <td><s:property value="customerProject.amount"/></td>
    <td></td>
    <td></td>
  </tr>  
	 </table>
  </div>
  <div class="panel panel-success" id="activityList" style="padding:2px;">
     <div class="panel-heading crm-table-title"><B>销售活动</B></div>  
	  <table  class="table table-hover" cellspacing="2" cellpadding="0" >
	      <thead>
	        <th>主题</th>
	        <th>负责人</th>
	        <th>活动时间</th>
	        <th>进度</th>
	        <th>拜访对象</th>
	        <th>协同拜访人</th> 
	        <th>活动内容</th> 
	      </thead>
	      <s:iterator value="activityList" status="dl">
		      <tr>
		        <td class="ui-table-heedtwo"><s:property value="topical"/></td>
		        <td><s:property value="responseUserName"/></td>
		        <td><s:date name="activityDate" format="yyyy-MM-dd" /></td>
		        <td><s:property value="completionRate"/></td>
		        <td><s:property value="visitPerson"/></td>
		        <td><s:property value="coordinationVisitPerson"/></td>
		        <td><s:property value="comment"/></td>  
		      </tr>
	      </s:iterator>
		</table>
</div>

</body>
</html>