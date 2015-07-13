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
<title>新增新闻</title>
	<script type="text/javascript" language="javascript" src="<%=basePath %>script/common/common.js"></script>
	<link href="skin/Default/css/base.css" rel="stylesheet" type="text/css" />
	<link href="skin/Default/css/global.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=basePath %>/jsp/ckeditor/ckeditor.js"></script>
	<link rel="icon" href="<%=basePath %>/jsp/lhgcalendar/favicon.ico" type="image/x-icon"/>
	<script type="text/javascript" src="<%=basePath %>script/common/easyui-1.3.5/plugins/jquery.parser.js"></script>
	<script type="text/javascript" src="<%=basePath %>script/common/easyui-1.3.5/jquery.easyui.min.js"></script>
	
	<link rel="stylesheet" type="text/css" href="<%=basePath %>script/common/easyui-1.3.5/themes/icon.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath %>script/common/easyui-1.3.5/themes/bootstrap/easyui.css" /> 
	<script type="text/javascript" src="script/common/easyui/validate.js"></script>
	<script type="text/javascript" src="script/common/easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript">
		var basePath = "<%=path%>/";  
		 
		function cancelNews(){ 
			var url = basePath + "news!list";
			url = encodeURI(url);
			window.location.href = url;
		} 
		
		function submitNews(){
			if ($('#newsForm').form('validate')) {
				newsForm.submit();
			}
		}
	</script>
</head>
<body>
	<div class="ui-content-title" align="center"><H3>新增新闻公告</H3></div> 
	<!--======表格样式1======--> 
	<s:form id="newsForm" name="newsForm" action="news!add" theme="simple" namespace="/" method="post" validate="false">
	<div class="panel panel-default" id="searchResult" style="padding:2px;">
		<table class="ui-table-one" cellspacing="2" cellpadding="0" >
		  <tr>
		     <td class="ui-table-title" >标题：</td>
		     <td><input class="easyui-validatebox form-control" style="width:98%;" align="middle" type="text"
		     	name="news.title" validType="maxLength['标题',50]" required="true"/></td> 
		  </tr> 	
		   <tr>
		     <td  class="ui-table-title">内容：</td>
		     <td class="ui-table-textarea">
		   		<textarea name="news.content" rows="6" ></textarea> 
		   		<script type="text/javascript"> 
					CKEDITOR.replace('news.content');
			     </script>
		     </td>
		   </tr>  
		  <tr>
		     <td class="ui-table-title">图片：</td>
		     <td><s:file theme="simple" name="file"></s:file></td> 
		  </tr> 
		  <tr>
		     <td  class="ui-table-title">状态：</td>
		     <td><s:select theme="simple" cssClass="form-control" name="news.status" listKey="key" listValue="value" list="statusMap"/></td> 
		  </tr>
		</table> 
	</div> 
	<!--  按钮面板  -->
	<div class="crm-button-panel form-group ">
		<div class="col-sm-6">
			<a class="btn btn-primary col-sm-offset-9 col-sm-3" href="#" onClick="submitNews();">确定</a>
		</div>
		<div class="col-sm-6">
			<a class="btn btn-warning col-sm-3" href="#" onClick="cancelNews()">取消</a>
		</div>
	</div>
	</s:form>
</body>
</html>