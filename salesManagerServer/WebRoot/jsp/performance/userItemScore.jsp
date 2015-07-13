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
		<title>绩效项目评定</title>
	    <link type="text/css" href="<%=basePath %>skin/Default/css/fisheye.css" rel="stylesheet"  />
	    <link type="text/css" href="<%=basePath %>skin/bootstrap/css/jquery-ui-1.10.0.custom.css" rel="stylesheet"  />
	    <link type="text/css" href="<%=basePath %>skin/bootstrap/css/jquery.ui.1.10.0.ie.css" rel="stylesheet"  />
	    <link type="text/css" href="<%=basePath %>skin/Default/css/base.css" rel="stylesheet"  />
	    <link type="text/css" href="<%=basePath %>skin/Default/css/global.css" rel="stylesheet"  />
	    <link href="skin/bootstrap/css/bootstrap-dialog.min.css" rel="stylesheet"> 
		<script type="text/javascript" src="<%=basePath %>skin/bootstrap/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="<%=basePath %>skin/bootstrap/js/jquery-ui-1.10.0.custom.min.js"></script>
		<script type="text/javascript" src="<%=basePath %>skin/bootstrap/js/bootstrap-dialog.min.js"></script>
		<script type="text/javascript">
		
		function editItemScore () {
			if ($('#itemForm').form('validate')) {
				itemForm.submit();
			}
		}
		
		function calScore(weight) {
			var tempScore = parseInt($("#tempScore").val());
			if (0<=tempScore<=100) {
				var score = parseInt(tempScore*parseInt(weight)/100);
				$("#scoreTd").html(score);
				$("#score").val(score);
			} else {
				$.messager.alert('绩效得分','绩效得分只能输入0-100的整数','warning');
				$("#tempScore").val('');
			}
		}
		</script>
	</head>
	<body class="ui-lv2bg">
		<div class="panel panel-default" id="searchResult" style="padding:2px;">   
	    <div class="ui-content-title" align="center"><H3>绩效项目评定</H3></div> 
	    <form id="itemForm" name="itemForm" action="userPerformance!editItemScore" method="post">
	    <input type="hidden"  name ="userItem.userPlan.performancePlanId" value="${userItem.performancePlanId}"/> 
	    <input type="hidden"  name ="userItem.performanceItemId" value="${userItem.performanceItemId}"/>
		  <div class="panel panel-default" id="searchResult" style="padding:2px;">  
			<table class="ui-table-one" cellspacing="2" cellpadding="0"> 
			  <tr>
			    <td  class="ui-table-title">考核用户：</td>
			    <td colspan="3">
						${userItem.userPlan.userName }
				</td>
			  </tr>
			  <tr>
			    <td  class="ui-table-title">方案名称：</td>
			    <td  colspan="3">
			            ${userItem.userPlan.plan.name }
			    </td>
			  </tr>
			  <tr>
			    <td  class="ui-table-title">考核项目：</td>
			    <td  colspan="3">
			            ${userItem.item.itemName }
			    </td>
			  </tr>
			  <tr>
			        <td  class="ui-table-title">年度：</td>
			        <td>
			            ${userItem.userPlan.year }年
			        </td>
			        <s:if test="userPlan.plan.cycleId==2">
			        <td  class="ui-table-title">季度：</td>
                    <td>
                        ${userItem.userPlan.season }季度
                    </td>
                    </s:if>
                    <s:if test="userPlan.plan.cycleId==1">
                    <td  class="ui-table-title">月度：</td>
                    <td>
                        ${userItem.userPlan.month }月
                    </td>
                    </s:if>
			  </tr>
			  <tr>
			  <td  class="ui-table-title">评分（百分制）：</td>
			        <td>
			        <s:if test="userItem.score==null || userItem.score == ''">
			              <input type="text" id="tempScore" class="form-control" class="easyui-validatebox" validType="number" required="true" onblur="calScore('<s:property value="userItem.item.specificWeight" />')"/>
			        </s:if>
			        </td>
			        <td class="ui-table-title">绩效得分：<input type="hidden" id="score" class="form-control" name ="userItem.score"/></td>
                    <td id="scoreTd">
                       ${userItem.score } 
                    </td>
			  </tr>
			  <tr>
					<td class="ui-table-title">
						评语：
					</td>
					<td colspan="3" class="ui-table-textarea">
						<textarea rows="6" name="userItem.comment" class="form-control" class="easyui-validatebox" validType="maxLength['评语',100]">${userItem.comment }</textarea>
					</td>
				</tr>
				
			</table>
			<div class="ui-button-big center mt20 mb50 ">
			<s:if test="userItem.score==null || userItem.score == ''">
			<a href="javascript:editItemScore();" class="fl mr10">确定</a>
			</s:if>
			<a href="javascript:history.back();" class="fl mr10">返回</a>
			</div>
		  </div>
		  </form>
	</body>

</html>