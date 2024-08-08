package AndroidPageObject;

import java.io.IOException;
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

public class LogProcedureObjectFactory {

	private AndroidDriver driver;

	public LogProcedureObjectFactory(AndroidDriver driver) {
		this.driver = driver;

	}

	private By procedureIcon = AppiumBy.accessibilityId("Procedures");
	private By logNewProcedureButton = AppiumBy.accessibilityId("Log new procedure");
	private By logProcedureButton = AppiumBy.accessibilityId("Log Procedure");
	private By generalElement1 = By.xpath("//android.widget.ImageView");
	private By procedureTypeDropdown = AppiumBy.accessibilityId("Select type");
	private By generalElement2 = By.className("android.widget.EditText");
	private By procedure1 = AppiumBy.accessibilityId("Procedure 1");
	private By generalElement3 = By.className("android.widget.Button");
	private By gotoDashboard = AppiumBy.accessibilityId("Go to Dashboard");
	private By okButton = AppiumBy.accessibilityId("OK");
	private By SuccessIcon = AppiumBy.accessibilityId("Success Icon");
	private By logProcedurebutton = AppiumBy.accessibilityId("Log Procedure");

	public void clickSelectActionsButton() throws InterruptedException {
		driver.findElement(procedureIcon).click();
	}

	public void clicklogProcedureButton() throws InterruptedException, IOException {
//		driver.findElement(logNewProcedureButton).click();
		driver.findElement(logProcedureButton).click();

	}

	public void enterProcedureTitle() {
		WebElement title = driver.findElements(generalElement1).get(0);
		title.click();
		title.sendKeys("Appendicitis");

	}

	public void enterProcedureDescription() {
		WebElement desc = driver.findElements(generalElement2).get(1);
		desc.click();
		desc.sendKeys("Taking out the painful appendices");
		driver.navigate().back();
	}

	public void enterHealthProvider() {
		WebElement Provider = driver.findElements(generalElement2).get(3);
		Provider.click();
		Provider.sendKeys("Rev, Fr. Burke Memorial Clinic");
		driver.navigate().back();
	}

	public void selectProcedureType() throws InterruptedException {
		WebElement SelectType = driver.findElement(procedureTypeDropdown);
		SelectType.click();
		driver.findElement(procedure1).click();

		((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) SelectType).getId(), "endX", 322, "endY", 202));
		Thread.sleep(2000);
	}

	public void preprocedurePreparation() {
		WebElement ProcedurePrep = driver.findElements(generalElement2).get(0);
		ProcedurePrep.click();
		ProcedurePrep.sendKeys("Several scan was done, and a several doctor appointment was scheduled.");
		driver.navigate().back();
	}

	public void postProcedureCare() {
		WebElement care = driver.findElements(generalElement2).get(2);
		care.click();
		care.sendKeys("Series of antibiotics was administered, and also frequent dressing.");
		driver.navigate().back();
	}

//	Upload file

	/*
	 * WebElement select=
	 * driver.findElement(AppiumBy.accessibilityId("Select File")); select.click();
	 * 
	 * By permissionButton =
	 * By.id("com.android.permissioncontroller:id/permission_allow_button");
	 * WebElement permissionBtn=driver.findElement(permissionButton); try { if (
	 * permissionBtn.isDisplayed()) { (permissionBtn).click(); } } catch (Exception
	 * e) { // Permission button not found, proceed after a short sleep
	 * Thread.sleep(4000); }
	 * driver.findElement(AppiumBy.accessibilityId("Show roots")).click();
	 * driver.wait(2000);
	 * driver.findElements(By.className("android.widget.TextView")).get(3).click();
	 * driver.findElement(AppiumBy.id("com.android.documentsui:id/icon_thumb")).
	 * click(); Thread.sleep(2000); WebElement
	 * Thumbnail=driver.findElements(By.className("android.widget.LinearLayout")).
	 * get(9); Thumbnail.click(); Thread.sleep(2000);
	 * 
	 * 
	 * 
	 * 
	 * 
	 * Verify file uploaded successfully String Toast=driver.findElement(AppiumBy.
	 * accessibilityId("(Success Procedure File uploaded successfully")).
	 * getAttribute("Success " + "Procedure File uploaded successfully");
	 * 
	 * Thread.sleep(7000);
	 */
	public void selectDate() {
		driver.findElements(generalElement1).get(2).click();
		driver.findElement(okButton).click();
	}

	public void setTime() {
		WebElement setTime = driver.findElements(generalElement1).get(4);
		setTime.click();
		WebElement StartMinute = driver.findElement(By.xpath("//android.view.View"));
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) StartMinute).getId(), "endX", 372, "endY", 1625));
		WebElement Startseconds = driver.findElement(By.xpath("//android.view.View"));
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) Startseconds).getId(), "endX", 675, "endY", 1310));
		driver.findElement(AppiumBy.accessibilityId("OK")).click();

		driver.navigate().back();
	}

	public void clickLogProcedureButton() {
		driver.findElement(logProcedurebutton).click();

	}

	public boolean isProcedureAdded() throws InterruptedException {
		Thread.sleep(5000);
		return driver.findElement(SuccessIcon).isDisplayed();
	}

	public void backToDashboard() {
		driver.findElement(gotoDashboard).click();

	}
}