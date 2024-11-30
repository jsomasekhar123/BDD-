package stepDefinition;

import driver.CucumberTestRunner;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.Assert;

public class LoginStepDefinition extends CucumberTestRunner {

    @Given("verify user navigates the URL")
    public void verifyUserNavigatesTheURL(DataTable dataTable) {
        Assert.assertTrue(loginBaseClass.verifyNavigateURL(oBrowser, dataTable));
    }

    @And("verify user enters valid credentials")
    public void verifyUserEntersValidCredentials(DataTable dataTable) {
        Assert.assertTrue(loginBaseClass.verifyLoginToActiTimeFunctionality(oBrowser, dataTable));
    }

    @And("verify user logout from the application")
    public void verifyUserLogoutFromTheApplication() {
        Assert.assertTrue(appDep.logoutFromApplication(oBrowser));
    }

    @Then("verify create user functionality")
    public void verifyCreateUserFunctionality(DataTable dataTable) {
        Assert.assertTrue(loginBaseClass.verifyCreateUserFunctionality(oBrowser, dataTable));
    }

    @Then("verify delete user functionality")
    public void verifyDeleteUserFunctionality() {
        Assert.assertTrue(loginBaseClass.verifyDeleteUserFunctionality(oBrowser));
    }
}
