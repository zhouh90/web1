package com.qaf.sys.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 周 浩
 * @email zhou_eric90@163.com
 * @date 2018年4月12日 上午10:59:02
 * @描述
 */
@Controller
public class PageController {

	private static final Logger logger = LoggerFactory.getLogger(PageController.class);

	@RequestMapping("login")
	public String loginPage() {
		logger.info("enter login page");
		return "/sys/login";
	}

	@RequestMapping("forgot_password")
	public String forgetPassWordPage() {
		logger.info("enter forget password page");
		return "/sys/forgot-password";
	}

	@RequestMapping("home")
	public String homePage() {
		logger.info("enter home page");
		return "/index";
	}

	@RequestMapping("reset_password")
	public String resetPasswordPage() {
		logger.info("enter reset password page");
		return "/sys/reset-password";
	}

	@RequestMapping("new_account")
	public String newAccount() {
		logger.info("enter create new account page");
		return "/sys/create-new-account";
	}

}
