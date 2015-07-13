<%@ page language="java" import="java.util.*,org.jbpm.api.*,org.jbpm.api.task.*,com.sales.model.*,org.jbpm.api.history.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>车辆维修申请编辑</title>
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
        function editCa () {
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
	    <form class="form-horizontal" id="caForm" name="caForm" class="form-horizontal" action="car!reSubmitCa" method="post">
		  <div class="ui-table ml15">
			<table class="ui-table-one" cellspacing="2" cellpadding="0" >
			<input name="ca.carApplicationId" type="hidden" value="${ca.carApplicationId}"/>
			  <caption class="mt30">车辆维修申请编辑</caption>  
			  <tr>
			    <td  class="ui-table-title">单据号：</td>
			    <td colspan="3" class="ui-table-input-b">
						${ca.carApplicationCode}
				</td>
			  </tr>
			  <tr>
			    <td  class="ui-table-title">车牌号：</td>
			    <td>
					<input name="ca.plateNumber" placeholder="车牌号" class="form-control easyui-validatebox"  required="true" value="${ca.plateNumber}"></input>
				</td>
				<td  class="ui-table-title">预计维修日期：</td>
			    <td>
			        <input type="text" id="repairTimeId" name="ca.repairTime" value="<s:date name="ca.repairTime" format="yyyy-MM-dd" />" class="form-control"  placeholder="预计维修日期" />
			    </td>
			  </tr>
			  <tr>
			    <td  class="ui-table-title">维修费用：</td>
			    <td>
					<input id="money"  name="ca.repairSum" placeholder="维修费用" class="form-control easyui-validatebox"  required="true" validType="realNumber" value="${ca.repairSum}" onblur="numberUpper();"></input>
				</td>
				<td  class="ui-table-title">金额大写：</td>
			    <td>
			          <input id="moneyup" name="ca.uppercase" placeholder="金额大写" type="text" class="form-control easyui-validatebox"  required="true" value="${ca.uppercase}" readonly/>
			    </td>
			  </tr>
			  <tr>
			    <td  class="ui-table-title">是否报保险：</td>
			    <td colspan="3" class="color555">
			    
			          <input type="radio" name="ca.isInsurance" value="1" <s:if test="ca.isInsurance == 1">checked</s:if>/> 是
			          <input type="radio" name="ca.isInsurance" value="0" <s:if test="ca.isInsurance == 0">checked</s:if>/> 否
                
			    </td>
			  </tr>
			  
			  <tr>
			        <td class="ui-table-title">维修事由：</td>
					<td colspan="3">
						<textarea rows="6" name="ca.repairComment" class="form-control easyui-validatebox" placeholder="输入维修事由" validType="maxLength['维修事由',100]">${ca.repairComment}</textarea>
					</td>
			  </tr>
			    <tr>
			        <td class="ui-table-title">历史审批意见：</td>
					<td colspan="3">
			    <% 
			    SCarRepairApplication ca = (SCarRepairApplication)request.getAttribute("ca");
                ProcessEngine processEngine = Configuration.getProcessEngine();
            	TaskService taskService = processEngine.getTaskService();
            	ExecutionService executionService = processEngine.getExecutionService();
            	String processInstanceId = "carRepairApp."+ca.getCarApplicationId();
            	List<HistoryTask> list = processEngine.getHistoryService().createHistoryTaskQuery().list();
            	for(HistoryTask ht: list) {
                    String insId = ht.getExecutionId();
                    //只取此流程实例的审批信息
                    if(insId.equals(processInstanceId)) {
                    	String assignee = ht.getAssignee();
                        String historyTaskId = ht.getId();
                        List<HistoryComment> hList = taskService.getTaskComments(historyTaskId);
                        if(null != hList && hList.size() > 0) {
                            System.out.println(assignee+";"+hList.get(0).getMessage());
                %>
                        <%=hList.get(0).getMessage() %><br>
                <%
                        }
                    }
                }
  			  %>
               </td>
			  </tr>
			</table>
			<!--  按钮面板  -->
			<div class="crm-button-panel form-group ">
				<div class="col-sm-11 controls" align="center">
					<a class="btn btn-success " href="javascript:editCa();" id="cancel">提交</a>
					<a class="btn btn-warning " href="javascript:history.back();" id="cancel">取消</a>
				</div>
			</div>
			
		  </div>
		</form>
	</body>

</html>