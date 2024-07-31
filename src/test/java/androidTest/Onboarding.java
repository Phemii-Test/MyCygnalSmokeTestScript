package androidTest;





	import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.html.parser.DocumentParser;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;
import com.mailslurp.apis.EmailControllerApi;
import com.mailslurp.apis.InboxControllerApi;
import com.mailslurp.apis.WaitForControllerApi;
import com.mailslurp.clients.ApiClient;
import com.mailslurp.clients.ApiException;
import com.mailslurp.clients.Configuration;
import com.mailslurp.models.Email;
import com.mailslurp.models.EmailTextLinesResult;
import com.mailslurp.models.InboxDto;
import java.util.UUID;



import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

	public class Onboarding {

		AppiumDriver driver;
		InboxDto inbox;
	    String otp;
	    
	    ApiClient defaultClient = Configuration.getDefaultApiClient();

		

		@BeforeTest
		public void Setup() throws ApiException {
			defaultClient.setApiKey("238dbc0bcd01f3c8b7d4cd42b91b7a80b8af2cf4e5fd7487c311f089562dc43a");
			InboxControllerApi inboxControllerApi = new InboxControllerApi(defaultClient);
			

			 inbox = inboxControllerApi.createInboxWithDefaults();
	         assertNotNull(inbox, "Inbox creation failed. Inbox is null.");
	         assertEquals(inbox.getEmailAddress().contains("@mailslurp"), true, "Email address is not valid.");


			DesiredCapabilities cap = new DesiredCapabilities();

			cap.setCapability("deviceName", "VGP Phone");
			cap.setCapability("platformName", "Android");
			cap.setCapability("platformVersion", "10");
			cap.setCapability("udid", "emulator-5554");
			cap.setCapability("app", "/Users/olufemiomeiza/Downloads/app-release-2.apk");
			cap.setCapability("appPackage", "com.mycygnal.mycygnal");
			cap.setCapability("appActivity", "com.mycygnal.mycygnal.MainActivity");
			cap.setCapability("automationName", "appium");
			try {
				driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}

		@Test(priority = 0)
		public void clickForwardButton() {
			driver.findElement(By.xpath("//android.widget.Button")).click();
			driver.findElement(AppiumBy.className("android.widget.Button")).click();
		}

		@Test(priority = 1)
		public void signUpForm() throws InterruptedException {
			// Click the signup button
//			driver.findElement(AppiumBy.accessibilityId("Sign Up")).click();

			// Fill first name
			WebElement firstNameField = driver.findElement(By.xpath("//android.widget.ImageView"));
			firstNameField.click();
			firstNameField.sendKeys("Olufemi");

			// Fill last name
			WebElement lastName = driver.findElements(By.xpath("//android.widget.ImageView")).get(1);
			lastName.click();
			lastName.sendKeys("Omeiza");

			// Fill email

			WebElement email = driver.findElements(By.xpath("//android.widget.ImageView")).get(2);
			email.click();
			email.sendKeys(inbox.getEmailAddress());

//			fill Mobile number
			WebElement MobileNum=driver.findElements(By.className("android.widget.EditText")).get(3);
			MobileNum.click();
			MobileNum.sendKeys("+13473826505");

//			fill Password
			WebElement password = driver.findElements(By.className("android.widget.ImageView")).get(3);
			password.click();
			password.sendKeys("Hbon@1234");
			driver.navigate().back();


			// Check the terms and condition box and click signup button
			driver.findElement(By.xpath("//android.widget.CheckBox")).click();
			driver.findElement(AppiumBy.accessibilityId("Sign Up")).click();
			Thread.sleep(10000);


	    }


		@Test(priority = 2)
		public void OTP() throws ApiException, InterruptedException  {
			
			WaitForControllerApi waitForControllerApi = new WaitForControllerApi(defaultClient);
			Email email = waitForControllerApi.waitForLatestEmail(inbox.getId(), null, null, null, null, null, null);
			
		
//			String emailBody = email.getBody().trim().replaceAll("[^\\d.]+", "");
			EmailTextLinesResult extratctedEmailBody = new EmailControllerApi(defaultClient).getEmailHTMLQuery(email.getId(), ".code");
			CharSequence emailBody = String.valueOf(extratctedEmailBody);
            
            Pattern otpPattern = Pattern.compile("\\b\\d{6}\\b");
            Matcher matcher = otpPattern.matcher(emailBody);

            if (matcher.find()) {
                String otp = matcher.group();
                WebElement otpField = driver.findElements(By.className("android.widget.EditText")).get(0);
                otpField.click();
                otpField.clear(); 
               
                otpField.sendKeys(otp);
            } else {
                System.out.println("OTP not found in the email body.");
            }
               
	       
//	        click the verify button
			driver.findElement(AppiumBy.accessibilityId("Verify")).click();
//	        Validate that the verification was successful
			
			Thread.sleep(5000);

		}

		

		@Test(priority = 3)
		public void HealthAssessment() throws InterruptedException {
//			click the goto health assessment button 
			driver.findElements(By.className("android.widget.Button")).get(0).click();
			
//			select DOB
			 driver.findElements(By.className("android.widget.Button")).get(2).click();
			 driver.findElements(By.className("android.view.View")).get(11).click();
			 driver.findElements(By.className("android.widget.Button")).get(4).click();
			 
//		Select male gender
			 driver.findElements(By.className("android.widget.CheckBox")).get(0).click();
			 driver.findElements(By.className("android.widget.Button")).get(1).click();
			 
//			 Select height
			 driver.findElements(By.className("android.view.View")).get(5).click(); /* toggle the FT button*/
			 WebElement height= driver.findElements(By.className("android.view.View")).get(8);
			 ((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
						ImmutableMap.of("elementId", ((RemoteWebElement) height).getId(), "endX", 590, "endY", 1460));
			 Thread.sleep(5000);
			 driver.findElements(By.className("android.widget.Button")).get(1).click();

//			select Weight
			 driver.findElements(By.className("android.view.View")).get(5).click(); /* toggle the lbs button*/
			 WebElement weight= driver.findElements(By.className("android.view.View")).get(8);
			 ((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
						ImmutableMap.of("elementId", ((RemoteWebElement) weight).getId(), "endX", 590, "endY", 1460));
			 Thread.sleep(5000);
			 driver.findElements(By.className("android.widget.Button")).get(1).click();
			 

//			 Sleep quality
			 driver.findElements(By.className("android.view.View")).get(6).click();
			 driver.findElements(By.className("android.widget.Button")).get(1).click();
			 
//		skip blood group
			 driver.findElements(By.className("android.widget.Button")).get(2).click();

//       skip symptoms
			 driver.findElements(By.className("android.widget.Button")).get(2).click();
			 
//		click submit button
			 WebElement baloon= driver.findElement(AppiumBy.accessibilityId("Balloons"));
			 ((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
						ImmutableMap.of("elementId", ((RemoteWebElement) baloon).getId(), "endX", 276, "endY", 569));
			 
			 driver.findElements(By.className("android.widget.Button")).get(1).click();
			 Thread.sleep(5000);

		}
		
		@Test(priority=4)
		public void verifyAssessmentCompleted() throws InterruptedException {
			String actualtxt= driver.findElements(By.className("android.view.View")).get(3).getText();
			String ExpectedTxt= "You have now completed the basic assessment section, please proceed to your dashboard";
			Assert.assertEquals(actualtxt, ExpectedTxt);
		
		}
		@Test(priority=5)
		public void clickLearnYourCygnalBtn() throws InterruptedException {
			
			driver.findElement(By.className("android.widget.Button")).click();
		 Thread.sleep(5000);

		}
		@Test(priority=6)

			public boolean recommendationTabIsDisplayed() {
				return driver.findElements(By.className("android.widget.ImageView")).get(0).isDisplayed();
			
		}
		
		@Test(priority=7)
		
		public void deleteAccount() throws InterruptedException {
			driver.findElements(By.className("android.widget.ImageView")).get(12).click();
			
			 WebElement pInfo= driver.findElement(AppiumBy.accessibilityId("Personal Info"));
			 ((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
						ImmutableMap.of("elementId", ((RemoteWebElement) pInfo).getId(), "endX", 450, "endY", 132));
			 
			driver.findElements(By.className("android.widget.ImageView")).get(6).click();
 
			driver.findElements(By.className("android.widget.Button")).get(0).click();
			 Thread.sleep(5000);

		}
		
		@Test(priority=8)
		public boolean welcomeBackTextIsDisplayed() {
			return driver.findElement(AppiumBy.accessibilityId("Welcome Back")).isDisplayed();
		}
	}
	

