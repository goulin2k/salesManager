 
function onSuccess(data, status)  
{  
	data = $.trim(data);  
	$("#notification").text(data);  
}  

function onError(data, status)  
{  
	// handle an error  
}          

$(document).ready(function() {  
	$("#submit").click(function(){  

		var formData = $("#callAjaxForm").serialize();  

		$.ajax({  
			type: "POST",  
			url: "callajax.php",  
			cache: false,  
			data: formData,  
			success: onSuccess,  
			error: onError  
		});  

		return false;  
	});  
});  


 