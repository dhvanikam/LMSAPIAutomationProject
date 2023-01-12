package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(plugin = { "pretty", "html:target/Api.html" }, // reporting purpose
		monochrome = false, // console output
		tags = "", // tags from feature file
		features = { "src/test/resources/features" }, // location of feature files
		glue = { "stepDefinition"}) // location of step definition files

public class TestRunner extends AbstractTestNGCucumberTests {

	

}