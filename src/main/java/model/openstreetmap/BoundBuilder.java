package model.openstreetmap;

public class BoundBuilder {
    private final double DEFAULT_VALUE = 500;
    private double minLatitude = DEFAULT_VALUE;
    private double minLongitude = DEFAULT_VALUE;
    private double maxLatitude = DEFAULT_VALUE;
    private double maxLongitude = DEFAULT_VALUE;

    public BoundBuilder setMinLatitude(double latitude){
        this.minLatitude = latitude;
        return this;
    }

    public BoundBuilder setMinLongitude(double longitude){
        this.minLongitude = longitude;
        return this;
    }

    public BoundBuilder setMaxLatitude(double latitude){
        this.maxLatitude = latitude;
        return this;
    }

    public BoundBuilder setMaxLongitude(double longitude){
        this.maxLongitude = longitude;
        return this;
    }

    public Bound build(){
        if (minLatitude == DEFAULT_VALUE || minLongitude == DEFAULT_VALUE || maxLatitude == DEFAULT_VALUE || maxLongitude == DEFAULT_VALUE)
            throw new RuntimeException(String.format(
                    "minLatitude : %f, minLongitude : %f, maxLatitude : %f, maxLongitude : %f, DEFAULT_VALUE : %f /n All 4 elements should have get value",
                    minLatitude, minLongitude, maxLatitude, maxLongitude, DEFAULT_VALUE
            )); // BuilderException
        else {
            Bound bound = new Bound(minLatitude, minLongitude, maxLatitude, maxLongitude);
            return bound;
        }

    }
}