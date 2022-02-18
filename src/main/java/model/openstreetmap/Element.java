package model.openstreetmap;

import java.util.ArrayList;

public class Element {

    Long id;
    Boolean visible;
    short version;
    long changeset;
    String timestamp;
    String user;
    Long uid;
    ArrayList<Tag> tags;

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
        return tags.add(tag);
    }
    public ArrayList<Tag> getTags() {
        return tags;
    }
}
