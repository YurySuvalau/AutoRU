package tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import utils.appium.AppiumServerJava;
import utils.driver.AndroidDriverManager;
import utils.driver.DriverManager;

public class AutoRuTest {
    AppiumDriver driver;
    AppiumDriverLocalService appiumServer;

    @Test
    public void searchTest() {
        DriverManager driverManager = new AndroidDriverManager();
        appiumServer = AppiumServerJava.startServer();
        driver = driverManager.getDriver(appiumServer, "New_Device_1_API_30", "Android");
        driver.findElementByXPath("//*[@text='Марка и модель']").click();
        driver.findElementByXPath("//*[@text='BMW']").click();
        driver.findElementByXPath("//*[@text='3 серии']/ancestor::*[@class='android.widget.RelativeLayout']/android.widget.CheckBox").click();
        driver.findElementById("ru.auto.ara:id/tvText").click();
        driver.findElementById("ru.auto.ara:id/image").click();
        String titleText = driver.findElementById("ru.auto.ara:id/name").getText();
        boolean isTextPresent = titleText.contains("BMW 3 серии");
        Assert.assertTrue(isTextPresent);
    }

    @AfterMethod(alwaysRun = true)
    public void stopServer() {
        if (driver != null) {
            driver.quit();
        }
        appiumServer.stop();
    }
}