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
<title>新增用户</title>
<link href="skin/Default/css/base.css" rel="stylesheet" type="text/css" />
<link href="skin/Default/css/global.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath %>script/common/jquery-1.4.2.min.js" ></script>
<script type="text/javascript" >
	function check()
	{
		var loginName = $.trim($("#loginName").val());
		if(loginName =="")
		{
			alert("登录名不能为空！");
			$("#loginName").focus();
			return false;
		}
		var positionBsId = $.trim($("#userPositionBsId").val());
		if(positionBsId =="")
		{
			alert("业务岗位不能为空！");
			$("#positionBsId").focus();
			return false;
		}

		//$("#showmsg").html("<img src='images/loading.gif' border='0' />登录处理中...");
		$("#userForm").submit();
	}
	
	function getUser(){ 
	var array = window.showModalDialog("<%=basePath %>/sUser!userOpenWindowList", "", "dialogWidth:800px; dialogHeight:520px; status:no;help:no");
	//for chrome
    if (array == undefined) {
    	array = window.returnValue;
    }
	$("#parentUserId").val(array[0]);
	$("#parentUserName").val(array[1]);
}

function getK3User(){ 
	var array = window.showModalDialog("<%=basePath %>/sUser!k3UserOpenWindowList", "", 
			"dialogWidth:800px; dialogHeight:600px; status:no;help:no");
	//for chrome
    if (array == undefined) {
    	array = window.returnValue;
    }
	$("#k3UserId").val(array[0]);
	$("#k3UserName").val(array[1]);
}
	</script>
</head>
<!-- jQuery核心JS -->

<body class="ui-lv2bg">
 <!--======表格样式1======--> 
 <div class="ui-table ml15">
  <s:form id="userForm" name="userForm" action="sUser!addUser" theme="simple" namespace="/" method="post" validate="false">
<table class="ui-table-one" cellspacing="2" cellpadding="0" >
<caption class="mt30">用户信息</caption>
  <tr>
    <td class="ui-table-title">登录名：</td>
    <td class="ui-table-input-r"><input name="userInfo.loginName" id="loginName" type="text" /></td>
    <td class="ui-table-title">真实名：</td>
    <td class="ui-table-input-r"><input name="userInfo.userName" id="userName" type="text" /></td>
  </tr>
  <tr>
    <td class="ui-table-title">用户密码：</td>
    <td class="ui-table-input-r"><input name="userInfo.loginPasswor" id="loginPasswor" type="password" /></td>
    <td class="ui-table-title">手机号码：</td>
    <td class="ui-table-input-r"><input name="userInfo.mobile" id="mobile" type="text" /></td>
  </tr>
  <tr>
    <td class="ui-table-title">固定电话：</td>
    <td class="ui-table-input-r"><input name="userInfo.phone" id="phone" type="text" /></td>
    <td class="ui-table-title">email：</td>
    <td class="ui-table-input-r"><input name="userInfo.email" id="email" type="text" /></td>
  </tr>
  <tr>
    <td class="ui-table-title">所属部门：</td>
    <td class="ui-table-input-r">
    <select name="userInfo.departmentId" id="departmentId">
    <option value="">请选择部门</option>
    <s:iterator id="department" value="departList" status="dl">
    <option value="${department.departmentId }">${department.name }</option>
    </s:iterator>
    </select>
    </td>
    <td class="ui-table-title">用户角色：</td>
    <td class="ui-table-input-r">
    <select name="userInfo.roleId" id="roleId">
    <option value="">请选择角色</option>
    <s:iterator id="role" value="roleList" status="dl">
    <option value="${role.roleId }">${role.name }</option>
    </s:iterator>
    </select></td>
  </tr>
  <tr>
    <td class="ui-table-title">行政岗位：</td>
    <td class="ui-table-input-r">
    <select name="userInfo.positionOrgId" id="positionOrgId">
    <option value="">请选择行政岗位</option>
    <s:iterator id="positionOrg" value="positionOrgList" status="dl">
    <option value="${positionOrg.positionId }">${positionOrg.name }</option>
    </s:iterator>
    </select></td>
    <td class="ui-table-title">业务岗位：</td>
    <td class="ui-table-input-r">
    <select name="userPositionBsId" id="userPositionBsId">
    <option value="">请选择业务岗位</option>
    <s:iterator id="positionBs" value="positionBsList" status="pl">
    <option value="${positionBs.positionId }">${positionBs.name }</option>
    </s:iterator>
    </select></td>
  </tr>
  <tr>
    <td class="ui-table-title">上级领导：</td>
    <td class="ui-table-input-r">
<input name="userInfo.parentUserId" id="parentUserId" type="hidden"/>
    <input name="parentUserName" id="parentUserName" type="text" onClick="getUser();" readonly="readonly"/>
</td>
    <td class="ui-table-title">K3对应用户：</td>
    <td class="ui-table-input-r">
    <input name="userInfo.k3UserId" id="k3UserId" type="hidden"/>
    <input name="userInfo.k3UserName" id="k3UserName" type="text" onClick="getK3User();" readonly="readonly"/></td>
  </tr>

</table>
<div class="crm-button-panel form-group ">
		<div class="col-sm-6">
			<button class="btn btn-primary col-sm-offset-9 col-sm-3" id="sumbit">确定</button>
		</div>
		<div class="col-sm-6">
			<button class="btn btn-warning col-sm-3" id="cancel">取消</button>
		</div>
	</div>
</s:form>

<div class="clear"></div>
</div>
</body>
</html>



