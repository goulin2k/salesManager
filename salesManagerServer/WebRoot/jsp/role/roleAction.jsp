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
<title>角色权限</title>
<link href="skin/Default/css/base.css" rel="stylesheet" type="text/css" />
<link href="skin/Default/css/global.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath %>script/common/jquery-1.4.2.min.js" ></script>
<script type="text/javascript" >
	function check(){
	   var roleId = $("#roleId").val();
	  var m = document.all.moduleIds;
	   moduleIds = "";
	   for (i= 0 ;i < m.length; i++){
	      if(m[i].checked == true){
			 if(moduleIds == ""){
				moduleIds = m[i].value;
			 }else{
				moduleIds = moduleIds + "," + m[i].value;
			 }
		  }
	  }
	if(moduleIds!=""){
	   $.ajax({
	           type: "POST",
			   url: "role!setRoleAction",
			   data: "moduleIds="+moduleIds+"&roleId="+roleId,
			   dataType:'json',
			   cache: false,
			   async: true,
			   success: function(data){
			   if(data == "0"){
			   alert("角色权限设置成功");
			   }
	           }
	   });
	 }
}

function selectall(v){
  var m = document.all.moduleIds;
       for (i=0;i<m.length;i++){
          m[i].checked = v;
	   	 }
}

$(document).ready(function() {
	  
	    if ('${roleId}' == 1) {
	       selectall("checked");
	       ui= "确定";
	       $("#tijiao").html(ui);
        } 
	});
	</script>
</head>
<!-- jQuery核心JS -->

<body class="ui-lv2bg">
 <!--======表格样式1======--> 
 <div class="ui-table ml15">
  <s:form id="actionForm" name="actionForm" action="role!setRoleAction" theme="simple" namespace="/" method="post" validate="false">
  <input type="hidden" id="roleId" name="roleId" value="<s:property value='roleId' />" />
<table class="ui-table-one" cellspacing="2" cellpadding="0" >
 <tr>
        <td  class="ui-table-title">功能列表：</td>
        <td  colspan="3" class="color555 f13" >
         <span class="color-bule"><img src="skin/Default/images/ui-content-ico/list.png" />客户管理</span>
        <hr/>
        <s:iterator id="modul" value="actionList.get(0).moduleList" status="status">
        <input type="checkbox" name="moduleIds" id="moduleIds" value="${modul.moduleId}" ${modul.isCheck}/> ${modul.moduleNameChinese}
        &nbsp;&nbsp;&nbsp;&nbsp;
        <s:if test="#status.index % 5 ==4 ">
        <br/>
        </s:if>
        </s:iterator>
         <br/>
         <div class="mb15"></div>
         <div class="mb15"></div>
         <span class="color-bule"><img src="skin/Default/images/ui-content-ico/list.png" />销售管理</span>
        <hr/>
        <s:iterator id="modul" value="actionList.get(1).moduleList" status="status">
        <input type="checkbox" name="moduleIds" value="${modul.moduleId}" ${modul.isCheck}/> ${modul.moduleNameChinese}
        &nbsp;&nbsp;&nbsp;&nbsp;
        <s:if test="#status.index % 5 ==4 ">
        <br/>
        </s:if>
        </s:iterator>
         <br/>
         <div class="mb15"></div>
          <span class="color-bule"><img src="skin/Default/images/ui-content-ico/list.png" />订单合同</span>
        <hr/>
        <s:iterator id="modul" value="actionList.get(2).moduleList" status="status">
        <input type="checkbox" name="moduleIds"  value="${modul.moduleId}" ${modul.isCheck}/> ${modul.moduleNameChinese}
        &nbsp;&nbsp;&nbsp;&nbsp;
        <s:if test="#status.index % 5 ==4 ">
        <br/>
        </s:if>
        </s:iterator>
         <br/>
         <div class="mb15"></div>
          <span class="color-bule"><img src="skin/Default/images/ui-content-ico/list.png" />绩效合同</span>
        <hr/>
        <s:iterator id="modul" value="actionList.get(3).moduleList" status="status">
        <input type="checkbox" name="moduleIds" value="${modul.moduleId}" ${modul.isCheck}/> ${modul.moduleNameChinese}
        &nbsp;&nbsp;&nbsp;&nbsp;
        <s:if test="#status.index % 5 ==4 ">
        <br/>
        </s:if>
        </s:iterator>
         <br/>
         <div class="mb15"></div>
        <span class="color-bule"><img src="skin/Default/images/ui-content-ico/list.png" />协同办公</span>
        <hr/>
        <s:iterator id="modul" value="actionList.get(4).moduleList" status="status">
        <input type="checkbox" name="moduleIds" value="${modul.moduleId}" ${modul.isCheck}/> ${modul.moduleNameChinese}
        &nbsp;&nbsp;&nbsp;&nbsp;
        <s:if test="#status.index % 5 ==4 ">
        <br/>
        </s:if>
        </s:iterator>
         <br/>
         <div class="mb15"></div>
          <span class="color-bule"><img src="skin/Default/images/ui-content-ico/list.png" />统计分析</span>
        <hr/>
        <s:iterator id="modul" value="actionList.get(5).moduleList" status="status">
        <input type="checkbox" name="moduleIds" value="${modul.moduleId}" ${modul.isCheck}/> ${modul.moduleNameChinese}
        &nbsp;&nbsp;&nbsp;&nbsp;
        <s:if test="#status.index % 5 ==4 ">
        <br/>
        </s:if>
        </s:iterator>
         <br/>
         <div class="mb15"></div>
            <span class="color-bule"><img src="skin/Default/images/ui-content-ico/list.png" />系统管理</span>
        <hr/>
        <s:iterator id="modul" value="actionList.get(6).moduleList" status="status">
        <input type="checkbox" name="moduleIds" value="${modul.moduleId}" ${modul.isCheck}/> ${modul.moduleNameChinese}
        &nbsp;&nbsp;&nbsp;&nbsp;
        <s:if test="#status.index % 5 ==4 ">
        <br/>
        </s:if>
        </s:iterator>
        </td>
      </tr>

</table>

<div class="ui-button-big center mt15 mb50 " id="tijiao"><a href="#" onclick="check();">确定</a></div>
</s:form>

<div class="clear"></div>
</div>
</body>
</html>



