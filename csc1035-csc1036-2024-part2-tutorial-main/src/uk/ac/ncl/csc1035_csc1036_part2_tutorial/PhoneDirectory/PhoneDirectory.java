package uk.ac.ncl.csc1035_csc1036_part2_tutorial.PhoneDirectory;

import uk.ac.ncl.csc1035_csc1036_part2_tutorial.PhoneNumber.PhoneNumber;
import uk.ac.ncl.csc1035_csc1036_part2_tutorial.util.Name;
import uk.ac.ncl.csc1035_csc1036_part2_tutorial.util.PhoneCall;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface PhoneDirectory {
    boolean addRecord(String fullname, String phoneNumber);
    boolean addRecord(Name name, PhoneNumber phoneNumber);
    boolean removeRecordByNumber(String phoneNumber);
    int removeAllRecordsByName(String fullname);
    void savePhoneDirectoryToFile(String path) throws IOException;
    void loadPhoneDirectoryFromFile(String path) throws IOException, ClassNotFoundException;
    void display();
    Map<PhoneNumber, Name> getPhoneDirectory();
    boolean makePhoneCall(String phoneNumber);
    public List<PhoneCall> getPhoneCallHistory();
    void savePhoneCallHistoryToFile(String path) throws IOException;
    void loadPhoneCallHistoryFromFile(String path) throws IOException, ClassNotFoundException;
}
