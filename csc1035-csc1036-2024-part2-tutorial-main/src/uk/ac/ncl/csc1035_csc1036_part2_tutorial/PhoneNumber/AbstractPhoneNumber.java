package uk.ac.ncl.csc1035_csc1036_part2_tutorial.PhoneNumber;

import java.io.Serializable;
import java.util.Objects;

public abstract class AbstractPhoneNumber implements PhoneNumber, Serializable {
    static boolean isPositiveInteger(String number) {
        if (number == null)
            return false;

        try {
            Long tmp = Long.parseLong(number);
            if(tmp < 0)
                return false;

            // Finally, it's safe to return a util.PhoneNumber object
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }

    String phoneNumber;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Produces a string representation of the util.PhoneNumber object.
     * @return string representation of the phone number.
     */
    @Override
    public String toString() {
        return getPhoneNumber();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractPhoneNumber that = (AbstractPhoneNumber) o;
        return Objects.equals(phoneNumber, that.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneNumber);
    }

    /**
     * Creates a util.PhoneNumber instance from the given input string, which satisfies
     * the given requirements: "each phone number must consist of 9 digit characters".
     * @param number - input string representing a phone number
     * @return the instance of the util.PhoneNumber class if the provided string represents a
     * valid phone number, and null otherwise.
     */
    public static PhoneNumber getInstance(String number) {
        // Nothing in - exit right away
        if(number == null)
            return null;
        // Removing whitespaces at the beginning and at the end
        number = number.trim();
        // The very first character after trimming is '+'.
        // So potentially dealing with an InternationalPhoneNumber
        boolean startsWithPlus = false;
        if(number.toCharArray()[0] == '+') {
            startsWithPlus = true;
            // Taking everything past the '+' sign
            number = number.substring(1);
        }
        // Removing whitespaces at the beginning and at the end again
        number = number.trim();
        // Get all substrings separated by whitespaces
        String[] elements = number.split("\\s+");
        // Only one substring - potentially, DomesticPhoneNumber class
        if(elements.length == 1) {
            // Must be 9 characters long and be a positive number
            if(elements[0].length() == 9 && isPositiveInteger(elements[0]))
                return new DomesticPhoneNumber(elements[0]);
        }
        // Two substrings and '+' at the start - potentially, InternationalPhoneNumber class
        if(elements.length == 2 && startsWithPlus) {
            // No restrictionas on length, but both elements must be a positive numbers
            if(isPositiveInteger(elements[0]) && isPositiveInteger(elements[1]))
                return new InternationalPhoneNumber(elements[0], elements[1]);
        }
        // Could not create an object at this point. Just return null
        return null;
    }
}
