package Tests;

import E2EFwkBase.base;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.FacebookHomePage;
import utilities.UiNavigationUtils;

import java.io.IOException;
import java.util.List;

import static pageObjects.FacebookHomePage.naviBarLinks;

public class ValidateNavigationBar extends base {

    WebDriver driver;
    UiNavigationUtils utilsObj = new UiNavigationUtils();
    public static Logger log = LogManager.getLogger(ValidateNavigationBar.class.getName());
    @BeforeMethod
    public void initSetup() throws IOException {
        driver = initializeDriver();
        driver.get(prop.getProperty("facebookUrl"));
        utilsObj.loginFacebookPage(driver,prop.getProperty("facebookUsername"),prop.getProperty("facebookPasswd"));
    }

    @Test
    public void validateNavigationBar() {
        FacebookHomePage homePage = new FacebookHomePage(driver);
        List<WebElement> naviBarAllLIElements = driver.findElements(By.xpath
                (naviBarLinks));
        log.info("Get all the navigation bar links elements");
        boolean found = false;
        for(int i=1; i<=naviBarAllLIElements.size();i++) {
            String ariaLabel = homePage.getAttributeInNavigationBar("aria-label",i);
            System.out.println("Aria Label value : "+ariaLabel);
            log.info("fetch all the aria label of the li elements in the nav bar");
            if(ariaLabel.equals("Invalid-AriaLabel")) {
                found = true;
                break;
            }
        }
        Assert.assertTrue(found,"Marketplace link not found in the navigation bar");
        log.info("successfully validated the aria label for Marketplace value");
    }

    @AfterMethod
    public void closeSetup() {
        driver.close();
        log.info("browser instance closed successfully.");
    }
}
