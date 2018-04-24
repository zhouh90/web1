<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="../common/tags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>找回密码</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<link href="${contextPath}/resources/common/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	<link href="${contextPath}/resources/common/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="${contextPath}/resources/common/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css">
	<link href="${contextPath}/resources/common/css/templatemo_style.css" rel="stylesheet" type="text/css">
</head>
<body class="templatemo-bg-gray">
	<div class="container">
		<div class="col-md-12">
			<h1 class="margin-bottom-15">密码重置</h1>
			<form class="form-horizontal templatemo-forgot-password-form templatemo-container" id="forgot-form" role="form" action="#" method="post">	
				<div class="form-group">
		          <div class="col-md-12">
		          	请输入您的<span style="color:red">注册账号</span>以及注册时填写的<span style="color:red">邮箱地址</span>
		          </div>
		        </div>
		        <div class="form-group">
		          <div class="col-md-12">
		            <input type="text" class="form-control" id="username" name="userName" placeholder="您的登录账号">	            
		          </div>              
		        </div>	
		        <div class="form-group">
		          <div class="col-md-8">
		            <input type="text" class="form-control" id="email" name="email" placeholder="您注册是所填写的邮箱地址">
		          </div>
		          <div class="col-md-4">
		          	<input type="button" value="获取验证码" class="btn btn-info" onClick="sendEamil()">
		          </div>
		        </div>
		        <div class="form-group">
		          <div class="col-md-12">
		            <label>为确保是本人操作，需要您提供在您安全邮箱里面收到的6位随机码：</label>
		          </div>
		        </div>
		        <div class="form-group">
		          <div class="col-md-8">
		            <input type="text" class="form-control" id="randomcode" name="randomCode" placeholder="6位随机验证码">	            
		          </div>
		          <div class="col-md-4">
		            <input type="button" value="验证邮箱" class="btn btn-danger" onClick="checkEmil();">
                    <br><br>
		          </div>
		        </div>
		     </form>
		</div>
	</div>
	<script src="${contextPath}/resources/common/js/jquery.min.js"></script>
	<script src="${contextPath}/resources/common/js/bootstrap.min.js"></script>
	<script src="${contextPath}/resources/common/js/toast.js"></script>
	<script src="${contextPath}/resources/common/common.js"></script>
	<script src="${contextPath}/resources/common/canvas-particle.js"></script>
	<script src="${contextPath}/resources/js/sys/forgot-password.js"></script>
</body>
</html>