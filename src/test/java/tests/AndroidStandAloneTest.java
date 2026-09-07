package tests;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class AndroidStandAloneTest {
    private AndroidDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setUp() throws MalformedURLException {
        UiAutomator2Options options=new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setAutomationName("UiAutomator2");
        options.setDeviceName("RZCW11AC09N");
        options.setAppPackage("com.android.settings");
        options.setAppActivity(".Settings");
        driver=new AndroidDriver(new URL("http://127.0.0.1:4723"),options);
        wait=new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void androidSettingsTest() {

        WebElement connections=wait.until(ExpectedConditions.elementToBeClickable
                (AppiumBy.androidUIAutomator("new UiSelector().text(\"Connections\")")));
        connections.click();
        Assert.assertTrue(connections.isDisplayed());
        WebElement wiFi=wait.until(ExpectedConditions.elementToBeClickable
                (AppiumBy.androidUIAutomator("new UiSelector().text(\"Wi-Fi\")")));
        Assert.assertTrue(wiFi.isDisplayed());
    }
    @Test
    public void androidSettingsTestUsingResourceIds() {

        WebElement connections=wait.until(ExpectedConditions.elementToBeClickable
                (AppiumBy.androidUIAutomator("new UiSelector().text(\"Connections\")")));
        connections.click();
        Assert.assertTrue(connections.isDisplayed());
       /* WebElement wiFi=wait.until(ExpectedConditions.elementToBeClickable
                (AppiumBy.id("/android:id/title/")));*/
        WebElement wiFi=wait.until(ExpectedConditions.elementToBeClickable
                (AppiumBy.xpath("//android.widget.TextView[@resource-id=\"android:id/title\" and @text=\"Wi-Fi\"]")));
        Assert.assertTrue(wiFi.isDisplayed());
    }
    @AfterMethod
    public void tearDown()
    {
        driver.quit();
    }

}
