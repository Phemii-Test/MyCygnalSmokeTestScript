package vgplatform_inc;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class loginObject {

	private AndroidDriver driver;

	public void ElementLocators(AndroidDriver driver) {
		this.driver = driver;
	}
	/*
	 * 
	 * public void clickForwardButton() throws InterruptedException {
	 * Thread.sleep(5000); driver.findElement(forwardButton).click(); }
	 * 
	 * public void dragCheckBox() { WebElement checkbox =
	 * driver.findElement(checkBox); ((JavascriptExecutor)
	 * driver).executeScript("mobile: dragGesture", ImmutableMap.of("elementId",
	 * ((RemoteWebElement) checkbox).getId(), "endX", 442, "endY", 517)); }
	 * 
	 * public void SelectLogin() { driver.findElement(loginButton).click(); }
	 * 
	 * public void enterLoginDetails(String email, String password) throws
	 * InterruptedException { WebElement emailElement =
	 * driver.findElement(emailField); emailElement.click();
	 * emailElement.sendKeys(email);
	 * 
	 * WebElement passwordElement = driver.findElements(passwordField).get(1);
	 * passwordElement.click(); passwordElement.sendKeys(password); }
	 * 
	 * public void clickLoginButton() throws InterruptedException {
	 * driver.findElement(loginButton).click(); Thread.sleep(5000); }
	 */

}
