<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.thoughtworks.xstream.io.path.Path"%>
<%@taglib prefix="s" uri="/struts-tags"%>


<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<script type="text/javascript" language="javascript">
   
	$(document).ready(function() {
	    if ('${msg}' == 'addsuccuss') {
	        alert('增加关注人成功！');
        }  else if ('${msg}' == 'deletesuccess') {
           alert('删除关注人成功！');
	    }  else if ('${msg}' == 'deletefail') {
           alert('删除项目失败，项目有关联计划或活动');
	    }  
	});
	
</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>客户信息</title>
<link href="skin/Default/css/base.css" rel="stylesheet" type="text/css" />
<link href="skin/Default/css/global.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" language="javascript" src="<%=basePath %>script/common/common.js"></script>
</head>
<!-- jQuery核心JS -->

<body class="ui-lv2bg">

  <nav class="navbar navbar-default crm-toolbar" role="navigation">
		<ul class="nav navbar-nav">
	    	<li class="active"><a href="customerLinkman!addLinkmanFromCu?customerId=${tcustomer.customerId }&customerName=${tcustomer.fname }">
	    		<span class="glyphicon glyphicon-phone-alt"></span>&nbsp;添加联系人</a></li>
	    	<li class="active"><a href="customerProject!addCustomerProjectFromCu?customerId=${tcustomer.customerId }&customerName=${tcustomer.fname }">
	    		<span class="glyphicon glyphicon-book"></span>&nbsp;添加项目</a></li>
	    	<li class="active"><a href="customer!addCustomerUserInit?customerId=${tcustomer.customerId }&customerName=${tcustomer.fname }">
	    		<span class="glyphicon glyphicon-camera"></span>&nbsp;添加关注人</a></li>
	    	<li class="active"><a href="customer!updateCustomerFinEvaluationInit?customerId=${tcustomer.customerId }&customerName=${tcustomer.fname }">
	    		<span class="glyphicon glyphicon-stats"></span>&nbsp;财务评价</a></li>
	    	<li class="active"><a href="customer!updateCustomerLevelInit?customerId=${tcustomer.customerId }&customerName=${tcustomer.fname }">
	    		<span class="glyphicon glyphicon-flag"></span>&nbsp;客户等级</a></li>
	    	<li class="active"><a href="pproduct!getPotentialProduct?customerId=${tcustomer.customerId}&customerName=${tcustomer.fname }">
	    		<span class="glyphicon glyphicon-barcode"></span>&nbsp;潜力产品</a></li>	    	
	    	<li class="active"><a href="customer!selectCustomerList">
	    		<span class="glyphicon glyphicon-circle-arrow-left"></span>&nbsp;返回列表</a></li>
	    </ul>
	 </nav>
       
<div class="ui-content-title"><p><img src="skin/Default/images/ui-content-ico/1.png" />客户基础资料</p></div>
 <!--======表格样式1======--> 
 <div class="ui-table ml15">
<table class="ui-table-one" cellspacing="0" cellpadding="0" >
	<input type="hidden" id="message" value="${msg}" />
  <tr>
    <td class="ui-table-title">客户名称：</td>
    <td class="ui-table-input-r">
    ${tcustomer.fname }
    </td>
    <td class="ui-table-title">客户编码：</td>
    <td class="ui-table-input-r">
    ${tcustomer.fnumber }</td>
  </tr>
  <tr>
    <td class="ui-table-title">客户全称：</td>
    <td class="ui-table-input-r">
    ${tcustomer.ffullname }</td>
    <td class="ui-table-title">客户地址：</td>
    <td class="ui-table-input-r">
    ${tcustomer.faddress}</td>
  </tr>
  <tr>
    <td class="ui-table-title">联系电话：</td>
    <td class="ui-table-input-r">
    ${tcustomer.fphone}</td>
    <td class="ui-table-title">移动电话：</td>
    <td class="ui-table-input-r">
    ${tcustomer.fmobilephone }</td>
  </tr>
  <tr>
    <td class="ui-table-title">已分配给：</td>
    <td class="ui-table-input-r">
    <s:iterator id="couser" value="couserList">
    ${couser.userName }&nbsp;
    </s:iterator>
    </td>
    <td class="ui-table-title">关注人：</td>
    <td class="ui-table-input-r">
    <s:iterator id="customerUser" value="customerUserList">
    ${customerUser.userName }&nbsp;
    </s:iterator>
    </td>
  </tr>
  <tr>
    <td class="ui-table-title">联系人完整度</td>
    <td class="ui-table-input-r" style='color: red;'>
    ${linkmanPerfection}</td>
  </tr>
</table>
</div>

<div class="ui-content-title"><p><img src="skin/Default/images/ui-content-ico/1.png" />业务往来资料</p></div>
 <!--======表格样式1======--> 
 <div class="ui-table ml15">
<table class="ui-table-one" cellspacing="0" cellpadding="0" >
  <tr>
    <td class="ui-table-title">结算方式：</td>
    <td class="ui-table-input-r">
    ${tcustomer.settlename }
    </td>
    <td class="ui-table-title">分管部门：</td>
    <td class="ui-table-input-r">
    ${tcustomer.departmentname}</td> 
  </tr>
  
  <tr>
    <td class="ui-table-title">最后交易日期：</td>
    <td class="ui-table-input-r">
    <s:date name="tcustomer.flasttradedate" format="yyyy-MM-dd" /> </td>   
    <td class="ui-table-title">最后交易金额：</td>
    <td class="ui-table-input-r">
    ${tcustomer.flasttradeamount }</td>
  </tr>
  <tr>
    <td class="ui-table-title">最后收款日期：</td>
    <td class="ui-table-input-r">
    <s:date name="tcustomer.flastreceivedate" format="yyyy-MM-dd"/> </td>   
    <td class="ui-table-title">最后收款金额：</td>
    <td class="ui-table-input-r">
    ${tcustomer.flastrpamount }</td>
  </tr>
  <tr>
  <td class="ui-table-title">付款条件：</td>
    <td class="ui-table-input-r">
    ${tcustomer.payCondition }</td>
    <td class="ui-table-title">业务专管员：</td>
    <td class="ui-table-input-r">
    ${tcustomer.employeename }</td>
  </tr>
</table>
</div>

<div class="ui-content-title"><p><img src="skin/Default/images/ui-content-ico/1.png" />客户财务评价</p></div>
 <!--======表格样式1======--> 
 <div class="ui-table ml15">
<table class="ui-table-one" cellspacing="0" cellpadding="0" >
  <tr>
    <td class="ui-table-title">销售经理评价：</td>
    <td class="ui-table-input-r">
    ${customerFinEvaluation.evaSalemanagerName }
    </td>
    <td class="ui-table-title">销售副总评价：</td>
    <td class="ui-table-input-r">
    ${customerFinEvaluation.evaSalegenName }</td>
  </tr>
  <tr>
    <td class="ui-table-title">财务主管评价：</td>
    <td class="ui-table-input-r">
    ${customerFinEvaluation.evaFinmanagerName}</td>   
    <td class="ui-table-title">客户等级：</td>
    <td>${customerLevel.levelName}</td>
  </tr>

</table>
</div>

<div class="ui-content-title"><p><img src="skin/Default/images/ui-content-ico/1.png" />近两天历史订购统计 - 前5位</p></div>    
<div class="ui-table-style1">
	<table class="table table-hover" cellspacing="30" cellpadding="30" >
        <thead>
                <th>产品名称</th>
                <th>计量单位</th>
                <th>订购数量</th>
        </thead>
        <s:iterator id="os" value="orderStatisticsList" status="dl">
             <tr>
               <td>${os.productName }</td>
               <td>${os.unitName }</td>
               <td>${os.totalQuantity }</td>
             </tr> 
        </s:iterator>
     </table>
</div>
    
<div class="ui-content-title"><p><img src="skin/Default/images/ui-content-ico/1.png" />客户潜力产品</p></div>    
   <div class="ui-table-style1">
	<table class="table table-hover" cellspacing="30" cellpadding="30" >
             <thead>
                <input type="hidden" id="pageNumber" name="pageNumber" value="${page.start}" />
                <th>更改日期</th>
                <th>提交人</th>
                <th>潜力产品描述</th>
     			<th>操作</th>
             </thead>
       <!--      =================表格循环===============-->
             <s:iterator id="pp" value="pproductList" status="dl">
             <tr>
               <td>${pp.lastUpdateDate }</td>
               <td>${pp.userName }</td>
               <td>${pp.productDescription }</td>
               <td><a href="pproduct!getPotentialProduct?customerId=${pp.customerId }&customerName=${pp.customerName}">修改</a></td>
             </tr> 
             </s:iterator>
         <!--     =================表格循环over===============-->
         
     </table>
    </div>
          
	<div class="ui-content-title"><p><img src="skin/Default/images/ui-content-ico/1.png" />联系人档案</p></div>    
	    <div class="ui-table-style1">
			<table class="table table-hover" cellspacing="30" cellpadding="30" >
	              <thead>
		              <input type="hidden" id="pageNumber" name="pageNumber" value="${page.start}" />
		                <th>姓名</th>
		                <th>部门</th>
		                <th>职务</th>
		                <th>联系电话</th>
		                <th>与公司关系</th>
		               <th>与竞争对手关系</th>
		               <th>修改</th>
		               <th>删除</th>
	              </thead>
	        <!--      =================表格循环===============-->
	              <s:iterator id="linkman" value="linkmanList" status="dl">
	              <tr>
	                <td>${linkman.name }</td>
	                <td>${linkman.department }</td>
	                <td>${linkman.station }</td>
	                <td>${linkman.mobile }</td>
	                <td>${linkman.relationUsName }</td>
	                <td>${linkman.relationCompName }</td>
	                <td><a href="customerLinkman!updateLinkmanInit?linkmanId=${linkman.linkmanId }&customerId=${linkman.customerId }">修改</a></td>
	                <td><a href="customerLinkman!deleteLinkman?linkmanId=${linkman.linkmanId }&customerId=${linkman.customerId }">删除</a></td>
	              </tr> 
	              </s:iterator>
	          <!--     =================表格循环over===============-->
	          
	            </table>
          </div>
            
<div class="ui-content-title"><p><img src="skin/Default/images/ui-content-ico/1.png" />客户项目跟踪</p></div>
    <div class="clear"></div>
    
	 <div class="ui-table-style1">
		<table  class="table table-hover" cellspacing="0" cellpadding="0" >
              <thead>
                <th>项目名称</th>
                <th>启动时间</th>
                <th>预计结束时间</th>
                <th>预计年销售额</th>
                <th>查看</th>
                <th>操作</th>
              </thead>
          <!--      =================表格循环===============-->
              <s:iterator id="customerProject" value="customerProjectList" status="dl">
              <tr>
                <td>${customerProject.name }</td>
                <td><s:date name="#customerProject.startTime" format="yyyy-MM-dd" /></td>
                <td><s:date name="#customerProject.planEndTime" format="yyyy-MM-dd" /></td>
                <td>${customerProject.amount }</td>
                <td><a href="customerProject!updateCustomerProjectInit?projectId=${customerProject.projectId }&customerId=${tcustomer.customerId }">查看</a></td>
                <td><a href="customerProject!deleteCustomerProject?projectId=${customerProject.projectId }&customerId=${tcustomer.customerId }">删除</a></td>
                <td></td>
              </tr> 
              </s:iterator>
          <!--     =================表格循环over===============-->
            </table>
            </div>
            
   <div class="ui-content-title"><p><img src="skin/Default/images/ui-content-ico/1.png" />销售计划与活动</p></div>
    <div class="clear"></div>
	 <div class="ui-table-style1">
		<table class="table table-hover" cellspacing="0" cellpadding="0" >
              <thead>
                <th>目标与主题</th>
                <th>拜访人员</th>
                <th>拜访日期</th>
                <th>拜访对象</th>
                <th>对应项目</th> 
               <th>拜访内容</th>
              </thead>
              <s:iterator id="activity" value="activityList" status="al">
              <tr>
                <td>${activity.projectTopical }</td>
                <td>${activity.responseUserName }</td>
                <td><s:date name="#activity.activityDate" format="yyyy-MM-dd" /></td>
                <td>${activity.visitPerson }</td>
                <td>${activity.customerProjectName }</td> 
               <td>${activity.comment }</td>
              </tr>
              </s:iterator>
            </table>
            </div>
            
    <div class="ui-content-title"><p><img src="skin/Default/images/ui-content-ico/1.png" />销售业务往来</p></div>
    <div class="clear"></div>
	 <div class="ui-table-style1">
		<table class="table table-hover" cellspacing="0" cellpadding="0" >
              <thead>
                <th>订单号</th>
                <th>订单日期</th>
                <th>订单金额</th>
                <th>业务主管</th>
               <th>订单执行状态</th>
              </thead>     
              <s:iterator id="order" value="orderList" status="ol"> 
              <tr>
                <td>${order.fBillNo }</td>
                <td><s:date name="#order.fDate" format="yyyy-MM-dd" /></td>
                <td>${order.amountString }</td>
                <td>${order.managerName }</td>
               <td>${order.statusName }</td>
              </tr>
              </s:iterator>  
            </table>
            </div>
            
    <div class="ui-content-title"><p><img src="skin/Default/images/ui-content-ico/1.png" />收款记录</p></div>
    <div class="clear"></div> 
	 <div class="ui-table-style1">
		<table class="table table-hover" cellspacing="0" cellpadding="0" >
              <thead>
                <th >发票号</th>
                <th>开票日期</th>
                <th>收款日期</th>
                <th>已收金额</th>
               <th>应收金额</th>
              </thead>    
              <s:iterator id="saleBill" value="saleBillList" status="ol"> 
              <tr>
                <td>${saleBill.fBillNo }</td>
                <td><s:date name="#saleBill.fDate" format="yyyy-MM-dd" /></td>
                <td><s:date name="#saleBill.receiveDate" format="yyyy-MM-dd" /></td>
                <td>${saleBill.receiveAmountString }</td>
               <td>${saleBill.saleAmountString }</td>
              </tr>
              </s:iterator>     
            </table>
            </div>
</body>
</html>



