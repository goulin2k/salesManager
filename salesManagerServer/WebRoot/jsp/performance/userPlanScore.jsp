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
		<title>绩效目标评定</title>
	    <link type="text/css" href="<%=basePath %>skin/Default/css/fisheye.css" rel="stylesheet"  />
	    <link type="text/css" href="<%=basePath %>skin/bootstrap/css/jquery-ui-1.10.0.custom.css" rel="stylesheet"  />
	    <link type="text/css" href="<%=basePath %>skin/bootstrap/css/jquery.ui.1.10.0.ie.css" rel="stylesheet"  />
	    <link type="text/css" href="<%=basePath %>skin/Default/css/base.css" rel="stylesheet"  />
	    <link type="text/css" href="<%=basePath %>skin/Default/css/global.css" rel="stylesheet"  />
	    <link href="skin/bootstrap/css/bootstrap-dialog.min.css" rel="stylesheet">	   
	
		<script type="text/javascript" src="<%=basePath %>skin/bootstrap/js/jquery-1.8.3.min.js"></script>	
		<script type="text/javascript" src="<%=basePath %>skin/bootstrap/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="<%=basePath %>skin/bootstrap/js/jquery-ui-1.10.0.custom.min.js"></script>
		<script type="text/javascript" src="<%=basePath %>skin/bootstrap/js/bootstrap-dialog.min.js"></script>
			
		<!-- easyUI js & css -->
		<script type="text/javascript" language="javascript" src="<%=basePath %>script/common/common.js"></script>
		<script type="text/javascript" src="<%=basePath %>script/common/easyui-1.3.5/plugins/jquery.parser.js"></script>
		<script type="text/javascript" src="<%=basePath %>script/common/easyui-1.3.5/jquery.easyui.min.js"></script>
		
		
		<link rel="stylesheet" type="text/css" href="<%=basePath %>script/common/easyui-1.3.5/themes/icon.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath %>script/common/easyui-1.3.5/themes/bootstrap/easyui.css" /> 
		<script type="text/javascript">
		
		function editScore (status) {
			if ($('#planForm').form('validate')) {
				$("#statusId").val(status);
				planForm.submit();
			}
		}
		
		function calScore(index,weight) {
			if ($("#tempScore_"+index).val() == "") {
				return;
			}
			var tempScore = parseInt($("#tempScore_"+index).val());
			if (tempScore>=0&&tempScore<=100) {
				var score = parseInt(tempScore*parseInt(weight)/100);
				$("#scoreTd_"+index).html(score+"");
				$("#score_"+index).val(score);
			} else {
				$.messager.alert('绩效得分','绩效得分只能输入0-100的整数','warning');
			}
		}
		
		function calSqlScore(index,userItemId,unit) {
			$.get("userPerformance!calSqlScore", {'userItemId': userItemId}, function(data) {
                var score = data.jsonReturn;
				$("#realTd_"+index).html(score+unit);
				//$("#score_"+index).v	al(score);
			});
			
		}
		</script>
	</head>
	<body class="ui-lv2bg">
		<div class="panel panel-default" id="searchResult" style="padding:2px;">   
	    <div class="ui-content-title" align="center"><H3>绩效目标评定</H3></div> 
	    <form id="planForm" name="planForm" action="userPerformance!editScoreList" method="post">
	    <div class="panel panel-default" id="searchResult" style="padding:2px;"> 
	    <input type="hidden"  name ="userPlan.performancePlanId" value="${userPlan.performancePlanId}"/> 
	    	<div class="panel-heading crm-table-title"><B>绩效目标</B></div>	
			<table class="ui-table-one" cellspacing="2" cellpadding="0" > 
			  <tr>
			    <td  class="ui-table-title">考核用户：</td>
			    <td colspan="3">
						${userPlan.userName }
						<input type="hidden" id="statusId" name ="userPlan.status"/>
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
			  </table>
			  </div>
			  <div class="panel panel-default" id="searchResult" style="padding:2px;">
     			<div class="panel-heading crm-table-title"><B>考核项目明细</B></div> 
			       <!-- ==============嵌入的子表格样式================--> 
			        <table id="itemsTable"  class="ui-table-two" cellspacing="2" cellpadding="0" >
			              <tr class="ui-table-title-two">
			                <td>项目名称</td>
			                <td>项目来源</td>
			                <td>权重</td>	 
			                <td>考核人</td>                  	
			                <td>目标值</td>
			                <td>完成值</td>
			                <td>评分（百分制）</td>
			                <td>绩效得分</td>
			                <td>评语</td>
			              </tr>
			              <s:iterator id="uitem" value="userPlan.userItemList" status="dl">
			              <tr>
			                <td class="ui-table-heedtwo">${uitem.item.itemName } <input type="hidden" name ="userPlan.userItemList[<s:property value="#dl.index" />].performanceItemId" value="${uitem.performanceItemId }"/></td>
			                <td>${uitem.item.resourceName }</td>
			                <td>${uitem.item.specificWeight }%</td>
			                <td>${uitem.accessUserName }</td>
			                <td>${uitem.goal }${uitem.item.measurementUnit }</td>
			                <td id="realTd_<s:property value="#dl.index" />">
			                <s:if test="item.resourceId==2">
			                <div class="ui-combottom fr"><a href="javascript:calSqlScore('<s:property value="#dl.index" />','<s:property value="performanceItemId" />','<s:property value="item.measurementUnit" />');"> 计算</a></div>
			                </s:if>
			                </td>
			                <td>
				                <s:if test="accessUserId == #session.USER_INFO.userId || accessUserId == null">
				                <input type="text" value="${uitem.scoreEnter }" style="width:50" id="tempScore_<s:property value="#dl.index" />" class="easyui-validatebox" validType="number" onblur="calScore('<s:property value="#dl.index" />','<s:property value="item.specificWeight" />')"/>			                
				                </s:if>
			                </td>
			                <td id="scoreTd_<s:property value="#dl.index" />">
				                ${uitem.score }
			                </td>
			                <input type="hidden" value="${uitem.score }" id="score_<s:property value="#dl.index" />" name ="userPlan.userItemList[<s:property value="#dl.index" />].score"/>			                
			                <td>
			                <s:if test="accessUserId == #session.USER_INFO.userId || accessUserId == null">
			                <textarea rows="1" cols="10" name="userPlan.userItemList[<s:property value="#dl.index" />].comment" class="easyui-validatebox" validType="maxLength['评语',100]">${uitem.comment }</textarea>
			                </s:if>
			                <s:else>${uitem.comment }</s:else>
			                </td>
			              </tr>
			              </s:iterator>
			        </table>
			        
			        </td>
				  </tr>
			</table>
			<div class="ui-button-big center mt10 mb50 "><a href="javascript:editScore(1);"  class="fl mr10">提交</a><a href="javascript:editScore(0);" class="fl mr10">保存</a><a href="javascript:history.back();"  class="fl">返回</a></div>
		  </div>
		  </form>
	    </div>
	</body>

</html>