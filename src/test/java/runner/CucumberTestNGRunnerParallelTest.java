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
        features = "src/test/resources/features"
)

public class CucumberTestNGRunnerParallelTest extends AbstractTestNGCucumberTests{


    @DataProvider(parallel = true)
    public Object[][] parallelScenarioProvider(){
        return super.scenarios();
    }

    @Override
    @Test(description = "Runs Cucumber Scenarios in parallel", dataProvider = "parallelScenarioProvider")
    public void runScenario(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper) {
        super.runScenario(pickleWrapper, featureWrapper);
    }
}
