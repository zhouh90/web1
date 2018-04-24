package com.qaf.sys.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: CaptchaUtil
 * @Description: 关于验证码的工具类
 * @author 无名
 * @date 2016-5-7 上午8:33:08
 * @version 1.0
 */
public final class CaptchaUtil {

	private static final Logger logger = LoggerFactory.getLogger(CaptchaUtil.class);

	private CaptchaUtil() {
	}

	/*
	 * 随机字符字典
	 */
	private static final char[] CHARS = { '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

	/*
	 * 随机数
	 */
	private static Random random = new Random();

	/*
	 * 获取6位随机数
	 */
	private static String getRandomString() {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < 6; i++) {
			buffer.append(CHARS[random.nextInt(CHARS.length)]);
		}
		return buffer.toString();
	}

	/*
	 * 获取随机数颜色
	 */
	private static Color getRandomColor() {
		return new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
	}

	/*
	 * 返回某颜色的反色
	 */
	private static Color getReverseColor(Color c) {
		return new Color(255 - c.getRed(), 255 - c.getGreen(), 255 - c.getBlue());
	}

	public static void outputCaptcha(HttpServletRequest request, HttpServletResponse response) {
		ServletOutputStream out = null;
		try {
			response.setContentType("image/jpeg");

			String randomString = getRandomString();
			request.getSession(true).setAttribute("randomString", randomString);

			int width = 100;
			int height = 30;

			Color color = getRandomColor();
			Color reverse = getReverseColor(color);

			BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = bi.createGraphics();
			g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
			g.setColor(color);
			g.fillRect(0, 0, width, height);
			g.setColor(reverse);
			g.drawString(randomString, 18, 20);
			for (int i = 0, n = random.nextInt(100); i < n; i++) {
				g.drawRect(random.nextInt(width), random.nextInt(height), 1, 1);
			}
			g.dispose();
			out = response.getOutputStream();
			ImageIO.write((RenderedImage) bi, "jpeg", (OutputStream) out);
			out.flush();
		} catch (IOException e) {
			logger.error("获取图片验证码失败，失败原因：{}" + e.getMessage(), e);
		} finally {
			if (null != out) {
				try {
					out.close();
				} catch (IOException e) {
					logger.error("获取图片验证码关闭输出文件失败，失败原因：{}" + e.getMessage(), e);
				}
			}
		}

	}
}