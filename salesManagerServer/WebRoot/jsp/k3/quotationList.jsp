<%@ page language="java" import="com.sales.common.Constants" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%> 

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<htmlxmlns="http://www.w3.org/1999/xhtml">
<head>
<title>询价单列表 </title>

<script type="text/javascript" language="javascript" src="<%=basePath %>script/common/pager/jquery.pager.js"></script>
<link type="text/css" rel="stylesheet" href="<%=basePath %>script/common/pager/Pager.css" />
<script type="text/javascript" src="<%=basePath %>script/common/pages.js"></script>
<script type="text/javascript">
	var basePath = "<%=path%>/";
	var winHeight = $(window).height();
	var winWidth = $(window).width();
	
	$(function(){
		$( "#startTime" ).datepicker({
    		dateFormat: "yy-mm-dd"
	    });
	    $( "#endTime" ).datepicker({
	    	dateFormat: "yy-mm-dd"
	    });
	});
	
	function getCustomer(){ 
		var array = window.showModalDialog("<%=basePath %>/customer!customerOpenWindow", "", "dialogWidth:800px; dialogHeight:530px; status:no;help:no");
		//for chrome
	    if (array == undefined) {
	    	array = window.returnValue;
	    }
		$("#customerId").val(array[0]);
		$("#customerName").val(array[1]);  
	}
	
	function getProducts(){ 
		var array = window.showModalDialog("<%=basePath %>/product!list", "", "dialogWidth:800px; dialogHeight:500px; status:no;help:no");
		//for chrome
	    if (array == undefined) {
	    	array = window.returnValue;
	    }
		$("#productCode").val(array[0]);
		$("#productName").val(array[1] + array[2]);  
	}
	
	function addOrfq(quotationId) {
		var returnValue = window.showModalDialog("<%=basePath %>/quotation!toOrfq?quotation.quotationId=" + quotationId, window, 
				"dialogWidth:" + winWidth + "px; dialogHeight:" + winHeight + "; status:no;help:no;resizable:yes");
		//for chrome
		if (!returnValue) {
		    returnValue = window.returnValue;
		}
		
		window.location.reload();
	}
	
	function showOrfq(interId) {
		var returnValue = window.showModalDialog("<%=basePath %>/orfq!showSorfq?orfq.fInterID=" + interId, 
				window, "dialogWidth:" + winWidth + "px; dialogHeight:" + winHeight + "; status:no;help:no;resizable:yes");
		//for chrome
		if (!returnValue) {
		    returnValue = window.returnValue;
		}
		
		//window.location.reload();
	}
	
	
</script>
</head>

<body class="ui-lv2bg">
	<!-- 标准工具栏 -->
<nav class="navbar navbar-default crm-toolbar" role="navigation">
	<ul class="nav navbar-nav">
    	<li class="active"><a href="quotation!editNew"><span class="glyphicon glyphicon-star"></span>&nbsp;新增</a></li>
    	<li class="active"><a href="quotation!index"><span class="glyphicon glyphicon-refresh"></span>&nbsp;清空查询条件</a></li>
    </ul>
</nav>

<div class="crm-querybar panel panel-info ">
	<s:form id="searchForm" name="searchForm" action="quotation!index" method="post"
			  cssClass="form-horizontal" style="margin-bottom: 2px;">
		<input type="hidden" id="pageCount" name="pageCount" value="${page.pageCount}" />
		<input type="hidden" id="pageNumber" name="pageNumber" value="${page.start}" />
		<input type="hidden" id="totalCount" name="totalCount" value="${page.totalCount}" />
		<div class="form-group">
		   	<div class="col-sm-4">	
		   		<input type="hidden" id="customerId" name="quotation.customerId" 
		   			value="<s:property value="quotation.customerId"/>" />
		   		<input type="text" class="form-control" id="customerName" placeholder="选择客户"
		   			name="quotation.customerName" onClick="getCustomer();" value="<s:property value="quotation.customerName"/>" />
		    </div>
		    <div class="col-sm-4">
		    	<input type="hidden" id="productCode" name="quotation.productCode" 
		    		value="<s:property value="quotation.productCode"/>" />
		    	<input type="text" class="form-control" id="productName" name="quotation.productName" placeholder="选择物料"
		    		onClick="getProducts();" value="<s:property value="quotation.productName"/>" />
		    </div>
		    <div class="col-sm-4">
		    	<input type="text" id="quotationUserName" name="quotation.quotationUserName" 
		    		value="<s:property value="quotation.quotationUserName"/>" class="form-control" placeholder="业务员"/>
		    </div>
		 </div>
		 <div class="form-group">
		 	<div class="col-sm-4" >	 
		     	<input class="form-control" placeholder="开始日期" id="startTime" name="quotation.startTime" 
		     		value="<s:property value="quotation.startTime"/>"/>
		     </div>  
		     <div class="col-sm-4" >   
		     	<input class="form-control" placeholder="结束日期" id="endTime" name="quotation.endTime" 
		     		value="<s:property value="quotation.endTime"/>"/>
		     </div>
		   	<!--提交按钮	-->
		   	<div class="col-sm-4" align="right">
	     		<button type="submit" class="btn btn-primary">查询</button>			
	     	</div>
	     </div>
	</s:form>
</div>


 <div class="panel panel-default" id="searchResult" style="padding:2px;">
     <div class="panel-heading crm-table-title"><B>销售询价单记录</B></div>
	<table  class="table table-hover" cellspacing="0" cellpadding="0" >
	  <thead>	  
	    <th >询价单号</th>
	    <th >询价时间</th>
	    <th >购货单位</th> 
	    <th >询价人</th> 
	    <th>是否开票</th>
	    <th>物料</th>
	    <th>数量</th>
	    <th>回复单价</th>
	    <th>操作</th>
	  </thead>
		<!--      =================表格循环===============-->
	  <s:set name="oldCode" value="" scope="page"></s:set>
	  <s:iterator value="quotationList" status="dl" var="qo">	     
		<tr class="ui-table-style1-tr2">
		  <s:if test="quotationCode == #oldCode">
		  	<td></td>
		  	<td></td>
		  	<td></td>
		  	<td></td>
		  	<td></td>
		  </s:if>
		  <s:else>
		  	<td><s:property value="quotationCode"/></td>
		  	<td><s:date name="quotationTime"  format="yyyy-MM-dd"/></td>
		  	<td><a href="#" title="<s:property value='customerName'/>" class="easyui-tooltip">
		  		<s:if test="customerName.length()>6"><s:property value="customerName.substring(0,6)+' ...'"/></s:if>
		  		<s:else><s:property value="customerName"/></s:else> </a></td>
		  	<td><s:property value="quotationUserName"/></td>  
		  	<td> <s:if test="issued==1">开票</s:if>
		  		<s:else>不开票</s:else></td>  
		  </s:else>
		  
		  <td><s:property value="productName"/></td>
		  <td><s:property value="num"/></td>
		  <td><s:property value="price"/></td>
		 
		  <s:if test="quotationCode == #oldCode">
		  	<td></td>
		  </s:if>
		  <s:else>
			  <td><a href="<%=basePath %>quotation!show?quotation.quotationId=<s:property value="quotationId"/>"> 查看 </a>
			  <s:if test="status==0">
			      <s:if test="roleId==7">	
				  	<a href="<%=basePath %>quotation!reply?quotation.quotationId=<s:property value="quotationId"/>"> 回复 </a>
				  </s:if>
			  </s:if>
			  <s:if test="status==1">	
				  <s:if test="roleId!=7 && orfqId == null">	
				  	<a href="javascript:addOrfq(<s:property value="quotationId"/>)"> 生成报价单 </a>
				  </s:if><s:elseif test="orfqId != null">
				  	<a href="javascript:showOrfq(<s:property value="orfqId"/>)"> 报价单 </a>
				  </s:elseif>
				  
			  </s:if>
			  </td>
		  </s:else>
		</tr>
		<s:set name="oldCode" value="quotationCode"></s:set>
	  </s:iterator>
	</table>
</div>

<div class="ui-pagelist mt10 mb50">	
	<div style="float: right;" id="pager"></div>
</div>
</body>
</html>