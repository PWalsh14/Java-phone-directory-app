package uk.ac.ncl.csc1035_csc1036_part2_tutorial.test;

import org.junit.Assert;
import org.junit.Test;
import uk.ac.ncl.csc1035_csc1036_part2_tutorial.PhoneNumber.AbstractPhoneNumber;
import uk.ac.ncl.csc1035_csc1036_part2_tutorial.PhoneNumber.PhoneNumber;


public class InternationalPhoneNumberTest {

    @Test
    public void testGetInstanceNormal() {
        Assert.assertNotNull(AbstractPhoneNumber.getInstance("+44 012312894"));
        Assert.assertNotNull(AbstractPhoneNumber.getInstance(" +33  0123128"));
        Assert.assertNotNull(AbstractPhoneNumber.getInstance("+ 1 012312854394"));
    }

    @Test
    public void testGetInstanceNullInput() {
        Assert.assertNull(AbstractPhoneNumber.getInstance(null));
    }

    @Test
    public void testGetInstanceInvalidCharacter() {
        Assert.assertNull(AbstractPhoneNumber.getInstance("+43i23oo8475"));
        Assert.assertNull(AbstractPhoneNumber.getInstance("43 235254626"));
        Assert.assertNull(AbstractPhoneNumber.getInstance(" + 234 i23oo8475"));
        Assert.assertNull(AbstractPhoneNumber.getInstance("fw 123008475"));
    }

    @Test
    public void testGetInstanceNegativeInput() {
        Assert.assertNull(AbstractPhoneNumber.getInstance("+ -33 -12319847"));
        Assert.assertNull(AbstractPhoneNumber.getInstance("  + 33 -12319847"));
        Assert.assertNull(AbstractPhoneNumber.getInstance(" +44   123  19847"));
    }
    @Test
    public void testToString() {
        Assert.assertEquals("+44 123456789", AbstractPhoneNumber.getInstance("  +44   123456789").toString());
        Assert.assertEquals("+1 789", AbstractPhoneNumber.getInstance("  +  1 789").toString());
        Assert.assertEquals("+33 12345678900", AbstractPhoneNumber.getInstance("+  33  12345678900  ").toString());
    }

    @Test
    public void testEquals() {
        PhoneNumber number = AbstractPhoneNumber.getInstance("+44 123456789");
        // Comparison to null
        Assert.assertFalse(number.equals(null));
        // Comparison to itself
        Assert.assertTrue(number.equals(number));
        // Comparison to the same number
        Assert.assertTrue(number.equals(AbstractPhoneNumber.getInstance("+ 44  123456789")));
        // Comparison to the same phone number with a different extension
        Assert.assertFalse(number.equals(AbstractPhoneNumber.getInstance("+33 123456789")));
        // Comparison to a number with the same extension but a different number
        Assert.assertFalse(number.equals(AbstractPhoneNumber.getInstance("+44 987654321")));
        // Both are different
        Assert.assertFalse(number.equals(AbstractPhoneNumber.getInstance("+33 987654321")));
    }

}
