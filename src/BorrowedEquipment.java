public class BorrowedEquipment {
    private Equipment e;
    private String dateBorrowed;
    private String dueDate;
    private String dateReturned;


    public BorrowedEquipment(Equipment e, String dateBorrowed, String dueDate, String dateReturned) {
        this.e = e;
        this.dateBorrowed = dateBorrowed;
        this.dueDate = dueDate;
        this.dateReturned = dateReturned;
    }

    public String getEquipment(){ return e.getInformation(); }
    public String getDateBorrowed() { return dateBorrowed; }
    public String getDueDate() { return dueDate; }
    public String getDateReturned() { return dateReturned; }

}
