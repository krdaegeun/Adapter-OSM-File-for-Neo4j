package model.openstreetmap;

import model.Coordinate;

public class Node extends Element {
    private Coordinate coordinate;

    public void setCoordinate(Coordinate coordinate){
        this.coordinate = coordinate;
    }
    public Coordinate getCoordinate() {
        return coordinate;
    }
}
