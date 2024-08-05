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
import io.appium.java_client.android.AndroidDriver;

public class LogSleep {

	AppiumDriver driver;

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
		cap.setCapability("app", "/Users/olufemiomeiza/Downloads/Runner-6.app");

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
	public void startLoggingSleep() throws InterruptedException {
		int startX = 343;
		int startY = 602;
		int endX = 41;
		int endY = 601;
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

		// Create the sequence of actions
		Sequence swipe = new Sequence(finger, 0)
				.addAction(
						finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), startX, startY))
				.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
				.addAction(
						finger.createPointerMove(Duration.ofMillis(2000), PointerInput.Origin.viewport(), endX, endY))
				.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

		// Perform the sequence
		driver.perform(Collections.singletonList(swipe));
		Thread.sleep(5000);

		driver.findElement(AppiumBy.accessibilityId("Sleep icon")).click();
	}

	@Test(priority = 2)
	public void addSleepTime() {

		driver.findElement(AppiumBy.accessibilityId("Add new sleep record")).click();
//	     Start Time
		driver.findElements(By.className("XCUIElementTypeImage")).get(0).click();
		driver.findElement(AppiumBy.accessibilityId("Switch to text input mode")).click();
		WebElement hour = driver.findElement(AppiumBy.accessibilityId("Hour"));
		hour.clear();
		hour.sendKeys("12");

		WebElement minute = driver.findElement(AppiumBy.accessibilityId("Minute"));
		minute.clear();
		minute.sendKeys("00");
		driver.findElement(AppiumBy.accessibilityId("AM")).click();

		driver.findElement(AppiumBy.accessibilityId("OK")).click();

//				End time
		driver.findElements(By.className("XCUIElementTypeImage")).get(2).click();
		driver.findElement(AppiumBy.accessibilityId("Switch to text input mode")).click();
		WebElement hour1 = driver.findElement(AppiumBy.accessibilityId("Hour"));
		hour1.clear();
		hour1.sendKeys("6");

		WebElement minute1 = driver.findElement(AppiumBy.accessibilityId("Minute"));
		minute1.clear();
		minute1.sendKeys("00");

		driver.findElement(AppiumBy.accessibilityId("AM")).click();

		driver.findElement(AppiumBy.accessibilityId("OK")).click();

	}

	@Test(priority = 3)
	public void sleepQuality() {
		int startX = 23;
		int startY = 384;
		int endX = 163;
		int endY = 384;
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

		// Create the sequence of actions
		Sequence swipe = new Sequence(finger, 0)
				.addAction(
						finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), startX, startY))
				.addAction(finger.createPointerDown(PointerInput.MouseButton.RIGHT.asArg()))
				.addAction(
						finger.createPointerMove(Duration.ofMillis(5000), PointerInput.Origin.viewport(), endX, endY))
				.addAction(finger.createPointerUp(PointerInput.MouseButton.RIGHT.asArg()));

		// Perform the sequence
		driver.perform(Collections.singletonList(swipe));

	}

	@Test(priority = 4)
	public void recordSleep() throws InterruptedException {
		driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Record Sleep\"]")).click();
		Thread.sleep(5000);
	}

	@Test(priority = 5)
	public void validateSleepLog() {

		boolean checkMark = driver.findElement(AppiumBy.accessibilityId("Success Icon")).isDisplayed();
		Assert.assertTrue(checkMark);
		System.out.println("sleep logged succesfuly;" + checkMark);

		driver.findElement(AppiumBy.accessibilityId("View Sleep Tracker")).click();
	}

	@Test(priority = 6)
	public void backToHomescreen() {
		driver.findElements(By.className("XCUIElementTypeButton")).get(1).click();

	}

}
