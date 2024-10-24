package uk.ac.ncl.csc1035_csc1036_part2_tutorial.ui;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import uk.ac.ncl.csc1035_csc1036_part2_tutorial.PhoneDirectory.*;
import uk.ac.ncl.csc1035_csc1036_part2_tutorial.PhoneNumber.AbstractPhoneNumber;
import uk.ac.ncl.csc1035_csc1036_part2_tutorial.PhoneNumber.PhoneNumber;
import uk.ac.ncl.csc1035_csc1036_part2_tutorial.util.Name;
import uk.ac.ncl.csc1035_csc1036_part2_tutorial.util.PhoneCall;

public class PhoneDirectoryApp {

    static void printMainMenu() {
        System.out.println();
        System.out.println("--------------------");
        System.out.println("Welcome to the phone directory management system.");
        System.out.println();
        System.out.println("----- PHONE DIRECTORY OPERATIONS -----");
        System.out.println("1. Add a new record (string version)");
        System.out.println("11. Add a new record (types version)");
        System.out.println("2. Remove a record by phone number");
        System.out.println("3. Remove all records by name");
        System.out.println("4. Display the directory");
        System.out.println("5. Save the directory to a file");
        System.out.println("6. Load the directory from a file");
        System.out.println("----- PHONE CALL OPERATIONS -----");
        System.out.println("7. Make a phone call");
        System.out.println("8. Show phone calls history");
        System.out.println("9. Save the phone calls history to a file");
        System.out.println("10. Load the phone calls history from a file");
        System.out.println("--------------------");
        System.out.println("0. Exit");
        System.out.println("--------------------");
        System.out.println();
    }

    static int readOpt(Scanner inputScanner) {
        System.out.print("Enter your choice here: ");
        int opt = -1;
        try {
            opt = inputScanner.nextInt();
        }
        catch(InputMismatchException e) {
            System.err.println("Invalid input encountered");
        }
        inputScanner.nextLine();
        return opt;
    }

    public static void main(String[] args) {
        PhoneDirectory phoneDirectory = PhoneDirectoryImpl.getInstance();
        Scanner inputScanner = new Scanner(System.in);
        printMainMenu();
        int opt = readOpt(inputScanner);
        while(opt != 0) {
            switch (opt) {
                // Adding a new record to the directory
                case 1: {
                    System.out.println();
                    System.out.print("Enter the fullname here: ");
                    String fullname = inputScanner.nextLine();
                    System.out.print("Enter the phone number here: ");
                    String phoneNumber = inputScanner.nextLine();
                    // Using addRecord(String fullname, String phoneNumber)
                    if (phoneDirectory.addRecord(fullname, phoneNumber))
                        System.out.println("\nThe record was added successfully\n");
                    else
                        System.err.println("\nCould not add the record\n");
                    break;
                }
                case 11: {
                    System.out.println();
                    System.out.print("Enter the fullname here: ");
                    String fullname = inputScanner.nextLine();
                    System.out.print("Enter the phone number here: ");
                    String phoneNumber = inputScanner.nextLine();
                    // Using addRecord(Name name, PhoneNumber phoneNumber)
                    // Trying to create a util.Name instance
                    Name name = Name.getInstance(fullname);
                    if(name == null) {
                        System.err.println("The name you entered \"" + fullname + "\" is invalid");
                        break;
                    }
                    PhoneNumber number = AbstractPhoneNumber.getInstance(phoneNumber);
                    // Try to create a util.PhoneNumber instance
                    if(number == null) {
                        System.err.println("The phone number you entered \"" + phoneNumber + "\" is invalid");
                        break;
                    }
                    if (phoneDirectory.addRecord(name, number))
                        System.out.println("\nThe record was added successfully\n");
                    else
                        System.err.println("\nCould not add the record\n");
                    break;
                }
                // Removing a record from the directory by phone number
                case 2: {
                    System.out.println();
                    System.out.print("Enter phone number here: ");
                    String phoneNumber = inputScanner.nextLine();
                    if (phoneDirectory.removeRecordByNumber(phoneNumber))
                        System.out.println("\nThe record was removed successfully\n");
                    else
                        System.err.println("\nCould not remove the record\n");
                    break;
                }
                // Removing all records by name
                case 3: {
                    System.out.println();
                    System.out.print("Enter fullname here: ");
                    String fullname = inputScanner.nextLine();
                    int counter = phoneDirectory.removeAllRecordsByName(fullname);
                    System.out.println();
                    System.out.println(counter + " records were removed");
                    System.out.println();
                    break;
                }
                // Outputting list of names on the screen
                case 4: {
                    System.out.println();
                    System.out.println("*********************");
                    System.out.println("Phone Directory:");
                    phoneDirectory.display();
                    System.out.println("*********************");
                    System.out.println();
                    System.out.println("... press Enter to continue ...");
                    inputScanner.nextLine();
                    break;
                }
                case 5: {
                    System.out.println();
                    System.out.print("Enter the destination filepath: ");
                    String path = inputScanner.nextLine();
                    try {
                        phoneDirectory.savePhoneDirectoryToFile(path);
                        System.out.println("The phone directory was successfully saved to file");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                case 6: {
                    System.out.println();
                    System.out.print("Enter the source filepath: ");
                    String path = inputScanner.nextLine();
                    try {
                        phoneDirectory.loadPhoneDirectoryFromFile(path);
                        System.out.println("The phone directory was successfully loaded from file");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                case 7: {
                    System.out.println();
                    System.out.print("Enter phone number here: ");
                    String phoneNumber = inputScanner.nextLine();
                    boolean isCallSuccessful = false;
                    isCallSuccessful = phoneDirectory.makePhoneCall(phoneNumber);
                    if(!isCallSuccessful)
                        System.err.println("Could not this number: " + phoneNumber);
                    System.out.println();
                    break;
                }
                case 8: {
                    List<PhoneCall> phoneCallHistory = phoneDirectory.getPhoneCallHistory();
                    System.out.println();
                    System.out.println("*********************");
                    System.out.println("Phone Calls History:");
                    int i = 1;
                    for(PhoneCall phoneCall : phoneCallHistory) {
                        System.out.println(i + ") " + phoneCall);
                        i++;
                    }
                    System.out.println("*********************");
                    System.out.println();
                    System.out.println("... press Enter to continue ...");
                    inputScanner.nextLine();
                    break;
                }
                case 9: {
                    System.out.println();
                    System.out.print("Enter the destination filepath: ");
                    String path = inputScanner.nextLine();
                    try {
                        phoneDirectory.savePhoneCallHistoryToFile(path);
                        System.out.println("The phone calls history was successfully saved to file");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                case 10: {
                    System.out.println();
                    System.out.print("Enter the source filepath: ");
                    String path = inputScanner.nextLine();
                    try {
                        phoneDirectory.loadPhoneCallHistoryFromFile(path);
                        System.out.println("The phone calls history was successfully loaded from file");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                default: {
                    System.err.println("\n!!! Unrecognised option \"" + opt + "\". Try again\n");
                    break;
                }
            }
            printMainMenu();
            opt = readOpt(inputScanner);
        }
        inputScanner.close();
    }

}
