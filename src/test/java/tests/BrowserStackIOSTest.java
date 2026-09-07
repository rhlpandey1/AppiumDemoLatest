package tests;

import base.BaseTest;
import driver.DriverManager;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BrowserStackIOSTest extends BaseTest {

    @Test
    public void iosSmokeTest() {

        Assert.assertNotNull(
                DriverManager.getDriver(),
                "iOS driver was not created"
        );
    }
}