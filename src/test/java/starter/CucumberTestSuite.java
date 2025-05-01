package starter;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

import io.cucumber.junit.platform.engine.Constants;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("/features")
@ConfigurationParameter(key = Constants.GLUE_PROPERTY_NAME, value = "starter")
@ConfigurationParameter(
    key = PLUGIN_PROPERTY_NAME,
    value = "io.cucumber.core.plugin.SerenityReporterParallel,pretty,timeline:build/reports/cucumber/timeline"
)
@ConfigurationParameter(key = "cucumber.filter.tags", value = "not @disabled")
@ConfigurationParameter(key = "cucumber.publish.quiet", value = "false")
@ConfigurationParameter(key = "cucumber.junit-platform.naming-strategy", value = "long")
public class CucumberTestSuite {
}
