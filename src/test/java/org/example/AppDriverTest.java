package org.example;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AppDriverTest {

    private static AppDriver driver;

    /**
     * SetupChrome creates a new "AppDriver" inturn creates a new chrome driver.
     */
    @BeforeClass
    public static void setUpChrome(){
        driver = new AppDriver();
    }

    @Test
    public void testGoogle(){
        driver.getWebsite("https://www.google.com");
        String str = driver.getDriver().getTitle();
        Assert.assertEquals(str,"Google");
    }

    /**
     * Make sure you can access expedia.com
     */
    @Test
    public void testExpedia(){
        driver.getWebsite("https://www.expedia.com");
        String str = driver.getDriver().getTitle();
        Assert.assertEquals(str,"Expedia Travel: Vacation Homes, Hotels, Car Rentals, Flights & More");
    }

    /**
     * This test changes the travel tab to flights
     */
    @Test
    public void testChangeTab(){
        driver.getWebsite(driver.getExpediaHome());
        driver.getDriver().findElement(By.xpath("//*[@id='uitk-tabs-button-container']/li[2]/a")).click();
        String roundTrip = driver.getDriver().
                findElement(By.xpath("//*[@id=\"uitk-tabs-button-container\"]/div/li[1]/a")).getText();

        Assert.assertEquals("Roundtrip",roundTrip);
    }

    /**
     * makes sure this test can get to the location on the page needed to run the code
     *
     */
    @Test
    public void testInitNav(){
        driver.initNav();

        String roundTrip = driver.getDriver().
                findElement(By.xpath("//*[@id=\"uitk-tabs-button-container\"]/div/li[1]/a")).getText();

        Assert.assertEquals("Roundtrip",roundTrip);
    }


    @Test
    public void testSetLoc1(){
        String loc = "atl";
        driver.setLoc1(loc);

        String loc1 = driver.getDriver()
                .findElement(By.xpath("//*[@id='wizard-flight-tab-roundtrip']/div[2]/div[1]/div/div[1]/div/div/div/button[1]"))
                .getAttribute("aria-label");
        Assert.assertEquals("Leaving from Atlanta (ATL - Hartsfield-Jackson Atlanta Intl.)",loc1);
    }

    /**
     *
     * //*[@id='wizard-flight-tab-roundtrip']/div[2]/div[1]/div/div[1]/div/div/div/button[1]
     */

    @Test
    public void testSetFrom(){

    }

    @Test
    public void testSetTo(){

    }

    @Test
    public void testDateSpan(){

    }

    @Test
    public void testSaveTrip(){

    }



    @AfterClass
    public static void cleanUp() throws Exception {
        Thread.sleep(30000);
        driver.cleanup();
    }

}
