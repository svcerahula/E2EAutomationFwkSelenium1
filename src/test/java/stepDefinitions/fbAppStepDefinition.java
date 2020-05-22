package stepDefinitions;

import E2EFwkBase.base;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pageObjects.FacebookLoginPage;

import static pageObjects.FacebookHomePage.facebookIconXpath;
import static pageObjects.FacebookSecondAttemptLoginPage.errorIconXpath;

@RunWith(Cucumber.class)
public class fbAppStepDefinition extends base {
        public WebDriver driver;

        @Before
        public void initSetup() {
            System.out.println("Place all your init code here");
        }
        @Given("^Initialize browser with chrome$")
        public void initialize_browser_with_chrome() throws Throwable {
            driver = initializeDriver();
        }

        @When("^User enters \"([^\"]*)\" and \"([^\"]*)\" and clicks on LogIn button$")
        public void user_enters_something_and_something_and_clicks_on_login_button(String username, String password)
                throws Throwable {
            FacebookLoginPage lPage = new FacebookLoginPage(driver);
            lPage.inputEmailIdUsername(username);
            lPage.inputPassword(password);
            lPage.clickLogInButton();
        }

        @Then("^verify the user is successfully logged in$")
        public void verify_the_user_is_successfully_logged_in() throws Throwable {
            WebDriverWait wait = new WebDriverWait(driver,10);
            WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(facebookIconXpath));
            Assert.assertTrue(el.isEnabled()
                    ,"Facebook icon element is not enabled after Login is done successfully");
        }

        @And("^Navigate to \"([^\"]*)\" login page$")
        public void navigate_to_facebook_login_page(String url) throws Throwable {
            driver.get(url);
        }

        @When("^User enters (.+) and (.+) and clicks on LogIn button$")
        public void user_enters_and_and_clicks_on_login_button(String username, String password) throws Throwable {
            FacebookLoginPage lPage = new FacebookLoginPage(driver);
            lPage.inputEmailIdUsername(username);
            lPage.inputPassword(password);
            lPage.clickLogInButton();
        }

        @Then("^verify the login is not successful$")
        public void verify_the_login_is_not_successful() throws Throwable {
            WebDriverWait wait = new WebDriverWait(driver,10);
            wait.until(ExpectedConditions.visibilityOfElementLocated(errorIconXpath));
            System.out.println("invalid user login hence doesn't match error appears");
        }

    @After
        public void tearDown() {
            driver.quit();
        }
}