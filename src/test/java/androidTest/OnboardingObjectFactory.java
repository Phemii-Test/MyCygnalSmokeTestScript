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
		driver.findElements(commonElement2).get(2).click();
		driver.findElements(commonElement6).get(11).click();
		driver.findElements(commonElement2).get(4).click();
	}

	public void selectGender() {
//		Select male gender
		driver.findElements(checkbox).get(0).click();
		driver.findElements(commonElement2).get(1).click();
	}

	public void setHeight() throws InterruptedException {
//			 Select height
		driver.findElements(By.className("android.view.View")).get(5).click(); /* toggle the FT button */
		WebElement height = driver.findElements(commonElement6).get(8);
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) height).getId(), "endX", 590, "endY", 1460));
		Thread.sleep(5000);
		driver.findElements(commonElement2).get(1).click();
	}

	public void setWeight() throws InterruptedException {
//			select Weight
		driver.findElements(commonElement6).get(5).click(); /* toggle the lbs button */
		WebElement weight = driver.findElements(By.className("android.view.View")).get(8);
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) weight).getId(), "endX", 590, "endY", 1460));
		Thread.sleep(5000);
		driver.findElements(commonElement2).get(1).click();
	}

	public void selectSleepQuality() {

		driver.findElements(commonElement6).get(6).click();
		driver.findElements(commonElement2).get(1).click();
	}

	public void skipBloodGroup() {
		driver.findElements(commonElement2).get(2).click();
	}

	public void skipSymptoms() {
		driver.findElements(commonElement2).get(2).click();

	}

	public void clickSubmitButton() throws InterruptedException {
//		click submit button
		WebElement baloon = driver.findElement(AppiumBy.accessibilityId("Balloons"));
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) baloon).getId(), "endX", 276, "endY", 569));

		driver.findElements(commonElement2).get(1).click();
		Thread.sleep(5000);

	}

	public void verifyAssessmentCompleted() throws InterruptedException {
		String actualtxt = driver.findElements(commonElement6).get(3).getText();
		String ExpectedTxt = "You have now completed the basic assessment section, please proceed to your dashboard";
		Assert.assertEquals(actualtxt, ExpectedTxt);

	}

	public void clickLearnYourCygnalBtn() throws InterruptedException {

		driver.findElement(commonElement2).click();

	}

	public boolean recommendationTabIsDisplayed() throws InterruptedException {
		Thread.sleep(5000);

		return driver.findElements(commonElement5).get(0).isDisplayed();

	}

	public void deleteAccount() throws InterruptedException {
		driver.findElements(commonElement5).get(7).click();

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
