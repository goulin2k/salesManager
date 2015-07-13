<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>机构树</title>
<script type="text/javascript" src="<%=basePath %>script/common/jquery-1.4.2.min.js" ></script>
<script type="text/javascript" src="<%=basePath %>script/common/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath %>script/common/easyui/validate.js"></script> 
<script type="text/javascript" src="<%=basePath %>script/common/easyui/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath %>script/common/easyui/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>script/common/easyui/themes/default/easyui.css" />
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
</head>
<body>
    <ul id="departTree"></ul> 
</body>
</html>