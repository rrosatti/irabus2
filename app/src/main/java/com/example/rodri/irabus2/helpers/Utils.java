package com.example.rodri.irabus2.helpers;

/**
 * Created by rrosatti on 9/9/17.
    public Utils() {}

 */

public class Utils {

    public Utils() {}

    public long convertFromTimeToMilliseconds(String time) {
        long totalTime = 0;
        String[] hourMinute = time.split(":");
        totalTime += Long.parseLong(hourMinute[0]) * 3600000;
        totalTime += Long.parseLong(hourMinute[1]) * 60000;
        return totalTime;
    }


}
