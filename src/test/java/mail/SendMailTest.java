//package mail;
//
//import java.util.Properties;
//
//import javax.mail.Authenticator;
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.AddressException;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//
///**
// * @author 周 浩
// * @email zhou_eric90@163.com
// * @date 2018年5月8日 下午3:35:43
// * @描述
// */
//public class SendMailTest {
//
//	public static void main(String[] args) {
//		try {
//			new SendMailTest().testSend();
//		} catch (AddressException e) {
//			e.printStackTrace();
//		} catch (MessagingException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void testSend() throws AddressException, MessagingException {
//
//		Properties props = new Properties();
//		props.setProperty("mail.transport.protocol", "SMTP");
//		props.setProperty("mail.smtp.host", "smtp.163.com");
//		props.setProperty("mail.smtp.port", "25");
//		// 指定验证为true
//		props.setProperty("mail.smtp.auth", "true");
//		props.setProperty("mail.smtp.timeout", "1000");
//		// 验证账号及密码，密码需要是第三方授权码
//		Authenticator auth = new Authenticator() {
//			public PasswordAuthentication getPasswordAuthentication() {
//				return new PasswordAuthentication("15502108775@163.com", "ZhZhzihao1990");
//			}
//		};
//		Session session = Session.getInstance(props, auth);
//
//		// 2.创建一个Message，它相当于是邮件内容
//		Message message = new MimeMessage(session);
//		// 设置发送者
//		message.setFrom(new InternetAddress("15502108775@163.com"));
//		// 设置发送方式与接收者
//		message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress("zhouhao@chianzyjr.com"));
//		// 设置主题
//		message.setSubject("邮件发送测试");
//		// 设置内容
//		message.setContent("asadasdasfas", "text/html;charset=utf-8");
//
//		// 3.创建 Transport用于将邮件发送
//		Transport.send(message);
//	}
//
//}
