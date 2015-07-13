<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新闻展示</title>
<link href="<%=basePath %>skin/Default/css/base.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>skin/Default/css/global.css" rel="stylesheet" type="text/css" /> 
<script type="text/javascript" src="<%=basePath %>/jsp/ckeditor/ckeditor.js"></script>
</head>
<body>
	<div class="ui-content-title"><p><img src="skin/Default/images/ui-content-ico/list.png" />新闻中心</p></div>
 <!--======内容区域子导航======--> 

<!--======================================== 正文=========================================-->  
<div class="ui-lv3-con">
        	<h1><s:property value="news.title"/></h1>
            <hr /><p>时间：<s:date name="news.addTime" format="yyyy-MM-dd" />　作者：<s:property value="news.userName"/></p></div>
            <s:if test="news.imgUrl != null">
				<p><img src="<s:property value="news.imgUrl"/>" width="700" height="600" /></p> 
			</s:if> 
            <div class="ui-lv3-con-news">
 			<p><s:property value="news.content"  escape="false"/></p> 
 			<script type="text/javascript">
	   			CKEDITOR.config.readOnly = true;   			
				CKEDITOR.replace('news.content',  {
			        toolbarStartupExpanded:false,
			        toolbar :[['Print']],
			        height:800 
			    });
		     </script>
            </div> 
            <div class="ui-lv3-page">
                 
                 <a href="<%=basePath %>news!list" class="fr"><p><span>公司公告新闻列表</span>   >></p></a>
             </div>
        
</body>
</html>