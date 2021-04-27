package org.example;

public class Trip {
    flight firstFlight;
    flight returnFlight;
    double totalPrice;
    String leavingFrom;
    String goingTo;

    public Trip(){
        leavingFrom = "Atlanta";
        goingTo = "Cancun";
        firstFlight = new flight();
        returnFlight = new flight();
        calcTotalPrice();
    }

    public Trip(String goingTo, flight firstFlight, flight returnFlight){
        leavingFrom = "Atlanta";
        this.goingTo = goingTo;
        this.firstFlight = firstFlight;
        this.returnFlight = returnFlight;
        calcTotalPrice();
    }

    public void calcTotalPrice(){
        totalPrice = firstFlight.getPrice()+ returnFlight.getPrice();
    }



}
