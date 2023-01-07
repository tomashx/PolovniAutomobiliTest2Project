package pages;

import org.openqa.selenium.support.ui.ExpectedCondition;
import testingData.TimeDelay;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public abstract class BasePageClass {
    protected static WebDriver driver;

    public BasePageClass(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    protected void openUrl(String url){
        driver.get(url);
    }

    protected String getCurrentUrl(){
        return driver.getCurrentUrl();
    }

    protected static boolean changeURLWait(String url, int timeout){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.urlToBe(url));
    }
    public static Boolean waitForUrl(String url, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        ExpectedCondition<Boolean> urlIsCorrect = arg0 -> driver.getCurrentUrl().equals(url);
        return wait.until(urlIsCorrect);
    }

    protected static WebElement locatedElementVisibleWait(By locator, int timeout){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void elementVisibleWait(WebElement element, int timeout){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void typeInElement(WebElement element, String text){
        elementVisibleWait(element, TimeDelay.DELAY_3_SEC);
        element.sendKeys(text);
    }

    protected void clearTypeFromWebElement(WebElement element){
        elementVisibleWait(element, TimeDelay.DELAY_3_SEC);
        element.clear();
    }

    protected void clearTypeTextInElement(WebElement element, String text){
        clearTypeFromWebElement(element);
        typeInElement(element, text);
    }

    protected void elementClick(WebElement element){
        element.click();
    }

    protected WebElement findWebElement (By locator){
        locatedElementVisibleWait(locator, TimeDelay.DELAY_1_MIN);
        WebElement element = driver.findElement(locator);
        return element;
    }

    protected void buttonClick(By locator){
        WebElement element = locatedElementVisibleWait(locator,TimeDelay.DELAY_5_SEC);
        elementClick(element);
    }
    protected void buttonClickLongWait(By locator){
        WebElement element = locatedElementVisibleWait(locator,TimeDelay.DELAY_1_MIN);
        elementClick(element);
    }



}