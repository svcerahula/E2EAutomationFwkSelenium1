package pageObjects;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SlideShareHomeResultsPage {
    WebDriver driver;
    Logger log;
    public static String searchResultsXpath
            = "//div[contains(@class,\"searchResults\") and @id=\"control\"]/div/ul/li";
    public static String paginationXpath = "//ul[@class=\"pagination pagination\"]";
    public SlideShareHomeResultsPage(WebDriver webDriver,Logger logObj) {
        driver = webDriver;
        log = logObj;
        PageFactory.initElements(driver,this);
    }
    @FindBy(id="nav-search-query")
    WebElement searchBox;

    @FindBy(xpath="//button[@class=\"button expand\"]")
    WebElement searchButton;

    @FindBy(xpath="//a[text()=\"Next \"]")
    WebElement nextButton;

    @FindBy(xpath="//li[@class=\"current\"]/a")
    WebElement currentPage;

    public String getCurrentPageInSearchResult() {
        return currentPage.getText();
    }

    public void clickNextPageButton() {
        nextButton.click();
    }

    public void typeSearchString(String searchString) {
        searchBox.sendKeys(searchString);
    }

    public void clickOnSearch() {
        searchButton.click();
    }

    /*
    method to enter the search text and click on Search button
     */
    public void enterSearchTextAndClickFind(String searchString) {
        typeSearchString(searchString);
        clickOnSearch();
    }

    public boolean searchThroughPaginationResults(String searchSlideShare) {
        boolean found=false;
        while(!found) {
            String currPage = getCurrentPageInSearchResult();
            log.debug("current result page which is Searched for :"+currPage);
            List<WebElement> searchResults = driver.findElements(By.xpath(searchResultsXpath));
            for (int i = 1; i <= searchResults.size(); i++) {
                WebElement el = driver.findElement(
                        By.xpath(searchResultsXpath + "[" + i + "]" + "/div/a/img")
                );
                String altValue = el.getAttribute("alt");
                log.info("Slideshare topic : "+altValue);
                if (altValue.equalsIgnoreCase(searchSlideShare)) {
                    found = true;
                    el.click(); //click on the book it moves to a new page
                    if(driver.findElement(By.xpath
                            ("//span[contains(text(),\""+searchSlideShare
                                    +"\") and contains(@class,\"title-banner\")]"))
                            .isEnabled())
                        log.info("Successfully did a search for the slideShare : "
                                +searchSlideShare +" and it was Found in the Page : "+currPage);
                    break;
                }
            }
            if(!found && nextButton.getAttribute("href")!=null) {
                clickNextPageButton(); // click on Next Button to search in the next page
            }
        }
        return found;
    }
}
