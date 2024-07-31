package androidTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class AddHeartRatePageObjectFactory {

	private static  AppiumDriver driver;
	public static int initialHeartRate;
	public static int updatedHeartRate;

	public AddHeartRatePageObjectFactory(AndroidDriver driver) {
		this.driver = driver;
	}
	

	private By heartRateIcon = AppiumBy.accessibilityId("Heart Rate icon");
	private By logNewHaertRateButton = AppiumBy.accessibilityId("Add new heart rate record");
	private By commonElements1 = By.className("android.widget.ImageView");
	private By okButton = AppiumBy.accessibilityId("OK");
	private By commonElements2 = By.xpath("//android.view.View");
	private By commonElements4 = By.className("android.view.View");
	private By onMedication = AppiumBy.accessibilityId("Yes, I'm on medication");
	private By recordHeartRateButton = AppiumBy.accessibilityId("Record Heart Rate");
	private By SuccessIcon = AppiumBy.accessibilityId("Success Icon");
	private By recordRelatedMedButton = AppiumBy.accessibilityId("Record Related Medication");
	private By viewHeartRateButton = AppiumBy.accessibilityId("View Heart Rate");
	private By commonElements3 = By.xpath("//android.widget.Button");


public void clickheartRateIcon() throws InterruptedException {
	// click the heart rate button from the dashboard.
	driver.findElement(heartRateIcon).click();
}
	@Test(priority=7)

	public void checkInitialHeartRateValue() {
		WebElement InitialHeartRateElement = driver.findElements(commonElements4).get(4);
		String InitialHeartRateClean = InitialHeartRateElement.getText().trim().replaceAll("[^\\d.]+", "");

		if (!InitialHeartRateClean.isEmpty()) {
			int initialHeartRate = Integer.parseInt(InitialHeartRateClean);
			System.out.println("initial heartrate:" + initialHeartRate);
		}

	}
	@Test(priority=8)

public void clickAddNewHeartrateButton() throws InterruptedException	{
	driver.findElement(logNewHaertRateButton).click();
	Thread.sleep(5000);
	}
	@Test(priority=9)

public void recordBeatPerMinute() {
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
//		    	    "elementId", ((RemoteWebElement) bpm).getId(),
				"startX", 870, "startY", 1175, "endX", 557, "endY", 1192));
	}
	@Test(priority=10)

	private void slideGesture(RemoteWebDriver driver2, WebElement bpm, int i, int j, int k, int l, int m) {
		// TODO Auto-generated method stub
		
	}
	@Test(priority=11)

	public void addDate() {

		driver.findElements(commonElements1).get(3).click();
//			driver.findElement(AppiumBy.accessibilityId("1, Monday, July 1, 2024")).click();
		driver.findElement(okButton).click();
	}
	@Test(priority=12)

	public void addTime() {
		WebElement setTime = driver.findElements(commonElements1).get(5);
		setTime.click();
		WebElement StartMinute = driver.findElement(commonElements2);
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) StartMinute).getId(), "endX", 372, "endY", 1625));
		WebElement Startseconds = driver.findElement(commonElements2);
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) Startseconds).getId(), "endX", 675, "endY", 1310));
		driver.findElement(AppiumBy.accessibilityId("OK")).click();

		((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) setTime).getId(), "endX", 538, "endY", 530));
	}
	@Test(priority=13)

	public void tickOnMedication() {
		driver.findElement(onMedication).click();
	}
	@Test(priority=14)

	public void clickRecordHeartrateButton() {

		driver.findElement(recordHeartRateButton).click();

	}

	@Test(priority = 15)
	public boolean isHeartRateRecorded() {

		return driver.findElement(SuccessIcon).isDisplayed();

	}
	@Test(priority=16)

	public boolean isRecordRelatedMedicationDisplayed() {
		return driver.findElement(recordRelatedMedButton).isDisplayed();
	}
	@Test(priority=17)

	public void clickViewHeartRateButton() {
		driver.findElement(viewHeartRateButton).click();

	}

	@Ignore
	@Test(priority = 18)
	public void VerifyUpdatedHeartRate() {

		WebElement updatedHeartRateElement = driver.findElements(commonElements4).get(4);
		String updatedHeartrateClean = updatedHeartRateElement.getText().trim().replaceAll("[^\\d.]+", "");

		if (!updatedHeartrateClean.isEmpty()) {
			int updatedHeartRate = Integer.parseInt(updatedHeartrateClean);
			System.out.println("updated heartrate:" + updatedHeartRate);
		}

		if (updatedHeartRate > initialHeartRate) {
			System.out.println("Heartrate has been logged successfully");

		} else {
			System.out.println("Heart rate wasn't logged properly");
		}

//		    Click the back button to get back to the dashboard.
	}
	@Test(priority=19)

	public void clickBackButton() {
		driver.findElement(commonElements3).click();
	}
}