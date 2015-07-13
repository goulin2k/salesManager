/**
 * 
 */
package com.sales.common;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class WorkCalendar {
	private static final String TIME_WORKSTART = "09:00:00";
	private static final String TIME_WORKOFF = "17:30:00";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WorkCalendar wa = new WorkCalendar();
		Date date = new Date();
		date.setHours(13);
		System.out.println(date + " isTimeInWorkTime:\t" + wa.isTimeInWorkTime(date));
		date.setHours(19);
		System.out.println(date + " isTimeInWorkTime:\t" + wa.isTimeInWorkTime(date));
		date.setHours(6);
		System.out.println(date + " isTimeInWorkTime:\t" + wa.isTimeInWorkTime(date));
		
		date.setHours(13);
		System.out.println(date + " ajustWorkTime to:\t" + wa.adjustWorkTime(date));
		date.setHours(19);
		System.out.println(date + " ajustWorkTime to:\t" + wa.adjustWorkTime(date));
		date.setHours(6);
		System.out.println(date + " ajustWorkTime to:\t" + wa.adjustWorkTime(date));
		
		date = strToDate("2014-03-11 13:09:00");
		System.out.println(date + " ajustWorkDay to:\t" + wa.adjustWorkDay(date));
		date = strToDate("2014-03-08 13:09:00");
		System.out.println(date + " ajustWorkDay to:\t" + wa.adjustWorkDay(date));
		date = strToDate("2014-03-09 13:09:00");
		System.out.println(date + " ajustWorkDay to:\t" + wa.adjustWorkDay(date));
		
		date = strToDate("2014-03-11 13:09:00");
		System.out.println(date + " adjustToWorkTime to:\t" + wa.adjustToWorkTime(date));
		date = strToDate("2014-03-08 00:09:00");
		System.out.println(date + " adjustToWorkTime to:\t" + wa.adjustToWorkTime(date));
		date = strToDate("2014-03-10 00:09:00");
		System.out.println(date + " adjustToWorkTime to:\t" + wa.adjustToWorkTime(date));
		date = strToDate("2014-03-14 22:09:00");
		System.out.println(date + " adjustToWorkTime to:\t" + wa.adjustToWorkTime(date));
		
		Date begin = strToDate("2014-03-11 13:09:00");
		Date end  = strToDate("2014-03-13 13:09:00");
		System.out.println(end + " - " + begin + " diff days:\t" + wa.diffDays(begin, end) + " days.");
		
		begin = strToDate("2014-03-11 13:09:00");
		end  = strToDate("2014-03-23 13:09:00");
		System.out.println(end + " - " + begin + " diff WorkDays:\t" + wa.diffWorkDays(begin, end) + " days.");
		
		begin = strToDate("2014-03-14 13:09:00");
		end  = strToDate("2014-03-17 13:09:00");
		System.out.println(end + " - " + begin + " diff WorkDays:\t" + wa.diffWorkDays(begin, end) + " days.");
		
		begin = strToDate("2014-03-11 13:09:00");
		end  = strToDate("2014-03-14 13:09:00");
		System.out.println(end + " - " + begin + " diff WorkDays:\t" + wa.diffWorkDays(begin, end) + " days.");
		
		System.out.println("------------相同日期------------------------------------------------------------------");
		begin = strToDate("2014-03-11 13:09:00");
		end  = strToDate("2014-03-11 17:18:00");
		System.out.println(end + " - " + begin + " diff WorkDays:\t" + wa.diffWorkHours(begin, end) + " Hours.");
		
		System.out.println("------------相同日期下班时间完成------------------------------------------------------------------");
		begin = strToDate("2014-03-11 13:09:00");
		end  = strToDate("2014-03-11 20:18:00");
		System.out.println(end + " - " + begin + " diff WorkDays:\t" + wa.diffWorkHours(begin, end) + " Hours.");
		
		System.out.println("------------不同日期下班时间完成------------------------------------------------------------------");
		begin = strToDate("2014-03-11 13:09:00");
		end  = strToDate("2014-03-12 06:18:00");
		System.out.println(end + " - " + begin + " diff WorkDays:\t" + wa.diffWorkHours(begin, end) + " Hours.");
		
		System.out.println("------------跨周日期下班时间完成------------------------------------------------------------------");
		begin = strToDate("2014-03-14 13:09:00");
		end  = strToDate("2014-03-17 09:18:00");
		System.out.println(end + " - " + begin + " diff WorkDays:\t" + wa.diffWorkHours(begin, end) + " Hours.");
		
		System.out.println("------------跨周日期下班时间完成------------------------------------------------------------------");
		begin = strToDate("2014-03-14 13:09:00");
		end  = strToDate("2014-03-17 12:18:00");
		System.out.println(end + " - " + begin + " diff WorkDays:\t" + wa.diffWorkHours(begin, end) + " Hours.");
		
		System.out.println("------------跨周日期下班时间完成------------------------------------------------------------------");
		begin = strToDate("2014-03-14 13:09:00");
		end  = strToDate("2014-03-18 12:18:00");
		System.out.println(end + " - " + begin + " diff WorkDays:\t" + wa.diffWorkHours(begin, end) + " Hours.");
	}
	
	/** 获取时间字符串的小时字段
	 * @param timeString
	 * @return
	 */
	private static int getHours(String timeString) {
		String s = timeString.substring(0, 2);
		return Integer.parseInt(s);
	}
	
	/** 获取时间字符串的分钟字段
	 * @param timeString
	 * @return
	 */
	private static int getMinutes(String timeString) {
		String s = timeString.substring(3, 5);
		return Integer.parseInt(s);
	}

	/** 获取两个日期之间的工时数
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public int getWorkHoursBetweenDays(Date startDate, Date endDate) {
		//判断开始、结束时间是否在休息时间内
		return 0;
	}
	
	/**
	 * 调整时间到工作日和工作时间
	 * @param date
	 * @return
	 */
	private static Date adjustToWorkTime(Date date) {
		Date dt = adjustWorkTime(date);
		return adjustWorkDay(dt);
	}

	/**
	 * 判断一个时间是否在工作时间内 
	 * @param date
	 * @return
	 */
	private static boolean isTimeInWorkTime(Date date) {
		DateFormat fmt = new SimpleDateFormat("HH:mm:ss");
		String timeString = fmt.format(date);
		if(timeString.compareTo(TIME_WORKSTART)>0 
				&& timeString.compareTo(TIME_WORKOFF) <0)
			return true;
		
		return false;
	}
	
	
	
	/** 两个日期之间的间隔天数
	 * @param begin
	 * @param end
	 * @return
	 */
	public static long diffDays(Date begin, Date end) {
		Calendar cal = Calendar.getInstance();
		
		long days=(end.getTime()-begin.getTime())/(24*60*60*1000);
		cal.setTime(begin);
		int hourBegin = cal.get(Calendar.HOUR_OF_DAY);
		int minBegin = cal.get(Calendar.MINUTE);
		cal.setTime(end);
		int hourEnd = cal.get(Calendar.HOUR_OF_DAY);
		int minEnd = cal.get(Calendar.MINUTE);
		
		//如果开始时间大于结束时间，天数加一（处理余数问题）
		if((hourBegin*100 + minBegin)>(hourEnd*100 + minEnd))
			days++;
		return days;
	}
	
	/**
	 * 两个日期之间的间隔星期数
	 * @param begin
	 * @param end
	 * @return
	 */
	public static long diffWeeks(Date begin, Date end) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(begin);
		int yearBegin = cal.get(Calendar.YEAR);
		int weekBegin = cal.get(Calendar.WEEK_OF_YEAR);
		cal.setTime(end);
		int yearEnd = cal.get(Calendar.YEAR);
		
		int weekEnd = cal.get(Calendar.WEEK_OF_YEAR);
		if(yearEnd == yearBegin && weekEnd == 1)
			weekEnd = 53;
		long interval = (weekEnd - weekBegin) + (yearEnd - yearBegin) * 52;
		
		/*TODO	*/
		if(interval < 0)
			System.out.println("week interval=" + interval);
		
		return interval;
	}
	
	/**
	 * 两个日期之间的间隔月份数
	 * @param begin
	 * @param end
	 * @return
	 */
	public static long diffMonths(Date begin, Date end) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(begin);
		int yearBegin = cal.get(Calendar.YEAR);
		int monthBegin = cal.get(Calendar.MONTH);
		cal.setTime(end);
		int yearEnd = cal.get(Calendar.YEAR);
		int monthEnd = cal.get(Calendar.MONTH);
		long interval = (monthEnd - monthBegin) + (yearEnd - yearBegin) * 12;
		
		/** TODO		*/
		if(interval < 0)
			System.out.println("month interval=" + interval);
		
		return interval;
	}
	
	/**
	 * 指定的日期加上天数
	 * @param date
	 * @param interval
	 * @return
	 */
	public static Date addDays(Date date, int interval) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, interval);
		
		return cal.getTime();
	}
	
	
	
	/**	两个日期之间的间隔工作日
	 * @param begin
	 * @param end
	 * @return
	 */
	public static int diffWorkDays(Date begin, Date end) {
		
		
		int holidays = 0;
		int days = new Long(diffDays(begin, end)).intValue();
		Calendar cal = Calendar.getInstance();
		cal.setTime(begin);
		int weekBegin = cal.get(Calendar.DAY_OF_WEEK);
		cal.setTime(end);
		int weekEnd = cal.get(Calendar.DAY_OF_WEEK);
		
		holidays = days/7 * 2;			//开始日期到结束日期中间有多少个休息日
		if(weekBegin > weekEnd)		//若结束日期的星期几小于开始日期，自动增加2个休息日
			holidays = holidays + 2;
		return days - holidays;
		
	}
	
	/** 计算两个日期之间的工作小时数（只去掉两个日期之间的周末时间，法定节假日未去掉）
	 * @param begin
	 * @param end
	 * @return
	 */
	public static int diffWorkHours(Date begin, Date end) {
		//调整时间和日期到工作日和工作时间
		begin = adjustToWorkTime(begin);
		begin = adjustWorkDay(begin);
		end = adjustToWorkTime(end);
		end = adjustWorkDay(end);
		
		int workdays = diffWorkDays(begin, end);
		Calendar cal = Calendar.getInstance();
		cal.setTime(begin);
		int hourBegin = cal.get(Calendar.HOUR_OF_DAY);
		int minBegin = cal.get(Calendar.MINUTE);
		cal.setTime(end);
		int hourEnd = cal.get(Calendar.HOUR_OF_DAY);
		int minEnd = cal.get(Calendar.MINUTE);
		
		int workHours = workdays * 8 + (hourEnd - hourBegin);
		if(minEnd < minBegin)		//不到一个小时减一
			workHours--;
		
		return workHours;
	}
	
	/**
	 * 调整当前时间到工作时间（如果是下班时间调整到最后一个下班时间）
	 * @param date
	 * @return
	 */
	private static Date adjustWorkTime(Date date) {
		DateFormat fmt = new SimpleDateFormat("HH:mm:ss");
		String timeString = fmt.format(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		
		if(isTimeInWorkTime(date)) 
			return date;
		
		cal.set(Calendar.HOUR_OF_DAY, getHours(TIME_WORKOFF));
		cal.get(Calendar.HOUR);
		cal.set(Calendar.MINUTE, getMinutes(TIME_WORKOFF));
		cal.get(Calendar.MINUTE);
		if(timeString.compareTo(TIME_WORKOFF) < 0 ) {
			cal.add(Calendar.DATE, -1);
		} 
			
		return cal.getTime();
	}
	
	/**调整当前日期到工作日（如果是周末调整到最后一个周五的下班时间）
	 * 
	 * @param date
	 * @return
	 */
	private static Date adjustWorkDay(Date date) {
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		
		if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
			cal.set(Calendar.HOUR_OF_DAY, getHours(TIME_WORKOFF));
			cal.get(Calendar.HOUR);
			cal.set(Calendar.MINUTE, getMinutes(TIME_WORKOFF));
			cal.get(Calendar.MINUTE);
		} else if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			cal.add(Calendar.DAY_OF_MONTH, -2);
			cal.set(Calendar.HOUR_OF_DAY, getHours(TIME_WORKOFF));
			cal.get(Calendar.HOUR);
			cal.set(Calendar.MINUTE, getMinutes(TIME_WORKOFF));
			cal.get(Calendar.MINUTE);
		}
		
		return cal.getTime();
	
	}
	

	/**
	 * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param strDate
	 * @return
	 */
	public static Date strToDate(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}
	
	/**
	 * 获得指定日期所在的季度
	 * @param date
	 * @return
	 */
	public static int getQuarter(Date date) {
		if (date == null)
			return -1;
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int month = calendar.get(Calendar.MONTH) + 1;
		
		if(month <=3)
			return 1;
		else if(month > 3 && month <=6)
			return 2;
		else if(month >6 && month <=9)
			return 3;
		else
			return 4;
	}
	
	/**
	 * 获得指定季度的第一天
	 * @param quarter
	 * @return
	 */
	public static Date getFirstDayOfQuarter(int year, int quarter) {
		Calendar calendar = Calendar.getInstance();
		
		calendar.setTime(new Date());  
		calendar.set(Calendar.YEAR, year);
        int month = getQuarterInMonth(calendar.get(Calendar.MONTH)+1, true);  
        calendar.set(Calendar.MONTH, month - 1);  
        calendar.set(Calendar.DAY_OF_MONTH, 1); 
        return calendar.getTime();
	}
	
	/**
	 * 获得指定季度的最后一天
	 * @param quarter
	 * @return
	 */
	public static Date getLastDayOfQuarter(int year, int quarter) {
		Calendar calendar = Calendar.getInstance();

		calendar.setTime(new Date());
		calendar.set(Calendar.YEAR, year);
		int month = getQuarterInMonth(calendar.get(Calendar.MONTH), false);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DAY_OF_MONTH, 0);
		return calendar.getTime();
	}
	
	private static int getQuarterInMonth(int month, boolean isQuarterStart) {  
        int months[] = { 1, 4, 7, 10 };  
        if (!isQuarterStart) {  
            months = new int[] { 3, 6, 9, 12 };  
        }  
        if (month >= 0 && month <= 2)  
            return months[0];  
        else if (month >= 3 && month <= 5)  
            return months[1];  
        else if (month >= 6 && month <= 8)  
            return months[2];  
        else  
            return months[3];  
    } 

}
