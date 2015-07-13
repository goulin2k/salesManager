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
<title>用户详情</title>
<link href="skin/Default/css/base.css" rel="stylesheet" type="text/css" />
<link href="skin/Default/css/global.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath %>script/common/jquery-1.4.2.min.js" ></script>
<script type="text/javascript" >
	function check()
	{
		var loginName = $.trim($("#loginName").val());
		if(loginName =="")
		{
			alert("用户名不能为空！");
			$("#loginName").focus();
			return false;
		}
		//$("#showmsg").html("<img src='images/loading.gif' border='0' />登录处理中...");
		$("#userForm").submit();
	}
	
	function getUser(){ 
	var array = window.showModalDialog("<%=basePath %>/sUser!userOpenWindowList", "", "dialogWidth:600px; dialogHeight:400px; status:no;help:no");
	//for chrome
    if (array == undefined) {
    	array = window.returnValue;
    }
	$("#parentUserId").val(array[0]);
	$("#parentUserName").val(array[1]);
}

function getK3User(){ 
	var array = window.showModalDialog("<%=basePath %>/sUser!k3UserOpenWindowList", "", "dialogWidth:600px; dialogHeight:400px; status:no;help:no");
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
	<div class="ui-content-title"><p><img src="skin/Default/images/ui-content-ico/1.png" />修改用户</p></div>
   <!--======内容区域子导航======--> 

   <!--===== 分割线======-->     
<hr class="hr-two" />
    
    
    
    
<!--======搜索======-->
        <div class="clear"></div>
       

 <!--======表格样式1======--> 
 <div class="ui-table ml15">
  <s:form id="userForm" name="userForm" action="sUser!updateUser" theme="simple" namespace="/" method="post" validate="false">
<table class="ui-table-one" cellspacing="2" cellpadding="0" >
<caption class="mt10 mb10">用户信息</caption>
 
   <tr>
    <td class="ui-table-title">登录名：</td>
    <td class="ui-table-input-r">
     <input name="userInfo.userId" id="userId" type="hidden" value="${userInfo.userId}"/>
    <input name="userInfo.loginName" id="loginName" type="text" value="${userInfo.loginName}" readonly="readonly"/></td>
    <td class="ui-table-title">真实名：</td>
    <td class="ui-table-input-r"><input name="userInfo.userName" id="userName" type="text" value="${userInfo.userName}"/></td>
  </tr>
  <tr>
    <td class="ui-table-title">用户密码：</td>
    <td class="ui-table-input-r"><input name="userInfo.loginPasswor" id="loginPasswor" type="password" /></td>
    <td class="ui-table-title">手机号码：</td>
    <td class="ui-table-input-r"><input name="userInfo.mobile" id="mobile" type="text" value="${userInfo.mobile}" /></td>
  </tr>
  <tr>
    <td class="ui-table-title">固定电话：</td>
    <td class="ui-table-input-r"><input name="userInfo.phone" id="phone" type="text" value="${userInfo.phone}" /></td>
    <td class="ui-table-title">email：</td>
    <td class="ui-table-input-r"><input name="userInfo.email" id="email" type="text" value="${userInfo.email}"/></td>
  </tr>
  <tr>
    <td class="ui-table-title">所属部门：</td>
    <td class="ui-table-input-r">
    <select name="userInfo.departmentId" id="departmentId">
    <option value="">请选择部门</option>
    <s:iterator id="department" value="departList" status="dl">
    <option value="${department.departmentId }" ${department.isSelect }>${department.name }</option>
    </s:iterator>
    </select>
    </td>
    <td class="ui-table-title">用户角色：</td>
    <td class="ui-table-input-r">
    <select name="userInfo.roleId" id="roleId">
    <option value="">请选择角色</option>
    <s:iterator id="role" value="roleList" status="dl">
    <option value="${role.roleId }" ${role.isSelect }>${role.name }</option>
    </s:iterator>
    </select></td>
  </tr>
  <tr>
    <td class="ui-table-title">行政岗位：</td>
    <td class="ui-table-input-r">
    <select name="userInfo.positionOrgId" id="positionOrgId">
    <option vaule="">请选择岗位</option>
    <s:iterator id="positionOrg" value="positionOrgList" status="dl">
    <option value="${positionOrg.positionId }" ${positionOrg.isSelect }>${positionOrg.name }</option>
    </s:iterator>
    </select></td>
    <td class="ui-table-title">业务岗位：</td>
    <td class="ui-table-input-r">
    <select name="userInfo.positionBsId" id="positionBsId">
    <option value="">请选择岗位</option>
    <s:iterator id="positionBs" value="positionBsList" status="dl">
    <option value="${positionBs.positionId }" ${positionBs.isSelect }>${positionBs.name }</option>
    </s:iterator>
    </select></td>
  </tr>
   <tr>
   <td class="ui-table-title">上级领导</td>
    <td class="ui-table-input-r">
    <input name="userInfo.parentUserId" id="parentUserId" type="hidden" value="${userInfo.parentUserId}"/>
    <input name="parentUserName" id="parentUserName" type="text" onClick="getUser();" readonly="readonly" value="${parentUserName}"/>
    </td>
    <td class="ui-table-title">K3对应用户：</td>
    <td class="ui-table-input-r">
    <input name="userInfo.k3UserId" id="k3UserId" type="hidden" value="${userInfo.k3UserId}"/>
    <input name="userInfo.k3UserName" id="k3UserName" type="text" value="${userInfo.k3UserName}" onClick="getK3User();" readonly="readonly"/></td>
    
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



