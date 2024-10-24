package uk.ac.ncl.csc1035_csc1036_part2_tutorial.PhoneDirectory;

import uk.ac.ncl.csc1035_csc1036_part2_tutorial.PhoneNumber.AbstractPhoneNumber;
import uk.ac.ncl.csc1035_csc1036_part2_tutorial.PhoneNumber.DomesticPhoneNumber;
import uk.ac.ncl.csc1035_csc1036_part2_tutorial.PhoneNumber.InternationalPhoneNumber;
import uk.ac.ncl.csc1035_csc1036_part2_tutorial.PhoneNumber.PhoneNumber;
import uk.ac.ncl.csc1035_csc1036_part2_tutorial.util.Name;
import uk.ac.ncl.csc1035_csc1036_part2_tutorial.util.PhoneCall;

import java.io.*;
import java.util.*;

public class PhoneDirectoryImpl implements PhoneDirectory {

    private Map<PhoneNumber, Name> phoneDirectory;
    private List<PhoneCall> phoneCallHistory;

    private PhoneDirectoryImpl() {
        this.phoneDirectory = new HashMap<>();
        this.phoneCallHistory = new ArrayList<>();
    }

    public static PhoneDirectory getInstance() {
        return new PhoneDirectoryImpl();
    }

    public boolean addRecord(String fullname, String phoneNumber) {
        // Trying to create a util.Name instance
        Name name = Name.getInstance(fullname);
        if(name == null) {
            System.err.println("The name you entered \"" + fullname + "\" is invalid");
            return false;
        }

        PhoneNumber number = AbstractPhoneNumber.getInstance(phoneNumber);

        // Try to create a util.PhoneNumber instance
        if(number == null) {
            System.err.println("The phone number you entered \"" + phoneNumber + "\" is invalid");
            return false;
        }

        // Making sure that the phone number is unique
        if(phoneDirectory.containsKey(number)) {
            System.err.println("The phone number you entered \"" + phoneNumber + "\" already exists");
            return false;
        }

        // Can add the record into the directory now
        phoneDirectory.put(number, name);
        return true;
    }

    @Override
    public boolean addRecord(Name name, PhoneNumber phoneNumber) {
        if(phoneDirectory.containsKey(phoneNumber))
            return false;

        phoneDirectory.put(phoneNumber, name);
        return true;
    }

    public boolean removeRecordByNumber(String phoneNumber) {
        // Try to create a util.PhoneNumber instance
        PhoneNumber number = AbstractPhoneNumber.getInstance(phoneNumber);
        if(number == null) {
            System.err.println("The phone number you entered \"" + phoneNumber + "\" is invalid");
            return false;
        }

        // Making sure that the phone number exists
        if(!phoneDirectory.containsKey(number)) {
            System.err.println("The phone number you entered \"" + number + "\" does not exist");
            return false;
        }

        phoneDirectory.remove(number);
        return true;
    }

    public int removeAllRecordsByName(String fullname) {
        // Try to create a util.Name instance
        Name name = Name.getInstance(fullname);
        if(name == null) {
            System.err.println("The name you entered \"" + fullname + "\" is invalid");
            return 0;
        }
        // Iterating through the directory, removing and counting
        Iterator<Map.Entry<PhoneNumber, Name>> iterator = phoneDirectory.entrySet().iterator();
        int counter = 0;
        while(iterator.hasNext()) {
            if(iterator.next().getValue().equals(name)) {
                iterator.remove();
                counter++;
            }
        }
        return counter;
    }

    @Override
    public Map<PhoneNumber, Name> getPhoneDirectory() {
        return phoneDirectory;
    }

    public void savePhoneDirectoryToFile(String path) throws IOException {
        FileOutputStream f = new FileOutputStream(path);
        ObjectOutputStream o = new ObjectOutputStream(f);
        o.writeObject(phoneDirectory);
        o.close();
        f.close();
    }

    public void loadPhoneDirectoryFromFile(String path) throws IOException, ClassNotFoundException {
        FileInputStream f = new FileInputStream(path);
        ObjectInputStream i = new ObjectInputStream(f);
        phoneDirectory = (HashMap<PhoneNumber, Name>) i.readObject();
        i.close();
        f.close();
    }

    public void display() {
        int i = 1;
        for(PhoneNumber phoneNumber : phoneDirectory.keySet()) {
            System.out.print(i + ") " + phoneDirectory.get(phoneNumber) + "   |   " + phoneNumber);
            if(phoneNumber instanceof DomesticPhoneNumber)
                System.out.print("   |   DOMESTIC");
            else if (phoneNumber instanceof InternationalPhoneNumber)
                System.out.print("   |   INTERNATIONAL");
            else
                System.out.print("   |   <UNKNOWN>");
            System.out.println();
            i++;
        }
    }

    public boolean makePhoneCall(String phoneNumber) {
        PhoneNumber number = AbstractPhoneNumber.getInstance(phoneNumber);
        // Could not create a PhoneNumber instance. So can't make a phone call
        if(number == null)
            return false;
        // Trying to get the Name from the database
        Name name = phoneDirectory.get(number);
        // The contact does not exist. So can't make a phone call again
        if(name == null)
            return false;
        // Finally, can start making a phone call
        PhoneCall phoneCall = new PhoneCall(number, name);
        phoneCall.start();
        System.out.println("Calling " + name + " ...");
        /*
            The actual implementation of making a phone call.
            Here you can put logic of connecting to a communication
            module, etc.
            Here, we just model it as a random delay.
         */
        try {
            Thread.sleep((long) (Math.random() * 10000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        /*
            This is where all telecom logic code ends.
            Finally, finishing the phone call.
         */
        phoneCall.finish();
        System.out.println("... call ended");
        phoneCallHistory.add(phoneCall);
        return true;
    }

    public void savePhoneCallHistoryToFile(String path) throws IOException {
        FileOutputStream f = new FileOutputStream(path);
        ObjectOutputStream o = new ObjectOutputStream(f);
        o.writeObject(phoneCallHistory);
        o.close();
        f.close();
    }

    public void loadPhoneCallHistoryFromFile(String path) throws IOException, ClassNotFoundException {
        FileInputStream f = new FileInputStream(path);
        ObjectInputStream i = new ObjectInputStream(f);
        phoneCallHistory = (ArrayList<PhoneCall>) i.readObject();
        i.close();
        f.close();
    }

    public List<PhoneCall> getPhoneCallHistory() {
        return phoneCallHistory;
    }
}
