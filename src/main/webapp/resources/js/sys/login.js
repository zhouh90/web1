$(document).ready(function() {
	//监听docuemnt的onkeydown事件看是不是按了回车键
	$(document).keydown(function(event){
		event = event ? event : window.event;
		if (event.keyCode === 13){
			doLogin();
		}
	});
});

$('#captchaImage').click(function() {//点击刷新验证码
	$('#captchaImage').attr("src", "/sys/user/captcha?timestamp=" + (new Date()).valueOf());
});

window.onload = function() {
	initFormData();
	initBGImage();
}

function initFormData(){
	var userNameValue = getCookieValue("userName");
	var passWordValue = getCookieValue("passWord");
	var isRemember = getCookieValue("remember-account");
	if(isRemember == 'true'){
		if(typeof(userNameValue) != 'undefind' && userNameValue != '' && typeof(passWordValue) != 'undefind' && passWordValue != ''){
			$("#userName").val(userNameValue);
			$("#passWord").val(passWordValue);
			$("#remember-account").attr("checked", true);
		}
	}else{
		$("#userName").val('');
		$("#passWord").val('');
		$("#remember-account").attr("checked", false);
	}
}

function initBGImage(){
	var config = {
		vx : 4,
		vy : 4,
		height : 2,
		width : 2,
		count : 100,
		color : "121, 162, 185",
		stroke : "100, 200, 180",
		dist : 6000,
		e_dist : 20000,
		max_conn : 10
	}
	CanvasParticle(config);
}

function doLogin() {
	var userName = $('#userName').val();
	var passWord = $('#passWord').val();
	var validateCode = $('#captcha').val();
	var isRemember = $('#remember-account').is(':checked');
	//addCookie("remember-account",isRemember,7,"/");
	if (!userName) {
		$('#userName').focus();
		showtoastFromDiv("login-form","账号不能为空","inline-block",1000);
		return;
	}
	if (!passWord) {
		$('#passWord').focus();
		showtoastFromDiv("login-form","密码不能为空","inline-block",1000);
		return;
	}
	if (!validateCode) {
		$('#validateCode').focus();
		showtoastFromDiv("login-form","验证码不能为空","inline-block",1000);
		return;
	}
	showtoastFromDiv("login-form","正在登录,请稍后...","inline-block",500);
	passWord = hex_md5(passWord);
	$('#passWord').val(passWord);
	$.ajax({  
        cache: true,  
        type: "POST",  
        url:"/sys/user/doLogin",  
        data:$('#login-form').serialize(),
        async: false,  
        error: function(request) {  
            showtoastFromDiv("login-form","请求服务器失败，请重新登录！","inline-block",1000);
        },  
        success: function(data) {
        	if (!data) {
        		showtoastFromDiv("login-form","系统未知错误，请重新登录重试！","inline-block",1000);
        		//错误后刷新验证码
    	    	$('#captcha').val('');
    	    	$('#captchaImage').attr("src", "/sys/user/captcha?timestamp=" + (new Date()).valueOf());
        	} else {
        		if (data.code == 0) {//成功登录
            		if(isRemember){
//						addCookie("userName", userName, 7, "/");
//						addCookie("passWord", passWord, 7, "/");
                    }
            		location.href = "/home";
//            		sessionStorage.setItem("nickName",data.nickName);
//            		sessionStorage.setItem("userName",userName);
            		addCookie("nickName", data.nickName, 30, "/");
            		addCookie("userName", userName, 30, "/");
            		console.log('登陆成功');
            	} else {
            		showtoastFromDiv("login-form",data.msg,"inline-block",1000);
            		//错误后刷新验证码
            		$('#captcha').val('');
        	    	$('#captchaImage').attr("src", "/sys/user/captcha?timestamp=" + (new Date()).valueOf());
            	}
        	}
        }  
    });
	return false; // 必须返回false，否则表单会自己再做一次提交操作，并且页面跳转
}