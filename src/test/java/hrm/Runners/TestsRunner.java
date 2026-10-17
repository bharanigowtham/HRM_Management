package hrm.Runners;

import org.testng.annotations.DataProvider;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions
(
		features = "src/test/resources/Featurefiles",
		glue = {"hrm.StepDefinitions", "hrm.Hooks"},
		dryRun = false,									
		monochrome = true,
		tags="@Smoke",
		plugin = {"pretty", "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" }
)


public class TestsRunner extends AbstractTestNGCucumberTests {
	
	@Override
	@DataProvider(parallel=false)
	public Object[][] scenarios() {
		return super.scenarios();
	}
	
}
