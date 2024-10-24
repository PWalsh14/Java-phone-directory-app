package uk.ac.ncl.csc1035_csc1036_part2_tutorial.test;

import org.junit.Assert;
import org.junit.Test;
import uk.ac.ncl.csc1035_csc1036_part2_tutorial.util.Name;

public class NameTest {

    @Test
    public void testGetInstanceNormal() {
        Assert.assertNotNull(Name.getInstance("fedor shmarov"));
    }

    @Test
    public void testGetInstanceNullInput() {
        Assert.assertNull(Name.getInstance(null));
    }

    @Test
    public void testGetInstanceInvalidLength() {
        Assert.assertNull(Name.getInstance("fewerwdsdfsdfwefwdsdfwefsdcsdvsdavasdvasvcacvadv sdafs"));
        Assert.assertNull(Name.getInstance(" sdafs   fewerwdsdfsdfwefwdsdfwefsdcsdvsdavasdvasvcacvadv"));
        Assert.assertNull(Name.getInstance("fewerwdsdfsdfwefwdsdfwefsdcsdvsdavasdvasvcacvadv fewerwdsdfsdfwefwdsdfwefsdcsdvsdavasdvasvcacvadv"));
    }

    @Test
    public void testGetInstanceInvalidCharacter() {
        Assert.assertNull(Name.getInstance("fedor0 shmarov"));
        Assert.assertNull(Name.getInstance("fedor shmarov1"));
    }

    @Test
    public void testToString() {
        Name name = Name.getInstance("   fedor    shmarov   ");
        Assert.assertEquals("FEDOR SHMAROV", name.toString());
    }

    @Test
    public void testEquals() {
        Name name = Name.getInstance("fedor shmarov");
        Assert.assertFalse(name.equals(null));
        Assert.assertTrue(name.equals(name));
        Assert.assertTrue(name.equals(Name.getInstance("  FedOr  shMarov  ")));
        Assert.assertFalse(name.equals(Name.getInstance("john smith")));
    }
}
