package iOSTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class AddMedicationiOS {
	
	AppiumDriver driver;

	 @BeforeTest
	    public void Setup() throws MalformedURLException {

	        DesiredCapabilities cap = new DesiredCapabilities();

	        
	        cap.setCapability("platformName", "iOS");
	        cap.setCapability("automationName", "XCUITest");
	        cap.setCapability("udid", "19E35E91-B86A-497A-90FC-12B61C30E7DF");
	        cap.setCapability("bundleId","com.mycygnal.mycygnal" );
	        cap.setCapability("deviceName", "iPad (10th generation)");
	        cap.setCapability("platformVersion", "17.5");
//	        cap.setCapability("usePreinstalledWDA", "true");
	        cap.setCapability("app", "/Users/olufemiomeiza/Downloads/Runner-4.app");
	       
	        try {
				driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		}
	    @Test(priority=0)
	    public void Login() throws InterruptedException {
	    	 WebElement nextBtn=driver.findElement(By.className("XCUIElementTypeButton"));
		        nextBtn.click();
		        Thread.sleep(2000);
		        
		        driver.findElement(AppiumBy.accessibilityId("Next page button")).click();
		        
	        driver.findElement(AppiumBy.accessibilityId("Login")).click();
////	        
	        WebElement emailField = driver.findElements(By.className("XCUIElementTypeTextField")).get(0);
	        emailField.click();
	        emailField.sendKeys("Ohlufehmii@gmail.com");

	        WebElement passwordField = driver.findElements(By.className("XCUIElementTypeTextField")).get(2);
	        passwordField.click();
	        passwordField.sendKeys("Hbon@1234");

	        driver.findElement(AppiumBy.accessibilityId("Login")).click();
	        
	        try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

	    }
	    @Test(priority=1)
		public void addMedication() throws InterruptedException  {
	    	
			WebElement actionsButton= driver.findElement(AppiumBy.accessibilityId("Medical Log Page Navigation Icon"));
			actionsButton.click();
			driver.findElement(AppiumBy.accessibilityId("Add Medication")).click();

//			Fill med form

			WebElement addMedName=driver.findElements(By.className("XCUIElementTypeTextField")).get(0);
			addMedName.click();
			addMedName.sendKeys("Lesinopril");

			WebElement Dosage=driver.findElements(By.className("XCUIElementTypeTextField")).get(2);
			Dosage.click();
			Dosage.sendKeys("2");


		       WebElement Frequency = driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeOther[`value == \"0%\"`]"));
		       Frequency.sendKeys("1%");
		       System.out.println(Frequency.getAttribute("value"));
	            Thread.sleep(2000);
		      
//			Select Category
			
			WebElement category=driver.findElements(By.className("XCUIElementTypeStaticText")).get(9);
			category.click();

//		    select start date
			driver.findElements(By.className("XCUIElementTypeImage")).get(0).click();
			driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`name == \"22, Saturday, June 22, 2024\"`]")).click();
			driver.findElement(AppiumBy.accessibilityId("OK")).click();

//			select end date
			WebElement endDate=driver.findElements(By.className("XCUIElementTypeImage")).get(2);
			endDate.click();
			driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`name == \"22, Saturday, June 22, 2024\"`]")).click();
			WebElement ok= driver.findElement(AppiumBy.accessibilityId("OK"));
			ok.click();

//			click the add medication button
			driver.findElement(AppiumBy.name("Add Medication")).click();

//			Verify that medication has been added.
			boolean icon = driver.findElement(AppiumBy.accessibilityId("Success Icon")).isDisplayed();
			Assert.assertTrue(icon);

//			String ActualsuccessTxt= driver.findElement(AppiumBy.accessibilityId("You have added a new medication")).getText();
//			String ExpectedSuccessTxt= "You have added a new medication";
//			Assert.assertEquals(ActualsuccessTxt, ExpectedSuccessTxt);

			}
		@Test(priority=2)
		public void SetMedReminder() throws InterruptedException {
			driver.findElement(AppiumBy.accessibilityId("View Medication")).click();

//			Add first dose


			WebElement first =driver.findElements(By.className("android.widget.ImageView")).get(0);
			first.click();
			WebElement StartMinute = driver.findElement(By.xpath("//android.view.View"));
			((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
				    "elementId", ((RemoteWebElement) StartMinute).getId(),
				    "endX", 372,
				    "endY", 1625
				));
			WebElement Startseconds = driver.findElement(By.xpath("//android.view.View"));
			((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
				    "elementId", ((RemoteWebElement) Startseconds).getId(),
				    "endX", 675,
				    "endY", 1310
				));
			driver.findElement(AppiumBy.accessibilityId("OK")).click();

			driver.findElements(By.className("android.widget.ImageView")).get(2).click();
			WebElement EndMinute = driver.findElement(By.xpath("//android.view.View"));
			((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
				    "elementId", ((RemoteWebElement) EndMinute).getId(),
				    "endX", 372,
				    "endY", 1625
				));
			WebElement Endseconds = driver.findElement(By.xpath("//android.view.View"));
			((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
				    "elementId", ((RemoteWebElement) Endseconds).getId(),
				    "endX", 944,
				    "endY", 1499
				));
			driver.findElement(AppiumBy.accessibilityId("OK")).click();

////			SCROLL UP
////			((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
////				    "elementId", ((RemoteWebElement) Frequency).getId(),
////				    "endX", 351,
////				    "endY", 992
////				));
//
//
//
//
////			Add second dose
			WebElement dose2field= driver.findElements(By.className("android.widget.ImageView")).get(4);
			dose2field.click();
			WebElement StartMinute2 = driver.findElement(By.xpath("//android.view.View"));
			((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
				    "elementId", ((RemoteWebElement) StartMinute2).getId(),
				    "endX", 664,
				    "endY", 1322
				));
			WebElement Startseconds2 = driver.findElement(By.xpath("//android.view.View"));
			((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
				    "elementId", ((RemoteWebElement) Startseconds2).getId(),
				    "endX", 675,
				    "endY", 1310
				));
			driver.findElement(AppiumBy.accessibilityId("PM")).click();
			driver.findElement(AppiumBy.accessibilityId("OK")).click();

			driver.findElements(By.className("android.widget.ImageView")).get(6).click();
			WebElement EndMinute2 = driver.findElement(By.xpath("//android.view.View"));
			((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
				    "elementId", ((RemoteWebElement) EndMinute2).getId(),
				    "endX", 664,
				    "endY", 1322
				));
			WebElement Endseconds2 = driver.findElement(By.xpath("//android.view.View"));
			((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
				    "elementId", ((RemoteWebElement) Endseconds2).getId(),
				    "endX", 944,
				    "endY", 1499
				));
			driver.findElement(AppiumBy.accessibilityId("PM")).click();
			driver.findElement(AppiumBy.accessibilityId("OK")).click();

////			Add third dose
			driver.findElements(By.className("android.widget.ImageView")).get(8).click();
			WebElement StartMinute3 = driver.findElement(By.xpath("//android.view.View"));
			((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
				    "elementId", ((RemoteWebElement) StartMinute3).getId(),
				    "endX", 973,
				    "endY", 1630
				));
			WebElement Startseconds3 = driver.findElement(By.xpath("//android.view.View"));
			((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
				    "elementId", ((RemoteWebElement) Startseconds3).getId(),
				    "endX", 675,
				    "endY", 1310
				));
			driver.findElement(AppiumBy.accessibilityId("PM")).click();
			driver.findElement(AppiumBy.accessibilityId("OK")).click();

			driver.findElements(By.className("android.widget.ImageView")).get(10).click();
			WebElement EndMinute3 = driver.findElement(By.xpath("//android.view.View"));
			((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
				    "elementId", ((RemoteWebElement) EndMinute3).getId(),
				    "endX", 973,
				    "endY", 1630
				));
			WebElement Endseconds3 = driver.findElement(By.xpath("//android.view.View"));
			((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
				    "elementId", ((RemoteWebElement) Endseconds3).getId(),
				    "endX", 944,
				    "endY", 1499
				));
			driver.findElement(AppiumBy.accessibilityId("PM")).click();
			driver.findElement(AppiumBy.accessibilityId("OK")).click();

//			Toggle alarm
			WebElement Alarm = driver.findElement(AppiumBy.accessibilityId("Toggle Alarm On"));
			Alarm.click();
//			Click set reminder button
			Thread.sleep(2000);
			driver.findElements(By.className("android.widget.Button")).get(4).click();

		}
	@Test(priority=3)
	public void ValidateReminderSet() {
		boolean successCheckMark= driver.findElement(AppiumBy.accessibilityId("Success Icon")).isDisplayed();
		Assert.assertTrue(successCheckMark);

//		Go to the medication management page
		driver.findElement(AppiumBy.accessibilityId("Go to Medications")).click();

//		Click the back button to get back to the dashboard.
		driver.findElement(By.xpath("//android.widget.Button")).click();
//	}
}
}