package baseClass;

import driver.CucumberTestRunner;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.HomePage;
import pages.UserPage;
import java.util.List;
import java.util.Map;

public class LoginBaseClass extends CucumberTestRunner {
    public boolean verifyNavigateURL(WebDriver oBrowser, DataTable dataTable){
        List<Map<String, String>> inputData = null;
        try{
            inputData = dataTable.asMaps(String.class, String.class);
            return appDep.navigateURL(oBrowser, inputData.get(0).get("appURL"));
        }catch(Exception e){
            reports.writeReport(oBrowser, "Exception", "Exception in the method 'verifyNavigateURL()': " + e);
            return false;
        }
    }


    public boolean verifyLoginToActiTimeFunctionality(WebDriver oBrowser, DataTable dataTable){
        List<Map<String, String>> inputData = null;
        try{
            inputData = dataTable.asMaps(String.class, String.class);
            return appDep.loginToApplication(oBrowser, inputData.get(0).get("userName"), inputData.get(0).get("password"));
        }catch(Exception e){
            reports.writeReport(oBrowser, "Exception", "Exception in the method 'verifyLoginToActiTimeFunctionality()': " + e);
            return false;
        }
    }


    public boolean verifyCreateUserFunctionality(WebDriver oBrowser, DataTable dataTable){
        List<Map<String, String>> inputData = null;
        try{
            inputData = dataTable.asMaps(String.class, String.class);
            Assert.assertTrue(appInd.clickObject(oBrowser, HomePage.obj_USERS_Menu));
            reports.writeReport(oBrowser, "Screenshot", "Before creating the user");
            appDep.waitForElement(oBrowser, UserPage.obj_AddUsers_Btn, "Clickable", "", 10);
            Assert.assertTrue(appInd.clickObject(oBrowser, UserPage.obj_AddUsers_Btn));
            appDep.waitForElement(oBrowser, UserPage.obj_CreateUser_Btn, "Clickable", "", 10);

            Assert.assertTrue(appInd.setObject(oBrowser, UserPage.obj_User_FirstName_Edit, inputData.get(0).get("firstName")));
            Assert.assertTrue(appInd.setObject(oBrowser, UserPage.obj_User_LastName_Edit, inputData.get(0).get("lastName")));
            Assert.assertTrue(appInd.setObject(oBrowser, UserPage.obj_User_Email_Edit, inputData.get(0).get("email")));
            Assert.assertTrue(appInd.setObject(oBrowser, UserPage.obj_User_UserName_Edit, inputData.get(0).get("userName")));
            Assert.assertTrue(appInd.setObject(oBrowser, UserPage.obj_User_Password_Edit, inputData.get(0).get("password")));
            Assert.assertTrue(appInd.setObject(oBrowser, UserPage.obj_User_RetypePassword_Edit, inputData.get(0).get("retypePassword")));
            reports.writeReport(oBrowser, "Screenshot", "During creation of user");
            Assert.assertTrue(appInd.clickObject(oBrowser, UserPage.obj_CreateUser_Btn));
            userNameCrated = inputData.get(0).get("lastName") + ", " + inputData.get(0).get("firstName");
            appDep.waitForElement(oBrowser, By.xpath(String.format(UserPage.obj_userName_Link, userNameCrated)), "Text", userNameCrated, 10);
            reports.writeReport(oBrowser, "Screenshot", "After creating the user");
            return appInd.verifyElementPresent(oBrowser, By.xpath(String.format(UserPage.obj_userName_Link, userNameCrated)));
        }catch(Exception e){
            reports.writeReport(oBrowser, "Exception", "Exception in the method 'verifyCreateUserFunctionality()': " + e);
            return false;
        }finally {
            inputData = null;
        }
    }


    public boolean verifyDeleteUserFunctionality(WebDriver oBrowser){
        try{
            reports.writeReport(oBrowser, "Screenshot", "Before deleting the user");
            Assert.assertTrue(appInd.clickObject(oBrowser, By.xpath(String.format(UserPage.obj_userName_Link, userNameCrated))));
            appDep.waitForElement(oBrowser, UserPage.obj_SaveChanges_Btn, "Clickable", "", 10);
            Assert.assertTrue(appInd.clickObject(oBrowser, UserPage.obj_DeleteUser_Btn));
            appDep.threadSleep(2000);
            oBrowser.switchTo().alert().accept();
            appDep.threadSleep(2000);
            reports.writeReport(oBrowser, "Screenshot", "After deleting the user");
            return appInd.verifyElementNotPresent(oBrowser, By.xpath(String.format(UserPage.obj_userName_Link, userNameCrated)));
        }catch(Exception e){
            reports.writeReport(oBrowser, "Exception", "Exception in the method 'verifyDeleteUserFunctionality()': " + e);
            return false;
        }
    }
}
