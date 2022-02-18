package model.openstreetmap;

import java.util.ArrayList;

public class Relation extends Element{
    ArrayList<Member> members = new ArrayList<Member>(50);

    public Relation(long id, boolean visible, short version, long changeset, String timestamp, String user, long uid){
        super(id, visible, version, changeset,timestamp,user,uid);
    }

    public boolean addMember(Member member){
        if (member != null){
            return members.add(member);
        }else{
            return false;
        }
    }
    public ArrayList<Member> getMembers() {
        return members;
    }
}
