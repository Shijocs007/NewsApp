package com.shijo.mvpexample.util;

import java.util.Calendar;

public class Util {

    /**
     * return the current date of the system
     * the format will be YYYY-MM_DD */
    public String getTodayDate() {

        String date = "";
        Calendar c= Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int day = c.get(Calendar.DAY_OF_MONTH);

        if(month<10 && day < 10) {
            date = year +"-0"+month+"-0"+day;
        } else if(month < 10) {
            date = year +"-0"+month+"-"+day;
        } else if(day < 10) {
            date = year +"-"+month+"-0"+day;
        } else {
            date = year +"-"+month+"-"+day;
        }
        return date;
    }
}
