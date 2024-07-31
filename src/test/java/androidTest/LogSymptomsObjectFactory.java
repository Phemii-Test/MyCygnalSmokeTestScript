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
import io.appium.java_client.HidesKeyboard;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.PressesKey;

public class LogSymptomsObjectFactory {

	private AndroidDriver driver;

	public LogSymptomsObjectFactory(AndroidDriver driver) {

		this.driver = driver;
	}

	private By logSymptoms = AppiumBy.accessibilityId("Log Symptoms");
	private By generalElement1 = By.xpath("//android.widget.ImageView");
	private By severity = AppiumBy.accessibilityId("0%");
	private By generalElement2 = By.className("android.widget.EditText");
	private By frequencyDropdown = AppiumBy.accessibilityId("Select Frequency");
	private By frequencyValue = AppiumBy.accessibilityId("Daily");
	private By generalElement3 = By.className("android.widget.Button");
	private By gotoDashboard = AppiumBy.accessibilityId("Go to Dashboard");
	private By okButton = AppiumBy.accessibilityId("OK");
	private By SuccessIcon = AppiumBy.accessibilityId("Success Icon");
	private By selectActionsButton = AppiumBy.accessibilityId("Select Actions Button");

	public void SelectActionsButton() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(selectActionsButton).click();
	}

	public void clickLogSymptomsButton() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(logSymptoms).click();
	}

	public void enterSymptomsTitle() {
		WebElement title = driver.findElements(generalElement1).get(0);
		title.click();
		title.sendKeys("Headache");
	}

	public void selectSymptomsSeverity() {
		WebElement Severity = driver.findElement(severity);
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) Severity).getId(), "endX", 435, "endY", 1070));

	}

	public void EnterSymptomsDescription() {
		WebElement desc = driver.findElements(generalElement2).get(1);
		desc.click();
		desc.sendKeys("I feel slight headache at my forehead.");
		driver.navigate().back();
	}

	public void setFrequency() {
		driver.findElement(frequencyDropdown).click();
		driver.findElement(frequencyValue).click();
	}

	public void setSymptomsTrigger() {
		WebElement Triggers = driver.findElements(generalElement2).get(3);
		Triggers.click();
		Triggers.sendKeys("Stress");
		driver.findElements(generalElement3).get(1).click();
	}

	public void enterAleviatingFactor() {
		WebElement alivFactors = driver.findElements(generalElement2).get(3);
		alivFactors.click();
		alivFactors.sendKeys("Analgesics");
		driver.findElements(generalElement3).get(4).click();
	}

	public void selectDate() {
		driver.findElements(generalElement1).get(3).click();
		driver.findElement(okButton).click();
	}

	public void setTime() {
		driver.findElements(generalElement1).get(5).click();
		WebElement StartMinute = driver.findElement(By.xpath("//android.view.View"));
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) StartMinute).getId(), "endX", 372, "endY", 1625));
		WebElement Startseconds = driver.findElement(By.xpath("//android.view.View"));
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) Startseconds).getId(), "endX", 675, "endY", 1310));
		driver.findElement(okButton).click();
		driver.navigate().back();

	}

	public void clickLogSymptomsAgain() {
		driver.findElement(logSymptoms).click();
	}

	public boolean isSymptomsAdded() throws InterruptedException {
		Thread.sleep(5000);

		return driver.findElement(SuccessIcon).isDisplayed();
	}

	public void backToDashboard() throws InterruptedException {
		Thread.sleep(3000);

		driver.findElement(gotoDashboard).click();

	}
}