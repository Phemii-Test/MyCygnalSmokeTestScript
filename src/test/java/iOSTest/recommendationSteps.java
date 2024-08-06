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

public class recommendationSteps {

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

		
	    Thread.sleep(5000);
		

	}
	@Test(priority=1)
	public void initiateRecommendation() {
		driver.findElement(AppiumBy.accessibilityId("Recommendation icon")).click();
		driver.findElement(AppiumBy.accessibilityId("Go to Assessment")).click();

	}
	@Test(priority=2)
	public void accessmentQuestion1() {
//		Select No, I don't smoke
		driver.findElements(By.className("XCUIElementTypeSwitch")).get(1).click();
		driver.findElement(AppiumBy.accessibilityId("Continue")).click();

	}
	@Test(priority=3)
	public void accessmentQuestion2() {
//		Select yes, I smoke
		driver.findElements(By.className("XCUIElementTypeSwitch")).get(0).click();
		driver.findElement(AppiumBy.accessibilityId("Continue")).click();

	}
	
	@Test(priority=4)
	public void accessmentQuestion3() throws InterruptedException {
//		for how many years did you smoked before you quit.
		int startX = 198;
		int startY = 547;
		int endX = 198;
		int endY = 353;
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

		// Create the sequence of actions
		Sequence swipe = new Sequence(finger, 0)
				.addAction(
						finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), startX, startY))
				.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
				.addAction(
						finger.createPointerMove(Duration.ofMillis(3000), PointerInput.Origin.viewport(), endX, endY))
				.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

		// Perform the sequence
		driver.perform(Collections.singletonList(swipe));
		Thread.sleep(5000);

	}
	@Test(priority=5)
	public void accessmentQuestion4() throws InterruptedException {
//		for how many pack of cigrette?.
		int startX = 366;
		int startY = 762;
		int endX = 161;
		int endY = 759;
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

		// Create the sequence of actions
		Sequence swipe = new Sequence(finger, 0)
				.addAction(
						finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), startX, startY))
				.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
				.addAction(
						finger.createPointerMove(Duration.ofMillis(3000), PointerInput.Origin.viewport(), endX, endY))
				.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

		// Perform the sequence
		driver.perform(Collections.singletonList(swipe));

	}
	@Test(priority=6)
	public void scrollUpAndContinue() throws InterruptedException {
//		perform an upward scroll
		int startX = 171;
		int startY = 687;
		int endX = 152;
		int endY = 135;
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

		// Create the sequence of actions
		Sequence swipe = new Sequence(finger, 0)
				.addAction(
						finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), startX, startY))
				.addAction(finger.createPointerDown(PointerInput.MouseButton.MIDDLE.asArg()))
				.addAction(
						finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), endX, endY))
				.addAction(finger.createPointerUp(PointerInput.MouseButton.MIDDLE.asArg()));

		// Perform the sequence
		driver.perform(Collections.singletonList(swipe));
		Thread.sleep(5000);

		driver.findElement(AppiumBy.accessibilityId("Continue")).click();
	}
	@Test(priority=7)
	public void accessmentQuestion5() throws InterruptedException {
		driver.findElements(By.className("XCUIElementTypeSwitch")).get(1).click();
		driver.findElements(By.className("XCUIElementTypeSwitch")).get(2).click();
		driver.findElements(By.className("XCUIElementTypeSwitch")).get(4).click();
		
//		perform an upward scroll
		int startX = 117;
		int startY = 752;
		int endX = 166;
		int endY = 325;
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

		// Create the sequence of actions
		Sequence swipe = new Sequence(finger, 0)
				.addAction(
						finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), startX, startY))
				.addAction(finger.createPointerDown(PointerInput.MouseButton.MIDDLE.asArg()))
				.addAction(
						finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), endX, endY))
				.addAction(finger.createPointerUp(PointerInput.MouseButton.MIDDLE.asArg()));

		// Perform the sequence
		driver.perform(Collections.singletonList(swipe));
		Thread.sleep(5000);
		
		driver.findElement(AppiumBy.accessibilityId("Continue")).click();

	}
	
	@Test(priority=8)
	public void accessmentQuestion6() throws InterruptedException {
		driver.findElements(By.className("XCUIElementTypeSwitch")).get(0).click();
		driver.findElements(By.className("XCUIElementTypeSwitch")).get(1).click();
		driver.findElements(By.className("XCUIElementTypeSwitch")).get(2).click();
		
		driver.findElement(AppiumBy.accessibilityId("Submit")).click();
		Thread.sleep(5000);
		
		driver.findElement(AppiumBy.accessibilityId("See Recommendation")).click();

	}
	@Test(priority=9)
	public boolean influenzaIsDisplayed() throws InterruptedException {
		Thread.sleep(5000);

		return driver.findElement(AppiumBy.accessibilityId("Annual Influenza (Flu) Vaccine")).isDisplayed();
	}
	
	@Test(priority=10)
	public boolean HPVVaccineIsDisplayed() {
		return driver.findElement(AppiumBy.accessibilityId("HPV Vaccine")).isDisplayed();
	}
	
	@Test(priority=11)
	public boolean Covid19VaccineIsDisplayed() {
		return driver.findElement(AppiumBy.accessibilityId("Updated COVID-19 Vaccines (2023 / 2024)")).isDisplayed();
	}
	
	@Test(priority = 12)
	public void deleteAccount() throws InterruptedException {
//		click profile page button
		driver.findElement(AppiumBy.accessibilityId("Profile Page Navigation Icon")).click();

		WebElement pInfo = driver.findElement(AppiumBy.accessibilityId("Personal Info"));
		int endX = 133;
		int endY = 63;

		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

		Sequence swipe = new Sequence(finger, 0)

				.addAction(
						finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), endX, endY))
				.addAction(finger.createPointerUp(PointerInput.MouseButton.MIDDLE.asArg()));

		driver.perform(Collections.singletonList(swipe));

		driver.findElement(AppiumBy.accessibilityId("Close Account")).click();
//click delete account button
		driver.findElement(AppiumBy.accessibilityId("Delete Account")).click();
		Thread.sleep(5000);

	}

	@Test(priority = 13)
	public void verifyDeleteAccount() {
		boolean text = driver.findElement(AppiumBy.accessibilityId("Welcome Back")).isDisplayed();
		Assert.assertTrue(text);
	}	
}
