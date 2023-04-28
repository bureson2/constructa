package cz.cvut.fel.constructa.service.util;

import java.util.Calendar;
import java.util.Date;

/**
 * A utility class for rounding time values to the nearest quarter hour.
 */
public final class RoundTime {
    /**
     * Rounds up a date to the nearest quarter hour.
     *
     * @param date the date
     * @return The number of minutes to add to the input date to round it up to the nearest quarter hour.
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
     * Rounds down a date to the nearest quarter hour.
     *
     * @param date The date to round down.
     * @return The number of minutes to subtract from the input date to round it down to the nearest quarter hour.
     */
    public static int getRoundedDownMinutes(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int minutes = calendar.get(Calendar.MINUTE);

        return minutes % 15;
    }

    /**
     * Rounds a number of minutes up to the nearest quarter hour.
     *
     * @param minutes The number of minutes to round up.
     * @return The rounded-up number of minutes.
     */
    public static int setToQuarterHour(int minutes) {
        int remainder = minutes % 15;
        if(remainder != 0){
            minutes += 15 - remainder;
        }
        return minutes;
    }
}
