package androidTest;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class addMedicationObjectFactory {
	private AndroidDriver driver;

	public addMedicationObjectFactory(AndroidDriver driver) {
		this.driver = driver;
	}

	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	private By selectActionsButton = AppiumBy.accessibilityId("Select Actions Button");
	private By addMedicationsButton = AppiumBy.accessibilityId("Add Medications");
	private By medNameField = By.xpath("//android.widget.ImageView[1]");
	private By dosageField = By.className("android.widget.EditText");
	private By frequency = AppiumBy.accessibilityId("0%");
	private By category = AppiumBy.accessibilityId("Antipertensives");
	private By startDateSelector = By.className("android.widget.ImageView");
	private By endDateSelector = By.className("android.widget.ImageView");
	private By okButton = AppiumBy.accessibilityId("OK");
	private By addMedicationButton = AppiumBy.accessibilityId("Add Medication");
	private By successIcon = AppiumBy.accessibilityId("Success Icon");
	private By viewMedButton = AppiumBy.accessibilityId("View Medication");
	private By doseTime = By.className("android.widget.ImageView");
	private By morning = AppiumBy.accessibilityId("AM");
	private By evening = AppiumBy.accessibilityId("PM");
	private By alarm = AppiumBy.accessibilityId("Toggle Alarm On");
	private By setReminder = AppiumBy.accessibilityId("Set Reminder");
	private By gotoMedication = AppiumBy.accessibilityId("Go to Medications");
	private By backButton = By.xpath("//android.widget.Button");

	public void clickSelectActionsButton() throws InterruptedException {
		Thread.sleep(5000);
		driver.findElement(selectActionsButton).click();
	}

	public void clickAddMedicationsButton() {
		driver.findElement(addMedicationsButton).click();
	}

	public void fillMedicationForm(String medName, String dosage) {
		WebElement medNameElement = driver.findElement(medNameField);
		((JavascriptExecutor) driver).executeScript("mobile: doubleClickGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) medNameElement).getId()));
		medNameElement.sendKeys(medName);

		WebElement dosageElement = driver.findElements(dosageField).get(1);
		((JavascriptExecutor) driver).executeScript("mobile: doubleClickGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) dosageElement).getId()));
		dosageElement.sendKeys(dosage);
	}

	public void setFrequency() {
		WebElement frequencyElement = driver.findElement(frequency);
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) frequencyElement).getId(), "endX", 351, "endY", 992));
	}

	public void selectCategory() {
		driver.navigate().back();
		driver.findElement(category).click();
	}

	public void selectStartDate() {
		WebElement startDateElement = driver.findElements(startDateSelector).get(1);
		startDateElement.click();
		driver.findElement(okButton).click();
	}

	public void selectEndDate() {
		WebElement endDateElement = driver.findElements(endDateSelector).get(3);
		endDateElement.click();
		driver.findElement(okButton).click();

		((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) endDateElement).getId(), "endX", 500, "endY", 700));
	}

	public void clickAddMedicationButton() {
		driver.findElement(addMedicationButton).click();
	}

	public boolean isMedicationAdded() throws InterruptedException {
		Thread.sleep(5000);
		return driver.findElement(successIcon).isDisplayed();
	}

	public void viewMedicationButton() {

		driver.findElement(viewMedButton).click();
	}
//			Add first dose

	public void firstDoseStartTime() {
		driver.findElements(doseTime).get(0).click();
		WebElement StartMinute = driver.findElement(By.xpath("//android.view.View"));
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) StartMinute).getId(), "endX", 372, "endY", 1625));
		WebElement Startseconds = driver.findElement(By.xpath("//android.view.View"));
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) Startseconds).getId(), "endX", 675, "endY", 1310));
		driver.findElement(okButton).click();
	}

	public void firstDoseEndTime() {
		driver.findElements(doseTime).get(2).click();
		WebElement EndMinute = driver.findElement(By.xpath("//android.view.View"));
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) EndMinute).getId(), "endX", 372, "endY", 1625));
		WebElement Endseconds = driver.findElement(By.xpath("//android.view.View"));
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) Endseconds).getId(), "endX", 944, "endY", 1499));
		driver.findElement(okButton).click();
	}

	public void secondDoseStartTime() {
		driver.findElements(doseTime).get(4).click();
		WebElement StartMinute2 = driver.findElement(By.xpath("//android.view.View"));
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) StartMinute2).getId(), "endX", 664, "endY", 1322));
		WebElement Startseconds2 = driver.findElement(By.xpath("//android.view.View"));
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) Startseconds2).getId(), "endX", 675, "endY", 1310));
		driver.findElement(evening).click();
		driver.findElement(okButton).click();
	}

	public void secondDoseEndTime() {

		WebElement dose2field = driver.findElements(doseTime).get(6);
		dose2field.click();
		WebElement EndMinute2 = driver.findElement(By.xpath("//android.view.View"));
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) EndMinute2).getId(), "endX", 664, "endY", 1322));
		WebElement Endseconds2 = driver.findElement(By.xpath("//android.view.View"));
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) Endseconds2).getId(), "endX", 944, "endY", 1499));
		driver.findElement(evening).click();
		driver.findElement(okButton).click();

		((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) dose2field).getId(), "endX", 681, "endY", 950));
	}

	public void thirdDoseStartTime() {
		driver.findElements(doseTime).get(8).click();
		WebElement StartMinute3 = driver.findElement(By.xpath("//android.view.View"));
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) StartMinute3).getId(), "endX", 973, "endY", 1630));
		WebElement Startseconds3 = driver.findElement(By.xpath("//android.view.View"));
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) Startseconds3).getId(), "endX", 675, "endY", 1310));
		driver.findElement(evening).click();
		driver.findElement(okButton).click();

	}

	public void thirdDoseEndTime() {

		driver.findElements(doseTime).get(10).click();
		WebElement EndMinute3 = driver.findElement(By.xpath("//android.view.View"));
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) EndMinute3).getId(), "endX", 973, "endY", 1630));
		WebElement Endseconds3 = driver.findElement(By.xpath("//android.view.View"));
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) Endseconds3).getId(), "endX", 944, "endY", 1499));
		driver.findElement(evening).click();
		driver.findElement(okButton).click();
	}

	public void toggleAlarmOn() {

		driver.findElement(alarm).click();
	}

	public void clickSetReminderButton() {
		driver.findElement(setReminder).click();

	}

	public void clickGotoMedicationButton() throws InterruptedException {

		driver.findElement(gotoMedication).click();

	}

	public void backButton() {

		driver.findElement(backButton).click();

	}

}
