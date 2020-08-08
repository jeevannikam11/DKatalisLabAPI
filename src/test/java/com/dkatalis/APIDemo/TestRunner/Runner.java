package com.dkatalis.APIDemo.TestRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(monochrome = true, strict = true,
        plugin = { "html:target/cucumber-html-reports", "json:target/cucumber-html-reports/cucumber.json","pretty"},
        features = "src/test/resources/Features",
        glue = {"com/dkatalis/APIDemo"})

public class Runner {
}
