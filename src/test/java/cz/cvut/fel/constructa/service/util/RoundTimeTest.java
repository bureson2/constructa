package cz.cvut.fel.constructa.service.util;

import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.assertThat;

class RoundTimeTest {

    @Test
    void testGetRoundedUpMinutes() {
        assertThat(RoundTime.getRoundedUpMinutes(new GregorianCalendar(2020, Calendar.JANUARY, 1, 12, 7).getTime()))
                .isEqualTo(8);
        assertThat(RoundTime.getRoundedUpMinutes(new GregorianCalendar(2020, Calendar.JANUARY, 1, 12, 22).getTime()))
                .isEqualTo(8);
        assertThat(RoundTime.getRoundedUpMinutes(new GregorianCalendar(2020, Calendar.JANUARY, 1, 12, 37).getTime()))
                .isEqualTo(8);
        assertThat(RoundTime.getRoundedUpMinutes(new GregorianCalendar(2020, Calendar.JANUARY, 1, 12, 52).getTime()))
                .isEqualTo(8);
        assertThat(RoundTime.getRoundedUpMinutes(new GregorianCalendar(2020, Calendar.JANUARY, 1, 13, 8).getTime()))
                .isEqualTo(7);
    }

    @Test
    void testGetRoundedDownMinutes() {
        assertThat(
                RoundTime.getRoundedDownMinutes(new GregorianCalendar(2020, Calendar.JANUARY, 1, 12, 7).getTime()))
                .isEqualTo(7);
        assertThat(
                RoundTime.getRoundedDownMinutes(new GregorianCalendar(2020, Calendar.JANUARY, 1, 12, 22).getTime()))
                .isEqualTo(7);
        assertThat(
                RoundTime.getRoundedDownMinutes(new GregorianCalendar(2020, Calendar.JANUARY, 1, 12, 37).getTime()))
                .isEqualTo(7);
        assertThat(
                RoundTime.getRoundedDownMinutes(new GregorianCalendar(2020, Calendar.JANUARY, 1, 12, 52).getTime()))
                .isEqualTo(7);
        assertThat(
                RoundTime.getRoundedDownMinutes(new GregorianCalendar(2020, Calendar.JANUARY, 1, 13, 8).getTime()))
                .isEqualTo(8);
    }

    @Test
    void testSetToQuarterHour() {
        assertThat(RoundTime.setToQuarterHour(7)).isEqualTo(15);
        assertThat(RoundTime.setToQuarterHour(12)).isEqualTo(15);
        assertThat(RoundTime.setToQuarterHour(22)).isEqualTo(30);
        assertThat(RoundTime.setToQuarterHour(37)).isEqualTo(45);
        assertThat(RoundTime.setToQuarterHour(52)).isEqualTo(60);
    }
}
