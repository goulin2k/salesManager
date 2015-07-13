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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>修改客户项目</title>
<link href="skin/Default/css/base.css" rel="stylesheet" type="text/css" />
<link href="skin/Default/css/global.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" >
//添加日期输入控件
	$(document).ready(function(){  
	    $("#submit").click(function(checkEvent){
	    	if($('#customerProjectForm').form('validate')){
				$("#customerProjectForm").submit();
	    	}
		});
	
	    $("#cancel").click(function(checkEvent){
	    	checkEvent.preventDefault();
	    	history.back();
	    });
	    
	    $( "#startTime" ).datepicker({
    		dateFormat: "yy-mm-dd"
	    });
	    
	    $( "#planEndTime" ).datepicker({
	    	dateFormat: "yy-mm-dd"
	    });
	});
  
	function getLinkman(customerId){
		$.ajax({
		    type: "POST",
			url: "customerProject!jsonLinkmans",
			data:"customerId="+customerId,
			dataType:"json",
			success: function(data){ 
				var content = "<select name='customerProject.linkmanId' id='linkmanId'><option value=''>请选择负责人</option>";
				var contentOp = "";
				$.each(data, function(j, value) {
			       	    contentOp += "<option value='"+value.linkmanId+"'>"+value.name+"</option>";
			        });
			        contentOp+= "</select>"
			        content += contentOp;
			        $("#selectTxt").html(content);
		    }
		});
	}
	</script>
</head>
<!-- jQuery核心JS -->

<body class="ui-lv2bg">

 <!--======表格样式1======--> 

  <s:form id="customerProjectForm" name="customerProjectForm" action="customerProject!updateCustomerProject" theme="simple" namespace="/" method="post" validate="false">
<table class="ui-table-one" cellspacing="2" cellpadding="0" >
<caption class="mt30">客户项目信息</caption>
  <tr>
  <td class="ui-table-title">客户名称：</td>
    <td class="ui-table-input-r">
    <input name="customerProject.projectId" id="projectId" type="hidden" value="${customerProject.projectId }"/>
    <input name="customerProject.customerId" type="hidden" value="${customerProject.customerId }"/>
    <input name="customerId" type="hidden" value="${customerId }"/>
    <input name="customerProject.customerName" id="customerId" type="text" value="${customerProject.customerName }"/></td>
    <td class="ui-table-title">项目名称：</td>
    <td class="ui-table-input-r">
    	<input name="customerProject.name" class="easyui-validatebox" id="name" type="text" 
    		value="${customerProject.name }" required="true"/></td>
    
  </tr>
  <tr>
    <td class="ui-table-title">开始日期：</td>
    <td class="ui-table-input-r">
    	<input name="customerProject.startTime" id="startTime" type="text" 
    		value="<s:property value="customerProject.startTime"/> " class="easyui-validatebox" required="true"/> 
    </td>
    <td class="ui-table-title">预计结束日期：</td>
    <td class="ui-table-input-r">
    	<input name="customerProject.planEndTime" id="planEndTime" type="text" 
    		value="<s:property value="customerProject.planEndTime"/>" class="easyui-validatebox" required="true"/>
    </td>
  </tr>
  <tr>
    <td class="ui-table-title">预计年销售金额：</td>
    <td class="ui-table-input-r">
    	<input name="customerProject.amount" id="amount" type="text" 
    		value="${customerProject.amount }" class='easyui-numberbox' precision='0' min='0' max='100000000' required="true"/></td>
    <td class="ui-table-title">机会评估(销售人员)：</td>
    <td class="ui-table-input-r">
    <s:if test="userPositionOrgId ==3">
    <select name="customerProject.evaluationSaler" id="evaluationSaler" class="form-control">
    <option value="">请选择机会评估</option>
    <s:iterator id="evaSaler" value="evaluationSalerList" status="dl">
    <option value="${evaSaler.enumerationId }" ${evaSaler.isSelect }>${evaSaler.enumerationName }</option>
    </s:iterator>
    </select>
    </s:if>
    <s:else>
    <input name="customerProject.evaluationSaler" id="evaluationSaler" type="hidden" readonly="readonly" value ="${customerProject.evaluationSaler }"/>
    <input name="customerProject.evaluationSalerName" id="evaluationSalerName" type="text" readonly="readonly" value ="${customerProject.evaluationSalerName }"/>
</s:else>
    </td>
  </tr>
   <tr>
    <td class="ui-table-title">机会评估(销售经理)：</td>
    <td class="ui-table-input-r">
    <s:if test="userPositionOrgId ==4">
    <select name="customerProject.evaluationManager" id="evaluationManager">
    <option value="">请选择机会评估</option>
    <s:iterator id="evaManager" value="evaluationManagerList" status="dl">
    <option value="${evaManager.enumerationId }" ${evaManager.isSelect }>${evaManager.enumerationName }</option>
    </s:iterator>
    </select>
    </s:if>
    <s:else>
    <input name="customerProject.evaluationManager" id="evaluationManager" type="hidden" readonly="readonly" value ="${customerProject.evaluationManager }"/>
    <input name="customerProject.evaluationManagerName" id="evaluationManagerName" type="text" readonly="readonly" value ="${customerProject.evaluationManagerName }"/>
</s:else>
    </td>
    <td class="ui-table-title">机会评估(销售副总)：</td>
    <td class="ui-table-input-r">
    <s:if test="userPositionOrgId ==5">
    <select name="customerProject.evaluationGen" id="evaluationGen" class="form-control">
    <option value="">请选择机会评估</option>
    <s:iterator id="evaGen" value="evaluationGenList" status="dl">
    <option value="${evaGen.enumerationId }" ${evaGen.isSelect }>${evaGen.enumerationName }</option>
    </s:iterator>
    </select>
    </s:if>
    <s:else>
    <input name="customerProject.evaluationGen" id="evaluationGenName" type="hidden" readonly="readonly" value ="${customerProject.evaluationGen }"/>
    <input name="customerProject.evaluationGenName" id="evaluationGenName" type="text" readonly="readonly" value ="${customerProject.evaluationGenName }"/>
</s:else>
    </td>
  </tr>
  <tr>
    <td class="ui-table-title">使用部位：</td>
    <td class="ui-table-input-r" colspan="3">
    	<input name="customerProject.usePartion" id="usePartion" type="text" 
    		value="${customerProject.usePartion }" class="easyui-validatebox"/></td>
  </tr><tr>
    <td class="ui-table-title">使用目的：</td>
    <td class="ui-table-input-r" colspan="3">
    	<input name="customerProject.useAttention" id="useAttention" type="text" 
    		class="easyui-validatebox" value="${customerProject.useAttention }"/></td>
  </tr>
  <tr>
    <td class="ui-table-title">使用现状：</td>
    <td class="ui-table-input-r" colspan="3">
    	<input name="customerProject.usingStatus" id="usingStatus" type="text" 
    		value="${customerProject.usingStatus }" class="easyui-validatebox"/></td>
  </tr><tr>
    <td class="ui-table-title">现有问题：</td>
    <td class="ui-table-input-r" colspan="3">
    	<input name="customerProject.problem" id="problem" type="text" 
    		value="${customerProject.problem }" class="easyui-validatebox"/></td>
  </tr>
  <tr>
    <td class="ui-table-title">解决办法：</td>
    <td class="ui-table-input-r" colspan="3">
    	<input name="customerProject.solution" id="solution" type="text" 
    		value="${customerProject.solution }" class="easyui-validatebox"/></td>
  </tr>
    <td class="ui-table-title">客户负责人</td>
    <td class="ui-table-input-r">
	    <select name="customerProject.linkmanId" id="linkmanId">
	    <option value="">请选择负责人</option>
	    <s:iterator id="customerLinkman" value="customerLinkmanList" status="dl">
	    <option value="${customerLinkman.linkmanId }" ${customerLinkman.isSelect }>${customerLinkman.name }</option>
	    </s:iterator>
	    </select>
    </td>
  </tr>

</table>

<div class="ui-content-title"><p><img src="skin/Default/images/ui-content-ico/1.png" />销售计划</p></div>
    <div class="clear"></div>
	 <div class="ui-table-style1">
		<table class="table table-hover" cellspacing="0" cellpadding="0" >
              <thead>
                <th >创建时间</th>	  
			   <th >主题</th>
			   <th >计划类型</th>
			   
			   <th >下达人</th>
			   <th >负责人</th> 
			   <th >开始时间</th>
			   <th >结束时间</th> 
			   
              </thead>
              <s:iterator id="projectList" value="projectList" status="al">
              <tr class="ui-table-style1-tr2">
				  <td><s:date  name="createTime" format="yyyy-MM-dd HH:mm"/></td>
				  <td><s:property value="topical"/></td>
				  <td><s:property value="enumerationName"/></td>
				  
				  <td><s:property value="createUserName"/></td>
				  <td><s:property value="responseUserName"/></td>
				  <td><s:property value="startTime"/></td>
				  <td><s:property value="endTime"/></td>
				   
				</tr>
              </s:iterator>
            </table>
            </div>

<div class="ui-content-title"><p><img src="skin/Default/images/ui-content-ico/1.png" />项目销售活动</p></div>
    <div class="clear"></div>
	 <div class="ui-table-style1">
		<table class="table table-hover" cellspacing="0" cellpadding="0" >
              <thead>
                <th>目标与主题</th>
                <th>拜访人员</th>
                <th>拜访日期</th>
                <th>拜访对象</th>
               
               <th>拜访内容</th>
              </thead>
              <s:iterator id="activity" value="activityList" status="al">
              <tr>
                <td>${activity.projectTopical }</td>
                <td>${activity.responseUserName }</td>
                <td><s:date name="#activity.activityDate" format="yyyy-MM-dd" /></td>
                <td>${activity.visitPerson }</td>
                 
               <td>${activity.comment }</td>
              </tr>
              </s:iterator>
            </table>
  </div>
  
  

	<!--  按钮面板  -->
	<div class="crm-button-panel form-group ">
		<div class="col-sm-6">
			<a class="btn btn-primary col-sm-offset-9 col-sm-3" id="submit">确定</a>
		</div>
		<div class="col-sm-6">
			<a class="btn btn-warning col-sm-3" id="cancel">取消</a>
		</div>
	</div>
</s:form>
</body>
</html>



