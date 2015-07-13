<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>修改部门</title>
<script type="text/javascript" src="<%=basePath %>script/common/jquery-1.4.2.min.js" ></script>
<script type="text/javascript" src="<%=basePath %>script/common/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath %>script/common/easyui/validate.js"></script>
<script type="text/javascript" src="<%=basePath %>script/common/easyui/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath %>script/common/easyui/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>script/common/easyui/themes/default/easyui.css" />
<link href="<%=basePath %>skin/Default/css/base.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>skin/Default/css/global.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">
function updateDepartment () {
	if ($('#department').form('validate')) {
		department.submit();
	}
}
</script>
</head>
<body class="ui-lv2bg">
<div class="ui-content-title"><p><img src="skin/Default/images/ui-content-ico/set.png" />修改部门</p></div>
<div class="ui-content-SlaveInformation-small-s ml15 mt10 fl">
    	<div class="ui-content-SlaveInformation-title "><p><img src="skin/Default/images/ui-content-ico/tree.png" class="mr8"/>部门树</p></div>
            <div class="mt10 ml10">
            	<ul id="departTree"></ul>
        </div>
    </div>
<div class="ui-content-SlaveInformation-small-b ml15 mt10 fl">
	<div class="ui-content-title"><p><img src="skin/Default/images/ui-content-ico/tree.png" />部门列表管理</p></div>
<div class="ui-table ml15">
<form id="department" name="department" action="<%=basePath %>department!update" method="post">
<table class="ui-table-3" cellspacing="2" cellpadding="0" >
<caption><img src="skin/Default/images/ui-content-ico/people.png" />部门信息</caption> 
  <tr>
    <td class="ui-table-title">上级部门：</td>
    <td class="ui-table-input-r"><s:property value="department.parentName"/>
    <input type="hidden" name="department.parentId" value="<s:property value="department.parentId"/>"></input>
    <input type="hidden" name="department.departmentId" value="<s:property value="department.departmentId"/>"></input></td> 
  </tr> 
  <tr>
    <td class="ui-table-title">部门名称：</td>
    <td class="ui-table-input-r"><input type="text" name="department.name" value="<s:property value="department.name"/>" class="easyui-validatebox" required="true" validType="maxLength['部门名称', 50]" /></td> 
  </tr> 
  <tr>
    <td class="ui-table-title">联系人：</td>
    <td class="ui-table-input-r"><input type="text" name="department.linkMan" value="<s:property value="department.linkMan"/>" class="easyui-validatebox" validType="maxLength['联系人', 20]" /></td> 
  </tr> 
  <tr>
    <td class="ui-table-title">电话：</td>
    <td class="ui-table-input-r"><input type="text" name="department.linkPhone" value="<s:property value="department.linkPhone"/>" class="easyui-validatebox" validType="maxLength['电话', 30]" /></td> 
  </tr> 
  <tr>
    <td  class="ui-table-title">备注：</td>
    <td  class="ui-table-textarea">
  		<textarea name="department.comment" rows="4" class="easyui-validatebox" validType="maxLength['备注',200]"><s:property value="department.comment"/></textarea>
    </td>
  </tr>

</table>
</form>

<div class="ui-button-big center mb50"><a href="#" onClick="updateDepartment();">确定</a></div>

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