package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FacebookHomePage {
    WebDriver driver;
    public static By accountButtonXpath = By.xpath("//div[@aria-label=\"Account\"]");
    public static By facebookIconXpath = By.xpath("//a[@aria-label=\"Facebook\"]");
    public static By facebookTitleXpath = By.xpath("//head/title");
    public static By fbNotificationsButtonXpath = By.xpath("//div[@aria-label=\"Notifications\" and @role=\"button\" and @aria-hidden=\"true\"]");
    public static String naviBarLinks = "//div[@aria-label=\"Facebook\" and @role=\"navigation\"]/ul/li";
    public FacebookHomePage(WebDriver webDriver) {
        driver = webDriver;
        PageFactory.initElements(webDriver,this);
    }

    @FindBy(xpath="//span[text()=\"Log Out\"]")
    WebElement logOutButton;

    public void logOutButtonClick() {
        logOutButton.click();
    }

    public String getAttributeInNavigationBar(String attr,int liPosition) {
        return driver.findElement(By.xpath(naviBarLinks+"["
                +
                liPosition
                +"]"+"/span/div/a")).getAttribute(attr);
    }

}
