package uk.ac.ncl.csc1035_csc1036_part2_tutorial.test;

import org.junit.Assert;
import org.junit.Test;
import uk.ac.ncl.csc1035_csc1036_part2_tutorial.PhoneNumber.AbstractPhoneNumber;
import uk.ac.ncl.csc1035_csc1036_part2_tutorial.PhoneNumber.PhoneNumber;

public class DomesticPhoneNumberTest {

    @Test
    public void testGetInstanceNormal() {
        Assert.assertNotNull(AbstractPhoneNumber.getInstance("012312894"));
    }

    @Test
    public void testGetInstanceNullInput() {
        Assert.assertNull(AbstractPhoneNumber.getInstance(null));
    }

    @Test
    public void testGetInstanceInvalidLength() {
        Assert.assertNull(AbstractPhoneNumber.getInstance("1231984705"));
    }

    @Test
    public void testGetInstanceInvalidCharacter() {
        Assert.assertNull(AbstractPhoneNumber.getInstance("i23oo8475"));
    }

    @Test
    public void testGetInstanceNegativeInput() {
        Assert.assertNull(AbstractPhoneNumber.getInstance("-12319847"));
    }
    @Test
    public void testToString() {
        Assert.assertEquals("123456789", AbstractPhoneNumber.getInstance("123456789").toString());
    }

    @Test
    public void testEquals() {
        PhoneNumber number = AbstractPhoneNumber.getInstance("123456789");
        // Comparison to null
        Assert.assertFalse(number.equals(null));
        // Comparison to itself
        Assert.assertTrue(number.equals(number));
        // Comparison to the same number
        Assert.assertTrue(number.equals(AbstractPhoneNumber.getInstance("123456789")));
        // Comparison to a different number
        Assert.assertFalse(number.equals(AbstractPhoneNumber.getInstance("987654321")));
    }

}
