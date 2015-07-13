<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.thoughtworks.xstream.io.path.Path"%>
<%@taglib prefix="s" uri="/struts-tags"%>


<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增客户项目</title>
<script type="text/javascript" language="javascript" src="<%=basePath %>script/common/common.js"></script>
<script type="text/javascript" >
	$(document).ready(function(){  
	    $("#submit").click(function(checkEvent){
	    	if($('#customerProjectForm').form('validate')){
				$("#customerProjectForm").submit();
	    	}
		});
	
	    $("#cancel").click(function(checkEvent){
	    	checkEvent.preventDefault();
	    	history.back();
	    });
	    
	    $( "#startTime" ).datepicker({
    		dateFormat: "yy-mm-dd"
	    });
	    
	    $( "#planEndTime" ).datepicker({
	    		dateFormat: "yy-mm-dd"
	    });
	});
	
	function getCustomer(){ 
		var array = window.showModalDialog("<%=basePath %>/customer!customerOpenWindow", "", "dialogWidth:800px; dialogHeight:600px; status:no;help:no");
		//for chrome
	    if (array == undefined) {
	    	array = window.returnValue;
	    }
		$("#customerId").val(array[0]);
		$("#customerName").val(array[1]);
		var id = $.trim($("#customerId").val());
		if(id != null && id != "" ){
			getLinkman(id);
		}
	}

	function getLinkman(customerId){
		$.ajax({
		    type: "POST",
			url: "customerProject!jsonLinkmans",
			data:"customerId="+customerId,
			dataType:"json",
			success: function(data){ 
				var content = "<select name='customerProject.linkmanId' id='linkmanId'><option value=''>请选择负责人</option>";
				var contentOp = "";
				$.each(data, function(j, value) {
			       	    contentOp += "<option value='"+value.linkmanId+"'>"+value.name+"</option>";
			        });
			        contentOp+= "</select>"
			        content += contentOp;
			        $("#selectTxt").html(content);
		    }
		});
	}
	</script>
</head>
<!-- jQuery核心JS -->

<body class="ui-lv2bg">
       

 <!--======表格样式1======--> 
 <div class="ui-table ml15">

  	<s:form id="customerProjectForm" name="customerProjectForm" action="customerProject!addCustomerProject" theme="simple" namespace="/" method="post" validate="false">
	<table class="ui-table-one" cellspacing="2" cellpadding="0" >
		<caption class="mt30">新增客户项目</caption>
		  <tr>
		    <td class="ui-table-title">客户：</td>
		    <td class="ui-table-input-r">
			    <input name="customerProject.customerId" id="customerId" type="hidden" value="${customerProject.customerId }"/>
			    <input name="customerProject.customerName" id="customerName" 
			    	type="text"  value="${customerProject.customerName }"
			    	<s:if test="customerProject.customerId == null">onClick="getCustomer();"</s:if>
			    	 readonly="readonly" class="easyui-validatebox" required="true"/></td>
		    <td class="ui-table-title">项目名称：</td>
		    <td class="ui-table-input-r">
		    	<input name="customerProject.name" id="name" type="text" value="${customerProject.name }" 
		    		class="easyui-validatebox" required="true" required/></td>
		  </tr>
		  <tr>
		    <td class="ui-table-title">开始日期：</td>
		    <td class="ui-table-input-r">
		    	<input name="customerProject.startTime" id="startTime" type="text" 
		    		value="<s:property value="customerProject.startTime"/>" class="easyui-validatebox" required="true"/></td>
		    <td class="ui-table-title">预计结束日期：</td>
		    <td class="ui-table-input-r">
		    	<input name="customerProject.planEndTime" id="planEndTime" type="text" class="easyui-validatebox" required="true"/></td>
		  </tr>
		  <tr>
		    <td class="ui-table-title">预计年销售金额：</td>
		    <td class="ui-table-input-r">
		    	<input name="customerProject.amount" id="amount" type="text" 
		    		class='easyui-numberbox' precision='0' min='0' max='100000000' required="true"/></td>
		    <s:if test="userPositionOrgId ==3">
			    <td class="ui-table-title">机会评估：</td>
			    <td class="ui-table-input-r">
				    <select name="customerProject.evaluationSaler" id="evaluationSaler" class="form-control">
					    <option value="">请选择机会评估</option>
					    <s:iterator id="enumeration" value="enumerationList" status="dl">
					    <option value="${enumeration.enumerationId }">${enumeration.enumerationName }</option>
					    </s:iterator>
				    </select>
			    </td>
		    </s:if>
		    <s:if test="userPositionOrgId ==4">
			    <td class="ui-table-title">机会评估：</td>
			    <td class="ui-table-input-r">
				    <select name="customerProject.evaluationManager" id="evaluationManager" class="form-control">
					    <option value="">请选择机会评估</option>
					    <s:iterator id="enumeration" value="enumerationList" status="dl">
					    <option value="${enumeration.enumerationId }">${enumeration.enumerationName }</option>
					    </s:iterator>
				    </select>
			    </td>
		    </s:if>
		    <s:if test="userPositionOrgId ==5">
			    <td class="ui-table-title">机会评估：</td>
			    <td class="ui-table-input-r">
				    <select name="customerProject.evaluationGen" id="evaluationGen" class="form-control">
					    <option value="">请选择机会评估</option>
					    <s:iterator id="enumeration" value="enumerationList" status="dl">
					    <option value="${enumeration.enumerationId }">${enumeration.enumerationName }</option>
					    </s:iterator>
				    </select>
			    </td>
		    </s:if>  
		  </tr>
		  <tr>
		    <td class="ui-table-title">使用部位：</td>
		    <td class="ui-table-input-r" colspan="3">
		    	<input name="customerProject.usePartion" id="usePartion" type="text" class="easyui-validatebox"/></td>
		  </tr><tr>
		    <td class="ui-table-title">使用目的：</td>
		    <td class="ui-table-input-r" colspan="3">
		    	<input name="customerProject.useAttention" id="useAttention" type="text" class="easyui-validatebox"/></td>
		  </tr>
		  <tr>
		    <td class="ui-table-title">使用现状：</td>
		    <td class="ui-table-input-r" colspan="3">
		    	<input name="customerProject.usingStatus" id="usingStatus" type="text" class="easyui-validatebox"/></td>
		  </tr><tr>
		    <td class="ui-table-title">现有问题：</td>
		    <td class="ui-table-input-r" colspan="3">
		    	<input name="customerProject.problem" id="problem" type="text" class="easyui-validatebox"/></td>
		  </tr>
		  <tr>
		    <td class="ui-table-title">解决办法：</td>
		    <td class="ui-table-input-r" colspan="3">
		    	<input name="customerProject.solution" id="solution" type="text" class="easyui-validatebox"/></td>
		  </tr>
		  <tr>
		    <td class="ui-table-title">客户负责人</td>
		    <td class="ui-table-input-r">
			    <select name="customerProject.linkmanId" id="linkmanId" class="form-control">
			    <option value="">请选择负责人</option>
			    <s:iterator id="customerLinkman" value="customerLinkmanList" status="dl">
			    <option value="${customerLinkman.linkmanId }">${customerLinkman.name }</option>
			    </s:iterator>
			    </select>
		    </td>
		  </tr>
	</table>
	
	<div class="crm-button-panel form-group ">
		<div class="col-sm-6">
			<a class="btn btn-primary col-sm-offset-9 col-sm-3" id="submit">确定</a>
		</div>
		<div class="col-sm-6">
			<a class="btn btn-warning col-sm-3" id="cancel">取消</a>
		</div>
	</div>
	</s:form>
</div>
</body>
</html>



