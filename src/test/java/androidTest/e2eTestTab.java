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
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.PressesKey;

public class e2eTestTab {

	AndroidDriver driver;
//	private static String initialAverageSleep;
//	private static String finalAverageSleep;
	public static String averageHeartRate;
	public static String updatedAverageHeartRate;
	public static String initialaverageBP;
	public static String updatedAverageBP;

		@BeforeTest
		public void Setup() throws MalformedURLException {

			DesiredCapabilities cap=new DesiredCapabilities();

			cap.setCapability("deviceName", "VGP Phone");
			cap.setCapability("platformName", "Android");
			cap.setCapability("platformVersion", "10");
			cap.setCapability("udid", "emulator-5554");
//			cap.setCapability("app", "/Users/olufemiomeiza/Downloads/MyCygnal-3.apk");
			cap.setCapability("appPackage", "com.mycygnal.mycygnal");
			cap.setCapability("appActivity", "com.mycygnal.mycygnal.MainActivity");
			cap.setCapability("automationName", "appium");

//			AppiumDriver driver=new AppiumDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);

		try {
				driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		}
		@Test(priority=0)
		public void Login() {
//			click the forward button for the omescreen display.
			driver.findElement(By.xpath("//android.widget.Button")).click();
			driver.findElement(By.xpath("//android.widget.Button")).click();

			WebElement checkbox = driver.findElement(By.className("android.widget.CheckBox"));
			((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
				    "elementId", ((RemoteWebElement) checkbox).getId(),
				    "endX", 442,
				    "endY", 517
				));
			driver.findElement(AppiumBy.accessibilityId("Login")).click();
//			Enter Login details and click enter.
			WebElement emailField = driver.findElements(By.xpath("//android.widget.ImageView")).get(0);
			emailField.click();
			emailField.sendKeys("Ohlufehmii@gmail.com");

			WebElement passwordField = driver.findElements(By.xpath("//android.widget.ImageView")).get(1);
			passwordField.click();
			passwordField.sendKeys("Hbon@1234");

			driver.findElement(AppiumBy.accessibilityId("Login")).click();

		}
		@Test(priority=1)
		public void addMedication() throws InterruptedException {
			driver.findElement(AppiumBy.accessibilityId("Select Actions Button")).click();
			driver.findElement(AppiumBy.accessibilityId("Add Medications")).click();

//			Fill med form

			WebElement addMedName=driver.findElements(By.xpath("//android.widget.ImageView")).get(0);
			((JavascriptExecutor) driver).executeScript("mobile: doubleClickGesture", ImmutableMap.of(
				    "elementId", ((RemoteWebElement) addMedName).getId()
				));
			addMedName.sendKeys("Lesinopril");

			WebElement Dosage=driver.findElements(By.className("android.widget.EditText")).get(1);
			((JavascriptExecutor) driver).executeScript("mobile: doubleClickGesture", ImmutableMap.of(
				    "elementId", ((RemoteWebElement) Dosage).getId()
				));
			Dosage.sendKeys("2");

		       Thread.sleep(2000);


			WebElement Frequency = driver.findElement(AppiumBy.accessibilityId("0%"));
			((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
				    "elementId", ((RemoteWebElement) Frequency).getId(),
				    "endX", 501,
				    "endY", 532
				));
//			Select Category
			driver.findElement(AppiumBy.accessibilityId("Antipertensives")).click();

//		    select start date
			driver.findElements(By.xpath("//android.widget.ImageView")).get(1).click();
			driver.findElement(AppiumBy.accessibilityId("4, Tuesday, June 4, 2024")).click();
			driver.findElement(AppiumBy.accessibilityId("OK")).click();

//			select end date
			WebElement endDate=driver.findElements(By.xpath("//android.widget.ImageView")).get(3);
			endDate.click();
			driver.findElement(AppiumBy.accessibilityId("22, Saturday, June 22, 2024")).click();
			WebElement ok= driver.findElement(AppiumBy.accessibilityId("OK"));
			ok.click();

//			click the add medication button
			driver.findElement(AppiumBy.accessibilityId("Add Medication")).click();

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

//			SCROLL UP
//			((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
//				    "elementId", ((RemoteWebElement) Frequency).getId(),
//				    "endX", 351,
//				    "endY", 992
//				));




//			Add second dose
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

//			Add third dose
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
	}

//	Log symptoms
	@Test(priority = 4)
	public void logSymptom() throws InterruptedException {
		driver.findElement(AppiumBy.accessibilityId("Select Actions Button")).click();
		driver.findElement(AppiumBy.accessibilityId("Log Symptoms")).click();

//		Fill the symptoms form.

//		Syptoms title
		WebElement title = driver.findElements(By.xpath("//android.widget.ImageView")).get(0);
		title.click();
		title.sendKeys("Headache");

//		Scroll to set severity
		WebElement Frequency = driver.findElement(AppiumBy.accessibilityId("0%"));
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) Frequency).getId(), "endX", 435, "endY", 1070));
//		Symptoms description
		WebElement desc = driver.findElements(By.className("android.widget.EditText")).get(1);
		desc.click();
		desc.sendKeys("I feel slight headache at my forehead.");

//		Set frequency
		driver.findElement(AppiumBy.accessibilityId("Select Frequency")).click();
		driver.findElement(AppiumBy.accessibilityId("Daily")).click();
	    Thread.sleep(2000);

//		Set the trigger.
		WebElement Triggers = driver.findElements(By.className("android.widget.EditText")).get(3);
		Triggers.click();
		Triggers.sendKeys("Stress");
		((PressesKey) driver).pressKey(new KeyEvent(AndroidKey.ENTER));

//		State the alleviating factors.
		WebElement alivFactors = driver.findElements(By.className("android.widget.EditText")).get(4);
		alivFactors.click();
		alivFactors.sendKeys("Analgesics");
		((PressesKey) driver).pressKey(new KeyEvent(AndroidKey.ENTER));



//		select end date
		driver.findElements(By.xpath("//android.widget.ImageView")).get(4).click();
		driver.findElement(AppiumBy.accessibilityId("5, Wednesday, June 5, 2024")).click();
		driver.findElement(AppiumBy.accessibilityId("OK")).click();

//		Set time.
		driver.findElements(By.xpath("//android.widget.ImageView")).get(6).click();
		WebElement StartMinute = driver.findElement(By.xpath("//android.view.View"));
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) StartMinute).getId(), "endX", 372, "endY", 1625));
		WebElement Startseconds = driver.findElement(By.xpath("//android.view.View"));
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) Startseconds).getId(), "endX", 675, "endY", 1310));
		driver.findElement(AppiumBy.accessibilityId("OK")).click();

//		Click the log symptoms button
		driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Log Symptoms\"]")).click();
	}

	@Test(priority = 5)
	public void ValidateSymptomsLog() {
//	 A checkmark icon is displayed.
		boolean successIcon = driver.findElement(AppiumBy.accessibilityId("Success Icon")).isDisplayed();
		Assert.assertTrue(successIcon);

//		Navigate to the dashboard
		driver.findElement(AppiumBy.accessibilityId("Go to Dashboard")).click();

	}
//	Log Procedure
	@Test(priority=6)
	public void logProcedure() throws InterruptedException, IOException {
		driver.findElement(AppiumBy.accessibilityId("Select Actions Button")).click();
		driver.findElement(AppiumBy.accessibilityId("Log Procedure")).click();

//		Fill the procedure form.

//	Procedure Title
		WebElement title=	driver.findElements(By.className("android.widget.ImageView")).get(0);
		title.click();
		title.sendKeys("Appendicitis");

//		Procedure description
		WebElement desc=	driver.findElements(By.className("android.widget.EditText")).get(1);
		desc.click();
		desc.sendKeys("Taking out the painful appendices");
		driver.navigate().back();

//		Health Provider
		WebElement Provider=	driver.findElements(By.className("android.widget.EditText")).get(3);
		Provider.click();
		Provider.sendKeys("Rev, Fr. Burke Memorial Clinic");
		driver.navigate().back();

//	Procedure type
	WebElement SelectType =driver.findElement(AppiumBy.accessibilityId("Select type"));
			SelectType.click();
	driver.findElement(AppiumBy.accessibilityId("Procedure 1")).click();


//	Pre-procedure preparation.
	WebElement ProcedurePrep=	driver.findElements(By.className("android.widget.EditText")).get(5);
	ProcedurePrep.click();
	ProcedurePrep.sendKeys("Several scan was done, and a several doctor appointment was scheduled.");


//	Post-procedure care.
	WebElement care=driver.findElements(By.className("android.widget.EditText")).get(7);
	care.click();
	care.sendKeys("Series of antibiotics was administered, and also frequent dressing.");


//	Upload file

   WebElement select= driver.findElement(AppiumBy.accessibilityId("Select File"));
   select.click();

//   By permissionButton = By.id("com.android.permissioncontroller:id/permission_allow_button");
//
//
//   WebElement permissionBtn=driver.findElement(permissionButton);
//   try {
//       if ( permissionBtn.isDisplayed()) {
//           (permissionBtn).click();
//       }
//   } catch (Exception e) {
//       Thread.sleep(2000);
//   }
//driver.findElement(AppiumBy.accessibilityId("Show roots")).click();
//Thread.sleep(2000);
//driver.findElements(By.className("android.widget.TextView")).get(3).click();
//driver.findElement(AppiumBy.id("com.android.documentsui:id/icon_thumb")).click();
	Thread.sleep(2000);
    WebElement Thumbnail=driver.findElements(By.className("android.widget.LinearLayout")).get(17);
    Thumbnail.click();




//	Verify file uploaded successfully
//    String Toast=driver.findElement(AppiumBy.accessibilityId("(Success Procedure File uploaded successfully")).getAttribute("Success "
//    		+ "Procedure File uploaded successfully");

    Thread.sleep(7000);

//	select date
	driver.findElements(By.className("android.widget.ImageView")).get(3).click();
	driver.findElement(AppiumBy.accessibilityId("5, Wednesday, June 5, 2024")).click();
	driver.findElement(AppiumBy.accessibilityId("OK")).click();

//	Set time.
	WebElement setTime=driver.findElements(By.className("android.widget.ImageView")).get(5);
	setTime.click();
	WebElement StartMinute = driver.findElement(By.xpath("//android.view.View"));
	((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
			ImmutableMap.of("elementId", ((RemoteWebElement) StartMinute).getId(), "endX", 372, "endY", 1625));
	WebElement Startseconds = driver.findElement(By.xpath("//android.view.View"));
	((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
			ImmutableMap.of("elementId", ((RemoteWebElement) Startseconds).getId(), "endX", 675, "endY", 1310));
	driver.findElement(AppiumBy.accessibilityId("OK")).click();

//	Click the log procedure button
	driver.findElements(By.className("android.widget.Button")).get(1).click();

	}

	@Test(priority = 7)
	public void ValidateProcedureLog() {
//	 A checkmark icon is displayed.
		boolean successIcon = driver.findElement(AppiumBy.accessibilityId("Success Icon")).isDisplayed();
		Assert.assertTrue(successIcon);

//		Navigate to the dashboard
		driver.findElement(AppiumBy.accessibilityId("Go to Dashboard")).click();
	}

//	Log sleep tracker

	@Test(priority= 8)
	public void ValidateInitialAverageSleepValue() {
//		click on the sleep graph from the home screen
		driver.findElement(AppiumBy.accessibilityId("Sleep icon")).click();

//		Get Initial average sleep  value.
		WebElement averageSleep = driver.findElements(By.className("android.view.View")).get(3);
		String InititialAverageSleep = averageSleep.getText();
		 double initialAverageSleep = Double.parseDouble(InititialAverageSleep.replace("[^\\d.]+", ""));
		 System.out.println("Initial Average Sleep: " + initialAverageSleep);
	}
	@Test (priority= 9)
	public void LogSleep() throws InterruptedException {
		driver.findElement(AppiumBy.accessibilityId("Add new sleep record")).click();

//	Set from time.
		driver.findElements(By.className("android.widget.ImageView")).get(0).click();
		WebElement clockField= driver.findElement(By.xpath("//android.view.View"));

		((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) clockField).getId(),
			    "endX", 669,
			    "endY", 1322
			));
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) clockField).getId(),
			    "endX", 669,
			    "endY", 1322
			));
		driver.findElement(AppiumBy.accessibilityId("OK")).click();

//		Set to time
		driver.findElements(By.className("android.widget.ImageView")).get(3).click();

		((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) clockField).getId(),
			    "endX", 824,
			    "endY", 1877
			));
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) clockField).getId(),
			    "endX", 669,
			    "endY", 1322
			));
		driver.findElement(AppiumBy.accessibilityId("OK")).click();

//		Select sleep quality
		WebElement Frequency = driver.findElement(AppiumBy.accessibilityId("0%"));
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) Frequency).getId(),
			    "endX", 435,
			    "endY", 1070
			));

//		Select the yes I'm on medication option

		driver.findElement(AppiumBy.accessibilityId("Yes, I'm on medication")).click();

//		Click the record sleep button
		driver.findElements(By.className("android.widget.Button")).get(1).click();
	}
@Test (priority = 10)
public void VerifySleepLog() throws InterruptedException {
//	 A checkmark icon is displayed.
		boolean successIcon = driver.findElement(AppiumBy.accessibilityId("Success Icon")).isDisplayed();
		Assert.assertTrue(successIcon);
// Validate that the add related medication is displayed since I specified I'm on medication
		boolean RecordMedBtn = driver.findElement(AppiumBy.accessibilityId("Record Related Medication")).isDisplayed();
		Assert.assertTrue(RecordMedBtn);

//		Click view sleep tracker button
		driver.findElement(AppiumBy.accessibilityId("View Sleep Tracker")).click();
//		Click the back button to get back to the dashboard.
		driver.findElement(By.xpath("//android.widget.Button")).click();
		Thread.sleep(2000);
}
@Ignore
@Test(priority = 11)
public void ValidateAverageSleepTrackerFunctionality() {
	WebElement averageSleep = driver.findElements(By.className("android.view.View")).get(3);
	String updatedAverageText = averageSleep.getText();
    double finalAverageSleep = Double.parseDouble(updatedAverageText.replace("[^\\d.]+", ""));
    System.out.println("Updated Average Blood Pressure: " + finalAverageSleep);

//     Verify that the average has been updated
  /*  if (Integer.parseInt(SleepTrackerPageObjectFactory.initialAverageSleep) > Integer.parseInt(SleepTrackerPageObjectFactory.finalAverageSleep)) {
        System.out.println("Test Passed: Average sleep stat has been updated.");
    } else {
        System.out.println("Test Failed: Average sleep stat has not been updated.");
    }
*/
}

@Test(priority=12)
public void logHeartRate() throws InterruptedException {
//click the heart rate button from the dashboard.
	driver.findElement(AppiumBy.accessibilityId("Heart Rate icon")).click();

//    Log Heart Rate
    driver.findElement(AppiumBy.accessibilityId("Add new heart rate record")).click();
    Thread.sleep(2000);


//    set the beat per minute
//    WebElement bpm= driver.findElement(AppiumBy.accessibilityId("0\\n50\\n100\\n150\\n200\\n250\\n300\\n350\\n400\\n450\\n500"));
    ((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
//    	    "elementId", ((RemoteWebElement) bpm).getId(),
    		"startX", 1073,
    	    "startY", 581,
    	    "endX", 805,
    	    "endY", 589
    	));

//	select date
	driver.findElements(By.className("android.widget.ImageView")).get(3).click();
	driver.findElement(AppiumBy.accessibilityId("5, Wednesday, June 5, 2024")).click();
	driver.findElement(AppiumBy.accessibilityId("OK")).click();

//	Set time.
	WebElement setTime=driver.findElements(By.className("android.widget.ImageView")).get(5);
	setTime.click();
	WebElement StartMinute = driver.findElement(By.xpath("//android.view.View"));
	((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
			ImmutableMap.of("elementId", ((RemoteWebElement) StartMinute).getId(), "endX", 372, "endY", 1625));
	WebElement Startseconds = driver.findElement(By.xpath("//android.view.View"));
	((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
			ImmutableMap.of("elementId", ((RemoteWebElement) Startseconds).getId(), "endX", 675, "endY", 1310));
	driver.findElement(AppiumBy.accessibilityId("OK")).click();


//	Select the yes I'm on medication option

	driver.findElement(AppiumBy.accessibilityId("Yes, I'm on medication")).click();

//	Click the record heart rate button
	Thread.sleep(2000);
	driver.findElements(By.className("android.widget.Button")).get(1).click();
}
@Test(priority=13)
public void verifyHeartRateLog () throws InterruptedException {
//	A checkmark icon is displayed.
	boolean successIcon = driver.findElement(AppiumBy.accessibilityId("Success Icon")).isDisplayed();
	Assert.assertTrue(successIcon);
//Validate that the add related medication is displayed since I specified I'm on medication
	boolean RecordMedBtn = driver.findElement(AppiumBy.accessibilityId("Record Related Medication")).isDisplayed();
	Assert.assertTrue(RecordMedBtn);

//	Click view HeartRate button
	driver.findElement(AppiumBy.accessibilityId("View Heart Rate")).click();

	driver.findElement(By.xpath("//android.widget.Button")).click();
	Thread.sleep(2000);


}
@Ignore
@Test(priority=14)
public void VerifyAverageHeartRateChange() {

	WebElement updatedAverageHeartRateElement = driver.findElements(By.className("android.view.View")).get(4);
    String updatedAverageHeartRate = updatedAverageHeartRateElement.getText();
    System.out.println("Updated average heart rate: " + updatedAverageHeartRate);

    // Assert that the average blood pressure has changed
    assert !averageHeartRate.equals(updatedAverageHeartRate);
    System.out.println("Average heart rates compared successfully!");

//    Click the back button to get back to the dashboard.
}
@Test(priority = 15)
public void setInitialAverageBP() {
	driver.findElement(AppiumBy.accessibilityId("Blood Pressure icon")).click();
//	Verify initial average heartRate
//		WebElement averageBPElement = driver.findElements(By.className("android.view.View")).get(4);
//        String initialaverageBP = averageBPElement.getText();
//        System.out.println("Initial average blood pressure: " + initialaverageBP);
}

@Test(priority= 16)
public void logBP() throws InterruptedException {
	driver.findElement(AppiumBy.accessibilityId("Add new blood pressure record")).click();
//	log systolic BP
	Thread.sleep(2000);
	((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
			"startX", 1113,
    	    "startY", 550,
    	    "endX", 814,
    	    "endY", 537
    	));

//	log diastolic BP
	Thread.sleep(2000);
	((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
			 "startX", 941,
     	    "startY", 1122,
     	    "endX", 809,
     	    "endY", 1117
    	));
//	select date
	driver.findElements(By.className("android.widget.ImageView")).get(6).click();
	driver.findElement(AppiumBy.accessibilityId("5, Wednesday, June 5, 2024")).click();
	driver.findElement(AppiumBy.accessibilityId("OK")).click();

//	Set time.
	WebElement setTime=driver.findElements(By.className("android.widget.ImageView")).get(8);
	setTime.click();
	WebElement StartMinute = driver.findElement(By.xpath("//android.view.View"));
	((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
			ImmutableMap.of("elementId", ((RemoteWebElement) StartMinute).getId(), "endX", 372, "endY", 1625));
	WebElement Startseconds = driver.findElement(By.xpath("//android.view.View"));
	((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
			ImmutableMap.of("elementId", ((RemoteWebElement) Startseconds).getId(), "endX", 675, "endY", 1310));
	driver.findElement(AppiumBy.accessibilityId("OK")).click();

//	Select the yes I'm on medication option

	driver.findElement(AppiumBy.accessibilityId("Yes, I'm on medication")).click();

	driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Record Blood Pressure\"]")).click();
	}

@Test(priority = 17)
public void verifyBPLog () throws InterruptedException {
//	A checkmark icon is displayed.
	boolean successIcon = driver.findElement(AppiumBy.accessibilityId("Success Icon")).isDisplayed();
	Assert.assertTrue(successIcon);
//Validate that the add related medication is displayed since I specified I'm on medication
	boolean RecordMedBtn = driver.findElement(AppiumBy.accessibilityId("Record Related Medication")).isDisplayed();
	Assert.assertTrue(RecordMedBtn);

//	Click view BP button
	driver.findElement(AppiumBy.accessibilityId("View Blood Pressure")).click();

	driver.findElement(By.xpath("//android.widget.Button")).click();


}
@Ignore
@Test(priority = 18)
public void VerifyAverageBPChange() {

WebElement updatedAverageBPElement = driver.findElements(By.className("android.view.View")).get(4);
    String updatedAverageBP = updatedAverageBPElement.getText();
    System.out.println("Updated average blood pressure: " + updatedAverageBP);

    // Assert that the average blood pressure has changed
    assert !initialaverageBP.equals(updatedAverageBP);
    System.out.println("Average blood pressure compared successfully!");
//    Click the back button to get back to the dashboard.
	driver.findElement(By.xpath("//android.widget.Button")).click();
}

@Test(priority= 19)
public void FirstQuestioneer() {
	driver.findElement(AppiumBy.accessibilityId("Recommendations")).click();
	driver.findElement(AppiumBy.accessibilityId("Go to Assessment")).click();
//	Do you currently smoke response
//	driver.findElement(AppiumBy.accessibilityId("No, I do not smoke")).click();
	driver.findElement(AppiumBy.accessibilityId("Continue")).click();

//	For how many years have you smoked?
	WebElement scroll = driver.findElements(By.className("android.view.View")).get(4);
	((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
			ImmutableMap.of("elementId", ((RemoteWebElement) scroll).getId(), "endX", 542, "endY", 618));


//	How many packs of cigarettes do you typically smoke in a day?
	WebElement scroll2=driver.findElements(By.className("android.view.View")).get(11);
	((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
			ImmutableMap.of("elementId", ((RemoteWebElement) scroll2).getId(), "endX", 324, "endY", 1881));

	driver.findElement(AppiumBy.accessibilityId("Continue")).click();

//	Have you smoked cigarettes in the past?
	driver.findElement(AppiumBy.accessibilityId(" Yes, I smoke")).click();
	driver.findElement(AppiumBy.accessibilityId("Continue")).click();

//	For how many years did you smoke before you quit?
	WebElement scroll3= driver.findElements(By.className("android.view.View")).get(4);
	((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
			ImmutableMap.of("elementId", ((RemoteWebElement) scroll3).getId(), "endX", 542, "endY", 618));

//	How many packs of cigarettes do you typically smoke in a day?
	WebElement scroll4=driver.findElements(By.className("android.view.View")).get(11);
	((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
			ImmutableMap.of("elementId", ((RemoteWebElement) scroll4).getId(), "endX", 324, "endY", 1881));

	driver.findElement(AppiumBy.accessibilityId("Continue")).click();

}

@Test(priority=20)
public void secondQuestioneer () throws InterruptedException {
//	click the vaccines that have been taken.
	driver.findElements(By.className("android.view.View")).get(3).click();
	driver.findElements(By.className("android.view.View")).get(4).click();
	driver.findElements(By.className("android.view.View")).get(5).click();
	driver.findElements(By.className("android.view.View")).get(6).click();
	WebElement scroll=driver.findElements(By.className("android.view.View")).get(7);
	scroll.click();


	driver.findElement(AppiumBy.accessibilityId("Continue")).click();

//	select the vaccines that have been taken
	driver.findElements(By.className("android.view.View")).get(3).click();
	driver.findElements(By.className("android.view.View")).get(4).click();
	driver.findElements(By.className("android.view.View")).get(5).click();

//    Click the submit button
	driver.findElement(AppiumBy.accessibilityId("Submit")).click();

	driver.wait(1000);

//	Validate that the questioneer filled was successful.
	boolean recoDashoardWelcome=driver.findElement(AppiumBy.accessibilityId("👋\\nWe are so glad you are\\ntaking steps to own your\\nhealth\\nReview each category below and learn how to live your most healthy life.\\n\\nYou may update your information on the dashboard at any time to keep the recommendations current")).isDisplayed();
	Assert.assertTrue(recoDashoardWelcome);

}

@Test(priority=21)
public void checkRecommendations() {
//click the see recommendation button
	driver.findElement(AppiumBy.accessibilityId("See Recommendation")).click();
//	validate the recommendations are displayed.
boolean firstRecommendation=	driver.findElement(AppiumBy.accessibilityId("HPV Vaccine")).isDisplayed();
Assert.assertTrue(firstRecommendation);

boolean SecondRecommendation=	driver.findElement(AppiumBy.accessibilityId("Updated COVID-19 Vaccines (2023â2024)")).isDisplayed();
Assert.assertTrue(SecondRecommendation);


}
@AfterTest
public void quitApp() {
	driver.quit();
}
}



