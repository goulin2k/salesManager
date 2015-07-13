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
	<title>客户财务评估</title>
	
	<script type="text/javascript" >
		function check()
		{
			var customerId = $.trim($("#customerId").val());
			if(customerId =="")
			{
				alert("客户不能为空！");
				$("#customerId").focus();
				return false;
			}
			$("#customerFinEvaluationForm").submit();
		}
	
	</script>
</head>
<!-- jQuery核心JS -->

<body class="ui-lv2bg">

 <!--======表格样式1======--> 
 <div class="ui-table ml15">
  <s:form id="customerFinEvaluationForm" name="customerFinEvaluationForm" action="customer!updateCustomerFinEvaluation" theme="simple" namespace="/" method="post" validate="false">
<table class="ui-table-one" cellspacing="2" cellpadding="0" >
<caption class="mt30"><img src="skin/Default/images/ui-content-ico/add.png"/ class="mr8">修改客户财务评价</caption>
  <tr>
    <td class="ui-table-title">客户：</td>
    <td class="ui-table-input-r">
    <input name="customerFinEvaluation.customerId" id="customerId" type="hidden" value ="${customerId }"/>
    <input name="customerName" id="customerName" type="text" readonly="readonly"/ value ="${customerName }"></td>
    <td class="ui-table-title">销售经理评价：</td>
    <td class="ui-table-input-r">
	    <s:if test="userPositionOrgId==4 || userPositionOrgId == 3|| userPositionOrgId == 2">
		    <select name="customerFinEvaluation.evaSalemanager" id="evaSalemanager" class="form-control">
			    <option value="">请选择财务评价</option>
			    <s:iterator id="evamanager" value="evaSalemanagerList" status="dl">
			    	<option value="${evamanager.enumerationId }" ${evamanager.isSelect }>${evamanager.enumerationName }</option>
			    </s:iterator>
		    </select>
	    </s:if>
	    <s:else>
	    <input name="customerFinEvaluation.evaSalemanager" id="evaSalemanager" type="hidden" readonly="readonly" value ="${customerFinEvaluation.evaSalemanager }"/>
	    <input name="customerFinEvaluation.evaSalemanagerName" id="evaSalemanagerName" 
	    	type="text" readonly="readonly" value ="${customerFinEvaluation.evaSalemanagerName }" />
	    </s:else> 
    </td>
  </tr>
   <tr>
    <td class="ui-table-title">总经理评价：</td>
    <td class="ui-table-input-r">
    <s:if test="userPositionOrgId == 3|| userPositionOrgId == 2">
    <select name="customerFinEvaluation.evaSalegen" id="evaSalegen" class="form-control">
    <option value="">请选择财务评价</option>
    <s:iterator id="evagen" value="evaSalegenList" status="dl">
    <option value="${evagen.enumerationId }" ${evagen.isSelect }>${evagen.enumerationName }</option>
    </s:iterator>
    </select>
    </s:if>
    <s:else>
<input name="customerFinEvaluation.evaSalegen" id="evaSalegen" type="hidden" readonly="readonly" value ="${customerFinEvaluation.evaSalegen }"/>
<input name="customerFinEvaluation.evaSalegenName" id="evaSalegenName" 
	type="text" readonly="readonly" value ="${customerFinEvaluation.evaSalegenName }" />
</s:else> 
    
    </td>
    <td class="ui-table-title">客户信用评价：</td>
    <td class="ui-table-input-r">
    <s:if test="userPositionOrgId==8 || userPositionOrgId == 2">
		<select name="customerFinEvaluation.evaFinmanager" id="evaFinmanager" class="form-control">
	    <option value="">请选择信用评价</option>
	    <s:iterator id="evaCredit" value="evaCreditList" status="dl">
	    <option value="${evaCredit.enumerationId }" ${evaCredit.isSelect }>${evaCredit.enumerationName }</option>
	    </s:iterator>
	    </select>
	</s:if>
	<s:else>
		<input name="customerFinEvaluation.evaFinmanager" id="evaFinmanager" type="hidden" readonly="readonly" value ="${customerFinEvaluation.evaFinmanager }"/>
		<input name="customerFinEvaluation.evaFinmanagerName" id="evaFinmanagerName" type="text" 
			readonly="readonly" value ="${customerFinEvaluation.evaFinmanagerName }" />
	</s:else> 
    
    </td>
  </tr>

</table>

<!--  按钮面板  -->
	<div class="crm-button-panel form-group ">
		<div class="col-sm-6">
			<button class="btn btn-primary col-sm-offset-9 col-sm-3" onclick="check();">确定</button>
		</div>
		<div class="col-sm-6">
			<button class="btn btn-warning col-sm-3" onclick="Javascript:history.back();">取消</button>
		</div>
	</div>
</s:form>
</div>
</body>
</html>



