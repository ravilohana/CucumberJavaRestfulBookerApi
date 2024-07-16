package runner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@CucumberOptions(
        plugin = {"html:target/cucumber/cucumber.html"
                ,"pretty",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},
        glue = {"stepsDefs"},
        features = "src/test/resources/features/update_booking_api.feature"
)

public class CucumberTestNGRunnerSequentialTest extends AbstractTestNGCucumberTests{



}
