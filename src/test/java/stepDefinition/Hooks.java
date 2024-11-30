package stepDefinition;

import driver.CucumberTestRunner;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks extends CucumberTestRunner {
    @Before
    public void setup(Scenario scenario){
        String scenarioName = null;
        try{
            scenarioName = scenario.getName();
            test = extent.startTest(scenarioName);
            oBrowser = appInd.launchBrowser("Chrome");
            if(oBrowser!=null) reports.writeReport(oBrowser, "Pass", "Browser launched successful");
            else reports.writeReport(null, "Fail", "Failed to launch the browser");
        }catch(Exception e){
            reports.writeReport(null, "", "");
        }
    }

    @After
    public void tearDown(Scenario scenario){
        String scenarioName = null;
        TakesScreenshot ts = null;
        try{
            scenarioName = scenario.getName().replace(" ", "_");
            if(scenario.isFailed()==true){
                ts = (TakesScreenshot) oBrowser;
                byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
                scenario.attach(scenarioName, "image/png", scenarioName);
            }
            reports.endExtentReport(test);
            if(oBrowser!=null){
                oBrowser.close();
            }
        }catch(Exception e){
            reports.writeReport(null, "", "");
        }
    }
}
