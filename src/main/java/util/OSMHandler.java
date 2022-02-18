package util;

import model.Coordinate;
import model.openstreetmap.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;



public class OSMHandler extends DefaultHandler {

    private Element currentElement;

    public static Bound bound;
    public static ArrayList<Node> nodes = new ArrayList<>(1000);
    public static ArrayList<Way> ways = new ArrayList<>(300);
    public static List<Relation> relations = new ArrayList<>(100);

    // To check if there r nodes or ways which cannot be found inside of way/relation element
    private long notFoundNodeNumberForWay = 0;
    private long notFoundMemberNumberForRelation = 0;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("bounds")){
            bound = buildBound(attributes);
        }
        else if (qName.equals("node")){
            Node node = buildNode(attributes);
            nodes.add(node);
            setCurrentElement(node);
        }
        else if (qName.equals("tag")){
            Tag tag = buildTag(attributes);

            if(nullCheckTag(tag)){
                if(nullCheckCurrentElement()){
                    currentElement.attachTag(tag);
                } else {
                    throw new RuntimeException("Current Element is not determined");
                }
            } else{
                throw new RuntimeException("Tag is not builded");
            }

        }
        else if (qName.equals("way")){
            Way way = buildWay(attributes);
            ways.add(way);
            setCurrentElement(way);
        }
        else if (qName.equals("nd")){

            if (nullCheckCurrentElement() && typeCheckCurrentElement(ElementType.WAY)){

                long ref = SaxHelper.asLong(attributes, "ref");

                if (nullCheckRef(ref)){

                    Node foundNode = findNode(ref);

                    if (nullCheckElement(foundNode)){
                        ((Way) currentElement).addNode(foundNode);
                    } else {
                        notFoundNodeNumberForWay ++;
                    }

                }

            } else {
                throw new RuntimeException("Current Element is not determined as Way");
            }
        }
        else if (qName.equals("relation")){

            Relation relation = buildRelation(attributes);

            if(nullCheckElement(relation)){
                relations.add(relation);
                setCurrentElement(relation);
            }

        }
        else if (qName.equals("member")){

            if (nullCheckCurrentElement() && typeCheckCurrentElement(ElementType.RELATION)){

                Member member = buildMember(attributes);

                if (nullCheckRef(member.getReference())){

                    if(member.getType().equals("Way")){

                        Way foundWay = findWay(member);
                        member.setWay(foundWay);

                        if(nullCheckElement(foundWay)){
                            ((Relation) currentElement).addMember(member);
                        } else {
                            notFoundMemberNumberForRelation++;
                        }

                    }
                }
            }

        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("node") || qName.equals("way") || qName.equals("relation")){

            if(qName.equals("way")){
                if(notFoundNodeNumberForWay != 0){
                    System.out.println("Way : " + currentElement.getId() + " has " + notFoundNodeNumberForWay  + " not found nodes");
                    notFoundNodeNumberForWay = 0;
                }
            }

            if(qName.equals("relation")){
                if(notFoundMemberNumberForRelation != 0){
                    System.out.println("Relation : " + currentElement.getId() + " has " + notFoundMemberNumberForRelation + " not found members");
                    notFoundMemberNumberForRelation = 0;
                }
            }

            deleteCurrentElement();
        }
    }

    private Bound buildBound(Attributes attributes){
        return new BoundBuilder().setMinLatitude(SaxHelper.asDouble(attributes, "minlat"))
                .setMinLongitude(SaxHelper.asDouble(attributes, "minlon"))
                .setMaxLatitude(SaxHelper.asDouble(attributes, "maxlat"))
                .setMaxLongitude(SaxHelper.asDouble(attributes, "maxlon"))
                .build();
    }

    private Element buildElement(Attributes attributes, ElementType type){
        long id = SaxHelper.asLong(attributes, "id");;
        boolean visible = SaxHelper.asBoolean(attributes, "visible");;
        short version = SaxHelper.asShort(attributes, "version");;
        long changeset = SaxHelper.asLong(attributes, "changeset");;
        String timestamp = SaxHelper.asString(attributes, "timestamp");;
        String user = SaxHelper.asString(attributes, "user");;
        long uid = SaxHelper.asLong(attributes, "uid");;

        if (type == ElementType.NODE){
            return new Node(id, visible, version, changeset, timestamp, user, uid);
        } else if (type == ElementType.WAY){
            return new Way(id, visible, version, changeset, timestamp, user, uid);
        } else if (type == ElementType.RELATION){
            return new Relation(id, visible, version, changeset, timestamp, user, uid);
        }

        return null;
    }

    private Node buildNode(Attributes attributes){
        Node node = (Node) buildElement(attributes, ElementType.NODE);
        node.setCoordinate(new Coordinate(SaxHelper.asDouble(attributes, "lat"), SaxHelper.asDouble(attributes, "lon")));
        return node;
    }

    private Way buildWay(Attributes attributes){
        return (Way) buildElement(attributes, ElementType.WAY);
    }

    private Relation buildRelation(Attributes attributes){
        return (Relation) buildElement(attributes, ElementType.RELATION);
    }

    private Tag buildTag(Attributes attributes){
        return new Tag(SaxHelper.asString(attributes, "k"), SaxHelper.asString(attributes, "v"));
    }

    private Member buildMember(Attributes attributes){
        String type = SaxHelper.asString(attributes, "type");
        Long ref = SaxHelper.asLong(attributes, "ref");
        String role = SaxHelper.asString(attributes, "role");
        return new Member(type, ref, role);
    }

    private boolean nullCheckRef(long ref){
        return ref != 0;
    }

    private boolean nullCheckCurrentElement(){
        return currentElement != null;
    }

    private boolean nullCheckElement(Element element){
        return element != null;
    }

    private boolean nullCheckTag(Tag tag){
        return tag != null;
    }

    private boolean typeCheckCurrentElement(ElementType elementType){
        if(elementType == ElementType.NODE){
            return currentElement instanceof Node;
        }else if(elementType == ElementType.WAY){
            return currentElement instanceof Way;
        }else if(elementType == ElementType.RELATION){
            return currentElement instanceof Relation;
        }

        return false;
    }

    private Node findNode(long ref){
        return nodes.stream()
                .filter(node -> node.getId() == ref)
                .findAny()
                .orElse(null);
    }

    private Way findWay(Member member){
        long ref = member.getReference();
        String type = member.getType();

        return ways.stream()
                .filter(way -> way.getId() == member.getReference())
                .findAny()
                .orElse(null);
    }

    private boolean setCurrentElement(Element element){
        if (nullCheckElement(element)){
            currentElement = element;
            return true;
        } else {
            return false;
        }
    }

    private void deleteCurrentElement(){
        currentElement = null;
    }
}







//    private void setOpenStreetMap(){
//
//        OpenStreetMapBuilder openStreetMapBuilder = new OpenStreetMapBuilder();
//        openStreetMap = openStreetMapBuilder
//                .setLeftDown(new Coordinate(minLatitude, minLongitude))
//                .setLeftUp(new Coordinate(maxLatitude, minLongitude))
//                .setRightUp(new Coordinate(maxLatitude, maxLongitude))
//                .setRightDown(new Coordinate(minLatitude, maxLongitude))
//                .build();
//    }
//
//    private void setBlock(){
//        // latitude and longitude have minus system. so always maxLatitude/Longitude > minLatitude/Longitude
//        BLOCK_LENGTH = (maxLatitude - minLatitude) / BLOCK_NUMBER;
//        BLOCK_WIDTH = (maxLongitude - minLongitude) / BLOCK_NUMBER;
//    }
//
//    private Coordinate getCoordinate(int rowNumber, int columnNumber){
//        return new Coordinate(minLatitude + (BLOCK_LENGTH * rowNumber), minLongitude + (BLOCK_WIDTH * columnNumber));
//    }
//
//    private boolean buildGrounds(){
//        Coordinate currentCoordinate, calculatedCoordinate;
//        long id = 0;
//        for (int columnNumber=0 ; columnNumber < BLOCK_NUMBER  ; columnNumber++){
//            int rowNumber = 0;
//            currentCoordinate = getCoordinate(rowNumber, columnNumber);
//            calculatedCoordinate = getCoordinate(rowNumber + 1, columnNumber+1);
//
//            /**
//             * If because of rounding error calLat is over maxLat, then maxLat will be calLat, same in calLon below
//             */
//            if (calculatedCoordinate.getLatitude() > maxLatitude || columnNumber == (BLOCK_NUMBER-1)){
//                calculatedCoordinate = new Coordinate(maxLatitude, calculatedCoordinate.getLongitude());
//                if (columnNumber != (BLOCK_NUMBER-1)){
//                    throw new RuntimeException(); // new Exception
//                }
//
//            }
//
//            for (rowNumber = 0; rowNumber < BLOCK_NUMBER; rowNumber++){
//                /**
//                 * It checks if it's over maxLat or last one in the row
//                 */
//                if(calculatedCoordinate.getLongitude() < maxLongitude && rowNumber!=(BLOCK_NUMBER-1)){
//                    GroundBuilder groundBuilder = new GroundBuilder();
//                    Ground ground = groundBuilder
//                            .setId(id)
//                            .setLeftUp(new Coordinate(calculatedCoordinate.getLatitude(), currentCoordinate.getLongitude()))
//                            .setLeftDown(new Coordinate(currentCoordinate.getLatitude(), currentCoordinate.getLongitude()))
//                            .setRightUp(new Coordinate(calculatedCoordinate.getLatitude(), calculatedCoordinate.getLongitude()))
//                            .setRightDown(new Coordinate(currentCoordinate.getLatitude(), calculatedCoordinate.getLongitude()))
//                            .build();
//
//                    grounds.put(ground.getId(), ground);
//
//                    currentCoordinate.setLongitude(calculatedCoordinate.getLongitude());
//                    calculatedCoordinate.setLongitude(calculatedCoordinate.getLongitude() + BLOCK_WIDTH);
//
//                }
//                else {
//                    calculatedCoordinate.setLongitude(maxLongitude);
//
//                    GroundBuilder groundBuilder = new GroundBuilder();
//                    Ground ground = groundBuilder
//                            .setId(id)
//                            .setLeftUp(new Coordinate(calculatedCoordinate.getLatitude(), currentCoordinate.getLongitude()))
//                            .setLeftDown(new Coordinate(currentCoordinate.getLatitude(), currentCoordinate.getLongitude()))
//                            .setRightUp(new Coordinate(calculatedCoordinate.getLatitude(), calculatedCoordinate.getLongitude()))
//                            .setRightDown(new Coordinate(currentCoordinate.getLatitude(), calculatedCoordinate.getLongitude()))
//                            .build();
//
//                    grounds.put(ground.getId(), ground);
//
//
//                    id++;
//                    if(rowNumber!=(BLOCK_NUMBER-1)){
//                        throw new RuntimeException();
//                    }
//
//                    break;
//                }
//
//                id++;
//
//            }
//
//
//            double divLat = (maxLat-minLat) / denominator;
//            int c = BLOCK_NUMBER;
//        }
//        return true;
//    }

