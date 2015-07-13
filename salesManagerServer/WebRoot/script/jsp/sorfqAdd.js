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
    productTable.append("<tr id='productTr" + jobline + "'><td id='demo-tip-darkgray' title=" + fName + "+" + fModel + "><input type='hidden' name='orfqEntrys[" + jobline + "].fItemID' value='" + fItemID + "'/><input type='hidden' name='orfqEntrys[" + jobline + "].iCNumber' value='" + fNumber + "'/><input type='hidden' name='orfqEntrys[" + jobline + "].iCItemName' value='" + fName + "'/><input type='hidden' name='orfqEntrys[" + jobline + "].fModel' value='" + fModel + "'/>" + fNumber + "</td>" + 
    	//"<td><input type='hidden' name='orfqEntrys[" + jobline + "].iCItemName' value='" + fName + "'/>" + fName + "</td>" + 
    	//"<td><input type='hidden' name='orfqEntrys[" + jobline + "].fModel' value='" + fModel + "'/>" + fModel + "</td>" + 
    	"<td><input style='width:100%' type='hidden' name='orfqEntrys[" + jobline + "].fUnitID' value='" + fUnitID + "'/><input type='hidden' name='orfqEntrys[" + jobline + "].unitName' value='" + measureUnitName + "'/>" + measureUnitName + "</td>" + 
    	"<td><input style='width:100%' type='text' class='easyui-numberbox w30' precision='0' min='0' max='100000' required='true' name='orfqEntrys[" + jobline + "].fAuxQty' onchange='changeAuxQty($(this).parent().parent())'/></td>" + 
    	"<td><input style='width:100%' type='text' class='easyui-numberbox w30' precision='2' min='0' max='10000000' required='true' name='orfqEntrys[" + jobline + "].fAuxPrice' onchange='changeAuxPrice($(this).parent().parent())'/></td>" + 
    	"<td><input style='width:100%' type='text' class='easyui-numberbox w30' precision='2' min='0' max='10000000' required='true' name='orfqEntrys[" + jobline + "].fAuxPriceIncludeTax'  onchange='changeAuxPriceIncludeTax($(this).parent().parent())'/></td>" + 
    	"<td><input style='width:100%' type='text' name='orfqEntrys[" + jobline + "].fAmount' disabled/></td>" + 
    	"<td><input style='width:100%' type='text' class='easyui-numberbox easyui-validatebox w30' required='true' precision='2' min='0' max='100' name='orfqEntrys[" + jobline + "].fCess' value='17' onchange='changeCess($(this).parent().parent())'/></td>" + 
    	"<td><input style='width:100%' type='text' class='easyui-numberbox easyui-validatebox w30' required='true' precision='0' min='0' max='100' name='orfqEntrys[" + jobline + "].FDescount' value='0' onchange='changeDescount($(this).parent().parent())'/></td>" + 
    	"<td><input style='width:100%' type='text' name='orfqEntrys[" + jobline + "].fDiscountAmt' disabled/></td>" + 
    	"<td><input style='width:100%' type='text' name='orfqEntrys[" + jobline + "].fAuxTaxPriceDiscount' disabled/></td>" + 
    	"<td><input style='width:100%' type='text' name='orfqEntrys[" + jobline + "].fTaxAmount' disabled/></td>" +  
    	"<td><input style='width:100%' type='text' name='orfqEntrys[" + jobline + "].fAmountIncludeTax' disabled/></td>" +   
    	"<td><input style='width:100%' type='text' class='easyui-validatebox w40' required='true' validType='maxLength[\"货期\",50]' name='orfqEntrys[" + jobline + "].fText'/></td>" +
    	"<td><a href='#' onclick='$(this).parent().parent().remove();'>删除</a></td>" + "</tr>");
    var tid = "productTr" + jobline;
    $.parser.parse("#"+tid);    
	$("#newProduct").attr('disabled', false);
} 

function getProducts(){ 
	var array = window.showModalDialog($("#basepath").val() + "/product!list", "", "dialogWidth:800px; dialogHeight:500px; status:no;help:no");
	//for chrome
    if (array == undefined) {
    	array = window.returnValue;
    }
	addRow(array[0], array[1], array[2], array[3], array[4], array[5]);
//	showDialog(BootstrapDialog.TYPE_WARNING,"输入提示" ,array[0]+ array[1]+ array[2]+ array[3]+ array[4]);
	//$.ajax({
    //    type: "POST",
    //    url: $("#basepath").val() + "/product!jsonProduct?fNumber=" + $('#fItemId').attr("value"),  
    //    dataType: "json", 
    //    success: function (data) {
    //        addRow(data.jsonStr.fNumber, data.jsonStr.fName, data.jsonStr.fModel, data.jsonStr.FUnitID, data.jsonStr.measureUnitName);
    //    }
	//}); 
}

function getCustomer(){ 
	var array = window.showModalDialog($("#basepath").val() + "/customer!customerDistributionOpenWindow", "", "dialogWidth:800px; dialogHeight:530px; status:no;help:no");
	//for chrome
    if (array == undefined) {
    	array = window.returnValue;
    }
	
	
	$("#fCustID").val(array[0]);
	$("#customerName").val(array[1]);	
	
	$("#fPayCondition").val(array[9]);
	
	if($("#fPayCondition").val()>0) {
		$("#fPayCondition").attr("disabled", true);
	}else {
		$("#fPayCondition").attr("disabled", false);
	}
	$("#payCondition").val(array[10]);
	//setPay($("#fPayCondition"));
	
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
	
	if($('#productTable').find("tr").length <= 1){
		showDialog(BootstrapDialog.TYPE_WARNING,"输入提示" ,"请选择报价单物料信息！");
		checkEvent.preventDefault();
		return;
	}
	if ($('#orfq').form('validate')) {
		if($('#fCustID').val()==null || $('#fCustID').val()==""){
			showDialog(BootstrapDialog.TYPE_WARNING,"输入提示" ,"请选择购货单位");
		}
		else if($('#fPayCondition').val() == ""){ 
			showDialog(BootstrapDialog.TYPE_WARNING,"输入提示" ,"请选择付款条件");
		}
		else if($('#fCurrencyID').val() == ""){ 
			showDialog(BootstrapDialog.TYPE_WARNING,"输入提示" ,"请选择币别");
		}
		else if($('#invoiceTypeId').val() == ""){ 
			showDialog(BootstrapDialog.TYPE_WARNING,"输入提示" ,"请选择开票方式");
		}
		else if($('#transTypeId').val() == ""){ 
			showDialog(BootstrapDialog.TYPE_WARNING,"输入提示" ,"请选择运输方式");
		}
		else if($('#fDeptID').val() == ""){ 
			showDialog(BootstrapDialog.TYPE_WARNING,"输入提示" ,"请选择部门");
		}
		else if($('#fMangerID').val() == ""){ 
			showDialog(BootstrapDialog.TYPE_WARNING,"输入提示" ,"请选择主管");
		}
		else if($('#fEmpID').val() == ""){ 
			showDialog(BootstrapDialog.TYPE_WARNING,"输入提示" ,"请选择业务员");
		}
		else if($('#productTable').find("tr").length <= 1){
			showDialog(BootstrapDialog.TYPE_WARNING,"输入提示" ,"请选择物料");
		}
		else{
			//alert('ddd');
			$("input[type=text]").attr('disabled', false);
			$("#fPayCondition").attr("disabled", false);
			$("#saveButton").attr("href", "#");
			//alert($("#saveButton").attr("href"));
			orfq.submit();
			//this.close();
		} 
	}
}

function getExchangeRate(currencyId){
	$.ajax({
        type: "POST",
        url: $("#basepath").val() + "/orfq!jsonCurrency?fCurrencyID=" + currencyId.value,  
        dataType: "json", 
        success: function (data) { 
        	$("#fExchangeRate").attr('disabled', false);
           	$("#fExchangeRate").val(data.jsonStr.FExchangeRate);
           	$("#fExchangeRate").attr('disabled', true);
			var txt = currencyId.options[currencyId.selectedIndex].text; 
			$("#currencyName").val(txt);
        }
	}); 
}

function changeAuxPriceIncludeTax(trVal){ 
	//修改单价  FAuxPriceIncludeTax / (1 + fCess/100)
	var fAuxPrice = numDiv(trVal.find('td').eq(4).find('input').val(), numAdd(1, numDiv(trVal.find('td').eq(6).find('input').val(), 100))); 
	trVal.find('td').eq(3).find('input').val(fAuxPrice); 
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

function changeCess(trVal){ 
	//修改含税单价 fAuxPrice * (1 + fCess/100)
	var FAuxPriceIncludeTax = numMulti(trVal.find('td').eq(3).find('input').val(), numAdd(1, numDiv(trVal.find('td').eq(6).find('input').val(), 100))); 
	trVal.find('td').eq(4).find('input').val(FAuxPriceIncludeTax); 
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
	//修改含税折扣后单价 FAuxPriceIncludeTax*（1-FDescount）
	var FAuxTaxPriceDiscount = trVal.find('td').eq(4).find('input').val()*(1-trVal.find('td').eq(7).find('input').val()/100);
	trVal.find('td').eq(9).find('input').attr('disabled', true);
	trVal.find('td').eq(9).find('input').val(FAuxTaxPriceDiscount);
	trVal.find('td').eq(9).find('input').attr('disabled', true);	
}

function changeAuxPrice(trVal){ 
	//修改含税单价 fAuxPrice * (1 + fCess/100)
	var FAuxPriceIncludeTax = numMulti(trVal.find('td').eq(3).find('input').val(), numAdd(1, numDiv(trVal.find('td').eq(6).find('input').val(), 100)));
	trVal.find('td').eq(4).find('input').val(FAuxPriceIncludeTax); 
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

function setPay(text){
	var txt = text.options[text.selectedIndex].text;
	$("#payCondition").val(txt);
} 

function setDepart(text){
	var txt = text.options[text.selectedIndex].text;
	$("#departName").val(txt);
}

function setManger(text){
	var txt = text.options[text.selectedIndex].text;
	$("#managerName").val(txt);
}

function setEmployee(text){
	var txt = text.options[text.selectedIndex].text;
	$("#employeeName").val(txt);
}

function setInvoiceType(text){
	var txt = text.options[text.selectedIndex].text;
	$("#invoiceTypeName").val(txt);
}

function setTransType(text){
	var txt = text.options[text.selectedIndex].text;
	$("#transTypeName").val(txt);
}
