package tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import scenarios.DriverBase;
import utilities.Common;
import web.AppliTools;

public class VisualTest extends DriverBase {

	Common common = new Common();

	@Test(dataProvider = "hardCodedBrowsers")
	public void genericVisualTest(String browser, String version, String os, Method method) throws IOException {
		//create webdriver session
		this.createDriver(browser, version, os, method.getName());
		WebDriver driver = this.getWebDriver();
		AppliTools appliTools = new AppliTools();
		for (int i = 0; i < readUrlFileSize(); i++) {
			String[] details = common.readUrl(i);
			//appliTools.runAppliTest(driver, details[0], "Product mobile visual tests");
			appliTools.runAppliTestMobile(driver, details[0], "Product mobile visual tests");
		}
		//new Driver().quitDriver(driver);
	}



	private int readUrlFileSize() throws FileNotFoundException {
		return common.readUrlFileSize();
	}


}
