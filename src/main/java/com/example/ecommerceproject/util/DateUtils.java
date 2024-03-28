package com.example.ecommerceproject.util;


import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	
	private static ThreadLocal<SimpleDateFormat> localDateFormate = new ThreadLocal<>();

    private static SimpleDateFormat getSimpleDateFormat() {

        SimpleDateFormat dateFormat = localDateFormate.get();
        if (dateFormat == null) {
            dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            localDateFormate.set(dateFormat);
        }
        return dateFormat;
    }

    public static String format(Date date) {
        return  getSimpleDateFormat().format(date);
    }

}
