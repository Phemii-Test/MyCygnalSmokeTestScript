package iOSPageObject;

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

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class AddMedicationiOS {

	private AppiumDriver driver;

	public AddMedicationiOS(AppiumDriver driver) {
		this.driver = driver;
	}

	private By selectActionButton = AppiumBy.accessibilityId("Select Actions Button");
	private By addMedButton = AppiumBy.accessibilityId("Add Medications");
	private By commonElement1 = By.className("XCUIElementTypeTextField");
	private By antipertensiveOption = AppiumBy.accessibilityId("Antipertensives");
	private By commonElement2 = By.className("XCUIElementTypeImage");
	private By addMedication = By.xpath("//XCUIElementTypeButton[@name='Add Medication']");
	private By checkMarkButton = AppiumBy.accessibilityId("Success Icon");
	private By viewMedication = AppiumBy.accessibilityId("View Medication");
	private By hourOption = AppiumBy.accessibilityId("Hour");
	private By minuteOption = AppiumBy.accessibilityId("Minute");
	private By okButton = AppiumBy.accessibilityId("OK");
	private By am = AppiumBy.accessibilityId("AM");
	private By pm = AppiumBy.accessibilityId("PM");
	private By switchInput = AppiumBy.accessibilityId("Switch to text input mode");
	private By toggleAlarm = AppiumBy.accessibilityId("Toggle Alarm On");
	private By setReminder = By.xpath("//XCUIElementTypeButton[@name='Set Reminder']");
	private By medicationPage = AppiumBy.accessibilityId("Go to Medications");
	private By backHomeButton = By.xpath("//XCUIElementTypeButton");

//		WebElement nextBtn = driver.findElement(By.className("XCUIElementTypeButton"));
//		nextBtn.click();
//		Thread.sleep(2000);
//
//		driver.findElement(AppiumBy.accessibilityId("Next page button")).click();
//
//		driver.findElement(AppiumBy.accessibilityId("Login")).click();
//////	        
//		WebElement emailField = driver.findElements(By.className("XCUIElementTypeTextField")).get(0);
//		emailField.click();
//		emailField.sendKeys("Ohlufehmii@gmail.com");
//
//		WebElement passwordField = driver.findElements(By.className("XCUIElementTypeTextField")).get(2);
//		passwordField.click();
//		passwordField.sendKeys("Hbon@1234");
//
//		driver.findElement(AppiumBy.accessibilityId("Login")).click();
//
//		Thread.sleep(5000);
//	}

	public void addMedication() {

		driver.findElement(selectActionButton).click();

		driver.findElement(addMedButton).click();
	}

	public void addMedicationName(String medName) {

		driver.findElements(commonElement1).get(0).sendKeys(medName);
		;
	}

	public void addDosage(String dosage) {

		driver.findElements(commonElement1).get(2).sendKeys(dosage);
		;
	}

	public void setFrequency() throws InterruptedException {

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

	}

	public void selectCategory() {

		driver.findElement(antipertensiveOption).click();
	}

	public void date() throws InterruptedException {

//		    select start date
		driver.findElements(commonElement2).get(0).click();
		driver.findElement(okButton).click();

//			select end date
		driver.findElements(commonElement2).get(2).click();
		driver.findElement(okButton).click();
		

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
	
		driver.findElement(addMedication).click();

	}

	public boolean verifyMedicationAdded() throws InterruptedException {
		Thread.sleep(5000);
		 return  driver.findElement(checkMarkButton).isDisplayed();

	}

	public void addFirstDoseTime() throws InterruptedException {
		driver.findElement(viewMedication).click();

//          Start Time
		driver.findElements(commonElement2).get(0).click();
		driver.findElement(switchInput).click();
		WebElement hour = driver.findElement(hourOption);
		hour.clear();
		hour.sendKeys("5");

		WebElement minute = driver.findElement(minuteOption);
		minute.clear();
		minute.sendKeys("00");
		driver.findElement(am).click();

		driver.findElement(okButton).click();

//			End time
		driver.findElements(commonElement2).get(2).click();
		driver.findElement(switchInput).click();
		WebElement hour1 = driver.findElement(hourOption);
		hour1.clear();
		hour1.sendKeys("5");

		WebElement minute1 = driver.findElement(minuteOption);
		minute1.clear();
		minute1.sendKeys("05");

		driver.findElement(minuteOption).click();

		driver.findElement(okButton).click();
	}

	public void addSecondDoseTime() throws InterruptedException {

//	          Start Time
		driver.findElements(commonElement2).get(4).click();
		driver.findElement(switchInput).click();
		WebElement hour = driver.findElement(hourOption);
		hour.clear();
		hour.sendKeys("10");

		WebElement minute = driver.findElement(minuteOption);
		minute.clear();
		minute.sendKeys("00");
		driver.findElement(am).click();

		driver.findElement(okButton).click();

//				End time
		driver.findElements(commonElement2).get(6).click();
		driver.findElement(switchInput).click();
		WebElement hour1 = driver.findElement(hourOption);
		hour1.clear();
		hour1.sendKeys("10");

		WebElement minute1 = driver.findElement(minuteOption);
		minute1.clear();
		minute1.sendKeys("05");

		driver.findElement(am).click();

		driver.findElement(okButton).click();

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

	public void addThirdDoseTime() throws InterruptedException {

//	   	          Start Time
		driver.findElements(commonElement2).get(8).click();
		driver.findElement(switchInput).click();
		WebElement hour = driver.findElement(hourOption);
		hour.clear();
		hour.sendKeys("3");

		WebElement minute = driver.findElement(minuteOption);
		minute.clear();
		minute.sendKeys("00");
		driver.findElement(pm).click();

		driver.findElement(okButton).click();

//	   				End time
		driver.findElements(commonElement2).get(10).click();
		driver.findElement(switchInput).click();
		WebElement hour1 = driver.findElement(hourOption);
		hour1.clear();
		hour1.sendKeys("3");

		WebElement minute1 = driver.findElement(minuteOption);
		minute1.clear();
		minute1.sendKeys("05");

		driver.findElement(pm).click();

		driver.findElement(okButton).click();
	}

	public void completeReminderSetup() throws InterruptedException {

//			Toggle alarm
		WebElement Alarm = driver.findElement(toggleAlarm);
		Alarm.click();
		
		
//			Click set reminder button
		driver.findElement(setReminder).click();
		Thread.sleep(5000);
	}

	public boolean ValidateReminderSet() {
		return driver.findElement(checkMarkButton).isDisplayed();

	}

	public void gotoMedDashboard() {
//		Go to the medication management page
		driver.findElement(medicationPage).click();

//		Click the back button to get back to the dashboard.
		driver.findElement(backHomeButton).click();
//	}
	}
}