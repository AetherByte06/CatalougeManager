public class Member {
    private String name;
    private String memberID;
    private String contactNumber;


    public Member(String name, String memberID, String contactNumber) {
        this.name = name;
        this.memberID = memberID;
        this.contactNumber = contactNumber;
    }

    public String getMemberID() { return this.memberID; }
    public String getName() { return this.name; }
    public String getContactNumber(){ return this.contactNumber; }
    public void setMemberID(String memberID) { this.memberID = memberID; }
    public void setName(String name) { this.name = name; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }
}
