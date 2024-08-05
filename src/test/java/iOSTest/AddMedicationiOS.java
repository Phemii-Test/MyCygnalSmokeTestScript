package iOSTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class AddMedicationiOS {

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

		driver.findElement(AppiumBy.accessibilityId("Next page button")).click();

		driver.findElement(AppiumBy.accessibilityId("Login")).click();
////	        
		WebElement emailField = driver.findElements(By.className("XCUIElementTypeTextField")).get(0);
		emailField.click();
		emailField.sendKeys("Ohlufehmii@gmail.com");

		WebElement passwordField = driver.findElements(By.className("XCUIElementTypeTextField")).get(2);
		passwordField.click();
		passwordField.sendKeys("Hbon@1234");

		driver.findElement(AppiumBy.accessibilityId("Login")).click();

		Thread.sleep(5000);
	}

	@Test(priority = 1)
	public void addMedication() throws InterruptedException {

		driver.findElement(AppiumBy.accessibilityId("Select Actions Button")).click();
		;
		driver.findElement(AppiumBy.accessibilityId("Add Medications")).click();

//			Medication name

		driver.findElements(By.className("XCUIElementTypeTextField")).get(0).sendKeys("Lesinopril");

//			add Dosage
		driver.findElements(By.className("XCUIElementTypeTextField")).get(2).sendKeys("2");

//          Set Frequency

		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		int startX = 22;
		int startY = 394;
		int endX = 129;
		int endY = 394;
		// Create the sequence of actions
		Sequence scroll = new Sequence(finger, 1)
				.addAction(
						finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), startX, startY))
				.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
				.addAction(
						finger.createPointerMove(Duration.ofMillis(5000), PointerInput.Origin.viewport(), endX, endY))
				.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

		// Perform the sequence
		driver.perform(Collections.singletonList(scroll));
		Thread.sleep(2000);
//			Select Category

		driver.findElement(AppiumBy.accessibilityId("Antipertensives")).click();

//		    select start date
		driver.findElements(By.className("XCUIElementTypeImage")).get(0).click();
		driver.findElement(AppiumBy.accessibilityId("OK")).click();

//			select end date
		driver.findElements(By.className("XCUIElementTypeImage")).get(2).click();
		driver.findElement(AppiumBy.accessibilityId("OK")).click();

//			click the add medication button
		driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Add Medication\"]")).click();

//			Verify that medication has been added.
		Thread.sleep(5000);
		boolean icon = driver.findElement(AppiumBy.accessibilityId("Success Icon")).isDisplayed();
		Assert.assertTrue(icon);

	}

	@Test(priority = 2)
	public void addFirstDoseTime() throws InterruptedException {
		driver.findElement(AppiumBy.accessibilityId("View Medication")).click();

//          Start Time
		driver.findElements(By.className("XCUIElementTypeImage")).get(0).click();
		driver.findElement(AppiumBy.accessibilityId("Switch to text input mode")).click();
		WebElement hour = driver.findElement(AppiumBy.accessibilityId("Hour"));
		hour.clear();
		hour.sendKeys("5");

		WebElement minute = driver.findElement(AppiumBy.accessibilityId("Minute"));
		minute.clear();
		minute.sendKeys("00");
		driver.findElement(AppiumBy.accessibilityId("AM")).click();

		driver.findElement(AppiumBy.accessibilityId("OK")).click();

//			End time
		driver.findElements(By.className("XCUIElementTypeImage")).get(2).click();
		driver.findElement(AppiumBy.accessibilityId("Switch to text input mode")).click();
		WebElement hour1 = driver.findElement(AppiumBy.accessibilityId("Hour"));
		hour1.clear();
		hour1.sendKeys("5");

		WebElement minute1 = driver.findElement(AppiumBy.accessibilityId("Minute"));
		minute1.clear();
		minute1.sendKeys("05");

		driver.findElement(AppiumBy.accessibilityId("AM")).click();

		driver.findElement(AppiumBy.accessibilityId("OK")).click();
	}

	@Test(priority = 3)
	public void addSecondDoseTime() throws InterruptedException {

//	          Start Time
		driver.findElements(By.className("XCUIElementTypeImage")).get(4).click();
		driver.findElement(AppiumBy.accessibilityId("Switch to text input mode")).click();
		WebElement hour = driver.findElement(AppiumBy.accessibilityId("Hour"));
		hour.clear();
		hour.sendKeys("10");

		WebElement minute = driver.findElement(AppiumBy.accessibilityId("Minute"));
		minute.clear();
		minute.sendKeys("00");
		driver.findElement(AppiumBy.accessibilityId("AM")).click();

		driver.findElement(AppiumBy.accessibilityId("OK")).click();

//				End time
		driver.findElements(By.className("XCUIElementTypeImage")).get(6).click();
		driver.findElement(AppiumBy.accessibilityId("Switch to text input mode")).click();
		WebElement hour1 = driver.findElement(AppiumBy.accessibilityId("Hour"));
		hour1.clear();
		hour1.sendKeys("10");

		WebElement minute1 = driver.findElement(AppiumBy.accessibilityId("Minute"));
		minute1.clear();
		minute1.sendKeys("05");

		driver.findElement(AppiumBy.accessibilityId("AM")).click();

		driver.findElement(AppiumBy.accessibilityId("OK")).click();

//				Perform a scroll upward.

		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		int startX = 188;
		int startY = 744;
		int endX = 193;
		int endY = 399;
		// Create the sequence of actions
		Sequence scroll = new Sequence(finger, 0)
				.addAction(
						finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), startX, startY))
				.addAction(finger.createPointerDown(PointerInput.MouseButton.MIDDLE.asArg()))
				.addAction(
						finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), endX, endY))
				.addAction(finger.createPointerUp(PointerInput.MouseButton.MIDDLE.asArg()));

		// Perform the sequence
		driver.perform(Collections.singletonList(scroll));
		Thread.sleep(5000);
	}

	@Test(priority = 4)
	public void addThirdDoseTime() throws InterruptedException {

//	   	          Start Time
		driver.findElements(By.className("XCUIElementTypeImage")).get(8).click();
		driver.findElement(AppiumBy.accessibilityId("Switch to text input mode")).click();
		WebElement hour = driver.findElement(AppiumBy.accessibilityId("Hour"));
		hour.clear();
		hour.sendKeys("3");

		WebElement minute = driver.findElement(AppiumBy.accessibilityId("Minute"));
		minute.clear();
		minute.sendKeys("00");
		driver.findElement(AppiumBy.accessibilityId("PM")).click();

		driver.findElement(AppiumBy.accessibilityId("OK")).click();

//	   				End time
		driver.findElements(By.className("XCUIElementTypeImage")).get(10).click();
		driver.findElement(AppiumBy.accessibilityId("Switch to text input mode")).click();
		WebElement hour1 = driver.findElement(AppiumBy.accessibilityId("Hour"));
		hour1.clear();
		hour1.sendKeys("3");

		WebElement minute1 = driver.findElement(AppiumBy.accessibilityId("Minute"));
		minute1.clear();
		minute1.sendKeys("05");

		driver.findElement(AppiumBy.accessibilityId("PM")).click();

		driver.findElement(AppiumBy.accessibilityId("OK")).click();
	}

	@Test(priority = 5)
	public void completeReminderSetup() throws InterruptedException {

//			Toggle alarm
		WebElement Alarm = driver.findElement(AppiumBy.accessibilityId("Toggle Alarm On"));
		Alarm.click();
//			Click set reminder button
		driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Set Reminder\"]")).click();
		Thread.sleep(5000);
	}

	@Test(priority = 5)
	public void ValidateReminderSet() {
		boolean successCheckMark = driver.findElement(AppiumBy.accessibilityId("Success Icon")).isDisplayed();
		Assert.assertTrue(successCheckMark);

	}

	@Test(priority = 5)
	public void gotoMedDashboard() {
//		Go to the medication management page
		driver.findElement(AppiumBy.accessibilityId("Go to Medications")).click();

//		Click the back button to get back to the dashboard.
		driver.findElement(By.xpath("//XCUIElementTypeButton")).click();
//	}
	}
}