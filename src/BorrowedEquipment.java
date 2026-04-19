import java.time.LocalDate;

public class BorrowedEquipment {
    private Equipment e;
    private LocalDate dateBorrowed;
    private LocalDate dueDate;
    private LocalDate dateReturned;
    private Member memberBorrowed;


    public BorrowedEquipment(Equipment e, LocalDate dateBorrowed, LocalDate dueDate, LocalDate dateReturned, Member memberBorrowed) {
        this.e = e;
        this.dateBorrowed = dateBorrowed;
        this.dueDate = dueDate;
        this.dateReturned = dateReturned;
        this.memberBorrowed = memberBorrowed;
    }

    public String getEquipmentInfo(){ return this.e.getInformation(); }
    public String getName() { return this.e.getItemName();}
    public Equipment getEquipment() { return this.e; }
    public LocalDate getDateBorrowed() { return this.dateBorrowed; }
    public LocalDate getDueDate() { return this.dueDate; }
    public LocalDate getDateReturned() { return this.dateReturned; }
    public Member getMemberBorrowed() { return this.memberBorrowed; }
    public void setDateReturned(LocalDate dateReturned){
        this.dateReturned = dateReturned;
    }

}
