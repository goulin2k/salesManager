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
<title>销售计划编辑</title>
	<link type="text/css" href="<%=basePath %>skin/Default/css/jquery-ui-1.10.0.custom.css" rel="stylesheet"  />
	<!-- Le HTML5 shim, for IE6-8 support of HTML elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
	<script type="text/javascript" language="javascript" src="<%=basePath %>script/common/jquery-ui-1.10.0.custom.min.js"></script>
	<script src='dwr/interface/customerProjectService.js'></script>
	<script src='dwr/engine.js'></script>
	<script src='dwr/util.js'></script>
	<script type="text/javascript" language="javascript" src="<%=basePath %>script/common/common.js"></script>
</head>
<body class="ui-lv2bg">

<form id="projectForm" name="projectForm" action="<%=basePath %>project!update" method="post">
	<!-- 标准工具栏 -->
	<nav class="navbar navbar-default crm-toolbar" role="navigation">
		<ul class="nav navbar-nav">
	    	<li class="active"><a href="javascript:history.back();"><span class="glyphicon glyphicon-arrow-left"></span>&nbsp;返回</a></li>
	    </ul>
	 </nav>
	 
	<div class="panel panel-success" style="padding:2px;">
     
	<table class="ui-table-one" cellspacing="2" cellpadding="0" >
	
	  <tr>
	    <td  class="ui-table-title">主题：
	    	<input type="hidden" name="project.projectId" value="<s:property value="project.projectId"/>" /></td>
	    <td class="ui-table-heed">
	    	<input class="form-control" type="text"  value="<s:property value="project.topical"/>" id="topical"
	    		name="project.topical" validType="maxLength['活动主题',10]"/></td>
	    <td  class="ui-table-title">负责人：</td>
	    <td>
	    	<input id="responseId" name="project.responseUserId"  disabled="disabled"
	    		value="<s:property value="project.responseUserName"/>" class="form-control"/></td>
	  </tr>
	  <tr>
	    <td  class="ui-table-title">对应客户：</td>
	    <td>
	    	<input type="hidden" id="customerId" name="project.customerId" />
	    	<input type="text" id="customerName" onClick="getCustomer();" value="<s:property value="project.customerName"/>" class="form-control"/></td>
	    <td  class="ui-table-title">计划类型：</td>
	    <td>
	    	<s:select theme="simple"  name="project.enumerationId" listKey="enumerationId" 
	    		listValue="enumerationName" list="enumerationList" cssClass="form-control"/></td>
	  </tr>
	  <tr>
	    <td class="ui-table-title">客户项目：</td>
	    <td class="ui-table-select-s"> 
			<s:select theme="simple"  id="customerProjectId" cssClass="form-control"
				name="project.customerProjectId" listKey="projectId" listValue="name" list="customerProjectList" />
		</td> 
	  </tr> 
	  <tr>
	    <td  class="ui-table-title">备注：</td>
	    <td  class="ui-table-textarea" colspan="4">
	  		<textarea name="project.comment" rows="4" class="form-control" 
	  			required="true" validType="maxLength['备注',500]"><s:property value="project.comment"/></textarea>
	    </td>
	  </tr>
	  <tr>
	    <td class="ui-table-title">计划目标：</td>
	    <td class="ui-table-textarea" colspan="4">
	  		<textarea name="project.goal" rows="4" class="form-control" 
	  			required="true" validType="maxLength['目标',300]"><s:property value="project.goal"/></textarea>
	    </td>
	  </tr>  
	  <tr>
	    <td  class="ui-table-title">关注人：</td>
	    <td class="ui-table-heed">
		    <div>	
		    	<div class="col-sm-4">
		    	<button class="btn btn-primary" id="addAttentionUser" style="width:60px;" onclick="getUser();">添加</button>&nbsp;
		    	</div>
		    	<div class="col-sm-8" id="attentionUser">		    	
			    	<s:iterator value="project.attentionUserList" status="dl">
			    		<span class='label label-warning' style='margin:7px 0 0px 5px;cursor:pointer;' 
			    			id='<s:property value="userId"/>' ><s:property value="userName"/></span>
			    	</s:iterator>
		    	</div>
		    </div>
		    <input id="attentionUserIds" name="attentionUserIds" type="hidden" />
	    </td>
	  </tr>
	  <tr>
	    <td  class="ui-table-title">开始时间：</td>
	    <td>
	    	<input class="form-control" id="startTime" name="project.startTime" 
	    		 value="<s:property value="project.startTime"/>"/></td>
	    <td  class="ui-table-title">结束时间：</td>
	    <td>
	    	<input class="form-control" id="endTime" name="project.endTime"  value="<s:property value="project.endTime"/>"/></td>
	  </tr> 
	  
	 </table>
	 
	 <!--  按钮面板  -->
	<div class="crm-button-panel form-group ">
		<div class="col-sm-6">
			<button class="btn btn-primary col-sm-offset-9 col-sm-3" id="submit">确定</button>
		</div>
		<div class="col-sm-6">
			<button class="btn btn-warning col-sm-3" id="cancel">取消</button>
		</div>
	</div>
  </div>
  
</form>    
	<script type="text/javascript">
		var basePath = "<%=path%>/";
		$(document).ready(function(){  
		    $("#submit").click(function(checkEvent){
		    	var topical = $.trim($("#topical").val());
				if(topical == "") {
					showDialog(BootstrapDialog.TYPE_WARNING,"输入提示" ,"请选择输入计划主题信息！");
					$("#topical").focus();
					checkEvent.preventDefault();
					return;
				}
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
			alert(attentionIds);
			$("#attentionUserIds").val(attentionIds);
			$("#projectForm").submit();
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
			$("#attentionUser").append("<span class='label label-warning col-sm-1'" +
					" style='margin:7px 0 0px 5px;cursor:pointer;' id='" + attUserId + 
					"' onclick='this.remove();'>" + attUserName + "</span>");
		}

		function getUser(){ 
			var array = window.showModalDialog("<%=basePath %>/sUser!attentionUserList", "", 
					"dialogWidth:800px; dialogHeight:530px; status:no;help:no");
			//for chrome
		    if (array == undefined) {
		    	array = window.returnValue;
		    }
		    //checkEvent.preventDefault();
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

	</script>
</body>
</html>