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
import io.appium.java_client.android.AndroidDriver;

public class recommendationSteps {

	private AppiumDriver driver;

	public recommendationSteps(AppiumDriver driver) {
		this.driver=driver;
	}
	
	private By recommendationIcon = AppiumBy.accessibilityId("Recommendations icon");
	private By gotoAssessment = AppiumBy.accessibilityId("Go to Assessment");
	private By commonElement1 = By.className("XCUIElementTypeSwitch");
	private By continueButton = AppiumBy.accessibilityId("Continue");
	private By submitButton = AppiumBy.accessibilityId("Submit");
	private By seeRecommendationButton = AppiumBy.accessibilityId("See Recommendation");
	private By influenzaRecommendation = AppiumBy.accessibilityId("Annual Influenza (Flu) Vaccine");
	private By HPVRecommendation = AppiumBy.accessibilityId("HPV Vaccine");
	private By covid19Recommendation = AppiumBy.accessibilityId("Updated COVID-19 Vaccines (2023 / 2024)");
	
	
	@Test(priority=1)
	public void initiateRecommendation() throws InterruptedException {
		Thread.sleep(5000);
		driver.findElement(recommendationIcon).click();
		driver.findElement(gotoAssessment).click();

	}
	@Test(priority=2)
	public void accessmentQuestion1() {
//		Select No, I don't smoke
		driver.findElements(commonElement1).get(1).click();
		driver.findElement(continueButton).click();

	}
	@Test(priority=3)
	public void accessmentQuestion2() {
//		Select yes, I smoke
		driver.findElements(commonElement1).get(0).click();
		driver.findElement(continueButton).click();

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

		driver.findElement(continueButton).click();
	}
	@Test(priority=7)
	public void accessmentQuestion5() throws InterruptedException {
		driver.findElements(commonElement1).get(1).click();
		driver.findElements(commonElement1).get(2).click();
		driver.findElements(commonElement1).get(4).click();
		
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
		
		driver.findElement(continueButton).click();

	}
	
	@Test(priority=8)
	public void accessmentQuestion6() throws InterruptedException {
		driver.findElements(commonElement1).get(0).click();
		driver.findElements(commonElement1).get(1).click();
		driver.findElements(commonElement1).get(2).click();
		
		driver.findElement(submitButton).click();
		Thread.sleep(5000);
		
		driver.findElement(seeRecommendationButton).click();

	}
	@Test(priority=9)
	public boolean influenzaIsDisplayed() throws InterruptedException {
		Thread.sleep(5000);

		return driver.findElement(influenzaRecommendation).isDisplayed();
	}
	
	@Test(priority=10)
	public boolean HPVVaccineIsDisplayed() {
		return driver.findElement(HPVRecommendation).isDisplayed();
	}
	
	@Test(priority=11)
	public boolean Covid19VaccineIsDisplayed() {
		return driver.findElement(covid19Recommendation).isDisplayed();
	}
	
}
