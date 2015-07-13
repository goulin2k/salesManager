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
<title>新增销售活动</title>

	<script type="text/javascript" language="javascript" src="<%=basePath %>script/common/common.js"></script>
	<script type="text/javascript" src="<%=basePath %>script/common/easyui-1.3.5/plugins/jquery.parser.js"></script>
	<script type="text/javascript" src="<%=basePath %>script/common/easyui-1.3.5/jquery.easyui.min.js"></script>
	
	<link rel="stylesheet" type="text/css" href="<%=basePath %>script/common/easyui-1.3.5/themes/icon.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath %>script/common/easyui-1.3.5/themes/bootstrap/easyui.css" /> 
	<script type="text/javascript" src="script/common/easyui/validate.js"></script>
	<script type="text/javascript" src="script/common/easyui/locale/easyui-lang-zh_CN.js"></script>
	<script src='dwr/interface/customerProjectService.js'></script>
	<script src='dwr/engine.js'></script>
	<script src='dwr/util.js'></script>

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
		getCustomerProjectList(array[0]);
	}
	
	function getProject(){ 
		var array = window.showModalDialog("<%=basePath %>/project!openlist", "", 
				"dialogWidth:800px; dialogHeight:530px; status:no;help:no");
		//for chrome
	    if (array == undefined) {
	    	array = window.returnValue;
	    }
		$("#projectId").val(array[0]);
		$("#projectTopical").val(array[1]); 
		if(array[2] > 0){
			$("#customerName").removeAttr("disabled");
			$("#customerId").val(array[2]);
			$("#customerName").val(array[3]); 
			$("#customerName").attr("disabled","disabled");
			getCustomerProjectList(array[2]);
		}
	}
	 
	function cancelActivity(){ 
		history.back();
	}
			
	function getCustomerProjectList(customerId){
		customerProjectService.getCPListByCustomerId(customerId, fillCustomerProject);
	}
	
	function fillCustomerProject(data){
		dwr.util.removeAllOptions("customerProjectId");
		$("#customerProjectId").append("<option value='0'>无销售项目</option>");  //为Select追加一个Option(下拉项) 
	    dwr.util.addOptions("customerProjectId", data, "projectId", "name");		    
	}
	
	$(function(){
		$("#submit").click(function(checkEvent){
			if($("#activity").form('validate')) {
				$("#activity").submit();
			}
		});
		
		$("#cancel").click(function(checkEvent){
	    	checkEvent.preventDefault();
	    	cancelActivity();
	    });
		
		$("#activityDate" ).datepicker({
	 		dateFormat: "yy-mm-dd"
	 	});
		
		var customerId = $.trim($("#customerId").val());
		if(customerId > 0) {
			getCustomerProjectList(customerId);
		}
	});
	
	</script>
</head>
<body class="ui-lv2bg">
<div class="ui-content-title" align="center"><h2>销售活动信息</h2></div>	

<form id="activity" name="activity" action="<%=basePath %>activity!add" method="post">
<input type="hidden" name="activity.activityId" value="<s:property value="activity.activityId"/>"></input>
<div class="ui-table ml15">
<table class="ui-table-one" cellspacing="2" cellpadding="0" >
   
  <tr>
     <td class="ui-table-title">活动主题：</td>
     <td><input class="form-control easyui-validatebox" placeholder="活动概要描述" type="text" required="true"
     	name="activity.topical" value="<s:property value="activity.topical"/>"/></td> 
  </tr>
  <tr>
     <td class="ui-table-title">销售计划：</td>
     <td>
     	<input type="hidden" id="projectId" name="activity.projectId" value="<s:property value="activity.projectId"/>"/>
     	<input type="hidden" id="from" name="from" value="<s:property value="from"/>"/>
     	<input type="hidden" id="completionRate" name="activity.completionRate" value="0"/>
     	<s:if test="activity.activityId == null">
        	<input type="text" class="form-control" placeholder="选择计划" id="projectTopical" 
        		value="<s:property value="activity.projectTopical"/>" onClick="getProject();" /></td> 
        </s:if><s:else>
        	<s:property value="activity.projectTopical"/>
        </s:else>
        
  </tr>
   
  <tr>
     <td  class="ui-table-title">活动类型：</td>
     <td class="ui-table-select-s">
     	<s:select theme="simple" emptyOption="false" name="activity.enumerationId" placeholder="选择活动类型"
     		listKey="enumerationId" listValue="enumerationName" list="enumerationList" class="form-control" /></td>
  </tr>
  <tr>
     <td  class="ui-table-title">活动时间：</td>
     <td class="ui-table-select-s">
     	<input class="form-control" id="activityDate" name="activity.activityDate" placeholder="填写活动执行时间"
     		value="<s:date name="activity.activityDate" format="yyyy-MM-dd" />"  /></td> 
  </tr>
  <tr>
     <td  class="ui-table-title">对应客户：</td>
     <td class="ui-table-select-s">
     	<input type="hidden" id="customerId" name="activity.customerId"  value="<s:property value="activity.customerId"/>"/>
        <s:textfield cssClass="form-control" id="customerName" 
        	name="activity.customerName" onClick="getCustomer();" placeholder="选择活动客户"/>
     </td> 
  </tr>
  <tr>
    <td class="ui-table-title">客户项目：</td>
    <td class="ui-table-select-s">
    	<s:if test="activity.activityId == null">
			<select id="customerProjectId" 
					name="activity.customerProjectId" cssClass="form-control" placeholder="选择客户项目"><select>
		</s:if><s:else>
			<s:property value="activity.customerProjectName"/>
		</s:else>
	</td> 
  </tr> 
  <tr>
     <td  class="ui-table-title">拜访对象：</td>
     <td class="ui-table-select-s">
     	<input type="text" name="activity.visitPerson" class="form-control" placeholder="填写活动拜访的客户人员" 
     		type="text" value="<s:property value="activity.visitPerson"/>"/></td> 
  </tr>
  <tr>
     <td  class="ui-table-title">协同拜访人：</td>
     <td class="ui-table-select-s">
     	<input type="text" name="activity.coordinationVisitPerson" class="form-control" 
     		placeholder="填写参与活动的协同人员" type="text" value="<s:property value="activity.coordinationVisitPerson"/>"/></td> 
  </tr>
  
  <tr>
     <td  class="ui-table-title">活动内容：</td>
     <td colspan="3">
   		<textarea name="activity.comment" rows="6" class="form-control" 
   			placeholder="录入销售活动内容" required><s:property value="activity.comment"/></textarea>
     </td>
   </tr>  	
</table> 
</div>

<!--  按钮面板  -->
	<div class="crm-button-panel form-group ">
		<div class="col-sm-6">
			<a class="btn btn-primary col-sm-offset-9 col-sm-3" id="submit">确定</a>
		</div>
		<div class="col-sm-6">
			<a class="btn btn-warning col-sm-3" id="cancel">取消</a>
		</div>
	</div>
</form>
</body>
</html>