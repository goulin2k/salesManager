
/* 显示BootstrapDialog,可选的对话框提示类型
	BootstrapDialog.TYPE_DEFAULT, 
	BootstrapDialog.TYPE_INFO, 
	BootstrapDialog.TYPE_PRIMARY,
	BootstrapDialog.TYPE_SUCCESS,
	BootstrapDialog.TYPE_WARNING,
	BootstrapDialog.TYPE_DANGER
*/
function showDialog(type, title, msg) {
	BootstrapDialog.show({
		type: type,
		title: title,
	    message: msg,
	    buttons: [{
	    	label: '关闭',
	    	action: function(dialogItself) {
	    		dialogItself.close();
	    	}
	    }]
	});
}