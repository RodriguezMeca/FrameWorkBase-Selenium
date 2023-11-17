package pages;

import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ElementNotFoundException;

import java.text.Normalizer;
import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BasePages {

    protected static WebDriver driver;
    private static WebDriverWait wait;

    private static Scenario scenario;

    public BasePages(WebDriver driver) {
        BasePages.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public static void navigateTo(String url) {
        driver.manage().window().maximize();
        driver.get(url);
    }

    public static void closeBrowser() {
        driver.quit();
    }

    public static void openBrowser() {
        WebDriverManager.chromedriver().clearDriverCache().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        driver = new ChromeDriver(chromeOptions);
    }

    private WebElement find(String locator) {
        WebElement element = findObject(locator);
        if (element== null) {
            throw new ElementNotFoundException("Element " + locator + " not found");
        }
        return element;
    }

    private WebElement findObject(String locator) {
        try {
            if (locator.startsWith("//")) {
                return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
            } else {
                return wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(locator)));
            }
        } catch (org.openqa.selenium.TimeoutException e){
            return null;
        }
    }

    public List<WebElement> bringMeAllElements(String locator) {
        return driver.findElements(By.xpath(locator));
    }

    public void clickElement(String locator) {
        find(locator).click();
    }

    public void clickElementWithXY(int indexX, int indexY) {
        Actions actions = new Actions(driver);
        actions.moveByOffset(indexX, indexY).click().perform();
    }

    public void clickElementAfterPosition(String locator) {
        Actions actions = new Actions(driver);
        Point point = find(locator).getLocation();
        actions.moveByOffset(point.getX(), point.getY()).click().perform();
    }

    public void dismissAlert() {
        try {
            driver.switchTo().alert().accept();
        } catch (NoAlertPresentException e) {
            e.printStackTrace();
        }

    }

    public void doubleClick(String locator) {
        Actions actions = new Actions(driver);
        actions.doubleClick(find(locator));
    }

    public int dropdownSize(String locator) {
        Select dropdown = new Select(find(locator));
        List<WebElement> dropdownOptions = dropdown.getOptions();
        return dropdownOptions.size();
    }

    public boolean elementIsEnabled(String locator) {
        return find(locator).isEnabled();
    }

    public boolean elementIsDisplayed(String locator) {
        return find(locator).isDisplayed();
    }

    public boolean elementIsSelected(String locator) {
        return find(locator).isSelected();
    }

    public static Scenario getScenario() {
        return scenario;
    }

    public static void setScenario(Scenario currentScenario) {
        scenario = currentScenario;
    }

    public String getValueFromTable(String locator, int row, int column) {
        String cell = locator+"/table/tbody/tr["+row+"]/td["+column+"]";
        return find(cell).getText();
    }

    public String getValueSpecificText(String locator) {
        Pattern pattern = Pattern.compile(".* of (\\d+)");
        Matcher matcher = pattern.matcher(locator);
        if (matcher.matches()) {
            return  matcher.group(1);
        }
        return "0";
    }

    public void hoverOverElement(String locator) {
        Actions actions = new Actions(driver);
        actions.moveToElement(find(locator)).perform();
    }

    public String locatorDynamicPersonalice(String part1, String part2, String part3) {
        return part1+part2+part3;
    }

    public String normalizeText(String texto) {
        return Normalizer.normalize(texto, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "");
    }

    public void rightClick(String locator) {
        Actions actions = new Actions(driver);
        actions.contextClick(find(locator));
    }

    public void scrollToElement(String locator) {
        Actions actions = new Actions(driver);
        actions.scrollToElement(find(locator)).perform();
    }

    public void selectFromDropdownByValue(String locator, String valueToSelect) {
        Select dropdown = new Select(find(locator));
        dropdown.selectByValue(valueToSelect);
    }

    public void selectFromDropdownByIndex(String locator, int valueToSelect) {
        Select dropdown = new Select(find(locator));
        dropdown.selectByIndex(valueToSelect);
    }

    public void selectFromDropdownByText(String locator, String valueToSelect) {
        Select dropdown = new Select(find(locator));
        dropdown.selectByVisibleText(valueToSelect);
    }

    public void selectFromDropdownByClick(String locator1, String locator2) {
        find(locator1).click();
        find(locator2).click();
    }

    public void selectNumberElements (String locator, int index) {
        List<WebElement> results = driver.findElements(By.xpath(locator));
        results.get(index).click();
    }

    public void setValueOnTable(String locator, int row, int column, String text) {
        String cell = locator+"/table/tbody/tr["+row+"]/td["+column+"]";
        find(cell).sendKeys(text);
    }

    public void switchToFrame(String frameId) {
        driver.switchTo().frame(find(frameId));
    }

    public void switchToParentFrame() {
        driver.switchTo().parentFrame();
    }

    public void switchToWindow(String window) {
        String mainPrincipal = driver.getWindowHandle();
        switch (window) {
            case "nextWindow":
                Set<String> handles = driver.getWindowHandles();
                for (String actual : handles) {
                    if (!actual.equalsIgnoreCase(mainPrincipal)) {
                        Dimension dimension = new Dimension(1366,1200);
                        driver.switchTo().window(actual);
                        driver.manage().window().setSize(dimension);
                    }
                }
                break;
            case "mainPrincipal":
                driver.switchTo().window(mainPrincipal);
                driver.manage().window().maximize();
                break;
            default:
                break;
        }
    }

    public String textFromElement(String locator) {
        return find(locator).getText();
    }

    public void waitForSeconds(int seconds) {
        int seg = seconds*1000;
        try {
            Thread.sleep(seg);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void write(String locator, String textToWrite) {
        find(locator).clear();
        find(locator).sendKeys(textToWrite);
    }


}
