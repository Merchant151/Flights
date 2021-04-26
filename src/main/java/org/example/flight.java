package org.example;

import static jdk.internal.math.FloatingDecimal.parseDouble;

public class flight {

    private String airline ,departTime, departLoc, arrivalTime, arrivalLocation;
    private double price;

    public flight(){
        airline = "MadeUp";
        departTime = "10:00am";
        departLoc = "Atlanta";
        arrivalTime = "10:00pm";
        arrivalLocation = "Cancun";
        price = 999999.99;
    }

    public flight(String flightStr){
        //example flight string
        //Select and show fare information for Frontier Airlines flight, departing at 8:00am from Atlanta, Landing at 2:26pm in Cancun, Priced at $402
        String[] strings = flightStr.split(" ");
        airline = strings[6];
        departTime = strings[11];
        departLoc = strings[13].substring(0,strings[13].length());
        arrivalTime = strings[16];
        arrivalLocation = strings[18].substring(0,strings[18].length());
        price = parseDouble(strings[21].substring(1));
    }

    @Override
    public String toString() {
        return "" + airline+" "+departTime+" "+departLoc+" "+arrivalTime+" "+arrivalLocation+" "+price;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getDepartTime() {
        return departTime;
    }

    public void setDepartTime(String departTime) {
        this.departTime = departTime;
    }

    public String getDepartLoc() {
        return departLoc;
    }

    public void setDepartLoc(String departLoc) {
        this.departLoc = departLoc;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getArrivalLocation() {
        return arrivalLocation;
    }

    public void setArrivalLocation(String arrivalLocation) {
        this.arrivalLocation = arrivalLocation;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    /** This is the li object that contains flight data from search page (for the first flight)
     *          <li data-test-id="offer-listing"
     *          id="AQqYAwqCA3Y1LXNvcy0wZmIwYzNjZTAyYTQ1ODAwMjg2YThmYWFmMzIxMTQwMC0yLTEtc3QtdjUtc29zLTFiZmMwM
     *          jBjZWQyZmQwMDA0ZmQ1NDk5MmEzMDExNDAwLTIxLTJ-Mi5TfkFRb0NDQUVTQndqVUJCQUJHQUVvQWxnQ2NBQX5BUXBBQ2
     *          g4SXhuSVNCREV3TVRBWTRGd2d6SEFva0xYckFURHV0ZXNCT0VkQUFGZ0JDaDBJeG5JU0FqTXhHTXh3SUlKdEtOUzM2d0V
     *          3enJqckFUaEhRQUZZQVJJS0NBRVFBUmdCS2dKR09SZ0JJZ1FJQVJBQktBSW9BeWdFTUFJLkFRcEZDaUVJenBZQkVnUXhO
     *          REk0R0lKdElLT3hBU2l1aGV3Qk1PT0c3QUU0VkVBQVdBRUtJQWpPbGdFU0F6RTJNeGlqc1FFZzRGd290NGZzQVRDNWlPd
     *          0JPRkpBQUZnQkVnb0lBaEFCR0FFcUFrNUxHQUVpQkFnQkVBRW9BaWdES0FRd0FnEXsUrkfhFnlAIgEBKgUSAwoBMRI_Ch
     *          YKCjIwMjEtMDUtMDISA0FUTBoDQ1VOChYKCjIwMjEtMDUtMDkSA0NVThoDQVRMEgcSBUNPQUNIGgIQASACGggIARIEGgA
     *          iAA==">
     *          <div data-test-id="intersection-observer">
     *          <div class="uitk-card uitk-card-roundcorner-all uitk-spacing uitk-spacing-margin-blockend-three">
     *          <span class="is-visually-hidden"><h3 class="uitk-heading-7">
     *           Frontier Airlines flight departing at 8:00am from $402</h3>
     *           </span><div class="uitk-card-content-section uitk-card-content-section-padded">
     *           <div class="uitk-layout-grid uitk-layout-grid-gap-two uitk-layout-grid-columns-12">
     *           <div class="uitk-layout-grid-item uitk-layout-grid-item-columnspan-small-8
     *           uitk-layout-grid-item-columnspan-large-9">
                  <div class="uitk-layout-grid uitk-layout-grid-columns-medium-12">
     *             <div class="uitk-layout-grid-item uitk-layout-grid-item-columnspan-medium-7
     *               uitk-layout-grid-item-columnspan-large-6">
     *                <div data-test-id="arrival-time">
     *                 <span class="uitk-type-400 uitk-type-bold uitk-text-emphasis-theme"
     *                   data-test-id="departure-time">8:00am - 2:26pm</span>
     *                    </div>
     *                    <div class="uitk-flex">
     *                     <div class="truncate uitk-type-200 uitk-spacing uitk-spacing-margin-blockstart-one
     *                      uitk-flex-item uitk-flex-basis-seventy_two uitk-text-emphasis-theme"
     *                       data-test-id="arrival-departure">Atlanta (ATL)
     *                       - Cancun (CUN)</div>
     *                       </div></div>
     *                       <div class="uitk-layout-grid-item uitk-layout-grid-item-columnspan-medium-5
     *                       uitk-layout-grid-item-columnspan-large-6">
     *                       <div class="uitk-type-200 uitk-text-emphasis-theme" data-test-id="journey-duration">
     *                        7h 26m (1 stop)</div><div class="uitk-text-spacing-one truncate uitk-type-200"
     *                        data-test-id="layovers">3h 50m in Orlando (MCO)</div>
     *                         </div>
     *                        <div class="uitk-spacing uitk-spacing-margin-blockstart-two uitk-spacing-padding-blockend-one uitk-layout-grid-item uitk-layout-grid-item-columnspan-all uitk-type-200">
     *                       <div class="uitk-flex uitk-flex-align-content-center uitk-flex-gap-one truncate"
     *                        data-test-id="airline-info">
     *                        <div class="uitk-flex-item">
     *                        <div aria-hidden="true" data-test-id="airline-logo-queued"
     *                         data-alt="Frontier Airlines" style="height: 16px; width: 16px;">
     *                         <figure class="uitk-image ratio-1-1"><div class="uitk-image-placeholder">
     *                          <img alt="" class="uitk-image-media"
     *                          src="https://images.trvl-media.com/media/content/expus/graphics/static_content/fusion/v0.1b/images/airlines/vector/s/F9_sq.svg"
     *                           data-loaded="true"></div>
     *                            </figure></div></div>
     *                            <div class="truncate-lines-2 uitk-text-default-theme"
     *                             data-test-id="flight-operated">Frontier Airlines</div>
     *                             </div></div>
     *                             <div class="uitk-spacing uitk-spacing-margin-blockstart-one uitk-layout-grid-item uitk-layout-grid-item-columnspan-all">
     *                              <div class="uitk-type-200">3 cleaning and safety practices</div>
     *                               </div></div></div>
     *                               <div class="uitk-layout-grid-item uitk-layout-grid-item-columnspan-small-4 uitk-layout-grid-item-columnspan-large-3">
     *                               <div class="uitk-flex uitk-flex-nowrap">
     *                                <div class="uitk-flex-item uitk-flex-grow-1">
     *                                <div class="uitk-flex uitk-flex-align-items-flex-end uitk-flex-column" data-test-id="price-column">
     *                                <div class="uitk-price-lockup right-align"><section><span class="uitk-price-a11y is-visually-hidden">$402</span>
     *                               <span data-stid="" class="uitk-lockup-price" aria-hidden="true">$402</span></section></div><div class="uitk-type-right uitk-type-100">
     *                               Roundtrip per traveler</div></div></div></div></div></div><button data-test-id="select-link"
     *                               class="uitk-card-link"><span class="is-visually-hidden">
     *                                   <a tabindex="-1"
     *                               role="button"
     *                               href="/Flights-Search?leg1=from%3AAtlanta%2C%20GA%20%28ATL-Hartsfield-Jackson%20Atlanta%20Intl
     *                               .%29%2Cto%3ACancun%2C%20Quintana%20Roo%20%28CUN-Cancun%20Intl
     *                               .%29%2Cdeparture%3A5%2F2%2F2021TANYT&amp;
     *                               leg2=from%3ACancun%2C%20Quintana%20Roo%20%28CUN-Cancun%20Intl
     *                               .%29%2Cto%3AAtlanta%2C%20GA%20%28ATL-Hartsfield-Jackson%20Atlanta%20Intl.%29%2Cdeparture%3A5%2F9%2F2021TANYT&amp;
     *                               mode=search&amp;options=carrier%3A%2A%2Ccabinclass%3A%2Cmaxhops%3A1%2Cnopenalty%3AN&amp;pageId=0&amp;passengers=adults%3A1%2Cchildren%3A0%2Cinfantinlap%3AN&amp;pwaDialog=details-and-fares-dialog&amp;sortOrder=INCREASING&amp;sortType=PRICE&amp;trip=roundtrip">
     *   Select and show fare information for Frontier Airlines flight, departing at 8:00am from Atlanta, Landing at 2:26pm in Cancun, Priced at $402
     *                               </a></span></button></div></div></div></li>
     */

}

