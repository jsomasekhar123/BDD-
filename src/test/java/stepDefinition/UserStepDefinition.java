package stepDefinition;

import driver.CucumberTestRunner;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.Assert;

public class UserStepDefinition extends CucumberTestRunner {
    @Given("verify user navigates the URL {string}")
    public void verifyUserNavigatesTheURL(String appURL) {
        Assert.assertTrue(userBaseClass.verifyNavigateURL(oBrowser, appURL));
    }

    @And("verify user enters valid credentials {string} and {string}")
    public void verifyUserEntersValidCredentialsAnd(String userName, String password) {
        Assert.assertTrue(userBaseClass.verifyLoginToActiTimeFunctionality(oBrowser, userName, password));
    }

    @Then("verify create user functionality with {string}, {string}, {string}, {string}, {string}, {string} details")
    public void verifyCreateUserFunctionalityWithDetails(String firstName, String lastName, String email, String userName, String password, String retypePassword) {
        Assert.assertTrue(userBaseClass.verifyCreateUserFunctionality(oBrowser, firstName, lastName, email, userName, password, retypePassword));
    }
}
