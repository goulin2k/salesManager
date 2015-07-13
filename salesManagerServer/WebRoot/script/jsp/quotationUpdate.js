
$(document).ready(function(){  
    $("#ok").click(function(checkEvent){
    	updateQuotation();
    });
    $("#cancel").click(function(checkEvent){
    	checkEvent.preventDefault();
    	history.back();
    });
});

function updateQuotation(){
	if($('#quotation').form('validate')) {
		BootstrapDialog.show({
		    title: '提交确认',
		    message: '是否确认提交询价回复信息？',
		    buttons: [{
		        label: '确定',
		        action: function(dialog) {
		        	$('#quotation').submit();
		        }
		    }, {
		        label: '取消',
		        action: function(dialog) {
		        	dialog.close();
			    	return false;
		        }
		    }]
		});
	}
//	submitForm();
} 

function submitForm() {
	if($('#quotation').form('validate')) {
		$('#quotation').submit();
	}
}