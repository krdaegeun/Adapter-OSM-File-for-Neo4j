package model.openstreetmap;

public class Bound {
    private final double DEFAULT_VALUE = 500;
    private double minLatitude = DEFAULT_VALUE;
    private double minLongitude = DEFAULT_VALUE;
    private double maxLatitude = DEFAULT_VALUE;
    private double maxLongitude = DEFAULT_VALUE;

    Bound(double minLatitude, double minLongitude, double maxLatitude, double maxLongitude){
        this.minLatitude = minLatitude;
        this.minLongitude = minLongitude;
        this.maxLatitude = maxLatitude;
        this.maxLongitude = maxLongitude;
    }

    public double getMinLatitude() {
        return minLatitude;
    }

    public double getMinLongitude() {
        return minLongitude;
    }

    public double getMaxLatitude() {
        return maxLatitude;
    }

    public double getMaxLongitude() {
        return maxLongitude;
    }

    @Override
    public String toString() {
        return "Bound{" +
                "minLatitude=" + minLatitude +
                ", minLongitude=" + minLongitude +
                ", maxLatitude=" + maxLatitude +
                ", maxLongitude=" + maxLongitude +
                '}';
    }
}