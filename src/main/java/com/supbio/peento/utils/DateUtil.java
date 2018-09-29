package com.supbio.peento.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;

public class DateUtil {

    private static Logger logger = LoggerFactory.getLogger(DateUtil.class);

    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public final static String DF_yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";

    /**
     * yyyy-MM-dd HH:mm:ss.SSS
     */
    public final static String DF_yyyy_MM_dd_HH_mm_ss_SSS = "yyyy-MM-dd HH:mm:ss.SSS";

    /**
     * yyyy-MM-dd
     */
    public final static String DF_yyyy_MM_dd = "yyyy-MM-dd";

    /**
     * yyyy年MM月dd日
     */
    public final static String DF_yyyy_MM_dd2 = "yyyy年MM月dd日";

    /**
     * HH:mm:ss
     */
    public final static String DF_HH_mm_ss = "HH:mm:ss";

    /**
     * MM-dd
     */
    public final static String DF_MM_dd = "MM-dd";

    /**
     * HH:mm:ss.SSS
     */
    public final static String DF_HH_mm_ss_SSS = "HH:mm:ss.SSS";

    /**
     * yyyyMM
     */
    public final static String DF_yyyyMM = "yyyyMM";

    /**
     * yyyy-MM
     */
    public final static String DF_yyyy_MM = "yyyy-MM";

    /**
     * yyyyMMddHHmmssSSS
     */
    public final static String DF_yyyyMMddHHmmssSSS = "yyyyMMddHHmmssSSS";

    /**
     * yyyyMMddHHmmss
     */
    public final static String DF_yyyyMMddHHmmss = "yyyyMMddHHmmss";

    /**
     * MM-dd mm:ss
     */
    public final static String DF_MM_dd_HH_mm = "MM-dd HH:mm";

    /**
     * yyyy-MM-dd HH:mm
     */
    public final static String DF_yyyy_MM_dd_HH_mm = "yyyy-MM-dd HH:mm";

    /**
     * yyyy年MM月dd日 HH:mm
     */
    public final static String DF_yyyy_MM_dd_HH_mm2 = "yyyy年MM月dd日 HH:mm";

    /**
     * yyyy年MM月dd日 HH:mm:ss
     */
    public final static String DF_yyyy_MM_dd_HH_mm3 = "yyyy年MM月dd日 HH:mm:ss";

    /**
     * yyyy年MM月dd日 HH:mm:ss
     */
    public final static String DF_MM_dd2 = "MM月dd日";

    /**
     * HH:mm:ss
     */
    public final static String DF_HH_mm = "HH:mm";

    /**
     * 得到unix时间戳，可直接存库表用
     *
     * @return
     */
    public static int getUnixTimeStamp() {
        return (int) (System.currentTimeMillis() / 1000);
    }

    public static void main(String[] args) throws Exception{

        Date today = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(today);
        long l = c.getTime().getTime();
        String date = unixTimeStamp2DateString(l, "yyyy-MM-dd");
        System.out.println(date);

        // String string = unixTimeStamp2DateString(new Integer(1482991508),
        // "yyyy-MM-dd");
        // System.out.println(string);
        String s1 = "2018-01-10 12:20:00";
        String s2 = "2018-01-10 12:22:01";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d1 = sdf.parse(s1);
        Date d2 = sdf.parse(s2);
        System.out.println(differMinute(d1, d2));

    }

    /**
     * 获取时间段内日期
     *
     * @param start
     * @param end
     * @return
     */
    public static List<Date> getDateListbetweenDate(Date start, Date end) {
        List<Date> lDate = new ArrayList<Date>();
        lDate.add(start);
        Calendar calBegin = Calendar.getInstance();
        calBegin.setTime(start);
        Calendar calEnd = Calendar.getInstance();
        calEnd.setTime(end);
        while (end.after(calBegin.getTime())) {
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            Date time = calBegin.getTime();
            if(!lDate.contains(time))
                lDate.add(time);
        }
        return lDate;
    }

    /**
     * 默认 格式yyyy-MM-dd
     *
     * @param startstr
     * @param endstr
     * @param dateformat
     * @return
     */
    public static List<String> getDateListbetweenDate(String startstr,
                                                      String endstr, String dateformat) {
        if (StringUtils.isBlank(dateformat)) {
            dateformat = "yyyy-MM-dd";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(dateformat);
        Date start;
        Date end;
        try {
            start = sdf.parse(startstr);
            end = sdf.parse(endstr);
            List<String> lDate = new ArrayList<String>();
            lDate.add(startstr);
            Calendar calBegin = Calendar.getInstance();
            calBegin.setTime(start);
            Calendar calEnd = Calendar.getInstance();
            calEnd.setTime(end);
            while (end.after(calBegin.getTime())) {
                calBegin.add(Calendar.DAY_OF_MONTH, 1);
                String format = sdf.format(calBegin.getTime());
                if(!lDate.contains(format))
                    lDate.add(format);
            }
            return lDate;
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 传入日期字符串"yyyy-MM-dd" 和小时分钟字符串得到时间戳 "HH:mm:ss" 到10位时间戳
     *
     * @param date
     * @param hourMinute
     * @return
     */
    public static Integer gettimestamp(String date, String hourMinute) {
        StringBuffer sb = new StringBuffer();
        sb.append(date);
        sb.append(" ");
        sb.append(hourMinute);
        String dateTime = sb.toString();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            int time = (int) (simpleDateFormat.parse(dateTime).getTime() / 1000L);
            return time;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 由时间戳得到日期和小时 date:"yyyy-MM-dd" hour:"HH:mm"
     *
     * @param timeStamp
     * @return
     */
    public static Object getDateStringAndHourString(Integer timeStamp) {
        if (timeStamp == null) {
            return null;
        }
        HashMap<String, Object> map = new HashMap<String, Object>();
        Date dateTime = new Date(timeStamp);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String dateString = simpleDateFormat.format(dateTime);
        String[] arr = dateString.split(" ");
        String date = arr[0];
        String hour = arr[1];
        map.put("date", date);
        map.put("hour", hour);
        return map;
    }

    public static Object getWeekName(int dayOfWeek) {
        if (Calendar.MONDAY == dayOfWeek) {
            return "星期一";
        } else if (Calendar.TUESDAY == dayOfWeek) {
            return "星期二";
        } else if (Calendar.WEDNESDAY == dayOfWeek) {
            return "星期三";
        } else if (Calendar.THURSDAY == dayOfWeek) {
            return "星期四";
        } else if (Calendar.FRIDAY == dayOfWeek) {
            return "星期五";
        } else if (Calendar.SATURDAY == dayOfWeek) {
            return "星期六";
        } else if (Calendar.SUNDAY == dayOfWeek) {
            return "星期日";
        } else {
            return "日期错误";
        }
    }

    public static String getWeekDay(Date date){
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (week < 0)
            week = 0;
        return weekDays[week];
    }

    // 由long类时间戳得到日期字符串
    public static String unixTimeStamp2DateString(long timeStamp, String format) {
        String timeStampLength = timeStamp+"";
        if(timeStampLength.length()<11){
            timeStamp=timeStamp*1000;
        }
        SimpleDateFormat datetimefmt = new SimpleDateFormat(format);
        Date date = new Date(timeStamp);
        String retStr = datetimefmt.format(date);
        return retStr;
    }

    // 由日期字符串和日期格式获取日期对象
    public static Date dateString2Date(String dateStr, String datePatten) {
        SimpleDateFormat format = new SimpleDateFormat(datePatten);
        try {
            Date date = format.parse(dateStr);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 日期转换字符串
     * @param date
     * @param pattern
     * @return
     */
    public static String date2Str(Date date,String pattern){
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     *时间戳转化为周几；
     */
    public static String timestamp2Dayweek(Integer timestamp){
        GregorianCalendar cal = new GregorianCalendar();
        long time = timestamp;
        cal.setTime(new Date(time*1000));
        int dow = cal.get(Calendar.DAY_OF_WEEK);
        System.out.println(timestamp*1000+"---------dayWeek-----"+dow);
        switch (dow) {
            case Calendar.MONDAY:
                return "周一";
            case Calendar.TUESDAY:
                return "周二";
            case Calendar.WEDNESDAY:
                return "周三";
            case Calendar.THURSDAY:
                return "周四";
            case Calendar.FRIDAY:
                return "周五";
            case Calendar.SATURDAY:
                return "周六";
            case Calendar.SUNDAY:
                return "周日";
        }
        return "Unknown";
    }

    /**
     * 判断现在时间是否为timestamp5分钟之前的时间。
     * @param timestamp
     * @return
     */
    public static boolean timestampIsValid(Integer timestamp) {
        boolean valid = false;
        GregorianCalendar cal = new GregorianCalendar();
        cal.add(Calendar.MINUTE, 5);
        long timestampnew = ((long)timestamp)*1000;
        valid = timestampnew - cal.getTimeInMillis() > 0;
        return valid;
    }
    /**
     * 整数(秒数)转换为时分秒格式(xx:xx:xx)
     * @param time
     * @return
     */
    public static String secToTime(int time) {
        String timeStr = null;
        int hour = 0;
        int minute = 0;
        int second = 0;
        if (time <= 0)
            return "00:00";
        else {
            minute = time / 60;
            if (minute < 60) {
                second = time % 60;
                timeStr = unitFormat(minute) + ":" + unitFormat(second);
            } else {
                hour = minute / 60;
                if (hour > 99)
                    return "99:59:59";
                minute = minute % 60;
                second = time - hour * 3600 - minute * 60;
                timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);
            }
        }
        return timeStr;
    }

    /**
     * 整数(秒数)转换为完整时分秒格式(xx:xx:xx)
     * 如 71   00:01:11
     * @param seconds
     * @return
     */
    public static String secToFullTime(int seconds) {
        StringBuffer sb = new StringBuffer();
        try {
            int temp = 0;
            temp = seconds / 3600;
            sb.append((temp < 10) ? "0" + temp + ":" : "" + temp + ":");

            temp = seconds % 3600 / 60;
            sb.append((temp < 10) ? "0" + temp + ":" : "" + temp + ":");

            temp = seconds % 3600 % 60;
            sb.append((temp < 10) ? "0" + temp : "" + temp);
        }catch (Exception e){

            logger.error(e.getMessage());
            return  "00:00:00";
        }

        return  sb.toString();
    }

    public static String unitFormat(int i) {
        String retStr = null;
        if (i >= 0 && i < 10)
            retStr = "0" + Integer.toString(i);
        else
            retStr = "" + i;
        return retStr;
    }

    // 由时间戳得到日期字符串
    public static String unixTimeStampInteger2DateString(Integer timeStamp, String format) {
        long l=(long)timeStamp.intValue()*1000;
        SimpleDateFormat datetimefmt = new SimpleDateFormat(format);
        Date date= new Date(l);
        String retStr = datetimefmt.format(date);
        return retStr;
    }
    // 由时间戳得到hh:mma格式
    public static String timeStampToHourString(Integer timeStamp) {
        long l=(long)timeStamp.intValue()*1000;
        SimpleDateFormat datetimefmt = new SimpleDateFormat("HH:mma", Locale.ENGLISH);
        Date date= new Date(l);
        String retStr = datetimefmt.format(date).toLowerCase();
        return retStr;
    }

    public static long unixDataStringToTimeStamp(String dataString,String format) throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        Date parsedDate = dateFormat.parse(dataString);
        return parsedDate.getTime();

    }

    // 由时间戳得到日期字符串
    public static Date unixTimeStampInteger2Date(Integer timeStamp) {
        long l=(long)timeStamp.intValue()*1000;
        Date date= new Date(l);
        return date;
    }

    public static Date unixDataStringToDate(String dataString,String format) throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        Date parsedDate = dateFormat.parse(dataString);
        return parsedDate;

    }

    /**
     * 某一个月的开始时间（某一个月的第一天的00:00）
     * @param dateString 某一个月（yyyy-MM）
     * @return
     * @throws ParseException
     */
    public static Integer beginMonth(String dateString) throws ParseException {

        Date date = unixDataStringToDate(dateString,"yyyy-MM");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Calendar calendar1 = new GregorianCalendar(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),1);
        return Math.toIntExact(calendar1.getTimeInMillis() / 1000);

    }
    /**
     * 某一个月的结束时间（某一个的后一月的开始时间的00:00）
     * @param dateString 某一个月（yyyy-MM）
     * @return
     * @throws ParseException
     */
    public static Integer endMonth(String dateString) throws ParseException {

        Date date = unixDataStringToDate(dateString,"yyyy-MM");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MONTH,calendar.get(Calendar.MONTH)+1);
        Calendar calendar1 = new GregorianCalendar(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),1);
        return Math.toIntExact(calendar1.getTimeInMillis() / 1000);

    }

    /**
     * 某一天的开始时间（yyyy-MM-dd 00:00:00）
     * @param dateString  某一天（yyyy-MM-dd）
     * @return
     */
    public static  Date startDay(String dateString){
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(DateUtil.dateString2Date(dateString,DateUtil.DF_yyyy_MM_dd));
        //一天的开始时间 yyyy:MM:dd 00:00:00
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        return calendar.getTime();
    }

    public static Date endDay(String dateString){
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(DateUtil.dateString2Date(dateString,DateUtil.DF_yyyy_MM_dd));
        //一天的结束时间 yyyy:MM:dd 23:59:59
        calendar.set(Calendar.HOUR_OF_DAY,23);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,59);
        calendar.set(Calendar.MILLISECOND,59);
        return calendar.getTime();
    }
    /**
     * 两个date相差的秒数
     * @param d1
     * @param d2
     * @return
     */
    public static Integer differSecond(Date d1, Date d2){
        if (d1 == null || d2 == null) {
            return 0;
        }
        int second = (int)((d1.getTime() - d2.getTime())/1000);
        return second < 0 ? - second : second;
    }

    /**
     * 两个date相差的分钟数（忽略秒，不足一分钟返回1）
     * @param d1
     * @param d2
     * @return
     */
    public static Integer differMinute(Date d1, Date d2){
        if (d1 == null || d2 == null) {
            return 0;
        }
        d1 = DateUtils.setSeconds(d1, 0);
        d2 = DateUtils.setSeconds(d2, 0);
        long time = d1.getTime() - d2.getTime();
        time = time < 0 ? - time : time;
        int minute = (int)(time / (1000*60));
        return minute == 0 ? 1 : minute;
    }

    /**
     * 两个date相差的分钟数（向上取整）
     * @param d1
     * @param d2
     * @return
     */
    public static Integer differMinuteUp(Date d1, Date d2){
        if (d1 == null || d2 == null) {
            return 0;
        }
        long time = d1.getTime() - d2.getTime();
        time = time < 0 ? - time : time;
        double second = (double)(time / 1000);
        return (int)(Math.ceil(second/60));
    }

    /**
     * 两个date相差的分钟数（向下取整）
     * @param d1
     * @param d2
     * @return
     */
    public static Integer differMinuteDown(Date d1, Date d2){
        if (d1 == null || d2 == null) {
            return 0;
        }
        long time = d1.getTime() - d2.getTime();
        time = time < 0 ? - time : time;
        int second = (int)(time / 1000);
        return second/60;
    }

    /**
     * 按指定的字符串格式将字符串型日期转化为java.util.Date型日期
     *
     * @param dateFormatType
     *            "yyyy-MM-dd" 或者 "yyyy-MM-dd hh:mm:ss"
     * @return java.util.Date型日期
     * @Param dateStr 字符型日期
     */
    public static Date turnStrDateToJavaUtilDate(String strDate, String dateFormatType) {

        Date javaUtilDate = null;
        DateFormat df = new SimpleDateFormat(dateFormatType);
        if (strDate != null && (!"".equals(strDate)) && dateFormatType != null && (!"".equals(dateFormatType))) {

            try {

                javaUtilDate = df.parse(strDate);
            } catch (ParseException e) {
                logger.error(e.getMessage());
            }
        }
        return javaUtilDate;
    }

    /**
     * 根据传入的时间和相差分钟数，获取计算后的时间，可加可减
     * @param date
     * @param time
     * @return
     */
    public static Date addMinute(Date date ,Integer time){
        Calendar cal= Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE,time);
        return cal.getTime();
    }

    /**
     * 根据传入的时间和相差小时数，获取计算后的时间，可加可减
     * @param date
     * @param time
     * @return
     */
    public static Date addHour(Date date ,Integer time){
        Calendar cal= Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR_OF_DAY,time);
        return cal.getTime();
    }

    /**
     * 根据传入的时间和天数，获取计算后的时间，可加可减
     * @param date
     * @param time
     * @return
     */
    public static Date addDay (Date date ,Integer time){
        Calendar cal= Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH,time);
        return cal.getTime();
    }

    /**
     * 根据传入的时间和相差秒数，获取计算后的时间，可加可减
     * @param date
     * @param time
     * @return
     */
    public static Date addSecond(Date date ,Integer time){
        Calendar cal= Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.SECOND,time);
        return cal.getTime();
    }

    /**
     * 根据开始时间和时长（00:00:00） 计算结束时间
     * @param startDate
     * @param duration
     * @return
     */
    public static Date calculateEndDateByStartDateAndDuration(Date startDate,String duration){
        String [] videoTime = duration.split(":");
        Date endDate = addHour(startDate,Integer.valueOf(videoTime[0]));
        endDate = addMinute(endDate,Integer.valueOf(videoTime[1]));
        endDate = addSecond(endDate,Integer.valueOf(videoTime[2]));
        return endDate;
    }

    /**
     * 获取当天的开始时间
     *
     * @param
     * @return java.util.Date型日期
     *
     */
    public static Date getNowStartTime() {
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        return todayStart.getTime();
    }

    /**
     * 获取当天的结束时间
     *
     * @param
     * @return java.util.Date型日期
     *
     */
    public static Date getNowEndTime() {
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        return todayEnd.getTime();
    }

    /**
     * 根据传入的日期（2000-01-01 16:46:00）+ 传入的时间（2018-07-03 10:46:00）---》returnDate(2018-07-03 16:46:00)
     * @param fromDate
     * @return java.util.Date型日期  returnDate
     */
    public  static Date getFromTime2NowDate(Date fromDate,Date fromTime){
        Calendar nowDate = Calendar.getInstance();
        nowDate.setTime(fromDate);
        Calendar returnDate = Calendar.getInstance();
        returnDate.setTime(fromTime);
        returnDate.set(nowDate.get(Calendar.YEAR), nowDate.get(Calendar.MONTH), nowDate.get(Calendar.DAY_OF_MONTH));
        return returnDate.getTime();
    }

    /**
     * 通过生日日期和当前日期计算年龄
     * @param birthDate
     * @param currentDate
     * @return
     */
    public static Integer calculateAge(Date birthDate, Date currentDate) {
        LocalDate birthLocalDate = birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate currentLocalDate = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        if ((birthDate != null) && (currentDate != null)) {
            return Period.between(birthLocalDate, currentLocalDate).getYears();
        } else {
            return 0;
        }
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param smdate
     *            较小的时间
     * @param bdate
     *            较大的时间
     * @return 相差天数
     */
    public static int daysBetween(Date smdate, Date bdate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            smdate = sdf.parse(sdf.format(smdate));
            bdate = sdf.parse(sdf.format(bdate));
            Calendar cal = Calendar.getInstance();
            cal.setTime(smdate);
            long time1 = cal.getTimeInMillis();
            cal.setTime(bdate);
            long time2 = cal.getTimeInMillis();
            long between_days = (time2 - time1) / (1000 * 3600 * 24);

            return Integer.parseInt(String.valueOf(between_days));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 辅助方法，用于比较两个字符串格式的日期的前后顺序
     *
     * @param dateStr1
     *            日期1的字符串
     * @param dateStr2
     *            日期2的字符串
     * @param fmt
     *            日期1和日期2的各种，默认为"yyyy-MM-dd hh:mm"
     * @return 1代表日期1在日期2之后，-1代表日期1在日期2之前，0代表日期1和日期2相等
     */
    public static int compareDate(String dateStr1, String dateStr2, String fmt) {
        if (null == fmt || "".equals(fmt.trim())) {
            fmt = "yyyy-MM-dd hh:mm:ss";
        }
        // 先将日期字符串转换成日期格式，
        SimpleDateFormat formatDate = new SimpleDateFormat(fmt);
        try {
            Date date1 = formatDate.parse(dateStr1);
            Date date2 = formatDate.parse(dateStr2);
            return compareDate(date1, date2);
        } catch (ParseException e) {
            logger.error(e.getMessage());
            return 0;
        }
    }

    /**
     * 比较两个日期的前后顺序
     *
     * @param date1
     *            日期1
     * @param date2
     *            日期2
     * @return 1代表日期1在日期2之后，-1代表日期1在日期2之前，0代表日期1和日期2相等
     */
    public static int compareDate(Date date1, Date date2) {
        try {
            if (date1.getTime() > date2.getTime()) {
                return 1;
            } else if (date1.getTime() < date2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return 0;
    }

}
