package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SettingsPage {
    private final AppiumDriver driver;
    private final WebDriverWait wait;

    public SettingsPage(AppiumDriver driver,WebDriverWait wait) {
        this.driver=driver;
        this.wait = wait;
    }
    public enum LocatorType {
        UI,
        XPATH
    }
    private WebElement getConnections() {
        return wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.androidUIAutomator(
                                "new UiSelector().text(\"Connections\")"
                        )
                )
        );
    }

    private WebElement getWifi(LocatorType locatorType) {

        By locator;

        if (locatorType == LocatorType.UI) {

            locator = AppiumBy.androidUIAutomator(
                    "new UiSelector().text(\"Wi-Fi\")"
            );

        } else if (locatorType == LocatorType.XPATH) {

            locator = AppiumBy.xpath(
                    "//android.widget.TextView[@resource-id=\"android:id/title\" and @text=\"Wi-Fi\"]"
            );

        } else {
            throw new IllegalArgumentException(
                    "Unsupported locator type: " + locatorType
            );
        }

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        );
    }

    public void clickConnections() {
        getConnections().click();
    }

    public boolean isWifiDisplayed(String locatorType) {
        return getWifi(LocatorType.valueOf(locatorType.trim().toUpperCase())).isDisplayed();
    }
}