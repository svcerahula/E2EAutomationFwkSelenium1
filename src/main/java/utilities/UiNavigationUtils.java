package utilities;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.FacebookHomePage;
import pageObjects.FacebookLoginPage;
import pageObjects.FacebookSecondAttemptLoginPage;

import java.io.File;
import java.io.IOException;

public class UiNavigationUtils {

    public WebDriver loginFacebookPage(WebDriver driver,String userName, String passWord) {
        FacebookLoginPage lPage = new FacebookLoginPage(driver);
        lPage.inputEmailIdUsername(userName);
        lPage.inputPassword(passWord);
        lPage.clickLogInButton();
        return driver;
    }

    public WebDriver logoutFacebookPage(WebDriver driver, WebDriverWait wait) {
        FacebookHomePage homePage = new FacebookHomePage(driver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(FacebookHomePage.accountButtonXpath)).click();
        //finally click on logout
        homePage.logOutButtonClick();
        return driver;
    }

    public String getScreenshotPath(WebDriver driver, String fileName, Logger log) throws IOException {
        TakesScreenshot ts =  (TakesScreenshot)driver;
        File  source = ts.getScreenshotAs(OutputType.FILE);
        String destFile = System.getProperty("user.dir")+"\\reports\\"+fileName+".png";
        log.debug("Destination screenshot save location is : "+destFile);
        FileUtils.copyFile(source,new File(destFile));
        return destFile;
    }
}
