package scenarios;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;

public class DriverBase {


	private ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();

	private ThreadLocal<String> sessionId = new ThreadLocal<>();
	private DesiredCapabilities capabilities = new DesiredCapabilities();

	@DataProvider(name = "hardCodedBrowsers", parallel = true)
	public static Object[][] sauceBrowserDataProvider(Method testMethod) {
		return new Object[][] {
				//new Object[] { "chrome", "71", "OS X 10.14" },
				new Object[] { "Chrome", "8.0", "Android" }
		};
	}


	protected WebDriver getWebDriver() {
		return webDriver.get();
	}

	public String getSessionId() {
		return sessionId.get();
	}

	protected void createDriver(String browser, String version, String os, String methodName)
			throws MalformedURLException {
		mobileCaps(browser,version,methodName,os);
		//desktopCaps(browser, version, os, methodName);
		webDriver.set(new RemoteWebDriver(
				new URL("https://user:key@ondemand.saucelabs.com:443/wd/hub"),
				capabilities));
		String id = ((RemoteWebDriver) getWebDriver()).getSessionId().toString();
		sessionId.set(id);
	}

	private void desktopCaps(String browser, String version, String os, String methodName) {
		capabilities.setCapability(CapabilityType.BROWSER_NAME, browser);
		capabilities.setCapability(CapabilityType.VERSION, version);
		capabilities.setCapability(CapabilityType.PLATFORM, os);
		capabilities.setCapability("tunnelIdentifier", "tunnelPool");
		capabilities.setCapability("name", methodName);
	}

	private void mobileCaps(String browser, String version, String methodName, String os) {
		capabilities.setCapability("name", methodName);
		capabilities.setCapability("tunnelIdentifier", "tunnelPool");
		capabilities.setCapability("appiumVersion", "1.9.1");
		capabilities.setCapability("deviceName","Android GoogleAPI Emulator");
		capabilities.setCapability("deviceOrientation", "portrait");
		capabilities.setCapability("browserName", "Chrome");
		capabilities.setCapability("platformVersion", "8.0");
		capabilities.setCapability("platformName","Android");
	}
//
//	@AfterMethod
//	public void tearDown(ITestResult result) {
//		((JavascriptExecutor) webDriver.get())
//				.executeScript("sauce:job-result=" + (result.isSuccess() ? "passed" : "failed"));
//		webDriver.get().quit();
//	}

	protected void annotate(String text) {
		((JavascriptExecutor) webDriver.get()).executeScript("sauce:context=" + text);
	}

}
