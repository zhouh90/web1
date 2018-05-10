package com.qaf.sys.controller.interceptor;

import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 周 浩
 * @email zhou_eric90@163.com
 * @date 2018年4月12日 上午11:00:36
 * @描述
 */
public class ViewInterceptor implements HandlerInterceptor {

	private static final Set<String> white_urls = new HashSet<>();
	static {
		white_urls.add("new_account");
		white_urls.add("captcha");
		white_urls.add("forgot_password");
		white_urls.add("sendEmail");
		white_urls.add("checkEmail");
		white_urls.add("checkAccount");
		white_urls.add("addUser");
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String requestUrl = request.getRequestURI();
		for (String subUrl : white_urls) {
			if (requestUrl.contains(subUrl)) {
				return true;
			}
		}
//		if (requestUrl.contains("new_account") || requestUrl.contains("captcha") || requestUrl.contains("forgot_password") || requestUrl.contains("sendEmail") || requestUrl.contains("checkEmail")) {
//			return true;
//		}
		// 进入登录页面，判断session中是否有key，有的话重定向到首页，否则进入登录界面
		if (requestUrl.contains("login") || requestUrl.contains("doLogin")) {
			if (request.getSession().getAttribute("userInfo") != null) {
				response.sendRedirect(request.getContextPath() + "/home");// 默认跟路径为首页
			} else {
				return true;// 继续登陆请求
			}
		}

		if (request.getSession().getAttribute("userInfo") != null) {
			return true;
		}
		if (isAjaxRequest(request)) {
			response.setHeader("sessionstatus", "timeout");
		} else {
			PrintWriter out = response.getWriter();
			out.println("<html>");
			out.println("<script>");
			out.println(" window.open ('" + request.getContextPath() + "/login','_top');");
			out.println("</script>");
			out.println("</html>");
		}
		return false;
	}

	private boolean isAjaxRequest(HttpServletRequest request) {
		String headerX = request.getHeader("X-Requested-With");
		return headerX != null && headerX.equalsIgnoreCase("XMLHttpRequest");
	}

}
