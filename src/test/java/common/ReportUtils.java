package common;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import driver.CucumberTestRunner;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import java.io.File;

public class ReportUtils extends CucumberTestRunner {
    /************************************************************
     * Method Name      : startExtentReport()
     * Purpose          : it will create the folder and file structure for the extent report
     * Author           :
     ***********************************************************/
    public ExtentReports startExtentReport(String reportFileName){
        String resultPath = null;
        File objResultPath = null;
        File objScreenshot = null;
        try{
            resultPath = System.getProperty("user.dir") + "/target/extent-report";
            objResultPath = new File(resultPath);
            if(!objResultPath.exists()){
                objResultPath.mkdirs();
            }

            screenshotDir = resultPath + "/screenshot";
            objScreenshot = new File(screenshotDir);
            if(!objScreenshot.exists()){
                objScreenshot.mkdirs();
            }

            extent = new ExtentReports(resultPath +"/"+ reportFileName+".html", false);
            extent.addSystemInfo("Host Name", System.getProperty("os.name"));
            extent.addSystemInfo("User Name", System.getProperty("user.name"));
            extent.addSystemInfo("Environment", propData.get("environment"));
            extent.addSystemInfo("appName", propData.get("appName"));
            extent.loadConfig(new File(System.getProperty("user.dir")+"/extent-config.xml"));
            return extent;
        }catch(Exception e){
            System.out.println("Exception in 'startExtentReport()' method. " + e);
            return null;
        }finally {
            resultPath = null;
            objResultPath = null;
            objScreenshot = null;
        }
    }


    /************************************************************
     * Method Name      : endExtentReport()
     * Purpose          : it will end/flush the extent report
     * Author           :
     ***********************************************************/
    public void endExtentReport(ExtentTest test){
        try{
            extent.endTest(test);
            extent.flush();
            //extent.close();
        }catch(Exception e){
            System.out.println("Exception in 'endExtentReport()' method. " + e);
        }
    }


    /************************************************************
     * Method Name      : captureScreenshot()
     * Purpose          : it will capture the screenshot image
     * Author           :
     ***********************************************************/
    public String captureScreenshot(WebDriver oBrowser){
        String filePath = null;
        File objSrc = null;
        try{
            filePath = screenshotDir + "/screenshot_"+appInd.getDateTime("YYYYMMddhhmmss")+".png";
            objSrc = ((TakesScreenshot) oBrowser).getScreenshotAs(OutputType.FILE);
            FileHandler.copy(objSrc, new File(filePath));
            return filePath;
        }catch(Exception e){
            System.out.println("Exception in 'captureScreenshot()' method. " + e);
            return null;
        }
    }


    /************************************************************
     * Method Name      : writeReport()
     * Purpose          : it will write the report to ExtentReport
     * Author           :
     ***********************************************************/
    public void writeReport(WebDriver oBrowser, String status, String description){
        try{
            switch(status.toLowerCase()){
                case "pass":
                    test.log(LogStatus.PASS, description);
                    break;
                case "fail":
                    test.log(LogStatus.FAIL, description + ": " + test.addScreenCapture(captureScreenshot(oBrowser)));
                    break;
                case "warning":
                    test.log(LogStatus.WARNING, description);
                    break;
                case "exception":
                    test.log(LogStatus.FATAL, description + ": " + test.addScreenCapture(captureScreenshot(oBrowser)));
                    break;
                case "info":
                    test.log(LogStatus.INFO, description);
                    break;
                case "screenshot":
                    test.log(LogStatus.PASS, description + ": " + test.addScreenCapture(captureScreenshot(oBrowser)));
                    break;
                default:
                    System.out.println("Invalid status '"+status+"' was mentioned. Please provide appropriate status");
            }
        }catch(Exception e){
            System.out.println("Exception in 'writeReport()' method. " + e);
        }
    }
}
