package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

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
        //click website

        //typeintextbox

        //selectfirstresult
    }
}
