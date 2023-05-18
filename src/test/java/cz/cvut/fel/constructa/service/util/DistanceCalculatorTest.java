package cz.cvut.fel.constructa.service.util;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

class DistanceCalculatorTest {

    @Test
    void testHaversineDistance() {
        assertThat(DistanceCalculator.haversineDistance(0.0, 0.0, 0.0, 0.0)).isEqualTo(0.0, within(0.0001));
    }

    @Test
    void testHaversineDistanceSameLocation() {
        assertThat(DistanceCalculator.haversineDistance(50.0, 14.0, 50.0, 14.0)).isEqualTo(0.0, within(0.0001));
    }

    @Test
    void testHaversineDistanceDifferentLocations() {
        assertThat(DistanceCalculator.haversineDistance(50.0, 14.0, 51.0, 15.0)).isGreaterThan(0.0);
    }

    @Test
    void testHaversineDistanceNegativeCoordinates() {
        assertThat(DistanceCalculator.haversineDistance(-50.0, -14.0, -51.0, -15.0)).isGreaterThan(0.0);
    }

    @Test
    void testHaversineDistanceLargeDistance() {
        double distance = DistanceCalculator.haversineDistance(0.0, 0.0, 90.0, 180.0);
        assertThat(distance).isCloseTo(10007.543, within(0.001));
    }

    @Test
    void testHaversineDistanceReverseCoordinates() {
        double distance1 = DistanceCalculator.haversineDistance(50.0, 14.0, 51.0, 15.0);
        double distance2 = DistanceCalculator.haversineDistance(51.0, 15.0, 50.0, 14.0);
        assertThat(distance1).isEqualTo(distance2, within(0.0001));
    }

}
