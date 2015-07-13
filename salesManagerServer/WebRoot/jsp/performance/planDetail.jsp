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
		<title>考核方案查看</title>
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
			<table class="ui-table-one" cellspacing="2" cellpadding="0" > 
			  <tr>
			    <td  class="ui-table-title">方案名称：</td>
			    <td colspan="3">
						${plan.name }
				</td>
			  </tr>
			  <tr>
			    <td  class="ui-table-title">方案周期：</td>
			    <td  colspan="3">
			            ${plan.cycleName }
			    </td>
			  </tr>
			  <tr>
			    <td  class="ui-table-title">岗位类型：</td>
			        <td>
			            ${plan.postTypeName }
			        </td>
			        <td  class="ui-table-title">适用岗位：</td>
                    <td>
                        ${plan.suitablePostName }
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
			                <td>项目来源</td>
			                <td>单位</td>
			                <td>权重</td>	                	
			               	                
			              </tr>
			              <s:iterator id="sitem" value="plan.itemList" status="dl">
			              <tr>
			                <td class="ui-table-heedtwo">${sitem.itemName }</td>
			                <td>${sitem.resourceName }</td>
			                <td>${sitem.measurementUnit }</td>
			                <td>${sitem.specificWeight }%</td>
			                
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