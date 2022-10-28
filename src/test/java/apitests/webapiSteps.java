package apitests;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.api.AlertPageObject;
import org.api.MarketAlertWebViewer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class webapiSteps {

    WebDriver driver;
    WebDriverWait wait;
    MarketAlertWebViewer viewer;
    List<AlertPageObject> alerts;


    @After
    public void afterTest(){
        driver.quit();
    }


    @Given("I am a user of marketalertum")
    public void iAmAUserOfMarketalertum() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Owner\\Desktop\\University\\Year3_Sem1\\CPS3230 - Fundamentals of Software Testing\\assignment_part_1\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        viewer = new MarketAlertWebViewer(driver, wait);
    }

    @When("I login using valid credentials")
    public void iLoginUsingValidCredentials() {
        viewer.LogIn("f9d4119e-c37e-4af5-9a52-eec8744ff433");
    }

    @Then("I should see my alerts")
    public void iShouldSeeMyAlerts() {
        Assertions.assertEquals("https://www.marketalertum.com/Alerts/List", driver.getCurrentUrl());
    }

    @When("I login using invalid credentials")
    public void iLoginUsingInvalidCredentials() {
        viewer.LogIn("wrong id");
    }

    @Then("I should see the login screen again")
    public void iShouldSeeTheLoginScreenAgain() {
        Assertions.assertEquals("https://www.marketalertum.com/Alerts/Login", driver.getCurrentUrl());
    }


    @Given("I am an administrator of the website and I upload {int} alerts")
    public void iAmAnAdministratorOfTheWebsiteAndIUploadAlerts(int arg0) {
        for (int i = 0; i < arg0; i++) {
            viewer.PostAlert(
                    "f9d4119e-c37e-4af5-9a52-eec8744ff433",
                    1,
                    "Title",
                    "Sample Text",
                    100,
                    "https://www.redbubble.com/i/throw-pillow/sample-text-by-BoneDaddy69420/63939290.5X2YF",
                    "https://ih1.redbubble.net/image.1895061325.9290/throwpillow,small,1000x-bg,f8f8f8-c,0,200,1000,1000.jpg"
            );
        }

    }

    @When("I view a list of {int} alerts")
    public void iViewAListOfAlerts(int arg0) {
        viewer.LogIn("f9d4119e-c37e-4af5-9a52-eec8744ff433");
        alerts = viewer.GetAlerts(arg0);
    }

    @Then("each alert should contain an icon")
    public void eachAlertShouldContainAnIcon() {
        for (AlertPageObject alert: alerts) {
            Assertions.assertEquals("icon-car.png", alert.GetAlertIconURL());
        }
    }

    @And("each alert should contain a heading")
    public void eachAlertShouldContainAHeading() {
        for (AlertPageObject alert: alerts) {
            Assertions.assertEquals("Title (just now)", alert.GetHeading());
        }
    }

    @And("each alert should contain a description")
    public void eachAlertShouldContainADescription() {
        for (AlertPageObject alert: alerts) {
            Assertions.assertEquals("Sample Text", alert.GetDesc());
        }
    }

    @And("each alert should contain an image")
    public void eachAlertShouldContainAnImage() {
        for (AlertPageObject alert: alerts) {
            Assertions.assertEquals("https://ih1.redbubble.net/image.1895061325.9290/throwpillow,small,1000x-bg,f8f8f8-c,0,200,1000,1000.jpg", alert.GetImageURL());
        }
    }

    @And("each alert should contain a price")
    public void eachAlertShouldContainAPrice() {
        for (AlertPageObject alert: alerts) {
            Assertions.assertEquals("Price: €1.00", alert.GetPrice());
        }
    }

    @And("each alert should contain a link to the original product website")
    public void eachAlertShouldContainALinkToTheOriginalProductWebsite() {
        for (AlertPageObject alert: alerts) {
            Assertions.assertEquals("https://www.redbubble.com/i/throw-pillow/sample-text-by-BoneDaddy69420/63939290.5X2YF", alert.GetProductURL());
        }
    }

    @Then("I should see {int} alerts")
    public void iShouldSeeAlerts(int arg0) {
        Assertions.assertEquals(arg0, alerts.stream().count());
    }

    @Given("I am an administrator of the website and I upload an alert of type {int}")
    public void iAmAnAdministratorOfTheWebsiteAndIUploadAnAlertOfTypeAlertType(int arg0) {
        viewer.PostAlert(
                "f9d4119e-c37e-4af5-9a52-eec8744ff433",
                arg0,
                "Title",
                "Sample Text",
                100,
                "https://www.redbubble.com/i/throw-pillow/sample-text-by-BoneDaddy69420/63939290.5X2YF",
                "https://ih1.redbubble.net/image.1895061325.9290/throwpillow,small,1000x-bg,f8f8f8-c,0,200,1000,1000.jpg"
        );

    }

    @And("the icon displayed should be {string}")
    public void theIconDisplayedShouldBe(String arg0) {
        Assertions.assertEquals(arg0, alerts.get(0).GetAlertIconURL());
    }
}
