package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SettingsPage;

public class AndroidTest extends BaseTest {
    @Test
    public void androidSettingsTest() {

        SettingsPage settingsPage = pageObjectManager.getSettingsPage();

        settingsPage.clickConnections();

        Assert.assertTrue(
                settingsPage.isWifiDisplayed("ui")
        );

        Assert.assertTrue(
                settingsPage.isWifiDisplayed("xpath")
        );
    }



}
