package pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WikipediaPage {

    private final AppiumDriver driver;
    private final WebDriverWait wait;

    public WikipediaPage(AppiumDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    private WebElement getWikipediaLogo() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//android.widget.ImageView")
                )
        );
    }

    public boolean isWikipediaDisplayed() {
        return getWikipediaLogo().isDisplayed();
    }
}