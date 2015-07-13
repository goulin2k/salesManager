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
		<title>考核目标新增</title>
		<link href="skin/Default/css/base.css" rel="stylesheet" type="text/css" />
		<link href="skin/Default/css/global.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=basePath %>script/common/jquery-1.4.2.min.js" ></script>
		<script type="text/javascript" src="<%=basePath %>script/common/easyui-1.3.5/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="script/common/easyui/validate.js"></script>
		<script type="text/javascript" src="script/common/easyui/locale/easyui-lang-zh_CN.js"></script>
		<script src='dwr/interface/performancePlanService.js'></script>
		<script src='dwr/interface/performanceItemService.js'></script>
		<script src='dwr/engine.js'></script>
		<script src='dwr/util.js'></script>
		<script type="text/javascript">
		$(function() {
			$("#userSelection").change(function(){
				var userId = $("#userSelection").val();
				getPlanList(userId);
				//$("#userSelection option[value='--请选择用户--']").remove(); 
			});
			$("#planSelection").change(function(){
				var planId = $("#planSelection").val();
				getSelectedPlan(planId);
				getItemList(planId);
				//$("#userSelection option[value='--请选择方案--']").remove(); 
			});
			getUserList('<s:property value="#session.USER_INFO.userId" />');			
		});
		

		function getUserList(parentId){
			performancePlanService.getChildrenUserList(parentId,fillUserSelection);
		}

		function fillUserSelection(data){
			dwr.util.removeAllOptions("userSelection");
			dwr.util.addOptions("userSelection", [""]);
			dwr.util.addOptions("userSelection",data,"id","text");
			//getPlanList(data[0].userId);
		}

		function getPlanList(userId){
			performancePlanService.getPlanListByUserPosition(userId,fillPlanSelection);
		}

		function fillPlanSelection(data){
			dwr.util.removeAllOptions("planSelection");
			dwr.util.addOptions("planSelection", [""]);
			dwr.util.addOptions("planSelection",data,"planId","name");
			
		}

		function getSelectedPlan(planId) {
			performancePlanService.getPerformancePlan(planId,handleUiDisplay);
		}

		function handleUiDisplay(data) {
			if (data.cycleId == '1') {
				$('#seasonTitle').hide();
				$('#seasonSelect').hide();
				$('#monthTitle').show();
				$('#monthSelect').show();
			} else if (data.cycleId == '2') {
				$('#seasonTitle').show();
				$('#seasonSelect').show();
				$('#monthTitle').hide();
				$('#monthSelect').hide();
			} else {
				$('#seasonTitle').hide();
				$('#seasonSelect').hide();
				$('#monthTitle').hide();
				$('#monthSelect').hide();
			}
		}

		function getItemList(planId) {
			performanceItemService.getPlanItemList(planId,fillItemTable);
		}
		
		
		function fillItemTable(data) {
			clearItemTable();
			var index = 0;
			for (var i=0;i<data.length;i++) {
				//alert(data[i].itemName);
				var goalHtm = "";
				var userHtm = "";
				var checkHtml = "";
				if (data[i].resourceId == '2') {
					goalHtm = '<input type="text" name ="userPlan.userItemList['+index+'].goal" class="easyui-validatebox" required="true" validType="number"/>';
				    checkHtml = '(<input type="checkbox" name="userPlan.userItemList['+index+'].checkBoxStatus" />统计下属)';
				    userHtm = '<input type="hidden" name ="userPlan.userItemList['+index+'].accessUserId" value="<s:property value="#session.USER_INFO.userId" />"/>'+'<s:property value="#session.USER_INFO.userName" />';
				} else {
					userHtm = '<select id="accessSelection_'+index+'" name="userPlan.userItemList['+index+'].accessUserId"></select>';
				}
				var htm = '<tr id="as_'+data.itemId+'">'+
                '<td class="ui-table-heedtwo"><input type="hidden" name ="userPlan.userItemList['+index+'].itemId" value="'+data[i].itemId +'"/>'+data[i].itemName +'</td>'+
                '<td>'+data[i].resourceName +'</td>'+
                '<td>'+data[i].specificWeight+'%</td>'+
                '<td>'+userHtm+'</td>'+
                '<td>'+goalHtm+data[i].measurementUnit+checkHtml +'</td>'+
                '</tr>';
				$("#itemsTable").append(htm);
				getAccessUser(data[i].postType,data[i].accessPostId,index);
				index++;
			}
			$.parser.parse();    
		}

		function clearItemTable() {
			$("#itemsTable tr").each(function(i){
				if (i != 0) {
				    $(this).remove();
				}
			});
		}
		function getAccessUser(postType,postId,index) {
			//alert(postType+","+postId);
			var sid = "accessSelection_"+index;
			performancePlanService.getUserList(postType,postId,function(data){fillAccessSelection(data,sid)});
		}
		function fillAccessSelection(data,sid) {
			//alert(sid+";"+data.length);
			dwr.util.addOptions(sid,data,"userId","userName");	    
		}

		function editPlan () {
			if ($('#planForm').form('validate')) {
				$.get("userPerformance!judgePlan", $("#planForm").serializeArray(), function(data) {
					if (data.jsonReturn == "YES") {
						planForm.submit();
					}else {
						$.messager.alert('考核目标','考核目标不能重复制定','warning');
					} 
				});
			}
		}
		</script>
	</head>
	<body class="ui-lv2bg"> 
	    <div class="panel panel-default" id="searchResult" style="padding:2px;"> 
		<div class="ui-content-title" align="center"><H3>考核目标新增</H3></div>
	    <form id="planForm" name="planForm" action="userPerformance!editPlan" method="post"> 
			<div class="panel panel-default" id="searchResult" style="padding:2px;"> 
			<table class="ui-table-one" cellspacing="2" cellpadding="0" > 
			  <div class="panel-heading crm-table-title"><B>考核目标</B></div>	
			  <tr>
			    <td  class="ui-table-title">考核用户：</td>
			    <td colspan="3" >
						<select id="userSelection" name="userPlan.userId" class="form-control" class="easyui-validatebox w200" required="true">
	                    </select>
				</td>
			  </tr>
			  <tr>
			    <td  class="ui-table-title">方案名称：</td>
			    <td colspan="3" >
						<select id="planSelection" name="userPlan.planId" class="form-control" class="easyui-validatebox w200" required="true">
	                    </select>
				</td>
			  </tr>
			 
			  <tr>
			    <td  class="ui-table-title">年度：</td>
			        <td>
			              <select id="postSelection" class="form-control" name="userPlan.year" >
	                     <option value="2013">2013年</option>
		                 <option value="2014">2014年</option>
		                 <option value="2015">2015年</option>
		                 <option value="2016">2016年</option>
		                 </select>
			        </td>
			        <td id="seasonTitle" class="ui-table-title" style="display:none">季度：</td>
                    <td id="seasonSelect" style="display:none">
                        <select name="userPlan.season" class="form-control">
	                     <option value="1">1季度</option>
		                 <option value="2">2季度</option>
		                 <option value="3">3季度</option>
		                 <option value="4">4季度</option>
		                 </select>
                    </td>
                    <td id="monthTitle" class="ui-table-title" style="display:none">月度：</td>
                    <td id="monthSelect" style="display:none">
	                    <select name="userPlan.month" class="form-control">
	                     <option value="1">1月</option>
		                 <option value="2">2月</option>
		                 <option value="3">3月</option>
		                 <option value="4">4月</option>
		                 <option value="5">5月</option>
		                 <option value="6">6月</option>
		                 <option value="7">7月</option>
		                 <option value="8">8月</option>
		                 <option value="9">9月</option>
		                 <option value="10">10月</option>
		                 <option value="11">11月</option>
		                 <option value="12">12月</option>
		                 </select>
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
			                <td>权重</td>	 
			                <td>考核人</td>                  	
			                <td>目标值</td>	  
			                           
			              </tr>
			              
			        </table> 
              </div>
              <!--  按钮面板  -->
				<div class="crm-button-panel form-group ">
					<div class="col-sm-6">
						<a class="btn btn-primary col-sm-offset-9 col-sm-3" href="javascript:editPlan();">确定</a>
					</div>
					<div class="col-sm-6">
						<a class="btn btn-warning col-sm-3" href="userPerformance!planList">取消</a>
					</div>
				</div>
		</form>
		</div>
	</body>

</html>