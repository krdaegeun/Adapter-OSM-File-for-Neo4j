package model.openstreetmap;

import java.util.ArrayList;

public class Relation extends Element{
    ArrayList<Member> members = new ArrayList<Member>();

    public boolean addMember(Member member){
        if (member != null)
            return members.add(member);
        else
            return false;
    }
    public ArrayList<Member> getMembers() {
        return members;
    }
}
