<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<spring:url var="contextPath" value="/"></spring:url>
<c:set var="contextPath"
	value="${fn:substring(contextPath,0,fn:length(contextPath)-1)}"></c:set>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${contextPath}/resources/common/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link href="${contextPath}/resources/common/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="${contextPath}/resources/common/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css">
<link href="${contextPath}/resources/common/css/templatemo_style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div>
		<jsp:include page="header.jsp" flush="true" />
	</div>
	<div class="container">
		<form class="form-horizontal templatemo-container templatemo-login-form-1 margin-bottom-30" id="reset-form" role="form" action="#" method="post">
			<div class="form-group">
				<div class="col-xs-12">
					<div class="control-wrapper">
						<label for="oldPassWord" class="control-label fa-label">
							<i class="fa fa-lock fa-medium"></i>
						</label>
						<input type="password" class="form-control" id="oldPassWord" name="oldPassWord" placeholder="原密码">
						<input type="hidden" class="form-control" id="userName" name="userName" >						
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="col-md-12">
					<div class="control-wrapper">
						<label for="password" class="control-label fa-label">
							<i class="fa fa-lock fa-medium"></i>
						</label> 
						<input type="password" class="form-control" id="newPassWord1" name="newPassWord1" placeholder="请输入新密码">
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="col-md-12">
					<div class="control-wrapper">
						<label for="password" class="control-label fa-label">
							<i class="fa fa-lock fa-medium"></i>
						</label> 
						<input type="password" class="form-control" id="newPassWord2" name="newPassWord2" placeholder="再次输入新密码">
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="col-md-12">
					<div class="control-wrapper">
						<input type="button" value="确认修改" class="btn btn-info" onClick="return resetPassword();"> 
						<a href="${contextPath}/forgot_password" class="text-right pull-right">忘记密码?</a>
					</div>
				</div>
			</div>
			<hr>
		</form>
	</div>
	

	<script src="${contextPath}/resources/common/js/jquery.min.js"></script>
	<script src="${contextPath}/resources/common/js/bootstrap.min.js"></script>
	<script src="${contextPath}/resources/common/js/toast.js"></script>
	<script src="${contextPath}/resources/common/js/md5.js"></script>
	<script src="${contextPath}/resources/common/common.js"></script>
	<script src="${contextPath}/resources/common/canvas-particle.js"></script>
	<script src="${contextPath}/resources/js/sys/header.js"></script>
	<script src="${contextPath}/resources/js/sys/reset-password.js"></script>
</body>
</html>