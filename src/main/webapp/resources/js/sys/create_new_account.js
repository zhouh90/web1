$(function(){
	var canReg = {
		name_pass:false,
		nick_pass:false,
		pw1_pass:false,
		pw2_pass:false,
		email_pass:false
		//captcha_pass:false
	}
	checkCanReg();
	
	$('#userName').blur(function () {
		var inputName = $(this).val();
		if(!inputName){
			$('#input-username').html('登录名不能为空');
			$('#input-username').css('color','red');
			$('#input-username').css('display','block');
			canReg.name_pass = false;
			return;
		}
		var isUsed = checkAccount(inputName);
		if(isUsed == -1){//已被占用
			$('#input-username').html('该用户已被注册，换个试试');
			$('#input-username').css('color','red');
			$('#input-username').css('display','block');
			canReg.name_pass = false;
		}else if(isUsed == 0){
			$('#input-username').html('该用户可以使用');
			$('#input-username').css('color','green');
			$('#input-username').css('display','block');
			canReg.name_pass = true;
		}
		checkCanReg();
	});
	
	
	$('#nickName').blur(function(){
		var nickName = $(this).val();
		if(nickName){
			$('#input-nickname').html('很响亮的名字');
			$('#input-nickname').css('color','green');
			$('#input-nickname').css('display','block');
			canReg.nick_pass = true;
		}else{
			$('#input-nickname').html('走南闯北，总得有个名吧');
			$('#input-nickname').css('color','red');
			$('#input-nickname').css('display','block');
			canReg.nick_pass = false;
		}
		checkCanReg();
	});
	
	
	$('#passWord1').blur(function(){
		var pawd1 = $(this).val();
		if(!pawd1){
			$('#input-pswd').html('密码不能为空');
			$('#input-pswd').css('color','red');
			$('#input-pswd').css('display','block');
			canReg.pw1_pass = false;
			return;
		}
		var reg = /^.*(?=.{6,})(?=.*\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&*? ]).*$/;
		if(reg.test(pawd1)){
			$('#input-pswd').html('密码符合安全要求');
			$('#input-pswd').css('color','green');
			$('#input-pswd').css('display','block');
			canReg.pw1_pass = true;
		}else{
			$('#input-pswd').html('密码不符合安全要求,必须包含大、小写字母，数字和特殊符号，且长度不小于6位');
			$('#input-pswd').css('color','red');
			$('#input-pswd').css('display','block');
			canReg.pw1_pass = false;
		}
		checkCanReg();
	});
	
	
	$('#passWord2').blur(function(){
		var pawd1 = $('#passWord1').val();
		var pawd2 = $(this).val();
		if(!pawd2){
			$('#confirm-pswd').html('密码不能为空');
			$('#confirm-pswd').css('color','red');
			$('#confirm-pswd').css('display','block');
			canReg.pw2_pass = false;
			return;
		}
		if(pawd1 == pawd2){
			$('#confirm-pswd').html('两次密码输入一致');
			$('#confirm-pswd').css('color','green');
			$('#confirm-pswd').css('display','block');
			canReg.pw2_pass = true;
		}else{
			$('#confirm-pswd').html('两次密码输入不一致');
			$('#confirm-pswd').css('color','red');
			$('#confirm-pswd').css('display','block');
			canReg.pw2_pass = false;
		}
		checkCanReg();
	});
	
	
//	$('#captcha').blur(function(){
//		var captcha = $(this).val();
//		if(!captcha){
//			$('#input-captcha').html('请输入图形验证码');
//			$('#input-captcha').css('color','red');
//			$('#input-captcha').css('display','block');
//			canReg.captcha_pass = false;
//		}else{
//			$('#input-captcha').css('display','none');
//			canReg.captcha_pass = true;
//		}
//		checkCanReg();
//	});
	
	
	$('#email').blur(function(){
		var email = $(this).val();
		var reg = /\w+[@]{1}\w+[.]\w+/;
		if(reg.test(email)){
			$('#input-email').html('邮箱合法');
			$('#input-email').css('color','green');
			$('#input-email').css('display','block');
			canReg.email_pass = true;
		}else{
			$('#input-email').html('邮箱格式非法');
			$('#input-email').css('color','red');
			$('#input-email').css('display','block');
			canReg.email_pass = false;
		}
		checkCanReg();
	});
	
	
	function checkCanReg(){
		if(canReg.name_pass && canReg.pw1_pass && canReg.pw2_pass && canReg.email_pass && canReg.nick_pass){
			$('#register-btn').attr('disabled',false);
		}else{
			$('#register-btn').attr('disabled',true);
		}
	}
	
	
	function checkAccount(inputName){
		var res = -2;
		$.ajax({
			cache: true,  
	        type: "POST",  
	        url:"/sys/role/checkAccount",
			data:{userName:inputName},
			async: false,
	        error: function(request) {  
	            showtoastFromDiv("reg-form","请求服务器失败，请重试！","inline-block",1000);
	        },  
	        success: function(data) {
	        	res = data.code;
	        }
		});
		return res;
	}
});

function doRegister(){
	var pw1 = $('#passWord1').val();
	var md5PassWord1 = hex_md5(pw1);
	$('#passWord1').val(md5PassWord1);

	var pw2 = $('#passWord2').val();
	var md5PassWord2 = hex_md5(pw2);
	$('#passWord2').val(md5PassWord2);
	
	var captcha = $('#captcha').val();
	if(!captcha){
		$('#captcha').focus();
		showtoastFromDiv("reg-form","验证码不能为空","inline-block",1000);
		return;
	}
	
	$.ajax({
		cache: true,  
        type: "POST",  
        url:"/sys/role/addUser",  
        data:$('#reg-form').serialize(),
        error: function(request) {  
            showtoastFromDiv("reg-form","请求服务器失败，请重新注册！","inline-block",1000);
        },  
        success: function(data) {
        	var code = data.code;
        	if(code == 0){
        		showtoastFromDiv("reg-form","注册成功，正在跳转到首页...","inline-block",2000);
        		window.setInterval(function(){ 
    				goTo("/home"); 
				},2000); 
        		addCookie("nickName", data.nickName, 30, "/");
        		addCookie("userName", userName, 30, "/");
        	}else{
        		showtoastFromDiv("reg-form","注册失败，请重试","inline-block",1000);
        	}
        }
	});
}