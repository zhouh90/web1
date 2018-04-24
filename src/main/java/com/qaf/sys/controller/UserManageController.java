package com.qaf.sys.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qaf.sys.model.User;
import com.qaf.sys.service.UserService;
import com.qaf.sys.utils.DateUtils;
import com.qaf.sys.utils.MD5Util;
import com.qaf.sys.utils.R;
import com.qaf.sys.utils.mail.MailBean;
import com.qaf.sys.utils.mail.MailUtil;

/**
 * @author 周 浩
 * @email zhou_eric90@163.com
 * @date 2018年4月12日 上午10:59:12
 * @描述
 */
@Controller
@RequestMapping("/sys/user")
public class UserManageController {

	private static final Logger logger = LoggerFactory.getLogger(UserManageController.class);

	private static final int MD5_CNT = 3;

	@Autowired
	private UserService userService;

	@Resource
	private MailUtil mailUtil;

	@RequestMapping("/captcha")
	@ResponseBody
	public void captcha(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
		int width = 63;
		int height = 37;
		Random random = new Random();
		// 设置response头信息
		// 禁止缓存
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		// 生成缓冲区image类
		BufferedImage image = new BufferedImage(width, height, 1);
		// 产生image类的Graphics用于绘制操作
		Graphics g = image.getGraphics();
		// Graphics类的样式
		g.setColor(this.getRandColor(200, 250));
		g.setFont(new Font("Times New Roman", 0, 28));
		g.fillRect(0, 0, width, height);
		// 绘制干扰线
		for (int i = 0; i < 40; i++) {
			g.setColor(this.getRandColor(130, 200));
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int x1 = random.nextInt(12);
			int y1 = random.nextInt(12);
			g.drawLine(x, y, x + x1, y + y1);
		}
		// 绘制字符
		String strCode = "";
		for (int i = 0; i < 4; i++) {
			String rand = String.valueOf(random.nextInt(10));
			strCode = strCode + rand;
			g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
			g.drawString(rand, 13 * i + 6, 28);
		}
		// 将字符保存到session中用于前端的验证
		session.setAttribute("captcha", strCode);
		g.dispose();

		ImageIO.write(image, "JPEG", response.getOutputStream());
		response.getOutputStream().flush();
	}

	private Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	@RequestMapping("/doLogin")
	@ResponseBody
	public R doLogin(HttpServletRequest request, HttpServletResponse response) {
		logger.info("后台执行用户登录doLogin");
		String realValidateCode = (String) request.getSession(true).getAttribute("captcha");
		String validateCode = request.getParameter("captcha");// 验证码
		if (validateCode == null || !validateCode.equalsIgnoreCase(realValidateCode)) {
			return R.error(-1, "验证码不正确");
		}
		String userName = request.getParameter("userName");// 账号
		String password = request.getParameter("passWord");// 密码
		User user = userService.findUserByName(userName);
		if (user != null && user.getPassWord().equals(MD5Util.md5(password, MD5_CNT))) {
			request.getSession().setAttribute("userInfo", user);
			return R.ok().put("nickName", user.getNickName());
		}
		return R.error(-1, "用户名或密码不正确");
	}

	@RequestMapping("/logout")
	public String logout(HttpServletResponse response, HttpServletRequest request) {
		if (request.getSession().getAttribute("userInfo") != null) {
			request.getSession().removeAttribute("userInfo");
		}
		logger.info("logout");
		return "/sys/login";
	}

	@RequestMapping("/resetPassword")
	@ResponseBody
	public R resetPassWord(HttpServletRequest request, HttpServletResponse response) {
		String userName = request.getParameter("userName");
		String oldPassWord = request.getParameter("oldPassWord");
		String newPassWord1 = request.getParameter("newPassWord1");
		String newPassWord2 = request.getParameter("newPassWord2");

		if (StringUtils.isBlank(newPassWord1) || StringUtils.isBlank(newPassWord2)) {
			logger.info("修改密码参数出错----新密码不能为空");
			return R.error(-1, "新密码不能为空");
		}
		if (!newPassWord1.equals(newPassWord2)) {
			logger.info("修改密码参数出错----两次密码输入不一致");
			return R.error(-1, "两次密码不一致");
		}

		User user = userService.findUserByName(userName);
		if (user == null) {
			logger.info("修改密码参数出错----找不到该用户,username:{}", userName);
			return R.error(-1, "系统异常");
		}

		if (!user.getPassWord().equals(MD5Util.md5(oldPassWord, MD5_CNT))) {
			logger.info("修改密码参数出错----原始密码不正确");
			return R.error(-1, "原始密码不正确");
		}

		String newPass = MD5Util.md5(newPassWord1, MD5_CNT);
		if (userService.updatePassWordByName(userName, newPass) == 1) {
			return R.ok();
		}
		return R.error(-1, "密码修改失败");
	}

	@RequestMapping("/sendEmail")
	@ResponseBody
	public R sendEmail(HttpServletRequest request, HttpServletResponse response) {
		String userName = request.getParameter("userName");
		String email = request.getParameter("email");
		User user = userService.findUserByUserNameAndEmail(userName, email);
		if (user == null) {
			return R.error(-1, "该用户未注册或邮箱不正确！");
		}
		System.out.println("id:" + user.getId());
		String key = buildEamilCodeKey(userName, email);
		String randomNum = generateRandomNum(6);
		System.out.println("key:" + key);
		System.out.println("num:" + randomNum);
		try {
			sendRandomNumEmail(user.getNickName(), email, randomNum);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("验证码发送失败---msg:{}", e.getMessage());
			return R.error(-1, "验证码发送失败,请稍后重试！");
		}
		return R.ok("验证码已发至安全邮箱！");
	}

	private void sendRandomNumEmail(String name, String toEmailAddress, String randomNum) throws MessagingException {
		MailBean mailBean = new MailBean();
		mailBean.setFrom("XXX@163.com");
		mailBean.setSubject("用户密码重置--验证");
		String[] tos = org.springframework.util.StringUtils.tokenizeToStringArray(toEmailAddress, ConfigurableApplicationContext.CONFIG_LOCATION_DELIMITERS);
		mailBean.setToEmails(tos);
		mailBean.setTemplate("亲爱的  ${nick_name}:<br/> 您于 ${time} 进行密码找回操作,验证码为： ${random_num} ,有效期5分钟！<br/> 切记不要将此验证码泄露给他人,如果不是您本人操作,请忽略该邮件！");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("nick_name", name);
		map.put("time", DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
		map.put("random_num", randomNum);
		mailBean.setData(map);
		mailUtil.send(mailBean);
	}

	/**
	 * 生产i位随机数字
	 * 
	 * @param i
	 * @return
	 */
	private String generateRandomNum(int i) {
		StringBuffer sb = new StringBuffer();
		Random ran = new Random();
		for (int ii = 0; ii < i; ii++) {
			sb.append(ran.nextInt(10) + "");
		}
		return sb.toString();
	}

	private String buildEamilCodeKey(String userName, String email) {
		return "u_" + userName + "_e_" + email;
	}
}
