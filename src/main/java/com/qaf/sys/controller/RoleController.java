package com.qaf.sys.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qaf.sys.model.User;
import com.qaf.sys.service.UserService;
import com.qaf.sys.utils.MD5Util;
import com.qaf.sys.utils.R;

/**
 * @author 周 浩
 * @email zhou_eric90@163.com
 * @date 2018年4月25日 上午10:21:37
 * @描述
 */
@Controller
@RequestMapping("/sys/role")
public class RoleController extends IBaseController {

	private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

	@Autowired
	private UserService userService;

	@RequestMapping("/checkAccount")
	@ResponseBody
	public R checkAccount(String userName) {
		if (StringUtils.isBlank(userName)) {
			logger.info("check account ---- username is blank");
			return R.error(-1, "登录账号不能为空");
		}
		User u = userService.findUserByName(userName);
		if (u != null) {
			logger.info("check account ---- username has already exist");
			return R.error(-1, "该用户名已经被注册");
		}
		return R.ok();
	}

	@RequestMapping("/add")
	@ResponseBody
	public R addUser(HttpServletRequest request, HttpServletResponse response) {
		String userName = request.getParameter("username");
		String nickName = request.getParameter("nickname");
		String passWord = request.getParameter("password");
		String email = request.getParameter("email");
		String captcha = request.getParameter("captcha");

		User u = userService.findUserByName(userName);
		if (u != null) {
			logger.info("add account ---- username has already exist");
			return R.error(-1, "该用户名已经被注册,请换个用户名注册");
		}
		// TODO 校验图片验证码

		u = new User();
		passWord = MD5Util.md5(passWord, MD5_CNT - 1);
		u.setUserName(userName);
		u.setPassWord(passWord);
		u.setEmail(email);
		u.setNickName(nickName);
		u.setMobile(null);
		int r = userService.addUser(u);
		if (r == 1) {
			return R.ok("注册成功！");
		}
		return R.error(-1, "注册失败！");
	}

}
