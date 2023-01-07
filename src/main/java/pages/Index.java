package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import testingData.StandardData;
import testingData.TimeDelay;

import java.time.Duration;

public class Index extends BasePageClass {

    private final String indexURL = StandardData.INDEX_URL;

    private final By buttonRegistracija = By.xpath("//*[contains(@class, 'top-menu-register')]");
    private final  By postaviOglasLocator = By.xpath("//*[contains(@class, 'top-menu-submit-classified')]");

    //private final By prijaviSeMenuHoverLocator = By.xpath("//*[contains(@class, 'uk-button-dropdown')]");

    private static final By userMenuDropDownClickLocator = By.xpath("//*[contains(@class, 'top-menu-profile')]//*[contains(@class, 'position-absolute')]");
    private final By prijaviSeMenuHoverLocator = By.xpath("//*[contains(@class, 'top-menu-profile')]//*[contains(@class, 'js_ga-event')]/div");
    private static final By indexPrijaviSeLocator = By.xpath("//span[text()='Moj profil']");
    private static final By logovanEmailLocator = By.xpath("//*[@class='ym-hide-content']");
    private static final By userMenuHoverLocator = By.xpath("//*[contains(@class, 'top-menu-profile')]");

    public Index (WebDriver driver) {
        super(driver);
    }

    public Index open(){
        openUrl(indexURL);
        return this;
    }


    public Index PostaviOglasClick(){
        buttonClick(postaviOglasLocator);
        return this;
    }


    public Index zatvoriTabove() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TimeDelay.DELAY_3_SEC));
        String activeTab = driver.getWindowHandle();

        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(activeTab)) {
                driver.switchTo().window(handle);
                driver.close();
            }
        }

        driver.switchTo().window(activeTab);
        return this;
    }


    public Index hoverUserToShowLogout() {
        WebElement element = driver.findElement(userMenuHoverLocator);
        Actions hoverAction = new Actions(driver);
        hoverAction.moveToElement(element).perform();
        return this;
    }
    public String getUserEmailAfterLogin() {
        WebElement element = findWebElement(logovanEmailLocator);
        return element.getText();
    }


    public String waitForPrijaviSe(){
        WebElement element = locatedElementVisibleWait(indexPrijaviSeLocator, TimeDelay.DELAY_1_MIN);
        return null;
    }

    public Index userMenuDropDownClick(){
        buttonClick(userMenuDropDownClickLocator);
        return this;
    }
}
