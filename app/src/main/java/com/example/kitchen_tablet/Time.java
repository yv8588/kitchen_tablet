package com.example.kitchen_tablet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Time {
    /**
     * makes a string time into int;
     * @param t time in string.
     * @return the time in int.
     */
    public static long TimetoInt(String t){
        Long time= Long.parseLong(t);
        return time;
    }

    /**
     * makes a int time to a string of the time.
     * @param t time i int.
     * @return the time in string.
     */
    public static String TimeToString(Long t){
        String time=t.toString();
        return time;
    }

    /**
     * reverse a string into date format.
     * @param str the string.
     * @return reversed string.
     */
    public static String reverse(String str){
        String nstr="";
        SimpleDateFormat originalFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            Date date = originalFormat.parse(str);
            nstr=date.toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return nstr;
    }
}
