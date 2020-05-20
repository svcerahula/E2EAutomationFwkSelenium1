package Tests;

import E2EFwkBase.base;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.UiNavigationUtils;
import java.io.IOException;
import static pageObjects.FacebookHomePage.fbNotificationsButtonXpath;

public class ValidateTabsInHomePage extends base {
    WebDriver driver;
    UiNavigationUtils utilsObj = new UiNavigationUtils();
    public static Logger log = LogManager.getLogger(ValidateTabsInHomePage.class.getName());
    @BeforeMethod
    public void initSetup() throws IOException {
        driver = initializeDriver();
        log.info("Driver initialization done successfully");
        driver.get(prop.getProperty("facebookUrl"));
        utilsObj.loginFacebookPage(driver,prop.getProperty("facebookUsername"),prop.getProperty("facebookPasswd"));
        log.info("logged in FB home page successfully");
    }

    @Test
    public void validateNotificationsButton() {
        WebDriverWait wait =new WebDriverWait(driver,10);
        WebElement notificationButton = wait.until(ExpectedConditions.visibilityOfElementLocated(fbNotificationsButtonXpath));
        Assert.assertTrue(notificationButton.isEnabled());
        log.info("successfully validated the notifications button in the FB homepage");
    }

    @AfterMethod
    public void closeSetup() {
        driver.close();
        log.info("Successfully closed the browser instance");
    }
}
