package Tests;

import DataProviders.FBLoginInputs;
import E2EFwkBase.base;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import utilities.UiNavigationUtils;
import java.io.IOException;
import static pageObjects.FacebookHomePage.facebookIconXpath;
import static pageObjects.FacebookSecondAttemptLoginPage.errorIconXpath;

public class HomePage extends base {

    WebDriver driver;
    UiNavigationUtils utilsObj = new UiNavigationUtils();
    public static Logger log = LogManager.getLogger(HomePage.class.getName());
    String fbUrl;
    @BeforeMethod
    public void initSetup() throws IOException {
        driver = initializeDriver();
        log.info("Driver initialized successfully");
    }
    /*
        Login in Facebook login page using valid and invalid user
     */
    @Test(dataProvider = "loginData",dataProviderClass = FBLoginInputs.class)
    public void loginPage(String url , String username, String password,String userType) throws IOException {
        driver.get(url);
        utilsObj.loginFacebookPage(driver,username,password);
        log.info("Logged in FB homepage successfully");
        WebDriverWait wait = new WebDriverWait(driver,10);
        if(userType.contains("invalid")) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(errorIconXpath));
            log.warn("invalid user login hence doesnt match error appears");
        }
        else {
            String title = driver.getTitle();
            log.info("Page title is : "+title);
            WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(facebookIconXpath));
            Assert.assertTrue(el.isEnabled()
                    ,"Facebook icon element is not enabled");
            utilsObj.logoutFacebookPage(driver,wait);
            log.info("Logged out of FB page successfully");
        }
    }

    @AfterMethod
    public void closeSetup() {
        driver.close();
        log.info("closed browser successfully.");
    }
}
