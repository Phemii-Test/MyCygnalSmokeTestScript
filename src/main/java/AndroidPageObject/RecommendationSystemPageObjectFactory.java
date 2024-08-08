package AndroidPageObject;

import java.net.MalformedURLException;
import java.net.URL;
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

public class RecommendationSystemPageObjectFactory {

	private AppiumDriver driver;

	public RecommendationSystemPageObjectFactory(AppiumDriver driver) {
		this.driver = driver;
	}

	private By recommendationIcon = By.className("android.widget.ImageView");
	private By gotoAssessmentButton = AppiumBy.accessibilityId("Go to Assessment");
	private By continueButton = AppiumBy.accessibilityId("Continue");
	private By commonElements1 = By.className("android.view.View");
	private By checkBox = By.className("android.widget.CheckBox");
	private By submitButton = AppiumBy.accessibilityId("Submit");
	private By recommendationWelcomeElement = AppiumBy.accessibilityId(
			"👋\\\\nWe are so glad you are\\\\ntaking steps to own your\\\\nhealth\\\\nReview each category below and learn how to live your most healthy life.\\\\n\\\\nYou may update your information on the dashboard at any time to keep the recommendations current");
	private By seeRecomendationButton = AppiumBy.accessibilityId("See Recommendation");
	private By HPVRecommendation = AppiumBy.accessibilityId("HPV Vaccine");
	private By covid19Recommendation = AppiumBy.accessibilityId("Updated COVID-19 Vaccines (2023â2024)");

	public void gotoAssessment() {
		driver.findElements(recommendationIcon).get(4).click();
		driver.findElement(gotoAssessmentButton).click();
	}

	public void firstQuestion() {
		driver.findElements(checkBox).get(1).click();
		driver.findElement(continueButton).click();
	}

	public void secondQuestion() {

//How many packs of cigarettes do you typically smoke in a day?
		WebElement scroll2 = driver.findElements(commonElements1).get(11);
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) scroll2).getId(), "endX", 324, "endY", 1881));

//perform a Scroll upward.
		WebElement scrollUp = driver.findElements(commonElements1).get(12);
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) scrollUp).getId(), "endX", 561, "endY", 660));

		driver.findElement(continueButton).click();
	}

	public void thirdQuestion() {

		driver.findElements(checkBox).get(0).click();
		driver.findElement(continueButton).click();
	}

	public void fourthQuestion() {
		WebElement scroll3 = driver.findElements(commonElements1).get(4);
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) scroll3).getId(), "endX", 542, "endY", 618));

		// perform a Scroll upward.
		WebElement scrollUp2 = driver.findElements(commonElements1).get(12);
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) scrollUp2).getId(), "endX", 561, "endY", 660));

		WebElement scroll4 = driver.findElements(commonElements1).get(11);
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) scroll4).getId(), "endX", 544, "endY", 1687));

		driver.findElement(continueButton).click();

	}

	public void fifthQuestion() throws InterruptedException {
//click the vaccines that have been taken.
		driver.findElements(commonElements1).get(3).click();
		driver.findElements(commonElements1).get(5).click();
		WebElement scroll = driver.findElements(commonElements1).get(7);
		scroll.click();

//scroll up and click the continue button
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) scroll).getId(), "endX", 450, "endY", 961));

		driver.findElement(AppiumBy.accessibilityId("Continue")).click();
	}

	public void sixthQuestion() throws InterruptedException {

//select the vaccines that have been taken
		driver.findElements(commonElements1).get(3).click();
		driver.findElements(commonElements1).get(4).click();
		driver.findElements(commonElements1).get(5).click();

//Click the submit button
		driver.findElement(submitButton).click();
	}

	public boolean isRecommendationWelcomeNotedDisplayed() throws InterruptedException {
		driver.wait(5000);
		return driver.findElement(recommendationWelcomeElement).isDisplayed();
	}

	public void clickSeeRecommendationButton() {
		driver.findElement(seeRecomendationButton).click();
	}

	public boolean isHPVVaccinceDisplayed() {

		return driver.findElement(HPVRecommendation).isDisplayed();
	}

	public boolean iscovid19VaccineDisplayed() throws InterruptedException {
		Thread.sleep(5000);

		return driver.findElement(covid19Recommendation).isDisplayed();
	}

	public void clickHomeButton() {
		driver.findElements(recommendationIcon).get(4).click();
	}

}
