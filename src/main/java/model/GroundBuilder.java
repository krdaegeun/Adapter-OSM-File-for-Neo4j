package model;

public class GroundBuilder {

    private Long id;
    private Coordinate edgeLeftUp;
    private Coordinate edgeLeftDown;
    private Coordinate edgeRightUp;
    private Coordinate edgeRightDown;

    public GroundBuilder setId(Long id){
        this.id = id;
        return this;
    }
    public GroundBuilder setLeftUp(Coordinate edgeLeftUp){
        this.edgeLeftUp = edgeLeftUp;
        return this;
    }

    public GroundBuilder setLeftDown(Coordinate edgeLeftDown){
        this.edgeLeftDown = edgeLeftDown;
        return this;
    }

    public GroundBuilder setRightUp(Coordinate edgeRightUp){
        this.edgeRightUp = edgeRightUp;
        return this;
    }

    public GroundBuilder setRightDown(Coordinate edgeRightDown){
        this.edgeRightDown = edgeRightDown;
        return this;
    }

    public Ground build(){
        Ground ground = new Ground(id, edgeLeftUp, edgeLeftDown, edgeRightUp, edgeRightDown);
        return ground;
    }
}
