$(function(){
	var userName = sessionStorage.getItem("nickName");
	console.log("1:"+userName);
	if( typeof(userName) == 'undefined' || userName == null || userName == 'null'){
		userName = getCookieValue("nickName");
		var account  = getCookieValue("userName");
		console.log("2:"+userName+"---account:"+account);
	}
	$('#user-name').html(userName+' <span class="caret"></span>');
	
});