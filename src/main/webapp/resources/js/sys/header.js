$(function(){
	var userName = getCookieValue("nickName");
	if( typeof(userName) == 'undefined' || userName == null || userName == 'null'){
		userName = "平台用户";
	}
	$('#user-name').html(userName+' <span class="caret"></span>');
	
});