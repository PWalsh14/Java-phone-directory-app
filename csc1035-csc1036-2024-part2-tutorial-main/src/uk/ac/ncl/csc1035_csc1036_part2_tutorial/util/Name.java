package uk.ac.ncl.csc1035_csc1036_part2_tutorial.util;

import java.io.Serializable;
import java.util.Objects;

public class Name implements Serializable {

    private static final long serialVersionUID = 123456789L;
    private final String firstname;
    private final String lastname;

    static public boolean isValidString(String name) {
        if(name == null)
            return false;

        // Checking whether each character is in the "A-Z" or "a-z" range,
        // and return false immediately as soon as the first character
        // out of this range is detected.
        for(char c : name.toCharArray())
            if(!((c >= 65 && c <= 90) || (c >= 97 && c <= 122)))
                return false;

        return true;
    }

    private Name(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    static public Name getInstance(String fullname) {
        // Nothing in - leave right away
        if(fullname == null)
            return null;

        // Removing all whitespaces at the beginning and the end of the string
        fullname = fullname.trim();

        // Get all substrings separated by whitespaces
        String[] elements = fullname.split("\\s+");
        // Must have only 2 substrings: extension and phone number
        if(elements.length != 2)
            return null;

        // Both firstname and lastname must be valid strings,
        // and each of them must contain fewer than 32 characters
        if(!isValidString(elements[0]) || !isValidString(elements[1]) ||
            elements[0].length() > 32 || elements[1].length() > 32)
            return null;

        // Finally, safe to create a Name object
        return new Name(elements[0].toUpperCase(), elements[1].toUpperCase());
    }

    @Override
    public String toString() {
        return firstname + " " + lastname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name = (Name) o;
        return Objects.equals(firstname, name.firstname) && Objects.equals(lastname, name.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastname);
    }
}
