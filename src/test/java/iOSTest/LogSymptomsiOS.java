package iOSTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.HidesKeyboard;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.PressesKey;
import io.appium.java_client.ios.IOSDriver;

public class LogSymptomsiOS {

	IOSDriver driver;

	@BeforeTest
	public void Setup() throws MalformedURLException {

		DesiredCapabilities cap = new DesiredCapabilities();

		cap.setCapability("platformName", "iOS");
		cap.setCapability("automationName", "XCUITest");
		cap.setCapability("udid", "293CB621-315F-4285-922D-9DD8D1F45AC5");
		cap.setCapability("bundleId", "com.mycygnal.mycygnal");
		cap.setCapability("deviceName", "iPad (10th generation)");
		cap.setCapability("platformVersion", "17.5");
//	        cap.setCapability("usePreinstalledWDA", "true");
		cap.setCapability("app", "/Users/olufemiomeiza/Downloads/Runner-6.app");

		try {
			driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
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

		WebElement nextBtn1 = driver.findElement(By.className("XCUIElementTypeButton"));
		nextBtn1.click();

		driver.findElement(AppiumBy.accessibilityId("Login")).click();

		WebElement emailField = driver.findElements(By.className("XCUIElementTypeTextField")).get(0);
		emailField.click();
		emailField.sendKeys("Ohlufehmii@gmail.com");

		WebElement passwordField = driver.findElements(By.className("XCUIElementTypeTextField")).get(2);
		passwordField.click();
		passwordField.sendKeys("Hbon@1234");

		driver.findElement(AppiumBy.accessibilityId("Login")).click();

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	@Test(priority = 1)
	public void logsymptoms() throws InterruptedException {
		driver.findElement(AppiumBy.accessibilityId("Select Actions Button")).click();
		driver.findElement(AppiumBy.accessibilityId("Log Symptoms")).click();

//	    	Enter Symptom name
		driver.findElements(By.className("XCUIElementTypeTextField")).get(2).sendKeys("Headache");
	}

	@Test(priority = 2)
	public void symptomsSeverity() {
		int startX = 21;
		int startY = 302;
		int endX = 125;
		int endY = 305;
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

		// Create the sequence of actions
		Sequence swipe = new Sequence(finger, 1)
				.addAction(
						finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), startX, startY))
				.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
				.addAction(
						finger.createPointerMove(Duration.ofMillis(5000), PointerInput.Origin.viewport(), endX, endY))
				.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

		// Perform the sequence
		driver.perform(Collections.singletonList(swipe));
	}

	@Test(priority = 3)
	public void symptomsDescription() {
//	    	Symptoms description
		driver.findElements(By.className("XCUIElementTypeTextField")).get(4).sendKeys("Slight headache");
		driver.findElement(AppiumBy.accessibilityId("Return")).click();
	}

	@Test(priority = 4)
	public void Frequency() {
//	    	Set frequency
		driver.findElement(AppiumBy.accessibilityId("Select Frequency")).click();
		driver.findElement(AppiumBy.accessibilityId("Daily")).click();
	}

	@Test(priority = 5)
	public void Triggers() {
//	    	Set triggers
		driver.findElements(By.className("XCUIElementTypeTextField")).get(0).sendKeys("stress");
		driver.findElement(By.xpath("(//XCUIElementTypeButton[@name=\"Add\"])[1]")).click();
//			driver.navigate().back();
	}

	@Test(priority = 6)
	public void alleviatingFactors() throws InterruptedException {
//	    	Set Alleviating factors
		driver.findElements(By.className("XCUIElementTypeTextField")).get(1).sendKeys("Paracetamol");
		driver.findElement(By.xpath("(//XCUIElementTypeButton[@name=\"Add\"])[2]")).click();

	}

	@Test(priority = 7)
	public void setDateAndTime() throws InterruptedException {
//	    	Set date
		driver.findElements(By.className("XCUIElementTypeImage")).get(3).click();
		driver.findElement(AppiumBy.accessibilityId("OK")).click();

//	    	Set time
		driver.findElements(By.className("XCUIElementTypeImage")).get(5).click();
		driver.findElement(AppiumBy.accessibilityId("Switch to text input mode")).click();
		WebElement hour = driver.findElement(AppiumBy.accessibilityId("Hour"));
		hour.clear();
		hour.sendKeys("6");
		WebElement Minute = driver.findElement(AppiumBy.accessibilityId("Minute"));
		Minute.clear();
		Minute.sendKeys("16");
		driver.findElement(AppiumBy.accessibilityId("OK")).click();

		driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Log Symptoms\"]")).click();

		Thread.sleep(5000);
	}

	@Test(priority = 8)
	public void ValidateProcedureLog() {
		String actualText = driver.findElement(AppiumBy.accessibilityId("Symptom Logged")).getText();
		String expectedText = "Symptom Logged";
		Assert.assertEquals(actualText, expectedText);

//			Navigate to the dashboard
		driver.findElement(AppiumBy.accessibilityId("Go to Dashboard")).click();
	}

}
