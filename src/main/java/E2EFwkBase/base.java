package E2EFwkBase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class base {
        WebDriver wd;
        public Properties prop;
    public WebDriver initializeDriver() throws IOException {

        prop = new Properties();
        FileInputStream fis = new FileInputStream(
                System.getProperty("user.dir")+"\\src\\main\\resources\\data.properties");
        prop.load(fis);
        String browserName = prop.getProperty("browser");
        String chromeDriverPath= prop.getProperty("chromeDriverPath");
        if(browserName.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver",chromeDriverPath);
            wd = new ChromeDriver();
            //chrome
        } else if (browserName.equalsIgnoreCase("firefox")) {
            //firefox
            wd = new FirefoxDriver();
            //internet explorer
        } else if  (browserName.equalsIgnoreCase("firefox")) {
            wd = new InternetExplorerDriver();
        }

        wd.manage().window().maximize();
        wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return wd;
    }
}
