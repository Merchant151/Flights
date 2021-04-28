package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

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
        App my = new App();
        List<Trip> besttriplist;
        List<String> locationlist;

        //create locationlist
        locationlist = createLocationList();

        //for each loop of best trip()
        besttriplist = my.runCollection(locationlist,"May 1, 2021","Aug 15, 2021");

        //output(besttriplist)
        System.out.println("Here is a list of the cheapest flights to vacation spots!");
        for (int i = 0; i < besttriplist.size(); i++) {
            System.out.println(besttriplist.get(i).toString());
            System.out.println("\n\n");
        }
    }

    private static List<String> createLocationList() {
        List<String> goingList = new ArrayList<>();
        goingList.add("Cancun");
        goingList.add("Las Vegas");
        goingList.add("Denver");
        goingList.add("Rome");
        goingList.add("Milan");
        goingList.add("Paris");
        goingList.add("Madrid");
        goingList.add("Amsterdam");
        goingList.add("Singapore");

        return goingList;
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
    public Trip buildTrip(String destination,String leavingDate)  {
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
            try {
                tripList.add(buildTrip(going, date));
                //System.out.println("App.fillFilightList "+date);
                date = myDriver.plus1(date);
                //System.out.println("App.fillFilightList new date"+date);
                newDateChange(date);
            }catch(StaleElementReferenceException e){
                myDriver.clickPopUp();
            }
        }while(!date.equals(lastDate));

        return tripList;



    }

    private void newDateChange(String date) {
        myDriver.dateSpan(date);
        myDriver.submitDate();
        myDriver.submitOptionsFromSearchPage();
        myDriver.waitForFlightLoad();
        myDriver.clickNonStop();
        myDriver.waitForFlightLoad();
    }

    public List<Trip> runCollection(List<String> goingList, String date, String lastDate) {
        List<Trip> bestTrips = new ArrayList<>();
        setAllData("Atl",goingList.get(0),date);
        loadDate();

        for (int i = 0; i < goingList.size(); i++) {
            bestTrips.add(bestTrip(fillFlightList(goingList.get(i), date,lastDate)));

            setAllData("Atl",goingList.get(i),date);
            loadDate();
        }
        return bestTrips;
    }
}
