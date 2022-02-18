package model;

import model.Coordinate;

public class OpenStreetMap {

    private Coordinate edgeLeftUp;
    private Coordinate edgeLeftDown;
    private Coordinate edgeRightUp;
    private Coordinate edgeRightDown;

    OpenStreetMap(Coordinate edgeLeftUp, Coordinate edgeLeftDown, Coordinate edgeRightUp, Coordinate edgeRightDown){
        this.edgeLeftUp = edgeLeftUp;
        this.edgeLeftDown = edgeLeftDown;
        this.edgeRightUp = edgeRightUp;
        this.edgeRightDown = edgeRightDown;
    }

    public Coordinate getEdgeLeftUp() {
        return edgeLeftUp;
    }

    public Coordinate getEdgeLeftDown() {
        return edgeLeftDown;
    }

    public Coordinate getEdgeRightUp() {
        return edgeRightUp;
    }

    public Coordinate getEdgeRightDown() {
        return edgeRightDown;
    }
}
