
try{
	$('html').click(function() {$('#settings-box').removeClass("open"); });
	$('.settings-btn, #settings-box').click(function(e){ e.stopPropagation(); });
}catch(exception){
	console.log("exception: "+exception);
}

$(document).ready(function(){	
	$('#settings-btn').click(function(){$('#settings-box').addClass("open"); } ); 
	$('#settings-close').click(function(){$('#settings-box').removeClass("open");} ); 
});


$('#right-sidebar-fixed').change(function() {
	$("#page-wrapper").toggleClass("sidebar-content");
	return;
});

$('#white-top-header').change(function() {
	$("nav.navbar.navbar-static-top").toggleClass("white-bg");
	return;
});

$('#navbar-closed').change(function() {
	$("body").toggleClass("canvas-menu");
	
	if($(this).is(":checked")) {
		$(".navbar-closed").html('<a class="close-canvas-menu"><i class="fa fa-times"></i></a>');
		$('.close-canvas-menu').click( function() {
		    $("body").toggleClass("mini-navbar");
		    SmoothlyMenu();
		});
      return;
    }
	$(".navbar-closed").html('');
});

$(document).ready(function(){
	var numbFriend = $("#p_p_id_1_WAR_chatportlet_ .chat-bar .buddy-list .trigger-name").html();
	if(numbFriend != undefined)
	{
		var numbInt= numbFriend.replace( /^\D+/g, '');
		numbInt= parseInt(numbInt);
		if(numbInt >=0 ){
			numbInt = "<span class='badge badge-warning pull-right'>"+ numbInt +"</span>";
			$("#p_p_id_1_WAR_chatportlet_ .chat-bar .buddy-list .trigger-name").html(numbInt);
		}
	}
});
