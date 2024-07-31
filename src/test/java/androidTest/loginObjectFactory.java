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

public class loginObjectFactory {

	private AndroidDriver driver;

	public loginObjectFactory(AndroidDriver driver) {
		this.driver = driver;
	}

	private By forwardButton = By.xpath("//android.widget.Button");
	private By checkBox = By.className("android.widget.CheckBox");
	private By loginButton = AppiumBy.accessibilityId("Login");
	private By emailField = By.xpath("//android.widget.ImageView[1]");
	private By passwordField = By.className("android.widget.ImageView");

	public void clickForwardButton() throws InterruptedException {
		Thread.sleep(5000);
		driver.findElement(forwardButton).click();
	}
	public void clickForwardButton2() throws InterruptedException {
		driver.findElement(forwardButton).click();
	}

	public void dragCheckBox() {
		WebElement checkbox = driver.findElement(checkBox);
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) checkbox).getId(), "endX", 442, "endY", 517));
	}

	public void SelectLogin() {
		driver.findElement(loginButton).click();
	}

	public void enterLoginDetails(String email, String password) throws InterruptedException {
		WebElement emailElement = driver.findElement(emailField);
		emailElement.click();
		emailElement.sendKeys(email);

		WebElement passwordElement = driver.findElements(passwordField).get(1);
		passwordElement.click();
		passwordElement.sendKeys(password);
	}

	public void clickLoginButton() throws InterruptedException {
		driver.findElement(loginButton).click();
		Thread.sleep(2000);
	}
}
