package model;

import java.util.HashMap;
import java.util.Map;

public class Ground {

    private Map<String, Ground> besideGrounds = new HashMap<String, Ground>();
    private Long id;
    private Coordinate edgeLeftUp;
    private Coordinate edgeLeftDown;
    private Coordinate edgeRightUp;
    private Coordinate edgeRightDown;
    private int weight = 1;

    public Ground(long id, Coordinate edgeLeftUp, Coordinate edgeLeftDown, Coordinate edgeRightUp, Coordinate edgeRightDown) {
        this.id = id;
        this.edgeLeftUp = edgeLeftUp;
        this.edgeLeftDown = edgeLeftDown;
        this.edgeRightUp = edgeRightUp;
        this.edgeRightDown = edgeRightDown;
    }

    public Long getId(){
        return this.id;
    }

}
