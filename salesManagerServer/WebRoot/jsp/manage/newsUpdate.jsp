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
<title>修改新闻</title>
<script type="text/javascript" language="javascript" src="<%=basePath %>script/common/common.js"></script>
<link href="skin/Default/css/base.css" rel="stylesheet" type="text/css" />
<link href="skin/Default/css/global.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath %>/jsp/ckeditor/ckeditor.js"></script>
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
<body class="ui-lv2bg"> 
<div class="ui-content-title" align="center"><H3>修改新闻</H3></div> 
   <!--======内容区域子导航======--> 

<s:form id="newsForm" name="newsForm" action="news!update" theme="simple" namespace="/" method="post" validate="false">
<div class="panel panel-default" id="searchResult" style="padding:2px;"> 
			<table class="ui-table-one" cellspacing="2" cellpadding="0" >
			   
			  <tr><input type="hidden" name="news.newsId" value="<s:property value="news.newsId"/>" />
			     <td class="ui-table-title">标题：</td>
			     <td><input class="form-control" type="text" required="true" name="news.title" value="<s:property value="news.title"/>" validType="maxLength['标题',50]"/></td> 
			  </tr> 
			  <tr>
			    <td  class="ui-table-title">内容：</td>
			    <td  class="ui-table-textarea" colspan="2">
			  		<textarea name="news.content" rows="6"><s:property value="news.content"/></textarea> 
			   		<script type="text/javascript">
						CKEDITOR.replace('news.content');;
				     </script>
			    </td>
			  </tr>
			  <tr>
			     <td class="ui-table-title">图片：</td>
			     <td><s:file theme="simple" name="file"></s:file>
					 <s:if test="news.imgUrl!=null">
						<img src="<s:property value="news.imgUrl"/>" width="300" height="300" />
					 </s:if>
				 </td> 
			  </tr> 
			  <tr>
			     <td  class="ui-table-title">状态：</td>
			     <td ><s:select theme="simple" cssClass="form-control" name="news.status" listKey="key" listValue="value" list="statusMap"/></td> 
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