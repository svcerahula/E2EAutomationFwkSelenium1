package cucumberOptions;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.junit.runner.RunWith;

//@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/features",glue="stepDefinitions")
public class TestRunner1 extends AbstractTestNGCucumberTests {

}