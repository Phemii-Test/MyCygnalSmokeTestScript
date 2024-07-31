package androidTest;

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
import org.testng.ITestListener;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class e2eTest {
	 AndroidDriver driver;

	
	
		@BeforeTest
		public void Setup() throws MalformedURLException {

			DesiredCapabilities cap = new DesiredCapabilities();

			cap.setCapability("deviceName", "VGP Phone");
			cap.setCapability("platformName", "Android");
			cap.setCapability("platformVersion", "10");
			cap.setCapability("udid", "emulator-5554");
//			cap.setCapability("app", "/Users/olufemiomeiza/Downloads/app-release-9.apk");
			cap.setCapability("appPackage", "com.mycygnal.mycygnal");
			cap.setCapability("appActivity", "com.mycygnal.mycygnal.MainActivity");
			cap.setCapability("automationName", "appium");

			try {
				driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}

		}
		@Test(priority = 0)

		public void clickForwardButton() throws InterruptedException {
			Thread.sleep(5000);
			driver.findElement(By.xpath("//android.widget.Button")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//android.widget.Button")).click();
			
			driver.findElement(AppiumBy.accessibilityId("Login")).click();
			
			WebElement emailElement = driver.findElement(By.xpath("//android.widget.ImageView[1]"));
			emailElement.click();
			emailElement.sendKeys("ohlufehmii@gmail.com");

			WebElement passwordElement = driver.findElements(By.className("android.widget.ImageView")).get(1);
			passwordElement.click();
			passwordElement.sendKeys("Hbon@1234");
		
			driver.findElement(AppiumBy.accessibilityId("Login")).click();
			Thread.sleep(2000);
		}
		@Test(priority = 1)
		public void FirstQuestioneer() {
			driver.findElement(AppiumBy.accessibilityId("Recommendations icon")).click();
			driver.findElement(AppiumBy.accessibilityId("Go to Assessment")).click();
//	Do you currently smoke response
	driver.findElement(AppiumBy.accessibilityId("No, I do not smoke")).click();
			driver.findElement(AppiumBy.accessibilityId("Continue")).click();
/*
//	For how many years have you smoked?
			WebElement scroll = driver.findElements(By.className("android.view.View")).get(4);
			((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
					ImmutableMap.of("elementId", ((RemoteWebElement) scroll).getId(), "endX", 542, "endY", 618));

//	How many packs of cigarettes do you typically smoke in a day?
			WebElement scroll2 = driver.findElements(By.className("android.view.View")).get(11);
			((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
					ImmutableMap.of("elementId", ((RemoteWebElement) scroll2).getId(), "endX", 324, "endY", 1881));
*/
//	perform a Scroll upward.
			WebElement scrollUp = driver.findElements(By.className("android.view.View")).get(12);
			((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
					ImmutableMap.of("elementId", ((RemoteWebElement) scrollUp).getId(), "endX", 561, "endY", 660));

			driver.findElement(AppiumBy.accessibilityId("Continue")).click();

//	Have you smoked cigarettes in the past?
			driver.findElement(AppiumBy.accessibilityId(" Yes, I smoke")).click();
			driver.findElement(AppiumBy.accessibilityId("Continue")).click();

//	For how many years did you smoke before you quit?
			WebElement scroll3 = driver.findElements(By.className("android.view.View")).get(4);
			((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
					ImmutableMap.of("elementId", ((RemoteWebElement) scroll3).getId(), "endX", 542, "endY", 618));

//	How many packs of cigarettes do you typically smoke in a day?
			WebElement scroll4 = driver.findElements(By.className("android.view.View")).get(11);
			((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
					ImmutableMap.of("elementId", ((RemoteWebElement) scroll4).getId(), "endX", 324, "endY", 1881));
//	perform a Scroll upward.
			WebElement scrollUp2 = driver.findElements(By.className("android.view.View")).get(12);
			((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
					ImmutableMap.of("elementId", ((RemoteWebElement) scrollUp2).getId(), "endX", 561, "endY", 660));

			driver.findElement(AppiumBy.accessibilityId("Continue")).click();

		}

		@Test(priority = 2)
		public void secondQuestioneer() throws InterruptedException {
//	click the vaccines that have been taken.
			driver.findElements(By.className("android.view.View")).get(3).click();
			driver.findElements(By.className("android.view.View")).get(4).click();
			driver.findElements(By.className("android.view.View")).get(5).click();
			driver.findElements(By.className("android.view.View")).get(6).click();
			WebElement scroll = driver.findElements(By.className("android.view.View")).get(7);
			scroll.click();

//	scroll up and click the continue button
			((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
					ImmutableMap.of("elementId", ((RemoteWebElement) scroll).getId(), "endX", 450, "endY", 961));

			driver.findElement(AppiumBy.accessibilityId("Continue")).click();

//	select the vaccines that have been taken
			driver.findElements(By.className("android.view.View")).get(3).click();
			driver.findElements(By.className("android.view.View")).get(4).click();
			driver.findElements(By.className("android.view.View")).get(5).click();

//    Click the submit button
			driver.findElement(AppiumBy.accessibilityId("Submit")).click();

			driver.wait(1000);

//	Validate that the questioneer filled was successful.
			boolean recoDashoardWelcome = driver.findElement(AppiumBy.accessibilityId(
					"👋\\nWe are so glad you are\\ntaking steps to own your\\nhealth\\nReview each category below and learn how to live your most healthy life.\\n\\nYou may update your information on the dashboard at any time to keep the recommendations current"))
					.isDisplayed();
			Assert.assertTrue(recoDashoardWelcome);

		}

		@Test(priority = 3)
		public void checkRecommendations() {
//click the see recommendation button
			driver.findElement(AppiumBy.accessibilityId("See Recommendation")).click();
//	validate the recommendations are displayed.
			boolean firstRecommendation = driver.findElement(AppiumBy.accessibilityId("HPV Vaccine")).isDisplayed();
			Assert.assertTrue(firstRecommendation);

			boolean SecondRecommendation = driver
					.findElement(AppiumBy.accessibilityId("Updated COVID-19 Vaccines (2023â2024)")).isDisplayed();
			Assert.assertTrue(SecondRecommendation);

		}
		

		
		}
	

