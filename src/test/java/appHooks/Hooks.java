package appHooks;

import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utilities.ConfigReader;
import utilities.Loggerload;

public class Hooks {
	static Scenario scenario;

	@Before
	public void scenario(Scenario scenario) throws Throwable {
	
		Loggerload.info(
				"===============================================================================================");
		Loggerload.info(scenario.getSourceTagNames() + " : " + scenario.getName());
		Loggerload.info(
				"-----------------------------------------------------------------------------------------------");

	}

	@AfterStep
	public void afterstep(Scenario scenario) {
		if (scenario.isFailed()) {
			Loggerload.error("Steps Failed");

		}
	}

}
