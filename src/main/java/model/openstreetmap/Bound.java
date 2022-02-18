package model.openstreetmap;

public class Bound {
    private double minLatitude = 500;
    private double minLongitude = 500;
    private double maxLatitude = 500;
    private double maxLongitude = 500;

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