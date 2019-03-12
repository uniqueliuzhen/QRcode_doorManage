package com.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * <p>
 * Date Utils 工具类
 * </p>
 *
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class DateUtils {
	private static Logger logger = LoggerFactory.getLogger(DateUtils.class);

	public static final long DAY = 24 * 60 * 60 * 1000L;
	public static final long MINUTE = 60 * 1000L;

	/**
	 * Date format: "YY"
	 */
	public static final String YY = "yy";

	/**
	 * Date format: "YYYY"
	 */
	public static final String YYYY = "yyyy";

	/**
	 * Date format: "MM"
	 */
	public static final String MM = "MM";

	/**
	 * Date format: "DD"
	 */
	public static final String DD = "dd";

	/**
	 * Date format: "MM/DD"
	 */
	public static final String MM_DD = "MM/dd";

	/**
	 * Date format: "MM-DD"
	 */
	public static final String OMM_DD = "MM-dd";

	/**
	 * Date format: "HH:mm:ss"
	 */
	public static final String OHH_MM_SS = "HH:mm:ss";

	/**
	 * Date format: "MM-dd HH:mm:ss"
	 */
	public static final String OMM_DD_HH_MM_SS = "MM-dd HH:mm:ss";

	/**
	 * Date format: "YYYYMM"
	 */
	public static final String YYYYMM = "yyyyMM";

	public static final String YYYYMMDD = "yyyyMMdd";

	/**
	 * Date format: "YYYY/MM"
	 */
	public static final String YYYY_MM = "yyyy/MM";

	/**
	 * Date format: "YY/MM/DD"
	 */
	public static final String YY_MM_DD = "yy/MM/dd";

	/**
	 * Date format: "YYYY-MM-DD"
	 */
	public static final String OYYYY_MM_DD = "yyyy-MM-dd";

	/**
	 * Date format: "YYYY-MM-DD HH:MI" Add By YEHOOHAHA
	 */
	public static final String OYYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";

	/**
	 * Date format: "MM-dd HH:mm" Add By YEHOOHAHA
	 */
	public static final String OMM_DD_HH_MM = "MM-dd HH:mm";

	/**
	 * Date format: "YYYY-MM-DD HH:mm:ss" Add By YEHOOHAHA
	 */
	public static final String OYYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

	/**
	 * Date format: "HH:MI"
	 */
	public static final String HH_MM = "HH:mm";

	/**
	 * Date format: "HHMI"
	 */
	public static final String HHMM = "HHmm";

	/**
	 * Date format: "YY/MM/DD HH:MI"
	 */
	public static final String YY_MM_DD_HH_MM = "yy/MM/dd HH:mm";

	/**
	 * Date format: "YYYY/MM/DD HH:MI"
	 */
	public static final String YYYY_MM_DD_HH_MM = "yyyy/MM/dd HH:mm";

	/**
	 * Date format: "YYYY/MM/DD HH:MI:SS"
	 */
	public static final String YYYY_MM_DD_HH_MM_SS = "yyyy/MM/dd HH:mm:ss";

	/**
	 * Date format: "HH:MI:SS"
	 */
	public static final String HH_MM_SS = "HH:mm:ss";

	/**
	 * Date format: "YYYYMMDDHHMMSS"
	 */
	public static final String YYYYMMDDHHMM = "yyyyMMddHHmm";

	/**
	 * Date format: "YYYYMMDDHHMISS"
	 */
	public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
	
	/**
	 * Date format "yyyyMMddhhmmssSSS"
	 * */
	public static final String YYYYMMDDHHMMSSSSS="yyyyMMddhhmmssSSS";

	/**
	 * Date format: "YYYYMMDD_HHMMSS"
	 */
	public static final String YYYYMMDD_HHMMSS = "yyyyMMdd-HHmmss";

	/**
	 * Date format YYYY年MM月DD日
	 */
	public static final String YYYY$MM$DD$ = "yyyy年MM月dd日";

	/**
	 * Date format YYYY年MM月
	 */
	public static final String YYYY$MM$ = "yyyy年MM月";

	/**
	 * Date format MM月DD日
	 */
	public static final String MM$DD$ = "MM月dd日";

	/**
	 * Date format DD日
	 */
	public static final String DD$ = "dd日";

	/**
	 * Date format: "HH"
	 */
	public static final String HH = "HH";

	/**
	 * Date format: "MI"
	 */
	public static final String MI = "mm";

	private static final Map<String, DateFormat> DFS = new HashMap<String, DateFormat>();

	private DateUtils() {
	}

	public static DateFormat getFormat(String pattern) {
		DateFormat format = DFS.get(pattern);
		if (format == null) {
			format = new SimpleDateFormat(pattern);
			DFS.put(pattern, format);
		}
		return format;
	}

	public static Date parse(String source, String pattern) {
		if (source == null) {
			return null;
		}
		Date date;
		try {
			date = getFormat(pattern).parse(source);
		} catch (ParseException e) {
			if (logger.isDebugEnabled()) {
				logger.debug(source + " doesn't match " + pattern);
			}
			return null;
		}
		return date;
	}

	public static String format(String pattern) {
		return format(new Date(), pattern);
	}

	public static String format(Date date, String pattern) {
		return format(date, pattern, false);
	}

	/**
	 * 格式化时间格式为pattern返回字符串 当日期为null时 根据 notNull 判断返回空字符串还是null
	 * 
	 * @return String
	 */
	public static String format(Date date, String pattern, boolean notNull) {
		if (date == null) {
			if (!notNull) {
				return null;
			} else {
				return "";
			}
		}
		return getFormat(pattern).format(date);
	}

	/**
	 * * @param year * 年 * @param month * 月(1-12) * @param day * 日(1-31) * @return 输入的年、月、日是否是有效日期
	 */
	public static boolean isValid(int year, int month, int day) {
		if (month > 0 && month < 13 && day > 0 && day < 32) {
			// month of calendar is 0-based
			int mon = month - 1;
			Calendar calendar = new GregorianCalendar(year, mon, day);
			if (calendar.get(Calendar.YEAR) == year && calendar.get(Calendar.MONTH) == mon
					&& calendar.get(Calendar.DAY_OF_MONTH) == day) {
				return true;
			}
		}
		return false;
	}

	private static Calendar convert(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		return calendar;

	}

	/** * 返回指定年数位移后的日期 */
	public static Date yearOffset(Date date, int offset) {
		return offsetDate(date, Calendar.YEAR, offset);
	}

	/** * 返回指定月数位移后的日期 */
	public static Date monthOffset(Date date, int offset) {
		return offsetDate(date, Calendar.MONTH, offset);
	}

	/** * 返回指定天数位移后的日期 */
	public static Date dayOffset(Date date, int offset) {
		return offsetDate(date, Calendar.DATE, offset);
	}

	/**
	 * * 返回指定日期相应位移后的日期 * * @param date * 参考日期 * @param field * 位移单位，见 {@link Calendar} * @param offset *
	 * 位移数量，正数表示之后的时间，负数表示之前的时间 * @return 位移后的日期
	 */
	public static Date offsetDate(Date date, int field, int offset) {
		Calendar calendar = convert(date);
		calendar.add(field, offset);
		return calendar.getTime();
	}

	/** * 返回当月第一天的日期 */

	public static Date firstDay(Date date) {
		Calendar calendar = convert(date);
		calendar.set(Calendar.DATE, 1);
		return calendar.getTime();
	}

	/** * 返回当月最后一天的日期 */
	public static Date lastDay(Date date) {
		Calendar calendar = convert(date);
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
		return calendar.getTime();
	}

	/**
	 * * 返回两个日期间的差异天数 * * @param date1 * 参照日期 * @param date2 * 比较日期 * @return
	 * 参照日期与比较日期之间的天数差异，正数表示参照日期在比较日期之后，0表示两个日期同天，负数表示参照日期在比较日期之前
	 */
	public static int dayDiff(Date date1, Date date2) {
		long diff = date1.getTime() - date2.getTime();
		return (int) (diff / DAY);
	}

	/**
	 * 使用dayDiff方法判断两个日期相等有bug,如果相差几个小时，可能出错
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isSameDate(Date date1, Date date2) {
		if (date1 == null)
			return false;
		if (date2 == null)
			return false;

		String date1Str = format(date1, OYYYY_MM_DD);
		String date2Str = format(date2, OYYYY_MM_DD);

		if (date1Str.equals(date2Str)) {
			return true;
		}
		return false;
	}

	/**
	 * * 返回两个日期间的差异分钟 * * @param date1 * 参照日期 * @param date2 * 比较日期 * @return
	 * 参照日期与比较日期之间的分钟差异，正数表示参照日期在比较日期之后，0表示两个日期同一分钟，负数表示参照日期在比较日期之前
	 */
	public static long minDiff(Date date1, Date date2) {
		long diff = date1.getTime() - date2.getTime();
		return (long) (diff / MINUTE);
	}

	/**
	 * return a new Date object through add int d days on specified date.
	 * 
	 * @param date
	 *            Date
	 * @param d
	 *            int
	 * @return Date
	 */
	public final static Date addDays(Date date, int d) {
		if (date == null)
			date = new Date();
		long times = date.getTime() + ((long) d) * 24 * 60 * 60 * 1000;
		return new Date(times);
	}

	/**
	 * return a new Date object through add int d days on specified date.
	 * 
	 * @param date
	 *            Date
	 * @param d
	 *            int
	 * @return Date
	 */
	public final static Date addDays(int d) {
		return addDays(null, d);
	}

	public final static Date addMinutes(Date date, int m) {
		if (date == null)
			date = new Date();
		long times = date.getTime() + ((long) m) * 60 * 1000;
		return new Date(times);
	}

	public final static Date addMinutes(int m) {
		return addMinutes(null, m);
	}

	/**
	 * return a new Date object through add int m months on specified date.
	 * 
	 * @param date
	 *            Date
	 * @param m
	 *            int
	 * @return Date
	 */
	public final static Date addMonths(Date date, int m) {
		if (date == null)
			date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		/*
		 * int month = calendar.get(Calendar.MONTH) + m; int year = calendar.get(Calendar.YEAR) + month/12; month =
		 * month%12; if(month<0){ year --; month += 12; } calendar.set(year,month,calendar.get(Calendar.DAY_OF_MONTH));
		 */
		calendar.add(Calendar.MONTH, m);
		return calendar.getTime();
	}

	/**
	 * return a new Date object through add int m months on today.
	 * 
	 * @param m
	 *            int
	 * @return Date
	 */
	public final static Date addMonths(int m) {
		return addMonths(null, m);
	}

	/**
	 * Parse String strDate into Date object through specified pattern.
	 * 
	 * @param strDate
	 *            String
	 * @param pattern
	 *            String
	 * @return Date
	 * @throws ParseException
	 */
	public final static Date getDate(String strDate, String pattern) {
		if (strDate == null || strDate.equals("") || pattern == null || pattern.equals("")) {
			String message = "One of arguments is null or is a space string! strDate: " + strDate + ", pattern : \""
					+ pattern + "\".";
			logger.warn(message);
			throw new NullPointerException(message);
		}
		Date date = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			date = sdf.parse(strDate);
		} catch (IllegalArgumentException illegalArgumentException) {
			String message = "the given pattern is invalid! pattern : \"" + pattern + "\".";
			logger.error(message, illegalArgumentException);
			throw new RuntimeException(message, illegalArgumentException);
		} catch (ParseException ex) {
			/** @todo Handle this exception */
			StringBuffer sb = (new StringBuffer("The string \"")).append(strDate)
					.append("\" cannot be parsed to Date object,because it is not matching to pattern \"")
					.append(pattern).append("\"!");
			logger.error(sb.toString(), ex);
			throw new RuntimeException(sb.toString(), ex);
		}
		return date;
	}

	/**
	 * Parse String strDate into object type of java.sql.Date through specified pattern.
	 * 
	 * @param strDate
	 * @param pattern
	 * @return
	 */
	public final static java.sql.Date getSQLDate(String strDate, String pattern) {
		return getSQLDate(getDate(strDate, pattern));
	}

	/**
	 * convent object type of java.util.Date to object type of java.sql.Date
	 * 
	 * @param date
	 * @return
	 */
	public final static java.sql.Date getSQLDate(Date date) {
		return new java.sql.Date(date.getTime());
	}

	public final static Date getDate(String pattern) {
		if (pattern == null)
			pattern = OYYYY_MM_DD;
		return getDate(format(new Date(), pattern), pattern);
	}

	public final static Date getDateByDefaultPattern() {
		return getDate(null);
	}

	public final static Date getDate(Date date, String pattern) {
		return getDate(format(date, OYYYY_MM_DD), pattern);
	}

	/**
	 * return currently date.
	 * 
	 * @return Date
	 */
	public final static Date getDate() {
		return getDateByDefaultPattern();
	}

	/**
	 * return currently date.
	 * 
	 * @return Date
	 */
	public final static Date currentDate() {
		return new Date();
	}

	/**
	 * Get count days between Date startDate and Date endDate.
	 * 
	 * @param startDate
	 *            Date
	 * @param endDate
	 *            Date
	 * @return int
	 */
	public final static int minusDays(Date startDate, Date endDate) {
		long times = (endDate.getTime() - startDate.getTime()) / (24 * 60 * 60 * 1000);
		return Integer.parseInt(Long.toString(times));
	}

	public final static int minusMinutes(Date startDate, Date endDate) {
		long times = (endDate.getTime() - startDate.getTime()) / (60 * 1000);
		return Integer.parseInt(Long.toString(times));
	}

	public final static int minusSeconds(Date startDate, Date endDate) {
		long times = (endDate.getTime() - startDate.getTime()) / 1000;
		return Integer.parseInt(Long.toString(times));
	}

	/**
	 * Get count weeks between Date startDate and Date endDate.
	 * 
	 * @param startDate
	 *            Date
	 * @param endDate
	 *            Date
	 * @return int
	 */
	public final static int minusWeeks(Date startDate, Date endDate) {
		return minusDays(startDate, endDate) / 7;
	}

	/**
	 * Get the day of the week included the specified Date date .
	 * 
	 * @param date
	 *            Date
	 * @return int
	 */
	public final static int getDayOfWeek() {
		return getDayOfWeek(null);
	}

	/**
	 * Get the day of the week included the specified Date date .
	 * 
	 * @param date
	 *            Date
	 * @return int
	 */
	public final static int getDayOfWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		if (date == null)
			date = new Date();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * Get the day of the week (String) included the specified Date date .
	 * 
	 * @param date
	 *            Date
	 * @return int
	 */
	public static String getDayOfWeekStr(Date date) {
		Calendar calendar = Calendar.getInstance();
		if (date == null)
			date = new Date();
		calendar.setTime(date);
		int tmp = calendar.get(Calendar.DAY_OF_WEEK);
		String str = "";
		switch (tmp) {
		case 1:
			str = "日";
			break;
		case 2:
			str = "一";
			break;
		case 3:
			str = "二";
			break;
		case 4:
			str = "三";
			break;
		case 5:
			str = "四";
			break;
		case 6:
			str = "五";
			break;
		case 7:
			str = "六";
			break;
		}
		return str;
	}

	/**
	 * Retrieves whether the date is sunday.
	 * 
	 * @param date
	 *            Date
	 * @return boolean
	 */
	public final static boolean isSunday(Date date) {
		return getDayOfWeek(date) == Calendar.SUNDAY;
	}

	/**
	 * Retrieves whether the date is saturday.
	 * 
	 * @param date
	 *            Date
	 * @return boolean
	 */
	public final static boolean isSaturday(Date date) {
		return getDayOfWeek(date) == Calendar.SATURDAY;
	}

	/**
	 * Return Maximum DAY_OF_MONTH of the currently date.
	 * 
	 * @param date
	 *            Date
	 * @return int
	 */
	public final static int getMaximumDayOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		if (date == null)
			date = new Date();
		calendar.setTime(date);
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	/**
	 * Return Maximum DAY_OF_MONTH of today.
	 * 
	 * @return int
	 */
	public final static int getMaximumDayOfMonth() {
		return getMaximumDayOfMonth(null);
	}

	/**
	 * Return lasted date of the month included today .
	 * 
	 * @param date
	 *            Date
	 * @return Date
	 */
	public final static Date getMonthMaxDate() {
		return getMonthMaxDate(null);
	}

	/**
	 * Return lasted date of the month included current date .
	 * 
	 * @param date
	 *            Date
	 * @return Date
	 */
	public final static Date getMonthMaxDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		if (date == null)
			date = getDateByDefaultPattern();
		calendar.setTime(date);
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), getMaximumDayOfMonth(date));
		return calendar.getTime();
	}

	public final static Date getWeekFirstDate() {
		return getWeekFirstDate(null);
	}

	public final static Date getWeekFirstDate(Date date) {
		if (date == null)
			date = getDateByDefaultPattern();
		int index = getDayOfWeek(date);
		return addDays(date, 1 - index);
	}

	/**
	 * 获取当前季度的开始时间
	 * <p>
	 * </p>
	 * 
	 * @param date
	 * @return
	 * @throws
	 */
	public final static Date getQuarterFirstDate(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int currentMonth = c.get(Calendar.MONTH) + 1;
		Date now = null;
		try {
			if (currentMonth >= 1 && currentMonth <= 3)
				c.set(Calendar.MONTH, 0);
			else if (currentMonth >= 4 && currentMonth <= 6)
				c.set(Calendar.MONTH, 3);
			else if (currentMonth >= 7 && currentMonth <= 9)
				c.set(Calendar.MONTH, 4);
			else if (currentMonth >= 10 && currentMonth <= 12)
				c.set(Calendar.MONTH, 9);
			c.set(Calendar.DATE, 1);
			now = c.getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return now;
	}

	/**
	 * 获取当前季度的开始时间
	 * <p>
	 * </p>
	 * 
	 * @return
	 * @throws
	 */
	public final static Date getQuarterFirstDate() {
		return getQuarterFirstDate(new Date());
	}

	/**
	 * 获了以季度的最后一天
	 * <p></p> 
	 * @param date
	 * @return 
	 * @throws
	 */
	public final static Date getQuarterLastDate(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int currentMonth = c.get(Calendar.MONTH) + 1;
		Date now = null;
		try {
			if (currentMonth >= 1 && currentMonth <= 3) {
				c.set(Calendar.MONTH, 2);
				c.set(Calendar.DATE, 31);
			} else if (currentMonth >= 4 && currentMonth <= 6) {
				c.set(Calendar.MONTH, 5);
				c.set(Calendar.DATE, 30);
			} else if (currentMonth >= 7 && currentMonth <= 9) {
				c.set(Calendar.MONTH, 8);
				c.set(Calendar.DATE, 30);
			} else if (currentMonth >= 10 && currentMonth <= 12) {
				c.set(Calendar.MONTH, 11);
				c.set(Calendar.DATE, 31);
			}
			now = c.getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return now;

	}
	/**
	 * 获了以季度的最后一天
	 * <p></p> 
	 * @return 
	 * @throws
	 */
	public final static Date getQuarterLastDate() {
		return getQuarterLastDate(new Date());
	}
	public static Date getWeekLastDate() {
		return getWeekLastDate(null);
	}

	public static Date getWeekDate(Date date, int days) {
		if (date == null) {
			date = getDateByDefaultPattern();
		}
		int index = getDayOfWeek(date);
		return addDays(date, days - index);
	}

	public static Date getWeekLastDate(Date date) {
		if (date == null)
			date = getDateByDefaultPattern();
		int index = getDayOfWeek(date);
		return addDays(date, 7 - index);
	}

	/**
	 * 
	 * @param date
	 * @return 指示当前年中的星期数
	 */
	public static int getWeekOfYear(Date date) {
		if (date == null)
			date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * 
	 * @param date
	 * @return當月中的星期數
	 */
	public static int getWeekOfMonth(Date date) {
		if (date == null)
			date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.WEEK_OF_MONTH);
	}

	/**
	 * 
	 * @param date
	 * @param weeks
	 * @return 根據weeks返回起縱日期
	 */
	public static Date[] getWeekOfDate(Date date, int weeks) {
		if (date == null)
			date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int totalDay = calendar.getActualMaximum(Calendar.DAY_OF_YEAR);
		if (weeks < -1 || weeks == 0)
			weeks = 1;
		if (weeks > (int) (totalDay / 7))
			weeks = (int) (totalDay / 7);

		int year = calendar.get(Calendar.YEAR);
		if (weeks == -1) {
			weeks = calendar.get(Calendar.WEEK_OF_YEAR);
		}
		Calendar calendarB = Calendar.getInstance();
		Calendar calendarE = Calendar.getInstance();
		calendarB.set(Calendar.YEAR, year);
		calendarE.set(Calendar.YEAR, year);
		int begin = (7 * (weeks - 1) == 0) ? 1 : 7 * (weeks - 1);
		int end = 7 * weeks - 1;

		calendarB.set(Calendar.DAY_OF_YEAR, begin);
		calendarE.set(Calendar.DAY_OF_YEAR, end);

		return new Date[] { calendarB.getTime(), calendarE.getTime() };
	}

	public static Date[] getWeekOfDate(Date date) {
		return getWeekOfDate(date, -1);
	}

	/**
	 * 得到當天的起點時刻
	 * 
	 * @return
	 */
	public static Date getStartOfDay() {
		return getStartOfDay(null);
	}

	/**
	 * 得到明天的起點
	 * 
	 * @return Date
	 */
	public static Date getTomorrowStart() {
		return getStartOfDay(addDays(1));
	}

	/**
	 * 得到昨天的起點
	 * 
	 * @return date
	 */
	public static Date getYesterdayStart() {
		return getStartOfDay(addDays(-1));
	}

	/**
	 * 得到一天的起點時刻
	 * 
	 * @return date
	 */
	public static Date getStartOfDay(Date date) {
		if (null == date)
			date = getDate();
		return getDate(format(date, OYYYY_MM_DD), OYYYY_MM_DD);
	}

	/**
	 * 根據 月份 , 月的周次, 星期幾 得到具體日期 1月的月份數應是 0 , 2月是1 , 依此類推
	 * 
	 * @param month
	 * @param weekOfMonth
	 * @param dayOfWeek
	 * @return
	 */
	public static Date getDate(int month, int weekOfMonth, int dayOfWeek) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		if (month >= 0) {
			calendar.set(Calendar.MONTH, month);
		}
		calendar.set(Calendar.WEEK_OF_MONTH, weekOfMonth);
		calendar.set(Calendar.DAY_OF_WEEK, dayOfWeek);
		return calendar.getTime();
	}

	/**
	 * 返回日期所在那個月的第weekOfMonth周的星期 dayOfWeek那日
	 * 
	 * @param date
	 * @param weekOfMonth
	 * @param dayOfWeek
	 * @return
	 */
	public static Date getDate(Date date, int weekOfMonth, int dayOfWeek) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.WEEK_OF_MONTH, weekOfMonth);
		calendar.set(Calendar.DAY_OF_WEEK, dayOfWeek);
		return calendar.getTime();
	}

	/**
	 * 返回時隔stepMonth月后的相同周次星期幾所在的日期。
	 * 
	 * @param date
	 * @param stepMonth
	 * @return
	 */
	public static Date getDate(Date date, int stepMonth) {
		int weekOfMonth = DateUtils.getWeekOfMonth(date);
		int dayOfWeek = DateUtils.getDayOfWeek(date);
		return DateUtils.getDate(DateUtils.addMonths(date, stepMonth), weekOfMonth, dayOfWeek);
	}

	/**
	 * 使用日歷+-注意，當源日期所在月當中天數大于目的月當中最大數時 則會取目的月當中最大值,否則不會取到最大值 增加毫秒
	 */
	public static Date addMilliSeconds(Date date, int ms) {
		if (date == null)
			date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MILLISECOND, ms);
		return calendar.getTime();
	}

	/**
	 * 增加秒
	 */
	public static Date addSeconds(Date date, int s) {
		if (date == null)
			date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.SECOND, s);
		return calendar.getTime();
	}

	/**
	 * 增加小時數
	 */
	public static Date addHours(Date date, int h) {
		if (date == null)
			date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR, h);
		return calendar.getTime();
	}

	/**
	 * <p>
	 * 增加年
	 * </p>
	 * 
	 * @param y
	 * @return
	 * @throws
	 */
	public final static Date addYears(int y) {
		return addYears(null, y);
	}

	/**
	 * 增加年數
	 */
	public static Date addYears(Date date, int y) {
		if (date == null)
			date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		// if(calendar.getActualMaximum(Calendar.DAY_OF_MONTH) == calendar.get(Calendar.DAY_OF_MONTH)){
		// //說明是最后一天需要進行放到最后一天去,isLastOfMonth = true;
		// }
		calendar.add(Calendar.YEAR, y);
		// if(isLastOfMonth){
		// calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		// }
		return calendar.getTime();
	}

	/**
	 * @param date
	 * @param dateMap
	 * @return
	 */
	public static Date add(Date date, Map dateMap) {
		if (date == null)
			date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		if (dateMap.get("year") != null) {
			calendar.add(Calendar.YEAR, Integer.valueOf(dateMap.get("year").toString()));
		}
		if (dateMap.get("month") != null) {
			calendar.add(Calendar.MONTH, Integer.valueOf(dateMap.get("month").toString()));
		}
		if (dateMap.get("day") != null) {
			calendar.add(Calendar.DATE, Integer.valueOf(dateMap.get("day").toString()));
		}
		if (dateMap.get("hour") != null) {
			calendar.add(Calendar.HOUR, Integer.valueOf(dateMap.get("hour").toString()));
		}
		if (dateMap.get("minute") != null) {
			calendar.add(Calendar.MINUTE, Integer.valueOf(dateMap.get("minute").toString()));
		}
		if (dateMap.get("second") != null) {
			calendar.add(Calendar.SECOND, Integer.valueOf(dateMap.get("second").toString()));
		}
		if (dateMap.get("milliSecond") != null) {
			calendar.add(Calendar.MILLISECOND, Integer.valueOf(dateMap.get("milliSecond").toString()));
		}
		return calendar.getTime();
	}

	/**
	 * 日期之間相差的年數,注意不足年的取0.當startDate>endDate取得是負值
	 */
	@SuppressWarnings("deprecation")
	public static int minusYears(Date startDate, Date endDate) {
		Date start = new Date();// 不操作傳入進來的參數,否則會導startDate有修改,這個并不是外界程序所想看見的
		start.setTime(startDate.getTime());
		int minusYears = endDate.getYear() - start.getYear();
		start = addYears(start, minusYears);
		if (minusYears != 0 && endDate.getTime() > startDate.getTime() && start.getTime() > endDate.getTime()) {
			minusYears = minusYears - 1;// 存在不滿足一年的情況
		} else if (minusYears != 0 && endDate.getTime() < startDate.getTime() && start.getTime() < endDate.getTime()) {
			minusYears = minusYears + 1;// 存在不滿足一年的情況,因其值是負數所以在這進行+1
		}
		return minusYears;
	}

	/**
	 * 日期之間相差的月數
	 */
	@SuppressWarnings("deprecation")
	public static int minusMonths(Date startDate, Date endDate) {
		Date start = new Date(startDate.getTime());// 不操作傳入進來的參數,否則會導startDate有修改,這個并不是外界程序所想看見的

		int minusYear = minusYears(start, endDate);
		int minusMonth = 0;

		if (minusYear != 0) {// 相關超過一年使其在，相關一年內進行計算
			minusMonth = minusYear * 12;
			start = addYears(start, minusYear);// 增加年好進行月比較
		}

		// 因上面有可能不滿足一年的情況(不在同一年且start移動年之后仍存在不是同一年的情況)
		if (startDate.getYear() != endDate.getYear() && endDate.getTime() > startDate.getTime()
				&& endDate.getYear() > start.getYear()) {
			minusMonth += 12;
			start = addYears(start, 1);// 使其在同一年內,再來計算月份的相差
		}
		if (startDate.getYear() != endDate.getYear() && endDate.getTime() < startDate.getTime()
				&& endDate.getYear() < start.getYear()) {
			minusMonth -= 12;
			start = addYears(start, -1);
		}

		minusMonth = minusMonth + endDate.getMonth() - start.getMonth();
		;
		start = addMonths(startDate, minusMonth);// 放入到同年同月中去,算一下是否滿足月差(用minusMonth準確)
		// 在同一個月當中,現判斷是否足月的情況
		if (minusMonth != 0) {// 開始與結束不為同年同月
			if (endDate.getTime() > startDate.getTime() && start.getTime() > endDate.getTime()) {
				minusMonth = minusMonth - 1;// 存在不滿足一月的情況
			} else if (endDate.getTime() < startDate.getTime() && start.getTime() < endDate.getTime()) {
				minusMonth = minusMonth + 1;// 存在不滿足一月的情況,因其值是負數所以在這進行+1
			}
		}// 同年同月是為0,所以在這不用去判斷
		return minusMonth;
	}

	/**
	 * 日期之間相差的小時數
	 */
	public static int minusHours(Date startDate, Date endDate) {
		long times = (endDate.getTime() - startDate.getTime()) / (60 * 60 * 1000);
		return Integer.parseInt(Long.toString(times));
	}

	/**
	 * 日期之間相差的毫秒
	 */
	public static long minusMilliSeconds(Date startDate, Date endDate) {
		return endDate.getTime() - startDate.getTime();
	}

	/**
	 * 從年月日時分秒毫秒上計算時差
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static Map minus(Date startDate, Date endDate) {
		Map map = new TreeMap();
		int minusYears = minusYears(startDate, endDate);
		map.put("year", minusYears);

		int minusMonths = minusMonths(add(startDate, map), endDate);
		map.put("month", minusMonths);
		map.put("month1", minusMonths(startDate, endDate));// 這個是計算數包含了前面的年數的相差月

		int minusDays = minusDays(add(startDate, map), endDate);
		map.put("day", minusDays);
		map.put("day1", minusDays(startDate, endDate));

		int minusHours = minusHours(add(startDate, map), endDate);
		map.put("hour", minusHours);
		map.put("hour1", minusHours(startDate, endDate));

		int minusMinutes = minusMinutes(add(startDate, map), endDate);
		map.put("minute", minusMinutes);
		map.put("minute1", minusMinutes(startDate, endDate));

		int minusSeconds = minusSeconds(add(startDate, map), endDate);
		map.put("second", minusSeconds);
		map.put("second1", minusSeconds(startDate, endDate));

		long minusMilliSeconds = minusMilliSeconds(add(startDate, map), endDate);
		map.put("minusMilliSecond", minusMilliSeconds);
		map.put("minusMilliSecond1", endDate.getTime() - startDate.getTime());
		return map;
	}

	/**
	 * 計算相差的開端的值
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static String minusOutsetStr(Date startDate, Date endDate) {
		Map map = minus(startDate, endDate);
		if ((Integer) map.get("year") != 0) {
			return map.get("year") + "年";
		}
		if ((Integer) map.get("month") != 0) {
			return map.get("month") + "月";
		}
		if ((Integer) map.get("day") != 0) {
			return map.get("day") + "天";
		}
		if ((Integer) map.get("hour") != 0) {
			return map.get("hour") + "小時";
		}
		if ((Integer) map.get("minute") != 0) {
			return map.get("minute") + "分鐘";
		}
		if ((Integer) map.get("second") != 0) {
			return map.get("second") + "秒";
		} else {
			return "1秒";
		}
	}

	/**
	 * 为指定时间加上年数、月数、天数、小时数 返回：java.util.Date addDateReturnDate("y",1,new Date())
	 */
	public static Date addDateReturnDate(String interval, int number, Date date) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		if (interval.equals("y")) {
			int currYear = gc.get(Calendar.YEAR);
			gc.set(Calendar.YEAR, currYear + number);
		} else if (interval.equals("m")) {
			int currMonth = gc.get(Calendar.MONTH);
			gc.set(Calendar.MONTH, currMonth + number);
		} else if (interval.equals("d")) {
			int currDay = gc.get(Calendar.DATE);
			gc.set(Calendar.DATE, currDay + number);
		} else if (interval.equals("h")) {
			int currDay = gc.get(Calendar.HOUR);
			gc.set(Calendar.HOUR, currDay + number);
		} else if (interval.equals("t")) {
			int currDay = gc.get(Calendar.MINUTE);
			gc.set(Calendar.MINUTE, currDay + number);
		} else if (interval.equals("s")) {
			int currDay = gc.get(Calendar.SECOND);
			gc.set(Calendar.SECOND, currDay + number);
		}
		return gc.getTime();
	}

	/**
	 * 
	 * <p>
	 * 获取指定历史日期时间
	 * </p>
	 * 
	 * @param year
	 *            最近n年 （正数）
	 * @param month
	 *            最近n月（正数）
	 * @param day
	 *            最近n日（正数）
	 * @return 返回 yyyy-MM-dd HH:mm:ss
	 * @throws
	 */
	public static String getBeforeDate(int year, int month, int day) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		calendar.setTime(new Date());
		calendar.add(Calendar.YEAR, -year);
		calendar.add(Calendar.MONTH, -month);
		calendar.add(Calendar.DAY_OF_MONTH, -day);
		return format.format(calendar.getTime());
	}

	/**
	 * 
	 * <p>
	 * 格式化日期
	 * </p>
	 * 
	 * @return 返回 yyyy-MM-dd HH:mm:ss
	 * @throws
	 */
	public static String getFormatDate(String str, String pattern) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = format1.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return format.format(date);
	}

	/**
	 * the pattern string is "yyyy-MM-dd"
	 * 
	 * @return
	 */
	public static String getDateString() {
		return getDateString(new Date(), OYYYY_MM_DD);
	}

	/**
	 * the pattern string is "yyyy-MM-dd"
	 * 
	 * @param date
	 * @return
	 */
	public static String getDateString(Date date) {
		return getDateString(date, OYYYY_MM_DD);
	}

	/**
	 * the pattern string is "MM-dd"
	 * 
	 * @return
	 */
	public static String getShortDateString() {
		return getDateString(new Date(), OMM_DD);
	}

	/**
	 * the pattern string is "MM-dd"
	 * 
	 * @param date
	 * @return
	 */
	public static String getShortDateString(Date date) {
		return getDateString(date, OMM_DD);
	}

	/**
	 * return a String object like date by specified pattern of today.
	 * 
	 * @param pattern
	 *            String
	 * @return String
	 */
	public static String getDateString(String pattern) {
		return getDateString(new Date(), pattern);
	}

	/**
	 * the pattern string is "HH:mm"
	 * 
	 * @return
	 */
	public static String getTimeString() {
		return getDateString(new Date(), HHMM);
	}

	/**
	 * the pattern string is "HH:mm"
	 * 
	 * @param date
	 * @return
	 */
	public static String getTimeString(Date date) {
		return getDateString(date, HHMM);
	}

	/**
	 * the pattern string is "yyyy-MM-dd HH:mm"
	 * 
	 * @return
	 */
	public static String getFullDateString() {
		return getDateString(new Date(), OYYYY_MM_DD_HH_MM);
	}

	/**
	 * the pattern string is "yyyy-MM-dd HH:mm"
	 * 
	 * @param date
	 * @return
	 */
	public static String getFullDateString(Date date) {
		return getDateString(date, OYYYY_MM_DD_HH_MM);
	}

	/**
	 * the pattern string is "MM-dd HH:mm"
	 * 
	 * @return
	 */
	public static String getShortFullDateString() {
		return getDateString(new Date(), OMM_DD_HH_MM);
	}

	/**
	 * the pattern string is "MM-dd HH:mm"
	 * 
	 * @param date
	 * @return
	 */
	public static String getShortFullDateString(Date date) {
		return getDateString(date, OMM_DD_HH_MM);
	}

	/**
	 * the pattern string is "HH:mm:ss"
	 * 
	 * @return
	 */
	public static String getFullTimeString() {
		return getDateString(new Date(), OYYYY_MM_DD_HH_MM_SS);
	}

	/**
	 * the pattern string is "HH:mm:ss"
	 * 
	 * @param date
	 * @return
	 */
	public static String getFullTimeString(Date date) {
		return getDateString(date, OHH_MM_SS);
	}

	/**
	 * the pattern string is "yyyy-MM-dd HH:mm:ss"
	 * 
	 * @return
	 */
	public static String getFullDateTimeString() {
		return getDateString(new Date(), OYYYY_MM_DD_HH_MM_SS);
	}

	/**
	 * the pattern string is "yyyy-MM-dd HH:mm:ss"
	 * 
	 * @param date
	 * @return
	 */
	public static String getFullDateTimeString(Date date) {
		return getDateString(date, OYYYY_MM_DD_HH_MM_SS);
	}

	/**
	 * the pattern string is "MM-dd HH:mm:ss"
	 * 
	 * @return
	 */
	public static String getShortFullDateTimeString() {
		return getDateString(new Date(), OMM_DD_HH_MM_SS);
	}

	/**
	 * the pattern string is "MM-dd HH:mm:ss"
	 * 
	 * @param date
	 * @return
	 */
	public static String getShortFullDateTimeString(Date date) {
		return getDateString(date, OMM_DD_HH_MM_SS);
	}

	/**
	 * return a String object like date by specified pattern of date.
	 * 
	 * @param date
	 *            Date Specifid date
	 * @param pattern
	 *            String
	 * @return String
	 */
	public static String getDateString(Date date, String pattern) {
		if (date == null || pattern == null || pattern.equals("")) {
			String message = "One of arguments is null or is a space string! date: \"" + date.toString()
					+ "\", pattern : \"" + pattern + "\".";
			logger.warn(message);
			throw new NullPointerException(message);
		}
		String ret = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			ret = sdf.format(date);
		} catch (IllegalArgumentException ex) {
			String message = "the given pattern is invalid! pattern : \"" + pattern + "\".";
			logger.error(message, ex);
			throw new RuntimeException(message, ex);
		}
		return ret;
	}

	/**
	 * 得到指定模式的日期字串
	 * 
	 * 
	 * @param strDate
	 *            String
	 * @param pattern
	 *            String
	 * @param newPattern
	 *            String
	 * @return String
	 */
	public static String getPatternDateString(String strDate, String pattern, String newPattern) {
		if (strDate == null || strDate.equals("") || pattern == null || pattern.equals("") || newPattern == null
				|| newPattern.equals("")) {
			String message = "One of arguments is null or is a space string! strDate: \"" + strDate
					+ "\" , pattern : \"" + pattern + "\" , newPattern : \"" + newPattern + "\".";
			logger.warn(message);
			throw new NullPointerException(message);
		}
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			sdf.setLenient(false);
			SimpleDateFormat newSdf = new SimpleDateFormat(newPattern);
			strDate = newSdf.format(sdf.parse(strDate));
		} catch (IllegalArgumentException illegalArgumentException) {
			String message = "One of those given patterns is invalid! pattern : " + pattern + " , newPattern : "
					+ newPattern;
			logger.error(message, illegalArgumentException);
			throw new RuntimeException(message, illegalArgumentException);
		} catch (ParseException ex) {
			/** @todo Handle this exception */
			StringBuffer sb = (new StringBuffer("The string \"")).append(strDate)
					.append("\" cannot be parsed to Date object,because it is not matching to pattern \"")
					.append(pattern).append("\"!");
			logger.error(sb.toString(), ex);
			// throw new RuntimeException(sb.toString(),ex);
		}
		return strDate;
	}

	/**
	 * <p>
	 * 返回当前日期的年份的字符串, pattern : "yyyy"
	 * </p>
	 * 
	 * @return
	 * @throws
	 */
	public static final String getYearString() {
		return getYearString(null);
	}

	/**
	 * <p>
	 * 返回指定日期的年份的字符串, pattern : "yyyy"
	 * </p>
	 * 
	 * @param date
	 * @return
	 * @throws
	 */
	public static final String getYearString(Date date) {
		if (null == date) {
			date = new Date();
		}
		return getDateString(date, YYYY);
	}

	/**
	 * <p>
	 * 返回当前日期的年月的字符串, pattern : "yyyyMM"
	 * </p>
	 * 
	 * @return
	 * @throws
	 */
	public static final String getYearMonthString() {
		return getYearMonthString(null);
	}

	/**
	 * <p>
	 * 返回指定日期的年月的字符串, pattern : "yyyyMM"
	 * </p>
	 * 
	 * @param date
	 * @return
	 * @throws
	 */
	public static final String getYearMonthString(Date date) {
		if (null == date) {
			date = new Date();
		}
		return getDateString(date, YYYYMM);
	}
	
}
