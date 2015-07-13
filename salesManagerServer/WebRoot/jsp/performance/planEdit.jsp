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
		<title>考核方案新增</title>
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
			
			$("input[name=plan.postType]").change(function() {
				if ($('input[name=plan.postType]')[0].checked) {
					getPostList($("input[name=plan.postType]:checked").val());
				} else {
					getPostList($("input[name=plan.postType]:checked").val());
				}
			});

			$("#itemSelection").change(function(){
				//alert($("#itemSelection").find("option:selected").text());
				//alert($("#itemSelection").val());
				var itemId = $("#itemSelection").val();
				getSelectedItemDetail(itemId);
			});

			getPostList(1);
			getItemList();
		});
		
		function editPlan () {
			if ($('#planForm').form('validate')) {
				var total = statistics();
				if (total != 100) {
					$.messager.alert('信息','权重相加必须等于100%,目前：'+total+'%','error');
					return;
				}
				
				planForm.submit();
			}
		}

		function getPostList(postType){
			performancePlanService.getPostList(postType,fillPostSelection);
		}

		function fillPostSelection(data){
			dwr.util.removeAllOptions("postSelection");
			dwr.util.addOptions("postSelection",data,"positionId","name");		    
		}

		function getItemList() {
			performanceItemService.getItemListPage(null,fillItemSelection);
		}

		function fillItemSelection(data) {
			dwr.util.addOptions("itemSelection", ["--请选择--"]);
			dwr.util.addOptions("itemSelection",data,"itemId","itemName");
		}

		function getSelectedItemDetail(itemId) {
			performanceItemService.getItem(itemId,addSelectedItemToTable);
		}

		function addSelectedItemToTable(data) {
			addItem(data);
		}

		var index = 0;
		function addItem(data) {
			if(data.measurementUnit == null) {
				data.measurementUnit="";
			}
			if (!isSelectedItem(data.itemId)) {
				var htm = '<tr id="as_'+data.itemId+'">'+
	                '<td class="ui-table-heedtwo"><input type="hidden" name ="plan.planItemList['+index+'].itemId" value="'+data.itemId +'"/>'+data.itemName +'</td>'+
	                '<td>'+data.resourceName +'</td>'+
	                '<td>'+data.measurementUnit +'</td>'+
	                '<td><input type="text" name ="plan.planItemList['+index+'].specificWeight"/>%</td>'+
	                '<td><a href="javascript:deleteItem('+data.itemId+');">删除</a></td>'+
	              '</tr>';
				$("#itemsTable").append(htm);
				index++;
			} else {
				$.messager.alert('信息','不允许重复添加考核项目','error');
			}

		}
		
		function isSelectedItem(id) {
			var flag = false;
			$("#itemsTable tr").each(function(i){
				var trId = $(this).attr("id");
				var currentId = trId.substring(3,trId.length);
				//alert("currentId:"+currentId+";id:"+id);
				if (currentId == id) {
                    flag = true;
				}
			});
			return flag;
		}
		function deleteItem(id) {
			$("#itemsTable tr").each(function(i){
				var trId = $(this).attr("id");
				var selectedId= trId.substring(3,trId.length);
				if (selectedId == id) {
					$(this).remove();
				}
			});
		}

		function statistics() {
			var total = Number(0) ;
			$("#itemsTable tr").each(function(i){
				var weight = $(this).find("td").eq(3).find("input").attr("value");
				if (typeof(weight) != "undefined")
				{
				    //alert(weight);
				    total += Number(weight) ;
				}
			});
			
			return total;
		}
		</script>
	</head>
	<body class="ui-lv2bg"> 
		<div class="panel panel-default" id="searchResult" style="padding:2px;"> 
		<div class="ui-content-title" align="center"><H3>考核方案新增</H3></div>
	    <form id="planForm" name="planForm" action="performance!editPlan" method="post">
	    <div class="panel panel-default" id="searchResult" style="padding:2px;"> 
			  <div class="panel-heading crm-table-title"><B>考核方案</B></div>	
			<table class="ui-table-one" cellspacing="2" cellpadding="0" > 
			  <tr>
			    <td  class="ui-table-title">方案名称：</td>
			    <td colspan="3">
						<input name="plan.name" type="text" class="form-control" class="easyui-validatebox" required="true" validType="maxLength['方案名称',25]"/>
				</td>
			  </tr>
			  <tr>
			    <td  class="ui-table-title">方案周期：</td>
			    <td>
			    	<div >
			    		<div class="radio col-sm-3">
							<label><input type="radio" name="plan.cycleId" value="1"  checked="checked"/> 月度考核</label>
						</div>
						<div class="radio col-sm-3">
						  	<label><input type="radio" name="plan.cycleId" value="2"/> 季度考核</label>
						</div>
			    		<div class="radio col-sm-3">
							<label><input type="radio" name="plan.cycleId" value="3" /> 年度考核 </label>
						</div> 
			        </div>
			    </td>
			  </tr>
			  <tr>
			    <td  class="ui-table-title">岗位类型：</td>
			        <td>
			        	<div >
				    		<div class="radio col-sm-3">
								<label><input type="radio" name="plan.postType" value="1"  checked="checked"/> 行政岗位</label>
							</div>
							<div class="radio col-sm-3">
							  	<label><input type="radio" name="plan.postType" value="2"/> 业务岗位</label>
							</div> 
				        </div> 
			        </td>
			  </tr>
			  <tr>
			        <td  class="ui-table-title">适用岗位：</td>
                    <td>
	                    <select id="postSelection" name="plan.suitablePostId" class="form-control">
	                    </select>
                </td>
			  </tr>
			  <tr>
			    <td  class="ui-table-title">新增考核项目：</td>
                <td colspan="3" >
	                  <select id="itemSelection" name="newItem" class="form-control">
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
			                <td>单位</td>
			                <td>权重</td>	                	
			                <td>删除</td>		                
			              </tr>
			              <!-- 
			              <tr>
			                <td class="ui-table-heedtwo">摄像机</td>
			                <td>DSC-82342</td>
			                <td>-</td>
			                <td>台</td>
			                <td>删除</td>
			              </tr>
			              
			              <tr>
			                <td>合计：</td>
			                <td>&nbsp;</td>
			                <td>&nbsp;</td>
			                <td class="ui-table-money1">0</td>
			                <td>&nbsp;</td>
			              </tr>
			               -->
			        </table>
			        
			        </div>
              <!--  按钮面板  -->
				<div class="crm-button-panel form-group ">
					<div class="col-sm-6">
						<a class="btn btn-primary col-sm-offset-9 col-sm-3" href="javascript:editPlan();">确定</a>
					</div>
					<div class="col-sm-6">
						<a class="btn btn-warning col-sm-3" href="performance!planList">取消</a>
					</div>
				</div>
		</form>
		</div>
	</body>

</html>