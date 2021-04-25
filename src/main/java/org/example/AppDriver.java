package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AppDriver {

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
}
