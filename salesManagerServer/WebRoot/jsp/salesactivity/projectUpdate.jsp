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
<title>销售计划详细信息</title>
	<link type="text/css" href="<%=basePath %>skin/Default/css/jquery-ui-1.10.0.custom.css" rel="stylesheet"  />
	<!-- Le HTML5 shim, for IE6-8 support of HTML elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
	<script type="text/javascript" language="javascript" src="<%=basePath %>script/common/jquery-ui-1.10.0.custom.min.js"></script>
	<script src='dwr/interface/customerProjectService.js'></script>
	<script src='dwr/engine.js'></script>
	<script src='dwr/util.js'></script>
	<script type="text/javascript" language="javascript" src="<%=basePath %>script/common/common.js"></script>
	<script type="text/javascript">
	$(function() {
		var opt = {
		        autoOpen: false,
		        modal: true,
		        width: 600
		};
	
		// Modal Link
		$('#assess').click(function () {
		    $('#dialog-assess').dialog(opt).dialog('open');
		    return false;
		});
		
		//初始化对话框
		$("#dialog-assess").dialog({
		    autoOpen: false,
		    modal: true,
		    buttons: {
		        	确定: function () {
		        		$("#assessForm").submit();
			            $(this).dialog("close");
			        },
		        	取消: function () {
			            $(this).dialog("close");
			        },
		    }
		});
	});
	</script>
</head>
<body class="ui-lv2bg">

<form id="project" name="project" action="<%=basePath %>project!update" method="post">
	<!-- 标准工具栏 -->
	<nav class="navbar navbar-default crm-toolbar" role="navigation">
		<ul class="nav navbar-nav">
			<s:if test="project.status==0">
		    	<li class="active"><a href="project!edit?project.projectId=<s:property value="project.projectId"/>">
		    		<span class="glyphicon glyphicon-edit"></span>&nbsp;修改</a></li>
		    	<li class="active"><a href="project!commit?project.projectId=<s:property value="project.projectId"/>">
		    		<span class="glyphicon glyphicon-edit"></span>&nbsp;提交</a></li>
	    	</s:if>
	    	<s:if test="project.status==1">
		    	<li class="active"><a href="activity!editNew?projectId=<s:property value="project.projectId"/>&from=project!get">
		    		<span class="glyphicon glyphicon-plus"></span>&nbsp;添加活动</a></li>
		    	<li class="active"><a href="#" id="assess">
		    		<span class="glyphicon glyphicon-comment"></span>&nbsp;计划督办</a></li>
	    	</s:if>
	    	<s:if test="project.status==0">
		    	<li class="active"><a href="project!delete?project.projectId=<s:property value="project.projectId"/>">
		    		<span class="glyphicon glyphicon-remove"></span>&nbsp;删除</a></li>
		    </s:if>
	    	<li class="active"><a href="project!index">
	    		<span class="glyphicon glyphicon-arrow-left"></span>&nbsp;返回</a></li>
	    </ul>
	 </nav>
	 
	<div class="panel panel-default" id="searchResult" style="padding:2px;">
     <div class="panel-heading crm-table-title"><B>客户查询记录</B></div>
	<table class="ui-table-one" cellspacing="2" cellpadding="0" >
	
	  <tr>
	    <td  class="ui-table-title">主题：
	    	<input type="hidden" name="project.projectId" value="<s:property value="project.projectId"/>" /></td>
	    <td class="customerProject">
	    	<s:property value="project.topical"/></td>
	    <td  class="ui-table-title">负责人：</td>
	    <td>
	    	<s:property value="project.responseUserName"/></td>
	  </tr>
	  <tr>
	    <td  class="ui-table-title">对应客户：</td>
	    <td>
	    	<s:property value="project.customerName"/>
	    <td  class="ui-table-title">计划类型：</td>
	    <td>
	    	<s:select theme="simple" disabled="true" name="project.enumerationId" listKey="enumerationId" 
	    		listValue="enumerationName" list="enumerationList" cssClass="form-control"/></td>
	  </tr>
	  <tr>
	    <td class="ui-table-title">客户项目：</td>
	    <td class="ui-table-select-s"> 
	    	<s:property value="project.customerProjectName"/>
		</td> 
	  </tr> 
	  <tr>
	    <td  class="ui-table-title">备注：</td>
	    <td  class="ui-table-textarea" colspan="4">
	  		<s:property value="project.comment"/>
	    </td>
	  </tr>
	  <tr>
	    <td class="ui-table-title">计划目标：</td>
	    <td class="ui-table-textarea" colspan="4">
	  		<s:property value="project.goal"/>
	    </td>
	  </tr>  
	  <tr>
	    <td  class="ui-table-title">关注人：</td>
	    <td class="ui-table-heed">
		    <div class="col-sm-10" id="attentionUser">
		    	<s:iterator value="project.attentionUserList" status="dl">
		    		<span class='label label-warning' style='margin:7px 0 0px 5px;cursor:pointer;' 
		    			id='<s:property value="userId"/>' ><s:property value="userName"/></span>
		    	</s:iterator>
		    </div>
		    <input id="attentionUserIds" name="attentionUserIds" type="hidden" />
	    </td>
	  </tr>
	  <tr>
	    <td  class="ui-table-title">开始时间：</td>
	    <td>
	    	<s:property value="project.startTime"/></td>
	    <td  class="ui-table-title">结束时间：</td>
	    <td>
	    	<s:property value="project.endTime"/></td>
	  </tr> 
	  <tr>
	    <td  class="ui-table-title">进度：</td>
	    <td><s:property value="project.completionRate" /></td>
	    <td  class="ui-table-title">完成时间：</td>
	    <td><s:date name="project.completionTime" format="yyyy-MM-dd" /></td>
	  </tr>
	 </table>
  </div>
</form>     

<div class="panel panel-default" id="activityList" style="padding:2px;">
     <div class="panel-heading crm-table-title"><B>销售活动</B></div>  
	  <table  class="table table-hover" cellspacing="2" cellpadding="0" >
	      <thead>
	        <th>主题</th>
	        <th>负责人</th>
	        <th>活动时间</th>
	        <th>进度</th>
	        <th>拜访对象</th>
	        <th>协同拜访人</th> 
	        <th>活动内容</th>
		    <th>操作</th>
	      </thead>
	      <s:iterator value="project.activityList" status="dl">
		      <tr>
		        <td class="ui-table-heedtwo"><s:property value="topical"/></td>
		        <td><s:property value="responseUserName"/></td>
		        <td><s:date name="activityDate" format="yyyy-MM-dd" /></td>
		        <td><s:property value="completionRate"/></td>
		        <td><s:property value="visitPerson"/></td>
		        <td><s:property value="coordinationVisitPerson"/></td>
		        <td><s:property value="comment"/></td> 
				<td><a href="<%=basePath %>activity!get?activity.activityId=<s:property value="activityId"/>"> 修改  </a></td>
		      </tr>
	      </s:iterator>
		</table>
</div>
	
<div class="panel panel-default" id="assessList" style="padding:2px;">
     <div class="panel-heading crm-table-title"><B>计划督办</B></div>
	 <table  class="table table-hover" cellspacing="2" cellpadding="0" >
	      <thead>
	        <th>督办人</th>
	        <th>督办内容</th>
	        <th>督办时间</th> 
		    <th>操作</th>
	      </thead>
	      <s:iterator value="project.assessList" status="dl">
		      <tr> 
		        <td><s:property value="assessUserName"/></td>
		        <td><s:property value="comment"/></td>
		        <td><s:date name="commentTime" format="yyyy-MM-dd" /></td> 
				<td><a href="#">删除  </a></td>
		      </tr>
	      </s:iterator>
	    </thead>
	  </table>
</div>

<!--static dialog-->
<div id="dialog-assess"  title="计划督办">
	<s:form id="assessForm" name="assessForm" action="assess!add" method="post">
		<input type="hidden" name="assess.projectId" value="<s:property value="project.projectId"/>">
		<div class="form-group">
		  	<div class="col-sm-12">	      	
		    	<textarea rows="6" id="comment" name="assess.comment" required class="form-control" placeholder="计划督办内容">
		    	</textarea>
		    </div>
		</div>
	 </s:form>	
</div>

<script type="text/javascript">
	var basePath = "<%=path%>/";
</script>
</body>
</html>