package pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageObjectManager {
    private final AppiumDriver driver;
    private final WebDriverWait wait;
    private SettingsPage settingsPage;
    private WikipediaPage wikipediaPage;

    public PageObjectManager(AppiumDriver driver,WebDriverWait wait) {
        this.driver=driver;
        this.wait = wait;
    }
    public SettingsPage getSettingsPage(){
        if(settingsPage==null){
            settingsPage=new SettingsPage(driver,wait);
        }
        return settingsPage;
    }
    public WikipediaPage getWikipediaPage(){
        if(wikipediaPage==null){
            wikipediaPage=new WikipediaPage(driver,wait);
        }
        return wikipediaPage;
    }
}
