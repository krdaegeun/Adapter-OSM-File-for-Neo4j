package model.openstreetmap;

public class BoundBuilder {
    private double minLatitude = 500;
    private double minLongitude = 500;
    private double maxLatitude = 500;
    private double maxLongitude = 500;

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
        if (minLatitude == 500 || minLongitude == 500 || maxLatitude == 500 || maxLongitude == 500)
            throw new RuntimeException(); // BuilderException
        else {
            Bound bound = new Bound(minLatitude, minLongitude, maxLatitude, maxLongitude);
            return bound;
        }

    }
}