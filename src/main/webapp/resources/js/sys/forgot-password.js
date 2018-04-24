function sendEamil(){
	var userName = $('#username').val();
	var email = $('#email').val();
	console.log('userName:'+userName);
	console.log('email:'+email);
	if (!userName) {
		$('#username').focus();
		showtoastFromDiv("forgot-form","账号不能为空","inline-block",1000);
		return;
	}
	if (!email) {
		$('#email').focus();
		showtoastFromDiv("forgot-form","邮箱不能为空","inline-block",1000);
		return;
	}
	console.log($('#forgot-form').serialize());
	
	$.ajax({
        cache: true,
        type: "POST",
        url: "/sys/user/sendEmail",
        data: $('#forgot-form').serialize(),
        async: false,
        error: function(request) {
            showtoastFromDiv("forgot-form","请求服务器失败,稍后再试！","inline-block",2000);
        },
        success: function(data) {
        	if (!data) {
        		showtoastFromDiv("forgot-form","系统未知错误，请稍后重试！","inline-block",2000);
        	} else {
        		if (data.code == 0) {
        			showtoastFromDiv("forgot-form","验证码已发送,请注意查收邮箱！","inline-block",3000);
            	} else {
            		showtoastFromDiv("forgot-form",data.msg,"inline-block",1000);
            	}
        	}
        }
    });
	
}