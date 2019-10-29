package homework;

import java.io.IOException;
import java.util.Scanner;



public class AddressBookForFile {
    public static void runner(Table table, Scanner scanner1) {
        System.out.println();
        System.out.println(
                "Add a name (n) \n" + "Look up a name (l) \n" + "Update address (u) \n" + "Delete an entry (d) \n"
                        + "Save as text.txt file (s) \n" + "Display all entries (a) \n" + "Quit (q) \n");
        System.out.print("Your option is : ");
        String option = scanner1.nextLine();
        while (!option.equals("q")) {
            switch (option) {
                case "n":
                    System.out.print("Name :");
                    String keyN = scanner1.nextLine();
                    String addressN = table.lookUp(keyN);
                    if (addressN == null) {
                        System.out.print("Address : ");
                        String valueN = scanner1.nextLine();
                        boolean inOrNot = table.insert(keyN, valueN);
                        if (inOrNot) {
                            System.out.println("Entry saved! \n");
                            System.out.println();
                        }
                    } else {
                        System.out.println("Name already in the system.");
                        System.out.println();
                    }
                    System.out.println("Add a name (n) \n" + "Look up a name (l) \n" + "Update address (u) \n"
                            + "Delete an entry (d) \n" + "Save as text.txt file (s) \n" + "Display all entries (a) \n"
                            + "Quit (q) \n");
                    break;
                case "l":
                    System.out.print("Name : ");
                    String keyL = scanner1.nextLine();
                    String addressL = table.lookUp(keyL);
                    if (addressL == null) {
                        System.out.println("Name not in the system.");
                    } else {
                        System.out.print("Address : " + addressL);
                        System.out.println();
                    }
                    System.out.println();
                    System.out.println("Add a name (n) \n" + "Look up a name (l) \n" + "Update address (u) \n"
                            + "Delete an entry (d) \n" + "Save as text.txt file (s) \n" + "Display all entries (a) \n"
                            + "Quit (q) \n");
                    break;
                case "u":
                    System.out.print("Name : ");
                    String keyU = scanner1.nextLine();
                    String addressU = table.lookUp(keyU);
                    if (addressU != null) {
                        System.out.println("Address : " + addressU);
                        System.out.println("Please enter the new address : ");
                        String valueU = scanner1.nextLine();
                        boolean updateOrNot = table.update(keyU, valueU);
                        if (updateOrNot) {
                            System.out.println("Address updated.");
                        } else {
                            System.out.println("Wrong.");
                        }
                    } else {
                        System.out.println("Name not in the system.");
                    }
                    System.out.println();
                    System.out.println("Add a name (n) \n" + "Look up a name (l) \n" + "Update address (u) \n"
                            + "Delete an entry (d) \n" + "Save as text.txt file (s) \n" + "Display all entries (a) \n"
                            + "Quit (q) \n");
                    break;
                case "d":
                    System.out.print("Name :");
                    String keyToDelete = scanner1.nextLine();
                    boolean ifSuccessDeleted = table.delete(keyToDelete);
                    if (ifSuccessDeleted) {
                        System.out.println("Entry deleted.");
                    } else {
                        System.out.println("Name not in the system.");
                    }
                    System.out.println();
                    System.out.println("Add a name (n) \n" + "Look up a name (l) \n" + "Update address (u) \n"
                            + "Delete an entry (d) \n" + "Save as text.txt file (s) \n" + "Display all entries (a) \n"
                            + "Quit (q) \n");
                    break;
                case "s":
                    System.out.println("Please enter the file name: ");
                    String fileName = scanner1.nextLine();
                    table.save(fileName);
                    break;
                case "a":
                    int numberOfItem = table.displayAll();
                    System.out.println("Total number of entries: " + numberOfItem);
                    if (numberOfItem == 0) {
                        System.out.println("Empty address book.");
                    }
                    System.out.println();
                    System.out.println("Add a name (n) \n" + "Look up a name (l) \n" + "Update address (u) \n"
                            + "Delete an entry (d) \n" + "Save as text.txt file (s) \n" + "Display all entries (a) \n"
                            + "Quit (q) \n");
                    break;
                default:
                    System.out.println("Invalid option.");
                    System.out.println();
                    System.out.println("Add a name (n) \n" + "Look up a name (l) \n" + "Update address (u) \n"
                            + "Delete an entry (d) \n" + "Save as text.txt file (s) \n" + "Display all entries (a) \n"
                            + "Quit (q) \n");
                    break;
            }
            System.out.println();
            System.out.print("Choose option : ");
            System.out.println();
            option = scanner1.nextLine();
        }
        scanner1.close();
        System.out.println("Exit the Address Book. \nBye... ");
    }
    public static void main(String[] args) {
        Table table = new Table();
        System.out.println("Do you wan to open a file? (y/n) ");
        Scanner scanner1 = new Scanner(System.in);
        String openFile = scanner1.nextLine();
        while (!openFile.equals("n") && !openFile.equals("y")) {
            System.out.println("Wrong option, please enter Y or N");
            System.out.println();
            openFile = scanner1.nextLine();
        }
        if (openFile.equals("n")) {
            runner(table, scanner1);
        } else {
            System.out.println();
            System.out.println("Give me your file name without .txt");
            String option = scanner1.nextLine();
            try {
                Boolean reader = table.readFile(option);
                while(!reader) {
                    System.out.println("Can't find file, try again");
                    System.out.println();
                    System.out.println("Give me your file name without .txt");
                    System.out.println();
                    option = scanner1.nextLine();
                    reader = table.readFile(option);
                }
                table.displayAll();
                runner(table, scanner1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}