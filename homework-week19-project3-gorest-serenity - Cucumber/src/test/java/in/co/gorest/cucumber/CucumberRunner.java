package in.co.gorest.cucumber;


import cucumber.api.CucumberOptions;
import in.co.gorest.testbase.TestBase;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

/**
 * Created by Harsh
 */
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/java/resources/feature/"

)
public class CucumberRunner extends TestBase {



}
