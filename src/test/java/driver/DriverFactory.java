package driver;

import constants.ExecutionEnvironment;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import utils.ConfigManager;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {

    private DriverFactory() {
    }

    public static AppiumDriver createDriver(
            String environment,
            String platform
    ) throws MalformedURLException {

        ExecutionEnvironment executionEnvironment =
                ExecutionEnvironment.valueOf(
                        environment.trim().toUpperCase()
                );

        String platformName = platform.trim().toUpperCase();

        if (executionEnvironment == ExecutionEnvironment.LOCAL) {
            return createLocalAndroidDriver();
        }

        if (executionEnvironment == ExecutionEnvironment.BROWSERSTACK) {

            return switch (platformName) {
                case "ANDROID" -> createBrowserStackAndroidDriver();
                case "IOS" -> createBrowserStackIOSDriver();
                default -> throw new IllegalArgumentException(
                        "Unsupported platform: " + platform
                );
            };
        }

        throw new IllegalArgumentException(
                "Unsupported execution environment: " + environment
        );
    }

    private static AppiumDriver createLocalAndroidDriver()
            throws MalformedURLException {

        UiAutomator2Options options = new UiAutomator2Options();

        options.setPlatformName(
                ConfigManager.getInstance().get("platform.name")
        );

        options.setAutomationName(
                ConfigManager.getInstance().get("automation.name")
        );

        options.setDeviceName(
                ConfigManager.getInstance().get("device.name")
        );

        options.setAppPackage(
                ConfigManager.getInstance().get("app.package")
        );

        options.setAppActivity(
                ConfigManager.getInstance().get("app.activity")
        );

        return new AndroidDriver(
                new URL(
                        ConfigManager.getInstance()
                                .get("appium.server.url")
                ),
                options
        );
    }

    private static AppiumDriver createBrowserStackAndroidDriver()
            throws MalformedURLException {

        UiAutomator2Options options = new UiAutomator2Options();

        options.setPlatformName("Android");
        options.setAutomationName("UiAutomator2");

        options.setDeviceName(
                ConfigManager.getInstance()
                        .getBrowserStack("browserstack.device")
        );

        options.setPlatformVersion(
                ConfigManager.getInstance()
                        .getBrowserStack("browserstack.platform.version")
        );

        options.setCapability(
                "app",
                ConfigManager.getInstance()
                        .getBrowserStack("browserstack.app")
        );

        options.setCapability(
                "bstack:options",
                java.util.Map.of(
                        "userName",
                        System.getenv("BROWSERSTACK_USERNAME"),
                        "accessKey",
                        System.getenv("BROWSERSTACK_ACCESS_KEY"),
                        "projectName",
                        "Appium POC",
                        "buildName",
                        "BrowserStack Android Build",
                        "sessionName",
                        "Android Wikipedia Test"
                )
        );

        return new AndroidDriver(
                new URL(
                        ConfigManager.getInstance()
                                .getBrowserStack("browserstack_url")
                ),
                options
        );
    }
    private static AppiumDriver createBrowserStackIOSDriver()
            throws MalformedURLException {

        XCUITestOptions options = new XCUITestOptions();

        options.setPlatformName("iOS");
        options.setAutomationName("XCUITest");

        options.setDeviceName(
                ConfigManager.getInstance()
                        .getBrowserStack("browserstack.ios.device")
        );

        options.setPlatformVersion(
                ConfigManager.getInstance()
                        .getBrowserStack("browserstack.ios.platform.version")
        );

        options.setCapability(
                "app",
                ConfigManager.getInstance()
                        .getBrowserStack("browserstack.ios.app")
        );

        options.setCapability(
                "bstack:options",
                java.util.Map.of(
                        "userName",
                        System.getenv("BROWSERSTACK_USERNAME"),
                        "accessKey",
                        System.getenv("BROWSERSTACK_ACCESS_KEY"),
                        "projectName",
                        "Appium POC",
                        "buildName",
                        "BrowserStack iOS Build",
                        "sessionName",
                        "iOS Sample Test"
                )
        );

        return new IOSDriver(
                new URL(
                        ConfigManager.getInstance()
                                .getBrowserStack("browserstack_url")
                ),
                options
        );
    }
}