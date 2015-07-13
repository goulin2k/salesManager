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
<title>销售计划活动记录</title> 
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
$(function(){
    $('#activityDate').calendar(); 
	$('#img').calendar({ id:'#cal3' });
	$('#topical').focus(); 
});

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
<div class="ui-table ml15">
<table class="ui-table-one" cellspacing="2" cellpadding="0" >
<caption>销售活动</caption>
  <tr>
    <td  class="ui-table-title">主题：</td>
    <td class="ui-table-heed"><s:property value="project.topical"/></td>
  </tr>
  <tr>
    <td  class="ui-table-title">对应客户：</td>
    <td><s:property value="project.customerName"/></td>
    <td  class="ui-table-title">计划类型：</td>
    <td><s:property value="project.enumerationName"/></td>
  </tr>
  <tr>
    <td  class="ui-table-title">计划内容：</td>
    <td  class="ui-table-textarea" colspan="2">
  		<textarea name="comment" rows="4" readonly><s:property value="project.comment"/></textarea>
    </td>
  </tr>
  <tr>
    <td class="ui-table-title">计划目标：</td>
    <td class="ui-table-textarea" colspan="2">
  		<textarea name="goal" rows="4" readonly><s:property value="project.goal"/></textarea>
    </td>
  </tr> 
  <tr>
    <td  class="ui-table-title">负责人：</td>
    <td><s:property value="project.responseUserName"/></td>
    <td  class="ui-table-title">创建人：</td>
    <td><s:property value="project.createUserName"/></td>
  </tr>
  <tr>
    <td  class="ui-table-title">关注人：</td>
    <td class="ui-table-heed">
		<ul id="attentionUser">
	    	<s:iterator value="project.attentionUserList" status="dl">
	    		<li id='<s:property value="userId"/>'><s:property value="userName"/></li>
	    	</s:iterator>
	    </ul> 
	</td>
  </tr>
  <tr>
    <td  class="ui-table-title">开始时间：</td>
    <td><s:date name="project.startTime" format="yyyy-MM-dd" /></td>
    <td  class="ui-table-title">结束时间：</td>
    <td><s:date name="project.endTime" format="yyyy-MM-dd" /></td>
  </tr>
  <tr>
    <td  class="ui-table-title">进度：</td>
    <td><s:property value="project.completionRate"/></td>
    <td  class="ui-table-title">完成时间：</td>
    <td><s:date name="project.completionTime" format="yyyy-MM-dd" /></td>
  </tr>
  <tr>
    <td  class="ui-table-title">销售活动：</td>
    <td colspan="3">
    <!-- ==============嵌入的子表格样式================-->
    <table  class="ui-table-two" cellspacing="2" cellpadding="0" >
      <tr class="ui-table-title-two">
        <td>主题</td>
        <td>负责人</td>
        <td>活动时间</td>
        <td>进度</td>
        <td>拜访人</td>
        <td>协同拜访人</td> 
        <td>活动内容</td>
      </tr>
      <s:iterator value="project.activityList" status="dl">
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

    </td>
  </tr> 
  <tr>
    <td  class="ui-table-title">销售评价：</td>
    <td colspan="3">
    <!-- ==============嵌入的子表格样式================-->
    <table  class="ui-table-two" cellspacing="2" cellpadding="0" >
      <tr class="ui-table-title-two">
        <td>评价人</td>
        <td>评价内容</td>
        <td>评价时间</td> 
      </tr>
      <s:iterator value="project.assessList" status="dl">
      <tr> 
        <td><s:property value="assessUserName"/></td>
        <td><s:property value="comment"/></td>
        <td><s:date name="commentTime" format="yyyy-MM-dd" /></td> 
      </tr>
      </s:iterator>
    </table>

    </td>
  </tr> 
</table>
</div>
<form id="activity" name="activity" action="<%=basePath %>activity!addActivity" method="post">
<div class="ui-table ml15">
<table class="ui-table-one" cellspacing="2" cellpadding="0" >
   <caption>新增销售活动<input type="hidden" name="activity.projectId" value="<s:property value="project.projectId"/>"></input>
   						<input type="hidden" name="activity.customerId" value="<s:property value="project.customerId"/>"></input></caption> 
  <tr>
     <td class="ui-table-title">活动主题：</td>
     <td><input class="easyui-validatebox" id="topical" type="text" required="true" name="activity.topical" validType="maxLength['活动主题',100]"/></td>
  </tr>
   <tr>
     <td  class="ui-table-title">活动内容：</td>
     <td colspan="3" class="ui-table-textarea">
   		<textarea name="activity.comment" rows="6" class="easyui-validatebox" type="text" required="true" validType="maxLength['活动内容',500]"></textarea>
     </td>
   </tr>  
   <tr>
     <td  class="ui-table-title">进度：</td>
     <td class="ui-table-select-s"><s:select theme="simple" name="activity.completionRate" list="{'0%','20%','50%','80%','100%'}"/></td> 
  </tr>
  <tr>
     <td  class="ui-table-title">活动类型：</td>
     <td class="ui-table-select-s"><s:select theme="simple" name="activity.enumerationId" listKey="enumerationId" listValue="enumerationName" list="enumerationList" /></td> 
  </tr>
  <tr>
     <td  class="ui-table-title">活动时间：</td>
     <td class="ui-table-select-s"><input class="runcode" id="activityDate" name="activity.activityDate" value="<s:date name="activity.activityDate" format="yyyy-MM-dd" />" /></td> 
  </tr>
  <tr>
     <td  class="ui-table-title">拜访对象：</td>
     <td class="ui-table-select-s"><input type="text" name="activity.visitPerson" class="easyui-validatebox" required="true" validType="maxLength['拜访对象',50]" /></td> 
  </tr>
  <tr>
     <td  class="ui-table-title">协同拜访人：</td>
     <td class="ui-table-select-s"><input type="text" name="activity.coordinationVisitPerson" /></td> 
  </tr>
</table> 
</div>
<div class="ui-button-big center fl mt10 mb50 ">
<a href="#" class="fl mr10 " onClick="submitActivity();">确定</a>
<a href="#" class="fl" onClick="cancelActivity()">取消</a>
</div>
</form>
</body>
</html>