<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sales" uri="/WEB-INF/sales-util.tld"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>销售报价单</title>
<script type="text/javascript" src="<%=basePath %>script/common/jquery-1.4.2.min.js" ></script>
<script type="text/javascript" src="<%=basePath %>script/common/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath %>script/common/easyui/plugins/jquery.parser.js"></script>
<script type="text/javascript" src="<%=basePath %>script/common/easyui/validate.js"></script>
<script type="text/javascript" src="<%=basePath %>script/common/easyui/plugins/jquery.combotree.js"></script>
<script type="text/javascript" src="<%=basePath %>script/common/easyui/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath %>script/common/easyui/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>script/common/easyui/themes/default/easyui.css" /> 
<link href="<%=basePath %>skin/Default/css/base.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>skin/Default/css/global.css" rel="stylesheet" type="text/css" />  
<script type="text/javascript" src="<%=basePath %>script/tips/jquery.poshytip.js"></script> 
<link href="<%=basePath %>script/tips/tip-darkgray/tip-darkgray.css" rel="stylesheet" type="text/css" /> 
	<link rel="icon" href="<%=basePath %>/jsp/lhgcalendar/favicon.ico" type="image/x-icon"/>
	<link rel="shortcut icon" href="<%=basePath %>/jsp/lhgcalendar/favicon.ico" type="image/x-icon"/>
	<link href="<%=basePath %>/jsp/lhgcalendar/_doc/common.css" type="text/css" rel="stylesheet"/>
	<link href="<%=basePath %>/jsp/lhgcalendar/_doc/prettify/prettify.css" type="text/css" rel="stylesheet"/>
	<script type="text/javascript" src="<%=basePath %>/jsp/lhgcalendar/_doc/prettify/prettify.js"></script>
	<script type="text/javascript" src="<%=basePath %>/jsp/lhgcalendar/lhgcalendar.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>/jsp/lhgcalendar/_demo/demo.js"></script>
<script type="text/javascript"> 
/** 
* 时间对象的格式化 
*/  
Date.prototype.format = function(format)  
{  
/* 
* format="yyyy-MM-dd hh:mm:ss"; 
*/  
var o = {  
"M+" : this.getMonth() + 1,  
"d+" : this.getDate(),  
"h+" : this.getHours(),  
"m+" : this.getMinutes(),  
"s+" : this.getSeconds(),  
"q+" : Math.floor((this.getMonth() + 3) / 3),  
"S" : this.getMilliseconds()  
}  
  
if (/(y+)/.test(format))  
{  
format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4  
- RegExp.$1.length));  
}  
  
for (var k in o)  
{  
if (new RegExp("(" + k + ")").test(format))  
{  
format = format.replace(RegExp.$1, RegExp.$1.length == 1  
? o[k]  
: ("00" + o[k]).substr(("" + o[k]).length));  
}  
}  
return format;  
} 
$(function(){
    $('#fDate').calendar(); 
	$('#img').calendar({ id:'#cal3' });
	$('#fDate').val(new Date().format("yyyy-MM-dd"));
});
var jobline = -1;
function addRow(fNumber, fName, fModel, fUnitID, measureUnitName, fItemID){
	$("#newProduct").attr('disabled', true);
	jobline+=1;
	var productTable = $('#productTable');  
    productTable.append("<tr><td id='demo-tip-darkgray' title=" + fName + "+" + fModel + "><input type='hidden' name='orfqEntrys[" + jobline + "].fItemID' value='" + fItemID + "'/>" + fNumber + "</td>" + 
    	//"<td><input type='hidden' name='orfqEntrys[" + jobline + "].iCItemName' value='" + fName + "'/>" + fName + "</td>" + 
    	//"<td><input type='hidden' name='orfqEntrys[" + jobline + "].fModel' value='" + fModel + "'/>" + fModel + "</td>" + 
    	"<td><input style='width:100%' type='hidden' name='orfqEntrys[" + jobline + "].fUnitID' value='" + fUnitID + "'/>" + measureUnitName + "</td>" + 
    	"<td><input style='width:100%' type='text' class='easyui-numberbox w30' precision='0' min='0' max='100000' required='true' name='orfqEntrys[" + jobline + "].fAuxQty' onchange='changeAuxQty($(this).parent().parent())'/></td>" + 
    	"<td><input style='width:100%' type='text' class='easyui-numberbox w30' precision='2' min='0' max='10000000' required='true' name='orfqEntrys[" + jobline + "].fAuxPrice' onchange='changeAuxPrice($(this).parent().parent())'/></td>" + 
    	"<td><input style='width:100%' type='text' class='w30' name='orfqEntrys[" + jobline + "].fAuxPriceIncludeTax' disabled/></td>" + 
    	"<td><input style='width:100%' type='text' class='w30' name='orfqEntrys[" + jobline + "].fAmount' disabled/></td>" + 
    	"<td><input style='width:100%' type='text' class='easyui-numberbox w30' precision='2' min='0' max='100' name='orfqEntrys[" + jobline + "].fCess' value='17'/></td>" + 
    	"<td><input style='width:100%' type='text' class='easyui-numberbox w30' precision='2' min='0' max='100' name='orfqEntrys[" + jobline + "].FDescount' value='0' onchange='changeDescount($(this).parent().parent())'/></td>" + 
    	"<td><input style='width:100%' type='text' class='w30' name='orfqEntrys[" + jobline + "].fDiscountAmt' disabled/></td>" + 
    	"<td><input style='width:100%' type='text' class='w30' name='orfqEntrys[" + jobline + "].fAuxTaxPriceDiscount' disabled/></td>" + 
    	"<td><input style='width:100%' type='text' class='w30' name='orfqEntrys[" + jobline + "].fTaxAmount' disabled/></td>" +  
    	"<td><input style='width:100%' type='text' class='w30' name='orfqEntrys[" + jobline + "].fAmountIncludeTax' disabled/></td>" +   
    	"<td><input style='width:100%' type='text' class='easyui-validatebox w40' validType='maxLength[\"备注\",20]' name='orfqEntrys[" + jobline + "].fText'/></td>" +
    	"<td><a href='#' onclick='$(this).parent().parent().remove();'>删除</a></td>" + "</tr>");
    $.parser.parse();    
	$("#newProduct").attr('disabled', false);
} 

function getProducts(){ 
	var array = window.showModalDialog("<%=basePath %>/product!list", "", "dialogWidth:800px; dialogHeight:500px; status:no;help:no");
	//for chrome
    if (array == undefined) {
    	array = window.returnValue;
    }
	addRow(array[0], array[1], array[2], array[3], array[4], array[5]);
//	alert(array[0]+ array[1]+ array[2]+ array[3]+ array[4]);
	//$.ajax({
    //    type: "POST",
    //    url: "<%=basePath %>/product!jsonProduct?fNumber=" + $('#fItemId').attr("value"),  
    //    dataType: "json", 
    //    success: function (data) {
    //        addRow(data.jsonStr.fNumber, data.jsonStr.fName, data.jsonStr.fModel, data.jsonStr.FUnitID, data.jsonStr.measureUnitName);
    //    }
	//}); 
}

function getCustomer(){ 
	var array = window.showModalDialog("<%=basePath %>/customer!customerOpenWindow", "", "dialogWidth:800px; dialogHeight:530px; status:no;help:no");
	//for chrome
    if (array == undefined) {
    	array = window.returnValue;
    }
	$("#fCustID").val(array[0]);
	$("#customerName").val(array[1]); 
}

/**  
 * 加法运算，避免数据相加小数点后产生多位数和计算精度损失。  
 *   
 * @param num1加数1 | num2加数2  
 */ 
function numAdd(num1, num2) {  
    var baseNum, baseNum1, baseNum2;  
    try {  
        baseNum1 = num1.toString().split(".")[1].length;  
    } catch (e) {  
        baseNum1 = 0;  
    }  
    try {  
        baseNum2 = num2.toString().split(".")[1].length;  
    } catch (e) {  
        baseNum2 = 0;  
    }  
    baseNum = Math.pow(10, Math.max(baseNum1, baseNum2));  
    return (num1 * baseNum + num2 * baseNum) / baseNum;  
}

/**  
 * 加法运算，避免数据相减小数点后产生多位数和计算精度损失。  
 *   
 * @param num1被减数  |  num2减数  
 */
function numSub(num1, num2) {  
   var baseNum, baseNum1, baseNum2;  
   var precision;// 精度  
   try {  
       baseNum1 = num1.toString().split(".")[1].length;  
   } catch (e) {  
       baseNum1 = 0;  
   }  
   try {  
       baseNum2 = num2.toString().split(".")[1].length;  
   } catch (e) {  
       baseNum2 = 0;  
   }  
   baseNum = Math.pow(10, Math.max(baseNum1, baseNum2));  
   precision = (baseNum1 >= baseNum2) ? baseNum1 : baseNum2;  
   return ((num1 * baseNum - num2 * baseNum) / baseNum).toFixed(precision);  
}

/**  
 * 乘法运算，避免数据相乘小数点后产生多位数和计算精度损失。  
 *   
 * @param num1被乘数 | num2乘数  
 */ 
function numMulti(num1, num2) {  
   var baseNum = 0;  
   try {  
       baseNum += num1.toString().split(".")[1].length;  
   } catch (e) {  
   }  
   try {  
       baseNum += num2.toString().split(".")[1].length;  
   } catch (e) {  
   }  
   return Number(num1.toString().replace(".", "")) * Number(num2.toString().replace(".", "")) / Math.pow(10, baseNum);  
 }
 
/**  
 * 除法运算，避免数据相除小数点后产生多位数和计算精度损失。  
 *   
 * @param num1被除数 | num2除数  
 */ 
function numDiv(num1, num2) {  
   var baseNum1 = 0, baseNum2 = 0;  
   var baseNum3, baseNum4;  
   try {  
       baseNum1 = num1.toString().split(".")[1].length;  
   } catch (e) {  
       baseNum1 = 0;  
   }  
   try {  
       baseNum2 = num2.toString().split(".")[1].length;  
   } catch (e) {  
       baseNum2 = 0;  
   }  
   with (Math) {  
       baseNum3 = Number(num1.toString().replace(".", ""));  
       baseNum4 = Number(num2.toString().replace(".", ""));  
       return (baseNum3 / baseNum4) * pow(10, baseNum2 - baseNum1);  
   }  
}

function submitOrfq(){
	if ($('#orfq').form('validate')) {
		if($('#fCustID').val()==null || $('#fCustID').val()==""){
			alert("请选择购货单位");
		}
		else if($('#fPayCondition').val() == ""){ 
			alert("请选择付款条件");
		}
		else if($('#fCurrencyID').val() == ""){ 
			alert("请选择币别");
		}
		else if($('#fDeptID').val() == ""){ 
			alert("请选择部门");
		}
		else if($('#fMangerID').val() == ""){ 
			alert("请选择主管");
		}
		else if($('#fEmpID').val() == ""){ 
			alert("请选择业务员");
		}
		else if($('#productTable').find("tr").length <= 1){
			alert("请选择物料");
		}
		
		else{
			$("input[type=text]").attr('disabled', false);
			orfq.submit();
		} 
	}
}

function getExchangeRate(currencyId){
	$.ajax({
        type: "POST",
        url: "<%=basePath %>/orfq!jsonCurrency?fCurrencyID=" + currencyId,  
        dataType: "json", 
        success: function (data) { 
        	$("#fExchangeRate").attr('disabled', false);
           	$("#fExchangeRate").val(data.jsonStr.FExchangeRate);
           	$("#fExchangeRate").attr('disabled', true);
        }
	}); 
}

function changeAuxPriceIncludeTax(trVal){ 
	//修改价税合计FAuxPriceIncludeTax* FAuxQty
	var FAmountIncludeTax = trVal.find('td').eq(4).find('input').val() * trVal.find('td').eq(2).find('input').val();
	trVal.find('td').eq(11).find('input').attr('disabled', true);
	trVal.find('td').eq(11).find('input').val(FAmountIncludeTax);
	trVal.find('td').eq(11).find('input').attr('disabled', true);
	//修改税额（FAuxPriceIncludeTax-FAuxPrice）* FAuxQty
	var FTaxAmount = (trVal.find('td').eq(4).find('input').val()-trVal.find('td').eq(3).find('input').val()) * trVal.find('td').eq(2).find('input').val();
	trVal.find('td').eq(10).find('input').attr('disabled', true);
	trVal.find('td').eq(10).find('input').val(FTaxAmount);
	trVal.find('td').eq(10).find('input').attr('disabled', true);	
	//修改含税折扣后单价 FAuxPriceIncludeTax*（1-FDescount）
	var FAuxTaxPriceDiscount = trVal.find('td').eq(4).find('input').val()*(1-trVal.find('td').eq(7).find('input').val()/100);
	trVal.find('td').eq(9).find('input').attr('disabled', true);
	trVal.find('td').eq(9).find('input').val(FAuxTaxPriceDiscount);
	trVal.find('td').eq(9).find('input').attr('disabled', true);	
}

function changeAuxQty(trVal){
	//修改价税合计FAuxPriceIncludeTax* FAuxQty
	var FAmountIncludeTax = numMulti(trVal.find('td').eq(4).find('input').val(), trVal.find('td').eq(2).find('input').val()); 
	trVal.find('td').eq(11).find('input').attr('disabled', true);
	trVal.find('td').eq(11).find('input').val(FAmountIncludeTax);
	trVal.find('td').eq(11).find('input').attr('disabled', true);
	//修改税额（FAuxPriceIncludeTax-FAuxPrice）* FAuxQty
	var FTaxAmount = numMulti(numSub(trVal.find('td').eq(4).find('input').val(), trVal.find('td').eq(3).find('input').val()), trVal.find('td').eq(2).find('input').val());
	trVal.find('td').eq(10).find('input').attr('disabled', true);
	trVal.find('td').eq(10).find('input').val(FTaxAmount);
	trVal.find('td').eq(10).find('input').attr('disabled', true);	  
	//修改金额 FAuxPrice * FAuxQty
	var FAmount = numMulti(trVal.find('td').eq(3).find('input').val(), trVal.find('td').eq(2).find('input').val()); 
	trVal.find('td').eq(5).find('input').attr('disabled', true);
	trVal.find('td').eq(5).find('input').val(FAmount);
	trVal.find('td').eq(5).find('input').attr('disabled', true);	 
	//修改折扣金额 FAuxPrice * FAuxQty * FDescount
	var FAmount = numMulti(numMulti(trVal.find('td').eq(3).find('input').val(), trVal.find('td').eq(2).find('input').val()), numDiv(trVal.find('td').eq(7).find('input').val(), 100));
	trVal.find('td').eq(8).find('input').attr('disabled', true);
	trVal.find('td').eq(8).find('input').val(FAmount);
	trVal.find('td').eq(8).find('input').attr('disabled', true);	
	//修改含税折扣后单价 FAuxPriceIncludeTax*（1-FDescount）
	var FAuxTaxPriceDiscount = trVal.find('td').eq(4).find('input').val()*(1-trVal.find('td').eq(7).find('input').val()/100);
	trVal.find('td').eq(9).find('input').attr('disabled', true);
	trVal.find('td').eq(9).find('input').val(FAuxTaxPriceDiscount);
	trVal.find('td').eq(9).find('input').attr('disabled', true);	
}

function changeAuxPrice(trVal){ 
	//修改含税单价 fAuxPrice * (1 + fCess/100)
	var FAuxPriceIncludeTax = numMulti(trVal.find('td').eq(3).find('input').val(), numAdd(1, numDiv(trVal.find('td').eq(6).find('input').val(), 100)));
	trVal.find('td').eq(4).find('input').attr('disabled', true);
	trVal.find('td').eq(4).find('input').val(FAuxPriceIncludeTax);
	trVal.find('td').eq(4).find('input').attr('disabled', true);
	//修改价税合计FAuxPriceIncludeTax* FAuxQty
	var FAmountIncludeTax = numMulti(trVal.find('td').eq(4).find('input').val(), trVal.find('td').eq(2).find('input').val()); 
	trVal.find('td').eq(11).find('input').attr('disabled', true);
	trVal.find('td').eq(11).find('input').val(FAmountIncludeTax);
	trVal.find('td').eq(11).find('input').attr('disabled', true);
	//修改税额（FAuxPriceIncludeTax-FAuxPrice）* FAuxQty
	var FTaxAmount = numMulti(numSub(trVal.find('td').eq(4).find('input').val(), trVal.find('td').eq(3).find('input').val()), trVal.find('td').eq(2).find('input').val());
	trVal.find('td').eq(10).find('input').attr('disabled', true);
	trVal.find('td').eq(10).find('input').val(FTaxAmount);
	trVal.find('td').eq(10).find('input').attr('disabled', true);	 
	//修改金额 FAuxPrice * FAuxQty
	var FAmount = numMulti(trVal.find('td').eq(3).find('input').val(), trVal.find('td').eq(2).find('input').val());
	trVal.find('td').eq(5).find('input').attr('disabled', true);
	trVal.find('td').eq(5).find('input').val(FAmount);
	trVal.find('td').eq(5).find('input').attr('disabled', true);	 
	//修改折扣金额 FAuxPrice * FAuxQty * FDescount
	var FAmount = numMulti(numMulti(trVal.find('td').eq(3).find('input').val(), trVal.find('td').eq(2).find('input').val()), numDiv(trVal.find('td').eq(7).find('input').val(), 100));
	trVal.find('td').eq(8).find('input').attr('disabled', true);
	trVal.find('td').eq(8).find('input').val(FAmount);
	trVal.find('td').eq(8).find('input').attr('disabled', true);	
	//修改含税折扣后单价 FAuxPriceIncludeTax*（1-FDescount）
	var FAuxTaxPriceDiscount = trVal.find('td').eq(4).find('input').val()*(1-trVal.find('td').eq(7).find('input').val()/100);
	trVal.find('td').eq(9).find('input').attr('disabled', true);
	trVal.find('td').eq(9).find('input').val(FAuxTaxPriceDiscount);
	trVal.find('td').eq(9).find('input').attr('disabled', true);	
}

function changeDescount(trVal){  
	//修改含税折扣后单价 FAuxPriceIncludeTax*（1-FDescount）
	var FAuxTaxPriceDiscount = numMulti(trVal.find('td').eq(4).find('input').val(), numSub(1, numDiv(trVal.find('td').eq(7).find('input').val(), 100)));
	trVal.find('td').eq(9).find('input').attr('disabled', true);
	trVal.find('td').eq(9).find('input').val(FAuxTaxPriceDiscount);
	trVal.find('td').eq(9).find('input').attr('disabled', true);	
	//修改折扣金额 FAuxPrice * FAuxQty * FDescount
	var FAmount = numMulti(numMulti(trVal.find('td').eq(3).find('input').val(), trVal.find('td').eq(2).find('input').val()), numDiv(trVal.find('td').eq(7).find('input').val(), 100));
	trVal.find('td').eq(8).find('input').attr('disabled', true);
	trVal.find('td').eq(8).find('input').val(FAmount);
	trVal.find('td').eq(8).find('input').attr('disabled', true);	
}
</script>
</head>
<body class="ui-lv2bg">
	<div class="ui-content-title"><p><img src="skin/Default/images/ui-content-ico/tree.png" />销售报价单管理</p></div>
<form id="orfq" name="orfq" action="<%=basePath %>orfq!add" method="post">
<div class="ui-table ml15">
<table class="ui-table-one" cellspacing="2" cellpadding="0" >
  <tr> 
    <td  class="ui-table-title">单据编号：</td>
    <td><input type="text" name="orfq.fBillNo" id="fBillNo" value="<s:property value="orfq.fBillNo"/>" disabled/></td>
    <td  class="ui-table-title">日期：</td>
    <td><input class="ui-com-search-year" id="fDate" name="orfq.fdate" /></td>
  </tr>
  <tr>
    <td  class="ui-table-title">购货单位：</td>
    <td><input type="hidden" name="orfq.fCustID" id="fCustID" value="" />
    <input type="text" id="customerName" value="" onClick="getCustomer();"/></td>
    <td  class="ui-table-title">付款条件：</td>
    <td><s:select theme="simple" id="fPayCondition" name="orfq.fPayCondition" listKey="fId" listValue="fName" headerKey="" headerValue="请选择付款条件" list="payConditionList" /></td>
  </tr>
  <tr>
    <td  class="ui-table-title">币别：</td>
    <td><s:select theme="simple" id="fCurrencyID" name="orfq.fCurrencyID" listKey="fCurrencyID" listValue="fNumber" list="currencyList" required="true" headerKey="" headerValue="请选择币别" onchange="getExchangeRate(this.value);" /></td>
    <td  class="ui-table-title">汇率：</td>
    <td><input type="text" id="fExchangeRate" name="orfq.fExchangeRate" disabled/></td>
  </tr>
  <tr>
    <td  class="ui-table-title">开票方式：</td>
    <td><s:select theme="simple" id="invoiceTypeId" name="orfq.invoiceTypeId" listKey="typeId" listValue="name" list="invoiceTypeList" required="true" headerKey="" headerValue="请选择开票方式" /></td>
    <td  class="ui-table-title">运输方式：</td>
    <td><s:select theme="simple" id="transTypeId" name="orfq.transTypeId" listKey="typeId" listValue="name" list="invoiceTypeList" required="true" headerKey="" headerValue="请选择运输方式"/></td>
  </tr>
  <tr>
    <td  class="ui-table-title">部门：</td>
    <td class="ui-table-input-r"><s:select theme="simple" id="fDeptID" name="orfq.fDeptID" listKey="departId" listValue="name" headerKey="" headerValue="请选择部门" list="departList" /></td>
    <td  class="ui-table-title">主管：</td>
    <td><s:select theme="simple" id="fMangerID" name="orfq.fMangerID" listKey="staffId" listValue="name" headerKey="" headerValue="请选择主管" list="staffList" /></td>
  </tr> 
  <tr>
    <td  class="ui-table-title">业务员：</td>
    <td><s:select theme="simple" id="fEmpID" name="orfq.fEmpID" listKey="staffId" listValue="name" headerKey="" headerValue="请选择业务员" list="staffList" /></td>
    <td  class="ui-table-title">制单人：</td>
    <td><s:property value="orfq.billUserName"/></td>
  </tr> 
  <tr>
    <td  class="ui-table-title">选择物料：</td>
    <td><div class="ui-button-big fl ">
    	<a href="#" id="newProduct" class="ml10 fr" onClick="getProducts();">选择物料</a></div></td>
  </tr> 
  <tr>
    <td  class="ui-table-title">单据体：</td>
    <td colspan="3">
    <!-- ==============嵌入的子表格样式================-->
    <table id="productTable" class="ui-table-4" cellspacing="2" cellpadding="0" >
      <tr class="ui-table-title-4">
        <td class="td1">物料代码</td>
        <!-- <td class="td1">物料名称</td>
        <td class="td1">规格型号</td> -->
        <td class="td2">单位</td>
        <td class="td2">数量</td>
        <td class="td2">单价</td>
        <td class="td1">含税单价</td>
        <td class="td2">金额</td>
        <td>税率(%)</td> 
        <td>折扣率(%)</td>
        <td>折扣额</td>
        <td>含税折扣后单价</td> 
        <td>税额</td>
        <td class="td1">价税合计</td> 
        <td>备注</td> 
        <td>操作</td> 
      </tr>
    </table>

    </td>
  </tr>  
</table>
</div>
<div class="ui-button-big center "><a href="#" onClick="submitOrfq();">确定</a></div>
</form> 
</body>
</html>