package vgplatform_inc;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class ElementLocators {

	AndroidDriver driver;

	public static void loginlocators() {

		By forwardButton = By.xpath("//android.widget.Button");
		By checkBox = By.className("android.widget.CheckBox");
		By loginButton = AppiumBy.accessibilityId("Login");
		By emailField = By.xpath("//android.widget.ImageView[1]");
		By passwordField = By.className("android.widget.ImageView");

	}

}
