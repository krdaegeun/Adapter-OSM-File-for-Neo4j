package model.openstreetmap;

public class Member {
    private String type;
    private Long reference;
    private String role;
    private Way way;

    public Member(String type, Long reference, String role){
        this.type = type;
        this.reference = reference;
        this.role = role;
    }

    public void setWay(Way way) {
        this.way = way;
    }

    public Long getReference() {
        return reference;
    }

    public String getType() {
        return type;
    }
}
