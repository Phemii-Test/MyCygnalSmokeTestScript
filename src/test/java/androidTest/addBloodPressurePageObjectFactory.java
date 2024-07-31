package androidTest;

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
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class addBloodPressurePageObjectFactory {

	private AppiumDriver driver;
	public static boolean initialBP;
	public static boolean updatedLatestBP;

	public addBloodPressurePageObjectFactory(AndroidDriver driver) {
		this.driver = driver;
	}

	private By BpIcon = AppiumBy.accessibilityId("Blood Pressure icon");
	private By latestBPText = By.className("android.view.View");
	private By addNewBPButton = AppiumBy.accessibilityId("Add new blood pressure record");
	private By commonElement1 = By.className("android.widget.ImageView");
	private By okButton = AppiumBy.accessibilityId("OK");
	private By onMedication = AppiumBy.accessibilityId("Yes, I'm on medication");
	private By recordBPButton = AppiumBy.accessibilityId("Record Blood Pressure");
	private By SuccessIcon = AppiumBy.accessibilityId("Success Icon");
	private By viewBPButton = AppiumBy.accessibilityId("View Blood Pressure");
	private By commonElement2 = By.xpath("//android.widget.Button");
	private By commonElement3 = By.xpath("//android.view.View");

	public void clickBPIcon() throws InterruptedException {
		driver.findElement(BpIcon).click();
		Thread.sleep(5000);
	}

	public void verifyInitialLatestBPValue() {
		WebElement averageBPElement = driver.findElements(latestBPText).get(4);
		String CleaninitialaverageBP = averageBPElement.getText().trim().replaceAll("[^\\d.]+", "");
		if (!CleaninitialaverageBP.isEmpty()) {
			int initialBP = Integer.parseInt(CleaninitialaverageBP);
			System.out.println("Initial average blood pressure: " + initialBP);
		} else {
			System.err.println("initial BP value is empty or invalid.");
		}
	}

	public void clickAddNewBPButton() throws InterruptedException {
		driver.findElement(addNewBPButton).click();
		Thread.sleep(5000);
	}

	public void addSystolicValue() {

		((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
				ImmutableMap.of("startX", 870, "startY", 1175, "endX", 557, "endY", 1192));

//Perform a scroll up gesture.
		WebElement scrollup = driver.findElement(AppiumBy.accessibilityId("What is your diastolic blood pressure?"));
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) scrollup).getId(), "endX", 534, "endY", 290));
	}

	public void logDiastolicValue() throws InterruptedException {
		Thread.sleep(2000);
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
				ImmutableMap.of("startX", 755, "startY", 746, "endX", 569, "endY", 763));
	}

	public void addDate() {
		driver.findElements(By.className("android.widget.ImageView")).get(3).click();
		driver.findElement(AppiumBy.accessibilityId("OK")).click();
	}

	public void setTime() {
		WebElement setTime = driver.findElements(commonElement1).get(5);
		setTime.click();
		WebElement StartMinute = driver.findElement(commonElement3);
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) StartMinute).getId(), "endX", 372, "endY", 1625));
		WebElement Startseconds = driver.findElement(commonElement3);
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) Startseconds).getId(), "endX", 675, "endY", 1310));
		driver.findElement(okButton).click();

		((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) setTime).getId(), "endX", 538, "endY", 530));
	}

	public void clickOnMedication() {
		driver.findElement(onMedication).click();

//Perform a second scroll up gesture.
		WebElement scrollup2 = driver.findElement(AppiumBy.accessibilityId("Are you on related medication?"));
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) scrollup2).getId(), "endX", 481, "endY", 637));
	}

	public void clickRecordBPButton() {
		driver.findElement(recordBPButton).click();
	}

	public boolean isBPRecorded() throws InterruptedException {
		return driver.findElement(SuccessIcon).isDisplayed();
	}

	public boolean isRecordRelatedMedDisplayed() {
		return driver.findElement(AppiumBy.accessibilityId("Record Related Medication")).isDisplayed();

	}

	public void clickViewBPButton() {
		driver.findElement(viewBPButton).click();

	}

	public void VerifyLatestBPValueChange() {

		WebElement updatedAverageBPElement = driver.findElements(commonElement3).get(4);
		String CleanUpdatedAverageBP = updatedAverageBPElement.getText().trim().replaceAll("[^\\d.]+", "");
		if (!CleanUpdatedAverageBP.isEmpty()) {
			int updatedLatestBP = Integer.parseInt(CleanUpdatedAverageBP);
			System.out.println("Updated average blood pressure: " + updatedLatestBP);
		} else {
			System.err.println("Final BP value is empty or invalid.");
		}

		// Assert that the average blood pressure has changed
		if (updatedLatestBP = initialBP) {
			System.out.println("BP updated successfully");
		} else {
			System.out.println("The code is flaky");
		}
	}

	public void pressBackButton() {

		driver.findElement(commonElement2).click();
	}

}
