package Tests;

import E2EFwkBase.base;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.SlideShareHomeResultsPage;
import java.io.IOException;

public class SlideShareAppTests extends base {

    WebDriver driver;
    public static Logger log = LogManager.getLogger(SlideShareAppTests.class.getName());

    /*
    initialize setup
     */
    @BeforeMethod
    public void initSetup() throws IOException {
        driver = initializeDriver();
        log.info("Driver initialized successfully");
        driver.get(prop.getProperty("slideShareUrl"));
    }

    @Test
    public void searchPaginationResults() {
        SlideShareHomeResultsPage resultsPage = new SlideShareHomeResultsPage(driver,log);
        resultsPage.enterSearchTextAndClickFind("Selenium");
        resultsPage.searchThroughPaginationResults("Increase selenium tests stability via java script");
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }

}
