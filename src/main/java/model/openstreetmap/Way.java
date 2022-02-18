package model.openstreetmap;

import java.util.LinkedList;


public class Way extends Element {

    private LinkedList<Node> nodes = new LinkedList<Node>();

    public Way(long id, boolean visible, short version, long changeset, String timestamp, String user, long uid){
        super(id, visible, version, changeset,timestamp,user,uid);
    }

    public boolean addNode(Node node){
        if (node != null){
            return nodes.add(node);
        }
        else{
            return false;
        }

    }

}
