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
<title>消息列表</title>

<script type="text/javascript" language="javascript" src="<%=basePath %>script/common/pager/jquery.pager.js"></script>
<script type="text/javascript" src="<%=basePath %>script/common/pages.js"></script>

<link type="text/css" rel="stylesheet" href="<%=basePath %>script/common/pager/Pager.css" />

<script type="text/javascript">
			var basePath = "<%=path%>/";
			var nSearchPanelHeight = 120;				//定义查询框的高度（px）
			$(document).ready(function() {
				$('#modal').on('hidden.bs.modal', function (e) {
					$("#searchForm").submit();
				});
				
				$("#clear").click(function(checkEvent){
					$("#searchForm").attr("action", "info!setAllReaded");
					$("#searchForm").submit();
			    });
				
			});
	
	
	
	function deleteInfo(url) {
		$("#searchForm").attr("action", url);
		$("#searchForm").submit();
	}
	
	function gotoAboutUrl(imageurl, infoId) {
		
		if(imageurl == '') {
			return;
		}else {
		    var url = basePath + imageurl + "&modal=true";
		    var winHeight = $(window).height();
			var winWidth = $(window).width();
		    $.ajax({
		        type: "POST",
		        url: "info!jsonUpdateInfo",
		        data: "infoId=" + infoId,
		        dataType: 'json',
		        cache: false,
		        async: true,
		        success: function(data) {
		            if (data == "0") {
		            }
		        }
		    });
		    url = encodeURI(url);
		    
            openDialog(url);
		    
		}
		
		
				 
	}
</script>
</head>
<body class="ui-lv2bg">
	<div class="crm-querybar panel panel-info ">
	    <form class="form-horizontal" role="search" 
	    	id="searchForm" name="searchForm" action="info!infoList" method="post">
	      <input type="hidden" id="pageCount" name="pageCount" value="${page.pageCount}" />
	      <input type="hidden" id="pageNumber" name="pageNumber" value="${page.start}" />
	      <input type="hidden" id="totalCount" name="totalCount" value="${page.totalCount}" /> 
	      <div class="form-group">
	          <!-- <div class="col-sm-4">	
	             <input type="text" id="addTime" name="info.addTime" 
	             	value="<s:date name="info.logTime" format="yyyy-MM-dd" />" class="form-control"  placeholder="记录日期" />
	          </div>
	           -->
	          <div class="col-sm-2">	
	             <s:select
	                        id="status"
	                        tooltip="读取状态"
	                        name="info.status"
	                        emptyOption="false"
	                        
	                        list="#{'1':'未读消息 ','0':'已读消息 '}"
	                        cssClass="form-control"/>
	          </div>
	          <div class="col-sm-3">	
	             <s:select
	                        id="type"
	                        tooltip="消息类型"
	                        name="info.type"
	                        emptyOption="false"
	                        headerKey="-1" headerValue="全部消息类型" 
	                        list="#{'0':'未知消息类型','1':'销售活动','2':'销售计划','3':'流程审批','4':'销售询价',
	                        '5':'销售报价','6':'销售订单','7':'采购申请' ,'8':'采购订单' ,'9':'采购入库',
	                        '10':'发货通知','11':'销售出库','12':'销售发票'}"
	                        cssClass="form-control"/>
	          </div>
	            
	      		<button type="submit" onclick="$('#pageNumber').val(1);" class="btn btn-primary">查询</button>
	      		<a class="btn btn-danger" id="clear">全部已读</a>    
	      </div>
	      	
	    </form>
    </div>
    
	<div class="panel panel-default" id="searchResult" style="padding:2px;">
     <div class="panel-heading crm-table-title"><B>消息查询列表</B></div>
		<table class="table table-hover" cellspacing="0" cellpadding="0" >
              <thead>
              	<input type="hidden" id="pageNumber" name="pageNumber" value="${page.start}" />
                <th>消息标题</th>
                <th>消息类型</th>
                <th>消息状态</th>
                <th>消息时间</th>
                <th>相关信息</th>
               <th>查看</th>
               <th>删除</th>
              </thead>
        <!--      =================表格循环===============-->
              <s:iterator id="temp" value="infoList" status="dl">
              <tr class="ui-table-style1-tr2">
                <td>${temp.title }</td>
                <td>${temp.typeName }</td>
                <td>${temp.statusName }</td>
                <td><s:date name="#temp.addTime" format="yyyy-MM-dd HH:mm" /></td>
                <td>
	                <s:if test="#temp.imageUrl != null">
	                	<a href="javascript:gotoAboutUrl('${temp.imageUrl}',${temp.informationId})">相关信息</a>
	                </s:if>
	                <s:else>
	               		 无相关信息
	                </s:else> 
                </td>
                <td><a href="javascript:openModal(${temp.informationId})">查看</a></td>
                <td><a href="javascript:deleteInfo('info!deleteInfo?infoId=${temp.informationId}');">删除</a></td>
              </tr> 
              </s:iterator>
          <!--     =================表格循环over===============-->
          
            </table>
            </div>
   <!--      =================页=、==============-->

<!-- <table><tr><td>
    	<p>共&nbsp;${page.totalCount}&nbsp;条记录</p>
    	    <div style="float: right;" id="pager"> fff</div>
</td></tr></table> -->
<div class="ui-pagelist" id="pager">
</div>


</body>
</html>