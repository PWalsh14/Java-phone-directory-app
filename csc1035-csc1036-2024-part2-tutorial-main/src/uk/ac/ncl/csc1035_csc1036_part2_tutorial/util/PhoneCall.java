package uk.ac.ncl.csc1035_csc1036_part2_tutorial.util;

import uk.ac.ncl.csc1035_csc1036_part2_tutorial.PhoneNumber.PhoneNumber;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PhoneCall implements Serializable {

    enum STATUS {
        NOT_STARTED,
        IN_PROGRESS,
        FINISHED
    }

    private STATUS status = STATUS.NOT_STARTED;

    private PhoneNumber phoneNumber;

    private Name name;

    private long duration;

    private Calendar startTime;

    private Calendar finishTime;

    public PhoneCall(PhoneNumber phoneNumber, Name name) {
        this.phoneNumber = phoneNumber;
        this.name = name;
    }

    public Name getName() {
        return name;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public boolean start() {
        if(status != STATUS.NOT_STARTED)
            return false;

        status = STATUS.IN_PROGRESS;
        startTime = Calendar.getInstance();
        return true;
    }

    public boolean finish() {
        if(status != STATUS.IN_PROGRESS)
            return false;

        status = STATUS.FINISHED;
        finishTime = Calendar.getInstance();
        duration = (finishTime.getTimeInMillis() - startTime.getTimeInMillis())/1000;
        return true;
    }

    public long getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        // Setting formatting for the start time output
        String startTimeSimpleFormat =
                new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(startTime.getTime());
        return phoneNumber + "   |   " +
                name + "   |   " +
                startTimeSimpleFormat + "   |   " +
                duration + " sec";
    }
}
