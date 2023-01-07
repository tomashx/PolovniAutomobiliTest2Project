package pages;

import org.openqa.selenium.*;
import testingData.StandardData;
import testingData.TimeDelay;
import java.time.Duration;


public class ProtonMail extends BasePageClass{

    private final String protonUrl = StandardData.URL_PROTON_MAIL;
    private final By protonSignInLocator = By.xpath("//a[text()='Sign in']");

    private final By emailFieldLocator = By.id("username");

    private final By passwordFieldLocator = By.id("password");

    private final By protonSignInLoginLocator = By.xpath("//button[text()='Sign in']");

    private final By unreadLocator = By.xpath("//button[text()='Unread']");
    private final By findActivationMail = By.xpath("//*[@title='noreply@polovniautomobili.com']");

    private static final By activationLinkLocator = By.xpath("//*[@id='templateLowerBody']//a[contains(@href, 'https://www.polovniautomobili.com/aktivacija-naloga')]");

    private static final By selectDropdownLocator = By.xpath("//*[contains(@class, 'toolbar-button--dropdown')]");

    private static final By selectAllMessagesLocator = By.xpath("//button[text()='Select All']");

    private static final By deleteMessageButton = By.xpath("//*[contains(@class, 'toolbar')]/div/button[3]");

    public ProtonMail (WebDriver driver) { super(driver); }


    public ProtonMail OpenProtonMailInNewTab () {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TimeDelay.DELAY_3_SEC));
        driver.switchTo().newWindow(WindowType.TAB).get(protonUrl);
        return this;
    }

    public ProtonMail SignInProtonMailClick () {
        buttonClick(protonSignInLocator);
        return this;
    }

    public ProtonMail typeEmailProtonMail (String email){
        WebElement emailTextField = locatedElementVisibleWait(emailFieldLocator, TimeDelay.DELAY_3_SEC);
        clearTypeTextInElement(emailTextField, email);
        return this;
    }

    public ProtonMail typePasswordProtonMail (String password){
        WebElement passwordTextField = locatedElementVisibleWait(passwordFieldLocator, TimeDelay.DELAY_3_SEC);
        clearTypeTextInElement(passwordTextField, password);
        return this;
    }

    public ProtonMail signInButtonClick (){
        buttonClick(protonSignInLoginLocator);
        return this;
    }

    public ProtonMail emailInboxWait(){
        waitForUrl (StandardData.INBOX_MAIL_URL, TimeDelay.DELAY_1_MIN);
        return this;
    }

    public ProtonMail unreadMailClick (){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TimeDelay.DELAY_1_MIN));
        buttonClickLongWait(unreadLocator);
        return this;
    }

    public ProtonMail activationMailClick (){
        buttonClick(findActivationMail);
        return this;
    }

    public String activationLinkClick (){
        locatedElementVisibleWait(By.tagName("iframe"), TimeDelay.DELAY_1_MIN);
        driver.switchTo().frame(0);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", locatedElementVisibleWait(activationLinkLocator,TimeDelay.DELAY_3_SEC));
        String link = locatedElementVisibleWait(activationLinkLocator,TimeDelay.DELAY_3_SEC).getText();
        driver.switchTo().defaultContent();
        return link;
    }

    public ProtonMail OpenVerificationLinkNewTab (String verificationLink) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TimeDelay.DELAY_3_SEC));
        driver.switchTo().newWindow(WindowType.TAB).get(verificationLink);
        return this;
    }


    public ProtonMail zatvoriTabove() {
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

    public ProtonMail selectAllMessages (){
        buttonClick(selectDropdownLocator);
        buttonClick(selectAllMessagesLocator);
        return this;
    }


    public ProtonMail deleteMailButtonClick () {
        buttonClick(deleteMessageButton);
        return this;
    }

}
