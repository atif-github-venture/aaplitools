package web;

import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.selenium.Eyes;
import constants.GlobalVariables;
import org.openqa.selenium.WebDriver;

public class AppliTools {
    private Eyes eyes = new Eyes();

    public AppliTools() {
        eyes.setApiKey(GlobalVariables.appliToolsKey);
//        eyes.setHostOS("Mac 10.4");
//        eyes.setHostApp("Chrome");
        eyes.setHostOS("Android Mobile");
        eyes.setHostApp("Chrome");
    }

    public void runAppliTest(WebDriver driver, String url, String testName) {
        try {
            eyes.open(driver, "Fabric", testName, new RectangleSize(GlobalVariables.width, GlobalVariables.height));
            driver.get(GlobalVariables.baseFabricURL + url);
            eyes.setForceFullPageScreenshot(true);
            eyes.checkWindow("Product mobile visual tests");
            eyes.close();
        } finally {
            eyes.abortIfNotClosed();
        }
    }

    public void runAppliTestMobile(WebDriver driver, String url, String testName) {
        try {
            eyes.open(driver, "Fabric", testName);
            driver.get(GlobalVariables.baseFabricURL + url);
            eyes.setForceFullPageScreenshot(true);
            eyes.checkWindow("Product mobile visual tests-1");
            eyes.close();
        } finally {
            driver.quit();
            eyes.abortIfNotClosed();
        }
    }
}
