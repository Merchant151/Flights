package org.example;

import static java.lang.Double.parseDouble;
import static org.junit.Assert.assertTrue;

import junit.framework.TestResult;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    @Test
    public void testbuildFirstTrip() {
        String leaving = "ATL";
        String going = "CUN";
        String date = "May 1, 2021";
        myApp.setAllData(leaving,going,date);
        myApp.myDriver.submitDate();
        myApp.myDriver.submitOptions();
        myApp.myDriver.waitForFlightLoad();
        myApp.myDriver.clickNonStop();
        myApp.myDriver.waitForFlightLoad();

        Trip mytrip = myApp.buildTrip(going,date);

        System.out.println(mytrip.toString());
    }

    @Test
    public void testBuildSecondTrip(){
        String leaving = "ATL";
        String going = "CUN";
        String date = "May 1, 2021";
        myApp.setAllData(leaving,going,date);
        myApp.myDriver.submitDate();
        myApp.myDriver.submitOptions();
        myApp.myDriver.waitForFlightLoad();
        myApp.myDriver.clickNonStop();
        myApp.myDriver.waitForFlightLoad();

        Trip mytrip = myApp.buildTrip(going,date);

        date = myApp.myDriver.plus1(date);
        myApp.myDriver.dateSpan(date);
        myApp.myDriver.submitDate();
        myApp.myDriver.submitOptionsFromSearchPage();
        myApp.myDriver.waitForFlightLoad();

        Trip trip2 = myApp.buildTrip(going,date);

        System.out.println(mytrip.toString());
        System.out.println(trip2.toString());
    }

    @Test
    public void testBestTrip(){
        String leaving = "ATL";
        String going = "CUN";
        String date = "May 1, 2021";
        myApp.setAllData(leaving,going,date);
        myApp.loadDate();

        Trip mytrip = myApp.buildTrip(going,date);

        date = myApp.myDriver.plus1(date);
        myApp.myDriver.dateSpan(date);
        myApp.myDriver.submitDate();
        myApp.myDriver.submitOptionsFromSearchPage();
        myApp.myDriver.waitForFlightLoad();
        myApp.myDriver.clickNonStop();
        myApp.myDriver.waitForFlightLoad();

        Trip trip2 = myApp.buildTrip(going,date);

        List<Trip> tripList = new ArrayList<>();
        tripList.add(mytrip);
        tripList.add(trip2);

        Trip bestTrip = myApp.bestTrip(tripList);

        System.out.println(mytrip.totalPrice);
        System.out.println(trip2.totalPrice);
        System.out.println("the best price is " + bestTrip.totalPrice);

    }

    @Test
    public void testFillFlightList(){
        String leaving = "ATL";
        String going = "Cancun";
        String date = "May 1, 2021";
        String lastDate = "Jun 6, 2021";
        myApp.setAllData(leaving,going,date);
        myApp.loadDate();

        List<Trip> locbasedTripList = myApp.fillFlightList(going, date,lastDate);
        for (int i = 0; i < locbasedTripList.size(); i++) {
            System.out.println(locbasedTripList.get(i).totalPrice);
        }
        Trip best = myApp.bestTrip(locbasedTripList);

        System.out.println(best.totalPrice);
    }

    @Test
    public void testRunCollection(){
        List<String> goingList = new ArrayList<>();
        goingList.add("Cancun");
        goingList.add("Las Vegas");
        goingList.add("Denver");
        String  date = "May 1, 2021";
        String lastDate = "May 7, 2021";

        List<Trip> BestTrips = myApp.runCollection(goingList,date,lastDate);

        for (int i = 0; i < BestTrips.size(); i++) {
            System.out.println(BestTrips.get(i).toString());
        }
    }

    @Test
    public void tinyAppTest(){
        App my = new App();
        List<Trip> besttriplist;
        List<String> locationlist = new ArrayList<>();

        //create locationlist
        locationlist.add("Rome");
        locationlist.add("Las Vegas");
        locationlist.add("Denver");
        locationlist.add("Milan");

        //for each loop of best trip()
        besttriplist = my.runCollection(locationlist,"May 1, 2021","May 5, 2021");

        //output(besttriplist)
        System.out.println("Here is a list of the cheapest flights to vacation spots!");
        for (int i = 0; i < besttriplist.size(); i++) {
            System.out.println(besttriplist.get(i).toString());
            System.out.println("\n\n");
        }
    }

    @Test
    public void math(){
        String string = "$1,608";
        System.out.println(string);
        if(string.contains(",")){
            System.out.println("changing");
            string = string.substring(0,2) + string.substring(3);
            System.out.println(string);
        }
        System.out.println(parseDouble(string.substring(1)));
    }


    @AfterClass
    public static void cleaning(){
        try{
            Thread.sleep(10000);
        }catch (Exception e){
            //do nothing
        }
        myApp.myDriver.cleanup();
    }
}
