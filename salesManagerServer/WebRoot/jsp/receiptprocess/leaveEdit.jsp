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
	<title>请假单新增</title>
	<link href="skin/Default/css/base.css" rel="stylesheet" type="text/css" />
	<link href="skin/Default/css/global.css" rel="stylesheet" type="text/css" />
	
	<script type="text/javascript" language="javascript" src="<%=basePath %>script/common/common.js"></script>
	<script type="text/javascript" src="<%=basePath %>script/common/easyui-1.3.5/plugins/jquery.parser.js"></script>
	<script type="text/javascript" src="<%=basePath %>script/common/easyui-1.3.5/jquery.easyui.min.js"></script>
	
	<link rel="stylesheet" type="text/css" href="<%=basePath %>script/common/easyui-1.3.5/themes/icon.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath %>script/common/easyui-1.3.5/themes/bootstrap/easyui.css" /> 
	<script type="text/javascript" src="script/common/easyui/validate.js"></script>
	<script type="text/javascript" src="script/common/easyui/locale/easyui-lang-zh_CN.js"></script>
	
	<script type="text/javascript">
       	$(function() {
			$( "#startTimeId" ).datepicker({
	    		dateFormat: "yy-mm-dd"
	        });  
			$( "#endTimeId" ).datepicker({
	    		dateFormat: "yy-mm-dd"
	        });  
		});
       
       function editLeave (status) {
			if ($('#leaveForm').form('validate')) {
				$('#status').val(status);
				//alert(!dateCompare($('#startTimeId').val(), $('#endTimeId').val()));
				if (!dateCompare($('#startTimeId').val(), $('#endTimeId').val())) {
					$.messager.alert('提示','休假起止日期填报错误。','info');
				    return;
				} 
				var type = $('#typeId').val();
				//alert(type);
				if (type == 7) {
					//$.post("leave!leaveValidate", $("#leaveForm").serializeArray(), function(data) {
						//alert(data.jsonReturn);
						if (parseInt($("#daysId").val()) <= parseInt($("#overtimeHours").val())) {
							leaveForm.submit();
						}else {
							$.messager.alert('提示','当前剩余换休时间为'+$("#overtimeHours").val()+'小时, 已经小于所填调休小时数。','info');
							return;
						}
					//});
				} 
				else if (type == 3) {
					//$.post("leave!annualValidate", $("#leaveForm").serializeArray(), function(data) {
						//alert(data.jsonReturn);
						if (parseInt($("#daysId").val()) <= parseInt($("#annualHours").val())) {
							leaveForm.submit();
						}else {
							$.messager.alert('提示','当前剩余年假时间为'+$("#annualHours").val()+'小时, 已经小于所填请假小时数。','info');
							return;
						}
					//s});
				}
				else {
					//$('#leaveForm').form('submit'); 
					leaveForm.submit();
				}
			}
		}
       
       	function dateCompare(a, b) {
		    var arr = a.split("-");
		    var starttime = new Date(arr[0], arr[1], arr[2]);
		    var starttimes = starttime.getTime();
		
		    var arrs = b.split("-");
		    var lktime = new Date(arrs[0], arrs[1], arrs[2]);
		    var lktimes = lktime.getTime();
		    //alert(starttimes+":"+lktimes);
		    if (starttimes > lktimes) {
		        return false;
		    }
		    else
		        return true;
		
		}
	</script>
</head>
<body class="ui-lv2bg">
    <form class="form-horizontal" id="leaveForm" name="leaveForm" action="leave!editLeave" method="post">
    	<input name="leave.leaveId" type="hidden" value="${leave.leaveId}"/>
	  	<input type="hidden" id="status" name="leave.status"/>
	  	<input type="hidden" id="annualHours" value="${annualHours}"/>
	  	<input type="hidden" id="overtimeHours" value="${overtimeHours}"/>
		<table class="ui-table-one" cellspacing="2" cellpadding="0" >
			<caption class="mt30">请假单信息</caption>  
		  	<tr>
			  	<td colspan="4">提示：你的年假剩余<font color="red" bold="true">${annualHours}</font>小时, 调休剩余
			  		<font color="red" bold="true">${overtimeHours}</font>小时。</td>
		  	</tr>
		  	<tr>
			    <td  class="ui-table-title">休假类型：</td>
			    <td>
					<s:select
	                        id="typeId"
	                        tooltip="休假类型"
	                        name="leave.typeId"
	                        emptyOption="false"
	                        list="#{'1':'事假','2':'病假','3':'年假','4':'婚假','5':'产假','6':'丧假','7':'调休' }"
	                        cssClass="form-control "/>
				</td>
				<td  class="ui-table-title">休假小时：</td>
			    <td  >
			          <input id="daysId" name="leave.leaveDays" type="text" placeholder="休假小时" 
			          	class="easyui-validatebox form-control" validType="realNumber" required="true" value="${leave.leaveDays}"/>
			    </td>
		  	</tr>
		  	<tr>
		        <td  class="ui-table-title">开始日期：</td>
                   <td >
                       <input id="startTimeId" name="leave.startTime" value="<s:date name="leave.startTime" format="yyyy-MM-dd" />" 
                       	 class="easyui-validatebox form-control" placeholder="开始日期" required="true"/>
                   </td>
                   <td class="ui-table-title">结束日期：</td>
			    <td >
			        <input id="endTimeId" name="leave.endTime" value="<s:date name="leave.endTime" format="yyyy-MM-dd" />"  
			        class="easyui-validatebox form-control" placeholder="结束日期" required="true"/>
			    </td>
		    
		  	</tr>
		  
		  	<tr>
		        <td class="ui-table-title">休假原因：</td>
				<td colspan="3">
					<textarea rows="6" name="leave.comment" placeholder="休假原因" 
						class="form-control easyui-validatebox"  validType="maxLength['休假原因',100]">${leave.comment}</textarea>
				</td>
		  	</tr>
		   
		</table>
		<!--  按钮面板  -->
		<div class="crm-button-panel form-group ">
			<div class="col-sm-11 controls" align="center">
				<a class="btn btn-primary " href="javascript:editLeave(2);" id="save">保存</a>
				<a class="btn btn-success " href="javascript:editLeave(0);" id="submit">提交</a>
				<a class="btn btn-warning " href="javascript:history.back();" id="cancel">取消</a>
			</div>
		</div>
	</form>
</body>

</html>