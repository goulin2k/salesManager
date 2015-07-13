<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>绩效项目评定</title>
<script type="text/javascript" src="script/common/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" language="javascript" src="<%=basePath %>script/common/pager/jquery.pager.js"></script>
<link type="text/css" rel="stylesheet" href="<%=basePath %>script/common/pager/Pager.css" />
<script type="text/javascript" src="<%=basePath %>script/common/pages.js"></script>
<script type="text/javascript"> 
	function search(){
		$("#pageNumber").val("1");
		pagesearch();
	}
	
	function pagesearch(){
		itemForm.submit();
	}

	function deleteSearch() {	//清除查询条件
		window.location.href = basePath + 'userPerformance!itemScoreList';
	}
</script>
</head>
<body class="ui-lv2bg">
<!-- 标准工具栏 -->
	<nav class="navbar navbar-default crm-toolbar" role="navigation">
		<ul class="nav navbar-nav"> 
	    	<li class="active"><a href="userPerformance!planScoreList"><span class="glyphicon glyphicon-refresh"></span>&nbsp;清空查询条件</a></li>
	    </ul>
	</nav>  
	<!-- 查询输入栏 -->
         <div class="crm-querybar panel panel-info ">
	            <form id="itemForm" name="itemForm" class="form-horizontal" action="userPerformance!itemScoreList" method="post">
		            <input type="hidden" id="pageCount" name="pageCount" value="${page.pageCount}" />
				  	<input type="hidden" id="pageNumber" name="pageNumber" value="${page.start}" />
				  	<input type="hidden" id="totalCount" name="totalCount" value="${page.totalCount}" />
		            <div class="form-group">
			      		<div class="col-sm-2">	 
			                <select name="userItem.userPlan.plan.cycleId" class="form-control">
			                 <option value="-1">考核周期</option>
			                 <option value="1" <s:if test="userItem.userPlan.plan.cycleId==1">selected</s:if>>月度</option>
			                 <option value="2" <s:if test="userItem.userPlan.plan.cycleId==2">selected</s:if>>季度</option>
			                 <option value="3" <s:if test="userItem.userPlan.plan.cycleId==3">selected</s:if>>年度</option>
			                </select>
			            </div> 
			            <!--提交按钮	-->
					    <div class="col-sm-offset-6 col-sm-3">
				      		<button type="submit" class="btn btn-primary">查询</button>			
				        </div>	
		            </div>
	           </form>
	     </div>   
<div class="panel panel-default" id="searchResult" style="padding:2px;">
     <div class="panel-heading crm-table-title"><B>绩效方案查询记录</B></div>
		<table  class="table table-hover" cellspacing="0" cellpadding="0" >   
       		  <thead>	   
			    <th>用户名</th>  
			    <th>方案名称</th>
			    <th>考核周期</th>  
			    <th>考核项目</th> 
			    <th>评定</th> 
			  </thead>
              <s:iterator id="ui" value="userItemList" status="dl">
              <tr class="ui-table-style1-tr2">
                <td>${ui.userPlan.userName }</td>
                <td>${ui.userPlan.plan.name }</td>
                <td>
                ${ui.userPlan.year }年
                <s:if test="userPlan.plan.cycleId==2">
                    ${ui.userPlan.season }季度
                </s:if>
                <s:if test="userPlan.plan.cycleId==1">
                    ${ui.userPlan.month }月
                </s:if>
                </td>
                <td>${ui.item.itemName }</td>
                <td>
                <s:if test="score==null || score == ''">
                <a href="userPerformance!itemScore?userItemId=${ui.performanceItemId}">评定</a>
                </s:if>
                <s:else>
                <a href="userPerformance!itemScore?userItemId=${ui.performanceItemId}">已评定</a>
                </s:else>
                </td>
                
                
              </tr> 
              </s:iterator>
              
            </table>
            </div>
<div class="ui-pagelist mt10 mb50">	
	<div style="float: right;" id="pager"></div>
</div>
</body>
</html>