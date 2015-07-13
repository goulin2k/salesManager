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
		<title>考核项目新增</title>
		<link href="skin/Default/css/base.css" rel="stylesheet" type="text/css" />
		<link href="skin/Default/css/global.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=basePath %>script/common/jquery-1.4.2.min.js" ></script>
		<script type="text/javascript" src="<%=basePath %>script/common/easyui-1.3.5/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="script/common/easyui/validate.js"></script>
		<script type="text/javascript" src="script/common/easyui/locale/easyui-lang-zh_CN.js"></script>
		<script src='dwr/interface/performancePlanService.js'></script>
		<script src='dwr/engine.js'></script>
		<script src='dwr/util.js'></script>
		<script type="text/javascript">
		$(function() {
			$("input[name=item.resourceId]").change(function() {
				if ($('input[name=item.resourceId]')[0].checked) {
					clearSqlData();
					$('#sqltr').hide();
				} else {
					getSqlList();
					$('#sqltr').show();
				}
			});
			$("input[name=item.postType]").change(function() {
				if ($('input[name=item.postType]')[0].checked) {
					getPostList($("input[name=item.postType]:checked").val());
				} else {
					getPostList($("input[name=item.postType]:checked").val());
				}
			});

			getPostList(1);
		});
		
		function editItem () {
			if ($("#itemForm").form('validate')) {
				itemForm.submit();
			}
		}

		function getSqlList(){
			performancePlanService.getSqlList(fillSqlSelection);
		}

		function fillSqlSelection(data){
		    dwr.util.addOptions("sqlSelection",data,"sqlId","name");		    
		} 

		function clearSqlData() {
			dwr.util.removeAllOptions("sqlSelection");
			$('#measuretxt').val('');
		}
		
		function getPostList(postType){
			performancePlanService.getPostList(postType,fillPostSelection);
		}

		function fillPostSelection(data){
			dwr.util.removeAllOptions("postSelection");
		    dwr.util.addOptions("postSelection",data,"positionId","name");		    
		}
		
		</script>
	</head>
	<body class="ui-lv2bg">
		<div class="panel panel-default" id="searchResult" style="padding:2px;">   
	    <div class="ui-content-title" align="center"><H3>考核项目新增</H3></div>
	    <form id="itemForm" name="itemForm" action="performance!editItem" method="post"> 
			<div class="panel panel-default" id="searchResult" style="padding:2px;"> 
			  <div class="panel-heading crm-table-title"><B>考核目标</B></div>	
			<table class="ui-table-one" cellspacing="2" cellpadding="0"> 
				<tr>
					<td class="ui-table-title">
						项目名称：
					</td>
					<td colspan="3" >
						<input name="item.itemName" type="text" class="form-control" class="easyui-validatebox w200" required="true" validType="maxLength['项目名称',25]"/>
					</td>
				</tr>
                <tr>
			        <td  class="ui-table-title">来源：</td>
			        <td>
			        	<div >
				    		<div class="radio col-sm-3">
								<label><input type="radio" name="item.resourceId" value="1" checked="checked"/> 非量化指标</label>
							</div>
							<div class="radio col-sm-3">
							  	<label><input type="radio" name="item.resourceId" value="2"/> 量化指标</label>
							</div> 
				        </div> 
			        </td>
			    </tr>
			    <tr id="sqltr" style="display:none">
				    <td  class="ui-table-title">量化指标选择：</td>
                    <td class="ui-table-select-s"><select id="sqlSelection" class="form-control" name="item.sqlId"></select></td>
                    <td class="ui-table-title">度量单位：</td>
				    <td class="ui-table-input-s"><input id="measuretxt" class="form-control" name="item.measurementUnit" type="text" /></td>
				</tr>
				<tr>
					<td  class="ui-table-title">岗位类型：</td>
			        <td  class="color555"> 
			              <div >
				    		<div class="radio col-sm-3">
								<label><input type="radio" name="item.postType" value="1"  checked="checked"/> 行政岗位</label>
							</div>
							<div class="radio col-sm-3">
							  	<label><input type="radio" name="item.postType" value="2"/> 业务岗位</label>
							</div> 
				        </div> 
			        </td>
			        <td  class="ui-table-title">评价岗位：</td>
                    <td>
	                    <select id="postSelection" class="form-control" name="item.accessPostId">
	                    </select>
                    </td>
				</tr>

				<tr>
					<td class="ui-table-title">
						项目说明：
					</td>
					<td colspan="3" class="ui-table-textarea">
						<textarea rows="6" name="item.comment" class="form-control" class="easyui-validatebox" validType="maxLength['项目说明',100]"></textarea>
					</td>
				</tr>
			</table>
			
              <!--  按钮面板  -->
				<div class="crm-button-panel form-group ">
					<div class="col-sm-6">
						<a class="btn btn-primary col-sm-offset-9 col-sm-3" href="javascript:editItem();">确定</a>
					</div>
					<div class="col-sm-6">
						<a class="btn btn-warning col-sm-3" href="performance!itemList">取消</a>
					</div>
				</div> 
			</div>
		</form>
		</div>
	</body>

</html>