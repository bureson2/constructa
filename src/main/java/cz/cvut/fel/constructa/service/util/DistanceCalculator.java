package cz.cvut.fel.constructa.service.util;

public final class DistanceCalculator {

    private static final int EARTH_RADIUS = 6371; // poloměr Země v kilometrech

    private DistanceCalculator() {
        // Soukromý konstruktor, aby se zabránilo vytváření instancí třídy
    }

    public static double haversineDistance(double lat1, double lon1, double lat2, double lon2) {
        // Převod stupňů na radiány
        double lat1Rad = Math.toRadians(lat1);
        double lon1Rad = Math.toRadians(lon1);
        double lat2Rad = Math.toRadians(lat2);
        double lon2Rad = Math.toRadians(lon2);

        // Rozdíl mezi zeměpisnou šířkou a délkou
        double differenceLat = lat2Rad - lat1Rad;
        double differenceLon = lon2Rad - lon1Rad;

        double a = Math.sin(differenceLat / 2) * Math.sin(differenceLat / 2) +
                Math.cos(lat1Rad) * Math.cos(lat2Rad) * Math.sin(differenceLon / 2) * Math.sin(differenceLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c * 1000; // vzdálenost v metrech
    }
}
