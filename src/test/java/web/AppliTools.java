package web;

import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.selenium.Eyes;
import constants.GlobalVariables;
import org.openqa.selenium.WebDriver;

public class AppliTools {
    Eyes eyes = new Eyes();

    public AppliTools() {
        eyes.setApiKey(GlobalVariables.appliToolsKey);
        eyes.setHostOS("Mac 10.13");
        eyes.setHostApp("Firefox");
    }

    public void runAppliTest(WebDriver driver, String url, String testName) {
        try {
            eyes.open(driver, "Fabric", testName, new RectangleSize(GlobalVariables.width, GlobalVariables.height));
            driver.get(GlobalVariables.baseFabricURL + url);
            eyes.setForceFullPageScreenshot(true);
            eyes.checkWindow("Order Status");
            eyes.close();
        } finally {
            eyes.abortIfNotClosed();
        }
    }
}
