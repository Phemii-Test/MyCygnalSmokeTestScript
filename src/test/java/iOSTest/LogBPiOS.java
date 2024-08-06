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
import io.appium.java_client.android.AndroidDriver;

public class LogBPiOS {

	private AppiumDriver driver;
	
	public LogBPiOS (AppiumDriver driver) {
		this.driver=driver;
	}

	
private By BPIcon = AppiumBy.accessibilityId("Blood Pressure icon");
private By newRecordBtn = AppiumBy.accessibilityId("Add new blood pressure record");
private By commonElement1 = By.className("XCUIElementTypeImage");
private By okButton = AppiumBy.accessibilityId("OK");
private By onMedication = AppiumBy.accessibilityId("Yes, I'm on medication");
private By recordBPButton = AppiumBy.accessibilityId("Record Blood Pressure");
private By checkMark = AppiumBy.accessibilityId("Success Icon");
private By recordRelatedMed = AppiumBy.accessibilityId("Record Related Medication");
private By viewBPIcon = AppiumBy.accessibilityId("View Blood Pressure");
private By commonElement2 = By.className("XCUIElementTypeButton");


	@Test(priority = 1)

	public void beginBPLog()  {
		driver.findElement(BPIcon).click();
		driver.findElement(newRecordBtn).click();
	}
	
	public void systolicBP()  {
//		log systolic BP
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
	
	public void scrollUp() throws InterruptedException  {
//		Perform a scroll up gesture.
		int startsX = 144;
		int startsY = 570;
		int endsX = 176;
		int endsY = 142;

		PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger");

		Sequence swipe1 = new Sequence(finger1, 0)
				.addAction(finger1.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), startsX,
						startsY))
				.addAction(finger1.createPointerDown(PointerInput.MouseButton.MIDDLE.asArg())).addAction(finger1
						.createPointerMove(Duration.ofMillis(3000), PointerInput.Origin.viewport(), endsX, endsY))
				.addAction(finger1.createPointerUp(PointerInput.MouseButton.MIDDLE.asArg()));

		driver.perform(Collections.singletonList(swipe1));
		Thread.sleep(3000);
	}
	
	public void diastolicLog()  {
		int StartX = 271;
		int StartY = 277;
		int EndX = 208;
		int EndY = 274;
		PointerInput finger2 = new PointerInput(PointerInput.Kind.TOUCH, "finger");

		// Create the sequence of actions
		Sequence swipe2 = new Sequence(finger2, 0)
				.addAction(
						finger2.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), StartX, StartY))
				.addAction(finger2.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
				.addAction(
						finger2.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), EndX, EndY))
				.addAction(finger2.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

		// Perform the sequence
		driver.perform(Collections.singletonList(swipe2));
	}

	public void setDateAndTime()  {
	    driver.findElements(commonElement1).get(6).click();
		driver.findElement(okButton).click();

//		Set time.
		WebElement setTime = driver.findElements(commonElement1).get(8);
		setTime.click();
		driver.findElement(okButton).click();
	}
	public void recordBP()  {
		driver.findElement(onMedication).click();

		driver.findElement(recordBPButton).click();
	}

	public boolean verifyBPLog() throws InterruptedException {
		Thread.sleep(5000);
		return driver.findElement(checkMark).isDisplayed();
	}	
	public boolean isRecordMedButtonDisplayed(){
		return driver.findElement(recordRelatedMed).isDisplayed();
	}
	
	public void viewBPRecord() throws InterruptedException  {
		driver.findElement(viewBPIcon).click();
		Thread.sleep(5000);

		driver.findElements(commonElement2).get(1).click();

	}

}
