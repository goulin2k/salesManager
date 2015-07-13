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
		<title>绩效目标查看</title>
	
	</head>
	<body class="ui-lv2bg">
	    <div class="ui-content-title"><p><img src="skin/Default/images/ui-content-ico/set.png" />绩效目标查看</p></div>
		  <div class="ui-table ml15">
			<table class="ui-table-one" cellspacing="2" cellpadding="0" >
			 <caption><img src="skin/Default/images/ui-content-ico/pie-chart.png" />绩效目标</caption>
			  <tr>
			    <td  class="ui-table-title">考核用户：</td>
			    <td colspan="3">
						${userPlan.userName }
				</td>
			  </tr>
			  <tr>
			    <td  class="ui-table-title">方案名称：</td>
			    <td  colspan="3">
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
			        <td  class="ui-table-title">考核项目明细：</td>
			        <td colspan="3">
			       <!-- ==============嵌入的子表格样式================-->
			        <table id="itemsTable"  class="ui-table-two" cellspacing="2" cellpadding="0" >
			              <tr class="ui-table-title-two">
			                <td>项目名称</td>
			                <td>项目来源</td>
			                <td>权重</td>	 
			                <td>考核人</td>                  	
			                <td>目标值</td>	    
			              </tr>
			              <s:iterator id="uitem" value="userPlan.userItemList" status="dl">
			              <tr>
			                <td class="ui-table-heedtwo">${uitem.item.itemName }</td>
			                <td>${uitem.item.resourceName }</td>
			                <td>${uitem.item.specificWeight }%</td>
			                <td>${uitem.accessUserName }</td>
			                <td>${uitem.goal }${uitem.item.measurementUnit }</td>
			              </tr>
			              </s:iterator>
			        </table>
			       
			        </td>
				  </tr>
			</table>
             <div class="ui-button-big center mt10 mb50"><a href="javascript:history.back();">返回</a></div>
		  </div>
	</body>

</html>