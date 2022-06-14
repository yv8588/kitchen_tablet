package com.example.kitchen_tablet;

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
     * reverse a string.
     * @param str the string.
     * @return reversed string.
     */
    public static String reverse(String str){
        char ch;
        String nstr="";
        for (int i=0; i<str.length(); i++)
        {
            ch= str.charAt(i); //extracts each character
            nstr= ch+nstr; //adds each character in front of the existing string
        }
        return nstr;
    }
}
