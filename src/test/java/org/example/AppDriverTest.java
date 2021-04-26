package org.example;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

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
    public void testSetLoc1() throws Exception{
       driver.initNav();
        String loc = "atl";
        System.out.println("Starting setloc1");
        //Thread.sleep(5000);
        driver.setLoc1(loc);

        String loc1 = driver.getDriver()
                .findElement(By.xpath("//*[@id='wizard-flight-tab-roundtrip']/div[2]/div[1]/div/div[1]/div/div/div/button[1]"))
                .getAttribute("aria-label");
        Assert.assertEquals("Leaving from Atlanta (ATL - Hartsfield-Jackson Atlanta Intl.)",loc1);
    }

    //this test might not be nessisary
    @Test
    public void howManyInClass() {
        driver.initNav();

        WebElement locBox = driver.getDriver().findElement(By.xpath("//*[@id=\"wizard-flight-tab-roundtrip\"]/div[2]/div[1]/div/div[1]/div/div/div"));
        String me = locBox.getAttribute("class");
        locBox.click();

        List<WebElement> tingys = driver.getDriver().findElements(By.className("uitk-field-input"));
        for (int i = 0; i < tingys.size(); i++) {
            System.out.println(i);
            try {
                System.out.println(tingys.get(i).getAttribute("placeholder"));
            }catch (Exception e){
                System.out.println("Didn't work this time");
            }
        }

    }

    @Test
    public void testGo2Loc1() throws Exception{
        driver.initNav();
        WebElement locBox = driver.getDriver().findElement(By.xpath("//*[@id=\"wizard-flight-tab-roundtrip\"]/div[2]/div[1]/div/div[1]/div/div/div"));
        String me = locBox.getAttribute("class");
        locBox.click();
        Thread.sleep(100);

        List<WebElement> plsfind = driver.getDriver().findElements(By.className("uitk-field-input"));
        plsfind.get(1).sendKeys("atl");

        //System.out.println( plsfind.getAttribute("placeholder"));

        /**
         * <div class="uitk-field has-no-visible-label has-placeholder">
         *     <label class="uitk-field-label is-visually-hidden">Leaving from</label>
         * <input data-stid="location-field-leg1-origin-dialog-input" autocapitalize="off" autocomplete="off" autocorrect="off" type="text" spellcheck="false"
         * class="uitk-field-input uitk-typeahead-input  "
         * placeholder="Where are you leaving from?" a
         * ria-required="false" aria-invalid="false" value="">
         * </div>
         *
         * <div class="uitk-typeahead" data-stid="location-field-leg1-origin">
         *     <div class="uitk-field has-floatedLabel-label has-icon has-no-placeholder">
         *         <label for="location-field-leg1-origin-input" class="uitk-field-label is-visually-hidden">
         *             Leaving from</label>
         *             <input data-stid="location-field-leg1-origin-input" name="" id="location-field-leg1-origin-input"
         *             autocapitalize="off" autocomplete="off" autocorrect="off" type="text" spellcheck="false"
         *             class="uitk-field-input
         *             uitk-typeahead-input is-hidden"
         *             placeholder=" " aria-required="false"
         *             aria-invalid="false" value="">
         *             <button aria-label="Leaving from" data-stid="location-field-leg1-origin-dialog-trigger"
         *             type="button" class="uitk-faux-input">
         *             </button>
         *             <span class="uitk-field-label" aria-hidden="true">Leaving from</span>
         *             <svg class="uitk-icon uitk-field-icon uitk-icon-medium" aria-hidden="true"
         *             viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"
         *             xmlns:xlink="http://www.w3.org/1999/xlink">
         *             <svg>
         *                 <path fill-rule="evenodd" d="M5 9a7 7 0 1114 0c0 5.25-7 13-7 13S5 14.25 5
         *                 9zm4.5 0a2.5 2.5 0 105 0 2.5 2.5 0 00-5 0z" clip-rule="evenodd">
         *     </path></svg></svg></div></div>
         */

        Assert.assertEquals("uitk-menu-typeahead-wrapper",me);
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
        Thread.sleep(100000);
        driver.cleanup();
    }

}
