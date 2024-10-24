package uk.ac.ncl.csc1035_csc1036_part2_tutorial.PhoneNumber;

import java.io.Serializable;

/**
 * The util.PhoneNumber class provides a simple wrapper for string representation
 * of valid phone numbers.
 *
 * @author Fedor Shmarov
 */
public class DomesticPhoneNumber extends AbstractPhoneNumber implements Serializable {

    DomesticPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
