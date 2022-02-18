package model;

public class OpenStreetMapBuilder {
    private Coordinate edgeLeftUp;
    private Coordinate edgeLeftDown;
    private Coordinate edgeRightUp;
    private Coordinate edgeRightDown;

    public OpenStreetMapBuilder setLeftUp(Coordinate edgeLeftUp){
        this.edgeLeftUp = edgeLeftUp;
        return this;
    }

    public OpenStreetMapBuilder setLeftDown(Coordinate edgeLeftDown){
        this.edgeLeftDown = edgeLeftDown;
        return this;
    }

    public OpenStreetMapBuilder setRightUp(Coordinate edgeRightUp){
        this.edgeRightUp = edgeRightUp;
        return this;
    }

    public OpenStreetMapBuilder setRightDown(Coordinate edgeRightDown){
        this.edgeRightDown = edgeRightDown;
        return this;
    }

    public OpenStreetMap build(){
        if (edgeLeftUp == null || edgeLeftDown == null || edgeRightUp == null || edgeRightDown == null)
            throw new RuntimeException(); // OSMBuilderException
        else {
            OpenStreetMap openStreetMap = new OpenStreetMap(edgeLeftUp, edgeLeftDown, edgeRightUp, edgeRightDown);
            return openStreetMap;
        }

    }
}
