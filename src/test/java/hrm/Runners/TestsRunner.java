package hrm.Runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions
(
		features = "src/test/resources/Featurefiles",
		glue = {"hrm.StepDefinitions", "hrm.Hooks"},
		dryRun = false,									
		monochrome = true,
		tags="@TS006_TC001",
		plugin = {"pretty", "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" }
)


public class TestsRunner extends AbstractTestNGCucumberTests {
	
}
