<%@ page language="java" import="java.util.*,org.jbpm.api.*,org.jbpm.api.task.*,com.sales.model.*,com.sales.common.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>车辆维修报销管理</title>
<script type="text/javascript" language="javascript" src="script/common/pager/jquery.pager.js"></script>
<link type="text/css" rel="stylesheet" href="script/common/pager/Pager.css" />
<link type="text/css" href="<%=basePath %>skin/Default/css/jquery-ui-1.10.0.custom.css" rel="stylesheet"  />
<script type="text/javascript" language="javascript" src="script/common/jquery-ui-1.10.0.custom.min.js"></script>
<script type="text/javascript">
			var basePath = "<%=path%>/";
			var nSearchPanelHeight = 100;				//定义查询框的高度（px）
			$(document).ready(function(){
				$("#pager").pager({
						    pagenumber:${cr.pageNo},                         /* 表示初始页数 */
						    pagecount:${cr.pageCount},                      /* 表示总页数 */
						    totalCount:${cr.totalCount},
						    buttonClickCallback:PageClick                     /* 表示点击分页数按钮调用的方法 */                  
				});
				
			});
	
			/*
		        PageClick = function(pageclickednumber) {}部分
		        PageClick，表示自定义点击分页数时的function方法，如：function(pageclickednumber){}
		        jQuery插件JQuery Pager分页器只需要起始页数pagenumber，最大页数pagecount，
		             点击页数时的调用buttonClickCallback的 function方法就可实现javascript分页功能，
		             实际应用中只需对PageClick方法进行简单修改就可使用，如将pagenumber和 pagecount设为变量，
		             可通过GET的方法进行页数值传递，JQuery Pager就可实现javascript分页功能
		    */
		    PageClick = function(pageclickednumber) {
				$("#pager").pager({
				    pagenumber:pageclickednumber,                 /* 表示启示页 */
				    pagecount:${cr.pageCount},                  /* 表示最大页数pagecount */
				    buttonClickCallback:PageClick                 /* 表示点击页数时的调用的方法就可实现javascript分页功能 */            
				});
				
				$("#pageNumber").val(pageclickednumber);          /* 给pageNumber从新赋值 */
				/* 执行Action */
				pagesearch();
			}
			
	function search(){
		$("#pageNumber").val("1");
		pagesearch();
	}
	
	function pagesearch(){
		crForm.submit();
	}

	function deleteSearch() {	//清除查询条件
		window.location.href = basePath + 'car!crList';
	}
	$(function() {
		$( "#reimbursementTimeId" ).datepicker({
    		dateFormat: "yy-mm-dd"
        }); 
	});
</script>
</head>
<body class="ui-lv2bg">
    <nav class="navbar navbar-default" role="navigation">
	    <ul class="nav navbar-nav">
	    	<li class="active"><a href="car!toEditCr"><span class="glyphicon glyphicon-star"></span>&nbsp;新增</a></li>
	    </ul>
	    <form class="navbar-form navbar-right form-inline" role="search" id="crForm" name="crForm" action="car!crList" method="post">
	      <div class="form-group">
	             <input type="text" id="reimbursementTimeId" name="cr.reimbursementTime" value="<s:date name="cr.reimbursementTime" format="yyyy-MM-dd" />" class="form-control"  placeholder="报销日期" />
	             <input type="hidden" id="pageNumber" name="cr.pageNo" value="${cr.pageNo}" />
	      </div>
	      	<button type="submit"  class="btn btn-primary">查询</button>
	    </form>
    </nav>

	 <div class="panel panel-default" id="searchResult" style="padding:2px;">
       <div class="panel-heading crm-table-title"><B>车辆维修报销查询列表</B></div>
		<table  class="table table-hover" cellspacing="0" cellpadding="0" >
              <thead>
                <th>单据号</th>
                <th>报销日期</th>
                <th>车牌号</th>
                <th>维修费用(元)</th>
                <th>维修厂电话</th>
                
                <th>状态</th>
                <th>操作</th>
              </thead>
       
              <s:iterator id="sl" value="crList" status="dl">
              <tr class="ui-table-style1-tr2">
                <td>${sl.carReimbursementCode }</td>
                <td><s:date name="reimbursementTime" format="yyyy-MM-dd" /></td>
                <td>${sl.plateNumber }</td>
                <td>${sl.reimbursementSum }</td>
                <td>${sl.repairFactoryPhone }</td>
                
                
                <% 
                SCarRepairReimbursement cr = (SCarRepairReimbursement)request.getAttribute("sl");
                ProcessEngine processEngine = Configuration.getProcessEngine();
            	TaskService taskService = processEngine.getTaskService();
            	ExecutionService executionService = processEngine.getExecutionService();
            	String executionId = "carRepairRei."+cr.getCarReimbursementId();
            	ProcessInstance processInstance = executionService.findProcessInstanceById(executionId);
            	String activityName = "";
            	if (cr.getStatus().intValue() == 2) {
            		activityName = Constants.PROCESS_TEMP;
            	}
            	else if (cr.getStatus().intValue() == 1) {
            		activityName = Constants.PROCESS_END;
            	} else {
            		if (processInstance != null) {
            			String tempstr = processInstance.findActiveActivityNames().toString();
                		activityName = tempstr.substring(1,tempstr.length()-1);  
            		}  		
            	}
                %>
                <td><%=activityName%></td>
                <%if("暂存".equals(activityName)) { %>
                    <td><a href="car!toEditCr?crId=${sl.carReimbursementId }">编辑</a>  <a href="car!deleteCr?crId=${sl.carReimbursementId }">删除</a></td>
                <%}else if("车辆维修报销".equals(activityName)) {%>
                	<td><a href="car!toReSubmitCr?crId=${sl.carReimbursementId }">申请</a></td>
                <% } else {%>
                	<td><a href="car!crDetail?crId=${sl.carReimbursementId }">查看</a></td>
                <% }%>
              </tr> 
              
              </s:iterator>
              
            </table>
            </div>
  
    <div class="ui-pagelist" id="pager"></div>
    <div class="clear"></div>
	
</body>
</html>