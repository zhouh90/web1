<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="../common/tags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>注册账户</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<link href="${contextPath}/resources/common/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	<link href="${contextPath}/resources/common/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="${contextPath}/resources/common/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css">
	<link href="${contextPath}/resources/common/css/templatemo_style.css" rel="stylesheet" type="text/css">
	<style type="text/css">
		span.input-tips{
			display: none;
			color: #fe2617;
		}
	</style>
</head>
<body class="templatemo-bg-gray">
	<div class="container">
		<div class="col-md-12">
			<h1 class="margin-bottom-15">用 户 登注册</h1>
			<form class="form-horizontal templatemo-container templatemo-login-form-1 margin-bottom-30" id="reg-form" role="form" action="#" method="post">				
		        <div class="form-group">
		          <div class="col-xs-12">		            
		            <div class="control-wrapper">
		            	<label for="username" class="control-label fa-label"><i class="fa fa-user fa-medium"></i></label>
		            	<input type="text" class="form-control" id="userName" name="userName" placeholder="登录名，用来平台登录，一旦建立不可修改">
		            	<span class="input-tips" id="input-username">该用户名已被注册，换个试试</span>
		            </div>		            	            
		          </div>              
		        </div>
		        <div class="form-group">
		          <div class="col-xs-12">		            
		            <div class="control-wrapper">
		            	<label for="nickname" class="control-label fa-label"><i class="fa fa-comment fa-medium"></i></label>
		            	<input type="text" class="form-control" id="nickName" name="nickName" placeholder="第一印象很重要，起个响亮的名号吧">
		            	<span class="input-tips" id="input-nickname">走南闯北，总得有个名吧</span>
		            </div>		            	            
		          </div>              
		        </div>
		        <div class="form-group">
		          <div class="col-md-12">
		          	<div class="control-wrapper">
		            	<label for="password" class="control-label fa-label"><i class="fa fa-lock fa-medium"></i></label>
		            	<input type="password" class="form-control" id="passWord1" name="passWord1" placeholder="输入密码（不小于6位的大、小写字母，数字和特殊符号）">
		            	<span class="input-tips" id="input-pswd">密码格式不正确（必须包含包含字母和数字，且长度不小于6位）</span>
		            </div>
		          </div>
		        </div>
		        <div class="form-group">
		          <div class="col-md-12">
		          	<div class="control-wrapper">
		            	<label for="password" class="control-label fa-label"><i class="fa fa-lock fa-medium"></i></label>
		            	<input type="password" class="form-control" id="passWord2" name="passWord2" placeholder="再次确认密码">
		            	<span class="input-tips" id="confirm-pswd">两次密码输入不一致</span>
		            </div>
		          </div>
		        </div>
		        <div class="form-group">
		          <div class="col-md-12">
		          	<div class="control-wrapper">
		            	<label for="email" class="control-label fa-label"><i class="fa fa-envelope-square fa-medium"></i></label>
		            	<input type="text" class="form-control" id="email" name="email" placeholder="输入邮箱，可用来找回密码">
		            	<span class="input-tips" id="input-email">邮箱格式不正确</span>
		            </div>
		          </div>
		        </div>
		        <div class="form-group">
		          <div class="col-md-8">
		          	<div class="control-wrapper">
		            	<label for="captcha" class="control-label fa-label"><i class="fa fa-pencil fa-medium"></i></label>
		            	<input type="text" class="form-control" id="captcha" name="captcha" placeholder="图形验证码">
		            	<span class="input-tips" id="input-captcha">请输入图型验证码</span>
		            </div>
		          </div>
		          <div class="col-md-4">
					<img id="captchaImage" alt="" src="${contextPath}/sys/user/captcha" style="width:100px;height:30px;">
		          </div>
		        </div>
		        <div class="form-group">
		          <div class="col-md-12">
		          	<div class="control-wrapper">
		          		<input id="register-btn" type="button" value="注册" class="btn btn-info" onClick="return doRegister();">
		          		&nbsp;&nbsp;
		          		<a href="${contextPath}/login" class="text-right pull-right">已有账号？去登陆</a>
		          	</div>
		          </div>
		        </div>
	      	</form>
		</div>
	</div>
	<script src="${contextPath}/resources/common/js/jquery.min.js"></script>
	<script src="${contextPath}/resources/common/js/bootstrap.min.js"></script>
	<script src="${contextPath}/resources/common/js/toast.js"></script>
	<script src="${contextPath}/resources/common/js/md5.js"></script>
	<script src="${contextPath}/resources/common/common.js"></script>
	<script src="${contextPath}/resources/common/canvas-particle.js"></script>
	<script src="${contextPath}/resources/js/sys/create_new_account.js"></script>
</body>
</html>