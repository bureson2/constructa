package cz.cvut.fel.constructa.service.util;

import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.assertThat;

class RoundTimeTest {

    @Test
    void testGetRoundedUpMinutes() {
        assertThat(RoundTime.getRoundedUpMinutes(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime()))
                .isEqualTo(0);
    }

    @Test
    void testGetRoundedDownMinutes() {
        assertThat(
                RoundTime.getRoundedDownMinutes(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime()))
                .isEqualTo(0);
    }

    @Test
    void testSetToQuarterHour() {
        assertThat(RoundTime.setToQuarterHour(0)).isEqualTo(0);
    }
}
