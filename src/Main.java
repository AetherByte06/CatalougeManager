import java.util.Scanner;

/* ALL CODE (if not all, most) WILL THROW InterruptedException(s), THIS IS DUE TO THE FACT I AM USING
THE Thread.sleep() METHOD, WHICH ALLOWS FOR BREAKS IN THE PROGRAM.
 */

void main() throws InterruptedException {

    ArrayList<Equipment> equipmentList = new ArrayList<>();
    ArrayList<Member> memberList = new ArrayList<>();
    ArrayList<BorrowedEquipment> borrowedList = new ArrayList<>();
    ArrayList<String> memberNames = new ArrayList<>();
    boolean end = false;


    System.out.println("Welcome to the Catalogue Manager!");
    Thread.sleep(2000);


    do{
        System.out.println("What would you like to do?");
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Add new equipment \n2. Add new member \n3. Checkout equipment \n4. Calculate overdraft \n5. Exit");
        int option = Integer.parseInt(sc.nextLine());
        if (option > 5 || option < 1){
            System.out.println("Not an option. Please try again.");
        }
        else {
            switch (option) {
                case 1:
                    addEquipment(equipmentList);
                    break;
                case 2:
                    addMember(memberList);
                    break;
                case 3:
                    System.out.println("Which member is checking out equipment?:");
                    // add member names to an arraylist, index of name will be the same as the index of the member chosen.
                    for (Member m  :memberList){
                        memberNames.add(m.getName());
                        System.out.println(m.getName());
                    }
                    // searching the member name list for the index of the member name.
                    String memberName = sc.nextLine();
                    int memberIndex = memberNames.indexOf(memberName);
                    // finding the member that is trying to borrow.
                    Member member = memberList.get(memberIndex);

                    checkoutEquipment(borrowedList, equipmentList, member);
                    break;
                case 4:
                    //calculateOverdraftFee(borrowedList);
                    //break;
                case 5:
                    System.out.println("Thanks for using the program!");
                    end = true;
                    break;
                default:
                    System.out.println("balls");
                    break;
            }
        }
    } while (!end);
}

/**
 * Used to add equipment to the program.
 * Gets added to the equipmentList.
 * @param equipment
 * @throws InterruptedException
 */

public void addEquipment(ArrayList<Equipment> equipment) throws InterruptedException {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter the name of the equipment: ");
    String name = scanner.nextLine();
    System.out.print("Enter the manufacturer of the equipment: ");
    String manufacturer = scanner.nextLine();

    // TODO: Find possible error checking method for this.
    System.out.print("Enter the date that the equipment was acquired on (dd/mm/yyyy): ");
    String dateOfAcquisition = scanner.nextLine();

    System.out.print("Enter the type of the equipment: ");
    String type = scanner.nextLine();
    System.out.print("Is the equipment available for use? (true/false): ");
    boolean available = scanner.nextBoolean();

    Equipment e = new Equipment(name, manufacturer, dateOfAcquisition, type, available);
    equipment.add(e);
    System.out.println("Equipment added.");
    Thread.sleep(1000);
}

/**
 * Adds a member to the memberList.
 * @param members
 * @throws InterruptedException
 */
public void addMember(ArrayList<Member> members) throws InterruptedException {
    boolean end = false;
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter the name of the member: ");
    String name = scanner.nextLine();

    String memberID = null;
    while (end == false) {
        System.out.print("Enter the ID of the member (Length of 4, combination of letters + numbers): ");
        memberID = scanner.nextLine();
        if (memberID.length() != 4) {
            System.out.println("MemberID needs to be of length 4, please try again.");
        } else {
            end = true;
        }
    }

    System.out.print("Enter the member's contact number: ");
    String contactNumber = scanner.nextLine();

    Member m = new Member(name, memberID, contactNumber);
    members.add(m);
    System.out.println("Member added.");
    Thread.sleep(1000);
}

/**
 * Takes equipment that a member wants to borrow, assigns them a due date, return date and a borrowed date.
 * Actually attaches the member that has borrowed it.
 * @param borrowedEquipments
 * @param equipment
 * @param borrower
 * @throws InterruptedException
 */
public void checkoutEquipment(ArrayList<BorrowedEquipment> borrowedEquipments, ArrayList<Equipment> equipment, Member borrower) throws InterruptedException{
    if (!equipment.isEmpty()){
        System.out.println("Equipment List: ");
        for (Equipment e : equipment){
            System.out.println(e.getItemName());
        }
        Scanner sc = new Scanner(System.in);
        boolean valid = false;
        while (!valid) {
            System.out.print("Enter the name of the equipment you wish to borrow (type exit to stop): ");
            // using object.equals to prevent null errors.
            if (Objects.equals(sc.nextLine(), "exit")) {
                break;
            }
            String itemToBorrow = sc.nextLine();
            int count = 0;
            for (Equipment e : equipment) {
                if (count < equipment.size()) {
                    if (!Objects.equals(e.getItemName(), itemToBorrow)) {
                        count++;
                    } else {
                        if (e.getAvailability()) {
                            // Filled with test data for now, make sure to allocate this dynamically using something like DateTime.
                            // dk how that will work but it might function
                            borrowedEquipments.add(new BorrowedEquipment(e, "2/2/23", "2/3/23", "2/3/23", borrower));
                            e.setAvailability(false);
                            valid = true;
                        } else {
                            System.out.println("Equipment is not available to borrow.");
                        }
                    }
                } else {
                    System.out.println("Equipment does not exist, please add the equipment before checking it out.");
                    Thread.sleep(1000);
                    break;
                }
            }
        }
    } else{
        System.out.println("There is no equipment in the list, please add an equipment.");
        Thread.sleep(1000);
    }
}