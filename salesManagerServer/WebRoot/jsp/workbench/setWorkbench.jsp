<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@page import="com.thoughtworks.xstream.io.path.Path" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<% String path=request.getContextPath(); String basePath=request.getScheme()+
       "://"+request.getServerName()+ ":"+request.getServerPort()+path+ "/"; %>
       
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>自定义工作台</title>
        <link href="skin/Default/css/base.css" rel="stylesheet" type="text/css"/>
        <link href="skin/Default/css/global.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascript" src="<%=basePath %>script/common/jquery-1.4.2.min.js">
        </script>
        <script type="text/javascript">
            function check() {
                var m = document.all.moduleIds;
                moduleIds = "";
                for (i = 0; i < m.length; i++) {
                    if (m[i].checked == true) {
                        if (moduleIds == "") {
                            moduleIds = m[i].value;
                        } else {
                            moduleIds = moduleIds + "," + m[i].value;
                        }
                    }
                }
                if (moduleIds != "") {
                    $.ajax({
                        type: "POST",
                        url: "workbench!setWorkbench",
                        data: "moduleIds=" + moduleIds,
                        dataType: 'json',
                        cache: false,
                        async: true,
                        success: function(data) {
                            if (data == "0") {
                                alert("工作台设置成功");
                            }
                        }
                    });
                }
            }
        </script>
    </head>
    <!-- jQuery核心JS -->
    
    <body class="ui-lv2bg">
        <div class="ui-content-title">
            <p><img src="skin/Default/images/ui-content-ico/config.png" />自定义工作台</p>
        </div>
        <!--======内容区域子导航======-->
        <!--======搜索======-->
        <div class="clear">
        </div>
        <!--======表格样式1======-->
        <div class="ui-table ml15">
            <s:form id="moduleForm" name="moduleForm" action="workbench!setWorkbench"
            theme="simple" namespace="/" method="post" validate="false">
                <table class="ui-table-one" cellspacing="2" cellpadding="0">
                    <caption class="mt10 mb10">
                        <img src="skin/Default/images/ui-content-ico/works.png" /> 自定义工作台 </caption>
                    <tr>
                        <td class="ui-table-title">功能列表：</td>
                        <td colspan="3" class="color555 f13">
                            <span class="color-bule">
                                <img src="skin/Default/images/ui-content-ico/list.png" /> 客户管理</span>
                            <br/>
                            <s:iterator id="modul" value="actionList.get(0).moduleList" status="status">
                                <input type="checkbox" name="moduleIds" id="moduleIds" class="mb15 mr8"
                                value="${modul.moduleId}" ${modul.isCheck}/>
                                ${modul.moduleNameChinese} &nbsp;&nbsp;&nbsp;&nbsp;
                                <s:if test="#status.index % 5 ==4 ">
                                    <br/>
                                </s:if>
                            </s:iterator>
                            <br/>
                            <span class="color-bule"><img src="skin/Default/images/ui-content-ico/list.png" /> 销售管理</span>
                            <br/>
                            <s:iterator id="modul" value="actionList.get(1).moduleList" status="status">
                                <input type="checkbox" name="moduleIds" id="moduleIds" class="mb15 mr8"
                                value="${modul.moduleId}" ${modul.isCheck}/>
                                ${modul.moduleNameChinese} &nbsp;&nbsp;&nbsp;&nbsp;
                                <s:if test="#status.index % 5 ==4 ">
                                    <br/>
                                </s:if>
                            </s:iterator>
                            <br/>
                            <span class="color-bule"><img src="skin/Default/images/ui-content-ico/list.png" />订单合同</span>
                            <br/>
                            <s:iterator id="modul" value="actionList.get(2).moduleList" status="status">
                                <input type="checkbox" name="moduleIds" id="moduleIds" class="mb15 mr8"
                                value="${modul.moduleId}" ${modul.isCheck}/>
                                ${modul.moduleNameChinese} &nbsp;&nbsp;&nbsp;&nbsp;
                                <s:if test="#status.index % 5 ==4 ">
                                    <br/>
                                </s:if>
                            </s:iterator>
                            <br/>
                            <span class="color-bule">
                                <img src="skin/Default/images/ui-content-ico/list.png" />
                                绩效合同
                            </span>
                            <br/>
                            <s:iterator id="modul" value="actionList.get(3).moduleList" status="status">
                                <input type="checkbox" name="moduleIds" id="moduleIds" class="mb15 mr8"
                                value="${modul.moduleId}" ${modul.isCheck}/>
                                ${modul.moduleNameChinese} &nbsp;&nbsp;&nbsp;&nbsp;
                                <s:if test="#status.index % 5 ==4 ">
                                    <br/>
                                </s:if>
                            </s:iterator>
                            <br/>
                            <span class="color-bule">
                                <img src="skin/Default/images/ui-content-ico/list.png" />
                                协同办公
                            </span>
                            <br/>
                            <s:iterator id="modul" value="actionList.get(4).moduleList" status="status">
                                <input type="checkbox" name="moduleIds" id="moduleIds" class="mb15 mr8"
                                value="${modul.moduleId}" ${modul.isCheck}/>
                                ${modul.moduleNameChinese} &nbsp;&nbsp;&nbsp;&nbsp;
                                <s:if test="#status.index % 5 ==4 ">
                                    <br/>
                                </s:if>
                            </s:iterator>
                            <br/>
                            <span class="color-bule">
                                <img src="skin/Default/images/ui-content-ico/list.png" />
                                统计分析
                            </span>
                            <br/>
                            <s:iterator id="modul" value="actionList.get(5).moduleList" status="status">
                                <input type="checkbox" name="moduleIds" id="moduleIds" class="mb15 mr8"
                                value="${modul.moduleId}" ${modul.isCheck}/>
                                ${modul.moduleNameChinese} &nbsp;&nbsp;&nbsp;&nbsp;
                                <s:if test="#status.index % 5 ==4 ">
                                    <br/>
                                </s:if>
                            </s:iterator>
                            <br/>
                            <span class="color-bule">
                                <img src="skin/Default/images/ui-content-ico/list.png" />
                                系统管理
                            </span>
                            <br/>
                            <s:iterator id="modul" value="actionList.get(6).moduleList" status="status">
                                <input type="checkbox" name="moduleIds" id="moduleIds" class="mb15 mr8"
                                value="${modul.moduleId}" ${modul.isCheck}/>
                                ${modul.moduleNameChinese} &nbsp;&nbsp;&nbsp;&nbsp;
                                <s:if test="#status.index % 5 ==4 ">
                                    <br/>
                                </s:if>
                            </s:iterator>
                        </td>
                    </tr>
                </table>
                <div class="ui-button-big center mt10 mb50">
                    <a href="#" onclick="check();">
                        确定
                    </a>
                </div>
            </s:form>
            <div class="clear">
            </div>
        </div>
    </body>

</html>