package com.epam.soika;

import com.epam.soika.employee.Address;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple Runner.
 */
public class EntityTest  extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public EntityTest(String testName)
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( EntityTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testAddress()
    {
        Address address = new Address("abc", "def");
        assertTrue("abc".equals(address.getCity()));
        assertTrue("def".equals(address.getStreet()));
        address.setCity("123");
        address.setStreet("456");
        assertTrue("123".equals(address.getCity()));
        assertTrue("456".equals(address.getStreet()));
    }
}
