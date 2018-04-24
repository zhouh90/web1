package com.qaf.sys.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class DateUtils {
	public final static String YMD_FORMAT_STR = "yyyy-MM-dd";
	public final static String YMDHMS__FORMAT_STR = "yyyy-MM-dd hh:mm:ss";
	public final static String NEW_FORMAT_STR = "yyyy-MM-dd HH:mm:ss";
	public final static int NEXT_DAY = 1;

	public static String getDate(Date date, String pattern) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	public static boolean isAffterThan(Date d1, Date d2) {
		if (d1 == null || d2 == null) {
			return false;
		}
		if (d1.getTime() > d2.getTime()) {
			return true;
		}
		return false;
	}

	public static boolean isAffterThan(String d1, String d2) {
		Date date1 = parseString(d1, "yyyy-MM-dd hh:mm:ss");
		Date date2 = parseString(d2, "yyyy-MM-dd hh:mm:ss");
		if (date1.getTime() > date2.getTime()) {
			return true;
		}
		return false;
	}

	/**
	 * 格式化指定的日期
	 * 
	 * @param date
	 *            要格式化的日期
	 * @param format
	 *            格式化日期的格式
	 * @return
	 * @throws Exception
	 */
	public static String formatDate(Date date, String format) {
		if (date == null) {
			return null;
		}
		DateFormat df = new SimpleDateFormat(format);
		return df.format(date);
	}

	/**
	 * 
	 * @param source
	 *            解析的字符串
	 * @param format
	 *            日期的格式
	 * @return
	 * @throws Exception
	 */
	public static Date parseString(String source, String format) {
		try {
			if (StringUtils.isNotEmpty(source)) {
				DateFormat df = new SimpleDateFormat(format);
				return df.parse(source);
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}

	public static Date parseDefaultString(String source) throws Exception {
		return parseString(customizeTime(source), "yyyy-MM-dd HH:mm:ss");
	}

	public static String customizeTime(String time) throws Exception {
		Timestamp data = getTimeByCleanFormater(time, new SimpleDateFormat("yyyy-MM-dd"));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String datatime = sdf.format(data);
		data = getTimeByCleanFormater(time, new SimpleDateFormat("HH:mm:ss"));
		sdf = new SimpleDateFormat("HH:mm:ss");
		return datatime = datatime + " " + sdf.format(data);
	}

	public static Timestamp getTimeByCleanFormater(String time, SimpleDateFormat formater) throws Exception {
		String matche = formater.toPattern().trim();
		String ma = "";
		int ja = 0;
		String mas = "";
		String masx = "";
		String format = "YyMmDdhHSs";
		for (int i = 0; i < matche.length(); i++) {
			if (ma.equals(matche.substring(i, i + 1))) {
				ja++;
				if (i == matche.length() - 1 && format.contains(ma)) {
					mas = mas + "\\d{" + ja + "}";
					masx = masx + "\\d*";
				} else if (!format.contains(ma)) {
					mas = mas + ma;
					masx = masx + ma;
				}
			} else {
				ma = matche.substring(i, i + 1);
				if (format.contains(ma)) {
					ja = 0;
					ja++;
				} else if (i == 0) {
					mas = mas + ma;
					masx = masx + ma;
				} else if (!format.contains(matche.substring(i - 1, i))) {
					mas = mas + ma;
					masx = masx + ma;
				} else {
					mas = mas + "\\d{" + ja + "}";
					masx = masx + "\\d*";
					mas = mas + ma;
					masx = masx + ma;
				}
			}
		}
		Pattern p = Pattern.compile(mas, Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(time);
		if (m.find()) {
			return new Timestamp(formater.parse(m.group()).getTime());
		} else {
			p = Pattern.compile(masx, Pattern.CASE_INSENSITIVE);
			m = p.matcher(time);
			if (m.find()) {
				return new Timestamp(formater.parse(m.group()).getTime());
			} else {
				return null;
			}
		}
	}

	public static Date getCurrentDate() {
		return new Date();
	}

	public static Date addSecond(Date date, int seconds) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.SECOND, seconds);

		return cal.getTime();
	}

	public static Date addDay(Date date, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_YEAR, days);

		return cal.getTime();
	}

	public static Date addMinute(Date date, int minutes) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, minutes);

		return cal.getTime();
	}

	public static String getPreDay() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return formatDate(cal.getTime(), NEW_FORMAT_STR);
	}

	/**
	 * 获取更新价格的时间 前一天晚上九点多。。
	 * 
	 * @return
	 */
	public static String getUpdatePriceTime() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DAY_OF_MONTH, -1);
		cal.set(Calendar.HOUR_OF_DAY, 21);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return formatDate(cal.getTime(), NEW_FORMAT_STR);
	}

	/**
	 * 返回当天凌晨的时间
	 * 
	 * @return
	 */
	public static Date getCurrentDayZero(Date nowDate) {
		return DateUtils.parseString(DateUtils.getDate(nowDate, "yyyy-MM-dd"), "yyyy-MM-dd");
	}

	/**
	 * 返回下一天的时间
	 * 
	 * @return
	 */
	public static Date getNextCurrentDay(Date date) {
		Calendar startTime = Calendar.getInstance();
		startTime.setTime(date);
		startTime.add(Calendar.DAY_OF_MONTH, NEXT_DAY);
		return startTime.getTime();
	}

	public static Date getFirstDayOfWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_WEEK, 1);
		return calendar.getTime();
	}

	public static Date getLastDayOfWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_WEEK, 7);
		return calendar.getTime();
	}

	public static int getWeekOfYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * 周日：1~周六：7
	 * 
	 * @param date
	 * @return
	 *
	 */
	public static int getDayOfWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_WEEK);
	}

//	public static void main(String[] args) {
//		System.out.println(getDayOfWeek(new Date()));
//	}

	/**
	 * 返回当天所在月份数
	 * 
	 * @return
	 */
	public static int getMonthOfYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH);
	}

	/**
	 * 返回当天所在月份的第一天
	 * 
	 * @return
	 */
	public static Date getFirstDayOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DATE, 1);
		return calendar.getTime();
	}

	/**
	 * 返回当天所在月份的最后一天
	 * 
	 * @return
	 */
	public static Date getLastDayOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.roll(Calendar.DAY_OF_MONTH, -1);
		return calendar.getTime();
	}

	public static Date getFridayOfWeek(Date date1) {
		Calendar c = Calendar.getInstance();
		c.setTime(date1);
		c.set(Calendar.DAY_OF_WEEK, 6);
		return c.getTime();
	}

	/**
	 * 获取上周周一的时间
	 */
	public static Date getMondayOfLastWeek(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.WEEK_OF_YEAR, c.get(Calendar.WEEK_OF_YEAR) - 1);
		c.set(Calendar.DAY_OF_WEEK, 2);
		return c.getTime();
	}

	/**
	 * 返回当天近一月的月初时间
	 */
	public static Date getFirstDayOfLastMonth(Date date) {
		Date countDate = DateUtils.parseString(DateUtils.formatDate(date, "yyyy-MM"), "yyyy-MM");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(countDate);
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
		return calendar.getTime();
	}

	public static Date addMonth(Date date, int month) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, month);
		return c.getTime();
	}

	public static boolean isToday(String dateStr) {
		String dateValue = getDate(new Date(), YMD_FORMAT_STR);
		return dateStr.equals(dateValue);
	}

	/**
	 * 返回当天 23:59:59
	 */
	public static Date getLastTimeOfDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, 1);
		c.setTime(getCurrentDayZero(c.getTime()));
		c.add(Calendar.SECOND, -1);
		return c.getTime();
	}

	/**
	 * 返回不同格式的时间类型
	 */
	public static Date getNewFormat(Date date) {
		return DateUtils.parseString(formatDate(date, "yyyy-MM-dd"), "yyyy-MM-dd");
	}

	/**
	 * 返回与当前date 相隔 index天的日期
	 * 
	 * @param date
	 * @param index
	 * @return
	 */
	public static Date getDateByIndex(Date date, int index) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, index);
		return c.getTime();
	}

//	public static void main(String[] args) {
//		Date d1Date = DateUtils.parseString("2017-05-25 12:22:12", "yyyy-MM-dd HH:mm:ss");
//		Date d2Date = DateUtils.parseString("2017-05-25 11:22:12", "yyyy-MM-dd HH:mm:ss");
//		System.out.println(calculateHours(d1Date, d2Date));
//	}

	/**
	 * 计算两时间距离的小时数
	 * 
	 * @return
	 */
	public static Long calculateHours(Date startDate, Date endDate) {
		if (startDate == null) {
			startDate = DateUtils.getCurrentDate();
		}
		return (endDate.getTime() - startDate.getTime()) / (60 * 60 * 1000);
	}

	/**
	 * 计算两时间距离的天数
	 * 
	 * @return
	 */
	public static Long calculateDays(Date startDate, Date endDate) {
		if (startDate == null) {
			startDate = DateUtils.getCurrentDate();
		}
		return (endDate.getTime() - startDate.getTime()) / (24 * 60 * 60 * 1000);
	}

	/**
	 * 计算两时间距离的月数
	 * 
	 * @return
	 */
	public static int calculMonths(Date startTime, Date endTime) {
		Calendar start = Calendar.getInstance();
		start.setTime(startTime);
		int startYear = start.get(Calendar.YEAR);
		int startMonth = start.get(Calendar.MONTH);

		Calendar end = Calendar.getInstance();
		end.setTime(endTime);
		int endYear = end.get(Calendar.YEAR);
		int endMonth = end.get(Calendar.MONTH);

		return (endYear * 12 + endMonth) - (startYear * 12 + startMonth);

	}

	/**
	 * 获取 beforeHours 小时前的时间
	 * 
	 * @param beforeHours
	 * @return
	 */
	public static Date getHoursDate(int beforeHours) {
		Date now = new Date();
		long times = now.getTime() - beforeHours * 60 * 60 * 1000;
		return new Date(times);
	}

	public static int getYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}

	public static int getHour(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.HOUR_OF_DAY);
	}
}
