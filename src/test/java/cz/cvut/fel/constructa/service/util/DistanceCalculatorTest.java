package cz.cvut.fel.constructa.service.util;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

class DistanceCalculatorTest {

    @Test
    void testHaversineDistance() {
        assertThat(DistanceCalculator.haversineDistance(0.0, 0.0, 0.0, 0.0)).isEqualTo(0.0, within(0.0001));
    }
}
