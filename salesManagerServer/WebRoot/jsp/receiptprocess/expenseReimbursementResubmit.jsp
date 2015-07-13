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
		<title>费用报销编辑</title>
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
			$("#typeSelection").change(function(){
				var type = $("#typeSelection").val();
				if (type == 1) {
					getApplicationList('<s:property value="#session.USER_INFO.userId" />');
				} else if (type == 2) {
					getTripApplicationList('<s:property value="#session.USER_INFO.userId" />');
				}
			});
			
		});
        
        function editEr () {
			if ($('#erForm').form('validate')) {
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
			var select = document.getElementById("appIdSelection");
		    var children = select.childNodes;
		    for (var i=0;i<children.length;i++) {
		        if (children[i].value != "" && children[i].value=="<s:property value="expenseApplicationId" />") {
		            select.options[i].selected=true;
		        }
		    }
		}
		
		function getTripApplicationList(userId){
			tripApplicationService.getTripApplicationListForReimbursement(userId,fillTripApplicationSelection);
		}

		function fillTripApplicationSelection(data){
			dwr.util.removeAllOptions("appIdSelection");
			dwr.util.addOptions("appIdSelection", [""]);
			dwr.util.addOptions("appIdSelection",data,"tripId","tripCode");	
			var select = document.getElementById("appIdSelection");
		    var children = select.childNodes;
		    for (var i=0;i<children.length;i++) {
		        if (children[i].value != "" && children[i].value=="<s:property value="tripId" />") {
		            select.options[i].selected=true;
		        }
		    }
		}

		</script>
	</head>
	<body class="ui-lv2bg">
	    <form class="form-horizontal" id="erForm" name="erForm" action="expense!reSubmitEr" method="post">
		  <div class="ui-table ml15">
			<table class="ui-table-one" cellspacing="2" cellpadding="0" >
			<input name="er.reimbursementId" type="hidden" value="${er.reimbursementId}"/>
			  <caption class="mt30">费用报销编辑</caption> 
			  <tr>
			    <td  class="ui-table-title">单据号：</td>
			    <td  colspan="3">
					${er.reimbursementCode}
				</td>
			  </tr>
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
			    <td class="ui-table-title">费用申请单单据号：</td>
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
					<input id="money" name="er.reimbursementSum" class="form-control easyui-validatebox"  required="true" validType="realNumber" value="${er.reimbursementSum}" onblur="numberUpper();"></input>
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
			   <tr>
			        <td class="ui-table-title">历史审批意见：</td>
					<td colspan="3">
			    <% 
			    SExpenseReimbursement er = (SExpenseReimbursement)request.getAttribute("er");
                ProcessEngine processEngine = Configuration.getProcessEngine();
            	TaskService taskService = processEngine.getTaskService();
            	ExecutionService executionService = processEngine.getExecutionService();
            	String processInstanceId = "expenseRei."+er.getReimbursementId();
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
					<a class="btn btn-success " href="javascript:editEr();" id="cancel">提交</a>
					<a class="btn btn-warning " href="javascript:history.back();" id="cancel">取消</a>
				</div>
			</div>
			
		  </div>
		</form>
	</body>

</html>