package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.WikipediaPage;

public class BrowserStackAndroidTest extends BaseTest {

    @Test
    public void wikipediaAppTest() {

        WikipediaPage wikipediaPage =
                pageObjectManager.getWikipediaPage();

        Assert.assertTrue(
                wikipediaPage.isWikipediaDisplayed()
        );
    }
}