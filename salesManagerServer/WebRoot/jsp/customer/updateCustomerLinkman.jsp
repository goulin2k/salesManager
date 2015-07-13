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
<title>修改客户联系人</title>
<link href="skin/Default/css/base.css" rel="stylesheet" type="text/css" />
<link href="skin/Default/css/global.css" rel="stylesheet" type="text/css" />
s
<script type="text/javascript" >
	$(document).ready(function(){
		$( "#birthday" ).datepicker({
			dateFormat: "yy-mm-dd"
		});
		
		$("#submit").click(function(checkEvent){
			if($('#customerLinkmanForm').form('validate')){
	    		$('#customerLinkmanForm').submit();
	    	}
		});
		
		$("#cancel").click(function(checkEvent){
	    	checkEvent.preventDefault();
	    	history.back();
	    });
	});
	</script>
</head>
<!-- jQuery核心JS -->

<body class="ui-lv2bg">
	
       

 <!--======表格样式1======--> 
 <div class="ui-table ml15">
  <s:form id="customerLinkmanForm" name="customerLinkmanForm" action="customerLinkman!updateLinkman" theme="simple" namespace="/" method="post" validate="false">
<table class="ui-table-one" cellspacing="2" cellpadding="0" >
<caption class="mt30"><img src="skin/Default/images/ui-content-ico/add.png"/ class="mr8">修改客户联系人</caption>
  <tr>
    <td class="ui-table-title">对应客户：</td>
    <td class="ui-table-input-r">
    <input name="linkman.linkmanId" id="linkmanId" type="hidden" value="${linkman.linkmanId}"/>
    <input name="linkman.customerId" id="linkman.customerId" type="hidden" value="<s:property value="customerId"/>"/>
    <input name="linkman.customerName" id="customerName" type="text" value="${linkman.customerName}" readonly="readonly"/></td>
    <td class="ui-table-title">姓名：</td>
    <td class="ui-table-input-r"><input name="linkman.name" id="name" type="text" value="${linkman.name}" readonly="readonly"/></td>
  </tr>
  <tr>
    <td class="ui-table-title">职位：</td>
    <td class="ui-table-input-r"><input name="linkman.station" id="station" type="text" value="${linkman.station}"/></td>
    <td class="ui-table-title">部门：</td>
    <td class="ui-table-input-r"><input name="linkman.department" id="department" type="text" value="${linkman.department}"/></td>
  </tr>
  <tr>
    <td class="ui-table-title">手机：</td>
    <td class="ui-table-input-r">
    	<input name="linkman.mobile" id="mobile" type="text" class="easyui-validatebox" value="${linkman.mobile}"/></td>
    <td class="ui-table-title">电话：</td>
    <td class="ui-table-input-r">
    	<input name="linkman.phone" id="phone" type="text" class="easyui-validatebox" value="${linkman.phone}"/></td>
  </tr>
  <tr>
  	<td class="ui-table-title">传真：</td>
    <td colspan="3" class="ui-table-input-r"><input name="linkman.fax" id="fax" type="text" value="${linkman.fax}" class="easyui-validatebox"/></td>
  </tr>
  <tr>
    <td class="ui-table-title">邮件：</td>
    <td class="ui-table-input-r">
    	<input name="linkman.email" id="email" type="text" class="easyui-validatebox" value="${linkman.email}"/></td>
    <td class="ui-table-title">生日：</td>
    <td class="ui-table-input-r">
    	<input class="easyui-validatebox" name="linkman.birthday" id="birthday" type="text" value="${linkman.birthdayFormat}"/></td>
  </tr>
  <tr>
  	<td class="ui-table-title">兴趣爱好：</td>
  	<td colspan="3" class="ui-table-input-r">
  		<input class="easyui-validatebox" style="width:100%;" name="linkman.hobbies" id="hobbies" type="text" value="${linkman.hobbies}"/></td>
  </tr>
  <tr>
    <td class="ui-table-title">与公司关系：</td>
    <td class="ui-table-input-r">
    <select name="linkman.relationUs" id="elationUs" class="easyui-validatebox form-control" required="true">
	    <option value="">请选择关系</option>
	    <s:iterator id="enumerationUs" value="relationUsList" status="dl">
	    <option value="${enumerationUs.enumerationId }" ${enumerationUs.isSelect }>${enumerationUs.enumerationName }</option>
	    </s:iterator>
    </select>
    
    <td class="ui-table-title">与竞争对手关系：</td>
    <td class="ui-table-input-r">
     	<select name="linkman.relationComp" id="relationComp" class="easyui-validatebox form-control" required="true">
		    <option value="">请选择关系</option>
		    <s:iterator id="enumerationComp" value="relationCompList" status="dl">
		    <option value="${enumerationComp.enumerationId }" ${enumerationComp.isSelect }>${enumerationComp.enumerationName }</option>
		    </s:iterator>
    	</select>
    </td>
  </tr>


</table>

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
</div>
</body>
</html>



