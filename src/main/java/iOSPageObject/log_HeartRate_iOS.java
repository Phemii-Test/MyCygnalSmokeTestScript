package iOSPageObject;

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
import io.appium.java_client.ios.IOSDriver;

public class log_HeartRate_iOS {
	private AppiumDriver driver;

	public log_HeartRate_iOS(AppiumDriver driver) {
		this.driver = driver;
	}

	private By HearRateIcon = AppiumBy.accessibilityId("Heart Rate icon");
	private By newLogButton = AppiumBy.accessibilityId("Add new heart rate record");
	private By commonElement1 = By.className("XCUIElementTypeImage");
	private By okButton = AppiumBy.accessibilityId("OK");
	private By onMedication = AppiumBy.accessibilityId("Yes, I'm on medication");
	private By recordHeartRateButton = By.xpath("//XCUIElementTypeButton[@name='Record Heart Rate']");
	private By checkMark = AppiumBy.accessibilityId("Success Icon");
	private By recordRelatedMedButton = AppiumBy.accessibilityId("Record Related Medication");
	private By viewHeartRateButton = AppiumBy.accessibilityId("View Heart Rate");
	private By backHomeButton = By.className("XCUIElementTypeButton");

	@Test(priority = 1)
	public void beginHeartRatelog() {
//	    	click heartrate icon
		driver.findElement(HearRateIcon).click();

		driver.findElement(newLogButton).click();
	}

//	    	Set heart rate value
	public void setHeartRateValue() {
		int startX = 289;
		int startY = 432;
		int endX = 208;
		int endY = 426;
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

		// Create the sequence of actions
		Sequence swipe = new Sequence(finger, 0)
				.addAction(
						finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), startX, startY))
				.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
				.addAction(
						finger.createPointerMove(Duration.ofMillis(5000), PointerInput.Origin.viewport(), endX, endY))
				.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

		// Perform the sequence
		driver.perform(Collections.singletonList(swipe));

	}

	public void scrollUp() throws InterruptedException {

//			Perform a scroll up gesture.
		int startsX = 144;
		int startsY = 570;
		int endsX = 176;
		int endsY = 142;

		PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger");

		Sequence swipe1 = new Sequence(finger1, 0)
				.addAction(finger1.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), startsX,
						startsY))
				.addAction(finger1.createPointerDown(PointerInput.MouseButton.MIDDLE.asArg())).addAction(finger1
						.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), endsX, endsY))
				.addAction(finger1.createPointerUp(PointerInput.MouseButton.MIDDLE.asArg()));

		driver.perform(Collections.singletonList(swipe1));
		Thread.sleep(2000);
	}

	public void setDateAndtime() {
//				select date
		driver.findElements(commonElement1).get(3).click();
		driver.findElement(okButton).click();

//				Set time.
		WebElement setTime = driver.findElements(commonElement1).get(5);
		setTime.click();
		driver.findElement(okButton).click();
	}

	public void recordHeartRate() {
//				Select the yes I'm on medication option

		driver.findElement(onMedication).click();

		driver.findElement(recordHeartRateButton).click();

	}

	public boolean verifyBPLog() {
		return driver.findElement(checkMark).isDisplayed();
	}

	public boolean relatedMedIsDisplayed() {
		return driver.findElement(recordRelatedMedButton).isDisplayed();
	}

	public void viewHeartRateDashboard() throws InterruptedException {
		driver.findElement(viewHeartRateButton).click();
		Thread.sleep(5000);

		driver.findElements(backHomeButton).get(1).click();

	}
}
