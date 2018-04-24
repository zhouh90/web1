<%@ page language="java" contentType="text/html; charsset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" import="java.io.*"%>
<%@ include file="tags.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8" />
	<meta name="description" content="Social training and racing for runners, triathletes, and cyclists." />
	<meta name="keywords" content="" />
	<title>出错啦</title>
   <style type="text/css">

   </style>
 </head>
  <body>
   <div class="error"><p>对不起，系统转不动了，请稍等！</p>系统将自动跳转到首页。<br/>您也可以：<button class="btn btn-danger"><i class="icon-home icon-white"></i> 返回首页</button> &nbsp;&nbsp; <button class="btn"><i class="icon-user"></i>联系客服</button></div>
   <div id="stackTrace" class="display:none">
       <%
        // if there is an exception
        if (exception != null) {
            // print the stack trace hidden in the HTML source code for debug
           exception.printStackTrace(new PrintWriter(out));
        }
       %>
   </div>
  </body>
</html>