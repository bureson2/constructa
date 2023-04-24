package cz.cvut.fel.constructa.service.util;

import java.util.Calendar;
import java.util.Date;

public final class RoundTime {
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

    public static int getRoundedDownMinutes(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int minutes = calendar.get(Calendar.MINUTE);

        return minutes % 15;
    }

    public static int setToQuarterHour(int minutes) {
        int remainder = minutes % 15;
        if(remainder != 0){
            minutes += 15 - remainder;
        }
        return minutes;
    }


}
