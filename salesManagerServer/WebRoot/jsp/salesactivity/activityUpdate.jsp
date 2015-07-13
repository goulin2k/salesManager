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
<title>修改销售活动</title>

<script type="text/javascript">
var basePath = "<%=path%>/";
function getCustomer(){ 
	var array = window.showModalDialog("<%=basePath %>/customer!customerOpenWindow", "", "dialogWidth:800px; dialogHeight:530px; status:no;help:no");
	//for chrome
    if (array == undefined) {
    	array = window.returnValue;
    }
	$("#customerId").val(array[0]);
	$("#customerName").val(array[1]); 
}

function cancelActivity(){ 
	var url = basePath + "activity!index";
	url = encodeURI(url);
	window.location.href = url;
}

$(function(){
    $('#activityDate').calendar(); 
	$('#img').calendar({ id:'#cal3' });
});
</script>
</head>
<body>

<form id="activity" name="activity" action="<%=basePath %>activity!update" method="post">
<div class="ui-table ml15">
<table class="ui-table-one" cellspacing="2" cellpadding="0" >
   
  <tr>
     <td class="ui-table-title">活动主题：<input type="hidden" name="activity.activityId" value="<s:property value="activity.activityId"/>"></input></td>
     <td><input class="easyui-validatebox" type="text" required="true" name="activity.topical" validType="maxLength['活动主题',100]" value="<s:property value="activity.topical"/>"/></td>
     <td  class="ui-table-title">对应销售计划：</td>
     <td><s:property value="activity.projectTopical"/></td> 
  </tr>
   <tr>
     <td  class="ui-table-title">活动内容：</td>
     <td colspan="3" class="ui-table-textarea">
   		<textarea name="activity.comment" rows="6" class="easyui-validatebox" type="text" required="true" validType="maxLength['活动内容',500]"><s:property value="activity.comment"/></textarea>
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
     <td class="ui-table-select-s"><input class="runcode" id="activityDate" name="activity.activityDate" value="<s:property value="activity.activityDate"/>" /></td> 
  </tr>
  <tr>
     <td  class="ui-table-title">对应客户：</td>
     <td class="ui-table-select-s">
     	<s:if test="activity.projectId==null">
     	<input type="hidden" id="customerId" name="activity.customerId" value="<s:property value="activity.customerId"/>" />
        <input type="text" class="ui-com-search-input" id="customerName" name="activity.customerName" onClick="getCustomer();" value="<s:property value="activity.customerName"/>" />
		</s:if>
		<s:else>
     	<input type="hidden" id="customerId" name="activity.customerId" value="<s:property value="activity.customerId"/>" />
        <input type="text" class="ui-com-search-input" id="customerName" name="activity.customerName" disabled value="<s:property value="activity.customerName"/>" />
		</s:else> 
     </td> 
  </tr>
  <tr>
    <td class="ui-table-title">客户项目：</td>
    <td class="ui-table-select-s"> 
		<s:select theme="simple" id="customerProjectId" name="activity.customerProjectId" listKey="projectId" listValue="name" list="customerProjectList" />
	</td> 
  </tr> 
  <tr>
     <td  class="ui-table-title">拜访对象：</td>
     <td class="ui-table-select-s"><input type="text" name="activity.visitPerson" class="easyui-validatebox" required="true" validType="maxLength['拜访对象',50]" value="<s:property value="activity.visitPerson"/>" /></td> 
  </tr>
  <tr>
     <td  class="ui-table-title">协同拜访人：</td>
     <td class="ui-table-select-s"><input type="text" name="activity.coordinationVisitPerson" value="<s:property value="activity.coordinationVisitPerson"/>" /></td> 
  </tr>
</table> 
</div>
<div class="ui-button-big center fl mt10 mb50 "><a href="#" class="fl mr10 " onClick="activity.submit();">确定</a>
<a href="#" class="fl" onClick="cancelActivity()">取消</a></div>
</form>
</body>
</html>