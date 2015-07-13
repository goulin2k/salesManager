<%@ page language="java" import="com.sales.model.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sales" uri="/WEB-INF/sales-util.tld"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<htmlxmlns="http://www.w3.org/1999/xhtml">
<head>
<title> 部门信息 </title>

<script type="text/javascript" src="<%=basePath %>dwr/engine.js"></script>     
<script type="text/javascript" src="<%=basePath %>dwr/util.js"></script>
<script type="text/javascript" src="<%=basePath %>dwr/interface/msgPushManager.js"></script> 
<script type="text/javascript" src="<%=basePath %>script/common/jquery-1.4.2.min.js" ></script>
<script type="text/javascript" src="<%=basePath %>script/common/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath %>script/common/easyui/validate.js"></script> 
<script type="text/javascript" src="<%=basePath %>script/common/easyui/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" href="<%=basePath %>script/common/autocomplete/jquery.autocomplete.css" type="text/css"></link> 
<link href="skin/Default/css/base.css" rel="stylesheet" type="text/css" />
<link href="skin/Default/css/global.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>script/common/easyui/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>script/common/easyui/themes/default/easyui.css" />
</head>


<body class="ui-lv2bg">
	<div class="ui-content-title"><p><img src="skin/Default/images/ui-content-ico/list.png" />部门信息</p></div>
<div class="ui-content-SlaveInformation-small-s ml15 mt10 fl">
    	<div class="ui-content-SlaveInformation-title "><p><img src="skin/Default/images/ui-content-ico/tree.png" class="mr8"/>部门树</p></div>
            <div class="mt10 ml10">
            	<ul id="departTree"></ul>
        </div>
    </div>
<div class="ui-content-SlaveInformation-small-b ml15 mt10 fl">
	<div class="ui-content-title"><p><img src="skin/Default/images/ui-content-ico/tree.png" />部门列表管理</p></div>
		<div class="ui-content-subnav">
    	<ul>
        	<li><a href="<%=basePath %>department!editNew?department.parentId=<s:property value="department.departmentId"/>"><img src="skin/Default/images/ui-content-ico/add-tree.png" /><p>新建部门</p></a></li> 
        </ul>
    </div>
    
<div class="ui-table-style1-1">
	<table  cellspacing="0" cellpadding="0" >
	  <tr class="ui-table-style1-1-tr1">
	    <td class="td2">部门名称</td>
	    <td class="td3">联系人</td>
	    <td class="td4">电话</td>
	    <td class="td5">备注</td> 
	   <td>操作</td>
	  </tr>
		<!--      =================表格循环===============-->
	  <s:iterator value="departmentList" status="dl">
		<tr class="ui-table-style1-tr2">
		  <td><s:property value="name"/></td>
		  <td><s:property value="linkMan"/></td>
		  <td><s:property value="linkPhone"/></td>
		  <td><s:property value="comment"/></td>
		  <td><a href="<%=basePath %>department!show?department.departmentId=<s:property value="departmentId"/>"> 修改 </a></td>
		</tr>
	  </s:iterator>
	</table>
</div>
</div>
<script type="text/javascript"> 
     $(function(){    
            $('#departTree').tree({    
                checkbox: false,    
                url: 'department!tree',    
                onClick:function(node){   
                    window.location.href='department!list?department.departmentId=' + node.id;     
                }   
            });   
        });   
        function reload(){   
            $('#departTree').tree('reload');    
        }   
        function getChildNodes(){   
            var node = $('#departTree').tree('getSelected');    
            if (node){    
                var children = $('#departTree').tree('getChildNodes', node.target);    
                var s = '';    
                for(var i=0; i<children.length; i++){    
                    s += children[i].text + ',';    
                }   
                alert(s);   
            }   
        }   
        function getChecked(){   
            var nodes = $('#departTree').tree('getChecked');    
           var s = '';    
            for(var i=0; i<nodes.length; i++){    
                if (s != '') s += ',';    
               s += nodes[i].text;   
            }   
            alert(s);   
        }   
        function getSelected(){   
            var node = $('#departTree').tree('getSelected');    
           alert(node.text);   
        }   
        function collapse(){   
           var node = $('#departTree').tree('getSelected');    
           $('#departTree').tree('collapse',node.target);    
        }   
        function expand(){   
            var node = $('#departTree').tree('getSelected');    
            $('#departTree').tree('expand',node.target);    
        }   
        function collapseAll(){   
            $('#departTree').tree('collapseAll');    
        }   
        function expandAll(){   
           $('#departTree').tree('expandAll');    
        }   
        function append(){   
            var node = $('#departTree').tree('getSelected');    
            $('#departTree').tree('append',{    
                parent: node.target,   
                data:[{   
                    text:'new1',    
                    checked:true   
                },{   
                    text:'new2',    
                    state:'closed',    
                    children:[{   
                       text:'subnew1'   
                    },{   
                        text:'subnew2'   
                    }]   
               }]   
            });   
        }   
        function remove(){   
            var node = $('#departTree').tree('getSelected');    
            $('#departTree').tree('remove', node.target);    
       }   
        function update(){   
            var node = $('#departTree').tree('getSelected');    
            if (node){    
                node.text = '<span style="font-weight:bold">new text</span>';    
                node.iconCls = 'icon-save';    
               $('#departTree').tree('update', node);    
            }   
        }   
        function isLeaf(){   
            var node = $('#departTree').tree('getSelected');    
            var b = $('#departTree').tree('isLeaf', node.target);    
            alert(b)   
        }   
</script> 
</body>
</html>