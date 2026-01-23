package com.example;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }


    public void test5() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        numbers.stream()
                .map(x -> {
                    System.out.println(x);
                    return x;
                })
                .filter(x -> x > 2)
                .map(x -> {
                    System.out.println(x);
                    return x;
                })
                .toList();
    }

}
