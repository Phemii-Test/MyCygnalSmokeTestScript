package iOSTest;

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
import com.mailslurp.clients.ApiClient;
import com.mailslurp.clients.ApiException;
import com.mailslurp.clients.Configuration;
import com.mailslurp.models.InboxDto;

import androidTest.SleepTrackerPageObjectFactory;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.PressesKey;
import io.appium.java_client.ios.IOSDriver;

public class e2eTestiOS {

	AppiumDriver driver;
	InboxDto inbox;
	String otp;
	ApiClient defaultClient = Configuration.getDefaultApiClient();

	
	private AddMedicationiOS  addMedSteps;
	private iOS_Onboarding   onboardingSteps;
	private LogSymptomsiOS   symptomSteps;
	private LogProcedureiOS  procedureSteps;
	private log_HeartRate_iOS heartRateSteps;
	private LogBPiOS          bpSteps;
	private LogSleep         logSleepSteps;

	@BeforeTest
	public void Setup() throws MalformedURLException {

		DesiredCapabilities cap = new DesiredCapabilities();

		cap.setCapability("platformName", "iOS");
		cap.setCapability("automationName", "XCUITest");
		cap.setCapability("udid", "293CB621-315F-4285-922D-9DD8D1F45AC5");
		cap.setCapability("bundleId", "com.mycygnal.mycygnal");
		cap.setCapability("deviceName", "iPhone 15");
		cap.setCapability("platformVersion", "17.5");
//	        cap.setCapability("usePreinstalledWDA", "true");
		cap.setCapability("app", "/Users/olufemiomeiza/Downloads/Runner-7.app");

		try {
			driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		addMedSteps = new AddMedicationiOS(driver);
		onboardingSteps = new iOS_Onboarding(driver);
		symptomSteps = new LogSymptomsiOS(driver);
		procedureSteps = new LogProcedureiOS(driver);
		heartRateSteps = new log_HeartRate_iOS(driver);
		bpSteps = new LogBPiOS(driver);
		logSleepSteps = new LogSleep(driver);
		
	}
	
	@Test(priority=0)
	
	public void beforeTestSetup() throws MalformedURLException, ApiException {
		onboardingSteps.Setup();
	}

	@Test(priority=1)
public void signUp() throws InterruptedException, ApiException	{
		onboardingSteps.clickForwardButton();
		onboardingSteps.enterName("Test", "Account");
		onboardingSteps.enterEmail();
		onboardingSteps.fillMobileNum("4565728287");
		onboardingSteps.fillPassword("soAwesome@1234");
		onboardingSteps.completeForm();
		onboardingSteps.enterOTP();
	}

	@Test(priority=2)
public void onboardingAssessment() throws InterruptedException 	{
		onboardingSteps.startAssessment();
		onboardingSteps.selectDOB();
		onboardingSteps.selectGender();
		onboardingSteps.setHeight();
		onboardingSteps.setWeight();
		onboardingSteps.skipSleepBlooodGroupSymptoms();
		onboardingSteps.submitAccessment();
		onboardingSteps.accessHomeDashboard();

	}
	
	

	@Test(priority=3)
public void addMedication() throws InterruptedException	{
		
		addMedSteps.addMedication();
		addMedSteps.addMedicationName("Lesinopril");
		addMedSteps.addDosage("2");
		addMedSteps.setFrequency();
		addMedSteps.selectCategory();
		addMedSteps.date();
		addMedSteps.verifyMedicationAdded();	
	}
	
	@Test(priority=4)

public void setDosage() throws InterruptedException	{
		
		addMedSteps.addFirstDoseTime();
		addMedSteps.addSecondDoseTime();
		addMedSteps.addThirdDoseTime();
		addMedSteps.completeReminderSetup();
		addMedSteps.ValidateReminderSet();
		addMedSteps.gotoMedDashboard();
	}
	
	@Test(priority = 5)
	public void logSymptoms() throws InterruptedException {
		symptomSteps.symptomName("Headache");
		symptomSteps.symptomsSeverity();
		symptomSteps.symptomsDescription();
		symptomSteps.Frequency();
		symptomSteps.Triggers("Stress");
		symptomSteps.alleviatingFactors("Paracetamol");
		symptomSteps.setDateAndTime();
		symptomSteps.ValidateSymptomLog();
	}
	
	@Test(priority=6)
	public void logProcedure() {
		procedureSteps.startProcedureLog();
		procedureSteps.proceduretitle("Ceasarial Session");
		procedureSteps.procedureDescription("delivery through procedure");
		procedureSteps.healthProvider("Rev Fr Burke, Memorial clinic");
		procedureSteps.procedureType();
		procedureSteps.procedurePrep("Antenatal");
		procedureSteps.procedureCare("frequent checkup");
		procedureSteps.dateAndTime();
		procedureSteps.ValidateProcedureLog();
		procedureSteps.returnHome();
	}
	
	@Test(priority=7)
	 public void logHeartRate() throws InterruptedException {
		
		heartRateSteps.beginHeartRatelog();
		heartRateSteps.setHeartRateValue();
		heartRateSteps.scrollUp();
		heartRateSteps.setDateAndtime();
		heartRateSteps.recordHeartRate();
		heartRateSteps.verifyBPLog();
		heartRateSteps.relatedMedIsDisplayed();
		heartRateSteps.viewHeartRateDashboard();
	}
	
	@Test(priority=8)
	public void logBP() throws InterruptedException {
		bpSteps.beginBPLog();
		bpSteps.systolicBP();
		bpSteps.scrollUp();
		bpSteps.diastolicLog();
		bpSteps.setDateAndTime();
		bpSteps.recordBP();
		bpSteps.verifyBPLog();
		bpSteps.isRecordMedButtonDisplayed();
		bpSteps.viewBPRecord();
	}

	@Test(priority=9)
	public void logSleep() throws InterruptedException {
		logSleepSteps.startLoggingSleep();
		logSleepSteps.addSleepTime();
		logSleepSteps.sleepQuality();
		logSleepSteps.recordSleep();
		logSleepSteps.validateSleepLog();
		logSleepSteps.backToHomescreen();
		
	}
	@Test(priority = 10)
public void deleteAccount() throws InterruptedException 	{
		onboardingSteps.clickProfilePage();
		onboardingSteps.clickCloseAndDeleteButton();
		onboardingSteps.verifyDeleteAccount();
		
	}
	
	@AfterTest
	public void quitApp() throws InterruptedException {
		Thread.sleep(5000);
		driver.quit();
		
	}
	
	
}