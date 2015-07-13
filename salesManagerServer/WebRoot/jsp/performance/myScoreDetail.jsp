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
		<title>绩效详情</title>
	    <link type="text/css" href="<%=basePath %>skin/Default/css/fisheye.css" rel="stylesheet"  />
	    <link type="text/css" href="<%=basePath %>skin/bootstrap/css/jquery-ui-1.10.0.custom.css" rel="stylesheet"  />
	    <link type="text/css" href="<%=basePath %>skin/bootstrap/css/jquery.ui.1.10.0.ie.css" rel="stylesheet"  />
	    <link type="text/css" href="<%=basePath %>skin/Default/css/base.css" rel="stylesheet"  />
	    <link type="text/css" href="<%=basePath %>skin/Default/css/global.css" rel="stylesheet"  />
	    <link href="skin/bootstrap/css/bootstrap-dialog.min.css" rel="stylesheet"> 
		<script type="text/javascript" src="<%=basePath %>skin/bootstrap/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="<%=basePath %>skin/bootstrap/js/jquery-ui-1.10.0.custom.min.js"></script>
		<script type="text/javascript" src="<%=basePath %>skin/bootstrap/js/bootstrap-dialog.min.js"></script>
	</head>
	<body class="ui-lv2bg"> 
		<div class="panel panel-default" id="searchResult" style="padding:2px;">
		<div class="panel-heading crm-table-title"><B>考核方案查看</B></div>  
	    <input type="hidden"  name ="userPlan.performancePlanId" value="${userPlan.performancePlanId}"/> 
			<table class="ui-table-one" cellspacing="2" cellpadding="0" > 
			  <tr>
			    <td  class="ui-table-title">用户：</td>
			    <td>
			            ${userPlan.userName }
			    </td>
			    <td  class="ui-table-title">方案名称：</td>
			    <td>
			            ${userPlan.plan.name }
			    </td>
			  </tr>
			  <tr>
			        <td  class="ui-table-title">年度：</td>
			        <td>
			            ${userPlan.year }年
			        </td>
			        <s:if test="userPlan.plan.cycleId==2">
			        <td  class="ui-table-title">季度：</td>
                    <td>
                        ${userPlan.season }季度
                    </td>
                    </s:if>
                    <s:if test="userPlan.plan.cycleId==1">
                    <td  class="ui-table-title">月度：</td>
                    <td>
                        ${userPlan.month }月
                    </td>
                    </s:if>
			  </tr>
			  <tr>
			    <td  class="ui-table-title">考核总成绩：</td>
			    <td  colspan="3">
			            ${userPlan.totalScore }
			    </td>
			  </tr>
			  </table>
			  </div>
			  <div class="panel panel-default" id="searchResult" style="padding:2px;">
     			<div class="panel-heading crm-table-title"><B>考核项目明细</B></div> 
			       <!-- ==============嵌入的子表格样式================-->
			       	   <table id="itemsTable"  class="ui-table-two" cellspacing="2" cellpadding="0" >
			              <tr class="ui-table-title-two">
			                <td>项目名称</td>
			                <td>权重</td>	 
			                <td>考核人</td>                  	
			                <td>目标值</td>
			                <td>绩效得分（百分制）</td>
			                <td>加权得分</td>
			                <td>评语</td>
			              </tr>
			              <s:iterator id="uitem" value="userPlan.userItemList" status="dl">
			              <tr>
			                <td class="ui-table-heedtwo">${uitem.item.itemName } </td>
			                <td>${uitem.item.specificWeight }%</td>
			                <td>${uitem.accessUserName }</td>
			                <td>${uitem.goal }${uitem.item.measurementUnit }</td>
			                <td>${uitem.scoreHun }</td>
			                <td>${uitem.score }</td>
			                <td>${uitem.comment }</td>
			              </tr>
			              </s:iterator>
			        </table>
			        
			        </td>
				  </tr>
			</table>
            <div class="ui-button-big center mt10 mb50 "><a href="javascript:history.back();">返回</a></div>
		  </div>
	</body>

</html>