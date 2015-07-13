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
<title>修改公司发文</title>
<script type="text/javascript" src="<%=basePath %>script/common/jquery-1.4.2.min.js" ></script>
<script type="text/javascript" src="<%=basePath %>script/common/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath %>script/common/easyui/validate.js"></script>
<script type="text/javascript" src="<%=basePath %>script/common/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=basePath %>/jsp/ckeditor/ckeditor.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath %>script/common/easyui/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>script/common/easyui/themes/default/easyui.css" />
<link href="<%=basePath %>skin/Default/css/base.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>skin/Default/css/global.css" rel="stylesheet" type="text/css" /> 
	<link rel="icon" href="<%=basePath %>/jsp/lhgcalendar/favicon.ico" type="image/x-icon"/>
	<link rel="shortcut icon" href="<%=basePath %>/jsp/lhgcalendar/favicon.ico" type="image/x-icon"/>
	<link href="<%=basePath %>/jsp/lhgcalendar/_doc/common.css" type="text/css" rel="stylesheet"/>
	<link href="<%=basePath %>/jsp/lhgcalendar/_doc/prettify/prettify.css" type="text/css" rel="stylesheet"/>
	<script type="text/javascript" src="<%=basePath %>/jsp/lhgcalendar/_doc/prettify/prettify.js"></script>
	<script type="text/javascript" src="<%=basePath %>/jsp/lhgcalendar/lhgcalendar.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>/jsp/lhgcalendar/_demo/demo.js"></script>
<script type="text/javascript">
var basePath = "<%=path%>/";  
 
function cancelNews(){ 
	var url = basePath + "dispatch!list";
	url = encodeURI(url);
	window.location.href = url;
} 

function submitNews(){
	if ($('#news').form('validate')) {
		news.submit();
	}
}
</script>
</head>
<body>
<form id="news" name="news" action="<%=basePath %>dispatch!update" method="post">
<div class="ui-table ml15">
<table class="ui-table-one" cellspacing="2" cellpadding="0" >
   <caption>新增公司发文</caption> 
  <tr><input type="hidden" name="news.newsId" value="<s:property value="news.newsId"/>" />
     <td class="ui-table-title">标题：</td>
     <td><input class="easyui-validatebox" type="text" required="true" name="news.title" value="<s:property value="news.title"/>" validType="maxLength['标题',50]"/></td> 
  </tr> 
   <tr>
     <td  class="ui-table-title">内容：</td>
     <td colspan="3" class="ui-table-textarea">
   		<textarea name="news.content" rows="6" >
   			<s:property value="news.content"/>
   		</textarea>
   		<script type="text/javascript"> 
			CKEDITOR.replace('news.content');
	     </script>
     </td>
   </tr>  
  <tr>
     <td  class="ui-table-title">状态：</td>
     <td class="ui-table-select-s"><s:select theme="simple" name="news.status" listKey="key" listValue="value" list="statusMap"/></td> 
  </tr>
</table> 
</div>
<div class="ui-button-big center "><a href="#" onClick="submitNews();">确定</a>
<a href="#" onClick="cancelNews()">取消</a></div>
</form>
</body>
</html>