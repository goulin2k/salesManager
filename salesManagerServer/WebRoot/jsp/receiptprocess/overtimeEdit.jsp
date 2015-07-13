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
		<title>加班新增</title>
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
		});
        
        function editOt (status) {
			if ($('#otForm').form('validate')) {
				$('#status').val(status);
				otForm.submit();
			}
		}
		</script>
	</head>
	<body class="ui-lv2bg">
	    <form class="form-horizontal" id="otForm" name="otForm" action="overtime!editOt" method="post">
		  <div class="ui-table ml15">
			<table class="ui-table-one" cellspacing="2" cellpadding="0" >
			  <caption class="mt30">加班信息</caption> 
			  <input name="ot.overtimeId" type="hidden" value="${ot.overtimeId}"/>
			  <input type="hidden" id="status" name="ot.status"/>
			  <tr>
			    <td  class="ui-table-title">加班日期：</td>
                    <td>
                    <input id="startTimeId" name="ot.startTime" value="<s:date name="ot.startTime" format="yyyy-MM-dd" />" 
                       	 class="easyui-validatebox form-control" placeholder="加班日期" required="true"/>
                    
                    </td>
				<td  class="ui-table-title">加班小时数：</td>
			    <td>
			          <input name="ot.hours" type="text" class="form-control easyui-validatebox"  required="true" validType="realNumber" value="${ot.hours}"/>
			    </td>
			  </tr>
			  
			  <tr>
			        <td class="ui-table-title">加班说明：</td>
					<td colspan="3">
						<textarea rows="6" name="ot.comment" class="form-control easyui-validatebox" validType="maxLength['加班说明',100]">${ot.comment}</textarea>
					</td>
			  </tr>
			   
			</table>
			<!--  按钮面板  -->
			<div class="crm-button-panel form-group ">
				<div class="col-sm-11 controls" align="center">
					<a class="btn btn-primary " href="javascript:editOt(2);" id="cancel">保存</a>
					<a class="btn btn-success " href="javascript:editOt(0);" id="cancel">提交</a>
					<a class="btn btn-warning " href="javascript:history.back();" id="cancel">取消</a>
				</div>
			</div>
			
		  </div>
		</form>
	</body>

</html>