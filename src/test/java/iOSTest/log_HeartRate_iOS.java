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
import io.appium.java_client.ios.IOSDriver;

public class log_HeartRate_iOS {
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
	public void logHeartRate() throws InterruptedException {
//	    	click heartrate icon
		driver.findElement(AppiumBy.accessibilityId("Heart Rate icon")).click();

//	    	click addNew Heartrate button
		driver.findElement(AppiumBy.accessibilityId("Add new heart rate record")).click();

//	    	Set heart rate value
		Thread.sleep(5000);

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

//			Perform a scroll up gesture.
		int startsX = 144;
		int startsY = 570;
		int endsX = 176;
		int endsY = 142;

		PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger");

		Sequence swipe1 = new Sequence(finger, 0)
				.addAction(finger1.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), startsX,
						startsY))
				.addAction(finger1.createPointerDown(PointerInput.MouseButton.MIDDLE.asArg())).addAction(finger1
						.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), endsX, endsY))
				.addAction(finger1.createPointerUp(PointerInput.MouseButton.MIDDLE.asArg()));

		driver.perform(Collections.singletonList(swipe1));
		Thread.sleep(5000);

//				select date
		driver.findElements(By.className("XCUIElementTypeImage")).get(3).click();
		driver.findElement(AppiumBy.accessibilityId("OK")).click();

//				Set time.
		WebElement setTime = driver.findElements(By.className("XCUIElementTypeImage")).get(5);
		setTime.click();
		driver.findElement(AppiumBy.accessibilityId("OK")).click();

//				Select the yes I'm on medication option

		driver.findElement(AppiumBy.accessibilityId("Yes, I'm on medication")).click();

		driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Record Heart Rate\"]")).click();

	}

	@Test(priority = 2)
	public void verifyBPLog() throws InterruptedException {
//			A checkmark icon is displayed.
		boolean successIcon = driver.findElement(AppiumBy.accessibilityId("Success Icon")).isDisplayed();
		Assert.assertTrue(successIcon);
		// Validate that the add related medication is displayed since I specified I'm
		// on medication
		boolean RecordMedBtn = driver.findElement(AppiumBy.accessibilityId("Record Related Medication")).isDisplayed();
		Assert.assertTrue(RecordMedBtn);

//			Click view BP button
		driver.findElement(AppiumBy.accessibilityId("View Heart Rate")).click();
		Thread.sleep(5000);

		driver.findElements(By.className("XCUIElementTypeButton")).get(1).click();

	}
}
