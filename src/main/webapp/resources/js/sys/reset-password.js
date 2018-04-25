function resetPassword(){
	var oldPassWord = $('#oldPassWord').val();
	var newPassWord1 = $('#newPassWord1').val();
	var newPassWord2 = $('#newPassWord2').val();
	
	if (!oldPassWord) {
		$('#oldPassWord').focus();
		showtoastFromDiv("reset-form","原密码不能为空","inline-block",1000);
		return;
	}
	if (!newPassWord1) {
		$('#newPassWord1').focus();
		showtoastFromDiv("reset-form","新密码不能为空","inline-block",1000);
		return;
	}
	if (!newPassWord2) {
		$('#newPassWord2').focus();
		showtoastFromDiv("reset-form","再次确认新密码不能为空","inline-block",1000);
		return;
	}
	
	$('#userName').val(getCookieValue("userName"));
	
	oldPassWord = hex_md5(oldPassWord);
	$('#oldPassWord').val(oldPassWord);
	
	newPassWord1 = hex_md5(newPassWord1);
	$('#newPassWord1').val(newPassWord1);

	newPassWord2 = hex_md5(newPassWord2);
	$('#newPassWord2').val(newPassWord2);
	
	$.ajax({  
        cache: true,  
        type: "POST",  
        url:"/sys/user/resetPassword",  
        data:$('#reset-form').serialize(),
        async: false,  
        error: function(request) {  
            showtoastFromDiv("reset-form","请求服务器失败，请重新登录！","inline-block",1000);
        },  
        success: function(data) {
        	if (!data) {
        		showtoastFromDiv("reset-form","系统未知错误，请重试！","inline-block",1000);
    	    	$('#oldPassWord').val('');
    	    	$('#newPassWord1').val('');
    	    	$('#newPassWord2').val('');
        	} else {
        		if (data.code == 0) {//修改登录
        			showtoastFromDiv("reset-form","密码修改成功，正在跳转到首页,请稍后...","inline-block",2000);
        			window.setInterval(function(){ 
        				goTo("/home"); 
					},2000); 
            		console.log('密码修改成功');
            	} else {
            		showtoastFromDiv("reset-form",data.msg,"inline-block",3000);
            		$('#oldPassWord').val('');
        	    	$('#newPassWord1').val('');
        	    	$('#newPassWord2').val('');
            	}
        	}
        }  
    });
}

function goTo(url){
	location.href = url;
}