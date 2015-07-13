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
<title>绩效查询</title>
<script type="text/javascript" language="javascript" src="script/common/pager/jquery.pager.js"></script>
<link type="text/css" rel="stylesheet" href="script/common/pager/Pager.css" />
<script type="text/javascript" language="javascript" src="script/common/pages.js"></script>
<script type="text/javascript">
		$(function() {
			if ('<s:property value="userPlan.plan.cycleId"/>' == -1) {
					$("#selectionId").html('');
			}else if ('<s:property value="userPlan.plan.cycleId"/>' == 1) {
					$("#selectionId").html('年：<select name="userPlan.year"><option value="2013" <s:if test="userPlan.year==2013">selected</s:if>>2013年</option><option value="2014" <s:if test="userPlan.year==2014">selected</s:if>>2014年</option>' +
					'<option value="2015" <s:if test="userPlan.year==2015">selected</s:if>>2015年</option><option value="2016" <s:if test="userPlan.year==2016">selected</s:if>>2016年</option></select>' +
					'月：<select name="userPlan.month"><option value="1" <s:if test="userPlan.month==1">selected</s:if>>1月</option><option value="2" <s:if test="userPlan.month==2">selected</s:if>>2月</option><option value="3" <s:if test="userPlan.month==3">selected</s:if>>3月</option>' +
					'<option value="4" <s:if test="userPlan.month==4">selected</s:if>>4月</option><option value="5" <s:if test="userPlan.month==6">selected</s:if>>5月</option><option value="6" <s:if test="userPlan.month==6">selected</s:if>>6月</option><option value="7" <s:if test="userPlan.month==7">selected</s:if>>7月</option>' +
					'<option value="8" <s:if test="userPlan.month==8">selected</s:if>>8月</option><option value="9" <s:if test="userPlan.month==9">selected</s:if>>9月</option><option value="10" <s:if test="userPlan.month==10">selected</s:if>>10月</option><option value="11" <s:if test="userPlan.month==11">selected</s:if>>11月</option>' +
					'<option value="12">12月</option></select>');
			}else if ('<s:property value="userPlan.plan.cycleId"/>' == 2) {
					$("#selectionId").html('年：<select name="userPlan.year"><option value="2013" <s:if test="userPlan.year==2013">selected</s:if>>2013年</option><option value="2014" <s:if test="userPlan.year==2014">selected</s:if>>2014年</option>' +
					'<option value="2015" <s:if test="userPlan.year==2015">selected</s:if>>2015年</option><option value="2016" <s:if test="userPlan.year==2016">selected</s:if>>2016年</option></select>' +
					'季度：<select name="userPlan.season"><option value="1" <s:if test="userPlan.season==1">selected</s:if>>1季度</option><option value="2" <s:if test="userPlan.season==2">selected</s:if>>2季度</option>' +
					'<option value="3" <s:if test="userPlan.season==3">selected</s:if>>3季度</option><option value="4" <s:if test="userPlan.season==4">selected</s:if>>4季度</option></select>');
			}else if ('<s:property value="userPlan.plan.cycleId"/>' == 3) {
					$("#selectionId").html('年：<select name="userPlan.year"><option value="2013" <s:if test="userPlan.year==2013">selected</s:if>>2013年</option><option value="2014" <s:if test="userPlan.year==2014">selected</s:if>>2014年</option>' +
					'<option value="2015" <s:if test="userPlan.year==2015">selected</s:if>>2015年</option><option value="2016" <s:if test="userPlan.year==2016">selected</s:if>>2016年</option></select>');
			}
			
			$("#cycleSelection").change(function(){
				var cycleId = $("#cycleSelection").val();
				if (cycleId == -1) {
					$("#selectionId").html('');
				}else if (cycleId == 1) {
					$("#selectionId").html('年：<select name="userPlan.year"><option value="2013">2013年</option><option value="2014">2014年</option>' +
					'<option value="2015">2015年</option><option value="2016">2016年</option></select>' +
					'月：<select name="userPlan.month"><option value="1">1月</option><option value="2">2月</option><option value="3">3月</option>' +
					'<option value="4">4月</option><option value="5">5月</option><option value="6">6月</option><option value="7">7月</option>' +
					'<option value="8">8月</option><option value="9">9月</option><option value="10">10月</option><option value="11">11月</option>' +
					'<option value="12">12月</option></select>');
				}else if (cycleId == 2) {
					$("#selectionId").html('年：<select name="userPlan.year"><option value="2013">2013年</option><option value="2014">2014年</option>' +
					'<option value="2015">2015年</option><option value="2016">2016年</option></select>' +
					'季度：<select name="userPlan.season"><option value="1">1季度</option><option value="2">2季度</option>' +
					'<option value="3">3季度</option><option value="4">4季度</option></select>');
				}else if (cycleId == 3) {
					$("#selectionId").html('年：<select name="userPlan.year"><option value="2013">2013年</option><option value="2014">2014年</option>' +
					'<option value="2015">2015年</option><option value="2016">2016年</option></select>');
				}
			});
		});
		
</script>
</head>
<body> 
	 <!-- 查询输入栏 -->
        <div class="crm-querybar panel panel-info ">
            <form id="planForm" name="planForm" class="form-horizontal" action="userPerformance!myScoreList" method="post">
	            <input type="hidden" id="pageCount" name="pageCount" value="${page.pageCount}" />
			  	<input type="hidden" id="pageNumber" name="pageNumber" value="${page.start}" />
			  	<input type="hidden" id="totalCount" name="totalCount" value="${page.totalCount}" />
	            <div class="form-group">
		      		<div class="col-sm-3">	  
		                <input id="responseId" name="userPlan.userId"  type="text" class="form-control" placeholder="用户"/>
		            </div> 
		            <div class="col-sm-3">	 
		                <select name="userItem.userPlan.plan.cycleId" class="form-control">
		                 <option value="-1">考核周期</option>
		                 <option value="1" <s:if test="userPlan.plan.cycleId==1">selected</s:if>>月度</option>
		                 <option value="2" <s:if test="userPlan.plan.cycleId==2">selected</s:if>>季度</option>
		                 <option value="3" <s:if test="userPlan.plan.cycleId==3">selected</s:if>>年度</option>
		                </select>
		            </div> 
		            <!--提交按钮	-->
				    <div class="col-sm-3" align="right">
			      		<button type="submit" class="btn btn-primary">查询</button>			
			        </div>	
			        <div id="selectionId" class="fl"></div>
	            </div>
           </form>
     </div>     
 <!--  查询结果列表 -->
	<div class="panel panel-default" id="searchResult" style="padding:2px;">
	     <div class="panel-heading crm-table-title"><B>绩效方案查询记录</B></div>
		 <table  class="table table-hover" cellspacing="0" cellpadding="0" >   
       		  <thead>	   
			    <th>用户</th>  
			    <th>方案名称</th>
			    <th>考核周期</th>  
			    <th>考核总成绩</th> 
			    <th>查看</th> 
			  </thead>
              <s:iterator id="up" value="userPlanList" status="dl">
              <tr class="ui-table-style1-tr2">
                <td>${up.userName }</td>
                <td>${up.plan.name }</td>
                <td>
                ${up.year }年
                <s:if test="season!=null">
                    ${up.season }季度
                </s:if>
                <s:if test="month!=null">
                    ${up.month }月
                </s:if>
                </td>
                <td>${up.totalScore }</td>
                <td><a href="userPerformance!myScoreDetail?userPlanId=${up.performancePlanId}">查看</a></td>
                
                
              </tr> 
              </s:iterator>
              
            </table>
            </div>
<div class="ui-pagelist mt10 mb50">	
	<div style="float: right;" id="pager"></div>
</div>
	<script type="text/javascript"> 
		function search(){
			$("#pageNumber").val("1");
			pagesearch();
		}
		
		function pagesearch(){
			planForm.submit();
		}
		
		function deleteSearch() {	//清除查询条件
			window.location.href = basePath + 'userPerformance!myScoreList';
		}
		
		/**
		$('#responseId').combotree
		   ({ url: 'sUser!jsonUser',
		       valueField: 'userId',
		       textField: 'userId',
		       required: false,
		       editable: false,
		       onClick: function (node) { 
		            //JJ.Prm.GetDepartmentUser(node.id, 'selUserFrom'); 
		       }, //全部折叠
		       onLoadSuccess: function (node, data) {
		            //$('#selDepartmentFrom').combotree('tree').tree("collapseAll");   
		       }   
		});  
		 
		$('#responseId').combotree('setValue', '<s:property value="userPlan.userId"/>');
		**/  
	</script>
</body>
</html>