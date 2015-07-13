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
<script type="text/javascript" src="<%=basePath %>script/common/jquery-1.4.2.min.js" ></script>
<script type="text/javascript" src="<%=basePath %>script/common/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath %>script/common/easyui/validate.js"></script>
<script type="text/javascript" src="<%=basePath %>script/common/easyui/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath %>script/common/easyui/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>script/common/easyui/themes/default/easyui.css" />
<link href="<%=basePath %>skin/Default/css/base.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>skin/Default/css/global.css" rel="stylesheet" type="text/css" /> 
</head>
<body>
<div class="ui-content-title"><p><img src="skin/Default/images/ui-content-ico/time.png" />任务计划</p></div>
<div class="ui-table ml15">
<table class="ui-table-one" cellspacing="2" cellpadding="0" >

  <tr>
    <td  class="ui-table-title">主题：</td>
    <td class="ui-table-heed"><s:property value="project.topical"/></td>
  </tr>
  <tr>
    <td  class="ui-table-title">对应客户：</td>
    <td><s:property value="project.customerId"/></td>
    <td  class="ui-table-title">计划类型：</td>
    <td><s:property value="project.enumerationName"/></td>
  </tr>
  <tr>
    <td  class="ui-table-title">计划内容：</td>
    <td  class="ui-table-textarea" colspan="2">
  		<textarea name="comment" rows="4" readonly><s:property value="project.comment"/></textarea>
    </td>
  </tr>
  <tr>
    <td class="ui-table-title">计划目标：</td>
    <td class="ui-table-textarea" colspan="2">
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
    <td><s:date name="project.startTime" format="yyyy-MM-dd" /></td>
    <td  class="ui-table-title">结束时间：</td>
    <td><s:date name="project.endTime" format="yyyy-MM-dd" /></td>
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

</body>
</html>