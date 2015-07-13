<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>机构树</title>
<script type="text/javascript" src="<%=basePath %>script/common/jquery-1.4.2.min.js" ></script>
<script type="text/javascript" src="<%=basePath %>script/common/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath %>script/common/easyui/validate.js"></script> 
<script type="text/javascript" src="<%=basePath %>script/common/easyui/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath %>script/common/easyui/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>script/common/easyui/themes/default/easyui.css" />
</head>
<body class="ui-lv2bg">
<nav class="navbar navbar-default" role="navigation">
	    <ul class="nav navbar-nav">
	    	<li class="active"><a href="<%=basePath %>department!editNew?department.parentId=<s:property value="department.departmentId"/>"><span class="glyphicon glyphicon-star"></span>&nbsp;新增部门</a></li>
	    </ul>
    </nav>
<div class="ui-content-SlaveInformation-small-s ml15 mt10 fl">
        <div class="panel-heading crm-table-title"><B>部门树</B></div>
            <div class="mt10 ml10">
            	<ul id="departTree"></ul>
        </div>
    </div>
<div class="ui-content-SlaveInformation-small-b ml15 mt10 fl">
<div class="panel panel-default" id="searchResult" style="padding:2px;">
    <div class="panel-heading crm-table-title"><B>部门列表</B></div>
    <div class="ui-table-style1-1">
		<table class="table table-hover" cellspacing="0" cellpadding="0" >
		  <thead>
		    <th >部门名称</th>
		    <th >联系人</th>
		    <th >电话</th>
		    <th >备注</th>
		   <th >操作</th>
		  </thead>
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