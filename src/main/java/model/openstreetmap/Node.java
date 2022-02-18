package model.openstreetmap;

import model.Coordinate;

import java.util.ArrayList;

public class Node extends Element {
    private Coordinate coordinate;

    public Node(long id, boolean visible, short version, long changeset, String timestamp, String user, long uid){
        super(id, visible, version, changeset,timestamp,user,uid);
    }

    public void setCoordinate(Coordinate coordinate){
        this.coordinate = coordinate;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }
}
