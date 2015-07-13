<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>销售评价修改</title>
<script type="text/javascript" src="<%=basePath %>script/common/jquery-1.4.2.min.js" ></script>
<script type="text/javascript" src="<%=basePath %>script/common/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath %>script/common/easyui/validate.js"></script>
<script type="text/javascript" src="<%=basePath %>script/common/easyui/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath %>script/common/easyui/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>script/common/easyui/themes/default/easyui.css" />
<link href="<%=basePath %>skin/Default/css/base.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>skin/Default/css/global.css" rel="stylesheet" type="text/css" /> 
<script type="text/javascript"> 
function updateAssess() {
	if ($('#assess').form('validate')) {
		assess.submit();
	}
}
</script>
</head>
<body> 
<form id="assess" name="assess" action="<%=basePath %>assess!update" method="post">
<div class="ui-table ml15">
<table class="ui-table-one" cellspacing="2" cellpadding="0" >
   <caption>修改销售评价<input type="hidden" name="assess.assessId" value="<s:property value="assess.assessId"/>"></input></caption> 
   <tr>
     <td  class="ui-table-title">评价内容：</td>
     <td colspan="3" class="ui-table-textarea">
   		<textarea name="assess.comment" class="easyui-validatebox" required="true" validType="maxLength['评价内容',500]" rows="6"><s:property value="assess.comment"/></textarea>
     </td>
   </tr>  
</table> 
</div>
<div class="ui-button-big center "><a href="#" onClick="updateAssess();">确定</a></div>
</form>
</body>
</html>