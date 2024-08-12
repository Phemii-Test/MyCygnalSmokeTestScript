package iOSPageObject;

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
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class iOS_Onboarding {

	private AppiumDriver driver;
	
	InboxDto inbox;
	String otp;
	ApiClient defaultClient = Configuration.getDefaultApiClient();

	
	public iOS_Onboarding(AppiumDriver driver) {
		this.driver = driver;
		
	}
	
	

	
	public void Setup() throws MalformedURLException, ApiException {

		defaultClient.setApiKey("f53a1da18ea522580705ca6199318dd7e8a14f30f7339900c8f900f7f95d4ad6");
		InboxControllerApi inboxControllerApi = new InboxControllerApi(defaultClient);

		inbox = inboxControllerApi.createInboxWithDefaults();
		assertNotNull(inbox, "Inbox creation failed. Inbox is null.");
		assertEquals(inbox.getEmailAddress().contains("@mailslurp"), true, "Email address is not valid.");
		
		
	}

	private By commonElement1 = By.className("XCUIElementTypeButton");
	private By commonElement2 = By.className("XCUIElementTypeTextField");
	private By commonElement3 = By.className("XCUIElementTypeSwitch");
	private By doneButton = AppiumBy.accessibilityId("Done");
	private By previousButton = AppiumBy.accessibilityId("Previous month");
	private By yearElement = AppiumBy.accessibilityId("1, Tuesday, July 1, 2008");
	private By continueButton = AppiumBy.accessibilityId("Continue");
//	private By maleOption = AppiumBy.accessibilityId("Male");
	private By skipButton = AppiumBy.accessibilityId("Skip");
	private By submitButton = AppiumBy.accessibilityId("Submit");
	private By learnYourCygnalbutton = AppiumBy.accessibilityId("Learn your Cygnal");
	private By profilePage = AppiumBy.accessibilityId("Profile Page Navigation Icon");
	private By closeAccountbutton = AppiumBy.accessibilityId("Close Account");
	private By deleteAccountButton = AppiumBy.accessibilityId("Delete Account");
	private By welcombackText = AppiumBy.accessibilityId("Welcome Back");
	
@Test(priority=0)
	public void clickForwardButton() throws InterruptedException {
		driver.findElement(commonElement1).click();
		Thread.sleep(5000);
		driver.findElement(commonElement1).click();
		Thread.sleep(5000);
	}
@Test(priority=1)

	public void enterName(String firstName, String lastName) {
//		FirstName field
		driver.findElements(commonElement2).get(0).sendKeys(firstName);

//		Last name field
		driver.findElements(commonElement2).get(2).sendKeys(lastName);
	}
@Test(priority=2)

	public void enterEmail() {
//		Email field
		driver.findElements(commonElement2).get(4).sendKeys(inbox.getEmailAddress());
	}
@Test(priority=3)

	public void fillMobileNum(String mobileNumber) {

//		enterMobile number
		driver.findElements(commonElement2).get(6).sendKeys(mobileNumber);
	}
@Test(priority=4)

	public void fillPassword(String password) {

//	    fillPassword
		driver.findElements(commonElement2).get(7).sendKeys(password);
	}
@Test(priority=5)

	public void completeForm() throws InterruptedException {

//       checkT&C
		driver.findElement(commonElement3).click();

//		bring down the keyboard.
		driver.findElement(doneButton).click();

//		clickSignup button
		driver.findElements(commonElement1).get(1).click();

		Thread.sleep(5000);
	}
@Test(priority=6)

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
			driver.findElement(commonElement2).sendKeys(otp);

		} else {
			System.out.println("OTP not found in the email body.");
		}

//		clickVerifyButton
		driver.findElements(commonElement1).get(2).click();
		Thread.sleep(5000);
	}
@Test(priority=7)

	public void startAssessment() {
//		clickGoto Health Assessment button
		driver.findElement(commonElement1).click();
	}
@Test(priority=8)

	public void selectDOB() {

//	    select date
		driver.findElement(previousButton).click();/* click back calendar button */
		driver.findElement(yearElement).click();
		driver.findElement(continueButton).click(); /* click continue button */
	}

@Test(priority=9)


	public void selectGender()  {

//		select male gender
		driver.findElements(commonElement3).get(0).click();
		driver.findElement(continueButton).click(); /* click continue button */
	}
@Test(priority=10)

	public void setHeight() {

		int startX = 372;
		int startY = 479;
		int endX = 41;
		int endY = 472;

		// Define the input source for pointer (finger)
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

		// Create the sequence of actions
		Sequence swipe = new Sequence(finger, 0)
				.addAction(
						finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), startX, startY))
				.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
				.addAction(
						finger.createPointerMove(Duration.ofMillis(4000), PointerInput.Origin.viewport(), endX, endY))
				.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

		// Perform the sequence
		driver.perform(Collections.singletonList(swipe));

		driver.findElement(continueButton).click(); /* click continue button */
	}
@Test(priority=11)

	public void setWeight() throws InterruptedException  {
		int startX = 358;
		int startY = 476;
		int endX = 208;
		int endY = 476;

//		input weight
		PointerInput finger2 = new PointerInput(PointerInput.Kind.TOUCH, "finger");

		Sequence swipe2 = new Sequence(finger2, 0)
				.addAction(
						finger2.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), startX, startY))
				.addAction(finger2.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
				.addAction(
						finger2.createPointerMove(Duration.ofMillis(3000), PointerInput.Origin.viewport(), endX, endY))
				.addAction(finger2.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

		driver.perform(Collections.singletonList(swipe2));
        Thread.sleep(3000);
        driver.findElement(continueButton).click(); /* click continue button */
	}
@Test(priority=12)

	public void skipSleepBlooodGroupSymptoms() {

//			Skip sleep
		driver.findElement(skipButton).click();

//			skip blood group
		driver.findElement(skipButton).click();

//          skip symproms
		driver.findElement(skipButton).click();
	}
@Test(priority=13)

	public void submitAccessment() throws InterruptedException {

//			submit assessment
		int endsX = 72;
		int endsY = 283;

		PointerInput finger3 = new PointerInput(PointerInput.Kind.TOUCH, "finger");

		Sequence swipe3 = new Sequence(finger3, 0)

				.addAction(finger3.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), endsX,
						endsY))
				.addAction(finger3.createPointerUp(PointerInput.MouseButton.MIDDLE.asArg()));

		driver.perform(Collections.singletonList(swipe3));

		Thread.sleep(5000);
		driver.findElement(submitButton).click();
	}
@Test(priority=14)

	public void accessHomeDashboard() throws InterruptedException {

//			clickLearn your cygnal button
		driver.findElement(learnYourCygnalbutton).click();
		Thread.sleep(5000);

	}
@Test(priority=15)

	public void clickProfilePage() throws InterruptedException {
//		click profile page button
		driver.findElement(profilePage).click();

		int endX = 133;
		int endY = 63;

		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

		Sequence swipe = new Sequence(finger, 0)

				.addAction(
						finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), endX, endY))
				.addAction(finger.createPointerUp(PointerInput.MouseButton.MIDDLE.asArg()));

		driver.perform(Collections.singletonList(swipe));
	}
@Test(priority=16)

	public void clickCloseAndDeleteButton() throws InterruptedException {

		driver.findElement(closeAccountbutton).click();
//click delete account button
		driver.findElement(deleteAccountButton).click();
		Thread.sleep(5000);

	}
@Test(priority=17)

	public boolean verifyDeleteAccount() {
		return driver.findElement(welcombackText).isDisplayed();
	}
}
