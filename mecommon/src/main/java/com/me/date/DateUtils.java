package com.me.date;

import com.me.object.ObjectUtil;
import com.me.string.StringUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by zjm on 2017/4/11.
 */
public class DateUtils {

    public final static String DATE_TYPE_YYYYMMDD = "yyyyMMdd";
	public final static String DATE_TYPE_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
	public final static String DATE_TYPE_TIMESTAMP = "yyyy-MM-dd HH:mm:ss";
	public final static String DATE_TYPE_TIMESTAMP_NO_MINUS = "yyyyMMdd HH:mm:ss";
	public final static String DATE_TYPE_YYMMDDHH = "yyMMddHH";
	public final static String DATE_TYPE_YYMMDDHHMMSS = "yyMMddHHmmss";
	public final static String DATE_TYPE_YYYYMM = "yyyyMM";
	public final static String DATE_TYPE_YYYYMMDD_HHMMSS = "yyyyMMdd HH:mm:ss";
	public final static String DATE_TYPE_YYYYMMDD_HHMMSS_WITH_SLASH = "yyyy/MM/dd HH:mm:ss";

    /**
	 * @param milliseconds 时间-毫秒
	 * @param days 时间间隔日期-天数
	 * @return 目标时间-毫秒
	 */
	public static Long toDate(Long milliseconds, int days){
	    if(ObjectUtil.isNull(milliseconds)){
	        milliseconds = System.currentTimeMillis();
        }
	    Long dayToMinutes = TimeUnit.MILLISECONDS.convert(days, TimeUnit.DAYS);
	    return milliseconds - dayToMinutes;
    }

    /**
     * 毫秒数转换为时间字符串
     * @param milliseconds 时间-毫秒
     * @param pattern
     * @return 时间字符串
     */
    public static String convertToStr(Long milliseconds, String pattern){
        if(StringUtil.isBlank(pattern)){
            pattern = DATE_TYPE_YYYYMMDD_HHMMSS_WITH_SLASH;
        }
        if(ObjectUtil.isNull(milliseconds)){
            milliseconds = System.currentTimeMillis();
        }
        Date date = new Date(milliseconds);
        SimpleDateFormat sd = new SimpleDateFormat(pattern);
        return sd.format(date);
    }

    /**
     * 时间转换为字符串
     * @param date 日期
     * @param pattern
     * @return 时间字符串
     */
    public static String convertToStr(Date date, String pattern){
        if(StringUtil.isBlank(pattern)){
            pattern = DATE_TYPE_YYYYMMDD_HHMMSS_WITH_SLASH;
        }
        if(ObjectUtil.isNull(date)){
            date = new Date();
        }
        return new SimpleDateFormat(pattern).format(date);
    }

    /**
     * 字符串转换成日期
     * @param dateStr
     * @param format 日期格式
     * @return 日期
     */
    public static Date convertToDate(String dateStr, String format){
        if(StringUtil.isBlank(format)){
            format = DATE_TYPE_YYYYMMDD_HHMMSS_WITH_SLASH;
        }
        SimpleDateFormat sd = new SimpleDateFormat(format);
        if(StringUtil.isBlank(dateStr)){
            dateStr = convertToStr(new Date(), format);
        }
        try{
            return sd.parse(dateStr);
        }catch(ParseException e){
            throw new RuntimeException(e);
        }
    }
}
