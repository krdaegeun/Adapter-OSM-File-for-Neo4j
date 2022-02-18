package model.openstreetmap;

import java.util.LinkedList;


public class Way extends Element {

    private LinkedList<Node> nodes = new LinkedList<Node>();

    public boolean addNode(Node node){
        if (node != null)
            return nodes.add(node);
        else
            return false;
    }

}
