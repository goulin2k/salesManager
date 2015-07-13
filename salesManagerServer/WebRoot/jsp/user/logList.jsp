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
<title>日志记录</title>

<script type="text/javascript" language="javascript" src="<%=basePath %>script/common/pager/jquery.pager.js"></script>
<link type="text/css" rel="stylesheet" href="<%=basePath %>script/common/pager/Pager.css" />
<script type="text/javascript">
			var basePath = "<%=path%>/";
			$(document).ready(function(){
				$("#pager").pager({
						    pagenumber:${log.pageNo},                         /* 表示初始页数 */
						    pagecount:${log.pageCount},                      /* 表示总页数 */
						    totalCount:${log.totalCount},
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
				    pagecount:${log.pageCount},                  /* 表示最大页数pagecount */
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
		logForm.submit();
	}
	
	$(function() {
			$( "#logTimeId" ).datepicker({
	    		dateFormat: "yy-mm-dd"
	    	});  
		});
</script>
</head>
<body class="ui-lv2bg">
    <nav class="navbar navbar-default" role="navigation">
	    <form class="navbar-form navbar-right form-inline" role="search" id="logForm" name="logForm" action="log!logList" method="post">
	      <div class="form-group">
	      
	          <div class="col-sm-5">	
	             <input type="text" id="logTimeId" name="log.logTime" value="<s:date name="log.logTime" format="yyyy-MM-dd" />" class="form-control"  placeholder="记录日期" />
	          </div>
	          <div class="col-sm-5">	
	             <s:select
	                        id="typeId"
	                        tooltip="日志类型"
	                        name="log.typeId"
	                        emptyOption="false"
	                        headerKey="0" headerValue="选择日志类型" 
	                        list="#{'1':'业务操作','2':'用户登录' }"
	                        cssClass="form-control"/>
	          </div>
	            <input type="hidden" id="pageNumber" name="log.pageNo" value="${log.pageNo}" /> <input type="hidden" id="pageNumber" name="ea.pageNo" value="${ea.pageNo}" />
	      </div>
	      	<button type="submit"  class="btn btn-primary">查询</button>
	    </form>
    </nav>

	 <div class="panel panel-default" id="searchResult" style="padding:2px;">
       <div class="panel-heading crm-table-title"><B>日志查询列表</B></div>
		<table  class="table table-hover" cellspacing="0" cellpadding="0" >
              <thead>
                <th>标题</th>
                <th>类型</td>
                <th>记录时间</td>
                <th>操作用户</td>
                <th>操作内容</td>
              </thead> 
              <s:iterator id="slog" value="logList" status="dl">
              <tr class="ui-table-style1-tr2">
                <td>${slog.title }</td>
                <td>${slog.typeName }</td>
                <td><s:date name="logTime" format="yyyy-MM-dd hh:mm" /></td>
                <td>${slog.userName }</td>
                <td>${slog.logContent }</td>
                
              </tr> 
              </s:iterator>
              
            </table>
            </div>
  
    <div class="ui-pagelist" id="pager"></div>
    <div class="clear"></div>
	
</body>
</html>