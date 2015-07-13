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
		<title>参数编辑</title>
		<script type="text/javascript" src="script/common/easyui/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="script/common/easyui/validate.js"></script>
		<script type="text/javascript" src="script/common/easyui/locale/easyui-lang-zh_CN.js"></script>
		<link rel="stylesheet" type="text/css" href="script/common/easyui/themes/icon.css" />
		<link rel="stylesheet" type="text/css" href="script/common/easyui/themes/default/easyui.css" />
		<script type="text/javascript">
		function editParam () {
			if ($('#paramForm').form('validate')) {
				paramForm.submit();
			}
		}
		</script>
	</head>
	<body class="ui-lv2bg">
	    <div class="ui-content-title"><p><img src="skin/Default/images/ui-content-ico/config.png" />参数编辑</div>
	    <form id="paramForm" name="paramForm" action="parameter!edit" method="post">
		<div class="ui-table ml15">
			<table class="ui-table-one" cellspacing="2" cellpadding="0">
            	<caption><img src="skin/Default/images/ui-content-ico/config-edit.png" />参数</caption>
				<tr>
					<td class="ui-table-title">
						<img src="skin/Default/images/ui-table-note.png" />
						参数名称：
					</td>
					<td colspan="3" class="ui-table-input-b">
					    <input name="parameter.parameterId" type="hidden" value="${parameter.parameterId}"/>
						<input name="parameter.name" type="text" value="${parameter.name}" class="easyui-validatebox" required="true" validType="maxLength['参数名称',15]"/>
					</td>
				</tr>

				<tr>
					<td class="ui-table-title">
						<img src="skin/Default/images/ui-table-note.png" />
						参数值：
					</td>
					<td colspan="3" class="ui-table-input-b">
						<input name="parameter.value" type="text"  value="${parameter.value}" class="easyui-validatebox" required="true" validType="maxLength['参数值',15]"/>
					</td>
				</tr>

				<tr>
					<td class="ui-table-title">
						参数说明：
					</td>
					<td colspan="3" class="ui-table-textarea">
						<textarea rows="6" name="parameter.comment" class="easyui-validatebox" validType="maxLength['参数说明',100]">${parameter.comment}</textarea>
					</td>
				</tr>
			</table>
			 <div class="color-red">标注：<img src="skin/Default/images/ui-table-note.png" />该图标下所属行为必填项目。 </div>
			<div class="ui-button-big center mb50"><a href="javascript:editParam();">确定</a></div>
		</div>
		</form>
       
	</body>

</html>