package web;

import constants.GlobalVariables;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Driver {
    public WebDriver initiateDriver() {
        System.setProperty("webdriver.gecko.driver", GlobalVariables.geckoDriverPath);
        return new FirefoxDriver();
    }

    public void quitDriver(WebDriver driver) {
        driver.quit();
    }
}
