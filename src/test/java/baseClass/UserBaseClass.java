package baseClass;

import driver.CucumberTestRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.HomePage;
import pages.UserPage;

public class  UserBaseClass extends CucumberTestRunner {

    public boolean verifyNavigateURL(WebDriver oBrowser, String appURL){
        try{
            return appDep.navigateURL(oBrowser, appURL);
        }catch(Exception e){
            reports.writeReport(oBrowser, "Exception", "Exception in the method 'verifyNavigateURL()': " + e);
            return false;
        }
    }


    public boolean verifyLoginToActiTimeFunctionality(WebDriver oBrowser, String userName, String password){
        try{
            return appDep.loginToApplication(oBrowser, userName, password);
        }catch(Exception e){
            reports.writeReport(oBrowser, "Exception", "Exception in the method 'verifyLoginToActiTimeFunctionality()': " + e);
            return false;
        }
    }


    public boolean verifyCreateUserFunctionality(WebDriver oBrowser, String firstName, String lastName, String email, String userName, String password, String retypePassword){
        try{
            Assert.assertTrue(appInd.clickObject(oBrowser, HomePage.obj_USERS_Menu));
            reports.writeReport(oBrowser, "Screenshot", "Before creating the user");
            appDep.waitForElement(oBrowser, UserPage.obj_AddUsers_Btn, "Clickable", "", 10);
            Assert.assertTrue(appInd.clickObject(oBrowser, UserPage.obj_AddUsers_Btn));
            appDep.waitForElement(oBrowser, UserPage.obj_CreateUser_Btn, "Clickable", "", 10);

            Assert.assertTrue(appInd.setObject(oBrowser, UserPage.obj_User_FirstName_Edit, firstName));
            Assert.assertTrue(appInd.setObject(oBrowser, UserPage.obj_User_LastName_Edit, lastName));
            Assert.assertTrue(appInd.setObject(oBrowser, UserPage.obj_User_Email_Edit, email));
            Assert.assertTrue(appInd.setObject(oBrowser, UserPage.obj_User_UserName_Edit, userName));
            Assert.assertTrue(appInd.setObject(oBrowser, UserPage.obj_User_Password_Edit, password));
            Assert.assertTrue(appInd.setObject(oBrowser, UserPage.obj_User_RetypePassword_Edit, retypePassword));
            reports.writeReport(oBrowser, "Screenshot", "During creation of user");
            Assert.assertTrue(appInd.clickObject(oBrowser, UserPage.obj_CreateUser_Btn));
            userNameCrated = lastName + ", " + firstName;
            appDep.threadSleep(2000);
            appDep.waitForElement(oBrowser, By.xpath(String.format(UserPage.obj_userName_Link, userNameCrated)), "Text", userNameCrated, 10);
            reports.writeReport(oBrowser, "Screenshot", "After creating the user");
            return appInd.verifyElementPresent(oBrowser, By.xpath(String.format(UserPage.obj_userName_Link, userNameCrated)));
        }catch(Exception e){
            reports.writeReport(oBrowser, "Exception", "Exception in the method 'verifyCreateUserFunctionality()': " + e);
            return false;
        }
    }
}
