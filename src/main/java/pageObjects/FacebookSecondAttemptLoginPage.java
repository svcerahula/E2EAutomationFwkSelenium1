package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FacebookSecondAttemptLoginPage {
    WebDriver driver1;
    public static By errorIconXpath = By.xpath("//div[contains(text(),\"doesn't match any account\")]");
    public FacebookSecondAttemptLoginPage(WebDriver driver) {
        driver1 = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath="//a[contains(text(),\"Forgotten password\")]")
    WebElement forgottenPasswordLink;

    @FindBy(id="email")
    WebElement inputEmail;
}
