package com.zzx.transactions.utils;

import com.zzx.transactions.exceptions.CommonException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class TimeUtil {

    /**
     * ��׼ʱ���ʽ
     */
    public static final String STANDARD_DATE = "yyyy-MM-dd HH:mm:ss";
    /**
     * �������ʱ���ʽ
     */
    public static final String LONG_DATE = "yyyy-MM-dd HH:mm:ss:SSS";
    /**
     * ֻ�������յĸ�ʽ
     */
    public static final String SHORT_DATE = "yyyy-MM-dd";

    /**
     * ����ʱ���־
     */
    private static final String UTC = "UTC";

    /**
     * ����ʱ���־
     */
    private static final String GMT = "GMT";

    /**
     * ������
     */
    private static final Object LOCK_OBJ = new Object();
    /**
     * ���SimpleDateFormat��map
     */
    private static Map<String, ThreadLocal<SimpleDateFormat>> SDF_MAP = new ConcurrentHashMap<>();

    /**
     * String�����ǵ�ʱ��תDate����
     *
     * @param date
     * @param pattern
     * @return
     */
    public static Date stringConvertToDate(String date, String pattern) {
        if (Objects.isNull(date) || Objects.isNull(pattern) || date.length() != pattern.length()) {
            throw new CommonException("Stringʱ���ʽ����");
        }
        try {
            return getSDF(pattern).parse(date);
        } catch (ParseException e) {
            throw new CommonException("StringתDate����");
        }
    }

    /**
     * Date���͵�ʱ��תString����
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String dateConvertToString(Date date, String pattern) {
        if (Objects.isNull(date) || Objects.isNull(pattern)) {
            throw new CommonException("Date���͵�ʱ��תString����ʱ����Ϊ�գ�");
        }
        return getSDF(pattern).format(date);
    }

    /**
     * ����������ʽ��SimpleDateFormat
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
     * ʱ��ת��������GMTҲ������UTC
     *
     * @param oldTimeZone
     * @param newTimeZone
     * @param dateTime
     * @return
     */
    public static String timeZoneTransfer(String oldTimeZone, String newTimeZone, String dateTime) {
        if (oldTimeZone.contains(UTC) || newTimeZone.contains(UTC)) {
            oldTimeZone = oldTimeZone.replace("UTC", GMT);
            newTimeZone = newTimeZone.replace("UTC", GMT);
        }
        TimeZone oldSource = TimeZone.getTimeZone(oldTimeZone);
        TimeZone newSource = TimeZone.getTimeZone(newTimeZone);

        SimpleDateFormat oleTime = new SimpleDateFormat(STANDARD_DATE);
        SimpleDateFormat newTime = new SimpleDateFormat(STANDARD_DATE);

        oleTime.setTimeZone(oldSource);
        newTime.setTimeZone(newSource);

        Date olds = null;
        try {
            olds = oleTime.parse(dateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return newTime.format(olds);
    }

    /**
     * ��ȡ��������ڵ�ʱ��
     *
     * @return
     */
    public static String getFluterDateTime() {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        calendar.add(Calendar.YEAR, 1);
//        calendar.add(Calendar.MONTH,1);
//        calendar.add(Calendar.WEEK_OF_MONTH,1);
//        calendar.add(Calendar.DAY_OF_WEEK,1);
//        calendar.add(Calendar.HOUR,1);
//        calendar.add(Calendar.MINUTE,1);
//        calendar.add(Calendar.SECOND,1);
        Date date = calendar.getTime();
        return dateConvertToString(date, STANDARD_DATE);
    }

    /**
     * ��ȡĳ��ʱ����һ�ܵĵڼ���
     *
     * @param dateTime
     * @return
     */
    public static int indexOfDateTime(String dateTime) {
        ;
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(stringConvertToDate(dateTime, STANDARD_DATE));
        return calendar.get(Calendar.DAY_OF_WEEK);
    }


    /**
     * ��ȡ����һ��ʱ��
     *
     * @param dateTime
     * @return
     */
    public static String getMondayDateTime(String dateTime) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(stringConvertToDate(dateTime, STANDARD_DATE));
        int index = calendar.get(Calendar.DAY_OF_WEEK);
        if (index == 2) {
            return dateTime;
        }

        if (index == 1) {
            calendar.add(Calendar.DAY_OF_WEEK, -6);
            Date date = calendar.getTime();
            return dateConvertToString(date, STANDARD_DATE);
        }
        calendar.add(Calendar.DAY_OF_WEEK, 2 - index);
        Date date = calendar.getTime();
        return dateConvertToString(date, STANDARD_DATE);
    }


    public static void main(String[] args) {
        Date date = stringConvertToDate("2020-01-02 21:45:31", STANDARD_DATE);
        System.out.println(date);
        String dateString = dateConvertToString(new Date(), LONG_DATE);
        System.out.println(dateString);

        String dateTime = timeZoneTransfer("UTC-2:00", "UTC+8:00", "2020-01-02 21:45:31");
        System.out.println(dateTime);

        System.out.println(getFluterDateTime());
        System.out.println(indexOfDateTime("2020-06-13 10:48:21"));

        System.out.println(getMondayDateTime("2020-06-16 10:48:21"));

    }
}
