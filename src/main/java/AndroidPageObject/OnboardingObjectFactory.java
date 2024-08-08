package AndroidPageObject;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.html.parser.DocumentParser;

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
import java.util.UUID;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class OnboardingObjectFactory {

	private AppiumDriver driver;
	InboxDto inbox;
	String otp;

	ApiClient defaultClient = Configuration.getDefaultApiClient();

	public OnboardingObjectFactory(AppiumDriver driver) {
		this.driver = driver;
	}

	private By commonElement1 = By.xpath("//android.widget.Button");
	private By commonElement2 = By.className("android.widget.Button");
	private By commonElement3 = By.xpath("//android.widget.ImageView");
	private By commonElement4 = By.className("android.widget.EditText");
	private By commonElement5 = By.className("android.widget.ImageView");
	private By commonElement6 = By.className("android.view.View");
	private By checkbox = By.className("android.widget.CheckBox");
	private By welcomeBackBtn = AppiumBy.accessibilityId("Welcome Back");
	private By personalInfoBtn = AppiumBy.accessibilityId("Personal Info");
	private By signUpBtn = AppiumBy.accessibilityId("Sign Up");
	private By verifyBtn = AppiumBy.accessibilityId("Verify");
	private By continueButton= AppiumBy.accessibilityId("Continue");
	private By skipButton = AppiumBy.accessibilityId("Skip");
	private By profilePage= AppiumBy.accessibilityId("Profile Page Navigation Icon");
	private By learnYourCygnalbutton = AppiumBy.accessibilityId("Learn your Cygnal");
	private By checkMark = AppiumBy.accessibilityId("Success Icon");

			
			
		public void Setup() throws ApiException {
		defaultClient.setApiKey("238dbc0bcd01f3c8b7d4cd42b91b7a80b8af2cf4e5fd7487c311f089562dc43a");
		InboxControllerApi inboxControllerApi = new InboxControllerApi(defaultClient);

		inbox = inboxControllerApi.createInboxWithDefaults();
		assertNotNull(inbox, "Inbox creation failed. Inbox is null.");
		assertEquals(inbox.getEmailAddress().contains("@mailslurp"), true, "Email address is not valid.");

	}

	@Test(priority = 0)
	public void clickForwardButton() {
		driver.findElement(commonElement1).click();
		driver.findElement(commonElement2).click();
	}

	@Test(priority = 1)
	public void fillFirstName() throws InterruptedException {

		WebElement firstNameField = driver.findElement(commonElement3);
		firstNameField.click();
		firstNameField.sendKeys("Olufemi");
	}

	public void fillLastName() {
		WebElement lastName = driver.findElements(commonElement3).get(1);
		lastName.click();
		lastName.sendKeys("Omeiza");
	}

	public void fillEmail() throws InterruptedException {
		Thread.sleep(5000);
		WebElement email = driver.findElements(commonElement3).get(2);
		email.click();
		email.sendKeys(inbox.getEmailAddress());
	}

	public void fillMobileNum() {
		WebElement MobileNum = driver.findElements(commonElement4).get(3);
		MobileNum.click();
		MobileNum.sendKeys("+13473826505");
	}

	public void fillPassword() {
//			fill Password
		WebElement password = driver.findElements(commonElement5).get(3);
		password.click();
		password.sendKeys("Hbon@1234");
		driver.navigate().back();
	}

	public void completeSignUp() throws InterruptedException {
		// Check the terms and condition box and click signup button
		driver.findElement(checkbox).click();
		driver.findElement(signUpBtn).click();
		Thread.sleep(5000);
	}

	public void fillOTP() throws ApiException, InterruptedException {

		WaitForControllerApi waitForControllerApi = new WaitForControllerApi(defaultClient);
		Email email = waitForControllerApi.waitForLatestEmail(inbox.getId(), null, null, null, null, null, null);

		EmailTextLinesResult extratctedEmailBody = new EmailControllerApi(defaultClient)
				.getEmailHTMLQuery(email.getId(), ".code");
		CharSequence emailBody = String.valueOf(extratctedEmailBody);

		Pattern otpPattern = Pattern.compile("\\b\\d{6}\\b");
		Matcher matcher = otpPattern.matcher(emailBody);

		if (matcher.find()) {
			String otp = matcher.group();
			WebElement otpField = driver.findElements(commonElement4).get(0);
			otpField.click();
			otpField.clear();

			otpField.sendKeys(otp);
		} else {
			System.out.println("OTP not found in the email body.");
		}
	}

	public void clickVerifyBtn() throws InterruptedException {

		driver.findElement(verifyBtn).click();

		Thread.sleep(5000);

	}

	public void fillDOB() {
//			click the goto health assessment button 
		driver.findElements(commonElement2).get(0).click();

//			select DOB
		driver.findElements(commonElement6).get(16).click();
		driver.findElement(continueButton).click();
	}

	public void selectGender() throws InterruptedException {
//		Select male gender
		driver.findElements(checkbox).get(0).click();
		driver.findElement(continueButton).click();
		
		Thread.sleep(5000);
	}

	public void setHeight() throws InterruptedException {
//			 Select height
		int startX = 1006;
		int startY = 1167;
		int endX = 561;
		int endY = 1159;
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

		// Create the sequence of actions
		Sequence swipe = new Sequence(finger, 0)
				.addAction(
						finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), startX, startY))
				.addAction(finger.createPointerDown(PointerInput.MouseButton.MIDDLE.asArg()))
				.addAction(
						finger.createPointerMove(Duration.ofMillis(4000), PointerInput.Origin.viewport(), endX, endY))
				.addAction(finger.createPointerUp(PointerInput.MouseButton.MIDDLE.asArg()));

		// Perform the sequence
		driver.perform(Collections.singletonList(swipe));
		
	
		driver.findElement(continueButton).click();
	}

	public void setWeight() throws InterruptedException {
//			select Weight
		
		int startX = 1023;
		int startY = 1171;
		int endX = 140;
		int endY = 1167;
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

		// Create the sequence of actions
		Sequence swipe = new Sequence(finger, 0)
				.addAction(
						finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), startX, startY))
				.addAction(finger.createPointerDown(PointerInput.MouseButton.MIDDLE.asArg()))
				.addAction(
						finger.createPointerMove(Duration.ofMillis(4000), PointerInput.Origin.viewport(), endX, endY))
				.addAction(finger.createPointerUp(PointerInput.MouseButton.MIDDLE.asArg()));

		// Perform the sequence
		driver.perform(Collections.singletonList(swipe));
		
		
		driver.findElement(continueButton).click();
	}

	public void selectSleepQuality() {

		driver.findElements(commonElement6).get(6).click();
		driver.findElement(continueButton).click();
	}

	public void skipBloodGroup() {
		driver.findElement(skipButton).click();
	}

	public void skipSymptoms() {
		driver.findElement(skipButton).click();

	}

	public void clickSubmitButton() {
//		click submit button
		WebElement baloon = driver.findElement(AppiumBy.accessibilityId("Balloons"));
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) baloon).getId(), "endX", 276, "endY", 569));

		driver.findElements(commonElement2).get(1).click();

	}

	public boolean verifyAssessmentCompleted() throws InterruptedException  {
		Thread.sleep(5000);
		return driver.findElement(checkMark).isDisplayed();
	}

	public void clickLearnYourCygnalBtn()  {

		driver.findElement(learnYourCygnalbutton).click();

	}

	public boolean recommendationTabIsDisplayed() throws InterruptedException {
		Thread.sleep(5000);

		return driver.findElements(commonElement5).get(0).isDisplayed();

	}

	public void deleteAccount() throws InterruptedException {
		driver.findElement(profilePage).click();

		WebElement pInfo = driver.findElement(personalInfoBtn);
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) pInfo).getId(), "endX", 450, "endY", 132));

		driver.findElements(By.className("android.widget.ImageView")).get(6).click();

		driver.findElements(commonElement2).get(0).click();
		Thread.sleep(5000);

	}

	@Test(priority = 8)
	public boolean welcomeBackTextIsDisplayed() {
		return driver.findElement(welcomeBackBtn).isDisplayed();
	}
}
