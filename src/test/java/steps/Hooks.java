package steps;

import io.cucumber.java.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import pages.BasePages;

public class Hooks extends BasePages {
    public Hooks() {
        super(driver);
    }

    @Before
    public static void openDriver() {
        openBrowser();
    }

    @BeforeStep
    public void beforeScenario(Scenario scenario) {
        setScenario(scenario);
    }

    @AfterStep
    public static void takeScreenShot(Scenario scenario) {
        if (scenario.isFailed()) {
            scenario.log("El escenario fallo, refiérase a la imagen");
            final byte [] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot,"image/png","image");
        }
    }

    public static void logValueToReport(String message, int valueToLog) {
        Scenario scenario = getScenario();
        scenario.log(message + valueToLog);
    }

    @After
    public static void cleanBrowser(){
        BasePages.closeBrowser();
    }

}
