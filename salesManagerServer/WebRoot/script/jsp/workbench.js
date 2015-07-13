//dock menu JS options
//鱼眼效果
	$(document).ready(	
		function()
		{
			$('#dock2').Fisheye(
			{
				maxWidth: 30,
				items: 'a',
				itemsText: 'span',
				container: '.dock-container2',
				itemWidth: 80,
				proximity: 120,
				alignment : 'left',
				valign: 'bottom',
				halign : 'center'
			});
			
			$.pnotify.defaults.styling = "bootstrap3";
			
	});
		

	//跳转到工作台
	function gotoIndex(actionId){
			$.ajax({
				 type: "POST",
				 url: "workbench!gotoIndex",
				 data:"actionId="+actionId,
				 dataType:'json',
				 cache: false,
				 async: true,
				 success: function(data){
		             var ul = "<ul>";
				             $.each(data,function(i,moduleList){
				            
				            ul += "<li><a target='_blank' href='"+moduleList.moduleUrl+"'><img  src='skin/Default/images/ui-content-ico/"+moduleList.moduleNameEnglish+".png' /><p>"+moduleList.moduleNameChinese+"</p></a></li>";

				            });
				            ul += "</ul>";
				            $("#getModule").html(ul);
							      var options = {};							      
							      // run the effect
							      $("#getModule").show( "slide", options, 5000);
				            return false;
				            
				 }
		    });
		}
		
		
		
		function changeList(actionId) {

				//alert(vicNameTmp);
				//var $form = $("#changeList");
					$.ajax({
						 type: "POST",
						 url: "workbench!changeList",
						 data:"actionId="+actionId,
						 dataType:'json',
						 cache: false,
						 async: true,
						 success: function(data){
				             var ul = "<ul>";
						             $.each(data,function(i,moduleList){
						            
						            ul += "<li><a target='_blank' href='"+moduleList.moduleUrl+"'><img  src='skin/Default/images/ui-content-ico/"+moduleList.moduleNameEnglish+".png' /><p>"+moduleList.moduleNameChinese+"</p></a></li>";
		
						            });
						            ul += "</ul>";
						            $("#getModule").html(ul);
									      var options = {};							      
									      // run the effect
									      $("#getModule").show( "slide", options, 5000);
		      
						            return false;
						            
						 }
						 
						 
				    });
				
		 
		}
		
		//消息弹出框
	       function showMsg(msgContent){
	    	   show_stack_bottomright('info', msgContent);
			}
   		/*********** Custom Stacks ***********
			* A stack is an object which Pines Notify uses to determine where
			* to position notices. A stack has two mandatory variables, dir1
			* and dir2. dir1 is the first direction in which the notices are
			* stacked. When the notices run out of room in the window, they
			* will move over in the direction specified by dir2. The directions
			* can be "up", "down", "right", or "left". Stacks are independent
			* of each other, so a stack doesn't know and doesn't care if it
			* overlaps (and blocks) another stack. The default stack, which can
			* be changed like any other default, goes down, then left. Stack
			* objects are used and manipulated by Pines Notify, and therefore,
			* should be a variable when passed. So, calling something like
			*
			* $.pnotify({stack: {"dir1": "down", "dir2": "left"}});
			*
			* will NOT work. It will create a notice, but that notice will be
			* in its own stack and may overlap other notices.
			*/
		var stack_topleft = {"dir1": "down", "dir2": "right", "push": "top"};
		var stack_bottomleft = {"dir1": "right", "dir2": "up", "push": "top"};
		var stack_custom = {"dir1": "right", "dir2": "down"};
		var stack_custom2 = {"dir1": "left", "dir2": "up", "push": "top"};
		var stack_bar_top = {"dir1": "down", "dir2": "right", "push": "top", "spacing1": 0, "spacing2": 0};
		var stack_bar_bottom = {"dir1": "up", "dir2": "right", "spacing1": 0, "spacing2": 0};
		/*********** Positioned Stack ***********
			* This stack is initially positioned through code instead of CSS.
			* This is done through two extra variables. firstpos1 and firstpos2
			* are pixel values, relative to a viewport edge. dir1 and dir2,
			* respectively, determine which edge. It is calculated as follows:
			*
			* - dir = "up" - firstpos is relative to the bottom of viewport.
			* - dir = "down" - firstpos is relative to the top of viewport.
			* - dir = "right" - firstpos is relative to the left of viewport.
			* - dir = "left" - firstpos is relative to the right of viewport.
			*/
		var stack_bottomright = {"dir1": "up", "dir2": "left", "firstpos1": 25, "firstpos2": 25};
		
		function show_stack_bottomright(type, msgContent) {
			var opts = {
					title: "消息提醒",
					text: msgContent,
					addclass: "custom stack-bottomright",
					stack: stack_bottomright,
					animation: 'none' ,
					icon: "glyphicon glyphicon-envelope"
				};
				switch (type) {
				case 'error':
					opts.title = "消息提醒";
					opts.text = msgContent;
					opts.type = "error";
					break;
				case 'info':
					opts.title = "消息提醒";
					opts.text = msgContent;
					opts.type = "info";
					
					break;
				case 'success':
					opts.title = "消息提醒";
					opts.text = msgContent;
					opts.type = "success";
					break;
				}
				$.pnotify(opts);
				
		};
		
	function msgPush2() {
		$.ajax({
			 type: "POST",
			 url: "info!msgPush2",
			 dataType:'html',
			 cache: false,
			 async: true,
			 success: function(data){
			 	
			 	if(data != "") {
	             	showMsg(data);
	            }
			     return false;        
			 }
	    });
		
	}

	
		
	msgPush2();
	setInterval("msgPush2()",5*60*1000);		//每5min刷新消息
			
