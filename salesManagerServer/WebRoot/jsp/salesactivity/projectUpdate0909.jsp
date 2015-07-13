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
<title>销售计划</title>
<script type="text/javascript" src="<%=basePath %>script/common/jquery-1.4.2.min.js" ></script>
<script type="text/javascript" src="<%=basePath %>script/common/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath %>script/common/easyui/plugins/jquery.combotree.js"></script>
<script type="text/javascript" src="<%=basePath %>script/common/easyui/validate.js"></script>
<script type="text/javascript" src="<%=basePath %>script/common/easyui/locale/easyui-lang-zh_CN.js"></script>
<script src='dwr/interface/customerProjectService.js'></script>
<script src='dwr/engine.js'></script>
<script src='dwr/util.js'></script>
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
</head>
<body class="ui-lv2bg">
	<div class="ui-content-title"><p><img src="skin/Default/images/ui-content-ico/time.png" />销售计划</p></div>

<div class="ui-table ml15">
<form id="project" name="project" action="<%=basePath %>project!update" method="post">
<table class="ui-table-one" cellspacing="2" cellpadding="0" >
<caption><img src="skin/Default/images/ui-content-ico/smg-plan.png" />销售计划</caption>
  <tr>
    <td  class="ui-table-title">主题：<input type="hidden" name="project.projectId" value="<s:property value="project.projectId"/>" /></td>
    <td class="ui-table-heed"><input class="easyui-validatebox" type="text" required="true" value="<s:property value="project.topical"/>" name="project.topical" validType="maxLength['活动主题',10]"/></td>
    <td  class="ui-table-title">负责人：</td>
    <td>
    <input id="responseId" name="project.responseUserId" value="<s:property value="project.responseUserName"/>" /></td>
  </tr>
  <tr>
    <td  class="ui-table-title">对应客户：</td>
    <td><input type="hidden" id="customerId" name="project.customerId" />
    <input type="text" id="customerName" onClick="getCustomer();" value="<s:property value="project.customerName"/>" /></td>
    <td  class="ui-table-title">计划类型：</td>
    <td><s:select theme="simple" name="project.enumerationId" listKey="enumerationId" listValue="enumerationName" list="enumerationList" /></td>
  </tr>
  <tr>
    <td class="ui-table-title">客户项目：</td>
    <td class="ui-table-select-s"> 
		<s:select theme="simple" id="customerProjectId" name="project.customerProjectId" listKey="projectId" listValue="name" list="customerProjectList" />
	</td> 
  </tr> 
  <tr>
    <td  class="ui-table-title">备注：</td>
    <td  class="ui-table-textarea" colspan="4">
  		<textarea name="project.comment" rows="4" class="easyui-validatebox" required="true" validType="maxLength['备注',500]"><s:property value="project.comment"/></textarea>
    </td>
  </tr>
  <tr>
    <td class="ui-table-title">计划目标：</td>
    <td class="ui-table-textarea" colspan="4">
  		<textarea name="project.goal" rows="4" class="easyui-validatebox" required="true" validType="maxLength['目标',300]"><s:property value="project.goal"/></textarea>
    </td>
  </tr>  
  <tr>
    <td  class="ui-table-title">关注人：</td>
    <td class="ui-table-heed">
    <ul id="attentionUser">
    	<s:iterator value="project.attentionUserList" status="dl">
    		<li id='<s:property value="userId"/>'><s:property value="userName"/><img src="images/cancel.gif" height="18px" width="18px" onclick="$(this).parent().remove();"/></li>
    	</s:iterator>
    </ul> 
    <input name="savein" type="button" class="button" value="选择关注人" onClick="getUser();">
    <input id="attentionUserIds" name="attentionUserIds" type="hidden" />
    </td>
  </tr>
  <tr>
    <td  class="ui-table-title">开始时间：</td>
    <td><input class="ui-com-search-year" id="startTime" name="project.startTime" value="<s:property value="project.startTime"/>"/></td>
    <td  class="ui-table-title">结束时间：</td>
    <td><input class="ui-com-search-year" id="endTime" name="project.endTime" value="<s:property value="project.endTime"/>"/></td>
  </tr> 
  <tr>
    <td  class="ui-table-title">进度：</td>
    <td><s:property value="project.completionRate"/></td>
    <td  class="ui-table-title">完成时间：</td>
    <td><s:date name="project.completionTime" format="yyyy-MM-dd" /></td>
  </tr>
  </table>
     <div class="ui-button-big center fl mt20 mb50 ">
    <a href="#" class="fl mr10 " onClick="updateProject();">修改</a>
    <a href="#" class="fl" onClick="cancleProject()">取消</a>
    </div> <div class="clear"></div>
  <table>
  <tr>
    <td  class="ui-table-title">销售活动：</td>
    <td colspan="3">
    <!-- ==============嵌入的子表格样式================-->
    <table  class="ui-table-two" cellspacing="2" cellpadding="0" >
    <caption><img src="skin/Default/images/ui-content-ico/Sell32.png" />销售活动</caption>
      <tr class="ui-table-title-two">
        <td>主题</td>
        <td>负责人</td>
        <td>活动时间</td>
        <td>进度</td>
        <td>拜访对象</td>
        <td>协同拜访人</td> 
        <td>活动内容</td>
	    <td>操作</td>
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
		<td><a href="<%=basePath %>activity!get?activity.activityId=<s:property value="activityId"/>"> 修改  </a></td>
      </tr>
      </s:iterator>
    </table>

    </td>
  </tr> 
	  </table>
<div class="ui-button-big fr mt10 mb10 ">
<a href="#" class="fl mr10 " onClick="addProjectActivity(<s:property value="project.projectId"/>);">新增活动</a>
</div>

<div class="clear"></div>
<table>
  	<caption><img src="skin/Default/images/ui-content-ico/list-go.png" />销售评价</caption>
    <!-- ==============嵌入的子表格样式================-->
    <table  class="ui-table-two" cellspacing="2" cellpadding="0" >
      <tr class="ui-table-title-two">
        <td>评价人</td>
        <td>评价内容</td>
        <td>评价时间</td> 
	    <td>操作</td>
      </tr>
      <s:iterator value="project.assessList" status="dl">
      <tr> 
        <td><s:property value="assessUserName"/></td>
        <td><s:property value="comment"/></td>
        <td><s:date name="commentTime" format="yyyy-MM-dd" /></td> 
		<td><a href="<%=basePath %>assess!get?assess.assessId=<s:property value="assessId"/>"> 修改  </a></td>
      </tr>
      </s:iterator>
    </table>

    
</table>
<div class="ui-button-big  fr mt10 mb50 ">
<a href="#" class="fl mr10 " onClick="addProjectAssess(<s:property value="project.projectId"/>);">新增评价</a>
</div>
</form>
</div>


<script type="text/javascript">
var basePath = "<%=path%>/";
$(function(){
    $('#startTime').calendar();
    $('#endTime').calendar();
	$('#img').calendar({ id:'#cal3' });
});

function updateProject() {
	if ($('#project').form('validate')) {
		var attentionIds; 
		var attentions = $("#attentionUser li"); 
		for(var i=0; i<attentions.length; i++){ 
			if(i == 0){
				attentionIds = attentions.eq(i).attr("id");
			}
			else{
				attentionIds = attentionIds + "," + attentions.eq(i).attr("id");
			}
		} 
		$("#attentionUserIds").val(attentionIds);
		project.submit();
	}
} 

function getCustomer(){ 
	var array = window.showModalDialog("<%=basePath %>/customer!customerOpenWindow", "", "dialogWidth:800px; dialogHeight:530px; status:no;help:no");
	//for chrome
    if (array == undefined) {
    	array = window.returnValue;
    }
	$("#customerId").val(array[0]);
	$("#customerName").val(array[1]); 
	getCustomerProjectList(array[0]);
}
		
function getCustomerProjectList(customerId){
	customerProjectService.getCPListByCustomerId(customerId, fillCustomerProject);
}

function fillCustomerProject(data){
	dwr.util.removeAllOptions("customerProjectId");
    dwr.util.addOptions("customerProjectId", data, "projectId", "name");		    
}

function addAttUser(attUserId, attUserName){
	$("#attentionUser").append("<li id='" + attUserId + "'>" + attUserName + "<img src='images/cancel.gif' height='18px' width='18px' onclick='$(this).parent().remove();'/></li>")
}

function getUser(){ 
	var array = window.showModalDialog("<%=basePath %>/sUser!userOpenWindowList", "", "dialogWidth:800px; dialogHeight:530px; status:no;help:no");
	//for chrome
    if (array == undefined) {
    	array = window.returnValue;
    }
	addAttUser(array[0], array[1]); 
}

function cancleProject(){ 
	var url = basePath + "project!index";
	url = encodeURI(url);
	window.location.href = url;
}

function addProjectActivity(projectId){ 
	var url = basePath + "activity!showActivityProject?activity.projectId=" + projectId;
	url = encodeURI(url);
	window.location.href = url;
}

function addProjectAssess(projectId){ 
	var url = basePath + "assess!editNew?assess.projectId=" + projectId;
	url = encodeURI(url);
	window.location.href = url;
}
 
$('#responseId').combotree
   ({ url: '<%=basePath %>/sUser!jsonUser',
       valueField: 'userId',
       textField: 'userId',
       required: true,
       editable: false,
       onClick: function (node) { 
            //JJ.Prm.GetDepartmentUser(node.id, 'selUserFrom');
       }, //全部折叠
       onLoadSuccess: function (node, data) {
           //$('#selDepartmentFrom').combotree('tree').tree("collapseAll"); 
       }   
});  
</script>
</body>
</html>