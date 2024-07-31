package iOSTest;

import java.net.MalformedURLException;
import java.net.URL;
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
import io.appium.java_client.android.AndroidDriver;

public class LogBPiOS {

	AppiumDriver driver;

	 @BeforeTest
	    public void Setup() throws MalformedURLException {

	        DesiredCapabilities cap = new DesiredCapabilities();

	        
	        cap.setCapability("platformName", "iOS");
	        cap.setCapability("automationName", "XCUITest");
	        cap.setCapability("udid", "293CB621-315F-4285-922D-9DD8D1F45AC5");
	        cap.setCapability("bundleId","com.mycygnal.mycygnal" );
	        cap.setCapability("deviceName", "iPhone 15");
	        cap.setCapability("platformVersion", "17.5");
//	        cap.setCapability("usePreinstalledWDA", "true");
//	        cap.setCapability("app", "/Users/olufemiomeiza/Downloads/Runner-2.app");
	       
	        try {
				driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		}
	    @Test(priority=0)
	    public void Login() throws InterruptedException {
//	        WebElement nextBtn=driver.findElement(By.className("XCUIElementTypeButton"));
//	        nextBtn.click();
//	        Thread.sleep(2000);
//	        
//	        WebElement nextBtn1=driver.findElement(By.className("XCUIElementTypeButton"));
//	        nextBtn1.click();	        
//
//	        driver.findElement(AppiumBy.accessibilityId("Login")).click();
	        
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

public void logBP() throws InterruptedException {
		driver.findElement(AppiumBy.accessibilityId("Blood Pressure icon")).click();
		
		Thread.sleep(2000);

		driver.findElement(AppiumBy.accessibilityId("Add new blood pressure record")).click();
//		log systolic BP
		Thread.sleep(2000);
		WebElement systolic= driver.findElement(AppiumBy.accessibilityId("0\\n90\\n180\\n270\\n360\\n450\\n540\\n630\\n720\\n810\\n900"));
		
		HashMap<String, Object> params = new HashMap<>();
        params.put("elementId", ((RemoteWebElement) systolic).getId());
        params.put("startX", 287);
        params.put("startY", 428);
        params.put("endX", 549);
        params.put("endY", 1171);
        params.put("duration", 1.0); // duration in seconds

        ((JavascriptExecutor) driver).executeScript("mobile: scroll", params);
        Thread.sleep(2000);
      
		((JavascriptExecutor) driver).executeScript("mobile: scroll", ImmutableMap.of(
				"startX", 287,
	    	    "startY", 428,
	    	    "endX", 549,
	    	    "endY", 1171
	    	));

//		Perform a scroll up gesture.
		WebElement scrollup = driver.findElement(AppiumBy.accessibilityId("What is your diastolic blood pressure?"));
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
	    	    "elementId", ((RemoteWebElement) scrollup).getId(),
	    	    "endX", 177,
	    	    "endY", 149
	    	));

//		log diastolic BP
		Thread.sleep(2000);
		driver.findElement(AppiumBy.accessibilityId("0\\n90\\n180\\n270\\n360\\n450\\n540\\n630\\n720\\n810\\n900")).click();
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
				 "startX", 311,
	     	    "startY", 275,
	     	    "endX", 205,
	     	    "endY", 274
	    	));
//		select date
		driver.findElements(By.className("XCUIElementTypeImage")).get(6).click();
		driver.findElement(AppiumBy.accessibilityId("20, Thursday, June 20, 2024")).click();
		driver.findElement(AppiumBy.accessibilityId("OK")).click();

//		Set time.
		WebElement setTime=driver.findElements(By.className("XCUIElementTypeImage")).get(8);
		setTime.click();
		WebElement StartMinute = driver.findElement(By.xpath("XCUIElementTypeScrollView"));
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) StartMinute).getId(), "endX", 199, "endY", 393));
		WebElement Startseconds = driver.findElement(By.xpath("XCUIElementTypeScrollView"));
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) Startseconds).getId(), "endX", 202, "endY", 391));
		driver.findElement(AppiumBy.accessibilityId("OK")).click();

//		((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
//				ImmutableMap.of("elementId", ((RemoteWebElement)setTime ).getId(), "endX", 538, "endY", 530));

//		Select the yes I'm on medication option

		driver.findElement(AppiumBy.accessibilityId("Yes, I'm on medication")).click();

////		Perform a second scroll up gesture.
//		WebElement scrollup2 = driver.findElement(AppiumBy.accessibilityId("Are you on related medication?"));
//		((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
//	    	    "elementId", ((RemoteWebElement) scrollup2).getId(),
//	    	    "endX", 481,
//	    	    "endY", 637
//	    	));

		driver.findElement(AppiumBy.accessibilityId("Record Blood Pressure")).click();
	}

	@Test(priority = 2)
	public void verifyBPLog () throws InterruptedException {
//		A checkmark icon is displayed.
		boolean successIcon = driver.findElement(AppiumBy.accessibilityId("Success Icon")).isDisplayed();
		Assert.assertTrue(successIcon);
	//Validate that the add related medication is displayed since I specified I'm on medication
		boolean RecordMedBtn = driver.findElement(AppiumBy.accessibilityId("Record Related Medication")).isDisplayed();
		Assert.assertTrue(RecordMedBtn);

//		Click view BP button
		driver.findElement(AppiumBy.accessibilityId("View Blood Pressure")).click();

		driver.findElement(By.xpath("//android.widget.Button")).click();


	}

}
