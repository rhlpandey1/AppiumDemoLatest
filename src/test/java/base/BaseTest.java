package base;

import constants.FrameworkConstants;
import driver.DriverFactory;
import driver.DriverManager;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import pages.PageObjectManager;

import java.net.MalformedURLException;
import java.time.Duration;

public class BaseTest {
    protected WebDriverWait wait;
    protected PageObjectManager pageObjectManager;
    @BeforeMethod
    @Parameters({"environment","platform"})
    public void setUp(String environment,String platform) throws MalformedURLException {
        DriverManager.setDriver(DriverFactory.createDriver(environment,platform));
        wait=new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(FrameworkConstants.EXPLICIT_WAIT));
        pageObjectManager=new PageObjectManager(DriverManager.getDriver(),wait);
    }
    @AfterMethod
    public void tearDown()
    {
        DriverManager.getDriver().quit();
    }

}
