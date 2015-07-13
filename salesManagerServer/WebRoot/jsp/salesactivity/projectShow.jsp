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
<title>销售计划</title>
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
		var basePath = "<%=path%>/";
		function cancelActivity(){ 
			var url = basePath + "project!index";
			url = encodeURI(url);
			window.location.href = url;
		}
		
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
	<div class="ui-content-title"><p><img src="skin/Default/images/ui-content-ico/Sell16.png" />销售计划</p></div>
	<!-- 标准工具栏 -->
	<nav class="navbar navbar-default crm-toolbar" role="navigation">
		<ul class="nav navbar-nav">
	    	
	    	<li class="active"><a href="#" id="assess">
	    		<span class="glyphicon glyphicon-comment"></span>&nbsp;计划督办</a></li>
	    	<li class="active"><a href="project!index">
	    		<span class="glyphicon glyphicon-arrow-left"></span>&nbsp;返回</a></li>
	    </ul>
	 </nav>
<div class="ui-table ml15">
<table class="ui-table-one" cellspacing="2" cellpadding="0" >

  <tr>
    <td  class="ui-table-title">主题：</td>
    <td class="ui-table-heed"><s:property value="project.topical"/></td>
  </tr>
  <tr>
    <td  class="ui-table-title">对应客户：</td>
    <td><s:property value="project.customerName"/></td>
    <td  class="ui-table-title">计划类型：</td>
    <td><s:property value="project.enumerationName"/></td>
  </tr>
  <tr>
    <td  class="ui-table-title">计划内容：</td>
    <td  class="ui-table-textarea" colspan="3">
  		<textarea name="comment" rows="4" readonly><s:property value="project.comment"/></textarea>
    </td>
  </tr>
  <tr>
    <td class="ui-table-title">计划目标：</td>
    <td class="ui-table-textarea" colspan="3">
  		<textarea name="goal" rows="4" readonly><s:property value="project.goal"/></textarea>
    </td>
  </tr> 
  <tr>
    <td  class="ui-table-title">负责人：</td>
    <td><s:property value="project.responseUserName"/></td>
    <td  class="ui-table-title">创建人：</td>
    <td><s:property value="project.createUserName"/></td>
  </tr>
  <tr>
    <td  class="ui-table-title">关注人：</td>
    <td class="ui-table-heed"><s:property value="project.topical"/></td>
  </tr>
  <tr>
    <td  class="ui-table-title">开始时间：</td>
    <td><s:property value="project.startTime"/></td>
    <td  class="ui-table-title">结束时间：</td>
    <td><s:property value="project.endTime"/></td>
  </tr>
  <tr>
    <td  class="ui-table-title">进度：</td>
    <td><s:property value="project.completionRate"/></td>
    <td  class="ui-table-title">完成时间：</td>
    <td><s:date name="project.completionTime" format="yyyy-MM-dd" /></td>
  </tr>
  <tr>
    <td  class="ui-table-title">销售活动：</td>
    <td colspan="3">
    <!-- ==============嵌入的子表格样式================-->
    <table  class="ui-table-two" cellspacing="2" cellpadding="0" >
      <tr class="ui-table-title-two">
        <td>主题</td>
        <td>负责人</td>
        <td>活动时间</td>
        <td>进度</td>
        <td>拜访对象</td>
        <td>协同拜访人</td> 
        <td>活动内容</td>
      </tr>
      <s:iterator value="project.activityList" status="dl">
      <tr>
        <td class="ui-table-heedtwo"><s:property value="topical"/></td>
        <td><s:property value="responseUserName"/></td>
        <td><s:date name="activityDate" format="yyyy-MM-dd" /></td>
        <td><s:property value="completionRate"/></td>
        <td><s:property value="visitPerson"/></td>
        <td><s:property value="coordinationVisitPerson"/></td>
        <td><s:property value="comment"/></td> 
      </tr>
      </s:iterator>
    </table>

    </td>
  </tr> 
  <tr>
    <td  class="ui-table-title">销售评价：</td>
    <td colspan="3">
    <!-- ==============嵌入的子表格样式================-->
    <table  class="ui-table-two" cellspacing="2" cellpadding="0" >
      <tr class="ui-table-title-two">
        <td>评价人</td>
        <td>评价内容</td>
        <td>评价时间</td> 
      </tr>
      <s:iterator value="project.assessList" status="dl">
      <tr> 
        <td><s:property value="assessUserName"/></td>
        <td><s:property value="comment"/></td>
        <td><s:date name="commentTime" format="yyyy-MM-dd" /></td> 
      </tr>
      </s:iterator>
    </table>

    </td>
  </tr> 
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
</body>
</html>