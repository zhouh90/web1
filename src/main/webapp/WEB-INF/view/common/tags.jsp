<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:url var="contextPath" value="/"></spring:url>
<c:set var="contextPath" value="${fn:substring(contextPath,0,fn:length(contextPath)-1)}"></c:set>
<%-- <c:set var="SYS_VERSION" value="${systemVersionKey}"></c:set>
<c:set var="SERVE_IP" value="${serverIp}"></c:set>
<c:set var="PRICE_PATH" value="forward?method=/price"></c:set> --%>