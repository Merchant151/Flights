package org.example;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
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

    @AfterClass
    public static void cleanUp() throws Exception {
        Thread.sleep(30000);
        driver.cleanup();
    }

}
