package com.abhinav.klmdemoapp.utils.loadindicator;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtil {

    public static String getDifferenceInDates(Date d1, Date d2) {
        long diff = d2.getTime() - d1.getTime();

        long hours = TimeUnit.MILLISECONDS.toHours(diff);
        long minutesMillis = diff - (hours * 60 * 60 * 1000);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(minutesMillis);
        return String.format("%sh %sm", String.valueOf(hours), String.valueOf(minutes));
    }

    public static String getFormattedTimeStatus(Date date) {
        Calendar instance = Calendar.getInstance();
        long l = date.getTime() - instance.getTime().getTime();

        if (l > 0) {
            long hours = TimeUnit.MILLISECONDS.toHours(l);
            if (hours > 24) {
                long days = TimeUnit.MILLISECONDS.toDays(l);
                long diff = l - (days * 24 * 60 * 60 * 1000);
                long hours1 = TimeUnit.MILLISECONDS.toHours(diff);
                return String.format("%sdays %shours to Departure", String.valueOf(days), String.valueOf(hours1));
            } else if (hours > 0) {
                long minutesMillis = l - (hours * 60 * 60 * 1000);
                long minutes = TimeUnit.MILLISECONDS.toMinutes(minutesMillis);
                return String.format("%shours %smins to Departure", String.valueOf(hours), String.valueOf(minutes));
            } else {
                long minutes = TimeUnit.MILLISECONDS.toMinutes(l);
                return String.format("%smins to Departure", String.valueOf(hours), String.valueOf(minutes));
            }

        }
        return null;
    }
}
