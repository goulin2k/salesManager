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
<script type="text/javascript" src="<%=basePath %>script/common/easyui/plugins/jquery.combotree.js"></script>
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
	<div class="ui-content-title"><p><img src="skin/Default/images/ui-content-ico/time.png" />新增销售计划</p></div>
<div class="ui-table ml15">
<form id="project" name="project" action="<%=basePath %>project!add" method="post">
<table class="ui-table-one" cellspacing="2" cellpadding="0" >

  <tr>
    <td class="ui-table-title">主题：</td>
    <td class="ui-table-input-r"><input class="easyui-validatebox" type="text" required="true" name="project.topical" validType="maxLength['主题',100]"/></td>
  </tr>
  <tr>
    <td class="ui-table-title">负责人：</td>
    <td class="ui-table-input-r">
    <input id="responseId" name="project.responseUserId" /></td> 
  </tr> 
  <tr>
    <td class="ui-table-title">目标：</td>
    <td class="ui-table-textarea">
  		<textarea name="project.goal" rows="4" class="easyui-validatebox" type="text" required="true" validType="maxLength['目标',300]"></textarea>
    </td>
  </tr> 
  <tr>
    <td class="ui-table-title">开始时间：</td>
    <td class="ui-table-input-r"><input class="ui-com-search-year" id="startTime" name="project.startTime" value="<s:property value="project.startTime"/>"/></td>
  </tr>
  <tr>
    <td class="ui-table-title">结束时间：</td>
    <td class="ui-table-input-r"><input class="ui-com-search-year" id="endTime" required="true" name="project.endTime" value="<s:property value="project.endTime"/>"/></td> 
  </tr> 
  <tr>
    <td class="ui-table-title">客户：</td>
    <td class="ui-table-input-r"><input type="hidden" id="customerId" name="project.customerId" />
    <input type="text" id="customerName" onClick="getCustomer();" /></td> 
  </tr> 
  <tr>
    <td class="ui-table-title">客户项目：</td>
    <td class="ui-table-select-s">
		<select id="customerProjectId" name="project.customerProjectId"></select>
	</td> 
  </tr> 
  <tr>
    <td class="ui-table-title">计划类型：</td>
    <td class="ui-table-input-r"><s:select theme="simple" name="project.enumerationId" listKey="enumerationId" listValue="enumerationName" list="enumerationList" /></td> 
  </tr> 
  
  <tr>
    <td  class="ui-table-title">关注人：</td>
    <td class="ui-table-heed">    	
    <ul id="attentionUser"> 
    </ul> 
	    <input name="savein" type="button" class="button" value="选择关注人" onClick="getUser();">
	    <input id="attentionUserIds" name="attentionUserIds" type="hidden" />
	</td>
  </tr>
  <tr>
    <td  class="ui-table-title">备注：</td>
    <td  class="ui-table-textarea">
  		<textarea name="project.comment" rows="4" class="easyui-validatebox" type="text" required="true" validType="maxLength['备注',500]"></textarea>
    </td>
  </tr> 
</table>
</form>
</div>
<div class="ui-button-big center mt30 mb30">
<a href="#"  class="fl mr10"onclick="addProject();">确定</a>
<a href="#" class="fl" onclick="cancleProject();">取消</a></div>
<script type="text/javascript">
var basePath = "<%=path%>/";
$(function(){
    $('#startTime').calendar();
    $('#endTime').calendar();
	$('#img').calendar({ id:'#cal3' });
});

function addProject() {
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

function addAttUser(attUserId, attUserName){
	$("#attentionUser").append("<li id='" + attUserId + "'>" + attUserName + "<img src='images/cancel.gif' height='18px' width='18px' onclick='$(this).parent().remove();'/></li>")
}

function getUser(){ 
	var array = window.showModalDialog("<%=basePath %>/sUser!attentionUserList", "", "dialogWidth:800px; dialogHeight:530px; status:no;help:no");
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
		
function getCustomerProjectList(customerId){
	customerProjectService.getCPListByCustomerId(customerId, fillCustomerProject);
}

function fillCustomerProject(data){
	dwr.util.removeAllOptions("customerProjectId");
    dwr.util.addOptions("customerProjectId", data, "projectId", "name");		    
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
 
$('#responseId').combotree('setValue', <s:property value="project.responseUserId"/>);  

</script>
</body>
</html>