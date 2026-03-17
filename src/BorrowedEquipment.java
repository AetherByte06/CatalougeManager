public class BorrowedEquipment {
    private Equipment e;
    private String dateBorrowed;
    private String dueDate;
    private String dateReturned;
    private Member memberBorrowed;


    public BorrowedEquipment(Equipment e, String dateBorrowed, String dueDate, String dateReturned, Member memberBorrowed) {
        this.e = e;
        this.dateBorrowed = dateBorrowed;
        this.dueDate = dueDate;
        this.dateReturned = dateReturned;
        this.memberBorrowed = memberBorrowed;
    }

    public String getEquipment(){ return this.e.getInformation(); }
    public String getDateBorrowed() { return this.dateBorrowed; }
    public String getDueDate() { return this.dueDate; }
    public String getDateReturned() { return this.dateReturned; }
    public Member getMemberBorrowed() { return this.memberBorrowed; }

}
