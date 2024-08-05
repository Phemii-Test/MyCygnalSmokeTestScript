package iOSTest;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import androidTest.SleepTrackerPageObjectFactory;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.PressesKey;
import io.appium.java_client.ios.IOSDriver;

public class e2eTestiOS {

	AppiumDriver driver;
	public static String averageHeartRate;
	public static String updatedAverageHeartRate;
	public static String initialaverageBP;
	public static String updatedAverageBP;
	private static String initialAverageSleep;
	private static String finalAverageSleep;

	@BeforeTest
	public void Setup() throws MalformedURLException {

		DesiredCapabilities cap = new DesiredCapabilities();

		cap.setCapability("platformName", "iOS");
		cap.setCapability("automationName", "XCUITest");
		cap.setCapability("udid", "293CB621-315F-4285-922D-9DD8D1F45AC5");
		cap.setCapability("bundleId", "com.mycygnal.mycygnal");
		cap.setCapability("deviceName", "iPhone 15");
		cap.setCapability("platformVersion", "17.5");
//	        cap.setCapability("usePreinstalledWDA", "true");
		cap.setCapability("app", "/Users/olufemiomeiza/Downloads/Runner-7.app");

		try {
			driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test(priority = 0)
	public void Login() throws InterruptedException {
		WebElement nextBtn = driver.findElement(By.className("XCUIElementTypeButton"));
		nextBtn.click();
		Thread.sleep(2000);

		driver.findElement(AppiumBy.accessibilityId("Next page button")).click();

		driver.findElement(AppiumBy.accessibilityId("Login")).click();

		WebElement emailField = driver.findElements(By.className("XCUIElementTypeTextField")).get(0);
		emailField.click();
		emailField.sendKeys("Ohlufehmii@gmail.com");

		WebElement passwordField = driver.findElements(By.className("XCUIElementTypeTextField")).get(2);
		passwordField.click();
		passwordField.sendKeys("Hbon@1234");

		driver.findElement(AppiumBy.accessibilityId("Login")).click();
	}

	@Test(priority = 1)
	public void logsymptoms() throws InterruptedException {
		driver.findElement(AppiumBy.accessibilityId("Select Actions Button")).click();
		driver.findElement(AppiumBy.accessibilityId("Log Procedure")).click();
//	    	procedure title
		driver.findElements(By.className("XCUIElementTypeTextField")).get(0).sendKeys("Ceaserial Session");

//	    	procedure description
		driver.findElements(By.className("XCUIElementTypeTextField")).get(2).sendKeys("Delivery through operation");

//	    	Health provider
		driver.findElements(By.className("XCUIElementTypeTextField")).get(4)
				.sendKeys("Rev Fr, Burke Memorial Hospital");

//	    	Procedure type
		driver.findElement(AppiumBy.accessibilityId("Select type")).click();
		driver.findElement(AppiumBy.accessibilityId("Procedure 2")).click();

//	    	procedure preparation
		driver.findElements(By.className("XCUIElementTypeTextField")).get(6).sendKeys("Ultrasound scan");
		driver.navigate().back();

//	    	post procedure care.
		driver.findElements(By.className("XCUIElementTypeTextField")).get(8).sendKeys("Post procedure dressing.");
		driver.findElement(AppiumBy.accessibilityId("Hide keyboard")).click();

//			Upload file
		driver.findElement(AppiumBy.accessibilityId("Select File")).click();
		Thread.sleep(5000);
		driver.findElements(By.className("XCUIElementTypeImage")).get(24).click();
		Thread.sleep(3000);
		driver.findElement(AppiumBy.accessibilityId("OK")).click();

//			Set date
		driver.findElements(By.className("XCUIElementTypeImage")).get(2).click();
		driver.findElement(AppiumBy.accessibilityId("OK")).click();

//			set time
		driver.findElements(By.className("XCUIElementTypeImage")).get(4).click();
		driver.findElement(AppiumBy.accessibilityId("OK")).click();

//			click the log procedure button
		driver.findElements(By.className("XCUIElementTypeButton")).get(3).click();

	}

	@Test(priority = 2)
	public void ValidateProcedureLog() {
//		 A checkmark icon is displayed.
		boolean successIcon = driver.findElement(AppiumBy.accessibilityId("Success Icon")).isDisplayed();
		Assert.assertTrue(successIcon);

//			Navigate to the dashboard
		driver.findElement(AppiumBy.accessibilityId("Go to Dashboard")).click();
	}

	@AfterTest
	public void quitApp() {
		driver.quit();
	}

}