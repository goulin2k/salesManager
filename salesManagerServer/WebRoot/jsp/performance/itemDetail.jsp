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
		<title>考核项目查看</title>
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
	<div class="panel panel-default" id="searchResult" style="padding:2px;">
		<div class="panel-heading crm-table-title"><B>考核项目</B></div>  
			<table class="ui-table-one" cellspacing="2" cellpadding="0" >
				<tr>
					<td class="ui-table-title">
						项目名称：
					</td>
					<td >
						${item.itemName }
					</td>
				</tr>
                <tr>
			        <td  class="ui-table-title">来源：</td>
			        <td >
			              ${item.resourceName }
			        </td>
			    </tr>
			    <s:if test="item.resourceId==2">
			    <tr>
				    <td  class="ui-table-title">量化指标项：</td>
                    <td>
                    ${item.sqlName }
                    </td>
                    <td class="ui-table-title">度量单位：</td>
				    <td>
				    ${item.measurementUnit }
				    </td>
				</tr>
			    </s:if>
			    
			    
				<tr>
					<td  class="ui-table-title">岗位类型：</td>
			        <td>
			              ${item.postTypeName }
			        </td>
			        <td  class="ui-table-title">评价岗位：</td>
                    <td>
	                    ${item.accessPostName }
                    </td>
				</tr>

				<tr>
					<td class="ui-table-title">
						项目说明：
					</td>
					<td >
						${item.comment }
					</td>
				</tr>
			</table>
			
			<div class="ui-button-big center mt10 mb50 "><a href="javascript:history.back();">返回</a></div>
		</div>
	</body>

</html>