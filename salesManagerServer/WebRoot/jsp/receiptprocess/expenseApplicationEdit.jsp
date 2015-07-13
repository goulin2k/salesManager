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
		<title>费用申请新增</title>
		<link href="skin/Default/css/base.css" rel="stylesheet" type="text/css" />
		<link href="skin/Default/css/global.css" rel="stylesheet" type="text/css" />
		
		<script type="text/javascript" language="javascript" src="<%=basePath %>script/common/common.js"></script>
		<script type="text/javascript" src="<%=basePath %>script/common/easyui-1.3.5/plugins/jquery.parser.js"></script>
		<script type="text/javascript" src="<%=basePath %>script/common/easyui-1.3.5/jquery.easyui.min.js"></script>
		
		<link rel="stylesheet" type="text/css" href="<%=basePath %>script/common/easyui-1.3.5/themes/icon.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath %>script/common/easyui-1.3.5/themes/bootstrap/easyui.css" /> 
		<script type="text/javascript" src="script/common/easyui/validate.js"></script>
		<script type="text/javascript" src="script/common/easyui/locale/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript" src="script/common/number-chinese.js"></script>
		<script type="text/javascript">
        
        function editEa (status) {
			if ($('#eaForm').form('validate')) {
				$('#status').val(status);
				eaForm.submit();
			}
		}
        

		</script>
	</head>
	<body class="ui-lv2bg">
	    <form class="form-horizontal" id="eaForm" name="eaForm" action="expense!editEa" method="post">
		  <div class="ui-table ml15">
			<table class="ui-table-one" cellspacing="2" cellpadding="0" >
			  <caption class="mt30">费用申请信息</caption> 
			  <input name="ea.expenseApplicationId" type="hidden" value="${ea.expenseApplicationId}"/>
			  <input type="hidden" id="status" name="ea.status"/>
			  <tr>
			    <td  class="ui-table-title">借款金额：</td>
			    <td>
					<input id="money" name="ea.loanSum" class="form-control easyui-validatebox"  required="true" validType="realNumber" onblur="numberUpper();" value="${ea.loanSum}"></input>
				</td>
				<td  class="ui-table-title">金额大写：</td>
			    <td>
			          <input id="moneyup" name="ea.uppercase" type="text" class="form-control easyui-validatebox"  required="true" value="${ea.uppercase}" readonly/>
			    </td>
			  </tr>
			  
			  <tr>
			        <td class="ui-table-title">借款用途：</td>
					<td colspan="3">
						<textarea rows="6" name="ea.comment" class="form-control easyui-validatebox" validType="maxLength['借款用途',100]">${ea.comment}</textarea>
					</td>
			  </tr>
			   
			</table>
			<!--  按钮面板  -->
			<div class="crm-button-panel form-group ">
				<div class="col-sm-11 controls" align="center">
					<a class="btn btn-primary " href="javascript:editEa(2);" id="cancel">保存</a>
					<a class="btn btn-success " href="javascript:editEa(0);" id="cancel">提交</a>
					<a class="btn btn-warning " href="javascript:history.back();" id="cancel">取消</a>
				</div>
			</div>
			
		  </div>
		</form>
	</body>

</html>