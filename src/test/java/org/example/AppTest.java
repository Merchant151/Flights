package org.example;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    private static App myApp;
    @BeforeClass
    public static void before(){
        myApp = new App();
    }

    /**
     *
     */
    @Test
    public void testSetAllData(){
        String leaving = "ATL";
        String going = "CUN";
        String date = "May 1, 2021";

        myApp.setAllData(leaving,going,date);

    }
}
