package com.quankm.healthdiary.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Infernocorez on 6/8/2016.
 */

public class DateTimeUtil {
    public static long parseDateStringToMillisecs(String dateStr){
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date date = format.parse(dateStr);
            return date.getTime();
        } catch (ParseException e) {
            return -1;
        }
    }
}
