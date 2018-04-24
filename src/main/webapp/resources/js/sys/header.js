$(function(){
	console.log('init user info ...');
	var userName = sessionStorage.getItem("nickName");
	$('#user-name').html(userName+' <span class="caret"></span>');
	
});