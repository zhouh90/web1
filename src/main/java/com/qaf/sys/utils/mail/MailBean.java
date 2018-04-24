/**
 * 
 */
package com.qaf.sys.utils.mail;

import java.io.Serializable;
import java.util.Map;

/**
 * @author zhou.hao
 * @email zhou_h90@sina.com
 * @date 2015年6月25日
 */
public class MailBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String from;
	private String fromName;
	private String[] toEmails;

	private String subject;

	private Map data; // 邮件数据
	private String template; // 邮件模板

	/**
	 * @return the from
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * @param from
	 *            the from to set
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 * @return the fromName
	 */
	public String getFromName() {
		return fromName;
	}

	/**
	 * @param fromName
	 *            the fromName to set
	 */
	public void setFromName(String fromName) {
		this.fromName = fromName;
	}

	/**
	 * @return the toEmails
	 */
	public String[] getToEmails() {
		return toEmails;
	}

	/**
	 * @param toEmails
	 *            the toEmails to set
	 */
	public void setToEmails(String[] toEmails) {
		this.toEmails = toEmails;
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject
	 *            the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the data
	 */
	public Map getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(Map data) {
		this.data = data;
	}

	/**
	 * @return the template
	 */
	public String getTemplate() {
		return template;
	}

	/**
	 * @param template
	 *            the template to set
	 */
	public void setTemplate(String template) {
		this.template = template;
	}

}
