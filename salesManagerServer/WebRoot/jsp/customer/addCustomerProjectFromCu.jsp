<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.thoughtworks.xstream.io.path.Path"%>
<%@taglib prefix="s" uri="/struts-tags"%>


<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	
	<title>新增销售项目</title>
	<link type="text/css" href="<%=basePath %>skin/Default/css/jquery-ui-1.10.0.custom.css" rel="stylesheet"  />
	
    <script type="text/javascript" language="javascript" src="<%=basePath %>script/common/common.js"></script>
    
<script type="text/javascript" >
	//添加日期输入控件
	$(document).ready(function() {
	    $( "#startTime" ).datepicker({
	    		dateFormat: "yy-mm-dd"
	    });
	    $( "#planEndTime" ).datepicker({
	    		dateFormat: "yy-mm-dd"
	    });
	    
	    $("#submit").click(function(checkEvent){
			if($('#customerProjectForm').form('validate')){
	    		$('#customerProjectForm').submit();
	    	}
		});
		
		$("#cancel").click(function(checkEvent){
	    	checkEvent.preventDefault();
	    	history.back();
	    });
	  });

	</script>
</head>
<!-- jQuery核心JS -->

<body class="ui-lv2bg">
	
 <!--======表格样式1======--> 
 <div class="ui-table ml15">
  <s:form id="customerProjectForm" name="customerProjectForm" 
  		action="customerProject!addCustomerProject" theme="simple" namespace="/" method="post" validate="false">
<table class="ui-table-one" cellspacing="2" cellpadding="0" >
<caption class="mt30">项目信息填写</caption>
	<input name="customerProject.customerId" id="customerId" type="hidden" value="${customerId }"/>
    <input name="customerName" type="hidden" readonly="readonly" value="${customerName }"/>
  <tr>
    <td class="ui-table-title">客户：</td>
    <td class="ui-table-input-r">    
    	<input name="customerProject.customerName" id="customerName" type="text" 
    		readonly="readonly" value="${customerName }" class="easyui-validatebox" required="true"/></td>
    <td class="ui-table-title">项目名称：</td>
    <td class="ui-table-input-r">
    	<input name="customerProject.name" id="name" type="text" class="easyui-validatebox" required="true"/></td>
  </tr>
  <tr>
    <td class="ui-table-title">开始日期：</td>
    <td class="ui-table-input-r">
    	<input name="customerProject.startTime" id="startTime" class="easyui-validatebox"
    		value="<s:property value="customerProject.startTime"/>" required="true"/>
    </td>
    <td class="ui-table-title">预计结束日期：</td>
    <td class="ui-table-input-r">
    	<input name="customerProject.planEndTime" id="planEndTime" type="text" class="easyui-validatebox" required="true"/>
     </td>
  </tr>
  <tr>
    <td class="ui-table-title">预计年销售金额：</td>
    <td class="ui-table-input-r">
    	<input name="customerProject.amount" id="amount" type="text" 
    		class='easyui-numberbox' precision='0' min='0' max='100000000'/></td>
    
	    <td class="ui-table-title">机会评估(销售员)：</td>
	    <td class="ui-table-input-r">
	    <s:if test="userPositionOrgId ==3">
	    <select name="customerProject.evaluationSaler" id="evaluationSaler" class="form-control">
	    <option value="">请选择机会评估</option>
	    <s:iterator id="enumeration" value="enumerationList" status="dl">
	    <option value="${enumeration.enumerationId }">${enumeration.enumerationName }</option>
	    </s:iterator>
	    </select>
	    </s:if>
	    <s:else>
        <input name="customerProject.evaluationSaler" id="evaluationSaler" type="hidden" readonly="readonly"/>
        <input name="customerProject.evaluationSalerName" id="evaluationSalerName" type="text" readonly="readonly"/>
        </s:else>
	    </td>
	    </td>
   </tr>
   <tr> 
	    <td class="ui-table-title">机会评估(经理)：</td>
	    <td class="ui-table-input-r">
	    <s:if test="userPositionOrgId ==4">
	    <select name="customerProject.evaluationManager" id="evaluationManager" class="form-control">
	    <option value="">请选择机会评估</option>
	    <s:iterator id="enumeration" value="enumerationList" status="dl">
	    <option value="${enumeration.enumerationId }">${enumeration.enumerationName }</option>
	    </s:iterator>
	    </select>
	    </s:if>
	    <s:else>
        <input name="customerProject.evaluationManager" id="evaluationManager" type="hidden" readonly="readonly"/>
        <input name="customerProject.evaluationManagerName" id="evaluationManagerName" type="text" readonly="readonly"/>
        </s:else>
	    </td>
	    <td class="ui-table-title">机会评估(总经理)：</td>
	    <td class="ui-table-input-r">
	    <s:if test="userPositionOrgId ==5">
	    <select name="customerProject.evaluationGen" id="evaluationGen" class="form-control">
	    <option value="">请选择机会评估</option>
	    <s:iterator id="enumeration" value="enumerationList" status="dl">
	    <option value="${enumeration.enumerationId }">${enumeration.enumerationName }</option>
	    </s:iterator>
	    </select>
	    </s:if> 
        <s:else>
        <input name="customerProject.evaluationGen" id="evaluationGenName" type="hidden" readonly="readonly"/>
        <input name="customerProject.evaluationGenName" id="evaluationGenName" type="text" readonly="readonly"/>
        </s:else>
	    </td>
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

<!--  按钮面板  -->
	<div class="crm-button-panel form-group ">
		<div class="col-sm-6">
			<a class="btn btn-primary col-sm-offset-9 col-sm-3" id="submit">确定</a>
		</div>
		<div class="col-sm-6">
			<a class="btn btn-warning col-sm-3" id="cancel">取消</a>
		</div>
	</div>
</s:form>
<div class="clear"></div>
</div>
</body>
</html>



