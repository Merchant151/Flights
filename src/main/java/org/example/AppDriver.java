package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AppDriver {

    private final static String EXPEDIA_HOME = "https://www.expedia.com";
    private static WebDriver driver;

    public static void setUpChrome(){
        System.setProperty("webdriver.chrome.driver","chromedriver");
        //ChormeOptions options = new ChromeOptions(); (Chrome Options doesn't work)
        //options.addArguments("headless");
        //driver = new ChromeDriver(options); //This is for headless mode or no gui
        driver = new ChromeDriver();
    }

    public AppDriver(){
        setUpChrome();
    }

    public static void getWebsite(String website){
        driver.get(website);
    }

    public void cleanup() {
        driver.close();
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static String getExpediaHome() {
        return EXPEDIA_HOME;
    }

    /**
     * initNav is the inical navigation method designed to direct selenium to the part of the page needed for the
     * code to run successfully.
     */
    public void initNav() {
        getWebsite(getExpediaHome());
        driver.findElement(By.xpath("//*[@id='uitk-tabs-button-container']/li[2]/a")).click();
    }

    /**
     * will select the first option after typing in departure airport into "Leaving From" feild
     * @param airPortCode this string will be entered into the location search box
     */
    public void setLoc1(String airPortCode) {
        //click website loc1 box
        //System.out.println("searching for loc1");
        WebElement locBox = driver.findElement(By.xpath("//*[@id=\"wizard-flight-tab-roundtrip\"]/div[2]/div[1]/div/div[1]/div/div/div"));

        locBox.click();
        //new Actions(driver).moveToElement(driver.findElement(By.xpath("//*[@id=\"wizard-flight-tab-roundtrip\"]/div[2]/div[1]/div/div[1]/div/div/div"))).click().perform();

        //System.out.println("moved and clicked");

        //typeintextbox
        // the next three lines are ment to allow the webpage to load but it dosn't work.......
        //WebElement element;
        //WebDriverWait wait = new WebDriverWait(driver, 100);
        //element= wait.until(ExpectedConditions.elementToBeClickable(By.id("undefined-close")));
        try {
            Thread.sleep(100);
        }catch (Exception e){
            System.out.println(e);
        }
        //System.out.println("recommended webdriver wait");
        List<WebElement> inBox = driver.findElements(By.className("uitk-field-input"));
        //System.out.println("found box");
        //                                              //*[@id='app-layer-location-field-leg1-origin-ta-dialog']/div[2]
        //                                              //*[@id="app-layer-location-field-leg1-origin-ta-dialog"]/div[2]/div/div[1]/section/div/input
        inBox.get(1).sendKeys(airPortCode);
        //inBox.get(1).submit();

        //selectfirstresult
        List<WebElement> firstOption = driver.findElements(By.className("uitk-button"));
        for (int i = 0; i < firstOption.size(); i++) {
            //System.out.println(i);
            //System.out.println(firstOption.get(i).getText());
            if(firstOption.get(i).getText().contains(airPortCode)){
                //System.out.println("I choose Option "+i+" because it says "+firstOption.get(i).getText());
                firstOption.get(i).click();
                break;
            }
        }
        //firstOption.get(10).click();
    }

    /**
     * will select the first option after typing in departure airport into "Going to" feild
     * @param airPortCode this string will be entered into the location search box
     */
    public void setLoc2(String airPortCode) {
        List<WebElement> locBox = driver.findElements(By.className("uitk-faux-input"));
        locBox.get(1).click();

        List<WebElement> inBox = driver.findElements(By.className("uitk-field-input"));
        /*for (int i = 0; i < inBox.size(); i++) {
            System.out.println(i);
        }*/
        inBox.get(3).sendKeys(airPortCode);
        try{
            Thread.sleep(1000);
        }catch (Exception e){
            System.out.println(e);
        }

        List<WebElement> firstOption = driver.findElements(By.className("uitk-button"));
        for (int i = 0; i < firstOption.size(); i++) {
            //System.out.println(i);
            //System.out.println(firstOption.get(i).getText());
            if(firstOption.get(i).getText().contains(airPortCode)){
                firstOption.get(i).click();
                break;
            }
        }
        //firstOption.get(10).click();
    }

    /**
     * selects both a departing and return date based on input string return date will be 1 week after depart
     * @param departing date as a string in the format: May 3, 2021 please use 3 letter abbreviation!!!
     */
    public void dateSpan(String departing) {
        List<WebElement> leave = driver.findElements(By.className("uitk-faux-input"));
        leave.get(2).click();
        //System.out.println("AppDriver.dateSpan debug: "+departing);
        Boolean dateFound = false;
        List<WebElement> dates;
        int numberOTries = 0;
        while(!dateFound){
            dates = driver.findElements(By.className("uitk-date-picker-day"));
            //System.out.println(i);
            Boolean date1clicked = false;
            for (int i = 0; i < dates.size(); i++) {
                //System.out.println(dates.get(i).getAttribute("aria-label"));
                if(dates.get(i).getAttribute("aria-label").contains(departing)&&!date1clicked){
                    //System.out.println("AppDriver.DateSpan " +dates.get(i).getAttribute("aria-label"));
                    try {
                        dates.get(i).click();
                    }catch (ElementClickInterceptedException x){
                        Actions buttonunblocker = new Actions(driver);
                        buttonunblocker.moveToElement(dates.get(i));
                        dates.get(i).click();
                    }
                    date1clicked = true;
                }
            }
            try{
                Thread.sleep(300);
            }catch (Exception e){
                //haha
            }
            if(date1clicked){

                String datePlus7 = plus7(departing);
                //System.out.println("AppDriver.datespan trying to click "+ datePlus7);
                for (int i = 0; i < dates.size(); i++) {
                    if(dates.get(i).getAttribute("aria-label").contains((datePlus7))){
                        dates.get(i).click();
                        dateFound = true;
                    }
                }
            }

            if(!dateFound){
                //<button data-stid="date-picker-paging" type="button" class="uitk-button uitk-button-medium uitk-button-only-icon uitk-flex-item uitk-button-paging"><svg class="uitk-icon uitk-button-icon uitk-icon-medium" aria-labelledby="nextMonth-title" role="img" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink"><title id="nextMonth-title">Next month</title><svg><path d="M10 6L8.59 7.41 13.17 12l-4.58 4.59L10 18l6-6-6-6z"></path></svg></svg></button>
                if(numberOTries>5) {
                    System.out.println("can't find the day I am looking for");
                    List<WebElement> list = driver.findElements(By.className("uitk-button-paging"));
                    List<WebElement> pagers = new ArrayList<>();
                    try {
                        for (int i = 0; i < list.size(); i++) {
                            if (list.get(i).getAttribute("date-picker-paging").contains("date-picker-paging")) {
                                System.out.println(i);
                                System.out.println(list.get(i).getAttribute("type"));
                                pagers.add(list.get(i));
                            }
                        }
                    }catch (NullPointerException x){
                        //found nothing
                    }
                }
                //System.out.println("found next month button");
            }
            numberOTries++;
        }
    }

    public String plus7(String departing) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
        Calendar c = Calendar.getInstance();
        String nDate = "";

        String split[] = departing.split(" ");
        nDate += split[2].substring(0,4) +"-";
        nDate += monthFormat(split[0]) + "-";
        nDate += dayFormat(split[1].substring(0,split[1].indexOf(',')));
        //System.out.println(nDate);
        try{
            c.setTime(sdf.parse(nDate));
        }catch (Exception e){
            System.out.println("das tough");
        }
        c.add(Calendar.DAY_OF_MONTH,7);
        nDate = sdf.format(c.getTime());
        String splitA[] = nDate.split("-");
        //Jun 17, 2021
        String fDate = "";

        fDate += monthFormatReverse(splitA[1])+" ";
        fDate += dayFormatReverse(splitA[2])+", ";
        fDate += splitA[0];
        //System.out.println("AppDriver.plus7 Debug: "+nDate+"converted to "+fDate);

        //System.out.println(fDate);
        return fDate;
    }

    private String dayFormatReverse(String s) {
        if(s.substring(0,1).equals("0")){
            return s.substring(1);
        }
        return s;
    }

    private String monthFormatReverse(String s) {
        String str = "";
        switch (s){
            case "01":
                str = "Jan";
                break;
            case "02":
                str = "Feb";
                break;
            case "03":
                str = "Mar";
                break;
            case "04":
                str = "Apr";
                break;
            case "05":
                str = "May";
                break;
            case "06":
                str = "Jun";
                break;
            case "07":
                str = "Jul";
                break;
            case "08":
                str = "Aug";
                break;
            case "09":
                str = "Sep";
                break;
            case "10":
                str = "Oct";
                break;
            case "11":
                str = "Nov";
                break;
            case "12":
                str = "Dec";
                break;
            default:
                str = "Jan";
                break;
        }
        return str;
    }

    private String dayFormat(String substring) {
        if(substring.length()==1){
            return "0"+substring;
        }
        return substring;
    }

    private String monthFormat(String s) {
        String str = "";
        switch (s.toLowerCase()){
            case "jan":
                str = "01";
                break;
            case "feb":
                str = "02";
                break;
            case "mar":
                str = "03";
                break;
            case "apr":
                str = "04";
                break;
            case "may":
                str = "05";
                break;
            case "jun":
                str = "06";
                break;
            case "jul":
                str = "07";
                break;
            case "aug":
                str = "08";
                break;
            case "sep":
                str = "09";
                break;
            case "oct":
                str = "10";
                break;
            case "nov":
                str = "11";
                break;
            case "dec":
                str = "12";
                break;
            default:
                str = "01";
                break;
        }
        return str;
    }

    public void submitOptions() {
        List<WebElement> inBox = driver.findElements(By.className("uitk-field-input"));

        inBox.get(1).submit();
    }

    public void submitOptionsFromSearchPage() {
//<button name="searchButton" data-test-id="search-form-button" type="button" class="uitk-button uitk-button-large uitk-button-fullWidth uitk-button-has-text uitk-button-primary">Search</button>
            List<WebElement> inBox = driver.findElements(By.className("uitk-button"));
            for (int i = 0; i < inBox.size(); i++) {
                try {
                    //System.out.println(inBox.get(i).getAttribute("name"));
                    if (inBox.get(i).getAttribute("data-test-id").contains("search-form-button")) {
                        //System.out.println("dataID found");
                        inBox.get(i).click();
                    }
                } catch (NullPointerException e) {
                    //do nothing
                }
            }
    }



    //TODO: Unfinished
    public boolean clickNonStop(){
        //<div class="uitk-switch uitk-checkbox"><input id="stops-0" data-test-id="stops-0" name="stops" type="checkbox" value="0"><span class="uitk-switch-control" aria-hidden="true"></span><div class="uitk-switch-content"><label class="uitk-checkbox-switch-label" for="stops-0"><div class="uitk-type-300" data-test-id="stops-0-label" aria-hidden="true">Nonstop (10)</div><span id="stops-0-label" class="is-visually-hidden">10 Nonstop flights from $511</span></label></div></div>
        List<WebElement> checkboxs = driver.findElements(By.className("uitk-checkbox"));
        Boolean nonstopAvailible = false;
        for (int i = 0; i < checkboxs.size(); i++) {
            List<WebElement> children = checkboxs.get(i).findElements(By.xpath("./child::*"));
            for (int j = 0; j < children.size(); j++) {
                //System.out.println(children.get(j).getAttribute("data-test-id"));
                try {
                    if (children.get(j).getAttribute("data-test-id").contains("stops-0")) {
                        children.get(j).click();
                        nonstopAvailible = true;
                    }
                }catch (Exception e){
                    //System.out.println("non-stop flight unavailable");
                }
            }
        }
        return nonstopAvailible;
    }


    public String  readFlight() {
        try{
            Thread.sleep(100);
        }catch (Exception e){
            System.out.println(e);
        }
        List<WebElement> parent = driver.findElements(By.className("is-visually-hidden"));
        List<String> flightStrings = new ArrayList<String>();
        for (int i = 0; i < parent.size(); i++) {
            try {
                List<WebElement> children = parent.get(i).findElements(By.xpath("./child::*"));
                for (int j = 0; j < children.size(); j++) {
                    flightStrings.add(children.get(j).getText());
                    //System.out.println(children.get(j).getText());
                }
            }catch (Exception e){
                System.out.println("weird exeption trying again");
            }
        }
        try {
            return flightStrings.get(0);
        }catch (IndexOutOfBoundsException e){
            return "broken system flight departing at 12:00pm for 99999999999";
        }
    }

    public void changeToReturnFlight() {
        List<WebElement> parent = driver.findElements(By.className("is-visually-hidden"));
        boolean found = false;
        for (int i = 0; i < parent.size()||found; i++) {
            try {
                List<WebElement> children = parent.get(i).findElements(By.xpath("./child::*"));
                for (int j = 0; j < children.size(); j++) {
                    String flightstring = (children.get(j).getText());
                    if (flightstring.contains("flight departing")) {
                        parent.get(i).findElement(By.xpath("./..")).click();
                        found = true;
                    }

                }
                if (found) {
                    break;
                }
            }catch (StaleElementReferenceException e){
                System.out.println("Weird exception trying again..");
            }
        }

        boolean looking = true;
        while(looking) {
            List<WebElement> buttons = driver.findElements(By.className("uitk-button-large"));
            //WebElement button = driver.findElement(By.className("uitk-button"));
            //System.out.println(button.getAttribute("class"));
            try {
                for (int i = 0; i < buttons.size(); i++) {
                    //System.out.println(i);
                   // System.out.println(buttons.get(i).getAttribute("class"));
                   // System.out.println(buttons.get(i).getText());
                    if (buttons.get(i).getAttribute("data-test-id").contains("select-button")) {
                        buttons.get(i).click();
                        //System.out.println("slectbuttonfound");
                        looking = false;
                    }
                }
            }catch (Exception e){
                //System.out.println("can't find button tyring agian");
                try {
                    Thread.sleep(500);
                }catch (Exception x){
                    //do nothing
                }
            }
        }

    }
    /*
    <button data-test-id="select-button" aria-label="Continue with Basic Economy for $511" type="button" class="uitk-button uitk-button-large uitk-button-has-text uitk-button-primary uitk-spacing uitk-spacing-margin-inlinestart-three">Continue</button>
     */

    public boolean areFLightsLoaded() {

        try{
            //System.out.println("Are flights loaded?");
            readFlight();
            return true;
        }catch (IndexOutOfBoundsException e){
            return false;
        }
    }

    public void waitForFlightLoad() {
        boolean waiting = true;
        while (waiting){
            if(!areFLightsLoaded()){
                try{
                    //System.out.println("waiting for fight to load!");
                    Thread.sleep(500);
                }catch (Exception e){
                    System.out.println("oof");
                }
            }else{
                waiting = false;
            }

        }

    }

    public String readReturnFlight() {

        try{
            Thread.sleep(100);
        }catch (Exception e){
            System.out.println(e);
        }
        List<WebElement> parent = driver.findElements(By.className("is-visually-hidden"));
        List<String> flightStrings = new ArrayList<String>();
        for (int i = 0; i < parent.size(); i++) {
            List<WebElement> children = parent.get(i).findElements(By.xpath("./child::*"));
            for (int j = 0; j < children.size(); j++) {
                flightStrings.add(children.get(j).getText());
            }
        }
        return flightStrings.get(0);
    }

    public void submitDate() {
        //<button data-stid="apply-date-picker" type="button" class="uitk-button uitk-button-large uitk-button-fullWidth uitk-button-has-text uitk-button-primary uitk-button-floating-full-width"><span>Done<span class="is-visually-hidden">Save changes and close the date picker.</span></span></button>
        List<WebElement> buttons = driver.findElements(By.className("uitk-button"));
        for (int i = 0; i < buttons.size(); i++) {
            try {
                if (buttons.get(i).getAttribute("data-stid").contains("apply-date-picker")) {
                    buttons.get(i).click();
                    //System.out.println("I did it :)");
                    break;
                }
            }catch (NullPointerException e){
                //System.out.println("Not sure why I was hit???");
            }
        }
    }

    public String plus1(String departing) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
        Calendar c = Calendar.getInstance();
        String nDate = "";

        String split[] = departing.split(" ");
        nDate += split[2].substring(0,4) +"-";
        nDate += monthFormat(split[0]) + "-";
        nDate += dayFormat(split[1].substring(0,split[1].indexOf(',')));
        //System.out.println(nDate);
        try{
            c.setTime(sdf.parse(nDate));
        }catch (Exception e){
            System.out.println("das tough");
        }
        c.add(Calendar.DAY_OF_MONTH,1);
        nDate = sdf.format(c.getTime());
        String splitA[] = nDate.split("-");
        //Jun 17, 2021
        String fDate = "";
        fDate += monthFormatReverse(splitA[1])+" ";
        fDate += dayFormatReverse(splitA[2])+", ";
        fDate += splitA[0];


        //System.out.println(fDate);
        return fDate;
    }

    public void clickPopUp() {
        //<button type="button" class="uitk-button uitk-button-medium uitk-button-has-text uitk-button-tertiary uitk-dialog-footer-button">Refresh search</button>
        List<WebElement> button = driver.findElements(By.className("uitk-button-tertiary"));
        for (int i = 0; i < button.size(); i++) {
            if(button.get(i).getText().contains("Refresh")){
                System.out.println("clicked refresh");
                button.get(i).click();
            }

        }
    }



    /*
    <button type="button" class="uitk-date-picker-day uitk-new-date-picker-day" data-day="3" aria-label="May 3, 2021."></button>
     */
}
