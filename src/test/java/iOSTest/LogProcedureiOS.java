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
import io.appium.java_client.ios.IOSDriver;

public class LogProcedureiOS {

	IOSDriver driver;

	 @BeforeTest
	    public void Setup() throws MalformedURLException {

	        DesiredCapabilities cap = new DesiredCapabilities();

	        
	        cap.setCapability("platformName", "iOS");
	        cap.setCapability("automationName", "XCUITest");
	        cap.setCapability("udid", "028C43A1-95F0-4994-9D0E-FCB7FB054F6F");
	        cap.setCapability("bundleId","com.mycygnal.mycygnal" );
	        cap.setCapability("deviceName", "iPad (10th generation)");
	        cap.setCapability("platformVersion", "17.5");
//	        cap.setCapability("usePreinstalledWDA", "true");
	        cap.setCapability("app", "/Users/olufemiomeiza/Downloads/Runner-6.app");
	       
	        try {
				driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
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
	        
	        WebElement nextBtn1=driver.findElement(By.className("XCUIElementTypeButton"));
	        nextBtn1.click();	        

	        driver.findElement(AppiumBy.accessibilityId("Login")).click();
	        
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
	    public void logsymptoms() throws InterruptedException {
	    	driver.findElement(AppiumBy.accessibilityId("Select Actions Button")).click();
	    	driver.findElement(AppiumBy.accessibilityId("Log Procedure")).click(); 
//	    	procedure title
	    	driver.findElements(By.className("XCUIElementTypeTextField")).get(0).sendKeys("Ceaserial Session");
	    	
//	    	procedure description
	    	driver.findElements(By.className("XCUIElementTypeTextField")).get(2).sendKeys("Delivery through operation");
	    	
//	    	Health provider
	    	driver.findElements(By.className("XCUIElementTypeTextField")).get(4).sendKeys("Rev Fr, Burke Memorial Hospital");
	    	
//	    	Procedure type
	    	driver.findElement(AppiumBy.accessibilityId("Select type")).click();
	    	driver.findElement(AppiumBy.accessibilityId("Procedure 2")).click();

//	    	procedure preparation
	    	driver.findElements(By.className("XCUIElementTypeTextField")).get(6).sendKeys("Ultrasound scan");
			driver.navigate().back();

//	    	post procedure care.
	    	driver.findElements(By.className("XCUIElementTypeTextField")).get(8).sendKeys("Post procedure dressing.");
			driver.navigate().back();

//			driver.findElement(AppiumBy.accessibilityId("Hide keyboard")).click();
			
//			Upload file
			driver.findElement(AppiumBy.accessibilityId("Select File")).click();
			Thread.sleep(5000);
			driver.findElements(By.className("XCUIElementTypeImage")).get(24).click();
			Thread.sleep(3000);
			driver.findElement(AppiumBy.accessibilityId("OK")).click();
			
//			Set date
			driver.findElements(By.className("XCUIElementTypeImage")).get(2).click();
			driver.findElement(AppiumBy.accessibilityId("OK")).click();
			
//			set time
			driver.findElements(By.className("XCUIElementTypeImage")).get(4).click();
			driver.findElement(AppiumBy.accessibilityId("OK")).click();
			
//			click the log procedure button
			driver.findElements(By.className("XCUIElementTypeButton")).get(3).click();
					

	    }
	    @Test(priority = 2)
		public void ValidateProcedureLog() {
//		 A checkmark icon is displayed.
			boolean successIcon = driver.findElement(AppiumBy.accessibilityId("Success Icon")).isDisplayed();
			Assert.assertTrue(successIcon);

//			Navigate to the dashboard
			driver.findElement(AppiumBy.accessibilityId("Go to Dashboard")).click();
		}

}
