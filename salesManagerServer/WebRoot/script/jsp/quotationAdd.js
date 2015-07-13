$(document).ready(function(){  
    $("#submit").click(function(checkEvent){
    	addQuotation();
    });
    $("#cancel").click(function(checkEvent){
    	checkEvent.preventDefault();
    	history.back();
    });
});

var jobline = -1;
function addRow(fNumber, fName, fModel, fUnitID, measureUnitName, fItemId, minQty, packModel){
	$("#newProduct").attr('disabled', true);
	jobline+=1;
	var productTable = $('#productTable'); 
	console.log("<tr><td><input type='hidden' name='products[" + jobline + "].productCode' value='" + fNumber + "'/>" + fNumber + "</td>" + 
	    	"<td><input type='hidden' name='products[" + jobline + "].productName' value='" + fName + "'/><input type='hidden' name='products[" + jobline + "].unitId' value='" + fUnitID + "'/>" + fName + "</td>" + 
	    	"<td><input type='hidden' name='products[" + jobline + "].productModel' value='" + fModel + "'/><input type='hidden' name='products[" + jobline + "].itemId' value='" + fItemId + "'/>" + fModel + "</td>" + 
	    	"<td><input type='hidden' name='products[" + jobline + "].minQty' value='" + minQty + "'/><input type='hidden' name='products[" + jobline + "].measureUnitName' value='" + measureUnitName + "'/>" + measureUnitName + "</td>" +
	    	"<td><input type='text' class='easyui-numberbox' precision='0' min='0' max='1000000000' required='true' name='products[" + jobline + "].num'/></td>" +  
	    	"<td><a href='#' onclick='$(this).parent().parent().remove();'>删除</a></td>" + "</tr>" +
	    	
	    	"<td><input type='hidden' name='products[" + jobline + "].packModel' value='" + packModel + "'/></td>");
	productTable.append("<tr><td><input type='hidden' name='products[" + jobline + "].productCode' value='" + fNumber + "'/>" + fNumber + "</td>" + 
	    	"<td><input type='hidden' name='products[" + jobline + "].productName' value='" + fName + "'/><input type='hidden' name='products[" + jobline + "].unitId' value='" + fUnitID + "'/>" + fName + "</td>" + 
	    	"<td><input type='hidden' name='products[" + jobline + "].productModel' value='" + fModel + "'/><input type='hidden' name='products[" + jobline + "].itemId' value='" + fItemId + "'/>" + fModel + "</td>" + 
	    	"<td><input type='hidden' name='products[" + jobline + "].minQty' value='" + minQty + "'/><input type='hidden' name='products[" + jobline + "].measureUnitName' value='" + measureUnitName + "'/>" + measureUnitName + "</td>" +
	    	"<td><input type='text' class='easyui-numberbox' precision='0' min='0' max='1000000000' required='true' name='products[" + jobline + "].num'/></td>" +  
	    	"<td><a href='#' onclick='$(this).parent().parent().remove();'>删除</a></td>" + "</tr>" +
	    	
	    	"<td><input type='hidden' name='products[" + jobline + "].packModel' value='" + packModel + "'/></td>");
    
    $.parser.parse();
    
	$("#newProduct").attr('disabled', false);
} 

function getProducts(){ 
	var array = window.showModalDialog($('#basepath').val() + "/product!list", "", "dialogWidth:800px; dialogHeight:500px; status:no;help:no");
	//for chrome
    if (array == undefined) {
    	array = window.returnValue;
    }
    //alert(array[0]+','+ array[1]+','+ array[2]+','+ array[3]+','+array[4]+','+array[6]+','+ array[7]);
	addRow(array[0], array[1], array[2], array[3], array[4],  array[5], array[6], array[7]);
	return false;
}

function addQuotation(){ 
	var item = $(":radio:checked"); 
	var len = item.length;
	if($('#productTable').find("tr").length <= 1){
		showDialog(BootstrapDialog.TYPE_WARNING,"输入提示" ,"请选择询价单物料信息！");
		return;
	}
	else if(len <= 0){
		showDialog(BootstrapDialog.TYPE_WARNING,"输入提示" ,"请选择是否开票！");
		return;
	}
	else if($('#quotation').form('validate')){
		$("#submit").attr("disabled", true);
		$('#quotation').submit();
	}
}

function getCustomer(){ 
	var array = window.showModalDialog($('#basepath').val() + "/customer!customerDistributionOpenWindow", "", "dialogWidth:800px; dialogHeight:530px; status:no;help:no");
	//for chrome
    if (array == undefined) {
    	array = window.returnValue;
    }
	$("#customerId").val(array[0]);
	$("#customerName").val(array[1]); 
}