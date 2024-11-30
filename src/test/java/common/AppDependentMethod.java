package common;

import driver.CucumberTestRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.HomePage;
import pages.LoginPage;
import java.time.Duration;

public class AppDependentMethod extends CucumberTestRunner {
    /************************************************************
     * Method Name      : navigateURL()
     * Purpose          :
     * Author           :
     ***********************************************************/
    public boolean navigateURL(WebDriver oBrowser, String strURL){
        try{
            oBrowser.navigate().to(strURL);
            waitForElement(oBrowser, LoginPage.obj_Login_Btn, "Clickable", "", 10);
            reports.writeReport(oBrowser, "Screenshot", "URL loaded successful");
            return appInd.compareValues(oBrowser, oBrowser.getTitle(), "actiTIME - Login");
        }catch(Exception e){
            reports.writeReport(oBrowser, "Exception", "Exception in the method 'navigateURL()': " + e);
            return false;
        }
    }



    /************************************************************
     * Method Name      : loginToApplication()
     * Purpose          :
     * Author           :
     ***********************************************************/
    public boolean loginToApplication(WebDriver oBrowser, String userName, String password){
        try{
            Assert.assertTrue(appInd.setObject(oBrowser, LoginPage.obj_UserName_Edit, userName));
            Assert.assertTrue(appInd.setObject(oBrowser, LoginPage.obj_Password_Edit, password));
            Assert.assertTrue(appInd.clickObject(oBrowser, LoginPage.obj_Login_Btn));
            waitForElement(oBrowser, HomePage.obj_SaveChanges_Btn, "Clickable", "", 10);

            boolean blnRes = appInd.verifyText(oBrowser, HomePage.obj_SaveChanges_Btn, "value", "Save Changes");
            if(blnRes) {
                Assert.assertTrue(appInd.clickObject(oBrowser, HomePage.obj_ShortCut_Close_Btn));
                reports.writeReport(oBrowser, "Screenshot", "Login to actiTime was successful");
                return true;
            }
            else return false;

        }catch(Exception e){
            reports.writeReport(oBrowser, "Exception", "Exception in the method 'loginToApplication()': " + e);
            return false;
        }
    }


    /************************************************************
     * Method Name      : logoutFromApplication()
     * Purpose          :
     * Author           :
     ***********************************************************/
    public boolean logoutFromApplication(WebDriver oBrowser){
        try{
            Assert.assertTrue(appInd.clickObject(oBrowser, HomePage.obj_Logout_Link));
            waitForElement(oBrowser, LoginPage.obj_LoginHeader_Label, "Text", "Please identify yourself", 10);
            reports.writeReport(oBrowser, "Screenshot", "Logout from actiTime was successful");
            return appInd.verifyText(oBrowser, LoginPage.obj_LoginHeader_Label, "Text", "Please identify yourself");
        }catch(Exception e){
            reports.writeReport(oBrowser, "Exception", "Exception in the method 'logoutFromApplication()': " + e);
            return false;
        }
    }




    /************************************************************
     * Method Name      : waitForElement()
     * Purpose          :
     * Author           :
     ***********************************************************/
    public boolean waitForElement(WebDriver oBrowser, By objBy, String waitCondition, String text, long timeOut){
        WebDriverWait oWait = null;
        try{
            oWait = new WebDriverWait(oBrowser, Duration.ofSeconds(timeOut));
            switch(waitCondition.toLowerCase()){
                case "clickable":
                    oWait.until(ExpectedConditions.elementToBeClickable(objBy));
                    break;
                case "visibility":
                    oWait.until(ExpectedConditions.visibilityOfElementLocated(objBy));
                    break;
                case "text":
                    oWait.until(ExpectedConditions.textToBePresentInElementLocated(objBy, text));
                    break;
                case "invisibility":
                    oWait.until(ExpectedConditions.invisibilityOfElementLocated(objBy));
                    break;
                default:
                    reports.writeReport(oBrowser, "Fail", "Invalid wait condition '"+waitCondition+"' was provided. Please provided valid wait condition");
                    return false;
            }
            return true;
        }catch(Exception e){
            reports.writeReport(oBrowser, "Exception", "Exception in the method 'waitForElement()': " + e);
            return false;
        }
    }


    /************************************************************
     * Method Name      : threadSleep()
     * Purpose          :
     * Author           :
     ***********************************************************/
    public void threadSleep(long timeOut){
        try{
            Thread.sleep(timeOut);
        }catch(Exception e){
        }
    }

}
