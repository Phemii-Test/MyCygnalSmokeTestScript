package AndroidPageObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class SleepTrackerPageObjectFactory {

	private AndroidDriver driver;
	public static int initialAverageSleep;
	public static int finalAverageSleep;

	public SleepTrackerPageObjectFactory(AndroidDriver driver) {
		this.driver = driver;
	}

	private By heartRateIcon = AppiumBy.accessibilityId("Heart Rate icon");
	private By sleepIcon = AppiumBy.accessibilityId("Sleep icon");
	private By generalElement1 = By.className("android.view.View");
	private By newRecordButton = AppiumBy.accessibilityId("Add new sleep record");
	private By generalElement2 = By.className("android.widget.ImageView");
	private By okButton = AppiumBy.accessibilityId("OK");
	private By frequency = AppiumBy.accessibilityId("0%");
	private By onMedicationField = AppiumBy.accessibilityId("Yes, I'm on medication");
	private By generalElement3 = By.className("android.widget.Button");
	private By SuccessIcon = AppiumBy.accessibilityId("Success Icon");
	private By sleepTrackerButton = By.xpath("//android.widget.Button[@content-desc=\"View Sleep Tracker\"]");
	private By recordMedBtn = AppiumBy.accessibilityId("Record Related Medication");
	private By backButton = By.className("android.widget.Button");

	public void swipeToSleepIcon() throws InterruptedException {
		Thread.sleep(5000);

//		click on the sleep graph from the home screen
		WebElement HRIcon = driver.findElement(heartRateIcon);
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) HRIcon).getId(), "endX", 143, "endY", 1648));
	}

	public void clickSleepIcon() throws InterruptedException {
		driver.findElement(sleepIcon).click();

		Thread.sleep(2000);
	}

	public void validateInitialAverageSleep() {
		WebElement averageSleep = driver.findElements(generalElement1).get(3);
		String extractedInititialAverageSleep = averageSleep.getText().trim().replaceAll("[^\\d.]+", "");
		if (!extractedInititialAverageSleep.isEmpty()) {
			int initialAverageSleep = Integer.parseInt(extractedInititialAverageSleep);
			System.out.println("Initial Average Sleep: " + initialAverageSleep);
		} else {
			System.err.println("Initial Average Sleep value is empty or invalid.");
		}
	}

	public void clickAddNewSleepButton() {
		driver.findElement(newRecordButton).click();
	}

	public void addSleepTime() {
		driver.findElements(generalElement2).get(0).click();
		WebElement clockField = driver.findElement(By.xpath("//android.view.View"));

		((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) clockField).getId(), "endX", 669, "endY", 1322));
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) clockField).getId(), "endX", 669, "endY", 1322));
		driver.findElement(okButton).click();

//		Set to time
		driver.findElements(generalElement2).get(3).click();

		((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) clockField).getId(), "endX", 824, "endY", 1877));
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) clockField).getId(), "endX", 669, "endY", 1322));
		driver.findElement(okButton).click();
	}

	public void addSleepQuality() {
		WebElement Frequency = driver.findElement(frequency);
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) Frequency).getId(), "endX", 435, "endY", 1070));
	}

	public void tickOnMedication() {
		driver.findElement(onMedicationField).click();
	}

	public void clickRecordSleepButton() {
		driver.findElements(generalElement3).get(1).click();
	}

	public boolean isSleepRecorded() throws InterruptedException {
		Thread.sleep(2000);

		return driver.findElement(SuccessIcon).isDisplayed();
	}

	public boolean isRecordMedbtnDispplayed() throws InterruptedException {

		return driver.findElement(recordMedBtn).isDisplayed();
	}

	public void navigateToSleeptracker() throws InterruptedException {
		driver.findElement(sleepTrackerButton).click();

	}

	public void ValidateAverageSleepTrackerFunctionality() {
		WebElement averageSleep = driver.findElements(generalElement1).get(3);
		String updatedAverageText = averageSleep.getText().trim().replaceAll("[^\\d.]+", "");
		if (!updatedAverageText.isEmpty()) {
			int finalAverageSleep = Integer.parseInt(updatedAverageText);
			System.out.println("Updated Average Sleep: " + finalAverageSleep);
		} else {
			System.err.println("Final Average Sleep value is empty or invalid.");
		}

		if (finalAverageSleep > initialAverageSleep) {
			System.out.println("Test Passed: Average sleep stat has been updated.");
		} else {
			System.out.println("Test Failed: Average sleep stat has not been updated.");
		}

	}

	public void backtoDashboardButton() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElements(backButton).get(0).click();
	}

}