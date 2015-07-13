<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>费用报销新增</title>
		<link href="skin/Default/css/base.css" rel="stylesheet" type="text/css" />
		<link href="skin/Default/css/global.css" rel="stylesheet" type="text/css" />
		
		<script type="text/javascript" language="javascript" src="<%=basePath %>script/common/common.js"></script>
		<script type="text/javascript" src="<%=basePath %>script/common/easyui-1.3.5/plugins/jquery.parser.js"></script>
		<script type="text/javascript" src="<%=basePath %>script/common/easyui-1.3.5/jquery.easyui.min.js"></script>
		
		<link rel="stylesheet" type="text/css" href="<%=basePath %>script/common/easyui-1.3.5/themes/icon.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath %>script/common/easyui-1.3.5/themes/bootstrap/easyui.css" /> 
		<script type="text/javascript" src="script/common/easyui/validate.js"></script>
		<script type="text/javascript" src="script/common/easyui/locale/easyui-lang-zh_CN.js"></script>
		<script src='dwr/interface/expenseApplicationService.js'></script>
		<script src='dwr/interface/tripApplicationService.js'></script>
		<script src='dwr/engine.js'></script>
		<script src='dwr/util.js'></script>
		<script type="text/javascript" src="script/common/number-chinese.js"></script>
		<script type="text/javascript">
        $(function() {
			//getApplicationList('<s:property value="#session.USER_INFO.userId" />');
			$("#typeSelection").change(function(){
				var type = $("#typeSelection").val();
				if (type == 1) {
					getApplicationList('<s:property value="#session.USER_INFO.userId" />');
				} else if (type == 2) {
					getTripApplicationList('<s:property value="#session.USER_INFO.userId" />');
				}
			});
		});
        
        function editEr (status) {
			if ($('#erForm').form('validate')) {
				$('#status').val(status);
				erForm.submit();
				
			}
		}
        
        function getApplicationList(userId){
			expenseApplicationService.getExpenseApplicationListForReimbursement(userId,fillApplicationSelection);
		}

		function fillApplicationSelection(data){
			dwr.util.removeAllOptions("appIdSelection");
			dwr.util.addOptions("appIdSelection", [""]);
			dwr.util.addOptions("appIdSelection",data,"expenseApplicationId","expenseApplicationCode");		    
		}
		
		function getTripApplicationList(userId){
			tripApplicationService.getTripApplicationListForReimbursement(userId,fillTripApplicationSelection);
		}

		function fillTripApplicationSelection(data){
			dwr.util.removeAllOptions("appIdSelection");
			dwr.util.addOptions("appIdSelection", [""]);
			dwr.util.addOptions("appIdSelection",data,"tripId","tripCode");		    
		}

		</script>
	</head>
	<body class="ui-lv2bg">
	    <form class="form-horizontal" id="erForm" name="erForm" action="expense!editEr" method="post">
		  <div class="ui-table ml15">
			<table class="ui-table-one" cellspacing="2" cellpadding="0" >
			  <caption class="mt30">费用报销信息</caption> 
			  <input name="er.reimbursementId" type="hidden" value="${er.reimbursementId}"/>
			  <input type="hidden" id="status" name="er.status"/>
			  <tr>
			    <td  class="ui-table-title">报销类型：</td>
			    <td>
			         <s:select
	                        id="typeSelection"
	                        tooltip="报销类型"
	                        name="er.type"
	                        emptyOption="false"
	                        list="#{'1':'普通费用报销','2':'出差费用报销' }"
	                        cssClass="form-control "/>
			          
			    </td>
			    <td class="ui-table-title">费用申请单据号：</td>
			    <td>
			        <s:if test="er.type == 2">
			           <s:select
	                        id="appIdSelection"
	                        tooltip="费用申请单据号"
	                        name="er.expenseApplicationId"
	                        emptyOption="false"
	                        headerKey="" headerValue="" 
	                        listKey="tripId" listValue="tripCode" list="tripList" 
	                        cssClass="form-control"/>
	                </s:if>
	                <s:else>
	                    <s:select
	                        id="appIdSelection"
	                        tooltip="费用申请单据号"
	                        name="er.expenseApplicationId"
	                        emptyOption="false"
	                        headerKey="" headerValue="" 
	                        listKey="expenseApplicationId" listValue="expenseApplicationCode" list="eaList" 
	                        cssClass="form-control"/>
	                </s:else>
			    </td>
			  </tr>
			  <tr>
			    <td  class="ui-table-title">报销金额：</td>
			    <td>
					<input id="money" name="er.reimbursementSum" class="form-control easyui-validatebox"  required="true" validType="realNumber" onblur="numberUpper();" value="${er.reimbursementSum}"></input>
				</td>
				<td  class="ui-table-title">金额大写：</td>
			    <td>
			          <input id="moneyup" name="er.uppercase" type="text" class="form-control easyui-validatebox"  required="true" value="${er.uppercase}" readonly/>
			    </td>
			  </tr>
			  
			  <tr>
			        <td class="ui-table-title">报销摘要：</td>
					<td colspan="3">
						<textarea rows="6" name="er.reimbursementComment" class="form-control easyui-validatebox" validType="maxLength['报销摘要',100]">${er.reimbursementComment}</textarea>
					</td>
			  </tr>
			   
			</table>
			
			<!--  按钮面板  -->
			<div class="crm-button-panel form-group ">
				<div class="col-sm-11 controls" align="center">
					<a class="btn btn-primary " href="javascript:editEr(2);" id="cancel">保存</a>
					<a class="btn btn-success " href="javascript:editEr(0);" id="cancel">提交</a>
					<a class="btn btn-warning " href="javascript:history.back();" id="cancel">取消</a>
				</div>
			</div>
			
		  </div>
		</form>
	</body>

</html>