package listeners;

import driver.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class ScreenshotListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {

        try {
            File source = ((TakesScreenshot) DriverManager.getDriver())
                    .getScreenshotAs(OutputType.FILE);

            File destination = new File(
                    "target/screenshots/"
                            + result.getTestClass().getRealClass().getSimpleName()
                            + "_"
                            + result.getName()
                            + ".png"
            );

            destination.getParentFile().mkdirs();

            Files.copy(
                    source.toPath(),
                    destination.toPath(),
                    StandardCopyOption.REPLACE_EXISTING
            );

            System.out.println(
                    "Screenshot saved: " + destination.getAbsolutePath()
            );

        } catch (Exception e) {
            System.out.println(
                    "Failed to capture screenshot: " + e.getMessage()
            );
        }
    }
}