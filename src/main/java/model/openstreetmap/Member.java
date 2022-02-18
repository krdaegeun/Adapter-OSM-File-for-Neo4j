package model.openstreetmap;

public class Member {
    private String type;
    private Long reference;
    private String role;

    public Member(String type, Long reference, String role){
        this.type = type;
        this.reference = reference;
        this.role = role;
    }
}
