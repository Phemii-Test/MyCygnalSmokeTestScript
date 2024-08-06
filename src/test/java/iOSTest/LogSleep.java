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

	private AppiumDriver driver;
	
	public LogSleep(AppiumDriver driver) {
		
		this.driver= driver;
	}
	private By sleepIcon = AppiumBy.accessibilityId("Sleep icon");
	private By newSleepButton = AppiumBy.accessibilityId("Add new sleep record");
	private By commonElement1 = By.className("XCUIElementTypeImage");
	private By changeClockView = AppiumBy.accessibilityId("Switch to text input mode");
	private By hourOption = AppiumBy.accessibilityId("Hour");
	private By minuteOption = AppiumBy.accessibilityId("Minute");
	private By am = AppiumBy.accessibilityId("AM");
	private By okButton = AppiumBy.accessibilityId("OK");
	private By recordSleep = By.xpath("//XCUIElementTypeButton[@name='Record Sleep']");
	private By checkMark = AppiumBy.accessibilityId("Success Icon");
	private By viewSleepRecord = AppiumBy.accessibilityId("View Sleep Tracker");
	private By commonElement2 = By.className("XCUIElementTypeButton");


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

		driver.findElement(sleepIcon).click();
	}

	@Test(priority = 2)
	public void addSleepTime() {

		driver.findElement(newSleepButton).click();
//	     Start Time
		driver.findElements(commonElement1).get(0).click();
		driver.findElement(changeClockView).click();
		WebElement hour = driver.findElement(hourOption);
		hour.clear();
		hour.sendKeys("12");

		WebElement minute = driver.findElement(minuteOption);
		minute.clear();
		minute.sendKeys("00");
		driver.findElement(am).click();

		driver.findElement(okButton).click();

//				End time
		driver.findElements(commonElement1).get(2).click();
		driver.findElement(changeClockView).click();
		WebElement hour1 = driver.findElement(hourOption);
		hour1.clear();
		hour1.sendKeys("6");

		WebElement minute1 = driver.findElement(minuteOption);
		minute1.clear();
		minute1.sendKeys("00");

		driver.findElement(am).click();

		driver.findElement(okButton).click();

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
		driver.findElement(recordSleep).click();
		Thread.sleep(5000);
	}

	@Test(priority = 5)
	public boolean validateSleepLog() {
         return driver.findElement(checkMark).isDisplayed();

	}

	@Test(priority = 6)
	public void backToHomescreen() {
		driver.findElement(viewSleepRecord).click();

		driver.findElements(commonElement2).get(1).click();

	}

}
