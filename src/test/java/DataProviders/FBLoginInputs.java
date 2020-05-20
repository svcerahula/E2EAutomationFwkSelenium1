package DataProviders;

import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class FBLoginInputs {

    Properties appProps = new Properties();
    public String fbUrl;
    @DataProvider
    public Object[][] loginData() throws IOException {
        FileInputStream fis = new FileInputStream("C:\\Rahula Space\\Intellij Idea Projects" +
                "\\githubRepo\\E2EFwkJava\\src\\main\\resources\\data.properties");

        appProps.load(fis);
        fbUrl = appProps.getProperty("facebookUrl");
        return new Object[][] {
            {
                    fbUrl, appProps.getProperty("facebookUsername")
                    , appProps.getProperty("facebookPasswd"),"registered user"
            },
            {
                    fbUrl,"sm_rahula@gmail.com","xasgdisgf","invalid user"
            }
        };
    }

}
