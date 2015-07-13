
function openDialog(url) {
	$("#modalRemote_body").load(url, '', function() {
    	$("#modalRemote").modal({
	        keyboard: true
	    });
    });
}