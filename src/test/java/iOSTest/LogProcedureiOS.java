package iOSTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;

public class LogProcedureiOS {

	private AppiumDriver driver;
	
	public LogProcedureiOS(AppiumDriver driver) {
		this.driver= driver;
	}
	
	private By selectActions = AppiumBy.accessibilityId("Select Actions Button");
	private By logProcedureButton = AppiumBy.accessibilityId("Log Procedure");
	private By commonElement1 = By.className("XCUIElementTypeTextField");
	private By selectTypeButton = AppiumBy.accessibilityId("Select type");
	private By procedureType2 = AppiumBy.accessibilityId("Procedure 2");
	private By commonElement2 = By.className("XCUIElementTypeImage");
	private By okButton = AppiumBy.accessibilityId("OK");
	private By logProcedureBtn = By.xpath("//XCUIElementTypeButton[@name='Log Procedure']");
	private By checkMark = AppiumBy.accessibilityId("Success Icon");
	private By gotoDashboardButton = AppiumBy.accessibilityId("Go to Dashboard");



	public void startProcedureLog() {
		driver.findElement(selectActions).click();
		driver.findElement(logProcedureButton).click();
	}
	public void proceduretitle(String title) {
		driver.findElements(commonElement1).get(0).sendKeys(title);
	}
	public void procedureDescription(String description) {
		driver.findElements(commonElement1).get(2).sendKeys(description);
	}
	
	public void healthProvider(String healthProvider) {
		driver.findElements(commonElement1).get(4)
				.sendKeys(healthProvider);
	}
	public void procedureType() {
		driver.findElement(selectTypeButton).click();
		driver.findElement(procedureType2).click();
	}
	public void procedurePrep(String prep) {
		driver.findElements(commonElement1).get(6).sendKeys(prep);
		driver.navigate().back();
	}
	public void procedureCare(String Care) {
			driver.findElements(commonElement1).get(8).sendKeys(Care);
		driver.navigate().back();
	}

	public void dateAndTime() {
		driver.findElements(commonElement2).get(2).click();
		driver.findElement(okButton).click();

//			set time
		driver.findElements(commonElement2).get(4).click();
		driver.findElement(okButton).click();

//			click the log procedure button
		driver.findElement(logProcedureBtn).click();

	}

	public boolean ValidateProcedureLog() {
		return driver.findElement(checkMark).isDisplayed();

	}
    
	public void returnHome() {
		driver.findElement(gotoDashboardButton).click();

	}
}
