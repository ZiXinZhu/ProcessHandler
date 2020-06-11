package com.zzx.transactions.utils;

import com.zzx.transactions.exceptions.CommonException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;

public class TimeUtil {

    /**
     * 标准时间格式
     */
    public static final String STANDARD_DATE = "yyyy-MM-dd HH:mm:ss";
    /**
     * 带毫秒的时间格式
     */
    public static final String LONG_DATE = "yyyy-MM-dd HH:mm:ss:SSS";
    /**
     * 只有年月日的格式
     */
    public static final String SHORT_DATE = "yyyy-MM-dd";

    /**
     * 世界时间标志
     */
    private static final String UTC = "UTC";

    /**
     * 格林时间标志
     */
    private static final String GMT = "GMT";

    /**
     * 锁对象
     */
    private static final Object LOCK_OBJ = new Object();
    /**
     * 存放SimpleDateFormat的map
     */
    private static Map<String, ThreadLocal<SimpleDateFormat>> SDF_MAP = new ConcurrentHashMap<>();

    /**
     * String类型是的时间转Date类型
     *
     * @param date
     * @param pattern
     * @return
     */
    public static Date stringConvertToDate(String date, String pattern) {
        if (Objects.isNull(date) || Objects.isNull(pattern) || date.length() != pattern.length()) {
            throw new CommonException("String时间格式错误");
        }
        try {
            return getSDF(pattern).parse(date);
        } catch (ParseException e) {
            throw new CommonException("String转Date错误！");
        }
    }

    /**
     * Date类型的时间转String类型
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String dateConvertToString(Date date, String pattern) {
        if (Objects.isNull(date) || Objects.isNull(pattern)) {
            throw new CommonException("Date类型的时间转String类型时数据为空！");
        }
        return getSDF(pattern).format(date);
    }

    /**
     * 返还所传格式的SimpleDateFormat
     *
     * @param pattern
     * @return SimpleDateFormat
     */
    private static SimpleDateFormat getSDF(final String pattern) {
        ThreadLocal<SimpleDateFormat> t1 = SDF_MAP.get(pattern);
        if (Objects.isNull(t1)) {
            synchronized (LOCK_OBJ) {
                t1 = SDF_MAP.get(pattern);
                if (Objects.isNull(t1)) {
                    t1 = ThreadLocal.withInitial(() -> new SimpleDateFormat(pattern));
                }
            }
        }
        return t1.get();
    }

    /**
     * 时区转换可以是GMT也可以是UTC
     * @param oldTimeZone
     * @param newTimeZone
     * @param dateTime
     * @return
     */
    public static String timeZoneTransfer(String oldTimeZone, String newTimeZone, String  dateTime) {
        if (oldTimeZone.contains(UTC)||newTimeZone.contains(UTC)){
            oldTimeZone=oldTimeZone.replace("UTC",GMT);
            newTimeZone=newTimeZone.replace("UTC",GMT);
        }
        TimeZone oldSource=TimeZone.getTimeZone(oldTimeZone);
        TimeZone newSource=TimeZone.getTimeZone(newTimeZone);

        SimpleDateFormat oleTime=new SimpleDateFormat(STANDARD_DATE);
        SimpleDateFormat newTime=new SimpleDateFormat(STANDARD_DATE);

        oleTime.setTimeZone(oldSource);
        newTime.setTimeZone(newSource);

        Date olds= null;
        try {
            olds = oleTime.parse(dateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return newTime.format(olds);
    }

    public static void main(String[] args) {
        Date date = stringConvertToDate("2020-01-02 21:45:31", STANDARD_DATE);
        System.out.println(date);
        String dateString = dateConvertToString(new Date(), LONG_DATE);
        System.out.println(dateString);

        String dateTime=timeZoneTransfer("UTC-2:00","UTC+8:00","2020-01-02 21:45:31");
        System.out.println(dateTime);
    }
}
