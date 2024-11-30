package common;

import driver.CucumberTestRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.*;

public class AppIndependentMethod extends CucumberTestRunner {
    /************************************************************
     * Method Name      : getPropDetails()
     * Purpose          : it will read the pro file details and store in Map object and returns the same
     * Author           :
     ***********************************************************/
    public Map<String, String> getPropDetails(String filePath) {
        FileInputStream fin = null;
        Properties prop = null;
        Map<String, String> dataMap = null;
        Set<Map.Entry<Object, Object>> data = null;
        Iterator<Map.Entry<Object, Object>> it = null;
        try {
            dataMap = new HashMap<>();
            fin = new FileInputStream(filePath);
            prop = new Properties();
            prop.load(fin);

            data = prop.entrySet();
            it = data.iterator();
            while (it.hasNext() == true) {
                Map.Entry<Object, Object> mp = it.next();
                dataMap.put(mp.getKey().toString(), mp.getValue().toString());
            }
            return dataMap;
        }catch (Exception e) {
            reports.writeReport(null, "Exception", "Exception in the method 'getPropDetails()': " + e);
            return null;
        }finally {
            try {
                fin.close();
                fin = null;
                prop = null;
                data = null;
                it = null;
            } catch (Exception e) {
            }
        }
    }


    /************************************************************
     * Method Name      : getDateTime()
     * Purpose          : it will read the pro file details and store in Map object and returns the same
     * Author           :
     ***********************************************************/
    public String getDateTime(String format){
        Date date = null;
        SimpleDateFormat sdf = null;
        try{
            date = new Date();
            sdf = new SimpleDateFormat(format);
            return sdf.format(date);
        }catch (Exception e) {
            reports.writeReport(null, "Exception", "Exception in the method 'getDateTime()': " + e);
            return null;
        }finally{
            date = null;
            sdf = null;
        }
    }


    /************************************************************
     * Method Name      : launchBrowser()
     * Purpose          : it will read the pro file details and store in Map object and returns the same
     * Author           :
     ***********************************************************/
    public WebDriver launchBrowser(String browserName){
        WebDriver oDriver = null;
        try{
            switch(browserName.toLowerCase()){
                case "chrome":
                    oDriver = new ChromeDriver();
                    break;
                case "firefox":
                    oDriver = new FirefoxDriver();
                    break;
                case "edge":
                    oDriver = new EdgeDriver();
                    break;
                default:
                    reports.writeReport(null, "Fail", "Invalid '"+browserName+"' browser name was mentioned. Please specified valid broswer name");
                    return null;
            }
            if(oDriver==null){
                reports.writeReport(oDriver, "Fail", "Failed to launch '"+browserName+"' browser");
                return null;
            }else{
                reports.writeReport(oDriver, "Pass", "The '"+browserName+"' browser is launched successful");
                oDriver.manage().window().maximize();
                return oDriver;
            }
        }catch(Exception e){
            reports.writeReport(oDriver, "Exception", "Exception in the method 'launchBrowser()': " + e);
            return null;
        }
    }



    /************************************************************
     * Method Name      : clickObject()
     * Purpose          : it will perform click action on the webelement
     * Author           :
     ***********************************************************/
    public boolean clickObject(WebDriver oBrowser, By objBy){
        WebElement oEle = null;
        boolean blnRes = false;
        try{
            oEle = oBrowser.findElement(objBy);
            if(oEle.isDisplayed()){
                oEle.click();
                reports.writeReport(oBrowser, "Pass", "The element '"+objBy+"' was clicked successful");
                blnRes = true;
            }
            return blnRes;
        }catch(Exception e){
            reports.writeReport(oBrowser, "Exception", "Exception in the method 'clickObject()': " + e);
            return false;
        }
        finally {
            oEle = null;
        }
    }


    /************************************************************
     * Method Name      : setObject()
     * Purpose          : it will perform sendKeys action on the webelement
     * Author           :
     ***********************************************************/
    public boolean setObject(WebDriver oBrowser, By objBy, String strData){
        WebElement oEle = null;
        boolean blnRes = false;
        try{
            oEle = oBrowser.findElement(objBy);
            if(oEle.isDisplayed()){
                oEle.sendKeys(strData);
                reports.writeReport(oBrowser, "Pass", "The data '"+strData+"' is entered in the element '"+objBy+"' successful");
                blnRes = true;
            }
            return blnRes;
        }catch(Exception e){
            reports.writeReport(oBrowser, "Exception", "Exception in the method 'setObject()': " + e);
            return false;
        }
        finally {
            oEle = null;
        }
    }


    /************************************************************
     * Method Name      : compareValues()
     * Purpose          :
     * Author           :
     ***********************************************************/
    public boolean compareValues(WebDriver oBrowser, String actual, String expected){
        try{
            if(actual.equalsIgnoreCase(expected)){
                reports.writeReport(oBrowser, "Pass", "The actual '"+actual+"' & expected '"+expected+"' values are matched");
                return true;
            }else{
                reports.writeReport(oBrowser, "Fail", "Mis-match in the actual '"+actual+"' & expected '"+expected+"' values");
                return false;
            }
        }catch(Exception e){
            reports.writeReport(oBrowser, "Exception", "Exception in the method 'compareValues()': " + e);
            return false;
        }
    }



    /************************************************************
     * Method Name      : verifyText()
     * Purpose          :
     * Author           :
     ***********************************************************/
    public boolean verifyText(WebDriver oBrowser, By objBy, String elementType, String expected){
        WebElement oEle = null;
        Select oSelect = null;
        String actual = null;
        boolean blnRes = false;
        try{
            oEle = oBrowser.findElement(objBy);
            if(oEle.isDisplayed()){
                switch(elementType.toLowerCase()){
                    case "text":
                        actual = oEle.getText();
                        break;
                    case "value":
                        actual = oEle.getAttribute("value");
                        break;
                    case "dropdown":
                        oSelect = new Select(oEle);
                        actual = oSelect.getFirstSelectedOption().getText();
                        break;
                    default:
                        reports.writeReport(oBrowser, "Fail", "Invalid element Type '"+elementType+"' was mentioned.");
                        blnRes = false;
                }

                blnRes = compareValues(oBrowser, actual, expected);
            }
            return blnRes;
        }catch(Exception e){
            reports.writeReport(oBrowser, "Exception", "Exception in the method 'verifyText()': " + e);
            return false;
        }
        finally {
            oEle = null;
            oSelect = null;
            actual = null;
        }
    }



    /************************************************************
     * Method Name      : verifyElementPresent()
     * Purpose          :
     * Author           :
     ***********************************************************/
    public boolean verifyElementPresent(WebDriver oBrowser, By objBy){
        List<WebElement> oEle = null;
        try{
            oEle = oBrowser.findElements(objBy);
            if(oEle.size()>0){
                reports.writeReport(oBrowser, "Pass", "The Element '"+objBy+"' exist in the DOM");
                return true;
            }else{
                reports.writeReport(oBrowser, "Fail", "The Element '"+objBy+"' Doesn't exist in the DOM");
                return false;
            }
        }catch(Exception e){
            reports.writeReport(oBrowser, "Exception", "Exception in the method 'verifyElementPresent()': " + e);
            return false;
        }
        finally {
            oEle = null;
        }
    }


    /************************************************************
     * Method Name      : verifyElementNotPresent()
     * Purpose          :
     * Author           :
     ***********************************************************/
    public boolean verifyElementNotPresent(WebDriver oBrowser, By objBy){
        List<WebElement> oEle = null;
        try{
            oEle = oBrowser.findElements(objBy);
            if(oEle.size()>0){
                reports.writeReport(oBrowser, "Fail", "The Element '"+objBy+"' still exist in the DOM");
                return false;
            }else{
                reports.writeReport(oBrowser, "Pass", "The Element '"+objBy+"' removed from the DOM");
                return true;

            }
        }catch(Exception e){
            reports.writeReport(oBrowser, "Exception", "Exception in the method 'verifyElementNotPresent()': " + e);
            return false;
        }
        finally {
            oEle = null;
        }
    }
}
