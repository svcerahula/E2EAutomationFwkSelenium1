package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FacebookLoginPage {

    public FacebookLoginPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver,this);
    }

    @FindBy(id="email")
    WebElement inputTextBoxEmailId;

    @FindBy(name="pass")
    WebElement inputTextBoxPassword;

    @FindBy(xpath="//input[@value=\"Log In\"]")
    WebElement loginInButton;

    @FindBy(xpath="//div[contains(text(),\"doesn't match any account\")]")
    WebElement errorIconLoginPage;

    public void inputEmailIdUsername(String emailId) {
        inputTextBoxEmailId.sendKeys(emailId);
    }

    public void inputPassword(String password1) {
        inputTextBoxPassword.sendKeys(password1);
    }

    public void clickLogInButton() {
        loginInButton.click();
    }
}
