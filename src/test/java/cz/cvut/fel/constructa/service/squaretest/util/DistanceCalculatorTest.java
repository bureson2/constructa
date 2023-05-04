package cz.cvut.fel.constructa.service.squaretest.util;

import cz.cvut.fel.constructa.service.util.DistanceCalculator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

class DistanceCalculatorTest {

    @Test
    void testHaversineDistance() {
        Assertions.assertThat(DistanceCalculator.haversineDistance(0.0, 0.0, 0.0, 0.0)).isEqualTo(0.0, within(0.0001));
    }
}
