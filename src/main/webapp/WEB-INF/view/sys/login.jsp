<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="../common/tags.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>登录界面</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${contextPath}/resources/common/css/font-awesome.min.css" rel="stylesheet" type="text/css">
		<link href="${contextPath}/resources/common/css/bootstrap.min.css" rel="stylesheet" type="text/css">
		<link href="${contextPath}/resources/common/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css">
		<link href="${contextPath}/resources/common/css/templatemo_style.css" rel="stylesheet" type="text/css">
	</head>
	<body class="templatemo-bg-gray">
		<div class="container">
			<div class="col-md-12">
				<h1 class="margin-bottom-15">用 户 登 录</h1>
				<form class="form-horizontal templatemo-container templatemo-login-form-1 margin-bottom-30" id="login-form" role="form" action="#" method="post">				
			        <div class="form-group">
			          <div class="col-xs-12">		            
			            <div class="control-wrapper">
			            	<label for="username" class="control-label fa-label"><i class="fa fa-user fa-medium"></i></label>
			            	<input type="text" class="form-control" id="userName" name="userName" placeholder="Username">
			            </div>		            	            
			          </div>              
			        </div>
			        <div class="form-group">
			          <div class="col-md-12">
			          	<div class="control-wrapper">
			            	<label for="password" class="control-label fa-label"><i class="fa fa-lock fa-medium"></i></label>
			            	<input type="password" class="form-control" id="passWord" name="passWord" placeholder="Password">
			            </div>
			          </div>
			        </div>
			        <div class="form-group">
			          <div class="col-md-8">
			          	<div class="control-wrapper">
			            	<label for="captcha" class="control-label fa-label"><i class="fa fa-pencil fa-medium"></i></label>
			            	<input type="text" class="form-control" id="captcha" name="captcha" placeholder="Captcha">
			            </div>
			          </div>
			          <div class="col-md-4">
						<img id="captchaImage" alt="" src="${contextPath}/sys/user/captcha" style="width:100px;height:30px;">
			          </div>
			        </div>
			        <!-- <div class="form-group">
			          <div class="col-md-12">
		             	<div class="checkbox control-wrapper">
		                	<label>
		                  		<input type="checkbox" id="remember-account"> 记住用户名密码
	                		</label>
		              	</div>
			          </div>
			        </div> -->
			        <div class="form-group">
			          <div class="col-md-12">
			          	<div class="control-wrapper">
			          		<input type="button" value="登录" class="btn btn-info" onClick="return doLogin();">
			          		<a href="${contextPath}/forgot_password" class="text-right pull-right">忘记密码?</a>
			          	</div>
			          </div>
			        </div>
			        <hr>
			      </form>
			      <!-- <div class="text-center">
			      	<a href="mailto:zhouhao@chinazyjr.com?cc=zhushoudu@chinazyjr.com&subject=用户账号申请" class="templatemo-create-new">Create new account(人工审核) <i class="fa fa-arrow-circle-o-right"></i></a>	
			      </div> -->
			</div>
		</div>
		<script src="${contextPath}/resources/common/js/jquery.min.js"></script>
		<script src="${contextPath}/resources/common/js/bootstrap.min.js"></script>
		<script src="${contextPath}/resources/common/js/toast.js"></script>
		<script src="${contextPath}/resources/common/common.js"></script>
		<script src="${contextPath}/resources/common/canvas-particle.js"></script>
		<script src="${contextPath}/resources/js/sys/login.js" ></script>
	</body>
</html>