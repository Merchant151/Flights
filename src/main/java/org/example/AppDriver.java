package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

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
    public void setLoc1(String airPortCode) throws Exception{
        //click website loc1 box
        //System.out.println("searching for loc1");
        WebElement locBox = driver.findElement(By.xpath("//*[@id=\"wizard-flight-tab-roundtrip\"]/div[2]/div[1]/div/div[1]/div/div/div"));

        /*
        System.out.println("Debug Print test: ");
        System.out.println(locBox.getAttribute("class"));
        System.out.println("Debug print test complete!");

        System.out.println("trying to click");
        */
        locBox.click();
        //new Actions(driver).moveToElement(driver.findElement(By.xpath("//*[@id=\"wizard-flight-tab-roundtrip\"]/div[2]/div[1]/div/div[1]/div/div/div"))).click().perform();

        System.out.println("moved and clicked");

        //typeintextbox
        // the next three lines are ment to allow the webpage to load but it dosn't work.......
        //WebElement element;
        //WebDriverWait wait = new WebDriverWait(driver, 100);
        //element= wait.until(ExpectedConditions.elementToBeClickable(By.id("undefined-close")));
        Thread.sleep(100);
        System.out.println("recommended webdriver wait");
        List<WebElement> inBox = driver.findElements(By.className("uitk-field-input"));
        System.out.println("found box");
        //                                              //*[@id='app-layer-location-field-leg1-origin-ta-dialog']/div[2]
        //                                              //*[@id="app-layer-location-field-leg1-origin-ta-dialog"]/div[2]/div/div[1]/section/div/input
        inBox.get(1).sendKeys(airPortCode);
        //inBox.get(1).submit();

        //selectfirstresult
        List<WebElement> firstOption = driver.findElements(By.className("uitk-button"));
        firstOption.get(10).click();
    }

    /**
    * //*[@id="wizard-flight-tab-roundtrip"]/div[2]/div[1]/div/div[1]/div/div/div/button
    */
}
