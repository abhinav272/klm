package com.abhinav.klmdemoapp.data.response;

import com.abhinav.klmdemoapp.data.Constants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Times {
    @SerializedName("scheduled")
    @Expose
    private String scheduled;
    private Date date;

    public String getScheduled() {
        return scheduled;
    }

    public void setScheduled(String scheduled) {
        this.scheduled = scheduled;
    }

    public Date getDate() {
        if (date != null)
            return date;
        else {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.DATE_PATTERN_API_2, Locale.getDefault());
            try {
                date = simpleDateFormat.parse(scheduled);
                return date;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public String getFormattedDatePublicTransl() {
        Date date = getDate();
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        StringBuilder dString = new StringBuilder();
        dString.append(String.format("%02d", instance.get(Calendar.HOUR_OF_DAY)));
        dString.append(":");
        dString.append(String.format("%02d", instance.get(Calendar.MINUTE)));
        return dString.toString();
    }
}
