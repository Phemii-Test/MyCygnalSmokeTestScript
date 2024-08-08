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
import com.mailslurp.clients.ApiException;

import AndroidPageObject.AddHeartRatePageObjectFactory;
import AndroidPageObject.LogProcedureObjectFactory;
import AndroidPageObject.LogSymptomsObjectFactory;
import AndroidPageObject.OnboardingObjectFactory;
import AndroidPageObject.RecommendationSystemPageObjectFactory;
import AndroidPageObject.SleepTrackerPageObjectFactory;
import AndroidPageObject.addBloodPressurePageObjectFactory;
import AndroidPageObject.addMedicationObjectFactory;
import AndroidPageObject.loginObjectFactory;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class Testclass {
	AndroidDriver driver;

	private SleepTrackerPageObjectFactory sleepRecordSteps;
	private loginObjectFactory loginStep;
	private LogSymptomsObjectFactory symptomsSteps;
	private LogProcedureObjectFactory procedureSteps;
	private addMedicationObjectFactory medicationSteps;
	private addBloodPressurePageObjectFactory BPRecordSteps;
	private AddHeartRatePageObjectFactory HeartRateSteps;
	private RecommendationSystemPageObjectFactory recommendationSteps;
	private OnboardingObjectFactory onboardingSteps;

	@BeforeTest
	public void Setup() throws MalformedURLException {

		DesiredCapabilities cap = new DesiredCapabilities();

		cap.setCapability("deviceName", "VGP Phone");
		cap.setCapability("platformName", "Android");
		cap.setCapability("platformVersion", "10");
		cap.setCapability("udid", "emulator-5554");
		cap.setCapability("appPackage", "com.mycygnal.mycygnal");
		cap.setCapability("appActivity", "com.mycygnal.mycygnal.MainActivity");
		cap.setCapability("automationName", "appium");
		cap.setCapability("app", "/Users/olufemiomeiza/Downloads/app-release-10.apk");

		try {
			driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		sleepRecordSteps = new SleepTrackerPageObjectFactory(driver);
		loginStep = new loginObjectFactory(driver);
		symptomsSteps = new LogSymptomsObjectFactory(driver);
		procedureSteps = new LogProcedureObjectFactory(driver);
		medicationSteps = new addMedicationObjectFactory(driver);
		BPRecordSteps = new addBloodPressurePageObjectFactory(driver);
		HeartRateSteps = new AddHeartRatePageObjectFactory(driver);
		recommendationSteps = new RecommendationSystemPageObjectFactory(driver);
		onboardingSteps = new OnboardingObjectFactory(driver);

	}
	/*
	 * @Test(priority = 0) public void Login() throws InterruptedException {
	 * loginStep.clickForwardButton(); loginStep.clickForwardButton();
	 * loginStep.SelectLogin(); loginStep.enterLoginDetails("ohlufehmii@gmail.com",
	 * "Hbon@1234"); loginStep.clickLoginButton();
	 * 
	 * }
	 */

	@Test(priority = 0)
	public void signUp() throws InterruptedException, ApiException {
		onboardingSteps.Setup();
		onboardingSteps.clickForwardButton();
		onboardingSteps.fillFirstName();
		onboardingSteps.fillLastName();
		onboardingSteps.fillEmail();
		onboardingSteps.fillMobileNum();
		onboardingSteps.fillPassword();
		onboardingSteps.completeSignUp();
		onboardingSteps.fillOTP();
		onboardingSteps.clickVerifyBtn();
	}

	@Test(priority = 1)
	public void completeOnboardingAssessment() throws InterruptedException {
		onboardingSteps.fillDOB();
		onboardingSteps.selectGender();
		onboardingSteps.setHeight();
		onboardingSteps.setWeight();
		onboardingSteps.selectSleepQuality();
		onboardingSteps.skipBloodGroup();
		onboardingSteps.skipSymptoms();
		onboardingSteps.clickSubmitButton();
		onboardingSteps.verifyAssessmentCompleted();
		onboardingSteps.clickLearnYourCygnalBtn();
		onboardingSteps.recommendationTabIsDisplayed();
	}

	@Test(priority = 2)
	public void ValidateInitialAverageSleepValue() throws InterruptedException {
		sleepRecordSteps.swipeToSleepIcon();
		sleepRecordSteps.clickSleepIcon();
		sleepRecordSteps.validateInitialAverageSleep();
	}

	@Test(priority = 3)
	public void LogSleep() {
		sleepRecordSteps.clickAddNewSleepButton();
		sleepRecordSteps.addSleepTime();
		sleepRecordSteps.addSleepQuality();
		sleepRecordSteps.tickOnMedication();
		sleepRecordSteps.clickRecordSleepButton();
	}

	@Test(priority = 4)
	public void VerifySleepLog() throws InterruptedException {
		sleepRecordSteps.isSleepRecorded();
		sleepRecordSteps.isRecordMedbtnDispplayed();
		sleepRecordSteps.navigateToSleeptracker();
	}

	@Test(priority = 5)
	public void ValidateAverageSleepTrackerFunctionality() throws InterruptedException {
		sleepRecordSteps.ValidateAverageSleepTrackerFunctionality();
		sleepRecordSteps.backtoDashboardButton();
	}

	@Test(priority = 6)
	public void logProcedure() throws InterruptedException, IOException {
		medicationSteps.clickSelectActionsButton();
		procedureSteps.clicklogProcedureButton();
		procedureSteps.enterProcedureTitle();
		procedureSteps.enterProcedureDescription();
		procedureSteps.enterHealthProvider();
		procedureSteps.selectProcedureType();
		procedureSteps.preprocedurePreparation();
		procedureSteps.postProcedureCare();
		procedureSteps.selectDate();
		procedureSteps.setTime();
		procedureSteps.clickLogProcedureButton();

	}

	@Test(priority = 7)
	public void ValidateProcedureLog() throws InterruptedException {
		boolean isAdded = procedureSteps.isProcedureAdded();
		Assert.assertTrue(isAdded);
		symptomsSteps.backToDashboard();
	}

	@Test(priority = 8)
	public void addMedication() throws InterruptedException {
		medicationSteps.clickSelectActionsButton();
		medicationSteps.clickAddMedicationsButton();
		medicationSteps.fillMedicationForm("Lesinopril", "2");
		medicationSteps.setFrequency();
		medicationSteps.selectCategory();
		medicationSteps.selectStartDate();
		medicationSteps.selectEndDate();
		medicationSteps.clickAddMedicationButton();

		boolean isAdded = medicationSteps.isMedicationAdded();
		Assert.assertTrue(isAdded);
	}

	@Test(priority = 9)
	public void SetMedReminder() throws InterruptedException {
		medicationSteps.viewMedicationButton();
		medicationSteps.firstDoseStartTime();
		medicationSteps.firstDoseEndTime();
		medicationSteps.secondDoseStartTime();
		medicationSteps.secondDoseEndTime();
		medicationSteps.thirdDoseStartTime();
		medicationSteps.thirdDoseEndTime();
		medicationSteps.toggleAlarmOn();
		medicationSteps.clickSetReminderButton();

	}

	@Test(priority = 10)
	public void ValidateReminderSet() throws InterruptedException {
		boolean isAdded = medicationSteps.isMedicationAdded();
		Assert.assertTrue(isAdded);
		medicationSteps.clickGotoMedicationButton();
		medicationSteps.backButton();
	}

	@Test(priority = 11)
	public void logSymptom() throws InterruptedException {
		symptomsSteps.SelectActionsButton();
		symptomsSteps.clickLogSymptomsButton();
		symptomsSteps.enterSymptomsTitle();
		symptomsSteps.selectSymptomsSeverity();
		symptomsSteps.EnterSymptomsDescription();
		symptomsSteps.setFrequency();
		symptomsSteps.setSymptomsTrigger();
		symptomsSteps.enterAleviatingFactor();
		symptomsSteps.selectDate();
		symptomsSteps.setTime();
		symptomsSteps.clickLogSymptomsAgain();
	}

	@Test(priority = 12)
	public void ValidateSymptomsLog() throws InterruptedException {
		boolean isAdded = symptomsSteps.isSymptomsAdded();
		Assert.assertTrue(isAdded);
		symptomsSteps.backToDashboard();

	}

	@Test(priority = 13)
	public void verifyInitialLatestBP() throws InterruptedException {
		BPRecordSteps.clickBPIcon();
		BPRecordSteps.verifyInitialLatestBPValue();
	}

	@Test(priority = 14)
	public void logBP() throws InterruptedException {
		BPRecordSteps.clickAddNewBPButton();
		BPRecordSteps.addSystolicValue();
		BPRecordSteps.logDiastolicValue();
		BPRecordSteps.addDate();
		BPRecordSteps.setTime();
		BPRecordSteps.clickOnMedication();
		BPRecordSteps.clickRecordBPButton();
	}

	@Test(priority = 15)
	public void verifyBPLog() throws InterruptedException {
		BPRecordSteps.isBPRecorded();
		BPRecordSteps.isRecordRelatedMedDisplayed();
		BPRecordSteps.clickViewBPButton();
	}

	@Test(priority = 16)
	public void VerifyAverageBPChange() {
		BPRecordSteps.VerifyLatestBPValueChange();
		BPRecordSteps.pressBackButton();
	}

	@Test(priority = 17)
	public void verifyInitialHeartratevalue() throws InterruptedException {

		HeartRateSteps.clickheartRateIcon();
		HeartRateSteps.checkInitialHeartRateValue();

	}

	@Test(priority = 18)
	public void logNewHeartRate() throws InterruptedException {
		HeartRateSteps.clickAddNewHeartrateButton();
		HeartRateSteps.recordBeatPerMinute();
		HeartRateSteps.addDate();
		HeartRateSteps.addTime();
		HeartRateSteps.tickOnMedication();
		HeartRateSteps.clickRecordHeartrateButton();
	}

	@Test(priority = 19)
	public void validateHeartRatelogged() {
		HeartRateSteps.isHeartRateRecorded();
		HeartRateSteps.isRecordRelatedMedicationDisplayed();
	}

	@Test(priority = 20)
	public void verifyLatestHeartrateLog() {
		HeartRateSteps.clickViewHeartRateButton();
		HeartRateSteps.VerifyUpdatedHeartRate();
		HeartRateSteps.clickBackButton();
	}

	@Test(priority = 21)
	public void startRecommendationAssessment() throws InterruptedException {
		recommendationSteps.gotoAssessment();
		recommendationSteps.firstQuestion();
		recommendationSteps.thirdQuestion();
		recommendationSteps.fourthQuestion();
		recommendationSteps.fifthQuestion();
		recommendationSteps.sixthQuestion();

	}

	@Test(priority = 22)
	public void verifyRecommendation() throws InterruptedException {
		recommendationSteps.clickSeeRecommendationButton();
		recommendationSteps.iscovid19VaccineDisplayed();
	}

	@Test(priority = 23)
	public void deleteAccount() throws InterruptedException {
		onboardingSteps.deleteAccount();
		onboardingSteps.welcomeBackTextIsDisplayed();
	}

	@AfterTest
	public void QuitApp() {
		driver.quit();
	}
}