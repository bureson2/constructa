package cz.cvut.fel.constructa.service.util;

/**
 * A utility class for calculating distances between two geographic locations.
 */
public final class DistanceCalculator {

    /**
     * The radius of the Earth in kilometers.
     */
    private static final int EARTH_RADIUS = 6371; // poloměr Země v kilometrech

    /**
     * Private constructor to prevent instantiation of the class.
     */
    private DistanceCalculator() {
        // Private constructor to prevent instantiation of the class.
    }

    /**
     * Calculates the Haversine distance between two geographic locations given their latitude and longitude.
     *
     * @param lat1 The latitude of the first location.
     * @param lon1 The longitude of the first location.
     * @param lat2 The latitude of the second location.
     * @param lon2 The longitude of the second location.
     * @return The distance between the two locations in meters.
     */
    public static double haversineDistance(double lat1, double lon1, double lat2, double lon2) {
        // Convert degrees to radians
        double lat1Rad = Math.toRadians(lat1);
        double lon1Rad = Math.toRadians(lon1);
        double lat2Rad = Math.toRadians(lat2);
        double lon2Rad = Math.toRadians(lon2);

        // Difference between the latitudes and longitudes
        double differenceLat = lat2Rad - lat1Rad;
        double differenceLon = lon2Rad - lon1Rad;

        double a = Math.sin(differenceLat / 2) * Math.sin(differenceLat / 2) +
                Math.cos(lat1Rad) * Math.cos(lat2Rad) * Math.sin(differenceLon / 2) * Math.sin(differenceLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c * 1000; // distance in meters
    }
}
