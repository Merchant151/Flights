package org.example;

public class Trip {
    flight firstFlight;
    flight returnFlight;
    double totalPrice;
    String leavingFrom;
    String goingTo;
    String leavingDate;
    String returningDate;

    public Trip(){
        leavingFrom = "Atlanta";
        goingTo = "Cancun";
        leavingDate = "May 1, 2021";
        returningDate = "May 8, 2021";
        firstFlight = new flight();
        returnFlight = new flight();
        calcTotalPrice();
    }

    public Trip(String goingTo,String leavingDate,String returningDate, flight firstFlight, flight returnFlight){
        leavingFrom = "Atlanta";
        this.goingTo = goingTo;
        this.firstFlight = firstFlight;
        this.returnFlight = returnFlight;
        this.leavingDate = leavingDate;
        this.returningDate = returningDate;
        calcTotalPrice();
    }

    public void calcTotalPrice(){
        totalPrice = firstFlight.getPrice()+ returnFlight.getPrice();
    }

    public flight getFirstFlight() {
        return firstFlight;
    }

    public void setFirstFlight(flight firstFlight) {
        this.firstFlight = firstFlight;
    }

    public flight getReturnFlight() {
        return returnFlight;
    }

    public void setReturnFlight(flight returnFlight) {
        this.returnFlight = returnFlight;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getLeavingFrom() {
        return leavingFrom;
    }

    public void setLeavingFrom(String leavingFrom) {
        this.leavingFrom = leavingFrom;
    }

    public String getGoingTo() {
        return goingTo;
    }

    public void setGoingTo(String goingTo) {
        this.goingTo = goingTo;
    }

    public String getLeavingDate() {
        return leavingDate;
    }

    public void setLeavingDate(String leavingDate) {
        this.leavingDate = leavingDate;
    }

    public String getReturningDate() {
        return returningDate;
    }

    public void setReturningDate(String returningDate) {
        returningDate = returningDate;
    }

    @Override
    public String toString(){
        String tripString = "";

        //Trip Details: "Round trip from "leavingFrom" to "goingTo"
        // /n First flight leaves leavingdate at leavingtime
        // \n Second flight leaves returningdate at returningtime
        // \n the total price of the trip is totalPrice

        tripString += "Round trip with From "+leavingFrom+" to "+goingTo;
        tripString += "\nFirst flight with "+firstFlight.getAirline()+" leaves "+leavingDate+" at "+firstFlight.getDepartTime();
        tripString += "\nReturn flight with "+returnFlight.getAirline()+"leaves "+returningDate+" at "+returnFlight.getDepartTime();
        tripString += "\nThe total price of the trip is $"+totalPrice;
        return tripString;
    }



}
