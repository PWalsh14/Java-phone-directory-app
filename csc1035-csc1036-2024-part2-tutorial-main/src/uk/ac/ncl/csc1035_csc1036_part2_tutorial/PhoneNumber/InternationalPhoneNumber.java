package uk.ac.ncl.csc1035_csc1036_part2_tutorial.PhoneNumber;

import java.io.Serializable;
import java.util.Objects;

/**
 * The util.InternationalPhoneNumber class provides a simple wrapper for string representation
 * of valid phone numbers.
 *
 * @author Fedor Shmarov
 */
public class InternationalPhoneNumber extends AbstractPhoneNumber implements Serializable {

    private String extension;

    InternationalPhoneNumber(String extension, String phoneNumber) {
        this.extension = extension;
        this.phoneNumber = phoneNumber;
    }

    public String getExtension() {
        return extension;
    }

    @Override
    public String getPhoneNumber() {
        return "+" + getExtension() + " " + super.getPhoneNumber();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InternationalPhoneNumber that = (InternationalPhoneNumber) o;
        return super.equals(that) && extension.equals(that.extension);
    }

    @Override
    public int hashCode() {
        return Objects.hash(extension, phoneNumber);
    }
}
