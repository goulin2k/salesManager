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
     	<title>车辆维修申请新增</title>
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
        
        function editCa(status) {
			$('#status').val(status);
			if ($('#caForm').form('validate')) {
				caForm.submit();
			}
		}
        $(function() {
			
			$( "#repairTimeId" ).datepicker({
	    		dateFormat: "yy-mm-dd"
	        });  
		});
 
		</script>
	</head>
	<body class="ui-lv2bg">
	    <form id="caForm" class="form-horizontal" name="caForm" action="car!editCa" method="post">
		  <div class="ui-table ml15">
			<table class="ui-table-one" cellspacing="2" cellpadding="0" >
			  <caption class="mt30">车辆维修申请信息</caption>  
			  <input name="ca.carApplicationId" type="hidden" value="${ca.carApplicationId}"/>
			  <input type="hidden" id="status" name="ca.status"/>
			  <tr>
			    <td  class="ui-table-title">车牌号：</td>
			    <td>
			        <input class="form-control easyui-validatebox" placeholder="车牌号" name="ca.plateNumber" value="${ca.plateNumber}" validType="maxLength['车牌号',10]"  required="true"/>
				</td>
				<td  class="ui-table-title">预计维修日期：</td>
			    <td>
			          <input type="text" id="repairTimeId" name="ca.repairTime" value="<s:date name="ca.repairTime" format="yyyy-MM-dd" />" class="form-control"  placeholder="预计维修日期" />
			          
			    </td>
			  </tr>
			  <tr>
			    <td  class="ui-table-title">维修费用：</td>
			    <td>
			        <input id="money" class="form-control easyui-validatebox" placeholder="维修费用" name="ca.repairSum" value="${ca.repairSum}" onblur="numberUpper();" validType="realNumber"  required/>
					
				</td>
				<td  class="ui-table-title">金额大写：</td>
			    <td>
			          <input id="moneyup" class="form-control" placeholder="金额大写" name="ca.uppercase" value="${ca.uppercase}"  readonly/>
			    </td>
			  </tr>
			  <tr>
			    <td  class="ui-table-title">是否报保险：</td>
			    <td colspan="3">
			          <input type="radio" name="ca.isInsurance" value="1" checked/> 是
			          <input type="radio" name="ca.isInsurance" value="0" <s:if test="ca.isInsurance == 0">checked</s:if>/> 否
			    </td>
			  </tr>
			  
			  <tr>
			        <td class="ui-table-title">维修事由：</td>
					<td colspan="3">
					    <textarea name="ca.repairComment" rows="4" class="form-control easyui-validatebox" validType="maxLength['维修事由',100]" placeholder="输入维修事由">${ca.repairComment}</textarea>
						
					</td>
			  </tr>
			   
			</table>
			<!--  按钮面板  -->
			<div class="crm-button-panel form-group ">
				<div class="col-sm-11 controls" align="center">
					<a class="btn btn-primary " href="javascript:editCa(2);" id="cancel">保存</a>
					<a class="btn btn-success " href="javascript:editCa(0);" id="cancel">提交</a>
					<a class="btn btn-warning " href="javascript:history.back();" id="cancel">取消</a>
				</div>
			</div>
			
		  </div>
		</form>
	</body>

</html>