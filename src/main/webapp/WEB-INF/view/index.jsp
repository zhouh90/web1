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
<link href="${contextPath}/resources/common/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="${contextPath}/resources/common/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div>
		<jsp:include page="sys/header.jsp" flush="true" />
	</div>
	<h2>welcome!</h2>
	<script src="${contextPath}/resources/common/js/jquery.min.js"></script>
	<script src="${contextPath}/resources/common/js/bootstrap.min.js"></script>
	<script src="${contextPath}/resources/common/js/toast.js"></script>
	<script src="${contextPath}/resources/common/common.js"></script>
	<script src="${contextPath}/resources/common/canvas-particle.js"></script>
	<script src="${contextPath}/resources/js/sys/header.js"></script>
</body>
</html>