package driver;

import baseClass.LoginBaseClass;
import baseClass.UserBaseClass;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import common.AppDependentMethod;
import common.AppIndependentMethod;
import common.ReportUtils;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import java.util.Map;

@CucumberOptions(
        tags = "@Regression",
        glue = {"stepDefinition"},
        features = {"src/test/resources/featureFiles"},
        plugin = {
                "pretty",
                "html:target/cucumber-report/cucumberReport.html",
                "json:target/cucumber-report/cucumberReport.json"
        },
        monochrome = true, dryRun = false
)


public class CucumberTestRunner extends AbstractTestNGCucumberTests {
    public static AppIndependentMethod appInd = null;
    public static AppDependentMethod appDep = null;
    public static ReportUtils reports = null;
    public static ExtentReports extent = null;
    public static ExtentTest test = null;
    public static String screenshotDir = null;
    public static Map<String, String> propData = null;
    public static LoginBaseClass loginBaseClass;
    public static WebDriver oBrowser = null;
    public static String userNameCrated = null;
    public static UserBaseClass userBaseClass;

    @BeforeSuite
    public void loadClasses(){
        try{
            appInd = new AppIndependentMethod();
            appDep = new AppDependentMethod();
            reports = new ReportUtils();
            propData = appInd.getPropDetails(System.getProperty("user.dir")+"/src/main/resources/config.properties");
            extent = reports.startExtentReport("TestResults");
            loginBaseClass = new LoginBaseClass();
            userBaseClass = new UserBaseClass();
        }catch(Exception e){
            System.out.println("Exception in 'loadClasses()' method. " + e);
        }
    }

    @DataProvider(parallel = false)
    public Object[][] scenarios(){
        return super.scenarios();
    }
}
