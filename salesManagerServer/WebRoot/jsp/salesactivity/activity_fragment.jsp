<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="ui-content-title" align="center"><h2>销售活动信息</h2></div>	
<div class="ui-table ml15">
<table class="ui-table-one" cellspacing="2" cellpadding="0" >

  <tr>
    <td  class="ui-table-title">主题：</td>
    <td><s:property value="activity.topical"/></td>
    <td  class="ui-table-title">对应销售计划：</td>
    <td><s:property value="activity.projectTopical"/></td>
  </tr>
  <tr>
    <td  class="ui-table-title">对应客户：</td>
    <td><s:property value="activity.customerName"/></td>
    <td  class="ui-table-title">活动类型：</td>
    <td><s:property value="activity.enumerationName"/></td>
  </tr>
  <tr>
    <td  class="ui-table-title">负责人：</td>
    <td><s:property value="activity.responseUserName"/></td>
  </tr>  
  <tr>
    <td  class="ui-table-title">拜访对象：</td>
    <td><s:property value="activity.visitPerson"/></td>
    <td  class="ui-table-title">协同拜访人：</td>
    <td><s:property value="activity.coordinationVisitPerson"/></td>
  </tr>
  <tr>
    <td  class="ui-table-title">进度：</td>
    <td><s:property value="activity.completionRate"/></td>
    <td  class="ui-table-title">活动时间：</td>
    <td><s:date name="activity.activityDate" format="yyyy-MM-dd" /></td>
  </tr>
   <tr>
    <td  class="ui-table-title">签到地点：</td>
    <td colspan="3">
  		<s:property value="activity.locAddress"/>
    </td>
  </tr>
  <tr>
    <td  class="ui-table-title">活动内容：</td>
    <td  class="ui-table-textarea" colspan="3">
  		<s:property value="activity.comment"/>
    </td>
  </tr> 
</table>
</div>