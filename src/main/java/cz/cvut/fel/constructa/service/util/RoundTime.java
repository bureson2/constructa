package cz.cvut.fel.constructa.service.util;

import java.util.Calendar;
import java.util.Date;

/**
 * The type Round time.
 */
public final class RoundTime {
    /**
     * Gets rounded up minutes.
     *
     * @param date the date
     * @return the rounded up minutes
     */
    public static int getRoundedUpMinutes(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int minutes = calendar.get(Calendar.MINUTE);
        int remainder = minutes % 15;

        if (remainder != 0) {
            return 15 - remainder;
        } else {
            return 0;
        }
    }

    /**
     * Gets rounded down minutes.
     *
     * @param date the date
     * @return the rounded down minutes
     */
    public static int getRoundedDownMinutes(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int minutes = calendar.get(Calendar.MINUTE);

        return minutes % 15;
    }

    /**
     * Sets to quarter hour.
     *
     * @param minutes the minutes
     * @return the to quarter hour
     */
    public static int setToQuarterHour(int minutes) {
        int remainder = minutes % 15;
        if(remainder != 0){
            minutes += 15 - remainder;
        }
        return minutes;
    }


}
