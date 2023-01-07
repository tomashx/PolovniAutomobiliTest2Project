package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import testingData.TimeDelay;

public class Login extends BasePageClass{

    private final By registrujSeLocator = By.xpath("//a[text()='Registruj se']");

    private final By emailFieldLocator = By.id("username_header");

    private final By passwordFieldLocator = By.id("password_header");

    private final By buttonDaljeLocator = By.id("next-step");

    private final By buttonPrijaviSeLocator = By.xpath("//button[text()='Prijavi se']");

    public Login (WebDriver driver) { super(driver); }

    public Login RegistrujSeClick(){
        buttonClick(registrujSeLocator);
        return this;
    }

    public Login typeEmail (String email){
        WebElement emailTextField = locatedElementVisibleWait(emailFieldLocator, TimeDelay.DELAY_3_SEC);
        clearTypeTextInElement(emailTextField, email);
        return this;
    }

    public Login typePassword (String password){
        WebElement passwordTextField = locatedElementVisibleWait(passwordFieldLocator, TimeDelay.DELAY_3_SEC);
        clearTypeTextInElement(passwordTextField, password);
        return this;
    }

    public Login DaljeClick(){
        buttonClick(buttonDaljeLocator);
        return this;
    }

    public Login PrijaviSeClick(){
        buttonClick(buttonPrijaviSeLocator);
        return this;
    }
}
