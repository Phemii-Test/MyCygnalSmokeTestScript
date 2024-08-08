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
import io.appium.java_client.HidesKeyboard;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.PressesKey;
import io.appium.java_client.ios.IOSDriver;

public class LogSymptomsiOS {

	private AppiumDriver driver;

	public LogSymptomsiOS(AppiumDriver driver) {
		this.driver = driver;
	}

	private By commonElement1 = By.className("XCUIElementTypeTextField");
	private By actionsButton = AppiumBy.accessibilityId("Select Actions Button");
	private By logSymptomsButton = AppiumBy.accessibilityId("Log Symptoms");
	private By frequencyButton = AppiumBy.accessibilityId("Select Frequency");
	private By dailyOption = AppiumBy.accessibilityId("Daily");
	private By commonAddButton1 = By.xpath("(//XCUIElementTypeButton[@name='Add'])[1]");
	private By commonAddButton2 = By.xpath("(//XCUIElementTypeButton[@name='Add'])[2]");
	private By commonElement2 = By.className("XCUIElementTypeImage");
	private By okButton = AppiumBy.accessibilityId("OK");
	private By switchMode = AppiumBy.accessibilityId("Switch to text input mode");
	private By hourOption = AppiumBy.accessibilityId("Hour");
	private By minuteOption = AppiumBy.accessibilityId("Minute");
	private By logSymptomButton = By.xpath("//XCUIElementTypeButton[@name='Log Symptoms']");
	private By symptomLoggedText = AppiumBy.accessibilityId("Symptom Logged");
	private By gotoDashboardbutton = AppiumBy.accessibilityId("Go to Dashboard");

	public void symptomName(String symptomName) {
		driver.findElement(actionsButton).click();
		driver.findElement(logSymptomsButton).click();

//	    	Enter Symptom name
		driver.findElements(commonElement1).get(2).sendKeys(symptomName);
	}

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

	public void symptomsDescription() {
//	    	Symptoms description
		driver.findElements(commonElement1).get(4).sendKeys("Slight headache");
		driver.findElement(AppiumBy.accessibilityId("Return")).click();
	}

	public void Frequency() {
//	    	Set frequency
		driver.findElement(frequencyButton).click();
		driver.findElement(dailyOption).click();
	}

	public void Triggers(String triggers) {
//	    	Set triggers
		driver.findElements(commonElement1).get(0).sendKeys(triggers);
		driver.findElement(commonAddButton1).click();
//			driver.navigate().back();
	}

	public void alleviatingFactors(String AlleviatingFactor) {
//	    	Set Alleviating factors
		driver.findElements(commonElement1).get(1).sendKeys(AlleviatingFactor);
		driver.findElement(commonAddButton2).click();

	}

	public void setDateAndTime() throws InterruptedException {
//	    	Set date
		driver.findElements(commonElement2).get(3).click();
		driver.findElement(okButton).click();

//	    	Set time
		driver.findElements(commonElement2).get(5).click();
		driver.findElement(switchMode).click();
		WebElement hour = driver.findElement(hourOption);
		hour.clear();
		hour.sendKeys("6");
		WebElement Minute = driver.findElement(minuteOption);
		Minute.clear();
		Minute.sendKeys("16");
		driver.findElement(AppiumBy.accessibilityId("OK")).click();

		driver.findElement(logSymptomButton).click();

		Thread.sleep(5000);
	}

	@Test(priority = 8)
	public void ValidateSymptomLog() {
		String actualText = driver.findElement(symptomLoggedText).getText();
		String expectedText = "Symptom Logged";
		Assert.assertEquals(actualText, expectedText);

//			Navigate to the dashboard
		driver.findElement(gotoDashboardbutton).click();
	}

}
