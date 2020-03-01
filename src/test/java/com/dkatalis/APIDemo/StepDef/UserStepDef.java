package com.dkatalis.APIDemo.StepDef;

import com.dkatalis.APIDemo.StepDefHelper.UserStepDefHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class UserStepDef {

    public UserStepDefHelper userStepDefHelper = new UserStepDefHelper();

    @Given("User enter number {string} and number {string}")
    public void userEnterNumberAndNumber(String a, String b) {
        userStepDefHelper.getNumbers(a,b);
    }

    @When("User give operation {string}")
    public void userGiveOperation(String method) {

        userStepDefHelper.getRequiredMethods(method);
        
    }

    @Then("I receive valid Response as output as {string}")
    public void iReceiveValidResponseAsOutputAs(String output) {

        Assert.assertTrue(userStepDefHelper.output(output));
    }



    @Given("Load response from param {string} and value {string}")
    public void loadResponseFromParamAndValue(String param, String value) {
        Assert.assertNotNull(userStepDefHelper.getApiResponse(param, value));
    }

    @And("Compare both the responses line by line")
    public void compareBothTheResponsesLineByLine() {
        Assert.assertNotNull(userStepDefHelper.compareBothTheResponsesLineByLine());
    }
}
