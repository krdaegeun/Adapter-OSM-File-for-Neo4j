package model.openstreetmap;

import model.Coordinate;

import java.util.ArrayList;

public class Element {

    long id;
    boolean visible;
    short version;
    long changeset;
    String timestamp;
    String user;
    long uid;
    ArrayList<Tag> tags;

    public Element(long id, boolean visible, short version, long changeset, String timestamp, String user, long uid){
        this.id = id;
        this.visible = visible;
        this.version = version;
        this.changeset = changeset;
        this.timestamp = timestamp;
        this.user = user;
        this.uid = uid;
    }

    public long getId(){
        return id;
    }

    public Boolean getVisible() {
        return visible;
    }

    public short getVersion() {
        return version;
    }

    public long getChangeset() {
        return changeset;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getUser() {
        return user;
    }

    public Long getUid() {
        return uid;
    }

    public boolean attachTag(Tag tag){
        if (tags == null){
            tags = new ArrayList<>();
        }

        return tags.add(tag);
    }
    public ArrayList<Tag> getTags() {
        return tags;
    }

}
