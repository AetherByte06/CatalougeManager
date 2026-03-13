import java.util.Scanner;
void main() throws InterruptedException {

    ArrayList<Equipment> equipmentList = new ArrayList<>();
    ArrayList<Member> memberList = new ArrayList<>();
    //    Equipment e = new Equipment("test", "test", "23/12/20", "test", false);
    //    Member m = new Member("test", "1234", "01226750890");
    //    BorrowedEquipment be = new BorrowedEquipment(e, "21/12/20", "4/4/21", "5/5/21");


    System.out.println("Welcome to the Catalogue Manager!");
    Thread.sleep(1000);
    System.out.println("What would you like to do?");
    Scanner sc = new Scanner(System.in);
    System.out.println("1. Add new equipment \n2. Add new member \n3. Checkout equipment \n4. Calculate overdraft \n5. Exit");
    int option = Integer.parseInt(sc.nextLine());
    if (option > 5 || option < 1){
        System.out.println("Not an option. Please try again.");
    }
    else{
        switch (option){
            case 1:
                addEquipment(equipmentList);
            case 2:
                //addMember(memberList);
            default:
                System.out.println("balls");
        }
    }


    System.out.println(option);











    // test code
    //    System.out.println(e.getDateOfAcquisition());
    //    System.out.println(e.getAvailability());
    //    System.out.println(e.getItemName());
    //    System.out.println(e.getManufacturer());
    //    System.out.println(e.getType());

    //    System.out.println(m.getContactNumber());
    //    System.out.println(m.getMemberID());
    //    System.out.println(m.getName());

    //    System.out.println(be.getEquipment());
    //    System.out.println(be.getDateBorrowed());
    //    System.out.println(be.getDateReturned());
    //    System.out.println(be.getDueDate());

}
public void addEquipment(ArrayList<Equipment> equipment){
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter the name of the equipment: ");
    String name = scanner.nextLine();
    System.out.print("Enter the manufacturer of the equipment: ");
    String manufacturer = scanner.nextLine();
    System.out.print("Enter the date that the equipment was acquired on: ");
    String dateOfAcquisition = scanner.nextLine();
    System.out.print("Enter the type of the equipment: ");
    String type = scanner.nextLine();
    System.out.print("Is the equipment available for use? (true/false): ");
    boolean available = scanner.nextBoolean();

    Equipment e = new Equipment(name, manufacturer, dateOfAcquisition, type, available);
    equipment.add(e);


        }
