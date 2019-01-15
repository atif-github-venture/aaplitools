package scenarios;

import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.selenium.Eyes;
import constants.GlobalVariables;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import utilities.Common;
import utilities.HashMapHelper;
import utilities.YamlHelper;
import web.AppliTools;
import web.Driver;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;


public class VisualTestUsingApplitools {
    //private WebDriver driver;
    Common common = new Common();

//    @BeforeClass
//    public void openBrowser() {
//        driver = new Driver().initiateDriver();
//    }
//
//
//
//    @AfterClass
//    public void closeBrowser() {
//       new Driver().quitDriver(driver);
//    }

    @Test
    public void genericVisualTest() throws IOException {
        WebDriver driver = new Driver().initiateDriver();
        AppliTools appliTools = new AppliTools();
//        appliTools.runAppliTest(driver, common.readUrl("FATE-24972"), "FATE-24972"); //to read for specific FATE
        for(int i=0; i<readUrlFileSize(); i++){
            String[] details = common.readUrl(i);
            appliTools.runAppliTest(driver, details[0], details[1]);
        }
        new Driver().quitDriver(driver);
    }

    private int readUrlFileSize() throws FileNotFoundException {
        return common.readUrlFileSize();
    }
}