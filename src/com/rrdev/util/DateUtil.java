package com.rrdev.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

    public static final String DATE_PATTERN = "HH:mm:ss.SSS";

    public static String formatOnPattern(Date date, String pattern){
        return new SimpleDateFormat(pattern, Locale.getDefault()).format(date);
    }

    public static String formatOnPattern(Date date){
        return new SimpleDateFormat(DATE_PATTERN, Locale.getDefault()).format(date);
    }

    public static void skippSecond(int seconds){
        long time = System.currentTimeMillis();
        while (System.currentTimeMillis() < (time + seconds * 1000)) {}
    }

    public static void skippSecond(){
        long time = System.currentTimeMillis();
        while (System.currentTimeMillis() < (time + 1000)) {}
    }
}
