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
<title>客户列表</title>

<script type="text/javascript" language="javascript" src="<%=basePath %>script/common/pager/jquery.pager.js"></script>
<link type="text/css" rel="stylesheet" href="<%=basePath %>script/common/pager/Pager.css" />
<script type="text/javascript">
			var basePath = "<%=path%>/";
			$(document).ready(function(){
				$("#pager").pager({
						    pagenumber:${page.start},                         /* 表示初始页数 */
						    pagecount:${page.pageCount},                      /* 表示总页数 */
						    totalCount:${page.totalCount},						/* 记录总数		*/ 
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
				    pagecount:${page.pageCount},                  /* 表示最大页数pagecount */
				    totalCount:${page.totalCount},						/* 记录总数		*/ 
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
		var pageNumber = $("#pageNumber").val();
		var url= basePath+"customer!selectCustomerList?pageNumber="+pageNumber;
		url = encodeURI(url);
		window.location.href=url;
	}
</script>
</head>
<body class="ui-lv2bg">
	<div class="ui-content-title"><p><img src="skin/Default/images/ui-content-ico/1.png" />预警客户列表</p></div>
    <div class="clear"></div>
    
    
    
	 <div class="ui-table-style1">
		<table  cellspacing="0" cellpadding="0" >
              <tr class="ui-table-style1-tr1">
              <input type="hidden" id="pageNumber" name="pageNumber" value="${page.start}" />
                <td class="td2">id</td>
                <td class="td3">客户名称</td>
                <td class="td4">客户全名</td>
                <td class="td5">时间</td>
               <td>查看详情</td>
              </tr>
        <!--      =================表格循环===============-->
              <s:iterator id="customer" value="alarmCustomerList" status="dl">
              <tr class="ui-table-style1-tr2">
                <td>${customer.customerId }</td>
                <td>${customer.fname }</td>
                <td>${customer.ffullname }</td>
                <td>2012/12/31 12:43:57</td>
                <td><a href="customer!getCustomerInfoById?customerId=${customer.customerId }">查看详情</a></td>
              </tr> 
              </s:iterator>
          <!--     =================表格循环over===============-->
          
            </table>
            </div>
	
</body>
</html>