package com.example.rodri.irabus2.helpers;

import java.util.concurrent.TimeUnit;

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

    public String convertFromMillisecondsToStringTime(long time) {
        long hours = TimeUnit.MILLISECONDS.toHours(time);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(time) - (hours*60);
        String sTime = "";

        if (hours < 10)
            sTime += "0" + hours + ":";
        else
            sTime += hours + ":";

        if (minutes == 0)
            sTime += "00";
        else
            sTime += minutes;

        return sTime;
    }


}
