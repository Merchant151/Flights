package org.example;

import java.sql.Driver;
import java.util.ArrayList;
import java.util.List;

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

    public void initFilter(){
        myDriver.clickNonStop();
    }

    /**
     * This method is run assuming the page is already set to the trip it wants to collect
     * @param destination place you are going to vacation
     * @param leavingDate date you are leaving for your vacation
     * @return return trip built from data
     */
    public Trip buildTrip(String destination,String leavingDate) {
        flight firstFlight = new flight(myDriver.readFlight());
        myDriver.changeToReturnFlight();
        myDriver.waitForFlightLoad();
        flight returnFlight = new flight(0,myDriver.readReturnFlight());

        return new Trip(destination, leavingDate, myDriver.plus7(leavingDate),firstFlight,returnFlight);

    }

    public Trip bestTrip(List<Trip> tripList) {
        Trip best = tripList.get(0);
        for (int i = 0; i < tripList.size(); i++) {
            if (best.totalPrice > tripList.get(i).totalPrice){
                best = tripList.get(i);
            }
        }
        return best;
    }

    public void loadDate(){
        myDriver.submitDate();
        myDriver.submitOptions();
        myDriver.waitForFlightLoad();
        myDriver.clickNonStop();
        myDriver.waitForFlightLoad();

    }

    public List<Trip> fillFlightList(String going, String date, String lastDate) {
        List<Trip> tripList = new ArrayList<>();

        do{
            tripList.add(buildTrip(going,date));
            date = myDriver.plus1(date);
        }while(!date.equals(lastDate));

        return tripList;



    }
}
