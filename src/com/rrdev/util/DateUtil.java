package com.rrdev.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

    public static String DATE_PATTERN = "HH:mm:ss.SSS";

    public static String formatOnPattern(Date date, String pattern){
        return new SimpleDateFormat(pattern, Locale.getDefault()).format(date);
    }
}
