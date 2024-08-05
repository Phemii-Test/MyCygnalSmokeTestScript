package iOSTest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
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

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;

public class iOS_Onboarding {

	AppiumDriver driver;
	InboxDto inbox;
	String otp;

	ApiClient defaultClient = Configuration.getDefaultApiClient();

	@BeforeTest
	public void Setup() throws MalformedURLException, ApiException {

		defaultClient.setApiKey("f53a1da18ea522580705ca6199318dd7e8a14f30f7339900c8f900f7f95d4ad6");
		InboxControllerApi inboxControllerApi = new InboxControllerApi(defaultClient);

		inbox = inboxControllerApi.createInboxWithDefaults();
		assertNotNull(inbox, "Inbox creation failed. Inbox is null.");
		assertEquals(inbox.getEmailAddress().contains("@mailslurp"), true, "Email address is not valid.");

		DesiredCapabilities cap = new DesiredCapabilities();

		cap.setCapability("platformName", "iOS");
		cap.setCapability("automationName", "XCUITest");
		cap.setCapability("udid", "293CB621-315F-4285-922D-9DD8D1F45AC5");
		cap.setCapability("bundleId", "com.mycygnal.mycygnal");
		cap.setCapability("deviceName", "iPad (10th generation)");
		cap.setCapability("platformVersion", "17.5");
		cap.setCapability("app", "/Users/olufemiomeiza/Downloads/Runner-6.app");

		try {
			driver = new AppiumDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test(priority = 1)
	public void clickForwardButton() throws InterruptedException {
		driver.findElement(By.className("XCUIElementTypeButton")).click();
		Thread.sleep(5000);
		driver.findElement(By.className("XCUIElementTypeButton")).click();
		Thread.sleep(5000);
	}

	@Test(priority = 2)
	public void fillSignupForm() throws InterruptedException {
//		FirstName field
		driver.findElements(By.className("XCUIElementTypeTextField")).get(0).sendKeys("Olufemi");

//		Last name field
		driver.findElements(By.className("XCUIElementTypeTextField")).get(2).sendKeys("Hbon");

//		Email field
		driver.findElements(By.className("XCUIElementTypeTextField")).get(4).sendKeys(inbox.getEmailAddress());

//		enterMobile number
		driver.findElements(By.className("XCUIElementTypeTextField")).get(6).sendKeys("5177432388");

//	    fillPassword
		driver.findElements(By.className("XCUIElementTypeTextField")).get(7).sendKeys("SoAwesome@1234");

//       checkT&C
		driver.findElement(By.className("XCUIElementTypeSwitch")).click();

//		bring down the keyboard.
		driver.findElement(AppiumBy.accessibilityId("Done")).click();

//		clickSignup button
		driver.findElements(By.className("XCUIElementTypeButton")).get(1).click();

		Thread.sleep(5000);
	}

	@Test(priority = 3)
	public void enterOTP() throws ApiException, InterruptedException {
		WaitForControllerApi waitForControllerApi = new WaitForControllerApi(defaultClient);
		Email email = waitForControllerApi.waitForLatestEmail(inbox.getId(), null, null, null, null, null, null);

		EmailTextLinesResult extratctedEmailBody = new EmailControllerApi(defaultClient)
				.getEmailHTMLQuery(email.getId(), ".code");
		CharSequence emailBody = String.valueOf(extratctedEmailBody);

		Pattern otpPattern = Pattern.compile("\\b\\d{6}\\b");
		Matcher matcher = otpPattern.matcher(emailBody);

		if (matcher.find()) {
			String otp = matcher.group();
			driver.findElement(By.className("XCUIElementTypeTextField")).sendKeys(otp);

		} else {
			System.out.println("OTP not found in the email body.");
		}

//		clickVerifyButton
		driver.findElements(By.className("XCUIElementTypeButton")).get(2).click();
		Thread.sleep(5000);
	}

	@Test(priority = 4)
	public void takeOnboardingAssessment() throws InterruptedException {
//		clickGoto Health Assessment button
		driver.findElement(By.className("XCUIElementTypeButton")).click();

//	    select date
		driver.findElement(AppiumBy.accessibilityId("Previous month")).click();/* click back calendar button */
		driver.findElement(AppiumBy.accessibilityId("1, Tuesday, July 1, 2008")).click();
		driver.findElement(AppiumBy.accessibilityId("Continue")).click(); /* click continue button */

//		select male gender
		driver.findElement(AppiumBy.accessibilityId(" Male")).click();
		driver.findElement(AppiumBy.accessibilityId("Continue")).click(); /* click continue button */
		Thread.sleep(5000);

//		set height
		List<WebElement> elements = driver.findElements(By.className("XCUIElementTypeStaticText"));
		WebElement element = elements.get(6);

		// Define start and end coordinates
		int startX = 287;
		int startY = 532;
		int endX = 205;
		int endY = 520;

		// Define the input source for pointer (finger)
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

		// Create the sequence of actions
		Sequence swipe = new Sequence(finger, 0)
				.addAction(
						finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), startX, startY))
				.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
				.addAction(
						finger.createPointerMove(Duration.ofMillis(5000), PointerInput.Origin.viewport(), endX, endY))
				.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

		// Perform the sequence
		driver.perform(Collections.singletonList(swipe));
		Thread.sleep(5000);

		driver.findElement(AppiumBy.accessibilityId("Continue")).click(); /* click continue button */

//		input weight
		PointerInput finger2 = new PointerInput(PointerInput.Kind.TOUCH, "finger");

		Sequence swipe2 = new Sequence(finger2, 0)
				.addAction(
						finger2.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), startX, startY))
				.addAction(finger2.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
				.addAction(
						finger2.createPointerMove(Duration.ofMillis(5000), PointerInput.Origin.viewport(), endX, endY))
				.addAction(finger2.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

		driver.perform(Collections.singletonList(swipe2));
		Thread.sleep(5000);

		driver.findElement(AppiumBy.accessibilityId("Continue")).click(); /* click continue button */

//			Skip sleep
		driver.findElement(AppiumBy.accessibilityId("Skip")).click();

//			skip blood group
		driver.findElement(AppiumBy.accessibilityId("Skip")).click();

//          skip symproms
		driver.findElement(AppiumBy.accessibilityId("Skip")).click();

//			submit assessment
		WebElement baloon = driver.findElement(AppiumBy.accessibilityId("Balloons"));
		int endsX = 72;
		int endsY = 283;

		PointerInput finger3 = new PointerInput(PointerInput.Kind.TOUCH, "finger");

		Sequence swipe3 = new Sequence(finger3, 0)

				.addAction(finger3.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), endsX,
						endsY))
				.addAction(finger3.createPointerUp(PointerInput.MouseButton.MIDDLE.asArg()));

		driver.perform(Collections.singletonList(swipe3));

		Thread.sleep(5000);
		driver.findElement(AppiumBy.accessibilityId("Submit")).click();

//			clickLearn your cygnal button
		driver.findElement(AppiumBy.accessibilityId("Learn your Cygnal")).click();
		Thread.sleep(5000);

	}

	@Test(priority = 5)
	public void deleteAccount() throws InterruptedException {
//		click profile page button
		driver.findElement(AppiumBy.accessibilityId("Profile Page Navigation Icon")).click();

		WebElement pInfo = driver.findElement(AppiumBy.accessibilityId("Personal Info"));
		int endX = 133;
		int endY = 63;

		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

		Sequence swipe = new Sequence(finger, 0)

				.addAction(
						finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), endX, endY))
				.addAction(finger.createPointerUp(PointerInput.MouseButton.MIDDLE.asArg()));

		driver.perform(Collections.singletonList(swipe));

		driver.findElement(AppiumBy.accessibilityId("Close Account")).click();
//click delete account button
		driver.findElement(AppiumBy.accessibilityId("Delete Account")).click();
		Thread.sleep(5000);

	}

	@Test(priority = 5)
	public void verifyDeleteAccount() {
		boolean text = driver.findElement(AppiumBy.accessibilityId("Welcome Back")).isDisplayed();
		Assert.assertTrue(text);
	}
}
