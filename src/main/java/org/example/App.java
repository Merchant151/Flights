package org.example;

import java.sql.Driver;
import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App 
{

    public AppDriver myDriver;

    public App(){
        myDriver = new AppDriver();
    }

    public static void main( String[] args )
    {
        /*
        Triplist besttriplist = new Triplist();
        ArrayList<String> locationlist;

        //create locationlist
        locationlist = createLocationList();

        //for each loop of best trip()
        for (int i = 0; i < locationlist.toArray().length; i++) {
            besttriplist.addTrip(getBestTrip(locationlist.get(i)));
        }

        //output(besttriplist)
        System.out.println(output(besttriplist));
        */
    }

    public void setAllData(String leaving, String going, String date) {
        myDriver.initNav();
        try {
            myDriver.setLoc1(leaving);
            myDriver.setLoc2(going);
            Thread.sleep(100);
            myDriver.dateSpan(date);
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
