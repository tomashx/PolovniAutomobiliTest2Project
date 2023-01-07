package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import testingData.StandardData;
import testingData.TimeDelay;

public class Registracija extends BasePageClass {

    private static final By emailLocator = By.id("email");
    private final By passwordFirstLocator = By.id("password_first");
    private final By passwordSecondLocator = By.id("password_second");

    private final By TosCheckboxLocator = By.id("tos");

    private final By easySaleConsentCheckboxLocator = By.id("easySaleConsent");

    private final By easyBuyConsentCheckboxLocator = By.id("easyBuyConsent");

    private final By registrujSeLocator = By.xpath("//button[text()='Registruj se']");

    private static final By porukaUspesnaRegistracijaLocator = By.xpath("//*[contains(@class, 'uk-width-1-1')]/div/p");

    private final By anketaRegChkFirst = By.id ("interestedInBuying");

    private final By anketaRegChkSecond = By.id ("interestedInSelling");

    private final By anketaRegChkThird = By.id ("interestedInReviewingOffer");

    private final By anketaRegPotvrdi = By.xpath ("//*[contains(@class, 'submitRegistrationSurvey')]");

    private final By anketaRegUredu = By.xpath ("//*[contains(@class, 'paBlueButtonPrimary')]");

    private final By fillNameLocator = By.id("first_name");

    private final By fillLastNameLocator = By.id("last_name");

    private final By fillAddressLocator = By.id("address");

    private final By fillCityLocator = By.id("city");

    private final By fillZipCodeLocator = By.id("zip_code");

    private final By okrugClickLocator = By.xpath("//*[@title=' Odaberite okrug']/label/i");

    private final By selectOkrugLocator = By.xpath("//label[text()='Pomoravski']");

    private final By fillCellphoneLocator = By.id("cellphone");

    private final By submitButtonLocator = By.id("submit");

    private static final By userDataSaveMessageLocator = By.xpath("//*[contains(@class, 'uk-alert')]");

    private static final By userMenuHoverLocator = By.xpath("//*[contains(@class, 'top-menu-profile')]//*[contains(@class, 'js_ga-event')]/div");

    private static final By userMenuDropDownClickLocator = By.xpath("//*[contains(@class, 'top-menu-profile')]//*[contains(@class, 'position-absolute')]");

    private static final By userMenuLogoutHoverLocator = By.xpath("//*[contains(@class, 'uk-button-dropdown')]");
    private static final By logoutLocator = By.xpath("//*[contains(@class, 'uk-nav-dropdown')]//a[contains(@class, 'js-logout-link')]");

    public String privremeniMail;


    public Registracija(WebDriver driver) {
        super(driver);
    }


    public void setRandomEmail() {
        int randomNo = (int) (Math.random() * 10000000);
        privremeniMail = StandardData.EMAIL_USER_ADDRESS + "+" + randomNo + "@" + StandardData.EMAIL_DOMAIN;
        WebElement emailTextField = locatedElementVisibleWait(emailLocator, TimeDelay.DELAY_3_SEC);
        typeInElement(emailTextField, privremeniMail);
    }


    public Registracija typePassword (String password){
        WebElement passwordTextField = locatedElementVisibleWait(passwordFirstLocator, TimeDelay.DELAY_3_SEC);
        clearTypeTextInElement(passwordTextField, password);
        return this;
    }

    public Registracija retypePassword (String password){
        WebElement passwordTextField = locatedElementVisibleWait(passwordSecondLocator, TimeDelay.DELAY_3_SEC);
        clearTypeTextInElement(passwordTextField, password);
        return this;
    }

    public Registracija tosFirstCheckboxClick(){
        buttonClick(TosCheckboxLocator);
        return this;
    }

    public Registracija easySaleConsentSecondCheckboxClick(){
        buttonClick(easySaleConsentCheckboxLocator);
        return this;
    }

    public Registracija easyBuyConsentThirdCheckboxClick(){
        buttonClick(easyBuyConsentCheckboxLocator);
        return this;
    }

    public Registracija registrujSeClick(){
        buttonClick(registrujSeLocator);
        return this;
    }

    public static String getSuccessfullRegistratonMessageText() {
        WebElement element = locatedElementVisibleWait(porukaUspesnaRegistracijaLocator, TimeDelay.DELAY_3_SEC);
        return element.getText();
    }

    public Registracija chkBoxesClick(){
        buttonClick(anketaRegChkFirst);
        buttonClick(anketaRegChkSecond);
        buttonClick(anketaRegChkThird);
        buttonClick(anketaRegPotvrdi);
        buttonClick(anketaRegUredu);
        return this;
    }

    public Registracija fillUserRequiredFields (String name, String lastName, String address, String city, String zip, String cellphone){

        WebElement nameTextField = locatedElementVisibleWait(fillNameLocator, TimeDelay.DELAY_1_MIN);
        clearTypeTextInElement(nameTextField, name);
        WebElement lastNameTextField = locatedElementVisibleWait(fillLastNameLocator, TimeDelay.DELAY_3_SEC);
        clearTypeTextInElement(lastNameTextField, lastName);
        WebElement addressTextField = locatedElementVisibleWait(fillAddressLocator, TimeDelay.DELAY_3_SEC);
        clearTypeTextInElement(addressTextField, address);
        WebElement cityTextField = locatedElementVisibleWait(fillCityLocator, TimeDelay.DELAY_3_SEC);
        clearTypeTextInElement(cityTextField, city);
        WebElement zipTextField = locatedElementVisibleWait(fillZipCodeLocator, TimeDelay.DELAY_3_SEC);
        clearTypeTextInElement(zipTextField, zip);
        WebElement cellphoneTextField = locatedElementVisibleWait(fillCellphoneLocator, TimeDelay.DELAY_3_SEC);
        clearTypeTextInElement(cellphoneTextField, cellphone);
        return this;
    }

    public Registracija clickRegion(){
        try {
            WebElement okrugClick = locatedElementVisibleWait(okrugClickLocator, TimeDelay.DELAY_3_SEC);
            boolean okrugClickIsVisible = okrugClick.isDisplayed();
            if(okrugClickIsVisible){
                buttonClick(okrugClickLocator);
            }else
                System.out.println("Ne mogu da kliknem region");
        } catch (Exception e) {
            System.out.println(e);
        }
        return this;
    }

    public Registracija selectSumadijskiOkrug(){
        try {
            WebElement okrugSelect = locatedElementVisibleWait(selectOkrugLocator, TimeDelay.DELAY_3_SEC);
            boolean okrugSelectIsVisible = okrugSelect.isDisplayed();
            if(okrugSelectIsVisible){
                buttonClick(selectOkrugLocator);
            }else
                System.out.println("Ne mogu da kliknem Sumadijski");
        }catch (Exception e) {
            System.out.println(e);
        }
        return this;
    }

    public Registracija submit(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", locatedElementVisibleWait(submitButtonLocator,TimeDelay.DELAY_3_SEC));
        buttonClick(submitButtonLocator);
        return this;
    }

    public static String getSuccessfullUserDataSaveMessageText() {
        WebElement element = locatedElementVisibleWait(userDataSaveMessageLocator, TimeDelay.DELAY_3_SEC);
        return element.getText();
    }

    public Registracija hoverUserToShowLogout() {
        WebElement element = locatedElementVisibleWait(userMenuLogoutHoverLocator, TimeDelay.DELAY_5_SEC);
        Actions hoverAction = new Actions(driver);
        hoverAction.moveToElement(element).perform();
        return this;
    }

    public Registracija userMenuDropDownClick(){
        buttonClick(userMenuDropDownClickLocator);
        return this;
    }
    public Registracija LogoutClick(){
        WebElement element = locatedElementVisibleWait(logoutLocator, TimeDelay.DELAY_5_SEC);
        buttonClickLongWait(logoutLocator);
        return this;
    }

    public String waitForOdjaviSe(){
        WebElement element = locatedElementVisibleWait(logoutLocator, TimeDelay.DELAY_1_MIN);
        return null;
    }

}