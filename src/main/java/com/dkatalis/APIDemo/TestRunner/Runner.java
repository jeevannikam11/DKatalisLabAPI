package com.dkatalis.APIDemo.TestRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(monochrome = true, strict = true,
        features = "src/test/resources/Features",
        glue = {"com/dkatalis/APIDemo"},
        plugin={"pretty"})

public class Runner {
}
