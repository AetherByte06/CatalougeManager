import java.util.Scanner;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.time.temporal.ChronoUnit;

/* ALL CODE (if not all, most) WILL THROW InterruptedException(s), THIS IS DUE TO THE FACT I AM USING
THE Thread.sleep() METHOD, WHICH ALLOWS FOR BREAKS IN THE PROGRAM.
 */

void main() throws InterruptedException {

    ArrayList<Equipment> equipmentList = new ArrayList<>();
    ArrayList<Member> memberList = new ArrayList<>();
    ArrayList<BorrowedEquipment> borrowedList = new ArrayList<>();
    ArrayList<String> memberNames = new ArrayList<>();
    ArrayList<BorrowedEquipment> previousBorrowedList = new ArrayList<>();
    boolean end = false;


    System.out.println("Welcome to the Catalogue Manager!");
    Thread.sleep(2000);


    do{
        // Main menu.
        System.out.println("What would you like to do?");
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Add new equipment \n2. Add new member \n3. Edit member \n4. Delete member\n5. Checkout equipment \n6. Return equipment \n7. Calculate previous overdraft\n8. Exit");
        int option = Integer.parseInt(sc.nextLine());
        if (option > 8 || option < 1){
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
                    if (memberList.isEmpty()) {
                        System.out.println("There are no members to edit, please add a member before trying to edit.");
                        Thread.sleep(1000);
                        break;
                    }
                    System.out.println("Which member do you want to edit?: ");
                    for (Member member : memberList) {
                        System.out.println(member.getName());
                    }
                    Scanner s = new Scanner(System.in);
                    String name = s.nextLine();

                    Member selectedMember = null;
                    for (Member member : memberList) {
                        if (Objects.equals(member.getName(), name)) {
                            selectedMember = member;
                            break;
                        }
                    }
                    if (selectedMember == null) {
                        System.out.println("Member not found, please try again.");
                        Thread.sleep(1000);
                        break;
                    }

                    System.out.println("What do you want to edit about this member? \n1. Name \n2. ID \n3. Contact Number");
                    int choice = Integer.parseInt(sc.nextLine());
                    switch (choice) {
                        case 1:
                            System.out.println("Enter new name: ");
                            String newName = s.nextLine();
                            selectedMember.setName(newName);
                            break;
                        case 2:
                            System.out.println("Enter new ID: ");
                            String newID = s.nextLine();
                            selectedMember.setMemberID(newID);
                            break;
                        case 3:
                            System.out.println("Enter new contact number: ");
                            String newContactNumber = s.nextLine();
                            selectedMember.setContactNumber(newContactNumber);
                            break;
                        default:
                            break;
                    }
                case 4:
                    System.out.println("Enter members name to delete: ");
                    Scanner scanner = new Scanner(System.in);
                    String nameToDelete = scanner.nextLine();

                    Member memberToDelete = null;
                    for (Member member : memberList) {
                        if (Objects.equals(member.getName(), nameToDelete)) {
                            memberToDelete = member;
                            break;
                        }
                    }
                    if (memberToDelete == null) {
                        System.out.println("Member not found, please try again.");
                        Thread.sleep(1000);
                        break;
                    }

                    memberList.remove(memberToDelete);
                    System.out.println("Member deleted.");
                    Thread.sleep(1000);
                    break;
                case 5:
                    System.out.println("Which member is checking out equipment?:");
                    // add member names to an arraylist, index of name will be the same as the index of the member chosen.
                    for (Member m : memberList){
                        memberNames.add(m.getName());
                        System.out.println(m.getName());
                    }
                    // searching the member name list for the index of the member name.
                    String memberName = sc.nextLine();

                    if (!memberNames.contains(memberName)){
                        System.out.println("Member does not exist, have you added this member? Please try again.");
                        Thread.sleep(2000);
                        break;
                }

                    int memberIndex = memberNames.indexOf(memberName);
                    // finding the member that is trying to borrow.
                    Member member = memberList.get(memberIndex);

                    checkoutEquipment(borrowedList, equipmentList, member);
                    break;
                case 6:
                    returnEquipment(borrowedList, equipmentList, previousBorrowedList);
                    break;
                case 7:
                    System.out.println("Which instance do you want to calculate overdraft for?:");
                    Scanner sca = new Scanner(System.in);
                    String instance = sca.nextLine();
                    for (BorrowedEquipment b : previousBorrowedList){
                        if (Objects.equals(b.getEquipment().getItemName(), instance)){
                            calculateOverdraft(previousBorrowedList, b.getEquipment());
                        }
                        else{
                            System.out.println("Instance does not exist, please try again.");
                            Thread.sleep(1000);
                            break;
                        }
                    }
                    break;
                case 8:
                    System.out.println("Thanks for using the program!");
                    end = true;
                    break;
                default:
                    break;
            }
        }
    } while (!end);
}

/**
 * Used to add equipment to the program.
 * Gets added to the equipmentList.
 * @param equipment Equipment object.
 * @throws InterruptedException If the thread gets interrupted, this will get thrown.
 */

public void addEquipment(ArrayList<Equipment> equipment) throws InterruptedException {
    LocalDate date = LocalDate.now();
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter the name of the equipment: ");
    String name = scanner.nextLine();
    System.out.print("Enter the manufacturer of the equipment: ");
    String manufacturer = scanner.nextLine();

    System.out.print("Enter the type of the equipment: ");
    String type = scanner.nextLine();
    System.out.print("Is the equipment available for use? (true/false): ");
    boolean available = scanner.nextBoolean();

    Equipment e = new Equipment(name, manufacturer, date, type, available);
    equipment.add(e);
    System.out.println("Equipment added.");
    Thread.sleep(1000);
}

/**
 * Adds a member to the memberList.
 * @param members List of members.
 * @throws InterruptedException If the thread gets interrupted, this will get thrown.
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
 * Also attaches the member that has borrowed it.
 * @param borrowedEquipments List of borrowed equipments.
 * @param equipment List of equipment that can be borrowed.
 * @param borrower Member who has borrowed the equipment.
 * @throws InterruptedException If the thread gets interrupted, this will get thrown.
 */
public void checkoutEquipment(ArrayList<BorrowedEquipment> borrowedEquipments, ArrayList<Equipment> equipment, Member borrower) throws InterruptedException{
    LocalDate date = LocalDate.now();
    if (!equipment.isEmpty()){
        System.out.println("Equipment List: ");
        for (Equipment e : equipment){
            System.out.println(e.getItemName());
        }
        Scanner sc = new Scanner(System.in);
        boolean valid = false;
        while (!valid) {
            System.out.println("Enter the name of the equipment you wish to borrow (type exit to stop): ");
            String itemToBorrow = sc.nextLine();
            // Using Objects.equals to account for possible null entries.
            if (Objects.equals(itemToBorrow, "exit")){
                break;
            }
            int count = 0;
            for (Equipment e : equipment) {
                if (count < equipment.size()) {
                    if (!Objects.equals(e.getItemName(), itemToBorrow)) {
                        count++;
                    } else {
                        if (e.getAvailability()) {

                            /* The user can input how many days the equipment will be used for, and edits the due date
                            to match this input.
                             */

                            System.out.println("How many days is this equipment going to be used for?");
                            int numberOfDays = Integer.parseInt(sc.nextLine());
                            // DateReturned will be edited in the borrowedEquipment list.
                            borrowedEquipments.add(new BorrowedEquipment(e, date, date.plusDays(numberOfDays), null, borrower));
                            e.setAvailability(false);
                            System.out.println("Equipment has been borrowed.");
                            Thread.sleep(2000);
                            valid = true;
                        } else {
                            System.out.println("Equipment is not available to borrow.");
                            Thread.sleep(1000);
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

/**
 * Takes the equipment that a member wants to return, and edits the equipment list to reflect the item has been returned.
 * @param borrowedEquipments List of borrowed equipment.
 * @param equipment List of equipment.
 * @param previousBorrowedList List of previously borrowed equipment, used to calculate overdraft fees.
 * @throws InterruptedException If the thread gets interrupted, this will get thrown.
 */
public void returnEquipment(ArrayList<BorrowedEquipment> borrowedEquipments, ArrayList<Equipment> equipment, ArrayList<BorrowedEquipment> previousBorrowedList) throws InterruptedException {
    LocalDate date = LocalDate.now();
    if (!borrowedEquipments.isEmpty()){
        System.out.println("Equipment List: ");
        for (BorrowedEquipment e : borrowedEquipments){
            System.out.println(e.getEquipment().getItemName());
        }
        Scanner sc = new Scanner(System.in);
        boolean valid = false;
        while (!valid) {
            System.out.println("Enter the name of the equipment you wish to return (type exit to stop): ");
            String itemToReturn = sc.nextLine();
            if (Objects.equals(itemToReturn, "exit")){
                break;
            }
            else {
                boolean found = false;
                for (BorrowedEquipment be : borrowedEquipments) {
                    if (Objects.equals(be.getEquipment().getItemName(), itemToReturn)) {
                        if (equipment.contains(be.getEquipment())) {
                            System.out.println("Equipment has been returned.");
                            // finds the same equipment in the equipments list and sets its availability to true;
                            for (Equipment eq : equipment) {
                                if (Objects.equals(eq.getItemName(), itemToReturn)) {
                                    eq.setAvailability(true);
                                    break;
                                }
                            }
                            be.setDateReturned(date);
                            previousBorrowedList.add(be);
                            borrowedEquipments.remove(be);
                            calculateOverdraft(previousBorrowedList, be.getEquipment());
                            Thread.sleep(2000);
                            valid = true;
                            found = true;
                            break;
                        }
                    }
                }
                if (!found) {
                    System.out.println("Equipment does not exist in borrowed list, please try again.");
                    Thread.sleep(1000);
                }
            }
        }
    }
    else{
        System.out.println("There is no borrowed equipment in the list, please borrow an equipment before returning it.");
        Thread.sleep(1000);
    }
}

/**
 * Calculates the fixed overdraft fee if the item is returned late.
 * @param previousBorrowedEquipments List of previously borrowed equipments, used to get the due and return dates.
 * @param e Equipment being returned, used to find the correct borrowed equipment in the previousBorrowedEquipments list.
 */
public void calculateOverdraft(ArrayList<BorrowedEquipment> previousBorrowedEquipments, Equipment e) {
    for (BorrowedEquipment be : previousBorrowedEquipments) {
        if (Objects.equals(be.getEquipment().getItemName(), e.getItemName())){
            if (be.getDueDate().isAfter(be.getDateReturned()) || be.getDueDate().isEqual(be.getDateReturned())){
                System.out.println("Equipment has been returned on time. No overdraft fee is required.");
            }
            else{
                long daysLate = ChronoUnit.DAYS.between(be.getDueDate(), be.getDateReturned());
                double fee = daysLate * 5.0;
                System.out.println("Equipment is overdue by " + daysLate + " days.");
                System.out.println("Overdraft fee is £" + fee);
            }
        }
    }


}