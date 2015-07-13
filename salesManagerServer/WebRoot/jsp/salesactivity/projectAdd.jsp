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
	
	<link type="text/css" href="<%=basePath %>skin/Default/css/jquery-ui-1.10.0.custom.css" rel="stylesheet"  />
	<link rel="stylesheet" type="text/css" href="<%=basePath %>script/common/easyui-1.3.5/themes/icon.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath %>script/common/easyui-1.3.5/themes/bootstrap/easyui.css" /> 
	<!-- Le HTML5 shim, for IE6-8 support of HTML elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
	
	<script src='dwr/interface/customerProjectService.js'></script>
	<script src='dwr/engine.js'></script>
	<script src='dwr/util.js'></script>
	<script type="text/javascript" language="javascript" src="<%=basePath %>script/common/common.js"></script>
	<script type="text/javascript" src="<%=basePath %>script/common/easyui-1.3.5/plugins/jquery.parser.js"></script>
</head>
<body class="ui-lv2bg">
<div class="panel panel-default">
<div class="panel-body">	
	<s:form id="project" name="project" action="project!add" method="post" cssClass="form-horizontal">
	<input type="hidden" name="project.projectId" value="<s:property value="project.projectId"/>" />
	<input type="hidden" name="project.status" id="status" value="<s:property value="project.status"/>"/></td>
	<div class="form-group">
		<label  class="col-sm-2 control-label">主题：</label>
		<div class="col-sm-10">
			<input class="form-control" id="topical" name="project.topical"  value="<s:property value="project.topical"/>" 
				placeholder="计划主题" required>
		</div>
	</div>
	<div class="form-group">
		<label for="responseId" class="col-sm-2 control-label">负责人：</label>
		<div class="col-sm-4">
			<input type="hidden" id="responseId" name="project.responseUserId" value="<s:property value="project.responseUserId"/>" />
			<input type="text" class="form-control" id="responseName" name="responseName" required
				onClick="getResponser();" placeholder="计划负责人" value="<s:property value="project.responseUserName"/>">
		</div>
		<label for="responseId" class="col-sm-2 control-label">计划类型：</label>
		<div class="col-sm-4">
			<s:select
                    id="enumerationId"
                    tooltip="计划类型"
                    name="project.enumerationId"
                    emptyOption="false"
                    
                    listKey="enumerationId" listValue="enumerationName" list="enumerationList" 
                    cssClass="form-control easyui-validatebox" required="true"/>
		</div>
	</div>
	<div class="form-group">
		<label for="project.goal" class="col-sm-2 control-label">目标：</label>
		<div class="col-sm-10">
			<textarea type="textarea" rows="4" class="form-control" id="project.goal" name="project.goal" required
				placeholder="计划目标描述" ><s:property value="project.goal"/> </textarea>
		</div>
	</div>
	<div class="form-group">
		<label for="startTime" class="col-sm-2 control-label">开始时间：</label>
		<div class="col-sm-4">
			<input class="form-control" id="startTime" name="project.startTime" 
				value="<s:property value="project.startTime"/>" required/>
		</div>
		<label for="responseId" class="col-sm-2 control-label">结束时间：</label>
		<div class="col-sm-4">
			<input class="form-control" id="endTime" ="true" name="project.endTime" 
				value="<s:property value="project.endTime"/>" required/>
		</div>
	</div>
	<div class="form-group">
		<label for="customerName" class="col-sm-2 control-label">客户：</label>
		<div class="col-sm-4">
			<input type="hidden" id="customerId" name="project.customerId" />
    		<input type="text" id="customerName" onClick="getCustomer();" value="<s:property value="project.customerName"/>" 
    			placeholder="选择客户" class="form-control"/>
		</div>
		<label for="responseId" class="col-sm-2 control-label">销售项目：</label>
		<div class="col-sm-4">
			<s:select theme="simple"  id="customerProjectId" cssClass="form-control" headerKey="0" headerValue="无销售项目"
				name="project.customerProjectId" listKey="projectId" listValue="name" list="customerProjectList" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">关注人：</label>
		<input id="attentionUserIds" name="attentionUserIds" type="hidden" />
		<div class="col-sm-10" id="attentionUser">
			<a class="btn btn-primary col-sm-1" onclick="getUser();">添加</a>&nbsp;
			<s:iterator value="project.attentionUserList" status="dl">
			    		<span class='label label-warning' style='margin:7px 0 0px 5px;cursor:pointer;' 
			    			id='<s:property value="userId"/>' ><s:property value="userName"/></span>
			</s:iterator>
		</div>
	</div>
	<div class="form-group">
		<label for="project.comment" class="col-sm-2 control-label">备注：</label>
		<div class="col-sm-10">
			<textarea name="project.comment" rows="4"  type="text" 
  				class="form-control" placeholder="计划内容说明"><s:property value="project.comment"/></textarea>
		</div>
	</div>

</div>

<!--  按钮面板  -->
	<div class="crm-button-panel form-group ">
		<div class="col-sm-6">
			
			<button class="btn btn-primary col-sm-offset-5 col-sm-3" id="save">保存</button>
			<button class="btn btn-success col-sm-offset-1 col-sm-3" id="submit">提交</button>
		</div>
		<div class="col-sm-6">
			<button class="btn btn-warning col-sm-offset-2 col-sm-3" id="cancel">取消</button>
		</div>
	</div>
</s:form>
</div>
<script type="text/javascript">
var basePath = "<%=path%>/";
$(document).ready(function(){ 
	$("#save").click(function(checkEvent){
		$("#status").attr("value", "0");
    	addProject();
    });
    $("#submit").click(function(checkEvent){
    	$("#status").attr("value", "1");
    	addProject();
    });
    
    $("#cancel").click(function(checkEvent){
    	checkEvent.preventDefault();
    	history.back();
    });
    
	$( "#startTime" ).datepicker({
		dateFormat: "yy-mm-dd"
	});
	$( "#endTime").datepicker({
		dateFormat: "yy-mm-dd"
	});
});

function addProject() {
	var attentionIds; 
	var attentions = $("#attentionUser span"); 
	for(var i=0; i<attentions.length; i++){ 
		if(i == 0){
			attentionIds = attentions.eq(i).attr("id");
		}
		else{
			attentionIds = attentionIds + "," + attentions.eq(i).attr("id");
		}
	} 
	
	$("#attentionUserIds").val(attentionIds);
	if($("#project").form('validate')) {
		$("#project").submit();
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

function getResponser(){ 
	var array = window.showModalDialog("<%=basePath %>/sUser!userOpenWindowList", "", 
			"dialogWidth:800px; dialogHeight:500px; status:no;help:no");
	//for chrome
    if (array == undefined) {
    	array = window.returnValue;
    }
	$("#responseId").val(array[0]);
	$("#responseName").val(array[1]);
}

function addAttUser(attUserId, attUserName){	
	//alert(attUserName);
	if(attUserId == undefined) {
		return;
	}
	$("#attentionUser").append("<span class='label label-warning col-sm-1'" +
			" style='margin:7px 0 0px 5px;cursor:pointer;' id='" + attUserId + 
			"' onclick='this.remove();'>" + attUserName + "</span>");
	return false;
}

function getUser(){ 
	var arrayUser = window.showModalDialog("<%=basePath %>/sUser!attentionUserList", "", "dialogWidth:800px; dialogHeight:530px; status:no;help:no");
	//for chrome
    if (arrayUser == undefined) {
    	arrayUser = window.returnValue;
    }
	addAttUser(arrayUser[0], arrayUser[1]); 
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
	$("#customerProjectId").append("<option value='0'>无销售项目</option>");  //为Select追加一个Option(下拉项) 
    dwr.util.addOptions("customerProjectId", data, "projectId", "name");		    
}

</script>
</body>
</html>